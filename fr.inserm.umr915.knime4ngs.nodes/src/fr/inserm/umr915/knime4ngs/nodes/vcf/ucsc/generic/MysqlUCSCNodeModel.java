package fr.inserm.umr915.knime4ngs.nodes.vcf.ucsc.generic;

import java.io.BufferedReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


import org.knime.base.data.append.column.AppendedColumnRow;
import org.knime.core.data.DataCell;
import org.knime.core.data.DataRow;
import org.knime.core.data.DataTableSpec;
import org.knime.core.data.DataType;
import org.knime.core.data.RowIterator;
import org.knime.core.data.RowKey;
import org.knime.core.data.def.IntCell;
import org.knime.core.data.def.StringCell;
import org.knime.core.node.BufferedDataContainer;
import org.knime.core.node.BufferedDataTable;
import org.knime.core.node.ExecutionContext;
import org.knime.core.node.InvalidSettingsException;
import org.knime.core.node.defaultnodesettings.SettingsModel;
import org.knime.core.node.defaultnodesettings.SettingsModelBoolean;
import org.knime.core.node.defaultnodesettings.SettingsModelColumnName;
import org.knime.core.node.defaultnodesettings.SettingsModelString;


import fr.inserm.umr915.knime4ngs.corelib.bio.Segment;
import fr.inserm.umr915.knime4ngs.corelib.knime.ExecuteException;
import fr.inserm.umr915.knime4ngs.nodes.sql.AbstractSqlNodeModel;
import fr.inserm.umr915.knime4ngs.nodes.vcf.bin.UcscBin;
import fr.inserm.umr915.knime4ngs.nodes.vcf.ucsc.generic.UcscDatabaseMysqlHandler;
import fr.inserm.umr915.knime4ngs.nodes.vcf.ucsc.generic.UcscTableMysqlHandlers;


/**
 * GenericUCSCNodeModel
 */
public class MysqlUCSCNodeModel
	extends AbstractSqlNodeModel
	{
	static final UcscTableMysqlHandlers UCSC_HANDLERS=new UcscTableMysqlHandlers();
	
	private final SettingsModelColumnName m_chrom1Col =
        new SettingsModelColumnName(CHROM1_COL_PROPERTY,DEFAULT_CHROM1_COL);
	
	final static String DEFAULT_CHROM1_COL="CHROM";
	static final String CHROM1_COL_PROPERTY="chrom1.col";
	private final SettingsModelColumnName m_posCol =
        new SettingsModelColumnName(POS_COL_PROPERTY,DEFAULT_POS_COL);
	final static String DEFAULT_POS_COL="POS";
	static final String POS_COL_PROPERTY="pos.col";
	
	final static String HANDLER_NAME_PROPERTY="ucsc.mysql.handler.name";
	static final String HANDLER_NAME_DEFAULT="";
	private final SettingsModelString m_ucscHandler =
        new SettingsModelString(HANDLER_NAME_PROPERTY,HANDLER_NAME_DEFAULT);
	
	final static String POS_ONE_BASED="position.one";
	final static boolean POS_ONE_DEFAULT=true;
	private final SettingsModelBoolean m_positionOneBased=new SettingsModelBoolean(POS_ONE_BASED,POS_ONE_DEFAULT);
	
	
	class BedSorter
	implements Comparator<DataCell[]>
		{
		private int chromCol;
		private int chromStartCol;
		private int chromEndCol;
		
		
		public BedSorter(int chromCol, int chromStartCol, int chromEndCol)
			{
			this.chromCol = chromCol;
			if(this.chromCol<0) throw new IllegalArgumentException("chrom=-1");
			this.chromStartCol = chromStartCol;
			if(this.chromStartCol<0) throw new IllegalArgumentException("chromStartCol=-1");
			this.chromEndCol = chromEndCol;
			if(this.chromEndCol<0) throw new IllegalArgumentException("chromEndCol=-1");
			}
		
		
		
		public Segment make(DataCell array[])
			{
			if(array.length<= chromCol) throw new IllegalArgumentException("no chrom column in "+array);
			if(array.length<= chromStartCol) throw new IllegalArgumentException("no chromStart column in "+array);
			if(array.length<= chromEndCol) throw new IllegalArgumentException("no chromEnd column in "+array);
			if(array[chromCol].isMissing()) return null;
			if(array[chromStartCol].isMissing()) return null;
			if(array[chromEndCol].isMissing()) return null;
			String chrom=StringCell.class.cast(array[chromCol]).getStringValue();
			int start=IntCell.class.cast(array[chromStartCol]).getIntValue();
			int end=IntCell.class.cast(array[chromEndCol]).getIntValue();
			return new Segment(chrom, start+1,end-1);//move to a 1-based record
			}
		@Override
		public int compare(DataCell o1[],DataCell o2[]) {
			return make(o1).compareTo(make(o2));
			}

		}
	
    /**
     * Constructor for the node model.
     */
    public MysqlUCSCNodeModel()
    	{
        super(1,1);
    	}
    
    
    @Override
    protected BufferedDataTable[] execute(
    		final BufferedDataTable[] inData,
            final ExecutionContext exec
            ) throws Exception
            {
			BufferedDataContainer container1=null;
			if(m_ucscHandler.getStringValue().isEmpty())
	    		{
	    		throw new InvalidSettingsException("UCSC database was not selected");
	    		}
	    	UcscDatabaseMysqlHandler handler=UCSC_HANDLERS.getHandlerById(m_ucscHandler.getStringValue());
	    	if(m_ucscHandler.getStringValue().isEmpty())
				{
				throw new InvalidSettingsException("undefined handler "+m_ucscHandler.getStringValue());
				}
			
			
			
			
			Connection con=null;
			PreparedStatement pstmt=null;
			ResultSet resultset=null;
			try
		    	{
				BufferedDataTable vcfTable=inData[0];
				DataTableSpec inDataTableSpec1 =vcfTable.getDataTableSpec();
				DataTableSpec inDataTableSpec2=handler.getDataTableSpec();
				
				container1=exec.createDataContainer(new DataTableSpec(
						inDataTableSpec1,
						inDataTableSpec2
					));
				int chromCol=findColumnIndex(inDataTableSpec1,this.m_chrom1Col,StringCell.TYPE);
				int posCol=findColumnIndex(inDataTableSpec1,this.m_posCol,IntCell.TYPE);
				
				con=createConnection();		     
				
		        pstmt=con.prepareStatement(handler.getPreparedStatement());
		        double total=vcfTable.getRowCount();
		        int nRow=0;
		        int outCount=0;
		        
		        RowIterator itervcf=null;
		        
		        BufferedReader in=null;
		        try {
		        	itervcf=vcfTable.iterator();
		        	while(itervcf.hasNext())
		        		{
		        		++nRow;
		        		DataRow row=itervcf.next();
		        		//System.err.println(row);
		        		boolean found=false;
		        		
		        		
		        		
		        		do
		        		{
		        		if(row.getCell(chromCol).isMissing())
		        			{
		        			break;
		        			}
		        		if(row.getCell(posCol).isMissing())
		        			{
		        			break;
		        			}
		        		
		        		
		        		
		        		String chrom=StringCell.class.cast(row.getCell(chromCol)).getStringValue();
		        		int pos=IntCell.class.cast(row.getCell(posCol)).getIntValue();
		        		pstmt.setString(1, chrom);
		        		if(m_positionOneBased.getBooleanValue())
			        		{
			        		pstmt.setInt(2, pos-1);
			        		pstmt.setInt(3, pos-1);
			        		}
		        		else
		        			{
		        			pstmt.setInt(2, pos);
			        		pstmt.setInt(3, pos);
		        			}
		        		Integer bins[]=null;
		        		int binIndex=0;
		        		if(handler.getBinColumn()!=-1)
		        			{
		        			if(m_positionOneBased.getBooleanValue())
			        			{
		        				bins=UcscBin.getBins(pos-1, pos);
			        			}
		        			else
		        				{
		        				bins=UcscBin.getBins(pos, pos);
		        				}
		        			}
		        		//loop over all the bins (if any)
		        		for(;;)
			        		{
		        			//System.err.println("for");
			        		if(bins!=null)
			        			{
			        			//System.err.println("bin "+bins[binIndex]);
			        			pstmt.setInt(4, bins[binIndex]);
			        			binIndex++;
			        			}
			        		
			        		resultset=pstmt.executeQuery();
			        		while(resultset.next())
			        			{
			        			//System.err.println(outCount);
			        			DataCell cells[]=handler.parse(resultset);
			        			container1.addRowToTable(new AppendedColumnRow(
				        				RowKey.createRowKey(++outCount),	
				        				row,
				        				cells
				        				));
			        			found=true;
			        			}
			        		safeClose(resultset);
			        		if(bins==null || binIndex>=bins.length) break;
			        		}
		        		} while(false);
		        		
		        		
		        		if(!found)
							{
							DataCell empty[]=new DataCell[inDataTableSpec2.getNumColumns()];
		        			for(int i=0;i< empty.length;++i)
		        				{
		        				empty[i]=DataType.getMissingCell();
		        				}
							container1.addRowToTable(new AppendedColumnRow(
			        				RowKey.createRowKey(++outCount),	
			        				row,
			        				empty
			        				));
							}
		        		exec.checkCanceled();
		            	exec.setProgress(nRow/total,"SQL....");
		        		}
					} 
		        catch (Exception e)
					{
		        	e.printStackTrace();
					throw e;
					}
				finally
					{
					safeClose(in);
					safeClose(itervcf);
					}
		        if(container1==null)
		        	{
		        	throw new ExecuteException("No data in file to define a valid output table");
		        	}
				// once we are done, we close the container and return its table
		        container1.close();
		        BufferedDataTable out1 = container1.getTable();
		        container1=null;
		        
		      
		        BufferedDataTable array[]= new BufferedDataTable[]{out1};
		    	return array;
		    	}
		catch(Exception err)
			{
			getLogger().error("Boum", err);
			err.printStackTrace();
			throw err;
			}
		finally
			{
			safeClose(resultset);
			safeClose(pstmt);
			safeClose(con);
			safeClose(container1);
			}
       }
    
    @Override
    protected DataTableSpec[] configure(DataTableSpec[] inSpecs)
    		throws InvalidSettingsException {
    	if(inSpecs==null || inSpecs.length!=1)
    		{
    		throw new InvalidSettingsException("Expected one tables");
    		}
    	if(m_ucscHandler.getStringValue().isEmpty())
    		{
    		throw new InvalidSettingsException("UCSC database was not selected");
    		}
    	UcscDatabaseMysqlHandler handler=UCSC_HANDLERS.getHandlerById(m_ucscHandler.getStringValue());
    	if(m_ucscHandler.getStringValue().isEmpty())
			{
			throw new InvalidSettingsException("undefined handler "+m_ucscHandler.getStringValue());
			}
    	DataTableSpec in=inSpecs[0];
    	findColumnIndex(in,this.m_chrom1Col,StringCell.TYPE);
		findColumnIndex(in, this.m_posCol,IntCell.TYPE);
    	
    	return new DataTableSpec[]{new DataTableSpec(in,handler.getDataTableSpec())};
    	}
    
    @Override
    protected List<SettingsModel> getSettingsModel() {
    	List<SettingsModel> L=new ArrayList<SettingsModel>( super.getSettingsModel());
    	L.add(this.m_chrom1Col);
    	L.add(this.m_posCol);
    	L.add(this.m_ucscHandler);
    	L.add(this.m_positionOneBased);
    	return L;
    	}
	}


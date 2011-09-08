package fr.inserm.umr915.knime4ngs.nodes.vcf.bbfile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;



import org.broad.igv.bbfile.BBFileHeader;
import org.broad.igv.bbfile.BBFileReader;
import org.broad.igv.bbfile.BedFeature;
import org.broad.igv.bbfile.BigBedIterator;
import org.broad.igv.bbfile.BigWigIterator;
import org.broad.igv.bbfile.WigItem;
import org.knime.base.data.append.column.AppendedColumnRow;
import org.knime.core.data.DataCell;
import org.knime.core.data.DataColumnSpec;
import org.knime.core.data.DataColumnSpecCreator;
import org.knime.core.data.DataRow;
import org.knime.core.data.DataTableSpec;
import org.knime.core.data.DataType;
import org.knime.core.data.RowIterator;
import org.knime.core.data.RowKey;


import org.knime.core.data.def.DoubleCell;
import org.knime.core.data.def.IntCell;
import org.knime.core.data.def.StringCell;
import org.knime.core.node.BufferedDataContainer;
import org.knime.core.node.BufferedDataTable;
import org.knime.core.node.ExecutionContext;
import org.knime.core.node.InvalidSettingsException;
import org.knime.core.node.defaultnodesettings.SettingsModel;
import org.knime.core.node.defaultnodesettings.SettingsModelColumnName;
import org.knime.core.node.defaultnodesettings.SettingsModelString;

import fr.inserm.umr915.knime4ngs.corelib.bio.Position;
import fr.inserm.umr915.knime4ngs.corelib.bio.PositionKSorter;
import fr.inserm.umr915.knime4ngs.corelib.knime.ExecuteException;
import fr.inserm.umr915.knime4ngs.nodes.vcf.AbstractVCFNodeModel;

/**
 * Model for WIG file
 */
public class BigBedFileNodeModel extends AbstractVCFNodeModel
	{
	
	final static String DEFAULT_CHROM1_COL="CHROM";
	static final String CHROM1_COL_PROPERTY="chrom.col";
	private final SettingsModelColumnName m_chromCol =
        new SettingsModelColumnName(CHROM1_COL_PROPERTY,DEFAULT_CHROM1_COL);

	final static String DEFAULT_POS1_COL="POS";
	static final String POS1_COL_PROPERTY="pos.col";
	private final SettingsModelColumnName m_posCol =
        new SettingsModelColumnName(POS1_COL_PROPERTY,DEFAULT_POS1_COL);
	

	final static String DEFAULT_WIG_URI="file.wig";
	static final String WIG_URI_PROPERTY="wig.uri";
	private final SettingsModelString m_wigUri =
        new SettingsModelString(WIG_URI_PROPERTY,DEFAULT_WIG_URI);
	
	final static String WIG_COLNAME_DEF="big";
	static final String  WIG_COLNAME="col.name";
	private final SettingsModelString m_newColName =
        new SettingsModelString(WIG_COLNAME,WIG_COLNAME_DEF);
	
	
    /**
     * Constructor for the node model.
     */
    public BigBedFileNodeModel()
    	{
        super(1,1);
    	}
    
    private DataTableSpec createDataTableSpec(boolean isBed) throws InvalidSettingsException
    	{
    	DataColumnSpec cols[];
    	if(isBed)
	    	{
	    	cols=new DataColumnSpec[]{
	    		new DataColumnSpecCreator(this.m_newColName.getStringValue()+".start",IntCell.TYPE).createSpec(),
	    		new DataColumnSpecCreator(this.m_newColName.getStringValue()+".end",IntCell.TYPE).createSpec(),
	    		new DataColumnSpecCreator(this.m_newColName.getStringValue()+".rest",StringCell.TYPE).createSpec()
	    		};
	    	}
		else
			{
			cols=new DataColumnSpec[]{
	    		new DataColumnSpecCreator(this.m_newColName.getStringValue()+".start",IntCell.TYPE).createSpec(),
	    		new DataColumnSpecCreator(this.m_newColName.getStringValue()+".end",IntCell.TYPE).createSpec(),
	    		new DataColumnSpecCreator(this.m_newColName.getStringValue()+".value",DoubleCell.TYPE).createSpec()
	    		};
			}
    	return new DataTableSpec(cols);
    	}
    protected DataTableSpec createDataTableSpec() throws InvalidSettingsException
    	{
    	String uri=m_wigUri.getStringValue();
    	if(uri.isEmpty()) throw new InvalidSettingsException("Undefined big wig uri");
		//open big file
		BBFileReader reader=null;
		try {
			reader=new BBFileReader(uri);
		} catch (Exception e)
			{
			throw new InvalidSettingsException(e);
			}
		//get the big header
		BBFileHeader bbFileHdr = reader.getBBFileHeader();
		if(!bbFileHdr.isHeaderOK())
			{
			throw new InvalidSettingsException("bad header for "+uri);
			}
		
    	if(!(bbFileHdr.isBigBed() || bbFileHdr.isBigWig()))
	    	{
    		throw new InvalidSettingsException("undefined big type for "+uri);
    		}
    	return createDataTableSpec(bbFileHdr.isBigBed());
    	}
    
    @Override
    protected BufferedDataTable[] execute(
    		final BufferedDataTable[] inData,
            final ExecutionContext exec
            ) throws Exception
            {
			BufferedDataContainer container1=null;
			try
		    	{
				String uri=m_wigUri.getStringValue();
		    	if(uri.isEmpty()) throw new ExecuteException("Undefined big wig uri");
				//open big file
				BBFileReader reader=null;
				try {
					reader=new BBFileReader(uri);
				} catch (Exception e)
					{
					throw new InvalidSettingsException(e);
					}
				//get the big header
				BBFileHeader bbFileHdr = reader.getBBFileHeader();
				if(!bbFileHdr.isHeaderOK())
					{
					throw new ExecuteException("bad header for "+uri);
					}
				
				//is it wig or bed ?
				if(!(bbFileHdr.isBigBed() || bbFileHdr.isBigWig()))
					{
					throw new IOException("undefined big type for "+uri);
					}
				
				boolean isbigBed=bbFileHdr.isBigBed();
				//get all chromosomes
				final Set<String> chromosomes=new HashSet<String>(reader.getChromosomeNames());
				
				
				BufferedDataTable table1=inData[0];
				DataTableSpec inspec1 = table1.getDataTableSpec();
				
				
				DataTableSpec merged=new DataTableSpec(inspec1,createDataTableSpec(isbigBed));
		        container1 = exec.createDataContainer(merged);
		        
		        
		        double total=table1.getRowCount();
		       
		        RowIterator iter=null;
		       PositionKSorter posksorter=new PositionKSorter(
		    		   findColumnIndex(inspec1, m_chromCol,StringCell.TYPE),
		    	    	findColumnIndex(inspec1, m_posCol,IntCell.TYPE)
		    		   );
		        int outIndex=0;
		        final boolean contained=false;
		        int nRow=0;
		        try {
		        
		        	iter=table1.iterator();
		        	while(iter.hasNext())
			        	{
		        		++nRow;
			        	DataRow row=iter.next();
			        	Position pos=posksorter.make(row);
			        	boolean found=false;
			        	if(pos!=null && chromosomes.contains(pos.getChromosome()))
			        		{
			        		if(isbigBed)
			        			{
			        			BigBedIterator bigiter=reader.getBigBedIterator(pos.getChromosome(),pos.getPosition()-1,pos.getChromosome(),pos.getPosition(),contained);
			        			//loop over iterator
			        			while(bigiter.hasNext())
			        				{
			        				BedFeature f=bigiter.next();
			        				++outIndex;
			        				
			        				DataCell cell;
			        				if( f.getRestOfFields()==null ||
			        					f.getRestOfFields().length==0 ||
			        					(f.getRestOfFields().length==1 && f.getRestOfFields()[0].isEmpty()))
			        					{
			        					cell=DataType.getMissingCell();
			        					}
			        				else
			        					{
			        					StringBuilder toks=new StringBuilder();
			        					for(String s:f.getRestOfFields())
			        						{
			        						if(toks.length()!=0) toks.append('|');
			        						toks.append(s);
			        						}
			        					cell=new StringCell(toks.toString());
			        					}
			        				
			        				container1.addRowToTable(new AppendedColumnRow(RowKey.createRowKey(outIndex),row,
			        					new IntCell(f.getStartBase()),
			        					new IntCell(f.getEndBase()),
			        					cell
			        					));
			        				found=true;
			        				}
			        			}
			        		else
			        			{
			        			BigWigIterator bigiter=reader.getBigWigIterator(pos.getChromosome(),pos.getPosition()-1,pos.getChromosome(),pos.getPosition(),contained);
			        			//loop over iterator
			        			while(bigiter.hasNext())
			        				{
			        				WigItem f=bigiter.next();
			        				++outIndex;
			        				container1.addRowToTable(new AppendedColumnRow(RowKey.createRowKey(outIndex),row,
			        					new IntCell(f.getStartBase()),
			        					new IntCell(f.getEndBase()),
			        					new DoubleCell(f.getWigValue())
			        					));
			        				found=true;
			        				}
			        			}
			        		}
			        	
			        	
			        	
			        	if(!found)
			        		{
			        		++outIndex;
			        		DataCell empty[]=new DataCell[]{DataType.getMissingCell(),DataType.getMissingCell(),DataType.getMissingCell()};
			        		container1.addRowToTable(new AppendedColumnRow(RowKey.createRowKey(outIndex),row, empty));
			        		}
			        
			        	exec.checkCanceled();
		            	exec.setProgress(nRow/total,"Big....");
			        	}
		        
					} 
		        catch (Exception e)
					{
		        	e.printStackTrace();
					throw e;
					}
				finally
					{
					safeClose(iter);
					}
		        
				// once we are done, we close the container and return its table
				safeClose(container1);
				
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
			safeClose(container1);
			}
       }
    
    @Override
    protected DataTableSpec[] configure(DataTableSpec[] inSpecs)
    		throws InvalidSettingsException {
    	if(inSpecs==null || inSpecs.length!=1)
    		{
    		throw new InvalidSettingsException("Expected one table");
    		}
    	DataTableSpec in=inSpecs[0];
    	findColumnIndex(in, m_chromCol,StringCell.TYPE);
    	findColumnIndex(in, m_posCol,IntCell.TYPE);
    	return new DataTableSpec[]{new DataTableSpec(in,createDataTableSpec())};
    	}
    
    @Override
    protected List<SettingsModel> getSettingsModel() {
    	List<SettingsModel> L=new ArrayList<SettingsModel>( super.getSettingsModel());
    	L.add(this.m_chromCol);
    	L.add(this.m_posCol);
    	L.add(this.m_wigUri);
    	L.add(this.m_newColName);
    	return L;
    	}
	}


package fr.inserm.umr915.knime4ngs.nodes.vcf.tabix;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;



import org.knime.base.data.append.column.AppendedColumnRow;
import org.knime.core.data.DataCell;
import org.knime.core.data.DataColumnSpec;
import org.knime.core.data.DataColumnSpecCreator;
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
import org.knime.core.node.defaultnodesettings.SettingsModelColumnName;
import org.knime.core.node.defaultnodesettings.SettingsModelString;

import fr.inserm.umr915.knime4ngs.nodes.vcf.AbstractVCFNodeModel;





/**
 * TabixNodeModel
 */
public class TabixNodeModel extends AbstractVCFNodeModel
	{
	
	final static String DEFAULT_CHROM1_COL="CHROM";
	static final String CHROM1_COL_PROPERTY="chrom1.col";
	private final SettingsModelColumnName m_chrom1Col =
        new SettingsModelColumnName(CHROM1_COL_PROPERTY,DEFAULT_CHROM1_COL);

	final static String DEFAULT_POS1_COL="POS";
	static final String POS1_COL_PROPERTY="pos1.col";
	private final SettingsModelColumnName m_pos1Col =
        new SettingsModelColumnName(POS1_COL_PROPERTY,DEFAULT_POS1_COL);
	
	
	final static String DEFAULT_TABIX_FILENAME="data.gz";
	static final String TABIX_FILENAME_PROPERTY="filename.tabix";
	private final SettingsModelString m_tabixFilename =
        new SettingsModelString(TABIX_FILENAME_PROPERTY,DEFAULT_TABIX_FILENAME);
	
	
	final static String DEFAULT_TABIX_PREFIX="tabix";
	static final String TABIX_PREFIX_PROPERTY="tabix.prefix";
	private final SettingsModelString m_tabixPrefix =
        new SettingsModelString(TABIX_PREFIX_PROPERTY,DEFAULT_TABIX_PREFIX);
		
    /**
     * Constructor for the node model.
     */
    protected TabixNodeModel()
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
			try
		    	{
		        // the data table spec of the single output table, 
		        // the table will have three columns:
				BufferedDataTable table1=inData[0];
		       
				DataTableSpec inDataTableSpec1 = table1.getDataTableSpec();
				DataTableSpec inDataTableSpec2 = getDataTableSpec();

				int chromCol = findColumnIndex(inDataTableSpec1, m_chrom1Col,StringCell.TYPE);
		    	int posCol= findColumnIndex(inDataTableSpec1, m_pos1Col,IntCell.TYPE);
				
				DataTableSpec merged=new DataTableSpec(inDataTableSpec1,inDataTableSpec2);
		        container1 = exec.createDataContainer(merged);
		        
		        double total=table1.getRowCount();
		        int nRow=0;
		        RowIterator iter=null;
		        int outIndex=0;
		        TabixReader tabReader=null;
		        final Pattern tab=Pattern.compile("[\t]");
		        try {
		        	tabReader=new TabixReader(this.m_tabixFilename.getStringValue());
		        	
		        	iter=table1.iterator();
		        	
		        	while(iter.hasNext())
		        		{
		        		++nRow;
		        		DataRow row=iter.next();
		        		
		        		boolean found=false;
		        		if( !row.getCell(chromCol).isMissing() &&
		        			!row.getCell(posCol).isMissing() &&
		        			tabReader.containsChromosome(StringCell.class.cast(row.getCell(chromCol)).getStringValue()))
		        			{
		        			String chrom = StringCell.class.cast(row.getCell(chromCol)).getStringValue();
		        			int position1 = IntCell.class.cast(row.getCell(posCol)).getIntValue();
		        			TabixReader.Iterator iterator=tabReader.query(chrom+":"+position1+"-"+position1);
		        			String line;
		        			while(iterator!=null &&
		        				(line=iterator.next())!=null)
		        				{
		        				
		        				//System.err.println("match\n\t"+line+" vs\n\t"+row+"\n\t"+nRow);
		        				String tokens[]=tab.split(line);
		        				DataCell cells[]=new DataCell[inDataTableSpec2.getNumColumns()];
			        			for(int i=0;i< cells.length;++i)
			        				{
			        				if(i< tokens.length && !tokens[i].isEmpty())
			        					{
			        					cells[i]=new StringCell(tokens[i]);
			        					}
			        				else
			        					{
			        					cells[i]=DataType.getMissingCell();
			        					}
			        				}
			        			found=true;
			        			outIndex++;
			        			container1.addRowToTable(new AppendedColumnRow(
				        				RowKey.createRowKey(outIndex),	
				        				row, cells));
		        				}
		        			}
		        		
						if(!found)
							{
							//System.err.println("nothing for "+row);
							DataCell empty[]=new DataCell[inDataTableSpec2.getNumColumns()];
		        			for(int i=0;i< empty.length;++i)
		        				{
		        				empty[i]=DataType.getMissingCell();
		        				}
		        			outIndex++;
		        			container1.addRowToTable(new AppendedColumnRow(
			        				RowKey.createRowKey(outIndex),	
			        				row, empty));
							
							}
						
		        		exec.checkCanceled();
		            	exec.setProgress(nRow/total,"Joining....");
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
    
    
    private DataTableSpec getDataTableSpec()
    	throws InvalidSettingsException
    	{
    	File f=new File(this.m_tabixFilename.getStringValue());
    	if(!f.exists() || !f.isFile()) throw new InvalidSettingsException("Bad file:"+f);
    	try
	    	{
    		int max=1;
    		Pattern tab=Pattern.compile("[\t]");
	    	TabixReader r=new TabixReader(this.m_tabixFilename.getStringValue());
	    	String line;
	    	int nLine=0;
	    	while((line=r.readLine())!=null)
	    		{
	    		int count=tab.split(line).length;
	    		max=Math.max(max, count);
	    		if(nLine++> 5000) break;
	    		}
	    	DataColumnSpec cols[]=new DataColumnSpec[max];
	    	for(int i=0;i< max;++i)
	    		{
	    		cols[i]=new DataColumnSpecCreator(this.m_tabixPrefix.getStringValue()+"."+(i+1),StringCell.TYPE).createSpec();
	    		}
	    	DataTableSpec spec=new DataTableSpec(cols);
	    	return spec;
	    	}
    	catch(Exception err)
    		{
    		throw new InvalidSettingsException(err);
    		}
    	}
    
    @Override
    protected DataTableSpec[] configure(DataTableSpec[] inSpecs)
    		throws InvalidSettingsException {
    	if(inSpecs==null || inSpecs.length!=1)
    		{
    		throw new InvalidSettingsException("Expected one table");
    		}
    	findColumnIndex(inSpecs[0], m_chrom1Col,StringCell.TYPE);
    	findColumnIndex(inSpecs[0], m_pos1Col,IntCell.TYPE);
    	DataTableSpec out1=inSpecs[0];
    	DataTableSpec out2=getDataTableSpec();
    	return new DataTableSpec[]{new DataTableSpec(out1,out2)};
    	}
    
    @Override
    protected List<SettingsModel> getSettingsModel() {
    	List<SettingsModel> L=new ArrayList<SettingsModel>( super.getSettingsModel());
    	L.add(this.m_chrom1Col);
    	L.add(this.m_pos1Col);
    	L.add(this.m_tabixFilename);
    	L.add(this.m_tabixPrefix);
    	return L;
    	}
	}


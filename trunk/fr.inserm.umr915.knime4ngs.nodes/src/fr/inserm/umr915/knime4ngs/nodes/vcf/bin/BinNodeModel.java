package fr.inserm.umr915.knime4ngs.nodes.vcf.bin;

import java.util.ArrayList;
import java.util.List;
import org.knime.base.data.append.column.AppendedColumnRow;
import org.knime.core.data.DataCell;
import org.knime.core.data.DataColumnSpecCreator;
import org.knime.core.data.DataRow;
import org.knime.core.data.DataTableSpec;
import org.knime.core.data.DataType;
import org.knime.core.data.container.CloseableRowIterator;
import org.knime.core.data.def.IntCell;
import org.knime.core.node.BufferedDataContainer;
import org.knime.core.node.BufferedDataTable;
import org.knime.core.node.ExecutionContext;
import org.knime.core.node.InvalidSettingsException;
import org.knime.core.node.defaultnodesettings.SettingsModel;
import org.knime.core.node.defaultnodesettings.SettingsModelBoolean;
import org.knime.core.node.defaultnodesettings.SettingsModelColumnName;
import org.knime.core.node.defaultnodesettings.SettingsModelString;
import fr.inserm.umr915.knime4ngs.nodes.vcf.AbstractVCFNodeModel;



/**
 * This is the model implementation of VCFSource.
 * Reads a VCF file
 *
 * @author Pierre Lindenbaum
 */
public class BinNodeModel extends AbstractVCFNodeModel
	{
	static final String COL_START_CONFIG="col.start";
	static final String COL_START_DEFAULT="POS";
	private final SettingsModelColumnName m_colStart=new SettingsModelColumnName(
		COL_START_CONFIG,
		COL_START_DEFAULT
		);
	
	static final String COL_END_CONFIG="col.end";
	static final String COL_END_DEFAULT=COL_START_DEFAULT;//same
	private final SettingsModelColumnName m_colEnd=new SettingsModelColumnName(
		COL_END_CONFIG,
		COL_END_DEFAULT
		);
	
	static final String INDEX1_CONFIG="first.is.1";
	static final boolean INDEX1_DEFAULT=true;
	private final SettingsModelBoolean m_Index1=new SettingsModelBoolean(
			INDEX1_CONFIG,
			INDEX1_DEFAULT
			);
	
	static final String BINNAME_CONFIG="bin.column.name";
	static final String BINNAME_DEFAULT="bin";
	private final SettingsModelString m_binName=new SettingsModelString(
			BINNAME_CONFIG,
			BINNAME_DEFAULT
			);
	
	
    /**
     * Constructor for the node model.
     */
    protected BinNodeModel()
    	{
        super(1,1);
    	}
    
    
    private DataTableSpec createDataTableSpec(DataTableSpec inDataTableSpec)
    	{
    	DataTableSpec spec2=new DataTableSpec(
    		new DataColumnSpecCreator(m_binName.getStringValue(), IntCell.TYPE).createSpec()
    		);
    	return new DataTableSpec(inDataTableSpec.getName()+"+ bin",
    			inDataTableSpec,
    			spec2
    		);
    	
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
				BufferedDataTable inTable=inData[0];
				DataTableSpec inDataTableSpec = inTable.getDataTableSpec();
		        int colStart= findColumnIndex(inDataTableSpec,m_colStart,IntCell.TYPE);
		        int colEnd= findColumnIndex(inDataTableSpec,m_colEnd,IntCell.TYPE);
				
		        container1 = exec.createDataContainer(createDataTableSpec(inDataTableSpec));
		      
		        
		     
		        
		     
		        double total=inTable.getRowCount();
		        int nRow=0;
		        CloseableRowIterator iter=null;
		        try {
		        	iter=inTable.iterator();
		        	while(iter.hasNext())
		        		{
		        		++nRow;
		        		
		        		DataRow row=iter.next();
		        		DataCell cellStart=row.getCell(colStart);
		        		DataCell cellEnd=row.getCell(colEnd);
		        		DataCell binCell=DataType.getMissingCell();
		        		Integer start=null;
		        		Integer end=null;
		        		Integer bin=null;
		        		if( !cellStart.isMissing() &&
		        			!cellEnd.isMissing())
		        			{
		        			start= IntCell.class.cast(cellStart).getIntValue();
		        			end= IntCell.class.cast(cellEnd).getIntValue();
		        			if(m_Index1.getBooleanValue())
		        				{
		        				start--;
		        				end--;
		        				}
		        			if(start>=0 && end>=start)
			        			{
		        				if(colStart==colEnd) end++;
			        			bin=UcscBin.getBin(start, end);
			        			binCell=new IntCell(bin);
			        			}
		        			}
		        		row= new AppendedColumnRow(row,binCell);
						
						container1.addRowToTable(row);
		        		exec.checkCanceled();
		            	exec.setProgress(nRow/total,"Bin....");
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
    	findColumnIndex(inSpecs[0],m_colStart,IntCell.TYPE);
        findColumnIndex(inSpecs[0],m_colEnd,IntCell.TYPE);
    	return new DataTableSpec[]{createDataTableSpec(inSpecs[0])};
    	}
    
    @Override
    protected List<SettingsModel> getSettingsModel() {
    	List<SettingsModel> L=new ArrayList<SettingsModel>( super.getSettingsModel());
    	L.add(this.m_binName);
    	L.add(this.m_colStart);
    	L.add(this.m_colEnd);
    	L.add(this.m_Index1);
    	return L;
    	}
	}


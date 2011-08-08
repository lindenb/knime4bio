package fr.inserm.umr915.knime4ngs.nodes.vcf.sum;

import java.util.ArrayList;
import java.util.List;



import org.knime.base.data.append.column.AppendedColumnRow;
import org.knime.core.data.DataCell;
import org.knime.core.data.DataColumnSpec;
import org.knime.core.data.DataColumnSpecCreator;
import org.knime.core.data.DataRow;
import org.knime.core.data.DataTableSpec;
import org.knime.core.data.DataType;
import org.knime.core.data.RowIterator;

import org.knime.core.data.def.BooleanCell;
import org.knime.core.data.def.DoubleCell;
import org.knime.core.data.def.IntCell;
import org.knime.core.data.def.LongCell;
import org.knime.core.node.BufferedDataContainer;
import org.knime.core.node.BufferedDataTable;
import org.knime.core.node.ExecutionContext;
import org.knime.core.node.InvalidSettingsException;
import org.knime.core.node.defaultnodesettings.SettingsModel;
import org.knime.core.node.defaultnodesettings.SettingsModelFilterString;
import org.knime.core.node.defaultnodesettings.SettingsModelString;

import fr.inserm.umr915.knime4ngs.corelib.knime.ExecuteException;
import fr.inserm.umr915.knime4ngs.nodes.vcf.AbstractVCFNodeModel;


/**
 * SumNodeModel
 */
public class SumNodeModel extends AbstractVCFNodeModel
	{
	static final String NUMERIC_COLS_PROPERTY="numeric.calls";
	private final SettingsModelFilterString m_numericColumns =
        new SettingsModelFilterString(
        		NUMERIC_COLS_PROPERTY,
        		new String[0],
        		new String[0]
        		);
	
	
	final static String DEFAULT_FLAG="SUM";
	static final String FLAG_PROPERTY="flag";
	private final SettingsModelString m_flag =
        new SettingsModelString(FLAG_PROPERTY,DEFAULT_FLAG);

	
    /**
     * Constructor for the node model.
     */
    protected SumNodeModel()
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
				DataTableSpec spec2 = createDataTableSpec();

				int callCols[]=new int[m_numericColumns.getIncludeList().size()];
				
				for(int i=0;i< callCols.length;++i)
					{
					String s=m_numericColumns.getIncludeList().get(i);
					callCols[i]= inDataTableSpec1.findColumnIndex(s);
					if(callCols[i]==-1) throw new ExecuteException("cannot find "+s);
					}
				
				
				DataTableSpec merged=new DataTableSpec(inDataTableSpec1,spec2);
		        container1 = exec.createDataContainer(merged);
		        double total=table1.getRowCount();
		        int nRow=0;
		        RowIterator iter1=null;
		       
		        try {
		        	iter1=table1.iterator();
		     
		        	while(iter1.hasNext())
		        		{
		        		++nRow;
		        		DataRow row=iter1.next();
		        		DataCell appendcells[]=new DataCell[spec2.getNumColumns()];
		        		
		        		
		        		for(int tagIdx=0;tagIdx<appendcells.length;++tagIdx)
		        			{
		        			appendcells[tagIdx]=DataType.getMissingCell();
		        			}
		        		
		        		Double min=null;
		        		Double max=null;
		        		double sum=0;
		        		int count=0;
		        		for(int j=0;j< callCols.length;++j)
			        		{
		        			DataCell cell=row.getCell(callCols[j]);
		        			if(cell.isMissing()) continue;
		        			double v=0;
		        			if(cell.getType().equals(DoubleCell.TYPE))
		        				{
		        				v= DoubleCell.class.cast(cell).getDoubleValue();
		        				}
		        			else if(cell.getType().equals(LongCell.TYPE))
		        				{
		        				v= LongCell.class.cast(cell).getLongValue();
		        				}
		        			else if(cell.getType().equals(IntCell.TYPE))
		        				{
		        				v= IntCell.class.cast(cell).getIntValue();
		        				}
		        			else if(cell.getType().equals(BooleanCell.TYPE))
		        				{
		        				v= BooleanCell.class.cast(cell).getIntValue();
		        				}
		        			else
		        				{
		        				throw new ExecuteException("bad type "+cell.getType());
		        				}
		        			++count;
		        			if(min==null || min>v) min=v;
		        			if(max==null || max<v) max=v;
		        			sum+=v;
			        		}
		        		
		        		if(count>0)
		        			{
		        			appendcells[0]=new IntCell(count);
		        			appendcells[1]=new DoubleCell(sum);
		        			appendcells[2]=new DoubleCell(sum/count);
		        			appendcells[3]=new DoubleCell(min);
		        			appendcells[4]=new DoubleCell(max);
		        			}
		        		
		        		container1.addRowToTable(new AppendedColumnRow(row,appendcells));
		        		exec.checkCanceled();
		            	exec.setProgress(nRow/total,"Computing....");
		        		}
					} 
		        catch (Exception e)
					{
		        	e.printStackTrace();
					throw e;
					}
				finally
					{
					safeClose(iter1);
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

    
    private DataTableSpec createDataTableSpec() throws InvalidSettingsException
    	{
    	String tag=this.m_flag.getStringValue();
    	DataColumnSpec cols[]=new DataColumnSpec[5];
	    cols[0]=new DataColumnSpecCreator(tag+":count.non.null",IntCell.TYPE).createSpec();
	    cols[1]=new DataColumnSpecCreator(tag+":sum",DoubleCell.TYPE).createSpec();
	    cols[2]=new DataColumnSpecCreator(tag+":mean",DoubleCell.TYPE).createSpec();
	    cols[3]=new DataColumnSpecCreator(tag+":min",DoubleCell.TYPE).createSpec();
	    cols[4]=new DataColumnSpecCreator(tag+":max",DoubleCell.TYPE).createSpec();
	    return new DataTableSpec(cols);
    	}
    
    @Override
    protected DataTableSpec[] configure(DataTableSpec[] inSpecs)
    		throws InvalidSettingsException {
    	if(inSpecs==null || inSpecs.length!=1)
    		{
    		throw new InvalidSettingsException("Expected one tables");
    		}
    	for(String s:m_numericColumns.getIncludeList())
    		{
    		if(inSpecs[0].findColumnIndex(s)==-1)
    			{
    			throw new InvalidSettingsException("Cannot find columns "+s);
    			}
    		}
    	return new DataTableSpec[]{new DataTableSpec(inSpecs[0],createDataTableSpec())};
    	}
    
    @Override
    protected List<SettingsModel> getSettingsModel() {
    	List<SettingsModel> L=new ArrayList<SettingsModel>( super.getSettingsModel());
    	L.add(this.m_flag);
    	L.add(this.m_numericColumns);
    	return L;
    	}
	}


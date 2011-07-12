package fr.inserm.umr915.knime4ngs.nodes.vcf.region;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.knime.base.data.sort.SortedTable;
import org.knime.core.data.DataRow;
import org.knime.core.data.DataTableSpec;
import org.knime.core.data.container.CloseableRowIterator;
import org.knime.core.node.BufferedDataContainer;
import org.knime.core.node.BufferedDataTable;
import org.knime.core.node.ExecutionContext;
import org.knime.core.node.InvalidSettingsException;
import org.knime.core.node.defaultnodesettings.SettingsModel;
import org.knime.core.node.defaultnodesettings.SettingsModelColumnName;
import fr.inserm.umr915.knime4ngs.nodes.vcf.AbstractVCFNodeModel;


@Deprecated
public class RegionNodeModel extends AbstractVCFNodeModel
	{
	
	static final String CHROM_COL_PROPERTY="chrom.col";
	private final SettingsModelColumnName m_chromCol =  new SettingsModelColumnName(CHROM_COL_PROPERTY,"chrom");
	static final String CHROMSTART_COL_PROPERTY="chromStart.col";
	private final SettingsModelColumnName m_chromStartCol =  new SettingsModelColumnName( CHROMSTART_COL_PROPERTY,"chromStart");
	static final String CHROMEND_COL_PROPERTY="chromEnd.col";
	private final SettingsModelColumnName m_chromEndCol =  new SettingsModelColumnName( CHROMEND_COL_PROPERTY,"chromEnd");
	
	
	private static class RowComparator implements Comparator<DataRow>
		{
		@Override
		public int compare(DataRow o1, DataRow o2) {
			return 0;//TODO
			}
		}
	
    /**
     * Constructor for the node model.
     */
    protected RegionNodeModel()
    	{
        super(2,2);
    	}
   
    @Override
    protected BufferedDataTable[] execute(
    		final BufferedDataTable[] inData,
            final ExecutionContext exec
            ) throws Exception
            {
			BufferedDataContainer container1=null;
			BufferedDataContainer container2=null;
			try
		    	{
		        // the data table spec of the single output table, 
		        // the table will have three columns:
				BufferedDataTable inTable=inData[0];
				RowComparator rowComparator=new RowComparator();
				@SuppressWarnings("unused")
				SortedTable sortedTable=new SortedTable(inData[2], rowComparator, false, exec);
				
				DataTableSpec inDataTableSpec = inTable.getDataTableSpec();
				
		        container1 = exec.createDataContainer(inDataTableSpec);
		        container2 = exec.createDataContainer(inDataTableSpec);
		        
		        
		        
		        
		        double total=inTable.getRowCount();
		        int nRow=0;
		        CloseableRowIterator iter=null;
		        try {
		        	iter=inTable.iterator();
		        	while(iter.hasNext())
		        		{
		        		++nRow;
		        		DataRow row=iter.next();
		        		
						container1.addRowToTable(row);
							
		        		}
		        	exec.checkCanceled();
	            	exec.setProgress(nRow/total,"Filtering....");
					} 
		        catch (Exception e)
					{
					throw e;
					}
				finally
					{
					if(iter!=null) iter.close();
					}
		        
				// once we are done, we close the container and return its table
		        container1.close();
		        BufferedDataTable out1 = container1.getTable();
		        container1=null;
		        
		        container2.close();
		        BufferedDataTable out2 = container2.getTable();
		        container2=null;
		        BufferedDataTable array[]= new BufferedDataTable[]{out1,out2};
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
			if(container1!=null) container1.close();
			if(container2!=null) container2.close();
			}
       }
    
    
    @Override
    protected DataTableSpec[] configure(DataTableSpec[] inSpecs)
    		throws InvalidSettingsException {
    	if(inSpecs==null || inSpecs.length!=2)
    		{
    		throw new InvalidSettingsException("Expected two table");
    		}
    	
    	DataTableSpec in=inSpecs[0];
    	
    	
    	
    	return new DataTableSpec[]{in,in};
    	}
    
    @Override
    protected List<SettingsModel> getSettingsModel() {
    	List<SettingsModel> L=new ArrayList<SettingsModel>( super.getSettingsModel());
    	L.add(this.m_chromCol);
    	L.add(this.m_chromStartCol);
    	L.add(this.m_chromEndCol);
    	return L;
    	}
    
    
    
	}


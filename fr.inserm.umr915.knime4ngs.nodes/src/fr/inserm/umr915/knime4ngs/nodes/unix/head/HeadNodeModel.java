package fr.inserm.umr915.knime4ngs.nodes.unix.head;

import java.util.ArrayList;
import java.util.List;
import org.knime.core.data.DataTableSpec;
import org.knime.core.data.container.CloseableRowIterator;
import org.knime.core.node.BufferedDataContainer;
import org.knime.core.node.BufferedDataTable;
import org.knime.core.node.ExecutionContext;
import org.knime.core.node.InvalidSettingsException;
import org.knime.core.node.defaultnodesettings.SettingsModel;
import org.knime.core.node.defaultnodesettings.SettingsModelInteger;

import fr.inserm.umr915.knime4ngs.corelib.knime.AbstractNodeModel;








/**
 * @author Pierre Lindenbaum
 */
public class HeadNodeModel extends AbstractNodeModel
	{
	
	final static int DEFAULT_ROW=10;
    final static String ROW_PROPERTY="rows"; 
	private final SettingsModelInteger m_rowCount = new SettingsModelInteger(ROW_PROPERTY,DEFAULT_ROW);
	
   
	
    /**
     * Constructor for the node model.
     */
    protected HeadNodeModel()
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
			BufferedDataTable inTable=inData[0];
	        container1 = exec.createDataContainer(inTable.getDataTableSpec());
	        int total=this.m_rowCount.getIntValue();
	        

	        int nRow=0;
	        CloseableRowIterator iter=null;
	        try {
	        	iter=inTable.iterator();
	        	while(iter.hasNext())
	        		{
	        		if(nRow>=total) break;
	        		++nRow;
	        		container1.addRowToTable(iter.next());
	        		
	        		//
	        		exec.checkCanceled();
	            	exec.setProgress(nRow+1/total,"Head....");
	        		}
	        	
				} 
	        catch (Exception e)
				{
	        	e.printStackTrace();
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
		if(container1!=null) container1.close();
		}
   }
    
    @Override
    protected DataTableSpec[] configure(DataTableSpec[] inSpecs)
    		throws InvalidSettingsException
    	{
    	if(inSpecs==null || inSpecs.length!=1)
    		{
    		throw new InvalidSettingsException("Expected one table");
    		}
    	
    	return inSpecs;
    	}
    
    @Override
    protected List<SettingsModel> getSettingsModel() {
    	List<SettingsModel> L=new ArrayList<SettingsModel>( super.getSettingsModel());
    	L.add(this.m_rowCount);
    	return L;
    	}
	}


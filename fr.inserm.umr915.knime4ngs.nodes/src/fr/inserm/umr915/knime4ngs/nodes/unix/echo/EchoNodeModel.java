package fr.inserm.umr915.knime4ngs.nodes.unix.echo;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.knime.core.data.DataColumnSpecCreator;
import org.knime.core.data.DataTableSpec;
import org.knime.core.data.RowKey;
import org.knime.core.data.def.DefaultRow;
import org.knime.core.data.def.StringCell;
import org.knime.core.node.BufferedDataContainer;
import org.knime.core.node.BufferedDataTable;
import org.knime.core.node.ExecutionContext;
import org.knime.core.node.InvalidSettingsException;
import org.knime.core.node.defaultnodesettings.SettingsModel;
import org.knime.core.node.defaultnodesettings.SettingsModelString;

import fr.inserm.umr915.knime4ngs.corelib.knime.AbstractNodeModel;








/**
 * @author Pierre Lindenbaum
 */
public class EchoNodeModel extends AbstractNodeModel
	{
    final static String CONTENT_PROPERTY="content"; 
	private final SettingsModelString m_content = new SettingsModelString(CONTENT_PROPERTY,"");
	
    /**
     * Constructor for the node model.
     */
    protected EchoNodeModel()
    	{
        super(0,1);
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
	        container1 = exec.createDataContainer(createSpec());
	        int nRow=0;
	        BufferedReader in=null;
	        try {
	        	in =new BufferedReader(new StringReader(this.m_content.getStringValue()));
	        	String line;
	        	
	        	while((line=in.readLine())!=null)
	        		{
	        		if(line.trim().isEmpty()) continue;
	        		
	        		container1.addRowToTable(new DefaultRow(RowKey.createRowKey(nRow++), new StringCell(line)));
	        		//
	        		exec.checkCanceled();
	        		}
	        	
				} 
	        catch (Exception e)
				{
	        	e.printStackTrace();
				throw e;
				}
			finally
				{
				if(in!=null) in.close();
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
    		throws InvalidSettingsException
    	{    	
    	return new DataTableSpec[]{createSpec()};
    	}
    
    private DataTableSpec createSpec()
    	{
    	return new DataTableSpec(new DataColumnSpecCreator("row",StringCell.TYPE).createSpec());
    	}
    
    @Override
    protected List<SettingsModel> getSettingsModel() {
    	List<SettingsModel> L=new ArrayList<SettingsModel>( super.getSettingsModel());
    	L.add(this.m_content);
    	return L;
    	}
	}


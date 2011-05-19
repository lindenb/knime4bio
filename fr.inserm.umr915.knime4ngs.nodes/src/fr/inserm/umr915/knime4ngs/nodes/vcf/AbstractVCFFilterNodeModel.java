package fr.inserm.umr915.knime4ngs.nodes.vcf;


import org.knime.core.data.DataRow;
import org.knime.core.data.DataTableSpec;
import org.knime.core.data.container.CloseableRowIterator;
import org.knime.core.node.BufferedDataContainer;
import org.knime.core.node.BufferedDataTable;
import org.knime.core.node.ExecutionContext;
import org.knime.core.node.InvalidSettingsException;
import org.knime.core.node.port.PortType;

@Deprecated
public abstract class AbstractVCFFilterNodeModel extends AbstractVCFNodeModel
	{
	public AbstractVCFFilterNodeModel(int nrInDataPorts, int nrOutDataPorts)
		{
		super(nrInDataPorts, nrOutDataPorts);
		}

	public AbstractVCFFilterNodeModel(PortType[] inPortTypes, PortType[] outPortTypes) {
		super(inPortTypes, outPortTypes);
		}

	
	protected abstract boolean accept(MutationColumns cols,DataRow row) throws Exception;
	
	
    /**
     * {@inheritDoc}
     */
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
		        DataTableSpec inDataTableSpec = inTable.getDataTableSpec();
		        MutationColumns vcfColumns= getMutationColumns(inData[0].getDataTableSpec());
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
						if(accept(vcfColumns, row))
							{
							container1.addRowToTable(row);
							}
						else
							{
							container2.addRowToTable(row);
							}
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
		    	getLogger().info("returning an array "+array.length);
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
	
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected DataTableSpec[] configure(final DataTableSpec[] inSpecs)
            throws InvalidSettingsException
        {
        if(inSpecs==null || inSpecs.length!=1)
        	{
        	throw new InvalidSettingsException("Expect one input.");
        	}
        DataTableSpec tableSpec=inSpecs[0];
        try
        	{
        	getMutationColumns(tableSpec);
        	}
        catch(Exception err)
        	{
        	throw new InvalidSettingsException("Not a VCF header",err);
        	}
    	
    	return new DataTableSpec[]{tableSpec,tableSpec};
    	}
    
	}

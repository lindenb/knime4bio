package fr.inserm.umr915.knime4ngs.nodes.vcf.substitution;


import org.knime.core.data.DataRow;
import org.knime.core.data.DataTableSpec;
import org.knime.core.data.container.CloseableRowIterator;
import org.knime.core.data.def.StringCell;
import org.knime.core.node.BufferedDataContainer;
import org.knime.core.node.BufferedDataTable;
import org.knime.core.node.ExecutionContext;
import org.knime.core.node.InvalidSettingsException;

import fr.inserm.umr915.knime4ngs.corelib.bio.Mutation;
import fr.inserm.umr915.knime4ngs.nodes.vcf.AbstractVCFNodeModel;





/**
 * This is the model implementation of VCFSource.
 * Reads a VCF file
 *
 * @author Pierre Lindenbaum
 */
public class SubstitutionNodeModel extends AbstractVCFNodeModel
	{
    /**
     * Constructor for the node model.
     */
    protected SubstitutionNodeModel()
    	{
        super(1,2);
    	}
    
    private static boolean isATGC(String s)
    	{
    	return Mutation.isATGC(s);
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
		       
				DataTableSpec inDataTableSpec = inTable.getDataTableSpec();
				int columnRef= findColumnIndex(inDataTableSpec,"REF",StringCell.TYPE);
				int columnAlt= findColumnIndex(inDataTableSpec,"ALT",StringCell.TYPE);
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
		        		String ref=StringCell.class.cast(row.getCell(columnRef)).getStringValue();
		        		String alt=StringCell.class.cast(row.getCell(columnAlt)).getStringValue();
						
		        		if(isATGC(ref) && isATGC(alt))
							{
							container1.addRowToTable(row);
							}
						else
							{
							container2.addRowToTable(row);
							}
		        		exec.checkCanceled();
		            	exec.setProgress(nRow/total,"Filtering....");
		        		}
		        
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
    	if(inSpecs==null || inSpecs.length!=1)
    		{
    		throw new InvalidSettingsException("Expected one table");
    		}
    	
    	findColumnIndex(inSpecs[0],"REF",StringCell.TYPE);
    	findColumnIndex(inSpecs[0],"ALT",StringCell.TYPE);
    	return new DataTableSpec[]{inSpecs[0],inSpecs[0]};
    	}
	}


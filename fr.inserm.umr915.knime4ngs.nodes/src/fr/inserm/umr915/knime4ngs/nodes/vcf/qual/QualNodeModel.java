package fr.inserm.umr915.knime4ngs.nodes.vcf.qual;

import java.util.ArrayList;
import java.util.List;

import org.knime.core.data.DataRow;
import org.knime.core.data.DataTableSpec;
import org.knime.core.data.container.CloseableRowIterator;
import org.knime.core.data.def.DoubleCell;
import org.knime.core.node.BufferedDataContainer;
import org.knime.core.node.BufferedDataTable;
import org.knime.core.node.ExecutionContext;
import org.knime.core.node.InvalidSettingsException;
import org.knime.core.node.defaultnodesettings.SettingsModel;
import org.knime.core.node.defaultnodesettings.SettingsModelDouble;
import fr.inserm.umr915.knime4ngs.nodes.vcf.AbstractVCFNodeModel;



/**
 * This is the model implementation of VCFSource.
 * Reads a VCF file
 *
 * @author Pierre Lindenbaum
 */
public class QualNodeModel extends AbstractVCFNodeModel
	{
	final static double DEFAULT_MIN_QUAL=20;
	
	static final String MIN_QUAL_PROPERTY="min.qual";
	private final SettingsModelDouble m_minQual =
        new SettingsModelDouble("min.qual",DEFAULT_MIN_QUAL);

    /**
     * Constructor for the node model.
     */
    protected QualNodeModel()
    	{
        super(1,2);
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
				int qualColumn= inDataTableSpec.findColumnIndex("QUAL");
				if(qualColumn==-1) throw new IllegalArgumentException("Cannot find column \"QUAL\"");
				if(inDataTableSpec.getColumnSpec(qualColumn).getType()!=DoubleCell.TYPE)
		    		{
		    		throw new IllegalArgumentException("column \"QUAL\" is not a double");
		    		}
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
		        		double qual=DoubleCell.class.cast(row.getCell(qualColumn)).getDoubleValue();

		        		if(	qual>= m_minQual.getDoubleValue()
		        			)
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
    	
    	DataTableSpec in=inSpecs[0];
    	findColumnIndex(in, "QUAL",DoubleCell.TYPE);
    	
    	
    	return new DataTableSpec[]{in,in};
    	}
    
    @Override
    protected List<SettingsModel> getSettingsModel() {
    	List<SettingsModel> L=new ArrayList<SettingsModel>( super.getSettingsModel());
    	L.add(this.m_minQual);
    	return L;
    	}
    
    
    
	}


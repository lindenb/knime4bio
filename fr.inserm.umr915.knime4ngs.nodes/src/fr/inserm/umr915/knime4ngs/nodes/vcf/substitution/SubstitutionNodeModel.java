package fr.inserm.umr915.knime4ngs.nodes.vcf.substitution;


import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.knime.core.data.DataCell;
import org.knime.core.data.DataRow;
import org.knime.core.data.DataTableSpec;
import org.knime.core.data.container.CloseableRowIterator;
import org.knime.core.data.def.StringCell;
import org.knime.core.node.BufferedDataContainer;
import org.knime.core.node.BufferedDataTable;
import org.knime.core.node.ExecutionContext;
import org.knime.core.node.InvalidSettingsException;
import org.knime.core.node.defaultnodesettings.SettingsModel;
import org.knime.core.node.defaultnodesettings.SettingsModelColumnName;

import fr.inserm.umr915.knime4ngs.nodes.vcf.AbstractVCFNodeModel;




public class SubstitutionNodeModel extends AbstractVCFNodeModel
	{
	/** ref column */
	static final String REF_COL_PROPERTY="ref.col";
	static final String REF_COL_DEFAULT="REF";
	private final SettingsModelColumnName m_refColumn = new SettingsModelColumnName(
			REF_COL_PROPERTY,
			REF_COL_DEFAULT
			);
	/** alt column */
	static final String ALT_COL_PROPERTY="alt.col";
	static final String ALT_COL_DEFAULT="ALT";
	private final SettingsModelColumnName m_altColumn = new SettingsModelColumnName(
			ALT_COL_PROPERTY,
			ALT_COL_DEFAULT
			);
    /**
     * Constructor for the node model.
     */
    protected SubstitutionNodeModel()
    	{
        super(1,2);
    	}
    
    private static boolean isATGC(String s)
    	{
    	if(s==null || s.length()!=1) return false;
    	int c= s.toUpperCase().charAt(0);
    	return c>='A' && c<='Z';
    	}
    @Override
    protected BufferedDataTable[] execute(
    		final BufferedDataTable[] inData,
            final ExecutionContext exec
            ) throws Exception
            {
    		Pattern mulSnv=Pattern.compile("[A-Za-z](,[A-Za-z])+");
			BufferedDataContainer container1=null;
			BufferedDataContainer container2=null;
			try
		    	{
		        // the data table spec of the single output table, 
		        // the table will have three columns:
				BufferedDataTable inTable=inData[0];
		       
				DataTableSpec inDataTableSpec = inTable.getDataTableSpec();
				int columnRef= findColumnIndex(inDataTableSpec,this.m_refColumn,StringCell.TYPE);
				int columnAlt= findColumnIndex(inDataTableSpec,this.m_altColumn,StringCell.TYPE);
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
		        		DataCell cell=row.getCell(columnRef);
		        		if(cell.isMissing()) continue;
		        		String ref=StringCell.class.cast(cell).getStringValue();
		        		cell=row.getCell(columnAlt);
		        		if(cell.isMissing()) continue;
		        		String alt=StringCell.class.cast(cell).getStringValue();
						
		        		if(isATGC(ref) && (isATGC(alt) || mulSnv.matcher(alt).matches()))
							{
							container1.addRowToTable(row);
							}
						else
							{
							container2.addRowToTable(row);
							}
		        		exec.checkCanceled();
		            	exec.setProgress(nRow/total,"Substitutions....");
		        		}
		        
					} 
		        catch (Exception e)
					{
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
		        
		        safeClose(container2);
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
			safeClose(container1);
			safeClose(container2);
			}
       }
    
    @Override
    protected DataTableSpec[] configure(DataTableSpec[] inSpecs)
    		throws InvalidSettingsException {
    	if(inSpecs==null || inSpecs.length!=1)
    		{
    		throw new InvalidSettingsException("Expected one table");
    		}
    	
    	findColumnIndex(inSpecs[0],this.m_refColumn,StringCell.TYPE);
    	findColumnIndex(inSpecs[0],this.m_altColumn,StringCell.TYPE);
    	return new DataTableSpec[]{inSpecs[0],inSpecs[0]};
    	}
    
    @Override
	protected List<SettingsModel> getSettingsModel() {
		List<SettingsModel> arrayModel=new ArrayList<SettingsModel>(super.getSettingsModel());
		arrayModel.add(this.m_refColumn);
		arrayModel.add(this.m_altColumn);
		return arrayModel;
		}
    
	}


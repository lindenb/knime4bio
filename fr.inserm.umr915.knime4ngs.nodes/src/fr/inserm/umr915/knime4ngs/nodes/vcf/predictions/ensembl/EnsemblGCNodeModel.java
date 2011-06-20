package fr.inserm.umr915.knime4ngs.nodes.vcf.predictions.ensembl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Pattern;

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
import org.knime.core.node.defaultnodesettings.SettingsModelStringArray;
import fr.inserm.umr915.knime4ngs.nodes.vcf.AbstractVCFNodeModel;

public class EnsemblGCNodeModel extends AbstractVCFNodeModel
	{
	
	final static String CHOICE[]={
			"3PRIME_UTR",//0
			"5PRIME_UTR",
			"DOWNSTREAM",
			"ESSENTIAL_SPLICE_SITE",//3
			"INTERGENIC",
			"INTRONIC",
			"NMD_TRANSCRIPT",//6
			"NON_SYNONYMOUS_CODING",//7
			"PARTIAL_CODON",//8
			"REGULATORY_REGION",//9
			"SPLICE_SITE",//10
			"STOP_GAINED",//11
			"STOP_LOST",//12
			"SYNONYMOUS_CODING",
			"UPSTREAM",
			"WITHIN_MATURE_miRNA",//15
			"WITHIN_NON_CODING_GENE",
			"COMPLEX_INDEL",
			"FRAMESHIFT_CODING"//18
			};
	final static String DEFAULT_CHOICE[]=new String[]{
		CHOICE[3],
		CHOICE[6],
		CHOICE[7],
		CHOICE[9],
		CHOICE[10],CHOICE[11],CHOICE[12],CHOICE[15],CHOICE[18],
		};
	
	
	final static String PROPERTY_CQ_COL="cq.col";
	final static String DEFAULT_CQ_COL="CQ";
	private SettingsModelColumnName m_cqCol=new SettingsModelColumnName(PROPERTY_CQ_COL,DEFAULT_CQ_COL);
	
	
	static final String GC_PROPERTY="ensembl.gc";
	private SettingsModelStringArray selected_gc=new SettingsModelStringArray(GC_PROPERTY, DEFAULT_CHOICE);

    /**
     * Constructor for the node model.
     */
    protected EnsemblGCNodeModel()
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
		       
				DataTableSpec inDataTableSpec = inTable.getDataTableSpec();
				int cqColumn=findColumnIndex(inDataTableSpec, m_cqCol,StringCell.TYPE);
				
		        container1 = exec.createDataContainer(inDataTableSpec);
		       
		        
		        Set<String> predictions=new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
		        predictions.addAll(Arrays.asList(selected_gc.getStringArrayValue()));
		        
		     
		        Pattern comma=Pattern.compile("[,]");
		        double total=inTable.getRowCount();
		        int nRow=0;
		        CloseableRowIterator iter=null;
		        try {
		        	iter=inTable.iterator();
		        	while(iter.hasNext())
		        		{
		        		++nRow;
		        		DataRow row=iter.next();
		        	
		        		if(!row.getCell(cqColumn).isMissing())
			        		{
			        		String content=StringCell.class.cast(row.getCell(cqColumn)).getStringValue();
							
							for(String s:comma.split(content))
								{
								if(predictions.contains(s))
									{
									container1.addRowToTable(row);
									break;
									}
								}
			        		}
						
		        		}
		        	exec.checkCanceled();
	            	exec.setProgress(nRow/total,"Filtering....");
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
    	
    	DataTableSpec in=inSpecs[0];
    	findColumnIndex(in, m_cqCol,StringCell.TYPE);   	
    	return new DataTableSpec[]{in};
    	}
    
    @Override
    protected List<SettingsModel> getSettingsModel() {
    	List<SettingsModel> L=new ArrayList<SettingsModel>( super.getSettingsModel());
    	L.add(this.selected_gc);
    	L.add(this.m_cqCol);
    	return L;
    	}
    
    
    
	}


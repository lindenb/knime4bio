package fr.inserm.umr915.knime4ngs.nodes.vcf.go;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
import org.knime.core.node.defaultnodesettings.SettingsModelString;

import fr.inserm.umr915.knime4ngs.nodes.vcf.AbstractVCFNodeModel;
import fr.inserm.umr915.knime4ngs.nodes.vcf.transcript.ucsc.UcscTranscriptNodeModel;



/**

 * @author Pierre Lindenbaum
 */
public class GoNodeModel extends AbstractVCFNodeModel
	{
	static final String GENONTOLOGY_PROPERTY="go.terms";
	static final String GENONTOLOGY_DEFAULT="GO:0007507";
	private final SettingsModelString m_goTerms =
        new SettingsModelString(GENONTOLOGY_PROPERTY,GENONTOLOGY_DEFAULT);

    /**
     * Constructor for the node model.
     */
    protected GoNodeModel()
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
			Set<String> goTerms=new HashSet<String>();
			Set<String> geneNames=new HashSet<String>();
			
			
			for(String s: this.m_goTerms.getStringValue().split("[\n, \t]"))
				{
				s=s.trim().toUpperCase();
				if(s.isEmpty()) continue;
				if(!s.startsWith("GO:")) continue;
				goTerms.add(s);
				}
			if(!goTerms.isEmpty())
				{
				Pattern tab=Pattern.compile("[\t]");
				String line;
				for(String goTerm:goTerms)
					{
					String uri="http://www.ebi.ac.uk/QuickGO/GAnnotation?tax=9606&relType=IP=&goid="+
						URLEncoder.encode(goTerm,"UTF-8")+
						"&format=tsv";
					
					BufferedReader in=new BufferedReader(new InputStreamReader(new URL(uri).openStream()));
					line=in.readLine(); //skip header
					
					if(line==null) { in.close(); continue;}
					while((line=in.readLine())!=null)
						{
						
						exec.checkCanceled();
						String tokens[]=tab.split(line);
						if(tokens.length<4) continue;
						String s=tokens[3].toUpperCase().trim();
						if(s.isEmpty()) continue;
						geneNames.add(s);
						}
					in.close();
					}
				}
			
			
			try
		    	{
		        // the data table spec of the single output table, 
		        // the table will have three columns:
				BufferedDataTable inTable=inData[0];
		       
				DataTableSpec inDataTableSpec = inTable.getDataTableSpec();
				int geneColumn = findColumnIndex(inDataTableSpec,UcscTranscriptNodeModel.KG_SYMBOL,StringCell.TYPE);
			
				
				
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
		        		String geneSymbol= getString(row, geneColumn);
		        		
		        		if(geneSymbol!=null && geneNames.contains(geneSymbol.toUpperCase()))
							{
							container1.addRowToTable(row);
							}
						else
							{
							container2.addRowToTable(row);
							}
		        		exec.checkCanceled();
		            	exec.setProgress(nRow/total,"Filtering on GO");
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
    	findColumnIndex(in,UcscTranscriptNodeModel.KG_SYMBOL,StringCell.TYPE);
    	
    	
    	return new DataTableSpec[]{in,in};
    	}
    
    @Override
    protected List<SettingsModel> getSettingsModel() {
    	List<SettingsModel> L=new ArrayList<SettingsModel>( super.getSettingsModel());
    	L.add(this.m_goTerms);
    	return L;
    	}
    
    
    
	}


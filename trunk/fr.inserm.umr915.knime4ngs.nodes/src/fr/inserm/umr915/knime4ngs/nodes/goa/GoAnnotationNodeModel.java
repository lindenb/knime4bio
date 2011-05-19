package fr.inserm.umr915.knime4ngs.nodes.goa;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import org.knime.core.data.DataCell;
import org.knime.core.data.DataColumnSpec;
import org.knime.core.data.DataColumnSpecCreator;
import org.knime.core.data.DataRow;
import org.knime.core.data.DataTableSpec;
import org.knime.core.data.RowKey;
import org.knime.core.data.container.CloseableRowIterator;
import org.knime.core.data.def.DefaultRow;
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
public class GoAnnotationNodeModel extends AbstractVCFNodeModel
	{
	static final String GENONTOLOGY_PROPERTY="go.terms";
	static final String GENONTOLOGY_DEFAULT="GO:0007507";
	private final SettingsModelString m_goTerm =  new SettingsModelString(GENONTOLOGY_PROPERTY,GENONTOLOGY_DEFAULT);

    /**
     * Constructor for the node model.
     */
    protected GoAnnotationNodeModel()
    	{
        super(0,1);
    	}
   
    private DataTableSpec createDataTableSpec()
    	{
    	DataColumnSpec array[]=new DataColumnSpec[14];
    	array[0]=new DataColumnSpecCreator("DB", StringCell.TYPE).createSpec();
    	array[1]=new DataColumnSpecCreator("ID", StringCell.TYPE).createSpec();
    	array[2]=new DataColumnSpecCreator("Splice", StringCell.TYPE).createSpec();
    	array[3]=new DataColumnSpecCreator("Symbol", StringCell.TYPE).createSpec();
    	array[4]=new DataColumnSpecCreator("Taxon", StringCell.TYPE).createSpec();
    	array[5]=new DataColumnSpecCreator("Qualifier", StringCell.TYPE).createSpec();
    	array[6]=new DataColumnSpecCreator("GO ID", StringCell.TYPE).createSpec();
    	array[7]=new DataColumnSpecCreator("GO Name", StringCell.TYPE).createSpec();
    	array[8]=new DataColumnSpecCreator("Reference", StringCell.TYPE).createSpec();
    	array[9]=new DataColumnSpecCreator("Evidence", StringCell.TYPE).createSpec();
    	array[10]=new DataColumnSpecCreator("With", StringCell.TYPE).createSpec();
    	array[11]=new DataColumnSpecCreator("Aspect", StringCell.TYPE).createSpec();
    	array[12]=new DataColumnSpecCreator("Date", StringCell.TYPE).createSpec();
    	array[13]=new DataColumnSpecCreator("Source", StringCell.TYPE).createSpec();

    	return new DataTableSpec(array);
    	}
    
    @Override
    protected BufferedDataTable[] execute(
    		final BufferedDataTable[] inData,
            final ExecutionContext exec
            ) throws Exception
            {
			BufferedDataContainer container1=null;
			Pattern tab=Pattern.compile("[\t]");
			BufferedReader in=null;
			try
				{
		        // the data table spec of the single output table, 
		        // the table will have three columns:
				DataTableSpec inSpec=createDataTableSpec();
				container1=exec.createDataContainer(inSpec);
		      
		        String goTerm=m_goTerm.getStringValue();
		        if(!goTerm.isEmpty())
					{
					
					String line;
					int nRow=0;
					String uri="http://www.ebi.ac.uk/QuickGO/GAnnotation?tax=9606&relType=IP=&goid="+
						URLEncoder.encode(goTerm,"UTF-8")+
						"&format=tsv";
						
					in=new BufferedReader(new InputStreamReader(new URL(uri).openStream()));
					line=in.readLine(); //skip header
					if(line!=null) 
						{
						while((line=in.readLine())!=null)
							{
							nRow++;
							exec.checkCanceled();
							String tokens[]=tab.split(line);
							DataCell array[]=new DataCell[inSpec.getNumColumns()];
							for(int i=0;i< Math.min(array.length, tokens.length);++i)
								{
								array[i]=new StringCell(tokens[i]);
								}
							
							for(int i=tokens.length; i< array.length;++i)
								{
								array[i]=new StringCell("");
								}
							container1.addRowToTable(new DefaultRow(RowKey.createRowKey(nRow), array));
							}
						}
					in.close();
					in=null;
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
			if(in!=null) in.close();
			}
       }
    
    
    @Override
    protected DataTableSpec[] configure(DataTableSpec[] inSpecs)
    		throws InvalidSettingsException {
    	if(inSpecs==null || inSpecs.length!=0)
    		{
    		throw new InvalidSettingsException("Expected no table");
    		}
    	
    	DataTableSpec in=createDataTableSpec();
    	
    	
    	return new DataTableSpec[]{in};
    	}
    
    @Override
    protected List<SettingsModel> getSettingsModel() {
    	List<SettingsModel> L=new ArrayList<SettingsModel>( super.getSettingsModel());
    	L.add(this.m_goTerm);
    	return L;
    	}
    
    
    
	}


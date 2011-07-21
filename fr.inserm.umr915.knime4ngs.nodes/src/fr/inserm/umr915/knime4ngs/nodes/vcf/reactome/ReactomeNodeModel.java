package fr.inserm.umr915.knime4ngs.nodes.vcf.reactome;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.knime.base.data.append.column.AppendedColumnRow;
import org.knime.core.data.DataCell;
import org.knime.core.data.DataColumnSpec;
import org.knime.core.data.DataColumnSpecCreator;
import org.knime.core.data.DataRow;
import org.knime.core.data.DataTableSpec;
import org.knime.core.data.DataType;
import org.knime.core.data.RowKey;
import org.knime.core.data.container.CloseableRowIterator;
import org.knime.core.data.def.StringCell;
import org.knime.core.node.BufferedDataContainer;
import org.knime.core.node.BufferedDataTable;
import org.knime.core.node.ExecutionContext;
import org.knime.core.node.InvalidSettingsException;
import org.knime.core.node.defaultnodesettings.SettingsModel;
import org.knime.core.node.defaultnodesettings.SettingsModelColumnName;


import fr.inserm.umr915.knime4ngs.nodes.vcf.AbstractVCFNodeModel;





/**
 * @author Pierre Lindenbaum
 */
public class ReactomeNodeModel extends AbstractVCFNodeModel
	{
	/** uniprot column */
	static final String UNIPROT_COl_PROPERTY="uniprot.id";
	static final String UNIPROT_COl_DEFAULT="uniprot.id";
	private final SettingsModelColumnName m_uniprotColumn = new SettingsModelColumnName(
			UNIPROT_COl_PROPERTY,
			UNIPROT_COl_DEFAULT
			);
	
    /**
     * Constructor for the node model.
     */
    protected ReactomeNodeModel()
    	{
        super(1,1);
    	}
    
    
    @Override
    protected BufferedDataTable[] execute(
    		final BufferedDataTable[] inData,
            final ExecutionContext exec
            ) throws Exception
		    {
    		final String PREFIX="/cgi-bin/eventbrowser?";
			BufferedDataContainer container1=null;
			Pattern tab=Pattern.compile("[\t]");
			try
		    	{
		        // the data table spec of the single output table, 
		        // the table will have three columns:
				BufferedDataTable inTable=inData[0];
		       
				DataTableSpec inDataTableSpec = inTable.getDataTableSpec();
				
				
				
			
				int uniprotCol= inDataTableSpec.findColumnIndex(this.m_uniprotColumn.getStringValue());

				container1 = exec.createDataContainer(createSpec(inDataTableSpec));
		      
			
				
		        int outIndex=0;
		        double total=inTable.getRowCount();
		        int nRow=0;
		        CloseableRowIterator iter=null;
		        try {
		        	iter=inTable.iterator();
		        	while(iter.hasNext())
		        		{
		        		++nRow;
		        		DataRow row=iter.next();
		        		DataCell cell=row.getCell(uniprotCol);
		        		boolean found=false;
		        		if(!cell.isMissing()) 
		        			{
		        			String uniprotId=StringCell.class.cast(cell).getStringValue().trim();
		        			do
		        				{
		        				if(uniprotId.isEmpty()) break;
		        				URL url=new URL("http://www.reactome.org/cgi-bin/link?SOURCE=UniProt&ID="+URLEncoder.encode(uniprotId, "UTF-8"));
		        				HttpURLConnection con2=(HttpURLConnection)url.openConnection();
		        				con2.setInstanceFollowRedirects(false);
		        				con2.connect();
		        				String location=con2.getHeaderField("Location");
		        				con2.disconnect();
		        				if(location==null)
		        					{
		        					//System.err.println("Cannot find redirect for "+url);
		        					break;
		        					}
		        				
		        				if(!location.startsWith(PREFIX))
		        					{
		        					System.err.println("Location :"+location+" doesn't start with "+PREFIX+" this node might not be up-to-date.");
		        					break;
		        					}
		        				location="http://www.reactome.org/cgi-bin/taboutputter?"+
		        						location.substring(PREFIX.length())+
		        						"&INSTRUCTIONID=10"
		        						;
		        				BufferedReader r=new BufferedReader(new InputStreamReader(new URL(location).openStream()));
		        				String line;
		        				
		        				while((line=r.readLine())!=null)
		        					{
		        					if(line.startsWith("#") || line.isEmpty()) continue;
		        					String tokens[]=tab.split(line);
		        					if(tokens.length==1)
		        						{
		        						System.err.println("Only one column in "+line+" ?");
		        						continue;
		        						}
		        					container1.addRowToTable(new AppendedColumnRow(RowKey.createRowKey(++outIndex),
		    		        				row,	
		    		        				new StringCell(tokens[0]),
		    		        				new StringCell(tokens[1]),
		    		        				(tokens.length==2?DataType.getMissingCell():new StringCell(tokens[2]))
		    		        				));
		        					found=true;
		        					}
		        				r.close();
		        				}while(false);
		        			}
		        		if(!found)
		        			{
		        			container1.addRowToTable(
		        				new AppendedColumnRow(RowKey.createRowKey(++outIndex),
		        				row,	
		        				DataType.getMissingCell(),
		        				DataType.getMissingCell(),
		        				DataType.getMissingCell()
		        				));
		        			}
		        		}
		        	exec.checkCanceled();
	            	exec.setProgress(nRow/total,"Reactome");
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
		        
		        
		        return new BufferedDataTable[]{out1};
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
    
    private DataTableSpec createSpec(DataTableSpec in)
	  	{
	  	return new DataTableSpec(in,
	  			new DataTableSpec(new DataColumnSpec[]{
	  				new DataColumnSpecCreator("reactome.stableIdentifier.identifier",StringCell.TYPE).createSpec(),
	  				new DataColumnSpecCreator("reactome.displayName",StringCell.TYPE).createSpec(),
	  				new DataColumnSpecCreator("reactome.goBiologicalProcess.accession",StringCell.TYPE).createSpec()
	  				}
	  				)
	  		);
	  	}
    
    @Override
    protected DataTableSpec[] configure(DataTableSpec[] inSpecs)
    		throws InvalidSettingsException
    	{
    	if(inSpecs==null || inSpecs.length!=1)
    		{
    		throw new InvalidSettingsException("Expected one table");
    		}
    	findColumnIndex(inSpecs[0], m_uniprotColumn,StringCell.TYPE);
    	return new DataTableSpec[]{createSpec(inSpecs[0])};
    	}

    @Override
    protected List<SettingsModel> getSettingsModel() {
    	List<SettingsModel> L=new ArrayList<SettingsModel>( super.getSettingsModel());
    	L.add(this.m_uniprotColumn);

    	return L;
    	}
	}


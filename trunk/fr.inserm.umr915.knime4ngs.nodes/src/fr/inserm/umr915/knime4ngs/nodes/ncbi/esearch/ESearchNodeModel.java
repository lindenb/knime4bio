package fr.inserm.umr915.knime4ngs.nodes.ncbi.esearch;

import java.io.IOException;
import java.io.StringReader;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;


import org.knime.core.data.DataColumnSpec;
import org.knime.core.data.DataColumnSpecCreator;
import org.knime.core.data.DataRow;
import org.knime.core.data.DataTableSpec;
import org.knime.core.data.RowKey;
import org.knime.core.data.def.DefaultRow;
import org.knime.core.data.def.IntCell;
import org.knime.core.node.BufferedDataContainer;
import org.knime.core.node.BufferedDataTable;
import org.knime.core.node.CanceledExecutionException;
import org.knime.core.node.ExecutionContext;
import org.knime.core.node.InvalidSettingsException;
import org.knime.core.node.defaultnodesettings.SettingsModel;
import org.knime.core.node.defaultnodesettings.SettingsModelInteger;
import org.knime.core.node.defaultnodesettings.SettingsModelString;


import org.xml.sax.Attributes;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import fr.inserm.umr915.knime4ngs.corelib.knime.AbstractNodeModel;


/**
 * ESearchNodeModel
 */
public class ESearchNodeModel extends AbstractNodeModel
	{
	final static String NCBI_QUERY_PROPERTY="entrez.query"; 
	final static String NCBI_QUERY_DEFAULT=""; 
	private final SettingsModelString m_entrezQuery = new SettingsModelString(
			NCBI_QUERY_PROPERTY,NCBI_QUERY_DEFAULT
			);
	
	final static String DB_IN_PROPERTY="db.in"; 
	final static String DB_IN_DEFAULT="pubmed"; 
	private final SettingsModelString m_database = new SettingsModelString(
			DB_IN_PROPERTY,DB_IN_DEFAULT
			);
	
	final static String LIMIT_PROPERTY="limit"; 
	final static int LIMIT_DEFAULT=1000; 
	private final SettingsModelInteger m_limit = new SettingsModelInteger(
			LIMIT_PROPERTY,LIMIT_DEFAULT
			);
	
	private class Handler extends DefaultHandler
		{
		int rowOut=0;
		ExecutionContext exec;
		BufferedDataContainer container;
		StringBuilder builder=null;
		private Handler(ExecutionContext exec,BufferedDataContainer container)
			{
			this.exec=exec;
			this.container=container;
			}
		@Override
		public void startElement(java.lang.String uri, java.lang.String localName, java.lang.String qName, Attributes atts) throws SAXException {
			if("Id".equals(qName)) builder=new StringBuilder();
			}
		
		@Override
		public void endElement(java.lang.String uri, java.lang.String localName, java.lang.String qName)
				throws SAXException
			{
			if(builder!=null)
				{
				DataRow row=new DefaultRow(RowKey.createRowKey(++rowOut),new IntCell(Integer.parseInt(builder.toString())));
				container.addRowToTable(row);
				exec.setProgress("ESearch "+(rowOut));
        		try {
					exec.checkCanceled();
				} catch (CanceledExecutionException e) {
					throw new SAXException(e);
				}
				}
			builder=null;
			}
		@Override
		public void characters(char[] ch, int start, int length)
				throws SAXException {
			if(builder==null) return;
			builder.append(ch,start,length);
			}
		}
	
    /**
     * Constructor for the node model.
     */
    public ESearchNodeModel()
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
	        container1 = exec.createDataContainer(createTableSpec());
	       
	    
	        SAXParser parser=SAXParserFactory.newInstance().newSAXParser();
	        parser.getXMLReader().setEntityResolver(new EntityResolver()
				{
				@Override
				public InputSource resolveEntity(String publicId, String systemId)
				throws SAXException, IOException
					{
					return new InputSource(new StringReader(""));
					}
				});
	        
	        String url="http://eutils.ncbi.nlm.nih.gov/entrez/eutils/esearch.fcgi?db="+
	        	URLEncoder.encode(this.m_database.getStringValue(), "UTF-8")+
	        	"&term="+
	        	URLEncoder.encode(this.m_entrezQuery.getStringValue(), "UTF-8")+
	        	"&retmax="+
	        	this.m_limit.getIntValue()+
	        	"&tool=knime4bio"
	        	;
	        //System.err.println(url);
	        parser.parse(url, new Handler(exec, container1));
	        
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
    
    
    private DataTableSpec createTableSpec()
    	{
    	DataColumnSpec colspecs[]=new DataColumnSpec[]{
    			new DataColumnSpecCreator(this.m_database.getStringValue()+".id", IntCell.TYPE).createSpec()
    		};
    	return new DataTableSpec(colspecs);
    	}
    
    @Override
    protected DataTableSpec[] configure(DataTableSpec[] inSpecs)
    		throws InvalidSettingsException
    	{    	
    	return new DataTableSpec[]{createTableSpec()};
    	}
    
    @Override
    protected List<SettingsModel> getSettingsModel() {
    	List<SettingsModel> L=new ArrayList<SettingsModel>( super.getSettingsModel());
    	L.add(this.m_database);
    	L.add(this.m_limit);
    	L.add(this.m_entrezQuery);
    	return L;
    	}
    
	}


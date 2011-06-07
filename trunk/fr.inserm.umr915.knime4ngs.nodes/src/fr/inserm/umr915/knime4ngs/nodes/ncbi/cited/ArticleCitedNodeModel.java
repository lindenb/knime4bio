package fr.inserm.umr915.knime4ngs.nodes.ncbi.cited;

import java.io.IOException;
import java.io.StringReader;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.xml.parsers.DocumentBuilderFactory;

import org.knime.base.data.append.column.AppendedColumnRow;
import org.knime.core.data.DataCell;
import org.knime.core.data.DataColumnSpec;
import org.knime.core.data.DataColumnSpecCreator;
import org.knime.core.data.DataRow;
import org.knime.core.data.DataTableSpec;
import org.knime.core.data.DataType;
import org.knime.core.data.RowIterator;
import org.knime.core.data.RowKey;
import org.knime.core.data.def.IntCell;
import org.knime.core.node.BufferedDataContainer;
import org.knime.core.node.BufferedDataTable;
import org.knime.core.node.ExecutionContext;
import org.knime.core.node.InvalidSettingsException;
import org.knime.core.node.defaultnodesettings.SettingsModel;
import org.knime.core.node.defaultnodesettings.SettingsModelBoolean;
import org.knime.core.node.defaultnodesettings.SettingsModelColumnName;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import fr.inserm.umr915.knime4ngs.corelib.knime.AbstractNodeModel;


/**
 * @author Pierre Lindenbaum
 */
public class ArticleCitedNodeModel extends AbstractNodeModel
	{
	final static String SHOW_PROPERTY="show.pmids"; 
	final static boolean SHOW_DEFAULT=false; 
	private final SettingsModelBoolean m_showPmid = new SettingsModelBoolean(SHOW_PROPERTY,SHOW_DEFAULT);
	
	final static String PMID_COL_PROPERTY="pmid.column"; 
	final static String PMID_COL_DEFAULT="PMID"; 
	private final SettingsModelColumnName m_pmidCol = new SettingsModelColumnName(PMID_COL_PROPERTY,PMID_COL_DEFAULT);
	
	
	
    /**
     * Constructor for the node model.
     */
    protected ArticleCitedNodeModel()
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
		RowIterator iter=null;
		DataTableSpec spec1=inData[0].getDataTableSpec();
		int pmidCol= findColumnIndex(spec1, m_pmidCol, IntCell.TYPE);
		
		XPathFactory xpathFactory=XPathFactory.newInstance();
		XPath xpath=xpathFactory.newXPath();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(false);
		factory.setCoalescing(true);
		factory.setIgnoringComments(true);
		factory.setValidating(false);
		factory.setIgnoringElementContentWhitespace(true);
		DocumentBuilder builder = factory.newDocumentBuilder();
        builder.setEntityResolver(new EntityResolver()
			{
			@Override
			public InputSource resolveEntity(String publicId, String systemId)
			throws SAXException, IOException
				{
				return new InputSource(new StringReader(""));
				}
			});
		try
	    	{
			
			
			
	        container1 = exec.createDataContainer(createTableSpec(spec1));
	        float total=inData[0].getRowCount();
	        int nRow=0;
	        int rowOut=0;
	        try {
	        	iter= inData[0].iterator();
	        	while(iter.hasNext())
	        		{
	        		++nRow;
	        		DataRow row=iter.next();
	        		exec.setProgress(nRow/total, "Cited");
	        		exec.checkCanceled();
	        		Set<Integer> cited=new TreeSet<Integer>();
	        		DataCell cell= row.getCell(pmidCol);
	        		if(!cell.isMissing())
	        			{
	        			int pmid=((IntCell)cell).getIntValue();
	        			String uri="http://eutils.ncbi.nlm.nih.gov/entrez/eutils/elink.fcgi?" +
	        			"retmode=xml"+
	        			"&dbfrom=pubmed"+
	        			"&linkname=pubmed_pubmed_citedin"+
	        			"&tool="+URLEncoder.encode(this.getClass().getSimpleName(), "UTF-8")+
	        			"&id="+pmid+
	        			"&cmd=neighbor" +
	        			"&email="+URLEncoder.encode("me@nowehere.com", "UTF-8");
	        			
	        			Document dom=builder.parse(uri);
	        			NodeList L=(NodeList)xpath.evaluate("/eLinkResult/LinkSet/LinkSetDb[LinkName='pubmed_pubmed_citedin' and DbTo='pubmed']/Link/Id", dom, XPathConstants.NODESET);

	        			for(int i=0;i< L.getLength();++i)
		        			{
		        			cited.add(Integer.parseInt(L.item(i).getTextContent()));
		        			}
	        			}
	        		DataCell cells[];
	        		if(m_showPmid.getBooleanValue())
	        			{
	        			if(cited.isEmpty())
		        			{
	        				cells=new DataCell[2];
	        				cells[0]=new IntCell(cited.size());
	        				cells[1]=DataType.getMissingCell();
	        				container1.addRowToTable(new AppendedColumnRow(
    	        					RowKey.createRowKey(++rowOut),
    	        					row,
    	        					cells));
		        			}
	        			else
	        				{
	        				
	        				for(Integer i:cited)
		        				{
	        					cells=new DataCell[2];
	        					cells[0]=new IntCell(cited.size());
	        					cells[1]=new IntCell(i);
	        					container1.addRowToTable(new AppendedColumnRow(
	    	        					RowKey.createRowKey(++rowOut),
	    	        					row,
	    	        					cells));
		        				}
	        				}	
	        			}
	        		else
		        		{
	        			cells=new DataCell[1];
	        			cells[0]=new IntCell(cited.size());
	        			container1.addRowToTable(new AppendedColumnRow(
	        					row,
	        					cells));
		        		}
	        		
	        		
	        		
	        		}
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
		getLogger().error("Boum", err);
		err.printStackTrace();
		throw err;
		}
	finally
		{
		safeClose(container1);
		}
   }
    
    
    private DataTableSpec createTableSpec(DataTableSpec src)
    	{
    	DataColumnSpec colspecs[]=new DataColumnSpec[1+(m_showPmid.getBooleanValue()?1:0)];
    	colspecs[0]=new DataColumnSpecCreator("cited.by.pmid.count", IntCell.TYPE).createSpec();
    	if(m_showPmid.getBooleanValue())
    		{
    		colspecs[1]=new DataColumnSpecCreator("cited.by.pmid", IntCell.TYPE).createSpec();
    		}
    	DataTableSpec spec2=new DataTableSpec(colspecs);
    	return new DataTableSpec(src,spec2);
    	}
    
    @Override
    protected DataTableSpec[] configure(DataTableSpec[] inSpecs)
    		throws InvalidSettingsException
    	{    	
    	if(inSpecs==null || inSpecs.length!=1)
    		{
    		throw new InvalidSettingsException("expected one tables");
    		}
    	DataTableSpec spec1=inSpecs[0];
    	findColumnIndex(spec1, m_pmidCol, IntCell.TYPE);
    	return new DataTableSpec[]{createTableSpec(spec1)};
    	}
    
    @Override
    protected List<SettingsModel> getSettingsModel() {
    	List<SettingsModel> L=new ArrayList<SettingsModel>( super.getSettingsModel());
    	L.add(this.m_pmidCol);
    	L.add(this.m_showPmid);
    	return L;
    	}
    
    
    
	}


package fr.inserm.umr915.knime4ngs.nodes.ncbi.elink;

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
import org.knime.core.node.defaultnodesettings.SettingsModelColumnName;
import org.knime.core.node.defaultnodesettings.SettingsModelInteger;
import org.knime.core.node.defaultnodesettings.SettingsModelString;

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
 * ELinkNodeModel
 */
public class ELinkNodeModel extends AbstractNodeModel
	{
	final static String ALL_NCBI_DB[]={
		"pubmed",
		"protein",
		"nuccore",
		"nucleotide",
		"nucgss",
		"nucest",
		"structure",
		"genome",
		"genomeprj",
		"bioproject",
		"biosample",
		"biosystems",
		//"blastdbinfo",
		"books",
		"cancerchromosomes",
		"cdd",
		"gap",
		"dbvar",
		"epigenomics",
		"gene",
		"gensat",
		"gds",
		"geo",
		"geoprofiles",
		"homologene",
		"journals",
		"mesh",
		"ncbisearch",
		"nlmcatalog",
		"omia",
		"omim",
		"pmc",
		"popset",
		"probe",
		"proteinclusters",
		"pcassay",
		"pccompound",
		"pcsubstance",
		"pubmedhealth",
		"seqannot",
		"snp",
		"sra",
		"taxonomy",
		//"toolkit",
		//"toolkitall",
		"unigene",
		"unists",
		"gencoll"
		};
	
	final static String NCBI_ID_COL_PROPERTY="id.column"; 
	final static String NCBI_ID_COL_DEFAULT="entrez.id"; 
	private final SettingsModelColumnName m_ncbiIdCol = new SettingsModelColumnName(
			NCBI_ID_COL_PROPERTY,NCBI_ID_COL_DEFAULT
			);
	
	final static String DB_IN_PROPERTY="db.in"; 
	final static String DB_IN_DEFAULT="gene"; 
	private final SettingsModelString m_dbIn = new SettingsModelString(
			DB_IN_PROPERTY,DB_IN_DEFAULT
			);
	
	final static String DB_OUT_PROPERTY="db.out"; 
	final static String DB_OUT_DEFAULT="pubmed"; 
	private final SettingsModelString m_dbOut = new SettingsModelString(
			DB_OUT_PROPERTY,DB_OUT_DEFAULT
			);
	
	final static String LIMIT_PROPERTY="limit"; 
	final static int LIMIT_DEFAULT=10; 
	private final SettingsModelInteger m_limit = new SettingsModelInteger(
			LIMIT_PROPERTY,LIMIT_DEFAULT
			);
	
    /**
     * Constructor for the node model.
     */
    protected ELinkNodeModel()
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
		int dbInCol= findColumnIndex(spec1, this.m_ncbiIdCol, IntCell.TYPE);
		
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
	        String dbInUTF8=URLEncoder.encode(m_dbIn.getStringValue(), "UTF-8");
	        String dbOutUTF8=URLEncoder.encode(m_dbOut.getStringValue(), "UTF-8");
	        try {
	        	iter= inData[0].iterator();
	        	while(iter.hasNext())
	        		{
	        		++nRow;
	        		DataRow row=iter.next();
	        		
	        		
	        		Set<Integer> linked =new TreeSet<Integer>();
	        		DataCell cell= row.getCell(dbInCol);
	        		if(!cell.isMissing())
	        			{
	        			int entrezid=((IntCell)cell).getIntValue();
	        			
	        			String uri="http://www.ncbi.nlm.nih.gov/entrez/eutils/elink.fcgi?" +
	        					"dbfrom=" + dbInUTF8 +
	        					"&db="+dbOutUTF8+
	        					"&id="+entrezid+
	        					"&tool=knime&email=me_nowhere_com"
	        					;
	        			
	        			
	        			Document dom=builder.parse(uri);
	        			NodeList L=(NodeList)xpath.evaluate("/eLinkResult/LinkSet/LinkSetDb/Link/Id", dom, XPathConstants.NODESET);

	        			for(int i=0;i< L.getLength() && i< this.m_limit.getIntValue();++i)
		        			{
	        				linked.add(Integer.parseInt(L.item(i).getTextContent()));
		        			}
	        			}
	        		
        			if(linked.isEmpty())
	        			{
        				
        				container1.addRowToTable(new AppendedColumnRow(
	        					RowKey.createRowKey(++rowOut),
	        					row,
	        					DataType.getMissingCell()
	        					));
	        			}
        			else
        				{
        				for(Integer i:linked)
	        				{
        					container1.addRowToTable(new AppendedColumnRow(
    	        					RowKey.createRowKey(++rowOut),
    	        					row,
    	        					new IntCell(i)
    	        					));
	        				}
        				}
        			exec.setProgress(nRow/total, "ELink");
	        		exec.checkCanceled();
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
    	DataColumnSpec colspecs[]=new DataColumnSpec[]{
    			new DataColumnSpecCreator(this.m_dbOut.getStringValue()+".id", IntCell.TYPE).createSpec()
    		};
    	DataTableSpec spec2=new DataTableSpec(colspecs);
    	return new DataTableSpec(src,spec2);
    	}
    
    @Override
    protected DataTableSpec[] configure(DataTableSpec[] inSpecs)
    		throws InvalidSettingsException
    	{    	
    	if(inSpecs==null || inSpecs.length!=1)
    		{
    		throw new InvalidSettingsException("expected one table");
    		}
    	DataTableSpec spec1=inSpecs[0];
    	findColumnIndex(spec1, m_ncbiIdCol, IntCell.TYPE);
    	
    	return new DataTableSpec[]{createTableSpec(spec1)};
    	}
    
    @Override
    protected List<SettingsModel> getSettingsModel() {
    	List<SettingsModel> L=new ArrayList<SettingsModel>( super.getSettingsModel());
    	L.add(this.m_dbIn);
    	L.add(this.m_dbOut);
    	L.add(this.m_limit);
    	L.add(this.m_ncbiIdCol);
    	return L;
    	}
    
    
    
	}


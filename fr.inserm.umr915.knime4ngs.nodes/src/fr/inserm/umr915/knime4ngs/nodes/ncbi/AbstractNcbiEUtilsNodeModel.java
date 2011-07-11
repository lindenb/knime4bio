package fr.inserm.umr915.knime4ngs.nodes.ncbi;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;

import org.knime.base.data.append.column.AppendedColumnRow;
import org.knime.core.data.DataCell;
import org.knime.core.data.DataRow;
import org.knime.core.data.DataTableSpec;
import org.knime.core.data.DataType;
import org.knime.core.data.RowKey;
import org.knime.core.data.container.CloseableRowIterator;
import org.knime.core.node.BufferedDataContainer;
import org.knime.core.node.BufferedDataTable;
import org.knime.core.node.ExecutionContext;
import org.knime.core.node.InvalidSettingsException;
import org.knime.core.node.defaultnodesettings.SettingsModel;
import org.knime.core.node.defaultnodesettings.SettingsModelInteger;
import org.knime.core.node.defaultnodesettings.SettingsModelString;
import org.xml.sax.SAXException;

import fr.inserm.umr915.knime4ngs.corelib.knime.AbstractNodeModel;
import fr.inserm.umr915.knime4ngs.corelib.knime.ExecuteException;

public abstract class AbstractNcbiEUtilsNodeModel extends AbstractNodeModel
	{
    final static String TERM_PROPERTY="ncbi.query"; 
	private final SettingsModelString m_term = new SettingsModelString(TERM_PROPERTY,"");
	
	 final static String LIMIT_PROPERTY="ncbi.qlimit"; 
	 final static int LIMIT_DEFAULT=10; 
	private final SettingsModelInteger m_limit = new SettingsModelInteger(LIMIT_PROPERTY,LIMIT_DEFAULT);
	
	protected XMLInputFactory xmlInputFactory;
	protected AbstractNcbiEUtilsNodeModel()
		{
		super(1, 1);
		this.xmlInputFactory = XMLInputFactory.newInstance();
		this.xmlInputFactory.setProperty(XMLInputFactory.IS_COALESCING, Boolean.TRUE);
		this.xmlInputFactory.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE,Boolean.FALSE);
		this.xmlInputFactory.setProperty(XMLInputFactory.IS_REPLACING_ENTITY_REFERENCES,Boolean.TRUE);
		this.xmlInputFactory.setProperty(XMLInputFactory.IS_VALIDATING,Boolean.FALSE);
		this.xmlInputFactory.setProperty(XMLInputFactory.SUPPORT_DTD,Boolean.FALSE);
		}
	
	protected String getQuery(DataRow row,DataTableSpec spec)
		throws ExecuteException
		{
		String query=m_term.getStringValue();
		int i=0;
		while(i< query.length())
			{
			int j=query.indexOf("##",i);
			if(j==-1 || j+2==query.length())
				{
				break;
				}
			int k=query.indexOf("##",j+2);
			if(k==-1 || k==j+2)
				{
				break;
				}
			String colName=query.substring(j+2, k);
			int n=spec.findColumnIndex(colName);
			if(n==-1) throw new ExecuteException("Cannot find "+colName+" in columns");
			DataCell cell=row.getCell(n);
			if(cell.isMissing()) return null;
			String replace=String.valueOf(cell);
			
			query=query.substring(0,j)+replace+query.substring(k+2);
			i=j+2+replace.length();
			}
		return query;
		}
	protected abstract String getDatabase();
	protected abstract List<DataCell[]> parseXML(InputStream in) throws ExecuteException,IOException,SAXException,XMLStreamException;
	
	protected List<DataCell[]> scan(DataRow row,DataTableSpec spec) throws ExecuteException,IOException,SAXException,XMLStreamException
		{
		String query=getQuery(row,spec);
		if(query==null || query.isEmpty()) return Collections.emptyList();
		URL url= new URL(
				"http://eutils.ncbi.nlm.nih.gov/entrez/eutils/esearch.fcgi?db="+getDatabase()+
				"&term="+ URLEncoder.encode(query, "UTF-8")+	
				"&retstart=0&retmax="+m_limit.getIntValue()+
				"&usehistory=y&retmode=xml&email=3rd_party_app&tool=knime");
		//System.err.println(url);
			XMLEventReader reader=this.xmlInputFactory.createXMLEventReader(url.openStream());
			XMLEvent evt;
			String QueryKey=null;
			String WebEnv=null;
			int count=-1;

			while(!(evt=reader.nextEvent()).isEndDocument())
				{
				if(!evt.isStartElement()) continue;	
				String tag= evt.asStartElement().getName().getLocalPart();
				if(tag.equals("QueryKey"))
					{
					QueryKey= reader.getElementText().trim();
					}
				else if(tag.equals("WebEnv"))
					{
					WebEnv= reader.getElementText().trim();
					}
				else  if(tag.equals("Count") && count==-1)
					{
					count=Integer.parseInt(reader.getElementText());
					}
				}
		reader.close();
		if(count==0) return Collections.emptyList(); 
		if(count>m_limit.getIntValue()) count=m_limit.getIntValue();
		url= new URL("http://eutils.ncbi.nlm.nih.gov/entrez/eutils/efetch.fcgi?db="+getDatabase()+
				"&WebEnv="+URLEncoder.encode(WebEnv,"UTF-8")+
				"&query_key="+URLEncoder.encode(QueryKey,"UTF-8")+
				"&retmode=xml&retmax="+count+
				"&email=3rd_party_app&tool=knime"
				)
				;
		//System.err.println(url);
		InputStream in=url.openStream();
		List<DataCell[]> rows=parseXML(in);
		in.close();
		return rows;
		}
	
	protected abstract DataTableSpec createDataSpec();
	
	@Override
	protected BufferedDataTable[] execute(BufferedDataTable[] inData,
			ExecutionContext exec) throws Exception
		{
		BufferedDataContainer container1=null;
		try
	    	{
	        // the data table spec of the single output table, 
	        // the table will have three columns:
			BufferedDataTable inTable=inData[0];
			DataTableSpec spec1=inTable.getDataTableSpec();
			DataTableSpec spec2=createDataSpec();
			
	        container1 = exec.createDataContainer(
	        		new DataTableSpec(spec1,
	        		spec2
	        		));
	        int total=inTable.getRowCount();
	        
	        
	        int nRow=0;
	        int outIndex=0;
	        CloseableRowIterator iter=null;
	        try {
	        	iter=inTable.iterator();
	        	while(iter.hasNext())
	        		{
	        		DataRow row=iter.next();
	        		++nRow;
	        		exec.checkCanceled();
	            	exec.setProgress(nRow/total,"NCBI....");
	            	
	            	List<DataCell[]> cells=scan(row,spec1);
	            	if(cells!=null && !cells.isEmpty())
	            		{
	            		for(DataCell[] r2:cells)
	            			{
	            			outIndex++;
	            			container1.addRowToTable(new AppendedColumnRow(RowKey.createRowKey(outIndex),row,r2));
	            			}
	            		}
	            	else
	            		{
	            		DataCell empty[]=new DataCell[spec2.getNumColumns()];
	            		for(int i=0;i< empty.length;++i) empty[i]=DataType.getMissingCell();
	            		outIndex++;
            			container1.addRowToTable(new AppendedColumnRow(RowKey.createRowKey(outIndex),row,empty));
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
		}
   }
	
	@Override
	protected DataTableSpec[] configure(DataTableSpec[] inSpecs)
			throws InvalidSettingsException
		{
		if(inSpecs==null || inSpecs.length!=1)
			{
			throw new InvalidSettingsException("Expected on table");
			}
		return new DataTableSpec[]{new DataTableSpec(inSpecs[0],createDataSpec())};
		}
	@Override
	protected List<SettingsModel> getSettingsModel() {
		List<SettingsModel> L=new ArrayList<SettingsModel>(super.getSettingsModel());
		L.add(this.m_term);
		L.add(this.m_limit);
		return L;
		}
	}

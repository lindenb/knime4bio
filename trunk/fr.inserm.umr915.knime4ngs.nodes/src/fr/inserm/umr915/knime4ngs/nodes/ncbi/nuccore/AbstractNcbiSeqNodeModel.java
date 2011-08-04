package fr.inserm.umr915.knime4ngs.nodes.ncbi.nuccore;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;

import org.knime.base.data.append.column.AppendedColumnRow;
import org.knime.core.data.DataCell;
import org.knime.core.data.DataColumnSpec;
import org.knime.core.data.DataColumnSpecCreator;
import org.knime.core.data.DataRow;
import org.knime.core.data.DataTableSpec;
import org.knime.core.data.DataType;
import org.knime.core.data.RowKey;
import org.knime.core.data.container.CloseableRowIterator;
import org.knime.core.data.def.IntCell;
import org.knime.core.data.def.StringCell;
import org.knime.core.node.BufferedDataContainer;
import org.knime.core.node.BufferedDataTable;
import org.knime.core.node.ExecutionContext;
import org.knime.core.node.defaultnodesettings.SettingsModel;
import org.knime.core.node.defaultnodesettings.SettingsModelInteger;
import org.xml.sax.Attributes;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


import fr.inserm.umr915.knime4ngs.corelib.knime.ExecuteException;
import fr.inserm.umr915.knime4ngs.nodes.ncbi.AbstractNcbiEUtilsNodeModel;








/**
 * @author Pierre Lindenbaum
 */
public abstract class AbstractNcbiSeqNodeModel extends AbstractNcbiEUtilsNodeModel
	{
	public  final static String LIMIT_SIZE_PROPERTY="ncbi.limit.seq.length"; 
	public  final static int LIMIT_SIZE_DEFAULT=100; 
	private final SettingsModelInteger m_trimSize = new SettingsModelInteger(LIMIT_SIZE_PROPERTY,LIMIT_SIZE_DEFAULT);
	
	
	private class Handler extends DefaultHandler
		{
		int rowOut=0;
		BufferedDataContainer container;
		private StringBuilder builder=null;
		private StringBuilder seq=null;
		private int limitLength;
		private DataCell cells[]=null;
		boolean found=false;
		DataRow row;
		private Handler(DataRow row,BufferedDataContainer container,int rowOut)
			{
			this.row=row;
			this.container=container;
			this.limitLength=m_trimSize.getIntValue();
			this.rowOut=rowOut;
			}
		private DataCell text()
			{
			if(builder==null || builder.length()==0) return DataType.getMissingCell();
			return new StringCell(builder.toString());
			}
		
		private DataCell integer()
			{
			if(builder==null || builder.length()==0) return DataType.getMissingCell();
			try {
				return new IntCell(Integer.parseInt(builder.toString()));
				} 
			catch (Exception e)
				{
				return DataType.getMissingCell();
				}
			}
		
		@Override
		public void startElement(java.lang.String uri, java.lang.String localName, java.lang.String qName, Attributes atts) throws SAXException
			{
			if(	"TSeq".equals(qName))
				{
				cells=new DataCell[]{
						DataType.getMissingCell(),
						DataType.getMissingCell(),
						DataType.getMissingCell(),
						DataType.getMissingCell(),
						DataType.getMissingCell(),
						DataType.getMissingCell(),
						DataType.getMissingCell()
						};
				}
			else if(
				"TSeq_gi".equals(qName) ||
				"TSeq_accver".equals(qName) ||
				"TSeq_taxid".equals(qName) ||
				"TSeq_orgname".equals(qName) ||
				"TSeq_defline".equals(qName) ||
				"TSeq_length".equals(qName)
				)
				{
				builder=new StringBuilder();
				}
			else if("TSeq_sequence".equals(qName))
				{
				seq=new StringBuilder();
				}
			}
		
		@Override
		public void endElement(java.lang.String uri, java.lang.String localName, java.lang.String qName)
				throws SAXException
			{
			if(	"TSeq".equals(qName))
				{
				container.addRowToTable(new AppendedColumnRow(
						RowKey.createRowKey(++rowOut),
						this.row,
						this.cells));
				found=true;
				this.cells=null;
				}
			else if("TSeq_gi".equals(qName)) { cells[0]=integer();}
			else if("TSeq_accver".equals(qName)) { cells[1]=text();}
			else if("TSeq_taxid".equals(qName)) { cells[2]=integer();}
			else if("TSeq_orgname".equals(qName)) { cells[3]=text();}
			else if("TSeq_defline".equals(qName)) { cells[4]=text();}
			else if("TSeq_length".equals(qName)) { cells[5]=integer();}
			else if("TSeq_sequence".equals(qName))
				{
				if(seq==null || seq.length()==0)
					{
					cells[6]=DataType.getMissingCell();
					}
				else
					{
					cells[6]=new StringCell(seq.toString());
					}
				}
			builder=null;
			seq=null;
			}
		@Override
		public void characters(char[] ch, int start, int length)
				throws SAXException {
			if(builder!=null)
				{
				builder.append(ch,start,length);
				}
			else if(seq!=null && seq.length()< this.limitLength)
				{
				seq.append(ch,start,Math.min(length,this.limitLength-seq.length()));
				}
			}
		}

	
    /**
     * Constructor for the node model.
     */
    protected AbstractNcbiSeqNodeModel()
    	{
       
    	}
    @Override
    protected DataTableSpec createDataSpec()
    	{
    	DataColumnSpec cols[]=new DataColumnSpec[7];
    	cols[0]=new DataColumnSpecCreator("ncbi."+getDatabase()+".gi", IntCell.TYPE).createSpec();
    	cols[1]=new DataColumnSpecCreator("ncbi."+getDatabase()+".accver", StringCell.TYPE).createSpec();
    	cols[2]=new DataColumnSpecCreator("ncbi."+getDatabase()+".taxid", IntCell.TYPE).createSpec();
    	cols[3]=new DataColumnSpecCreator("ncbi."+getDatabase()+".orgname", StringCell.TYPE).createSpec();
    	cols[4]=new DataColumnSpecCreator("ncbi."+getDatabase()+".defline", StringCell.TYPE).createSpec();
    	cols[5]=new DataColumnSpecCreator("ncbi."+getDatabase()+".length", IntCell.TYPE).createSpec();
    	cols[6]=new DataColumnSpecCreator("ncbi."+getDatabase()+".sequence", StringCell.TYPE).createSpec();
    	return new DataTableSpec(cols);
    	}
    
    @Override
    protected abstract String getDatabase();
    
    @Override
    protected final List<DataCell[]> parseXML(InputStream in)
    		throws ExecuteException, IOException, SAXException,
    		XMLStreamException
    	{
    	throw new IllegalStateException();
    	}
    
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
	        
	        int rowOut=0;
	        int nRow=0;
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
	        CloseableRowIterator iter=null;
	        try {
	        	iter=inTable.iterator();
	        	while(iter.hasNext())
	        		{
	        		DataRow row=iter.next();
	        		++nRow;
	        		
	        		String query=getQuery(row,spec1);
	        		boolean found=false;
	        		if(!(query==null || query.isEmpty()))
		        		{
	        			if(query.matches("[0-9]+"))
	        				{
	        				String uri= "http://eutils.ncbi.nlm.nih.gov/entrez/eutils/efetch.fcgi?db="+
	        				getDatabase()+
	        				"&id="+URLEncoder.encode(query,"UTF-8")+
	        				"&rettype=fasta"+
	        				"&retmode=xml"+
	        				"&email=3rd_party_app&tool=knime"
	        				;
	        				Handler handler=new Handler(row,container1,rowOut);
	        				parser.parse(uri, handler);
	        				rowOut=handler.rowOut;
	        				found=handler.found;
	        				}
	        			else
		        			{
			        		URL url= new URL(
			        				"http://eutils.ncbi.nlm.nih.gov/entrez/eutils/esearch.fcgi?db="+
			        				getDatabase()+
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
			        		if(count!=0)
				        		{
				        		if(count>m_limit.getIntValue()) count=m_limit.getIntValue();
				        		String uri= "http://eutils.ncbi.nlm.nih.gov/entrez/eutils/efetch.fcgi?db="+getDatabase()+
				        				"&WebEnv="+URLEncoder.encode(WebEnv,"UTF-8")+
				        				"&query_key="+URLEncoder.encode(QueryKey,"UTF-8")+
				        				"&rettype=fasta"+
				        				"&retmode=xml&retmax="+count+
				        				"&email=3rd_party_app&tool=knime"
				        				;
				        		Handler handler=new Handler(row,container1,rowOut);
				        		parser.parse(uri, handler);
				        		rowOut=handler.rowOut;
				        		found=handler.found;
				        		}
		        			}
		        		}
	        		if(!found)
	        			{
	        			DataCell empty[]=new DataCell[spec2.getNumColumns()];
	            		for(int i=0;i< empty.length;++i) empty[i]=DataType.getMissingCell();
	            		rowOut++;
            			container1.addRowToTable(new AppendedColumnRow(RowKey.createRowKey(rowOut),row,empty));
	        			}
	            	exec.checkCanceled();
	            	exec.setProgress(nRow/total,"NCBI....");
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
	protected List<SettingsModel> getSettingsModel() {
		List<SettingsModel> L=new ArrayList<SettingsModel>(super.getSettingsModel());
		L.add(this.m_trimSize);
		return L;
		}
    
	}


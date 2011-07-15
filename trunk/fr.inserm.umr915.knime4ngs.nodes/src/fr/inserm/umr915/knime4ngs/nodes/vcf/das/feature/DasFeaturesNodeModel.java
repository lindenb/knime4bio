package fr.inserm.umr915.knime4ngs.nodes.vcf.das.feature;

import java.io.IOException;
import java.io.StringReader;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

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
import org.knime.core.node.InvalidSettingsException;
import org.knime.core.node.defaultnodesettings.SettingsModel;
import org.knime.core.node.defaultnodesettings.SettingsModelColumnName;
import org.knime.core.node.defaultnodesettings.SettingsModelInteger;
import org.knime.core.node.defaultnodesettings.SettingsModelString;
import org.xml.sax.Attributes;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;



import fr.inserm.umr915.knime4ngs.nodes.vcf.AbstractVCFNodeModel;





/**
 * @author Pierre Lindenbaum
 */
public class DasFeaturesNodeModel extends AbstractVCFNodeModel
	{
	static final String TAG="__POSITION__";
	/** chrom column */
	static final String CHROM_COL_PROPERTY="chrom.col";
	static final String CHROM_COL_DEFAULT="CHROM";
	private final SettingsModelColumnName m_chromColumn = new SettingsModelColumnName(
			CHROM_COL_PROPERTY,
			CHROM_COL_DEFAULT
			);
	
	/** pos column */
	static final String POS_COL_PROPERTY="pos.col";
	static final String POS_COL_DEFAULT="POS";
	private final SettingsModelColumnName m_posColumn = new SettingsModelColumnName(
			POS_COL_PROPERTY,
			POS_COL_DEFAULT
			);

	
	/** das URI */
	static final String DAS_URI_PROPERTY="das.uri";
	static final String DEFAULT_DAS_URI="http://genome.ucsc.edu/cgi-bin/das/hg19/features?segment="+TAG+";type=knownGene";
	private final SettingsModelString m_dasUri =new SettingsModelString(DAS_URI_PROPERTY,DEFAULT_DAS_URI);
	
	static final String MAX_RECORD_PROPERTY="max.records";
	static final int MAX_RECORD_DEF=1;
	private final SettingsModelInteger m_maxRecord =new SettingsModelInteger(MAX_RECORD_PROPERTY,MAX_RECORD_DEF);
	
	
	static class DasFeature
		{
		String label=null;
		String linkUri=null;
		String link=null;
		String type=null;
		int start;
		int end;
		String strand=null;
		String id=null;
		}
	
    /**
     * Constructor for the node model.
     */
    protected DasFeaturesNodeModel()
    	{
        super(1,1);
    	}
    
    private class FeatureHandler extends DefaultHandler
    	{
    	DataRow row;
    	BufferedDataContainer container;
    	StringBuilder content=null;
    	DasFeature feature=null;
    	int outIndex;
    	int countExported=0;
    	int maxRows=0;
 
    	FeatureHandler(int outIndex,DataRow row,BufferedDataContainer container)
    		{
    		this.outIndex=outIndex;
    		this.row=row;
    		this.container=container;
    		this.maxRows=DasFeaturesNodeModel.this.m_maxRecord.getIntValue();
    		}
    	@Override
    	public void startElement(String uri, String localName, String name,
                Attributes attributes) throws SAXException
            {
            if(name.equals("FEATURE"))
                {
            	this.feature=new DasFeature();
            	this.feature.label=attributes.getValue("label");
            	this.feature.id=attributes.getValue("id");
                }
            else if(this.feature!=null &&
            		( name.equals("TYPE") || name.equals("START") ||
            		  name.equals("END") || name.equals("ORIENTATION") || 
            		  name.equals("LINK")
            		))
	            {
            	if(name.equals("LINK"))
            		{
            		this.feature.linkUri=attributes.getValue("href");
            		}
	        	this.content=new StringBuilder();
	            }
            }

    	DataCell castString(String s)
    		{
    		if(s==null || s.isEmpty()) return DataType.getMissingCell();
    		return new StringCell(s);
    		}
    	
    	 
    	
    	@Override
    	public void endElement(String uri, String localName, String name)
    			throws SAXException
    		{
    		if(name.equals("FEATURE"))
	            {
	        	if(this.feature!=null && countExported+1<=this.maxRows)
	        		{	
	        		DataCell cells[]=new DataCell[8];
	        		cells[0]=castString(this.feature.label);
	        		cells[1]=castString(this.feature.linkUri);
	        		cells[2]=castString(this.feature.link);
	        		cells[3]=new IntCell(this.feature.start);
	        		cells[4]=new IntCell(this.feature.end);
	        		cells[5]=castString(this.feature.strand);
	        		cells[6]=castString(this.feature.type);
	        		cells[7]=castString(this.feature.id);
	        		
	        		this.container.addRowToTable(
	        			new AppendedColumnRow(
	        			RowKey.createRowKey(++outIndex),
	        			this.row,
	        			cells
	        			)
	        			);
	        		++countExported;
	        		}
	        	this.feature=null;
	            }
    		else if(this.feature!=null && this.content!=null)
    			{
    			if(name.equals("TYPE"))
					{
					this.feature.type=this.content.toString();
					}
    			else if(name.equals("START"))
    				{
    				this.feature.start=Integer.parseInt(this.content.toString());
    				}
    			else if(name.equals("END"))
					{
					this.feature.end=Integer.parseInt(this.content.toString());
					}
    			else if(name.equals("ORIENTATION"))
					{
					this.feature.strand=this.content.toString();
					}
    			else if(name.equals("LINK"))
					{
					this.feature.link=this.content.toString();
					}
    			}
    		this.content=null;
    		}
    	 @Override
	    public void characters(char[] ch, int start, int length)
	            throws SAXException
	        {
	        if(this.content==null) return;
	        this.content.append(ch,start,length);
	        }
		@Override
		public void endDocument() throws SAXException
			{
			if(this.countExported==0)
				{
				DataCell cells[]=new DataCell[8];
        		for(int i=0;i< cells.length;++i)
        			{
        			cells[i]=DataType.getMissingCell();
        			}
        		
        		this.container.addRowToTable(
        			new AppendedColumnRow(
        			RowKey.createRowKey(++outIndex),
        			this.row,
        			cells
        			)
        			);
				}
			}
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
				
				
				
			
				int chromColumn= inDataTableSpec.findColumnIndex(this.m_chromColumn.getStringValue());
				int posColumn= inDataTableSpec.findColumnIndex(this.m_posColumn.getStringValue());
				
		       
				
				container1 = exec.createDataContainer(createSpec(inDataTableSpec));
		      
			
				
				XMLReader xmlreader=XMLReaderFactory.createXMLReader();
		        
		        
				xmlreader.setEntityResolver(new EntityResolver()
		        	{
					@Override
					public InputSource resolveEntity(String arg0, String arg1)
							throws SAXException, IOException {
						return new InputSource(new StringReader(""));
						}
		        	});
				
		        int outIndex=0;
		        double total=inTable.getRowCount();
		        int nRow=0;
		        int tagIndex=m_dasUri.getStringValue().indexOf(TAG);
		        if(tagIndex==-1) throw new IllegalStateException();
		        CloseableRowIterator iter=null;
		        try {
		        	iter=inTable.iterator();
		        	while(iter.hasNext())
		        		{
		        		++nRow;
		        		DataRow row=iter.next();
		        		DataCell cell=row.getCell(chromColumn);
		        		if(cell.isMissing()) continue;
		        		String chrom=StringCell.class.cast(cell).getStringValue();
		        		cell=row.getCell(posColumn);
		        		if(cell.isMissing()) continue;
		        		int pos1=IntCell.class.cast(cell).getIntValue();
		        		
		        		
						String uri=m_dasUri.getStringValue().substring(0,tagIndex)+
							URLEncoder.encode(chrom, "UTF-8") +":"+(pos1)+","+(pos1)+
							m_dasUri.getStringValue().substring(tagIndex+TAG.length())
							;
												
						try
							{
							FeatureHandler handler=new FeatureHandler(outIndex,row,container1);
					        xmlreader.setContentHandler(handler);
							xmlreader.parse(uri);
							outIndex=handler.outIndex;
							}
						catch(Exception err)
							{
							System.err.println("DAS ERROR:"+err.getMessage()+" "+uri);
							}
		        		}
		        	exec.checkCanceled();
	            	exec.setProgress(nRow/total,"DAS Feature");
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
	  				new DataColumnSpecCreator("label.das.features",StringCell.TYPE).createSpec(),
	  				new DataColumnSpecCreator("linkUri.das.features",StringCell.TYPE).createSpec(),
	  				new DataColumnSpecCreator("link.das.features",StringCell.TYPE).createSpec(),
	  				new DataColumnSpecCreator("start.features",IntCell.TYPE).createSpec(),
	  				new DataColumnSpecCreator("end.features",IntCell.TYPE).createSpec(),
	  				new DataColumnSpecCreator("strand.das.features",StringCell.TYPE).createSpec(),
	  				new DataColumnSpecCreator("type.das.features",StringCell.TYPE).createSpec(),
	  				new DataColumnSpecCreator("id.das.features",StringCell.TYPE).createSpec()
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
    	findColumnIndex(inSpecs[0], m_chromColumn,StringCell.TYPE);
    	findColumnIndex(inSpecs[0], m_posColumn,IntCell.TYPE);
    	String s=m_dasUri.getStringValue();
    	if(!s.contains(TAG))
    		{
    		throw new InvalidSettingsException("DAS uri contain the word \""+TAG+"\"");
    		}
    	return new DataTableSpec[]{createSpec(inSpecs[0])};
    	}
    
  
    
    @Override
    protected List<SettingsModel> getSettingsModel() {
    	List<SettingsModel> L=new ArrayList<SettingsModel>( super.getSettingsModel());
    	L.add(this.m_chromColumn);
    	L.add(this.m_posColumn);
    	L.add(this.m_dasUri);
    	L.add(this.m_maxRecord);
    	return L;
    	}
	}


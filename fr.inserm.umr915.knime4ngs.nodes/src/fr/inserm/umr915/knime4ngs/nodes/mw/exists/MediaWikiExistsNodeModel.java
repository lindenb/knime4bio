package fr.inserm.umr915.knime4ngs.nodes.mw.exists;


import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import org.knime.base.data.append.column.AppendedColumnRow;
import org.knime.core.data.DataCell;
import org.knime.core.data.DataColumnSpecCreator;
import org.knime.core.data.DataRow;
import org.knime.core.data.DataTableSpec;
import org.knime.core.data.DataType;
import org.knime.core.data.container.CloseableRowIterator;
import org.knime.core.data.def.BooleanCell;
import org.knime.core.node.BufferedDataContainer;
import org.knime.core.node.BufferedDataTable;
import org.knime.core.node.ExecutionContext;
import org.knime.core.node.InvalidSettingsException;
import org.knime.core.node.defaultnodesettings.SettingsModel;
import org.knime.core.node.defaultnodesettings.SettingsModelColumnName;
import org.knime.core.node.defaultnodesettings.SettingsModelString;

import fr.inserm.umr915.knime4ngs.corelib.knime.AbstractNodeModel;

/**
 * Write a MediaWiki table
 */
public class MediaWikiExistsNodeModel extends AbstractNodeModel
	{
	static final String MW_API_PROPERTY="mw.uri";
	static final String MW_API_DEFAULT="http://en.wikipedia.org/w/api.php";
	protected final SettingsModelString m_api_url = new SettingsModelString(MW_API_PROPERTY,MW_API_DEFAULT);
	
	static final String COL_PROPERTY="column.column";
	static final String COL_DEFAULT="Article";
	protected final SettingsModelColumnName m_column = new SettingsModelColumnName(COL_PROPERTY,COL_DEFAULT);
	
    /**
     * Constructor for the node model.
     */
    public MediaWikiExistsNodeModel()
    	{
        super(1,1);
    	}
    
    
    @Override
	 protected BufferedDataTable[] execute(
			BufferedDataTable[] inData,
	    	ExecutionContext exec
	    	) throws Exception
	    	{
    		BufferedDataContainer container1=null;
	    	if(inData==null || inData.length!=1)
		     	{
		     	throw new InvalidSettingsException("Expected one table.");
		     	}
	    	BufferedDataTable inTable=inData[0];
	    	int nRows=0;
	    	double total=inTable.getRowCount();
	    	CloseableRowIterator iter=null;
	    	DataTableSpec spec1=inTable.getDataTableSpec();
	    	DataTableSpec spec2=new DataTableSpec(new DataColumnSpecCreator("in.mediawiki",BooleanCell.TYPE).createSpec());
	    	int colIndex=findColumnIndex(spec1,m_column.getColumnName());
	    	
	    	XMLInputFactory factory = XMLInputFactory.newInstance();
			factory.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, Boolean.FALSE);
			factory.setProperty(XMLInputFactory.IS_VALIDATING, Boolean.FALSE);
			factory.setProperty(XMLInputFactory.IS_COALESCING, Boolean.TRUE);
			factory.setProperty(XMLInputFactory.IS_REPLACING_ENTITY_REFERENCES, Boolean.TRUE);
			
			QName pageid=new QName("pageid");
			
			
			
	    	try
		    	{
	    		container1 = exec.createDataContainer(new DataTableSpec(spec1,spec2));
	    		
	    		
		    	iter=inTable.iterator();
		    	while(iter.hasNext())
		    		{
		    		++nRows;
		    		DataRow row=iter.next();
		    		DataCell cell=row.getCell(colIndex);
		    		DataCell boolCell=DataType.getMissingCell();
		    		if(!cell.isMissing())
		    			{
		    			InputStream in=null;
		    			XMLEventReader r=null;
		    			try {
		    				String url=this.m_api_url.getStringValue()+"?action=query&format=xml&titles="+
		    					URLEncoder.encode(String.valueOf(cell.toString()), "UTF-8");
		    				boolCell=BooleanCell.FALSE;
		    				in=new URL(url).openStream();
		    				r=factory.createXMLEventReader(in);
		    				
			    			while(r.hasNext())
			    				{
			    				XMLEvent evt=r.nextEvent();
			    				if(!evt.isStartElement()) continue;
			    				StartElement e=evt.asStartElement();
			    				String tag=e.getName().getLocalPart();
			    				if(tag.equals("page"))
			    					{
			    					Attribute att=e.getAttributeByName(pageid);
			    					if(att!=null)
			    						{
			    						boolCell=BooleanCell.TRUE;
			    						break;
			    						}
			    					break;
			    					}
		    					
			    				}
		    			
							} 
		    			catch (Exception e)
							{
		    				boolCell=DataType.getMissingCell();
							System.err.println(e.getMessage());
							} 
						finally
							{
							if(r!=null) try { r.close(); } catch(Exception err) {}
							safeClose(in);
							}
		    			}
		    		
		    		
		    		
		    		container1.addRowToTable(new AppendedColumnRow(row, boolCell));
		    		exec.checkCanceled();
	            	exec.setProgress(nRows/total,"Building wiki table");
		    		}
		    	
		    	container1.close();
		        BufferedDataTable out1 = container1.getTable();
		        container1=null;
		        BufferedDataTable array[]= new BufferedDataTable[]{out1};
		    	return array;
		    	}
	    	catch (Exception e) {
				e.printStackTrace();
				throw e;
				}
	    	finally
	    		{
	    		safeClose(iter);
	    		safeClose(container1);
	    		}
	    	}
    
    @Override
    protected DataTableSpec[] configure(final DataTableSpec[] inSpecs)
            throws InvalidSettingsException
        {
        if(inSpecs==null || inSpecs.length!=1)
        	{
        	throw new InvalidSettingsException("Expected one table.");
        	}
        findColumnIndex(inSpecs[0],m_column.getColumnName());
        
        DataTableSpec spec2=new DataTableSpec(new DataColumnSpecCreator("in.mediawiki",BooleanCell.TYPE).createSpec());
        
    	return new DataTableSpec[]{new DataTableSpec(inSpecs[0],spec2)};
    	}
  
   @Override
   protected List<SettingsModel> getSettingsModel()
	   	{
	   	List<SettingsModel> a=new ArrayList<SettingsModel>(super.getSettingsModel());
	   	a.add(m_api_url);
	   	a.add(m_column);
	   	return a;
	   	}
   
   
	}


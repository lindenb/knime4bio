package fr.inserm.umr915.knime4ngs.nodes.vcf.rs;

import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;


import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import org.knime.core.data.DataRow;
import org.knime.core.data.DataTableSpec;
import org.knime.core.data.container.CloseableRowIterator;
import org.knime.core.data.def.IntCell;
import org.knime.core.data.def.StringCell;
import org.knime.core.node.BufferedDataContainer;
import org.knime.core.node.BufferedDataTable;
import org.knime.core.node.InvalidSettingsException;
import org.knime.core.node.defaultnodesettings.SettingsModel;
import org.knime.core.node.defaultnodesettings.SettingsModelBoolean;
import org.knime.core.node.defaultnodesettings.SettingsModelDoubleBounded;
import org.knime.core.node.ExecutionContext;

import fr.inserm.umr915.knime4ngs.corelib.bio.Position;
import fr.inserm.umr915.knime4ngs.corelib.knime.AbstractNodeModel;





@Deprecated
public class RsFilterNodeModel extends AbstractNodeModel
	{ 
	private URL serverURL=null;
	private XMLInputFactory xmlInputFactory=null;
    /** the settings key which is used to retrieve and 
        store the settings (from the dialog or from a settings file)    
       (package visibility to be usable from the dialog). */
	static final String CFGKEY_MIN_HETEROZYGOSITY = "min.heterozygocity";
	static final String CFGKEY_CONSIDER_HETEROZYGOSITY = "consider.heterozygocity";

    /** initial default count value. */
    static final double DEFAULT_HETEROZYGOSITY = 0;

  
    private final SettingsModelDoubleBounded m_heterozygosity =
        new SettingsModelDoubleBounded(
        			RsFilterNodeModel.CFGKEY_MIN_HETEROZYGOSITY,
                    RsFilterNodeModel.DEFAULT_HETEROZYGOSITY,
                    0.0, 0.5);
    
    private final SettingsModelBoolean m_considerHeterozygosity =
    	new SettingsModelBoolean(
    			CFGKEY_CONSIDER_HETEROZYGOSITY,
    			false
    		);
    
    /**
     * Constructor for the node model.
     */
    protected RsFilterNodeModel()
    	{
        super(1,2);
    	}
    

    private boolean accept(Position position) throws Exception
    	{
    	URL url=new URL(this.serverURL,"lookup/SNP131?segment="+URLEncoder.encode(position.toString(),"UTF-8"));
    	InputStream in=null;
    	try
	    	{
    		in=url.openStream();
	    	XMLEventReader r=this.xmlInputFactory.createXMLEventReader(in);
	    	while(r.hasNext())
	    		{
	    		XMLEvent evt=r.nextEvent();
	    		if(evt.isStartElement())
	    			{
	    			StartElement start=evt.asStartElement();
	    			String name=start.getName().getLocalPart();
	    			if(name.startsWith("Snp")) return false;
	    			}
	    		}
	    	r.close();
	    	return true;
	    	}
    	finally
    		{
    		if(in!=null) in.close();
    		}
    	}
    
    @Override
    protected BufferedDataTable[] execute(
    		final BufferedDataTable[] inData,
            final ExecutionContext exec
            ) throws Exception
            {
			BufferedDataContainer container1=null;
			BufferedDataContainer container2=null;
			try
		    	{
				this.serverURL=new URL("todo");
    			this.xmlInputFactory=XMLInputFactory.newFactory();
		        // the data table spec of the single output table, 
		        // the table will have three columns:
				BufferedDataTable inTable=inData[0];
		       
				
				DataTableSpec inDataTableSpec = inTable.getDataTableSpec();
				int chromCol= findColumnIndex(inDataTableSpec, "CHROM",StringCell.TYPE);
				int posCol= findColumnIndex(inDataTableSpec, "POS",IntCell.TYPE);
				
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
		        		Position pos=new Position(
	        				StringCell.class.cast(row.getCell(chromCol)).getStringValue(),
	        				IntCell.class.cast(row.getCell(posCol)).getIntValue()
	        				);

		        		if(accept(pos))
							{
							container1.addRowToTable(row);
							}
						else
							{
							container2.addRowToTable(row);
							}
		        		}
		        	exec.checkCanceled();
	            	exec.setProgress(nRow/total,"Filtering....");
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
			this.xmlInputFactory=null;
    		this.serverURL=null;
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
    	findColumnIndex(in,"CHROM",StringCell.TYPE);
    	findColumnIndex(in,"POS",IntCell.TYPE);
    	return new DataTableSpec[]{in,in};
    	}

    @Override
    protected List<SettingsModel> getSettingsModel()
    	{
    	List<SettingsModel> L=new ArrayList<SettingsModel>(super.getSettingsModel());
    	L.add(m_considerHeterozygosity);
    	L.add(m_heterozygosity);
    	return L;
    	}
    
	}


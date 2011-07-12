package fr.inserm.umr915.knime4ngs.nodes.vcf.predictions.basic;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



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

import org.knime.core.node.defaultnodesettings.SettingsModelStringArray;
import org.knime.core.node.ExecutionContext;


import fr.inserm.umr915.knime4ngs.corelib.knime.AbstractNodeModel;


@Deprecated
public class BasicPredictionNodeModel extends AbstractNodeModel
	{
	private static final int NUM_THREAD=4;
	
	static String PREDICTIONS_PROPERTY="prediction.list";
	static final String PREDICTIONS[]=new String[]{
		"EXON",
		"EXON_CODING_NON_SYNONYMOUS",
		"EXON_CODING_SYNONYMOUS",
		"EXON_STOP_GAINED",
		"EXON_STOP_LOST",
		"INTRON",
		"UTR3",
		"UTR5",
		"INTRON_SPLICING",
		"INTRON_SPLICING_DONOR",
		"INTRON_SPLICING_ACCEPTOR",
		};
	static final String DEFAULT_PREDICTIONS[]=new String[]{
		PREDICTIONS[1],
		PREDICTIONS[3],
		PREDICTIONS[4],
		PREDICTIONS[8],
		PREDICTIONS[9],
		PREDICTIONS[10],
		};
	private SettingsModelStringArray m_predictions=new SettingsModelStringArray(PREDICTIONS_PROPERTY,PREDICTIONS);
	private CloseableRowIterator iterator=null;
	private BufferedDataContainer container1=null;
	private BufferedDataContainer container2=null;
	private int numberOfRowsProcessed=0;
	private static class Columns
		{
		int chromCol;
		int posCol;
		int refCol;
		int altCol;
		}
  
	private class SpeedUp implements Runnable
		{
		private XMLInputFactory factory;
		Columns columns;
		Set<String> predictions=new HashSet<String>();
		
		public SpeedUp()
			{
			factory=XMLInputFactory.newFactory();
			}
		

		@Override
		public void run()
			{
			for(;;)
				{
				DataRow row=null;
				
				synchronized (BasicPredictionNodeModel.this)
					{
					CloseableRowIterator iter=BasicPredictionNodeModel.this.iterator;
					if(iter==null || !iter.hasNext()) return;

					row=iter.next();
					}
				
				if(row==null)
					{
					return;
					}
				String chrom= StringCell.class.cast(row.getCell(columns.chromCol)).getStringValue();
				
				int position0=	IntCell.class.cast(row.getCell(columns.posCol)).getIntValue()-1;
				String ref= StringCell.class.cast(row.getCell(columns.refCol)).getStringValue();
				String alt= StringCell.class.cast(row.getCell(columns.altCol)).getStringValue();
				boolean ok;
				try
					{
					ok=accept(chrom,position0,ref,alt);
					}
				catch (Exception e)
					{
					e.printStackTrace();
					throw new RuntimeException(e);
					}
				synchronized (BasicPredictionNodeModel.this)
					{
					BufferedDataContainer container=(ok?
							BasicPredictionNodeModel.this.container1:
							BasicPredictionNodeModel.this.container2
							);
					if(container==null)
						{
						return;
						}
					container.addRowToTable(row);
					++numberOfRowsProcessed;
					}
				}
			}
		
		
	    private boolean accept(
	    		String chrom,
	    		int position0,
	    		String ref,
	    		String alt
	    		) throws Exception
	    	{
	    	
	    	String url=("http://srv-clc-02.irt.univ-nantes.prive:8080/biomachin/prediction?structure=false&sequence=false&pos="+chrom+":"+position0+"&alt="+alt+"&ref="+ref);//TODO
	    	
	    	
	    	
	    	boolean status=false;
	    	InputStream in=null;
	    	URLConnection con=null;
	    	try
		    	{
	    		con=new URL(url).openConnection();
	    		con.setConnectTimeout(60*1000);
	    		in=con.getInputStream();
		    	XMLEventReader r= factory.createXMLEventReader(in);
		    	while(r.hasNext())
		    		{
		    		XMLEvent evt=r.nextEvent();
		    		if(evt.isStartElement())
		    			{
		    			StartElement start=evt.asStartElement();
		    			String name=start.getName().getLocalPart();
		    			if(name.equals("type"))
		    				{
		    				String s=r.getElementText();
		    				if(this.predictions.contains(s))
		    					{
		    					status=true;
		    					break;
		    					}
		    				}
		    			}
		    		}
		    	r.close();
		    	
		    	return status;
		    	}
	    	finally
	    		{
	    		if(in!=null) in.close();
	    		}
	    	}
		
		}
	
    
    /**
     * Constructor for the node model.
     */
    protected BasicPredictionNodeModel()
    	{
        super(1,2);
    	}
    

 
    
    @Override
    protected BufferedDataTable[] execute(
    		final BufferedDataTable[] inData,
            final ExecutionContext exec
            ) throws Exception
            {
    		ExecutorService executorService=null;	
			try
		    	{
				Set<String> set=new HashSet<String>();
				for(String s: this.m_predictions.getStringArrayValue()) set.add(s);
  
		        // the data table spec of the single output table, 
	        // the table will have three columns:
			BufferedDataTable inTable=inData[0];
	       
			
			DataTableSpec inDataTableSpec = inTable.getDataTableSpec();
			Columns columns=new Columns();
			columns.chromCol= findColumnIndex(inDataTableSpec, "CHROM",StringCell.TYPE);
			columns.posCol= findColumnIndex(inDataTableSpec, "POS",IntCell.TYPE);
			columns.refCol= findColumnIndex(inDataTableSpec, "REF",StringCell.TYPE);
			columns.altCol= findColumnIndex(inDataTableSpec, "ALT",StringCell.TYPE);
			
	        this.container1 = exec.createDataContainer(inDataTableSpec);
	        this.container2 = exec.createDataContainer(inDataTableSpec);
	        
	      
	        this.numberOfRowsProcessed=0;
	        double total= inTable.getRowCount();
	        this.iterator=null;
	        
	        this.iterator=inTable.iterator();
	        
	        executorService=Executors.newFixedThreadPool(NUM_THREAD);
	        
	        
	        
        	for(int i=0;i< NUM_THREAD;++i)
        		{
        		SpeedUp thread=new SpeedUp();
        		
        		thread.columns=columns;
        		thread.predictions.addAll(set);
        		executorService.submit(thread);
        		}
        	executorService.shutdown();
        	while(!executorService.isTerminated())
        		{
        		if(this.numberOfRowsProcessed%100==0)
        			{
        			
        			exec.setProgress(numberOfRowsProcessed/total,"Inserting...");
        			exec.checkCanceled();
        			}
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
			if(this.iterator!=null)
				{
				this.iterator.close();
				this.iterator=null;
				}
			
			if(container1!=null) container1.close();
			container1=null;
			if(container2!=null) container2.close();
			container2=null;
			numberOfRowsProcessed=0;
			
			
			
			try
				{
				executorService.shutdownNow();
				}
			catch(Throwable err)
				{
				
				}
			
			
			
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
    	findColumnIndex(in,"REF",StringCell.TYPE);
    	findColumnIndex(in,"ALT",StringCell.TYPE);
    	return new DataTableSpec[]{in,in};
    	}

    @Override
    protected List<SettingsModel> getSettingsModel()
    	{
    	List<SettingsModel> L=new ArrayList<SettingsModel>(super.getSettingsModel());
    	L.add(m_predictions);
    	return L;
    	}
    
	}


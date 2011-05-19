package fr.inserm.umr915.knime4ngs.nodes.vcf.rsid;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Pattern;


import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import org.knime.core.data.DataCell;
import org.knime.core.data.DataRow;
import org.knime.core.data.DataTableSpec;
import org.knime.core.data.container.CloseableRowIterator;
import org.knime.core.data.def.DefaultRow;
import org.knime.core.data.def.IntCell;
import org.knime.core.data.def.StringCell;
import org.knime.core.node.BufferedDataContainer;
import org.knime.core.node.BufferedDataTable;
import org.knime.core.node.InvalidSettingsException;
import org.knime.core.node.defaultnodesettings.SettingsModel;
import org.knime.core.node.defaultnodesettings.SettingsModelBoolean;
import org.knime.core.node.ExecutionContext;


import fr.inserm.umr915.knime4ngs.corelib.bio.Segment;
import fr.inserm.umr915.knime4ngs.corelib.knime.AbstractNodeModel;




public class RsidNodeModel extends AbstractNodeModel
	{
	private static final int NUM_THREAD=10;
	static final String USE_BATCH_PROPERTY="use.batch";
	static final boolean DEFAULT_USE_BATCH_PROPERTY=false;
	
	private final SettingsModelBoolean m_useBatch=new SettingsModelBoolean(USE_BATCH_PROPERTY, DEFAULT_USE_BATCH_PROPERTY);
	private CloseableRowIterator iterator=null;
	private BufferedDataContainer container=null;
	private int numberOfRowsProcessed=0;

	
	private static class RowInfo
		implements Comparable<RowInfo>
		{
		Segment position0;
		DataRow dataRow;
		Set<String> ids=new HashSet<String>();
		@Override
		public int compareTo(RowInfo o) {
			return position0.compareTo(o.position0);
			}
		}
	
	
	private static class Column
		{
		int chromCol= -1;
		int posCol= -1;
		int idCol = -1;
		}
	
	
	private class FasterBin implements Runnable
		{
		private Pattern semicolon=Pattern.compile("[\\;,]");
		Column columns;
		XMLInputFactory xmlInputFactory=XMLInputFactory.newFactory();
		@Override
		public void run()
			{
			for(;;)
			{
			DataRow row=null;
			synchronized (RsidNodeModel.this)
				{
				CloseableRowIterator iter=RsidNodeModel.this.iterator;
				if(iter==null || !iter.hasNext()) return;
				row=iter.next();
				}
			if(row==null)
				{
				return;
				}

			String chrom=getString(row, this.columns.chromCol);
    		int position0=getInt(row, this.columns.posCol)-1;
    		String id=getString(row, this.columns.idCol);;
    		
			Segment seg=new Segment(chrom, position0);
			
			Set<String> ids=new HashSet<String>();
			
			
			
	    	for(String s: semicolon.split(id))
	    		{
	    		ids.add(s);
	    		}
	    	try
    	    	{
    			ids.addAll(searchId(seg));
    	    	}
	    	catch(Exception err)
	    		{
	    		throw new RuntimeException(err);
	    		}
	    	
			ids.remove("");
			ids.remove(".");
			ids.remove(";");
			StringBuilder b=new StringBuilder();
			for(String i:ids)
				{
				if(b.length()!=0) b.append(";");
				b.append(i);
				}
			id=b.toString();
			
   		 
			DataCell array[]=new DataCell[row.getNumCells()];
			for(int i=0;i< array.length;++i)
				{
				array[i]=(i==this.columns.idCol?new StringCell(id):row.getCell(i));
				}
    		row=new DefaultRow(row.getKey(),array);

			synchronized (RsidNodeModel.this)
				{
				BufferedDataContainer container= RsidNodeModel.this.container;
				if(container==null)
					{
					return;
					}
				container.addRowToTable(row);
				
				++numberOfRowsProcessed;
				}

			}
		}
		
		private Set<String> searchId(Segment segment) throws Exception
	    	{
	    	Set<String> ids=new HashSet<String>();
	 
	    	String url="http://srv-clc-02.irt.univ-nantes.prive:8080/biomachin/lookup/snp131?segment="+
	    		URLEncoder.encode(segment.toString(),"UTF-8")+"&expect.length=0";
	    	InputStream in=null;
	    	try
		    	{
	    		in= new URL(url).openStream();
		    	XMLEventReader r=this.xmlInputFactory.createXMLEventReader(in);
		    	while(r.hasNext())
		    		{
		    		XMLEvent evt=r.nextEvent();
		    		if(evt.isStartElement())
		    			{
		    			StartElement start=evt.asStartElement();
		    			String name=start.getName().getLocalPart();
		    			if(name.equals("name"))
		    				{
		    				ids.add(r.getElementText());
		    				}
		    			}
		    		}
		    	r.close();
		    	return ids;
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
    protected RsidNodeModel()
    	{
        super(1,1);
    	}
    

    
    
    @Override
    protected BufferedDataTable[] execute(
    		final BufferedDataTable[] inData,
            final ExecutionContext exec
            ) throws Exception
            {
    		if(this.m_useBatch.getBooleanValue())
    			{
    			return executeBatch(inData,exec);
    			}
    		else
    			{
    			return executeOneByOne(inData,exec);
    			}
            }
    
    
    private BufferedDataTable[] executeOneByOne(
    		final BufferedDataTable[] inData,
            final ExecutionContext exec
            ) throws Exception
            {
    		ExecutorService executorService = null;
			this.container=null;
			this.iterator=null;
			this.numberOfRowsProcessed=0;
			try
		    	{
		        // the data table spec of the single output table, 
		        // the table will have three columns:
				BufferedDataTable inTable=inData[0];
		       
				
				DataTableSpec inDataTableSpec = inTable.getDataTableSpec();
				Column columns=new Column();
				columns.chromCol= findColumnIndex(inDataTableSpec, "CHROM",StringCell.TYPE);
				columns.posCol= findColumnIndex(inDataTableSpec, "POS",IntCell.TYPE);
				columns.idCol = findColumnIndex(inDataTableSpec,"ID",StringCell.TYPE);
				
		        this.container= exec.createDataContainer(inDataTableSpec);
		        
		    
		        double total=inTable.getRowCount();
		        this.iterator=inTable.iterator();
		        executorService=Executors.newFixedThreadPool(NUM_THREAD);
		        for(int i=0;i< NUM_THREAD;++i)
		        	{
		        	FasterBin bin=new FasterBin();
		        	bin.columns=columns;
		        	executorService.submit(bin);
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
		        
		        this.container.close();
		        BufferedDataTable out1 = this.container.getTable();
		        this.container=null;
		        
		        
		        BufferedDataTable array[]= new BufferedDataTable[]{out1};
		    	return array;
		    	}
		catch(Exception err)
			{
			getLogger().error("Boum", err);
			throw err;
			}
		finally
			{
			if(this.container!=null) this.container.close();
			this.container=null;
			this.numberOfRowsProcessed=0;
			
			if(this.iterator!=null)
				{
				this.iterator.close();
				this.iterator=null;
				}
			if(executorService!=null)
				{
				try {
					executorService.shutdownNow();
				} catch (Exception e) {
					
					}
				}
			}
       }
    
    
    private BufferedDataTable[] executeBatch(
    		final BufferedDataTable[] inData,
            final ExecutionContext exec
            ) throws Exception
            {
    		final Pattern semicolon=Pattern.compile("[\\;,]");
    		final Pattern tab=Pattern.compile("[\t]");
    		final int BUFFER_SIZE=10000;
			BufferedDataContainer container1=null;
			try
		    	{
				
    		
		        // the data table spec of the single output table, 
		        // the table will have three columns:
				BufferedDataTable inTable=inData[0];
		       
				
				DataTableSpec inDataTableSpec = inTable.getDataTableSpec();
				int chromCol= findColumnIndex(inDataTableSpec, "CHROM",StringCell.TYPE);
				int posCol= findColumnIndex(inDataTableSpec, "POS",IntCell.TYPE);
				int idCol = findColumnIndex(inDataTableSpec,"ID",StringCell.TYPE);
				
		        container1 = exec.createDataContainer(inDataTableSpec);
		        
		        List<RowInfo> dataRowBuffer=new ArrayList<RowInfo>();
		        double total=inTable.getRowCount();
		        int nRow=0;
		        CloseableRowIterator iter=null;
		        try {
		        	iter=inTable.iterator();
		        	for(;;)
		        		{
		        		dataRowBuffer.clear();
		        		while(iter.hasNext())
		        			{
		        			++nRow;
		        			RowInfo rowInfo=new RowInfo();
		        			rowInfo.dataRow=iter.next();
		        			
		        			String id= StringCell.class.cast(rowInfo.dataRow.getCell(idCol)).getStringValue();
		        			for(String s: semicolon.split(id))
			            		{
		        				rowInfo.ids.add(s);
			            		}
		        			rowInfo.position0=new Segment(
		        					StringCell.class.cast(rowInfo.dataRow.getCell(chromCol)).getStringValue(),
		        					IntCell.class.cast(rowInfo.dataRow.getCell(posCol)).getIntValue()-1
		        					);
			        		dataRowBuffer.add(rowInfo);
			        		exec.checkCanceled();
			        		if(dataRowBuffer.size()> BUFFER_SIZE) break;
		        			}
		        		if(dataRowBuffer.isEmpty()) break;
		        		
		        		//prepare POST...
		        		
		        		Collections.sort(dataRowBuffer);
		        		StringBuilder postData=new StringBuilder(BUFFER_SIZE*20);
		        		postData.append("expect.length=2&segments=");
		        		Set<Segment> uniqSegments=new TreeSet<Segment>();
		        		for(int i=0;i< dataRowBuffer.size();++i)
		        			{
		        			uniqSegments.add(dataRowBuffer.get(i).position0);
		        			}
	        			
		        		boolean first=true;
		        		for(Segment u:uniqSegments)
		        			{
		        			if(!first) postData.append("%3B");
		        			first=false;
		        			postData.append(
		        				u.getChromosome()+":"+
		        				u.getChromStart()
		        				);
		        			}
		        		
		   
		        	    // URL of CGI-Bin script.
		        		URL url = new URL ("http://srv-clc-02.irt.univ-nantes.prive:8080/biomachin/lookup/snp131");
		        	    // URL connection channel.
		        	    URLConnection urlConn = url.openConnection();
		        	    // Let the run-time system (RTS) know that we want input.
		        	    urlConn.setDoInput (true);
		        	    // Let the RTS know that we want to do output.
		        	    urlConn.setDoOutput (true);
		        	    // No caching, we want the real thing.
		        	    urlConn.setUseCaches (false);
		        	    PrintWriter wr=new PrintWriter( urlConn.getOutputStream());
		        	    wr.write(postData.toString());
		        	    wr.flush();
		        	   
		        	 // Get the response
		        	    BufferedReader rd = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
		        	    String line=rd.readLine();
		        	    if(line==null || !line.startsWith("#"))
		        	    	{
		        	    	throw new IOException("bad first line "+line);
		        	    	}
		        	    String headers[] = tab.split(line);
		        	    int snp1000GColumn = -1;
		        	    for(int i=0;i< headers.length;++i)
		        	    	{
		        	    	if(headers[i].equalsIgnoreCase("Snp131.name"))
		        	    		{
		        	    		snp1000GColumn=i;
		        	    		break;
		        	    		}
		        	    	}
		        	    if(snp1000GColumn==-1) throw new IOException("Cannot find id column in "+line);
		        	   
		        	    RowInfo key=new RowInfo();
		        	    
		        	    
		        	    while ((line = rd.readLine()) != null)
		        	    	{
		        	    	String tokens[]=tab.split(line);
		        	    	if(tokens.length==1) continue;
		        	    	key.position0= Segment.parse(tokens[0]);
		        	    	int index=Collections.binarySearch(dataRowBuffer, key);
		        	    	if(index<0 ) continue;
		        	    	while(index<  dataRowBuffer.size() &&
		        	    		dataRowBuffer.get(index).position0.equals(key.position0)
		        	    		)
			        	    	{
			        	    	if(tokens.length==2)
			        	    		{
			        	    		dataRowBuffer.get(index).ids.add(tokens[1]);
			        	    		}
			        	    	else
			        	    		{
			        	    		dataRowBuffer.get(index).ids.add(tokens[snp1000GColumn]);
			        	    		}
			        	    	//System.err.println("OK "+index+" "+line+" "+dataRowBuffer.get(index).position0);
			        	    	++index;
			        	    	}
		        	    	}
		        	    wr.close();
		        	    rd.close();
		        	    
		        		//end post
		        		
		            	exec.setProgress(nRow/total,"Searching IDs....");
		            	
		            	for(RowInfo row: dataRowBuffer)
		            		{
		            		row.ids.remove(".");
		            		row.ids.remove("");
		            		row.ids.remove("null");
		            		StringBuilder id=new StringBuilder();
		            		for(String s:row.ids)
		            			{
		            			if(id.length()!=0) id.append(";");
		            			id.append(s);
		            			}
		            		
		            		 
		        			DataCell array[]=new DataCell[row.dataRow.getNumCells()];
		        			for(int i=0;i< array.length;++i)
		        				{
		        				array[i]=(i==idCol?new StringCell(id.toString()):row.dataRow.getCell(i));
		        				}
		        			row.dataRow=new DefaultRow(row.dataRow.getKey(),array);
		        			
		            		
		        			container1.addRowToTable(row.dataRow);
		            		}
		          
		            	dataRowBuffer.clear();
		        		}

					} 
		        catch (Exception e)
					{
					throw e;
					}
				finally
					{
					if(iter!=null) iter.close();
					}
		        
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
    		throws InvalidSettingsException {
    	if(inSpecs==null || inSpecs.length!=1)
    		{
    		throw new InvalidSettingsException("Expected one table");
    		}
    	
    	DataTableSpec in=inSpecs[0];
    	findColumnIndex(in,"CHROM",StringCell.TYPE);
    	findColumnIndex(in,"ID",StringCell.TYPE);
    	findColumnIndex(in,"POS",IntCell.TYPE);
    	return new DataTableSpec[]{in};
    	}

    @Override
    protected List<SettingsModel> getSettingsModel()
    	{
    	List<SettingsModel> L=new ArrayList<SettingsModel>(super.getSettingsModel());
    	L.add(m_useBatch);
    	return L;
    	}
    
	}


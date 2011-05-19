package fr.inserm.umr915.knime4ngs.nodes.vcf.transcript.ucsc;


import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;

import org.knime.core.data.DataCell;
import org.knime.core.data.DataColumnSpec;
import org.knime.core.data.DataColumnSpecCreator;
import org.knime.core.data.DataRow;
import org.knime.core.data.DataTableSpec;
import org.knime.core.data.RowKey;
import org.knime.core.data.container.CloseableRowIterator;
import org.knime.core.data.def.DefaultRow;
import org.knime.core.data.def.IntCell;
import org.knime.core.data.def.StringCell;
import org.knime.core.node.BufferedDataContainer;
import org.knime.core.node.BufferedDataTable;
import org.knime.core.node.ExecutionContext;
import org.knime.core.node.InvalidSettingsException;

import fr.inserm.umr915.knime4ngs.corelib.bio.Segment;
import fr.inserm.umr915.knime4ngs.corelib.knime.AbstractNodeModel;
import fr.inserm.umr915.knime4ngs.nodes.vcf.winnerloser.WinnerLoserNodeModel;



/**
 * @author Pierre Lindenbaum
 */
public class UcscTranscriptNodeModel extends AbstractNodeModel
	{
	private class Transcript
		{
		String name;
		int txStart0;
		int txEnd0;
		String geneSymbol;
		String description;
		}
	
	public UcscTranscriptNodeModel()
    	{
        super(1,1);
    	}
    
	public static final String KG_NAME="knowngene.name";
	public static final String KG_START="knowngene.txStart1";
	public static final String KG_END="knowngene.txEnd1";
	public static final String KG_SYMBOL="kgxref.geneSymbol";
	public static final String KG_DESC="kgxref.description";
	
	
	private DataTableSpec createDataTableSpec(DataTableSpec spec2)
		{
		DataColumnSpec array[]=new DataColumnSpec[]{
			new DataColumnSpecCreator(KG_NAME,StringCell.TYPE).createSpec(),
			new DataColumnSpecCreator(KG_START,IntCell.TYPE).createSpec(),
			new DataColumnSpecCreator(KG_END,IntCell.TYPE).createSpec(),
			new DataColumnSpecCreator(KG_SYMBOL,StringCell.TYPE).createSpec(),
			new DataColumnSpecCreator(KG_DESC,StringCell.TYPE).createSpec(),
			};
		DataTableSpec spec1=new DataTableSpec(array);
		return new DataTableSpec(getClass().getSimpleName(), spec1, spec2);
		}
	
	private Transcript geneGeneInfo(Transcript tr) throws XMLStreamException,IOException
		{
		tr.geneSymbol=tr.name;
		tr.description="";
		InputStream in=null;
		XMLEventReader reader=null;
		try
			{
			String uri="http://srv-clc-02.irt.univ-nantes.prive:8080/biomachin/lookup/kgXref?name="+//TODO
				URLEncoder.encode(tr.name,"UTf-8")
				;

			URL url=new URL(uri);
			XMLInputFactory xmlInputFactory=XMLInputFactory.newFactory();
			in=url.openStream();
			reader=xmlInputFactory.createXMLEventReader(in);
			while(reader.hasNext())
				{
				XMLEvent evt=reader.nextEvent();

				if(evt.isEndDocument()) break;
				if(evt.isStartElement() &&
					evt.asStartElement().getName().getLocalPart().equalsIgnoreCase("KgXref"))
					{
					String kgId="";
					String geneSymbol=tr.name;
					String desc="";
					while(reader.hasNext())
						{
						
						evt=reader.nextEvent();
						
						if(evt.isEndDocument()) break;
						if(evt.isStartElement())
							{
							String tag=evt.asStartElement().getName().getLocalPart();
	
							if(tag.equalsIgnoreCase("kgID"))
								{
								kgId=reader.getElementText();
								System.err.println(kgId);
								}
							else if(tag.equalsIgnoreCase("geneSymbol"))
								{
								geneSymbol=reader.getElementText();
								}
							else if(tag.equalsIgnoreCase("description"))
								{
								desc=reader.getElementText();
								}
							}
						else if(evt.isEndElement() &&
								evt.asEndElement().getName().getLocalPart().equalsIgnoreCase("KgXref"))
							{
							
							if(kgId.equalsIgnoreCase(tr.name))
								{

								tr.geneSymbol=geneSymbol;
								tr.description=desc;;
								return tr;
								}
							break;
							}
						}
					}
				}
			return tr;
			}
		finally
			{
			if(reader!=null) reader.close();
			if(in!=null) in.close();
			}
		}
	
	private Transcript parseTranscript(XMLEventReader reader)
		throws XMLStreamException
		{
		Transcript tr=new Transcript();
		while(reader.hasNext())
			{
			XMLEvent evt=reader.nextEvent();
			if(evt.isEndDocument()) break;
			if(evt.isStartElement() )
				{
				String tag=evt.asStartElement().getName().getLocalPart();
				if(tag.equals("name"))
					{
					tr.name=reader.getElementText();
					}
				else if(tag.equals("txStart"))
					{
					tr.txStart0=Integer.parseInt(reader.getElementText());
					}
				else if(tag.equals("txEnd"))
					{
					tr.txEnd0=Integer.parseInt(reader.getElementText());
					}
				}
			else if(evt.isEndElement() &&
					evt.asEndElement().getName().getLocalPart().equalsIgnoreCase("KnownGene"))
				{
				break;
				}
			}
		return tr;
		}
	
	
	private List<Transcript> parseTranscripts(Segment segment0)
	throws XMLStreamException,IOException
		{
		InputStream in=null;
        try {
        	
        	String uri="http://srv-clc-02.irt.univ-nantes.prive:8080/biomachin/lookup/knownGene?segment="+//TODO
        		URLEncoder.encode(segment0.toString(),"UTf-8")
        		;
        	
        	URL url=new URL(uri);
        	XMLInputFactory xmlInputFactory=XMLInputFactory.newFactory();
        	XMLEventReader reader=xmlInputFactory.createXMLEventReader(url.openStream());
        	List<Transcript> transcripts=new ArrayList<Transcript>();
        	while(reader.hasNext())
        		{
        		XMLEvent evt=reader.nextEvent();
        		if(evt.isEndDocument()) break;
        		if(evt.isStartElement() &&
        		   evt.asStartElement().getName().getLocalPart().equalsIgnoreCase("KnownGene"))
        			{
        			Transcript tr=parseTranscript(reader);
        			transcripts.add(tr);
        			}
        		}
        	
        	reader.close();
        	return transcripts;
			} 
		finally
			{
			if(in!=null) in.close();
			in=null;
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
				int chromColumn = findColumnIndex(inDataTableSpec,"CHROM",StringCell.TYPE);
				int posColumn = findColumnIndex(inDataTableSpec,"POS",IntCell.TYPE);
				DataTableSpec outDataTableSpec2=createDataTableSpec(inDataTableSpec);
		        container1 = exec.createDataContainer(outDataTableSpec2);
		        
		      
		        Map<String,Segment> chromosomes=new HashMap<String,Segment>();
		        
		        int nRowOut=0;
		        CloseableRowIterator iter=null;
		        try {
		        	iter=inTable.iterator();
		        	while(iter.hasNext())
		        		{
		        		
		        		DataRow row=iter.next();
		        		String chrom = getString(row, chromColumn);
		        		int position0=getInt(row, posColumn)-1;//
		        		Segment seg=chromosomes.get(chrom);
		        		if(seg==null)
		        			{
		        			seg=new Segment(chrom, position0,position0+1);
		        			
		        			}
		        		else
		        			{
		        			seg=new Segment(chrom,
		        				Math.min(position0, seg.getChromStart()),
		        				Math.max(position0, seg.getChromEnd())
		        				);
		        			}
		        		chromosomes.put(chrom,seg);
		        		
		        		exec.checkCanceled();
		            	exec.setProgress("Collecting chromosomes");
		        		}
		        	
					} 
		        catch (Exception e)
					{
					throw e;
					}
				finally
					{
					if(iter!=null) iter.close();
					iter=null;
					}
				
				
				int nRow=0;
				int total= inTable.getRowCount();
				//loop over each chromosome
				for(String chromosome:chromosomes.keySet())
					{
					System.err.println("Looping "+chromosome);
					Map<String,Transcript> name2transcript=new HashMap<String, UcscTranscriptNodeModel.Transcript>();
					
					 List<Transcript> transcripts=null;
					 int prevpos0=-1;
					 try {
			        	iter=inTable.iterator();
			        	while(iter.hasNext())
			        		{
			        		DataRow row=iter.next();
			        		String chrom = getString(row, chromColumn);
			        		if(!chrom.equals(chromosome)) continue;
			        		++nRow;
			        		int position0=getInt(row, posColumn)-1;
			        		
			        		
			        		if(prevpos0!=position0)
			        			{
			        			transcripts=parseTranscripts(new Segment(chrom, position0));
			        			for(int i=0;i< transcripts.size();++i)
				        			{
				        			Transcript tr=transcripts.get(i);
				        			Transcript old=name2transcript.get(tr.name);
				        			if(old!=null)
				        				{
				        				transcripts.set(i, old);
				        				}
				        			else
				        				{
				        				name2transcript.put(tr.name, tr);
				        				geneGeneInfo(tr);
				        				}
				        			}
			        			prevpos0=position0;
			        			}
			        		
			        		
			        		
			        		
			        		for(Transcript transcript:name2transcript.values())
			        			{
			        			if(transcript.txEnd0<=position0) continue;
			        			if(position0< transcript.txStart0) continue;
			        			if(transcript.geneSymbol==null) geneGeneInfo(transcript);
			        			
			        			List<DataCell> dataCells=new ArrayList<DataCell>();
			        			dataCells.add(new StringCell(transcript.name));
			        			dataCells.add(new IntCell(transcript.txStart0+1));
			        			dataCells.add(new IntCell(transcript.txEnd0+1));
			        			dataCells.add(new StringCell(transcript.geneSymbol));
			        			dataCells.add(new StringCell(transcript.description));
			        			for(int i=0;i< row.getNumCells();++i)
			        				{
			        				dataCells.add(row.getCell(i));
			        				}
			        			
			        			
			        			++nRowOut;
			        			DefaultRow row2=new DefaultRow(
			        				RowKey.createRowKey(nRowOut),
			        				dataCells
			        				);
			        			container1.addRowToTable(row2);
			        			exec.checkCanceled();
				            	exec.setProgress(nRow/(double)total,"Processsing chromosome "+chromosome);
			        			}
			        		
			        		}
			        	
						} 
			        catch (Exception e)
						{
						throw e;
						}
					finally
						{
						if(iter!=null) iter.close();
						iter=null;
						}
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
    		throw new InvalidSettingsException("Expected one table");
    		}
    	findColumnIndex(inSpecs[0], "CHROM",StringCell.TYPE);
    	findColumnIndex(inSpecs[0], "POS",IntCell.TYPE);
    	return new DataTableSpec[]{createDataTableSpec(inSpecs[0])};
    	}
	}


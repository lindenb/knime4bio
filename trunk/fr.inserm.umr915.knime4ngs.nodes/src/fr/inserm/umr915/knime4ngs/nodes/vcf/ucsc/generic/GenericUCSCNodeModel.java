package fr.inserm.umr915.knime4ngs.nodes.vcf.ucsc.generic;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

import org.knime.base.data.append.column.AppendedColumnRow;
import org.knime.core.data.DataCell;
import org.knime.core.data.DataRow;
import org.knime.core.data.DataTableSpec;
import org.knime.core.data.DataType;
import org.knime.core.data.RowIterator;
import org.knime.core.data.RowKey;
import org.knime.core.data.def.IntCell;
import org.knime.core.data.def.StringCell;
import org.knime.core.node.BufferedDataContainer;
import org.knime.core.node.BufferedDataTable;
import org.knime.core.node.ExecutionContext;
import org.knime.core.node.InvalidSettingsException;
import org.knime.core.node.defaultnodesettings.SettingsModel;
import org.knime.core.node.defaultnodesettings.SettingsModelColumnName;
import org.knime.core.node.defaultnodesettings.SettingsModelString;

import fr.inserm.umr915.knime4ngs.corelib.bio.Position;
import fr.inserm.umr915.knime4ngs.corelib.bio.PositionKSorter;
import fr.inserm.umr915.knime4ngs.corelib.bio.Segment;
import fr.inserm.umr915.knime4ngs.corelib.knime.ExecuteException;
import fr.inserm.umr915.knime4ngs.nodes.vcf.AbstractVCFNodeModel;


/**
 * GenericUCSCNodeModel
 */
public class GenericUCSCNodeModel
	extends AbstractVCFNodeModel
	{
	static final UcscTableHandlers UCSC_HANDLERS=new UcscTableHandlers();
	
	private final SettingsModelColumnName m_chrom1Col =
        new SettingsModelColumnName(CHROM1_COL_PROPERTY,DEFAULT_CHROM1_COL);
	
	final static String DEFAULT_CHROM1_COL="CHROM";
	static final String CHROM1_COL_PROPERTY="chrom1.col";
	private final SettingsModelColumnName m_posCol =
        new SettingsModelColumnName(POS_COL_PROPERTY,DEFAULT_POS_COL);
	final static String DEFAULT_POS_COL="POS";
	static final String POS_COL_PROPERTY="pos.col";
	
	final static String HANDLER_NAME_PROPERTY="ucsc.handder.name";
	static final String HANDLER_NAME_DEFAULT="";
	private final SettingsModelString m_ucscHandler =
        new SettingsModelString(HANDLER_NAME_PROPERTY,HANDLER_NAME_DEFAULT);
	
	
	class BedSorter
	implements Comparator<DataCell[]>
		{
		private int chromCol;
		private int chromStartCol;
		private int chromEndCol;
		
		
		public BedSorter(int chromCol, int chromStartCol, int chromEndCol)
			{
			this.chromCol = chromCol;
			if(this.chromCol<0) throw new IllegalArgumentException("chrom=-1");
			this.chromStartCol = chromStartCol;
			if(this.chromStartCol<0) throw new IllegalArgumentException("chromStartCol=-1");
			this.chromEndCol = chromEndCol;
			if(this.chromEndCol<0) throw new IllegalArgumentException("chromEndCol=-1");
			}
		
		
		
		public Segment make(DataCell array[])
			{
			if(array.length<= chromCol) throw new IllegalArgumentException("no chrom column in "+array);
			if(array.length<= chromStartCol) throw new IllegalArgumentException("no chromStart column in "+array);
			if(array.length<= chromEndCol) throw new IllegalArgumentException("no chromEnd column in "+array);
			if(array[chromCol].isMissing()) return null;
			if(array[chromStartCol].isMissing()) return null;
			if(array[chromEndCol].isMissing()) return null;
			String chrom=StringCell.class.cast(array[chromCol]).getStringValue();
			int start=IntCell.class.cast(array[chromStartCol]).getIntValue();
			int end=IntCell.class.cast(array[chromEndCol]).getIntValue();
			return new Segment(chrom, start+1,end-1);//move to a 1-based record
			}
		@Override
		public int compare(DataCell o1[],DataCell o2[]) {
			return make(o1).compareTo(make(o2));
			}

		}
	
    /**
     * Constructor for the node model.
     */
    public GenericUCSCNodeModel()
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
			if(m_ucscHandler.getStringValue().isEmpty())
	    		{
	    		throw new InvalidSettingsException("UCSC database was not selected");
	    		}
	    	UcscDatabaseHandler handler=UCSC_HANDLERS.getHandlerById(m_ucscHandler.getStringValue());
	    	if(m_ucscHandler.getStringValue().isEmpty())
				{
				throw new InvalidSettingsException("undefined handler "+m_ucscHandler.getStringValue());
				}
			
			
			
			BedSorter bedsorter=new BedSorter(
					handler.getChromColumn(),
					handler.getChromStartColumn(),
					handler.getChromEndColumn()
					);
			
			Pattern delim=Pattern.compile("[\t]");
			
			try
		    	{
				BufferedDataTable vcfTable=inData[0];
				DataTableSpec inDataTableSpec1 =vcfTable.getDataTableSpec();
				DataTableSpec inDataTableSpec2=handler.getDataTableSpec();
				
				container1=exec.createDataContainer(new DataTableSpec(
						inDataTableSpec1,
						inDataTableSpec2
					));
				

				
				PositionKSorter vcfSorter=new PositionKSorter(
					findColumnIndex(inDataTableSpec1,this.m_chrom1Col.getColumnName(),StringCell.TYPE),
					findColumnIndex(inDataTableSpec1,this.m_posCol.getColumnName(),IntCell.TYPE)
					);

		        
		       
		        double total=vcfTable.getRowCount();
		        int nRow=0;
		        int outCount=0;
		        RowIterator itervcf=null;
		        Position prevVCF=null;
		        Segment prevBed=null;
		        LinkedList<DataCell[]> buffer=new LinkedList<DataCell[]>();
		        BufferedReader in=null;
		        try {
		        	itervcf=vcfTable.iterator();
		        	
		        	in=openReader(handler.getUrl());
		        	while(itervcf.hasNext())
		        		{
		        		++nRow;
		        		DataRow row=itervcf.next();
		        		Position position0= vcfSorter.make(row);
		        		if(prevVCF!=null && prevVCF.compareTo(position0)>0)
		        			{
		        			throw new IOException(
		        				"Expected sorted VCF file  but found "+prevVCF+" > "+position0	
		        				);
		        			}
		        		prevVCF=position0;
		        		List<DataCell[]> found=new ArrayList<DataCell[]>();
		        		
		        		
		        		boolean willCallNext=true;
		        		int buffIndex=0;
		        		while(buffIndex< buffer.size())
		        			{
		        			DataCell[] r=buffer.get(buffIndex);
		        			Segment seg=bedsorter.make(r);
		        			
		        			int i= seg.getChromosome().compareTo(position0.getChromosome());
		        			if(i>0)
		        				{
		        				willCallNext=false;
		        				break;
		        				}
		        			if(i<0)
		        				{
		        				buffer.remove(buffIndex);
		        				continue;
		        				}
		        			if( seg.getChromStart()>position0.getPosition())
		        				{
		        				willCallNext=false;
		        				break;
		        				}

		        			if(seg.getChromEnd() < position0.getPosition())
		        				{
		        				buffIndex++;
		        				//buffer.removeFirst(); non
		        				continue;
		        				}
		        			
		        			found.add(r);
		        			buffIndex++;
		        			}
						
		        		if(willCallNext && in!=null)
			        		{
		        			String line=null;
		        			while((line=in.readLine())!=null)
		        				{
		        				String tokens[]=delim.split(line);
		        				DataCell bedRow[]=handler.parse(tokens);
		        				
		        				Segment seg=bedsorter.make(bedRow);
		        				if(seg==null) continue;
		        				if(prevBed!=null && prevBed.compareTo(seg)>0)
				        			{
				        			throw new IOException(
				        				"Expected sorted Segment file  but found "+prevBed+" > "+seg	
				        				);
				        			}
		        				prevBed=seg;
		        				
		        				int i= seg.getChromosome().compareTo(position0.getChromosome());
		        				if(i>0)
		        					{
		        					
		        					buffer.add(bedRow);
		        					break;
		        					}
		        				if(i<0) continue;
		        				if( seg.getChromStart()>position0.getPosition())
		        					{
		        					
		        					buffer.add(bedRow);
		        					break;
		        					}
		        				if(seg.getChromEnd() < position0.getPosition())
		        					{
		        					continue;
		        					}
		        				buffer.add(bedRow);
		        				found.add(bedRow);
		        				}
			        		if(line==null)
			        			{
			        			safeClose(in);
			        			in=null;
			        			}
			        		}
		        		
		        		if(!found.isEmpty())
							{
		        			for(DataCell cells[]:found)
			        			{
			        			container1.addRowToTable(new AppendedColumnRow(
			        				RowKey.createRowKey(++outCount),	
			        				row,
			        				cells
			        				));
			        			}
							}
						else
							{
							DataCell empty[]=new DataCell[inDataTableSpec2.getNumColumns()];
		        			for(int i=0;i< empty.length;++i)
		        				{
		        				empty[i]=DataType.getMissingCell();
		        				}
							container1.addRowToTable(new AppendedColumnRow(
			        				RowKey.createRowKey(++outCount),	
			        				row,
			        				empty
			        				));
							}
		        		exec.checkCanceled();
		            	exec.setProgress(nRow/total,"Filtering....");
		        		}
		        	
					} 
		        catch (Exception e)
					{
		        	e.printStackTrace();
					throw e;
					}
				finally
					{
					safeClose(in);
					safeClose(itervcf);
					}
		        if(container1==null)
		        	{
		        	throw new ExecuteException("No data in file to define a valid output table");
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
			safeClose(container1);
			}
       }
    
    @Override
    protected DataTableSpec[] configure(DataTableSpec[] inSpecs)
    		throws InvalidSettingsException {
    	if(inSpecs==null || inSpecs.length!=1)
    		{
    		throw new InvalidSettingsException("Expected one tables");
    		}
    	if(m_ucscHandler.getStringValue().isEmpty())
    		{
    		throw new InvalidSettingsException("UCSC database was not selected");
    		}
    	UcscDatabaseHandler handler=UCSC_HANDLERS.getHandlerById(m_ucscHandler.getStringValue());
    	if(m_ucscHandler.getStringValue().isEmpty())
			{
			throw new InvalidSettingsException("undefined handler "+m_ucscHandler.getStringValue());
			}
    	DataTableSpec in=inSpecs[0];
    	findColumnIndex(in,this.m_chrom1Col,StringCell.TYPE);
		findColumnIndex(in, this.m_posCol,IntCell.TYPE);
    	
    	return new DataTableSpec[]{new DataTableSpec(in,handler.getDataTableSpec())};
    	}
    
    @Override
    protected List<SettingsModel> getSettingsModel() {
    	List<SettingsModel> L=new ArrayList<SettingsModel>( super.getSettingsModel());
    	L.add(this.m_chrom1Col);
    	L.add(this.m_posCol);
    	L.add(this.m_ucscHandler);

    	return L;
    	}
	}


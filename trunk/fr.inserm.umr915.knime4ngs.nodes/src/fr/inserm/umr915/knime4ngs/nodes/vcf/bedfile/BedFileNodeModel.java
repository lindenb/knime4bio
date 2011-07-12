package fr.inserm.umr915.knime4ngs.nodes.vcf.bedfile;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

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
import org.knime.core.data.def.StringCell;
import org.knime.core.node.BufferedDataContainer;
import org.knime.core.node.BufferedDataTable;
import org.knime.core.node.ExecutionContext;
import org.knime.core.node.InvalidSettingsException;
import org.knime.core.node.defaultnodesettings.SettingsModel;
import org.knime.core.node.defaultnodesettings.SettingsModelBoolean;
import org.knime.core.node.defaultnodesettings.SettingsModelColumnName;
import org.knime.core.node.defaultnodesettings.SettingsModelInteger;
import org.knime.core.node.defaultnodesettings.SettingsModelString;

import fr.inserm.umr915.knime4ngs.corelib.bio.Position;
import fr.inserm.umr915.knime4ngs.corelib.bio.PositionKSorter;
import fr.inserm.umr915.knime4ngs.corelib.bio.Segment;
import fr.inserm.umr915.knime4ngs.corelib.knime.ExecuteException;
import fr.inserm.umr915.knime4ngs.nodes.vcf.AbstractVCFNodeModel;





/**
 * 
 */
public class BedFileNodeModel extends AbstractVCFNodeModel
	{
	final static String DEFAULT_CHROM1_COL="CHROM";
	static final String CHROM1_COL_PROPERTY="chrom1.col";
	final static String DEFAULT_POS_COL="POS";
	static final String POS_COL_PROPERTY="pos.col";
	final static int DEFAULT_CHROM2_COL=-1;
	static final String CHROM2_COL_PROPERTY="chrom2.col";
	final static int DEFAULT_START_COL=-1;
	static final String START_COL_PROPERTY="start.col";
	static final String END_COL_PROPERTY="end";
	final static int DEFAULT_END_COL=-1;
	
	static final String FILENAME_URI_PROPERTY="file.uri";
	final static String DEFAULT_FILENAME_URI="";
	
	static final String FILE_DELIM_PROPERTY="file.delim";
	final static String DEFAULT_FILE_DELIM="[\\t]";
	
	static final String FILE_IGNORE_PROPERTY="file.ignore";
	final static String DEFAULT_FILE_IGNORE="#";
	
	static final String COL_PREFIX_PROPERTY="column.prefix";
	final static String DEFAULT_ICOL_PREFIX="ext.";
	
	static final String STOP_FIRST_PROPERTY="stop.first";
	final static boolean STOP_FIRST_DEF=false;
	
	private final SettingsModelColumnName m_chrom1Col =
        new SettingsModelColumnName(CHROM1_COL_PROPERTY,DEFAULT_CHROM1_COL);
	private final SettingsModelColumnName m_posCol =
        new SettingsModelColumnName(POS_COL_PROPERTY,DEFAULT_POS_COL);
	private final SettingsModelInteger m_chrom2Col =
        new SettingsModelInteger(CHROM2_COL_PROPERTY,DEFAULT_CHROM2_COL);
	private final SettingsModelInteger m_start =
        new SettingsModelInteger(START_COL_PROPERTY,DEFAULT_START_COL);
	private final SettingsModelInteger m_end =
        new SettingsModelInteger(END_COL_PROPERTY,DEFAULT_END_COL);
	
	private final SettingsModelString m_fileUri =
        new SettingsModelString(FILENAME_URI_PROPERTY,DEFAULT_FILENAME_URI);
	private final SettingsModelString m_fileDelim =
        new SettingsModelString(FILE_DELIM_PROPERTY,DEFAULT_FILE_DELIM);
	private final SettingsModelString m_fileIgnore =
        new SettingsModelString(FILE_IGNORE_PROPERTY,DEFAULT_FILE_IGNORE);
	private final SettingsModelString m_columnPrefix =
        new SettingsModelString(COL_PREFIX_PROPERTY,DEFAULT_ICOL_PREFIX);
	private final SettingsModelBoolean m_stopFirst =
        new SettingsModelBoolean(STOP_FIRST_PROPERTY,STOP_FIRST_DEF);
	
	class BedSorter
	implements Comparator<List<String>>
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
		
		
		
		public Segment make(List<String> array)
			{
			if(array.size()<= chromCol) throw new IllegalArgumentException("no chrom column in "+array);
			if(array.size()<= chromStartCol) throw new IllegalArgumentException("no chromStart column in "+array);
			if(array.size()<= chromEndCol) throw new IllegalArgumentException("no chromEnd column in "+array);
			String chrom=unquote(array.get(chromCol));
			int start=Integer.parseInt(array.get(chromStartCol));
			int end=Integer.parseInt(array.get(chromEndCol));
			return new Segment(chrom, start,end);
			}
		@Override
		public int compare(List<String> o1,List<String> o2) {
			return make(o1).compareTo(make(o2));
			}

		}
	
    /**
     * Constructor for the node model.
     */
    public BedFileNodeModel()
    	{
        super(1,1);
    	}
    
    private static String unquote(String s)
		{
		if(
			(s.startsWith("\'") && s.endsWith("\'") && s.length()>1) || 
			(s.startsWith("\"") && s.endsWith("\"") && s.length()>1))
			{
			s=s.substring(1,s.length()-1);
			}
		return s;
		}
    
    @Override
    protected BufferedDataTable[] execute(
    		final BufferedDataTable[] inData,
            final ExecutionContext exec
            ) throws Exception
            {
			BufferedDataContainer container1=null;
			String fileUri=this.m_fileUri.getStringValue();
			if(fileUri.isEmpty()) throw new ExecuteException("file uri is empty");
			BedSorter bedsorter=new BedSorter(
					this.m_chrom2Col.getIntValue()-1,
					this.m_start.getIntValue()-1,
					this.m_end.getIntValue()-1
					);
			String skipLineStartingWith=this.m_fileIgnore.getStringValue();
			Pattern delim=Pattern.compile(this.m_fileDelim.getStringValue());
			
			try
		    	{
		        // the data table spec of the single output table, 
		        // the table will have three columns:
				BufferedDataTable vcfTable=inData[0];
				
		       
				DataTableSpec inDataTableSpec1 =vcfTable.getDataTableSpec();
				DataTableSpec inDataTableSpec2=null;//still undefined here

				
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
		        LinkedList<List<String>> buffer=new LinkedList<List<String>>();
		        BufferedReader in=null;
		        try {
		        	itervcf=vcfTable.iterator();
		        	
		        	in=openReader(fileUri);
		        	while(itervcf.hasNext())
		        		{
		        		++nRow;
		        		DataRow row=itervcf.next();
		        		Position position0= vcfSorter.make(row);
		        		if(position0==null) continue;
		        		if(prevVCF!=null && prevVCF.compareTo(position0)>0)
		        			{
		        			throw new IOException(
		        				"Expected sorted VCF file  but found "+prevVCF+" > "+position0	
		        				);
		        			}
		        		prevVCF=position0;
		        		List<List<String>> found=new ArrayList<List<String>>();
		        		
		        		
		        		boolean willCallNext=true;
		        		int buffIndex=0;
		        		while(buffIndex< buffer.size())
		        			{
		        			List<String> r=buffer.get(buffIndex);
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
		        				
		        				if(!skipLineStartingWith.isEmpty())
		        					{
		        					if(line.startsWith(skipLineStartingWith)) continue;
		        					}
		        				String tokens[]=delim.split(line);
		        				List<String> bedRow=new ArrayList<String>(Arrays.asList(tokens));
		        				for(int i=0;i< bedRow.size();++i)
		        					{
		        					bedRow.set(i, unquote(bedRow.get(i)));
		        					}
		        				
		        				//first time we see data
		        				if(container1==null)
		        					{
		        					DataColumnSpec cells[]=new DataColumnSpec[bedRow.size()];
		        					for(int i=0;i< bedRow.size();++i)
		        						{
		        						cells[i]=new DataColumnSpecCreator(
		        							this.m_columnPrefix+"."+i,
		        							(i==bedsorter.chromEndCol || i==bedsorter.chromStartCol?IntCell.TYPE:StringCell.TYPE)
		        							).createSpec();
		        						}
		        					inDataTableSpec2= new DataTableSpec(cells);
		        					container1=exec.createDataContainer(
		        						new DataTableSpec(inDataTableSpec1,
		        						inDataTableSpec2)	
		        						);
		        					}
		        				else
		        					{
		        					//less columns than expected ?
		        					while(bedRow.size()< inDataTableSpec2.getNumColumns() )
		        						{
		        						bedRow.add("");
		        						}
		        					}
		        				
		        				Segment seg=bedsorter.make(bedRow);
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
		        			for(List<String> r2:found)
			        			{
		        				DataCell cells[]=new DataCell[inDataTableSpec2.getNumColumns()];
			        			for(int i=0;i< cells.length;++i)
			        				{
			        				cells[i]=(i==bedsorter.chromEndCol || i==bedsorter.chromStartCol?
			        						 new IntCell(Integer.parseInt(r2.get(i)))
			        						:(r2.get(i).isEmpty()?DataType.getMissingCell():new StringCell(r2.get(i)))
			        						);
			        				}
		        				
		        				
			        			container1.addRowToTable(new AppendedColumnRow(
			        				RowKey.createRowKey(++outCount),	
			        				row,
			        				cells
			        				));
			        			if(this.m_stopFirst.getBooleanValue()) break;
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
		            	exec.setProgress(nRow/total,"BED....");
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
    
    @Override
    protected DataTableSpec[] configure(DataTableSpec[] inSpecs)
    		throws InvalidSettingsException {
    	if(inSpecs==null || inSpecs.length!=1)
    		{
    		throw new InvalidSettingsException("Expected one tables");
    		}
    	DataTableSpec in=inSpecs[0];
    	findColumnIndex(in,this.m_chrom1Col,StringCell.TYPE);
		findColumnIndex(in, this.m_posCol,IntCell.TYPE);
    	
    	return new DataTableSpec[]{null};
    	}
    
    @Override
    protected List<SettingsModel> getSettingsModel() {
    	List<SettingsModel> L=new ArrayList<SettingsModel>( super.getSettingsModel());
    	L.add(this.m_chrom1Col);
    	L.add(this.m_posCol);
    	L.add(this.m_chrom2Col);
    	L.add(this.m_start);
    	L.add(this.m_end);
    	L.add(this.m_fileUri);
    	L.add(this.m_fileDelim);
    	L.add(this.m_fileIgnore);
    	L.add(this.m_columnPrefix);
    	L.add(this.m_stopFirst);
    	return L;
    	}
	}


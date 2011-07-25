package fr.inserm.umr915.knime4ngs.nodes.vcf.wig;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



import org.knime.base.data.append.column.AppendedColumnRow;
import org.knime.core.data.DataCell;
import org.knime.core.data.DataColumnSpec;
import org.knime.core.data.DataColumnSpecCreator;
import org.knime.core.data.DataRow;
import org.knime.core.data.DataTableSpec;
import org.knime.core.data.DataType;
import org.knime.core.data.RowIterator;

import org.knime.core.data.def.DoubleCell;
import org.knime.core.data.def.IntCell;
import org.knime.core.data.def.StringCell;
import org.knime.core.node.BufferedDataContainer;
import org.knime.core.node.BufferedDataTable;
import org.knime.core.node.ExecutionContext;
import org.knime.core.node.InvalidSettingsException;
import org.knime.core.node.defaultnodesettings.SettingsModel;
import org.knime.core.node.defaultnodesettings.SettingsModelBoolean;
import org.knime.core.node.defaultnodesettings.SettingsModelColumnName;
import org.knime.core.node.defaultnodesettings.SettingsModelString;

import fr.inserm.umr915.knime4ngs.corelib.knime.ExecuteException;
import fr.inserm.umr915.knime4ngs.nodes.vcf.AbstractVCFNodeModel;

/**
 * Model for WIG file
 */
public class WigNodeModel extends AbstractVCFNodeModel
	{
	
	final static String DEFAULT_CHROM1_COL="CHROM";
	static final String CHROM1_COL_PROPERTY="chrom.col";
	private final SettingsModelColumnName m_chromCol =
        new SettingsModelColumnName(CHROM1_COL_PROPERTY,DEFAULT_CHROM1_COL);

	final static String DEFAULT_POS1_COL="POS";
	static final String POS1_COL_PROPERTY="pos.col";
	private final SettingsModelColumnName m_posCol =
        new SettingsModelColumnName(POS1_COL_PROPERTY,DEFAULT_POS1_COL);
	

	final static String DEFAULT_WIG_URI="file.wig";
	static final String WIG_URI_PROPERTY="wig.uri";
	private final SettingsModelString m_wigUri =
        new SettingsModelString(WIG_URI_PROPERTY,DEFAULT_WIG_URI);
	
	final static String WIG_COLNAME_DEF="wig.value";
	static final String  WIG_COLNAME="col.name";
	private final SettingsModelString m_newColName =
        new SettingsModelString(WIG_COLNAME,WIG_COLNAME_DEF);
	
	
	final static boolean USE_STEP_DEF=false;
	static final String  USE_STEP_PROP="use.step";
	private final SettingsModelBoolean m_useStep =
        new SettingsModelBoolean(USE_STEP_PROP,USE_STEP_DEF);
	
	private static class Position
	implements Comparable<Position>
		{
		String chrom;
		int position0;
		Position(String chrom,int position0)
			{
			this.chrom=chrom;
			this.position0=position0;
			}
			
		public String getChromosome()
	 		{
	 		return chrom;
	 		}	
	 	public int getPosition()
	 		{
	 		return position0;
	 		}
			
		@Override
		public int compareTo(Position pos)
			{
			int i=chrom.compareTo(pos.chrom);
			if(i!=0) return i;
			i= position0-pos.position0;
			return i;
			}
		@Override
		public String toString() {
			return getChromosome()+":"+getPosition();
			}
		}

	/** PositionKSorter */
	private static class PositionKSorter
	implements java.util.Comparator<DataRow>
		{
		int chromCol=-1;
		int posCol=-1;
		
		PositionKSorter(int chromCol,int posCol)
			{
			this.chromCol=chromCol;
			this.posCol=posCol;
			}
		
		public Position make(DataRow r)
			{
			if(	r.getCell(chromCol).isMissing() ||
				r.getCell(posCol).isMissing()) return null;
			return new Position(
				StringCell.class.cast(r.getCell(chromCol)).getStringValue(),
				IntCell.class.cast(r.getCell(posCol)).getIntValue()-1 //switch to a 0 based data
				);
			}
		@Override
		public int compare(DataRow r1,DataRow r2)
			{
			return make(r1).compareTo(make(r2));
			}
		}
	
	private static class WigRecord
		{
		String chrom;
		int chromStart;
		int chromEnd;
		double value;
		int compareTo(Position pos)
			{
			int i=this.chrom.compareTo(pos.chrom);
			if(i!=0) return i;
			if(this.chromEnd<=pos.position0) return -1;
			if(this.chromStart>pos.position0) return 1;
			return 0;
			}
		@Override
		public String toString() {
			return chrom+":"+chromStart+"-"+chromEnd;
			}
		}
	
	private static enum STEP { FIXED, VARIABLE, UN};
	
	
	/** WigProcessor */
	private static class WigProcessor
		{
		BufferedReader in=null;
		private String currChrom=null;
		private int currPosition=0;
		private int currStep=1;
		private int currSpan=1;
		private WigRecord buffer=null;
		private STEP stepType=STEP.UN;
		boolean useStepInsteadOfSpan=USE_STEP_DEF;
		
		private void split(String line)
			{
			currChrom=null;
			currPosition=0;
			currStep=1;
			currSpan=1;
			buffer=null;
			
			for(String s:line.split("[ ]+"))
				{
				if(s.startsWith("chrom="))
					{
					int i=s.indexOf('=');
					currChrom=s.substring(i+1);
					}
				else if(s.startsWith("start="))
					{
					int i=s.indexOf('=');
					currPosition=Integer.parseInt(s.substring(i+1));
					/* WIGs ARE +1 based !!! see http://genome.ucsc.edu/goldenPath/help/wiggle.html
					"For a chromosome of length N, the first position is 1 and the last position is N."
					*/
					currPosition--;
					}
				else if(s.startsWith("step="))
					{
					int i=s.indexOf('=');
					currStep=Integer.parseInt(s.substring(i+1));
					}
				else if(s.startsWith("span="))
					{
					int i=s.indexOf('=');
					currSpan=Integer.parseInt(s.substring(i+1));
					}
				}
			}
		
		private void fixedStep(String line)
			{
			stepType=STEP.FIXED;
			split(line);
			}

		protected void variableStep(String line)
			{
			stepType=STEP.VARIABLE;
			split(line);
			}
		
		public WigRecord next(final Position pos) throws IOException
			{
			if(buffer!=null && buffer.compareTo(pos)==0)
				{
				return buffer;
				}
			if(in==null)
				{
				return null;
				}
			String line=null;
			while((line=in.readLine())!=null)
				{
				if(line.startsWith("fixedStep"))
					{
					fixedStep(line);
					continue;
					}
				else if(line.startsWith("variableStep"))
					{
					variableStep(line);
					continue;
					}
				else if(line.isEmpty())
					{
					continue;
					}
				else if(stepType==STEP.UN)
					{
					throw new IOException("undefined step type before "+line);
					}
				int j= pos.chrom.compareTo(this.currChrom);
				
				
				
				if(j>0)
					{
					continue;
					}
				if(j<0)
					{
					buffer=null;
					return null;
					}
				
				
				if(buffer==null) buffer=new WigRecord();
				buffer.chrom=this.currChrom;
				switch(stepType)
					{
					case FIXED:
						{
						buffer.chromStart=currPosition;
						buffer.chromEnd=buffer.chromStart+(useStepInsteadOfSpan?currStep:currSpan); //CHANGE currSpan;
						buffer.value=Double.parseDouble(line);
						break;
						}
					case VARIABLE:
						{
						int i=line.indexOf(" ");
						if(i==-1) i=line.indexOf("\t");
						if(i==-1) throw new IOException("space missing in "+line);
						buffer.chromStart=Integer.parseInt(line.substring(0,i));
						buffer.chromEnd=buffer.chromStart+currSpan;
						buffer.value=Double.parseDouble(line.substring(i+1));
						break;
						}
					}

				currPosition+=currStep;
				
				int i= buffer.compareTo(pos);
				if(i>0)
					{
					return null;
					}
				if(i<0)
					{
					continue;
					}
				
				return buffer;
				}
			if(line==null && in!=null)
				{
				in.close();
				in=null;
				}
			return null;
			}
		}
	
    /**
     * Constructor for the node model.
     */
    public WigNodeModel()
    	{
        super(1,1);
    	}
    
    protected DataTableSpec createDataTableSpec()
    	{
    	DataColumnSpec cols[]=new DataColumnSpec[]{
    		new DataColumnSpecCreator(this.m_newColName.getStringValue(),DoubleCell.TYPE).createSpec()
    		};
    	return new DataTableSpec(cols);
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
				BufferedDataTable table1=inData[0];
				DataTableSpec inspec1 = table1.getDataTableSpec();

				
				PositionKSorter sorter=new PositionKSorter(
						findColumnIndex(inspec1,this.m_chromCol.getColumnName(),StringCell.TYPE),
						findColumnIndex(inspec1,this.m_posCol.getColumnName(),IntCell.TYPE)
					);
				
				
				DataTableSpec merged=new DataTableSpec(inspec1,createDataTableSpec());
		        container1 = exec.createDataContainer(merged);
		        
		        
		        double total=table1.getRowCount();
		       
		        RowIterator iter=null;
		        Position prev=null;
		        WigProcessor proc=new WigProcessor();
		        proc.useStepInsteadOfSpan=this.m_useStep.getBooleanValue();
		        int nRow=0;
		        try {
		        	proc.in=openReader(this.m_wigUri.getStringValue());
		        	iter=table1.iterator();
		        	while(iter.hasNext())
			        	{
		        		++nRow;
			        	DataRow row=iter.next();
			        	Position pos=sorter.make(row);
			        	WigRecord rec=null;
			        	if(pos!=null)
			        		{
			        		rec= proc.next(pos);
			        		if(prev!=null && prev.compareTo(pos)>0)
			        			{
			        			throw new ExecuteException("Expected the data to be sorted on CHROM/POS but got "+pos+" after "+prev);
			        			}
			        		prev=pos;
			        		}
			        	if(rec==null)
			        		{
			        		DataCell cell=DataType.getMissingCell();
			        		container1.addRowToTable(new AppendedColumnRow(row, cell));
			        		}
			        	else
			        		{
			        		DataCell cell=new DoubleCell(rec.value);
			        		container1.addRowToTable(new AppendedColumnRow(row, cell));
			        		}
			        	exec.checkCanceled();
		            	exec.setProgress(nRow/total,"Wig....");
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
					safeClose(proc.in);
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
    		throw new InvalidSettingsException("Expected one table");
    		}
    	DataTableSpec in=inSpecs[0];
    	findColumnIndex(in, m_chromCol,StringCell.TYPE);
    	findColumnIndex(in, m_posCol,IntCell.TYPE);
    	return new DataTableSpec[]{new DataTableSpec(in,createDataTableSpec())};
    	}
    
    @Override
    protected List<SettingsModel> getSettingsModel() {
    	List<SettingsModel> L=new ArrayList<SettingsModel>( super.getSettingsModel());
    	L.add(this.m_chromCol);
    	L.add(this.m_posCol);
    	L.add(this.m_wigUri);
    	L.add(this.m_newColName);
    	L.add(this.m_useStep);
    	return L;
    	}
	}


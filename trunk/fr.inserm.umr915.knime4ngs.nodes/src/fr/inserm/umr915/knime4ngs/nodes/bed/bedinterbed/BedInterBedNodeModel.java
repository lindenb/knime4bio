package fr.inserm.umr915.knime4ngs.nodes.bed.bedinterbed;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.knime.core.data.DataCell;
import org.knime.core.data.DataRow;
import org.knime.core.data.DataTableSpec;
import org.knime.core.data.RowIterator;
import org.knime.core.data.RowKey;
import org.knime.core.data.def.DefaultRow;
import org.knime.core.data.def.IntCell;
import org.knime.core.data.def.StringCell;
import org.knime.core.node.BufferedDataContainer;
import org.knime.core.node.BufferedDataTable;
import org.knime.core.node.ExecutionContext;
import org.knime.core.node.InvalidSettingsException;
import org.knime.core.node.defaultnodesettings.SettingsModel;
import org.knime.core.node.defaultnodesettings.SettingsModelColumnName;
import org.knime.core.node.defaultnodesettings.SettingsModelString;
import fr.inserm.umr915.knime4ngs.corelib.bio.BedKSorter;
import fr.inserm.umr915.knime4ngs.corelib.bio.Segment;
import fr.inserm.umr915.knime4ngs.nodes.vcf.AbstractVCFNodeModel;





/**
 * BedInterBedNodeModel
 */
public class BedInterBedNodeModel extends AbstractVCFNodeModel
	{
	static final String CHROM1_COL_PROPERTY="chrom1.col";
	final static String DEFAULT_CHROM1_COL="chrom";
	
	static final String START1_COL_PROPERTY="start1.col";
	final static String DEFAULT_START1_COL="start";
	
	static final String END1_COL_PROPERTY="end1.col";
	final static String DEFAULT_END1_COL="end";
	

	private final SettingsModelColumnName m_chrom1Col =
        new SettingsModelColumnName(CHROM1_COL_PROPERTY,DEFAULT_CHROM1_COL);
	private final SettingsModelColumnName m_start1 =
        new SettingsModelColumnName(START1_COL_PROPERTY,DEFAULT_START1_COL);
	private final SettingsModelColumnName m_end1 =
        new SettingsModelColumnName(END1_COL_PROPERTY,DEFAULT_END1_COL);
	
	static final String CHROM2_COL_PROPERTY="chrom2.col";
	final static String DEFAULT_CHROM2_COL="chrom";
	
	static final String START2_COL_PROPERTY="start2.col";
	final static String DEFAULT_START2_COL="start";
	
	static final String END2_COL_PROPERTY="end2.col";
	final static String DEFAULT_END2_COL="end";

	private final SettingsModelColumnName m_chrom2Col =
        new SettingsModelColumnName(CHROM2_COL_PROPERTY,DEFAULT_CHROM2_COL);
	private final SettingsModelColumnName m_start2 =
        new SettingsModelColumnName(START2_COL_PROPERTY,DEFAULT_START2_COL);
	private final SettingsModelColumnName m_end2 =
        new SettingsModelColumnName(END2_COL_PROPERTY,DEFAULT_END2_COL);
	
	enum OverlapType {OVERLAP_BOTH,ONLY_FIRST,NO_OVERLAP_FIRST};
	static final String OVERLAP_PROPERTY="overlap";
	static final String OVERLAP_DEFAULT_VALUES[]=new String[]{
		OverlapType.OVERLAP_BOTH.name(),
		OverlapType.ONLY_FIRST.name(),
		OverlapType.NO_OVERLAP_FIRST.name(),
		};
	private final SettingsModelString m_overlapType =
        new SettingsModelString(OVERLAP_PROPERTY,OVERLAP_DEFAULT_VALUES[1]);
	
    /**
     * Constructor for the node model.
     */
    protected BedInterBedNodeModel()
    	{
        super(2,1);
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
				BufferedDataTable bed1Table=inData[0];
				BufferedDataTable bed2Table=inData[1];
		       
				DataTableSpec inDataTableSpec1 = bed1Table.getDataTableSpec();
				DataTableSpec inDataTableSpec2 = bed2Table.getDataTableSpec();

				
				BedKSorter bedsorter1=new BedKSorter(
						inDataTableSpec1.findColumnIndex(this.m_chrom1Col.getColumnName()),
						inDataTableSpec1.findColumnIndex(this.m_start1.getColumnName()),
						inDataTableSpec1.findColumnIndex(this.m_end1.getColumnName())
						);
				
			
				
				BedKSorter bedsorter2=new BedKSorter(
						inDataTableSpec2.findColumnIndex(this.m_chrom2Col.getColumnName()),
						inDataTableSpec2.findColumnIndex(this.m_start2.getColumnName()),
						inDataTableSpec2.findColumnIndex(this.m_end2.getColumnName())
						);
				final OverlapType overlapType=getOverlapType();
				
				switch(overlapType)
					{
				    case OVERLAP_BOTH:
				    	{
				    	container1= exec.createDataContainer(new DataTableSpec(inDataTableSpec1, inDataTableSpec2));
						break;
				    	}
					case NO_OVERLAP_FIRST:
					case ONLY_FIRST:
					default:
						{
						container1= exec.createDataContainer(inDataTableSpec1);
						break;
						}
					}
				

		        
		       
		        double total=bed1Table.getRowCount();
		        int nRow=0;
		        int outCount=0;
		        RowIterator iterbed1=null;
		        RowIterator iterbed2=null;
		        Segment prevSegment1=null;
		        Segment prevSegment2=null;
		        LinkedList<DataRow> buffer=new LinkedList<DataRow>();
		        try {
		        	iterbed1=bed1Table.iterator();
		        	iterbed2=bed2Table.iterator();
		        	while(iterbed1.hasNext())
		        		{
		        		++nRow;
		        		DataRow row1=iterbed1.next();
		        		Segment segment1= bedsorter1.make(row1);
		        		if(segment1==null) continue;
		        		
		        		if(prevSegment1!=null && prevSegment1.compareTo(segment1)>0)
		        			{
		        			throw new IOException(
		        				"Expected sorted BED(1) file  but found "+prevSegment1+" > "+segment1	
		        				);
		        			}
		        		prevSegment1=segment1;
		        		boolean found_one_overlap_segment1=false;
		        		
		        		/* scan BED2 buffer */
		        		boolean willCallNext=true;
		        		int buffIndex=0;
		        		while(buffIndex< buffer.size())
		        			{
		        			DataRow row2=buffer.get(buffIndex);
		        			Segment segment2=bedsorter2.make(row2);
		        			
		        			int i= segment2.getChromosome().compareTo(segment1.getChromosome());
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
		        			if( segment2.getChromStart() >= segment1.getChromEnd())
		        				{
		        				willCallNext=false;
		        				break;
		        				}

		        			if(segment2.getChromEnd() <= segment1.getChromStart())
		        				{
		        				buffIndex++;
		        				//buffer.remove(buffIndex); non
		        				continue;
		        				}
		        			
		        			switch(overlapType)
		        				{
		        				case OVERLAP_BOTH:
			        				{
			        				List<DataCell> cells=new ArrayList<DataCell>(row1.getNumCells()+row2.getNumCells());
			        				for(int k=0;k< row1.getNumCells();++k)
				        				{
			        					cells.add(row1.getCell(k));	
				        				}
			        				for(int k=0;k< row2.getNumCells();++k)
				        				{
			        					cells.add(row2.getCell(k));	
				        				}
			        				container1.addRowToTable(new DefaultRow(
			        					RowKey.createRowKey(++outCount),
			        					cells
			        					));
			        				break;
			        				}
		        				case ONLY_FIRST:
		        					{
		        					if(!found_one_overlap_segment1)
		        						{
		        						container1.addRowToTable(row1);
		        						}
		        					break;
		        					}
		        				}
		        			found_one_overlap_segment1=true;
		        			buffIndex++;
		        			}
						
		        		if(willCallNext)
			        		{
		        			while(iterbed2.hasNext())
		        				{
		        				exec.checkCanceled();
		        				DataRow row2=iterbed2.next();
		        				Segment segment2=bedsorter2.make(row2);
		        				if(segment2==null) continue;
		        				if(prevSegment2!=null && prevSegment2.compareTo(segment2)>0)
				        			{
				        			throw new IOException(
				        				"Expected a sorted segment file  but found "+prevSegment2+" > "+segment2	
				        				);
				        			}
		        				prevSegment2=segment2;
		        				
		        				int i= segment2.getChromosome().compareTo(segment1.getChromosome());
		        				if(i>0)
		        					{
		        					buffer.add(row2);
		        					break;
		        					}
		        				if(i<0) continue;
		        				if( segment2.getChromStart() >= segment1.getChromEnd())
		        					{
		        					buffer.add(row2);
		        					break;
		        					}
		        				if( segment2.getChromEnd() <= segment1.getChromStart())
		        					{
		        					continue;
		        					}
		        				buffer.add(row2);
		        				
		        				switch(overlapType)
			        				{
			        				case OVERLAP_BOTH:
				        				{
				        				List<DataCell> cells=new ArrayList<DataCell>(row1.getNumCells()+row2.getNumCells());
				        				for(int k=0;k< row1.getNumCells();++k)
					        				{
				        					cells.add(row1.getCell(k));	
					        				}
				        				for(int k=0;k< row2.getNumCells();++k)
					        				{
				        					cells.add(row2.getCell(k));	
					        				}
				        				container1.addRowToTable(new DefaultRow(
				        					RowKey.createRowKey(++outCount),
				        					cells
				        					));
				        				break;
				        				}
			        				case ONLY_FIRST:
			        					{
			        					if(!found_one_overlap_segment1)
			        						{
			        						container1.addRowToTable(row1);
			        						}
			        					break;
			        					}
			        				}
		        				found_one_overlap_segment1=true;
		        				}
			        			
			        		}
		        		
		        		if(!found_one_overlap_segment1 &&
		        				overlapType==OverlapType.NO_OVERLAP_FIRST)
							{
		        			container1.addRowToTable(row1);
							}
						
		        		exec.checkCanceled();
		            	exec.setProgress(nRow/total,"Bed vs Bed....");
		        		}
		        	
					} 
		        catch (Exception e)
					{
		        	e.printStackTrace();
					throw e;
					}
				finally
					{
					safeClose(iterbed1);
					safeClose(iterbed2);
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
    	if(inSpecs==null || inSpecs.length!=2)
    		{
    		throw new InvalidSettingsException("Expected two tables");
    		}
    	DataTableSpec t1=inSpecs[0];
    	findColumnIndex(t1,this.m_chrom1Col,StringCell.TYPE);
		findColumnIndex(t1, this.m_start1,IntCell.TYPE);
		findColumnIndex(t1, this.m_end1,IntCell.TYPE);
		
		DataTableSpec t2=inSpecs[1];
		findColumnIndex(t2,this.m_chrom2Col,StringCell.TYPE);
		findColumnIndex(t2, this.m_start2,IntCell.TYPE);
		findColumnIndex(t2, this.m_end2,IntCell.TYPE);
		
		
		
		switch(getOverlapType())
			{
		    case OVERLAP_BOTH: return new DataTableSpec[]{new DataTableSpec(t1,t2)};
			case NO_OVERLAP_FIRST:
			case ONLY_FIRST:
			default:
				{
				return new DataTableSpec[]{t1};
				}
			}
    	
    	}
    
    private OverlapType getOverlapType()
    	{
    	String s=this.m_overlapType.getStringValue();
    	if(s==null || s.equals(OVERLAP_DEFAULT_VALUES[1])) return OverlapType.ONLY_FIRST;
    	if(s.equals(OVERLAP_DEFAULT_VALUES[0])) return OverlapType.OVERLAP_BOTH;
    	if(s.equals(OVERLAP_DEFAULT_VALUES[2])) return OverlapType.NO_OVERLAP_FIRST;
    	return OverlapType.ONLY_FIRST;
    	}
    
    @Override
    protected List<SettingsModel> getSettingsModel() {
    	List<SettingsModel> L=new ArrayList<SettingsModel>( super.getSettingsModel());
    	L.add(this.m_chrom1Col);
    	L.add(this.m_start1);
    	L.add(this.m_end1);
    	L.add(this.m_chrom2Col);
    	L.add(this.m_start2);
    	L.add(this.m_end2);
    	L.add(this.m_overlapType);
    	return L;
    	}
	}


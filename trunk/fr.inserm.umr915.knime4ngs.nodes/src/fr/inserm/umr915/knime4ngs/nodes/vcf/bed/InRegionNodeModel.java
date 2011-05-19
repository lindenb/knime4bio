package fr.inserm.umr915.knime4ngs.nodes.vcf.bed;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


import org.knime.base.data.sort.SortedTable;
import org.knime.core.data.DataRow;
import org.knime.core.data.DataTableSpec;
import org.knime.core.data.RowIterator;

import org.knime.core.data.def.IntCell;
import org.knime.core.data.def.StringCell;
import org.knime.core.node.BufferedDataContainer;
import org.knime.core.node.BufferedDataTable;
import org.knime.core.node.ExecutionContext;
import org.knime.core.node.InvalidSettingsException;
import org.knime.core.node.defaultnodesettings.SettingsModel;
import org.knime.core.node.defaultnodesettings.SettingsModelBoolean;
import org.knime.core.node.defaultnodesettings.SettingsModelColumnName;



import fr.inserm.umr915.knime4ngs.corelib.bio.BedKSorter;
import fr.inserm.umr915.knime4ngs.corelib.bio.Position;
import fr.inserm.umr915.knime4ngs.corelib.bio.PositionKSorter;
import fr.inserm.umr915.knime4ngs.corelib.bio.Segment;
import fr.inserm.umr915.knime4ngs.nodes.vcf.AbstractVCFNodeModel;





/**
 * 
 */
public class InRegionNodeModel extends AbstractVCFNodeModel
	{

	final static String DEFAULT_CHROM_COL="chrom";
	static final String CHROM_COL_PROPERTY="chrom.col";
	final static String DEFAULT_START_COL="start";
	static final String START_COL_PROPERTY="start.col";
	final static String DEFAULT_END_COL="end";
	static final String END_COL_PROPERTY="end.col";
	
	
	final static boolean DEFAULT_ZERO=true;
	static final String ZERO_PROPERTY="zero.based";
	
	private final SettingsModelColumnName m_chromCol =
        new SettingsModelColumnName(CHROM_COL_PROPERTY,DEFAULT_CHROM_COL);
	private final SettingsModelColumnName m_start =
        new SettingsModelColumnName(START_COL_PROPERTY,DEFAULT_START_COL);
	private final SettingsModelColumnName m_end =
        new SettingsModelColumnName(END_COL_PROPERTY,DEFAULT_END_COL);
	private final SettingsModelBoolean m_isZeroBased =
        new SettingsModelBoolean(ZERO_PROPERTY,DEFAULT_ZERO);
	
	
    /**
     * Constructor for the node model.
     */
    protected InRegionNodeModel()
    	{
        super(2,2);
    	}
    
    private Segment toZeroBased(Segment seg)
    	{
    	if(!this.m_isZeroBased.getBooleanValue())
    		{
    		seg=new Segment(seg.getChromosome(), seg.getChromStart()-1,seg.getChromEnd()-1);
    		}
    	return seg;
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
		        // the data table spec of the single output table, 
		        // the table will have three columns:
				BufferedDataTable vcfTable=inData[0];
				BufferedDataTable bedTable=inData[1];
		       
				DataTableSpec inDataTableSpec1 = vcfTable.getDataTableSpec();
				DataTableSpec inDataTableSpec2 = bedTable.getDataTableSpec();

				
				BedKSorter bedsorter=new BedKSorter(
						inDataTableSpec2.findColumnIndex(this.m_chromCol.getColumnName()),
						inDataTableSpec2.findColumnIndex(this.m_start.getColumnName()),
						inDataTableSpec2.findColumnIndex(this.m_end.getColumnName())
						);
				System.err.println("Sort BED");
				SortedTable sortedBed=new SortedTable(bedTable, bedsorter,false, exec);
				
				PositionKSorter vcfSorter=new PositionKSorter(
					findColumnIndex(inDataTableSpec1, "CHROM",StringCell.TYPE),
					findColumnIndex(inDataTableSpec1, "POS",IntCell.TYPE)
					);
				System.err.println("Sort VCF");
				SortedTable sortedVCF=new SortedTable(vcfTable, vcfSorter,false, exec);
				
		        container1 = exec.createDataContainer(inDataTableSpec1);
		        container2 = exec.createDataContainer(inDataTableSpec1);
		        
		       
		        double total=bedTable.getRowCount();
		        int nRow=0;
		        RowIterator itervcf=null;
		        RowIterator iterbed=null;
		        LinkedList<Segment> buffer=new LinkedList<Segment>();
		        try {
		        	itervcf=sortedVCF.iterator();
		        	iterbed=sortedBed.iterator();
		        	while(itervcf.hasNext())
		        		{
		        		++nRow;
		        		DataRow row=itervcf.next();
		        		Position position1= vcfSorter.make(row);
		        		Position position0= new Position(position1.getChromosome(), position1.getPosition()-1);
		        		boolean found=false;
		        		
		        		while(!buffer.isEmpty())
		        			{
		        			Segment seg=buffer.getFirst();
		        			int i= seg.getChromosome().compareTo(position0.getChromosome());
		        			if(i>0) break;
		        			if(i<0)
		        				{
		        				buffer.removeFirst();
		        				continue;
		        				}
		        			i= seg.getChromEnd()-position0.getPosition();
		        			if(i<=0)
		        				{
		        				buffer.removeFirst();
		        				continue;
		        				}
		        			found=true;
		        			break;
		        			}
						
		        		
		        		if(!found)
		        			{
		        			while(iterbed.hasNext())
		        				{
		        				Segment seg=toZeroBased(bedsorter.make(iterbed.next()));
		        				int i= seg.getChromosome().compareTo(position0.getChromosome());
		        				if(i>0)
		        					{
		        					buffer.add(seg);
		        					break;
		        					}
		        				if(i<0) continue;
		        				i= seg.getChromStart() - position0.getPosition();
		        				if(i>=0)
		        					{
		        					found=(i==0);
		        					buffer.add(seg);
		        					break;
		        					}
		        				i= seg.getChromEnd() - position0.getPosition();
		        				if(i<=0) continue;
		        				buffer.add(seg);
		        				found=true;
		        				break;
		        				}
		        			}
		        		
		        		
		        		if(found)
							{
		        			container1.addRowToTable(row);
							}
						else
							{
							container2.addRowToTable(row);
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
					safeClose(itervcf);
					safeClose(iterbed);
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
			if(container1!=null) container1.close();
			if(container2!=null) container2.close();
			}
       }
    
    @Override
    protected DataTableSpec[] configure(DataTableSpec[] inSpecs)
    		throws InvalidSettingsException {
    	if(inSpecs==null || inSpecs.length!=2)
    		{
    		throw new InvalidSettingsException("Expected two tables");
    		}
    	DataTableSpec in=inSpecs[0];
    	findColumnIndex(in, "CHROM",StringCell.TYPE);
		findColumnIndex(in, "POS",IntCell.TYPE);
    	
    	return new DataTableSpec[]{in,in};
    	}
    
    @Override
    protected List<SettingsModel> getSettingsModel() {
    	List<SettingsModel> L=new ArrayList<SettingsModel>( super.getSettingsModel());
    	L.add(this.m_chromCol);
    	L.add(this.m_start);
    	L.add(this.m_end);
    	L.add(this.m_isZeroBased);
    	return L;
    	}
	}


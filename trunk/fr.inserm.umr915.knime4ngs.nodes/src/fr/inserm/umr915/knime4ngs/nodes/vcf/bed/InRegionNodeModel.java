package fr.inserm.umr915.knime4ngs.nodes.vcf.bed;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


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
	final static String DEFAULT_CHROM1_COL="CHROM";
	static final String CHROM1_COL_PROPERTY="chrom1.col";
	final static String DEFAULT_POS_COL="POS";
	static final String POS_COL_PROPERTY="pos.col";
	final static String DEFAULT_CHROM2_COL="chrom";
	static final String CHROM2_COL_PROPERTY="chrom2.col";
	final static String DEFAULT_START_COL="start";
	static final String START_COL_PROPERTY="start.col";
	static final String END_COL_PROPERTY="end.col";
	final static String DEFAULT_END_COL="end";
	

	
	private final SettingsModelColumnName m_chrom1Col =
        new SettingsModelColumnName(CHROM1_COL_PROPERTY,DEFAULT_CHROM1_COL);
	private final SettingsModelColumnName m_posCol =
        new SettingsModelColumnName(POS_COL_PROPERTY,DEFAULT_POS_COL);
	private final SettingsModelColumnName m_chrom2Col =
        new SettingsModelColumnName(CHROM2_COL_PROPERTY,DEFAULT_CHROM2_COL);
	private final SettingsModelColumnName m_start =
        new SettingsModelColumnName(START_COL_PROPERTY,DEFAULT_START_COL);
	private final SettingsModelColumnName m_end =
        new SettingsModelColumnName(END_COL_PROPERTY,DEFAULT_END_COL);
	
	
	
    /**
     * Constructor for the node model.
     */
    protected InRegionNodeModel()
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
				BufferedDataTable vcfTable=inData[0];
				BufferedDataTable bedTable=inData[1];
		       
				DataTableSpec inDataTableSpec1 = vcfTable.getDataTableSpec();
				DataTableSpec inDataTableSpec2 = bedTable.getDataTableSpec();

				
				BedKSorter bedsorter=new BedKSorter(
						inDataTableSpec2.findColumnIndex(this.m_chrom2Col.getColumnName()),
						inDataTableSpec2.findColumnIndex(this.m_start.getColumnName()),
						inDataTableSpec2.findColumnIndex(this.m_end.getColumnName())
						);
				
			
				
				PositionKSorter vcfSorter=new PositionKSorter(
					findColumnIndex(inDataTableSpec1,this.m_chrom1Col.getColumnName(),StringCell.TYPE),
					findColumnIndex(inDataTableSpec1,this.m_posCol.getColumnName(),IntCell.TYPE)
					);
				
				
				
		        container1 = exec.createDataContainer(new DataTableSpec(
		        		inDataTableSpec1,
						bedTable.getDataTableSpec()
						));

		        
		       
		        double total=vcfTable.getRowCount();
		        int nRow=0;
		        int outCount=0;
		        RowIterator itervcf=null;
		        RowIterator iterbed=null;
		        Position prevVCF=null;
		        Segment prevBed=null;
		        LinkedList<DataRow> buffer=new LinkedList<DataRow>();
		        try {
		        	itervcf=vcfTable.iterator();
		        	iterbed=bedTable.iterator();
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
		        		List<DataRow> found=new ArrayList<DataRow>();
		        		
		        		
		        		boolean willCallNext=true;
		        		int buffIndex=0;
		        		while(buffIndex< buffer.size())
		        			{
		        			DataRow r=buffer.get(buffIndex);
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
		        				//buffer.remove(buffIndex); non
		        				continue;
		        				}
		        			found.add(r);
		        			buffIndex++;
		        			}
						
		        		if(willCallNext)
			        		{
		        			while(iterbed.hasNext())
		        				{
		        				DataRow bedRow=iterbed.next();
		        				Segment seg=bedsorter.make(bedRow);
		        				if(seg==null) continue;
		        				if(prevBed!=null && prevBed.compareTo(seg)>0)
				        			{
				        			throw new IOException(
				        				"Expected a sorted segment file  but found "+prevBed+" > "+seg	
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
			        			
			        		}
		        		
		        		if(!found.isEmpty())
							{
		        			for(DataRow r2:found)
			        			{
			        			container1.addRowToTable(new AppendedColumnRow(
			        				RowKey.createRowKey(++outCount),	
			        				row,
			        				r2
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
					safeClose(itervcf);
					safeClose(iterbed);
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
    	DataTableSpec in=inSpecs[0];
    	findColumnIndex(in,this.m_chrom1Col,StringCell.TYPE);
		findColumnIndex(in, this.m_posCol,IntCell.TYPE);
		
		findColumnIndex(inSpecs[1],this.m_chrom2Col,StringCell.TYPE);
		findColumnIndex(inSpecs[1], this.m_start,IntCell.TYPE);
		findColumnIndex(inSpecs[1], this.m_end,IntCell.TYPE);
    	
    	return new DataTableSpec[]{new DataTableSpec(in,inSpecs[1]) };
    	}
    
    @Override
    protected List<SettingsModel> getSettingsModel() {
    	List<SettingsModel> L=new ArrayList<SettingsModel>( super.getSettingsModel());
    	L.add(this.m_chrom1Col);
    	L.add(this.m_posCol);
    	L.add(this.m_chrom2Col);
    	L.add(this.m_start);
    	L.add(this.m_end);
    	return L;
    	}
	}


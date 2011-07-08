package fr.inserm.umr915.knime4ngs.nodes.vcf.aggregation;

import java.util.ArrayList;
import java.util.List;

import org.knime.core.data.DataCell;
import org.knime.core.data.DataColumnSpec;
import org.knime.core.data.DataColumnSpecCreator;
import org.knime.core.data.DataRow;
import org.knime.core.data.DataTableSpec;
import org.knime.core.data.RowKey;
import org.knime.core.data.container.CloseableRowIterator;
import org.knime.core.data.def.BooleanCell;
import org.knime.core.data.def.DefaultRow;
import org.knime.core.data.def.DoubleCell;
import org.knime.core.data.def.IntCell;
import org.knime.core.data.def.LongCell;
import org.knime.core.data.def.StringCell;
import org.knime.core.node.BufferedDataContainer;
import org.knime.core.node.BufferedDataTable;
import org.knime.core.node.ExecutionContext;
import org.knime.core.node.InvalidSettingsException;
import org.knime.core.node.defaultnodesettings.SettingsModel;
import org.knime.core.node.defaultnodesettings.SettingsModelColumnName;
import org.knime.core.node.defaultnodesettings.SettingsModelDouble;
import org.knime.core.node.defaultnodesettings.SettingsModelString;

import fr.inserm.umr915.knime4ngs.corelib.bio.Position;
import fr.inserm.umr915.knime4ngs.corelib.bio.PositionKSorter;
import fr.inserm.umr915.knime4ngs.corelib.knime.ExecuteException;
import fr.inserm.umr915.knime4ngs.nodes.vcf.AbstractVCFNodeModel;



/**

 * @author Pierre Lindenbaum
 */
public class AggregationNodeModel extends AbstractVCFNodeModel
	{
	
	/** chrom column */
	static final String CHROM_COL_PROPERTY="chrom.col";
	static final String CHROM_COL_DEFAULT="CHROM";
	private final SettingsModelColumnName m_chromColumn = new SettingsModelColumnName(
			CHROM_COL_PROPERTY,
			CHROM_COL_DEFAULT
			);
	
	/** pos column */
	static final String POS_COL_PROPERTY="pos.col";
	static final String POS_COL_DEFAULT="POS";
	private final SettingsModelColumnName m_posColumn = new SettingsModelColumnName(
			POS_COL_PROPERTY,
			POS_COL_DEFAULT
			);
	
	
	/** val column */
	static final String VALUE_COL_PROPERTY="numeric.col";
	static final String VALUE_COL_DEFAULT="value";
	private final SettingsModelColumnName m_valueColumn = new SettingsModelColumnName(
			VALUE_COL_PROPERTY,
			VALUE_COL_DEFAULT
			);
	
	/** prefix column */
	static final String PREFIX_PROPERTY="prefix.property";
	static final String PREFIX_DEFAULT="window";
	private final SettingsModelString m_prefix = new SettingsModelString(
			PREFIX_PROPERTY,
			PREFIX_DEFAULT
			);
	
	/** THRESHOLD column */
	static final String THRESHOLD_PROPERTY="THRESHOLD";
	static final double THRESHOLD_DEFAULT=0.0;
	private final SettingsModelDouble m_treshold = new SettingsModelDouble(
			THRESHOLD_PROPERTY,
			THRESHOLD_DEFAULT
			);
	
	private static class Range
		{
		String chrom=null;
		int count=0;
		int chromStart=0;
		int chromEnd=0;
		Double max=null;
		Double min=null;
		double total=0;
		boolean under;
		}
	
    /**
     * Constructor for the node model.
     */
    protected AggregationNodeModel()
    	{
        super(1,1);
    	}
   
    
    private DataTableSpec createDataSpec()
    	{
    	String prefix=this.m_prefix.getStringValue()+".";
    	return new DataTableSpec(
    		new DataColumnSpec[]{	
    			new DataColumnSpecCreator(prefix+"chrom", StringCell.TYPE).createSpec(),
    			new DataColumnSpecCreator(prefix+"chromStart", IntCell.TYPE).createSpec(),
    			new DataColumnSpecCreator(prefix+"chromEnd", IntCell.TYPE).createSpec(),
    			new DataColumnSpecCreator(prefix+"InOut", BooleanCell.TYPE).createSpec(),
    			new DataColumnSpecCreator(prefix+"Count", IntCell.TYPE).createSpec(),
    			new DataColumnSpecCreator(prefix+"Min", DoubleCell.TYPE).createSpec(),
    			new DataColumnSpecCreator(prefix+"Max", DoubleCell.TYPE).createSpec(),
    			new DataColumnSpecCreator(prefix+"Mean", DoubleCell.TYPE).createSpec()
    			}
    		);
    	}
   private int execute(int outIndex,List<Range> ranges,BufferedDataContainer container)
   throws Exception
   	 {
	 for(int i=0;i<ranges.size();++i)
	 	{
		Range prev=(i>0?ranges.get(i-1):null);
		Range mid=ranges.get(i);
		Range next=(i+1< ranges.size()?ranges.get(i+1):null);
		
		DataCell cells[]=new DataCell[]
              {
			  new StringCell(mid.chrom),
			  new IntCell(prev==null?mid.chromStart:prev.chromEnd+1),
			  new IntCell(next==null?mid.chromEnd:next.chromStart-1),
			  (mid.under?BooleanCell.FALSE:BooleanCell.TRUE),
			  new IntCell(mid.count),
			  new DoubleCell(mid.min),
			  new DoubleCell(mid.max),
			  new DoubleCell(mid.total/mid.count)
              };
		container.addRowToTable(new DefaultRow(RowKey.createRowKey(++outIndex),cells));
	 	}
	 return outIndex;
   	 }
    
    @Override
    protected BufferedDataTable[] execute(
    		final BufferedDataTable[] inData,
            final ExecutionContext exec
            ) throws Exception
            {
			BufferedDataContainer container1=null;
			BufferedDataTable inTable=inData[0];
			int chromCol=findColumnIndex(inTable.getDataTableSpec(),m_chromColumn,StringCell.TYPE);
	    	int posCol=findColumnIndex(inTable.getDataTableSpec(),m_posColumn,IntCell.TYPE);
			int valCol=findColumnIndex(inTable.getDataTableSpec(), m_valueColumn.getColumnName());
			PositionKSorter ksorter=new PositionKSorter(chromCol, posCol);
			
			try
		    	{
		        container1 = exec.createDataContainer(createDataSpec());
		       
		        Position prev=null;
		        int outIndex=0;
		        double total=inTable.getRowCount();
		        int nRow=0;
		        CloseableRowIterator iter=null;
		        List<Range> ranges=new ArrayList<AggregationNodeModel.Range>();
		        try {
		        	iter=inTable.iterator();
		        	while(iter.hasNext())
		        		{
		        		++nRow;
		        		DataRow row=iter.next();
		        		Position pos=ksorter.make(row);
		        		if(pos==null) continue;
		        		DataCell valCell= row.getCell(valCol);
		        		if(valCell.isMissing()) continue;
		        		if(prev!=null && !(prev.compareTo(pos)<=0))
		        			{
		        			throw new ExecuteException("Expected sorted data but found  "+pos+" after "+prev);
		        			}
		        		
		        		if(prev!=null && !prev.getChromosome().equals(pos.getChromosome()))
		        			{
		        			outIndex = execute(outIndex,ranges,container1);
		        			ranges.clear();
		        			}
		        		prev=pos;
		        		
		        		double currVal=0;
		        		if(valCell.getType().equals(LongCell.TYPE))
		        			{
		        			currVal= LongCell.class.cast(valCell).getLongValue();
		        			}
		        		else if(valCell.getType().equals(IntCell.TYPE))
		        			{
		        			currVal= IntCell.class.cast(valCell).getIntValue();
		        			}
		        		else if(valCell.getType().equals(BooleanCell.TYPE))
		        			{
		        			currVal= (BooleanCell.class.cast(valCell).getBooleanValue()?1:0);
		        			}
		        		else if(valCell.getType().equals(DoubleCell.TYPE))
		        			{
		        			currVal= DoubleCell.class.cast(valCell).getDoubleValue();
		        			}
		        		else
		        			{
		        			throw new ExecuteException("Cannot handle value.type "+valCell.getType().getClass().getName());
		        			}
		        		
		        		if(ranges.isEmpty() || ranges.get(ranges.size()-1).under!=(currVal< this.m_treshold.getDoubleValue()))
		        			{
		        			Range currentRange=new Range();
		        			currentRange.chrom=pos.getChromosome();
		        			currentRange.chromStart=pos.getPosition();
		        			currentRange.chromEnd=pos.getPosition();
		        			currentRange.max=currVal;
		        			currentRange.min=currVal;
		        			currentRange.total=currVal;
		        			currentRange.count=1;
		        			currentRange.under=currVal< this.m_treshold.getDoubleValue();
		        			ranges.add(currentRange);
		        			}
		        		//extend range
		        		else
		        			{
		        			Range currentRange=ranges.get(ranges.size()-1);
		        			currentRange.chromStart=Math.min(pos.getPosition(),currentRange.chromStart);
		        			currentRange.chromEnd=Math.max(pos.getPosition(),currentRange.chromEnd);
		        			currentRange.max = Math.max(currVal,currentRange.max);
		        			currentRange.min = Math.min(currVal,currentRange.min);
		        			currentRange.total+=currVal;
		        			currentRange.count++;
		        			}
		        		
		        		exec.checkCanceled();
		            	exec.setProgress(nRow/total,"Aggregate Region...");
		        		}
		        	if(!ranges.isEmpty())
		        		{
		        		outIndex=execute(outIndex,ranges,container1);
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
    	findColumnIndex(in,m_chromColumn,StringCell.TYPE);
    	findColumnIndex(in,m_posColumn,IntCell.TYPE);
    	findColumnIndex(in, m_valueColumn.getColumnName());
    	return new DataTableSpec[]{createDataSpec()};
    	}
    
    @Override
    protected List<SettingsModel> getSettingsModel() {
    	List<SettingsModel> L=new ArrayList<SettingsModel>( super.getSettingsModel());
    	L.add(this.m_chromColumn);
    	L.add(this.m_posColumn);
    	L.add(this.m_prefix);
    	L.add(this.m_treshold);
    	L.add(this.m_valueColumn);
    	return L;
    	}
    
    
    
	}


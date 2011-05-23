package fr.inserm.umr915.knime4ngs.nodes.vcf.joinpos;

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
import fr.inserm.umr915.knime4ngs.nodes.vcf.AbstractVCFNodeModel;





/**
 * 
 */
public class JoinPosNodeModel extends AbstractVCFNodeModel
	{
	
	final static String DEFAULT_CHROM1_COL="CHROM";
	static final String CHROM1_COL_PROPERTY="chrom1.col";
	private final SettingsModelColumnName m_chrom1Col =
        new SettingsModelColumnName(CHROM1_COL_PROPERTY,DEFAULT_CHROM1_COL);

	final static String DEFAULT_POS1_COL="POS";
	static final String POS1_COL_PROPERTY="pos1.col";
	private final SettingsModelColumnName m_pos1Col =
        new SettingsModelColumnName(POS1_COL_PROPERTY,DEFAULT_POS1_COL);
	
	
	final static String DEFAULT_CHROM2_COL="CHROM";
	static final String CHROM2_COL_PROPERTY="chrom2.col";
	private final SettingsModelColumnName m_chrom2Col =
        new SettingsModelColumnName(CHROM2_COL_PROPERTY,DEFAULT_CHROM2_COL);

	final static String DEFAULT_POS2_COL="POS";
	static final String POS2_COL_PROPERTY="pos2.col";
	private final SettingsModelColumnName m_pos2Col =
        new SettingsModelColumnName(POS2_COL_PROPERTY,DEFAULT_POS2_COL);
	
	
	private static class Position
	implements Comparable<Position>
		{
		String chrom;
		int position;
		Position(String chrom,int position)
			{
			this.chrom=chrom;
			this.position=position;
			}
			
		public String getChromosome()
	 		{
	 		return chrom;
	 		}	
	 	public int getPosition()
	 		{
	 		return position;
	 		}
			
		@Override
		public int compareTo(Position pos)
			{
			int i=chrom.compareTo(pos.chrom);
			if(i!=0) return i;
			i= position-pos.position;
			return i;
			}
		@Override
		public String toString() {
			return getChromosome()+":"+getPosition();
			}
		}

	
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
			return new Position(
				StringCell.class.cast(r.getCell(chromCol)).getStringValue(),
				IntCell.class.cast(r.getCell(posCol)).getIntValue()
				);
			}
		@Override
		public int compare(DataRow r1,DataRow r2)
			{
			return make(r1).compareTo(make(r2));
			}
		}

	
	
    /**
     * Constructor for the node model.
     */
    protected JoinPosNodeModel()
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
				BufferedDataTable table1=inData[0];
				BufferedDataTable bedTable=inData[1];
		       
				DataTableSpec inDataTableSpec1 = table1.getDataTableSpec();
				DataTableSpec inDataTableSpec2 = bedTable.getDataTableSpec();

				
				PositionKSorter sorter1=new PositionKSorter(
						inDataTableSpec1.findColumnIndex(this.m_chrom1Col.getColumnName()),
						inDataTableSpec1.findColumnIndex(this.m_pos1Col.getColumnName())
					);
				
				PositionKSorter sorter2=new PositionKSorter(
						inDataTableSpec2.findColumnIndex(this.m_chrom2Col.getColumnName()),
						inDataTableSpec2.findColumnIndex(this.m_pos2Col.getColumnName())
					);
				
				DataTableSpec merged=new DataTableSpec(inDataTableSpec1,inDataTableSpec2);
		        container1 = exec.createDataContainer(merged);
		        
		       
		        double total=bedTable.getRowCount();
		        int nRow=0;
		        RowIterator iter1=null;
		        RowIterator iter2=null;
		        int outIndex=0;
		        Position prev1=null;
		        Position prev2=null;
		        LinkedList<DataRow> buffer=new LinkedList<DataRow>();
		        try {
		        	iter1=table1.iterator();
		        	iter2=bedTable.iterator();
		        	while(iter1.hasNext())
		        		{
		        		++nRow;
		        		DataRow row=iter1.next();
		        		Position position1= sorter1.make(row);
		        		if(prev1!=null && prev1.compareTo(position1)>0)
		        			{
		        			throw new IOException("Input should be sorted on CHROM/POS but found "+
		        				prev1+" > "+position1);
		        			}
		        		prev1=position1;
		        		DataRow found=null;
		        		
		        		while(!buffer.isEmpty())
		        			{
		        			DataRow row2=buffer.getFirst();
		        			Position position2 = sorter2.make(row2);
		        			int i= position2.compareTo(position1);
		        			if(i>0) break;
		        			if(i<0)
		        				{
		        				buffer.removeFirst();
		        				continue;
		        				}
		        			outIndex++;
		        			found=new AppendedColumnRow(
		        				RowKey.createRowKey(outIndex),	
		        				row, row2);
		        			break;
		        			}
						
		        		
		        		if(found==null)
		        			{
		        			while(iter2.hasNext())
		        				{
		        				DataRow row2=iter2.next();
		        				Position position2=sorter2.make(row2);
		        				if(prev2!=null && prev2.compareTo(position2)>0)
				        			{
				        			throw new IOException("Input should be sorted on CHROM/POS but found "+
				        				prev2+" > "+position2);
				        			}
		        				prev2=position2;
		        				
		        				
		        				int i= position2.compareTo(position1);
		        				if(i>0)
		        					{
		        					buffer.add(row2);
		        					break;
		        					}
		        				if(i<0) continue;
		        				outIndex++;
			        			found=new AppendedColumnRow(
			        				RowKey.createRowKey(outIndex),	
			        				row, row2);
			        			break;
		        				}
		        			}
		        		
		        		if(found!=null)
							{
		        			container1.addRowToTable(found);
							}
						else
							{
							DataCell empty[]=new DataCell[inDataTableSpec2.getNumColumns()];
		        			for(int i=0;i< empty.length;++i)
		        				{
		        				empty[i]=DataType.getMissingCell();
		        				}
		        			outIndex++;
		        			container1.addRowToTable(new AppendedColumnRow(
			        				RowKey.createRowKey(outIndex),	
			        				row, empty));
							
							}
		        		exec.checkCanceled();
		            	exec.setProgress(nRow/total,"Joining....");
		        		}
		        	
					} 
		        catch (Exception e)
					{
		        	e.printStackTrace();
					throw e;
					}
				finally
					{
					safeClose(iter1);
					safeClose(iter2);
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
    	if(inSpecs==null || inSpecs.length!=2)
    		{
    		throw new InvalidSettingsException("Expected two tables");
    		}
    	DataTableSpec in=inSpecs[0];
    	return new DataTableSpec[]{new DataTableSpec(in,inSpecs[1])};
    	}
    
    @Override
    protected List<SettingsModel> getSettingsModel() {
    	List<SettingsModel> L=new ArrayList<SettingsModel>( super.getSettingsModel());
    	L.add(this.m_chrom1Col);
    	L.add(this.m_pos1Col);
    	L.add(this.m_chrom2Col);
    	L.add(this.m_pos2Col);
    	return L;
    	}
	}


package fr.inserm.umr915.knime4ngs.nodes.vcf.predictions;

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

import fr.inserm.umr915.knime4ngs.corelib.bio.Mutation;
import fr.inserm.umr915.knime4ngs.corelib.bio.MutationKSorter;






/**
 * 
 */
public abstract class AbstractPredictionInNodeModel extends AbstractPredictionNodeModel
	{
	public final static String DEFAULT_PREDICTION_COL="prediction";
	public static final String PREDICTION_COL_PROPERTY="prediction.col";
	protected final SettingsModelColumnName m_predictionCol =
        new SettingsModelColumnName(PREDICTION_COL_PROPERTY,DEFAULT_PREDICTION_COL);
	
	
	
    /**
     * Constructor for the node model.
     */
    protected AbstractPredictionInNodeModel()
    	{
        super(2,1);
    	}
    
    protected abstract Mutation makeMutationPrediction(DataCell cell);
    
    private int compare(Mutation position2,Mutation position1)
    	{
    	return position2.getPosition().compareTo(position1.getPosition());
    	}
    
    private boolean same(Mutation position2,Mutation position1)
    	{
    	if(compare(position2,position1)!=0) return false;
    	return	position2.getRef().equalsIgnoreCase(position1.getRef()) &&
    			position2.getAlt().equalsIgnoreCase(position1.getAlt());
    	
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
				BufferedDataTable table2=inData[1];
		       
				DataTableSpec inDataTableSpec1 = table1.getDataTableSpec();
				DataTableSpec inDataTableSpec2 = table2.getDataTableSpec();

				
				MutationKSorter sorter1=new MutationKSorter(
						inDataTableSpec1.findColumnIndex(this.m_chromCol.getColumnName()),
						inDataTableSpec1.findColumnIndex(this.m_posCol.getColumnName()),
						inDataTableSpec1.findColumnIndex(this.m_refCol.getColumnName()),
						inDataTableSpec1.findColumnIndex(this.m_altCol.getColumnName())
					);
				int predCol=findColumnIndex(inDataTableSpec2, this.m_predictionCol, IntCell.TYPE);
				
				
				DataTableSpec merged=new DataTableSpec(inDataTableSpec1,inDataTableSpec2);
		        container1 = exec.createDataContainer(merged);
		        
		        
		       
		        double total=table1.getRowCount();
		        int nRow=0;
		        RowIterator iter1=null;
		        RowIterator iter2=null;
		        int outIndex=0;
		        Mutation prev1=null;
		        Mutation prev2=null;
		        LinkedList<DataRow> buffer=new LinkedList<DataRow>();
		        try {
		        	iter1=table1.iterator();
		        	iter2=table2.iterator();
		        	while(iter1.hasNext())
		        		{
		        		++nRow;
		        		DataRow row=iter1.next();
		        		Mutation position0= sorter1.make(row);
		        		
		        		
		        		if(prev1!=null && prev1.compareTo(position0)>0)
		        			{
		        			throw new IOException(
		        				"Input should be sorted on CHROM/POS/REF/ALT but found "+
		        				prev1+" > "+position0);
		        			}
		        		prev1=position0;
		        		List<DataRow> found=new ArrayList<DataRow>();
		        		
		        		if(isATGC(position0.getRef()))
		        			{
		        			for(String alt: alts(position0.getRef(),position0.getAlt()))
			        			{
		        				Mutation position1=new Mutation(
		        						position0.getPosition(),
		        						position0.getRef(), alt);
		        				
				        		while(!buffer.isEmpty())
				        			{
				        			DataRow row2=buffer.getFirst();
				        			Mutation position2 = makeMutationPrediction(row2.getCell(predCol));
				        			int i= compare(position2,position1);
				        			if(i>0) break;
				        			if(i<0)
				        				{
				        				buffer.removeFirst();
				        				continue;
				        				}
				        			if(!same(position2,position1)) continue;
				        			outIndex++;
				        			found.add(new AppendedColumnRow(
				        				RowKey.createRowKey(outIndex),	
				        				row, row2));
				        			break;
				        			}
								
				        		
				        		if(found.isEmpty())
				        			{
				        			while(iter2.hasNext())
				        				{
				        				DataRow row2=iter2.next();
				        				Mutation position2=makeMutationPrediction(row2.getCell(predCol));
				        				if(prev2!=null && compare(prev2,position2)>0)
						        			{
						        			throw new IOException("Input should be sorted on CHROM/POS/REF/ALT but found "+
						        				prev2+" > "+position2);
						        			}
				        				prev2=position2;
				        				
				        				
				        				int i=compare(position2,position1);
				        				if(i>0)
				        					{
				        					buffer.add(row2);
				        					break;
				        					}
				        				if(i<0) continue;
				        				if(!same(position2,position1)) continue;
				        				
				        				outIndex++;
					        			found.add(new AppendedColumnRow(
					        				RowKey.createRowKey(outIndex),	
					        				row, row2));
					        			break;
				        				}
				        			}
			        			}//end of loop(alts())
		        			}//end if atgc(ref)
		        		
		        		if(found.isEmpty())
		        			{
		        			DataCell empty[]=new DataCell[inDataTableSpec2.getNumColumns()];
		        			for(int i=0;i< empty.length;++i)
		        				{
		        				empty[i]=DataType.getMissingCell();
		        				}
		        			outIndex++;
		        			found.add(new AppendedColumnRow(
		        				RowKey.createRowKey(outIndex),	
		        				row, empty));
		        			}
		        		
		        		for(DataRow r:found)
			        		{
			        		container1.addRowToTable(r);
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
    	 findColumnIndex(inSpecs[0],m_chromCol,StringCell.TYPE);
         findColumnIndex(inSpecs[0],m_posCol,IntCell.TYPE);
         findColumnIndex(inSpecs[0],m_refCol,StringCell.TYPE);
         findColumnIndex(inSpecs[0],m_altCol,StringCell.TYPE);
         findColumnIndex(inSpecs[1],m_predictionCol,StringCell.TYPE);
    	return super.configure(inSpecs);
    	}
    
    @Override
    protected List<SettingsModel> getSettingsModel() {
    	List<SettingsModel> L=new ArrayList<SettingsModel>( super.getSettingsModel());
    	L.add(this.m_predictionCol);
    	return L;
    	}
	}


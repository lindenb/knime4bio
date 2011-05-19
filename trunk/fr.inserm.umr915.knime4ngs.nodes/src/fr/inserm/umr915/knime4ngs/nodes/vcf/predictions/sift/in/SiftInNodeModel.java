package fr.inserm.umr915.knime4ngs.nodes.vcf.predictions.sift.in;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import org.knime.base.data.append.column.AppendedColumnRow;
import org.knime.base.data.sort.SortedTable;
import org.knime.core.data.DataRow;
import org.knime.core.data.DataTableSpec;
import org.knime.core.data.DataType;
import org.knime.core.data.RowIterator;
import org.knime.core.data.container.CloseableRowIterator;
import org.knime.core.data.def.StringCell;
import org.knime.core.node.BufferedDataContainer;
import org.knime.core.node.BufferedDataTable;
import org.knime.core.node.ExecutionContext;
import org.knime.core.node.InvalidSettingsException;
import org.knime.core.node.defaultnodesettings.SettingsModel;
import org.knime.core.node.defaultnodesettings.SettingsModelStringArray;

import fr.inserm.umr915.knime4ngs.corelib.bio.Mutation;
import fr.inserm.umr915.knime4ngs.corelib.bio.Position;
import fr.inserm.umr915.knime4ngs.nodes.vcf.AbstractVCFNodeModel;



/**
 * This is the model implementation of VCFSource.
 * Reads a VCF file
 *
 * @author Pierre Lindenbaum
 */
public class SiftInNodeModel extends AbstractVCFNodeModel
	{
	private Pattern comma=Pattern.compile("[,]");
	static String PREDICTIONS_PROPERTY="prediction.list";
	static final String PREDICTIONS[]=new String[]{
		"DAMAGING",
		"DAMAGING *Warning! Low confidence.",
		"N/A",
		"Not scored",
		"TOLERATED"
		};
	static final String DEFAULT_PREDICTIONS[]=new String[]{
		PREDICTIONS[0],PREDICTIONS[1]
		};
	SettingsModelStringArray m_predictions=new SettingsModelStringArray(PREDICTIONS_PROPERTY,PREDICTIONS);
	
	
	private class RowComparator implements Comparator<DataRow>
		{
		int coordinateCol;
		
		@Override
		public int compare(DataRow o1, DataRow o2)
			{
			Mutation m1= parseSiftMutation(StringCell.class.cast(o1.getCell(this.coordinateCol)).getStringValue());
			Mutation m2= parseSiftMutation(StringCell.class.cast(o2.getCell(this.coordinateCol)).getStringValue());
			return m1.compareTo(m2);
			}
		}
	
    /**
     * Constructor for the node model.
     */
    protected SiftInNodeModel()
    	{
        super(2,2);
    	}
   
    private Mutation parseSiftMutation(String cell)
		{
		String variant[]=comma.split(cell);
		if(!variant[0].startsWith("chr")) variant[0]="chr"+variant[0];
		if(variant[3].length()!=3 || variant[3].charAt(1)!='/')
			{
			throw new IllegalArgumentException(cell);
			}
		Position pos= new Position(variant[0],Integer.parseInt(variant[1]));
		Mutation mutation=new Mutation(
				pos,
				variant[3].substring(0,1).toUpperCase(),
				variant[3].substring(2).toUpperCase()
				);
		return mutation;
		}
    
    @Override
    protected BufferedDataTable[] execute(
    		final BufferedDataTable[] inData,
            final ExecutionContext exec
            ) throws Exception
            {
			BufferedDataContainer container1=null;
			BufferedDataContainer container2=null;
			RowComparator siftComparator=new RowComparator();
			siftComparator.coordinateCol = findColumnIndex(inData[1].getDataTableSpec(),"Coordinates", StringCell.TYPE);
			int predictionColumn = findColumnIndex(inData[1].getDataTableSpec(),"Prediction", StringCell.TYPE);
			
			
			MutationColumns mutationColumns=getMutationColumns(inData[0].getDataTableSpec());
			MutationComparator mutComparator= new MutationComparator(mutationColumns);
			try
		    	{
		        // the data table spec of the single output table, 
		        // the table will have three columns:
				BufferedDataTable inTable=inData[0];
				
				
				SortedTable sortedTable1=new SortedTable(inData[0], mutComparator, false, exec);
				SortedTable sortedTable2=new SortedTable(inData[1], siftComparator, false, exec);
				
				DataTableSpec inDataTableSpec = inTable.getDataTableSpec();
				
		        container1 = exec.createDataContainer(new DataTableSpec(inDataTableSpec, new DataTableSpec(new String[]{"SIFT"}, new DataType[]{StringCell.TYPE})));
		        container2 = exec.createDataContainer(inDataTableSpec);
		        
		        ArrayList<DataRow> dataRowBuffer=new  ArrayList<DataRow>();
		        Set<String> predictions=new HashSet<String>(Arrays.asList(this.m_predictions.getStringArrayValue()));
		        
		        
		        double total=inTable.getRowCount();
		        int nRow=0;
		        RowIterator iter1=null;
		        RowIterator iter2=null;
		        try {
		        	iter1=sortedTable1.iterator();
		        	iter2=sortedTable2.iterator();
		        	while(iter1.hasNext())
		        		{
		        		++nRow;
		        		DataRow row1=iter1.next();
		        		String prediction=null;
		        		Mutation mut1=getMutation(mutationColumns, row1);
		        		
	        			while(!dataRowBuffer.isEmpty())
	        				{
	        				Mutation mut2=parseSiftMutation(StringCell.class.cast(dataRowBuffer.get(0).getCell(siftComparator.coordinateCol)).getStringValue());
	        				if(mut2.compareTo(mut1)<0)
	        					{
	        					dataRowBuffer.remove(0);
	        					}
	        				else
	        					{
	        					break;
	        					}
	        				}
		        		
	        			if(dataRowBuffer.isEmpty() && iter2.hasNext())
	        				{
	        				dataRowBuffer.add(iter2.next());
	        				}
	        			
	        			if(!dataRowBuffer.isEmpty())
	        				{
	        				Mutation mut2=parseSiftMutation(StringCell.class.cast(dataRowBuffer.get(0).getCell(siftComparator.coordinateCol)).getStringValue());
	        				if(mut1.compareTo(mut2)==0)
	        					{
	        					prediction=StringCell.class.cast(dataRowBuffer.get(0).getCell(predictionColumn)).getStringValue();
	        					if(!predictions.contains(prediction))
	        						{
	        						prediction=null;
	        						}
	        					}
	        				}
		        		
		        		if(prediction!=null)
		        			{
		        			row1=new AppendedColumnRow(row1, new StringCell(prediction));
		        			container1.addRowToTable(row1);
		        			}
		        		else
		        			{
		        			container2.addRowToTable(row1);
		        			}
							
		        		}
		        	exec.checkCanceled();
	            	exec.setProgress(nRow/total,"Filtering....");
					} 
		        catch (Exception e)
					{
					throw e;
					}
				finally
					{
					if(iter1!=null && iter1 instanceof CloseableRowIterator) CloseableRowIterator.class.cast(iter1).close();
					if(iter2!=null && iter2 instanceof CloseableRowIterator) CloseableRowIterator.class.cast(iter2).close();
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
    		throw new InvalidSettingsException("Expected two table");
    		}
    	
    	DataTableSpec in=inSpecs[0];
    	DataTableSpec in2=new DataTableSpec(in, new DataTableSpec(new String[]{"SIFT"}, new DataType[]{StringCell.TYPE}));
    	return new DataTableSpec[]{in2,in};
    	}
    
    @Override
    protected List<SettingsModel> getSettingsModel() {
    	List<SettingsModel> L=new ArrayList<SettingsModel>( super.getSettingsModel());
    	L.add(this.m_predictions);
    	return L;
    	}
    
    
	}


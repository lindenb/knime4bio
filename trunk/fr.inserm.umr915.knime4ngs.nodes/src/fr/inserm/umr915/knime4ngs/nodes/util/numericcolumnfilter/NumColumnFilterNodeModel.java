package fr.inserm.umr915.knime4ngs.nodes.util.numericcolumnfilter;

import java.util.ArrayList;
import java.util.List;

import org.knime.core.data.DataCell;
import org.knime.core.data.DataRow;
import org.knime.core.data.DataTableSpec;
import org.knime.core.data.RowIterator;
import org.knime.core.data.def.BooleanCell;
import org.knime.core.data.def.DoubleCell;
import org.knime.core.data.def.IntCell;
import org.knime.core.data.def.LongCell;
import org.knime.core.node.BufferedDataContainer;
import org.knime.core.node.BufferedDataTable;
import org.knime.core.node.ExecutionContext;
import org.knime.core.node.InvalidSettingsException;
import org.knime.core.node.defaultnodesettings.SettingsModel;
import org.knime.core.node.defaultnodesettings.SettingsModelBoolean;
import org.knime.core.node.defaultnodesettings.SettingsModelDoubleRange;
import org.knime.core.node.defaultnodesettings.SettingsModelFilterString;
import fr.inserm.umr915.knime4ngs.corelib.knime.AbstractNodeModel;
import fr.inserm.umr915.knime4ngs.corelib.knime.ExecuteException;


/**
 * @author Pierre Lindenbaum
 */
public class NumColumnFilterNodeModel extends AbstractNodeModel
	{
	/** left column */
	static final String COLS_PROPERTY="left.col";
	static final String COLS_INCLUDE[]=new String[]{""};
	static final String COLS_EXCLUDE[]=new String[]{""};
	private final SettingsModelFilterString m_leftColumn = new SettingsModelFilterString(
			COLS_PROPERTY
			//LEFT_COLS_INCLUDE,
			//LEFT_COLS_EXCLUDE
			);
	
	/** duplicates  */
	static final String BOUND_PROPERTY="range";
	static final double  BOUND_MIN=0.0;
	static final double  BOUND_MAX=1.0;
	private final SettingsModelDoubleRange m_range = new SettingsModelDoubleRange(
			BOUND_PROPERTY,
			BOUND_MIN,
			BOUND_MAX
			);
	
	static final String INVERT_PROPERTY="invert";
	static final boolean INVERT_DEFAULT=false;
	private final SettingsModelBoolean m_invert = new SettingsModelBoolean(
			INVERT_PROPERTY,
			INVERT_DEFAULT
			);
	
	static final String ALL_COLUMN_MATCH_PROPERTY="all.columns.must.in.range";
	static final boolean ALL_COLUMN_MATCH_DEFAULT=true;
	private final SettingsModelBoolean m_allColumnMatch = new SettingsModelBoolean(
			ALL_COLUMN_MATCH_PROPERTY,
			ALL_COLUMN_MATCH_DEFAULT
			);
	
	
    /**
     * Constructor for the node model.
     */
    protected NumColumnFilterNodeModel()
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
		RowIterator iter=null;
		try
	    	{
			BufferedDataTable inTable=inData[0];
	        container1 = exec.createDataContainer(inData[0].getDataTableSpec());
	        int nRow=0;
	        float total=inTable.getRowCount();
	      	List<String> L= this.m_leftColumn.getIncludeList();
	      	int indexes[]=new int[L.size()];
	      	for(int i=0;i< indexes.length;++i)
	      		{
	      		indexes[i]= inTable.getDataTableSpec().findColumnIndex(L.get(i));
	      		if(indexes[i]==-1) throw new ExecuteException("Cannot find column "+L.get(i));
	      		}
	      	
	        try {
	        	iter=inTable.iterator();
		        while(iter.hasNext())
	        		{
	        		DataRow r=iter.next();
	        		++nRow;
	        		exec.checkCanceled();
	            	exec.setProgress(nRow/total,"Numeric column filter....");
	            	Double values[]=new Double[indexes.length];
	            	
	            	
	            	for(int i=0;i< indexes.length;++i)
		            	{
	            		DataCell c=r.getCell(indexes[i]);
	            		Double currVal=null;
	            		if(c.isMissing())
	            			{
	            			currVal=null;
	            			}
	            		else if(c.getType().equals(LongCell.TYPE))
		        			{
		        			currVal=(double) LongCell.class.cast(c).getLongValue();
		        			}
		        		else if(c.getType().equals(IntCell.TYPE))
		        			{
		        			currVal=(double) IntCell.class.cast(c).getIntValue();
		        			}
		        		else if(c.getType().equals(BooleanCell.TYPE))
		        			{
		        			currVal=(double) (BooleanCell.class.cast(c).getBooleanValue()?1:0);
		        			}
		        		else if(c.getType().equals(DoubleCell.TYPE))
		        			{
		        			currVal=(double) DoubleCell.class.cast(c).getDoubleValue();
		        			}
		        		else
		        			{
		        			throw new ExecuteException("type ???! "+c.getType());
		        			}
	            		values[i]=currVal;
		            	}
					
	            	if(m_allColumnMatch.getBooleanValue())
	            		{
	            		boolean all_in_range=true;
	            		for(int i=0;i< values.length;++i)
	            			{
	            			if(values[i]==null)
	            				{
	            				all_in_range=false;
	            				break;
	            				}
	            			if(!(m_range.getMinRange()<= values[i] && values[i]<= m_range.getMaxRange()))
	            				{
	            				all_in_range=false;
	            				break;
	            				}
	            			}
	            		if(all_in_range!=m_invert.getBooleanValue())
	            			{
	            			container1.addRowToTable(r);
	            			}
	            		}
	            	else
	            		{
	            		boolean one_in_range=false;
	            		for(int i=0;i< values.length;++i)
	            			{
	            			if(values[i]==null) continue;
	            			if(!(m_range.getMinRange()<= values[i] && values[i]<= m_range.getMaxRange()))
	            				{
	            				one_in_range=true;
	            				break;
	            				}
	            			}
	            		if(one_in_range!=m_invert.getBooleanValue())
	            			{
	            			container1.addRowToTable(r);
	            			}
	            		}
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
    		throws InvalidSettingsException
    	{    	
    	if(inSpecs==null || inSpecs.length!=1)
    		{
    		throw new InvalidSettingsException("expected one table");
    		}
    	List<String> L=this.m_leftColumn.getIncludeList();
    	if(L.isEmpty()) throw new InvalidSettingsException("No column selected");
    	for(String s:L)
    		{
    		if(inSpecs[0].findColumnIndex(s)==-1)
    			{
    			 throw new InvalidSettingsException("Cannot find column "+s);
    			}
    		}
    	return new DataTableSpec[]{inSpecs[0]};
    	}
    
    @Override
    protected List<SettingsModel> getSettingsModel() {
    	List<SettingsModel> L=new ArrayList<SettingsModel>( super.getSettingsModel());
    	L.add(this.m_leftColumn);
    	L.add(this.m_allColumnMatch);
    	L.add(this.m_invert);
    	L.add(this.m_range);
    	return L;
    	} 
    
	}


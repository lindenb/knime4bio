package fr.inserm.umr915.knime4ngs.nodes.unix.uniq;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.knime.core.data.DataCell;
import org.knime.core.data.DataRow;
import org.knime.core.data.DataTableSpec;
import org.knime.core.data.RowIterator;
import org.knime.core.node.BufferedDataContainer;
import org.knime.core.node.BufferedDataTable;
import org.knime.core.node.ExecutionContext;
import org.knime.core.node.InvalidSettingsException;
import org.knime.core.node.defaultnodesettings.SettingsModel;
import org.knime.core.node.defaultnodesettings.SettingsModelBoolean;
import org.knime.core.node.defaultnodesettings.SettingsModelFilterString;

import fr.inserm.umr915.knime4ngs.corelib.knime.AbstractNodeModel;
import fr.inserm.umr915.knime4ngs.corelib.knime.CellSorter;
import fr.inserm.umr915.knime4ngs.corelib.knime.ExecuteException;


/**
 * @author Pierre Lindenbaum
 */
public class UniqNodeModel extends AbstractNodeModel
	{
	/** left column */
	static final String LEFT_COLS_PROPERTY="left.col";
	static final String LEFT_COLS_INCLUDE[]=new String[]{""};
	static final String LEFT_COLS_EXCLUDE[]=new String[]{""};
	private final SettingsModelFilterString m_leftColumn = new SettingsModelFilterString(
			LEFT_COLS_PROPERTY
			//LEFT_COLS_INCLUDE,
			//LEFT_COLS_EXCLUDE
			);
	
	/** duplicates  */
	static final String DUPLICATE_PROPERTY="show.dups";
	static final boolean  DUPLICATE_DEFAULT=false;
	private final SettingsModelBoolean m_showDuplicates = new SettingsModelBoolean(
			DUPLICATE_PROPERTY,
			DUPLICATE_DEFAULT
			);
	
	static class Sorter implements Comparator<DataRow>
		{
		List<Integer> indexes=new ArrayList<Integer>();
		List<CellSorter> comparators=new ArrayList<CellSorter>();
		@Override
		public int compare(DataRow r1,DataRow r2)
			{
			for(int i=0;i<indexes.size();++i)
				{
				DataCell cell1 = r1.getCell(indexes.get(i));
				DataCell cell2 = r2.getCell(indexes.get(i));
				int d= comparators.get(i).compare(cell1,cell2);
				if(d!=0) return d;
				}
			return 0;
			}
		@Override
		public String toString() {
			return "Sorter: indexes:"+indexes+" "+comparators;
			}
		}
	
    /**
     * Constructor for the node model.
     */
    protected UniqNodeModel()
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
			
			Sorter sorter=new Sorter();
	        DataTableSpec inSpecs=inTable.getDataTableSpec();
		    for(String s:this.m_leftColumn.getIncludeList())
				{
				int j=inSpecs.findColumnIndex(s);
				if(j==-1) throw new IllegalStateException("Cannot find column "+s);
				sorter.indexes.add(j);
				sorter.comparators.add(CellSorter.getCellSorterByType( inSpecs.getColumnSpec(j).getType()));
				}
			
			if(sorter.indexes.isEmpty())
				{
				throw new ExecuteException("No columns defined");
				}
		    
	        container1 = exec.createDataContainer(inSpecs);
	        int nRow=0;
	        float total=inTable.getRowCount();
	      	DataRow prev=null;
	        try {
	        	iter=inTable.iterator();
	        	if(this.m_showDuplicates.getBooleanValue()==false)
		        	{
		        	while(iter.hasNext())
		        		{
		        		DataRow r=iter.next();
		        		++nRow;
		        		exec.checkCanceled();
		            	exec.setProgress(nRow/total,"Uniq....");
						if(prev==null)
							{
							prev=r;
							continue;
							}
						int diff=sorter.compare(prev,r);
		
						if(diff==0)
							{
							//continue
							}
						else if(diff>0)
							{
							throw new ExecuteException("Expected rows to be sorted with "+sorter+" but got\n"+prev +"\nand\nthen "+r);
							}
						else
							{
							container1.addRowToTable(prev);
							prev=r;
							}
		        		}
			       if(prev!=null)
						{
						container1.addRowToTable(prev);
						}
		        	}
	        	else
	        		{
	        		int count=0;
		        	while(iter.hasNext())
		        		{
		        		DataRow r=iter.next();
		        		++nRow;
		        		exec.checkCanceled();
		            	exec.setProgress(nRow/total,"Uniq....");
						if(prev==null)
							{
							prev=r;
							count=1;
							continue;
							}
						int diff=sorter.compare(prev,r);
		
						if(diff==0)
							{
							count++;
							//continue
							}
						else if(diff>0)
							{
							throw new ExecuteException("Expected rows to be sorted: but got "+prev +" and then "+r);
							}
						else
							{
							if(count>1)
								{
								container1.addRowToTable(prev);
								}
							prev=r;
							count=1;
							}
		        		}
			       if(prev!=null && count>1)
						{
						container1.addRowToTable(prev);
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
    	return new DataTableSpec[]{inSpecs[0]};
    	}
    
    @Override
    protected List<SettingsModel> getSettingsModel() {
    	List<SettingsModel> L=new ArrayList<SettingsModel>( super.getSettingsModel());
    	L.add(this.m_leftColumn);
    	L.add(this.m_showDuplicates);
    	return L;
    	} 
    
	}


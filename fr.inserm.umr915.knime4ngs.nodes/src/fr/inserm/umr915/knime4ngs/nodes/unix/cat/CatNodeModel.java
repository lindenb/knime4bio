package fr.inserm.umr915.knime4ngs.nodes.unix.cat;

import org.knime.core.data.DataCell;
import org.knime.core.data.DataColumnSpec;
import org.knime.core.data.DataRow;
import org.knime.core.data.DataTableSpec;
import org.knime.core.data.RowIterator;
import org.knime.core.data.RowKey;
import org.knime.core.data.def.DefaultRow;
import org.knime.core.node.BufferedDataContainer;
import org.knime.core.node.BufferedDataTable;
import org.knime.core.node.ExecutionContext;
import org.knime.core.node.InvalidSettingsException;

import fr.inserm.umr915.knime4ngs.corelib.knime.AbstractNodeModel;


/**
 * @author Pierre Lindenbaum
 */
public class CatNodeModel extends AbstractNodeModel
	{
    /**
     * Constructor for the node model.
     */
    protected CatNodeModel()
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
		RowIterator iter=null;
		try
	    	{
	        container1 = exec.createDataContainer(inData[0].getDataTableSpec());
	        float total=inData[0].getRowCount()+inData[1].getRowCount();
	        int nRow=0;
	        try {
	        	iter= inData[0].iterator();
	        	while(iter.hasNext())
	        		{
	        		container1.addRowToTable(new DefaultRow(RowKey.createRowKey(++nRow),iter.next()));
	        		exec.setProgress(nRow/total, "Cat");
	        		exec.checkCanceled();
	        		}
	        	safeClose(iter);
	        	
	        	int columns[]=new int[inData[0].getDataTableSpec().getNumColumns()];
	        	for(int i=0;i< columns.length;++i)
	        		{
	        		String colName=inData[0].getDataTableSpec().getColumnSpec(i).getName();
	        		columns[i]=inData[1].getDataTableSpec().findColumnIndex(colName);
	        		if(columns[i]==-1) throw new InvalidSettingsException("column "+colName+" : table2 missing in column table 1 ???");
	        		}
	        	iter= inData[1].iterator();
	        	while(iter.hasNext())
	        		{
	        		DataCell cells[]=new DataCell[columns.length];
	        		DataRow row=iter.next();
	        		for(int i=0;i<columns.length;++i)
	        			{
	        			cells[i]=row.getCell(columns[i]);
	        			}
	        		container1.addRowToTable(new DefaultRow(RowKey.createRowKey(++nRow),cells));
	        		exec.setProgress(nRow/total, "Cat");
	        		exec.checkCanceled();
	        		}
	        	safeClose(iter);
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
    	if(inSpecs==null || inSpecs.length!=2)
    		{
    		throw new InvalidSettingsException("expected two tables");
    		}
    	DataTableSpec spec1=inSpecs[0];
    	DataTableSpec spec2=inSpecs[1];
    	if(spec1.getNumColumns()!=spec2.getNumColumns())
    		{
    		throw new InvalidSettingsException("not the same number of columns");
    		}
    	for(int i=0;i< spec1.getNumColumns();++i)
    		{
    		DataColumnSpec col1=spec1.getColumnSpec(i);
    		int j=spec2.findColumnIndex(col1.getName());
    		if(j==-1) throw new InvalidSettingsException("Cannot find column "+col1.getName()+" in 2nd table");
    		DataColumnSpec col2=spec2.getColumnSpec(j);
    		if(!col1.getType().equals(col2.getType()))
    			{
    			throw new InvalidSettingsException("not the same type for columns "+col2.getName());
    			}	
    		}
    	return new DataTableSpec[]{inSpecs[0]};
    	}
    
    
    
	}


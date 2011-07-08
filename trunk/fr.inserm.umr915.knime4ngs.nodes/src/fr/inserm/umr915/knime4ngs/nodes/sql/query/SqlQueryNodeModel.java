package fr.inserm.umr915.knime4ngs.nodes.sql.query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.knime.base.data.append.column.AppendedColumnRow;
import org.knime.core.data.DataCell;
import org.knime.core.data.DataRow;
import org.knime.core.data.DataTableSpec;
import org.knime.core.data.RowKey;
import org.knime.core.data.container.CloseableRowIterator;
import org.knime.core.data.def.BooleanCell;
import org.knime.core.data.def.DoubleCell;
import org.knime.core.data.def.IntCell;
import org.knime.core.data.def.LongCell;
import org.knime.core.data.def.StringCell;
import org.knime.core.node.BufferedDataContainer;
import org.knime.core.node.BufferedDataTable;
import org.knime.core.node.ExecutionContext;
import org.knime.core.node.InvalidSettingsException;
import org.knime.core.node.defaultnodesettings.SettingsModel;
import org.knime.core.node.defaultnodesettings.SettingsModelString;
import fr.inserm.umr915.knime4ngs.nodes.sql.AbstractSqlNodeModel;








/**
 * @author Pierre Lindenbaum
 */
public class SqlQueryNodeModel extends AbstractSqlNodeModel
	{
	
	final static String DEFAULT_QUERY="select now()";
    final static String QUERY_PROPERTY="sql.query"; 
	private final SettingsModelString m_sqlQuery = new SettingsModelString(QUERY_PROPERTY,DEFAULT_QUERY);
	
	/** property for table join */
	public static final String JOIN_PROPERTY="join.method";
	
	public static final String JOIN_PROPERTIES[]=new String[]
		{
		"Node1: Print ALL matches, Node2: Print ALL unmatched rows",//0
		"Node1: Print ONLY ONE matche per ROW,  Node2: Print ALL unmatched rows",//1
		"Node1: Ignore ALL Matching rows, Node2: Print ALL unmatched rows",//2
		"Node1: Print ALL matches, Node2: Ignore ALL unmatched rows",//3
		"Node1: Print ONLY ONE matche per ROW, Node2: Ignore ALL unmatched rows"//4
		};
	
	
	/** default property for table join */
	public static final String DEFAULT_JOIN_PROPERTY=JOIN_PROPERTIES[1];
	/** settings for table uri */
	private final SettingsModelString m_join=new SettingsModelString(
			JOIN_PROPERTY,
			DEFAULT_JOIN_PROPERTY
			);	

	
    /**
     * Constructor for the node model.
     */
    protected SqlQueryNodeModel()
    	{
        super(1,2);
    	}
    
    @Override
    protected BufferedDataTable[] execute(final BufferedDataTable[] inData,
            final ExecutionContext exec) throws Exception
    	{
		BufferedDataContainer container1=null;
		BufferedDataContainer container2=null;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet result=null;
		try
	    	{
	        // the data table spec of the single output table, 
	        // the table will have three columns:
			//data table
			BufferedDataTable inTable=inData[0];
			int total=inTable.getRowCount();
			
			String joinChoice=m_join.getStringValue();
			boolean ignoreMatches=(joinChoice.equals(JOIN_PROPERTIES[2]));
			boolean ignoreDiscarded=(joinChoice.equals(JOIN_PROPERTIES[3]) || joinChoice.equals(JOIN_PROPERTIES[4]));
			boolean onePerMatch=(joinChoice.equals(JOIN_PROPERTIES[1]) || joinChoice.equals(JOIN_PROPERTIES[4]));

	       
	        
	      
	        
	        container2=exec.createDataContainer(inTable.getDataTableSpec());
	
	        int outRow=0;
	        int nRow=0;
	        CloseableRowIterator iter=null;
	        try {
	        	iter=inTable.iterator();
	        	String query= m_sqlQuery.getStringValue();
	        	
	        	QueryParser parser=new QueryParser(inTable.getDataTableSpec(),query);
	        	con=super.createConnection();
	        	
	        	
	        	
	        	pstmt=con.prepareStatement(parser.getQuery());
	        	
	        	/* first time , create a dummy query */
	        	for(QueryParser.Column col: parser.getColumns())
	        		{
	        		if(col.dataColumnSpec.getType().equals(StringCell.TYPE))
	    				{
	        			pstmt.setString(col.index0+1,String.valueOf(System.currentTimeMillis()));
	    				}
	        		else if(col.dataColumnSpec.getType().equals(IntCell.TYPE))
	    				{
	        			pstmt.setInt(col.index0+1,0);
	        			}
	        		else if(col.dataColumnSpec.getType().equals(LongCell.TYPE))
	    				{
	        			pstmt.setLong(col.index0+1,System.currentTimeMillis());
	    				}
	        		else if(col.dataColumnSpec.getType().equals(DoubleCell.TYPE))
	    				{
	        			pstmt.setDouble(col.index0+1,0f);
	    				}
	        		else if(col.dataColumnSpec.getType().equals(BooleanCell.TYPE))
	    				{
	        			pstmt.setBoolean(col.index0+1,false);
	    				}
	        		else
	        			{
	        			throw new IllegalArgumentException("data type not handled: "+col.dataColumnSpec.getType());
	        			}
	        		}
	        	pstmt.setMaxRows(1);
	        	result=pstmt.executeQuery();
	        	container1 = exec.createDataContainer(
	        			 new DataTableSpec(
	     	        	inTable.getDataTableSpec(),
	     	        	createTableSpec(pstmt.getMetaData())
	     	        	));
	        	result.close();
	        	
	        	 
	        	pstmt.setMaxRows(0);//no limit
	        	while(iter.hasNext())
	        		{
	        		
	        		++nRow;
	        		exec.checkCanceled();
	            	exec.setProgress(nRow/total,"SQL....");
	        		DataRow row=iter.next();
	        		
	        		for(QueryParser.Column col: parser.getColumns())
		        		{
		        		if(col.dataColumnSpec.getType().equals(StringCell.TYPE))
	        				{
		        			DataCell cell= row.getCell(col.dataColumnIndex);
		        			if(cell.isMissing())
		        				{
		        				pstmt.setNull(col.index0+1, java.sql.Types.VARCHAR);
		        				}
		        			else
		        				{
		        				pstmt.setString(col.index0+1,StringCell.class.cast(cell).getStringValue());
		        				}
	        				}
		        		else if(col.dataColumnSpec.getType().equals(IntCell.TYPE))
	        				{
		        			DataCell cell= row.getCell(col.dataColumnIndex);
		        			if(cell.isMissing())
		        				{
		        				pstmt.setNull(col.index0+1, java.sql.Types.INTEGER);
		        				}
		        			else
		        				{
		        				pstmt.setInt(col.index0+1,IntCell.class.cast(cell).getIntValue());
		        				}
	        				}
		        		else if(col.dataColumnSpec.getType().equals(LongCell.TYPE))
	        				{
		        			DataCell cell= row.getCell(col.dataColumnIndex);
		        			if(cell.isMissing())
		        				{
		        				pstmt.setNull(col.index0+1, java.sql.Types.BIGINT);
		        				}
		        			else
		        				{
		        				pstmt.setLong(col.index0+1,IntCell.class.cast(cell).getLongValue());
		        				}
	        				}
		        		else if(col.dataColumnSpec.getType().equals(DoubleCell.TYPE))
	        				{
		        			DataCell cell= row.getCell(col.dataColumnIndex);
		        			if(cell.isMissing())
		        				{
		        				pstmt.setNull(col.index0+1, java.sql.Types.DOUBLE);
		        				}
		        			else
		        				{
		        				pstmt.setDouble(col.index0+1,DoubleCell.class.cast(cell).getDoubleValue());
		        				}
	        				}
		        		else if(col.dataColumnSpec.getType().equals(BooleanCell.TYPE))
	        				{
		        			DataCell cell= row.getCell(col.dataColumnIndex);
		        			if(cell.isMissing())
		        				{
		        				pstmt.setNull(col.index0+1, java.sql.Types.BOOLEAN);
		        				}
		        			else
		        				{
		        				pstmt.setBoolean(col.index0+1,BooleanCell.class.cast(cell).getBooleanValue());
		        				}
	        				}
		        		else
		        			{
		        			throw new IllegalArgumentException("data type not handled: "+col.dataColumnSpec.getType());
		        			}
		        		}
	        		boolean foundOne=false;
	        		result=pstmt.executeQuery();
	        		while(result.next())
	        			{
	        			outRow++;
	        			foundOne=true;

	        			if(ignoreMatches) break;
	        			DataRow outrow=new AppendedColumnRow(
	        					RowKey.createRowKey(outRow),
	        					row,
	        					resultSetToDataCell(result)
	        					);
	        			container1.addRowToTable(outrow);
	        			if(onePerMatch) break;
	        			}
	        		result.close();
	        		
	        		if(!foundOne)
		        		{
		        		if(!ignoreDiscarded)
			        		{
			        		container2.addRowToTable(row);
			        		}
		        		}

	        		
	        		//
	        		
	        		}
	        	if(container1==null)
	        		{
	        		container1=exec.createDataContainer(inTable.getDataTableSpec()); 
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
				safeClose(result);
				safeClose(pstmt);
				safeClose(con);
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
		safeClose(container1);
		safeClose(container2);
		}
   }
    

    
    @Override
    protected DataTableSpec[] configure(DataTableSpec[] inSpecs)
    		throws InvalidSettingsException {
    	if(inSpecs==null || inSpecs.length!=1)
			{
			throw new InvalidSettingsException("Expected one table");
			}
	
    	return new DataTableSpec[]{null,(DataTableSpec)inSpecs[0]};
    	}
    
    @Override
    protected List<SettingsModel> getSettingsModel() {
    	List<SettingsModel> L=new ArrayList<SettingsModel>( super.getSettingsModel());
    	L.add(this.m_sqlQuery);
    	L.add(this.m_join);
    	return L;
    	}
    
    
    
	}


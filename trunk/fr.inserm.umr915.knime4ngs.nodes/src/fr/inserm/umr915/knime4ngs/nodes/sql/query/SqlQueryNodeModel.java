package fr.inserm.umr915.knime4ngs.nodes.sql.query;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.knime.base.data.append.column.AppendedColumnRow;
import org.knime.core.data.DataCell;
import org.knime.core.data.DataColumnSpecCreator;
import org.knime.core.data.DataRow;
import org.knime.core.data.DataTableSpec;
import org.knime.core.data.DataType;
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
import org.knime.core.node.port.PortObject;
import org.knime.core.node.port.PortObjectSpec;
import org.knime.core.node.port.PortType;
import org.knime.core.node.port.database.DatabasePortObject;
import org.knime.core.node.port.database.DatabaseQueryConnectionSettings;
import org.knime.core.util.FileUtil;

import fr.inserm.umr915.knime4ngs.corelib.knime.AbstractNodeModel;








/**
 * @author Pierre Lindenbaum
 */
public class SqlQueryNodeModel extends AbstractNodeModel
	{
	
	final static String DEFAULT_QUERY="select now()";
    final static String QUERY_PROPERTY="sql.query"; 
	private final SettingsModelString m_sqlQuery = new SettingsModelString(QUERY_PROPERTY,DEFAULT_QUERY);
	
	/** property for table join */
	public static final String JOIN_PROPERTY="join.method";
	
	public static final String JOIN_PROPERTIES[]=new String[]
		{
		"All matches, All discarded",//0
		"One match, All discarded",//1
		"Ignore Match, All dicarded",//2
		"All Matches, Ignore dicarded",//3
		"One Match, Ignore dicarded"//4
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
        super(
        		new PortType[]{DatabasePortObject.TYPE,BufferedDataTable.TYPE},
        		new PortType[]{BufferedDataTable.TYPE,BufferedDataTable.TYPE}
        		);
        getLogger().error("OK here");
    	}
    
    @Override
    protected BufferedDataTable[] execute(final BufferedDataTable[] inData,
            final ExecutionContext exec)
        throws Exception {
            throw new IllegalStateException("Should not happen");
    	}
    
  
    
   
    @Override
    protected PortObject[] execute(final PortObject[] inObjects, final ExecutionContext exec)
    		throws Exception
        {
		BufferedDataContainer container1=null;
		BufferedDataContainer container2=null;
		DatabaseQueryConnectionSettings dbsettings=null;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet result=null;
		try
	    	{
	        // the data table spec of the single output table, 
	        // the table will have three columns:
			//data table
			BufferedDataTable inTable=(BufferedDataTable)inObjects[1];
			int total=inTable.getRowCount();
			//sql config
			DatabasePortObject inDbConfig=(DatabasePortObject)inObjects[0];
	       
			String joinChoice=m_join.getStringValue();
			boolean ignoreMatches=(joinChoice.equals(JOIN_PROPERTIES[2]));
			boolean ignoreDiscarded=(joinChoice.equals(JOIN_PROPERTIES[3]) || joinChoice.equals(JOIN_PROPERTIES[4]));
			boolean onePerMatch=(joinChoice.equals(JOIN_PROPERTIES[1]) || joinChoice.equals(JOIN_PROPERTIES[4]));

	       
	        
	        dbsettings =  new DatabaseQueryConnectionSettings(
                		inDbConfig.getConnectionModel(),
                		getCredentialsProvider()
                		);
	        
	        container2=exec.createDataContainer(inTable.getDataTableSpec());
	
	        int outRow=0;
	        int nRow=0;
	        CloseableRowIterator iter=null;
	        try {
	        	iter=inTable.iterator();
	        	String query= m_sqlQuery.getStringValue();
	        	
	        	QueryParser parser=new QueryParser(inTable.getDataTableSpec(),query);
	        	con=dbsettings.createConnection();
	        	
	        	
	        	
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
	        		exec.checkCanceled();
	            	exec.setProgress(nRow/total,"SQL....");
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
				try { if(result!=null) result.close(); }  catch (Exception e2) {}
				try { if(pstmt!=null) pstmt.close(); }  catch (Exception e2) {}
				try { if(con!=null) con.close(); }  catch (Exception e2) {}
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
    
    
    private DataCell[]  resultSetToDataCell(ResultSet m_result) throws SQLException,IOException
    	{
        DataCell[] cells = new DataCell[m_result.getMetaData().getColumnCount()];
       
        for (int i = 0; i < cells.length; i++)
        	{
            DataCell cell;
            int colType=m_result.getMetaData().getColumnType(i+1);
            switch (colType)
                	{
                    // all types that can be interpreted as integer
                    case Types.TINYINT:
                    case Types.SMALLINT:
                    case Types.INTEGER:
                    
                    case Types.BIT:
                    	{
                    	int value= m_result.getInt(i+1);
                    	cell=(m_result.wasNull()?DataType.getMissingCell():new IntCell(value));
                    	break;
                    	}
                    case Types.BOOLEAN:
                    	{
                        boolean value = m_result.getBoolean(i+1);
                        cell=(m_result.wasNull()?DataType.getMissingCell():(value?BooleanCell.TRUE:BooleanCell.FALSE));
                        break;
                    	}
                   case Types.REAL:
                   case Types.FLOAT:
                   case Types.DOUBLE:
                   case Types.DECIMAL:
                   case Types.NUMERIC:
                   		{
               			double value = m_result.getDouble(i+1);
                        cell=(m_result.wasNull()?DataType.getMissingCell():new DoubleCell(value));
                        break;
                   		}
                    case Types.BIGINT:
                    	{
                		long value = m_result.getLong(i+1);
                        cell=(m_result.wasNull()?DataType.getMissingCell():new LongCell(value));
                        break;
                    	}
                    case Types.DATE:
                    case Types.TIME:
                    case Types.TIMESTAMP:
                    case Types.CHAR:
                    case Types.VARCHAR:
                    case Types.NCHAR:
                    case Types.NVARCHAR:
                    case Types.LONGNVARCHAR:
                    	{
                		String value = m_result.getString(i+1);
                        cell=(value==null || m_result.wasNull()?DataType.getMissingCell():new StringCell(value));
                        break;
                    	}
                    case Types.VARBINARY:	
                    case Types.LONGVARBINARY:
                    	{
                    	cell= readBlob(m_result, i);
                    	break;
                    	}
                   default:
	                   	{
	                	throw new SQLException("data type not handled: "+colType);   
	                   	}
                     
                }
            // finally set the new cell into the array of cells
            cells[i] = cell;
            }
       return cells;
    }

    private DataCell readBlob(ResultSet m_result,int index0) throws SQLException,IOException
    		{
   
            InputStream is = m_result.getBinaryStream(index0);
            if (m_result.wasNull() || is == null) {
                return DataType.getMissingCell();
            } else {
                InputStreamReader reader = new InputStreamReader(is);
                StringWriter writer = new StringWriter();
                FileUtil.copy(reader, writer);
                reader.close();
                writer.close();
                return new StringCell(writer.toString());
            }
    
    	}
    
    
    private DataTableSpec createTableSpec(final ResultSetMetaData meta) throws SQLException
    	{
    	int cols = meta.getColumnCount();
    	DataTableSpec spec = null;
		for (int i = 0; i < cols; i++) {
		    int dbIdx = i + 1;
		    String name = meta.getColumnName(dbIdx);
		    int type = meta.getColumnType(dbIdx);
		    DataType newType;
		    switch (type) {
		        // all types that can be interpreted as integer
		        case Types.TINYINT:
		        case Types.SMALLINT:
		        case Types.INTEGER:
		        case Types.BIT:
		        case Types.BOOLEAN:
		            newType = IntCell.TYPE;
		            break;
		        // all types that can be interpreted as double
		        case Types.FLOAT:
		        case Types.DOUBLE:
		        case Types.NUMERIC:
		        case Types.DECIMAL:
		        case Types.REAL:
		        case Types.BIGINT:
		            newType = DoubleCell.TYPE;
		            break;
		        case Types.TIME:
		        case Types.DATE:
		        case Types.TIMESTAMP:
		            newType = StringCell.TYPE;
		            break;
		        default:
		            newType = StringCell.TYPE;
		    }
		    if (spec == null) {
		        spec = new DataTableSpec("database",
		                new DataColumnSpecCreator(
		                name, newType).createSpec());
		    } else {
		        name = DataTableSpec.getUniqueColumnName(spec, name);
		        spec = new DataTableSpec("database", spec,
		               new DataTableSpec(new DataColumnSpecCreator(
		                       name, newType).createSpec()));
		    }
		}
		return spec;
		}
    
    @Override
    protected PortObjectSpec[] configure(PortObjectSpec[] inSpecs)
    		throws InvalidSettingsException
    	{
    	if(inSpecs==null || inSpecs.length!=2)
    		{
    		throw new InvalidSettingsException("Expected two table");
    		}
    	
    	return new DataTableSpec[]{null,(DataTableSpec)inSpecs[1]};
    	}
    
    @Override
    protected DataTableSpec[] configure(DataTableSpec[] inSpecs)
    		throws InvalidSettingsException {
    	throw new IllegalStateException("should not happen");
    	}
    
    @Override
    protected List<SettingsModel> getSettingsModel() {
    	List<SettingsModel> L=new ArrayList<SettingsModel>( super.getSettingsModel());
    	L.add(this.m_sqlQuery);
    	L.add(this.m_join);
    	return L;
    	}
    
    
    
	}


package fr.inserm.umr915.knime4ngs.nodes.sql;


import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;

import org.knime.core.data.DataCell;
import org.knime.core.data.DataColumnSpecCreator;
import org.knime.core.data.DataTableSpec;
import org.knime.core.data.DataType;
import org.knime.core.data.def.BooleanCell;
import org.knime.core.data.def.DoubleCell;
import org.knime.core.data.def.IntCell;
import org.knime.core.data.def.LongCell;
import org.knime.core.data.def.StringCell;
import org.knime.core.node.InvalidSettingsException;
import org.knime.core.node.NodeSettingsRO;
import org.knime.core.node.defaultnodesettings.SettingsModel;
import org.knime.core.node.defaultnodesettings.SettingsModelString;
import org.knime.core.util.FileUtil;

import java.sql.Connection;

import fr.inserm.umr915.knime4ngs.corelib.knime.AbstractNodeModel;

/**
 * 
 * AbstractSqlNodeModel
 *
 */
public abstract class AbstractSqlNodeModel extends AbstractNodeModel
	{
	/** default mysql driver */
	public final static String MYSQL_DRIVER="com.mysql.jdbc.Driver";
	
	
	final static String JDBC_URIS[]=new String[]{
		"jdbc:mysql://localhost",
		"jdbc:mysql://genome-mysql.cse.ucsc.edu/hg18",
		"jdbc:mysql://genome-mysql.cse.ucsc.edu/hg19"
		};
    final static String JDBC_URI_PROPERTY="jdbc.uri"; 
    final static String DEFAULT_JDBC_URI_PROPERTY=JDBC_URIS[0];
    
    private final SettingsModelString m_jdbcUri = new SettingsModelString(
    		JDBC_URI_PROPERTY,
    		DEFAULT_JDBC_URI_PROPERTY
    		);
	
    final static String JDBC_LOGIN="jdbc.login"; 
    final static String DEFAULT_JDBC_LOGIN="anonymous";
    
    private final SettingsModelString m_jdbcLogin = new SettingsModelString(
    		JDBC_LOGIN,
    		DEFAULT_JDBC_LOGIN
    		);
    final static String JDBC_PASSWORD="jdbc.password"; 
    final static String DEFAULT_JDBC_PASSWORD="";
    private final SettingsModelString m_jdbcPassword = new SettingsModelString(
    		JDBC_PASSWORD,
    		DEFAULT_JDBC_PASSWORD
    		);
    
    public final static Set<String> ALL_URIS=Collections.synchronizedSet(new TreeSet<String>());
    public final static Set<String> ALL_LOGINS=Collections.synchronizedSet(new TreeSet<String>());
    public final static Set<String> ALL_PASSWORDS=Collections.synchronizedSet(new TreeSet<String>());
    
	protected AbstractSqlNodeModel(int inDataPorts,int outDataPorts)
		{
		super(inDataPorts,outDataPorts);
		for(String s:JDBC_URIS) ALL_URIS.add(s);
		
		}
	
	/** public (called by view) */
	public Connection createConnection() throws java.sql.SQLException
		{
		try {
			Class.forName(MYSQL_DRIVER);
			} 
		catch (Exception e) {
			throw new SQLException(e);
			}
		final int timeout=45;
		Properties props=new Properties();
		props.put("user", m_jdbcLogin.getStringValue());
		props.put("password", m_jdbcPassword.getStringValue());
		//http://dev.mysql.com/doc/refman/5.0/en/connector-j-reference-configuration-properties.html
		props.put("connectTimeout", String.valueOf(timeout*1000));//45secs
		props.put("socketTimeout", String.valueOf(timeout*1000));//45secs
		DriverManager.setLoginTimeout(timeout);//45 secs
		try
			{
			return DriverManager.getConnection(
				m_jdbcUri.getStringValue(),
				props
				);
			}
		catch(java.sql.SQLException err)
			{
			System.err.println("Cannot connect: "+m_jdbcUri.getStringValue()+":"+props);
			err.printStackTrace();
			throw err;
			}	
		}
	
	@Override
	protected void loadValidatedSettingsFrom(NodeSettingsRO settings)
			throws InvalidSettingsException {
		super.loadValidatedSettingsFrom(settings);
		String s=m_jdbcUri.getStringValue();
		if(s!=null && !(s.isEmpty())) ALL_URIS.add(s);
		s=m_jdbcLogin.getStringValue();
		if(s!=null && !(s.isEmpty())) ALL_LOGINS.add(s);
		s=m_jdbcPassword.getStringValue();
		if(s!=null) ALL_PASSWORDS.add(s);
		}
	
	
	public void safeClose(Connection o)
		{
		if(o!=null) try { o.close();} catch(Exception err) {}
		}
	
	public void safeClose(Statement o)
		{
		if(o!=null) try { o.close();} catch(Exception err) {}
		}
	
	public void safeClose(ResultSet o)
		{
		if(o!=null) try { o.close();} catch(Exception err) {}
		}
	
    @Override
    protected List<SettingsModel> getSettingsModel() {
    	List<SettingsModel> L=new ArrayList<SettingsModel>( super.getSettingsModel());
    	L.add(this.m_jdbcUri);
    	L.add(this.m_jdbcLogin);
    	L.add(this.m_jdbcPassword);
    	return L;
    	}
    
    
    protected DataCell[]  resultSetToDataCell(ResultSet m_result) throws SQLException,IOException
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

    protected DataCell readBlob(ResultSet m_result,int index0) throws SQLException,IOException
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


protected DataTableSpec createTableSpec(final ResultSetMetaData meta) throws SQLException
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
    
	}

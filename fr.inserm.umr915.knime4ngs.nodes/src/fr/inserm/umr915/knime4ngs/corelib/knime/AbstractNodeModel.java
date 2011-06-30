package fr.inserm.umr915.knime4ngs.corelib.knime;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.zip.GZIPInputStream;

import org.knime.core.data.DataRow;
import org.knime.core.data.DataTable;
import org.knime.core.data.DataTableSpec;
import org.knime.core.data.DataType;
import org.knime.core.data.RowIterator;
import org.knime.core.data.container.CloseableRowIterator;
import org.knime.core.data.def.BooleanCell;
import org.knime.core.data.def.DoubleCell;
import org.knime.core.data.def.IntCell;
import org.knime.core.data.def.LongCell;
import org.knime.core.data.def.StringCell;
import org.knime.core.node.BufferedDataContainer;
import org.knime.core.node.BufferedDataTable;
import org.knime.core.node.CanceledExecutionException;
import org.knime.core.node.ExecutionContext;
import org.knime.core.node.ExecutionMonitor;
import org.knime.core.node.InvalidSettingsException;
import org.knime.core.node.NodeLogger;
import org.knime.core.node.NodeModel;
import org.knime.core.node.NodeSettingsRO;
import org.knime.core.node.NodeSettingsWO;
import org.knime.core.node.defaultnodesettings.SettingsModel;
import org.knime.core.node.defaultnodesettings.SettingsModelColumnName;
import org.knime.core.node.port.PortType;



public abstract class AbstractNodeModel  extends NodeModel
	{
	// the logger instance
    private static final NodeLogger logger = NodeLogger.getLogger("knime4bio");
	protected AbstractNodeModel(int nrInDataPorts, int nrOutDataPorts)
		{
		super(nrInDataPorts, nrOutDataPorts);
		}

	protected AbstractNodeModel(PortType[] inPortTypes, PortType[] outPortTypes)
		{
		super(inPortTypes, outPortTypes);
		}
	
	
	
	protected InputStream openStream(String uri) throws IOException
		{
		getLogger().info(uri);
		InputStream in=null;
		if(uri.startsWith("http://") || uri.startsWith("https://") || uri.startsWith("ftp://") || uri.startsWith("file://"))
			{
			URL url=new URL(uri);
			in=url.openStream();
			}
		else
			{
			in=new FileInputStream(uri);
			}
		if(uri.toLowerCase().endsWith(".gz"))
			{
			in=new GZIPInputStream(in);
			}
		return in;
		}
	
	protected BufferedReader openReader(String uri) throws IOException
		{
		getLogger().info("opening "+uri);
		return new BufferedReader(new InputStreamReader(openStream(uri)));
		}
	
	protected static NodeLogger getLogger()
		{
		return AbstractNodeModel.logger;
		}
	
	protected List<SettingsModel> getSettingsModel()
		{
		return Collections.emptyList();
		}
	
	public String getNodeName()
		{
		return getClass().getSimpleName();
		}
	
	/**
	 * Check DataRow have been sorted
	 * @param inData
	 * @param exec
	 */
	protected boolean isDataTableIsSorted(
			final DataTable inData,
			final ExecutionContext exec,
			Comparator<DataRow> dataRowComparator
			) throws IllegalArgumentException,CanceledExecutionException
		 {
	     boolean isSorted=true;
		 DataRow prev=null;
		 RowIterator iter=null;
		 int nRow=0;
		 try    {
		       	iter=inData.iterator();
		       	while(iter.hasNext())
		       		{
		       		++nRow;
		       		DataRow row=iter.next();
		       		if(prev!=null && dataRowComparator.compare(prev, row)>0)
		       			{
		       			isSorted=false;
		       			break;
		       			}
		       		prev=row;
		       		exec.checkCanceled();
			       	exec.setProgress("Checking table is ordered...");
		       		}
				} 
	       catch (IllegalArgumentException e)
				{
				throw e;
				}
	       catch (CanceledExecutionException e)
				{
				throw e;
				}
			finally
				{
				safeClose(iter);
				}
		 return isSorted;
		 }
	
	
	protected abstract BufferedDataTable[] execute(final BufferedDataTable[] inData,
            final ExecutionContext exec)
        throws Exception;
	
	/**
     * {@inheritDoc}
     */
    @Override
    protected void saveSettingsTo(final NodeSettingsWO settings)
    	{
    	for(SettingsModel param:getSettingsModel())
    		{
    		param.saveSettingsTo(settings);
    		}
    	}
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void loadValidatedSettingsFrom(final NodeSettingsRO settings)
            throws InvalidSettingsException
        {
    	for(SettingsModel param:getSettingsModel())
			{
    		param.loadSettingsFrom(settings);
			}
        }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void validateSettings(final NodeSettingsRO settings)
            throws InvalidSettingsException
       {
    	for(SettingsModel param:getSettingsModel())
			{
			param.validateSettings(settings);
			}
       }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void loadInternals(final File internDir,
            final ExecutionMonitor exec) throws IOException,
            CanceledExecutionException
            {
    		getLogger().info(getNodeName()+" load internals from "+internDir);
            }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void saveInternals(final File internDir,
            final ExecutionMonitor exec) throws IOException,
            CanceledExecutionException
        {
    	getLogger().info(getNodeName()+" save internals from "+internDir);
    	}

    /**
     * {@inheritDoc}
     */
    @Override
    protected void reset()
    	{
    	getLogger().info(getNodeName()+" reset");
    	}
    /** throws an InvalidSettingsException if column was not found */
    public int findColumnIndex(DataTableSpec dataTableSpec,String name)
		throws InvalidSettingsException
		{
		int index;
		if((index=dataTableSpec.findColumnIndex(name))==-1)
			{
			throw new InvalidSettingsException("Node "+this.getNodeName()+": cannot find column title= \""+name+"\"");
			}
		return index;
		}
	
	 /** throws an InvalidSettingsException if column was not found */
	public int findColumnIndex(DataTableSpec dataTableSpec,String name,DataType dataType)
	throws InvalidSettingsException
		{
		if(name==null) throw new NullPointerException("undefined column name");
		int index=findColumnIndex(dataTableSpec,name);
		if(dataTableSpec.getColumnSpec(name).getType()!=dataType)
			{
			throw new InvalidSettingsException("Node "+this.getNodeName()+" column["+index+"]=\""+name+"\" is not a \""+dataType+"\" but a \""+dataTableSpec.getColumnSpec(name).getType()+"\"");
			}
		return index;
		}
	
	public int findColumnIndex(DataTableSpec dataTableSpec,SettingsModelColumnName colName,DataType dataType)
		throws InvalidSettingsException
		{
		return findColumnIndex(dataTableSpec,colName.getColumnName(),dataType);
		}
	
	
	protected String getString(DataRow row,int index)
		{
		StringCell cell= StringCell.class.cast(row.getCell(index));
		if(cell.isMissing()) return null;
		return cell.getStringValue();
		}
	
	protected int getInt(DataRow row,int index)
		{
		IntCell cell= IntCell.class.cast(row.getCell(index));
		if(cell.isMissing()) throw new IllegalStateException();
		return cell.getIntValue();
		}
	
	protected long getLong(DataRow row,int index)
		{
		LongCell cell= LongCell.class.cast(row.getCell(index));
		if(cell.isMissing()) throw new IllegalStateException();
		return cell.getLongValue();
		}
	
	protected double getDouble(DataRow row,int index)
		{
		DoubleCell cell= DoubleCell.class.cast(row.getCell(index));
		if(cell.isMissing()) throw new IllegalStateException();
		return cell.getDoubleValue();
		}
	protected boolean getBool(DataRow row,int index)
		{
		BooleanCell cell= BooleanCell.class.cast(row.getCell(index));
		if(cell.isMissing()) throw new IllegalStateException();
		return cell.getBooleanValue();
		}
	
	protected void safeClose(RowIterator iter)
		{
		if(iter==null) return;
		if(iter instanceof CloseableRowIterator)
			{
			CloseableRowIterator.class.cast(iter).close();
			}
		}
	protected void safeClose(InputStream in)
		{
		if(in==null) return;
		try { in.close();}
		catch (Exception e) {}
		}
	
	protected void safeClose(Reader in)
		{
		if(in==null) return;
		try { in.close();}
		catch (Exception e) {}
		}
	protected void safeClose(BufferedDataContainer c)
		{
		if(c==null) return;
		try { c.close();}
		catch (Exception e) {e.printStackTrace();}
		}
	}

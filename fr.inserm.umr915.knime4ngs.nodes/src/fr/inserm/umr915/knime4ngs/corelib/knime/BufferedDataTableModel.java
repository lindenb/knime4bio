package fr.inserm.umr915.knime4ngs.corelib.knime;

import java.beans.PropertyChangeSupport;
import java.util.Vector;

import javax.swing.SwingUtilities;
import javax.swing.table.AbstractTableModel;

import org.knime.core.data.DataCell;
import org.knime.core.data.DataColumnSpec;
import org.knime.core.data.DataRow;
import org.knime.core.data.container.CloseableRowIterator;
import org.knime.core.data.def.BooleanCell;
import org.knime.core.data.def.DoubleCell;
import org.knime.core.data.def.IntCell;
import org.knime.core.data.def.LongCell;
import org.knime.core.data.def.StringCell;
import org.knime.core.node.BufferedDataTable;

public class BufferedDataTableModel extends AbstractTableModel
	{
	public static final String IS_SCANNING_PROPERTY="is.scanning";
	private static final long serialVersionUID = 1L;
	private static final int CACHE_SIZE=1000;
	private BufferedDataTable dataTable;
	private int rowCount=0;
	private int colCount=0;
	private Vector<DataRow> cachedRows=new Vector<DataRow>(CACHE_SIZE);
	private int cachedRowIndex=0;
	private Vector<Class<?>> classes=new Vector<Class<?>>();
	private Vector<String> headers=new Vector<String>();
	private ScanThread scanthread=null;
	private java.beans.PropertyChangeSupport propertyChangeSupport;
	
	private class ScanThread extends Thread
		{
		int startRow;
		BufferedDataTable table;
		CloseableRowIterator iter=null;
		Vector<DataRow> cachedRows=new Vector<DataRow>(CACHE_SIZE);
		
		ScanThread(int startRow,BufferedDataTable table)
			{
			this.startRow=startRow;
			this.table=table;
			}
		@Override
		public void run()
			{
			
			try {
				int nRow=-1;
				iter=this.table.iterator();
				while(iter.hasNext())
					{
					DataRow dataRow=iter.next();
					nRow++;
					if(nRow>=this.startRow)
						{
						this.cachedRows.add(dataRow);
						}
					if(this.cachedRows.size()+1>=CACHE_SIZE)
						{
						break;
						}
					}
				iter.close();
				iter=null;
				SwingUtilities.invokeAndWait(new Runnable()
					{
					@Override
					public void run()
						{
						try
								{
								BufferedDataTableModel.this.cachedRows=ScanThread.this.cachedRows;
								BufferedDataTableModel.this.cachedRowIndex=ScanThread.this.startRow;
								BufferedDataTableModel.this.scanthread=null;
								if(!BufferedDataTableModel.this.cachedRows.isEmpty())
									{
									BufferedDataTableModel.this.fireTableRowsUpdated(
										BufferedDataTableModel.this.cachedRowIndex,
										BufferedDataTableModel.this.cachedRowIndex+BufferedDataTableModel.this.cachedRows.size()-1
										);
									}
								}
						catch(Exception err)
							{
							//ignore err.printStackTrace();
							}
						}
					});
				}
			catch (Exception e)
				{
				//ignore -+e.printStackTrace();
				return;
				}
			finally
				{
				if(iter!=null) iter.close();
				}
			}
		}
	
	
	public BufferedDataTableModel()
		{
		this(null);
		}
	
	public BufferedDataTableModel(BufferedDataTable dataTable)
		{
		this.propertyChangeSupport=new PropertyChangeSupport(this);
		setDataTable(dataTable);
		}
	
	public java.beans.PropertyChangeSupport getPropertyChangeSupport()
		{
		return propertyChangeSupport;
		}
	
	private synchronized void fireScanningEvent(boolean isScanning)
		{
		boolean old=this.scanthread!=null;
		this.propertyChangeSupport.firePropertyChange(IS_SCANNING_PROPERTY, old,isScanning);
		}
	
	public synchronized void setDataTable(BufferedDataTable dataTable)
		{
		stop();
		this.dataTable=dataTable;
		if(this.dataTable==null)
			{
			this.rowCount=0;
			this.colCount=0;
			this.classes.clear();
			this.headers.clear();
			}
		else
			{
			this.rowCount=this.dataTable.getRowCount();
			this.colCount=this.dataTable.getDataTableSpec().getNumColumns();
			this.classes.setSize(this.colCount);
			this.headers.setSize(this.colCount);
			for(int i=0;i< this.colCount;++i)
				{
				DataColumnSpec colspec=this.dataTable.getDataTableSpec().getColumnSpec(i);
				this.headers.set(i, colspec.getName());
				if(colspec.getType()==StringCell.TYPE)
					{
					this.classes.set(i,String.class);
					}
				else if(colspec.getType()==BooleanCell.TYPE)
					{
					this.classes.set(i,Boolean.class);
					}
				else if(colspec.getType()==IntCell.TYPE)
					{
					this.classes.set(i,Integer.class);
					}
				else if(colspec.getType()==DoubleCell.TYPE)
					{
					this.classes.set(i,Double.class);
					}
				else if(colspec.getType()==LongCell.TYPE)
					{
					this.classes.set(i,Long.class);
					}
				else
					{
					this.classes.set(i,String.class);
					}
				}
			}
		fireTableStructureChanged();
		}
	
	@Override
	public int getRowCount() {
		return this.rowCount;
		}

	@Override
	public int getColumnCount() {
		return this.colCount;
		}

	@Override
	public synchronized Class<?> getColumnClass(int columnIndex)
		{
		return this.classes.get(columnIndex);
		}
	
	
	@Override
	public synchronized String getColumnName(int column) {
		return this.headers.get(column);
		}
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex)
		{
		return false;
		}
	
	@Override
	public synchronized Object getValueAt(int rowIndex, int columnIndex)
		{
		if(this.dataTable==null) return null;
		if(	rowIndex>=this.cachedRowIndex &&
			rowIndex-this.cachedRowIndex<this.cachedRows.size())
			{
			DataCell cell= this.cachedRows.get(rowIndex-this.cachedRowIndex).getCell(columnIndex);
			if(cell.isMissing()) return null;
			return toObject(cell);
			}
		
		ScanThread thread=this.scanthread;
		if(thread!=null && thread.startRow<=rowIndex && rowIndex< thread.startRow+CACHE_SIZE )
			{
			return null;
			}
		
		scan(rowIndex);
		return null;
		}
	
	
	private synchronized void stop()
		{
		if(this.scanthread!=null)
			{
			try {
				this.scanthread.interrupt();
				}
			catch (Exception e)
				{
				}
			this.scanthread=null;
			fireScanningEvent(false);
			}
		}
	
	private synchronized void scan(int rowIndex)
		{
		stop();
		this.scanthread=new ScanThread(rowIndex,this.dataTable);
		this.scanthread.start();
		fireScanningEvent(true);
		}
	
	
	private static Object toObject(DataCell cell)
		{
		if(cell==null || cell.isMissing()) return null;
		if(cell.getType()==StringCell.TYPE)
			{
			return StringCell.class.cast(cell).getStringValue();
			}
		else if(cell.getType()==BooleanCell.TYPE)
			{
			return BooleanCell.class.cast(cell).getBooleanValue();
			}
		else if(cell.getType()==IntCell.TYPE)
			{
			return IntCell.class.cast(cell).getIntValue();
			}
		else if(cell.getType()==DoubleCell.TYPE)
			{
			return DoubleCell.class.cast(cell).getDoubleValue();
			}
		else if(cell.getType()==LongCell.TYPE)
			{
			return LongCell.class.cast(cell).getLongValue();
			}
		else
			{
			return cell.toString();
			}
		}
	
	public synchronized void dispose()
		{
		stop();
		}
	}

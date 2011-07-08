package fr.inserm.umr915.knime4ngs.nodes.ncbi.view;

import java.awt.BorderLayout;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.net.URL;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;



import org.knime.core.node.BufferedDataTable;


import fr.inserm.umr915.knime4ngs.corelib.knime.AbstractNodeView;
import fr.inserm.umr915.knime4ngs.corelib.knime.BufferedDataTableModel;

/**
 * 
 * NcbiViewNodeView
 *
 */
public class NcbiViewNodeView  extends AbstractNodeView<NcbiViewNodeModel>
	{
	private int idColumn=-1;
	private String database=null;
	private NcbiViewNodeModel nodeModel;
	private BufferedDataTableModel tableModel;
	private JTable table;
	private JTextArea textArea;
	private Lookup NcbiThread=null;
	
	
	/**
	 * Thread
	 */
	private class Lookup extends Thread
		{
		String database;
		Integer ncbiId;
		private String html="";
		Lookup( String database, Integer ncbiId)
			{
			this.database=database;
			this.ncbiId=ncbiId;
			}
		
		private String fetchRecord(
				String database,
				Integer ncbiId
				) throws Exception
				{
				
				if(database==null  ||database.isEmpty() || ncbiId==null || ncbiId<1)
					{
					return "";
					}
				
				Reader r=null;
				try {
					String uri="";
					if(database.equalsIgnoreCase("pubmed"))
						{
						uri="http://www.ncbi.nlm.nih.gov/entrez/eutils/efetch.fcgi?db=pubmed&id="+ncbiId+"&rettype=abstract&retmode=text";
						}
					else if(database.equalsIgnoreCase("gene"))
						{
						uri="http://www.ncbi.nlm.nih.gov/entrez/eutils/efetch.fcgi?db=gene&id="+ncbiId+"&rettype=text&retmode=text";
						}
					else if(database.equalsIgnoreCase("snp"))
						{
						uri="http://www.ncbi.nlm.nih.gov/entrez/eutils/efetch.fcgi?db=snp&id="+ncbiId+"&rettype=text&retmode=text";
						}
					else
						{
						return "";
						}
					StringWriter w=new StringWriter();
					int nRead;
					char array[]=new char[2048];
					r=new InputStreamReader(new URL(uri).openStream());
					while((nRead=r.read(array))!=-1)
						{
						w.write(array, 0, nRead);
						}
					return w.toString();
					}
				catch (Exception err)
					{
					err.printStackTrace();
					return String.valueOf(err.getMessage());
					}
				finally
					{
					if(r!=null) r.close();
				    }
				}

		
		
		@Override
		public void run()
			{

			try
				{
				this.html="";
				try
					{
					this.html= fetchRecord(
						this.database,
						this.ncbiId
						);
					}
				catch(Throwable err)
					{
					this.html=String.valueOf(err.getMessage());
					}
				if(NcbiViewNodeView.this.NcbiThread==this
							&& !interrupted())
					{
					SwingUtilities.invokeAndWait(new Runnable()
						{
						@Override
						public void run()
							{
							
							NcbiViewNodeView.this.textArea.setText(Lookup.this.html);
							NcbiViewNodeView.this.textArea.setCaretPosition(0);
							}
						});
					}
				
					
				}
			catch(Exception err)
				{
				err.printStackTrace();
				try {
					SwingUtilities.invokeAndWait(new Runnable()
						{
						@Override
						public void run()
							{
							NcbiViewNodeView.this.textArea.setText("An error occured.");
							}
						});
					} 
				catch (Exception e) {
					//System.err.println("checked ? export CATALINA_OPTS=\"-Djava.awt.headless=true\"");
					e.printStackTrace();
					}
				}
			}
		}
	
	public NcbiViewNodeView(NcbiViewNodeModel nodeModel)
		{
		super(nodeModel);
		this.nodeModel=nodeModel;
		this.tableModel=new BufferedDataTableModel();
		this.tableModel.addTableModelListener(new TableModelListener()
			{
			@Override
			public void tableChanged(TableModelEvent e)
				{
				tableModelChanged();
				}
			});
		this.table=new JTable(this.tableModel);
		this.table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		this.table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.table.getSelectionModel().addListSelectionListener(new ListSelectionListener()
			{
			@Override
			public void valueChanged(ListSelectionEvent e)
				{
				updateSelection();
				}
			});
		JPanel right=new JPanel(new BorderLayout(5,5));
		JScrollPane scroll=new JScrollPane(table);
		right.add(scroll,BorderLayout.CENTER);
		
		
		
		JTabbedPane left=new JTabbedPane();
		
		
			{
			JPanel pane2=new JPanel(new BorderLayout());
			this.textArea=new JTextArea(25,80);
			pane2.add(new JScrollPane(this.textArea));
			left.addTab("NCBI",pane2);
			}
		
		
		JSplitPane split=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,right,left);
		JPanel pane=new JPanel(new BorderLayout(5,5));
		pane.add(split,BorderLayout.CENTER);
		setComponent(pane);
		}
	
	private void tableModelChanged()
		{
		updateSelection();
		}
	
	private void updateSelection()
		{
		if(idColumn==-1) return;
		if(this.database==null || database.isEmpty()) return;
		int n= this.table.getSelectedRow();
		if(n==-1) return;
		n= this.table.convertRowIndexToModel(n);
		if(n==-1) return;
		Object o1=this.tableModel.getValueAt(n,this.idColumn);
		if(o1!=null && !(o1 instanceof Integer)) return;
		
		
		Integer id=((Integer)o1);
		this.NcbiThread=new Lookup(
				this.database,id);
		this.NcbiThread.start();
		}
	
	@Override
	protected void modelChanged()
		{
		disposeNcbiThread();
		//System.err.println("model changed called");
		idColumn=-1;
		
		
		
		BufferedDataTable tables[]=nodeModel.getInternalTables();
		
		
		if(tables!=null && tables.length>0)
			{
			this.idColumn=this.nodeModel.getNcbIdColumn();
			this.database=this.nodeModel.getNcbIDatabase();
			this.tableModel.setDataTable(tables[0]);
			}
		else
			{
			this.tableModel.setDataTable(null);
			}
		}
	
	@Override
	protected void onOpen()
		{
		modelChanged();
		}
	
	
	@Override
	protected void onClose()
		{
		disposeNcbiThread();
		this.tableModel.dispose();
		}
	
	
	
	

private synchronized void disposeNcbiThread()
	{
	if(NcbiThread!=null)
		{
		try { NcbiThread.interrupt();}
		catch(Exception err) {}
		NcbiThread=null;
		}
	}
	
	
	}

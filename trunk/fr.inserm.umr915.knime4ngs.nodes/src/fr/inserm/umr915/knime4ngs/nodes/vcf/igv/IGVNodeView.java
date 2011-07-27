package fr.inserm.umr915.knime4ngs.nodes.vcf.igv;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import org.knime.core.node.BufferedDataTable;
import fr.inserm.umr915.knime4ngs.corelib.knime.AbstractNodeView;
import fr.inserm.umr915.knime4ngs.corelib.knime.BufferedDataTableModel;

/**
 * 
 * IGVNodeView
 *
 */
public class IGVNodeView  extends AbstractNodeView<IGVNodeModel>
	{
	private int chromColumn=-1;
	private int posColumn=-1;
	private String igvHost=IGVNodeModel.IGV_HOST_DEFAULT;
	private int igvPort=IGVNodeModel.IGV_PORT_DEFAULT;
	private IGVNodeModel nodeModel;
	private BufferedDataTableModel tableModel;
	private JTable table;
	private JTextField countRowsLabel;


	public IGVNodeView(IGVNodeModel nodeModel)
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
		
		JPanel content=new JPanel(new BorderLayout(5,5));
		JScrollPane scroll=new JScrollPane(table);
		content.add(scroll,BorderLayout.CENTER);
		JPanel bot=new JPanel(new FlowLayout(FlowLayout.LEADING));
		content.add(bot,BorderLayout.SOUTH);
		countRowsLabel=new JTextField(20);
		countRowsLabel.setEditable(false);
		bot.add(countRowsLabel);

	
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener()
			{
			@Override
			public void valueChanged(ListSelectionEvent arg0)
				{
				if(IGVNodeView.this.table.getSelectedRow()==-1) return;
				showSelection();
				}
			});
		
		
		setComponent(content);
		}
	
	private void tableModelChanged()
		{
		if(countRowsLabel!=null)
			{
			countRowsLabel.setText("Rows: "+this.tableModel.getRowCount());
			}
		}
	
	private void showSelection()
		{
		if(chromColumn==-1) return;
		if(posColumn==-1) return;
		if(igvHost==null || igvHost.isEmpty()) return;
		int n= this.table.getSelectedRow();
		if(n==-1) return;
		n= this.table.convertRowIndexToModel(n);
		if(n==-1) return;
		Object o1=this.tableModel.getValueAt(n,this.chromColumn);
		if(o1==null || !(o1 instanceof String)) return;
		String chrom=o1.toString();
		Object o2=this.tableModel.getValueAt(n,this.posColumn);
		if(o2==null || !(o2 instanceof Integer)) return;
		Integer pos=(Integer)o2;
		
		Socket socket=null;
		PrintWriter out=null;
		BufferedReader in=null;
		try
			{
			socket = new Socket(this.igvHost,this.igvPort);
	        out = new PrintWriter(socket.getOutputStream(), true);
	        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	        out.println("goto "+chrom+":"+pos);
	        in.readLine();
			}
		catch(Exception err)
			{
			JOptionPane.showMessageDialog(this.table, String.valueOf(err.getMessage()));
			}
		finally
			{
			if(in!=null) try{ in.close();} catch(Exception err) {}
			if(out!=null) try{ out.close();} catch(Exception err) {}
			if(socket!=null) try{ socket.close();} catch(Exception err) {}
			}
		}
	
	@Override
	protected void modelChanged()
		{
		//System.err.println("model changed called");
		chromColumn=-1;
		posColumn=-1;
		igvHost=IGVNodeModel.IGV_HOST_DEFAULT;
		igvPort=IGVNodeModel.IGV_PORT_DEFAULT;
		
		
		BufferedDataTable tables[]=nodeModel.getInternalTables();
		
		
		
		if(tables!=null && tables.length>0 && tables[0]!=null)
			{
			this.chromColumn = tables[0].getDataTableSpec().findColumnIndex(this.nodeModel.m_chromColumn.getColumnName());
			this.posColumn= tables[0].getDataTableSpec().findColumnIndex(this.nodeModel.m_posColumn.getColumnName());
			this.igvHost = this.nodeModel.m_igvHost.getStringValue();
			this.igvPort = this.nodeModel.m_igvPort.getIntValue();
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
		this.tableModel.dispose();
		}
	
	

	
	}

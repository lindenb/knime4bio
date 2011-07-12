package fr.inserm.umr915.knime4ngs.nodes.vcf.browser;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.net.URLEncoder;

import javax.swing.AbstractAction;
import javax.swing.JButton;
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
 * LocalBamNodeView
 *
 */
public class WebBrowserNodeView  extends AbstractNodeView<WebBrowserNodeModel>
	{
	private int chromColumn=-1;
	private int posColumn=-1;
	private String build="hg19";
	private WebBrowserNodeModel nodeModel;
	private BufferedDataTableModel tableModel;
	private JTable table;
	private JTextField countRowsLabel;
	private AbstractAction openUcscBrowser;
	
	@SuppressWarnings("serial")
	public WebBrowserNodeView(WebBrowserNodeModel nodeModel)
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

		this.openUcscBrowser=new AbstractAction("Open")
			{
			@Override
			public void actionPerformed(ActionEvent arg0)
				{
				showSelection();				
				}
			};
		this.openUcscBrowser.setEnabled(false);
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener()
			{
			@Override
			public void valueChanged(ListSelectionEvent arg0)
				{
				WebBrowserNodeView.this.openUcscBrowser.setEnabled(WebBrowserNodeView.this.table.getSelectedRow()!=-1);
				}
			});
		
		JButton button=new JButton(this.openUcscBrowser);
		button.setOpaque(true);
		button.setFont(button.getFont().deriveFont(48f));
		button.setBackground(Color.GREEN);
		bot.add(button);
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
		if(build==null || build.isEmpty()) return;
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
		
		java.awt.Desktop desktop = java.awt.Desktop.getDesktop();
		if(desktop==null || !desktop.isSupported(java.awt.Desktop.Action.BROWSE)) return;
		
		try
			{
			java.net.URI uri = new java.net.URI(
				"http://genome.ucsc.edu/cgi-bin/hgTracks?org=Human&db="+
				URLEncoder.encode(build, "UTF-8")+"&position="+
				URLEncoder.encode(chrom+":"+
						(pos-1)+"-"+
						(pos+1), "UTF-8")
				);
			desktop.browse( uri );
			}
		catch(Exception err)
			{
			JOptionPane.showMessageDialog(this.table, String.valueOf(err.getMessage()));
			}
		
		}
	
	@Override
	protected void modelChanged()
		{
		//System.err.println("model changed called");
		chromColumn=-1;
		posColumn=-1;
		build="hg19";
		
		
		BufferedDataTable tables[]=nodeModel.getInternalTables();
		
		
		
		if(tables!=null && tables.length>0 && tables[0]!=null)
			{
			this.chromColumn = tables[0].getDataTableSpec().findColumnIndex(this.nodeModel.m_chromColumn.getColumnName());
			this.posColumn= tables[0].getDataTableSpec().findColumnIndex(this.nodeModel.m_posColumn.getColumnName());
			this.build = this.nodeModel.m_build.getStringValue();
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

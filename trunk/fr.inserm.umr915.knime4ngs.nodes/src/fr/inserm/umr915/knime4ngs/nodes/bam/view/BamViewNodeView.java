package fr.inserm.umr915.knime4ngs.nodes.bam.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import org.knime.core.node.BufferedDataTable;


import fr.inserm.umr915.knime4ngs.corelib.knime.AbstractNodeView;
import fr.inserm.umr915.knime4ngs.corelib.knime.BufferedDataTableModel;

public class BamViewNodeView  extends AbstractNodeView<BamViewNodeModel>
	{
	private int chromColumn=-1;
	private int posColumn=-1;
	private int sampleColumn=-1;
	private Map<String,String> sample2bam=new HashMap<String, String>();
	private BamViewNodeModel nodeModel;
	private BufferedDataTableModel tableModel;
	private JTable table;
	private JLabel bamIconLabel;
	private JTextField countRowsLabel;
	private Lookup bamThread=null;
	
	
	/**
	 * Thread
	 */
	private class Lookup extends Thread
		{
		BufferedImage img=null;
		private String sampleName=null;
		private String bamName=null;
		String chrom;
		int start;
		int end;
		Integer highlight;
		
		Lookup(
			String sampleName,
			String bamName,
			String chrom,
			int start,
			int end,
			Integer highlight)
			{
			this.sampleName=sampleName;
			this.bamName=bamName;
			this.chrom=chrom;
			this.start=start;
			this.end=end;
			this.highlight=highlight;
			}
		
		@Override
		public void run()
			{
			
			
			try
				{
					try
						{
						String uri="http://srv-clc-02.irt.univ-nantes.prive:8080/biomachin/samples?sample="+
							URLEncoder.encode(this.sampleName,"UTF-8")+"&bam="+
							URLEncoder.encode(this.bamName,"UTF-8")+"&segment="+chrom+":"+start+"-"+end+
							"&highlight="+highlight
							;
						System.err.println(uri);
						
						
						this.img=ImageIO.read(new URL(uri));
						}
					catch(Throwable err)
						{
						System.err.println("checked ? export CATALINA_OPTS=\"-Djava.awt.headless=true\"");
						img=null;
						}
					if(BamViewNodeView.this.bamThread==this
							&& !interrupted())
							{
							SwingUtilities.invokeAndWait(new Runnable()
								{
								@Override
								public void run()
									{
									Icon icn=(Lookup.this.img==null?null:new ImageIcon(Lookup.this.img));
									BamViewNodeView.this.bamIconLabel.setIcon(icn);
									BamViewNodeView.this.bamIconLabel.setText("");
									Lookup.this.img=null;
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
							BamViewNodeView.this.bamIconLabel.setIcon(null);
							BamViewNodeView.this.bamIconLabel.setText("An error occured.");
							}
						});
					} 
				catch (Exception e) {
					System.err.println("checked ? export CATALINA_OPTS=\"-Djava.awt.headless=true\"");
					e.printStackTrace();
					}
				}
			}
		}
	
	public BamViewNodeView(BamViewNodeModel nodeModel)
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
		JPanel bot=new JPanel(new FlowLayout(FlowLayout.LEADING));
		right.add(bot,BorderLayout.SOUTH);
		countRowsLabel=new JTextField(20);
		countRowsLabel.setEditable(false);
		bot.add(countRowsLabel);
		
		JTabbedPane left=new JTabbedPane();
		
		
			{
			JPanel pane2=new JPanel(new BorderLayout());
			this.bamIconLabel=new JLabel();
			pane2.add(new JScrollPane(this.bamIconLabel));
			left.addTab("Bam",pane2);
			}
		
		
		JSplitPane split=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,right,left);
		JPanel pane=new JPanel(new BorderLayout(5,5));
		pane.add(split,BorderLayout.CENTER);
		setComponent(pane);
		}
	
	private void tableModelChanged()
		{
		updateSelection();
		countRowsLabel.setText("Rows: "+this.tableModel.getRowCount());
		}
	
	private void updateSelection()
		{
		System.err.println("A");
		if(chromColumn==-1) return;
		System.err.println("B");
		if(posColumn==-1) return;
		System.err.println("C");
		if(sampleColumn==-1) return;
		System.err.println("D");
		int n= this.table.getSelectedRow();
		if(n==-1) return;
		n= this.table.convertRowIndexToModel(n);
		if(n==-1) return;
		System.err.println("E");
		Object o1=this.tableModel.getValueAt(n,this.chromColumn);
		if(o1==null || !(o1 instanceof String)) return;
		System.err.println("F");
		Object o2=this.tableModel.getValueAt(n,this.posColumn);
		if(o2==null || !(o2 instanceof Integer)) return;
		Object o3=this.tableModel.getValueAt(n,this.sampleColumn);
		if(o3==null || !(o3 instanceof String)) return;
		String bam= this.sample2bam.get(o3);
		if(bam==null || bam.isEmpty()) return;
		
		
		
		this.bamIconLabel.setText(o1.toString()+":"+o2);
		int pos1=(Integer)o2;
		this.bamThread=new Lookup(o3.toString(),bam,o1.toString(),pos1-10,pos1+10,pos1);
		this.bamThread.start();
		}
	
	@Override
	protected void modelChanged()
		{
		disposeBameThread();
		System.err.println("model changed called");
		chromColumn=-1;
		posColumn=-1;
		sampleColumn=-1;
		sample2bam.clear();
		BufferedDataTable tables[]=nodeModel.getInternalTables();
		if(tables!=null && tables.length>0)
			{
			this.chromColumn=this.nodeModel.chromColumn;
			this.posColumn=this.nodeModel.posColumn;
			this.sampleColumn=this.nodeModel.sampleColumn;
			this.sample2bam.clear();
			this.sample2bam.putAll(this.nodeModel.sample2bam);
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
		disposeBameThread();
		this.tableModel.dispose();
		}
	
	
	
	

private synchronized void disposeBameThread()
	{
	if(bamThread!=null)
		{
		try { bamThread.interrupt();}
		catch(Exception err) {}
		bamThread=null;
		}
	}
	
	
	}

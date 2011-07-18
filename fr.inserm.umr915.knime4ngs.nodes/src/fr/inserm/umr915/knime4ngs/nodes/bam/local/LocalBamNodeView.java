package fr.inserm.umr915.knime4ngs.nodes.bam.local;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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

import net.sf.samtools.Cigar;
import net.sf.samtools.CigarElement;
import net.sf.samtools.SAMFileReader;
import net.sf.samtools.SAMFileReader.ValidationStringency;
import net.sf.samtools.SAMRecord;
import net.sf.samtools.util.CloseableIterator;

import org.knime.core.node.BufferedDataTable;


import fr.inserm.umr915.knime4ngs.corelib.knime.AbstractNodeView;
import fr.inserm.umr915.knime4ngs.corelib.knime.BufferedDataTableModel;

/**
 * 
 * LocalBamNodeView
 *
 */
public class LocalBamNodeView  extends AbstractNodeView<LocalBamNodeModel>
	{
	private int chromColumn=-1;
	private int posColumn=-1;
	private int sampleColumn=-1;
	private Map<String,File> sample2bam=new HashMap<String, File>();
	private LocalBamNodeModel nodeModel;
	private BufferedDataTableModel tableModel;
	private JTable table;
	private JLabel bamIconLabel;
	private JTextField countRowsLabel;
	private Lookup bamThread=null;
	private JTextField build=null;
	
	
	/**
	 * Thread
	 */
	private class Lookup extends Thread
		{
		BufferedImage img=null;
		//private String sampleName=null;
		private File bamFile=null;
		String chrom;
		int start;
		int end;
		Integer highlight;
		
		Lookup(
			//String sampleName,
			File bamFile,
			String chrom,
			int start,
			int end,
			Integer highlight)
			{
			//this.sampleName=sampleName;
			this.bamFile=bamFile;
			this.chrom=chrom;
			this.start=start;
			this.end=end;
			this.highlight=highlight;
			}
		
		private BufferedImage bamImage(
				File bamFile,
				String refSeq,
				int chromStart,
				int chromEnd,
				Integer highlight
				) throws Exception
				{
				//System.err.println("calling bamImage");
				if(refSeq==null  || refSeq.trim().isEmpty())
					{
					throw new IllegalArgumentException("bad chromosome");
					}
				if(chromStart<0 || chromStart>=chromEnd )
					{
					throw new IllegalArgumentException("bad chromStart:"+chromStart);
					}
				
				//System.err.println("2calling bamImage");
		
				
				

				SAMFileReader inputSam=null;
				List<SAMRecord> records=new ArrayList<SAMRecord>();
				CloseableIterator<SAMRecord> iter=null;
				
				
				try {
					inputSam=new SAMFileReader(bamFile);
					inputSam.setValidationStringency(ValidationStringency.SILENT);
					

					iter= inputSam.queryOverlapping(
						refSeq,
						chromStart,
						chromEnd
						);			
					
					while (iter.hasNext())
				     	{
						
						final SAMRecord samRecord =iter.next();
						if(samRecord.getReadUnmappedFlag()) continue;
						records.add(samRecord);
				     	}
					final int fontHeight=14;
					final String fontFamily="Courier";
					Font font=new Font(fontFamily, Font.PLAIN, fontHeight);
					BufferedImage img=new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
					Graphics g1=img.getGraphics();
					FontMetrics fm=g1.getFontMetrics(font);
					g1.dispose();
					
					final int marginy=2;
					int cx=fm.getMaxAdvance()+2;
					int cy=fm.getHeight()+marginy;
					
					img=new BufferedImage(
							cx*(chromEnd - chromStart),
							Math.max(cy,cy*(records.size())),
							BufferedImage.TYPE_INT_ARGB
							);
					
					int y=0;
					Graphics2D g=(Graphics2D)img.getGraphics();
					g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
					if(highlight!=null)
						{
						int x= (highlight-chromStart)*cx;
						g.setColor(Color.LIGHT_GRAY);
						g.fillRect(x, 1, cx, img.getHeight()-1);
						g.setColor(Color.RED);
						g.drawRect(x, 1, cx, img.getHeight()-1);
						}
					g.setColor(Color.BLACK);
					for(SAMRecord rec:records)
						{
						Cigar cigar=rec.getCigar();
						
						byte cigarBytes[]=rec.getReadBases();
						int byte_index=0;
						
						int pos=rec.getUnclippedStart()-chromStart;
						
						for(CigarElement elt:cigar.getCigarElements())
							{
							int cl=elt.getLength();
							switch(elt.getOperator())
								{
								case M:
									{
									while(cl>0)
									    {
										g.drawString(String.valueOf((char)cigarBytes[byte_index]), pos*cx,y+ fm.getHeight());
										
										byte_index++;
									    cl--;
									    ++pos;
									    }
									break;
									}
								case H:break; /* ignore */
							    case S:break; /* ignore */
							    case P: //...
							    case N://....
							    case D:
									{
									/* Spans positions, No Coverage */
										while(cl>0)
									    {
										pos++;
										cl--;
									    }
									break;
									}
							    case I: /* cursor not moved on reference */ ;
							    	{
							    	g.setColor(Color.GREEN);
							    	g.drawRect(pos*cx-2, y, 2,cy);
							    	g.setColor(Color.BLACK);
							    	while(cl>0)
									    {
							    		byte_index++;
										cl--;
									    }
							    	break;
							    	}
							    default: throw new IllegalArgumentException(elt.getOperator().toString());

								}
							}
						//System.err.println( " "+rec.getCigarString()+" "+rec.getAlignmentStart()+" "+chromStart+" "+y);
						y+=cy;
						}
					
					g.setColor(Color.BLACK);
					g.drawRect(0, 0, img.getWidth()-1, img.getHeight()-1);
					g.dispose();
					
					return img;
					}
				catch (Exception err)
					{
					err.printStackTrace();
					throw err;
					}
				finally
					{
					if(iter!=null) try {iter.close();} catch(Exception err){}
				    if(inputSam!=null) try {inputSam.close();} catch(Exception err){}
				    }
				}

		
		
		@Override
		public void run()
			{

			try
				{
					try
						{
						
						this.img= bamImage(
							this.bamFile,
							this.chrom,
							this.start,
							this.end,
							this.highlight
								
							);
						}
					catch(Throwable err)
						{
						img=null;
						}
					if(LocalBamNodeView.this.bamThread==this
							&& !interrupted())
							{
							SwingUtilities.invokeAndWait(new Runnable()
								{
								@Override
								public void run()
									{
									Icon icn=(Lookup.this.img==null?null:new ImageIcon(Lookup.this.img));
									LocalBamNodeView.this.bamIconLabel.setIcon(icn);
									LocalBamNodeView.this.bamIconLabel.setText("");
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
							LocalBamNodeView.this.bamIconLabel.setIcon(null);
							LocalBamNodeView.this.bamIconLabel.setText("An error occured.");
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
	
	public LocalBamNodeView(LocalBamNodeModel nodeModel)
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
			JPanel pane2=new JPanel(new BorderLayout(5,5));
			this.bamIconLabel=new JLabel();
			pane2.add(new JScrollPane(this.bamIconLabel),BorderLayout.CENTER);
			bot=new JPanel(new FlowLayout(FlowLayout.LEADING));
			pane2.add(bot,BorderLayout.SOUTH);
			bot.add(new JLabel("Build:",JTextField.RIGHT));
			bot.add(this.build=new JTextField("hg19",8));
			bot.add(new JButton(new AbstractAction("Open Browser")
				{
				private static final long serialVersionUID = 1L;

				@Override
				public void actionPerformed(ActionEvent event)
					{
					openBrowser();
					}
				}));
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
	
	private void openBrowser()
		{

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
		
		String assembly=this.build.getText().trim();
		if(assembly.isEmpty()) return;
		
		java.awt.Desktop desktop = java.awt.Desktop.getDesktop();
		if(desktop==null || !desktop.isSupported(java.awt.Desktop.Action.BROWSE)) return;
		
		try
			{
			java.net.URI uri = new java.net.URI(
				"http://genome.ucsc.edu/cgi-bin/hgTracks?org=Human&db="+
				URLEncoder.encode(assembly, "UTF-8")+"&position="+
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
	
	private void updateSelection()
		{
		if(chromColumn==-1) return;
		if(posColumn==-1) return;
		if(sampleColumn==-1) return;
		int n= this.table.getSelectedRow();
		if(n==-1) return;
		n= this.table.convertRowIndexToModel(n);
		if(n==-1) return;
		Object o1=this.tableModel.getValueAt(n,this.chromColumn);
		if(o1==null || !(o1 instanceof String)) return;
		Object o2=this.tableModel.getValueAt(n,this.posColumn);
		if(o2==null || !(o2 instanceof Integer)) return;
		Object o3=this.tableModel.getValueAt(n,this.sampleColumn);
		if(o3==null || !(o3 instanceof String)) return;
		File bam= this.sample2bam.get(o3);
		if(bam==null) return;
		
		
		
		this.bamIconLabel.setText(o1.toString()+":"+o2);
		int pos1=(Integer)o2;
		this.bamThread=new Lookup(
				//o3.toString(),
				bam,o1.toString(),pos1-10,pos1+10,pos1);
		this.bamThread.start();
		}
	
	@Override
	protected void modelChanged()
		{
		disposeBamThread();
		//System.err.println("model changed called");
		chromColumn=-1;
		posColumn=-1;
		sampleColumn=-1;
		sample2bam.clear();
		
		
		BufferedDataTable tables[]=nodeModel.getInternalTables();
		
		Map<String,File> map=null;
		if(tables!=null && tables.length>1)
			{
			try
				{
				map=this.nodeModel.fillMap(tables[1]);
				}
			catch(Throwable err)
				{
				err.printStackTrace();
				tables=null;
				}
			}
		
		if(tables!=null && tables.length>1)
			{
			this.chromColumn=this.nodeModel.chromColumn;
			this.posColumn=this.nodeModel.posColumn;
			this.sampleColumn=this.nodeModel.sampleColumn;
			this.sample2bam.clear();
			this.sample2bam.putAll(map);
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
		disposeBamThread();
		this.tableModel.dispose();
		}
	
	
	
	

private synchronized void disposeBamThread()
	{
	if(bamThread!=null)
		{
		try { bamThread.interrupt();}
		catch(Exception err) {}
		bamThread=null;
		}
	}
	
	
	}

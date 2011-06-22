package fr.inserm.umr915.knime4ngs.nodes.vcf.ucsc.kgview;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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

import org.knime.core.data.DataCell;
import org.knime.core.data.DataRow;
import org.knime.core.data.container.CloseableRowIterator;
import org.knime.core.data.def.IntCell;
import org.knime.core.data.def.StringCell;
import org.knime.core.node.BufferedDataTable;


import fr.inserm.umr915.knime4ngs.corelib.bio.ucsc.KnownGene;
import fr.inserm.umr915.knime4ngs.corelib.knime.AbstractNodeView;
import fr.inserm.umr915.knime4ngs.corelib.knime.BufferedDataTableModel;

/**
 * 
 * KgViewNodeView
 *
 */
public class KgViewNodeView  extends AbstractNodeView<KgViewNodeModel>
	{
	private int chromStart=0;
	private int chromEnd=0;
	private static final int LEFT_MARGIN=300;
	private static final int IMAGE_WIDTH=500;
	private static final int GENE_HEIGHT=50;
	private int chromColumn=-1;
	private int posColumn=-1;
	private KgViewNodeModel nodeModel;
	private BufferedDataTableModel tableModel;
	private JTable table;
	private JLabel kgLabel;
	private JTextField countRowsLabel;
	private Lookup kgThread=null;
	private Connection con=null;
	
	/**
	 * Thread
	 */
	private class Lookup extends Thread
		{
		String chrom;
		int pos0;

		Lookup(String chrom,int pos0)
			{
			this.chrom=chrom;
			this.pos0=pos0;
			}
		
		@Override
		public void run()
			{
			try
				{
				try
					{
					Thread.sleep(5*1000);
					}
				catch(Throwable err)
					{
					return;
					}
				if(KgViewNodeView.this.kgThread==this
						&& !interrupted())
						{
						SwingUtilities.invokeAndWait(new Runnable()
							{
							@Override
							public void run()
								{
								try
									{
									paintGene(Lookup.this.chrom,Lookup.this.pos0);
									}
								catch(Exception err)
									{
									err.printStackTrace();
									}
								}
							});
						}
				}
			catch(Exception err)
				{
				err.printStackTrace();
				}
			}
		}
	
	public KgViewNodeView(KgViewNodeModel nodeModel)
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
			this.kgLabel=new JLabel();
			pane2.add(new JScrollPane(this.kgLabel));
			left.addTab("KnownGene",pane2);
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
		disposeKgThread();
		if(chromColumn==-1) return;
		if(posColumn==-1) return;
		int n= this.table.getSelectedRow();
		if(n==-1) return;
		n= this.table.convertRowIndexToModel(n);
		if(n==-1) return;
		Object o1=this.tableModel.getValueAt(n,this.chromColumn);
		if(o1==null || !(o1 instanceof String)) return;
		Object o2=this.tableModel.getValueAt(n,this.posColumn);
		if(o2==null || !(o2 instanceof Integer)) return;
		
		
		this.kgLabel.setText(o1.toString()+":"+o2);
		int pos1=(Integer)o2;
		this.kgThread=new Lookup( o1.toString(),pos1-1);
		this.kgThread.start();
		}
	
	@Override
	protected void modelChanged()
		{
		disposeKgThread();
		//System.err.println("model changed called");
		chromColumn=-1;
		posColumn=-1;
		BufferedDataTable tables[]=nodeModel.getInternalTables();
		if(tables!=null && tables.length>0)
			{
			this.chromColumn=this.nodeModel.chromColumn;
			this.posColumn=this.nodeModel.posColumn;
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
		disposeKgThread();
		this.tableModel.dispose();
		if(con!=null)
			{
			try{con.close();} catch(Exception err){}
			con=null;
			}
		}
	
	
	
	

private synchronized void disposeKgThread()
	{
	if(this.kgThread!=null)
		{
		try { this.kgThread.interrupt();}
		catch(Exception err) {}
		this.kgThread=null;
		}
	}
	

private void paintGene(String chrom,int pos0) throws Exception
	{
	if(nodeModel==null) return;
	BufferedDataTable tables[]=nodeModel.getInternalTables();
	if(tables==null || tables.length==0) return;
	
	if(chrom==null  || chrom.trim().isEmpty())
		{
		throw new IllegalArgumentException("bad chromosome");
		}
	if(pos0<0)
		{
		throw new IllegalArgumentException("bad position:"+pos0);
		}
	
	
	
	
	PreparedStatement pstmt=null;
	ResultSet resultset=null;
	CloseableRowIterator iter=null;
	try
		{
		if(this.con==null)
			{
			this.con=nodeModel.createConnection();
			}

		List<KnownGene> genes=new ArrayList<KnownGene>();
		pstmt=con.prepareStatement(
				"select * from knownGene where chrom=? and NOT(txEnd<=? or txStart>?)");
		pstmt.setString(1,chrom);
		pstmt.setInt(2,pos0);
		pstmt.setInt(3,pos0);
		resultset=pstmt.executeQuery();
		while(resultset.next())
			{
			genes.add(KnownGene.parse(resultset));
			}
		resultset.close();
		resultset=null;
		pstmt.close();
		pstmt=null;
		if(genes.isEmpty())
			{
			this.kgLabel.setIcon(null);
			return;
			}
		
		
			
		this.chromStart=Integer.MAX_VALUE;
		this.chromEnd=0;
		for(KnownGene kg:genes)
			{
			chromStart=Math.min(chromStart,kg.getTxStart());
			chromEnd=Math.max(chromEnd,kg.getTxEnd());
			}
			
		chromStart-=50;
		chromEnd+=50;
		
		
		final int sampleCount=0;//not implemented
		BufferedImage img= new BufferedImage(
				(IMAGE_WIDTH+LEFT_MARGIN),
				GENE_HEIGHT*(genes.size()+sampleCount+1),
				BufferedImage.TYPE_INT_RGB);
		Graphics2D g=img.createGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, img.getWidth(), img.getHeight());
			/*
			for(int i=0;i< frame.getSampleCount();++i)
				{
				int y= (genes.size()+i)*GENE_HEIGHT;
				g.setColor(Color.LIGHT_GRAY);
				g.drawLine(0, y, IMAGE_WIDTH+LEFT_MARGIN, y);
				g.drawString(frame.getSamples().get(i).getName(),1, y+GENE_HEIGHT-GENE_HEIGHT/2);
				}*/
		
		iter=tables[0].iterator();
		while(iter.hasNext())
			{
			DataRow row=iter.next();
			DataCell c=row.getCell(this.chromColumn);
			if(c.isMissing()) continue;
			String chr=StringCell.class.cast(c).getStringValue();
			int i=chr.compareTo(chrom);
			if(i<0) continue;
			if(i>0) break;
			c=row.getCell(this.posColumn);
			if(c.isMissing()) continue;
			int p0=IntCell.class.cast(c).getIntValue()-1;
			if(p0< chromStart) continue;
			if(p0>= chromEnd) break;
			
			g.setColor(Color.GREEN);
			Rectangle rect= new Rectangle(
					chrom2pixel(p0),
					0,
					1,
					img.getHeight()
					);
			g.draw(rect);
			}
		iter.close();
		
			
		int y=0;
		for(KnownGene kg:genes)
			{
			int exonHeight=(GENE_HEIGHT/2)-5;
			int midy=y+GENE_HEIGHT/2;
			g.setColor(Color.BLACK);
			g.drawString(kg.getName(),1, y+GENE_HEIGHT-1);
			g.drawLine(chrom2pixel(kg.getTxStart()), midy, chrom2pixel(kg.getTxEnd()), midy);
			g.setColor(Color.BLUE);
			g.fillRect(
					chrom2pixel(kg.getCdsStart()),
					midy-3,
					chrom2pixel(kg.getCdsEnd())-chrom2pixel(kg.getCdsStart()),
					7);
			for(int i=0;i< kg.getExonCount();++i)
				{
				Rectangle rect= new Rectangle(
					chrom2pixel(kg.getExonStarts()[i]),
					midy-exonHeight/2,
					chrom2pixel(kg.getExonEnds()[i])-chrom2pixel(kg.getExonStarts()[i]),
					exonHeight
					);
				if(rect.width<=0) rect.width=1;
				g.setColor(Color.LIGHT_GRAY);
				g.fill(rect);
				g.setColor(Color.DARK_GRAY);
				g.draw(rect);
				}
			y+=GENE_HEIGHT;
			}
			
			for(int i=0;i< sampleCount;++i)
				{
				y= (genes.size()+i)*GENE_HEIGHT;
				g.setColor(Color.LIGHT_GRAY);
				g.drawLine(0, y, IMAGE_WIDTH+LEFT_MARGIN, y);
				}
			
			g.setColor(Color.RED);
			g.drawRect(
				chrom2pixel(pos0),
				0,
				1,
				img.getHeight()
				);
			
			g.setColor(Color.DARK_GRAY);
			g.drawRect(0, 0, img.getWidth()-1, img.getHeight()-1);
			g.dispose();
			this.kgLabel.setIcon(new ImageIcon(img));
			}
		catch (Exception err)
			{
			err.printStackTrace();
			throw err;
			}
		finally
			{
			nodeModel.safeClose(resultset);
			nodeModel.safeClose(pstmt);
			if(iter!=null) try { iter.close();} catch(Exception e) {}
			iter=null;
		    }
	
		}

private int chrom2pixel(int pos)
	{
	return LEFT_MARGIN+(int)((((double)(pos-this.chromStart)/(double)(this.chromEnd-this.chromStart))*IMAGE_WIDTH));
	}
}
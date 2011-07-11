package fr.inserm.umr915.knime4ngs.nodes.vcf.genscan;

//import java.awt.AlphaComposite;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JScrollPane;



import org.knime.core.data.DataCell;
import org.knime.core.data.DataRow;
import org.knime.core.data.container.CloseableRowIterator;
import org.knime.core.data.def.BooleanCell;
import org.knime.core.data.def.DoubleCell;
import org.knime.core.data.def.IntCell;
import org.knime.core.data.def.LongCell;
import org.knime.core.data.def.StringCell;
import org.knime.core.node.BufferedDataTable;
import org.knime.core.node.BufferedDataTableHolder;
import org.knime.core.node.NodeModel;
import org.knime.core.node.NodeView;



import fr.inserm.umr915.knime4ngs.corelib.bio.HasPositionColumns;
import fr.inserm.umr915.knime4ngs.corelib.bio.HasValueColumn;


/**
 * 
 * LocalBamNodeView
 *
 */
public class GenscanNodeView<T extends NodeModel>  extends NodeView<T>
	{
	private JLabel label;
	private T nodeModel;

	
	public GenscanNodeView(T model)
		{
		super(model);
		this.nodeModel=model;
		this.label=new JLabel();
		setComponent(new JScrollPane(label));
		}
	
	
	private BufferedImage createImage()
		{
		if( this.nodeModel==null || !(this.nodeModel instanceof HasPositionColumns) || !(this.nodeModel instanceof BufferedDataTableHolder)) return null;
		
		BufferedDataTable tables[]=BufferedDataTableHolder.class.cast(this.nodeModel).getInternalTables();
		if(tables==null || tables.length==0 )
			{
			return null;
			}
		
		int valueColumn=-1;
		if(this.nodeModel instanceof HasValueColumn)
			{
			valueColumn= HasValueColumn.class.cast(this.nodeModel).getValueColumn();
			}

		int chromCol= HasPositionColumns.class.cast(this.nodeModel).getChromosomeColumn();
		if(chromCol==-1) return null;
		int startCol= HasPositionColumns.class.cast(this.nodeModel).getChromStartColumnIndex();
		if(startCol==-1) return null;
		int endCol= HasPositionColumns.class.cast(this.nodeModel).getChromEndColumnIndex();
		if(endCol==-1) endCol=startCol;
		Dimension screen=Toolkit.getDefaultToolkit().getScreenSize();
		screen.width-=200;
		screen.height-=200;
		
		double countUnderPixel[]=new double[screen.width-10];
		double minValues[]=new double[countUnderPixel.length];
		double maxValues[]=new double[countUnderPixel.length];
		double sumValues[]=new double[countUnderPixel.length];
		Arrays.fill(countUnderPixel, 0);
		
		ChromInfo chromInfo=ChromInfo.getDefaultInstance();
		Map<String,Color> chrom2color=new HashMap<String,Color>();
		for(String k:chromInfo.getChromosomes())
			{
			chrom2color.put(k, chrom2color.size()%2==0?Color.GREEN:Color.RED);
			}
		
		BufferedImage img=new BufferedImage(screen.width, screen.height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g=(Graphics2D)img.getGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, screen.width, screen.height);
		int leftMargin=(screen.width-countUnderPixel.length)/2;
		int heigth=screen.height-10;
		int topMargin=5;

		
		
		
		//draw chromosomes
		int kIndex=0;
		for(String k:chromInfo.getChromosomes())
			{
			++kIndex;
			int x0=(int)chromInfo.positionToPixel(k, 0, countUnderPixel.length);
			int x1=(int)chromInfo.positionToPixel(k, chromInfo.getChromosomeLength(k), countUnderPixel.length);
			g.setColor(kIndex%2==0?Color.WHITE:new Color(240,240,240));
			g.fillRect(leftMargin+x0, topMargin,(x1-x0), heigth);
			}
		
		
		Composite old=g.getComposite();
		
		//draw chromosomes
		CloseableRowIterator iter=null;
		Double minVal=null;
		Double maxVal=null;
		try {
			BufferedDataTable table=tables[0];
			
			/* there is a value */
			if(valueColumn!=-1)
				{
				
				/* get min max */
				iter=table.iterator();
				while(iter.hasNext())
					{
					DataRow row=iter.next();
					DataCell c=row.getCell(chromCol);
					if(c.isMissing()) continue;
					String chromosome =StringCell.class.cast(c).getStringValue();
					if(!chrom2color.containsKey(chromosome)) continue;
					c=row.getCell(startCol);
					if(c.isMissing()) continue;
					c=row.getCell(endCol);
					if(c.isMissing()) continue;
					
					
					c=row.getCell(valueColumn);
					if(c.isMissing()) continue;
					double currVal=1;
					if(c.getType().equals(LongCell.TYPE))
	        			{
	        			currVal= LongCell.class.cast(c).getLongValue();
	        			}
	        		else if(c.getType().equals(IntCell.TYPE))
	        			{
	        			currVal= IntCell.class.cast(c).getIntValue();
	        			}
	        		else if(c.getType().equals(BooleanCell.TYPE))
	        			{
	        			currVal= (BooleanCell.class.cast(c).getBooleanValue()?1:0);
	        			}
	        		else if(c.getType().equals(DoubleCell.TYPE))
	        			{
	        			currVal= DoubleCell.class.cast(c).getDoubleValue();
	        			}
					minVal=(minVal==null?currVal:Math.min(currVal, minVal));
					maxVal=(maxVal==null?currVal:Math.max(currVal, maxVal));
					
					
					}
				iter.close();
				
				
				
				/* display data */
				iter=table.iterator();
				while(iter.hasNext())
					{
					DataRow row=iter.next();
				
					DataCell c=row.getCell(chromCol);
					if(c.isMissing()) continue;
					String chromosome =StringCell.class.cast(c).getStringValue();
					if(!chrom2color.containsKey(chromosome)) continue;
					c=row.getCell(startCol);
					if(c.isMissing()) continue;
					int chromStart =IntCell.class.cast(c).getIntValue();
					c=row.getCell(endCol);
					if(c.isMissing()) continue;
					int chromEnd =IntCell.class.cast(c).getIntValue();
					if(startCol==endCol) chromEnd++;
					c=row.getCell(valueColumn);
					if(c.isMissing()) continue;
					double currVal=1;
					if(c.getType().equals(LongCell.TYPE))
	        			{
	        			currVal= LongCell.class.cast(c).getLongValue();
	        			}
	        		else if(c.getType().equals(IntCell.TYPE))
	        			{
	        			currVal= IntCell.class.cast(c).getIntValue();
	        			}
	        		else if(c.getType().equals(BooleanCell.TYPE))
	        			{
	        			currVal= (BooleanCell.class.cast(c).getBooleanValue()?1:0);
	        			}
	        		else if(c.getType().equals(DoubleCell.TYPE))
	        			{
	        			currVal= DoubleCell.class.cast(c).getDoubleValue();
	        			}
					int x0= (int)chromInfo.positionToPixel(chromosome, chromStart,countUnderPixel.length);
					int x1= (int)chromInfo.positionToPixel(chromosome, chromEnd,countUnderPixel.length);
					if(x1==x0) ++x1;
					
					
					double y;
					if(maxVal!=minVal)
						{
						y=topMargin+ heigth-((currVal-minVal)/(maxVal-minVal))*heigth;
						}
					else
						{
						y=topMargin+heigth/2;
						}
					g.setColor(chrom2color.get(chromosome));
					g.fillRect(leftMargin+x0,(int)y-5,(x1-x0),10);
					while(x0<x1)
						{
						countUnderPixel[x0]++;
						sumValues [x0]+=valueColumn;
						minValues[x0]=(countUnderPixel[x0]==1?currVal:Math.min(minValues[x0], currVal));
						maxValues[x0]=(countUnderPixel[x0]==1?currVal:Math.max(maxValues[x0], currVal));
						++x0;
						}
					}
				g.setColor(Color.BLACK);
				
				iter.close();
				}
			else
				{
				maxVal=0.0;
				iter=table.iterator();
				while(iter.hasNext())
					{
					DataRow row=iter.next();
				
					DataCell c=row.getCell(chromCol);
					if(c.isMissing()) continue;
					String chromosome =StringCell.class.cast(c).getStringValue();
					if(!chrom2color.containsKey(chromosome)) continue;
					c=row.getCell(startCol);
					if(c.isMissing()) continue;
					int chromStart =IntCell.class.cast(c).getIntValue();
					c=row.getCell(endCol);
					if(c.isMissing()) continue;
					int chromEnd =IntCell.class.cast(c).getIntValue();
					if(startCol==endCol) chromEnd++;
					
					for(int i=chromStart;i<chromEnd;++i)
						{
						int index= (int)chromInfo.positionToPixel(chromosome, i, countUnderPixel.length);
						countUnderPixel[index]++;
						maxVal=Math.max(countUnderPixel[index], maxVal);
						
						
						}
					}
				iter.close();
				
				
				
				final int radius=5;
				for(String k:chromInfo.getChromosomes())
					{
					g.setColor(chrom2color.get(k));
					int x0=(int)chromInfo.positionToPixel(k, 0, countUnderPixel.length);
					int x1=(int)chromInfo.positionToPixel(k, chromInfo.getChromosomeLength(k), countUnderPixel.length);
					while(x0<x1)
						{
						double v=countUnderPixel[x0];
						double y;
						if(maxVal!=minVal)
							{
							y=topMargin+ heigth-(v/(maxVal))*heigth;
							}
						else
							{
							y=topMargin+heigth/2;
							}
						g.fillOval(leftMargin+x0-radius,(int)y-radius,radius*2,radius*2);
						
						sumValues[x0]++;
						minValues[x0]=(countUnderPixel[x0]==1?v:Math.min(minValues[x0], v));
						maxValues[x0]=(countUnderPixel[x0]==1?v:Math.max(maxValues[x0], v));
						
						++x0;
						}
					}
				}
			} 
		catch (Exception e)
			{
			e.printStackTrace();
			}
		finally
			{
			if(iter!=null) iter.close();
			}
		
		
		
		
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.4f));
		
		for(int i=0;i< countUnderPixel.length;++i)
			{
			if(countUnderPixel[i]==0) continue;
			int miny=0,maxy=0,midy=0;
			for(int side=0;side<3;++side)
				{
				double val=0;
				switch(side)
					{
					case 0:val=minValues[i];break;
					case 1:val=maxValues[i];break;
					case 2:val=sumValues[i]/countUnderPixel[i];break;
					}
				
				int y=(int)(topMargin+ heigth-(val/(maxVal))*heigth);
				switch(side)
					{
					case 0:miny=y;break;
					case 1:maxy=y;break;
					case 2:midy=y;break;
					}
				}
			final int radius=5;
			g.setColor(Color.BLACK);
			g.drawLine(leftMargin+i-radius,miny,leftMargin+i+radius,miny);
			g.drawLine(leftMargin+i-radius,maxy,leftMargin+i+radius,maxy);
			g.drawLine(leftMargin+i-radius,midy,leftMargin+i+radius,midy);
			g.setColor(Color.LIGHT_GRAY);
			g.drawLine(leftMargin+i,miny,leftMargin+i,maxy);
			}
			
		g.setComposite(old);
		
		
		//draw chromosomes frame
		for(String k:chromInfo.getChromosomes())
			{
			int x0=(int)chromInfo.positionToPixel(k, 0, countUnderPixel.length);
			int x1=(int)chromInfo.positionToPixel(k, chromInfo.getChromosomeLength(k), countUnderPixel.length);
			g.setColor(Color.BLACK);
			g.drawRect(leftMargin+x0, topMargin,(x1-x0), heigth);
			g.drawString(k,leftMargin+x0+2,topMargin+12);
			}
		
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, screen.width-1, screen.height-1);
		
		g.dispose();
		return img;
		}
	
	@Override
	protected void modelChanged()
		{
		try
		{
		BufferedImage img=createImage();
		ImageIcon icn=img==null?null:new ImageIcon(img);
		this.label.setIcon(icn);
		} catch(Exception err)
		{
			err.printStackTrace();
		}
		}
	
	@Override
	protected void onOpen()
		{
		if(this.label.getIcon()==null)
			{
			modelChanged();
			}
		}
	
	
	@Override
	protected void onClose()
		{
		this.label.setIcon(null);
		this.nodeModel=null;
		}

	
	
	}

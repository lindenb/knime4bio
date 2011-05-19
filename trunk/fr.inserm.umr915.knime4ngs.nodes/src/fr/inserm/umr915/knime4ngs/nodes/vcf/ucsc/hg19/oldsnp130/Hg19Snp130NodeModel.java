package fr.inserm.umr915.knime4ngs.nodes.vcf.ucsc.hg19.oldsnp130;

import java.util.zip.GZIPInputStream;
import java.io.IOException;
import java.io.File;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Set;
import java.util.regex.Pattern;
import java.net.URL;
import org.knime.base.data.append.column.AppendedColumnRow;
import org.knime.base.data.sort.SortedTable;
import org.knime.core.data.DataCell;
import org.knime.core.data.DataType;
import org.knime.core.data.DataColumnSpec;
import org.knime.core.data.DataColumnSpecCreator;
import org.knime.core.data.DataRow;
import org.knime.core.data.DataTable;
import org.knime.core.data.DataTableSpec;
import org.knime.core.data.RowIterator;
import org.knime.core.data.RowKey;
import org.knime.core.data.container.CloseableRowIterator;
import org.knime.core.data.def.IntCell;
import org.knime.core.data.def.StringCell;
import org.knime.core.node.BufferedDataContainer;
import org.knime.core.node.BufferedDataTable;
import org.knime.core.node.ExecutionContext;
import org.knime.core.node.InvalidSettingsException;
import org.knime.core.node.defaultnodesettings.SettingsModel;
import org.knime.core.node.defaultnodesettings.SettingsModelBoolean;
import org.knime.core.node.defaultnodesettings.SettingsModelString;
import org.knime.core.node.defaultnodesettings.SettingsModelColumnName;
import org.knime.core.node.NodeLogger;
import org.knime.core.node.NodeModel;
import org.knime.core.node.NodeSettingsRO;
import org.knime.core.node.NodeSettingsWO;
import org.knime.core.node.defaultnodesettings.SettingsModel;
import org.knime.core.node.port.PortType;
import org.knime.core.node.CanceledExecutionException;
import org.knime.core.node.ExecutionContext;
import org.knime.core.node.ExecutionMonitor;

/**
 *  Hg19Snp130NodeModel
 *    
 */
public class Hg19Snp130NodeModel
	extends NodeModel
	{
	private final Pattern delimiter=Pattern.compile("[\t]");
	/** property for table uri */
	public static final String URI_PROPERTY="hg19Snp130.uri";
	/** property for table uri */
	public static final String DEFAULT_URI_PROPERTY="http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/snp131.txt.gz";
	/** settings for table uri */
	private final SettingsModelString m_uri=new SettingsModelString(
			URI_PROPERTY,
			DEFAULT_URI_PROPERTY
			);
	
	/** property for table join */
	public static final String JOIN_PROPERTY="hg19Snp130.one.per.row";
	/** default property for table join */
	public static final boolean DEFAULT_JOIN_PROPERTY=true;
	/** settings for table uri */
	private final SettingsModelBoolean m_join=new SettingsModelBoolean(
			JOIN_PROPERTY,
			DEFAULT_JOIN_PROPERTY
			);	
	
	/** VCF chromosome column */
	static final String CHROM_COL_PROPERTY="vcf.chrom.col";
	static final String DEFAULT_CHROM_COL="CHROM";
	private final SettingsModelColumnName m_chromCol =  new SettingsModelColumnName(CHROM_COL_PROPERTY,DEFAULT_CHROM_COL);
	
	/** VCF position column */
	static final String CHROM_POS1_PROPERTY="vcf.pos1.col";
	static final String DEFAULT_POS1_COL="POS";
	private final SettingsModelColumnName m_pos1Col =  new SettingsModelColumnName(CHROM_POS1_PROPERTY,DEFAULT_POS1_COL);
		
	
	/** property for chrom */
	public static final String SHOW_CHROM_PROPERTY="hg19Snp130.use.chrom";
	/** property for chrom */
	public static final boolean DEFAULT_SHOW_CHROM_PROPERTY=true;
	/** settings for chrom */
	private final SettingsModelBoolean m_show_chrom=new SettingsModelBoolean(
			SHOW_CHROM_PROPERTY,
			DEFAULT_SHOW_CHROM_PROPERTY
			);
	
	/** property for chromStart */
	public static final String SHOW_CHROMSTART_PROPERTY="hg19Snp130.use.chromStart";
	/** property for chromStart */
	public static final boolean DEFAULT_SHOW_CHROMSTART_PROPERTY=true;
	/** settings for chromStart */
	private final SettingsModelBoolean m_show_chromStart=new SettingsModelBoolean(
			SHOW_CHROMSTART_PROPERTY,
			DEFAULT_SHOW_CHROMSTART_PROPERTY
			);
	
	/** property for chromEnd */
	public static final String SHOW_CHROMEND_PROPERTY="hg19Snp130.use.chromEnd";
	/** property for chromEnd */
	public static final boolean DEFAULT_SHOW_CHROMEND_PROPERTY=true;
	/** settings for chromEnd */
	private final SettingsModelBoolean m_show_chromEnd=new SettingsModelBoolean(
			SHOW_CHROMEND_PROPERTY,
			DEFAULT_SHOW_CHROMEND_PROPERTY
			);
	
	/** property for name */
	public static final String SHOW_NAME_PROPERTY="hg19Snp130.use.name";
	/** property for name */
	public static final boolean DEFAULT_SHOW_NAME_PROPERTY=true;
	/** settings for name */
	private final SettingsModelBoolean m_show_name=new SettingsModelBoolean(
			SHOW_NAME_PROPERTY,
			DEFAULT_SHOW_NAME_PROPERTY
			);
	
	
	
	
	private static class Position
		implements Comparable<Position>
		{
		String chrom;
		int position0;
		Position(String chrom,int position0)
			{
			this.chrom=chrom;
			this.position0=position0;
			}
			
		public String getChromosome()
	 		{
	 		return chrom;
	 		}	
	 	public int getPosition0()
	 		{
	 		return position0;
	 		}
			
		@Override
		public int compareTo(Position pos)
			{
			int i=chrom.compareTo(pos.chrom);
			if(i!=0) return i;
			i= position0-pos.position0;
			return i;
			}
		@Override
		public String toString() {
			return getChromosome()+":"+getPosition0();
			}
		}
	
	private static class PositionKSorter
		implements java.util.Comparator<DataRow>
		{
		int chromCol=-1;
		int posCol=-1;
		
		PositionKSorter(int chromCol,int posCol)
			{
			this.chromCol=chromCol;
			this.posCol=posCol;
			}
		
		public Position make(DataRow r)
			{
			return new Position(
				StringCell.class.cast(r.getCell(chromCol)).getStringValue(),
				IntCell.class.cast(r.getCell(posCol)).getIntValue()-1
				);
			}
		@Override
		public int compare(DataRow r1,DataRow r2)
			{
			return make(r1).compareTo(make(r2));
			}
		}
	
	private static class Record
	 	{
	 	/** chrom */
		String	chrom;
	 	/** chromStart */
		int	chromStart;
	 	/** chromEnd */
		int	chromEnd;
	 	/** name */
		String	name;
	 	
	 	public String getChromosome()
	 		{
	 		return chrom;
	 		}
	 	
		public int getStart()
			{
			return chromStart;
			}
		
		public int getEnd()
			{
			return chromEnd;
			}
		@Override
		public String toString() {
			return getChromosome()+":"+getStart()+"-"+getEnd();
			}
	 	}
	 
	
	
	
	public Hg19Snp130NodeModel()
		{
		super(1, 2);
		}
	
	private Record parseRecord(String line)
		{
		if(line==null) return null;
		String tokens[]=this.delimiter.split(line);
		Record rec=new Record();
	 	rec.chrom=(tokens[1]);
	 	rec.chromStart=Integer.parseInt(tokens[2]);
	 	rec.chromEnd=Integer.parseInt(tokens[3]);
	 	rec.name=(tokens[4]);
		return rec;
		}
	
	public DataCell[] toDataCells(Record rec)
			{
			List<DataCell> cells=new ArrayList<DataCell>();
		        
			if(m_show_chrom.getBooleanValue())
			 	{
				    cells.add(new StringCell(rec.chrom));
			 	}
			if(m_show_chromStart.getBooleanValue())
			 	{
				    cells.add(new IntCell(rec.chromStart));
			 	}
			if(m_show_chromEnd.getBooleanValue())
			 	{
				    cells.add(new IntCell(rec.chromEnd));
			 	}
			if(m_show_name.getBooleanValue())
			 	{
				    cells.add(new StringCell(rec.name));
			 	}
		    return cells.toArray(new DataCell[cells.size()]);
		    }
	
	
	private DataTableSpec createDataTableSpec()
    	{
    	List<DataColumnSpec> specs=new ArrayList<DataColumnSpec>();
		 if(m_show_chrom.getBooleanValue())
		 	{
		 	specs.add(new DataColumnSpecCreator("hg19Snp130.chrom",StringCell.TYPE).createSpec());
		 	}
		 if(m_show_chromStart.getBooleanValue())
		 	{
		 	specs.add(new DataColumnSpecCreator("hg19Snp130.chromStart",IntCell.TYPE).createSpec());
		 	}
		 if(m_show_chromEnd.getBooleanValue())
		 	{
		 	specs.add(new DataColumnSpecCreator("hg19Snp130.chromEnd",IntCell.TYPE).createSpec());
		 	}
		 if(m_show_name.getBooleanValue())
		 	{
		 	specs.add(new DataColumnSpecCreator("hg19Snp130.name",StringCell.TYPE).createSpec());
		 	}
    	return new DataTableSpec(specs.toArray(new DataColumnSpec[specs.size()]));
    	}
	
	private DataTableSpec createDataTableSpec(DataTableSpec in)
		{
		return new DataTableSpec(in,this.createDataTableSpec());
		}
	

	
	protected InputStream openStream(String uri) throws IOException
		{
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
		return new BufferedReader(new InputStreamReader(openStream(uri)));
		}
	
	
	@Override
    protected BufferedDataTable[] execute(
    		final BufferedDataTable[] inData,
            final ExecutionContext exec
            ) throws Exception
            {
			BufferedDataContainer container1=null;
			BufferedDataContainer container2=null;
			BufferedReader in=null;
			try
		    	{
		    	
		    	
		        // the data table spec of the single output table, 
		        // the table will have three columns:
				BufferedDataTable inTable=inData[0];
				DataTableSpec inSpec=inTable.getDataTableSpec();
				PositionKSorter rowComparator=new PositionKSorter(
					inSpec.findColumnIndex(m_chromCol.getStringValue()),
					inSpec.findColumnIndex(m_pos1Col.getStringValue())
					);

				DataTableSpec inDataTableSpec = inTable.getDataTableSpec();
		        container1 = exec.createDataContainer(createDataTableSpec(inDataTableSpec));
		        container2 = exec.createDataContainer(inDataTableSpec);
		        
		       
		        
		        
		        LinkedList<Record> buffer=new LinkedList<Record>();
		        double total=inTable.getRowCount();
		        int nRow=0;
		        int nRowOut=0;
		        CloseableRowIterator iter=null;
		        in=openReader(m_uri.getStringValue());
		        try {
		        	iter=inTable.iterator();
		        	while(iter.hasNext())
		        		{
		        		++nRow;
		        		DataRow row=iter.next();
		        		Position vcf=rowComparator.make(row);
		        		System.err.println("X1 "+vcf+" "+" "+buffer.size());
		        		boolean fill_buffer=true;
		        		//update the buffer
		        		while(!buffer.isEmpty())
	        				{
	        				Record r=buffer.getFirst();
	        				int i=r.getChromosome().compareTo(vcf.getChromosome());
	        				if(i<0)
	        					{
	        					buffer.removeFirst();
	        					continue;
	        					}
	        				if(i>0)
	        					{
	        					break;
	        					}
	        				
	        				if(r.getEnd() <= vcf.getPosition0())
	        					{
	        					buffer.removeFirst();
	        					continue;
	        					}
	        				if(r.getStart()> vcf.getPosition0())
	        					{
	        					fill_buffer=false;
	        					}
	        				break;
	        				}
		        		
		        		//try to load buffer from stream
		        		if(fill_buffer && in!=null)
	        				{
		        			for(;;)
			        			{
		        				String line=in.readLine();
		        				
		        				if(line==null) break;
	        					Record r= parseRecord(line);
	        					if(r==null) continue;
	        					System.err.println("X2 "+vcf+" "+buffer.size()+" "+r);
	        					int i=r.getChromosome().compareTo(vcf.getChromosome());
		        				if(i<0)
		        					{
		        					//chrom before vcf ignore it
		        					continue;
		        					}
		        				if(i>0)
		        					{
		        					//chrom after vcf, add to buffer and break
		        					buffer.add(r);
		        					break;
		        					}
		        				
		        				if(r.getEnd() <= vcf.getPosition0())
		        					{
		        					//pos before vcf ignore it
		        					continue;
		        					}
		        				buffer.add(r);
		        				if(r.getStart()> vcf.getPosition0())
		        					{
		        					break;
		        					}
			        			}
	        				}
		        		
		        	boolean foundOne=false;
		        	for(Record r:buffer)
		        		{
		        		int i=r.getChromosome().compareTo(vcf.getChromosome());
        				if(i>0)
        					{
        					break;
        					}
        				if(r.getStart()> vcf.getPosition0())
        					{
        					break;
        					}
        				foundOne=true;
        				container1.addRowToTable(new AppendedColumnRow(
        						RowKey.createRowKey(++nRowOut),
        						row,toDataCells(r)));
		        		}	
		        	if(!foundOne)
		        		{
		        		container2.addRowToTable(row);
		        		}

	        		
	        		exec.checkCanceled();
	            	exec.setProgress(nRow/total,"hg19Snp130 processing....");
	        		}
	        	
				} 
	        catch (Exception e)
				{
	        	e.printStackTrace();
				throw e;
				}
			finally
				{
				if(iter!=null) iter.close();
				}
	        
			// once we are done, we close the container and return its table
	        container1.close();
	        BufferedDataTable out1 = container1.getTable();
	        container1=null;
	        container2.close();
	        BufferedDataTable out2 = container2.getTable();
	        container2=null;
	      
	        BufferedDataTable array[]= new BufferedDataTable[]{out1,out2};
	    	return array;
	    	}
		catch(Exception err)
			{
			err.printStackTrace();
			throw err;
			}
		finally
			{
			if(in!=null) try { in.close();} catch(Throwable err) {}
			if(container1!=null) try { container1.close();} catch(Throwable err) {}
			if(container2!=null) try { container2.close();} catch(Throwable err) {}
			}
       }
	
	
	@Override
    protected DataTableSpec[] configure(DataTableSpec[] inSpecs)
    		throws InvalidSettingsException
    	{
    	if(inSpecs==null || inSpecs.length!=1)
    		{
    		throw new InvalidSettingsException("Expected one table");
    		}
    	
    	DataTableSpec out=createDataTableSpec(inSpecs[0]);
    	return new DataTableSpec[]{out,inSpecs[0]};
    	}
	
	
	/**
     * {@inheritDoc}
     */
    @Override
    protected void saveSettingsTo(final NodeSettingsWO settings)
    	{
    	m_chromCol.saveSettingsTo(settings);
		m_pos1Col.saveSettingsTo(settings);
    	m_uri.saveSettingsTo(settings);
    	m_join.saveSettingsTo(settings);
		m_show_chrom.saveSettingsTo(settings);
		m_show_chromStart.saveSettingsTo(settings);
		m_show_chromEnd.saveSettingsTo(settings);
		m_show_name.saveSettingsTo(settings);
    	}
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void loadValidatedSettingsFrom(final NodeSettingsRO settings)
            throws InvalidSettingsException
        {
        m_chromCol.loadSettingsFrom(settings);
		m_pos1Col.loadSettingsFrom(settings);
        m_uri.loadSettingsFrom(settings);
        m_join.loadSettingsFrom(settings);
		m_show_chrom.loadSettingsFrom(settings);
		m_show_chromStart.loadSettingsFrom(settings);
		m_show_chromEnd.loadSettingsFrom(settings);
		m_show_name.loadSettingsFrom(settings);
        }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void validateSettings(final NodeSettingsRO settings)
            throws InvalidSettingsException
       {
        m_chromCol.validateSettings(settings);
		m_pos1Col.validateSettings(settings);
		m_uri.validateSettings(settings);
		m_join.validateSettings(settings);
		m_show_chrom.validateSettings(settings);
		m_show_chromStart.validateSettings(settings);
		m_show_chromEnd.validateSettings(settings);
		m_show_name.validateSettings(settings);
       }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void loadInternals(final File internDir,
            final ExecutionMonitor exec) throws IOException,
            CanceledExecutionException
            {
    		
            }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void saveInternals(final File internDir,
            final ExecutionMonitor exec) throws IOException,
            CanceledExecutionException
        {
    	}

    /**
     * {@inheritDoc}
     */
    @Override
    protected void reset()
    	{
    	
    	}	
		
	}


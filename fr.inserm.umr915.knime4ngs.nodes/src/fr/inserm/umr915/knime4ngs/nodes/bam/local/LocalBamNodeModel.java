package fr.inserm.umr915.knime4ngs.nodes.bam.local;



import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.knime.core.data.DataRow;
import org.knime.core.data.DataTableSpec;
import org.knime.core.data.container.CloseableRowIterator;
import org.knime.core.data.def.IntCell;
import org.knime.core.data.def.StringCell;
import org.knime.core.node.BufferedDataTable;
import org.knime.core.node.BufferedDataTableHolder;
import org.knime.core.node.ExecutionContext;
import org.knime.core.node.InvalidSettingsException;
import org.knime.core.node.defaultnodesettings.SettingsModel;
import org.knime.core.node.defaultnodesettings.SettingsModelColumnName;

import fr.inserm.umr915.knime4ngs.nodes.vcf.AbstractVCFNodeModel;


public class LocalBamNodeModel  extends AbstractVCFNodeModel
	implements BufferedDataTableHolder
	{
	/** chrom column */
	static final String CHROM_COL_PROPERTY="chrom.col";
	static final String CHROM_COL_DEFAULT="CHROM";
	private final SettingsModelColumnName m_chromColumn = new SettingsModelColumnName(
			CHROM_COL_PROPERTY,
			CHROM_COL_DEFAULT
			);
	
	/** pos column */
	static final String POS_COL_PROPERTY="pos.col";
	static final String POS_COL_DEFAULT="POS";
	private final SettingsModelColumnName m_posColumn = new SettingsModelColumnName(
			POS_COL_PROPERTY,
			POS_COL_DEFAULT
			);
	/** sample column */
	static final String SAMPLE1_COL_PROPERTY="sample1.col";
	static final String SAMPLE1_COL_DEFAULT="SAMPLE";
	private final SettingsModelColumnName m_sample1Column = new SettingsModelColumnName(
			SAMPLE1_COL_PROPERTY,
			SAMPLE1_COL_DEFAULT
			);
	
	/** sample column */
	static final String SAMPLE2_COL_PROPERTY="sample2.col";
	static final String SAMPLE2_COL_DEFAULT="SAMPLE";
	private final SettingsModelColumnName m_sample2Column = new SettingsModelColumnName(
			SAMPLE2_COL_PROPERTY,
			SAMPLE2_COL_DEFAULT
			);
	
	
	/** sample column */
	static final String BAM_COL_PROPERTY="bam.uri";
	static final String BAM_COL_DEFAULT="BAM";
	private final SettingsModelColumnName m_bamColumn = new SettingsModelColumnName(
			BAM_COL_PROPERTY,
			BAM_COL_DEFAULT
			);
	
	int chromColumn=-1;
	int posColumn=-1;
	int sampleColumn=-1;
	Map<String, File> sample2bam=new HashMap<String, File>();
	private BufferedDataTable internalDataTable=null;
    /**
     * Constructor for the node model.
     */
    public LocalBamNodeModel()
    	{
        super(2,0);
    	}
   
    
    @Override
    protected BufferedDataTable[] execute(
    		final BufferedDataTable[] inData,
            final ExecutionContext exec
            ) throws Exception
        {
		this.sample2bam.clear();
		
		
			int bamColumn=findColumnIndex(inData[1].getSpec(),m_bamColumn,StringCell.TYPE);
			int sampleColumn=findColumnIndex(inData[1].getSpec(),m_sample2Column,StringCell.TYPE);
			CloseableRowIterator iter=null;
			try
				{
				iter=inData[1].iterator();
				while(iter.hasNext())
					{
					DataRow row=iter.next();
					String sample= getString(row, sampleColumn);
					String bam= getString(row, bamColumn);
					if(bam==null || bam.isEmpty()) continue;
					File bamFile=new File(bam);
					if(!bamFile.exists() ||
						!bamFile.isFile() || 
						!bamFile.canRead() ||
						!bamFile.getName().toLowerCase().endsWith(".bam"))
						{
						throw new IllegalArgumentException("bad file:"+bamFile);
						}
					this.sample2bam.put(sample, bamFile);
					}
				}
			finally
				{
				safeClose(iter);
				}
		setInternalTables(inData);
		return new BufferedDataTable[0];
        }
    
    @Override
    protected DataTableSpec[] configure(DataTableSpec[] inSpecs)
    		throws InvalidSettingsException {
    	if(inSpecs==null || inSpecs.length!=2 )
    		{
    		throw new InvalidSettingsException("Expected two tables");
    		}
    	
		this.chromColumn=findColumnIndex(inSpecs[0],this.m_chromColumn,StringCell.TYPE);
		this.posColumn=findColumnIndex(inSpecs[0], this.m_posColumn,IntCell.TYPE);
		this.sampleColumn=findColumnIndex(inSpecs[0], this.m_sample1Column,StringCell.TYPE);
		findColumnIndex(inSpecs[1], this.m_bamColumn,StringCell.TYPE);
		findColumnIndex(inSpecs[1], this.m_sample2Column,StringCell.TYPE);
    	return new DataTableSpec[0];
    	}
    
	@Override
	protected List<SettingsModel> getSettingsModel() {
		 List<SettingsModel> L=new ArrayList<SettingsModel>(super.getSettingsModel());
		 L.add(this.m_chromColumn);
		 L.add(this.m_posColumn);
		 L.add(this.m_sample1Column);
		 L.add(this.m_sample2Column);
		 L.add(this.m_bamColumn);
		return L;
		}
	@Override
	public BufferedDataTable[] getInternalTables()
		{
		if(this.internalDataTable==null) return new BufferedDataTable[0];
		return new BufferedDataTable[]{this.internalDataTable};
		}


	@Override
	public void setInternalTables(BufferedDataTable[] tables)
		{
		if(tables==null || tables.length==0)
			{
			this.internalDataTable=null;
			return;
			}
		
		this.internalDataTable=tables[0];
		}

    @Override
    protected void reset()
    	{
    	this.sample2bam.clear();
    	this.chromColumn=-1;
    	this.posColumn=-1;
    	this.sampleColumn=-1;
    	this.internalDataTable=null;
    	super.reset();
    	}
    
	}

package fr.inserm.umr915.knime4ngs.nodes.vcf.ucsc.kgview;



import java.util.ArrayList;
import java.util.List;
import org.knime.core.data.DataTableSpec;
import org.knime.core.data.def.IntCell;
import org.knime.core.data.def.StringCell;
import org.knime.core.node.BufferedDataTable;
import org.knime.core.node.BufferedDataTableHolder;
import org.knime.core.node.ExecutionContext;
import org.knime.core.node.InvalidSettingsException;
import org.knime.core.node.defaultnodesettings.SettingsModel;
import org.knime.core.node.defaultnodesettings.SettingsModelColumnName;

import fr.inserm.umr915.knime4ngs.nodes.sql.AbstractSqlNodeModel;


public class KgViewNodeModel  extends AbstractSqlNodeModel
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

	int chromColumn=-1;
	int posColumn=-1;
	int sampleColumn=-1;
	
	private BufferedDataTable internalDataTables[]=null;
    /**
     * Constructor for the node model.
     */
    public KgViewNodeModel()
    	{
        super(1,0);
    	}
   
 
    @Override
    protected BufferedDataTable[] execute(
    		final BufferedDataTable[] inData,
            final ExecutionContext exec
            ) throws Exception
        {
    	DataTableSpec inSpecs=inData[0].getDataTableSpec();
    	this.chromColumn=findColumnIndex(inSpecs,this.m_chromColumn,StringCell.TYPE);
		this.posColumn=findColumnIndex(inSpecs, this.m_posColumn,IntCell.TYPE);
		setInternalTables(inData);
		return new BufferedDataTable[0];
        }
    
    @Override
    protected DataTableSpec[] configure(DataTableSpec[] inSpecs)
    		throws InvalidSettingsException {
    	if(inSpecs==null || inSpecs.length!=1 )
    		{
    		throw new InvalidSettingsException("Expected one table");
    		}
    	
		this.chromColumn=findColumnIndex(inSpecs[0],this.m_chromColumn,StringCell.TYPE);
		this.posColumn=findColumnIndex(inSpecs[0], this.m_posColumn,IntCell.TYPE);
    	return new DataTableSpec[0];
    	}
    
	@Override
	protected List<SettingsModel> getSettingsModel() {
		 List<SettingsModel> L=new ArrayList<SettingsModel>(super.getSettingsModel());
		 L.add(this.m_chromColumn);
		 L.add(this.m_posColumn);
		return L;
		}
	@Override
	public BufferedDataTable[] getInternalTables()
		{
		if(this.internalDataTables==null) return new BufferedDataTable[0];
		return this.internalDataTables;
		}


	@Override
	public void setInternalTables(BufferedDataTable[] tables)
		{
		if(tables==null || tables.length==0)
			{
			this.internalDataTables=null;
			return;
			}
		
		this.internalDataTables=tables;
		}

    @Override
    protected void reset()
    	{
    	this.chromColumn=-1;
    	this.posColumn=-1;
    	this.sampleColumn=-1;
    	this.internalDataTables=null;
    	super.reset();
    	}
    
	}

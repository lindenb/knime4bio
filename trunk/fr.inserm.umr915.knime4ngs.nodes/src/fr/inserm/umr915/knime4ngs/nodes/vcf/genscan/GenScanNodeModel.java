package fr.inserm.umr915.knime4ngs.nodes.vcf.genscan;




import java.util.ArrayList;
import java.util.List;
import org.knime.core.data.DataTableSpec;
import org.knime.core.node.BufferedDataTable;
import org.knime.core.node.BufferedDataTableHolder;
import org.knime.core.node.ExecutionContext;
import org.knime.core.node.InvalidSettingsException;
import org.knime.core.node.defaultnodesettings.SettingsModel;
import org.knime.core.node.defaultnodesettings.SettingsModelColumnName;

import fr.inserm.umr915.knime4ngs.corelib.bio.HasPositionColumns;
import fr.inserm.umr915.knime4ngs.corelib.bio.HasValueColumn;
import fr.inserm.umr915.knime4ngs.nodes.vcf.AbstractVCFNodeModel;


public class GenScanNodeModel  extends AbstractVCFNodeModel
	implements BufferedDataTableHolder,HasPositionColumns,HasValueColumn
	{
	/** chrom column */
	static final String CHROM_COL_PROPERTY="chrom.col";
	static final String CHROM_COL_DEFAULT="CHROM";
	private final SettingsModelColumnName m_chromColumn = new SettingsModelColumnName(
			CHROM_COL_PROPERTY,
			CHROM_COL_DEFAULT
			);
	
	/** start column */
	static final String START_COL_PROPERTY="start.col";
	static final String START_COL_DEFAULT="POS";
	private final SettingsModelColumnName m_startColumn = new SettingsModelColumnName(
			START_COL_PROPERTY,
			START_COL_DEFAULT
			);
	/** end column */
	static final String END_COL_PROPERTY="end.col";
	static final String END_COL_DEFAULT="POS";
	private final SettingsModelColumnName m_endColumn = new SettingsModelColumnName(
			END_COL_PROPERTY,
			END_COL_DEFAULT
			);
	
	/** end column */
	static final String VAL_COL_PROPERTY="val.col";
	static final String VAL_COL_DEFAULT="VALUE";
	private final SettingsModelColumnName m_valueColumn = new SettingsModelColumnName(
			VAL_COL_PROPERTY,
			VAL_COL_DEFAULT
			);
	
	private BufferedDataTable internalDataTables[]=null;
    /**
     * Constructor for the node model.
     */
    public GenScanNodeModel()
    	{
        super(1,0);
    	}
   
    
    @Override
    protected BufferedDataTable[] execute(
    		final BufferedDataTable[] inData,
            final ExecutionContext exec
            ) throws Exception
        {
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
 
		return new DataTableSpec[0];
    	}
    
	@Override
	protected List<SettingsModel> getSettingsModel() {
		 List<SettingsModel> L=new ArrayList<SettingsModel>(super.getSettingsModel());
		 L.add(this.m_chromColumn);
		 L.add(this.m_startColumn);
		 L.add(this.m_endColumn);
		 L.add(this.m_valueColumn);
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
		
		if(tables==null || tables.length!=1 || tables[0]==null)
			{
			this.internalDataTables=null;
			return;
			}
		
		this.internalDataTables=tables;
		}

    @Override
    protected void reset()
    	{
    	this.internalDataTables=null;
    	super.reset();
    	}
    
    private int find(SettingsModelColumnName col)
    	{
    	
    	if(col.getColumnName()==null) return -1;
    	if(this.internalDataTables==null || this.internalDataTables.length==0 || this.internalDataTables[0]==null) return -1;
    	return this.internalDataTables[0].getDataTableSpec().findColumnIndex(col.getColumnName());
    	}
    @Override
    public int getChromosomeColumn() {
    	return find(this.m_chromColumn);
    	}
    
    @Override
    public int getChromStartColumnIndex() {
    	return find(this.m_startColumn);
    	}
    @Override
    public int getChromEndColumnIndex()
    	{
    	return find(this.m_endColumn);
    	}
    @Override
    public int getValueColumn()
    	{
    	return find(this.m_valueColumn);
    	}
	}

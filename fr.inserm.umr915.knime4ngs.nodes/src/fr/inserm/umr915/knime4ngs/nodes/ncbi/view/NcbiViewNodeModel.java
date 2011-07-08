package fr.inserm.umr915.knime4ngs.nodes.ncbi.view;



import java.util.ArrayList;
import java.util.List;

import org.knime.core.data.DataTableSpec;
import org.knime.core.data.def.IntCell;
import org.knime.core.node.BufferedDataTable;
import org.knime.core.node.BufferedDataTableHolder;
import org.knime.core.node.ExecutionContext;
import org.knime.core.node.InvalidSettingsException;
import org.knime.core.node.defaultnodesettings.SettingsModel;
import org.knime.core.node.defaultnodesettings.SettingsModelColumnName;
import org.knime.core.node.defaultnodesettings.SettingsModelString;

import fr.inserm.umr915.knime4ngs.nodes.vcf.AbstractVCFNodeModel;


public class NcbiViewNodeModel  extends AbstractVCFNodeModel
	implements BufferedDataTableHolder
	{
	/** id column */
	static final String NCBI_ID_PROPERTY="ncbi.id.col";
	static final String NCBI_ID_DEFAULT="PMID";
	private final SettingsModelColumnName m_ncbiIdColumn = new SettingsModelColumnName(
			NCBI_ID_PROPERTY,
			NCBI_ID_DEFAULT
			);
	/** db column */
	static final String DATABASES[]=new String[]{"pubmed","gene","snp"};
	static final String NCBI_DATABASE_PROPERTY="ncbi.db.col";
	static final String NCBI_DATABASE_DEFAULT=DATABASES[0];
	private final SettingsModelString m_ncbiDb = new SettingsModelString(
			NCBI_DATABASE_PROPERTY,
			NCBI_DATABASE_DEFAULT
			);
	
	
	private BufferedDataTable internalDataTable=null;
    /**
     * Constructor for the node model.
     */
    public NcbiViewNodeModel()
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
    	
		findColumnIndex(inSpecs[0],this.m_ncbiIdColumn,IntCell.TYPE);
    	return new DataTableSpec[0];
    	}
    
	@Override
	protected List<SettingsModel> getSettingsModel() {
		 List<SettingsModel> L=new ArrayList<SettingsModel>(super.getSettingsModel());
		 L.add(this.m_ncbiIdColumn);
		 L.add(this.m_ncbiDb);
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

	public int getNcbIdColumn()
		{
		if(internalDataTable==null)
			{
			return -1;
			}
		String s=this.m_ncbiIdColumn.getColumnName();
		if(s==null || s.isEmpty()) return -1;
		return internalDataTable.getDataTableSpec().findColumnIndex(s);
		}
	
	public String getNcbIDatabase()
		{
		return this.m_ncbiDb.getStringValue();
		}
	
    @Override
    protected void reset()
    	{
    	this.internalDataTable=null;
    	super.reset();
    	}
    
	}

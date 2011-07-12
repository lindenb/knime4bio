package fr.inserm.umr915.knime4ngs.nodes.vcf.browser;

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
import org.knime.core.node.defaultnodesettings.SettingsModelString;

import fr.inserm.umr915.knime4ngs.corelib.knime.AbstractNodeModel;


public class WebBrowserNodeModel  extends AbstractNodeModel
	implements BufferedDataTableHolder
	{
	/** chrom column */
	static final String CHROM_COL_PROPERTY="chrom.col";
	static final String CHROM_COL_DEFAULT="CHROM";
	 final SettingsModelColumnName m_chromColumn = new SettingsModelColumnName(
			CHROM_COL_PROPERTY,
			CHROM_COL_DEFAULT
			);
	
	/** pos column */
	static final String POS_COL_PROPERTY="pos.col";
	static final String POS_COL_DEFAULT="POS";
	 final SettingsModelColumnName m_posColumn = new SettingsModelColumnName(
			POS_COL_PROPERTY,
			POS_COL_DEFAULT
			);
	
	/** build column */
	static final String BUILD_PROPERTY="ucsc.build";
	static final String BUILD_DEFAULT="hg19";
	 final SettingsModelString m_build = new SettingsModelString(
			BUILD_PROPERTY,
			BUILD_DEFAULT
			);
	
	
	
	private BufferedDataTable internalDataTables[]=null;
    /**
     * Constructor for the node model.
     */
    public WebBrowserNodeModel()
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
    	
		findColumnIndex(inSpecs[0],this.m_chromColumn,StringCell.TYPE);
		findColumnIndex(inSpecs[0], this.m_posColumn,IntCell.TYPE);
		
    	return new DataTableSpec[0];
    	}
    
	@Override
	protected List<SettingsModel> getSettingsModel() {
		 List<SettingsModel> L=new ArrayList<SettingsModel>(super.getSettingsModel());
		 L.add(this.m_chromColumn);
		 L.add(this.m_posColumn);
		 L.add(this.m_build);
		 for(SettingsModel s:L) System.err.println(s.toString());
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
		if(tables==null || tables.length!=1)
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
    
	}

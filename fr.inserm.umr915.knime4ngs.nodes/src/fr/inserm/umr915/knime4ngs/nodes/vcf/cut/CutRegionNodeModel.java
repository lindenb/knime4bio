package fr.inserm.umr915.knime4ngs.nodes.vcf.cut;

import java.util.ArrayList;
import java.util.List;

import org.knime.core.data.DataCell;
import org.knime.core.data.DataRow;
import org.knime.core.data.DataTableSpec;
import org.knime.core.data.container.CloseableRowIterator;
import org.knime.core.data.def.IntCell;
import org.knime.core.data.def.StringCell;
import org.knime.core.node.BufferedDataContainer;
import org.knime.core.node.BufferedDataTable;
import org.knime.core.node.ExecutionContext;
import org.knime.core.node.InvalidSettingsException;
import org.knime.core.node.defaultnodesettings.SettingsModel;
import org.knime.core.node.defaultnodesettings.SettingsModelBoolean;
import org.knime.core.node.defaultnodesettings.SettingsModelColumnName;
import org.knime.core.node.defaultnodesettings.SettingsModelInteger;
import org.knime.core.node.defaultnodesettings.SettingsModelString;
import fr.inserm.umr915.knime4ngs.nodes.vcf.AbstractVCFNodeModel;



/**

 * @author Pierre Lindenbaum
 */
public class CutRegionNodeModel extends AbstractVCFNodeModel
	{
	static final String SELECT_CHROM_PROPERTY="select.chrom";
	static final String SELECT_CHROM_DEFAULT="chrUn";
	private final SettingsModelString m_selectChrom =
        new SettingsModelString(SELECT_CHROM_PROPERTY,SELECT_CHROM_DEFAULT);
	
	static final String SELECT_START_PROPERTY="select.chromStart";
	static final int SELECT_START_DEFAULT=0;
	private final SettingsModelInteger m_selectChromStart =
        new SettingsModelInteger(SELECT_START_PROPERTY,SELECT_START_DEFAULT);
	
	static final String SELECT_END_PROPERTY="select.chromEnd";
	static final int SELECT_END_DEFAULT=300000000;
	private final SettingsModelInteger m_selectChromEnd =
        new SettingsModelInteger(SELECT_END_PROPERTY,SELECT_END_DEFAULT);
	
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
	
	
	/** exclude column */
	static final String INVERT_PROPERTY="invert";
	static final boolean INVERT_DEFAULT=false;
	private final SettingsModelBoolean m_invert = new SettingsModelBoolean(
			INVERT_PROPERTY,
			INVERT_DEFAULT
			);
	
	
    /**
     * Constructor for the node model.
     */
    protected CutRegionNodeModel()
    	{
        super(1,1);
    	}
   
    
    
    @Override
    protected BufferedDataTable[] execute(
    		final BufferedDataTable[] inData,
            final ExecutionContext exec
            ) throws Exception
            {
			BufferedDataContainer container1=null;
			BufferedDataTable inTable=inData[0];
			int chromCol=findColumnIndex(inTable.getDataTableSpec(),m_chromColumn,StringCell.TYPE);
	    	int posCol=findColumnIndex(inTable.getDataTableSpec(),m_posColumn,IntCell.TYPE);
			
			String selChrom=this.m_selectChrom.getStringValue();
			int chromStart= this.m_selectChromStart.getIntValue();
			int chromEnd= this.m_selectChromEnd.getIntValue();
			
			try
		    	{
	
		        container1 = exec.createDataContainer(inTable.getDataTableSpec());
		       
		        
		        double total=inTable.getRowCount();
		        int nRow=0;
		        CloseableRowIterator iter=null;
		        try {
		        	iter=inTable.iterator();
		        	while(iter.hasNext())
		        		{
		        		++nRow;
		        		DataRow row=iter.next();
		        		DataCell cell=row.getCell(chromCol);
		        		if(cell.isMissing()) continue;
		        		String chrom=StringCell.class.cast(cell).getStringValue();
		        		
		        		cell=row.getCell(posCol);
		        		if(cell.isMissing()) continue;
		        		int pos=IntCell.class.cast(cell).getIntValue();
		        		
		        		if((chrom.equals(selChrom) && chromStart<=pos && chromEnd>=pos) == !m_invert.getBooleanValue() )
		        			{
		        			container1.addRowToTable(row);
		        			}
		        		
		        		exec.checkCanceled();
		            	exec.setProgress(nRow/total,"Cut Region...");
		        		}
					} 
		        catch (Exception e)
					{
					throw e;
					}
				finally
					{
					if(iter!=null) iter.close();
					}
		        
				// once we are done, we close the container and return its table
				safeClose(container1);
		        BufferedDataTable out1 = container1.getTable();
		        container1=null;
		        
		       
		        BufferedDataTable array[]= new BufferedDataTable[]{out1};
		    	return array;
		    	}
		catch(Exception err)
			{
			getLogger().error("Boum", err);
			err.printStackTrace();
			throw err;
			}
		finally
			{
			safeClose(container1);
			}
       }
    
    
    @Override
    protected DataTableSpec[] configure(DataTableSpec[] inSpecs)
    		throws InvalidSettingsException {
    	if(inSpecs==null || inSpecs.length!=1)
    		{
    		throw new InvalidSettingsException("Expected one table");
    		}
    	
    	DataTableSpec in=inSpecs[0];
    	findColumnIndex(in,m_chromColumn,StringCell.TYPE);
    	findColumnIndex(in,m_posColumn,IntCell.TYPE);
    	
    	return new DataTableSpec[]{in};
    	}
    
    @Override
    protected List<SettingsModel> getSettingsModel() {
    	List<SettingsModel> L=new ArrayList<SettingsModel>( super.getSettingsModel());
    	L.add(this.m_selectChrom);
    	L.add(this.m_selectChromStart);
    	L.add(this.m_selectChromEnd);
    	L.add(this.m_chromColumn);
    	L.add(this.m_posColumn);
    	L.add(this.m_invert);
    	return L;
    	}
    
    
    
	}


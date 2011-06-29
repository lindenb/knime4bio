package fr.inserm.umr915.knime4ngs.nodes.bed.writer;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import org.knime.core.data.DataRow;
import org.knime.core.data.DataTableSpec;
import org.knime.core.data.container.CloseableRowIterator;
import org.knime.core.data.def.IntCell;
import org.knime.core.data.def.StringCell;
import org.knime.core.node.BufferedDataTable;
import org.knime.core.node.ExecutionContext;
import org.knime.core.node.InvalidSettingsException;
import org.knime.core.node.defaultnodesettings.SettingsModel;
import org.knime.core.node.defaultnodesettings.SettingsModelBoolean;
import org.knime.core.node.defaultnodesettings.SettingsModelColumnName;
import org.knime.core.node.defaultnodesettings.SettingsModelString;




import fr.inserm.umr915.knime4ngs.nodes.vcf.AbstractVCFNodeModel;





/**
 * @author Pierre Lindenbaum
 */
public class BedWriterNodeModel extends AbstractVCFNodeModel
	{
	static final String DEFAULT_FILENAME_PROPERTY="out.bed";
	static final String FILENAME_PROPERTY="file.name";
	static final String OFFSET_PROPERTY="base.offset";
	static final String CHROMOSOME_COL_PROPERTY="chrom.column";
	static final String DEFAULT_CHROM_COL_PROPERTY="CHROM";
	static final String CHROMSTART_COL_PROPERTY="chromStart.column";
	static final String DEFAULT_CHROMSTART_COL_PROPERTY="POS";
	static final String CHROMEND_COL_PROPERTY="chromEnd.column";
	static final String DEFAULT_CHROMEND_COL_PROPERTY="POS";
	static final String NAME_COL_PROPERTY="name.column";
	static final String DEFAULT_NAME_COL_PROPERTY="name";
	
	
	private final SettingsModelBoolean m_dataAreOneBase =
        new SettingsModelBoolean(OFFSET_PROPERTY,true);
	private final SettingsModelString m_filename =
        new SettingsModelString(FILENAME_PROPERTY,DEFAULT_FILENAME_PROPERTY);
	private final SettingsModelColumnName m_colChrom =
        new SettingsModelColumnName(CHROMOSOME_COL_PROPERTY,DEFAULT_CHROM_COL_PROPERTY);
	private final SettingsModelColumnName m_colChromStart =
        new SettingsModelColumnName(CHROMSTART_COL_PROPERTY,DEFAULT_CHROMSTART_COL_PROPERTY);
	private final SettingsModelColumnName m_colChromEnd =
        new SettingsModelColumnName(CHROMEND_COL_PROPERTY,DEFAULT_CHROMEND_COL_PROPERTY);
	private final SettingsModelColumnName m_colName =
        new SettingsModelColumnName(NAME_COL_PROPERTY,DEFAULT_NAME_COL_PROPERTY);
	
    /**
     * Constructor for the node model.
     */
    protected BedWriterNodeModel()
    	{
        super(1,0);
    	}
    
    @Override
    protected BufferedDataTable[] execute(
    		final BufferedDataTable[] inData,
            final ExecutionContext exec
            ) throws Exception
            {
    		PrintWriter out=null;
			try
		    	{
		        // the data table spec of the single output table, 
		        // the table will have three columns:
				BufferedDataTable inTable=inData[0];
				DataTableSpec tableSpec=inTable.getDataTableSpec();
		        int colChrom=findColumnIndex(tableSpec, m_colChrom,StringCell.TYPE);
		        int colChromStart=findColumnIndex(tableSpec, m_colChromStart,IntCell.TYPE);
		        int shift=(m_dataAreOneBase.getBooleanValue()?-1:0);
		        int colChromEnd= -1;
		        if(m_colChromEnd.getStringValue()!=null)
		        	{
		        	colChromEnd=findColumnIndex(tableSpec,m_colChromEnd,IntCell.TYPE);
		        	}
		        if(colChromEnd==-1) colChromEnd=colChromStart;
		        int colName=-1;
		        if(m_colName.getStringValue()!=null)
		        	{
		        	colName=tableSpec.findColumnIndex(m_colName.getStringValue());
		        	}
		        
				File f= new File(m_filename.getStringValue());
				out =new PrintWriter(f);
				int total= inTable.getRowCount();
		        int nRow=0;
		        CloseableRowIterator iter=null;
		        try {
		        	iter=inTable.iterator();
		        	while(iter.hasNext())
		        		{
		        		++nRow;
		        		DataRow row=iter.next();
		        		if(row.getCell(colChrom).isMissing()) continue;
		        		if(row.getCell(colChromStart).isMissing()) continue;
		        		if(colChromEnd!=-1 && row.getCell(colChromEnd).isMissing()) continue;
		        		if(colName!=-1 && row.getCell(colName).isMissing()) continue;
		        		
		        		String chrom=StringCell.class.cast(row.getCell(colChrom)).getStringValue();
		        		int chromStart=IntCell.class.cast(row.getCell(colChromStart)).getIntValue()+shift;
		        		int chromEnd=IntCell.class.cast(row.getCell(colChromEnd)).getIntValue()+shift+(colChromEnd==colChromStart?1:0);
		        		out.print(chrom);
						out.print("\t");
						out.print(chromStart);
						out.print("\t");
						out.print(chromEnd);
						out.print("\t");
						out.print("+");
						out.print("\t");
						if(colName!=-1)
							{
							out.print(row.getCell(colName).toString());
							}
						else
							{
							out.print(row.getKey().getString());
							}
		        		out.println();
		        		}
		        	exec.checkCanceled();
	            	exec.setProgress(nRow/total,"Exporting to BED....");
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
		        BufferedDataTable array[]= new BufferedDataTable[0];
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
			if(out!=null)
				{
				out.flush();
				out.close();
				}
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
    	
    	return new DataTableSpec[0];
    	}
    
    @Override
    protected List<SettingsModel> getSettingsModel() {
    	List<SettingsModel> L=new ArrayList<SettingsModel>( super.getSettingsModel());
    	L.add(this.m_dataAreOneBase);
    	L.add(this.m_filename);
    	L.add(this.m_colChrom);
    	L.add(this.m_colChromStart);
    	L.add(this.m_colChromEnd);
    	L.add(this.m_colName);
    	return L;
    	}
	}


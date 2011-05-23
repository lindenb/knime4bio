package fr.inserm.umr915.knime4ngs.nodes.vcf.predictions;

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
import org.knime.core.node.defaultnodesettings.SettingsModelString;

/**
 * 
 * AbstractPredictionOutNodeModel
 *
 */
public abstract class AbstractPredictionOutNodeModel
	extends AbstractPredictionNodeModel
	{

	protected AbstractPredictionOutNodeModel()
		{
		super(1, 0);
		}


		
	
	 
	 protected abstract void write(PrintWriter out,String chrom,int pos,String ref,String alt);
	 protected abstract SettingsModelString getFileOutSetting();
	 
	 
	 @Override
	 protected BufferedDataTable[] execute(
			BufferedDataTable[] inData,
	    	ExecutionContext exec
	    	) throws Exception
	    	{
	    	if(inData==null || inData.length!=1)
		     	{
		     	throw new InvalidSettingsException("Expected one table.");
		     	}
	    	BufferedDataTable inTable=inData[0];
	    	int nRows=0;
	    	double total=inTable.getRowCount();
	    	CloseableRowIterator iter=null;
	    	
	    	DataTableSpec inSpecs=inData[0].getDataTableSpec();
	    	int chromCol=findColumnIndex(inSpecs,m_chromCol,StringCell.TYPE);
	        int posCol=findColumnIndex(inSpecs,m_posCol,IntCell.TYPE);
	        int refCol=findColumnIndex(inSpecs,m_refCol,StringCell.TYPE);
	        int altCol=findColumnIndex(inSpecs,m_altCol,StringCell.TYPE);
	    	
	    	PrintWriter out=null;
	    	try
		    	{
	    		out=new PrintWriter(getFileOutSetting().getStringValue());
	    		
		    	iter=inTable.iterator();
		    	while(iter.hasNext())
		    		{
		    		++nRows;
		    		DataRow row=iter.next();
		    		
		    		if(!isATGC(getString(row, refCol))) continue;
		    	
		    		for(String alt: alts(getString(row, refCol),getString(row, altCol)))
		    			{
		    			if(!isATGC(alt)) continue;
		    			write(out,
		    					getString(row, chromCol),
		    					getInt(row,posCol),
		    					getString(row, refCol),
		    					alt
		    					);
		    			}
		    		
		    		exec.checkCanceled();
	            	exec.setProgress(nRows/total);
		    		}
		
		        return new BufferedDataTable[0];
		    	}
	    	catch (Exception e) {
				e.printStackTrace();
				throw e;
				}
	    	finally
	    		{
	    		safeClose(iter);
	    		if( out!=null)  { out.flush(); out.close();}
	    		}
	    	}

	
	
	
	 
    /**
     * {@inheritDoc}
     */
    @Override
    protected DataTableSpec[] configure(final DataTableSpec[] inSpecs)
            throws InvalidSettingsException
        {
        if(inSpecs==null || inSpecs.length!=1)
        	{
        	throw new InvalidSettingsException("Expected one table.");
        	}
       
        findColumnIndex(inSpecs[0],m_chromCol,StringCell.TYPE);
        findColumnIndex(inSpecs[0],m_posCol,IntCell.TYPE);
        findColumnIndex(inSpecs[0],m_refCol,StringCell.TYPE);
        findColumnIndex(inSpecs[0],m_altCol,StringCell.TYPE);
        
    	return new DataTableSpec[0];
    	}
    
    
    @Override
    protected List<SettingsModel> getSettingsModel() {
    	List<SettingsModel> a=new ArrayList<SettingsModel>(super.getSettingsModel());
    	a.add(getFileOutSetting());
    	return a;
    	}
}

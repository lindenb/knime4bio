package fr.inserm.umr915.knime4ngs.nodes.mw.export;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


import org.knime.core.data.DataRow;
import org.knime.core.data.DataTableSpec;
import org.knime.core.data.container.CloseableRowIterator;
import org.knime.core.node.BufferedDataTable;
import org.knime.core.node.ExecutionContext;
import org.knime.core.node.InvalidSettingsException;
import org.knime.core.node.defaultnodesettings.SettingsModel;
import org.knime.core.node.defaultnodesettings.SettingsModelString;


import fr.inserm.umr915.knime4ngs.corelib.bio.Mutation;
import fr.inserm.umr915.knime4ngs.corelib.bio.Position;
import fr.inserm.umr915.knime4ngs.corelib.knime.AbstractNodeModel;
import fr.inserm.umr915.knime4ngs.nodes.vcf.predictions.AbstractPredictionOutNodeModel;


/**
 * This is the model implementation of VCFSource.
 * Reads a VCF file
 *
 * @author Pierre Lindenbaum
 */
public class MediaWikiExportNodeModel extends AbstractNodeModel
	{
	static final String FILENAME_PROPERTY="file.name";
	static final String DEFAULT_FILENAME="mediawiki.txt";
	protected final SettingsModelString m_filename = null;
	
    /**
     * Constructor for the node model.
     */
    protected MediaWikiExportNodeModel()
    	{
        super(1,0);
    	}
    
    
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
	    	
	    	PrintWriter out=null;
	    	try
		    	{
	    		out=new PrintWriter(m_filename.getStringValue());
	    		
		    	iter=inTable.iterator();
		    	while(iter.hasNext())
		    		{
		    		++nRows;
		    		DataRow row=iter.next();
		    		
		    		
		    		exec.checkCanceled();
	            	exec.setProgress(nRows/total,"Building wiki table");
		    		}
		
		        return new BufferedDataTable[0];
		    	}
	    	catch (Exception e) {
				e.printStackTrace();
				throw e;
				}
	    	finally
	    		{
	    		if(iter!=null) iter.close();
	    		if( out!=null)  { out.flush(); out.close();}
	    		}
	    	}
    
    @Override
    protected DataTableSpec[] configure(final DataTableSpec[] inSpecs)
            throws InvalidSettingsException
        {
        if(inSpecs==null || inSpecs.length!=1)
        	{
        	throw new InvalidSettingsException("Expected one table.");
        	}       
        
    	return new DataTableSpec[0];
    	}
  
   @Override
   protected List<SettingsModel> getSettingsModel()
	   	{
	   	List<SettingsModel> a=new ArrayList<SettingsModel>(super.getSettingsModel());
	   	a.add(m_filename);
	   	return a;
	   	}
   
   
	}


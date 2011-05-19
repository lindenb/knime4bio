package fr.inserm.umr915.knime4ngs.nodes.vcf.predictions;


import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import org.knime.core.data.DataCell;
import org.knime.core.data.DataColumnSpec;
import org.knime.core.data.DataRow;
import org.knime.core.data.DataTableSpec;
import org.knime.core.data.container.CloseableRowIterator;
import org.knime.core.data.def.DefaultRow;
import org.knime.core.node.BufferedDataContainer;
import org.knime.core.node.BufferedDataTable;
import org.knime.core.node.ExecutionContext;
import org.knime.core.node.InvalidSettingsException;
import org.knime.core.node.defaultnodesettings.SettingsModel;
import org.knime.core.node.defaultnodesettings.SettingsModelColumnName;
import org.knime.core.node.defaultnodesettings.SettingsModelString;
import org.knime.core.node.port.PortType;

import fr.inserm.umr915.knime4ngs.corelib.bio.Mutation;
import fr.inserm.umr915.knime4ngs.corelib.bio.Position;
import fr.inserm.umr915.knime4ngs.nodes.vcf.AbstractVCFNodeModel;

public abstract class AbstractPredictionOutNodeModel extends
		AbstractVCFNodeModel
	{
	
	
	private Pattern comma=Pattern.compile("[,]");
	public AbstractPredictionOutNodeModel(int nrInDataPorts, int nrOutDataPorts) {
		super(nrInDataPorts, nrOutDataPorts);
		}

	public AbstractPredictionOutNodeModel(PortType[] inPortTypes,
			PortType[] outPortTypes) {
		super(inPortTypes, outPortTypes);
		}

		
	private Set<String> alts(String refBase,String alts)//TODO fix this for all degenerate nucleotides
		{
		Set<String> altBases=new HashSet<String>();  
		for(String alt:comma.split(alts))
			{
			if(alt.isEmpty()) continue;
			if(refBase.equals("A"))
    			{
    				 if(alt.equals("W")) { alt="T"; }
    			else if(alt.equals("M")) { alt="C"; }
    			else if(alt.equals("R")) { alt="G"; }
    			}
    		else if(refBase.equals("C"))
    			{
    				 if(alt.equals("S")) { alt="G"; }
    			else if(alt.equals("M")) { alt="A"; }
    			else if(alt.equals("Y")) { alt="T"; }
    			}
    		else if(refBase.equals("G"))
    			{
    				 if(alt.equals("S")) { alt="C"; }
    			else if(alt.equals("K")) { alt="T"; }
    			else if(alt.equals("R")) { alt="A"; }
    			}
    		else if(refBase.equals("T"))
    			{
    				 if(alt.equals("W")) { alt="A"; }
    			else if(alt.equals("K")) { alt="G"; }
    			else if(alt.equals("Y")) { alt="C"; }
    			}
			if(refBase!=null && refBase.equals(alt)) continue;
			altBases.add(alt);
			}
		return altBases;
		}
	
	 private static boolean isATGC(String s)
	 	{
		return s.equals("A")  || s.equals("T") || s.equals("G") || s.equals("C"); 
	 	}
	 
	 protected abstract void write(PrintWriter out,Position pos,String ref,String alt);
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
	    	MutationColumns cols=getMutationColumns(inTable.getDataTableSpec());
	    	PrintWriter out=null;
	    	try
		    	{
	    		out=new PrintWriter(getFileOutSetting().getStringValue());
	    		
		    	iter=inTable.iterator();
		    	while(iter.hasNext())
		    		{
		    		++nRows;
		    		DataRow row=iter.next();
		    		Mutation mut=getMutation(cols, row);
		    		if(mut==null || !isATGC(mut.getRef())) continue;
		    	
		    		for(String alt: alts(mut.getRef(),mut.getAlt()))
		    			{
		    			if(!isATGC(alt)) continue;
		    			write(out,mut.getPosition(),mut.getRef(),alt);
		    			}
		    		
		    		exec.checkCanceled();
	            	exec.setProgress(nRows/total,mut.toString());
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
        DataTableSpec inTable=inSpecs[0];
       
        try
        	{
        	getMutationColumns(inTable);
        	}
        catch(IllegalArgumentException err)
        	{
        	err.printStackTrace();
        	throw new InvalidSettingsException("Illegal table",err);
        	}
        
        
    	return new DataTableSpec[0];
    	}
    
    
    @Override
    protected List<SettingsModel> getSettingsModel() {
    	List<SettingsModel> a=new ArrayList<SettingsModel>(super.getSettingsModel());
    	a.add(getFileOutSetting());
    	return a;
    	}
}

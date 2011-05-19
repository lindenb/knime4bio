package fr.inserm.umr915.knime4ngs.nodes.vcf.homopolymer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.knime.core.data.DataRow;
import org.knime.core.data.DataTableSpec;
import org.knime.core.data.container.CloseableRowIterator;
import org.knime.core.data.def.IntCell;
import org.knime.core.data.def.StringCell;
import org.knime.core.node.BufferedDataContainer;
import org.knime.core.node.BufferedDataTable;
import org.knime.core.node.InvalidSettingsException;
import org.knime.core.node.defaultnodesettings.SettingsModel;
import org.knime.core.node.defaultnodesettings.SettingsModelInteger;
import org.knime.core.node.ExecutionContext;



import fr.inserm.umr915.knime4ngs.corelib.bio.Segment;
import fr.inserm.umr915.knime4ngs.corelib.knime.AbstractNodeModel;




public class HomoPolymerNodeModel extends AbstractNodeModel
	{ 
	final static int DEFAULT_MAX_REPEAT=5;
	static final String MAX_REPEAT_PROPERTY="max.repeat";
	
	private final SettingsModelInteger m_maxRepeat =
        new SettingsModelInteger(MAX_REPEAT_PROPERTY,DEFAULT_MAX_REPEAT);
	
	
    /**
     * Constructor for the node model.
     */
    protected HomoPolymerNodeModel()
    	{
        super(1,2);
    	}
    

    @Override
    protected BufferedDataTable[] execute(
    		final BufferedDataTable[] inData,
            final ExecutionContext exec
            ) throws Exception
            {
			BufferedDataContainer container1=null;
			BufferedDataContainer container2=null;
			try
		    	{
				
    		
		        // the data table spec of the single output table, 
		        // the table will have three columns:
				BufferedDataTable inTable=inData[0];
		       
				
				DataTableSpec inDataTableSpec = inTable.getDataTableSpec();
				int chromCol= findColumnIndex(inDataTableSpec, "CHROM",StringCell.TYPE);
				int posCol= findColumnIndex(inDataTableSpec, "POS",IntCell.TYPE);
				
		        container1 = exec.createDataContainer(inDataTableSpec);
		        container2 = exec.createDataContainer(inDataTableSpec);
		        
		       
		        double total=inTable.getRowCount();
		        int nRow=0;
		        BufferedReader in=null;
		        CloseableRowIterator iter=null;
		        try {
		        	iter=inTable.iterator();
		        	
	        		while(iter.hasNext())
	        			{
	        			++nRow;
	        			DataRow dataRow=iter.next();
	        			String chrom=getString(dataRow, chromCol);
	        			int position0=getInt(dataRow, posCol)-1;
	        			
	        			int chromStart=Math.max(0,position0 - m_maxRepeat.getIntValue());
	        			int chromEnd=position0+ m_maxRepeat.getIntValue()+1;
	        			Segment seg= new Segment(chrom, chromStart,chromEnd);
	        			
	        			URL url=new URL("http://srv-clc-02.irt.univ-nantes.prive:8080/biomachin/genome/reference?segment="+
	        					URLEncoder.encode(seg.toString(),"UTF-8")
	        					);//TODO
	        			in=new BufferedReader(new InputStreamReader(url.openStream()));
	        			StringBuffer genomic=new StringBuffer(chromEnd-chromStart);
	        			int c;
	        			while((c=in.read())!=-1)
	        				{
	        				genomic.append((char)c);
	        				}
	        			in.close();
	        			
	        			int count=1;
	        			int loc= position0-chromStart;
	        			char ref= Character.toUpperCase(genomic.charAt(loc));
	        			while(loc-1>=0)
	        				{
	        				loc--;
	        				if(ref!=Character.toUpperCase(genomic.charAt(loc)))
	        					{
	        					break;
	        					}
	        				count++;
	        				}
	        			 loc= position0-chromStart+1;
	        			 while(loc< genomic.length())
	        			 	{
	        				 if(ref!=Character.toUpperCase(genomic.charAt(loc)))
	        					{
	        					break;
	        					}
	        				loc++;
	        				count++;
	        			 	}
	        			//System.err.println(genomic.toString()+" "+seg+" "+count); 
	        			 
	        			if(count <= m_maxRepeat.getIntValue())
	        				{
	        				container1.addRowToTable(dataRow);
	        				}
	        			else
	        				{
	        				container2.addRowToTable(dataRow);
	        				}
	        			
		        		exec.checkCanceled();
		        		exec.setProgress(nRow/(double)total, "Repeat");
	
	        			}
		        		
					} 
				finally
					{
					if(in!=null) in.close();
					if(iter!=null) iter.close();
					}
		        
		        container1.close();
		        BufferedDataTable out1 = container1.getTable();
		        container1=null;
		        
		        container2.close();
		        BufferedDataTable out2 = container2.getTable();
		        container2=null;
		        
		        BufferedDataTable array[]= new BufferedDataTable[]{out1,out2};
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
			if(container1!=null) container1.close();
			if(container2!=null) container1.close();
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
    	findColumnIndex(in,"CHROM",StringCell.TYPE);
    	findColumnIndex(in,"POS",IntCell.TYPE);
    	return new DataTableSpec[]{in,in};
    	}

    @Override
    protected List<SettingsModel> getSettingsModel()
    	{
    	List<SettingsModel> L=new ArrayList<SettingsModel>(super.getSettingsModel());
    	L.add(this.m_maxRepeat);
    	return L;
    	}
    
	}


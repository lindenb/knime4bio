package fr.inserm.umr915.knime4ngs.nodes.vcf.extractsnpeff;

import java.util.ArrayList;
import java.util.List;



import org.knime.base.data.append.column.AppendedColumnRow;
import org.knime.core.data.DataCell;
import org.knime.core.data.DataColumnSpec;
import org.knime.core.data.DataColumnSpecCreator;
import org.knime.core.data.DataRow;
import org.knime.core.data.DataTableSpec;
import org.knime.core.data.DataType;
import org.knime.core.data.RowIterator;


import org.knime.core.data.def.StringCell;
import org.knime.core.node.BufferedDataContainer;
import org.knime.core.node.BufferedDataTable;
import org.knime.core.node.ExecutionContext;
import org.knime.core.node.InvalidSettingsException;
import org.knime.core.node.defaultnodesettings.SettingsModel;
import org.knime.core.node.defaultnodesettings.SettingsModelColumnName;
import fr.inserm.umr915.knime4ngs.nodes.vcf.AbstractVCFNodeModel;


/**
 * ExtractSnpEffNodeModel
 */
public class ExtractSnpEffNodeModel extends AbstractVCFNodeModel
	{
	static final String COL_NAMES[]=new String[]{
			"Effect",
			"Effefct_Impact",
			"Functional_Class",
			"Codon_Change",
			"Amino_Acid_change",
			"Gene_Name",
			"Gene_BioType",
			"Coding",
			"Transcript",
			"Exon",
			"ERRORS_WARNINGS"
			};

	final static String DEFAULT_INFO_COL="INFO";
	static final String INFO_COL_PROPERTY="info.col";
	private final SettingsModelColumnName m_infoCol =
        new SettingsModelColumnName(INFO_COL_PROPERTY,DEFAULT_INFO_COL);
	
	
    /**
     * Constructor for the node model.
     */
    protected ExtractSnpEffNodeModel()
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
			try
		    	{
		        // the data table spec of the single output table, 
		        // the table will have three columns:
				BufferedDataTable table1=inData[0];
		       
				DataTableSpec inDataTableSpec1 = table1.getDataTableSpec();
				int infoCol=findColumnIndex(inDataTableSpec1,m_infoCol,StringCell.TYPE);
				
				
				DataTableSpec merged=new DataTableSpec(inDataTableSpec1,createDataTableSpec());
		        container1 = exec.createDataContainer(merged);
		        double total=table1.getRowCount();
		        int nRow=0;
		        RowIterator iter1=null;
		       
		        try {
		        	iter1=table1.iterator();
		     
		        	while(iter1.hasNext())
		        		{
		        		++nRow;
		        		DataRow row=iter1.next();
		        		DataCell appendcells[]=new DataCell[COL_NAMES.length];
		        		DataCell infocell= row.getCell(infoCol);
		        		
		        		for(int tagIndex=0;tagIndex<COL_NAMES.length;++tagIndex)
		        			{
		        			appendcells[tagIndex]=DataType.getMissingCell();
		        			}
		        		
		        		while(!infocell.isMissing())
		        			{
		        			String infoContent=StringCell.class.cast(infocell).getStringValue();
		        			int effBeg=0;
		        			if(infoContent.startsWith("EFF="))
		        				{
		        				effBeg=0;
		        				}
		        			else if((effBeg=infoContent.indexOf(";EFF="))==-1)
		        				{
		        				break;
		        				}
		        			while(infoContent.charAt(effBeg)!='=') effBeg++;
		        			effBeg++;
		        			int opar=infoContent.indexOf('(',effBeg);
		        			if(opar==-1) break;
		        			String effect=infoContent.substring(effBeg,opar).trim();
		        			if(effect.isEmpty()) break;
		        			appendcells[0]=new StringCell(effect);
		        			int i=opar+1;
		        			int prev=i;
		        			int fieldIdx=1;
		        			boolean eof=false;
		        			for(;;)
		        				{
		        				if(i==infoContent.length() || infoContent.charAt(i)==')')
		        					{
		        					eof=true;
		        					}
		        				if(eof || infoContent.charAt(i)=='|')
		        					{
		        					if(fieldIdx>=COL_NAMES.length) break;
		        					String s2=infoContent.substring(prev, i).trim();
		        					if(!s2.isEmpty())
			        					{
					        			appendcells[fieldIdx]=new StringCell(s2);
			        					}
		        					if(eof) break;
		        					fieldIdx++;
		        					prev=i+1;
		        					}
		        				++i;
		        				}
		        			break;
		        			}
		        			
		        		container1.addRowToTable(new AppendedColumnRow(row,appendcells));
		        		exec.checkCanceled();
		            	exec.setProgress(nRow/total,"Extracting SnpEff....");
		        		}
		        	
					} 
		        catch (Exception e)
					{
		        	e.printStackTrace();
					throw e;
					}
				finally
					{
					safeClose(iter1);
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
    
    private DataTableSpec createDataTableSpec() throws InvalidSettingsException
    	{
    	DataColumnSpec cols[]=new DataColumnSpec[COL_NAMES.length];
    	for(int i=0;i< COL_NAMES.length;++i)
    		{
    		cols[i]=new DataColumnSpecCreator("SnpEff."+COL_NAMES[i],StringCell.TYPE).createSpec();
    		}
    	return new DataTableSpec(cols);
    	}
    
    @Override
    protected DataTableSpec[] configure(DataTableSpec[] inSpecs)
    		throws InvalidSettingsException {
    	if(inSpecs==null || inSpecs.length!=1)
    		{
    		throw new InvalidSettingsException("Expected one tables");
    		}
    	findColumnIndex(inSpecs[0],m_infoCol,StringCell.TYPE);
    	return new DataTableSpec[]{new DataTableSpec(inSpecs[0],createDataTableSpec())};
    	}
    
    @Override
    protected List<SettingsModel> getSettingsModel() {
    	List<SettingsModel> L=new ArrayList<SettingsModel>( super.getSettingsModel());
    	L.add(this.m_infoCol);
    	return L;
    	}
	}


package fr.inserm.umr915.knime4ngs.nodes.vcf.gn;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.knime.base.data.append.column.AppendedColumnRow;
import org.knime.core.data.DataRow;
import org.knime.core.data.DataTableSpec;
import org.knime.core.data.DataType;
import org.knime.core.data.container.CloseableRowIterator;
import org.knime.core.data.def.StringCell;
import org.knime.core.node.BufferedDataContainer;
import org.knime.core.node.BufferedDataTable;
import org.knime.core.node.ExecutionContext;
import org.knime.core.node.InvalidSettingsException;
import org.knime.core.node.defaultnodesettings.SettingsModel;
import org.knime.core.node.defaultnodesettings.SettingsModelString;



import fr.inserm.umr915.knime4ngs.nodes.vcf.AbstractVCFNodeModel;


@Deprecated
public class GeneNameNodeModel extends AbstractVCFNodeModel
	{
	static final String DEFAULT_FLAG_PROPERTY="GN";
	static final String FLAG_PROPERTY="vcf.info.flag.gn";
	static final String INFO_COL_PROPERTY="info.column";
	static final String DEFAULT_INFO_COL_PROPERTY="INFO";

	
	
	private final SettingsModelString m_flag =
        new SettingsModelString(FLAG_PROPERTY,DEFAULT_FLAG_PROPERTY);
	private final SettingsModelString m_colInfo =
        new SettingsModelString(INFO_COL_PROPERTY,DEFAULT_INFO_COL_PROPERTY);
	
	
    /**
     * Constructor for the node model.
     */
    protected GeneNameNodeModel()
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
			
				
				
				String flag=this.m_flag.getStringValue();
				if(flag==null || flag.isEmpty()) throw new IllegalArgumentException("bad FLAG");
				String flagEq=flag+"=";
				int infoColumn= inDataTableSpec.findColumnIndex(this.m_colInfo.getStringValue());
				if(infoColumn==-1) throw new IllegalArgumentException("Cannot find column \""+this.m_colInfo.getStringValue()+"\"");
		        
				DataTableSpec outDataTableSpec=new DataTableSpec(inDataTableSpec,new DataTableSpec(
		    			new String[]{m_flag.getStringValue()},
		    			new DataType[]{StringCell.TYPE}
		    			));
				
				container1 = exec.createDataContainer(outDataTableSpec);
		        container2 = exec.createDataContainer(outDataTableSpec);
		        
		        Pattern semicolon=Pattern.compile("[\\;]");
		        double total=inTable.getRowCount();
		        int nRow=0;
		        CloseableRowIterator iter=null;
		        try {
		        	iter=inTable.iterator();
		        	while(iter.hasNext())
		        		{
		        		++nRow;
		        		DataRow row=iter.next();
		        		String gn=null;
		        		String info=StringCell.class.cast(row.getCell(infoColumn)).getStringValue();
						for(String token:semicolon.split(info))
							{
							if(!token.startsWith(flagEq)) continue;
							gn= token.substring(flagEq.length());
							break;
							}	
						
		        		if(gn!=null && !gn.isEmpty())
							{
		        			row=new AppendedColumnRow(row,new StringCell(gn));
		        			container1.addRowToTable(row);
							}
						else
							{
							row=new AppendedColumnRow(row,new StringCell(""));
							container2.addRowToTable(row);
							}
		        		}
		        	exec.checkCanceled();
	            	exec.setProgress(nRow/total,"Extracting Gene Name....");
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
		        
				// once we are done, we close the container and return its table
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
			if(container2!=null) container2.close();
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
    	findColumnIndex(inSpecs[0],m_colInfo.getStringValue());
    	DataTableSpec spec2=new DataTableSpec(
    			new String[]{m_flag.getStringValue()},
    			new DataType[]{StringCell.TYPE}
    			);
    	DataTableSpec dataTableSpec3=new DataTableSpec(inSpecs[0],spec2);
    	return new DataTableSpec[]{dataTableSpec3,dataTableSpec3};
    	}
    
    @Override
    protected List<SettingsModel> getSettingsModel() {
    	List<SettingsModel> L=new ArrayList<SettingsModel>( super.getSettingsModel());
    	L.add(this.m_flag);
    	L.add(this.m_colInfo);
    	return L;
    	}
	}


package fr.inserm.umr915.knime4ngs.nodes.vcf.extractinfo;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import java.util.regex.Pattern;



import org.knime.base.data.append.column.AppendedColumnRow;
import org.knime.core.data.DataCell;
import org.knime.core.data.DataColumnSpec;
import org.knime.core.data.DataColumnSpecCreator;
import org.knime.core.data.DataRow;
import org.knime.core.data.DataTableSpec;
import org.knime.core.data.DataType;
import org.knime.core.data.RowIterator;

import org.knime.core.data.def.BooleanCell;
import org.knime.core.data.def.DoubleCell;
import org.knime.core.data.def.IntCell;
import org.knime.core.data.def.LongCell;
import org.knime.core.data.def.StringCell;
import org.knime.core.node.BufferedDataContainer;
import org.knime.core.node.BufferedDataTable;
import org.knime.core.node.ExecutionContext;
import org.knime.core.node.InvalidSettingsException;
import org.knime.core.node.defaultnodesettings.SettingsModel;
import org.knime.core.node.defaultnodesettings.SettingsModelColumnName;
import org.knime.core.node.defaultnodesettings.SettingsModelString;
import fr.inserm.umr915.knime4ngs.nodes.vcf.AbstractVCFNodeModel;


/**
 * ExtractInfoNodeModel
 */
public class ExtractInfoNodeModel extends AbstractVCFNodeModel
	{
	
	final static String DEFAULT_INFO_COL="INFO";
	static final String INFO_COL_PROPERTY="info.col";
	private final SettingsModelColumnName m_infoCol =
        new SettingsModelColumnName(INFO_COL_PROPERTY,DEFAULT_INFO_COL);
	
	
	final static String DEFAULT_FLAG="GN";
	static final String FLAG_PROPERTY="flag";
	private final SettingsModelString m_flag =
        new SettingsModelString(FLAG_PROPERTY,DEFAULT_FLAG);

	final static String DATATYPES[]=new String[]{"String","Integer","Long","Double","Boolean"};
	final static String DEFAULT_DATATYPE=DATATYPES[0];
	static final String DATATYPE_PROPERTY="type";
	private final SettingsModelString m_dataType =
        new SettingsModelString(DATATYPE_PROPERTY,DEFAULT_DATATYPE);

	
    /**
     * Constructor for the node model.
     */
    protected ExtractInfoNodeModel()
    	{
        super(1,1);
    	}
    
    private List<String> extractFields() throws InvalidSettingsException 
    	{
    	TreeSet<String> tags=new TreeSet<String>();
    	for(String S:this.m_flag.getStringValue().split("[ \n\t,;]+"))
    		{
    		S=S.trim();
    		if(S.isEmpty()) continue;
    		tags.add(S);
    		}
    	if(tags.isEmpty()) throw new InvalidSettingsException("No tag was defined");
    	return new ArrayList<String>(tags);
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
			    Pattern semicolon=Pattern.compile("[;]");
		       
				DataTableSpec inDataTableSpec1 = table1.getDataTableSpec();
				int infoCol=findColumnIndex(inDataTableSpec1,m_infoCol,StringCell.TYPE);
				
				
				DataTableSpec merged=new DataTableSpec(inDataTableSpec1,createDataTableSpec());
		        container1 = exec.createDataContainer(merged);
		        DataType datatype=getDataType();
		        final List<String> theTags=extractFields();
		        double total=table1.getRowCount();
		        int nRow=0;
		        RowIterator iter1=null;
		       
		        try {
		        	iter1=table1.iterator();
		     
		        	while(iter1.hasNext())
		        		{
		        		++nRow;
		        		DataRow row=iter1.next();
		        		DataCell appendcells[]=new DataCell[theTags.size()];
		        		DataCell infocell= row.getCell(infoCol);
		        		
		        		for(int tagIndex=0;tagIndex<theTags.size();++tagIndex)
		        			{
		        			appendcells[tagIndex]=DataType.getMissingCell();
		        			}
		        		
		        		if(!infocell.isMissing())
		        			{
		        			for(int tagIndex=0;tagIndex<theTags.size();++tagIndex)
			        			{
			        			String theTag=theTags.get(tagIndex);
			        			appendcells[tagIndex]=DataType.getMissingCell();
			        			String value=null;
			        			String tokens[]=semicolon.split(StringCell.class.cast(infocell).getStringValue());
			        			for(String s:tokens)
			        				{
			        				if(s.equals(theTag) && datatype.equals(BooleanCell.TYPE))
			        					{
			        					value="true";
			        					break;
			        					}
			        				if( s.startsWith(theTag) &&
			        					s.length()> theTag.length() &&
			        					s.charAt(theTag.length())=='=')
			        					{
			        					value=s.substring(theTag.length()+1).trim();
			        					break;
			        					}
			        				}
			        			if(value==null || value.isEmpty())
			        				{
			        				appendcells[tagIndex]=DataType.getMissingCell();
			        				}
			        			else if(datatype.equals(DoubleCell.TYPE))
			        				{
			        				try {
			        					appendcells[tagIndex]=new DoubleCell(Double.parseDouble(value));
										} 
			        				catch (Exception e)
										{
			        					appendcells[tagIndex]=DataType.getMissingCell();
										}
			        				}
			        			else if(datatype.equals(LongCell.TYPE))
			        				{
			        				try {
			        					appendcells[tagIndex]=new LongCell(Long.parseLong(value));
										} 
			        				catch (Exception e)
										{
			        					appendcells[tagIndex]=DataType.getMissingCell();
										}
			        				}
			        			else if(datatype.equals(IntCell.TYPE))
			        				{
			        				try {
			        					appendcells[tagIndex]=new IntCell(Integer.parseInt(value));
										} 
			        				catch (Exception e)
										{
			        					appendcells[tagIndex]=DataType.getMissingCell();
										}
			        				}
			        			else if(datatype.equals(BooleanCell.TYPE))
			        				{
			        				try {
			        					
			        					if(value.equals("1")) value="true";
			        					if(value.equals("0")) value="false";
			        					value=value.toLowerCase();
			        					if(value.equals("t")) value="true";
			        					if(value.equals("f")) value="false";
			        					if(value.equals("y")) value="true";
			        					if(value.equals("n")) value="false";
			        					appendcells[tagIndex]=Boolean.parseBoolean(value)?BooleanCell.TRUE:BooleanCell.FALSE;
										} 
			        				catch (Exception e)
										{
			        					appendcells[tagIndex]=DataType.getMissingCell();
										}
			        				}
			        			else
			        				{
			        				try {
			        					appendcells[tagIndex]=new StringCell(value);
										} 
			        				catch (Exception e)
										{
			        					appendcells[tagIndex]=DataType.getMissingCell();
										}
			        				}
			        			}
		        			}
		        		container1.addRowToTable(new AppendedColumnRow(row,appendcells));
		        		exec.checkCanceled();
		            	exec.setProgress(nRow/total,"Extracting INFO....");
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
    public DataType getDataType() throws InvalidSettingsException
    	{
    	if(m_dataType.getStringValue().equals("Long"))
    		{
    		return LongCell.TYPE;
    		}
    	else if(m_dataType.getStringValue().equals("Integer"))
    		{
    		return IntCell.TYPE;
    		}
    	else if(m_dataType.getStringValue().equals("Double"))
			{
			return DoubleCell.TYPE;
			}
    	else if(m_dataType.getStringValue().equals("Boolean"))
    		{
    		return BooleanCell.TYPE;
    		}
    	return StringCell.TYPE;
    	}
    
    private DataTableSpec createDataTableSpec() throws InvalidSettingsException
    	{
    	List<String> tags=extractFields();
    	if(tags.isEmpty()) throw new InvalidSettingsException("No tag was defined");
    	DataColumnSpec cols[]=new DataColumnSpec[tags.size()];
    	DataType t=getDataType();
    	for(int i=0;i< tags.size();++i)
    		{
    		cols[i]=new DataColumnSpecCreator(tags.get(i),t).createSpec();
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
    	L.add(this.m_dataType);
    	L.add(this.m_infoCol);
    	L.add(this.m_flag);
    	return L;
    	}
	}


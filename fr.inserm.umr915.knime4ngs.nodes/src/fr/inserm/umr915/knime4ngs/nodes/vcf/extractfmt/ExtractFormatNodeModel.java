package fr.inserm.umr915.knime4ngs.nodes.vcf.extractfmt;

import java.util.ArrayList;
import java.util.List;
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
public class ExtractFormatNodeModel extends AbstractVCFNodeModel
	{
	
	final static String DEFAULT_FORMAT_COLUMN="FORMAT";
	static final String FORMAT_COL_PROPERTY="format.col";
	private final SettingsModelColumnName m_formatCol =
        new SettingsModelColumnName(FORMAT_COL_PROPERTY,DEFAULT_FORMAT_COLUMN);
	
	
	final static String DEFAULT_CALL_COLUMN="CALL";
	static final String CALL_COL_PROPERTY="call.col";
	private final SettingsModelColumnName m_callCol =
        new SettingsModelColumnName(CALL_COL_PROPERTY,DEFAULT_CALL_COLUMN);
	
	
	final static String DEFAULT_FLAG="GQ";
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
    protected ExtractFormatNodeModel()
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
			    Pattern colon=Pattern.compile("[\\:]");
		       
				DataTableSpec inDataTableSpec1 = table1.getDataTableSpec();
				int formatCol=findColumnIndex(inDataTableSpec1,m_formatCol,StringCell.TYPE);
				int callCol=findColumnIndex(inDataTableSpec1,m_callCol,StringCell.TYPE);
				
				DataTableSpec merged=new DataTableSpec(inDataTableSpec1,createDataTableSpec());
		        container1 = exec.createDataContainer(merged);
		        DataType datatype=getDataType();
		        final String theTag=m_flag.getStringValue().trim();
		        double total=table1.getRowCount();
		        int nRow=0;
		        RowIterator iter1=null;
		       
		        try {
		        	iter1=table1.iterator();
		     
		        	while(iter1.hasNext())
		        		{
		        		++nRow;
		        		DataRow row=iter1.next();
		        		DataCell appendcell=DataType.getMissingCell();
		        		DataCell fmtCell= row.getCell(formatCol);
		        		DataCell callCell= row.getCell(callCol);
		        		if(!(fmtCell.isMissing() || callCell.isMissing()))
		        			{
		        			String tokens[]=colon.split(StringCell.class.cast(fmtCell).getStringValue());
		        			int tagIndex=-1;
		        			for(int i=0;i< tokens.length;++i)
		        				{
		        				if(tokens[i].equals(theTag))
		        					{
		        					tagIndex=i;
		        					break;
		        					}
		        				}
		        			if(tagIndex!=-1)
		        				{
		        				tokens=colon.split(StringCell.class.cast(callCell).getStringValue());
		        				if(tagIndex<tokens.length)
		        					{
		        					String value=tokens[tagIndex];
		        					

				        			if(value.isEmpty())
				        				{
				        				appendcell=DataType.getMissingCell();
				        				}
				        			else if(datatype.equals(DoubleCell.TYPE))
				        				{
				        				try {
				        					appendcell=new DoubleCell(Double.parseDouble(value));
											} 
				        				catch (Exception e)
											{
											appendcell=DataType.getMissingCell();
											}
				        				}
				        			else if(datatype.equals(LongCell.TYPE))
				        				{
				        				try {
				        					appendcell=new LongCell(Long.parseLong(value));
											} 
				        				catch (Exception e)
											{
											appendcell=DataType.getMissingCell();
											}
				        				}
				        			else if(datatype.equals(IntCell.TYPE))
				        				{
				        				try {
				        					appendcell=new IntCell(Integer.parseInt(value));
											} 
				        				catch (Exception e)
											{
											appendcell=DataType.getMissingCell();
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
				        					appendcell=Boolean.parseBoolean(value)?BooleanCell.TRUE:BooleanCell.FALSE;
											} 
				        				catch (Exception e)
											{
											appendcell=DataType.getMissingCell();
											}
				        				}
				        			else
				        				{
				        				try {
				        					appendcell=new StringCell(value);
											} 
				        				catch (Exception e)
											{
											appendcell=DataType.getMissingCell();
											}
				        				}
				        			}
	        					}
	        				}
		        		container1.addRowToTable(new AppendedColumnRow(row,appendcell));
		        		exec.checkCanceled();
		            	exec.setProgress(nRow/total,"Extracting FORMAT....");
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
    	if(m_flag.getStringValue().trim().isEmpty()) throw new InvalidSettingsException("Empty flag");
    	DataColumnSpec cols[]=new DataColumnSpec[1];
    	cols[0]=new DataColumnSpecCreator(m_flag.getStringValue(),getDataType()).createSpec();
    	return new DataTableSpec(cols);
    	}
    
    @Override
    protected DataTableSpec[] configure(DataTableSpec[] inSpecs)
    		throws InvalidSettingsException {
    	if(inSpecs==null || inSpecs.length!=1)
    		{
    		throw new InvalidSettingsException("Expected one tables");
    		}
    	findColumnIndex(inSpecs[0],m_formatCol,StringCell.TYPE);
    	findColumnIndex(inSpecs[0],m_callCol,StringCell.TYPE);
    	return new DataTableSpec[]{new DataTableSpec(inSpecs[0],createDataTableSpec())};
    	}
    
    @Override
    protected List<SettingsModel> getSettingsModel() {
    	List<SettingsModel> L=new ArrayList<SettingsModel>( super.getSettingsModel());
    	L.add(this.m_dataType);
    	L.add(this.m_formatCol);
    	L.add(this.m_flag);
    	L.add(this.m_callCol);
    	return L;
    	}
	}


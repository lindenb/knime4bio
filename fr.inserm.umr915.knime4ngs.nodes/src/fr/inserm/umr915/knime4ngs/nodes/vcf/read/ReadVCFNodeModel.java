package fr.inserm.umr915.knime4ngs.nodes.vcf.read;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.knime.core.data.DataCell;
import org.knime.core.data.DataColumnSpec;
import org.knime.core.data.DataColumnSpecCreator;
import org.knime.core.data.DataRow;
import org.knime.core.data.DataTableSpec;
import org.knime.core.data.DataType;
import org.knime.core.data.RowKey;
import org.knime.core.data.container.CloseableRowIterator;
import org.knime.core.data.def.DefaultRow;
import org.knime.core.data.def.DoubleCell;
import org.knime.core.data.def.IntCell;
import org.knime.core.data.def.StringCell;
import org.knime.core.node.BufferedDataContainer;
import org.knime.core.node.BufferedDataTable;
import org.knime.core.node.ExecutionContext;
import org.knime.core.node.InvalidSettingsException;
import org.knime.core.node.defaultnodesettings.SettingsModel;
import org.knime.core.node.defaultnodesettings.SettingsModelColumnName;
import org.knime.core.node.defaultnodesettings.SettingsModelInteger;
import fr.inserm.umr915.knime4ngs.nodes.vcf.AbstractVCFNodeModel;


/**
 * This is the model implementation of VCFLoader.
 * Loads a VCF File
 *
 * @author Pierre Lindenbaum
 */
public class ReadVCFNodeModel extends AbstractVCFNodeModel
	{
	static final String MIN_QUAL_PROPERTY="min.qual";
     static final int DEFAULT_MIN_QUAL=10;
	
	private final SettingsModelInteger m_minQual =
        new SettingsModelInteger(MIN_QUAL_PROPERTY,DEFAULT_MIN_QUAL);

	
	//SAMPLE_?A%E
	
	static final String SAMPLE_COL_DEFAULT="Sample";
	static final String SAMPLE_COL_PROPERTY="sampleName";
	private final SettingsModelColumnName m_sampleColumn =  new SettingsModelColumnName(SAMPLE_COL_PROPERTY,SAMPLE_COL_DEFAULT);
	//VCF URL
	static final String VCF_COL_DEFAULT="URI";
	static final String VCF_COL_PROPERTY="vcf.uri";
	private final SettingsModelColumnName m_vcfColumn =  new SettingsModelColumnName( VCF_COL_PROPERTY,VCF_COL_PROPERTY);
	
	
    public ReadVCFNodeModel()
    	{
        super(1, 2);
    	}

    private DataTableSpec createVcfHeaderDataColumnSpec()
    	{
    	DataColumnSpec[] allColSpecs = new DataColumnSpec[3];
		allColSpecs[0] =  new DataColumnSpecCreator("SAMPLE", StringCell.TYPE).createSpec();
		allColSpecs[1] =  new DataColumnSpecCreator("URI", StringCell.TYPE).createSpec();
		allColSpecs[2] =  new DataColumnSpecCreator("HEADER", StringCell.TYPE).createSpec();
		return new DataTableSpec(allColSpecs);
    	}
    
    private DataTableSpec createVcfDataColumnSpec()
		{
		DataColumnSpec[] allColSpecs = new DataColumnSpec[11];
		allColSpecs[0] =  new DataColumnSpecCreator("SAMPLE", StringCell.TYPE).createSpec();
	    allColSpecs[1] =  new DataColumnSpecCreator("CHROM", StringCell.TYPE).createSpec();
	    allColSpecs[2] =  new DataColumnSpecCreator("POS", IntCell.TYPE).createSpec();
	    allColSpecs[3] =  new DataColumnSpecCreator("ID", StringCell.TYPE).createSpec();
	    allColSpecs[4] =  new DataColumnSpecCreator("REF", StringCell.TYPE).createSpec();
	    allColSpecs[5] =  new DataColumnSpecCreator("ALT", StringCell.TYPE).createSpec();
	    allColSpecs[6] =  new DataColumnSpecCreator("QUAL", DoubleCell.TYPE).createSpec();
	    allColSpecs[7] =  new DataColumnSpecCreator("FILTER", StringCell.TYPE).createSpec();
	    allColSpecs[8] =  new DataColumnSpecCreator("INFO", StringCell.TYPE).createSpec();
	    allColSpecs[9] =  new DataColumnSpecCreator("FORMAT", StringCell.TYPE).createSpec();
	    allColSpecs[10] = new DataColumnSpecCreator("CALL", StringCell.TYPE).createSpec();
	    return new DataTableSpec( allColSpecs);
		}

    /**
     * {@inheritDoc}
     */
    @Override
    protected BufferedDataTable[] execute(final BufferedDataTable[] inData,
            final ExecutionContext exec) throws Exception
        {
    	if(inData==null || inData.length==0) throw new IllegalArgumentException("bad input");
    	BufferedDataTable inTable=inData[0];
    	BufferedReader reader=null;
    	CloseableRowIterator iter=null;
    	Pattern tab=Pattern.compile("[\t]");
    	BufferedDataContainer container1=null;
    	BufferedDataContainer container2=null;
    	int nRows1=0;
    	int nRows2=0;
    	int sampleCol= this.findColumnIndex(inTable.getDataTableSpec(), this.m_sampleColumn.getStringValue());
    	int uriCol= this.findColumnIndex(inTable.getDataTableSpec(), this.m_vcfColumn.getStringValue());
    	
    	try
	    	{
    		container1 = exec.createDataContainer(createVcfDataColumnSpec());
    		container2 = exec.createDataContainer(createVcfHeaderDataColumnSpec());
    		
	    	iter=inTable.iterator();
	    	while(iter.hasNext())
	    		{
	    		DataRow row=iter.next();
	    		StringCell sample= StringCell.class.cast(row.getCell(sampleCol));
	    		String uri=StringCell.class.cast(row.getCell(uriCol)).getStringValue();
				reader=openReader(uri);
				String line;
				
				while((line=reader.readLine())!=null)
					{
					if(line.startsWith("#"))
						{
						if(line.startsWith("##"))
							{
							nRows2++;
							container2.addRowToTable(new DefaultRow(
								RowKey.createRowKey(nRows2),
								sample,
								row.getCell(uriCol),
								new StringCell(line.substring(2))
								));
							}
						continue;
						}
					
					
					String tokens[]=tab.split(line);
					if(tokens.length<9)
						{
						throw new IOException("Error "+line);
						}
					
					
					Float qual=null;
					
						
					try {
						qual=Float.parseFloat(tokens[5]);
						} 
					catch (Exception e)
						{
						e.printStackTrace();
						getLogger().info("Cannot parse "+tokens[5]);
						qual=null;
						}
						
					if(	qual!=null &&
	        			qual< m_minQual.getIntValue())
	        			{
						continue;
	        			}
					
					DataCell[] cells = new DataCell[11];
					cells[0] = sample;
					cells[1] = new StringCell(tokens[0]); //chrom
		            cells[2] = new IntCell(Integer.parseInt(tokens[1])); //pos
		            cells[3] = new StringCell(tokens[2].equals(".")?"":tokens[2]);//id
		            cells[4] = new StringCell(tokens[3].toUpperCase());//ref
		            cells[5] = new StringCell(tokens[4].toUpperCase());//alt
		            cells[6] = new DoubleCell(Double.parseDouble(tokens[5]));//qual
		            cells[7] = (tokens[6].isEmpty() || tokens[6].equals(".")? DataType.getMissingCell():new StringCell(tokens[6]));//filter
		            cells[8] = new StringCell(tokens[7]);//info
		            cells[9] = new StringCell(tokens[8]);//format
		            cells[10] = new StringCell(tokens[9]);//call
		           
		            ++nRows1;
			        container1.addRowToTable(new DefaultRow(
			        		RowKey.createRowKey(nRows1),
			        		cells));
			    
		            // check if the execution monitor was canceled
			        exec.checkCanceled();
		            exec.setProgress("Adding row " + nRows1);

					}
				reader.close();
				reader=null;
	    		}
	    	safeClose(iter);
	    	iter=null;
	    	safeClose(container1);
	    	safeClose(container2);
	    	BufferedDataTable out1 = container1.getTable();
	    	BufferedDataTable out2 = container2.getTable();
	    	
	        container1=null;
	        container2=null;
	        return new BufferedDataTable[]{out1,out2};
	    	}
    	catch (Exception e)
	    	{
			e.printStackTrace();
			throw e;
			}
    	finally
    		{
    		safeClose(container1);
	    	safeClose(container2);
    		}
    	}

    /**
     * {@inheritDoc}
     */
    @Override
    protected DataTableSpec[] configure(final DataTableSpec[] inSpecs)
            throws InvalidSettingsException {
    	getLogger().info("calling configure");
        if(inSpecs==null || inSpecs.length!=1)
        	{
        	throw new InvalidSettingsException("Expect one input.");
        	}
  

    	return new DataTableSpec[]{
    		createVcfDataColumnSpec(),
    		createVcfHeaderDataColumnSpec(),
    		};
    	}

    @Override
    protected List<SettingsModel> getSettingsModel()
    	{
    	getLogger().info("calling get settings model");
    	List<SettingsModel> L=new ArrayList<SettingsModel>(super.getSettingsModel());
    	L.add(this.m_minQual);
    	L.add(this.m_sampleColumn);
    	L.add(this.m_vcfColumn);
    	return L;
    	}

	}


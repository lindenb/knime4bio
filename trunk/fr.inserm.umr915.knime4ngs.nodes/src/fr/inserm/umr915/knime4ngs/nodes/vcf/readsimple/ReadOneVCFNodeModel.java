package fr.inserm.umr915.knime4ngs.nodes.vcf.readsimple;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.knime.core.data.DataCell;
import org.knime.core.data.DataColumnSpec;
import org.knime.core.data.DataColumnSpecCreator;
import org.knime.core.data.DataTableSpec;
import org.knime.core.data.DataType;
import org.knime.core.data.RowKey;
import org.knime.core.data.def.DefaultRow;
import org.knime.core.data.def.DoubleCell;
import org.knime.core.data.def.IntCell;
import org.knime.core.data.def.StringCell;
import org.knime.core.node.BufferedDataContainer;
import org.knime.core.node.BufferedDataTable;
import org.knime.core.node.ExecutionContext;
import org.knime.core.node.InvalidSettingsException;
import org.knime.core.node.defaultnodesettings.SettingsModel;
import org.knime.core.node.defaultnodesettings.SettingsModelInteger;
import org.knime.core.node.defaultnodesettings.SettingsModelString;

import fr.inserm.umr915.knime4ngs.nodes.vcf.AbstractVCFNodeModel;


/**
 * This is the model implementation of VCFLoader.
 * Loads a VCF File
 *
 * @author Pierre Lindenbaum
 */
public class ReadOneVCFNodeModel extends AbstractVCFNodeModel
	{
	static final String MIN_QUAL_PROPERTY="min.qual";
     static final int DEFAULT_MIN_QUAL=10;
	
	private final SettingsModelInteger m_minQual =
        new SettingsModelInteger(MIN_QUAL_PROPERTY,DEFAULT_MIN_QUAL);

	
	//SAMPLE_?A%E
	
	static final String SAMPLE_COL_DEFAULT="Sample";
	static final String SAMPLE_COL_PROPERTY="sampleName";
	private final SettingsModelString m_sampleColumn =  new SettingsModelString(SAMPLE_COL_PROPERTY,SAMPLE_COL_DEFAULT);
	//VCF URL
	static final String VCF_COL_DEFAULT="file.vcf";
	static final String VCF_COL_PROPERTY="vcf.uri";
	private final SettingsModelString m_vcfColumn =  new SettingsModelString( VCF_COL_PROPERTY,VCF_COL_PROPERTY);
	
	
    public ReadOneVCFNodeModel()
    	{
        super(0, 2);
    	}

    
    private DataTableSpec createVcfHeaderDataColumnSpec()
    	{
 
    	DataColumnSpec[] allColSpecs = new DataColumnSpec[2];
    	allColSpecs[0] =  new DataColumnSpecCreator("URI", StringCell.TYPE).createSpec();
		allColSpecs[1] =  new DataColumnSpecCreator("HEADER", StringCell.TYPE).createSpec();
		return new DataTableSpec(allColSpecs);
    	}
    
    private DataTableSpec createVcfDataColumnSpec()
		{
		DataColumnSpec[] allColSpecs = new DataColumnSpec[11];
		
	    allColSpecs[0] =  new DataColumnSpecCreator("CHROM", StringCell.TYPE).createSpec();
	    allColSpecs[1] =  new DataColumnSpecCreator("POS", IntCell.TYPE).createSpec();
	    allColSpecs[2] =  new DataColumnSpecCreator("ID", StringCell.TYPE).createSpec();
	    allColSpecs[3] =  new DataColumnSpecCreator("REF", StringCell.TYPE).createSpec();
	    allColSpecs[4] =  new DataColumnSpecCreator("ALT", StringCell.TYPE).createSpec();
	    allColSpecs[5] =  new DataColumnSpecCreator("QUAL", DoubleCell.TYPE).createSpec();
	    allColSpecs[6] =  new DataColumnSpecCreator("FILTER", StringCell.TYPE).createSpec();
	    allColSpecs[7] =  new DataColumnSpecCreator("INFO", StringCell.TYPE).createSpec();
	    allColSpecs[8] =  new DataColumnSpecCreator("FORMAT", StringCell.TYPE).createSpec();
	    allColSpecs[9] = new DataColumnSpecCreator("CALL", StringCell.TYPE).createSpec();
		allColSpecs[10] =  new DataColumnSpecCreator("SAMPLE", StringCell.TYPE).createSpec();
	    return new DataTableSpec( allColSpecs);
		}

    /**
     * {@inheritDoc}
     */
    @Override
    protected BufferedDataTable[] execute(final BufferedDataTable[] inData,
            final ExecutionContext exec) throws Exception
        {
    	if(inData!=null && inData.length!=0) throw new IllegalArgumentException("bad input");
    	BufferedReader reader=null;
    	Pattern tab=Pattern.compile("[\t]");
    	BufferedDataContainer container1=null;
    	BufferedDataContainer container2=null;
    	int nRows1=0;
    	int nRows2=0;
    
    	try
	    	{
    		container1 = exec.createDataContainer(createVcfDataColumnSpec());
    		container2 = exec.createDataContainer(createVcfHeaderDataColumnSpec());
    		StringCell sample=null;

	    	
    		String uri=this.m_vcfColumn.getStringValue();
			reader=openReader(uri);
			String line;
			
			while((line=reader.readLine())!=null)
				{
				if(line.startsWith("#"))
					{
					if(line.startsWith("##"))
						{
						nRows2++;
						DataCell cells[]=new DataCell[2];
						
						cells[0]=new StringCell(uri);
						cells[1]=new StringCell(line.substring(2));
						
						container2.addRowToTable(new DefaultRow(
							RowKey.createRowKey(nRows2),
							cells
							));
						}
					else
						{
						final String H="#CHROM\tPOS\tID\tREF\tALT\tQUAL\tFILTER\tINFO\tFORMAT";
						if(!line.startsWith(H))
							{
							throw new IOException("Expected VCF header to start with \n"+H+" but got "+line);
							}
						String tokens[]=tab.split(line);
						if(tokens.length<10) throw new IOException("Column for call missing in "+line);
						if(!this.m_sampleColumn.getStringValue().trim().isEmpty())
							{
							sample=new StringCell(this.m_sampleColumn.getStringValue().trim());
							}
						else
							{
							sample=new StringCell(tokens[9].trim());
							}	
						}
					continue;
					}
				
				if(sample==null) throw new IOException("Header was not found.");
				
				String tokens[]=tab.split(line);
				if(tokens.length<9)
					{
					StringWriter sw=new StringWriter();
					for(int i=0;i< tokens.length;++i)
						{
						sw.append("\n("+i+")\t\""+tokens[i]+"\"");
						}
					throw new IOException("Error expected 9 columns but got "+tokens.length +" in "+line+ sw);
					}
				
				
				Float qual=null;
				
					
				try {
					if(!(tokens[5].equals(".") || tokens[5].isEmpty()))
						{
						qual=Float.parseFloat(tokens[5]);
						}
					} 
				catch (Exception e)
					{
					e.printStackTrace();
					getLogger().info("Cannot parse QUAL:"+tokens[5]);
					qual=null;
					}
					
				if(	qual!=null &&
        			qual< m_minQual.getIntValue())
        			{
					continue;
        			}
				
				DataCell[] cells = new DataCell[11];
				
				cells[0] = new StringCell(tokens[0]); //chrom
	            cells[1] = new IntCell(Integer.parseInt(tokens[1])); //pos
	            cells[2] = new StringCell(tokens[2].equals(".")?"":tokens[2]);//id
	            cells[3] = new StringCell(tokens[3].toUpperCase());//ref
	            cells[4] = new StringCell(tokens[4].toUpperCase());//alt
	            cells[5] = new DoubleCell(Double.parseDouble(tokens[5]));//qual
	            cells[6] = (tokens[6].isEmpty() || tokens[6].equals(".")? DataType.getMissingCell():new StringCell(tokens[6]));//filter
	            cells[7] = new StringCell(tokens[7]);//info
	            cells[8] = new StringCell(tokens[8]);//format
	            cells[9] = new StringCell(tokens[9]);//call
	            cells[10] = sample;
		        ++nRows1;
		        container1.addRowToTable(new DefaultRow(
		        		RowKey.createRowKey(nRows1),
		        		cells));
		        
	            // check if the execution monitor was canceled
		        if(nRows1%10000==0)
		        	{
		        	exec.checkCanceled();
		        	exec.setProgress("rows: "+nRows1);
		        	}
				}
			reader.close();
			reader=null;
				
	    		
	    	
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
	    	safeClose(reader);
    		}
    	}

    /**
     * {@inheritDoc}
     */
    @Override
    protected DataTableSpec[] configure(final DataTableSpec[] inSpecs)
            throws InvalidSettingsException
        {
        if(inSpecs!=null && inSpecs.length!=0)
        	{
        	throw new InvalidSettingsException("Expected zero input.");
        	}
  

    	return new DataTableSpec[]{
    		createVcfDataColumnSpec(),
    		createVcfHeaderDataColumnSpec(),
    		};
    	}

    @Override
    protected List<SettingsModel> getSettingsModel()
    	{
    	List<SettingsModel> L=new ArrayList<SettingsModel>(super.getSettingsModel());
    	L.add(this.m_minQual);
    	L.add(this.m_sampleColumn);
    	L.add(this.m_vcfColumn);
    	return L;
    	}

	}


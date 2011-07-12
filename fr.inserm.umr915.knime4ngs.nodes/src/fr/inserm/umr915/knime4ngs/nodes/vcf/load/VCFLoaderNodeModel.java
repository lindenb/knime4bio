package fr.inserm.umr915.knime4ngs.nodes.vcf.load;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.knime.core.data.DataCell;
import org.knime.core.data.DataColumnSpec;
import org.knime.core.data.DataColumnSpecCreator;
import org.knime.core.data.DataRow;
import org.knime.core.data.DataTableSpec;
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


@Deprecated
public class VCFLoaderNodeModel extends AbstractVCFNodeModel
	{
	final static int DEFAULT_MIN_DEPTH=4;
	final static int DEFAULT_MAX_DEPTH=200;
	static final String MIN_DEPTH_PROPERTY="min.depth";
	static final String MAX_DEPTH_PROPERTY="max.depth";
	
	private final SettingsModelInteger m_minDepth =
        new SettingsModelInteger(MIN_DEPTH_PROPERTY,DEFAULT_MIN_DEPTH);
	private final SettingsModelInteger m_maxDepth =
        new SettingsModelInteger(MAX_DEPTH_PROPERTY,DEFAULT_MAX_DEPTH);
	
	//SAMPLE_?A%E
	
	static final String SAMPLE_COL_DEFAULT="Sample";
	static final String SAMPLE_COL_PROPERTY="sampleName";
	private final SettingsModelColumnName m_sampleColumn =  new SettingsModelColumnName(SAMPLE_COL_PROPERTY,SAMPLE_COL_DEFAULT);
	//VCF URL
	static final String VCF_COL_DEFAULT="vcf";
	static final String VCF_COL_PROPERTY="sampleVcf";
	private final SettingsModelColumnName m_vcfColumn =  new SettingsModelColumnName( VCF_COL_PROPERTY,VCF_COL_PROPERTY);
	
	
    public VCFLoaderNodeModel()
    	{
        super(1, 1);
    	}

    private DataColumnSpec[] createVcfDataColumnSpec()
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
	    return allColSpecs;
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
    	Pattern semicolon=Pattern.compile("[\\;]");
    	BufferedDataContainer container=null;
    	int nRows=0;
    	int sampleCol= this.findColumnIndex(inTable.getDataTableSpec(), this.m_sampleColumn.getStringValue());
    	int vcfCol= this.findColumnIndex(inTable.getDataTableSpec(), this.m_vcfColumn.getStringValue());
    	
    	try
	    	{
    		DataTableSpec dataTableSpec=new DataTableSpec(createVcfDataColumnSpec());
    		container = exec.createDataContainer(dataTableSpec);
    		
	    	iter=inTable.iterator();
	    	while(iter.hasNext())
	    		{
	    		DataRow row=iter.next();
	    		StringCell sample= StringCell.class.cast(row.getCell(sampleCol));
				
	    		
	    		String uri="http://srv-clc-02.irt.univ-nantes.prive:8080/biomachin/samples?vcf="+
	    			URLEncoder.encode(StringCell.class.cast(row.getCell(vcfCol)).getStringValue(),"UTF-8")+
	    			"&sample="+
	    			URLEncoder.encode(StringCell.class.cast(row.getCell(sampleCol)).getStringValue(),"UTF-8")
	    			;
			
				
				reader=openReader(uri);
				String line;
				
				while((line=reader.readLine())!=null)
					{
					if(line.startsWith("#")) continue;
					
					++nRows;
					String tokens[]=tab.split(line);
					if(tokens.length<5) throw new IOException("Error "+line);
					
					
					Integer depth=null;
					for(String token:semicolon.split(tokens[7]))//INFO
						{
						if(!token.startsWith("DP=")) continue;
						try {
							depth=Integer.parseInt(token.substring(3));
							} 
						catch (Exception e)
							{
							e.printStackTrace();
							getLogger().info("Cannot parse "+token);
							depth=null;
							}
						}
					if(	depth!=null &&
	        			(depth< m_minDepth.getIntValue() ||
	        			depth> m_maxDepth.getIntValue()))
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
		            cells[7] = new StringCell(tokens[6]);//filter
		            cells[8] = new StringCell(tokens[7]);//info
		            cells[9] = new StringCell(tokens[8]);//format
		            cells[10] = new StringCell(tokens[9]);//call
		           
		            DataRow newrow = new DefaultRow(RowKey.createRowKey(nRows), cells);
			        container.addRowToTable(newrow);
			    
		            // check if the execution monitor was canceled
			        exec.checkCanceled();
		            if(nRows%1000000==0)
		            	{
		            	exec.setProgress("Adding row " + nRows);
		            	//TODO
		            	break;
		            	}
					
					}
				reader.close();
				reader=null;
	    		}
	    	container.close();
	        BufferedDataTable out = container.getTable();
	        
	        container=null;
	        
	        
	        return new BufferedDataTable[]{out};
	    	} catch (Exception e) {
			e.printStackTrace();
			throw e;
			}
    	finally
    		{
    		
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
    	DataTableSpec tableSpec=inSpecs[0];
    	//check two columns (URI & samples)
    	if(tableSpec.getNumColumns()<2)
    		{
    		throw new InvalidSettingsException("Expect two columns from "+ tableSpec.getName()+" but got "+tableSpec.getNumColumns());
    		}

    	return new DataTableSpec[]{new DataTableSpec(createVcfDataColumnSpec())};
    	}

    @Override
    protected List<SettingsModel> getSettingsModel()
    	{
    	getLogger().info("calling get settings model");
    	List<SettingsModel> L=new ArrayList<SettingsModel>(super.getSettingsModel());
    	L.add(this.m_minDepth);
    	L.add(this.m_maxDepth);
    	L.add(this.m_sampleColumn);
    	L.add(this.m_vcfColumn);
    	
    	return L;
    	}

	}


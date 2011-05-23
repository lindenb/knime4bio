package fr.inserm.umr915.knime4ngs.nodes.vcf.context.sam;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.List;

import net.sf.picard.reference.IndexedFastaSequenceFile;

import org.knime.base.data.append.column.AppendedColumnRow;
import org.knime.core.data.DataColumnSpec;
import org.knime.core.data.DataColumnSpecCreator;
import org.knime.core.data.DataRow;
import org.knime.core.data.DataTableSpec;
import org.knime.core.data.container.CloseableRowIterator;
import org.knime.core.data.def.IntCell;
import org.knime.core.data.def.StringCell;
import org.knime.core.node.BufferedDataContainer;
import org.knime.core.node.BufferedDataTable;
import org.knime.core.node.ExecutionContext;
import org.knime.core.node.InvalidSettingsException;
import org.knime.core.node.defaultnodesettings.SettingsModel;
import org.knime.core.node.defaultnodesettings.SettingsModelColumnName;
import org.knime.core.node.defaultnodesettings.SettingsModelInteger;
import org.knime.core.node.defaultnodesettings.SettingsModelString;



import fr.inserm.umr915.knime4ngs.nodes.vcf.AbstractVCFNodeModel;





/**
 * @author Pierre Lindenbaum
 */
public class SamContextNodeModel extends AbstractVCFNodeModel
	{
	/** chrom column */
	static final String CHROM_COL_PROPERTY="chrom.col";
	static final String CHROM_COL_DEFAULT="CHROM";
	private final SettingsModelColumnName m_chromColumn = new SettingsModelColumnName(
			CHROM_COL_PROPERTY,
			CHROM_COL_DEFAULT
			);
	
	/** pos column */
	static final String POS_COL_PROPERTY="pos.col";
	static final String POS_COL_DEFAULT="POS";
	private final SettingsModelColumnName m_posColumn = new SettingsModelColumnName(
			POS_COL_PROPERTY,
			POS_COL_DEFAULT
			);

	final static int DEFAULT_EXTEND=5;
	static final String EXTEND_PROPERTY="extend";
	
	private final SettingsModelInteger m_extend =
        new SettingsModelInteger(EXTEND_PROPERTY,DEFAULT_EXTEND);
	
	
	/** das URI */
	static final String REFERENCE_URI_PROPERTY="ref.genome";
	static final String DEFAULT_REFERENCE_URI="hg19.fa";
	private final SettingsModelString m_refUri =new SettingsModelString(REFERENCE_URI_PROPERTY,DEFAULT_REFERENCE_URI);
	
	
	
    /**
     * Constructor for the node model.
     */
    protected SamContextNodeModel()
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
				BufferedDataTable inTable=inData[0];
		       
				DataTableSpec inDataTableSpec = inTable.getDataTableSpec();
				container1 = exec.createDataContainer(createSpec(inDataTableSpec));
				
				
			
				int chromColumn= inDataTableSpec.findColumnIndex(this.m_chromColumn.getStringValue());
				int pos0Column= inDataTableSpec.findColumnIndex(this.m_posColumn.getStringValue());
				int extend=this.m_extend.getIntValue();
				if(extend<=0) extend=0;
		       
				
				File fasta=new File(m_refUri.getStringValue());
				net.sf.picard.reference.IndexedFastaSequenceFile indexedFasta=new IndexedFastaSequenceFile(fasta);
			
		        
		        double total=inTable.getRowCount();
		        int nRow=0;
		        CloseableRowIterator iter=null;
		        try {
		        	iter=inTable.iterator();
		        	while(iter.hasNext())
		        		{
		        		++nRow;
		        		DataRow row=iter.next();
		        		
		        		String chrom=StringCell.class.cast(row.getCell(chromColumn)).getStringValue();
		        		int pos0=IntCell.class.cast(row.getCell(pos0Column)).getIntValue();
		        		
		        		int leftLen=extend;
		        		if(pos0-leftLen<1) leftLen=pos0-1;
		        	
		        		String dna=new String(indexedFasta.getSubsequenceAt(chrom,pos0-leftLen,pos0+extend).getBases());
		     			
		        		row=new AppendedColumnRow(row,
									new StringCell(dna.substring(0, leftLen)),
									new StringCell(dna.substring(leftLen,leftLen+1)),
									new StringCell(dna.substring(leftLen+1))
									);
						
						
						container1.addRowToTable(row);
						
		        		}
		        	exec.checkCanceled();
	            	exec.setProgress(nRow/total,"Getting DNA context");
					} 
		        catch (Exception e)
					{
		        	e.printStackTrace();
					throw e;
					}
				finally
					{
					 safeClose(iter);
					}
		        
				// once we are done, we close the container and return its table
		        safeClose(container1);
		        BufferedDataTable out1 = container1.getTable();
		        container1=null;
		        
		        
		        return new BufferedDataTable[]{out1};
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
    
    @Override
    protected DataTableSpec[] configure(DataTableSpec[] inSpecs)
    		throws InvalidSettingsException
    	{
    	if(inSpecs==null || inSpecs.length!=1)
    		{
    		throw new InvalidSettingsException("Expected one table");
    		}
    	File f=new File(m_refUri.getStringValue());
    	if(!f.exists()) throw new InvalidSettingsException(new FileNotFoundException(m_refUri.getStringValue()));
    	findColumnIndex(inSpecs[0], m_chromColumn,StringCell.TYPE);
    	findColumnIndex(inSpecs[0], m_posColumn,IntCell.TYPE);
    	return new DataTableSpec[]{createSpec(inSpecs[0])};
    	}
    
    private DataTableSpec createSpec(DataTableSpec in)
    	{
    	return new DataTableSpec(in,
    			new DataTableSpec(new DataColumnSpec[]{
    				new DataColumnSpecCreator("left.context",StringCell.TYPE).createSpec(),
    				new DataColumnSpecCreator("ref.context",StringCell.TYPE).createSpec(),
    				new DataColumnSpecCreator("right.context",StringCell.TYPE).createSpec()
    				}
    				)
    	
    		);
    	}
    
    @Override
    protected List<SettingsModel> getSettingsModel() {
    	List<SettingsModel> L=new ArrayList<SettingsModel>( super.getSettingsModel());
    	L.add(this.m_chromColumn);
    	L.add(this.m_posColumn);
    	L.add(this.m_refUri);
    	L.add(this.m_extend);
    	return L;
    	}
	}


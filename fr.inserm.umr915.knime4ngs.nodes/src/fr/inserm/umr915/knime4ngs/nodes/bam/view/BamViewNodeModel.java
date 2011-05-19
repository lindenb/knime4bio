package fr.inserm.umr915.knime4ngs.nodes.bam.view;



import java.util.HashMap;
import java.util.Map;

import org.knime.core.data.DataRow;
import org.knime.core.data.DataTableSpec;
import org.knime.core.data.container.CloseableRowIterator;
import org.knime.core.data.def.IntCell;
import org.knime.core.data.def.StringCell;
import org.knime.core.node.BufferedDataTable;
import org.knime.core.node.BufferedDataTableHolder;
import org.knime.core.node.ExecutionContext;
import org.knime.core.node.InvalidSettingsException;

import fr.inserm.umr915.knime4ngs.nodes.vcf.AbstractVCFNodeModel;


public class BamViewNodeModel  extends AbstractVCFNodeModel
	implements BufferedDataTableHolder
	{
	int chromColumn=-1;
	int posColumn=-1;
	int sampleColumn=-1;
	Map<String, String> sample2bam=new HashMap<String, String>();
	private BufferedDataTable internalDataTable=null;
    /**
     * Constructor for the node model.
     */
    public BamViewNodeModel()
    	{
        super(2,0);
    	}
   
    
    @Override
    protected BufferedDataTable[] execute(
    		final BufferedDataTable[] inData,
            final ExecutionContext exec
            ) throws Exception
        {
		this.sample2bam.clear();
		
		if(inData.length>1)
			{
			int bamColumn=findColumnIndex(inData[1].getSpec(),"bam",StringCell.TYPE);
			int sampleColumn=findColumnIndex(inData[1].getSpec(),"SAMPLE",StringCell.TYPE);
			CloseableRowIterator iter=null;
			try
				{
				iter=inData[1].iterator();
				while(iter.hasNext())
					{
					DataRow row=iter.next();
					String sample= getString(row, sampleColumn);
					String bam= getString(row, bamColumn);
					if(bam==null || bam.isEmpty()) continue;
					this.sample2bam.put(sample, bam);
					}
				}
			finally
				{
				if(iter!=null) iter.close();
				}
			}
		setInternalTables(inData);
		return new BufferedDataTable[0];
        }
    
    @Override
    protected DataTableSpec[] configure(DataTableSpec[] inSpecs)
    		throws InvalidSettingsException {
    	if(inSpecs==null || (inSpecs.length!=2 && inSpecs.length!=1))
    		{
    		throw new InvalidSettingsException("Expected one or two table");
    		}
    	
		this.chromColumn=findColumnIndex(inSpecs[0], "CHROM",StringCell.TYPE);
		this.posColumn=findColumnIndex(inSpecs[0], "POS",IntCell.TYPE);
		this.sampleColumn=findColumnIndex(inSpecs[0], "SAMPLE",StringCell.TYPE);
    	return new DataTableSpec[0];
    	}
    

	@Override
	public BufferedDataTable[] getInternalTables()
		{
		if(this.internalDataTable==null) return new BufferedDataTable[0];
		return new BufferedDataTable[]{this.internalDataTable};
		}


	@Override
	public void setInternalTables(BufferedDataTable[] tables)
		{
		if(tables==null || tables.length==0)
			{
			this.internalDataTable=null;
			return;
			}
		
		this.internalDataTable=tables[0];
		}

    @Override
    protected void reset()
    	{
    	this.sample2bam.clear();
    	this.chromColumn=-1;
    	this.posColumn=-1;
    	this.sampleColumn=-1;
    	this.internalDataTable=null;
    	super.reset();
    	}
    
	}

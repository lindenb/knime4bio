package fr.inserm.umr915.knime4ngs.nodes.vcf.predictions.polyphen.out;


import java.io.PrintWriter;
import org.knime.core.node.defaultnodesettings.SettingsModelString;
import fr.inserm.umr915.knime4ngs.nodes.vcf.predictions.AbstractPredictionOutNodeModel;


/**
 * This is the model implementation of VCFSource.
 * Reads a VCF file
 *
 * @author Pierre Lindenbaum
 */
public class PolyphenOutNodeModel extends AbstractPredictionOutNodeModel
	{
	static final String FILENAME_PROPERTY="file.name";
	static final String DEFAULT_FILENAME="input.polyphen";
	protected final SettingsModelString m_filename = null;
	
    /**
     * Constructor for the node model.
     */
    public PolyphenOutNodeModel()
    	{
    	}
    
   @Override
	protected SettingsModelString getFileOutSetting()
	   	{
		return m_filename;
	   	}
  
   
    @Override
    protected void write(PrintWriter out, String chrom,int  pos, String ref, String alt)
    	{
    	out.print(chrom+":"+pos);
    	out.print('\t');
    	out.print(ref+"/"+alt);
    	out.println();
    	}
   
	}


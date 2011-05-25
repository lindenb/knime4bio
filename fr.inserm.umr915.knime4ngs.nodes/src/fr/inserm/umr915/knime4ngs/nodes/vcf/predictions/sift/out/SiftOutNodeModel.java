package fr.inserm.umr915.knime4ngs.nodes.vcf.predictions.sift.out;

import java.io.PrintWriter;

import org.knime.core.node.defaultnodesettings.SettingsModelString;

import fr.inserm.umr915.knime4ngs.nodes.vcf.predictions.AbstractPredictionOutNodeModel;


/**
 * @author Pierre Lindenbaum
 */
public class SiftOutNodeModel extends AbstractPredictionOutNodeModel
	{
	static final String FILENAME_PROPERTY="file.name";
	static final String DEFAULT_FILENAME="input.sift";
	protected final SettingsModelString m_filename = new SettingsModelString(FILENAME_PROPERTY,DEFAULT_FILENAME);
	
	
    /**
     * Constructor for the node model.
     */
    public SiftOutNodeModel()
    	{
        }
    
    @Override
	protected SettingsModelString getFileOutSetting()
	   	{
		return m_filename;
	   	}
    
    @Override
    protected  void write(PrintWriter out,String chrom,int pos,String ref,String alt)
    	{
    	if(chrom.toLowerCase().startsWith("chr")) chrom=chrom.substring(3);
    	out.print(chrom);
    	out.print(',');
    	out.print(pos);
    	out.print(',');
    	out.print('1');
    	out.print(',');
    	out.print(ref+"/"+alt);
    	out.println();
    	}
   
	}


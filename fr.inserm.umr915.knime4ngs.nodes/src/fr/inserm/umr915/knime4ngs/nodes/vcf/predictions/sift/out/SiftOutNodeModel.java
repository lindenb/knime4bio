package fr.inserm.umr915.knime4ngs.nodes.vcf.predictions.sift.out;

import java.io.PrintWriter;

import org.knime.core.node.defaultnodesettings.SettingsModelString;


import fr.inserm.umr915.knime4ngs.corelib.bio.Position;
import fr.inserm.umr915.knime4ngs.nodes.vcf.predictions.AbstractPredictionOutNodeModel;


/**
 * This is the model implementation of VCFSource.
 * Reads a VCF file
 *
 * @author Pierre Lindenbaum
 */
public class SiftOutNodeModel extends AbstractPredictionOutNodeModel
	{
	static final String FILENAME_PROPERTY="file.name";
	static final String DEFAULT_FILENAME="input.sift";
	protected final SettingsModelString m_filename = null;
	
	
    /**
     * Constructor for the node model.
     */
    protected SiftOutNodeModel()
    	{
        super(1,0);
        }
    
    @Override
	protected SettingsModelString getFileOutSetting()
	   	{
		return m_filename;
	   	}
    
    @Override
    protected  void write(PrintWriter out,Position pos,String ref,String alt)
    	{
    	String s=pos.getChromosome();
    	if(s.toLowerCase().startsWith("chr")) s=s.substring(3);
    	out.print(s);
    	out.print(',');
    	out.print(pos.getPosition());
    	out.print(',');
    	out.print('1');
    	out.print(',');
    	out.print(ref+"/"+alt);
    	out.println();
    	}
   
	}


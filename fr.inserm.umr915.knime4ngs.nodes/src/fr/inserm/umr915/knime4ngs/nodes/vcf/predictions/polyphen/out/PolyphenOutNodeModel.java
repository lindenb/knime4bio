package fr.inserm.umr915.knime4ngs.nodes.vcf.predictions.polyphen.out;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;


import org.knime.core.node.defaultnodesettings.SettingsModel;
import org.knime.core.node.defaultnodesettings.SettingsModelString;


import fr.inserm.umr915.knime4ngs.corelib.bio.Position;
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
    protected PolyphenOutNodeModel()
    	{
        super(1,0);
    	}
    
   @Override
	protected SettingsModelString getFileOutSetting()
	   	{
		return m_filename;
	   	}
  
   
    @Override
    protected void write(PrintWriter out, Position pos, String ref, String alt)
    	{
    	out.print(pos.getChromosome()+":"+pos.getPosition());
    	out.print('\t');
    	out.print(ref+"/"+alt);
    	out.println();
    	}
   
	}


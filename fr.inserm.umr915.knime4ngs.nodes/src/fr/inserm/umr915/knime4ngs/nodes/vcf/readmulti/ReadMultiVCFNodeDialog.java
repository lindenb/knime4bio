package fr.inserm.umr915.knime4ngs.nodes.vcf.readmulti;

import javax.swing.JFileChooser;

import org.knime.core.node.defaultnodesettings.DefaultNodeSettingsPane;
import org.knime.core.node.defaultnodesettings.DialogComponentFileChooser;
import org.knime.core.node.defaultnodesettings.DialogComponentNumber;
import org.knime.core.node.defaultnodesettings.SettingsModelInteger;
import org.knime.core.node.defaultnodesettings.SettingsModelString;



/**
 * <code>NodeDialog</code> for the "VCFLoader" Node.
 * Loads one VCF File
 */
public class ReadMultiVCFNodeDialog extends DefaultNodeSettingsPane
	{
    public ReadMultiVCFNodeDialog()
    	{
    	//qual
    	addDialogComponent(new DialogComponentNumber(
                new SettingsModelInteger(
                	ReadMultiVCFNodeModel.MIN_QUAL_PROPERTY, ReadMultiVCFNodeModel.DEFAULT_MIN_QUAL),
                    "Minimum Qual",1));
    	
    	//vcf path
		addDialogComponent(new DialogComponentFileChooser(
	    		new SettingsModelString(ReadMultiVCFNodeModel.VCF_COL_PROPERTY,ReadMultiVCFNodeModel.VCF_COL_DEFAULT),
	    		"my.simple.vcf.read.HistoryID",
	    		JFileChooser.OPEN_DIALOG,
	    		".vcf",".VCF",
	    		".vcf.gz",".VCF.gz",
	    		".vcf.bgz",".VCF.bgz"
	    		));    	
    	}
}


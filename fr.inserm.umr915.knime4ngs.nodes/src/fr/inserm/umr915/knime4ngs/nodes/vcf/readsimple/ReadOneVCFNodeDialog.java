package fr.inserm.umr915.knime4ngs.nodes.vcf.readsimple;

import javax.swing.JFileChooser;

import org.knime.core.node.defaultnodesettings.DefaultNodeSettingsPane;
import org.knime.core.node.defaultnodesettings.DialogComponentFileChooser;
import org.knime.core.node.defaultnodesettings.DialogComponentNumber;
import org.knime.core.node.defaultnodesettings.DialogComponentString;
import org.knime.core.node.defaultnodesettings.SettingsModelInteger;
import org.knime.core.node.defaultnodesettings.SettingsModelString;



/**
 * <code>NodeDialog</code> for the "VCFLoader" Node.
 * Loads one VCF File
 */
public class ReadOneVCFNodeDialog extends DefaultNodeSettingsPane
	{
    public ReadOneVCFNodeDialog()
    	{
    	//qual
    	addDialogComponent(new DialogComponentNumber(
                new SettingsModelInteger(
                	ReadOneVCFNodeModel.MIN_QUAL_PROPERTY, ReadOneVCFNodeModel.DEFAULT_MIN_QUAL),
                    "Minimum Qual",1));
    	
    	//vcf path
		addDialogComponent(new DialogComponentFileChooser(
	    		new SettingsModelString(ReadOneVCFNodeModel.VCF_COL_PROPERTY,ReadOneVCFNodeModel.VCF_COL_DEFAULT),
	    		"my.simple.vcf.read.HistoryID",
	    		JFileChooser.OPEN_DIALOG,
	    		".vcf",".VCF",
	    		".vcf.gz",".VCF.gz",
	    		".vcf.bgz",".VCF.bgz"
	    		));
		
		//sample name
		addDialogComponent(new DialogComponentString(
	    		new SettingsModelString(ReadOneVCFNodeModel.SAMPLE_COL_PROPERTY,ReadOneVCFNodeModel.SAMPLE_COL_DEFAULT),
	    		"Sample Name (optional)",false,30
	    		));
    	
    	}
}


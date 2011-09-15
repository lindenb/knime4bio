package fr.inserm.umr915.knime4ngs.nodes.vcf.integragen.loadsnp;


import org.knime.core.node.defaultnodesettings.DialogComponentBoolean;
import org.knime.core.node.defaultnodesettings.SettingsModelBoolean;


import fr.inserm.umr915.knime4ngs.nodes.vcf.integragen.LoadIgNodeDialog;




/**
 * <code>NodeDialog</code> for the "VCFLoader" Node.
 * Loads one IG File
 */
public class LoadIgSnpNodeDialog extends LoadIgNodeDialog
	{
    public LoadIgSnpNodeDialog()
    	{
		addDialogComponent(new DialogComponentBoolean(
	    		new SettingsModelBoolean(LoadIgSnpNodeModel.IGNORE_CDNA_PROPERTY,LoadIgSnpNodeModel.IGNORE_CDNA_DEFAULT),
	    		"Ignore cDNA sequences(faster)"
				));
		addDialogComponent(new DialogComponentBoolean(
	    		new SettingsModelBoolean(LoadIgSnpNodeModel.IGNORE_PEP_PROPERTY,LoadIgSnpNodeModel.IGNORE_PEP_DEFAULT),
	    		"Ignore protein sequences(faster)"
				));
    	}
}


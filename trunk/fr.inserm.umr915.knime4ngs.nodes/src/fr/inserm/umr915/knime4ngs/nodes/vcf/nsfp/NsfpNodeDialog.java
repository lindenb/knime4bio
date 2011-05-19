package fr.inserm.umr915.knime4ngs.nodes.vcf.nsfp;

import javax.swing.JFileChooser;

import org.knime.core.node.defaultnodesettings.DefaultNodeSettingsPane;
import org.knime.core.node.defaultnodesettings.DialogComponentFileChooser;
import org.knime.core.node.defaultnodesettings.SettingsModelString;


public class NsfpNodeDialog extends DefaultNodeSettingsPane
	{
    protected NsfpNodeDialog()
    	{
    	DialogComponentFileChooser fileSel=new DialogComponentFileChooser(
    		new SettingsModelString(NsfpNodeModel.NSFP_FILENAME_PROPERTY, NsfpNodeModel.NSFP_DEFAULT_FILENAME),
    		NsfpNodeModel.NSFP_FILENAME_PROPERTY,
    		JFileChooser.OPEN_DIALOG,
    		false,
    		".zip",".ZIP"
    		);
    	addDialogComponent(fileSel);
    	}
	}


package fr.inserm.umr915.knime4ngs.nodes.vcf.rsid;

import org.knime.core.node.defaultnodesettings.DefaultNodeSettingsPane;
import org.knime.core.node.defaultnodesettings.DialogComponentBoolean;
import org.knime.core.node.defaultnodesettings.SettingsModelBoolean;




public class RsIdNodeDialog extends DefaultNodeSettingsPane
	{
    protected RsIdNodeDialog()
    	{
    	addDialogComponent(new DialogComponentBoolean(
                new SettingsModelBoolean(
                		RsidNodeModel.USE_BATCH_PROPERTY,
                		RsidNodeModel.DEFAULT_USE_BATCH_PROPERTY),
                    "Use batch"));   
    	}
	}


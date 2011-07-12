package fr.inserm.umr915.knime4ngs.nodes.vcf.qual;

import org.knime.core.node.defaultnodesettings.DefaultNodeSettingsPane;
import org.knime.core.node.defaultnodesettings.DialogComponentNumber;
import org.knime.core.node.defaultnodesettings.SettingsModelDouble;


@Deprecated
public class QualNodeDialog extends DefaultNodeSettingsPane
	{
	
	
    protected QualNodeDialog()
    	{
    	addDialogComponent(new DialogComponentNumber(
                new SettingsModelDouble( QualNodeModel.MIN_QUAL_PROPERTY, QualNodeModel.DEFAULT_MIN_QUAL),
                    "Minimum Quality",1));
    	}
	}


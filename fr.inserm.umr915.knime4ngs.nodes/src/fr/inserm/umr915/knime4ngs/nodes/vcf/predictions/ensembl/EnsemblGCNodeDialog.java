package fr.inserm.umr915.knime4ngs.nodes.vcf.predictions.ensembl;

import org.knime.core.node.defaultnodesettings.DefaultNodeSettingsPane;
import org.knime.core.node.defaultnodesettings.DialogComponentStringListSelection;
import org.knime.core.node.defaultnodesettings.SettingsModelStringArray;

public class EnsemblGCNodeDialog extends DefaultNodeSettingsPane
	{
    protected EnsemblGCNodeDialog()
    	{
    	SettingsModelStringArray stringModel=new SettingsModelStringArray( EnsemblGCNodeModel.GC_PROPERTY, EnsemblGCNodeModel.DEFAULT_CHOICE);
        addDialogComponent(new DialogComponentStringListSelection(stringModel, "Prediction", EnsemblGCNodeModel.CHOICE));
    	}
	}


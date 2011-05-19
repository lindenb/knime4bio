package fr.inserm.umr915.knime4ngs.nodes.vcf.predictions.sift.in;

import org.knime.core.node.defaultnodesettings.DefaultNodeSettingsPane;
import org.knime.core.node.defaultnodesettings.DialogComponentStringListSelection;
import org.knime.core.node.defaultnodesettings.SettingsModelStringArray;


/**
 * @author Pierre Lindenbaum
 */
public class SiftInNodeDialog extends DefaultNodeSettingsPane
	{
    protected SiftInNodeDialog()
    	{
    	SettingsModelStringArray settings=new SettingsModelStringArray(
    			SiftInNodeModel.PREDICTIONS_PROPERTY,
    			SiftInNodeModel.DEFAULT_PREDICTIONS
    			);
    	addDialogComponent(new DialogComponentStringListSelection(
    			settings,
    			"Predictions",
    			SiftInNodeModel.PREDICTIONS
    			));
    	}
	}


package fr.inserm.umr915.knime4ngs.nodes.vcf.predictions.basic;

import org.knime.core.node.defaultnodesettings.DefaultNodeSettingsPane;
import org.knime.core.node.defaultnodesettings.DialogComponentStringListSelection;
import org.knime.core.node.defaultnodesettings.SettingsModelStringArray;


@Deprecated
public class BasicPredictionNodeDialog extends DefaultNodeSettingsPane
	{
    protected BasicPredictionNodeDialog()
    	{
    	SettingsModelStringArray settings=new SettingsModelStringArray(
    			BasicPredictionNodeModel.PREDICTIONS_PROPERTY,
    			BasicPredictionNodeModel.DEFAULT_PREDICTIONS
    			);
    	addDialogComponent(new DialogComponentStringListSelection(
    			settings,
    			"Predictions",
    			BasicPredictionNodeModel.PREDICTIONS
    			));
        
                  
    	}
	}


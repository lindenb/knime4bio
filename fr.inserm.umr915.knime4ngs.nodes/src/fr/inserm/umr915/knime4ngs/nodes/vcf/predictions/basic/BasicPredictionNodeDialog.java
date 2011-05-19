package fr.inserm.umr915.knime4ngs.nodes.vcf.predictions.basic;

import org.knime.core.node.defaultnodesettings.DefaultNodeSettingsPane;
import org.knime.core.node.defaultnodesettings.DialogComponentStringListSelection;
import org.knime.core.node.defaultnodesettings.SettingsModelStringArray;




/**
 * <code>NodeDialog</code> for the "VCFSource" Node.
 * Reads a VCF file
 *
 * This node dialog derives from {@link DefaultNodeSettingsPane} which allows
 * creation of a simple dialog with standard components. If you need a more 
 * complex dialog please derive directly from 
 * {@link org.knime.core.node.NodeDialogPane}.
 * 
 * @author Pierre Lindenbaum
 */
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


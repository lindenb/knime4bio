package fr.inserm.umr915.knime4ngs.nodes.vcf.predictions.ensembl;

import org.knime.core.data.def.StringCell;
import org.knime.core.node.defaultnodesettings.DefaultNodeSettingsPane;
import org.knime.core.node.defaultnodesettings.DialogComponentColumnNameSelection;
import org.knime.core.node.defaultnodesettings.DialogComponentStringListSelection;
import org.knime.core.node.defaultnodesettings.SettingsModelColumnName;
import org.knime.core.node.defaultnodesettings.SettingsModelStringArray;

import fr.inserm.umr915.knime4ngs.corelib.knime.DataTypeColumnFilter;

public class EnsemblGCNodeDialog extends DefaultNodeSettingsPane
	{
    protected EnsemblGCNodeDialog()
    	{
    	//columns
    	addDialogComponent(new DialogComponentColumnNameSelection(
    			new SettingsModelColumnName(EnsemblGCNodeModel.PROPERTY_INFO_COL, EnsemblGCNodeModel.DEFAULT_INFO_COL),
    			"INFO", 0, true, false,
    			new DataTypeColumnFilter(StringCell.TYPE)));
    	
    	SettingsModelStringArray stringModel=new SettingsModelStringArray( EnsemblGCNodeModel.GC_PROPERTY, EnsemblGCNodeModel.DEFAULT_CHOICE);
        addDialogComponent(new DialogComponentStringListSelection(stringModel, "Prediction", EnsemblGCNodeModel.CHOICE));
    	}
	}


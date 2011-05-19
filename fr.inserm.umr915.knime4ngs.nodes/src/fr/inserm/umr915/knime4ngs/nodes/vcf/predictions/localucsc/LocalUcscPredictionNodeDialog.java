package fr.inserm.umr915.knime4ngs.nodes.vcf.predictions.localucsc;

import org.knime.core.data.def.IntCell;
import org.knime.core.data.def.StringCell;
import org.knime.core.node.defaultnodesettings.DefaultNodeSettingsPane;
import org.knime.core.node.defaultnodesettings.DialogComponentBoolean;
import org.knime.core.node.defaultnodesettings.DialogComponentColumnNameSelection;
import org.knime.core.node.defaultnodesettings.DialogComponentStringSelection;
import org.knime.core.node.defaultnodesettings.SettingsModelBoolean;
import org.knime.core.node.defaultnodesettings.SettingsModelColumnName;
import org.knime.core.node.defaultnodesettings.SettingsModelString;

import fr.inserm.umr915.knime4ngs.corelib.knime.DataTypeColumnFilter;


public class LocalUcscPredictionNodeDialog extends DefaultNodeSettingsPane
	{
    protected LocalUcscPredictionNodeDialog()
    	{
    	//build
    	addDialogComponent(new DialogComponentStringSelection(
    			new SettingsModelString(
    					LocalUcscPredictionNodeModel.PROPERTY_BUILD,
    	    			LocalUcscPredictionNodeModel.DEFAULT_BUILD
    	    			),
    			"Build",
    			LocalUcscPredictionNodeModel.BUILDS
    			));
        //show RNA
    	addDialogComponent(
			new DialogComponentBoolean(new SettingsModelBoolean(LocalUcscPredictionNodeModel.PROPERTY_SHOW_RNA, false),
			"Show RNA"));
    	 //show Peptide
    	addDialogComponent(
			new DialogComponentBoolean(new SettingsModelBoolean(LocalUcscPredictionNodeModel.PROPERTY_SHOW_PEP, false),
			"Show Peptide"));
        
    	//columns
    	addDialogComponent(new DialogComponentColumnNameSelection(
    			new SettingsModelColumnName(LocalUcscPredictionNodeModel.PROPERTY_CHROM_COL, LocalUcscPredictionNodeModel.DEFAULT_CHROM_COL),
    			"Chrom", 0, true, false,
    			new DataTypeColumnFilter(StringCell.TYPE)));
    	addDialogComponent(new DialogComponentColumnNameSelection(
    			new SettingsModelColumnName(LocalUcscPredictionNodeModel.PROPERTY_POS_COL, LocalUcscPredictionNodeModel.DEFAULT_POS_COL),
    			"POS", 0, true, false,
    			new DataTypeColumnFilter(IntCell.TYPE)));
    	addDialogComponent(new DialogComponentColumnNameSelection(
    			new SettingsModelColumnName(LocalUcscPredictionNodeModel.PROPERTY_REF_COL, LocalUcscPredictionNodeModel.DEFAULT_REF_COL),
    			"REF", 0, true, false,
    			new DataTypeColumnFilter(StringCell.TYPE)));
    	addDialogComponent(new DialogComponentColumnNameSelection(
    			new SettingsModelColumnName(LocalUcscPredictionNodeModel.PROPERTY_ALT_COL, LocalUcscPredictionNodeModel.DEFAULT_ALT_COL),
    			"ALT", 0, true, false,
    			new DataTypeColumnFilter(StringCell.TYPE)));
    	}
	}


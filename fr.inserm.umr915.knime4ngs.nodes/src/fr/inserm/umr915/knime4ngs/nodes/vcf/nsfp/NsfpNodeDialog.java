package fr.inserm.umr915.knime4ngs.nodes.vcf.nsfp;

import javax.swing.JFileChooser;

import org.knime.core.data.def.IntCell;
import org.knime.core.data.def.StringCell;
import org.knime.core.node.defaultnodesettings.DefaultNodeSettingsPane;
import org.knime.core.node.defaultnodesettings.DialogComponentColumnNameSelection;
import org.knime.core.node.defaultnodesettings.DialogComponentFileChooser;
import org.knime.core.node.defaultnodesettings.SettingsModelColumnName;
import org.knime.core.node.defaultnodesettings.SettingsModelString;

import fr.inserm.umr915.knime4ngs.corelib.knime.DataTypeColumnFilter;


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
    	
    	//columns
    	addDialogComponent(new DialogComponentColumnNameSelection(
    			new SettingsModelColumnName(NsfpNodeModel.PROPERTY_CHROM_COL, NsfpNodeModel.DEFAULT_CHROM_COL),
    			"Chrom", 0, true, false,
    			new DataTypeColumnFilter(StringCell.TYPE)));
    	addDialogComponent(new DialogComponentColumnNameSelection(
    			new SettingsModelColumnName(NsfpNodeModel.PROPERTY_POS_COL, NsfpNodeModel.DEFAULT_POS_COL),
    			"POS", 0, true, false,
    			new DataTypeColumnFilter(IntCell.TYPE)));
    	addDialogComponent(new DialogComponentColumnNameSelection(
    			new SettingsModelColumnName(NsfpNodeModel.PROPERTY_REF_COL, NsfpNodeModel.DEFAULT_REF_COL),
    			"REF", 0, true, false,
    			new DataTypeColumnFilter(StringCell.TYPE)));
    	addDialogComponent(new DialogComponentColumnNameSelection(
    			new SettingsModelColumnName(NsfpNodeModel.PROPERTY_ALT_COL, NsfpNodeModel.DEFAULT_ALT_COL),
    			"ALT", 0, true, false,
    			new DataTypeColumnFilter(StringCell.TYPE)));
    	
    	}
	}


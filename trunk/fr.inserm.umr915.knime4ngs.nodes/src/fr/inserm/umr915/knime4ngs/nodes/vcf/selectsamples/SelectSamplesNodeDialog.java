package fr.inserm.umr915.knime4ngs.nodes.vcf.selectsamples;

import org.knime.core.data.def.IntCell;
import org.knime.core.data.def.StringCell;
import org.knime.core.node.defaultnodesettings.DefaultNodeSettingsPane;
import org.knime.core.node.defaultnodesettings.DialogComponentBoolean;
import org.knime.core.node.defaultnodesettings.DialogComponentColumnNameSelection;
import org.knime.core.node.defaultnodesettings.DialogComponentMultiLineString;
import org.knime.core.node.defaultnodesettings.SettingsModelBoolean;
import org.knime.core.node.defaultnodesettings.SettingsModelColumnName;
import org.knime.core.node.defaultnodesettings.SettingsModelString;



import fr.inserm.umr915.knime4ngs.corelib.knime.DataTypeColumnFilter;





public class SelectSamplesNodeDialog extends DefaultNodeSettingsPane
	{
	
    protected SelectSamplesNodeDialog()
    	{
    	
    	addDialogComponent(new DialogComponentColumnNameSelection(
                new SettingsModelColumnName(
                	SelectSamplesNodeModel.CHROM_COL_PROPERTY, SelectSamplesNodeModel.CHROM_COL_DEFAULT),
                    "Chrom:",0,new DataTypeColumnFilter(StringCell.TYPE)));
    	
    	addDialogComponent(new DialogComponentColumnNameSelection(
                new SettingsModelColumnName(
                	SelectSamplesNodeModel.POS_COL_PROPERTY, SelectSamplesNodeModel.POS_COL_DEFAULT),
                    "Pos:",0,new DataTypeColumnFilter(IntCell.TYPE)));
    	addDialogComponent(new DialogComponentColumnNameSelection(
                new SettingsModelColumnName(
                	SelectSamplesNodeModel.REF_COL_PROPERTY, SelectSamplesNodeModel.REF_COL_DEFAULT),
                    "Ref:",0,new DataTypeColumnFilter(StringCell.TYPE)));
    	addDialogComponent(new DialogComponentColumnNameSelection(
                new SettingsModelColumnName(
                	SelectSamplesNodeModel.ALT_COL_PROPERTY, SelectSamplesNodeModel.ALT_COL_DEFAULT),
                    "Alt:",0,new DataTypeColumnFilter(StringCell.TYPE)));
    	addDialogComponent(new DialogComponentColumnNameSelection(
                new SettingsModelColumnName(
                	SelectSamplesNodeModel.SAMPLE_COL_PROPERTY, SelectSamplesNodeModel.SAMPLE_COL_DEFAULT),
                    "Sample:",0,new DataTypeColumnFilter(StringCell.TYPE)));
    	addDialogComponent(new DialogComponentMultiLineString(
                new SettingsModelString( SelectSamplesNodeModel.QUERY_PROPERTY, SelectSamplesNodeModel.QUERY_DEFAULT)
                    ,"Query:",false,80,5));
    	addDialogComponent(new DialogComponentBoolean(
                new SettingsModelBoolean( SelectSamplesNodeModel.USE_REF_ALT_PROPERTY, SelectSamplesNodeModel.USE_REF_ALT_DEFAULT)
                    ,"use REF and ALT"));
    	}
	}


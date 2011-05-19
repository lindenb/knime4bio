package fr.inserm.umr915.knime4ngs.nodes.vcf.groupbysnp;

import org.knime.core.data.def.IntCell;
import org.knime.core.data.def.StringCell;
import org.knime.core.node.defaultnodesettings.DefaultNodeSettingsPane;
import org.knime.core.node.defaultnodesettings.DialogComponentColumnFilter;
import org.knime.core.node.defaultnodesettings.DialogComponentColumnNameSelection;
import org.knime.core.node.defaultnodesettings.SettingsModelColumnName;
import org.knime.core.node.defaultnodesettings.SettingsModelFilterString;



import fr.inserm.umr915.knime4ngs.corelib.knime.DataTypeColumnFilter;




public class GroupBySnpNodeDialog extends DefaultNodeSettingsPane
	{
	
    protected GroupBySnpNodeDialog()
    	{
    	addDialogComponent(new DialogComponentColumnNameSelection(
                new SettingsModelColumnName(
                		GroupBySnpNodeModel.CHROM_COL_PROPERTY, GroupBySnpNodeModel.CHROM_COL_DEFAULT),
                    "Chrom:",0,new DataTypeColumnFilter(StringCell.TYPE)));
    	
    	addDialogComponent(new DialogComponentColumnNameSelection(
                new SettingsModelColumnName(
                		GroupBySnpNodeModel.POS_COL_PROPERTY, GroupBySnpNodeModel.POS_COL_DEFAULT),
                    "Pos:",0,new DataTypeColumnFilter(IntCell.TYPE)));
    	addDialogComponent(new DialogComponentColumnNameSelection(
                new SettingsModelColumnName(
                		GroupBySnpNodeModel.REF_COL_PROPERTY, GroupBySnpNodeModel.REF_COL_DEFAULT),
                    "Ref:",0,new DataTypeColumnFilter(StringCell.TYPE)));
    	addDialogComponent(new DialogComponentColumnNameSelection(
                new SettingsModelColumnName(
                		GroupBySnpNodeModel.ALT_COL_PROPERTY, GroupBySnpNodeModel.ALT_COL_DEFAULT),
                    "Alt:",0,new DataTypeColumnFilter(StringCell.TYPE)));
    	addDialogComponent(new DialogComponentColumnNameSelection(
                new SettingsModelColumnName(
                		GroupBySnpNodeModel.SAMPLE_COL_PROPERTY, GroupBySnpNodeModel.SAMPLE_COL_DEFAULT),
                    "Sample:",0,new DataTypeColumnFilter(StringCell.TYPE)));
    	
    	addDialogComponent(new DialogComponentColumnFilter(
                new SettingsModelFilterString(
                		GroupBySnpNodeModel.LEFT_COLS_PROPERTY, GroupBySnpNodeModel.LEFT_COLS_INCLUDE,GroupBySnpNodeModel.LEFT_COLS_EXCLUDE),
                    0,false));
    	
    	addDialogComponent(new DialogComponentColumnFilter(
                new SettingsModelFilterString(
                		GroupBySnpNodeModel.TOP_COLS_PROPERTY, GroupBySnpNodeModel.TOP_COLS_INCLUDE,GroupBySnpNodeModel.TOP_COLS_EXCLUDE),
                    0,false));
    	}
    	
	}


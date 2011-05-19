package fr.inserm.umr915.knime4ngs.nodes.vcf.groupbygene;

import org.knime.core.data.def.IntCell;
import org.knime.core.data.def.StringCell;
import org.knime.core.node.defaultnodesettings.DefaultNodeSettingsPane;
import org.knime.core.node.defaultnodesettings.DialogComponentColumnNameSelection;
import org.knime.core.node.defaultnodesettings.SettingsModelColumnName;



import fr.inserm.umr915.knime4ngs.corelib.knime.DataTypeColumnFilter;




public class GroupByGeneNodeDialog extends DefaultNodeSettingsPane
	{
	
    protected GroupByGeneNodeDialog()
    	{
    	addDialogComponent(new DialogComponentColumnNameSelection(
                new SettingsModelColumnName(
                		GroupByGeneNodeModel.CHROM_COL_PROPERTY, GroupByGeneNodeModel.CHROM_COL_DEFAULT),
                    "Chrom:",0,new DataTypeColumnFilter(StringCell.TYPE)));
    	
    	addDialogComponent(new DialogComponentColumnNameSelection(
                new SettingsModelColumnName(
                		GroupByGeneNodeModel.POS_COL_PROPERTY, GroupByGeneNodeModel.POS_COL_DEFAULT),
                    "Pos:",0,new DataTypeColumnFilter(IntCell.TYPE)));
    	addDialogComponent(new DialogComponentColumnNameSelection(
                new SettingsModelColumnName(
                		GroupByGeneNodeModel.REF_COL_PROPERTY, GroupByGeneNodeModel.REF_COL_DEFAULT),
                    "Ref:",0,new DataTypeColumnFilter(StringCell.TYPE)));
    	addDialogComponent(new DialogComponentColumnNameSelection(
                new SettingsModelColumnName(
                		GroupByGeneNodeModel.ALT_COL_PROPERTY, GroupByGeneNodeModel.ALT_COL_DEFAULT),
                    "Alt:",0,new DataTypeColumnFilter(StringCell.TYPE)));
    	addDialogComponent(new DialogComponentColumnNameSelection(
                new SettingsModelColumnName(
                		GroupByGeneNodeModel.SAMPLE_COL_PROPERTY, GroupByGeneNodeModel.SAMPLE_COL_DEFAULT),
                    "Sample:",0,new DataTypeColumnFilter(StringCell.TYPE)));
    	addDialogComponent(new DialogComponentColumnNameSelection(
                new SettingsModelColumnName(
                		GroupByGeneNodeModel.GENE_COL_PROPERTY, GroupByGeneNodeModel.GENE_COL_DEFAULT),
                    "Gene:",0,new DataTypeColumnFilter(StringCell.TYPE)));
    	}
    	
	}


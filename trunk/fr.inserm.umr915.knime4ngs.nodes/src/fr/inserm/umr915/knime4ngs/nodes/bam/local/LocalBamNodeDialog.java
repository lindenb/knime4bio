package fr.inserm.umr915.knime4ngs.nodes.bam.local;

import org.knime.core.data.def.IntCell;
import org.knime.core.data.def.StringCell;
import org.knime.core.node.defaultnodesettings.DefaultNodeSettingsPane;
import org.knime.core.node.defaultnodesettings.DialogComponentColumnNameSelection;
import org.knime.core.node.defaultnodesettings.SettingsModelColumnName;
import fr.inserm.umr915.knime4ngs.corelib.knime.DataTypeColumnFilter;

public class LocalBamNodeDialog extends DefaultNodeSettingsPane
	{	
    protected LocalBamNodeDialog()
    	{
    	addDialogComponent(new DialogComponentColumnNameSelection(
                new SettingsModelColumnName(
                		LocalBamNodeModel.CHROM_COL_PROPERTY, LocalBamNodeModel.CHROM_COL_DEFAULT),
                    "Chrom:",0,new DataTypeColumnFilter(StringCell.TYPE)));
    	
    	addDialogComponent(new DialogComponentColumnNameSelection(
                new SettingsModelColumnName(
                		LocalBamNodeModel.POS_COL_PROPERTY, LocalBamNodeModel.POS_COL_DEFAULT),
                    "Pos:",0,new DataTypeColumnFilter(IntCell.TYPE)));
    	
    	addDialogComponent(new DialogComponentColumnNameSelection(
                new SettingsModelColumnName(
                		LocalBamNodeModel.SAMPLE1_COL_PROPERTY, LocalBamNodeModel.SAMPLE1_COL_DEFAULT),
                    "VCF Sample:",0,new DataTypeColumnFilter(StringCell.TYPE)));
    	
    	addDialogComponent(new DialogComponentColumnNameSelection(
                new SettingsModelColumnName(
                		LocalBamNodeModel.SAMPLE2_COL_PROPERTY, LocalBamNodeModel.SAMPLE2_COL_DEFAULT),
                    "VCF Sample:",1,new DataTypeColumnFilter(StringCell.TYPE)));
    	addDialogComponent(new DialogComponentColumnNameSelection(
                new SettingsModelColumnName(
                		LocalBamNodeModel.BAM_COL_PROPERTY, LocalBamNodeModel.BAM_COL_DEFAULT),
                    "BAM URI:",1,new DataTypeColumnFilter(StringCell.TYPE)));
    	}
    	
	}


package fr.inserm.umr915.knime4ngs.nodes.vcf.bed;

import org.knime.core.data.def.IntCell;
import org.knime.core.data.def.StringCell;
import org.knime.core.node.defaultnodesettings.DefaultNodeSettingsPane;
import org.knime.core.node.defaultnodesettings.DialogComponentColumnNameSelection;
import org.knime.core.node.defaultnodesettings.SettingsModelColumnName;
import fr.inserm.umr915.knime4ngs.corelib.knime.DataTypeColumnFilter;


public class InRegionNodeDialog extends DefaultNodeSettingsPane
	{
    protected InRegionNodeDialog()
    	{
    	addDialogComponent(new DialogComponentColumnNameSelection(
                new SettingsModelColumnName( InRegionNodeModel.CHROM1_COL_PROPERTY, InRegionNodeModel.DEFAULT_CHROM1_COL),
                   "CHROM",
                   0,
                   new DataTypeColumnFilter(StringCell.TYPE)
       			));
    	addDialogComponent(new DialogComponentColumnNameSelection(
                new SettingsModelColumnName( InRegionNodeModel.POS_COL_PROPERTY, InRegionNodeModel.DEFAULT_POS_COL),
                   "POS",
                   0,
                   new DataTypeColumnFilter(IntCell.TYPE)
       			));
    	
    	
    	addDialogComponent(new DialogComponentColumnNameSelection(
             new SettingsModelColumnName( InRegionNodeModel.CHROM2_COL_PROPERTY, InRegionNodeModel.DEFAULT_CHROM2_COL),
                "Chromosome",
                1,
                new DataTypeColumnFilter(StringCell.TYPE)
    			));
    	addDialogComponent(new DialogComponentColumnNameSelection(
                new SettingsModelColumnName( InRegionNodeModel.START_COL_PROPERTY, InRegionNodeModel.DEFAULT_START_COL),
                   "ChromStart",
                   1,
                   new DataTypeColumnFilter(IntCell.TYPE)
       			));
    	addDialogComponent(new DialogComponentColumnNameSelection(
                new SettingsModelColumnName( InRegionNodeModel.END_COL_PROPERTY, InRegionNodeModel.DEFAULT_END_COL),
                   "ChromEnd",
                   1,
                   new DataTypeColumnFilter(IntCell.TYPE)
       			));
    	}
	}


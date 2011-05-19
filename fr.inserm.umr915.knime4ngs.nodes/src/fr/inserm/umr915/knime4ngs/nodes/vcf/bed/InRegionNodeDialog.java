package fr.inserm.umr915.knime4ngs.nodes.vcf.bed;

import org.knime.core.data.def.IntCell;
import org.knime.core.data.def.StringCell;
import org.knime.core.node.defaultnodesettings.DefaultNodeSettingsPane;
import org.knime.core.node.defaultnodesettings.DialogComponentBoolean;
import org.knime.core.node.defaultnodesettings.DialogComponentColumnNameSelection;
import org.knime.core.node.defaultnodesettings.DialogComponentNumber;
import org.knime.core.node.defaultnodesettings.SettingsModelBoolean;
import org.knime.core.node.defaultnodesettings.SettingsModelColumnName;
import org.knime.core.node.defaultnodesettings.SettingsModelInteger;
import org.knime.core.node.defaultnodesettings.SettingsModelString;

import fr.inserm.umr915.knime4ngs.corelib.knime.DataTypeColumnFilter;


public class InRegionNodeDialog extends DefaultNodeSettingsPane
	{
    protected InRegionNodeDialog()
    	{
    	addDialogComponent(new DialogComponentColumnNameSelection(
             new SettingsModelColumnName( InRegionNodeModel.CHROM_COL_PROPERTY, InRegionNodeModel.DEFAULT_CHROM_COL),
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
    	addDialogComponent(new DialogComponentBoolean(
    			new SettingsModelBoolean(InRegionNodeModel.ZERO_PROPERTY, InRegionNodeModel.DEFAULT_ZERO),
    			"Zero-Based coordinates")
    			);
    	}
	}


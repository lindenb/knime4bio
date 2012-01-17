package fr.inserm.umr915.knime4ngs.nodes.bed.bedinterbed;

import org.knime.core.data.def.IntCell;
import org.knime.core.data.def.StringCell;
import org.knime.core.node.defaultnodesettings.DefaultNodeSettingsPane;
import org.knime.core.node.defaultnodesettings.DialogComponentColumnNameSelection;
import org.knime.core.node.defaultnodesettings.DialogComponentStringSelection;
import org.knime.core.node.defaultnodesettings.SettingsModelColumnName;
import org.knime.core.node.defaultnodesettings.SettingsModelString;

import fr.inserm.umr915.knime4ngs.corelib.knime.DataTypeColumnFilter;


public class BedInterBedNodeDialog extends DefaultNodeSettingsPane
	{
    protected BedInterBedNodeDialog()
    	{
    	addDialogComponent(new DialogComponentColumnNameSelection(
                new SettingsModelColumnName( BedInterBedNodeModel.CHROM1_COL_PROPERTY, BedInterBedNodeModel.DEFAULT_CHROM1_COL),
                   "Chromosome (1)",
                   0,
                   new DataTypeColumnFilter(StringCell.TYPE)
       			));
       	addDialogComponent(new DialogComponentColumnNameSelection(
                   new SettingsModelColumnName( BedInterBedNodeModel.START1_COL_PROPERTY, BedInterBedNodeModel.DEFAULT_START1_COL),
                      "ChromStart (1)",
                      0,
                      new DataTypeColumnFilter(IntCell.TYPE)
          			));
       	addDialogComponent(new DialogComponentColumnNameSelection(
                   new SettingsModelColumnName( BedInterBedNodeModel.END1_COL_PROPERTY, BedInterBedNodeModel.DEFAULT_END1_COL),
                      "ChromEnd (1)",
                      0,
                      new DataTypeColumnFilter(IntCell.TYPE)
          			));
    	
    	addDialogComponent(new DialogComponentColumnNameSelection(
             new SettingsModelColumnName( BedInterBedNodeModel.CHROM2_COL_PROPERTY, BedInterBedNodeModel.DEFAULT_CHROM2_COL),
                "Chromosome (2)",
                1,
                new DataTypeColumnFilter(StringCell.TYPE)
    			));
    	addDialogComponent(new DialogComponentColumnNameSelection(
                new SettingsModelColumnName( BedInterBedNodeModel.START2_COL_PROPERTY, BedInterBedNodeModel.DEFAULT_START2_COL),
                   "ChromStart (2)",
                   1,
                   new DataTypeColumnFilter(IntCell.TYPE)
       			));
    	addDialogComponent(new DialogComponentColumnNameSelection(
                new SettingsModelColumnName( BedInterBedNodeModel.END2_COL_PROPERTY, BedInterBedNodeModel.DEFAULT_END2_COL),
                   "ChromEnd (2)",
                  1,
                   new DataTypeColumnFilter(IntCell.TYPE)
       			));
    	
    	
    	addDialogComponent(new DialogComponentStringSelection(
                new SettingsModelString(BedInterBedNodeModel.OVERLAP_PROPERTY,BedInterBedNodeModel.OVERLAP_DEFAULT_VALUES[1]),
                   "Overlap type:",
                   BedInterBedNodeModel.OVERLAP_DEFAULT_VALUES
                   
       			));
    	}
	}


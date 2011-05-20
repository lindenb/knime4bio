package fr.inserm.umr915.knime4ngs.nodes.vcf.joinpos;

import org.knime.core.data.def.IntCell;
import org.knime.core.data.def.StringCell;
import org.knime.core.node.defaultnodesettings.DefaultNodeSettingsPane;
import org.knime.core.node.defaultnodesettings.DialogComponentColumnNameSelection;
import org.knime.core.node.defaultnodesettings.SettingsModelColumnName;


import fr.inserm.umr915.knime4ngs.corelib.knime.DataTypeColumnFilter;


public class JoinPosNodeDialog extends DefaultNodeSettingsPane
	{
    protected JoinPosNodeDialog()
    	{
    	addDialogComponent(new DialogComponentColumnNameSelection(
             new SettingsModelColumnName( JoinPosNodeModel.CHROM1_COL_PROPERTY, JoinPosNodeModel.DEFAULT_CHROM1_COL),
                "Chromosome Left",
                0,
                new DataTypeColumnFilter(StringCell.TYPE)
    			));
    	
    	addDialogComponent(new DialogComponentColumnNameSelection(
                new SettingsModelColumnName( JoinPosNodeModel.POS1_COL_PROPERTY, JoinPosNodeModel.DEFAULT_POS1_COL),
                   "Position Left",
                   0,
                   new DataTypeColumnFilter(IntCell.TYPE)
       			));
    	
    	addDialogComponent(new DialogComponentColumnNameSelection(
                new SettingsModelColumnName( JoinPosNodeModel.CHROM2_COL_PROPERTY, JoinPosNodeModel.DEFAULT_CHROM2_COL),
                   "Chromosome Right",
                   1,
                   new DataTypeColumnFilter(StringCell.TYPE)
       			));
       	
       	addDialogComponent(new DialogComponentColumnNameSelection(
               new SettingsModelColumnName( JoinPosNodeModel.POS2_COL_PROPERTY, JoinPosNodeModel.DEFAULT_POS2_COL),
                  "Position Left",
                  1,
                  new DataTypeColumnFilter(IntCell.TYPE)
      			));
    	}
	}


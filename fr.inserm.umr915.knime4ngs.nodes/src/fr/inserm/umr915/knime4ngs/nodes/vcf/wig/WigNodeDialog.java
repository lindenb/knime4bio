package fr.inserm.umr915.knime4ngs.nodes.vcf.wig;

import org.knime.core.data.def.IntCell;
import org.knime.core.data.def.StringCell;
import org.knime.core.node.defaultnodesettings.DefaultNodeSettingsPane;
import org.knime.core.node.defaultnodesettings.DialogComponentBoolean;
import org.knime.core.node.defaultnodesettings.DialogComponentColumnNameSelection;
import org.knime.core.node.defaultnodesettings.DialogComponentString;
import org.knime.core.node.defaultnodesettings.SettingsModelBoolean;
import org.knime.core.node.defaultnodesettings.SettingsModelColumnName;
import org.knime.core.node.defaultnodesettings.SettingsModelString;


import fr.inserm.umr915.knime4ngs.corelib.knime.DataTypeColumnFilter;


public class WigNodeDialog extends DefaultNodeSettingsPane
	{
    protected WigNodeDialog()
    	{
    	addDialogComponent(new DialogComponentColumnNameSelection(
             new SettingsModelColumnName( WigNodeModel.CHROM1_COL_PROPERTY, WigNodeModel.DEFAULT_CHROM1_COL),
                "Chromosome Left",
                0,
                new DataTypeColumnFilter(StringCell.TYPE)
    			));
    	
    	addDialogComponent(new DialogComponentColumnNameSelection(
                new SettingsModelColumnName( WigNodeModel.POS1_COL_PROPERTY, WigNodeModel.DEFAULT_POS1_COL),
                   "Position Left",
                   0,
                   new DataTypeColumnFilter(IntCell.TYPE)
       			));
    	
    	addDialogComponent(new DialogComponentString(
                new SettingsModelString( WigNodeModel.WIG_URI_PROPERTY, WigNodeModel.DEFAULT_WIG_URI),
                   "Wig URI",true,30
       			));
    	addDialogComponent(new DialogComponentString(
                new SettingsModelString( WigNodeModel.WIG_COLNAME, WigNodeModel.WIG_COLNAME_DEF),
                   "Column name",true,30
       			));
    	addDialogComponent(new DialogComponentBoolean(
                new SettingsModelBoolean( WigNodeModel.USE_STEP_PROP, WigNodeModel.USE_STEP_DEF),
                   "Use 'step' instead of 'span' for fixedStep wig"
       			));
    	}
	}


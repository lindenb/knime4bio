package fr.inserm.umr915.knime4ngs.nodes.vcf.ucsc.generic;

import org.knime.core.data.def.IntCell;
import org.knime.core.data.def.StringCell;
import org.knime.core.node.defaultnodesettings.DefaultNodeSettingsPane;
import org.knime.core.node.defaultnodesettings.DialogComponentColumnNameSelection;
import org.knime.core.node.defaultnodesettings.DialogComponentStringSelection;
import org.knime.core.node.defaultnodesettings.SettingsModelColumnName;
import org.knime.core.node.defaultnodesettings.SettingsModelString;

import fr.inserm.umr915.knime4ngs.corelib.knime.DataTypeColumnFilter;


public class GenericUCSCNodeDialog extends DefaultNodeSettingsPane
	{
    protected GenericUCSCNodeDialog()
    	{
    	addDialogComponent(new DialogComponentColumnNameSelection(
                new SettingsModelColumnName( GenericUCSCNodeModel.CHROM1_COL_PROPERTY, GenericUCSCNodeModel.DEFAULT_CHROM1_COL),
                   "CHROM",
                   0,
                   new DataTypeColumnFilter(StringCell.TYPE)
       			));
    	addDialogComponent(new DialogComponentColumnNameSelection(
                new SettingsModelColumnName( GenericUCSCNodeModel.POS_COL_PROPERTY, GenericUCSCNodeModel.DEFAULT_POS_COL),
                   "POS",
                   0,
                   new DataTypeColumnFilter(IntCell.TYPE)
       			));
    	
    	
    	addDialogComponent(new DialogComponentStringSelection(
                new SettingsModelString( GenericUCSCNodeModel.HANDLER_NAME_PROPERTY, GenericUCSCNodeModel.HANDLER_NAME_DEFAULT),
                   "UCS table:",
                  UcscTableHandlers.getTableIds()
       			));
    	
    	}
	}


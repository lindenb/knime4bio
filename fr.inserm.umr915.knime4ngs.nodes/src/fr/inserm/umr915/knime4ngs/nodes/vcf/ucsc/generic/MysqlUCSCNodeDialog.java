package fr.inserm.umr915.knime4ngs.nodes.vcf.ucsc.generic;

import org.knime.core.data.def.IntCell;
import org.knime.core.data.def.StringCell;
import org.knime.core.node.defaultnodesettings.DialogComponentBoolean;
import org.knime.core.node.defaultnodesettings.DialogComponentColumnNameSelection;
import org.knime.core.node.defaultnodesettings.DialogComponentStringSelection;
import org.knime.core.node.defaultnodesettings.SettingsModelBoolean;
import org.knime.core.node.defaultnodesettings.SettingsModelColumnName;
import org.knime.core.node.defaultnodesettings.SettingsModelString;

import fr.inserm.umr915.knime4ngs.corelib.knime.DataTypeColumnFilter;
import fr.inserm.umr915.knime4ngs.nodes.sql.AbstractSqlNodeSettingsPane;
import fr.inserm.umr915.knime4ngs.nodes.vcf.ucsc.generic.UcscTableMysqlHandlers;


public class MysqlUCSCNodeDialog extends AbstractSqlNodeSettingsPane
	{
    protected MysqlUCSCNodeDialog()
    	{
    	addDialogComponent(new DialogComponentColumnNameSelection(
                new SettingsModelColumnName( MysqlUCSCNodeModel.CHROM1_COL_PROPERTY, MysqlUCSCNodeModel.DEFAULT_CHROM1_COL),
                   "CHROM",
                   0,
                   new DataTypeColumnFilter(StringCell.TYPE)
       			));
    	addDialogComponent(new DialogComponentColumnNameSelection(
                new SettingsModelColumnName( MysqlUCSCNodeModel.POS_COL_PROPERTY, MysqlUCSCNodeModel.DEFAULT_POS_COL),
                   "POS",
                   0,
                   new DataTypeColumnFilter(IntCell.TYPE)
       			));
    	
    	
    	addDialogComponent(new DialogComponentStringSelection(
                new SettingsModelString( MysqlUCSCNodeModel.HANDLER_NAME_PROPERTY, MysqlUCSCNodeModel.HANDLER_NAME_DEFAULT),
                   "UCSC table:",
                  UcscTableMysqlHandlers.getTableIds()
       			));
    	
    	addDialogComponent(new DialogComponentBoolean(
                new SettingsModelBoolean( MysqlUCSCNodeModel.POS_ONE_BASED, MysqlUCSCNodeModel.POS_ONE_DEFAULT),
                   "First base is +1:"
       			));
    	addDialogComponent(new DialogComponentBoolean(
                new SettingsModelBoolean( MysqlUCSCNodeModel.STOP_PROPERTY, MysqlUCSCNodeModel.STOP_DEFAULT),
                   "Only one hit by position:"
       			));
    	}
	}


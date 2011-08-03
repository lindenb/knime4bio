package fr.inserm.umr915.knime4ngs.nodes.ncbi.elink;

import org.knime.core.data.def.IntCell;
import org.knime.core.node.defaultnodesettings.DefaultNodeSettingsPane;
import org.knime.core.node.defaultnodesettings.DialogComponentColumnNameSelection;
import org.knime.core.node.defaultnodesettings.DialogComponentNumber;
import org.knime.core.node.defaultnodesettings.DialogComponentStringSelection;
import org.knime.core.node.defaultnodesettings.SettingsModelColumnName;
import org.knime.core.node.defaultnodesettings.SettingsModelInteger;
import org.knime.core.node.defaultnodesettings.SettingsModelString;
import fr.inserm.umr915.knime4ngs.corelib.knime.DataTypeColumnFilter;
import fr.inserm.umr915.knime4ngs.nodes.ncbi.EInfo;


public class ELinkNodeDialog extends DefaultNodeSettingsPane
	{
    protected ELinkNodeDialog()
    	{
    	addDialogComponent(new DialogComponentColumnNameSelection(
                new SettingsModelColumnName( ELinkNodeModel.NCBI_ID_COL_PROPERTY, ELinkNodeModel.NCBI_ID_COL_DEFAULT),
                   "Column for EntrezID",
                   0,
                   new DataTypeColumnFilter(IntCell.TYPE)
       			));
    	
    	addDialogComponent(new DialogComponentStringSelection(
    			new SettingsModelString(ELinkNodeModel.DB_IN_PROPERTY, ELinkNodeModel.DB_IN_DEFAULT),
    			"Database Type Input",
    			EInfo.getDatabases()
    			));
    	
    	addDialogComponent(new DialogComponentStringSelection(
    			new SettingsModelString(ELinkNodeModel.DB_OUT_PROPERTY, ELinkNodeModel.DB_OUT_DEFAULT),
    			"Database Type Output",
    			EInfo.getDatabases()
    			));
    	
    	addDialogComponent(new DialogComponentNumber(
    			new SettingsModelInteger(ELinkNodeModel.LIMIT_PROPERTY, ELinkNodeModel.LIMIT_DEFAULT),
    			"Limit",1,10
    			));
    	}
	}


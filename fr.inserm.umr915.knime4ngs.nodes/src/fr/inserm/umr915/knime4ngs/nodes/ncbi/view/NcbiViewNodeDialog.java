package fr.inserm.umr915.knime4ngs.nodes.ncbi.view;

import org.knime.core.data.def.IntCell;
import org.knime.core.node.defaultnodesettings.DefaultNodeSettingsPane;
import org.knime.core.node.defaultnodesettings.DialogComponentColumnNameSelection;
import org.knime.core.node.defaultnodesettings.DialogComponentStringSelection;
import org.knime.core.node.defaultnodesettings.SettingsModelColumnName;
import org.knime.core.node.defaultnodesettings.SettingsModelString;

import fr.inserm.umr915.knime4ngs.corelib.knime.DataTypeColumnFilter;

public class NcbiViewNodeDialog extends DefaultNodeSettingsPane
	{	
    protected NcbiViewNodeDialog()
    	{
    	addDialogComponent(new DialogComponentColumnNameSelection(
                new SettingsModelColumnName(
                		NcbiViewNodeModel.NCBI_ID_PROPERTY, NcbiViewNodeModel.NCBI_ID_DEFAULT),
                    "NCBI ID",0,new DataTypeColumnFilter(IntCell.TYPE)));
    	
    	addDialogComponent(new DialogComponentStringSelection(
                new SettingsModelString(
                		NcbiViewNodeModel.NCBI_DATABASE_PROPERTY, NcbiViewNodeModel.NCBI_DATABASE_DEFAULT),
                    "Database",NcbiViewNodeModel.DATABASES));
    	}
    	
	}


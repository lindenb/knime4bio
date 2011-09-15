package fr.inserm.umr915.knime4ngs.nodes.unix.uniq;

import org.knime.core.node.defaultnodesettings.DefaultNodeSettingsPane;
import org.knime.core.node.defaultnodesettings.DialogComponentBoolean;
import org.knime.core.node.defaultnodesettings.DialogComponentColumnFilter;
import org.knime.core.node.defaultnodesettings.SettingsModelBoolean;
import org.knime.core.node.defaultnodesettings.SettingsModelFilterString;



public class UniqNodeDialog extends DefaultNodeSettingsPane
	{
    protected UniqNodeDialog()
    	{
    	addDialogComponent(new DialogComponentColumnFilter(
                new SettingsModelFilterString(
                		UniqNodeModel.LEFT_COLS_PROPERTY),
                    0,false));
    	addDialogComponent(new DialogComponentBoolean(
                new SettingsModelBoolean(
                		UniqNodeModel.DUPLICATE_PROPERTY, UniqNodeModel.DUPLICATE_DEFAULT),
                    "Show duplicated rows."));
    	addDialogComponent(new DialogComponentBoolean(
                new SettingsModelBoolean(
                		UniqNodeModel.SHOW_COUNT_PROPERTY, UniqNodeModel.SHOW_COUNT_DEFAULT),
                    "Show count"));
    	}
	}


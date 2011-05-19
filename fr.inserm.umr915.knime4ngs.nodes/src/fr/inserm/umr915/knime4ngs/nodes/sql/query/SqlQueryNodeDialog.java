package fr.inserm.umr915.knime4ngs.nodes.sql.query;

import java.util.Arrays;

import org.knime.core.node.defaultnodesettings.DefaultNodeSettingsPane;
import org.knime.core.node.defaultnodesettings.DialogComponentMultiLineString;
import org.knime.core.node.defaultnodesettings.DialogComponentStringSelection;
import org.knime.core.node.defaultnodesettings.SettingsModelString;


public class SqlQueryNodeDialog extends DefaultNodeSettingsPane
	{
    protected SqlQueryNodeDialog()
    	{
    	addDialogComponent(new DialogComponentMultiLineString(
                new SettingsModelString( SqlQueryNodeModel.QUERY_PROPERTY, SqlQueryNodeModel.DEFAULT_QUERY)
                   ,"SQL Query"));
    	addDialogComponent(new DialogComponentStringSelection(
				new SettingsModelString(
						SqlQueryNodeModel.JOIN_PROPERTY,
						SqlQueryNodeModel.DEFAULT_JOIN_PROPERTY
					),"Join Type",
				Arrays.asList(SqlQueryNodeModel.JOIN_PROPERTIES)
				));	
    	}
	}


package fr.inserm.umr915.knime4ngs.nodes.sql.query;

import org.knime.core.node.defaultnodesettings.DialogComponentBoolean;
import org.knime.core.node.defaultnodesettings.DialogComponentMultiLineString;
import org.knime.core.node.defaultnodesettings.SettingsModelBoolean;
import org.knime.core.node.defaultnodesettings.SettingsModelString;

import fr.inserm.umr915.knime4ngs.nodes.sql.AbstractSqlNodeSettingsPane;


public class SqlQueryNodeDialog extends AbstractSqlNodeSettingsPane
	{
    protected SqlQueryNodeDialog()
    	{
    	addDialogComponent(new DialogComponentMultiLineString(
                new SettingsModelString( SqlQueryNodeModel.QUERY_PROPERTY, SqlQueryNodeModel.DEFAULT_QUERY)
                   ,"SQL Query"));
    	addDialogComponent(new DialogComponentBoolean(
				new SettingsModelBoolean(
						SqlQueryNodeModel.STOP_AFTER_FIRST_PROPERTY,
						SqlQueryNodeModel.STOP_AFTER_FIRST_DEFAULT
					),
				"Stop after first match"
				));	
    	}
	}


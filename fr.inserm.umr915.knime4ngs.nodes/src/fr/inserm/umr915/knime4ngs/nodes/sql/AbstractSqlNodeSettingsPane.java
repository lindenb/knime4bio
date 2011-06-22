package fr.inserm.umr915.knime4ngs.nodes.sql;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.knime.core.node.defaultnodesettings.DefaultNodeSettingsPane;
import org.knime.core.node.defaultnodesettings.DialogComponentStringSelection;
import org.knime.core.node.defaultnodesettings.SettingsModelString;

public class AbstractSqlNodeSettingsPane extends DefaultNodeSettingsPane
	{
	protected AbstractSqlNodeSettingsPane()
		{
		Collection<String> set=new ArrayList<String>(AbstractSqlNodeModel.ALL_URIS);
		set.addAll(Arrays.asList(AbstractSqlNodeModel.JDBC_URIS));
		addDialogComponent(new DialogComponentStringSelection(
				new SettingsModelString(AbstractSqlNodeModel.JDBC_URI_PROPERTY, AbstractSqlNodeModel.DEFAULT_JDBC_URI_PROPERTY),
				"JDBC URI:",set,true
				));
		
		set=new ArrayList<String>(AbstractSqlNodeModel.ALL_LOGINS);
		set.add("anonymous");
		set.add("genome");
		addDialogComponent(new DialogComponentStringSelection(
				new SettingsModelString(AbstractSqlNodeModel.JDBC_LOGIN, AbstractSqlNodeModel.DEFAULT_JDBC_LOGIN),
				"JDBC Login:",set,true
				));
		set=new ArrayList<String>(AbstractSqlNodeModel.ALL_PASSWORDS);
		set.add("");
		addDialogComponent(new DialogComponentStringSelection(
				new SettingsModelString(AbstractSqlNodeModel.JDBC_PASSWORD, AbstractSqlNodeModel.DEFAULT_JDBC_PASSWORD),
				"JDBC Password:",set,true
				));
		}
	}

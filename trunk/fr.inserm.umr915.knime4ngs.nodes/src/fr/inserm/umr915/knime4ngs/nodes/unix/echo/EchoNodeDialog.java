package fr.inserm.umr915.knime4ngs.nodes.unix.echo;

import org.knime.core.node.defaultnodesettings.DefaultNodeSettingsPane;
import org.knime.core.node.defaultnodesettings.DialogComponentMultiLineString;
import org.knime.core.node.defaultnodesettings.SettingsModelString;


public class EchoNodeDialog extends DefaultNodeSettingsPane
	{
    protected EchoNodeDialog()
    	{
    	addDialogComponent(new DialogComponentMultiLineString(
                new SettingsModelString( EchoNodeModel.CONTENT_PROPERTY, "")
                    ,"Content",false,80,25));
			
    	}
	}


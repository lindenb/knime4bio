package fr.inserm.umr915.knime4ngs.nodes.unix.head;

import org.knime.core.node.defaultnodesettings.DefaultNodeSettingsPane;
import org.knime.core.node.defaultnodesettings.DialogComponentNumber;
import org.knime.core.node.defaultnodesettings.SettingsModelInteger;


public class HeadNodeDialog extends DefaultNodeSettingsPane
	{
    protected HeadNodeDialog()
    	{
    	addDialogComponent(new DialogComponentNumber(
                new SettingsModelInteger( HeadNodeModel.ROW_PROPERTY, HeadNodeModel.DEFAULT_ROW)
                    ,"Rows",1));
			
    	}
	}


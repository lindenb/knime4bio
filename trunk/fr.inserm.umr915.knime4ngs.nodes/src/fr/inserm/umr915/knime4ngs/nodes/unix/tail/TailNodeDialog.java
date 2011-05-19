package fr.inserm.umr915.knime4ngs.nodes.unix.tail;

import org.knime.core.node.defaultnodesettings.DefaultNodeSettingsPane;
import org.knime.core.node.defaultnodesettings.DialogComponentNumber;
import org.knime.core.node.defaultnodesettings.SettingsModelInteger;


public class TailNodeDialog extends DefaultNodeSettingsPane
	{
    protected TailNodeDialog()
    	{
    	addDialogComponent(new DialogComponentNumber(
                new SettingsModelInteger( TailNodeModel.ROW_PROPERTY, TailNodeModel.DEFAULT_ROW)
                    ,"Rows",1));
			
    	}
	}


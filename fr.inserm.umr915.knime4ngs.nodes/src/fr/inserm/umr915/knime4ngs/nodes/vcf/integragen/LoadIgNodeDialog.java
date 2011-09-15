package fr.inserm.umr915.knime4ngs.nodes.vcf.integragen;


import org.knime.core.data.def.StringCell;
import org.knime.core.node.defaultnodesettings.DefaultNodeSettingsPane;
import org.knime.core.node.defaultnodesettings.DialogComponentColumnNameSelection;
import org.knime.core.node.defaultnodesettings.SettingsModelColumnName;
import fr.inserm.umr915.knime4ngs.corelib.knime.DataTypeColumnFilter;


/**
 * <code>NodeDialog</code> for the "LoadIg" Node.
 * Loads one IG File
 */
public class LoadIgNodeDialog extends DefaultNodeSettingsPane
	{
    public LoadIgNodeDialog()
    	{
		addDialogComponent(new DialogComponentColumnNameSelection(
	    		new SettingsModelColumnName(LoadIgNodeModel.FILEPATH_PROPERTY,LoadIgNodeModel.FILEPATH_DEFAULT),
	    		"Path",0, new DataTypeColumnFilter(StringCell.TYPE) ));
    	
    	}
}


package fr.inserm.umr915.knime4ngs.nodes.vcf.alts2alt;

import org.knime.core.data.def.StringCell;
import org.knime.core.node.defaultnodesettings.DefaultNodeSettingsPane;
import org.knime.core.node.defaultnodesettings.DialogComponentColumnNameSelection;
import org.knime.core.node.defaultnodesettings.SettingsModelColumnName;



import fr.inserm.umr915.knime4ngs.corelib.knime.DataTypeColumnFilter;




public class AltsToAltNodeDialog extends DefaultNodeSettingsPane
	{
	
    protected AltsToAltNodeDialog()
    	{
    	addDialogComponent(new DialogComponentColumnNameSelection(
                new SettingsModelColumnName(
                	AltsToAltNodeModel.ALT_COL_PROPERTY, AltsToAltNodeModel.ALT_COL_DEFAULT),
                    "Alt:",0,new DataTypeColumnFilter(StringCell.TYPE)));
    	}
	}


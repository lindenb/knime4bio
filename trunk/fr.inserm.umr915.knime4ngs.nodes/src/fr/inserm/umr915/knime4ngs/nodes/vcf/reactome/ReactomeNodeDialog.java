package fr.inserm.umr915.knime4ngs.nodes.vcf.reactome;

import org.knime.core.data.def.StringCell;
import org.knime.core.node.defaultnodesettings.DefaultNodeSettingsPane;
import org.knime.core.node.defaultnodesettings.DialogComponentColumnNameSelection;
import org.knime.core.node.defaultnodesettings.SettingsModelColumnName;


import fr.inserm.umr915.knime4ngs.corelib.knime.DataTypeColumnFilter;



public class ReactomeNodeDialog extends DefaultNodeSettingsPane
	{
    protected ReactomeNodeDialog()
    	{
    	addDialogComponent(new DialogComponentColumnNameSelection(
                new SettingsModelColumnName(
                	ReactomeNodeModel.UNIPROT_COl_PROPERTY, ReactomeNodeModel.UNIPROT_COl_DEFAULT),
                    "Uniprot ID:",0,
                    new DataTypeColumnFilter(StringCell.TYPE)));
    	}
	}


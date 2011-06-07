package fr.inserm.umr915.knime4ngs.nodes.ncbi.cited;

import org.knime.core.data.def.IntCell;
import org.knime.core.node.defaultnodesettings.DefaultNodeSettingsPane;
import org.knime.core.node.defaultnodesettings.DialogComponentBoolean;
import org.knime.core.node.defaultnodesettings.DialogComponentColumnNameSelection;
import org.knime.core.node.defaultnodesettings.SettingsModelBoolean;
import org.knime.core.node.defaultnodesettings.SettingsModelColumnName;

import fr.inserm.umr915.knime4ngs.corelib.knime.DataTypeColumnFilter;


public class ArticleCitedNodeDialog extends DefaultNodeSettingsPane
	{
    protected ArticleCitedNodeDialog()
    	{
    	addDialogComponent(new DialogComponentColumnNameSelection(
                new SettingsModelColumnName(
                ArticleCitedNodeModel.PMID_COL_PROPERTY, ArticleCitedNodeModel.PMID_COL_DEFAULT),
                "PMID",
                0,
                new DataTypeColumnFilter(IntCell.TYPE)
    			));
    	addDialogComponent(new DialogComponentBoolean(
    		new SettingsModelBoolean(ArticleCitedNodeModel.SHOW_PROPERTY, ArticleCitedNodeModel.SHOW_DEFAULT),
    		"Show PMID citing"
    		));
    	
    	}
	}


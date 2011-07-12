package fr.inserm.umr915.knime4ngs.nodes.vcf.browser;

import org.knime.core.data.def.IntCell;
import org.knime.core.data.def.StringCell;
import org.knime.core.node.defaultnodesettings.DefaultNodeSettingsPane;
import org.knime.core.node.defaultnodesettings.DialogComponentColumnNameSelection;
import org.knime.core.node.defaultnodesettings.DialogComponentString;
import org.knime.core.node.defaultnodesettings.SettingsModelColumnName;
import org.knime.core.node.defaultnodesettings.SettingsModelString;

import fr.inserm.umr915.knime4ngs.corelib.knime.DataTypeColumnFilter;

public class WebBrowserNodeDialog extends DefaultNodeSettingsPane
	{	
    public WebBrowserNodeDialog()
    	{
    	addDialogComponent(new DialogComponentColumnNameSelection(
                new SettingsModelColumnName(
                		WebBrowserNodeModel.CHROM_COL_PROPERTY, WebBrowserNodeModel.CHROM_COL_DEFAULT),
                    "Chrom:",0,new DataTypeColumnFilter(StringCell.TYPE)));
    	
    	addDialogComponent(new DialogComponentColumnNameSelection(
                new SettingsModelColumnName(
                		WebBrowserNodeModel.POS_COL_PROPERTY, WebBrowserNodeModel.POS_COL_DEFAULT),
                    "Pos:",0,new DataTypeColumnFilter(IntCell.TYPE)));
    	
    	addDialogComponent(new DialogComponentString(
                new SettingsModelString( WebBrowserNodeModel.BUILD_PROPERTY, WebBrowserNodeModel.BUILD_DEFAULT),
                    "UCSC build:",true,20));
    	}
    	
	}


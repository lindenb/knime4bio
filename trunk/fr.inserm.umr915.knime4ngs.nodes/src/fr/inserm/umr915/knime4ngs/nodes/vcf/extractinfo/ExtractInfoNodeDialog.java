package fr.inserm.umr915.knime4ngs.nodes.vcf.extractinfo;

import org.knime.core.data.def.StringCell;
import org.knime.core.node.defaultnodesettings.DefaultNodeSettingsPane;
import org.knime.core.node.defaultnodesettings.DialogComponentColumnNameSelection;
import org.knime.core.node.defaultnodesettings.DialogComponentString;
import org.knime.core.node.defaultnodesettings.DialogComponentStringSelection;
import org.knime.core.node.defaultnodesettings.SettingsModelColumnName;
import org.knime.core.node.defaultnodesettings.SettingsModelString;
import fr.inserm.umr915.knime4ngs.corelib.knime.DataTypeColumnFilter;


public class ExtractInfoNodeDialog extends DefaultNodeSettingsPane
	{
    protected ExtractInfoNodeDialog()
    	{
    	addDialogComponent(new DialogComponentColumnNameSelection(
             new SettingsModelColumnName( ExtractInfoNodeModel.INFO_COL_PROPERTY, ExtractInfoNodeModel.DEFAULT_INFO_COL),
                "INFO column",
                0,
                new DataTypeColumnFilter(StringCell.TYPE)
    			));
    	
    	addDialogComponent(new DialogComponentString(
                new SettingsModelString( ExtractInfoNodeModel.FLAG_PROPERTY, ExtractInfoNodeModel.DEFAULT_FLAG),
                   "Flag name",
                   true,50
       			));
    	addDialogComponent(new DialogComponentStringSelection(
                new SettingsModelString( ExtractInfoNodeModel.DATATYPE_PROPERTY, ExtractInfoNodeModel.DEFAULT_DATATYPE),
                   "DataType",
                   ExtractInfoNodeModel.DATATYPES
       			));
    	}
	}


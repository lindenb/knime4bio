package fr.inserm.umr915.knime4ngs.nodes.vcf.extractfmt;

import org.knime.core.data.def.StringCell;
import org.knime.core.node.defaultnodesettings.DefaultNodeSettingsPane;
import org.knime.core.node.defaultnodesettings.DialogComponentColumnNameSelection;
import org.knime.core.node.defaultnodesettings.DialogComponentString;
import org.knime.core.node.defaultnodesettings.DialogComponentStringSelection;
import org.knime.core.node.defaultnodesettings.SettingsModelColumnName;
import org.knime.core.node.defaultnodesettings.SettingsModelString;
import fr.inserm.umr915.knime4ngs.corelib.knime.DataTypeColumnFilter;


public class ExtractFormatNodeDialog extends DefaultNodeSettingsPane
	{
    protected ExtractFormatNodeDialog()
    	{
    	addDialogComponent(new DialogComponentColumnNameSelection(
             new SettingsModelColumnName( ExtractFormatNodeModel.FORMAT_COL_PROPERTY, ExtractFormatNodeModel.DEFAULT_FORMAT_COLUMN),
                "FORMAT column",
                0,
                new DataTypeColumnFilter(StringCell.TYPE)
    			));
    	
    	addDialogComponent(new DialogComponentColumnNameSelection(
                new SettingsModelColumnName( ExtractFormatNodeModel.CALL_COL_PROPERTY, ExtractFormatNodeModel.DEFAULT_CALL_COLUMN),
                   "CALL column",
                   0,
                   new DataTypeColumnFilter(StringCell.TYPE)
       			));
    	
    	addDialogComponent(new DialogComponentString(
                new SettingsModelString( ExtractFormatNodeModel.FLAG_PROPERTY, ExtractFormatNodeModel.DEFAULT_FLAG),
                   "Flag name (many separated by a comma or a space)",
                   true,30
       			));
    	addDialogComponent(new DialogComponentStringSelection(
                new SettingsModelString( ExtractFormatNodeModel.DATATYPE_PROPERTY, ExtractFormatNodeModel.DEFAULT_DATATYPE),
                   "DataType",
                   ExtractFormatNodeModel.DATATYPES
       			));
    	}
	}


package fr.inserm.umr915.knime4ngs.nodes.vcf.extractfmtmulti;

import org.knime.core.data.def.StringCell;
import org.knime.core.node.defaultnodesettings.DefaultNodeSettingsPane;
import org.knime.core.node.defaultnodesettings.DialogComponentColumnFilter;
import org.knime.core.node.defaultnodesettings.DialogComponentColumnNameSelection;
import org.knime.core.node.defaultnodesettings.DialogComponentString;
import org.knime.core.node.defaultnodesettings.DialogComponentStringSelection;
import org.knime.core.node.defaultnodesettings.SettingsModelColumnName;
import org.knime.core.node.defaultnodesettings.SettingsModelFilterString;
import org.knime.core.node.defaultnodesettings.SettingsModelString;
import fr.inserm.umr915.knime4ngs.corelib.knime.DataTypeColumnFilter;


public class ExtractFormatMultiNodeDialog extends DefaultNodeSettingsPane
	{
    protected ExtractFormatMultiNodeDialog()
    	{
    	addDialogComponent(new DialogComponentColumnNameSelection(
             new SettingsModelColumnName( ExtractFormatMultiNodeModel.FORMAT_COL_PROPERTY, ExtractFormatMultiNodeModel.DEFAULT_FORMAT_COLUMN),
                "FORMAT column",
                0,
                new DataTypeColumnFilter(StringCell.TYPE)
    			));
    	
    	addDialogComponent(new DialogComponentColumnFilter(
                new SettingsModelFilterString(
                		ExtractFormatMultiNodeModel.CALL_COL_PROPERTY,
                		ExtractFormatMultiNodeModel.DEFAULT_CALL_COLUMNS,
                		new String[0]
                		 ),
                   0,true,
                   new DataTypeColumnFilter(StringCell.TYPE)
       			));
    	
    	addDialogComponent(new DialogComponentString(
                new SettingsModelString( ExtractFormatMultiNodeModel.FLAG_PROPERTY, ExtractFormatMultiNodeModel.DEFAULT_FLAG),
                   "Flag name (many separated by a comma or a space)",
                   true,30
       			));
    	addDialogComponent(new DialogComponentStringSelection(
                new SettingsModelString( ExtractFormatMultiNodeModel.DATATYPE_PROPERTY, ExtractFormatMultiNodeModel.DEFAULT_DATATYPE),
                   "DataType",
                   ExtractFormatMultiNodeModel.DATATYPES
       			));
    	}
	}


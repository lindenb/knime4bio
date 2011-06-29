package fr.inserm.umr915.knime4ngs.nodes.bed.writer;

import javax.swing.JFileChooser;

import org.knime.core.data.DataColumnSpec;
import org.knime.core.data.def.IntCell;
import org.knime.core.data.def.StringCell;
import org.knime.core.node.defaultnodesettings.DefaultNodeSettingsPane;
import org.knime.core.node.defaultnodesettings.DialogComponentBoolean;
import org.knime.core.node.defaultnodesettings.DialogComponentColumnNameSelection;
import org.knime.core.node.defaultnodesettings.DialogComponentFileChooser;
import org.knime.core.node.defaultnodesettings.SettingsModelBoolean;
import org.knime.core.node.defaultnodesettings.SettingsModelColumnName;
import org.knime.core.node.defaultnodesettings.SettingsModelString;
import org.knime.core.node.util.ColumnFilter;

import fr.inserm.umr915.knime4ngs.corelib.knime.DataTypeColumnFilter;


public class BedWriterNodeDialog extends DefaultNodeSettingsPane
	{
    protected BedWriterNodeDialog()
    	{
    	addDialogComponent(new DialogComponentFileChooser(
                new SettingsModelString(
                	BedWriterNodeModel.FILENAME_PROPERTY, BedWriterNodeModel.DEFAULT_FILENAME_PROPERTY),
                    "save.as.bed",JFileChooser.SAVE_DIALOG));
    	addDialogComponent(new DialogComponentBoolean(
                new SettingsModelBoolean(
                	BedWriterNodeModel.OFFSET_PROPERTY, true),
                    "First base is '1'."));
    	/** chrom  */
    	
    	addDialogComponent(new DialogComponentColumnNameSelection(
    		new SettingsModelColumnName(BedWriterNodeModel.CHROMOSOME_COL_PROPERTY,BedWriterNodeModel.DEFAULT_CHROM_COL_PROPERTY),
    		"Chromosome",0,new DataTypeColumnFilter(StringCell.TYPE)
    		));
    	/** chrom start */
    	addDialogComponent(new DialogComponentColumnNameSelection(
    		new SettingsModelColumnName(BedWriterNodeModel.CHROMSTART_COL_PROPERTY,BedWriterNodeModel.DEFAULT_CHROMSTART_COL_PROPERTY),
    		"chromStart",0,new DataTypeColumnFilter(IntCell.TYPE)
    		));
    	
    	/** chrom end */
    	addDialogComponent(new DialogComponentColumnNameSelection(
    		new SettingsModelColumnName(BedWriterNodeModel.CHROMEND_COL_PROPERTY,BedWriterNodeModel.DEFAULT_CHROMEND_COL_PROPERTY),
    		"chromEnd",0,false,new DataTypeColumnFilter(IntCell.TYPE)
    		));
    	
    	/** name  */
    	ColumnFilter filter=new ColumnFilter() {
			@Override
			public boolean includeColumn(DataColumnSpec colSpec)
				{
				return true;
				}
			
			@Override
			public String allFilteredMsg() {
				return "should not happen "+getClass();
				}
			};
		addDialogComponent(new DialogComponentColumnNameSelection(
    		new SettingsModelColumnName(BedWriterNodeModel.NAME_COL_PROPERTY,BedWriterNodeModel.DEFAULT_NAME_COL_PROPERTY),
    		"name",0,false,filter
    		));
    	}
	}


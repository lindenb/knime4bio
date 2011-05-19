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
import org.knime.core.node.defaultnodesettings.SettingsModelString;
import org.knime.core.node.util.ColumnFilter;


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
    	ColumnFilter filter=new ColumnFilter() {
			@Override
			public boolean includeColumn(DataColumnSpec colSpec)
				{
				return colSpec.getType().equals(StringCell.TYPE);
				}
			
			@Override
			public String allFilteredMsg() {
				return "Expected a String type for chromosome";
				}
			};
    	addDialogComponent(new DialogComponentColumnNameSelection(
    		new SettingsModelString(BedWriterNodeModel.CHROMOSOME_COL_PROPERTY,BedWriterNodeModel.DEFAULT_CHROM_COL_PROPERTY),
    		"Chromosome",0,filter
    		));
    	/** chrom start */
    	filter=new ColumnFilter() {
			@Override
			public boolean includeColumn(DataColumnSpec colSpec)
				{
				return colSpec.getType().equals(IntCell.TYPE);
				}
			
			@Override
			public String allFilteredMsg() {
				return "Expected a String type for chromStart";
				}
			};
    	addDialogComponent(new DialogComponentColumnNameSelection(
    		new SettingsModelString(BedWriterNodeModel.CHROMSTART_COL_PROPERTY,BedWriterNodeModel.DEFAULT_CHROMSTART_COL_PROPERTY),
    		"chromStart",0,filter
    		));
    	
    	/** chrom end */
    	filter=new ColumnFilter() {
			@Override
			public boolean includeColumn(DataColumnSpec colSpec)
				{
				return colSpec.getType().equals(IntCell.TYPE);
				}
			
			@Override
			public String allFilteredMsg() {
				return "Expected a String type for chromEnd";
				}
			};
    	addDialogComponent(new DialogComponentColumnNameSelection(
    		new SettingsModelString(BedWriterNodeModel.CHROMEND_COL_PROPERTY,BedWriterNodeModel.DEFAULT_CHROMEND_COL_PROPERTY),
    		"chromEnd",0,false,filter
    		));
    	
    	/** name  */
    	filter=new ColumnFilter() {
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
    		new SettingsModelString(BedWriterNodeModel.NAME_COL_PROPERTY,BedWriterNodeModel.DEFAULT_NAME_COL_PROPERTY),
    		"name",0,false,filter
    		));	
			
			
    	}
	}


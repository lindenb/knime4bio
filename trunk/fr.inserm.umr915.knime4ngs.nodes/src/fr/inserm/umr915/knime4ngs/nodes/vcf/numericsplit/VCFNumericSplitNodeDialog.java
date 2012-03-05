package fr.inserm.umr915.knime4ngs.nodes.vcf.numericsplit;

import org.knime.core.data.def.DoubleCell;
import org.knime.core.data.def.IntCell;
import org.knime.core.data.def.StringCell;
import org.knime.core.node.defaultnodesettings.DefaultNodeSettingsPane;
import org.knime.core.node.defaultnodesettings.DialogComponentBoolean;
import org.knime.core.node.defaultnodesettings.DialogComponentColumnNameSelection;
import org.knime.core.node.defaultnodesettings.DialogComponentNumber;
import org.knime.core.node.defaultnodesettings.SettingsModelColumnName;
import org.knime.core.node.defaultnodesettings.SettingsModelBoolean;
import org.knime.core.node.defaultnodesettings.SettingsModelInteger;

import fr.inserm.umr915.knime4ngs.corelib.knime.DataTypeColumnFilter;




public class VCFNumericSplitNodeDialog extends DefaultNodeSettingsPane
	{
	
    protected VCFNumericSplitNodeDialog()
    	{
    	
    	addDialogComponent(new DialogComponentColumnNameSelection(
                new SettingsModelColumnName(
                	VCFNumericSplitNodeModel.CHROM_COL_PROPERTY, VCFNumericSplitNodeModel.CHROM_COL_DEFAULT),
                    "Chrom:",0,new DataTypeColumnFilter(StringCell.TYPE)));
    	
    	addDialogComponent(new DialogComponentColumnNameSelection(
                new SettingsModelColumnName(
                	VCFNumericSplitNodeModel.POS_COL_PROPERTY, VCFNumericSplitNodeModel.POS_COL_DEFAULT),
                    "Pos:",0,new DataTypeColumnFilter(IntCell.TYPE)));
    	addDialogComponent(new DialogComponentColumnNameSelection(
                new SettingsModelColumnName(
                	VCFNumericSplitNodeModel.REF_COL_PROPERTY, VCFNumericSplitNodeModel.REF_COL_DEFAULT),
                    "Ref:",0,new DataTypeColumnFilter(StringCell.TYPE)));
    	addDialogComponent(new DialogComponentColumnNameSelection(
                new SettingsModelColumnName(
                	VCFNumericSplitNodeModel.ALT_COL_PROPERTY, VCFNumericSplitNodeModel.ALT_COL_DEFAULT),
                    "Alt:",0,new DataTypeColumnFilter(StringCell.TYPE)));
    	
   	
    	addDialogComponent(new DialogComponentBoolean(
                new SettingsModelBoolean( VCFNumericSplitNodeModel.USE_REF_ALT_PROPERTY, VCFNumericSplitNodeModel.DEFAULT_USE_REF_ALT),
                   "Use Ref/Alt"
       			));

    	
    	
    	addDialogComponent(new DialogComponentColumnNameSelection(
                new SettingsModelColumnName(
                	VCFNumericSplitNodeModel.VALUE_COL_PROPERTY, VCFNumericSplitNodeModel.VALUE_COL_DEFAULT),
                    "Observed Column:",0,new DataTypeColumnFilter(IntCell.TYPE,DoubleCell.TYPE)));
    
    	addDialogComponent(new DialogComponentNumber(
                new SettingsModelInteger(
                		VCFNumericSplitNodeModel.MIN_QUAL_PROPERTY, VCFNumericSplitNodeModel.DEFAULT_MIN_QUAL),
                    "Minimum Value (inclusive=",1));
    	addDialogComponent(new DialogComponentNumber(
                new SettingsModelInteger(
                		VCFNumericSplitNodeModel.MAX_QUAL_PROPERTY, VCFNumericSplitNodeModel.DEFAULT_MAX_QUAL),
                    "Maximum Value (inclusive)",1));
 	
    	
    	addDialogComponent(new DialogComponentBoolean(
                new SettingsModelBoolean( VCFNumericSplitNodeModel.INVERT_PROPERTY, VCFNumericSplitNodeModel.DEFAULT_INVERT),
                   "Invert"
       			));
 	
    	}
	}


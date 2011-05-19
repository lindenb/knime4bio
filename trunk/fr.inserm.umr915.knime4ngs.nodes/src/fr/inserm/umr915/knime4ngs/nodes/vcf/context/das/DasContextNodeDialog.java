package fr.inserm.umr915.knime4ngs.nodes.vcf.context.das;

import org.knime.core.data.def.IntCell;
import org.knime.core.data.def.StringCell;
import org.knime.core.node.defaultnodesettings.DefaultNodeSettingsPane;
import org.knime.core.node.defaultnodesettings.DialogComponentColumnNameSelection;
import org.knime.core.node.defaultnodesettings.DialogComponentNumber;
import org.knime.core.node.defaultnodesettings.DialogComponentString;
import org.knime.core.node.defaultnodesettings.SettingsModelColumnName;
import org.knime.core.node.defaultnodesettings.SettingsModelInteger;
import org.knime.core.node.defaultnodesettings.SettingsModelString;

import fr.inserm.umr915.knime4ngs.corelib.knime.DataTypeColumnFilter;



public class DasContextNodeDialog extends DefaultNodeSettingsPane
	{
    protected DasContextNodeDialog()
    	{
    	addDialogComponent(new DialogComponentString(
                new SettingsModelString(
                		DasContextNodeModel.DAS_URI_PROPERTY, DasContextNodeModel.DEFAULT_DAS_URI),
                    "Das URI:",true,100));
    	
    	addDialogComponent(new DialogComponentColumnNameSelection(
                new SettingsModelColumnName(
                		DasContextNodeModel.CHROM_COL_PROPERTY, DasContextNodeModel.CHROM_COL_DEFAULT),
                    "Chrom:",0,new DataTypeColumnFilter(StringCell.TYPE)));
    	
    	addDialogComponent(new DialogComponentColumnNameSelection(
                new SettingsModelColumnName(
                		DasContextNodeModel.POS_COL_PROPERTY, DasContextNodeModel.POS_COL_DEFAULT),
                    "Pos:",0,new DataTypeColumnFilter(IntCell.TYPE)));
		
    	
    	addDialogComponent(new DialogComponentNumber(
                new SettingsModelInteger(
                		DasContextNodeModel.EXTEND_PROPERTY, DasContextNodeModel.DEFAULT_EXTEND),
                    "Extend:",1,100));
    	
    	
    	
    	
    	}
	}


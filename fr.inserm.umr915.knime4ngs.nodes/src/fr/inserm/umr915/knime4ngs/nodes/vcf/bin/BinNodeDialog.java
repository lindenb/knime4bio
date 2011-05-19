package fr.inserm.umr915.knime4ngs.nodes.vcf.bin;


import org.knime.core.data.def.IntCell;
import org.knime.core.node.defaultnodesettings.DefaultNodeSettingsPane;
import org.knime.core.node.defaultnodesettings.DialogComponentBoolean;
import org.knime.core.node.defaultnodesettings.DialogComponentColumnNameSelection;
import org.knime.core.node.defaultnodesettings.DialogComponentString;
import org.knime.core.node.defaultnodesettings.SettingsModelBoolean;
import org.knime.core.node.defaultnodesettings.SettingsModelString;

import org.knime.core.node.defaultnodesettings.SettingsModelColumnName;


import fr.inserm.umr915.knime4ngs.corelib.knime.DataTypeColumnFilter;


public class BinNodeDialog extends DefaultNodeSettingsPane
	{
    protected BinNodeDialog()
    	{
    	addDialogComponent(new DialogComponentColumnNameSelection(
                new SettingsModelColumnName(
                	BinNodeModel.COL_START_CONFIG, BinNodeModel.COL_START_DEFAULT),
                    "Chrom Start:",0,new DataTypeColumnFilter(IntCell.TYPE)));
    	addDialogComponent(new DialogComponentColumnNameSelection(
                new SettingsModelColumnName(
                	BinNodeModel.COL_END_CONFIG, BinNodeModel.COL_END_DEFAULT),
                    "Chrom End:",0,new DataTypeColumnFilter(IntCell.TYPE)));
    	
    	addDialogComponent(new DialogComponentString(
                new SettingsModelString(
                	BinNodeModel.BINNAME_CONFIG, BinNodeModel.BINNAME_DEFAULT),
                    "'Bin' Column name:",true,20));
    	
    	addDialogComponent(new DialogComponentBoolean(
                new SettingsModelBoolean(
                	BinNodeModel.INDEX1_CONFIG, BinNodeModel.INDEX1_DEFAULT),
                    "Data are +1 based:"));
    	
    	}
	}


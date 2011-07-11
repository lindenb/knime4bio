package fr.inserm.umr915.knime4ngs.nodes.vcf.genscan;

import org.knime.core.data.def.IntCell;
import org.knime.core.data.def.StringCell;
import org.knime.core.node.defaultnodesettings.DefaultNodeSettingsPane;
import org.knime.core.node.defaultnodesettings.DialogComponentColumnNameSelection;
import org.knime.core.node.defaultnodesettings.SettingsModelColumnName;

import fr.inserm.umr915.knime4ngs.corelib.knime.DataTypeColumnFilter;
import fr.inserm.umr915.knime4ngs.corelib.knime.NumericColumnFilter;

public class GenScanNodeDialog extends DefaultNodeSettingsPane
	{	
    protected GenScanNodeDialog()
    	{
    	addDialogComponent(new DialogComponentColumnNameSelection(
                new SettingsModelColumnName(
                		GenScanNodeModel.CHROM_COL_PROPERTY, GenScanNodeModel.CHROM_COL_DEFAULT),
                    "Chrom:",0,new DataTypeColumnFilter(StringCell.TYPE)));
    	
    	addDialogComponent(new DialogComponentColumnNameSelection(
                new SettingsModelColumnName(
                		GenScanNodeModel.START_COL_PROPERTY, GenScanNodeModel.START_COL_DEFAULT),
                    "ChromStart:",0,new DataTypeColumnFilter(IntCell.TYPE)));
    	addDialogComponent(new DialogComponentColumnNameSelection(
                new SettingsModelColumnName(
                		GenScanNodeModel.END_COL_PROPERTY, GenScanNodeModel.END_COL_DEFAULT),
                    "ChromEnd:",0,new DataTypeColumnFilter(IntCell.TYPE)));
    	addDialogComponent(new DialogComponentColumnNameSelection(
                new SettingsModelColumnName(
                		GenScanNodeModel.VAL_COL_PROPERTY, GenScanNodeModel.VAL_COL_DEFAULT),
                    "Value:",0,false,true,new NumericColumnFilter()));
    	}
    	
	}


package fr.inserm.umr915.knime4ngs.nodes.vcf.bbfile;

import javax.swing.JFileChooser;

import org.knime.core.data.def.IntCell;
import org.knime.core.data.def.StringCell;
import org.knime.core.node.defaultnodesettings.DefaultNodeSettingsPane;
import org.knime.core.node.defaultnodesettings.DialogComponentColumnNameSelection;
import org.knime.core.node.defaultnodesettings.DialogComponentFileChooser;
import org.knime.core.node.defaultnodesettings.DialogComponentString;
import org.knime.core.node.defaultnodesettings.SettingsModelColumnName;
import org.knime.core.node.defaultnodesettings.SettingsModelString;


import fr.inserm.umr915.knime4ngs.corelib.knime.DataTypeColumnFilter;


public class BigBedFileNodeDialog extends DefaultNodeSettingsPane
	{
    protected BigBedFileNodeDialog()
    	{
    	addDialogComponent(new DialogComponentColumnNameSelection(
             new SettingsModelColumnName( BigBedFileNodeModel.CHROM1_COL_PROPERTY, BigBedFileNodeModel.DEFAULT_CHROM1_COL),
                "Chromosome",
                0,
                new DataTypeColumnFilter(StringCell.TYPE)
    			));
    	
    	addDialogComponent(new DialogComponentColumnNameSelection(
                new SettingsModelColumnName( BigBedFileNodeModel.POS1_COL_PROPERTY, BigBedFileNodeModel.DEFAULT_POS1_COL),
                   "Position",
                   0,
                   new DataTypeColumnFilter(IntCell.TYPE)
       			));
    	
    	addDialogComponent(new DialogComponentFileChooser(
                new SettingsModelString( BigBedFileNodeModel.WIG_URI_PROPERTY, BigBedFileNodeModel.DEFAULT_WIG_URI),
                   "BigBedWigFile ",JFileChooser.OPEN_DIALOG,".bb",".bw",".bigbed",".bigwig"
       			));
    	addDialogComponent(new DialogComponentString(
                new SettingsModelString( BigBedFileNodeModel.WIG_COLNAME, BigBedFileNodeModel.WIG_COLNAME_DEF),
                   "Column name",true,30
       			));
    	
    	}
	}


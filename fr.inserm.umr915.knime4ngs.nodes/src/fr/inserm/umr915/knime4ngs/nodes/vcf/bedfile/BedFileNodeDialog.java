package fr.inserm.umr915.knime4ngs.nodes.vcf.bedfile;

import org.knime.core.data.def.IntCell;
import org.knime.core.data.def.StringCell;
import org.knime.core.node.defaultnodesettings.DefaultNodeSettingsPane;
import org.knime.core.node.defaultnodesettings.DialogComponentBoolean;
import org.knime.core.node.defaultnodesettings.DialogComponentColumnNameSelection;
import org.knime.core.node.defaultnodesettings.DialogComponentNumber;
import org.knime.core.node.defaultnodesettings.DialogComponentString;
import org.knime.core.node.defaultnodesettings.SettingsModelBoolean;
import org.knime.core.node.defaultnodesettings.SettingsModelColumnName;
import org.knime.core.node.defaultnodesettings.SettingsModelInteger;
import org.knime.core.node.defaultnodesettings.SettingsModelString;

import fr.inserm.umr915.knime4ngs.corelib.knime.DataTypeColumnFilter;


public class BedFileNodeDialog extends DefaultNodeSettingsPane
	{
    protected BedFileNodeDialog()
    	{
    	addDialogComponent(new DialogComponentColumnNameSelection(
                new SettingsModelColumnName( BedFileNodeModel.CHROM1_COL_PROPERTY, BedFileNodeModel.DEFAULT_CHROM1_COL),
                   "CHROM",
                   0,
                   new DataTypeColumnFilter(StringCell.TYPE)
       			));
    	addDialogComponent(new DialogComponentColumnNameSelection(
                new SettingsModelColumnName( BedFileNodeModel.POS_COL_PROPERTY, BedFileNodeModel.DEFAULT_POS_COL),
                   "POS",
                   0,
                   new DataTypeColumnFilter(IntCell.TYPE)
       			));
    	
    	
    	addDialogComponent(new DialogComponentString(
                new SettingsModelString( BedFileNodeModel.FILENAME_URI_PROPERTY, BedFileNodeModel.DEFAULT_FILENAME_URI),
                   "File URI",
                   true,0
       			));
    	
    	addDialogComponent(new DialogComponentNumber(
             new SettingsModelInteger( BedFileNodeModel.CHROM2_COL_PROPERTY, BedFileNodeModel.DEFAULT_CHROM2_COL),
                "Chromosome",
                1
    			));
    	addDialogComponent(new DialogComponentNumber(
                new SettingsModelInteger( BedFileNodeModel.START_COL_PROPERTY, BedFileNodeModel.DEFAULT_START_COL),
                   "ChromStart",
                   1
       			));
    	addDialogComponent(new DialogComponentNumber(
                new SettingsModelInteger( BedFileNodeModel.END_COL_PROPERTY, BedFileNodeModel.DEFAULT_END_COL),
                   "ChromEnd",
                   1
       			));
    	
    	addDialogComponent(new DialogComponentString(
                new SettingsModelString( BedFileNodeModel.FILE_IGNORE_PROPERTY, BedFileNodeModel.DEFAULT_FILE_IGNORE),
                   "Ignore lines starting with",
                   true,50
       			));
    	
    	addDialogComponent(new DialogComponentString(
                new SettingsModelString( BedFileNodeModel.FILE_DELIM_PROPERTY, BedFileNodeModel.DEFAULT_FILE_DELIM),
                   "Regex delimiter",
                   true,5
       			));
    	
    	addDialogComponent(new DialogComponentString(
                new SettingsModelString( BedFileNodeModel.COL_PREFIX_PROPERTY, BedFileNodeModel.DEFAULT_ICOL_PREFIX),
                   "Column prefix",
                   true,10
       			));
    	addDialogComponent(new DialogComponentBoolean(
                new SettingsModelBoolean( BedFileNodeModel.STOP_FIRST_PROPERTY, BedFileNodeModel.STOP_FIRST_DEF),
                   "Stop after first hit"
       			));
    	}
	}


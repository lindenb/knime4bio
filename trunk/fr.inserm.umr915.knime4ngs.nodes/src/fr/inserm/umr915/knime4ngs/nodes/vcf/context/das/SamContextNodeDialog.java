package fr.inserm.umr915.knime4ngs.nodes.vcf.context.sam;

import javax.swing.JFileChooser;

import org.knime.core.data.def.IntCell;
import org.knime.core.data.def.StringCell;
import org.knime.core.node.defaultnodesettings.DefaultNodeSettingsPane;
import org.knime.core.node.defaultnodesettings.DialogComponentColumnNameSelection;
import org.knime.core.node.defaultnodesettings.DialogComponentFileChooser;
import org.knime.core.node.defaultnodesettings.DialogComponentNumber;
import org.knime.core.node.defaultnodesettings.SettingsModelColumnName;
import org.knime.core.node.defaultnodesettings.SettingsModelInteger;
import org.knime.core.node.defaultnodesettings.SettingsModelString;

import fr.inserm.umr915.knime4ngs.corelib.knime.DataTypeColumnFilter;



public class SamContextNodeDialog extends DefaultNodeSettingsPane
	{
    protected SamContextNodeDialog()
    	{
    	addDialogComponent(new DialogComponentFileChooser(
                new SettingsModelString(
                		SamContextNodeModel.REFERENCE_URI_PROPERTY, SamContextNodeModel.DEFAULT_REFERENCE_URI),
                    "ref.genome",JFileChooser.OPEN_DIALOG,".fa",".fasta"));
    	
    	addDialogComponent(new DialogComponentColumnNameSelection(
                new SettingsModelColumnName(
                		SamContextNodeModel.CHROM_COL_PROPERTY, SamContextNodeModel.CHROM_COL_DEFAULT),
                    "Chrom:",0,new DataTypeColumnFilter(StringCell.TYPE)));
    	
    	addDialogComponent(new DialogComponentColumnNameSelection(
                new SettingsModelColumnName(
                		SamContextNodeModel.POS_COL_PROPERTY, SamContextNodeModel.POS_COL_DEFAULT),
                    "Pos:",0,new DataTypeColumnFilter(IntCell.TYPE)));
		
    	
    	addDialogComponent(new DialogComponentNumber(
                new SettingsModelInteger(
                		SamContextNodeModel.EXTEND_PROPERTY, SamContextNodeModel.DEFAULT_EXTEND),
                    "Extend:",1,100));

    	}
	}


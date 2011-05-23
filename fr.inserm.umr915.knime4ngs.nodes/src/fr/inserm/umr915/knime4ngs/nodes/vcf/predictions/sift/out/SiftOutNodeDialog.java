package fr.inserm.umr915.knime4ngs.nodes.vcf.predictions.sift.out;

import org.knime.core.data.def.IntCell;
import org.knime.core.data.def.StringCell;
import org.knime.core.node.defaultnodesettings.DefaultNodeSettingsPane;
import org.knime.core.node.defaultnodesettings.DialogComponentColumnNameSelection;
import org.knime.core.node.defaultnodesettings.DialogComponentFileChooser;
import org.knime.core.node.defaultnodesettings.SettingsModelColumnName;
import org.knime.core.node.defaultnodesettings.SettingsModelString;

import fr.inserm.umr915.knime4ngs.corelib.knime.DataTypeColumnFilter;
import fr.inserm.umr915.knime4ngs.nodes.vcf.predictions.AbstractPredictionOutNodeModel;


/**
 * <code>NodeDialog</code> for the "VCFSource" Node.
 * Reads a VCF file
 *
 * This node dialog derives from {@link DefaultNodeSettingsPane} which allows
 * creation of a simple dialog with standard components. If you need a more 
 * complex dialog please derive directly from 
 * {@link org.knime.core.node.NodeDialogPane}.
 * 
 * @author Pierre Lindenbaum
 */
public class SiftOutNodeDialog extends DefaultNodeSettingsPane
	{
    protected SiftOutNodeDialog()
    	{
    	SettingsModelString settings=new SettingsModelString(SiftOutNodeModel.FILENAME_PROPERTY,SiftOutNodeModel.DEFAULT_FILENAME);
    	addDialogComponent(new DialogComponentFileChooser(settings, "Save as"));
    	
    	addDialogComponent(new DialogComponentColumnNameSelection(
                new SettingsModelColumnName(
            		AbstractPredictionOutNodeModel.CHROM_COL_PROPERTY,
            		AbstractPredictionOutNodeModel.DEFAULT_CHROM_COL),
                   "Chromosome",
                   0,
                   new DataTypeColumnFilter(StringCell.TYPE)
       			));
       	
       	addDialogComponent(new DialogComponentColumnNameSelection(
                   new SettingsModelColumnName(
            		   AbstractPredictionOutNodeModel.POS_COL_PROPERTY,
            		   AbstractPredictionOutNodeModel.DEFAULT_POS_COL),
                      "Position",
                      0,
                      new DataTypeColumnFilter(IntCell.TYPE)
          			));
       	
       	addDialogComponent(new DialogComponentColumnNameSelection(
                   new SettingsModelColumnName(
            		   AbstractPredictionOutNodeModel.REF_COL_PROPERTY,
            		   AbstractPredictionOutNodeModel.DEFAULT_REF_COL),
                      "Ref",
                      0,
                      new DataTypeColumnFilter(StringCell.TYPE)
          			));
       	addDialogComponent(new DialogComponentColumnNameSelection(
                new SettingsModelColumnName(
                	AbstractPredictionOutNodeModel.ALT_COL_PROPERTY,
                	AbstractPredictionOutNodeModel.DEFAULT_ALT_COL),
                   "Alt",
                   0,
                   new DataTypeColumnFilter(StringCell.TYPE)
       			));
    	}
	}


package fr.inserm.umr915.knime4ngs.nodes.vcf.predictions.polyphen.in;

import org.knime.core.data.def.IntCell;
import org.knime.core.data.def.StringCell;
import org.knime.core.node.defaultnodesettings.DefaultNodeSettingsPane;
import org.knime.core.node.defaultnodesettings.DialogComponentColumnNameSelection;
import org.knime.core.node.defaultnodesettings.SettingsModelColumnName;

import fr.inserm.umr915.knime4ngs.corelib.knime.DataTypeColumnFilter;
import fr.inserm.umr915.knime4ngs.nodes.vcf.predictions.AbstractPredictionInNodeModel;


/**
 * @author Pierre Lindenbaum
 */
public class PolyphenInNodeDialog extends DefaultNodeSettingsPane
	{
    protected PolyphenInNodeDialog()
    	{
    	addDialogComponent(new DialogComponentColumnNameSelection(
                new SettingsModelColumnName(
            		AbstractPredictionInNodeModel.CHROM_COL_PROPERTY,
            		AbstractPredictionInNodeModel.DEFAULT_CHROM_COL),
                   "Chromosome",
                   0,
                   new DataTypeColumnFilter(StringCell.TYPE)
       			));
       	
       	addDialogComponent(new DialogComponentColumnNameSelection(
                   new SettingsModelColumnName(
                		  AbstractPredictionInNodeModel.POS_COL_PROPERTY,
                		  AbstractPredictionInNodeModel.DEFAULT_POS_COL),
                      "Position",
                      0,
                      new DataTypeColumnFilter(IntCell.TYPE)
          			));
       	
       	addDialogComponent(new DialogComponentColumnNameSelection(
                   new SettingsModelColumnName(
                		   AbstractPredictionInNodeModel.REF_COL_PROPERTY,
                		   AbstractPredictionInNodeModel.DEFAULT_REF_COL),
                      "Ref",
                      0,
                      new DataTypeColumnFilter(StringCell.TYPE)
          			));
       	addDialogComponent(new DialogComponentColumnNameSelection(
                new SettingsModelColumnName(
                		AbstractPredictionInNodeModel.ALT_COL_PROPERTY,
                		AbstractPredictionInNodeModel.DEFAULT_ALT_COL),
                   "Alt",
                   0,
                   new DataTypeColumnFilter(StringCell.TYPE)
       			));
       	
    	addDialogComponent(new DialogComponentColumnNameSelection(
                new SettingsModelColumnName(
                		AbstractPredictionInNodeModel.PREDICTION_COL_PROPERTY,
                		AbstractPredictionInNodeModel.DEFAULT_PREDICTION_COL),
                   "Polyphen position",
                   1,
                   new DataTypeColumnFilter(StringCell.TYPE)
       			));
    	}
	}


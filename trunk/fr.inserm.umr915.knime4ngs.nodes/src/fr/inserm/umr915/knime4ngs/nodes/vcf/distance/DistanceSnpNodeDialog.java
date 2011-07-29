package fr.inserm.umr915.knime4ngs.nodes.vcf.distance;

import org.knime.core.data.def.IntCell;
import org.knime.core.data.def.StringCell;
import org.knime.core.node.defaultnodesettings.DefaultNodeSettingsPane;
import org.knime.core.node.defaultnodesettings.DialogComponentColumnNameSelection;
import org.knime.core.node.defaultnodesettings.SettingsModelColumnName;



import fr.inserm.umr915.knime4ngs.corelib.knime.DataTypeColumnFilter;




public class DistanceSnpNodeDialog extends DefaultNodeSettingsPane
	{
    protected DistanceSnpNodeDialog()
    	{
    	addDialogComponent(new DialogComponentColumnNameSelection(
                new SettingsModelColumnName(
                	DistanceSnpNodeModel.CHROM_COL_PROPERTY, DistanceSnpNodeModel.CHROM_COL_DEFAULT),
                    "Chrom:",0,new DataTypeColumnFilter(StringCell.TYPE)));
    	
    	addDialogComponent(new DialogComponentColumnNameSelection(
                new SettingsModelColumnName(
                	DistanceSnpNodeModel.POS_COL_PROPERTY, DistanceSnpNodeModel.POS_COL_DEFAULT),
                    "Pos:",0,new DataTypeColumnFilter(IntCell.TYPE)));
    	addDialogComponent(new DialogComponentColumnNameSelection(
                new SettingsModelColumnName(
                	DistanceSnpNodeModel.SAMPLE_COL_PROPERTY, DistanceSnpNodeModel.SAMPLE_COL_DEFAULT),
                    "Sample:",0,new DataTypeColumnFilter(StringCell.TYPE)));
    	}
	}


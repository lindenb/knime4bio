package fr.inserm.umr915.knime4ngs.nodes.vcf.samplepersnp2;

import org.knime.core.data.def.IntCell;
import org.knime.core.data.def.StringCell;
import org.knime.core.node.defaultnodesettings.DefaultNodeSettingsPane;
import org.knime.core.node.defaultnodesettings.DialogComponentColumnNameSelection;
import org.knime.core.node.defaultnodesettings.SettingsModelColumnName;



import fr.inserm.umr915.knime4ngs.corelib.knime.DataTypeColumnFilter;




public class SamplePerSnpNodeDialog extends DefaultNodeSettingsPane
	{
	
    protected SamplePerSnpNodeDialog()
    	{
    	
    	addDialogComponent(new DialogComponentColumnNameSelection(
                new SettingsModelColumnName(
                	SamplePerSnpNodeModel.CHROM_COL_PROPERTY, SamplePerSnpNodeModel.CHROM_COL_DEFAULT),
                    "Chrom:",0,new DataTypeColumnFilter(StringCell.TYPE)));
    	
    	addDialogComponent(new DialogComponentColumnNameSelection(
                new SettingsModelColumnName(
                	SamplePerSnpNodeModel.POS_COL_PROPERTY, SamplePerSnpNodeModel.POS_COL_DEFAULT),
                    "Pos:",0,new DataTypeColumnFilter(IntCell.TYPE)));
    	
    	addDialogComponent(new DialogComponentColumnNameSelection(
                new SettingsModelColumnName(
                	SamplePerSnpNodeModel.SAMPLE_COL_PROPERTY, SamplePerSnpNodeModel.SAMPLE_COL_DEFAULT),
                    "Sample:",0,new DataTypeColumnFilter(StringCell.TYPE)));
    	
    	}
	}


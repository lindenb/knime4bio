package fr.inserm.umr915.knime4ngs.nodes.vcf.string;
import org.knime.core.data.def.StringCell;
import org.knime.core.node.defaultnodesettings.DefaultNodeSettingsPane;
import org.knime.core.node.defaultnodesettings.DialogComponentColumnNameSelection;
import org.knime.core.node.defaultnodesettings.DialogComponentNumber;
import org.knime.core.node.defaultnodesettings.SettingsModelColumnName;
import org.knime.core.node.defaultnodesettings.SettingsModelInteger;



import fr.inserm.umr915.knime4ngs.corelib.knime.DataTypeColumnFilter;




public class StringEmblNodeDialog extends DefaultNodeSettingsPane
	{
	
    protected StringEmblNodeDialog()
    	{
    	addDialogComponent(new DialogComponentColumnNameSelection(
                new SettingsModelColumnName(
                		StringEmblNodeModel.GENE_COL_PROPERTY, StringEmblNodeModel.GENE_COL_DEFAULT),
                    "Gene:",0,new DataTypeColumnFilter(StringCell.TYPE)));
    	addDialogComponent(new DialogComponentNumber(
                new SettingsModelInteger(
                		StringEmblNodeModel.SCORE_PROPERTY, StringEmblNodeModel.DEFAULT_SCORE),
                    "Score:",1,10));
    	addDialogComponent(new DialogComponentNumber(
                new SettingsModelInteger(
                		StringEmblNodeModel.TAXON_PROPERTY, StringEmblNodeModel.DEFAULT_TAXON),
                    "NCBI Taxon Id:",1,10));
    	}
    	
	}


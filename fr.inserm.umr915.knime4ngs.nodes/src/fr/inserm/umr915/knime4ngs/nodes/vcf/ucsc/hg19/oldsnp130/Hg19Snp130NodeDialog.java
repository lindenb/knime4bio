package fr.inserm.umr915.knime4ngs.nodes.vcf.ucsc.hg19.oldsnp130;

import org.knime.core.node.defaultnodesettings.DefaultNodeSettingsPane;
import org.knime.core.node.defaultnodesettings.SettingsModelString;
import org.knime.core.node.defaultnodesettings.DialogComponentBoolean;
import org.knime.core.node.defaultnodesettings.DialogComponentString;
import org.knime.core.node.defaultnodesettings.DialogComponentColumnNameSelection;
import org.knime.core.node.defaultnodesettings.SettingsModelBoolean;
import org.knime.core.node.defaultnodesettings.SettingsModelColumnName;
import org.knime.core.node.util.ColumnFilter;
import org.knime.core.data.def.IntCell;
import org.knime.core.data.def.StringCell;
import org.knime.core.data.DataColumnSpec;


@Deprecated
public class Hg19Snp130NodeDialog extends DefaultNodeSettingsPane
	{
    public Hg19Snp130NodeDialog()
    	{
    	/** uri */
    	addDialogComponent(new DialogComponentString(
                new SettingsModelString(Hg19Snp130NodeModel.URI_PROPERTY, Hg19Snp130NodeModel.DEFAULT_URI_PROPERTY),
                    "Data URL",true,20));
    	/** join */
    	addDialogComponent(new DialogComponentBoolean(
                new SettingsModelBoolean(
                    Hg19Snp130NodeModel.JOIN_PROPERTY, Hg19Snp130NodeModel.DEFAULT_JOIN_PROPERTY),
                    "Limit to one hg19Snp130 per row"));

    	
    	/** VCF chromosome */
    	addDialogComponent(new DialogComponentColumnNameSelection(
    		new SettingsModelColumnName(Hg19Snp130NodeModel.CHROM_COL_PROPERTY,Hg19Snp130NodeModel.DEFAULT_CHROM_COL),
    		"VCF Chromosome",0, new ColumnFilter()
    			{
    			@Override
    			public String allFilteredMsg()
					{
					return "No Chromosome column defined";
					}
    			@Override
    			public boolean includeColumn(DataColumnSpec colSpec)
    				{
    				return colSpec.getType()==StringCell.TYPE;
    				}
    			}
    		));
    	/** VCF POS */
    	addDialogComponent(new DialogComponentColumnNameSelection(
    		new SettingsModelColumnName(Hg19Snp130NodeModel.CHROM_POS1_PROPERTY,Hg19Snp130NodeModel.DEFAULT_POS1_COL),
    		"VCF POS",0, new ColumnFilter()
    			{
    			@Override
    			public String allFilteredMsg()
					{
					return "No POS column defined";
					}
    			@Override
    			public boolean includeColumn(DataColumnSpec colSpec)
    				{
    				return colSpec.getType()==IntCell.TYPE;
    				}
    			}
    		));
    	
    	
    	/* dialog component for chrom */
		addDialogComponent(new DialogComponentBoolean(
                new SettingsModelBoolean(
                    Hg19Snp130NodeModel.SHOW_CHROM_PROPERTY,Hg19Snp130NodeModel.DEFAULT_SHOW_CHROM_PROPERTY),
                    "Show chrom"));
    	/* dialog component for chromStart */
		addDialogComponent(new DialogComponentBoolean(
                new SettingsModelBoolean(
                    Hg19Snp130NodeModel.SHOW_CHROMSTART_PROPERTY,Hg19Snp130NodeModel.DEFAULT_SHOW_CHROMSTART_PROPERTY),
                    "Show chromStart"));
    	/* dialog component for chromEnd */
		addDialogComponent(new DialogComponentBoolean(
                new SettingsModelBoolean(
                    Hg19Snp130NodeModel.SHOW_CHROMEND_PROPERTY,Hg19Snp130NodeModel.DEFAULT_SHOW_CHROMEND_PROPERTY),
                    "Show chromEnd"));
    	/* dialog component for name */
		addDialogComponent(new DialogComponentBoolean(
                new SettingsModelBoolean(
                    Hg19Snp130NodeModel.SHOW_NAME_PROPERTY,Hg19Snp130NodeModel.DEFAULT_SHOW_NAME_PROPERTY),
                    "Show name"));
    	}
	}



package fr.inserm.umr915.knime4ngs.nodes.vcf.chr;

import org.knime.core.data.def.StringCell;
import org.knime.core.node.defaultnodesettings.DefaultNodeSettingsPane;
import org.knime.core.node.defaultnodesettings.DialogComponentBoolean;
import org.knime.core.node.defaultnodesettings.DialogComponentColumnNameSelection;
import org.knime.core.node.defaultnodesettings.SettingsModelBoolean;
import org.knime.core.node.defaultnodesettings.SettingsModelColumnName;
import fr.inserm.umr915.knime4ngs.corelib.knime.DataTypeColumnFilter;


/**
 * @author Pierre Lindenbaum
 */
public class NormalizeChromNodeDialog extends DefaultNodeSettingsPane
	{
	
	
    protected NormalizeChromNodeDialog()
    	{
    	addDialogComponent(new DialogComponentColumnNameSelection(
                new SettingsModelColumnName( NormalizeChromNodeModel.CHROM_COL_PROPERTY, NormalizeChromNodeModel.CHROM_COL_DEFAULT),
                   "Chromosome",
                   0,
                   new DataTypeColumnFilter(StringCell.TYPE)
       			));
    	addDialogComponent(new DialogComponentBoolean(
                new SettingsModelBoolean( NormalizeChromNodeModel.CHR_PREFIX_PROPERTY, NormalizeChromNodeModel.CHR_PREFIX_DEFAULT),
                   "Append 'chr' prefix"
       			));
    	}
	}


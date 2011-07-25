package fr.inserm.umr915.knime4ngs.nodes.vcf.chr;

import org.knime.core.data.DataColumnSpec;
import org.knime.core.data.def.IntCell;
import org.knime.core.data.def.StringCell;
import org.knime.core.node.defaultnodesettings.DefaultNodeSettingsPane;
import org.knime.core.node.defaultnodesettings.DialogComponentBoolean;
import org.knime.core.node.defaultnodesettings.DialogComponentColumnNameSelection;
import org.knime.core.node.defaultnodesettings.SettingsModelBoolean;
import org.knime.core.node.defaultnodesettings.SettingsModelColumnName;
import org.knime.core.node.util.ColumnFilter;


/**
 * @author Pierre Lindenbaum
 */
public class NormalizeChromNodeDialog extends DefaultNodeSettingsPane
	{
	public NormalizeChromNodeDialog()
    	{
    	addDialogComponent(new DialogComponentColumnNameSelection(
                new SettingsModelColumnName( NormalizeChromNodeModel.CHROM_COL_PROPERTY, NormalizeChromNodeModel.CHROM_COL_DEFAULT),
                   "Chromosome",
                   0,
                   new ColumnFilter()
                	{
                	@Override
                	public String allFilteredMsg()
                		{
                		return "Cannot find a column for a chromosome";
                		}
                	@Override
                	public boolean includeColumn(DataColumnSpec colSpec)
                		{
                		return colSpec.getType().equals(IntCell.TYPE) ||
                			   colSpec.getType().equals(StringCell.TYPE);
                		}
                	}
       			));
    	addDialogComponent(new DialogComponentBoolean(
                new SettingsModelBoolean( NormalizeChromNodeModel.CHR_PREFIX_PROPERTY, NormalizeChromNodeModel.CHR_PREFIX_DEFAULT),
                   "Append 'chr' prefix"
       			));
    	}
	}


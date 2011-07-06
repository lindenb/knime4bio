package fr.inserm.umr915.knime4ngs.nodes.vcf.cut;

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




/**
 * @author Pierre Lindenbaum
 */
public class CutRegionNodeDialog extends DefaultNodeSettingsPane
	{
	public CutRegionNodeDialog()
    	{
    	addDialogComponent(new DialogComponentColumnNameSelection(
                new SettingsModelColumnName(
                		CutRegionNodeModel.CHROM_COL_PROPERTY, CutRegionNodeModel.CHROM_COL_DEFAULT),
                    "Chrom:",0,new DataTypeColumnFilter(StringCell.TYPE)));
    	
    	addDialogComponent(new DialogComponentColumnNameSelection(
                new SettingsModelColumnName(
                		CutRegionNodeModel.POS_COL_PROPERTY, CutRegionNodeModel.POS_COL_DEFAULT),
                    "Pos:",0,new DataTypeColumnFilter(IntCell.TYPE)));
    	
    	addDialogComponent(new DialogComponentString(
                new SettingsModelString( CutRegionNodeModel.SELECT_CHROM_PROPERTY, CutRegionNodeModel.SELECT_CHROM_DEFAULT),
                   "Chromosome",
                   true,50
       			));
    	addDialogComponent(new DialogComponentNumber(
                new SettingsModelInteger( CutRegionNodeModel.SELECT_START_PROPERTY, CutRegionNodeModel.SELECT_START_DEFAULT),
                   "Start Region",
                   1
       			));
    	addDialogComponent(new DialogComponentNumber(
                new SettingsModelInteger( CutRegionNodeModel.SELECT_END_PROPERTY, CutRegionNodeModel.SELECT_END_DEFAULT),
                   "End Region",
                   1
       			));
    	addDialogComponent(new DialogComponentBoolean(
                new SettingsModelBoolean( CutRegionNodeModel.INVERT_PROPERTY, CutRegionNodeModel.INVERT_DEFAULT),
                   "Invert"
       			));
    	}
	}


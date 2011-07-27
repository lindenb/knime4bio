package fr.inserm.umr915.knime4ngs.nodes.vcf.igv;

import org.knime.core.data.def.IntCell;
import org.knime.core.data.def.StringCell;
import org.knime.core.node.defaultnodesettings.DefaultNodeSettingsPane;
import org.knime.core.node.defaultnodesettings.DialogComponentColumnNameSelection;
import org.knime.core.node.defaultnodesettings.DialogComponentNumber;
import org.knime.core.node.defaultnodesettings.DialogComponentString;
import org.knime.core.node.defaultnodesettings.SettingsModelColumnName;
import org.knime.core.node.defaultnodesettings.SettingsModelInteger;
import org.knime.core.node.defaultnodesettings.SettingsModelString;

import fr.inserm.umr915.knime4ngs.corelib.knime.DataTypeColumnFilter;

public class IGVNodeDialog extends DefaultNodeSettingsPane
	{	
    public IGVNodeDialog()
    	{
    	addDialogComponent(new DialogComponentColumnNameSelection(
                new SettingsModelColumnName(
                		IGVNodeModel.CHROM_COL_PROPERTY, IGVNodeModel.CHROM_COL_DEFAULT),
                    "Chrom:",0,new DataTypeColumnFilter(StringCell.TYPE)));
    	
    	addDialogComponent(new DialogComponentColumnNameSelection(
                new SettingsModelColumnName(
                		IGVNodeModel.POS_COL_PROPERTY, IGVNodeModel.POS_COL_DEFAULT),
                    "Pos:",0,new DataTypeColumnFilter(IntCell.TYPE)));
    	
    	addDialogComponent(new DialogComponentString(
                new SettingsModelString( IGVNodeModel.IGV_HOST_PROPERTY, IGVNodeModel.IGV_HOST_DEFAULT),
                    "IGV Host:",true,20));
    	addDialogComponent(new DialogComponentNumber(
                new SettingsModelInteger( IGVNodeModel.IGV_PORT_PROPERTY, IGVNodeModel.IGV_PORT_DEFAULT),
                    "IGV Port:",10));
    	}
    	
	}


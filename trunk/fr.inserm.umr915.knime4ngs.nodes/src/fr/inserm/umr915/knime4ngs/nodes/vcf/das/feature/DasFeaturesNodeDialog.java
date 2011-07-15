package fr.inserm.umr915.knime4ngs.nodes.vcf.das.feature;

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



public class DasFeaturesNodeDialog extends DefaultNodeSettingsPane
	{
    protected DasFeaturesNodeDialog()
    	{
    	addDialogComponent(new DialogComponentString(
                new SettingsModelString(
                		DasFeaturesNodeModel.DAS_URI_PROPERTY, DasFeaturesNodeModel.DEFAULT_DAS_URI),
                    "Das Fatures URI :",true,50));
    	
    	addDialogComponent(new DialogComponentColumnNameSelection(
                new SettingsModelColumnName(
                		DasFeaturesNodeModel.CHROM_COL_PROPERTY, DasFeaturesNodeModel.CHROM_COL_DEFAULT),
                    "Chrom:",0,new DataTypeColumnFilter(StringCell.TYPE)));
    	
    	addDialogComponent(new DialogComponentColumnNameSelection(
                new SettingsModelColumnName(
                		DasFeaturesNodeModel.POS_COL_PROPERTY, DasFeaturesNodeModel.POS_COL_DEFAULT),
                    "Pos:",0,new DataTypeColumnFilter(IntCell.TYPE)));
		
    	
    	addDialogComponent(new DialogComponentNumber(
                new SettingsModelInteger(
                		DasFeaturesNodeModel.MAX_RECORD_PROPERTY, DasFeaturesNodeModel.MAX_RECORD_DEF),
                    "Max hits per row:",1,50));
    	
    	
    	
    	
    	}
	}


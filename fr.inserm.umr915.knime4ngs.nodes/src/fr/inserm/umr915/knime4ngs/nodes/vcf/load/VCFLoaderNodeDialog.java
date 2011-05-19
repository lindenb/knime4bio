package fr.inserm.umr915.knime4ngs.nodes.vcf.load;

import org.knime.core.data.def.StringCell;
import org.knime.core.node.defaultnodesettings.DefaultNodeSettingsPane;
import org.knime.core.node.defaultnodesettings.DialogComponentColumnNameSelection;
import org.knime.core.node.defaultnodesettings.DialogComponentNumber;
import org.knime.core.node.defaultnodesettings.SettingsModelColumnName;
import org.knime.core.node.defaultnodesettings.SettingsModelInteger;
import org.knime.core.node.defaultnodesettings.SettingsModelString;


import fr.inserm.umr915.knime4ngs.corelib.knime.DataTypeColumnFilter;


/**
 * <code>NodeDialog</code> for the "VCFLoader" Node.
 * Loads a VCF File
 *
 * This node dialog derives from {@link DefaultNodeSettingsPane} which allows
 * creation of a simple dialog with standard components. If you need a more 
 * complex dialog please derive directly from 
 * {@link org.knime.core.node.NodeDialogPane}.
 * 
 * @author Pierre Lindenbaum
 */
public class VCFLoaderNodeDialog extends DefaultNodeSettingsPane {

    /**
     * New pane for configuring VCFLoader node dialog.
     * This is just a suggestion to demonstrate possible default dialog
     * components.
     */
    protected VCFLoaderNodeDialog()
    	{
    	//depth
    	addDialogComponent(new DialogComponentNumber(
                new SettingsModelInteger(
                	VCFLoaderNodeModel.MIN_DEPTH_PROPERTY, VCFLoaderNodeModel.DEFAULT_MIN_DEPTH),
                    "Minimum Depth",1));
    	addDialogComponent(new DialogComponentNumber(
                new SettingsModelInteger(
                	VCFLoaderNodeModel.MAX_DEPTH_PROPERTY, VCFLoaderNodeModel.DEFAULT_MAX_DEPTH),
                    "Maximum Depth",1));
    	
    	//column name
		addDialogComponent(new DialogComponentColumnNameSelection(
	    		new SettingsModelColumnName(VCFLoaderNodeModel.SAMPLE_COL_PROPERTY,VCFLoaderNodeModel.SAMPLE_COL_DEFAULT),
	    		"Sample name",0, new DataTypeColumnFilter(StringCell.TYPE) ));
		
		//column vcf
		addDialogComponent(new DialogComponentColumnNameSelection(
	    		new SettingsModelColumnName(VCFLoaderNodeModel.VCF_COL_PROPERTY,VCFLoaderNodeModel.VCF_COL_DEFAULT),
	    		"VCF URL",0, new DataTypeColumnFilter(StringCell.TYPE) ));
    	
    	}
}


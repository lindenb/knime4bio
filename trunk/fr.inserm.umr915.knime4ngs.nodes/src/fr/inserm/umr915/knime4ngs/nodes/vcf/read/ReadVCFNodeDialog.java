package fr.inserm.umr915.knime4ngs.nodes.vcf.read;

import org.knime.core.data.def.StringCell;
import org.knime.core.node.defaultnodesettings.DefaultNodeSettingsPane;
import org.knime.core.node.defaultnodesettings.DialogComponentColumnNameSelection;
import org.knime.core.node.defaultnodesettings.DialogComponentNumber;
import org.knime.core.node.defaultnodesettings.SettingsModelColumnName;
import org.knime.core.node.defaultnodesettings.SettingsModelInteger;


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
public class ReadVCFNodeDialog extends DefaultNodeSettingsPane {

    /**
     * New pane for configuring VCFLoader node dialog.
     * This is just a suggestion to demonstrate possible default dialog
     * components.
     */
    protected ReadVCFNodeDialog()
    	{
    	//qual
    	addDialogComponent(new DialogComponentNumber(
                new SettingsModelInteger(
                	ReadVCFNodeModel.MIN_QUAL_PROPERTY, ReadVCFNodeModel.DEFAULT_MIN_QUAL),
                    "Minimum Qual",1));
    	
    	//column name
		addDialogComponent(new DialogComponentColumnNameSelection(
	    		new SettingsModelColumnName(ReadVCFNodeModel.SAMPLE_COL_PROPERTY,ReadVCFNodeModel.SAMPLE_COL_DEFAULT),
	    		"Sample name",0, new DataTypeColumnFilter(StringCell.TYPE) ));
		
		//column vcf
		addDialogComponent(new DialogComponentColumnNameSelection(
	    		new SettingsModelColumnName(ReadVCFNodeModel.VCF_COL_PROPERTY,ReadVCFNodeModel.VCF_COL_DEFAULT),
	    		"VCF URL",0, new DataTypeColumnFilter(StringCell.TYPE) ));
    	
    	}
}


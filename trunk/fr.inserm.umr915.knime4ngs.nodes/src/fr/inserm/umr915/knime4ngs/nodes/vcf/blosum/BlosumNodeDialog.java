package fr.inserm.umr915.knime4ngs.nodes.vcf.blosum;

import org.knime.core.data.def.StringCell;
import org.knime.core.node.defaultnodesettings.DefaultNodeSettingsPane;
import org.knime.core.node.defaultnodesettings.DialogComponentColumnNameSelection;
import org.knime.core.node.defaultnodesettings.DialogComponentStringSelection;
import org.knime.core.node.defaultnodesettings.SettingsModelColumnName;
import org.knime.core.node.defaultnodesettings.SettingsModelString;

import fr.inserm.umr915.knime4ngs.corelib.knime.DataTypeColumnFilter;


/**
 * @author Pierre Lindenbaum
 */
public class BlosumNodeDialog extends DefaultNodeSettingsPane
	{
	
	
    protected BlosumNodeDialog()
    	{
    	addDialogComponent(new DialogComponentColumnNameSelection(
                new SettingsModelColumnName( BlosumNodeModel.AA1_PROPERTY, BlosumNodeModel.AA1_DEFAULT),
                   "Amino Acid 1",
                   0,
                   new DataTypeColumnFilter(StringCell.TYPE)
       			));
    	addDialogComponent(new DialogComponentColumnNameSelection(
                new SettingsModelColumnName( BlosumNodeModel.AA2_PROPERTY, BlosumNodeModel.AA2_DEFAULT),
                   "Amino Acid 2",
                   0,
                   new DataTypeColumnFilter(StringCell.TYPE)
       			));
    	addDialogComponent(new DialogComponentStringSelection(
    			new SettingsModelString(BlosumNodeModel.MATRIX_PROPERTY, BlosumNodeModel.MATRIX_DEFAULT), "Matrix",
    		BlosumNodeModel.ALL_MATRIX
    			
    			));
    	}
	}


package fr.inserm.umr915.knime4ngs.nodes.vcf.substitution;

import org.knime.core.data.def.StringCell;
import org.knime.core.node.defaultnodesettings.DefaultNodeSettingsPane;
import org.knime.core.node.defaultnodesettings.DialogComponentColumnNameSelection;
import org.knime.core.node.defaultnodesettings.SettingsModelColumnName;



import fr.inserm.umr915.knime4ngs.corelib.knime.DataTypeColumnFilter;




public class SubstitutionNodeDialog extends DefaultNodeSettingsPane
	{
	
    protected SubstitutionNodeDialog()
    	{
    	
    	
    	addDialogComponent(new DialogComponentColumnNameSelection(
                new SettingsModelColumnName(
                		SubstitutionNodeModel.REF_COL_PROPERTY, SubstitutionNodeModel.REF_COL_DEFAULT),
                    "Ref:",0,new DataTypeColumnFilter(StringCell.TYPE)));
    	addDialogComponent(new DialogComponentColumnNameSelection(
                new SettingsModelColumnName(
                		SubstitutionNodeModel.ALT_COL_PROPERTY, SubstitutionNodeModel.ALT_COL_DEFAULT),
                    "Alt:",0,new DataTypeColumnFilter(StringCell.TYPE)));
    	
    	
    	}
	}


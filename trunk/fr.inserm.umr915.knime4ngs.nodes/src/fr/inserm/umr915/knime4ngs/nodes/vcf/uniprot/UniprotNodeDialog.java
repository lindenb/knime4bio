package fr.inserm.umr915.knime4ngs.nodes.vcf.uniprot;


import org.knime.core.data.def.IntCell;
import org.knime.core.data.def.StringCell;
import org.knime.core.node.defaultnodesettings.DefaultNodeSettingsPane;
import org.knime.core.node.defaultnodesettings.DialogComponentColumnNameSelection;

import org.knime.core.node.defaultnodesettings.SettingsModelColumnName;



import fr.inserm.umr915.knime4ngs.corelib.knime.DataTypeColumnFilter;


public class UniprotNodeDialog extends DefaultNodeSettingsPane
	{
    protected UniprotNodeDialog()
    	{
    	addDialogComponent(new DialogComponentColumnNameSelection(
             new SettingsModelColumnName( UniprotNodeModel.ACC_PROPERTY, UniprotNodeModel.ACC_DEFAULT),
                "Accession ID",
                0,
                new DataTypeColumnFilter(StringCell.TYPE)
    			));
    	
    	addDialogComponent(new DialogComponentColumnNameSelection(
                new SettingsModelColumnName( UniprotNodeModel.POS_PROPERTY, UniprotNodeModel.POS_DEFAULT),
                   "Position in peptide (first is +1)",
                   0,
                   new DataTypeColumnFilter(IntCell.TYPE)
       			));
    	
    	}
	}


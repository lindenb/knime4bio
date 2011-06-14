package fr.inserm.umr915.knime4ngs.nodes.vcf.tabix;

import javax.swing.JFileChooser;

import org.knime.core.data.def.IntCell;
import org.knime.core.data.def.StringCell;
import org.knime.core.node.defaultnodesettings.DefaultNodeSettingsPane;
import org.knime.core.node.defaultnodesettings.DialogComponentColumnNameSelection;
import org.knime.core.node.defaultnodesettings.DialogComponentFileChooser;
import org.knime.core.node.defaultnodesettings.DialogComponentString;
import org.knime.core.node.defaultnodesettings.SettingsModelColumnName;
import org.knime.core.node.defaultnodesettings.SettingsModelString;


import fr.inserm.umr915.knime4ngs.corelib.knime.DataTypeColumnFilter;


public class TabixNodeDialog extends DefaultNodeSettingsPane
	{
    protected TabixNodeDialog()
    	{
    	addDialogComponent(new DialogComponentColumnNameSelection(
             new SettingsModelColumnName( TabixNodeModel.CHROM1_COL_PROPERTY, TabixNodeModel.DEFAULT_CHROM1_COL),
                "Chromosome Left",
                0,
                new DataTypeColumnFilter(StringCell.TYPE)
    			));
    	
    	addDialogComponent(new DialogComponentColumnNameSelection(
                new SettingsModelColumnName( TabixNodeModel.POS1_COL_PROPERTY, TabixNodeModel.DEFAULT_POS1_COL),
                   "Position Left",
                   0,
                   new DataTypeColumnFilter(IntCell.TYPE)
       			));
    	
    	addDialogComponent(new DialogComponentFileChooser(
                new SettingsModelString( TabixNodeModel.TABIX_FILENAME_PROPERTY, TabixNodeModel.DEFAULT_TABIX_FILENAME),
                   "TabixFile",JFileChooser.OPEN_DIALOG,".gz",".GZ")
                  );
       	
       	addDialogComponent(new DialogComponentString(
               new SettingsModelString( TabixNodeModel.TABIX_PREFIX_PROPERTY, TabixNodeModel.DEFAULT_TABIX_PREFIX),
                  "Column Prefix",false,30
      			));
    	}
	}


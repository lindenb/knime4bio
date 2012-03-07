package fr.inserm.umr915.knime4ngs.nodes.vcf.extractsnpeff;

import org.knime.core.data.def.StringCell;
import org.knime.core.node.defaultnodesettings.DefaultNodeSettingsPane;
import org.knime.core.node.defaultnodesettings.DialogComponentColumnNameSelection;
import org.knime.core.node.defaultnodesettings.DialogComponentString;
import org.knime.core.node.defaultnodesettings.DialogComponentStringSelection;
import org.knime.core.node.defaultnodesettings.SettingsModelColumnName;
import org.knime.core.node.defaultnodesettings.SettingsModelString;
import fr.inserm.umr915.knime4ngs.corelib.knime.DataTypeColumnFilter;


public class ExtractSnpEffNodeDialog extends DefaultNodeSettingsPane
	{
    protected ExtractSnpEffNodeDialog()
    	{
    	addDialogComponent(new DialogComponentColumnNameSelection(
             new SettingsModelColumnName( ExtractSnpEffNodeModel.INFO_COL_PROPERTY, ExtractSnpEffNodeModel.DEFAULT_INFO_COL),
                "INFO column",
                0,
                new DataTypeColumnFilter(StringCell.TYPE)
    			));
    	
    	}
	}


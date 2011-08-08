package fr.inserm.umr915.knime4ngs.nodes.vcf.sum;

import org.knime.core.node.defaultnodesettings.DefaultNodeSettingsPane;
import org.knime.core.node.defaultnodesettings.DialogComponentColumnFilter;
import org.knime.core.node.defaultnodesettings.DialogComponentString;
import org.knime.core.node.defaultnodesettings.SettingsModelFilterString;
import org.knime.core.node.defaultnodesettings.SettingsModelString;
import fr.inserm.umr915.knime4ngs.corelib.knime.NumericColumnFilter;


public class SumNodeDialog extends DefaultNodeSettingsPane
	{
    protected SumNodeDialog()
    	{
    	
    	
    	addDialogComponent(new DialogComponentColumnFilter(
                new SettingsModelFilterString(
                		SumNodeModel.NUMERIC_COLS_PROPERTY,
                		new String[0],
                		new String[0]
                		 ),
                   0,true,
                   new NumericColumnFilter()
       			));
    	
    	addDialogComponent(new DialogComponentString(
                new SettingsModelString( SumNodeModel.FLAG_PROPERTY, SumNodeModel.DEFAULT_FLAG),
                   "Column prefix",
                   true,30));
   
    	}
	}


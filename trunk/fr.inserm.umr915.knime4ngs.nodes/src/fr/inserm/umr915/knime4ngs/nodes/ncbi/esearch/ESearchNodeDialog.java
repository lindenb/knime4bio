package fr.inserm.umr915.knime4ngs.nodes.ncbi.esearch;

import org.knime.core.node.defaultnodesettings.DefaultNodeSettingsPane;
import org.knime.core.node.defaultnodesettings.DialogComponentMultiLineString;
import org.knime.core.node.defaultnodesettings.DialogComponentNumber;
import org.knime.core.node.defaultnodesettings.DialogComponentStringSelection;
import org.knime.core.node.defaultnodesettings.SettingsModelInteger;
import org.knime.core.node.defaultnodesettings.SettingsModelString;
import fr.inserm.umr915.knime4ngs.nodes.ncbi.EInfo;



public class ESearchNodeDialog extends DefaultNodeSettingsPane
	{
    protected ESearchNodeDialog()
    	{
    	addDialogComponent(new DialogComponentMultiLineString(
                new SettingsModelString( ESearchNodeModel.NCBI_QUERY_PROPERTY, ESearchNodeModel.NCBI_QUERY_DEFAULT)
                    ,"Entrez Query:",false,80,25));

    	addDialogComponent(new DialogComponentStringSelection(
    			new SettingsModelString(ESearchNodeModel.DB_IN_PROPERTY, ESearchNodeModel.DB_IN_DEFAULT),
    			"Database Input",
    			EInfo.getDatabases()
    			));
    	
    
    	
    	addDialogComponent(new DialogComponentNumber(
    			new SettingsModelInteger(ESearchNodeModel.LIMIT_PROPERTY, ESearchNodeModel.LIMIT_DEFAULT),
    			"Limit",1,10
    			));
    	}
	}


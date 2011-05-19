package fr.inserm.umr915.knime4ngs.nodes.vcf.go;

import org.knime.core.node.defaultnodesettings.DefaultNodeSettingsPane;
import org.knime.core.node.defaultnodesettings.DialogComponentMultiLineString;
import org.knime.core.node.defaultnodesettings.SettingsModelString;



/**
 * @author Pierre Lindenbaum
 */
public class GoNodeDialog extends DefaultNodeSettingsPane
	{
	
	
    protected GoNodeDialog()
    	{
    	addDialogComponent(new DialogComponentMultiLineString(
                new SettingsModelString( GoNodeModel.GENONTOLOGY_PROPERTY, GoNodeModel.GENONTOLOGY_DEFAULT),
                    "GeneOntology Terms",false,20,10));
    	}
	}


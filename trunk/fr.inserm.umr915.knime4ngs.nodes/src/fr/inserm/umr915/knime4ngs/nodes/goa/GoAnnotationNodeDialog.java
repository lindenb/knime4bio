package fr.inserm.umr915.knime4ngs.nodes.goa;

import org.knime.core.node.defaultnodesettings.DefaultNodeSettingsPane;
import org.knime.core.node.defaultnodesettings.DialogComponentString;
import org.knime.core.node.defaultnodesettings.SettingsModelString;



/**
 * @author Pierre Lindenbaum
 */
public class GoAnnotationNodeDialog extends DefaultNodeSettingsPane
	{
    protected GoAnnotationNodeDialog()
    	{
    	addDialogComponent(new DialogComponentString(
                new SettingsModelString( GoAnnotationNodeModel.GENONTOLOGY_PROPERTY, GoAnnotationNodeModel.GENONTOLOGY_DEFAULT),
                    "GeneOntology Term"));
    	}
	}


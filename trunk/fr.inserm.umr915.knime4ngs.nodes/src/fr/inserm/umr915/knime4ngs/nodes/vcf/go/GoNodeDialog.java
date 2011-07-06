package fr.inserm.umr915.knime4ngs.nodes.vcf.go;

import org.knime.core.node.defaultnodesettings.DefaultNodeSettingsPane;
import org.knime.core.node.defaultnodesettings.DialogComponentNumber;
import org.knime.core.node.defaultnodesettings.DialogComponentString;
import org.knime.core.node.defaultnodesettings.SettingsModelInteger;
import org.knime.core.node.defaultnodesettings.SettingsModelString;





/**
 * @author Pierre Lindenbaum
 */
public class GoNodeDialog extends DefaultNodeSettingsPane
	{
    protected GoNodeDialog()
    	{
    	addDialogComponent(new DialogComponentString(
                new SettingsModelString( GoNodeModel.GENONTOLOGY_PROPERTY, GoNodeModel.GENONTOLOGY_DEFAULT),
                    "GeneOntology Term",true,20));
    	addDialogComponent(new DialogComponentNumber(
                new SettingsModelInteger( GoNodeModel.TAXON_PROPERTY, GoNodeModel.TAXON_DEFAULT),
                   "Taxon",
                   1
       			));
    	}
	}


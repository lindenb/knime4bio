package fr.inserm.umr915.knime4ngs.nodes.vcf.qual;

import org.knime.core.node.defaultnodesettings.DefaultNodeSettingsPane;
import org.knime.core.node.defaultnodesettings.DialogComponentNumber;
import org.knime.core.node.defaultnodesettings.SettingsModelDouble;



/**
 * <code>NodeDialog</code> for the "VCFSource" Node.
 * Reads a VCF file
 *
 * This node dialog derives from {@link DefaultNodeSettingsPane} which allows
 * creation of a simple dialog with standard components. If you need a more 
 * complex dialog please derive directly from 
 * {@link org.knime.core.node.NodeDialogPane}.
 * 
 * @author Pierre Lindenbaum
 */
public class QualNodeDialog extends DefaultNodeSettingsPane
	{
	
	
    protected QualNodeDialog()
    	{
    	addDialogComponent(new DialogComponentNumber(
                new SettingsModelDouble( QualNodeModel.MIN_QUAL_PROPERTY, QualNodeModel.DEFAULT_MIN_QUAL),
                    "Minimum Quality",1));
    	}
	}


package fr.inserm.umr915.knime4ngs.nodes.mw.export;

import org.knime.core.node.defaultnodesettings.DefaultNodeSettingsPane;
import org.knime.core.node.defaultnodesettings.DialogComponentFileChooser;
import org.knime.core.node.defaultnodesettings.SettingsModelString;

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
public class MediaWikiExportNodeDialog extends DefaultNodeSettingsPane
	{
    protected MediaWikiExportNodeDialog()
    	{
    	SettingsModelString settings=new SettingsModelString(MediaWikiExportNodeModel.FILENAME_PROPERTY,MediaWikiExportNodeModel.DEFAULT_FILENAME);
    	addDialogComponent(new DialogComponentFileChooser(settings, "Save as"));
    	}
	}


package fr.inserm.umr915.knime4ngs.nodes.ncbi;

import org.knime.core.node.defaultnodesettings.DefaultNodeSettingsPane;
import org.knime.core.node.defaultnodesettings.DialogComponentMultiLineString;
import org.knime.core.node.defaultnodesettings.DialogComponentNumber;
import org.knime.core.node.defaultnodesettings.SettingsModelIntegerBounded;
import org.knime.core.node.defaultnodesettings.SettingsModelString;


public class DefaultNcbiEUtilsNodeDialog extends DefaultNodeSettingsPane
	{
    public DefaultNcbiEUtilsNodeDialog()
    	{
    	addDialogComponent(new DialogComponentMultiLineString(
                new SettingsModelString( AbstractNcbiEUtilsNodeModel.TERM_PROPERTY, "")
                    ,"NCBI Query",true,40,20));
    	addDialogComponent(new DialogComponentNumber(new SettingsModelIntegerBounded(AbstractNcbiEUtilsNodeModel.LIMIT_PROPERTY, AbstractNcbiEUtilsNodeModel.LIMIT_DEFAULT, 1, 10000), "Limit", 1));
    	}
	}


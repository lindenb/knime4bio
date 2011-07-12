package fr.inserm.umr915.knime4ngs.nodes.vcf.homopolymer;

import org.knime.core.node.defaultnodesettings.DefaultNodeSettingsPane;
import org.knime.core.node.defaultnodesettings.DialogComponentNumber;
import org.knime.core.node.defaultnodesettings.SettingsModelInteger;


@Deprecated
public class HomoPolymerNodeDialog extends DefaultNodeSettingsPane
	{
    protected HomoPolymerNodeDialog()
    	{
    	addDialogComponent(new DialogComponentNumber(
                new SettingsModelInteger(
                	HomoPolymerNodeModel.MAX_REPEAT_PROPERTY, HomoPolymerNodeModel.DEFAULT_MAX_REPEAT),
                    "Num Repeats:",1));
    	}
	}


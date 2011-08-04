package fr.inserm.umr915.knime4ngs.nodes.ncbi.nuccore;

import org.knime.core.node.defaultnodesettings.DialogComponentNumber;
import org.knime.core.node.defaultnodesettings.SettingsModelInteger;

import fr.inserm.umr915.knime4ngs.nodes.ncbi.DefaultNcbiEUtilsNodeDialog;




public class NcbiSequenceNodeDialog extends DefaultNcbiEUtilsNodeDialog
	{
    protected NcbiSequenceNodeDialog()
    	{
    	
    	addDialogComponent(new DialogComponentNumber(
    			new SettingsModelInteger(AbstractNcbiSeqNodeModel.LIMIT_SIZE_PROPERTY, AbstractNcbiSeqNodeModel.LIMIT_SIZE_DEFAULT),
    			"Trim Sequence length to:",1,10
    			));
    	}
	}


package fr.inserm.umr915.knime4ngs.nodes.vcf.rs;

import org.knime.core.node.defaultnodesettings.DefaultNodeSettingsPane;
import org.knime.core.node.defaultnodesettings.DialogComponentBoolean;
import org.knime.core.node.defaultnodesettings.DialogComponentNumber;
import org.knime.core.node.defaultnodesettings.SettingsModelBoolean;
import org.knime.core.node.defaultnodesettings.SettingsModelDoubleBounded;


/**
 * @author Pierre Lindenbaum
 */
public class RsFilterNodeDialog extends DefaultNodeSettingsPane
	{
    protected RsFilterNodeDialog()
    	{
    	addDialogComponent(new DialogComponentBoolean(
                new SettingsModelBoolean(
                    RsFilterNodeModel.CFGKEY_CONSIDER_HETEROZYGOSITY, false),
                    "Consider Min. Heterozygosity"));
    	
        addDialogComponent(new DialogComponentNumber(
                new SettingsModelDoubleBounded(
                    RsFilterNodeModel.CFGKEY_MIN_HETEROZYGOSITY, 0,0,0.5),
                    "Min Heterozygosity", /*step*/ 0.01, /*componentwidth*/ 5));
        
                  
    	}
	}


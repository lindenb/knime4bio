package fr.inserm.umr915.knime4ngs.nodes.vcf.gn;


import org.knime.core.data.DataColumnSpec;
import org.knime.core.data.def.StringCell;
import org.knime.core.node.defaultnodesettings.DefaultNodeSettingsPane;
import org.knime.core.node.defaultnodesettings.DialogComponentColumnNameSelection;
import org.knime.core.node.defaultnodesettings.DialogComponentString;
import org.knime.core.node.defaultnodesettings.SettingsModelString;
import org.knime.core.node.util.ColumnFilter;

@Deprecated
public class GeneNameNodeDialog extends DefaultNodeSettingsPane
	{
    protected GeneNameNodeDialog()
    	{
    	/* gene FLAG */
    	addDialogComponent(new DialogComponentString(
                new SettingsModelString(
                	GeneNameNodeModel.FLAG_PROPERTY, GeneNameNodeModel.DEFAULT_FLAG_PROPERTY),
                    "INFO metaflag",true,50));
    	
    	/** INFO  */
    	ColumnFilter filter=new ColumnFilter() {
			@Override
			public boolean includeColumn(DataColumnSpec colSpec)
				{
				return colSpec.getType().equals(StringCell.TYPE);
				}
			
			@Override
			public String allFilteredMsg() {
				return "Expected a String type for INFO";
				}
			};
    	addDialogComponent(new DialogComponentColumnNameSelection(
    		new SettingsModelString(GeneNameNodeModel.INFO_COL_PROPERTY,GeneNameNodeModel.DEFAULT_INFO_COL_PROPERTY),
    		"INFO column",0,filter
    		));
			
    	}
	}


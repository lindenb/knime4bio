package fr.inserm.umr915.knime4ngs.nodes.vcf.region;

import org.knime.core.data.DataColumnSpec;
import org.knime.core.data.def.IntCell;
import org.knime.core.data.def.StringCell;
import org.knime.core.node.defaultnodesettings.DefaultNodeSettingsPane;
import org.knime.core.node.defaultnodesettings.DialogComponentColumnNameSelection;
import org.knime.core.node.defaultnodesettings.SettingsModelString;
import org.knime.core.node.util.ColumnFilter;




public class RegionNodeDialog extends DefaultNodeSettingsPane
	{
	
	
    protected RegionNodeDialog()
    	{
    	DialogComponentColumnNameSelection chromSel=new DialogComponentColumnNameSelection(
    		new SettingsModelString(RegionNodeModel.CHROM_COL_PROPERTY, "chrom"),
    		"Chromosome",1, new ColumnFilter()
    			{
    			@Override
    			public String allFilteredMsg()
					{
					return "No String column";
					}
    			@Override
    			public boolean includeColumn(DataColumnSpec colSpec)
    				{
    				return colSpec.getType()==StringCell.TYPE;
    				}
    			}
    		);
    	addDialogComponent(chromSel);
    	
    	ColumnFilter intFilter=new ColumnFilter()
			{
			@Override
			public String allFilteredMsg()
				{
				return "No Int column";
				}
			@Override
			public boolean includeColumn(DataColumnSpec colSpec)
				{
				return colSpec.getType()==IntCell.TYPE;
				}
			};
    	
    	DialogComponentColumnNameSelection chromStart=new DialogComponentColumnNameSelection(
        		new SettingsModelString(RegionNodeModel.CHROMSTART_COL_PROPERTY, "chromStart"),
        		"ChromStart",1, intFilter );
        addDialogComponent(chromStart);
        DialogComponentColumnNameSelection chromEnd=new DialogComponentColumnNameSelection(
        		new SettingsModelString(RegionNodeModel.CHROMEND_COL_PROPERTY, "chromEnd"),
        		"ChromEnd",1, intFilter );
        addDialogComponent(chromEnd);
    	}
	}


package fr.inserm.umr915.knime4ngs.nodes.mw.exists;

import org.knime.core.data.DataColumnSpec;
import org.knime.core.node.defaultnodesettings.DefaultNodeSettingsPane;
import org.knime.core.node.defaultnodesettings.DialogComponentColumnNameSelection;
import org.knime.core.node.defaultnodesettings.DialogComponentString;
import org.knime.core.node.defaultnodesettings.SettingsModelColumnName;
import org.knime.core.node.defaultnodesettings.SettingsModelString;
import org.knime.core.node.util.ColumnFilter;


public class MediaWikiExistsNodeDialog extends DefaultNodeSettingsPane
	{
    protected MediaWikiExistsNodeDialog()
    	{
    	SettingsModelString settings=new SettingsModelString(MediaWikiExistsNodeModel.MW_API_PROPERTY,MediaWikiExistsNodeModel.MW_API_DEFAULT);
    	addDialogComponent(new DialogComponentString(settings, "Mediawiki API URI",true,20));
    	
    	SettingsModelColumnName set=new SettingsModelColumnName(MediaWikiExistsNodeModel.COL_PROPERTY, MediaWikiExistsNodeModel.COL_DEFAULT);
    	addDialogComponent(new DialogComponentColumnNameSelection(set, "Column", 0,new ColumnFilter()
    		{
    		@Override
    		public String allFilteredMsg() {
    			return "all columns excluded";
    			}
    		@Override
    		public boolean includeColumn(DataColumnSpec colSpec) {
    				return true;
    				}
    		}));
    	}
	}


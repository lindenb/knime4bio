package fr.inserm.umr915.knime4ngs.nodes.util.numericcolumnfilter;

import org.knime.core.node.defaultnodesettings.DefaultNodeSettingsPane;
import org.knime.core.node.defaultnodesettings.DialogComponentBoolean;
import org.knime.core.node.defaultnodesettings.DialogComponentColumnFilter;
import org.knime.core.node.defaultnodesettings.DialogComponentDoubleRange;
import org.knime.core.node.defaultnodesettings.SettingsModelBoolean;
import org.knime.core.node.defaultnodesettings.SettingsModelDoubleRange;
import org.knime.core.node.defaultnodesettings.SettingsModelFilterString;

import fr.inserm.umr915.knime4ngs.corelib.knime.NumericColumnFilter;



public class NumColumnFilterNodeDialog extends DefaultNodeSettingsPane
	{
    protected NumColumnFilterNodeDialog()
    	{
    	
    	addDialogComponent(new DialogComponentColumnFilter(
                new SettingsModelFilterString( NumColumnFilterNodeModel.COLS_PROPERTY),
                    0,false,
                    new NumericColumnFilter())
    				);
    	
    	addDialogComponent(new DialogComponentDoubleRange(
    			new SettingsModelDoubleRange(
    					NumColumnFilterNodeModel.BOUND_PROPERTY,
    					NumColumnFilterNodeModel.BOUND_MIN,
    					NumColumnFilterNodeModel.BOUND_MAX),
    					-Double.MAX_VALUE,
    					Double.MAX_VALUE,
    					1,
    			"min/max"
    			));
    	addDialogComponent(new DialogComponentBoolean(
                new SettingsModelBoolean(
                		NumColumnFilterNodeModel.INVERT_PROPERTY, NumColumnFilterNodeModel.INVERT_DEFAULT),
                    "Inverse selection."));
    	
    	addDialogComponent(new DialogComponentBoolean(
                new SettingsModelBoolean(
                		NumColumnFilterNodeModel.ALL_COLUMN_MATCH_PROPERTY, NumColumnFilterNodeModel.ALL_COLUMN_MATCH_DEFAULT),
                    "All columns must be in range."));
    	}
	}


package fr.inserm.umr915.knime4ngs.nodes.vcf.aggregation;

import org.knime.core.data.DataColumnSpec;
import org.knime.core.data.DataType;
import org.knime.core.data.def.BooleanCell;
import org.knime.core.data.def.DoubleCell;
import org.knime.core.data.def.IntCell;
import org.knime.core.data.def.LongCell;
import org.knime.core.data.def.StringCell;
import org.knime.core.node.defaultnodesettings.DefaultNodeSettingsPane;
import org.knime.core.node.defaultnodesettings.DialogComponentColumnNameSelection;
import org.knime.core.node.defaultnodesettings.DialogComponentNumber;
import org.knime.core.node.defaultnodesettings.DialogComponentString;
import org.knime.core.node.defaultnodesettings.SettingsModelColumnName;
import org.knime.core.node.defaultnodesettings.SettingsModelDouble;
import org.knime.core.node.defaultnodesettings.SettingsModelString;
import org.knime.core.node.util.ColumnFilter;

import fr.inserm.umr915.knime4ngs.corelib.knime.DataTypeColumnFilter;




/**
 * @author Pierre Lindenbaum
 */
public class AggregationNodeDialog extends DefaultNodeSettingsPane
	{
	public AggregationNodeDialog()
    	{
    	addDialogComponent(new DialogComponentColumnNameSelection(
                new SettingsModelColumnName(
                		AggregationNodeModel.CHROM_COL_PROPERTY, AggregationNodeModel.CHROM_COL_DEFAULT),
                    "Chrom:",0,new DataTypeColumnFilter(StringCell.TYPE)));
    	
    	addDialogComponent(new DialogComponentColumnNameSelection(
                new SettingsModelColumnName(
                		AggregationNodeModel.POS_COL_PROPERTY, AggregationNodeModel.POS_COL_DEFAULT),
                    "Pos:",0,new DataTypeColumnFilter(IntCell.TYPE)));
    	
    	addDialogComponent(new DialogComponentColumnNameSelection(
                new SettingsModelColumnName(
                		AggregationNodeModel.VALUE_COL_PROPERTY, AggregationNodeModel.VALUE_COL_DEFAULT),
                    "Value:",0,new ColumnFilter()
                		{
						@Override
						public boolean includeColumn(DataColumnSpec colSpec)
							{
							DataType t=colSpec.getType();
							if(t.equals(IntCell.TYPE)) return true;
							if(t.equals(LongCell.TYPE)) return true;
							if(t.equals(BooleanCell.TYPE)) return true;
							if(t.equals(DoubleCell.TYPE)) return true;
							return false;
							}
						
						@Override
						public String allFilteredMsg() {
							return "Cannot find a numeric Column";
						}
					}));
    	
    	
    	addDialogComponent(new DialogComponentNumber(
                new SettingsModelDouble( AggregationNodeModel.THRESHOLD_PROPERTY, AggregationNodeModel.THRESHOLD_DEFAULT),
                   "Treshold",
                   0.0001
       			));
    	
    	addDialogComponent(new DialogComponentString(
                new SettingsModelString( AggregationNodeModel.PREFIX_PROPERTY, AggregationNodeModel.PREFIX_DEFAULT),
                   "new Columns Prefix",
                   true,50
       			));
    	
    	
    	
    	}
	}


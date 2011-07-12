package fr.inserm.umr915.knime4ngs.corelib.knime;

import org.knime.core.data.DataColumnSpec;
import org.knime.core.data.DataType;
import org.knime.core.data.def.BooleanCell;
import org.knime.core.data.def.DoubleCell;
import org.knime.core.data.def.IntCell;
import org.knime.core.data.def.LongCell;
import org.knime.core.node.util.ColumnFilter;

public class NumericColumnFilter implements ColumnFilter
	{	
	public NumericColumnFilter()
		{
		}
	
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
	public String allFilteredMsg()
		{
		return "Cannot find any numeric column";
		}
	@Override
	public String toString() {
		return getClass().getName();
		}
	}

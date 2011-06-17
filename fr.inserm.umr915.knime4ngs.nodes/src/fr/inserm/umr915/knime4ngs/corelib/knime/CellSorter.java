package fr.inserm.umr915.knime4ngs.corelib.knime;

import java.util.Comparator;

import org.knime.core.data.DataCell;
import org.knime.core.data.DataType;
import org.knime.core.data.def.IntCell;
import org.knime.core.data.def.LongCell;
import org.knime.core.data.def.DoubleCell;
import org.knime.core.data.def.BooleanCell;
import org.knime.core.data.def.StringCell;

@SuppressWarnings("rawtypes")
public abstract class CellSorter
implements Comparator<DataCell>
{
protected CellSorter()
	{
	}

protected abstract Comparable comparable(DataCell c); 
@SuppressWarnings("unchecked")
@Override
public int compare(DataCell cell1,DataCell cell2)
	{
	if(cell1.isMissing())
		{
		if(cell2.isMissing()) return 0;
		return -1;
		}
	if(cell2.isMissing())
		{
		return 1;
		}
	Comparable c1= comparable(cell1);
	Comparable c2= comparable(cell2);
	return c1.compareTo(c2);
	}

public static CellSorter getCellSorterByType(DataType type)
	{
	if(type.equals(StringCell.TYPE))
		{
		return new CellSorter()
			{
			@Override
			protected Comparable comparable(DataCell c)
				{
				return StringCell.class.cast(c).getStringValue();
				}
			@Override
			public String toString() { return "String"; }
			};
		}
	else if(type.equals(IntCell.TYPE))
		{
		return new CellSorter()
			{
			@Override
			protected Comparable comparable(DataCell c)
				{
				return IntCell.class.cast(c).getIntValue();
				}
			@Override
			public String toString() { return "Int"; }
			};
		}
	else if(type.equals(LongCell.TYPE))
		{
		return new CellSorter()
			{
			@Override
			protected Comparable comparable(DataCell c)
				{
				return LongCell.class.cast(c).getLongValue();
				}
			@Override
			public String toString() { return "Long"; }
			};
		}
	else if(type.equals(DoubleCell.TYPE))
		{
		return new CellSorter()
			{
			@Override
			protected Comparable comparable(DataCell c)
				{
				return DoubleCell.class.cast(c).getDoubleValue();
				}
			@Override
			public String toString() { return "Double"; }
			};
		}
	else if(type.equals(BooleanCell.TYPE))
		{
		return new CellSorter()
			{
			@Override
			protected Comparable comparable(DataCell c)
				{
				return BooleanCell.class.cast(c).getBooleanValue();
				}
			@Override
			public String toString() { return "Boolean"; }
			};
		}
	throw new IllegalArgumentException("CellSorter hasn't been designed for "+type);
	}
}
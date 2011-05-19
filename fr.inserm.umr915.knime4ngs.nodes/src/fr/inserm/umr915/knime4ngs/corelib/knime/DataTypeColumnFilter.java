package fr.inserm.umr915.knime4ngs.corelib.knime;

import org.knime.core.data.DataColumnSpec;
import org.knime.core.data.DataType;
import org.knime.core.data.def.StringCell;
import org.knime.core.node.util.ColumnFilter;

public class DataTypeColumnFilter implements ColumnFilter
	{
	private DataType dataType;
	
	public DataTypeColumnFilter()
		{
		}
	
	public DataTypeColumnFilter(DataType dataType)
		{
		this.dataType=dataType;
		if(this.dataType==null) this.dataType=StringCell.TYPE;
		}
	@Override
	public boolean includeColumn(DataColumnSpec colSpec)
		{
		return colSpec.getType().equals(this.dataType);
		}

	@Override
	public String allFilteredMsg()
		{
		return "No column having type="+this.dataType.getClass().getSimpleName();
		}
	@Override
	public String toString() {
		return getClass().getName()+" {"+this.dataType.getClass()+"}";
		}
	}

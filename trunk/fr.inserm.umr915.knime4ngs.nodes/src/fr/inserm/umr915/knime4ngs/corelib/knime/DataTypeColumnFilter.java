package fr.inserm.umr915.knime4ngs.corelib.knime;

import org.knime.core.data.DataColumnSpec;
import org.knime.core.data.DataType;
import org.knime.core.data.def.StringCell;
import org.knime.core.node.util.ColumnFilter;

public class DataTypeColumnFilter implements ColumnFilter
	{
	private DataType dataTypes[];
	
	public DataTypeColumnFilter()
		{
		this.dataTypes=new DataType[]{StringCell.TYPE};
		}
	
	
	public DataTypeColumnFilter(DataType...dataTypes)
		{
		this.dataTypes=new DataType[dataTypes.length];
		System.arraycopy(dataTypes, 0, this.dataTypes,0, this.dataTypes.length);
		}

	
	@Override
	public boolean includeColumn(DataColumnSpec colSpec)
		{
		for(DataType dt:this.dataTypes)
			{
			if(colSpec.getType().equals(dt)) return true;
			}
		return false;
		}

	@Override
	public String allFilteredMsg()
		{
		String s="";
		for(DataType dt:this.dataTypes)
			{
			s+= " "+ dt.getClass().getSimpleName();
			}
		return "No column having type(s)="+s;
		}
	@Override
	public String toString() {
		String s="";
		for(DataType dt:this.dataTypes)
			{
			s+= " "+ dt.getClass().getSimpleName();
			}
		return getClass().getName()+" {"+s+"}";
		}
	}

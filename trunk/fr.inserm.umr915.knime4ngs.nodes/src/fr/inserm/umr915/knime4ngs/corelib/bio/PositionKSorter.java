package fr.inserm.umr915.knime4ngs.corelib.bio;

import java.util.Comparator;

import org.knime.core.data.DataRow;
import org.knime.core.data.def.IntCell;
import org.knime.core.data.def.StringCell;

public class PositionKSorter
implements Comparator<DataRow>
	{
	private int chromCol;
	private int posCol;
	
	
	public PositionKSorter(int chromCol, int posCol)
		{
		this.chromCol = chromCol;
		if(this.chromCol<0) throw new IllegalArgumentException("CHROM col <0");
		this.posCol = posCol;
		if(this.posCol<0) throw new IllegalArgumentException("POS col <0");
		}
	
	public Position make(DataRow row)
		{
		if(row.getCell(chromCol).isMissing() || row.getCell(posCol).isMissing()) return null;
		String chrom=StringCell.class.cast(row.getCell(chromCol)).getStringValue();
		int pos=IntCell.class.cast(row.getCell(posCol)).getIntValue();
		return new Position(chrom, pos);
		}
	@Override
	public int compare(DataRow o1, DataRow o2) {
		return make(o1).compareTo(make(o2));
		}

	}

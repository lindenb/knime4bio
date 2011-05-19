package fr.inserm.umr915.knime4ngs.corelib.bio;

import java.util.Comparator;

import org.knime.core.data.DataRow;
import org.knime.core.data.def.IntCell;
import org.knime.core.data.def.StringCell;

public class BedKSorter
implements Comparator<DataRow>
	{
	private int chromCol;
	private int chromStartCol;
	private int chromEndCol;
	
	
	public BedKSorter(int chromCol, int chromStartCol, int chromEndCol)
		{
		this.chromCol = chromCol;
		if(this.chromCol<0) throw new IllegalArgumentException("chrom=-1");
		this.chromStartCol = chromStartCol;
		if(this.chromStartCol<0) throw new IllegalArgumentException("chromStartCol=-1");
		this.chromEndCol = chromEndCol;
		if(this.chromEndCol<0) throw new IllegalArgumentException("chromEndCol=-1");
		}
	
	public Segment make(DataRow row)
		{
		String chrom=StringCell.class.cast(row.getCell(chromCol)).getStringValue();
		int start=IntCell.class.cast(row.getCell(chromStartCol)).getIntValue();
		int end=IntCell.class.cast(row.getCell(chromEndCol)).getIntValue();
		return new Segment(chrom, start,end);
		}
	@Override
	public int compare(DataRow o1, DataRow o2) {
		return make(o1).compareTo(make(o2));
		}

	}

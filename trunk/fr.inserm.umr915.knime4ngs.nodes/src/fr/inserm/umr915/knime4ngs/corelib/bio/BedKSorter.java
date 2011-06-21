package fr.inserm.umr915.knime4ngs.corelib.bio;

import java.util.Comparator;

import org.knime.core.data.DataCell;
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
		DataCell c=row.getCell(chromCol);
		if(c.isMissing()) return null;
		String chrom=StringCell.class.cast(c).getStringValue();
		
		c=row.getCell(chromStartCol);
		if(c.isMissing()) return null;
		int start=IntCell.class.cast(c).getIntValue();
		
		
		c=row.getCell(chromEndCol);
		if(c.isMissing()) return null;
		int end=IntCell.class.cast(c).getIntValue();
		return new Segment(chrom, start,end);
		}
	@Override
	public int compare(DataRow o1, DataRow o2) {
		return make(o1).compareTo(make(o2));
		}

	}

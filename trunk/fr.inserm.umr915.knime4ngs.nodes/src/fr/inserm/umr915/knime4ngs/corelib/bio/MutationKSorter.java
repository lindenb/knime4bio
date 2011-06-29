package fr.inserm.umr915.knime4ngs.corelib.bio;

import java.util.Comparator;

import org.knime.core.data.DataCell;
import org.knime.core.data.DataRow;
import org.knime.core.data.def.IntCell;
import org.knime.core.data.def.StringCell;

public class MutationKSorter
implements Comparator<DataRow>
	{
	private int chromCol;
	private int posCol;
	private int refCol;
	private int altCol;
	
	public MutationKSorter(int chromCol, int posCol,int refCol,int altCol)
		{
		this.chromCol = chromCol;
		if(chromCol<0) throw new IllegalArgumentException("undefined CHROM column");
		this.posCol = posCol;
		if(posCol<0) throw new IllegalArgumentException("undefined POS column");
		this.refCol=refCol;
		if(refCol<0) throw new IllegalArgumentException("undefined REF column");
		this.altCol=altCol;
		if(altCol<0) throw new IllegalArgumentException("undefined ALT column");
		}
	
	public Mutation make(DataRow row)
		{
		DataCell c=row.getCell(chromCol);
		if(c.isMissing()) return null;
		String chrom=StringCell.class.cast(c).getStringValue();
		c=row.getCell(posCol);
		if(c.isMissing()) return null;
		int pos=IntCell.class.cast(c).getIntValue();
		c=row.getCell(refCol);
		if(c.isMissing()) return null;
		String ref=StringCell.class.cast(c).getStringValue();
		c=row.getCell(altCol);
		if(c.isMissing()) return null;
		String alt=StringCell.class.cast(c).getStringValue();
		return new Mutation(new Position(chrom, pos),ref.toUpperCase(),alt.toUpperCase());
		}
	@Override
	public int compare(DataRow o1, DataRow o2) {
		return make(o1).compareTo(make(o2));
		}

	}

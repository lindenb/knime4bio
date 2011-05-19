package fr.inserm.umr915.knime4ngs.corelib.bio;

import java.util.Comparator;

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
		this.posCol = posCol;
		this.refCol=refCol;
		this.altCol=altCol;
		}
	
	public Mutation make(DataRow row)
		{
		String chrom=StringCell.class.cast(row.getCell(chromCol)).getStringValue();
		int pos=IntCell.class.cast(row.getCell(posCol)).getIntValue();
		String ref=StringCell.class.cast(row.getCell(refCol)).getStringValue();
		String alt=StringCell.class.cast(row.getCell(altCol)).getStringValue();
		return new Mutation(new Position(chrom, pos),ref.toUpperCase(),alt.toUpperCase());
		}
	@Override
	public int compare(DataRow o1, DataRow o2) {
		return make(o1).compareTo(make(o2));
		}

	}

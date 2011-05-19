package fr.inserm.umr915.knime4ngs.corelib.knime;

import java.util.Comparator;

import org.knime.core.data.DataRow;
import org.knime.core.data.def.StringCell;

import fr.inserm.umr915.knime4ngs.corelib.bio.Mutation;
import fr.inserm.umr915.knime4ngs.corelib.bio.Position;

public class MutationComparator implements Comparator<DataRow>
	{
	private PositionComparator positionComparator;
	private int refColumn;
	private int altColumn;
	public MutationComparator(
		PositionComparator positionComparator,
		int refColumn,
		int altColumn
		)
		{
		this.positionComparator=positionComparator;
		this.refColumn=refColumn;
		this.altColumn=altColumn;
		}
	
	private Mutation parseMutation(DataRow row)
		{
		Position pos= this.positionComparator.parsePosition(row);
		String ref=StringCell.class.cast(row.getCell(this.refColumn)).getStringValue();
		String alt=StringCell.class.cast(row.getCell(this.altColumn)).getStringValue();
		return new Mutation(pos, ref,alt);
		}
	
	@Override
	public int compare(DataRow o1, DataRow o2)
		{
		return parseMutation(o1).compareTo(parseMutation(o2));
		}
	}

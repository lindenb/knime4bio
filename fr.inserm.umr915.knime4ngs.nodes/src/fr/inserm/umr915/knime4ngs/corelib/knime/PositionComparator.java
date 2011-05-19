package fr.inserm.umr915.knime4ngs.corelib.knime;

import java.util.Comparator;

import org.knime.core.data.DataRow;
import org.knime.core.data.def.IntCell;
import org.knime.core.data.def.StringCell;

import fr.inserm.umr915.knime4ngs.corelib.bio.Position;

public class PositionComparator implements Comparator<DataRow>
	{
	private int chromColumn;
	private int positionColumn;
	public PositionComparator(
		int chromColumn,
		int positionColumn
		)
		{
		this.chromColumn=chromColumn;
		this.positionColumn=positionColumn;
		}
	
	public Position parsePosition(DataRow row)
		{
		String c=StringCell.class.cast(row.getCell(this.chromColumn)).getStringValue();
		int i=IntCell.class.cast(row.getCell(this.positionColumn)).getIntValue();
		return new Position(c, i);
		}
	
	@Override
	public int compare(DataRow o1, DataRow o2)
		{
		return parsePosition(o1).compareTo(parsePosition(o2));
		}
	}

package fr.inserm.umr915.knime4ngs.nodes.vcf;

import java.util.Comparator;

import org.knime.core.data.DataRow;
import org.knime.core.data.DataTableSpec;
import org.knime.core.data.def.IntCell;
import org.knime.core.data.def.StringCell;
import org.knime.core.node.port.PortType;

import fr.inserm.umr915.knime4ngs.corelib.bio.Mutation;
import fr.inserm.umr915.knime4ngs.corelib.bio.Position;
import fr.inserm.umr915.knime4ngs.corelib.knime.AbstractNodeModel;

public abstract class AbstractVCFNodeModel extends AbstractNodeModel
	{
	protected static class PositionColumns
		{
		public int chrom;
		public int position;
		}
	
	
	protected static class MutationColumns
		extends PositionColumns
		{
		public int ref;
		public int alt;
		}
	
	protected static class VcfCallColumns
		extends MutationColumns
		{
		public int id;
		}
	
	protected class MutationComparator implements Comparator<DataRow>
		{
		private MutationColumns columns;
		public MutationComparator(MutationColumns columns)
			{
			this.columns=columns;
			}
		private Mutation parseMutation(DataRow row)
			{
			String c= StringCell.class.cast(row.getCell(this.columns.chrom)).getStringValue();
			int p= IntCell.class.cast(row.getCell(this.columns.position)).getIntValue();
			String r= StringCell.class.cast(row.getCell(this.columns.ref)).getStringValue().toUpperCase();
			String a= StringCell.class.cast(row.getCell(this.columns.alt)).getStringValue().toUpperCase();
			return new Mutation(new Position(c,p), r, a);
			}
		@Override
		public int compare(DataRow o1, DataRow o2)
			{
			return parseMutation(o1).compareTo(parseMutation(o2));
			}
		}
	
	public AbstractVCFNodeModel(int nrInDataPorts, int nrOutDataPorts)
		{
		super(nrInDataPorts, nrOutDataPorts);
		}

	public AbstractVCFNodeModel(PortType[] inPortTypes, PortType[] outPortTypes) {
		super(inPortTypes, outPortTypes);
		}
	
	private void getPositionColumns(PositionColumns g,final DataTableSpec dataTableSpec) throws IllegalArgumentException
		{
		g.chrom=dataTableSpec.findColumnIndex("CHROM");
		if(g.chrom==-1) throw new IllegalArgumentException("Cannot find column \"CHROM\"");
		g.position=dataTableSpec.findColumnIndex("POS");
		if(g.position==-1) throw new IllegalArgumentException("Cannot find column \"POS\"");
		}
	
	
	private void getMutationColumns(MutationColumns g,final DataTableSpec dataTableSpec) throws IllegalArgumentException
		{
		getPositionColumns(g,dataTableSpec);
		g.ref=dataTableSpec.findColumnIndex("REF");
		if(g.ref==-1) throw new IllegalArgumentException("Cannot find column \"REF\"");
		g.alt=dataTableSpec.findColumnIndex("ALT");
		if(g.alt==-1) throw new IllegalArgumentException("Cannot find column \"ALT\"");
		}
	
	protected MutationColumns getMutationColumns(final DataTableSpec dataTableSpec) throws IllegalArgumentException
		{
		MutationColumns g=new MutationColumns();
		getMutationColumns(g,dataTableSpec);
		return g;
		}
	
	protected PositionColumns getPositionColumns(final DataTableSpec dataTableSpec) throws IllegalArgumentException
		{
		PositionColumns g=new PositionColumns();
		getPositionColumns(g,dataTableSpec);
		return g;
		}
	
	
	public static Position getPosition(MutationColumns cols,DataRow dataRow)
		{
		return new Position(
			StringCell.class.cast(dataRow.getCell(cols.chrom)).getStringValue(),
			IntCell.class.cast(dataRow.getCell(cols.position)).getIntValue()
			);
		}
	
	public static Mutation getMutation(MutationColumns cols,DataRow dataRow)
		{
		return new Mutation(
			getPosition(cols, dataRow),
			StringCell.class.cast(dataRow.getCell(cols.ref)).getStringValue(),
			StringCell.class.cast(dataRow.getCell(cols.alt)).getStringValue()
			);
		}
	
	
	}

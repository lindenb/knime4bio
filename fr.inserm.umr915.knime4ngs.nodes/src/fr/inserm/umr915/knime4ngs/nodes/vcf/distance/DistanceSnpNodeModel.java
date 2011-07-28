package fr.inserm.umr915.knime4ngs.nodes.vcf.distance;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.knime.base.data.append.column.AppendedColumnRow;
import org.knime.core.data.DataCell;
import org.knime.core.data.DataColumnSpecCreator;
import org.knime.core.data.DataRow;
import org.knime.core.data.DataTableSpec;
import org.knime.core.data.DataType;
import org.knime.core.data.container.CloseableRowIterator;
import org.knime.core.data.def.IntCell;
import org.knime.core.data.def.StringCell;
import org.knime.core.node.BufferedDataContainer;
import org.knime.core.node.BufferedDataTable;
import org.knime.core.node.ExecutionContext;
import org.knime.core.node.InvalidSettingsException;
import org.knime.core.node.defaultnodesettings.SettingsModel;
import org.knime.core.node.defaultnodesettings.SettingsModelColumnName;
import fr.inserm.umr915.knime4ngs.corelib.bio.Position;
import fr.inserm.umr915.knime4ngs.nodes.vcf.AbstractVCFNodeModel;

public class DistanceSnpNodeModel extends AbstractVCFNodeModel
	{
	/** chrom column */
	static final String CHROM_COL_PROPERTY="chrom.col";
	static final String CHROM_COL_DEFAULT="CHROM";
	private final SettingsModelColumnName m_chromColumn = new SettingsModelColumnName(
			CHROM_COL_PROPERTY,
			CHROM_COL_DEFAULT
			);
	
	/** pos column */
	static final String POS_COL_PROPERTY="pos.col";
	static final String POS_COL_DEFAULT="POS";
	private final SettingsModelColumnName m_posColumn = new SettingsModelColumnName(
			POS_COL_PROPERTY,
			POS_COL_DEFAULT
			);
	
	
	
	private static class Sorter
		implements Comparator<DataRow>
		{
		int chromColumn;
		int positionColumn;
		
		
		public  Position getPosition(DataRow row)
			{
			DataCell k=row.getCell(this.chromColumn);
			DataCell p=row.getCell(this.positionColumn);
			if(k.isMissing()) return null;
			if(p.isMissing()) return null;
			return new Position(
					StringCell.class.cast(k).getStringValue(),
					IntCell.class.cast(p).getIntValue()
					);
			}
		
		@Override
		public int compare(DataRow row1, DataRow row2)
			{
			Position m1= getPosition(row1);
			Position m2= getPosition(row2);
			if(m1==null && m2==null) return 0;
			if(m1!=null && m2==null) return -1;
			if(m1==null && m2!=null) return 1;
			return m1.compareTo(m2);
			}
		}
	

	
	public DistanceSnpNodeModel()
		{
		super(1,1);
		}
	@Override
	protected BufferedDataTable[] execute(BufferedDataTable[] inData,
			ExecutionContext exec) throws Exception
		{
		
		
		BufferedDataTable inTable=inData[0];
		DataTableSpec inSpecs=inTable.getDataTableSpec();
		CloseableRowIterator iter=null;
		
		Sorter sorter=new Sorter();
		sorter.chromColumn = inSpecs.findColumnIndex(this.m_chromColumn.getStringValue());
		sorter.positionColumn = inSpecs.findColumnIndex(this.m_posColumn.getStringValue());
		
		
		
		//collect sample names
		double total= inTable.getRowCount();
		BufferedDataContainer container=exec.createDataContainer(createTableSpec(inSpecs));;
		try
			{
			Position prevPosition=null;
			iter=inTable.iterator();
			int nRow=0;
			while(iter.hasNext())
				{
				DataRow row=iter.next();
				nRow++;
				row=iter.next();
				Position position=sorter.getPosition(row);
				DataCell distCell=DataType.getMissingCell();
				if(prevPosition!=null)
					{
					int cmp = prevPosition.compareTo(position);
					if(cmp>0)
						{
						throw new IllegalStateException( "Table should have been sorted on CHROM/POS but got "+prevPosition+">"+position );
						}
					if(prevPosition.getChromosome().equals(position.getChromosome()))
						{
						distCell=new IntCell(position.getPosition()-prevPosition.getPosition());
						}
					}
				prevPosition=position;
				container.addRowToTable(new AppendedColumnRow(row,distCell));
	            exec.checkCanceled();
	            exec.setProgress(nRow/total,"Getting distance");
				}
			}
		catch(Exception err)
			{
			err.printStackTrace();
			throw err;
			}
		finally
			{
			safeClose(iter);
			}
		safeClose(container);
		BufferedDataTable out = container.getTable();
        container=null;
        return new BufferedDataTable[]{out};
		}
	
	
	@Override
	protected DataTableSpec[] configure(DataTableSpec[] tables)
			throws InvalidSettingsException
		{
		if(tables.length!=1 || tables[0]==null) throw new InvalidSettingsException("Expected one table");
		findColumnIndex(tables[0],this.m_chromColumn,StringCell.TYPE);
		findColumnIndex(tables[0],this.m_posColumn,IntCell.TYPE);
		DataTableSpec inSpecs=createTableSpec(tables[0]);
		return new DataTableSpec[]{inSpecs};
		}
	
	private DataTableSpec createTableSpec(DataTableSpec spec)
		{
		return new DataTableSpec(spec,
				new DataTableSpec(new DataColumnSpecCreator("distance.with.prev", IntCell.TYPE).createSpec())
				);
		}
	
	@Override
	protected List<SettingsModel> getSettingsModel() {
		List<SettingsModel> arrayModel=new ArrayList<SettingsModel>(super.getSettingsModel());
		arrayModel.add(this.m_chromColumn);
		arrayModel.add(this.m_posColumn);
		return arrayModel;
		}
	}

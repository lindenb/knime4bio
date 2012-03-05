package fr.inserm.umr915.knime4ngs.nodes.vcf.numericsplit;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;
import org.knime.core.data.DataCell;
import org.knime.core.data.DataRow;
import org.knime.core.data.DataTableSpec;
import org.knime.core.data.container.CloseableRowIterator;
import org.knime.core.data.def.BooleanCell;
import org.knime.core.data.def.DoubleCell;
import org.knime.core.data.def.IntCell;
import org.knime.core.data.def.StringCell;
import org.knime.core.node.BufferedDataContainer;
import org.knime.core.node.BufferedDataTable;
import org.knime.core.node.ExecutionContext;
import org.knime.core.node.InvalidSettingsException;
import org.knime.core.node.defaultnodesettings.SettingsModel;
import org.knime.core.node.defaultnodesettings.SettingsModelBoolean;
import org.knime.core.node.defaultnodesettings.SettingsModelColumnName;
import org.knime.core.node.defaultnodesettings.SettingsModelInteger;
import fr.inserm.umr915.knime4ngs.corelib.bio.Mutation;
import fr.inserm.umr915.knime4ngs.corelib.bio.Position;
import fr.inserm.umr915.knime4ngs.corelib.knime.ExecuteException;
import fr.inserm.umr915.knime4ngs.nodes.vcf.AbstractVCFNodeModel;

public class VCFNumericSplitNodeModel extends AbstractVCFNodeModel
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
	
	/** ref column */
	static final String REF_COL_PROPERTY="ref.col";
	static final String REF_COL_DEFAULT="REF";
	private final SettingsModelColumnName m_refColumn = new SettingsModelColumnName(
			REF_COL_PROPERTY,
			REF_COL_DEFAULT
			);
	/** alt column */
	static final String ALT_COL_PROPERTY="alt.col";
	static final String ALT_COL_DEFAULT="ALT";
	private final SettingsModelColumnName m_altColumn = new SettingsModelColumnName(
			ALT_COL_PROPERTY,
			ALT_COL_DEFAULT
			);
	
	
	
	
	/** value column */
	static final String VALUE_COL_PROPERTY="observed.col";
	static final String VALUE_COL_DEFAULT="QUAL";
	private final SettingsModelColumnName m_observedColumn = new SettingsModelColumnName(
			VALUE_COL_PROPERTY,
			VALUE_COL_DEFAULT
			);

	/** min value */
	static final String MIN_QUAL_PROPERTY="min.qual";
    static final int DEFAULT_MIN_QUAL=0;
	
	private final SettingsModelInteger m_minQual =
       new SettingsModelInteger(MIN_QUAL_PROPERTY,DEFAULT_MIN_QUAL);

	/** max value */
	static final String MAX_QUAL_PROPERTY="max.qual";
    static final int DEFAULT_MAX_QUAL=1000;
	
	private final SettingsModelInteger m_maxQual =
       new SettingsModelInteger(MAX_QUAL_PROPERTY,DEFAULT_MAX_QUAL);
	
	/** invert */
	static final String INVERT_PROPERTY="invert";
    static final boolean DEFAULT_INVERT=false;
	
	private final SettingsModelBoolean m_invert =
       new SettingsModelBoolean(INVERT_PROPERTY,DEFAULT_INVERT);

	/** use ref alt */
	static final String USE_REF_ALT_PROPERTY="use.ref.alt";
    static final boolean DEFAULT_USE_REF_ALT=false;
	
	private final SettingsModelBoolean m_useRefAlt =
       new SettingsModelBoolean(USE_REF_ALT_PROPERTY,DEFAULT_USE_REF_ALT);
	
	
	
	private static class Sorter
		implements Comparator<DataRow>
		{
		boolean useRefAlt;
		int chromColumn;
		int positionColumn;
		int refColumn;
		int altColumn;
		
		public  Mutation getMutation(DataRow row)
			{
			String ref="A";
			String alt="A";
			
			if(useRefAlt)
				{
				ref= StringCell.class.cast(row.getCell(this.refColumn)).getStringValue();
				alt= StringCell.class.cast(row.getCell(this.altColumn)).getStringValue();
				}
			
			return new Mutation(
				new Position(
					StringCell.class.cast(row.getCell(this.chromColumn)).getStringValue(),
					IntCell.class.cast(row.getCell(this.positionColumn)).getIntValue()
					),
				ref, alt
				);
			}
		
		@Override
		public int compare(DataRow row1, DataRow row2)
			{
			Mutation m1= getMutation(row1);
			Mutation m2= getMutation(row2);
			//System.err.println(m1.toString()+" vs "+m2.toString());
			return m1.compareTo(m2);
			}
		}
	
	public VCFNumericSplitNodeModel()
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
		sorter.useRefAlt=this.m_useRefAlt.getBooleanValue();
		sorter.chromColumn = inSpecs.findColumnIndex(this.m_chromColumn.getStringValue());
		sorter.positionColumn = inSpecs.findColumnIndex(this.m_posColumn.getStringValue());
		sorter.refColumn = inSpecs.findColumnIndex(this.m_refColumn.getStringValue());
		sorter.altColumn = inSpecs.findColumnIndex(this.m_altColumn.getStringValue());
		int observedColumn= inSpecs.findColumnIndex(this.m_observedColumn.getStringValue());
		//collect sample names
		double total= inTable.getRowCount();
		
		double min_value= this.m_minQual.getIntValue();
		double max_value= this.m_maxQual.getIntValue();
		
		
		BufferedDataContainer container=exec.createDataContainer(inSpecs);
		try
			{
			List<DataRow> rowBuffer=new Vector<DataRow>();
			Mutation prevMutation=null;
			iter=inTable.iterator();
			int nRow=0;
			for(;;)
				{
				boolean hasNext=iter.hasNext();
				Mutation mutation=null;
				DataRow row=null;
				if(hasNext)
					{
					nRow++;
					row=iter.next();
					mutation=sorter.getMutation(row);
					if(prevMutation!=null)
						{
						if(prevMutation.compareTo(mutation)>0)
							{
							throw new IllegalStateException( "Table should have been sorted on CHROM/POS/REF/ALT but got "+prevMutation+">"+mutation );
							}
						}
					}

				if( (!hasNext ||
					(prevMutation!=null && !prevMutation.equals(mutation)))
					&& !rowBuffer.isEmpty()
					)
					{
					boolean ok=true;
					
					for(DataRow r: rowBuffer)
						{
						DataCell cell=r.getCell(observedColumn);
						if(cell.isMissing())
							{
							getLogger().warn("Empty observed column=null for key = "+r.getKey());
							continue;
							}
						Double value=null;
						if(cell.getType()==BooleanCell.TYPE)
							{
							value = BooleanCell.class.cast(cell).getBooleanValue()?1.0:0.0;
							}
						else if(cell.getType()==IntCell.TYPE)
							{
							value =(double) IntCell.class.cast(cell).getIntValue();
							}
						else if(cell.getType()==DoubleCell.TYPE)
							{
							value =(double) DoubleCell.class.cast(cell).getDoubleValue();
							}
						else
							{
							throw new ExecuteException("Bad cell type : "+cell.getType());
							}
						
						if((min_value<= value && value <=max_value) == this.m_invert.getBooleanValue())
							{
							ok=false;
							break;
							}
						}
					
					
					if(ok)
						{
						for(DataRow buffrow: rowBuffer)
							{
							container.addRowToTable(buffrow);
							}
						}
					rowBuffer.clear();
					}
				if(!hasNext)
					{
					break;
					}
				prevMutation=mutation;
				rowBuffer.add(row);
	            exec.checkCanceled();
	            exec.setProgress(nRow/total,"Getting numeric split...");
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
		findColumnIndex(tables[0],this.m_refColumn,StringCell.TYPE);
		findColumnIndex(tables[0],this.m_altColumn,StringCell.TYPE);
		int n=tables[0].findColumnIndex(this.m_observedColumn.getColumnName());
		if(n==-1) throw new InvalidSettingsException("undefined column : "+
				this.m_observedColumn.getColumnName()
				);
		return new DataTableSpec[]{tables[0]};
		}
	
	
	
	@Override
	protected List<SettingsModel> getSettingsModel() {
		List<SettingsModel> arrayModel=new ArrayList<SettingsModel>(super.getSettingsModel());
		arrayModel.add(this.m_chromColumn);
		arrayModel.add(this.m_posColumn);
		arrayModel.add(this.m_refColumn);
		arrayModel.add(this.m_altColumn);
		arrayModel.add(this.m_useRefAlt);
		//
		arrayModel.add(this.m_observedColumn);
		arrayModel.add(this.m_minQual);
		arrayModel.add(this.m_maxQual);
		arrayModel.add(this.m_invert);
		return arrayModel;
		}
	}

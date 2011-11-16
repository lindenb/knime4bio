package fr.inserm.umr915.knime4ngs.nodes.vcf.selectsamples;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import org.knime.core.data.DataCell;
import org.knime.core.data.DataRow;
import org.knime.core.data.DataTableSpec;
import org.knime.core.data.container.CloseableRowIterator;
import org.knime.core.data.def.IntCell;
import org.knime.core.data.def.StringCell;
import org.knime.core.node.BufferedDataContainer;
import org.knime.core.node.BufferedDataTable;
import org.knime.core.node.ExecutionContext;
import org.knime.core.node.InvalidSettingsException;
import org.knime.core.node.defaultnodesettings.SettingsModel;
import org.knime.core.node.defaultnodesettings.SettingsModelBoolean;
import org.knime.core.node.defaultnodesettings.SettingsModelColumnName;
import org.knime.core.node.defaultnodesettings.SettingsModelString;

import fr.inserm.umr915.knime4ngs.corelib.bio.Mutation;
import fr.inserm.umr915.knime4ngs.corelib.bio.Position;
import fr.inserm.umr915.knime4ngs.corelib.knime.ExecuteException;
import fr.inserm.umr915.knime4ngs.nodes.vcf.AbstractVCFNodeModel;
import fr.inserm.umr915.knime4ngs.nodes.vcf.selectsamples.parser.SelectSamplesParser;

public class SelectSamplesNodeModel extends AbstractVCFNodeModel
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
	
	/** sample column */
	static final String SAMPLE_COL_PROPERTY="sample.col";
	static final String SAMPLE_COL_DEFAULT="SAMPLE";
	private final SettingsModelColumnName m_sampleColumn = new SettingsModelColumnName(
			SAMPLE_COL_PROPERTY,
			SAMPLE_COL_DEFAULT
			);
	
	/** select query */
	static final String QUERY_PROPERTY="query.sample";
	static final String QUERY_DEFAULT="SAMPLE";
	private final SettingsModelString m_query = new SettingsModelString(
			QUERY_PROPERTY,
			QUERY_DEFAULT
			);
	
	/** use ref alt */
	static final String USE_REF_ALT_PROPERTY="use.ref.alt";
	static final boolean USE_REF_ALT_DEFAULT=true;
	private final SettingsModelBoolean m_useRefalt = new SettingsModelBoolean(
			USE_REF_ALT_PROPERTY,
			USE_REF_ALT_DEFAULT
			);
	
	private static class Sorter
		implements Comparator<DataRow>
		{
		int chromColumn;
		int sampleColumn;
		int positionColumn;
		int refColumn;
		int altColumn;
		boolean useRefAlt;
		
		public  Mutation getMutation(DataRow row)
			{
			return new Mutation(
				new Position(
					StringCell.class.cast(row.getCell(this.chromColumn)).getStringValue(),
					IntCell.class.cast(row.getCell(this.positionColumn)).getIntValue()
					),
				useRefAlt?StringCell.class.cast(row.getCell(this.refColumn)).getStringValue():"A", 
				useRefAlt?StringCell.class.cast(row.getCell(this.altColumn)).getStringValue():"C"
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
	
	public SelectSamplesNodeModel()
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
		sorter.sampleColumn = inSpecs.findColumnIndex(this.m_sampleColumn.getStringValue());
		sorter.positionColumn = inSpecs.findColumnIndex(this.m_posColumn.getStringValue());
		sorter.useRefAlt= m_useRefalt.getBooleanValue();
		if(sorter.useRefAlt)
			{
			sorter.refColumn = inSpecs.findColumnIndex(this.m_refColumn.getStringValue());
			sorter.altColumn = inSpecs.findColumnIndex(this.m_altColumn.getStringValue());
			}
		SelectSamplesParser.Node select=null;
		try
			{
			select=new SelectSamplesParser(new StringReader(this.m_query.getStringValue())).input();
			}
		catch(Exception err)
			{
			throw new ExecuteException("bad query",err);
			}
		//collect sample names
		double total= inTable.getRowCount();
		BufferedDataContainer container=exec.createDataContainer(inSpecs);;
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
					Set<String> samples=new HashSet<String>();
					
					for(DataRow r: rowBuffer)
						{
						DataCell c=r.getCell(sorter.sampleColumn);
						if(c.isMissing()) throw new IllegalStateException("Empty sample=null in row id. "+r.getKey());
						String sample=StringCell.class.cast(c).getStringValue();
						samples.add(sample);
						}
					
					if(select.eval(samples))
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
	            exec.setProgress(nRow/total,"Selecting samples...");
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
		if(m_useRefalt.getBooleanValue())
			{
			findColumnIndex(tables[0],this.m_refColumn,StringCell.TYPE);
			findColumnIndex(tables[0],this.m_altColumn,StringCell.TYPE);
			}
		findColumnIndex(tables[0],this.m_sampleColumn,StringCell.TYPE);
		try {
			new SelectSamplesParser(new StringReader(this.m_query.getStringValue())).input();
		} catch (Exception e) {
			throw new InvalidSettingsException("Bad Query:\""+this.m_query.getStringValue()+"\"",e);
			}
		return tables;
		}
	
	
	
	@Override
	protected List<SettingsModel> getSettingsModel() {
		List<SettingsModel> arrayModel=new ArrayList<SettingsModel>(super.getSettingsModel());
		arrayModel.add(this.m_chromColumn);
		arrayModel.add(this.m_posColumn);
		arrayModel.add(this.m_refColumn);
		arrayModel.add(this.m_altColumn);
		arrayModel.add(this.m_sampleColumn);
		arrayModel.add(this.m_query);
		arrayModel.add(this.m_useRefalt);
		
		return arrayModel;
		}
	}

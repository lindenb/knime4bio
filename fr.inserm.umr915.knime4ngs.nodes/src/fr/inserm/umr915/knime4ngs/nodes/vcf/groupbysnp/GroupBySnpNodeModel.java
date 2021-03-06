package fr.inserm.umr915.knime4ngs.nodes.vcf.groupbysnp;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Vector;


import org.knime.core.data.DataCell;
import org.knime.core.data.DataColumnSpec;
import org.knime.core.data.DataColumnSpecCreator;
import org.knime.core.data.DataRow;
import org.knime.core.data.DataTableSpec;
import org.knime.core.data.DataType;
import org.knime.core.data.RowKey;
import org.knime.core.data.container.CloseableRowIterator;
import org.knime.core.data.def.DefaultRow;
import org.knime.core.data.def.IntCell;
import org.knime.core.data.def.StringCell;
import org.knime.core.node.BufferedDataContainer;
import org.knime.core.node.BufferedDataTable;
import org.knime.core.node.ExecutionContext;
import org.knime.core.node.InvalidSettingsException;
import org.knime.core.node.defaultnodesettings.SettingsModel;
import org.knime.core.node.defaultnodesettings.SettingsModelColumnName;
import org.knime.core.node.defaultnodesettings.SettingsModelFilterString;

import fr.inserm.umr915.knime4ngs.corelib.bio.Mutation;
import fr.inserm.umr915.knime4ngs.corelib.bio.Position;
import fr.inserm.umr915.knime4ngs.nodes.vcf.AbstractVCFNodeModel;

public class GroupBySnpNodeModel extends AbstractVCFNodeModel
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
	
	/** top column */
	static final String TOP_COLS_PROPERTY="top.col";
	static final String TOP_COLS_INCLUDE[]=new String[]{"INFO","FORMAT","CALL"};
	static final String TOP_COLS_EXCLUDE[]=new String[]{"#CHROM","CHROM","ID","REF","ALT"};
	private final SettingsModelFilterString m_topColumn = new SettingsModelFilterString(
			TOP_COLS_PROPERTY,
			TOP_COLS_INCLUDE,
			TOP_COLS_EXCLUDE
			);
	
	/** left column */
	static final String LEFT_COLS_PROPERTY="left.col";
	static final String LEFT_COLS_INCLUDE[]=new String[]{"ID"};
	static final String LEFT_COLS_EXCLUDE[]=new String[]{"#CHROM","CHROM","REF","ALT","INFO","FORMAT"};
	private final SettingsModelFilterString m_leftColumn = new SettingsModelFilterString(
			LEFT_COLS_PROPERTY,
			LEFT_COLS_INCLUDE,
			LEFT_COLS_EXCLUDE
			);
	
	private static class Sorter
		implements Comparator<DataRow>
		{
		int chromColumn;
		int sampleColumn;
		int positionColumn;
		int refColumn;
		int altColumn;
		
		public  Mutation getMutation(DataRow row)
			{
			return new Mutation(
				new Position(
					StringCell.class.cast(row.getCell(this.chromColumn)).getStringValue(),
					IntCell.class.cast(row.getCell(this.positionColumn)).getIntValue()
					),
				StringCell.class.cast(row.getCell(this.refColumn)).getStringValue(), 
				StringCell.class.cast(row.getCell(this.altColumn)).getStringValue()
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
	

	
	public GroupBySnpNodeModel()
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
		sorter.chromColumn = findColumnIndex(inSpecs,this.m_chromColumn,StringCell.TYPE);
		sorter.sampleColumn = findColumnIndex(inSpecs,this.m_sampleColumn,StringCell.TYPE);
		sorter.positionColumn = findColumnIndex(inSpecs,this.m_posColumn,IntCell.TYPE);
		sorter.refColumn = findColumnIndex(inSpecs,this.m_refColumn,StringCell.TYPE);
		sorter.altColumn = findColumnIndex(inSpecs,this.m_altColumn,StringCell.TYPE);
	
		List<Integer> leftIndexes=new ArrayList<Integer>();
		for(String s:this.m_leftColumn.getIncludeList())
			{
			int n=inSpecs.findColumnIndex(s);
			if(leftIndexes.contains(n)) continue;
			leftIndexes.add(n);
			}
		
		List<Integer> topIndexes=new ArrayList<Integer>();
		for(String s:this.m_topColumn.getIncludeList())
			{
			int n=inSpecs.findColumnIndex(s);
			if(leftIndexes.contains(n)) continue;//don't add if already in left
			topIndexes.add(n);
			}
		
		
		
		
		//collect sample names
		double total= inTable.getRowCount();
		int outIndex=0;
		BufferedDataContainer container=null;
		try
			{
			
			Set<String> sampleNames=new TreeSet<String>();
			iter=inTable.iterator();
			while(iter.hasNext())
				{
				DataRow r= iter.next();
				DataCell cell= r.getCell(sorter.sampleColumn);
				if(cell.isMissing()) continue;
				sampleNames.add(StringCell.class.cast(cell).getStringValue());
				}
			safeClose(iter);
			
			Map<String,Integer> sample2column=new TreeMap<String, Integer>();
			for(String s:sampleNames) sample2column.put(s, sample2column.size());
			
			
			final List<DataColumnSpec> specList=new ArrayList<DataColumnSpec>();
			specList.add(inSpecs.getColumnSpec(sorter.chromColumn));
			specList.add(inSpecs.getColumnSpec(sorter.positionColumn));
			specList.add(inSpecs.getColumnSpec(sorter.refColumn));
			specList.add(inSpecs.getColumnSpec(sorter.altColumn));
			for(int idx:leftIndexes)
				{
				specList.add(inSpecs.getColumnSpec(idx));
				}
			specList.add(new DataColumnSpecCreator("samples.per.snp", IntCell.TYPE).createSpec());
			for(String sampleName:sample2column.keySet())
				{
				for(int idx:topIndexes)
					{
					specList.add(new DataColumnSpecCreator(
							sampleName+":"+inSpecs.getColumnSpec(idx).getName(),
							inSpecs.getColumnSpec(idx).getType()
							).createSpec());
					}
				}
			container=exec.createDataContainer(new DataTableSpec(specList.toArray(new DataColumnSpec[specList.size()])));
			
			
			
			
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
					Map<String, Integer> sample2count=new HashMap<String, Integer>();
					
					for(DataRow r: rowBuffer)
						{
						DataCell c=r.getCell(sorter.sampleColumn);
						if(c.isMissing()) throw new IllegalStateException("Empty sample=null in "+r.getKey());
						String sample=StringCell.class.cast(c).getStringValue();
						Integer count= sample2count.get(sample);
						if(count==null)
							{
							count=0;
							}
						else
							{
							getLogger().warn("mutation defined twice for "+sample+":\n"+r);
							}
						sample2count.put(sample,count+1);
						}
					
					int samples_per_mutation= sample2count.size();
					
					List<DataCell> cells= new ArrayList<DataCell>();
					DataRow first=rowBuffer.get(0);
					cells.add(first.getCell(sorter.chromColumn));
					cells.add(first.getCell(sorter.positionColumn));
					cells.add(first.getCell(sorter.refColumn));
					cells.add(first.getCell(sorter.altColumn));
					for(int idx:leftIndexes)
						{
						cells.add(first.getCell(idx));
						}
					cells.add(new IntCell(samples_per_mutation));
					if(!topIndexes.isEmpty())
						{
						for(String sampleName:sample2column.keySet())
							{
							boolean ok=false;
							for(DataRow r:rowBuffer)
								{
								DataCell cell= r.getCell(sorter.sampleColumn);
								if(cell.isMissing()) continue;
								if(!StringCell.class.cast(cell).getStringValue().equals(sampleName)) continue;
								for(int idx:topIndexes)
									{
									cells.add(r.getCell(idx));
									}
								ok=true;
								break;
								}
							if(!ok)
								{
								for(int i=0;i< topIndexes.size();++i)
									{
									cells.add(DataType.getMissingCell());
									}
								}
							}
						}
					System.err.println(""+cells.size());for(Object o:cells) System.err.println("\t"+o);
					++outIndex;
					container.addRowToTable(new DefaultRow(RowKey.createRowKey(outIndex),cells));
					
					rowBuffer.clear();
					}
				if(!hasNext)
					{
					break;
					}
				prevMutation=mutation;
				rowBuffer.add(row);
	            exec.checkCanceled();
	            exec.setProgress(nRow/total,"Getting sample/snp");
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
		
		return new DataTableSpec[]{null};
		}
	@Override
	protected List<SettingsModel> getSettingsModel() {
		List<SettingsModel> arrayModel=new ArrayList<SettingsModel>(super.getSettingsModel());
		arrayModel.add(this.m_chromColumn);
		arrayModel.add(this.m_posColumn);
		arrayModel.add(this.m_refColumn);
		arrayModel.add(this.m_altColumn);
		arrayModel.add(this.m_sampleColumn);
		arrayModel.add(this.m_topColumn);
		arrayModel.add(this.m_leftColumn);
		return arrayModel;
		}
	}

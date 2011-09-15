package fr.inserm.umr915.knime4ngs.nodes.vcf.groupbygene;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import org.knime.core.data.DataCell;
import org.knime.core.data.DataColumnSpec;
import org.knime.core.data.DataColumnSpecCreator;
import org.knime.core.data.DataRow;
import org.knime.core.data.DataTableSpec;
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

import fr.inserm.umr915.knime4ngs.corelib.bio.Mutation;
import fr.inserm.umr915.knime4ngs.corelib.bio.Position;
import fr.inserm.umr915.knime4ngs.nodes.vcf.AbstractVCFNodeModel;

public class GroupByGeneNodeModel extends AbstractVCFNodeModel
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
	
	/** Gene column */
	static final String GENE_COL_PROPERTY="gene.col";
	static final String GENE_COL_DEFAULT="GENE";
	private final SettingsModelColumnName m_geneColumn = new SettingsModelColumnName(
			GENE_COL_PROPERTY,
			GENE_COL_DEFAULT
			);
	
	private static class Sorter
		implements Comparator<DataRow>
		{
		int chromColumn;
		int sampleColumn;
		int positionColumn;
		int refColumn;
		int altColumn;
		int geneColumn;
		
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
	
	
	private static class Hit
		{
		Set<Mutation> mutations=new HashSet<Mutation>();
		int count[];
		}
	
	public GroupByGeneNodeModel()
		{
		super(1,1);
		}
	@Override
	protected BufferedDataTable[] execute(BufferedDataTable[] inData,
			ExecutionContext exec) throws Exception
		{
		SortedSet<String> samplesNames=new TreeSet<String>();
		Set<String> chromosomes=new TreeSet<String>();
		
		BufferedDataTable inTable=inData[0];
		DataTableSpec inSpecs=inTable.getDataTableSpec();
		CloseableRowIterator iter=null;
		
		Sorter sorter=new Sorter();
		sorter.chromColumn = inSpecs.findColumnIndex(this.m_chromColumn.getStringValue());
		sorter.sampleColumn = inSpecs.findColumnIndex(this.m_sampleColumn.getStringValue());
		sorter.positionColumn = inSpecs.findColumnIndex(this.m_posColumn.getStringValue());
		sorter.refColumn = inSpecs.findColumnIndex(this.m_refColumn.getStringValue());
		sorter.altColumn = inSpecs.findColumnIndex(this.m_altColumn.getStringValue());
		sorter.geneColumn= inSpecs.findColumnIndex(this.m_geneColumn.getStringValue());
		

		//collect sample names
		double total= inTable.getRowCount();
		try
			{
			iter=inTable.iterator();
			int nRow=0;
			while(iter.hasNext())
				{
				nRow++;
				DataRow row=iter.next();
				samplesNames.add(getString(row,sorter.sampleColumn));
				chromosomes.add(getString(row,sorter.chromColumn));
	            exec.checkCanceled();
	            exec.setProgress(nRow/total,"Collecting data");
	            }
			}
		finally
			{
			safeClose(iter);
			iter=null;
			}
		
		Map<String, Integer> sample2column=new TreeMap<String, Integer>();
		for(String s:samplesNames)  sample2column.put(s, sample2column.size());
		
		//loop over each chromosomes
		
		BufferedDataContainer container=null;
		List<DataColumnSpec> colspecs=new ArrayList<DataColumnSpec>();
		colspecs.add(inSpecs.getColumnSpec(sorter.chromColumn));
		colspecs.add(inSpecs.getColumnSpec(sorter.geneColumn));
		colspecs.add(new DataColumnSpecCreator("num.samples",IntCell.TYPE).createSpec());
		colspecs.add(new DataColumnSpecCreator("num.distinct.snp",IntCell.TYPE).createSpec());
		for(String sample: sample2column.keySet())
			{
			colspecs.add(new DataColumnSpecCreator(sample,IntCell.TYPE).createSpec());
			}
		container=exec.createDataContainer(new DataTableSpec(colspecs.toArray(new DataColumnSpec[colspecs.size()])));
		int outIndex=0;
		for(String chromosome: chromosomes)
			{
			Map<String,Hit> gene2hit=new TreeMap<String,Hit>();
			exec.setProgress(0.0);
			int nRow=0;
			try
				{
				iter=inTable.iterator();
				
				while(iter.hasNext())
					{
					nRow++;
					exec.checkCanceled();
		            exec.setProgress(nRow/total,"Chromosome "+chromosome);
		         
					DataRow row=iter.next();
					String chrom=StringCell.class.cast(row.getCell(sorter.chromColumn)).getStringValue();
					if(!chrom.equals(chromosome)) continue;
					DataCell cell= row.getCell(sorter.geneColumn);
					if(cell.isMissing()) continue;
					String gene=StringCell.class.cast(cell).getStringValue();
					cell= row.getCell(sorter.sampleColumn);
					if(cell.isMissing()) continue;
					String sample=StringCell.class.cast(cell).getStringValue();
					
					
					Mutation mutation= sorter.getMutation(row);
					Hit hit= gene2hit.get(gene);
					if(hit==null)
						{
						hit=new Hit();
						hit.count=new int[sample2column.size()];
						Arrays.fill(hit.count, 0);
						gene2hit.put(gene,hit);
						}
					hit.mutations.add(mutation);
					hit.count[sample2column.get(sample)]++;
					}
				}
			finally
				{
				safeClose(iter);
				}
			
			for(String gene: gene2hit.keySet())
				{
				Hit hit= gene2hit.get(gene);
				List<DataCell> cells = new ArrayList<DataCell>();
				cells.add(new StringCell(chromosome));
				cells.add(new StringCell(gene));
				int n_samples=0;
				for(int n:hit.count)
					{
					n_samples+=(n==0?0:1);
					}
				cells.add(new IntCell(n_samples));
				cells.add(new IntCell(hit.mutations.size()));
				for(int n:hit.count)
					{
					cells.add(new IntCell(n));
					}
				container.addRowToTable(new DefaultRow(RowKey.createRowKey(++outIndex), cells));
				}
			
				
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
		arrayModel.add(this.m_geneColumn);
		return arrayModel;
		}
	}

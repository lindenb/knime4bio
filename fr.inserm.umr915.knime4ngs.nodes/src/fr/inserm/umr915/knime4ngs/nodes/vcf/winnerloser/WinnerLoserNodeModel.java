package fr.inserm.umr915.knime4ngs.nodes.vcf.winnerloser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;



import org.knime.core.data.DataCell;
import org.knime.core.data.DataColumnSpec;
import org.knime.core.data.DataColumnSpecCreator;
import org.knime.core.data.DataRow;
import org.knime.core.data.DataTableSpec;
import org.knime.core.data.RowKey;
import org.knime.core.data.container.CloseableRowIterator;
import org.knime.core.data.def.BooleanCell;
import org.knime.core.data.def.DefaultRow;
import org.knime.core.data.def.IntCell;
import org.knime.core.data.def.StringCell;
import org.knime.core.node.BufferedDataContainer;
import org.knime.core.node.BufferedDataTable;
import org.knime.core.node.ExecutionContext;
import org.knime.core.node.InvalidSettingsException;
import org.knime.core.node.defaultnodesettings.SettingsModel;
import fr.inserm.umr915.knime4ngs.nodes.vcf.AbstractVCFNodeModel;
import fr.inserm.umr915.knime4ngs.nodes.vcf.transcript.ucsc.UcscTranscriptNodeModel;

@Deprecated
public class WinnerLoserNodeModel extends AbstractVCFNodeModel
	{
	final static int DEFAULT_MIN_SAMPLE_PER_GENE=0;
	final static int DEFAULT_MAX_SAMPLE_PER_GENE=Integer.MAX_VALUE-1;
	static final String MIN_SAMPLE_PER_GENE="min.sample.per.gene";
	static final String MAX_SAMPLE_PER_GENE="max.sample.per.gene";
	static final String SAMPLE_PREFIX="sample";
	
	//public static final String GENE_COLUMN_PROPERTY="gene.column";
	
	//private final SettingsModelString m_geneColumn= new SettingsModelString(GENE_COLUMN_PROPERTY, GENE_COLUMN_PROPERTY);
	/*
	private final SettingsModelInteger m_minSamplePerGene=
        new SettingsModelInteger(MIN_SAMPLE_PER_GENE,DEFAULT_MIN_SAMPLE_PER_GENE);
	private final SettingsModelInteger m_maxSamplePerGene =
        new SettingsModelInteger(MAX_SAMPLE_PER_GENE,DEFAULT_MAX_SAMPLE_PER_GENE);
*/
	
	static private class Transcript
		{
		String kgName;
		String chromosome;
		int txStart1;
		int txEnd1;
		String symbol;
		String desc;
		int count[];
		int countMutations=0;
		}
	
	static private class Sample
		implements Comparable<Sample>
		{
		int index;
		int column;
		String name;
		@Override
		public int hashCode() {
			return name.hashCode();
			}
		@Override
		public boolean equals(Object obj) {
			if (this == obj) return true;
			if (obj == null) return false;
			return name.equals(Sample.class.cast(obj).name);
			}
		
		@Override
		public int compareTo(Sample o)
			{
			return name.compareTo(o.name);
			}
		@Override
		public String toString() {
			return name;
			}
		}

	
	public WinnerLoserNodeModel()
		{
		super(1,1);
		}
	
	
	private List<Sample> getSamples(DataTableSpec inSpecs)
		{
		SortedSet<Sample> samples=new TreeSet<Sample>();
		for(int i=0;i< inSpecs.getNumColumns();++i)
			{
			String colName=inSpecs.getColumnSpec(i).getName();
			
			if(!colName.startsWith(SAMPLE_PREFIX)) continue;
			if(!inSpecs.getColumnSpec(i).getType().equals(BooleanCell.TYPE))
				{
				continue;
				}
			Sample c=new Sample();
			c.name=colName.substring(SAMPLE_PREFIX.length());
			c.column=i;
			samples.add(c);
			}
		int index=0;
		for(Sample c:samples)
			{
			c.index=index++;
			}
		return new ArrayList<WinnerLoserNodeModel.Sample>(samples);
		}
	
	private DataTableSpec createDataTableSpec(DataTableSpec inSpecs)
		{
		List<Sample> samples=getSamples(inSpecs);
		System.err.println(samples);
		DataColumnSpec colSpecs[]=new DataColumnSpec[samples.size()+8];
		colSpecs[0]= new DataColumnSpecCreator("CHROM",StringCell.TYPE).createSpec();
		colSpecs[1]= new DataColumnSpecCreator(UcscTranscriptNodeModel.KG_START,IntCell.TYPE).createSpec();
		colSpecs[2]= new DataColumnSpecCreator(UcscTranscriptNodeModel.KG_END,IntCell.TYPE).createSpec();
		colSpecs[3]= new DataColumnSpecCreator(UcscTranscriptNodeModel.KG_NAME,StringCell.TYPE).createSpec();
		colSpecs[4]= new DataColumnSpecCreator(UcscTranscriptNodeModel.KG_SYMBOL,StringCell.TYPE).createSpec();
		colSpecs[5]= new DataColumnSpecCreator(UcscTranscriptNodeModel.KG_DESC,StringCell.TYPE).createSpec();
		colSpecs[6]= new DataColumnSpecCreator("Count(affected)",IntCell.TYPE).createSpec();
		colSpecs[7]= new DataColumnSpecCreator("Count(SNP)",IntCell.TYPE).createSpec();
		for(int i=0;i< samples.size();++i)
			{
			colSpecs[8+i]= new DataColumnSpecCreator(SAMPLE_PREFIX+samples.get(i).name,IntCell.TYPE).createSpec();
			}
		DataTableSpec out=new DataTableSpec(colSpecs);
		return out;
		}
	
	@Override
	protected BufferedDataTable[] execute(BufferedDataTable[] inData,
			ExecutionContext exec) throws Exception
		{
		
		BufferedDataTable inTable=inData[0];
		DataTableSpec inSpecs=inTable.getDataTableSpec();
		CloseableRowIterator iter=null;
		
		int kgNameCol= findColumnIndex(inSpecs,UcscTranscriptNodeModel.KG_NAME,StringCell.TYPE);
		int chromCol= findColumnIndex(inSpecs, "CHROM",StringCell.TYPE);
		int txStart1Col= findColumnIndex(inSpecs,UcscTranscriptNodeModel.KG_START,IntCell.TYPE);
		int txEnd1Col= findColumnIndex(inSpecs,UcscTranscriptNodeModel.KG_END,IntCell.TYPE);
		int symbolCol= findColumnIndex(inSpecs,UcscTranscriptNodeModel.KG_SYMBOL,StringCell.TYPE);
		int descCol= findColumnIndex(inSpecs,UcscTranscriptNodeModel.KG_DESC,StringCell.TYPE);
		
		
		List<Sample> samples= getSamples(inSpecs);
		Map<String,Transcript> kgName2transcript=new HashMap<String,Transcript>();
		//collect sample names
		double total= inTable.getRowCount();
		try
			{
			iter=inTable.iterator();
			int nRow=0;
			while(iter.hasNext())
				{
				nRow++;
				exec.checkCanceled();
		        exec.setProgress(nRow/total,"Building Transcript table");
				DataRow row=iter.next();
				Transcript tr=new Transcript();
				tr.count=new int[samples.size()];
				Arrays.fill(tr.count, 0);
				tr.kgName = getString(row, kgNameCol);
				if(tr.kgName==null || tr.kgName.trim().isEmpty()) continue;
				tr.chromosome= getString(row, chromCol);
				tr.txStart1= getInt(row, txStart1Col);
				tr.txEnd1= getInt(row, txEnd1Col);
				tr.symbol=  getString(row, symbolCol);
				tr.desc=  getString(row, descCol);
				if(kgName2transcript.containsKey(tr.kgName))
					{	
					Transcript tr2=kgName2transcript.get(tr.kgName);
					tr2.txStart1=Math.min(tr2.txStart1, tr.txStart1);
					tr2.txEnd1=Math.max(tr2.txEnd1, tr.txEnd1);
					tr=tr2;
					}
				else
					{
					kgName2transcript.put(tr.kgName, tr);
					}
				tr.countMutations++;
				for(Sample sample:samples)
					{
					boolean affected= getBool(row, sample.column);
					if(affected) tr.count[sample.index]++;
					}
				}
			}
		finally
			{
			if(iter!=null) iter.close();
			iter=null;
			}
		
		
		try
			{
			BufferedDataContainer container=null;
			
			
			
			DataTableSpec dataTableSpec =createDataTableSpec(inSpecs);
			container = exec.createDataContainer(dataTableSpec);
			exec.setProgress(0.0);
			try
				{
				total=kgName2transcript.size();
				int nRow=0;
				for(String kgName: kgName2transcript.keySet())
					{
					++nRow;
					exec.checkCanceled();
			        exec.setProgress(nRow/total,"Filling Transcript table");
					Transcript tr= kgName2transcript.get(kgName);
					
					int count_affected=0;
					for(int n=0;n<tr.count.length;++n)
						{
						if(tr.count[n]>0) count_affected++;
						}
					
					
					DataCell[] cells = new DataCell[8+samples.size()];
					cells[0]=new StringCell(tr.chromosome);
					cells[1]=new IntCell(tr.txStart1);
					cells[2]=new IntCell(tr.txEnd1);
					cells[3]=new StringCell(tr.kgName);
					cells[4]=new StringCell(tr.symbol);
					cells[5]=new StringCell(tr.desc);
					cells[6]=new IntCell(count_affected);
					cells[7]=new IntCell(tr.countMutations);
					for(int i=0;i< tr.count.length;++i)
						{
						
						cells[8+i]=new IntCell(tr.count[i]);
						}
					DefaultRow row=new DefaultRow(RowKey.createRowKey(nRow), cells);
					
					container.addRowToTable(row);
					}
				}
			catch(Exception err)
				{
				err.printStackTrace();
				throw err;
				}
			finally
				{
				
				}
			container.close();
			BufferedDataTable out = container.getTable();
	        container=null;
	        return new BufferedDataTable[]{out};
			}
		finally
			{
			
			}
		}
	
	@Override
	protected DataTableSpec[] configure(DataTableSpec[] tables)
			throws InvalidSettingsException
		{
		if(tables.length!=1 || tables[0]==null) throw new InvalidSettingsException("Expecetd one table");
		DataTableSpec inSpecs=tables[0];
		findColumnIndex(inSpecs,UcscTranscriptNodeModel.KG_NAME,StringCell.TYPE);
		findColumnIndex(inSpecs, "CHROM",StringCell.TYPE);
		findColumnIndex(inSpecs,UcscTranscriptNodeModel.KG_START,IntCell.TYPE);
		findColumnIndex(inSpecs,UcscTranscriptNodeModel.KG_END,IntCell.TYPE);
		findColumnIndex(inSpecs,UcscTranscriptNodeModel.KG_SYMBOL,StringCell.TYPE);
		findColumnIndex(inSpecs,UcscTranscriptNodeModel.KG_NAME,StringCell.TYPE);
		return new DataTableSpec[]{createDataTableSpec(inSpecs)};
		}
	@Override
	protected List<SettingsModel> getSettingsModel() {
		List<SettingsModel> arrayModel=new ArrayList<SettingsModel>(super.getSettingsModel());
		
		return arrayModel;
		}
	}

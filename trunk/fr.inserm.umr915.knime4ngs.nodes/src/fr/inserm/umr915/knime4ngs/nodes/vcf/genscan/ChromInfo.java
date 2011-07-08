package fr.inserm.umr915.knime4ngs.nodes.vcf.genscan;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("serial")
public abstract class ChromInfo
	{
	protected Map<String, Long> name2shift=new HashMap<String, Long>();
	private static class Hg19 extends ChromInfo
		{
		/* data from UCSC hg19 */
		private Map<String, Integer> name2length=new HashMap<String, Integer>(){{
			put("chr1",249250621);
			put("chr2",243199373);
			put("chr3",198022430);
			put("chr4",191154276);
			put("chr5",180915260);
			put("chr6",171115067);
			put("chr7",159138663);
			put("chrX",155270560);
			put("chr8",146364022);
			put("chr9",141213431);
			put("chr10",135534747);
			put("chr11",135006516);
			put("chr12",133851895);
			put("chr13",115169878);
			put("chr14",107349540);
			put("chr15",102531392);
			put("chr16",90354753);
			put("chr17",81195210);
			put("chr18",78077248);
			put("chr20",63025520);
			put("chrY",59373566);
			put("chr19",59128983);
			put("chr22",51304566);
			put("chr21",48129895);
			put("chrM",16571);
			}};
		private long  genomeLength=0L;
		private String chromNames[]=new String[]
		         {
				"chr1",
				"chr2",
				"chr3",
				"chr4",
				"chr5",
				"chr6",
				"chr7",
				"chr8",
				"chr9",
				"chr10",
				"chr11",
				"chr12",
				"chr13",
				"chr14",
				"chr15",
				"chr16",
				"chr17",
				"chr18",
				"chr20",
				"chr19",
				"chr21",
				"chr22",
				"chrX",
				"chrY",
				"chrM"
				};
		
		Hg19()
			{
			for(String k:name2length.keySet())
				{
				this.genomeLength+=name2length.get(k);
				}
			long prev=0L;
			for(String k:chromNames)
				{
				super.name2shift.put(k, prev);
				prev += name2length.get(k);
				}
			}
		
		@Override
		public long getGenomeSize()
			{
			return this.genomeLength;
			}
		@Override
		public Integer getChromosomeLength(String s)
			{
			return this.name2length.get(s);
			}
		@Override
		public String[] getChromosomes()
			{
			return this.chromNames;
			}
		}
	private static Hg19 HG19=new Hg19();
	public abstract long getGenomeSize();
	public abstract String[] getChromosomes();
	public abstract Integer getChromosomeLength(String s);
	public static ChromInfo getDefaultInstance()
		{
		return HG19;
		}
	public double positionToPixel(String chrom,int position, int pixelLength)
		{
		long left=this.name2shift.get(chrom);
		left+=position;
		return (((double)left)/((double)getGenomeSize()))*pixelLength;
		}
	}

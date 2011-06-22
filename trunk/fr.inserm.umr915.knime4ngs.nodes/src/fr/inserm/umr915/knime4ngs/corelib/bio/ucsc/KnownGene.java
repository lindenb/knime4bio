package fr.inserm.umr915.knime4ngs.corelib.bio.ucsc;

import java.sql.ResultSet;
import java.sql.SQLException;

public class KnownGene {
	private String name;
	private String chrom;
	private char strand;
	private int txStart;
	private int txEnd;
	private int cdsStart;
	private int cdsEnd;
	private int exonStarts[];
	private int exonEnds[];
	
	public String getName()
		{
		return name;
		}
	
	public String getChrom()
		{
		return chrom;
		}
	public char getStrand()
		{
		return strand;
		}
	
	public boolean isForward()
		{
		return getStrand()=='+';
		}
	
	public int getTxStart() {
	return txStart;
	}
	public int getTxEnd() {
	return txEnd;
	}
	public int getCdsStart() {
	return cdsStart;
	}
	public int getCdsEnd() {
	return cdsEnd;
	}
	public int[] getExonStarts() {
	return exonStarts;
	}
	public int[] getExonEnds() {
	return exonEnds;
	}
	
	public int getExonStart(int index)
		{
		return this.exonStarts[index];
		}
	
	
	public int getExonEnd(int index)
		{
		return this.exonEnds[index];
		}
	
	public Exon getExon(int index)
		{
		return new Exon(index);
		}
	public Intron getIntron(int i)
		{
		return new Intron(i);
		}
	
	
	abstract class Segment
		{
		private int index;
		protected Segment(int index)
			{
			this.index=index;
			}
	
		public int getIndex()
			{
			return index;
			}
	
		public KnownGene getGene()
			{
			return KnownGene.this;
			}
	
		public boolean contains(int position)
			{
			return getStart()<=position && position< getEnd();
			}
		public abstract boolean isSplicingAcceptor(int position);
		public abstract boolean isSplicingDonor(int position);
		public boolean isSplicing(int position)
			{
			return isSplicingAcceptor(position) || isSplicingDonor(position);
			}
	
		public abstract String getName();
		public abstract int getStart();
		public abstract int getEnd();
		}
	
	public class Exon extends Segment
		{
		private Exon(int index)
			{
			super(index);
			}
	
		@Override
		public String getName()
			{
			if(getGene().getStrand()=='+')
				{
			return "Exon "+(getIndex()+1);
				}
			else
				{
				return "Exon "+(getGene().getExonCount()-getIndex());
				}
			}
	
		@Override
		public int getStart()
			{
			return getGene().getExonStart(getIndex());
			}
	
		@Override
		public int getEnd()
			{
			return getGene().getExonEnd(getIndex());
			}
	
		@Override
		public String toString()
			{
			return getName();
			}
	
	
		public Intron getNextIntron()
			{
			if(getIndex()+1>=getGene().getExonCount()) return null;
			return getGene().getIntron(getIndex());
			}
		public Intron getPrevIntron()
			{
			if(getIndex()<=0) return null;
			return getGene().getIntron(getIndex()-1);
			}
	
		
		
		@Override
		public boolean isSplicingAcceptor(int position)
			{
			if(!contains(position)) return false;
			if(isForward())
				{
				if(getIndex()== 0) return false;
				return position==getStart();
				}
			else
				{
				if(getIndex()+1== getGene().getExonCount()) return false;
				return position==getEnd()-1;
				}
			}
	
		@Override
		public boolean isSplicingDonor(int position)
			{
			if(!contains(position)) return false;
			if(isForward())
				{
				if(getIndex()+1== getGene().getExonCount()) return false;
				return  (position==getEnd()-1) ||
				(position==getEnd()-2) ||
				(position==getEnd()-3) ;
				}
			else
				{
				if(getIndex()== 0) return false;
				return  (position==getStart()+0) ||
				(position==getStart()+1) ||
				(position==getStart()+2) ;
				}
			}
	
		}
	
	public class Intron extends Segment
		{
		Intron(int index)
			{
			super(index);
			}
	
		@Override
		public int getStart()
			{
			return getGene().getExonEnd(getIndex());
			}
	
		@Override
		public int getEnd()
			{
			return getGene().getExonStart(getIndex()+1);
			}
	
		@Override
		public String getName()
			{
			if(getGene().isForward())
				{
				return "Intron "+(getIndex()+1);
				}
			else
				{
				return "Intron "+(getGene().getExonCount()-getIndex());
				}
			}
	
		public boolean isSplicingAcceptor(int position)
			{
			if(!contains(position)) return false;
			if(isForward())
				{
				return  (position==getEnd()-1) ||
				(position==getEnd()-2);
				}
			else
				{
				return	position==getStart() ||
				position==getStart()+1;
				}
			}
	
	
		public boolean isSplicingDonor(int position)
			{
			if(!contains(position)) return false;
			if(isForward())
				{
				return	position==getStart() ||
				position==getStart()+1;
	
				}
			else
				{
				return  (position==getEnd()-1) ||
				(position==getEnd()-2);
				}
			}
	
		}
	
	
	
	
	
	@Override
	public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + name.hashCode();
	return result;
	}
	@Override
	public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	KnownGene other = (KnownGene) obj;
	if (!name.equals(other.name))
		return false;
	return true;
	}
	
	@Override
	public String toString()
		{
		return getName();
		}
	
	public int length()
		{
		return getTxEnd()-getTxStart();
		}
	public int getExonCount()
		{
		return this.exonStarts.length;
		}
	
	public static KnownGene parse(ResultSet row) throws SQLException
		{
		KnownGene g=new KnownGene();
		g.name=row.getString("name");
		g.chrom=row.getString("chrom");
		g.strand=row.getString("strand").charAt(0);
		g.txStart=row.getInt("txStart");
		g.txEnd=row.getInt("txEnd");
		g.cdsStart=row.getInt("cdsStart");
		g.cdsEnd=row.getInt("cdsEnd");
		int exons=row.getInt("exonCount");
		g.exonStarts=new int[exons];
		g.exonEnds=new int[exons];
		String ss[]=row.getString("exonStarts").split(",");
		for(int i=0;i< exons;++i) g.exonStarts[i]=Integer.parseInt(ss[i]);
		ss=row.getString("exonEnds").split(",");
		for(int i=0;i< exons;++i) g.exonEnds[i]=Integer.parseInt(ss[i]);
		return g;
		}
	
	}

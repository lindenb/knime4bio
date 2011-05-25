package fr.inserm.umr915.knime4ngs.corelib.bio;

public class Segment implements Comparable<Segment>
{
private String chrom;
private int chromStart;
private int chromEnd;

public Segment(String chrom, int position)
	{
	this(chrom,position,position+1);
	}

public Segment(String chrom, int chromStart,int chromEnd)
	{
	this.chrom = chrom;
	this.chromStart = chromStart;
	this.chromEnd=chromEnd;
	}

public String getChromosome()
	{
	return chrom;
	}

public int getChromStart() {
	return chromStart;
}

public int getChromEnd() {
	return chromEnd;
}

@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + chrom.hashCode();
	result = prime * result + chromStart;
	result = prime * result + chromEnd;
	return result;
}
@Override
public boolean equals(Object obj) {
	if (this == obj) return true;
	if (obj == null || getClass() != obj.getClass())
		return false;
	Segment other = (Segment) obj;
	if (chromStart != other.chromStart)
		return false;
	if (chromEnd != other.chromEnd)
		return false;
	return chrom.equals(other.chrom);
	}

@Override
public int compareTo(Segment o)
	{
	int i= chrom.compareTo(o.chrom);
	if(i!=0) return i;
	i= chromStart-o.chromStart;
	if(i!=0) return i;
	//i= chromEnd-o.chromEnd; NO! Not required, UCSC only sort on chrom/chromStart not chromEnd
	return i;
	}

@Override
public String toString()
	{
	return chrom + ":" + chromStart+"-"+chromEnd;
	}

public static Segment parse(String s)
	{
	int n=s.indexOf(':');
	if(n==-1) throw new IllegalArgumentException(" colon missing in "+s);
	int n2= s.indexOf('-', n+1);
	if(n2==-1)
		{
		return new Segment(s.substring(0,n),Integer.parseInt(s.substring(n+1)));
		}
	else
		{
		return new Segment(
				s.substring(0,n),
				Integer.parseInt(s.substring(n+1,n2)),
				Integer.parseInt(s.substring(n2+1))
				);
		}
	}

}

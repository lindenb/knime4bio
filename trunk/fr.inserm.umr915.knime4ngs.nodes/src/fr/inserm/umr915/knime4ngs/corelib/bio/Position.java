package fr.inserm.umr915.knime4ngs.corelib.bio;

public class Position implements Comparable<Position>
{
private String chrom;
private int position;
public Position(String chrom, int position)
	{
	this.chrom = chrom;
	this.position = position;
	}

public String getChromosome()
	{
	return chrom;
	}

public int getPosition()
	{
	return position;
	}

@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + chrom.hashCode();
	result = prime * result + position;
	return result;
}
@Override
public boolean equals(Object obj) {
	if (this == obj) return true;
	if (obj == null || getClass() != obj.getClass())
		return false;
	Position other = (Position) obj;
	if (position != other.position)
		return false;
	return chrom.equals(other.chrom);
	}

@Override
public int compareTo(Position o)
	{
	int i= chrom.compareTo(o.chrom);
	if(i!=0) return i;
	return position-o.position;
	}

@Override
public String toString()
	{
	return chrom + ":" + position;
	}


}

package fr.inserm.umr915.knime4ngs.corelib.bio;

public class Mutation
	implements Comparable<Mutation>
	{
	private Position position;
	private String ref;
	private String alt;
	public Mutation(Position position, String ref, String alt)
		{
		this.position = position;
		this.ref = ref;
		this.alt = alt;
		}
	
	public Position getPosition()
		{
		return position;
		}
	
	public String getRef()
		{
		return ref;
		}
	
	public String getAlt()
		{
		return alt;
		}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + alt.hashCode();
		result = prime * result + position.hashCode();
		result = prime * result + ref.hashCode();
		return result;
	}

	@Override
	public boolean equals(Object obj)
		{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Mutation other = (Mutation) obj;
		if (!alt.equals(other.alt)) return false;
		if (!position.equals(other.position)) return false;
		if (!ref.equals(other.ref)) return false;
		return true;
		}

	@Override
	public String toString()
		{
		return  position.toString() + " " + ref + "/"+alt;
		}
	
	public static boolean isATGC(String s)
	 	{
		if(s.length()!=1) return false;
		char c=Character.toUpperCase(s.charAt(0));
		return c=='A' || c=='T' || c=='G' || c=='C';
	 	}
	
	public boolean isSubstitution()
		{
		return isATGC(getRef()) && isATGC(getAlt());
		}
	
	@Override
	public int compareTo(Mutation o)
		{
		int i=position.compareTo(o.position);
		if(i!=0) return i;
		i= ref.compareTo(o.ref);
		if(i!=0) return i;
		return alt.compareTo(o.alt);
		}
	
	}

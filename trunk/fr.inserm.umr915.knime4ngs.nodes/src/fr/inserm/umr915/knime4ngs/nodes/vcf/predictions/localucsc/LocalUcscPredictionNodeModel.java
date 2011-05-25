package fr.inserm.umr915.knime4ngs.nodes.vcf.predictions.localucsc;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;



import net.sf.picard.reference.IndexedFastaSequenceFile;

import org.knime.base.data.append.column.AppendedColumnRow;
import org.knime.core.data.DataCell;
import org.knime.core.data.DataColumnSpec;
import org.knime.core.data.DataColumnSpecCreator;
import org.knime.core.data.DataRow;
import org.knime.core.data.DataTableSpec;
import org.knime.core.data.DataType;
import org.knime.core.data.RowKey;
import org.knime.core.data.container.CloseableRowIterator;
import org.knime.core.data.def.IntCell;
import org.knime.core.data.def.StringCell;

import org.knime.core.node.BufferedDataContainer;
import org.knime.core.node.BufferedDataTable;
import org.knime.core.node.InvalidSettingsException;
import org.knime.core.node.defaultnodesettings.SettingsModel;
import org.knime.core.node.defaultnodesettings.SettingsModelBoolean;
import org.knime.core.node.defaultnodesettings.SettingsModelColumnName;
import org.knime.core.node.defaultnodesettings.SettingsModelString;
import org.knime.core.node.port.PortObject;
import org.knime.core.node.port.PortObjectSpec;
import org.knime.core.node.port.PortType;
import org.knime.core.node.port.database.DatabasePortObject;
import org.knime.core.node.port.database.DatabasePortObjectSpec;
import org.knime.core.node.port.database.DatabaseQueryConnectionSettings;
import org.knime.core.node.ExecutionContext;


import fr.inserm.umr915.knime4ngs.corelib.knime.AbstractNodeModel;

class KnownGene
	{
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

class GeneticCode
	{
	private String ncbiTable=null;
	
	/** the standard genetic code */
	private static final GeneticCode STANDARD=new GeneticCode("FFLLSSSSYY**CC*WLLLLPPPPHHQQRRRRIIIMTTTTNNKKSSRRVVVVAAAADDEEGGGG");
	/** mitochondrial genetic code */
	private static final GeneticCode MITOCHONDRIAL=new GeneticCode("FFLLSSSSYY**CCWWLLLLPPPPHHQQRRRRIIMMTTTTNNKKSS**VVVVAAAADDEEGGGG");
	/** get the genetic-code table (NCBI data) */ 
	
	public GeneticCode(String ncbiTable)
		{
		this.ncbiTable=ncbiTable;
		}
	
	
	protected String getNCBITable()
		{
		return this.ncbiTable;
		}
	
	/** convert a base to index */
	private static int base2index(char c)
		{
		switch(c)
			{
			case 'T':case 't': return 0;
			case 'C':case 'c': return 1;
			case 'A':case 'a': return 2;
			case 'G':case 'g': return 3;
			default: return -1;
			}
		}
	/** translate cDNA to aminoacid */
	public char translate(char b1,char b2,char b3)
		{
		int base1= base2index(b1);
		int base2= base2index(b2);
		int base3= base2index(b3);
		if(base1==-1 || base2==-1 || base3==-1)
			{
			return '?';
			}
		else
			{
			return getNCBITable().charAt(base1*16+base2*4+base3);
			}
		}
	
	/** get the standard genetic code */
	public static GeneticCode getStandard()
		{
		return STANDARD;
		}
	
	/** get the mitochondrial genetic code */
	public static GeneticCode getMitochondrial()
		{
		return MITOCHONDRIAL;
		}
	
	/** get a genetic code from a chromosome name (either std or mitochondrial */
	public static GeneticCode getByChromosome(String chr)
		{
		if(chr.equalsIgnoreCase("chrM")) return getMitochondrial();
		return getStandard();
		}
	}



/**

 */
public class LocalUcscPredictionNodeModel extends AbstractNodeModel
	{
	final static String PROPERTY_CHROM_COL="vcf.chrom.col";
	final static String DEFAULT_CHROM_COL="CHROM";
	private SettingsModelColumnName m_chromCol=new SettingsModelColumnName(PROPERTY_CHROM_COL,DEFAULT_CHROM_COL);
	
	final static String PROPERTY_POS_COL="vcf.pos.col";
	final static String DEFAULT_POS_COL="POS";
	private SettingsModelColumnName m_pos1Col=new SettingsModelColumnName(PROPERTY_POS_COL, DEFAULT_POS_COL);
	
	
	final static String PROPERTY_REF_COL="vcf.ref.col";
	final static String DEFAULT_REF_COL="REF";
	private SettingsModelColumnName m_refCol=new SettingsModelColumnName(PROPERTY_REF_COL, DEFAULT_REF_COL);
	final static String PROPERTY_ALT_COL="vcf.alt.col";
	final static String DEFAULT_ALT_COL="ALT";
	private SettingsModelColumnName m_altCol=new SettingsModelColumnName(PROPERTY_ALT_COL, DEFAULT_ALT_COL);
	
	final static String PROPERTY_SHOW_RNA="show.rna";
	final boolean DEFAULT_SHOW_RNA=false;
	private SettingsModelBoolean m_showRNASequence=new SettingsModelBoolean(PROPERTY_SHOW_RNA,DEFAULT_SHOW_RNA);
	
	final static String PROPERTY_SHOW_PEP="show.rna";
	final boolean DEFAULT_SHOW_PEP=false;
	private SettingsModelBoolean m_showProteinSequence=new SettingsModelBoolean(PROPERTY_SHOW_PEP,DEFAULT_SHOW_PEP);
	
	/** das URI */
	static final String REFERENCE_URI_PROPERTY="ref.genome";
	static final String DEFAULT_REFERENCE_URI="hg19.fa";
	private final SettingsModelString m_refUri =new SettingsModelString(REFERENCE_URI_PROPERTY,DEFAULT_REFERENCE_URI);
	
	
	
	static private class Consequence
		{
		KnownGene gene=null;
		Set<String> type=new HashSet<String>();
		Set<String> splicing=new HashSet<String>();
		String exonName=null;
		String intronName=null;
		int position_in_cdna=-1;
		int position_protein=-1;
		String wildCodon=null;
		String mutCodon=null;
		char wildAA='\0';
		char mutAA='\0';
		StringBuilder wildRNA=null;
		ProteinCharSequence wildProt=null;
		ProteinCharSequence mutProt=null;
		MutedSequence mutRNA=null;
		}
	
	/**
	 * AbstractCharSequence
	 */
	private static abstract class AbstractCharSequence
		implements CharSequence
		{
		public abstract char charAt(int index);
		public abstract int length();
		@Override
		public CharSequence subSequence(int start, int end)
			{
			StringBuilder s=new StringBuilder(end-start);
			while(start<end)
				{
				s.append(charAt(start));
				++start;
				}
			return s;
			}
		@Override
		public String toString()
			{
			return subSequence(0,length()).toString();
			}
		}
	
	private static class GenomicSeq
		extends AbstractCharSequence
		{
		private byte[] array;
		private int chromStart;
		
		GenomicSeq(net.sf.picard.reference.IndexedFastaSequenceFile indexedFasta,String chrom,int chromStart,int chromEnd) throws IOException
			{
			this.array=indexedFasta.getSubsequenceAt(chrom,chromStart,chromEnd).getBases();
			this.chromStart=chromStart;
			}
		
		public int getChromStart()
			{
			return this.chromStart;
			}
		public int getChromEnd()
			{
			return getChromStart()+this.array.length;
			}
		@Override
		public char charAt(int index)
			{
			if(index<getChromStart() || index>=getChromEnd()) return '?';
			return Character.toUpperCase((char)this.array[index-getChromStart()]);
			}
		@Override
		public int length()
			{
			return getChromEnd();
			}
		}
	
	private static class ProteinCharSequence
		extends AbstractCharSequence
		{
		private GeneticCode code;
		private CharSequence delegate;
		public ProteinCharSequence(GeneticCode code,CharSequence delegate)
			{
			this.code=code;
			this.delegate=delegate;
			}
		
		@Override
		public char charAt(int index)
			{
			return this.code.translate(
				delegate.charAt(index*3+0),
				delegate.charAt(index*3+1),
				delegate.charAt(index*3+2)	
				);
			}
		
		@Override
		public int length()
			{
			return delegate.length()/3;
			}
		}
	
	
	private static class MutedSequence
		extends AbstractCharSequence
		{
		private CharSequence delegate;
		private Map<Integer,Character> mutations=new HashMap<Integer, Character>();
		public MutedSequence(CharSequence delegate)
			{
			this.delegate=delegate;
			}
		@Override
		public char charAt(int index)
			{
			Character c= mutations.get(index);
			return c==null?delegate.charAt(index):c;
			}
		@Override
		public int length()
			{
			return delegate.length();
			}
		}
	
	
    /**
     * Constructor for the node model.
     */
    protected LocalUcscPredictionNodeModel()
    	{
        super(
        		new PortType[]{BufferedDataTable.TYPE,DatabasePortObject.TYPE},
        		new PortType[]{BufferedDataTable.TYPE}
        		);
    	}
    
   
    
    protected DataTableSpec createDataTableSpec()
    	{
    	List<DataColumnSpec> specs=new ArrayList<DataColumnSpec>();
    	specs.add( new DataColumnSpecCreator("kg.name", StringCell.TYPE).createSpec());
    	specs.add( new DataColumnSpecCreator("kg.txStart0", IntCell.TYPE).createSpec());
    	specs.add( new DataColumnSpecCreator("kg.txEnd0", IntCell.TYPE).createSpec());
    	specs.add( new DataColumnSpecCreator("mutation.types", StringCell.TYPE).createSpec());
    	specs.add( new DataColumnSpecCreator("segment.name", StringCell.TYPE).createSpec());
    	specs.add( new DataColumnSpecCreator("position.in.cdna0", IntCell.TYPE).createSpec());
    	specs.add( new DataColumnSpecCreator("position.in.protein1", IntCell.TYPE).createSpec());
    	specs.add( new DataColumnSpecCreator("wild.codon", StringCell.TYPE).createSpec());
    	specs.add( new DataColumnSpecCreator("mut.codon", StringCell.TYPE).createSpec());
    	specs.add( new DataColumnSpecCreator("wild.AA", StringCell.TYPE).createSpec());
    	specs.add( new DataColumnSpecCreator("mut.AA", StringCell.TYPE).createSpec());
    	if(m_showRNASequence.getBooleanValue())
    		{
    		specs.add( new DataColumnSpecCreator("wild.cDNA", StringCell.TYPE).createSpec());
        	specs.add( new DataColumnSpecCreator("mut.cDNA", StringCell.TYPE).createSpec());
    		}
    	if(m_showProteinSequence.getBooleanValue())
			{
			specs.add( new DataColumnSpecCreator("wild.prot", StringCell.TYPE).createSpec());
	    	specs.add( new DataColumnSpecCreator("mut.prot", StringCell.TYPE).createSpec());
			}
    	return new DataTableSpec(specs.toArray(new DataColumnSpec[specs.size()]));
    	}
    
    protected DataCell[] createDataCell(Consequence c)
    	{
    	List<DataCell> array=new ArrayList<DataCell>();
    	array.add(c.gene==null?DataType.getMissingCell():new StringCell(c.gene.getName()));
    	array.add(c.gene==null?DataType.getMissingCell():new IntCell(c.gene.getTxStart()));
    	array.add(c.gene==null?DataType.getMissingCell():new IntCell(c.gene.getTxEnd()));
    	if(c.type.isEmpty() && c.splicing.isEmpty())
    		{
    		array.add(DataType.getMissingCell());
    		}
    	else
    		{
    		StringBuilder b=new StringBuilder();
    		for(String s: c.type)
    			{
    			if(b.length()!=0) b.append(",");
    			b.append(s);
    			}
    		for(String s: c.splicing)
				{
				if(b.length()!=0) b.append(",");
				b.append(s);
				}
    		array.add(new StringCell(b.toString()));
    		}
    	if(c.exonName!=null)
			{
    		array.add(new StringCell(c.exonName));
			}
    	else if(c.intronName!=null)
    		{
    		array.add(new StringCell(c.intronName));
    		}
    	else
    		{
			array.add(DataType.getMissingCell());
			}
    	
    	if(c.position_in_cdna>=0)
			{
			array.add(new IntCell(c.position_in_cdna));
			}
		else
			{
			array.add(DataType.getMissingCell());
			}
    	
    	if(c.position_protein>=0)
			{
			array.add(new IntCell(c.position_protein));
			}
		else
			{
			array.add(DataType.getMissingCell());
			}
    	
    	array.add(c.wildCodon==null?DataType.getMissingCell():new StringCell(c.wildCodon));
    	array.add(c.mutCodon==null?DataType.getMissingCell():new StringCell(c.mutCodon));
    	array.add(c.wildAA=='\0'?DataType.getMissingCell():new StringCell(String.valueOf(c.wildAA)));
    	array.add(c.mutAA=='\0'?DataType.getMissingCell():new StringCell(String.valueOf(c.mutAA)));
    	
    	if(m_showRNASequence.getBooleanValue())
			{
    		array.add(c.wildRNA==null?DataType.getMissingCell():new StringCell(c.wildRNA.toString()));
    		array.add(c.mutRNA==null?DataType.getMissingCell():new StringCell(c.mutRNA.toString()));
			}
		if(m_showProteinSequence.getBooleanValue())
			{
			array.add(c.wildProt==null?DataType.getMissingCell():new StringCell(c.wildProt.toString()));
    		array.add(c.mutProt==null?DataType.getMissingCell():new StringCell(c.mutProt.toString()));
			}
    	
    	return array.toArray(new DataCell[array.size()]);
    	}
    @Override
    protected BufferedDataTable[] execute(final BufferedDataTable[] inData,
            final ExecutionContext exec)
        throws Exception {
            throw new IllegalStateException("Should not happen");
    	}
    
   
    
    @Override
    protected BufferedDataTable[] execute(final PortObject[] inData, final ExecutionContext exec) throws Exception
            {
    		BufferedDataContainer container1=null;
    		int nRow=0;
    		DatabaseQueryConnectionSettings dbsettings=null;
		
			
  
		        // the data table spec of the single output table, 
	        // the table will have three columns:
			BufferedDataTable inTable=(BufferedDataTable)inData[0];
	       
			//sql config
			DatabasePortObject inDbConfig=(DatabasePortObject)inData[1];
			
			DataTableSpec inDataTableSpec = inTable.getDataTableSpec();
			
			int chromCol= inDataTableSpec.findColumnIndex(m_chromCol.getColumnName());
			int posCol= inDataTableSpec.findColumnIndex(m_pos1Col.getColumnName());
			int refCol= inDataTableSpec.findColumnIndex(m_refCol.getColumnName());
			int altCol=inDataTableSpec.findColumnIndex(m_altCol.getColumnName());
			
			
		net.sf.picard.reference.IndexedFastaSequenceFile indexedFasta=new IndexedFastaSequenceFile(new File(m_refUri.getStringValue()));	
			
	     container1 = exec.createDataContainer(new DataTableSpec(inDataTableSpec,createDataTableSpec()));
	        
	      CloseableRowIterator iter=null;
	      Connection con=null;
	      PreparedStatement pstmt=null;
	      ResultSet resultSet=null;
	      GenomicSeq genomicSeq=null;
	      try
	      	{
	    	 dbsettings =  new DatabaseQueryConnectionSettings(
              		inDbConfig.getConnectionModel(),
              		getCredentialsProvider()
              		);
	    	 con=dbsettings.createConnection();
	    	 pstmt=con.prepareStatement("select * from knownGene where chrom=? and not(txStart> ? or txEnd<=?)");
	    		
	    		iter=inTable.iterator();
	    		while(iter.hasNext())
	    			{
	    			DataRow row=iter.next();
					String k=getString(row, chromCol);
					int position0= getInt(row, posCol)-1;
					String refBase=getString(row,refCol).toUpperCase();
					String alt=getString(row, altCol).toUpperCase();
					boolean found=false;
					List<KnownGene> knownGenes=new ArrayList<KnownGene>();
					pstmt.setString(1, k);
					pstmt.setInt(2, position0);
					pstmt.setInt(3, position0);
					resultSet=pstmt.executeQuery();
					while(resultSet.next())
						{
						KnownGene kg=KnownGene.parse(resultSet);
						knownGenes.add(kg);
						}
					resultSet.close();
					
					for(KnownGene gene:knownGenes)
						{
						if(position0>=gene.getTxEnd()) continue;
						if(position0<gene.getTxStart()) continue;
						found=true;
						Consequence consequence=new Consequence();
						consequence.gene=gene;
						if( (refBase==null || refBase.equals("A") || refBase.equals("T") || refBase.equals("G") || refBase.equals("C")) &&
			            		(alt.equals("A") || alt.equals("T") || alt.equals("G") || alt.equals("C"))
			            		)
				        		{
			            		GeneticCode geneticCode=GeneticCode.getByChromosome(gene.getChrom());

				        		
				        		
				        		
				        		if(genomicSeq==null ||
				        	     !(genomicSeq.getChromStart()<=gene.getTxStart() && gene.getTxEnd() <= genomicSeq.getChromEnd())
				        	        )
			    	            	{
			    	            	int start=Math.max(gene.getTxStart()-100,0);
			    	            	genomicSeq=new GenomicSeq(indexedFasta,gene.getChrom(),start,gene.getTxEnd()+100);
			    	            	}
				        		
				        		if(refBase!=null && !String.valueOf(genomicSeq.charAt(position0)).equalsIgnoreCase(refBase))
				        			{
				        			System.err.println("WARNING REF!=GENOMIC SEQ!!! at "+genomicSeq.charAt(position0)+"/"+refBase);
				        			}
				        		
				        		if(gene.isForward())
				            		{
				            		if(position0 < gene.getCdsStart())
				            			{
				            			consequence.type.add("UTR5");
				            			}
				            		else if( gene.getCdsEnd()<= position0 )
				            			{
				            			consequence.type.add("UTR3");
				            			}
				            		else
					            		{
					            		int exon_index=0;
					            		while(exon_index< gene.getExonCount())
					            			{
					            			KnownGene.Exon exon= gene.getExon(exon_index);
					            			for(int i= exon.getStart();
					            					i< exon.getEnd();
					            					++i)
					            				{
					            				if(i==position0)
					        						{
					        						consequence.exonName= exon.getName();
					        						}
					            				if(i< gene.getCdsStart()) continue;
					            				if(i>=gene.getCdsEnd()) break;
					        					
					        					if(consequence.wildRNA==null)
					        						{
					        						consequence.wildRNA=new StringBuilder();
					        						consequence.mutRNA=new MutedSequence(consequence.wildRNA);
					        						}
					        					
					        					if(i==position0)
					        						{
					        						consequence.type.add("EXON");
					        						consequence.exonName=exon.getName();
					        						consequence.position_in_cdna=consequence.wildRNA.length();
					        						
					        						//in splicing ?
					        						if(exon.isSplicing(position0))
					        							{
					        							consequence.splicing.add("SPLICING");
					        							
					        							if(exon.isSplicingAcceptor(position0))
					        								{
					        								consequence.splicing.add("SPLICING_ACCEPTOR");
					        								}
					        							else  if(exon.isSplicingDonor(position0))
					        								{
					        								consequence.splicing.add( "SPLICING_DONOR");
					        								}
					        							}
					        						}
					        					
					        					consequence.wildRNA.append(genomicSeq.charAt(i));
					            				
					            				if(i==position0)
					            					{
					            					consequence.mutRNA.mutations.put(
					            							consequence.position_in_cdna,
					            							alt.charAt(0)
					            							);
					            					}
					            				
					            				if(consequence.wildRNA.length()%3==0 && consequence.wildRNA.length()>0 && consequence.wildProt==null)
						            				{
					            					consequence.wildProt=new ProteinCharSequence(geneticCode,consequence.wildRNA);
					            					consequence.mutProt=new ProteinCharSequence(geneticCode,consequence.mutRNA);
						            				}
					            				}
					            			KnownGene.Intron intron= exon.getNextIntron();
					            			if(intron!=null && intron.contains(position0))
					            				{
					            				consequence.intronName=intron.getName();
					            				consequence.type.add("INTRON");
					            				
					            				if(intron.isSplicing(position0))
					        						{
					            					consequence.splicing.add("INTRON_SPLICING");
					        						if(intron.isSplicingAcceptor(position0))
					        							{
					        							consequence.splicing.add("INTRON_SPLICING_ACCEPTOR");
					        							}
					        						else if(intron.isSplicingDonor(position0))
					        							{
					        							consequence.splicing.add("INTRON_SPLICING_DONOR");
					        							}
					        						}
					            				}
					            			++exon_index;
					            			}
					            		}
				            		
				            		
				            		}
				            	else // reverse orientation
				            		{
				            	
				            		if(position0 < gene.getCdsStart())
				            			{
				            			consequence.type.add("UTR3");
				            			}
				            		else if( gene.getCdsEnd()<=position0 )
				            			{
				            			consequence.type.add("UTR5");
				            			}
				            		else
					            		{
					            		int exon_index = gene.getExonCount()-1;
					            		while(exon_index >=0)
					            			{
					            			KnownGene.Exon exon= gene.getExon(exon_index);
					            			for(int i= exon.getEnd()-1;
					            				    i>= exon.getStart();
					            				--i)
					            				{
					            				if(i==position0)
					        						{
					            					consequence.exonName= exon.getName();
					        						}
					            				if(i>= gene.getCdsEnd()) continue;
					            				if(i<  gene.getCdsStart()) break;
					            				
					            				if(consequence.wildRNA==null)
					        						{
					            					consequence.wildRNA=new StringBuilder();
					            					consequence.mutRNA=new MutedSequence(consequence.wildRNA);
					        						}
					            				
					            				if(i==position0)
					        						{
					            					consequence.type.add("EXON");
					            					consequence.position_in_cdna=consequence.wildRNA.length();
					        						
					        						//in splicing ?
					        						if(exon.isSplicing(position0))
					        							{
					        							consequence.splicing.add( "INTRON_SPLICING");
					        							
					        							if(exon.isSplicingAcceptor(position0))
					        								{
					        								consequence.splicing.add("INTRON_SPLICING_ACCEPTOR");
					        								}
					        							else  if(exon.isSplicingDonor(position0))
					        								{
					        								consequence.splicing.add("INTRON_SPLICING_DONOR");
					        								}
					        							}
					        						
					        						
					        						consequence.mutRNA.mutations.put(
					        								consequence.position_in_cdna,
					        								complement(alt.charAt(0))
					        								);
					        						}
					            				
					            				consequence.wildRNA.append(complement(genomicSeq.charAt(i)));
					            				if( consequence.wildRNA.length()%3==0 &&
				            						consequence.wildRNA.length()>0 &&
				            						consequence.wildProt==null)
						            				{
					            					consequence.wildProt=new ProteinCharSequence(geneticCode,consequence.wildRNA);
					            					consequence.mutProt=new ProteinCharSequence(geneticCode,consequence.mutRNA);
						            				}
					            				
					            				}
					            			
					            			KnownGene.Intron intron= exon.getPrevIntron();
					            			if(intron!=null &&
					            				intron.contains(position0))
					            				{
					            				consequence.intronName=intron.getName();
					            				consequence.type.add("INTRON");
					            				
					            				if(intron.isSplicing(position0))
					        						{
					            					consequence.splicing.add("INTRON_SPLICING");
					        						if(intron.isSplicingAcceptor(position0))
					        							{
					        							consequence.splicing.add("INTRON_SPLICING_ACCEPTOR");
					        							}
					        						else if(intron.isSplicingDonor(position0))
					        							{
					        							consequence.splicing.add("INTRON_SPLICING_DONOR");
					        							}
					        						}
					            				}
					            			--exon_index;
					            			}
					            		}
				            		}//end of if reverse
				        		if( consequence.wildProt!=null &&
				        			consequence.mutProt!=null && 
				        			consequence.position_in_cdna>=0)
					    			{
				            		int pos_aa=consequence.position_in_cdna/3;
				            		int mod= consequence.position_in_cdna%3;
				            		consequence.wildCodon=""+
					            		consequence.wildRNA.charAt(consequence.position_in_cdna-mod+0)+
					            		consequence.wildRNA.charAt(consequence.position_in_cdna-mod+1)+
					            		consequence.wildRNA.charAt(consequence.position_in_cdna-mod+2)
				            			;
				            		consequence.mutCodon=""+
					            		consequence.mutRNA.charAt(consequence.position_in_cdna-mod+0)+
					            		consequence.mutRNA.charAt(consequence.position_in_cdna-mod+1)+
					            		consequence.mutRNA.charAt(consequence.position_in_cdna-mod+2)
					            			;
				            		consequence.position_protein=pos_aa+1;
				            		consequence.wildAA=consequence.wildProt.charAt(pos_aa);
				            		consequence.mutAA=consequence.mutProt.charAt(pos_aa);
					    			if(isStop(consequence.wildProt.charAt(pos_aa)) &&
					    			   !isStop(consequence.mutProt.charAt(pos_aa)))
					    				{
					    				consequence.type.add("EXON_STOP_LOST");
					    				}
					    			else if( !isStop(consequence.wildProt.charAt(pos_aa)) &&
					    				 isStop(consequence.mutProt.charAt(pos_aa)))
					    				{
					    				consequence.type.add( "EXON_STOP_GAINED");
					    				}
					    			else if(consequence.wildProt.charAt(pos_aa)==consequence.mutProt.charAt(pos_aa))
					    				{
					    				consequence.type.add("EXON_CODING_SYNONYMOUS");
					    				}
					    			else
					    				{
					    				consequence.type.add("EXON_CODING_NON_SYNONYMOUS");
					    				}
					    			}
				        		}//end of simpe ATCG
			            	else
			            		{
				        		Integer wildrna=null;
				        		
				        		
				        		
				        		
				        		if(gene.isForward())
				            		{
				            		if(position0 < gene.getCdsStart())
				            			{
				            			consequence.type.add("UTR5");
				            			}
				            		else if( gene.getCdsEnd()<= position0 )
				            			{
				            			consequence.type.add("UTR3");
				            			}
				            		else
					            		{
					            		int exon_index=0;
					            		while(exon_index< gene.getExonCount())
					            			{
					            			KnownGene.Exon exon= gene.getExon(exon_index);
					            			for(int i= exon.getStart();
					            					i< exon.getEnd();
					            					++i)
					            				{
					            				if(i==position0)
					        						{
					            					consequence.exonName=exon.getName();
					        						}
					            				if(i< gene.getCdsStart()) continue;
					            				if(i>= gene.getCdsEnd() ) break;
					        					
					        					if(wildrna==null)
					        						{
					        						wildrna=0;
					        						}
					        					
					        					if(i==position0)
					        						{
					        						consequence.type.add("EXON");
					        						consequence.exonName=exon.getName();
					        						consequence.position_in_cdna=wildrna;
					        					
					        						//in splicing ?
					        						if(exon.isSplicing(position0))
					        							{
					        							consequence.splicing.add("SPLICING");
					        							
					        							if(exon.isSplicingAcceptor(position0))
					        								{
					        								consequence.splicing.add("SPLICING_ACCEPTOR");
					        								}
					        							else  if(exon.isSplicingDonor(position0))
					        								{
					        								consequence.splicing.add("SPLICING_DONOR");
					        								}
					        							}
					        						}
					        					
					        					wildrna++;
					            				}
					            			KnownGene.Intron intron= exon.getNextIntron();
					            			if(intron!=null && intron.contains(position0))
					            				{
					            				consequence.intronName=intron.getName();
					            				consequence.type.add("INTRON");
					            				
					            				if(intron.isSplicing(position0))
					        						{
					            					consequence.splicing.add("INTRON_SPLICING");
					        						if(intron.isSplicingAcceptor(position0))
					        							{
					        							consequence.splicing.add( "INTRON_SPLICING_ACCEPTOR");
					        							}
					        						else if(intron.isSplicingDonor(position0))
					        							{
					        							consequence.splicing.add("INTRON_SPLICING_DONOR");
					        							}
					        						}
					            				}
					            			++exon_index;
					            			}
					            		}
				            		}
				            	else // reverse orientation
				            		{
				            	
				            		if(position0 < gene.getCdsStart())
				            			{
				            			consequence.type.add("UTR3");
				            			}
				            		else if( gene.getCdsEnd()<=position0 )
				            			{
				            			consequence.type.add("UTR5");
				            			}
				            		else
					            		{
					            		
					            		int exon_index = gene.getExonCount()-1;
					            		while(exon_index >=0)
					            			{
					            			KnownGene.Exon exon= gene.getExon(exon_index);
					            			for(int i= exon.getEnd()-1;
					            				    i>= exon.getStart();
					            				--i)
					            				{
					            				if(i==position0)
					        						{
					            					consequence.exonName= exon.getName();
					        						}
					            				if(i>= gene.getCdsEnd()) continue;
					            				if(i<  gene.getCdsStart()) break;
					            				
					            				if(wildrna==null)
					        						{
					        						wildrna=0;
					        						}
					            				
					            				if(i==position0)
					        						{
					            					consequence.type.add("EXON");
					            					consequence.position_in_cdna=wildrna;
					        						
					        						//in splicing ?
					        						if(exon.isSplicing(position0))
					        							{
					        							consequence.splicing.add("INTRON_SPLICING");
					        							
					        							if(exon.isSplicingAcceptor(position0))
					        								{
					        								consequence.splicing.add("INTRON_SPLICING_ACCEPTOR");
					        								}
					        							else  if(exon.isSplicingDonor(position0))
					        								{
					        								consequence.splicing.add("INTRON_SPLICING_DONOR");
					        								}
					        							}
					        						}
					            				
					            				wildrna++;
					            				}
					            			
					            			KnownGene.Intron intron= exon.getPrevIntron();
					            			if(intron!=null &&
					            				intron.contains(position0))
					            				{
					            				consequence.intronName=intron.getName();
					            				consequence.type.add("INTRON");
					            				
					            				if(intron.isSplicing(position0))
					        						{
					            					consequence.splicing.add("INTRON_SPLICING");
					        						if(intron.isSplicingAcceptor(position0))
					        							{
					        							consequence.splicing.add( "INTRON_SPLICING_ACCEPTOR");
					        							}
					        						else if(intron.isSplicingDonor(position0))
					        							{
					        							consequence.splicing.add("INTRON_SPLICING_DONOR");
					        							}
					        						}
					            				}
					            			--exon_index;
					            			}
					            		}
				            		}//end of if reverse
				        		if( wildrna!=null &&
				        			consequence.position_in_cdna>=0)
					    			{
				            		consequence.position_protein=consequence.position_in_cdna/3;
					    			}
			            		}//end of not simple ATGC
						++nRow;	
			            container1.addRowToTable(new AppendedColumnRow(RowKey.createRowKey(nRow),row,createDataCell(consequence)));
			            found=true;
						}//for(kg:genes)
					if(!found)
						{
						++nRow;	
			            container1.addRowToTable(new AppendedColumnRow(RowKey.createRowKey(nRow),row,createDataCell(new Consequence())));
						}
					
					exec.checkCanceled();
	    			}//while(iter.hasNext());
	    		safeClose(iter);
	    		
	    		
	    	safeClose(container1);
	        
			// once we are done, we close the container and return its table
	       
	        BufferedDataTable out1 = container1.getTable();
	        container1=null;
	        
	       
	        BufferedDataTable returnTables[]= new BufferedDataTable[]{out1};
	    	return returnTables;
	    	}
		catch(Exception err)
			{
			getLogger().error("Boum", err);
			err.printStackTrace();
			throw err;
			}
		finally
			{
			safeClose(iter);
			safeClose(container1);
			try {if( resultSet!=null)  resultSet.close(); } catch (Exception e) { }
			try {if( pstmt!=null)  pstmt.close(); } catch (Exception e) { }
			try {if( con!=null)  con.close(); } catch (Exception e) { }
			}
       }
    
    @Override
    protected DataTableSpec[] configure(PortObjectSpec[] inSpecs)
    		throws InvalidSettingsException {
    	if(inSpecs==null || inSpecs.length!=2)
    		{
    		throw new InvalidSettingsException("Expected two tables");
    		}
    	if(! (inSpecs[0] instanceof DataTableSpec)) throw new InvalidSettingsException("node0: expected a DataTableSpec");
    	DataTableSpec in=(DataTableSpec)inSpecs[0];
    	findColumnIndex(in,m_chromCol,StringCell.TYPE);
    	findColumnIndex(in,m_pos1Col,IntCell.TYPE);
    	findColumnIndex(in,m_refCol,StringCell.TYPE);
    	findColumnIndex(in,m_altCol,StringCell.TYPE);
    	
    	if(! (inSpecs[1] instanceof DatabasePortObjectSpec)) throw new InvalidSettingsException("node1: expected a DatabasePortObjectSpec but got "+inSpecs[1].getClass());
    	return new DataTableSpec[]{new DataTableSpec(in,createDataTableSpec())};
    	}

 
    
    @Override
    protected DataTableSpec[] configure(DataTableSpec[] inSpecs)
    		throws InvalidSettingsException {
    	throw new IllegalStateException("should not happen");
    	}
    
    @Override
    protected List<SettingsModel> getSettingsModel()
    	{
    	List<SettingsModel> L=new ArrayList<SettingsModel>(super.getSettingsModel());
    	L.add(m_refCol);
    	L.add(m_altCol);
    	L.add(m_refUri);
    	L.add(m_chromCol);
    	L.add(m_pos1Col);
    	L.add(m_showProteinSequence);
    	L.add(m_showRNASequence);
    	return L;
    	}
    
    private static char complement(char c)
		{
		switch(c)
			{
			case 'A': case 'a': return 'T';
			case 'T': case 't': return 'A';
			case 'G': case 'g': return 'C';
			case 'C': case 'c': return 'G';
			}
		return '?';
		}
	private boolean isStop(char c)
		{
		return !Character.isLetter(c);
		}
	}


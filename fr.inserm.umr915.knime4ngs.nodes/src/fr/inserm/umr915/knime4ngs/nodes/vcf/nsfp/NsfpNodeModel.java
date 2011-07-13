package fr.inserm.umr915.knime4ngs.nodes.vcf.nsfp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.knime.base.data.append.column.AppendedColumnRow;
import org.knime.core.data.DataCell;
import org.knime.core.data.DataColumnSpec;
import org.knime.core.data.DataColumnSpecCreator;
import org.knime.core.data.DataRow;
import org.knime.core.data.DataTable;
import org.knime.core.data.DataTableSpec;
import org.knime.core.data.DataType;
import org.knime.core.data.RowIterator;
import org.knime.core.data.def.IntCell;
import org.knime.core.data.def.StringCell;
import org.knime.core.node.BufferedDataContainer;
import org.knime.core.node.BufferedDataTable;
import org.knime.core.node.ExecutionContext;
import org.knime.core.node.InvalidSettingsException;
import org.knime.core.node.defaultnodesettings.SettingsModel;
import org.knime.core.node.defaultnodesettings.SettingsModelColumnName;
import org.knime.core.node.defaultnodesettings.SettingsModelString;

import fr.inserm.umr915.knime4ngs.corelib.bio.Mutation;
import fr.inserm.umr915.knime4ngs.corelib.bio.MutationKSorter;
import fr.inserm.umr915.knime4ngs.corelib.knime.ExecuteException;
import fr.inserm.umr915.knime4ngs.nodes.vcf.AbstractVCFNodeModel;



/**
 * This is the model implementation of VCFSource.
 * Reads a VCF file
 *
 * @author Pierre Lindenbaum
 */
public class NsfpNodeModel extends AbstractVCFNodeModel
	{
	
	static final String NSFP_FILENAME_PROPERTY="file.name.zip";
	static final String NSFP_DEFAULT_FILENAME="dbNSFP.chr1-22XY.zip";
	private final SettingsModelString m_nsfpFilename =  new SettingsModelString(
			NSFP_FILENAME_PROPERTY,NSFP_DEFAULT_FILENAME);
	
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
	
	private static final String NSFP_COLUMNS[]=
		{
		"#chr",//0
		"pos(1-based)",//1
		"ref",//2
		"alt",//3
		"aaref",//4
		"aaalt",//5
		"hg19pos(1-based)",//6
		"genename",//7
		"geneid",//8
		"CCDSid",//9
		"refcodon",//10
		"codonpos",//11
		"fold-degenerate",//12
		"aapos",//13
		"cds_strand",//14
		"LRT_Omega",//15
		"PhyloP_score",//16
		"PlyloP_pred",//17
		"SIFT_score",//18
		"SIFT_pred",//19
		"Polyphen2_score",//20
		"Polyphen2_pred",//21
		"LRT_score",//22
		"LRT_pred",//23
		"MutationTaster_score",//24
		"MutationTaster_pred",//25
		"Ancestral_allele",//26
		"UniSNP_ids",//27
		"Allele_freq",//28
		"Alt_gene_name",//29
		"dbXrefs",//30
		"Descriptive_gene_name",//31
		"1000_genomes_high_coverage",//32
		"1000_genomes_low_coverage"//33
		};
	private static final int NSFP_POSITION_COLUMN=6;
	
	private static final class NsfpRecord
		{
		private String row[];
		NsfpRecord()
			{
			row=new String[NSFP_COLUMNS.length];
			}
		public String getChromosome()
			{
			String s= String.valueOf(row[0]);
			if(!s.toLowerCase().startsWith("chr")) s="chr"+s;
			return s;
			}
		public int getPosition1()
			{
			return Integer.parseInt(row[NSFP_POSITION_COLUMN]);
			}
		
		public String getRef()
			{
			return row[2].toUpperCase();
			}
		
		public String getAlt()
			{
			return row[3].toUpperCase();
			}
	
		
		@Override
		public String toString() {
			return getChromosome()+":"+getPosition1()+" "+getRef()+"/"+getAlt();
			}
		}
	
	
	
    /**
     * Constructor for the node model.
     */
    protected NsfpNodeModel()
    	{
        super(1,1);
    	}
    
    private NsfpRecord parseNsfpRecord(String tokens[])
    	{
    	if(tokens[NSFP_POSITION_COLUMN].equals("NA") || tokens[NSFP_POSITION_COLUMN].isEmpty() || tokens[NSFP_POSITION_COLUMN].equals("."))
			{
			return null;
			}
    	
    	NsfpRecord rec=new NsfpRecord();
    	System.arraycopy(tokens,0,rec.row,0,tokens.length);
    	

    	return rec;
    	}
   
    private DataTableSpec createDataTableSpec(DataTableSpec in)
    	{
    	DataColumnSpec specs[]=new DataColumnSpec[NSFP_COLUMNS.length];
    	for(int i=0;i< specs.length;++i)
    		{
    		specs[i]=new DataColumnSpecCreator("nsfp."+NSFP_COLUMNS[i],StringCell.TYPE).createSpec();
    		if(i==NSFP_POSITION_COLUMN)
    			{
    			specs[i]=new DataColumnSpecCreator("nsfp.hg19pos(1-based)",IntCell.TYPE).createSpec();
    			}
    		}
    	DataTableSpec cols=new DataTableSpec(specs);
    	return new DataTableSpec(in,cols);
    	}
    
    
    @Override
    protected BufferedDataTable[] execute(
    		final BufferedDataTable[] inData,
            final ExecutionContext exec
            ) throws Exception
            {
			BufferedDataContainer container1=null;
			Pattern tab=Pattern.compile("[\t]");
			FileInputStream fin=null;
			BufferedReader in=null;
			try
		    	{
				//record the entries in the zip file
				Set<String> entryNames=new HashSet<String>();
				ZipFile zipFile=new ZipFile(new File(this.m_nsfpFilename.getStringValue()));
				Enumeration<? extends ZipEntry> t=zipFile.entries();
				while(t.hasMoreElements())
					{
					ZipEntry e=t.nextElement();
					entryNames.add(e.getName());
					}
				
		        // the data table spec of the single output table, 
		        // the table will have three columns:
				BufferedDataTable inTable=inData[0];
				DataTableSpec inSpec=inTable.getDataTableSpec();
				MutationKSorter rowComparator=new MutationKSorter(
					findColumnIndex(inSpec,m_chromCol,StringCell.TYPE),
					findColumnIndex(inSpec,m_pos1Col,IntCell.TYPE),
					findColumnIndex(inSpec,m_refCol,StringCell.TYPE),
					findColumnIndex(inSpec,m_altCol,StringCell.TYPE)
					);
				DataTable sortedTable=inTable;
				if(!isDataTableIsSorted(inTable, exec, rowComparator))
					{
					throw new ExecuteException("Data should be sorted by CHROM/POS/REF/ALT");
					}
				DataTableSpec inDataTableSpec = inTable.getDataTableSpec();
				
		        container1 = exec.createDataContainer(createDataTableSpec(inDataTableSpec));
		        DataCell emptycell[]=new DataCell[NSFP_COLUMNS.length];
		        for(int i=0;i< emptycell.length;++i) emptycell[i]=DataType.getMissingCell();
		        
		        
		        
		        LinkedList<NsfpRecord> buffer=new LinkedList<NsfpNodeModel.NsfpRecord>();
		        double total=inTable.getRowCount();
		        int nRow=0;
		        String prevChrom=null;
		        RowIterator iter=null;
		       
		        //System.err.println("OK");
		        try {
		        	iter=sortedTable.iterator();
		        	while(iter.hasNext())
		        		{
		        		++nRow;
		        		
		        		NsfpRecord candidate=null;
		        		DataRow row=iter.next();
		        		//System.err.println(row);
		        		Mutation mutation=rowComparator.make(row);
		        		//System.err.println(mutation.toString());
		        		String chrom=mutation.getPosition().getChromosome();
		        		if(prevChrom==null || !prevChrom.equals(chrom))
		        			{
		        			safeClose(in);
		        			in=null;
		        			String entryName=chrom;
		        			if(!chrom.startsWith("chr")) entryName="chr"+chrom;
		        			String selectedEntry=null;
		        			
		        			//search for the zip entry for this chromosome
		        			for(String s:entryNames)
		        				{
		        				if(s.endsWith("."+entryName))
		        					{
		        					selectedEntry=s;
		        					break;
		        					}
		        				}
		        			
		        			//the zip entry was found, let's read the archive
		        			if(selectedEntry!=null)
			        			{
			        			ZipEntry entry=new ZipEntry(selectedEntry);
			        			in=new BufferedReader(new InputStreamReader(zipFile.getInputStream(entry)));
			        			
			        			String line=in.readLine();
			        			if(line==null) throw new IOException("Cannot read first line of "+zipFile);
			        			//read the first line
			        			String tokens[]=tab.split(line);
			        			if(tokens.length!=NSFP_COLUMNS.length)
			        				{
			        				throw new IOException("Bad number of columns in "+line);
			        				}
			        			for(int i=0;i< tokens.length;++i)
			        				{
			        				if(!tokens[i].equalsIgnoreCase(NSFP_COLUMNS[i]))
			        					throw new IOException("expected "+NSFP_COLUMNS[i]+" got '"+tokens[i]+"'");
			        				}
			        			}
		        			else
		        				{
		        				System.err.println("chromosome "+chrom+" not in "+entryNames);
		        				}
		        			prevChrom=chrom;
		        			}
		        		
		        		if(in!=null)
		        			{
		        			int bufferIndex=0;
		        			while(bufferIndex< buffer.size())
		        				{
		        				NsfpRecord r=buffer.get(bufferIndex);
		        				int i=r.getChromosome().compareTo(chrom);
		        				if(i<0)
		        					{
		        					buffer.remove(bufferIndex);
		        					continue;
		        					}
		        				if(i>0)
		        					{
		        					break;
		        					}
		        				i= r.getPosition1()-mutation.getPosition().getPosition();
		        				if(i<0)
		        					{
		        					buffer.remove(bufferIndex);
		        					continue;
		        					}
		        				if(i>0)
		        					{
		        					break;
		        					}
		        				i= r.getRef().compareToIgnoreCase(mutation.getRef());
		        				if(i!=0)
		        					{
		        					bufferIndex++;
		        					continue;
		        					}
		        				
		        				i= r.getAlt().compareTo(mutation.getAlt());
		        				if(i!=0)
		        					{
		        					bufferIndex++;
		        					continue;
		        					}
		        				candidate=r;
		        				
		        				break;
		        				}
		        			
		        			bufferIndex=0;
		        			while(candidate==null)
		        				{
		        				String line=in.readLine();
		        				if(line==null) break;
		        				
	        					NsfpRecord r=parseNsfpRecord(tab.split(line));
	        					
	        					if(r==null) continue;
	        					int i=r.getChromosome().compareTo(chrom);
		        				if(i<0)
		        					{
		        					continue;
		        					}
		        				if(i>0)
		        					{
		        					buffer.add(r);
		        					break;
		        					}
		        				i= r.getPosition1()-mutation.getPosition().getPosition();
		        				if(i<0)
		        					{
		        					continue;
		        					}
		        				if(i>0)
		        					{
		        					buffer.add(r);
		        					break;
		        					}
		        				i= r.getRef().compareToIgnoreCase(mutation.getRef());
		        				if(i!=0)
		        					{
		        					buffer.add(r);
		        					}
		        				i= r.getAlt().compareToIgnoreCase(mutation.getAlt());
		        				if(i!=0)
		        					{
		        					buffer.add(r);
		        					}
		        				
		        				candidate=r;
		        				break;
		        				}
		        			}//end of if (in!=null);
		        		
		        		DataCell appendCells[]=null;
		        		if(candidate!=null)
		        			{
		        			DataCell cells[]=new DataCell[candidate.row.length];
		        			for(int i=0;i<cells.length;++i)
		        				{
		        				if(i==NSFP_POSITION_COLUMN)
		        					{
		        					cells[i]=new IntCell(candidate.getPosition1());
		        					}
		        				else if(candidate.row[i].isEmpty() || candidate.row[i].equals("."))
		        					{
		        					cells[i]=DataType.getMissingCell();
		        					}
		        				else
		        					{
		        					cells[i]=new StringCell(candidate.row[i]);
		        					}
		        				}
		        			appendCells=cells;
		        			}
		        		else
		        			{
		        			appendCells=emptycell;
		        			}
		        		
		        		container1.addRowToTable(new AppendedColumnRow(row,appendCells));
		        		exec.checkCanceled();
		            	exec.setProgress(nRow/total,"Nsfp processing....");
		        		}
		        	
					} 
		        catch (Exception e)
					{
		        	e.printStackTrace();
					throw e;
					}
				finally
					{
					safeClose(iter);
					safeClose(in);
					}
		        
				// once we are done, we close the container and return its table
		        container1.close();
		        BufferedDataTable out1 = container1.getTable();
		        container1=null;
		      
		      
		        BufferedDataTable array[]= new BufferedDataTable[]{out1};
		    	return array;
		    	}
		catch(Exception err)
			{
			getLogger().error("Boum", err);
			err.printStackTrace();
			throw err;
			}
		finally
			{
			safeClose(fin);
			safeClose(container1);
			}
       }
    
    
    @Override
    protected DataTableSpec[] configure(DataTableSpec[] inSpecs)
    		throws InvalidSettingsException {
    	if(inSpecs==null || inSpecs.length!=1)
    		{
    		throw new InvalidSettingsException("Expected one table");
    		}
    	
    	DataTableSpec in=inSpecs[0];
    	findColumnIndex(in,m_chromCol,StringCell.TYPE);
		findColumnIndex(in,m_pos1Col,IntCell.TYPE);
		findColumnIndex(in,m_refCol,StringCell.TYPE);
		findColumnIndex(in,m_altCol,StringCell.TYPE);
    	return new DataTableSpec[]{createDataTableSpec(in)};
    	}
    
    @Override
    protected List<SettingsModel> getSettingsModel() {
    	List<SettingsModel> L=new ArrayList<SettingsModel>( super.getSettingsModel());
    	L.add(this.m_nsfpFilename);
    	L.add(m_refCol);
    	L.add(m_altCol);
    	L.add(m_chromCol);
    	L.add(m_pos1Col);
    	return L;
    	}
    
    
    
	}


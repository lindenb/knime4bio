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
import org.knime.base.data.sort.SortedTable;
import org.knime.core.data.DataCell;
import org.knime.core.data.DataColumnSpec;
import org.knime.core.data.DataColumnSpecCreator;
import org.knime.core.data.DataRow;
import org.knime.core.data.DataTable;
import org.knime.core.data.DataTableSpec;
import org.knime.core.data.RowIterator;
import org.knime.core.data.def.IntCell;
import org.knime.core.data.def.StringCell;
import org.knime.core.node.BufferedDataContainer;
import org.knime.core.node.BufferedDataTable;
import org.knime.core.node.ExecutionContext;
import org.knime.core.node.InvalidSettingsException;
import org.knime.core.node.defaultnodesettings.SettingsModel;
import org.knime.core.node.defaultnodesettings.SettingsModelString;

import fr.inserm.umr915.knime4ngs.corelib.bio.Mutation;
import fr.inserm.umr915.knime4ngs.corelib.bio.MutationKSorter;
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
	
	private static final String NSFP_COLUMNS[]=
		{
		"#chr",
		"pos(1-based)",
		"ref",
		"alt",
		"aaref",
		"aaalt",
		"hg19pos(1-based)",
		"genename",
		"geneid",
		"CCDSid",
		"refcodon",
		"codonpos",
		"fold-degenerate",
		"aapos",
		"cds_strand",
		"LRT_Omega",
		"PhyloP_score",
		"PlyloP_pred",
		"SIFT_score",
		"SIFT_pred",
		"Polyphen2_score",
		"Polyphen2_pred",
		"LRT_score",
		"LRT_pred",
		"MutationTaster_score",
		"MutationTaster_pred"
		};
	
	
	private static final class NsfpRecord
		{
		String chr; //0
		String pos1; //1
		String ref; //2
		String alt; //3
		String aaref; //4
		String aaalt; //5
		int hg19pos1; //6
		String genename; //7
		String geneid; //8
		String CCDSid; //9
		String refcodon; //10
		String codonpos; //11
		String fold_degenerate; //12
		String aapos; //13
		String cds_strand; //14
		String LRT_Omega; //15
		String PhyloP_score; //16
		String PlyloP_pred; //17
		String SIFT_score; //18
		String SIFT_pred; //19
		String Polyphen2_score; //20
		String Polyphen2_pred; //21
		String LRT_score; //22
		String LRT_pred; //23
		String MutationTaster_score; //24
		String MutationTaster_pred; //25
		@Override
		public String toString() {
			return chr+":"+hg19pos1+" "+ref+"/"+alt;
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
    	NsfpRecord rec=new NsfpRecord();
    	rec.chr=tokens[0];
    	if(!rec.chr.toLowerCase().startsWith("chr")) rec.chr="chr"+rec.chr;
    	rec.pos1=tokens[1];
    	rec.ref=tokens[2];
    	rec.alt=tokens[3];
    	rec.aaref=tokens[4];
    	rec.aaalt=tokens[5];
    	if(tokens[6].equals("NA")) return null;
    	rec.hg19pos1=Integer.parseInt(tokens[6]);
    	rec.genename=tokens[7];
    	rec.geneid=tokens[8];
    	rec.CCDSid=tokens[9];
    	rec.refcodon=tokens[10];
    	rec.codonpos=tokens[11];
    	rec.fold_degenerate=tokens[12];
    	rec.aapos=tokens[13];
    	rec.cds_strand=tokens[14];
    	rec.LRT_Omega=tokens[15];
    	rec.PhyloP_score=tokens[16];
    	rec.PlyloP_pred=tokens[17];
    	rec.SIFT_score=tokens[18];
    	rec.SIFT_pred=tokens[19];
    	rec.Polyphen2_score=tokens[20];
    	rec.Polyphen2_pred=tokens[21];
    	rec.LRT_score=tokens[22];
    	rec.LRT_pred=tokens[23];
    	rec.MutationTaster_score=tokens[24];
    	rec.MutationTaster_pred=tokens[25];
    	return rec;
    	}
   
    private DataTableSpec createDataTableSpec(DataTableSpec in)
    	{
    	DataColumnSpec specs[]=new DataColumnSpec[26];
    	specs[0]=new DataColumnSpecCreator("nsfp.#chr",StringCell.TYPE).createSpec();
    	specs[1]=new DataColumnSpecCreator("nsfp.pos-hg18(1-based)",StringCell.TYPE).createSpec();
    	specs[2]=new DataColumnSpecCreator("nsfp.ref",StringCell.TYPE).createSpec();
    	specs[3]=new DataColumnSpecCreator("nsfp.alt",StringCell.TYPE).createSpec();
    	specs[4]=new DataColumnSpecCreator("nsfp.aaref",StringCell.TYPE).createSpec();
    	specs[5]=new DataColumnSpecCreator("nsfp.aaalt",StringCell.TYPE).createSpec();
    	specs[6]=new DataColumnSpecCreator("nsfp.hg19pos(1-based)",IntCell.TYPE).createSpec();
    	specs[7]=new DataColumnSpecCreator("nsfp.genename",StringCell.TYPE).createSpec();
    	specs[8]=new DataColumnSpecCreator("nsfp.geneid",StringCell.TYPE).createSpec();
    	specs[9]=new DataColumnSpecCreator("nsfp.CCDSid",StringCell.TYPE).createSpec();
    	specs[10]=new DataColumnSpecCreator("nsfp.refcodon",StringCell.TYPE).createSpec();
    	specs[11]=new DataColumnSpecCreator("nsfp.codonpos",StringCell.TYPE).createSpec();
    	specs[12]=new DataColumnSpecCreator("nsfp.fold-degenerate",StringCell.TYPE).createSpec();
    	specs[13]=new DataColumnSpecCreator("nsfp.aapos",StringCell.TYPE).createSpec();
    	specs[14]=new DataColumnSpecCreator("nsfp.cds_strand",StringCell.TYPE).createSpec();
    	specs[15]=new DataColumnSpecCreator("nsfp.LRT_Omega",StringCell.TYPE).createSpec();
    	specs[16]=new DataColumnSpecCreator("nsfp.PhyloP_score",StringCell.TYPE).createSpec();
    	specs[17]=new DataColumnSpecCreator("nsfp.PlyloP_pred",StringCell.TYPE).createSpec();
    	specs[18]=new DataColumnSpecCreator("nsfp.SIFT_score",StringCell.TYPE).createSpec();
    	specs[19]=new DataColumnSpecCreator("nsfp.SIFT_pred",StringCell.TYPE).createSpec();
    	specs[20]=new DataColumnSpecCreator("nsfp.Polyphen2_score",StringCell.TYPE).createSpec();
    	specs[21]=new DataColumnSpecCreator("nsfp.Polyphen2_pred",StringCell.TYPE).createSpec();
    	specs[22]=new DataColumnSpecCreator("nsfp.LRT_score",StringCell.TYPE).createSpec();
    	specs[23]=new DataColumnSpecCreator("nsfp.LRT_pred",StringCell.TYPE).createSpec();
    	specs[24]=new DataColumnSpecCreator("nsfp.MutationTaster_score",StringCell.TYPE).createSpec();
    	specs[25]=new DataColumnSpecCreator("nsfp.MutationTaster_pred",StringCell.TYPE).createSpec();
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
					findColumnIndex(inSpec,"CHROM",StringCell.TYPE),
					findColumnIndex(inSpec,"POS",IntCell.TYPE),
					findColumnIndex(inSpec,"REF",StringCell.TYPE),
					findColumnIndex(inSpec,"ALT",StringCell.TYPE)
					);
				DataTable sortedTable=inTable;
				if(!isDataTableIsSorted(inTable, exec, rowComparator))
					{
					System.err.println("not sorted");
					sortedTable=new SortedTable(inTable, rowComparator, false, exec);
					}
				DataTableSpec inDataTableSpec = inTable.getDataTableSpec();
				
		        container1 = exec.createDataContainer(createDataTableSpec(inDataTableSpec));
		        DataCell emptycell[]=new DataCell[26];
		        emptycell[0]=new StringCell("");
		        emptycell[1]=new StringCell("");
		        emptycell[2]=new StringCell("");
		        emptycell[3]=new StringCell("");
		        emptycell[4]=new StringCell("");
		        emptycell[5]=new StringCell("");
		        emptycell[6]=new IntCell(-1);
		        emptycell[7]=new StringCell("");
		        emptycell[8]=new StringCell("");
		        emptycell[9]=new StringCell("");
		        emptycell[10]=new StringCell("");
		        emptycell[11]=new StringCell("");
		        emptycell[12]=new StringCell("");
		        emptycell[13]=new StringCell("");
		        emptycell[14]=new StringCell("");
		        emptycell[15]=new StringCell("");
		        emptycell[16]=new StringCell("");
		        emptycell[17]=new StringCell("");
		        emptycell[18]=new StringCell("");
		        emptycell[19]=new StringCell("");
		        emptycell[20]=new StringCell("");
		        emptycell[21]=new StringCell("");
		        emptycell[22]=new StringCell("");
		        emptycell[23]=new StringCell("");
		        emptycell[24]=new StringCell("");
		        emptycell[25]=new StringCell("");
		        
		        
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
		        		Mutation mutation=rowComparator.make(row);
		        		//System.err.println(mutation.toString());
		        		String chrom=mutation.getPosition().getChromosome();
		        		if(prevChrom==null || !prevChrom.equals(chrom))
		        			{
		        			safeClose(in);
		        			in=null;
		        			String entryName=chrom;
		        			if(!chrom.startsWith("chr")) entryName="chr"+chrom;
		        			entryName="dbNSFP."+entryName;
		        			if(entryNames.contains(entryName))
			        			{
		        				
			        			ZipEntry entry=new ZipEntry(entryName);
			        			in=new BufferedReader(new InputStreamReader(zipFile.getInputStream(entry)));
			        			
			        			String line=in.readLine();
			        			if(line==null) throw new IOException("Cannot read first line of "+zipFile);
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
		        				System.err.println("chromosome "+chrom+" not in "+zipFile);
		        				}
		        			prevChrom=chrom;
		        			}
		        		
		        		if(in!=null)
		        			{
		        			
		        			while(!buffer.isEmpty())
		        				{
		        				
		        				NsfpRecord r=buffer.getFirst();
		        				int i=r.chr.compareTo(chrom);
		        				if(i<0)
		        					{
		        					buffer.removeFirst();
		        					continue;
		        					}
		        				if(i>0)
		        					{
		        					break;
		        					}
		        				i= r.hg19pos1-mutation.getPosition().getPosition();
		        				if(i<0)
		        					{
		        					buffer.removeFirst();
		        					continue;
		        					}
		        				if(i>0)
		        					{
		        					break;
		        					}
		        				i= r.ref.compareToIgnoreCase(mutation.getRef());
		        				if(i<0)
		        					{
		        					buffer.removeFirst();
		        					continue;
		        					}
		        				if(i>0)
		        					{
		        					break;
		        					}
		        				i= r.alt.compareToIgnoreCase(mutation.getAlt());
		        				if(i<0)
		        					{
		        					buffer.removeFirst();
		        					continue;
		        					}
		        				if(i>0)
		        					{
		        					break;
		        					}
		        				candidate=r;
		        				
		        				break;
		        				}
		        			while(candidate==null && buffer.isEmpty())
		        				{
		        				String line=in.readLine();
		        				if(line==null) break;
		        				
	        					NsfpRecord r=parseNsfpRecord(tab.split(line));
	        					if(r==null) continue;
	        					int i=r.chr.compareTo(chrom);
		        				if(i<0)
		        					{
		        					continue;
		        					}
		        				if(i>0)
		        					{
		        					buffer.add(r);
		        					break;
		        					}
		        				i= r.hg19pos1-mutation.getPosition().getPosition();
		        				if(i<0)
		        					{
		        					continue;
		        					}
		        				if(i>0)
		        					{
		        					buffer.add(r);
		        					break;
		        					}
		        				i= r.ref.compareToIgnoreCase(mutation.getRef());
		        				if(i<0)
		        					{
		        					continue;
		        					}
		        				if(i>0)
		        					{
		        					buffer.add(r);
		        					break;
		        					}
		        				i= r.alt.compareToIgnoreCase(mutation.getAlt());
		        				if(i<0)
		        					{
		        					continue;
		        					}
		        				if(i>0)
		        					{
		        					buffer.add(r);
		        					break;
		        					}
		        				
		        				candidate=r;
		        				break;
		        				}
		        			}//end of if (in!=null);
		        		
		        		DataCell appendCells[]=null;
		        		if(candidate!=null)
		        			{
		        			DataCell cells[]=new DataCell[26];
		        			cells[0]=new StringCell(candidate.chr);
		        			cells[1]=new StringCell(candidate.pos1);
		        			cells[2]=new StringCell(candidate.ref);
		        			cells[3]=new StringCell(candidate.alt);
		        			cells[4]=new StringCell(candidate.aaref);
		        			cells[5]=new StringCell(candidate.aaalt);
		        			cells[6]=new IntCell(candidate.hg19pos1);
		        			cells[7]=new StringCell(candidate.genename);
		        			cells[8]=new StringCell(candidate.geneid);
		        			cells[9]=new StringCell(candidate.CCDSid);
		        			cells[10]=new StringCell(candidate.refcodon);
		        			cells[11]=new StringCell(candidate.codonpos);
		        			cells[12]=new StringCell(candidate.fold_degenerate);
		        			cells[13]=new StringCell(candidate.aapos);
		        			cells[14]=new StringCell(candidate.cds_strand);
		        			cells[15]=new StringCell(candidate.LRT_Omega);
		        			cells[16]=new StringCell(candidate.PhyloP_score);
		        			cells[17]=new StringCell(candidate.PlyloP_pred);
		        			cells[18]=new StringCell(candidate.SIFT_score);
		        			cells[19]=new StringCell(candidate.SIFT_pred);
		        			cells[20]=new StringCell(candidate.Polyphen2_score);
		        			cells[21]=new StringCell(candidate.Polyphen2_pred);
		        			cells[22]=new StringCell(candidate.LRT_score);
		        			cells[23]=new StringCell(candidate.LRT_pred);
		        			cells[24]=new StringCell(candidate.MutationTaster_score);
		        			cells[25]=new StringCell(candidate.MutationTaster_pred);

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
    	findColumnIndex(in, "CHROM",StringCell.TYPE);
    	findColumnIndex(in, "POS",IntCell.TYPE);
    	findColumnIndex(in, "REF",StringCell.TYPE);
    	findColumnIndex(in, "ALT",StringCell.TYPE);
    	return new DataTableSpec[]{createDataTableSpec(in)};
    	}
    
    @Override
    protected List<SettingsModel> getSettingsModel() {
    	List<SettingsModel> L=new ArrayList<SettingsModel>( super.getSettingsModel());
    	L.add(this.m_nsfpFilename);
    	return L;
    	}
    
    
    
	}


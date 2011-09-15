package fr.inserm.umr915.knime4ngs.nodes.vcf.integragen.loadsnp;

import java.util.ArrayList;
import java.util.List;

import org.knime.core.data.DataColumnSpec;
import org.knime.core.data.DataColumnSpecCreator;
import org.knime.core.data.DataTableSpec;
import org.knime.core.data.def.DoubleCell;
import org.knime.core.data.def.IntCell;
import org.knime.core.data.def.StringCell;
import org.knime.core.node.defaultnodesettings.SettingsModel;
import org.knime.core.node.defaultnodesettings.SettingsModelBoolean;

import fr.inserm.umr915.knime4ngs.corelib.knime.ExecuteException;
import fr.inserm.umr915.knime4ngs.nodes.vcf.integragen.LoadIgNodeModel;


/**
 * This is the model implementation of VCFLoader.
 * Loads Integragen SNP file
 *
 * @author Pierre Lindenbaum
 */
public class LoadIgSnpNodeModel extends LoadIgNodeModel
	{
	static final String IGNORE_CDNA_PROPERTY="ignore.cdna";
	static final boolean IGNORE_CDNA_DEFAULT=true;
	private final SettingsModelBoolean m_ignore_cdna =  new SettingsModelBoolean( IGNORE_CDNA_PROPERTY,IGNORE_CDNA_DEFAULT);
	
	static final String IGNORE_PEP_PROPERTY="ignore.pep";
	static final boolean IGNORE_PEP_DEFAULT=true;
	private final SettingsModelBoolean m_ignore_protein =  new SettingsModelBoolean( IGNORE_PEP_PROPERTY,IGNORE_PEP_DEFAULT);
	
    public LoadIgSnpNodeModel()
    	{
    	}

    @Override
    protected DataTableSpec createVcfDataColumnSpec()
		{
		DataColumnSpec[] allColSpecs = new DataColumnSpec[42];
		
		 allColSpecs[0] =  new DataColumnSpecCreator("POS", IntCell.TYPE).createSpec();
		 allColSpecs[1] =  new DataColumnSpecCreator("CHROM", StringCell.TYPE).createSpec();
		 allColSpecs[2] =  new DataColumnSpecCreator("SAMPLE", StringCell.TYPE).createSpec();
		 allColSpecs[3] =  new DataColumnSpecCreator("ID", StringCell.TYPE).createSpec();
		 allColSpecs[4] =  new DataColumnSpecCreator("hapmap_ref_other", StringCell.TYPE).createSpec();
		 allColSpecs[5] =  new DataColumnSpecCreator("X1000Genome.obs", StringCell.TYPE).createSpec();
		 allColSpecs[6] =  new DataColumnSpecCreator("X1000Genome.desc", StringCell.TYPE).createSpec();
		 allColSpecs[7] =  new DataColumnSpecCreator("Freq.HTZ.Exomes", DoubleCell.TYPE).createSpec();
		 allColSpecs[8] =  new DataColumnSpecCreator("Freq.Hom.Exomes", DoubleCell.TYPE).createSpec();
		 allColSpecs[9] =  new DataColumnSpecCreator("A", IntCell.TYPE).createSpec();
		 allColSpecs[10] =  new DataColumnSpecCreator("C", IntCell.TYPE).createSpec();
		 allColSpecs[11] =  new DataColumnSpecCreator("G", IntCell.TYPE).createSpec();
		 allColSpecs[12] =  new DataColumnSpecCreator("T", IntCell.TYPE).createSpec();
		 allColSpecs[13] =  new DataColumnSpecCreator("ALT", StringCell.TYPE).createSpec();
		 allColSpecs[14] =  new DataColumnSpecCreator("total", IntCell.TYPE).createSpec();
		 allColSpecs[15] =  new DataColumnSpecCreator("used", IntCell.TYPE).createSpec();
		 allColSpecs[16] =  new DataColumnSpecCreator("score", StringCell.TYPE).createSpec();
		 allColSpecs[17] =  new DataColumnSpecCreator("REF", StringCell.TYPE).createSpec();
		 allColSpecs[18] =  new DataColumnSpecCreator("type", StringCell.TYPE).createSpec();
		 allColSpecs[19] =  new DataColumnSpecCreator("Gene.name", StringCell.TYPE).createSpec();
		 allColSpecs[20] =  new DataColumnSpecCreator("Gene.start", IntCell.TYPE).createSpec();
		 allColSpecs[21] =  new DataColumnSpecCreator("Gene.end", IntCell.TYPE).createSpec();
		 allColSpecs[22] =  new DataColumnSpecCreator("strand", StringCell.TYPE).createSpec();
		 allColSpecs[23] =  new DataColumnSpecCreator("nbre.exon", StringCell.TYPE).createSpec();
		 allColSpecs[24] =  new DataColumnSpecCreator("refseq", StringCell.TYPE).createSpec();
		 allColSpecs[25] =  new DataColumnSpecCreator("typeannot", StringCell.TYPE).createSpec();
		 allColSpecs[26] =  new DataColumnSpecCreator("type.pos", StringCell.TYPE).createSpec();
		 allColSpecs[27] =  new DataColumnSpecCreator("index.cdna", StringCell.TYPE).createSpec();
		 allColSpecs[28] =  new DataColumnSpecCreator("index.prot", StringCell.TYPE).createSpec();
		 allColSpecs[29] =  new DataColumnSpecCreator("Taille.cdna", IntCell.TYPE).createSpec();
		 allColSpecs[30] =  new DataColumnSpecCreator("Intron.start", StringCell.TYPE).createSpec();
		 allColSpecs[31] =  new DataColumnSpecCreator("Intron.end", StringCell.TYPE).createSpec();
		 allColSpecs[32] =  new DataColumnSpecCreator("codon.wild", StringCell.TYPE).createSpec();
		 allColSpecs[33] =  new DataColumnSpecCreator("aa.wild", StringCell.TYPE).createSpec();
		 allColSpecs[34] =  new DataColumnSpecCreator("codon.mut", StringCell.TYPE).createSpec();
		 allColSpecs[35] =  new DataColumnSpecCreator("aa.mut", StringCell.TYPE).createSpec();
		 allColSpecs[36] =  new DataColumnSpecCreator("cds.wild", StringCell.TYPE).createSpec();
		 allColSpecs[37] =  new DataColumnSpecCreator("cds.mut", StringCell.TYPE).createSpec();
		 allColSpecs[38] =  new DataColumnSpecCreator("prot.wild", StringCell.TYPE).createSpec();
		 allColSpecs[39] =  new DataColumnSpecCreator("prot.mut", StringCell.TYPE).createSpec();
		 allColSpecs[40] =  new DataColumnSpecCreator("mirna", StringCell.TYPE).createSpec();
		 allColSpecs[41] =  new DataColumnSpecCreator("region.splice", StringCell.TYPE).createSpec();
	    return new DataTableSpec( allColSpecs);
		}
    
    
    /* give a chance to accept the header */
    protected void validateHeader(
    		String line,
    		String uri,
    		String tokens[],int index,DataTableSpec spec
    		)  throws ExecuteException
    	{
    	switch(index)
    		{
    		case  0: 
    			if(!tokens[index].startsWith("Position."))
    				{
    				throw new ExecuteException(
    						"In line "+line+"\nfile\""+uri+"\"\n"+
    						"got column["+(index+1)+"]="+tokens[index]+" expected to start With \"Position\""
    						);
    				}
    			break;
    		case  1: 
    			if(!tokens[index].equalsIgnoreCase("chrom"))
    				{
    				throw new ExecuteException(
    						"In line "+line+"\nfile\""+uri+"\"\n"+
    						"got column["+(index+1)+"]="+tokens[index]+" expected  \"chrom\""
    						);
    				}
    			break;
    		case  2: 
    			if(!tokens[index].equalsIgnoreCase("sample.ID"))
    				{
    				throw new ExecuteException(
    						"In line "+line+"\nfile\""+uri+"\"\n"+
    						"got column["+(index+1)+"]="+tokens[index]+" expected \"sample.ID\""
    						);
    				}
    			break;
    			
    		case  3: 
    			if(!tokens[index].equalsIgnoreCase("rs.name"))
    				{
    				throw new ExecuteException(
    						"In line "+line+"\nfile\""+uri+"\"\n"+
    						"got column["+(index+1)+"]="+tokens[index]+" expected \"rs.name\""
    						);
    				}
    			break;	
    		case  13: 
    			if(!tokens[index].equalsIgnoreCase("modified_call"))
    				{
    				throw new ExecuteException(
    						"In line "+line+"\nfile\""+uri+"\"\n"+
    						"got column["+(index+1)+"]="+tokens[index]+" expected  \"modified_call\""
    						);
    				}
    			break;
    		case  17: 
    			if(!tokens[index].equalsIgnoreCase("reference"))
    				{
    				throw new ExecuteException(
    						"In line "+line+"\nfile\""+uri+"\"\n"+
    						"got column["+(index+1)+"]="+tokens[index]+" expected  \"reference\""
    						);
    				}
    			break;
    		case  41: 
    			if(!(tokens[index].equalsIgnoreCase("region.splice") ||
    				 tokens[index].equalsIgnoreCase("region.splice.intron")))
    				{
    				throw new ExecuteException(
    						"In line "+line+"\nfile\""+uri+"\"\n"+
    						"got column["+(index+1)+"]="+tokens[index]+" expected  \"region.splice\""
    						);
    				}
    			break;
    		default:super.validateHeader(line, uri, tokens, index, spec);break;
    		}
    	}
    
    @Override
    protected String getContent(String[] tokens, int index) {
    	switch(index)
    		{
    		case 36://cds wild
    		case 37://cds mut
    			return this.m_ignore_cdna.getBooleanValue()?null:tokens[index];
    		case 38://prot wild
    		case 39://prot mut
    			return this.m_ignore_protein.getBooleanValue()?null:tokens[index];
    		default:return tokens[index];
    		}
    	}
    
    @Override
    protected List<SettingsModel> getSettingsModel()
    	{
    	List<SettingsModel> L=new ArrayList<SettingsModel>(super.getSettingsModel());
    	L.add(this.m_ignore_cdna);
    	L.add(this.m_ignore_protein);
    	return L;
    	}
	}


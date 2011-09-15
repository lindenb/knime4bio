package fr.inserm.umr915.knime4ngs.nodes.vcf.integragen.loadindel;

import org.knime.core.data.DataColumnSpec;
import org.knime.core.data.DataColumnSpecCreator;
import org.knime.core.data.DataTableSpec;
import org.knime.core.data.def.DoubleCell;
import org.knime.core.data.def.IntCell;
import org.knime.core.data.def.StringCell;

import fr.inserm.umr915.knime4ngs.corelib.knime.ExecuteException;
import fr.inserm.umr915.knime4ngs.nodes.vcf.integragen.LoadIgNodeModel;


/**
 * This is the model implementation of VCFLoader.
 * Loads Integragen SNP file
 *
 * @author Pierre Lindenbaum
 */
public class LoadIgIndelNodeModel extends LoadIgNodeModel
	{
    public LoadIgIndelNodeModel()
    	{
    	}

    @Override
    protected DataTableSpec createVcfDataColumnSpec()
		{
		DataColumnSpec[] allColSpecs = new DataColumnSpec[35];
		 allColSpecs[0] =  new DataColumnSpecCreator("POS", IntCell.TYPE).createSpec();
		 allColSpecs[1] =  new DataColumnSpecCreator("CHROM", StringCell.TYPE).createSpec();
		 allColSpecs[2] =  new DataColumnSpecCreator("SAMPLE", StringCell.TYPE).createSpec();
		 allColSpecs[3] =  new DataColumnSpecCreator("ID", StringCell.TYPE).createSpec();
		 allColSpecs[4] =  new DataColumnSpecCreator("obs.dbsnp", StringCell.TYPE).createSpec();
		 allColSpecs[5] =  new DataColumnSpecCreator("strand.dbsnp", StringCell.TYPE).createSpec();
		 allColSpecs[6] =  new DataColumnSpecCreator("hapmap_ref_other", StringCell.TYPE).createSpec();
		 allColSpecs[7] =  new DataColumnSpecCreator("Freq.HTZ.Exomes", DoubleCell.TYPE).createSpec();
		 allColSpecs[8] =  new DataColumnSpecCreator("Freq.Hom.Exomes", DoubleCell.TYPE).createSpec();
		 allColSpecs[9] =  new DataColumnSpecCreator("Depth", IntCell.TYPE).createSpec();
		 allColSpecs[10] =  new DataColumnSpecCreator("CIGAR", StringCell.TYPE).createSpec();
		 allColSpecs[11] =  new DataColumnSpecCreator("ref_upstream", StringCell.TYPE).createSpec();
		 allColSpecs[12] =  new DataColumnSpecCreator("ref.indel", StringCell.TYPE).createSpec();
		 allColSpecs[13] =  new DataColumnSpecCreator("ref_downstream", StringCell.TYPE).createSpec();
		 allColSpecs[14] =  new DataColumnSpecCreator("Q.indel.", IntCell.TYPE).createSpec();
		 allColSpecs[15] =  new DataColumnSpecCreator("max_gtype", StringCell.TYPE).createSpec();
		 allColSpecs[16] =  new DataColumnSpecCreator("Q.max_gtype.", IntCell.TYPE).createSpec();
		 allColSpecs[17] =  new DataColumnSpecCreator("bp1_reads", IntCell.TYPE).createSpec();
		 allColSpecs[18] =  new DataColumnSpecCreator("ref_reads", IntCell.TYPE).createSpec();
		 allColSpecs[19] =  new DataColumnSpecCreator("indel_reads", IntCell.TYPE).createSpec();
		 allColSpecs[20] =  new DataColumnSpecCreator("other_reads", IntCell.TYPE).createSpec();
		 allColSpecs[21] =  new DataColumnSpecCreator("repeat_unit", StringCell.TYPE).createSpec();
		 allColSpecs[22] =  new DataColumnSpecCreator("ref_repeat_count", IntCell.TYPE).createSpec();
		 allColSpecs[23] =  new DataColumnSpecCreator("indel_repeat_count", IntCell.TYPE).createSpec();
		 allColSpecs[24] =  new DataColumnSpecCreator("Gene.name", StringCell.TYPE).createSpec();
		 allColSpecs[25] =  new DataColumnSpecCreator("Gene.start", IntCell.TYPE).createSpec();
		 allColSpecs[26] =  new DataColumnSpecCreator("Gene.end", IntCell.TYPE).createSpec();
		 allColSpecs[27] =  new DataColumnSpecCreator("Strand", StringCell.TYPE).createSpec();
		 allColSpecs[28] =  new DataColumnSpecCreator("Nbr.exon", IntCell.TYPE).createSpec();
		 allColSpecs[29] =  new DataColumnSpecCreator("refseq", StringCell.TYPE).createSpec();
		 allColSpecs[30] =  new DataColumnSpecCreator("type", StringCell.TYPE).createSpec();
		 allColSpecs[31] =  new DataColumnSpecCreator("type.pos", StringCell.TYPE).createSpec();
		 allColSpecs[32] =  new DataColumnSpecCreator("Intron.start", IntCell.TYPE).createSpec();
		 allColSpecs[33] =  new DataColumnSpecCreator("Intron.end", IntCell.TYPE).createSpec();
		 allColSpecs[34] =  new DataColumnSpecCreator("region.splice", IntCell.TYPE).createSpec();
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
    			if(!tokens[index].equalsIgnoreCase("rs.dbsnp"))
    				{
    				throw new ExecuteException(
    						"In line "+line+"\nfile\""+uri+"\"\n"+
    						"got column["+(index+1)+"]="+tokens[index]+" expected \"rs.name\""
    						);
    				}
    			break;	
    			
    		default:super.validateHeader(line, uri, tokens, index, spec);break;
    		}
    	}
    
    
	}


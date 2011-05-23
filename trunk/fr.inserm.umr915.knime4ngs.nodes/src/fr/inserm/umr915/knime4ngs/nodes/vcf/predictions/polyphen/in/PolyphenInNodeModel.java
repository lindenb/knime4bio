package fr.inserm.umr915.knime4ngs.nodes.vcf.predictions.polyphen.in;


import org.knime.core.data.DataCell;
import org.knime.core.data.def.StringCell;


import fr.inserm.umr915.knime4ngs.corelib.bio.Mutation;
import fr.inserm.umr915.knime4ngs.corelib.bio.Position;
import fr.inserm.umr915.knime4ngs.nodes.vcf.predictions.AbstractPredictionInNodeModel;



/**
 * This is the model implementation of VCFSource.
 * Reads a VCF file
 *
 * @author Pierre Lindenbaum
 */
public class PolyphenInNodeModel extends AbstractPredictionInNodeModel
	{
    /**
     * Constructor for the node model.
     */
    public PolyphenInNodeModel()
    	{
    	}
    
    
    @Override
    protected Mutation makeMutationPrediction(DataCell c)
    	{
    	String cell=StringCell.class.cast(c).getStringValue();
    	int n=cell.indexOf(':');
		if(n==-1) throw new IllegalArgumentException("bad name "+cell);
		String chrom=cell.substring(0,n);
		int n2=cell.indexOf('_',n);
		if(n2==-1) throw new IllegalArgumentException("bad pos "+cell);
		int pos=Integer.parseInt(cell.substring(n+1, n2));//minus 1
		String var= cell.substring(n2+1);
		if(!var.matches("[ATGC][ATGC]"))  throw new IllegalArgumentException("bad var "+cell);

		Position position= new Position(chrom,pos);
		Mutation mutation=new Mutation(
				position,
				var.substring(0,1).toUpperCase(),
				var.substring(1).toUpperCase()
				);
		return mutation;
		}
    
	}


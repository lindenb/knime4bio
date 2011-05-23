package fr.inserm.umr915.knime4ngs.nodes.vcf.predictions.sift.in;

import java.util.regex.Pattern;

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
public class SiftInNodeModel extends AbstractPredictionInNodeModel
	{
    /**
     * Constructor for the node model.
     */
    public SiftInNodeModel()
    	{
    	}
    
    private Pattern comma=Pattern.compile("[,]");
    
    @Override
    protected Mutation makeMutationPrediction(DataCell c)
    	{
    	String cell=StringCell.class.cast(c).getStringValue();
		String variant[]=comma.split(cell);
		if(!variant[0].startsWith("chr")) variant[0]="chr"+variant[0];
		if(variant[3].length()!=3 || variant[3].charAt(1)!='/')
			{
			throw new IllegalArgumentException(cell);
			}
		Position pos= new Position(variant[0],Integer.parseInt(variant[1]));
		Mutation mutation=new Mutation(
				pos,
				variant[3].substring(0,1).toUpperCase(),
				variant[3].substring(2).toUpperCase()
				);
		return mutation;
		}
    
	}


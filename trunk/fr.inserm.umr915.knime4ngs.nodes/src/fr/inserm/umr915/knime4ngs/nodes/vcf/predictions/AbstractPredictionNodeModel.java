package fr.inserm.umr915.knime4ngs.nodes.vcf.predictions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import org.knime.core.node.defaultnodesettings.SettingsModel;
import org.knime.core.node.defaultnodesettings.SettingsModelColumnName;


import fr.inserm.umr915.knime4ngs.nodes.vcf.AbstractVCFNodeModel;

/**
 * 
 * AbstractPredictionOutNodeModel
 *
 */
public abstract class AbstractPredictionNodeModel extends
		AbstractVCFNodeModel
	{
	public final static String DEFAULT_CHROM_COL="CHROM";
	public static final String CHROM_COL_PROPERTY="chrom.col";
	protected final SettingsModelColumnName m_chromCol =
        new SettingsModelColumnName(CHROM_COL_PROPERTY,DEFAULT_CHROM_COL);

	public final static String DEFAULT_POS_COL="POS";
	public static final String POS_COL_PROPERTY="pos.col";
	protected final SettingsModelColumnName m_posCol =
        new SettingsModelColumnName(POS_COL_PROPERTY,DEFAULT_POS_COL);
	
	public final static String DEFAULT_REF_COL="REF";
	public static final String REF_COL_PROPERTY="ref.col";
	protected final SettingsModelColumnName m_refCol =
        new SettingsModelColumnName(REF_COL_PROPERTY,DEFAULT_REF_COL);

	public final static String DEFAULT_ALT_COL="ALT";
	public static final String ALT_COL_PROPERTY="alt.col";
	protected final SettingsModelColumnName m_altCol =
        new SettingsModelColumnName(ALT_COL_PROPERTY,DEFAULT_ALT_COL);
	
	
	
	private Pattern comma=Pattern.compile("[,]");
	public AbstractPredictionNodeModel(int nrInDataPorts, int nrOutDataPorts) {
		super(nrInDataPorts, nrOutDataPorts);
		}

		
	protected Set<String> alts(String refBase,String alts)//TODO fix this for all degenerate nucleotides
		{
		Set<String> altBases=new HashSet<String>();  
		for(String alt:comma.split(alts))
			{
			if(alt.isEmpty()) continue;
			if(refBase.equals("A"))
    			{
    				 if(alt.equals("W")) { alt="T"; }
    			else if(alt.equals("M")) { alt="C"; }
    			else if(alt.equals("R")) { alt="G"; }
    			}
    		else if(refBase.equals("C"))
    			{
    				 if(alt.equals("S")) { alt="G"; }
    			else if(alt.equals("M")) { alt="A"; }
    			else if(alt.equals("Y")) { alt="T"; }
    			}
    		else if(refBase.equals("G"))
    			{
    				 if(alt.equals("S")) { alt="C"; }
    			else if(alt.equals("K")) { alt="T"; }
    			else if(alt.equals("R")) { alt="A"; }
    			}
    		else if(refBase.equals("T"))
    			{
    				 if(alt.equals("W")) { alt="A"; }
    			else if(alt.equals("K")) { alt="G"; }
    			else if(alt.equals("Y")) { alt="C"; }
    			}
			if(refBase!=null && refBase.equals(alt)) continue;
			altBases.add(alt);
			}
		return altBases;
		}
	
	 protected static boolean isATGC(String s)
	 	{
		return s.equals("A")  || s.equals("T") || s.equals("G") || s.equals("C"); 
	 	}
	 

	
    
    
    @Override
    protected List<SettingsModel> getSettingsModel() {
    	List<SettingsModel> a=new ArrayList<SettingsModel>(super.getSettingsModel());
    	a.add(m_chromCol);
    	a.add(m_posCol);
    	a.add(m_refCol);
    	a.add(m_altCol);
    	return a;
    	}
}

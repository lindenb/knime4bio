package fr.inserm.umr915.knime4ngs.nodes.vcf.evs;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.knime.base.data.append.column.AppendedColumnRow;
import org.knime.core.data.DataCell;
import org.knime.core.data.DataColumnSpec;
import org.knime.core.data.DataColumnSpecCreator;
import org.knime.core.data.DataRow;
import org.knime.core.data.DataTableSpec;
import org.knime.core.data.DataType;
import org.knime.core.data.RowIterator;

import org.knime.core.data.def.DoubleCell;
import org.knime.core.data.def.IntCell;
import org.knime.core.data.def.StringCell;
import org.knime.core.node.BufferedDataContainer;
import org.knime.core.node.BufferedDataTable;
import org.knime.core.node.ExecutionContext;
import org.knime.core.node.InvalidSettingsException;
import org.knime.core.node.defaultnodesettings.SettingsModel;
import org.knime.core.node.defaultnodesettings.SettingsModelColumnName;


import edu.washington.gs.evs.webservice.DataQuery;
import edu.washington.gs.evs.webservice.DataQueryService;
import edu.washington.gs.evs.webservice.EvsData;
import edu.washington.gs.evs.webservice.SnpData;
import edu.washington.gs.evs.webservice.SnpFunction;
import fr.inserm.umr915.knime4ngs.nodes.vcf.AbstractVCFNodeModel;


/**
 * EVSNodeModel
 */
public class EVSNodeModel extends AbstractVCFNodeModel
	{

	/** chrom column */
	static final String CHROM_COL_PROPERTY="chrom.col";
	static final String CHROM_COL_DEFAULT="CHROM";
	private final SettingsModelColumnName m_chromColumn = new SettingsModelColumnName(
			CHROM_COL_PROPERTY,
			CHROM_COL_DEFAULT
			);
	
	/** pos column */
	static final String POS_COL_PROPERTY="pos.col";
	static final String POS_COL_DEFAULT="POS";
	private final SettingsModelColumnName m_posColumn = new SettingsModelColumnName(
			POS_COL_PROPERTY,
			POS_COL_DEFAULT
			);
	
    /**
     * Constructor for the node model.
     */
    protected EVSNodeModel()
    	{
        super(1,1);
    	}
    
    private static void printjson(StringBuilder b,Object o)throws Exception
		{
		if(o==null)
			{
			b.append("null");
			}
		else if(o instanceof Number || o.getClass()==Boolean.class)
			{
			b.append(o.toString());
			}
		else if(o.getClass()==String.class)
			{
			String s=o.toString();
			b.append("\"");
			for(int i=0;i< s.length();++i)
				{
				switch(s.charAt(i))
					{
					case '\"': b.append("\\\"");break;
					case '\'': b.append("\\\'");break;
					case '\\': b.append("\\\\");break;
					case '\n': b.append("\\n");break;
					case '\t': b.append("\\t");break;
					default:b.append(s.charAt(i));break;
					}
				}
			b.append("\"");
			}
		else if(o instanceof List)
			{
			@SuppressWarnings("rawtypes")
			List L=(List)o;
			b.append("[");
			for(int i=0;i< L.size();++i)
				{
				if(i>0) b.append(",");
				printjson(b,L.get(i));
				}
			b.append("]");
			}
		else
			{
			boolean first=true;
			b.append("{");
			for(Method method:o.getClass().getMethods())
				{
				String name=method.getName();
				if(name.equals("getClass")) continue;
				if(!name.startsWith("get"))      continue;
				if(method.getParameterTypes().length != 0)   continue;
				if(Void.class.equals(method.getReturnType())) continue;
				if(!first) b.append(",");
				first=false;
				name=name.substring(3);
				printjson(b,name.substring(0, 1).toLowerCase()+name.substring(1));
				b.append(":");
				printjson(b,method.invoke(o));
				}
			b.append("}");
			}
		}
    
    private static String asJson(Object o)throws Exception
    	{
    	StringBuilder b=new StringBuilder();
    	printjson(b, o);
    	return b.toString();
    	}

    @Override
    protected BufferedDataTable[] execute(
    		final BufferedDataTable[] inData,
            final ExecutionContext exec
            ) throws Exception
            {
			BufferedDataContainer container1=null;
			try
		    	{
				DataQueryService service=new DataQueryService();
				DataQuery port=service.getDataQueryPort();
		        // the data table spec of the single output table, 
		        // the table will have three columns:
				BufferedDataTable table1=inData[0];
				DataTableSpec inspec1 = table1.getDataTableSpec();
				int chromCol=findColumnIndex(inspec1,m_chromColumn,StringCell.TYPE);
				int posCol=findColumnIndex(inspec1,m_posColumn,IntCell.TYPE);
				
				DataTableSpec spec=new DataTableSpec(inspec1,createDataTableSpec());
		        container1 = exec.createDataContainer(spec);
		        double total=table1.getRowCount();
		        int nRow=0;
		        RowIterator iter1=null;
		       
		        try {
		        	iter1=table1.iterator();
		     
		        	while(iter1.hasNext())
		        		{
		        		++nRow;
		        		DataRow row=iter1.next();
		        		boolean found=false;
		        		do
			        		{
			        		DataCell cell=row.getCell(chromCol);
			        		if(cell.isMissing()) break;
			        		String chrom=StringCell.class.cast(cell).getStringValue();
			        		cell=row.getCell(posCol);
			        		if(cell.isMissing()) break;
			        		int pos=IntCell.class.cast(cell).getIntValue();
			        		
			        		EvsData data=port.getEvsData(chrom+":"+pos+"-"+(pos+1));
			        		if(data==null) break;
			        		if(data.getStart()!=pos) break;
			        		for(SnpData snp: data.getSnpList())
			        			{
			        			DataCell appendcells[]=new DataCell[25];
			        			appendcells[0]=(snp.getPositionString()==null?DataType.getMissingCell():new StringCell(snp.getPositionString()));
			        			appendcells[1]=new IntCell(snp.getChrPosition());
			        			appendcells[2]=(snp.getAlleles()==null?DataType.getMissingCell():new StringCell(snp.getAlleles()));
			        			appendcells[3]=(snp.getUaAlleleCounts()==null?DataType.getMissingCell():new StringCell(snp.getUaAlleleCounts()));
			        			appendcells[4]=(snp.getAaAlleleCounts()==null?DataType.getMissingCell():new StringCell(snp.getAaAlleleCounts()));
			        			appendcells[5]=(snp.getTotalAlleleCounts()==null?DataType.getMissingCell():new StringCell(snp.getTotalAlleleCounts()));
			        			appendcells[6]=(snp.getUaAlleleAndCount()==null?DataType.getMissingCell():new StringCell(snp.getUaAlleleAndCount()));
			        			appendcells[7]=(snp.getAaAlleleAndCount()==null?DataType.getMissingCell():new StringCell(snp.getAaAlleleAndCount()));
			        			appendcells[8]=(snp.getTotalAlleleAndCount()==null?DataType.getMissingCell():new StringCell(snp.getTotalAlleleAndCount()));
			        			appendcells[9]=new DoubleCell(snp.getUaMAF());
			        			appendcells[10]=new DoubleCell(snp.getAaMAF());
			        			appendcells[11]=new DoubleCell(snp.getTotalMAF());
			        			appendcells[12]= new IntCell(snp.getAvgSampleReadDepth());
			        			appendcells[13]=(snp.getGeneList()==null?DataType.getMissingCell():new StringCell(snp.getGeneList()));
			        			
			        			SnpFunction func=snp.getSnpFunction();
			        			if(func==null)
			        				{
			        				appendcells[14]=DataType.getMissingCell();
			        				}
			        			else
			        				{
			        				appendcells[14]=new StringCell(asJson(func));
			        				}
			        			appendcells[15]=(snp.getConservationScore()==null?DataType.getMissingCell():new StringCell(snp.getConservationScore()));
			        			appendcells[16]=(snp.getConservationScoreGERP()==null?DataType.getMissingCell():new StringCell(snp.getConservationScoreGERP()));
			        			appendcells[17]=(snp.getRefAllele()==null?DataType.getMissingCell():new StringCell(snp.getRefAllele()));
			        			appendcells[18]=(snp.getAltAlleles()==null?DataType.getMissingCell():new StringCell(snp.getAltAlleles()));
			        			appendcells[19]=(snp.getAncestralAllele()==null?DataType.getMissingCell():new StringCell(snp.getAncestralAllele()));
			        			appendcells[20]=(snp.getChromosome()==null?DataType.getMissingCell():new StringCell(snp.getChromosome()));
			        			appendcells[21]=(snp.getHasAtLeastOneAccession()==null?DataType.getMissingCell():new StringCell(snp.getHasAtLeastOneAccession()));
			        			appendcells[22]=(snp.getRsIds()==null?DataType.getMissingCell():new StringCell(snp.getRsIds()));
			        			appendcells[23]=(snp.getFilters()==null?DataType.getMissingCell():new StringCell(snp.getFilters()));
			        			appendcells[24]=(snp.getClinicalLink()==null?DataType.getMissingCell():new StringCell(snp.getClinicalLink()));

			        			
			        			container1.addRowToTable(new AppendedColumnRow(row,appendcells));
			        			found=true;
			        			}
			        		
			        		} while(false);
		        		if(!found)
		        			{
		        			DataCell appendcells[]=new DataCell[25];
		        			for(int i=0;i< appendcells.length;++i) appendcells[i]=DataType.getMissingCell();
		        			container1.addRowToTable(new AppendedColumnRow(row,appendcells));
		        			}
		        		exec.checkCanceled();
		            	exec.setProgress(nRow/total,"Calling EVS....");
		        		}
		        	
					} 
		        catch (Exception e)
					{
		        	e.printStackTrace();
					throw e;
					}
				finally
					{
					safeClose(iter1);
					}
		        
				// once we are done, we close the container and return its table
				safeClose(container1);
				
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
			safeClose(container1);
			}
       }
 
    
    private DataTableSpec createDataTableSpec() throws InvalidSettingsException
    	{
    	DataColumnSpec a[]=new DataColumnSpec[25];
    	a[0]=new DataColumnSpecCreator("evs.PositionString", StringCell.TYPE).createSpec();
    	a[1]=new DataColumnSpecCreator("evs.ChrPosition", IntCell.TYPE).createSpec();
    	a[2]=new DataColumnSpecCreator("evs.Alleles", StringCell.TYPE).createSpec();
    	a[3]=new DataColumnSpecCreator("evs.UaAlleleCounts", StringCell.TYPE).createSpec();
    	a[4]=new DataColumnSpecCreator("evs.AaAlleleCounts", StringCell.TYPE).createSpec();
    	a[5]=new DataColumnSpecCreator("evs.TotalAlleleCounts", StringCell.TYPE).createSpec();
    	a[6]=new DataColumnSpecCreator("evs.UaAlleleAndCount", StringCell.TYPE).createSpec();
    	a[7]=new DataColumnSpecCreator("evs.AaAlleleAndCount", StringCell.TYPE).createSpec();
    	a[8]=new DataColumnSpecCreator("evs.TotalAlleleAndCount", StringCell.TYPE).createSpec();
    	a[9]=new DataColumnSpecCreator("evs.UaMAF", DoubleCell.TYPE).createSpec();
    	a[10]=new DataColumnSpecCreator("evs.AaMAF", DoubleCell.TYPE).createSpec();
    	a[11]=new DataColumnSpecCreator("evs.TotalMAF", DoubleCell.TYPE).createSpec();
    	a[12]=new DataColumnSpecCreator("evs.AvgSampleReadDepth", IntCell.TYPE).createSpec();
    	a[13]=new DataColumnSpecCreator("evs.GeneList", StringCell.TYPE).createSpec();
    	a[14]=new DataColumnSpecCreator("evs.SnpFunction", StringCell.TYPE).createSpec();
    	a[15]=new DataColumnSpecCreator("evs.ConservationScore", StringCell.TYPE).createSpec();
    	a[16]=new DataColumnSpecCreator("evs.ConservationScoreGERP", StringCell.TYPE).createSpec();
    	a[17]=new DataColumnSpecCreator("evs.RefAllele", StringCell.TYPE).createSpec();
    	a[18]=new DataColumnSpecCreator("evs.AltAlleles", StringCell.TYPE).createSpec();
    	a[19]=new DataColumnSpecCreator("evs.AncestralAllele", StringCell.TYPE).createSpec();
    	a[20]=new DataColumnSpecCreator("evs.Chromosome", StringCell.TYPE).createSpec();
    	a[21]=new DataColumnSpecCreator("evs.HasAtLeastOneAccession", StringCell.TYPE).createSpec();
    	a[22]=new DataColumnSpecCreator("evs.RsIds", StringCell.TYPE).createSpec();
    	a[23]=new DataColumnSpecCreator("evs.Filters", StringCell.TYPE).createSpec();
    	a[24]=new DataColumnSpecCreator("evs.ClinicalLink", StringCell.TYPE).createSpec();
    	return new DataTableSpec(a);
    	}
    
    @Override
    protected DataTableSpec[] configure(DataTableSpec[] inSpecs)
    		throws InvalidSettingsException {
    	if(inSpecs==null || inSpecs.length!=1)
    		{
    		throw new InvalidSettingsException("Expected one tables");
    		}
    	findColumnIndex(inSpecs[0],m_chromColumn,StringCell.TYPE);
    	findColumnIndex(inSpecs[0],m_posColumn,IntCell.TYPE);
    	return new DataTableSpec[]{new DataTableSpec(inSpecs[0],createDataTableSpec())};
    	}
    
    @Override
    protected List<SettingsModel> getSettingsModel() {
    	List<SettingsModel> L=new ArrayList<SettingsModel>( super.getSettingsModel());
    	L.add(this.m_chromColumn);
    	L.add(this.m_posColumn);
    	return L;
    	}
	}


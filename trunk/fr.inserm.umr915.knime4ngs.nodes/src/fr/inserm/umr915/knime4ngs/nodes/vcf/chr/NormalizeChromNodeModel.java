package fr.inserm.umr915.knime4ngs.nodes.vcf.chr;


import java.util.ArrayList;
import java.util.List;


import org.knime.base.data.replace.ReplacedColumnsDataRow;
import org.knime.base.data.replace.ReplacedColumnsTable;
import org.knime.core.data.DataCell;
import org.knime.core.data.DataColumnSpecCreator;
import org.knime.core.data.DataRow;
import org.knime.core.data.DataTableSpec;
import org.knime.core.data.DataType;
import org.knime.core.data.container.CloseableRowIterator;
import org.knime.core.data.def.IntCell;
import org.knime.core.data.def.StringCell;
import org.knime.core.node.BufferedDataContainer;
import org.knime.core.node.BufferedDataTable;
import org.knime.core.node.ExecutionContext;
import org.knime.core.node.InvalidSettingsException;
import org.knime.core.node.defaultnodesettings.SettingsModel;
import org.knime.core.node.defaultnodesettings.SettingsModelBoolean;
import org.knime.core.node.defaultnodesettings.SettingsModelColumnName;

import fr.inserm.umr915.knime4ngs.corelib.knime.ExecuteException;
import fr.inserm.umr915.knime4ngs.nodes.vcf.AbstractVCFNodeModel;




/**

 * @author Pierre Lindenbaum
 */
public class NormalizeChromNodeModel extends AbstractVCFNodeModel
	{
	static final String CHROM_COL_PROPERTY="chrom.col";
	static final String CHROM_COL_DEFAULT="CHROM";
	private final SettingsModelColumnName m_chromCol =
        new SettingsModelColumnName(CHROM_COL_PROPERTY,CHROM_COL_DEFAULT);
	
	
	static final String CHR_PREFIX_PROPERTY="chr.prefix";
	static final boolean CHR_PREFIX_DEFAULT=true;
	private final SettingsModelBoolean m_chrPrefix =
        new SettingsModelBoolean(CHR_PREFIX_PROPERTY,CHR_PREFIX_DEFAULT);

    /**
     * Constructor for the node model.
     */
    public NormalizeChromNodeModel()
    	{
        super(1,1);
    	}
   
    
    
    @Override
    protected BufferedDataTable[] execute(
    		final BufferedDataTable[] inData,
            final ExecutionContext exec
            ) throws Exception
            {
			BufferedDataContainer container1=null;
			BufferedDataTable inTable=inData[0];
			DataTableSpec inSpec= inTable.getDataTableSpec();
			int chromCol= inSpec.findColumnIndex(m_chromCol.getColumnName());
			if(chromCol==-1) throw new ExecuteException("Cannot find column for chrom");
			if(!(inSpec.getColumnSpec(chromCol).getType().equals(IntCell.TYPE) ||
				 inSpec.getColumnSpec(chromCol).getType().equals(StringCell.TYPE)
			   ))
				{
				throw new ExecuteException("Bad type for chrom:"+inSpec.getColumnSpec(chromCol).getType());
				}
			
			DataTableSpec spec2= ReplacedColumnsTable.createTableSpec(inSpec,
					new DataColumnSpecCreator(inSpec.getColumnSpec(chromCol).getName(),StringCell.TYPE).createSpec(),
					chromCol);
			
			try
		    	{
		       
		        container1 = exec.createDataContainer(spec2);

		        double total=inTable.getRowCount();
		        int nRow=0;
		        CloseableRowIterator iter=null;
		        try {
		        	iter=inTable.iterator();
		        	while(iter.hasNext())
		        		{
		        		++nRow;
		        		DataRow row=iter.next();
		        		DataCell cell= row.getCell(chromCol);
		        		if(!cell.isMissing())
		        			{
		        			String c;
		        			if(cell.getType().equals(IntCell.TYPE))
		        				{
		        				int n=IntCell.class.cast(cell).getIntValue();
		        				if(n<0) throw new ExecuteException("Bad chromosome ID: "+cell.toString());
		        				c=String.valueOf(n);
		        				}
		        			else
		        				{
		        				c=StringCell.class.cast(cell).getStringValue().trim();
		        				}
		        			
		        			if( c.isEmpty() ||
		        				c.equalsIgnoreCase("NULL")  ||
		        				c.equalsIgnoreCase("NIL")  ||
		        				c.equalsIgnoreCase("N/A")  ||
		        				c.equalsIgnoreCase("NA")  ||
		        				c.equalsIgnoreCase(".")  ||
		        				c.equals("?") ||
		        				c.equals("0"))
		        				{
		        				cell=DataType.getMissingCell();
		        				}
		        			}
		        		if(!cell.isMissing())
		        			{
		        			String c;
		        			if(cell.getType().equals(IntCell.TYPE))
		        				{
		        				int n=IntCell.class.cast(cell).getIntValue();
		        				if(n<0) throw new ExecuteException("Bad chromosome ID: "+cell.toString());
		        				c=String.valueOf(n);
		        				}
		        			else
		        				{
		        				c=StringCell.class.cast(cell).getStringValue().trim();
		        				}
		        			
		        			c=c.trim().toLowerCase();
		        			if(c.startsWith("chrom")) c=c.substring(5).trim();
		        			else if(c.startsWith("k")) c=c.substring(1).trim();
		        			else if(c.startsWith("chr")) c=c.substring(3).trim();
		        			while(c.startsWith("0")) c=c.substring(1);
		        			
		        			if(m_chrPrefix.getBooleanValue())
		        				{
		        				c="chr"+c;
		        				if(c.equals("chrx") || c.equals("chr23")) { c="chrX";}
		        				else if(c.equals("chry") || c.equals("chr24")) { c="chrY";}
		        				else if(c.equals("chrm") || c.equals("chrmt") || c.equals("chr25")) { c="chrM";}
		        				
		        				}
		        			else
		        				{
		        				if(c.equals("x") || c.equals("23")) { c="X";}
		        				else if(c.equals("y") || c.equals("24")) { c="Y";}
		        				else if(c.equals("m") || c.equals("mt") || c.equals("25")) { c="MT";}
		        				}
		        			if(c.isEmpty())
		        				{
		        				cell=DataType.getMissingCell();
		        				}
		        			else
		        				{
		        				cell=new StringCell(c);
		        				}
		        			}
		        		
						row=new ReplacedColumnsDataRow(row,cell,chromCol);
						container1.addRowToTable(row);
		        		exec.checkCanceled();
		            	exec.setProgress(nRow/total,"Normalize chrom...");
		        		}
					} 
		        catch (Exception e)
					{
					throw e;
					}
				finally
					{
					safeClose(iter);
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
    	int chromCol= in.findColumnIndex(m_chromCol.getColumnName());
		if(chromCol==-1) throw new InvalidSettingsException("Cannot find column for chrom");
		if(!(in.getColumnSpec(chromCol).getType().equals(IntCell.TYPE) ||
				in.getColumnSpec(chromCol).getType().equals(StringCell.TYPE)
		   ))
			{
			throw new InvalidSettingsException("Bad type for chrom:"+in.getColumnSpec(chromCol).getType());
			}
		DataTableSpec spec2= ReplacedColumnsTable.createTableSpec(in,
				new DataColumnSpecCreator(in.getColumnSpec(chromCol).getName(),StringCell.TYPE).createSpec(),
				chromCol);
    	
    	return new DataTableSpec[]{spec2};
    	}
    
    @Override
    protected List<SettingsModel> getSettingsModel() {
    	List<SettingsModel> L=new ArrayList<SettingsModel>( super.getSettingsModel());
    	L.add(this.m_chromCol);
    	L.add(this.m_chrPrefix);
    	return L;
    	}
    
    
    
	}


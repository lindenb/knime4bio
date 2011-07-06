package fr.inserm.umr915.knime4ngs.nodes.vcf.go;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import org.knime.core.data.DataCell;
import org.knime.core.data.DataColumnSpec;
import org.knime.core.data.DataColumnSpecCreator;
import org.knime.core.data.DataTableSpec;
import org.knime.core.data.DataType;
import org.knime.core.data.RowKey;
import org.knime.core.data.def.DefaultRow;
import org.knime.core.data.def.IntCell;
import org.knime.core.data.def.StringCell;
import org.knime.core.node.BufferedDataContainer;
import org.knime.core.node.BufferedDataTable;
import org.knime.core.node.ExecutionContext;
import org.knime.core.node.InvalidSettingsException;
import org.knime.core.node.defaultnodesettings.SettingsModel;
import org.knime.core.node.defaultnodesettings.SettingsModelInteger;
import org.knime.core.node.defaultnodesettings.SettingsModelString;
import fr.inserm.umr915.knime4ngs.nodes.vcf.AbstractVCFNodeModel;



/**

 * @author Pierre Lindenbaum
 */
public class GoNodeModel extends AbstractVCFNodeModel
	{
	static final String GENONTOLOGY_PROPERTY="go.terms";
	static final String GENONTOLOGY_DEFAULT="GO:0007507";
	private final SettingsModelString m_goTerms =
        new SettingsModelString(GENONTOLOGY_PROPERTY,GENONTOLOGY_DEFAULT);

	static final String TAXON_PROPERTY="taxon";
	static final int TAXON_DEFAULT=9606;
	private final SettingsModelInteger m_taxon =
        new SettingsModelInteger(TAXON_PROPERTY,TAXON_DEFAULT);
	
	
    /**
     * Constructor for the node model.
     */
    public GoNodeModel()
    	{
        super(0,1);
    	}
   
    protected DataTableSpec createDataTableSpec()
    	{
    	DataColumnSpec cols[]=new DataColumnSpec[]{
    			new DataColumnSpecCreator("DB", StringCell.TYPE).createSpec(),
    			new DataColumnSpecCreator("ID", StringCell.TYPE).createSpec(),
    			new DataColumnSpecCreator("Splice", StringCell.TYPE).createSpec(),
    			new DataColumnSpecCreator("Symbol", StringCell.TYPE).createSpec(),
    			new DataColumnSpecCreator("Taxon", StringCell.TYPE).createSpec(),
    			new DataColumnSpecCreator("Qualifier", StringCell.TYPE).createSpec(),
    			new DataColumnSpecCreator("GO ID", StringCell.TYPE).createSpec(),
    			new DataColumnSpecCreator("GO Name", StringCell.TYPE).createSpec(),
    			new DataColumnSpecCreator("Reference", StringCell.TYPE).createSpec(),
    			new DataColumnSpecCreator("Evidence", StringCell.TYPE).createSpec(),
    			new DataColumnSpecCreator("With", StringCell.TYPE).createSpec(),
    			new DataColumnSpecCreator("Aspect", StringCell.TYPE).createSpec(),
    			new DataColumnSpecCreator("Date", StringCell.TYPE).createSpec(),
    			new DataColumnSpecCreator("Source", StringCell.TYPE).createSpec()
    			};
    	return new DataTableSpec(cols);
    	}
    
    
    @Override
    protected BufferedDataTable[] execute(
    		final BufferedDataTable[] inData,
            final ExecutionContext exec
            ) throws Exception
            {
			BufferedDataContainer container1=null;
		
			
			
			BufferedReader in=null;
			try
		    	{
				DataTableSpec spec=createDataTableSpec();
		        container1 = exec.createDataContainer(spec);
		       
		    
		       
				Pattern tab=Pattern.compile("[\t]");
				String line;
				String goTerm=this.m_goTerms.getStringValue();
				
				String uri="http://www.ebi.ac.uk/QuickGO/GAnnotation?tax=" +
					this.m_taxon.getIntValue()+
					"&relType=IP=&goid="+
					URLEncoder.encode(goTerm,"UTF-8")+
					"&format=tsv";
					
				in=new BufferedReader(new InputStreamReader(new URL(uri).openStream()));
				line=in.readLine(); //skip header
				if(line==null)
					{
					throw new IOException("Cannot get header");
					}
				
				String tokens[]=tab.split(line);
				if(tokens.length!=spec.getNumColumns())
					{
					throw new IOException("Expected "+spec.getNumColumns()+" columns but found "+tokens.length);
					}
				for(int i=0;i< tokens.length;++i)
					{
					if(!tokens[i].equalsIgnoreCase(spec.getColumnSpec(i).getName()))
						{
						throw new IOException("columnn("+(i+1)+" expected "+spec.getColumnSpec(i).getName()+" but got "+tokens[i]);
						}
					}
				int outIndex=0;
				
				while((line=in.readLine())!=null)
					{
					exec.checkCanceled();
					tokens=tab.split(line);
					DataCell cells[]=new DataCell[spec.getNumColumns()];
					for(int i=0;i< spec.getNumColumns();++i)
						{
						if(tokens.length<=i || tokens[i].trim().isEmpty())
							{
							cells[i]=DataType.getMissingCell();
							}
						else if(spec.getColumnSpec(i).getType().equals(IntCell.TYPE))
							{
							cells[i]=new IntCell(Integer.parseInt(tokens[i]));
							}
						else
							{
							cells[i]=new StringCell(tokens[i]);
							}
						}
					container1.addRowToTable(new DefaultRow(RowKey.createRowKey(++outIndex), cells));
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
			safeClose(in);
			}
       }
    
    
    @Override
    protected DataTableSpec[] configure(DataTableSpec[] inSpecs)
    		throws InvalidSettingsException
    	{
    	return new DataTableSpec[]{createDataTableSpec()};
    	}
    
    @Override
    protected List<SettingsModel> getSettingsModel() {
    	List<SettingsModel> L=new ArrayList<SettingsModel>( super.getSettingsModel());
    	L.add(this.m_goTerms);
    	L.add(this.m_taxon);
    	return L;
    	}
    
    
    
	}


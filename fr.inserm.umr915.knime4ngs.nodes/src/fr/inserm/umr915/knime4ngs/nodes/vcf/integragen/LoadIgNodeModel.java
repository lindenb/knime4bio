package fr.inserm.umr915.knime4ngs.nodes.vcf.integragen;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.knime.core.data.DataCell;
import org.knime.core.data.DataRow;
import org.knime.core.data.DataTableSpec;
import org.knime.core.data.DataType;
import org.knime.core.data.RowKey;
import org.knime.core.data.container.CloseableRowIterator;
import org.knime.core.data.def.DefaultRow;
import org.knime.core.data.def.DoubleCell;
import org.knime.core.data.def.IntCell;
import org.knime.core.data.def.StringCell;
import org.knime.core.node.BufferedDataContainer;
import org.knime.core.node.BufferedDataTable;
import org.knime.core.node.ExecutionContext;
import org.knime.core.node.InvalidSettingsException;
import org.knime.core.node.defaultnodesettings.SettingsModel;
import org.knime.core.node.defaultnodesettings.SettingsModelColumnName;
import fr.inserm.umr915.knime4ngs.corelib.knime.ExecuteException;
import fr.inserm.umr915.knime4ngs.nodes.vcf.AbstractVCFNodeModel;


/**
 * This is the model implementation of VCFLoader.
 * Loads a VCF File
 *
 * @author Pierre Lindenbaum
 */
public abstract class LoadIgNodeModel extends AbstractVCFNodeModel
	{
	static final String FILEPATH_DEFAULT="Path";
	static final String FILEPATH_PROPERTY="file.ath";
	private final SettingsModelColumnName m_pathColumn =  new SettingsModelColumnName( FILEPATH_PROPERTY,FILEPATH_DEFAULT);
	
	
    protected LoadIgNodeModel()
    	{
        super(1,1);
    	}

    
    protected abstract DataTableSpec createVcfDataColumnSpec();
    
    /* give a chance to modify the content */
    protected String getContent(String tokens[],int index)
    	{
    	return tokens[index];
    	}
    /* give a chance to accept the header */
    protected void validateHeader(
    		String line,
    		String uri,
    		String tokens[],int index,DataTableSpec spec
    		)  throws ExecuteException
    	{
    	if(!tokens[index].equalsIgnoreCase(spec.getColumnSpec(index).getName()))
			{
			throw new ExecuteException(
				"In line "+line+"\nfile\""+uri+"\"\n"+
				"not expected column["+(index+1)+"]="+tokens[index]+" expected "+
				spec.getColumnSpec(index).getName()
				);
			}
    	}
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected BufferedDataTable[] execute(final BufferedDataTable[] inData,
            final ExecutionContext exec) throws Exception
        {
    	if(inData==null || inData.length!=1) throw new IllegalArgumentException("bad input. Expected one node");
    	BufferedReader reader=null;
    	Pattern tab=Pattern.compile("[\t]");
    	BufferedDataContainer container1=null;
    	int nRow0=0;
    	int nRows=0;
    	int nLine=0;
    	int columnPath=findColumnIndex(inData[0].getDataTableSpec(),
    			m_pathColumn, StringCell.TYPE);
    	CloseableRowIterator iter=null;
    	try
	    	{
    		float total=inData[0].getRowCount();
    		DataTableSpec spec1=createVcfDataColumnSpec();
    		container1 = exec.createDataContainer(spec1);
    		iter=inData[0].iterator();
    		
    		while(iter.hasNext())
	    		{
    			DataRow row=iter.next();
    			++nRow0;
    			exec.setProgress(nRow0/total,"Rows: "+nRows);
    			if(row.getCell(columnPath).isMissing()) continue;
	    		String uri=StringCell.class.cast(row.getCell(columnPath)).getStringValue();
	    		if(uri.isEmpty()) continue;
				reader=openReader(uri);
				String line;
				boolean firstLine=true;
				while((line=reader.readLine())!=null)
					{
					nLine++;
					String tokens[]=tab.split(line);
					
					if(firstLine)
						{
						if(tokens.length< spec1.getNumColumns())
							{
							throw new ExecuteException("bad number of tokens in header.");
							}
						for(int i=0;i< tokens.length;++i)
							{
							validateHeader(line,uri,tokens,i,spec1);
							}
						firstLine=false;
						continue;
						}
					DataCell cells[]=new DataCell[spec1.getNumColumns()];
					for(int i=0;i< cells.length;++i)
						{
						cells[i]=DataType.getMissingCell();
						if(i>=tokens.length) continue;
						String content=getContent(tokens,i);
						if(content==null || content.trim().isEmpty() || content.equals("!N/A") || content.equalsIgnoreCase("null")) continue;
						if(spec1.getColumnSpec(i).getType()==StringCell.TYPE)
							{
							cells[i]=new StringCell(content);
							}
						else if(spec1.getColumnSpec(i).getType()==IntCell.TYPE)
							{
							try
								{
								cells[i]=new IntCell(Integer.parseInt(content));
								}
							catch(NumberFormatException err)
								{
								throw new ExecuteException(
										"In line "+nLine+" "+line+" \""+uri+"\" column["+(i+1)+
										"] Cannot convert  "+content+" to integer "
										);
								}
							}
						else if(spec1.getColumnSpec(i).getType()==DoubleCell.TYPE)
							{
							try
								{
								cells[i]=new DoubleCell(Double.parseDouble(content));
								}
							catch(NumberFormatException err)
								{
								throw new ExecuteException(
										"In line "+nLine+" "+line+" \""+uri+"\" column["+(i+1)+
										"] Cannot convert  "+content+" to double "
										);
								}
							}
						else
							{
							throw new ExecuteException("boum"+spec1.getColumnSpec(i).getType());
							}
						}
					
					
			        ++nRows;
			        container1.addRowToTable(new DefaultRow(
			        		RowKey.createRowKey(nRows),
			        		cells));
			        
		            // check if the execution monitor was canceled
			        if(nRows%10000==0)
			        	{
			        	exec.checkCanceled();
			        	}
					}
				reader.close();
				reader=null;
	    		}
	    		
	    	
	    	safeClose(container1);

	    	BufferedDataTable out1 = container1.getTable();
	        container1=null;

	        return new BufferedDataTable[]{out1};
	    	}
    	catch (Exception e)
	    	{
			e.printStackTrace();
			throw e;
			}
    	finally
    		{
    		safeClose(iter);
    		safeClose(container1);
	    	safeClose(reader);
    		}
    	}

    /**
     * {@inheritDoc}
     */
    @Override
    protected DataTableSpec[] configure(final DataTableSpec[] inSpecs)
            throws InvalidSettingsException
        {

        if(inSpecs==null || inSpecs.length!=1)
        	{
        	throw new InvalidSettingsException("Expect one input.");
        	}
        findColumnIndex(inSpecs[0], m_pathColumn, StringCell.TYPE);
    	return new DataTableSpec[]{
    		createVcfDataColumnSpec()
    		};
    	}

    @Override
    protected List<SettingsModel> getSettingsModel()
    	{
    	List<SettingsModel> L=new ArrayList<SettingsModel>(super.getSettingsModel());
    	L.add(this.m_pathColumn);
    	return L;
    	}

	}


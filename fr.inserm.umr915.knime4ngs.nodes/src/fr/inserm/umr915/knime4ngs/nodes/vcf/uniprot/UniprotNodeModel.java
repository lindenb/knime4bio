package fr.inserm.umr915.knime4ngs.nodes.vcf.uniprot;


import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.knime.base.data.append.column.AppendedColumnRow;
import org.knime.core.data.DataCell;
import org.knime.core.data.DataColumnSpec;
import org.knime.core.data.DataColumnSpecCreator;
import org.knime.core.data.DataRow;
import org.knime.core.data.DataTableSpec;
import org.knime.core.data.DataType;
import org.knime.core.data.RowKey;
import org.knime.core.data.container.CloseableRowIterator;
import org.knime.core.data.def.IntCell;
import org.knime.core.data.def.StringCell;
import org.knime.core.node.BufferedDataContainer;
import org.knime.core.node.BufferedDataTable;
import org.knime.core.node.ExecutionContext;
import org.knime.core.node.InvalidSettingsException;
import org.knime.core.node.defaultnodesettings.SettingsModel;
import org.knime.core.node.defaultnodesettings.SettingsModelColumnName;
import fr.inserm.umr915.knime4ngs.corelib.bio.uniprot.Entry;
import fr.inserm.umr915.knime4ngs.corelib.bio.uniprot.FeatureType;
import fr.inserm.umr915.knime4ngs.corelib.bio.uniprot.LocationType;
import fr.inserm.umr915.knime4ngs.corelib.bio.uniprot.PositionType;
import fr.inserm.umr915.knime4ngs.corelib.bio.uniprot.Uniprot;
import fr.inserm.umr915.knime4ngs.nodes.vcf.AbstractVCFNodeModel;





public class UniprotNodeModel extends AbstractVCFNodeModel
	{
	static final String ACC_PROPERTY="uniprot.acc";
	static final String ACC_DEFAULT="Uniprot.acn";
	private SettingsModelColumnName m_accCol =new SettingsModelColumnName(
			UniprotNodeModel.ACC_PROPERTY,
			UniprotNodeModel.ACC_DEFAULT
			);
	
	static final String POS_PROPERTY="uniprot.pos";
	static final String POS_DEFAULT="aa.pos";
	private SettingsModelColumnName m_posCol =new SettingsModelColumnName(
			UniprotNodeModel.POS_PROPERTY,
			UniprotNodeModel.POS_DEFAULT
			);
	private Unmarshaller unmarshaller=null;
    /**
     * 
     */
    protected UniprotNodeModel()
    	{
        super(1,1);
    	}
    
    
    private DataTableSpec createDataTableSpec()
    	{
    	DataColumnSpec cols[]=new DataColumnSpec[]{
    		new DataColumnSpecCreator("uniprot.type", StringCell.TYPE).createSpec(),
    		new DataColumnSpecCreator("uniprot.description", StringCell.TYPE).createSpec(),
    		};
    	return new DataTableSpec(cols);
    	}
    
    private Entry fetch(String acn) throws IOException,JAXBException
    	{
    	if(acn==null || acn.isEmpty()) return null;
    	URL url=new URL("http://www.uniprot.org/uniprot/"+
    			URLEncoder.encode(acn, "UTF-8") +
    			".xml");
    	try
    		{
    		Uniprot uniprot=(Uniprot)this.unmarshaller.unmarshal(url);
    		if(uniprot.getEntry().isEmpty()) return null;
    		return uniprot.getEntry().get(0);
    		}
    	catch(Exception err)
    		{
    		System.err.println("Cannot parse: "+url+" "+err.getMessage());
    		return null;
    		}
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
				JAXBContext jc = JAXBContext.newInstance(Uniprot.class);
	            this.unmarshaller = jc.createUnmarshaller();
	            this.unmarshaller.setSchema(null);

				
				
		        // the data table spec of the single output table, 
		        // the table will have three columns:
				BufferedDataTable inTable=inData[0];
		       
				DataTableSpec inDataTableSpec = inTable.getDataTableSpec();
				int accCol= findColumnIndex(inDataTableSpec,m_accCol,StringCell.TYPE);
				int posCol= findColumnIndex(inDataTableSpec,m_posCol,IntCell.TYPE);
		        container1 = exec.createDataContainer(
		        		new DataTableSpec(
		        		inDataTableSpec,
		        		createDataTableSpec())
		        		);
		       
		        int outIndex=0;
		        double total=inTable.getRowCount();
		        int nRow=0;
		        CloseableRowIterator iter=null;
		        Entry currentEntry=null;
		        try {
		        	iter=inTable.iterator();
		        	while(iter.hasNext())
		        		{
		        		++nRow;
		        		DataRow row=iter.next();
		        		boolean ok=false;
		        		if(!(row.getCell(accCol).isMissing() ||
		        			 StringCell.class.cast(row.getCell(accCol)).getStringValue().trim().isEmpty() ||
		        			 row.getCell(posCol).isMissing() ||
		        			 IntCell.class.cast(row.getCell(posCol)).getIntValue()<1
		        			))
	        				{
		        			String acn=StringCell.class.cast(row.getCell(accCol)).getStringValue();
		        			if(currentEntry!=null)
		        				{
		        				boolean foundAcn=false;
		        				for(String s:currentEntry.getAccession())
		        					{
		        					if(s.equalsIgnoreCase(acn))
		        						{
		        						foundAcn=true;
		        						break;
		        						}
		        					}
		        				if(!foundAcn) currentEntry=null;
		        				}
		        			if(currentEntry==null)
		        				{
		        				currentEntry=fetch(acn);
		        				}
		        			if(currentEntry!=null)
		        				{
		        				int position=IntCell.class.cast(row.getCell(posCol)).getIntValue();
		        				for(FeatureType featureType:currentEntry.getFeature())
		        					{
		        					boolean echo=false;
		        	                LocationType locType=featureType.getLocation();
		        	                if(locType==null) continue;
		        	                PositionType begin=locType.getBegin();
		        	                PositionType end=locType.getEnd();
		        	                PositionType pos=locType.getPosition();
		        	                if(pos!=null && pos.getPosition().intValue()==position)
		        	                    {
		        	                	echo=true;
		        	                    }
		        	                else if(end!=null && begin!=null)
		        	                    {
		        	                    int n1=begin.getPosition().intValue();
		        	                    int n2=end.getPosition().intValue();
		        	                    if(n1>=position && position<=n2)
		        	                    	{
		        	                    	echo=true;
		        	                    	}
		        	                    }
		        	                if(echo)
		        	                	{
		        	                	ok=true;
		        	                	DataCell cells[]=new DataCell[2];
		        	                	String s=featureType.getType();
		        	                	if(s==null || s.trim().isEmpty())
		        	                		{
		        	                		cells[0]= DataType.getMissingCell();
		        	                		}
		        	                	else
		        	                		{
		        	                		cells[0]=  new StringCell(s);
		        	                		}
		        	                	s=featureType.getDescription();
		        	                	if(s==null || s.trim().isEmpty())
		        	                		{
		        	                		cells[1]= DataType.getMissingCell();
		        	                		}
		        	                	else
		        	                		{
		        	                		cells[1]=  new StringCell(s);
		        	                		}
            	                            
		        	                	container1.addRowToTable(new AppendedColumnRow(RowKey.createRowKey(++outIndex), row, cells));
		        	                	}
		        					}
		        				}
	        				}
		        		
		        		if(!ok)
		        			{
		        			DataCell empty[]=new DataCell[]
	                              {
		        				  DataType.getMissingCell(),
		        				  DataType.getMissingCell() 
	                              };
		        			container1.addRowToTable(new AppendedColumnRow(RowKey.createRowKey(++outIndex), row, empty));
		        			}
						
		        		
					
						exec.checkCanceled();
		            	exec.setProgress(nRow/total,"uniprot....");
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
			this.unmarshaller=null;
			}
       }
    
    @Override
    protected DataTableSpec[] configure(DataTableSpec[] inSpecs)
    		throws InvalidSettingsException
    	{
    	if(inSpecs==null || inSpecs.length!=1)
    		{
    		throw new InvalidSettingsException("Expected one table");
    		}
    	
    	findColumnIndex(inSpecs[0],m_accCol,StringCell.TYPE);
		findColumnIndex(inSpecs[0],m_posCol,IntCell.TYPE);
    	return new DataTableSpec[]{new DataTableSpec(inSpecs[0],createDataTableSpec())};
    	}
    
    @Override
    protected List<SettingsModel> getSettingsModel() {
    	List<SettingsModel> L=new ArrayList<SettingsModel>( super.getSettingsModel());
    	L.add(this.m_accCol);
    	L.add(this.m_posCol);
    	return L;
    	}
    
    
	}


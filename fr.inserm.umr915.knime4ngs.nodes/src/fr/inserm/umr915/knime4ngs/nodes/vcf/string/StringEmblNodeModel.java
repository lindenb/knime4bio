package fr.inserm.umr915.knime4ngs.nodes.vcf.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import org.knime.base.data.append.column.AppendedColumnRow;
import org.knime.core.data.DataCell;
import org.knime.core.data.DataColumnSpec;
import org.knime.core.data.DataColumnSpecCreator;
import org.knime.core.data.DataRow;
import org.knime.core.data.DataTableSpec;
import org.knime.core.data.DataType;
import org.knime.core.data.RowKey;
import org.knime.core.data.container.CloseableRowIterator;
import org.knime.core.data.def.StringCell;
import org.knime.core.node.BufferedDataContainer;
import org.knime.core.node.BufferedDataTable;
import org.knime.core.node.ExecutionContext;
import org.knime.core.node.InvalidSettingsException;
import org.knime.core.node.defaultnodesettings.SettingsModel;
import org.knime.core.node.defaultnodesettings.SettingsModelColumnName;
import org.knime.core.node.defaultnodesettings.SettingsModelInteger;


import fr.inserm.umr915.knime4ngs.nodes.vcf.AbstractVCFNodeModel;

public class StringEmblNodeModel extends AbstractVCFNodeModel
	{
	/** gene column */
	static final String GENE_COL_PROPERTY="gene.col";
	static final String GENE_COL_DEFAULT="GENE";
	private final SettingsModelColumnName m_geneColumn = new SettingsModelColumnName(
			GENE_COL_PROPERTY,
			GENE_COL_DEFAULT
			);
	
	/** taxon */
	static final String TAXON_PROPERTY="taxon";
	static final int DEFAULT_TAXON=9606;
	private final SettingsModelInteger m_taxonId = new SettingsModelInteger(
			TAXON_PROPERTY,
			DEFAULT_TAXON
			);
	
	/** score */
	static final String SCORE_PROPERTY="score";
	static final int DEFAULT_SCORE=1;
	private final SettingsModelInteger m_score = new SettingsModelInteger(
			SCORE_PROPERTY,
			DEFAULT_SCORE
			);
	
	private static class Identifier
		{
		String stringId;
		String preferredName;
		String annotation;
		}
	
	
	
	
	public StringEmblNodeModel()
		{
		super(1,1);
		}
	
	private List<Identifier> parseIdentifiers(String uri) throws IOException
		{
		Pattern tab=Pattern.compile("[\t]");
		List<Identifier> identifiers=new ArrayList<Identifier>();
		BufferedReader in=null;
		try
			{
			in=openReader(uri);
			String line;
			while((line=in.readLine())!=null)
				{
				if(line.isEmpty() || line.startsWith("stringId")) continue;
				String tokens[]=tab.split(line);
				if(tokens.length<5) continue;
				Identifier identifier=new Identifier();
				identifier.stringId=tokens[0];
				identifier.preferredName=tokens[3];
				identifier.annotation=tokens[4];
				identifiers.add(identifier);
				}
			return identifiers;
			}
		finally
			{
			safeClose(in);
			}
		}
	
	@Override
	protected BufferedDataTable[] execute(BufferedDataTable[] inData,
			ExecutionContext exec) throws Exception
		{
		BufferedDataTable inTable=inData[0];
		DataTableSpec inSpecs=inTable.getDataTableSpec();
		CloseableRowIterator iter=null;
		int geneColumn=findColumnIndex(inSpecs,m_geneColumn,StringCell.TYPE);
		int taxonId=this.m_taxonId.getIntValue();
		BufferedDataContainer container=null;
		
		container=exec.createDataContainer(new DataTableSpec(
			inSpecs,
			createDataTableSpec()
			));
		
		int outIndex=0;
		//collect sample names
		double total= inTable.getRowCount();
		try
			{
			iter=inTable.iterator();
			int nRow=0;
			while(iter.hasNext())
				{
				nRow++;
				DataRow row=iter.next();
				DataCell cell=row.getCell(geneColumn);
				Identifier selected=null;
				List<Identifier> partners=new ArrayList<Identifier>();
				
				if(!(cell.isMissing() || cell.toString().trim().isEmpty()))
					{
					
					String geneName=StringCell.class.cast(cell).getStringValue();
					String uri1="http://string-db.org/api/tsv/resolve?identifier="+
						URLEncoder.encode(geneName, "UTF-8")+
						"&species="+taxonId+"&caller_identity=knime4bio";
					
					List<Identifier> identifiers=parseIdentifiers(uri1);
					
					
					if(identifiers.size()==1)
						{
						selected=identifiers.get(0);
						}
					
					if(selected==null)
						{
						for(Identifier identifier:identifiers)
							{
							if(geneName.equalsIgnoreCase(identifier.preferredName))
								{	
								selected=identifier;
								break;
								}
							}
						}
					if(selected!=null)
						{
						uri1= "http://string-db.org/api/tsv-no-header/interactorsList?identifiers="+
							URLEncoder.encode(selected.stringId, "UTF-8")+
							"&required_score="+m_score.getIntValue()
							;
						BufferedReader in=null;
						Set<String> stringIds=new  HashSet<String>();
						try
							{
							in=openReader(uri1);
							String line;
							while((line=in.readLine())!=null)
								{
								if(line.isEmpty() || line.startsWith("stringId")) continue;
								stringIds.add(line.trim());
								}
							}
						finally
							{
							safeClose(in);
							}
						
						for(String id: stringIds)
							{
							uri1="http://string-db.org/api/tsv/resolve?identifier="+
								URLEncoder.encode(id, "UTF-8")+
								"&species="+taxonId+"&caller_identity=knime4bio";
							partners.addAll(parseIdentifiers(uri1));
							}
						}
					}
				
				if(selected!=null)
					{
					if(partners.isEmpty())
						{
						DataCell cells[]=new DataCell[6];
						cells[0]=new StringCell(selected.stringId);
						cells[1]=new StringCell(selected.preferredName);
						cells[2]=new StringCell(selected.annotation);
						cells[3]=DataType.getMissingCell();
						cells[4]=DataType.getMissingCell();
						cells[5]=DataType.getMissingCell();
						container.addRowToTable(
							new AppendedColumnRow(RowKey.createRowKey(++outIndex),
							row, cells)
							);
						}
					else
						{
						for(Identifier id:partners)
							{
							DataCell cells[]=new DataCell[6];
							cells[0]=new StringCell(selected.stringId);
							cells[1]=new StringCell(selected.preferredName);
							cells[2]=new StringCell(selected.annotation);
							cells[3]=new StringCell(id.stringId);
							cells[4]=new StringCell(id.preferredName);
							cells[5]=new StringCell(id.annotation);
							container.addRowToTable(
								new AppendedColumnRow(RowKey.createRowKey(++outIndex),
								row, cells)
								);
							}
						}
					}
				else
					{
					DataCell cells[]=new DataCell[6];
					cells[0]=DataType.getMissingCell();
					cells[1]=DataType.getMissingCell();
					cells[2]=DataType.getMissingCell();
					cells[3]=DataType.getMissingCell();
					cells[4]=DataType.getMissingCell();
					cells[5]=DataType.getMissingCell();
					container.addRowToTable(
						new AppendedColumnRow(RowKey.createRowKey(++outIndex),
						row, cells)
						);
					}
	            exec.checkCanceled();
	            exec.setProgress(nRow/total,"STRING");
	            }
			}
		finally
			{
			safeClose(iter);
			iter=null;
			}

		safeClose(container);
		BufferedDataTable out = container.getTable();
        container=null;
        return new BufferedDataTable[]{out};
		}
	
	  protected DataTableSpec createDataTableSpec() throws InvalidSettingsException
  		{
	   DataColumnSpec cols[]=new DataColumnSpec[]{
    		new DataColumnSpecCreator("string1.id",StringCell.TYPE).createSpec(),
    		new DataColumnSpecCreator("string1.preferredName",StringCell.TYPE).createSpec(),
    		new DataColumnSpecCreator("string1.annotation",StringCell.TYPE).createSpec(),
    		new DataColumnSpecCreator("string2.id",StringCell.TYPE).createSpec(),
    		new DataColumnSpecCreator("string2.preferredName",StringCell.TYPE).createSpec(),
    		new DataColumnSpecCreator("string2.annotation",StringCell.TYPE).createSpec()
    		};  
		return new DataTableSpec(cols);
  		}
	
	@Override
	protected DataTableSpec[] configure(DataTableSpec[] tables)
			throws InvalidSettingsException
		{
		if(tables.length!=1 || tables[0]==null) throw new InvalidSettingsException("Expected one table");
		findColumnIndex(tables[0],m_geneColumn,StringCell.TYPE);
		return new DataTableSpec[]{new DataTableSpec(tables[0],createDataTableSpec())};
		}
	@Override
	protected List<SettingsModel> getSettingsModel() {
		List<SettingsModel> arrayModel=new ArrayList<SettingsModel>(super.getSettingsModel());
		arrayModel.add(this.m_taxonId);
		arrayModel.add(this.m_geneColumn);
		arrayModel.add(this.m_score);
		return arrayModel;
		}
	}

package fr.inserm.umr915.knime4ngs.nodes.vcf.alts2alt;

import java.util.ArrayList;

import java.util.List;
import java.util.regex.Pattern;


import org.knime.core.data.DataCell;
import org.knime.core.data.DataColumnSpecCreator;
import org.knime.core.data.DataRow;
import org.knime.core.data.DataTableSpec;
import org.knime.core.data.RowKey;
import org.knime.core.data.container.CloseableRowIterator;
import org.knime.core.data.def.DefaultRow;
import org.knime.core.data.def.StringCell;
import org.knime.core.node.BufferedDataContainer;
import org.knime.core.node.BufferedDataTable;
import org.knime.core.node.ExecutionContext;
import org.knime.core.node.InvalidSettingsException;
import org.knime.core.node.defaultnodesettings.SettingsModel;
import org.knime.core.node.defaultnodesettings.SettingsModelColumnName;
import fr.inserm.umr915.knime4ngs.nodes.vcf.AbstractVCFNodeModel;

public class AltsToAltNodeModel extends AbstractVCFNodeModel
	{
	/** alt column */
	static final String ALT_COL_PROPERTY="alt.col";
	static final String ALT_COL_DEFAULT="ALT";
	private final SettingsModelColumnName m_altColumn = new SettingsModelColumnName(
			ALT_COL_PROPERTY,
			ALT_COL_DEFAULT
			);
	
	
	public AltsToAltNodeModel()
		{
		super(1,1);
		}
	
	
	@Override
	protected BufferedDataTable[] execute(BufferedDataTable[] inData,
			ExecutionContext exec) throws Exception
		{
		
		Pattern comma=Pattern.compile("[,]");
		BufferedDataTable inTable=inData[0];
		DataTableSpec inSpecs=inTable.getDataTableSpec();
		CloseableRowIterator iter=null;
		
		int altColumn = inSpecs.findColumnIndex(this.m_altColumn.getStringValue());
	
		int rowIndex=0;
		double total= inTable.getRowCount();
		BufferedDataContainer container=exec.createDataContainer(createTableSpec(inSpecs,altColumn));
		try
			{
			iter=inTable.iterator();
			int nRow=0;
			while(iter.hasNext())
				{
				DataRow row=iter.next();
				nRow++;
				DataCell cell=row.getCell(altColumn);
				
				List<DataCell> cells=new ArrayList<DataCell>(row.getNumCells()+1);
				for(int i=0;i< row.getNumCells();++i)
					{
					cells.add(row.getCell(i));
					}
				cells.add(row.getCell(altColumn));//append src
				
				if(cell.isMissing() || StringCell.class.cast(cell).getStringValue().trim().length()==0)
					{
					container.addRowToTable(new DefaultRow(RowKey.createRowKey(++rowIndex), cells));
					}
				else
					{
					String alts=StringCell.class.cast(cell).getStringValue();
					for(String alt:comma.split(alts))
						{
						List<DataCell> cells2=new ArrayList<DataCell>(cells);
						cells2.set(altColumn, new StringCell(alt));
						container.addRowToTable(new DefaultRow(RowKey.createRowKey(++rowIndex), cells2));
						}
					}
	            exec.checkCanceled();
	            exec.setProgress(nRow/total,"Processing ALTS to ALT");
				}
			}
		catch(Exception err)
			{
			err.printStackTrace();
			throw err;
			}
		finally
			{
			safeClose(iter);
			}
		safeClose(container);
		BufferedDataTable out = container.getTable();
        container=null;
        return new BufferedDataTable[]{out};
		}
	
	
	@Override
	protected DataTableSpec[] configure(DataTableSpec[] tables)
			throws InvalidSettingsException
		{
		if(tables.length!=1 || tables[0]==null) throw new InvalidSettingsException("Expected one table");
		int i=findColumnIndex(tables[0],this.m_altColumn,StringCell.TYPE);
		DataTableSpec inSpecs=createTableSpec(tables[0],i);
		return new DataTableSpec[]{inSpecs};
		}
	
	private DataTableSpec createTableSpec(DataTableSpec spec,int col)
		{
		
		return new DataTableSpec(spec,
				new DataTableSpec(new DataColumnSpecCreator(
						spec.getColumnSpec(col).getName()+".src",
						spec.getColumnSpec(col).getType()
						).createSpec())
				);
		}
	
	@Override
	protected List<SettingsModel> getSettingsModel() {
		List<SettingsModel> arrayModel=new ArrayList<SettingsModel>(super.getSettingsModel());
		arrayModel.add(this.m_altColumn);
		return arrayModel;
		}
	}

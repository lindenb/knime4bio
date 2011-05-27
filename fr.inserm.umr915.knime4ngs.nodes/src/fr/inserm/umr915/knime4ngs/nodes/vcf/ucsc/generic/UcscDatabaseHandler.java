package fr.inserm.umr915.knime4ngs.nodes.vcf.ucsc.generic;

import org.knime.core.data.DataCell;
import org.knime.core.data.DataTableSpec;

public interface UcscDatabaseHandler
	{
	public String getUrl();
	public DataTableSpec getDataTableSpec();
	public String getDatabaseName();
	public String getTableName();
	public DataCell[] parse(String tokens[]);
	public int getChromColumn();
	public int getChromStartColumn();
	public int getChromEndColumn();
	}

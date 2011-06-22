package fr.inserm.umr915.knime4ngs.nodes.vcf.ucsc.generic;

import java.sql.ResultSet;

import org.knime.core.data.DataCell;
import org.knime.core.data.DataTableSpec;

public interface UcscDatabaseMysqlHandler
	{
	public DataTableSpec getDataTableSpec();
	public String getPreparedStatement();
	public String getDatabaseName();
	public String getTableName();
	public DataCell[] parse(ResultSet row) throws java.sql.SQLException;
	public int getBinColumn();
	public int getChromColumn();
	public int getChromStartColumn();
	public int getChromEndColumn();
	}

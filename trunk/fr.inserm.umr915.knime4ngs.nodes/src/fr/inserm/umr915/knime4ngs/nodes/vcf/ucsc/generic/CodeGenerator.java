package fr.inserm.umr915.knime4ngs.nodes.vcf.ucsc.generic;

import java.io.BufferedReader;
import java.io.File;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;



public class CodeGenerator
{
private static class  KTable
	{
	String databaseName;
	String uri;
	SQLSchemaParser.Table table;
	int chrom;
	int chromStart;
	int chromEnd;
	public String getId()
		{
		return databaseName+"."+table.name;
		}
	private void echo(PrintStream out) throws Exception
		{
		out.println("new UcscDatabaseHandler()");
		out.println("	{");
		out.println("	public String getUrl()");
		out.println("		{");
		out.println("		return \""+this.uri+"\";");
		out.println("		}");
		
		out.println("public int getChromColumn() { return "+this.chrom+";}");
		out.println("public int getChromStartColumn() { return "+this.chromStart+";}");
		out.println("public int getChromEndColumn() { return "+this.chromEnd+";}");
		
		out.println("	public DataTableSpec getDataTableSpec()");
		out.println("		{");
		out.println("		DataColumnSpec  colSpecs[]=new DataColumnSpec["+table.columns.size()+"];");
		for(int i=0;i< table.columns.size();++i)
			{
			String datatType="StringCell.TYPE";
			if( table.columns.get(i).type==Long.class)
				{
				datatType="LongCell.TYPE";
				}
			else if( table.columns.get(i).type==Double.class)
				{
				datatType="DoubleCell.TYPE";
				}
			else if( table.columns.get(i).type==Integer.class)
				{
				datatType="IntCell.TYPE";
				}
			out.println("		colSpecs["+i+"]=new DataColumnSpecCreator(getDatabaseName()+\".\"+getTableName()+\"."+table.columns.get(i).name+"\","+datatType+").createSpec();");
			}
		out.println("		return new DataTableSpec(colSpecs);");
		out.println("		}");
		out.println("	public String getDatabaseName()");
		out.println("		{");
		out.println("		return \""+databaseName+"\";");
		out.println("		}");
		out.println("	public String getTableName()");
		out.println("		{");
		out.println("		return \""+table.name+"\";");
		out.println("		}");
		out.println("	public DataCell[] parse(String tokens[])");
		out.println("		{");
		out.println("DataCell cells[]=new DataCell["+table.columns.size()+"];");
		for(int i=0;i< table.columns.size();++i)
			{
			String op="";
			String cellType="StringCell";
			if( table.columns.get(i).type==Long.class)
				{
				cellType="LongCell";
				op="Long.parseLong";
				}
			else if( table.columns.get(i).type==Double.class)
				{
				cellType="DoubleCell";
				op="Double.parseDouble";
				}
			else if( table.columns.get(i).type==Integer.class)
				{
				cellType="IntCell";
				op="Integer.parseInt";
				}
			out.println("		if(tokens.length<="+i+" || tokens["+i+"].equals(\"NULL\"))");
			out.println("			{");
			out.println("			cells["+i+"]=DataType.getMissingCell();");
			out.println("			}");
			out.println("		else");
			out.println("			{");
			out.println("			cells["+i+"]=new "+cellType+"("+op+"(tokens["+i+"]));");
			out.println("			}");
			}
		out.println("		return cells;");
		out.println("		}");
		out.println("	}");
		}
	}

private List<KTable> ktables=new ArrayList<CodeGenerator.KTable>();

public void run(
	String databaseName,
	String uri) throws Exception
	{
	Set<String> tables=new TreeSet<String>();
	URL url=new URL(uri);
	String line;
	BufferedReader r=new BufferedReader(new InputStreamReader(url.openStream()));
	while((line=r.readLine())!=null)
		{
		line=line.trim();
		if(!line.startsWith("<a href=\"")) continue;
		line=line.substring(9);
		int i=line.indexOf("\"");
		if(i==-1) continue;
		line=line.substring(0,i);
		if(!line.endsWith(".sql")) continue;
		if(line.startsWith("wgEncode")) continue;
		tables.add(line);
		}
	r.close();
	
	for(String t:tables)
		{
		KTable ktable=new KTable();
		ktable.databaseName=databaseName;
		int i=t.lastIndexOf(".sql");
		ktable.uri= new URL(url,t.substring(0,i)+".txt.gz").toString();
		
		URL url2=new URL(url,t);
		InputStream in=url2.openStream();
		ktable.table=new SQLSchemaParser(in).input();
		in.close();
		
		ktable.chrom= ktable.table.findColumnIndex("chrom");
		if(ktable.chrom==-1 || ktable.table.get(ktable.chrom).type!=String.class) continue;
		ktable.chromStart= ktable.table.findColumnIndex("chromStart");
		if(ktable.chromStart==-1) ktable.chromStart= ktable.table.findColumnIndex("txStart");
		if(ktable.chromStart==-1 || ktable.table.get(ktable.chromStart).type!=Integer.class) continue;
		ktable.chromEnd= ktable.table.findColumnIndex("chromEnd");
		if(ktable.chromEnd==-1) ktable.chromEnd= ktable.table.findColumnIndex("txEnd");
		if(ktable.chromEnd==-1 || ktable.table.get(ktable.chromEnd).type!=Integer.class) continue;
		this.ktables.add(ktable);
		//if(this.ktables.size()>5) break;
 		}
	
	
	}

private void run(PrintStream out) throws Exception
	{
	run("hg19","http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/");
	echo(out);
	}

private void echo(PrintStream out) throws Exception
	{
	out.println("package fr.inserm.umr915.knime4ngs.nodes.vcf.ucsc.generic;");
	out.println("import org.knime.core.data.*;");
	out.println("import org.knime.core.data.def.*;");
	
	out.println("public class UcscTableHandlers");
	out.println("	{");
	out.println("	private static String TABLE_IDS[]=new String[]{");
	for(int i=0;i< this.ktables.size();++i)
		{
		KTable t=this.ktables.get(i);
		if(i>0) out.print(",");
		out.println("\""+t.getId()+"\"");
		}
	out.println("		};");
	out.println("	public UcscTableHandlers() {} ");
	out.println("	public static String[] getTableIds() { return TABLE_IDS;}");
	out.println("	public UcscDatabaseHandler getHandlerById(String id)");
	out.println("		{");
	for(int i=0;i< this.ktables.size();++i)
		{
		KTable t=this.ktables.get(i);
		out.println("if(id.equals(\""+t.getId()+"\")) return ");
		t.echo(out);
		out.println(";");
		}
	out.println("		return null;");
	out.println("		}");
	out.println("	}");
	}

public static void main(String[] args) throws Exception
	{
	
			File fileOut=null;
			int optind=0;
			while(optind< args.length)
				{
				if(args[optind].equals("-h") ||
				   args[optind].equals("-help") ||
				   args[optind].equals("--help"))
					{
					System.err.println("Author:Pierre Lindenbaum PhD.");
					System.err.println("Options:");
					System.err.println(" -h help; This screen.");
					System.err.println(" -o <file> outputfile");
					return;
					}
				else if(args[optind].equals("-o"))
					{
					fileOut=new File(args[++optind]);
					}
				else if(args[optind].equals("--"))
					{
					optind++;
					break;
					}
				else if(args[optind].startsWith("-"))
					{
					System.err.println("Unknown option "+args[optind]);
					return;
					}
				else 
					{
					break;
					}
				++optind;
				}
          if(optind!=args.length)
            {
            System.err.println("Illegal number of arguments");
            return;
			}
    PrintStream out=System.out;
	if(fileOut!=null)
		{
		out=new PrintStream(fileOut);
		}
	new CodeGenerator().run(out);
	out.flush();
	if(fileOut!=null)
		{
		out.close();
		}
	}
}

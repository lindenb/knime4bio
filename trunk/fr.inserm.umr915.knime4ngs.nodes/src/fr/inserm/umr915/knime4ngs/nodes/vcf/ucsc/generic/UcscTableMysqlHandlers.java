package fr.inserm.umr915.knime4ngs.nodes.vcf.ucsc.generic;
import org.knime.core.data.*;
import org.knime.core.data.def.*;
import java.sql.*;
import fr.inserm.umr915.knime4ngs.nodes.vcf.bin.UcscBin;
public class UcscTableMysqlHandlers
	{
	private static String TABLE_IDS[]=new String[]{
"hg19.acembly"
,"hg19.affyExonProbeAmbiguous"
,"hg19.affyExonProbeCore"
,"hg19.affyExonProbeExtended"
,"hg19.affyExonProbeFree"
,"hg19.affyExonProbeFull"
,"hg19.affyExonProbesetAmbiguous"
,"hg19.affyExonProbesetCore"
,"hg19.affyExonProbesetExtended"
,"hg19.affyExonProbesetFree"
,"hg19.affyExonProbesetFull"
,"hg19.agilentCgh1x1m"
,"hg19.agilentCgh1x244k"
,"hg19.agilentCgh2x105k"
,"hg19.agilentCgh2x400k"
,"hg19.agilentCgh4x180k"
,"hg19.agilentCgh4x44k"
,"hg19.agilentCgh8x60k"
,"hg19.agilentCghSnp2x400k"
,"hg19.agilentCghSnp4x180k"
,"hg19.agilentHrd1x1m"
,"hg19.altSeqHaplotypes"
,"hg19.altSeqPatches"
,"hg19.bacEndPairs"
,"hg19.burgeRnaSeqGemMapperAlignAdipose"
,"hg19.burgeRnaSeqGemMapperAlignAdiposeAllRawSignal"
,"hg19.burgeRnaSeqGemMapperAlignBT474"
,"hg19.burgeRnaSeqGemMapperAlignBT474AllRawSignal"
,"hg19.burgeRnaSeqGemMapperAlignBrain"
,"hg19.burgeRnaSeqGemMapperAlignBrainAllRawSignal"
,"hg19.burgeRnaSeqGemMapperAlignBreast"
,"hg19.burgeRnaSeqGemMapperAlignBreastAllRawSignal"
,"hg19.burgeRnaSeqGemMapperAlignColon"
,"hg19.burgeRnaSeqGemMapperAlignColonAllRawSignal"
,"hg19.burgeRnaSeqGemMapperAlignHME"
,"hg19.burgeRnaSeqGemMapperAlignHMEAllRawSignal"
,"hg19.burgeRnaSeqGemMapperAlignHeart"
,"hg19.burgeRnaSeqGemMapperAlignHeartAllRawSignal"
,"hg19.burgeRnaSeqGemMapperAlignLiver"
,"hg19.burgeRnaSeqGemMapperAlignLiverAllRawSignal"
,"hg19.burgeRnaSeqGemMapperAlignLymphNode"
,"hg19.burgeRnaSeqGemMapperAlignLymphNodeAllRawSignal"
,"hg19.burgeRnaSeqGemMapperAlignMB435"
,"hg19.burgeRnaSeqGemMapperAlignMB435AllRawSignal"
,"hg19.burgeRnaSeqGemMapperAlignMCF7"
,"hg19.burgeRnaSeqGemMapperAlignMCF7AllRawSignal"
,"hg19.burgeRnaSeqGemMapperAlignSkelMuscle"
,"hg19.burgeRnaSeqGemMapperAlignSkelMuscleAllRawSignal"
,"hg19.burgeRnaSeqGemMapperAlignT47D"
,"hg19.burgeRnaSeqGemMapperAlignT47DAllRawSignal"
,"hg19.burgeRnaSeqGemMapperAlignTestes"
,"hg19.burgeRnaSeqGemMapperAlignTestesAllRawSignal"
,"hg19.ccdsGene"
,"hg19.ccdsKgMap"
,"hg19.cgapSage"
,"hg19.consIndelsHgMmCanFam"
,"hg19.cpgIslandExt"
,"hg19.ctgPos"
,"hg19.ctgPos2"
,"hg19.cytoBand"
,"hg19.cytoBandIdeo"
,"hg19.darned"
,"hg19.dgv"
,"hg19.eioJcviNASNeg"
,"hg19.eioJcviNASPos"
,"hg19.ensGene"
,"hg19.estOrientInfo"
,"hg19.evofold"
,"hg19.exoniphy"
,"hg19.fishClones"
,"hg19.fosEndPairs"
,"hg19.gad"
,"hg19.gap"
,"hg19.gc5Base"
,"hg19.geneid"
,"hg19.genomicSuperDups"
,"hg19.genscan"
,"hg19.gnfAtlas2"
,"hg19.gold"
,"hg19.gwasCatalog"
,"hg19.hapmapAllelesChimp"
,"hg19.hapmapAllelesMacaque"
,"hg19.hapmapAllelesSummary"
,"hg19.hapmapPhaseIIISummary"
,"hg19.hapmapSnpsASW"
,"hg19.hapmapSnpsCEU"
,"hg19.hapmapSnpsCHB"
,"hg19.hapmapSnpsCHD"
,"hg19.hapmapSnpsGIH"
,"hg19.hapmapSnpsJPT"
,"hg19.hapmapSnpsLWK"
,"hg19.hapmapSnpsMEX"
,"hg19.hapmapSnpsMKK"
,"hg19.hapmapSnpsTSI"
,"hg19.hapmapSnpsYRI"
,"hg19.hg19ContigDiff"
,"hg19.hgIkmc"
,"hg19.hgdpGeo"
,"hg19.hinv70Coding"
,"hg19.hinv70NonCoding"
,"hg19.hinv70PseudoGene"
,"hg19.illuminaProbes"
,"hg19.jaxQtlAsIs"
,"hg19.jaxQtlPadded"
,"hg19.knownAlt"
,"hg19.knownCanonical"
,"hg19.knownGene"
,"hg19.laminB1"
,"hg19.laminB1Lads"
,"hg19.mgcGenes"
,"hg19.microsat"
,"hg19.mrnaOrientInfo"
,"hg19.multiz46way"
,"hg19.multiz46wayFrames"
,"hg19.multiz46waySummary"
,"hg19.nestedRepeats"
,"hg19.nscanGene"
,"hg19.ntHumChimpCodingDiff"
,"hg19.ntOoaHaplo"
,"hg19.ntSssSnps"
,"hg19.ntSssTop5p"
,"hg19.omimGene"
,"hg19.oreganno"
,"hg19.orfeomeGenes"
,"hg19.pgNA12878"
,"hg19.pgNA12891"
,"hg19.pgNA12892"
,"hg19.pgNA19240"
,"hg19.pgSjk"
,"hg19.pgVenter"
,"hg19.pgWatson"
,"hg19.pgYh1"
,"hg19.pgYoruban3"
,"hg19.phastCons46way"
,"hg19.phastCons46wayPlacental"
,"hg19.phastCons46wayPrimates"
,"hg19.phastConsElements46way"
,"hg19.phastConsElements46wayPlacental"
,"hg19.phastConsElements46wayPrimates"
,"hg19.phyloP46wayAll"
,"hg19.phyloP46wayPlacental"
,"hg19.phyloP46wayPrimates"
,"hg19.polyaDb"
,"hg19.polyaPredict"
,"hg19.recombRate"
,"hg19.refFlat"
,"hg19.refGene"
,"hg19.rgdQtl"
,"hg19.rgdRatQtl"
,"hg19.rnaCluster"
,"hg19.sestanBrainAtlas"
,"hg19.sgpGene"
,"hg19.simpleRepeat"
,"hg19.snp131"
,"hg19.snp131CodingDbSnp"
,"hg19.snp131Exceptions"
,"hg19.snp131OrthoPt2Pa2Rm2"
,"hg19.snp132"
,"hg19.snp132CodingDbSnp"
,"hg19.snp132Common"
,"hg19.snp132Flagged"
,"hg19.snp132Mult"
,"hg19.snp132OrthoPt2Pa2Rm2"
,"hg19.snpArrayAffy250Nsp"
,"hg19.snpArrayAffy250Sty"
,"hg19.snpArrayAffy5"
,"hg19.snpArrayAffy6"
,"hg19.snpArrayAffy6SV"
,"hg19.snpArrayIllumina1M"
,"hg19.snpArrayIllumina300"
,"hg19.snpArrayIllumina550"
,"hg19.snpArrayIllumina650"
,"hg19.snpArrayIlluminaHuman660W_Quad"
,"hg19.snpArrayIlluminaHumanCytoSNP_12"
,"hg19.snpArrayIlluminaHumanOmni1_Quad"
,"hg19.stsMap"
,"hg19.switchDbTss"
,"hg19.tRNAs"
,"hg19.targetScanS"
,"hg19.tfbsConsSites"
,"hg19.uMassBrainHistonePeaksInfant"
,"hg19.uMassBrainHistonePeaksNeuron"
,"hg19.uMassBrainHistonePeaksSample"
,"hg19.ucsfChipSeqH3K4me3BrainCoverage"
,"hg19.ucsfMedipSeqBrainCoverage"
,"hg19.ucsfMedipSeqBrainCpG"
,"hg19.ucsfMedipSeqBrainReads"
,"hg19.ucsfMreSeqBrainCpG"
,"hg19.ucsfMreSeqBrainReads"
,"hg19.ucsfRnaSeqBrainAllCoverage"
,"hg19.ucsfRnaSeqBrainAllReads"
,"hg19.ucsfRnaSeqBrainSmartCoverage"
,"hg19.ucsfRnaSeqBrainSmartReads"
,"hg19.vegaGene"
,"hg19.vegaPseudoGene"
,"hg19.vistaEnhancers"
,"hg19.wgRna"
,"hg19.xenoRefFlat"
,"hg19.xenoRefGene"
		};
	public UcscTableMysqlHandlers() {} 
	public static String[] getTableIds() { return TABLE_IDS;}
	public UcscDatabaseMysqlHandler getHandlerById(String id)
		{
if(id.equals("hg19.acembly")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 2;}
public int getChromStartColumn() { return 4;}
public int getChromEndColumn() { return 5;}
public String getPreparedStatement() { return "select bin,name,chrom,strand,txStart,txEnd,cdsStart,cdsEnd,exonCount,exonStarts,exonEnds from hg19.acembly where chrom=? and not(txEnd<=? or txStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[11];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".strand",StringCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".txStart",IntCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".txEnd",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".cdsStart",IntCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".cdsEnd",IntCell.TYPE).createSpec();
		colSpecs[8]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".exonCount",IntCell.TYPE).createSpec();
		colSpecs[9]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".exonStarts",StringCell.TYPE).createSpec();
		colSpecs[10]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".exonEnds",StringCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "acembly";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[11];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new StringCell(row.getString("strand"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new IntCell(row.getInt("txStart"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("txEnd"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new IntCell(row.getInt("cdsStart"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new IntCell(row.getInt("cdsEnd"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
			cells[8]=new IntCell(row.getInt("exonCount"));
			if(row.wasNull()) cells[8]=DataType.getMissingCell();
			cells[9]=new StringCell(row.getString("exonStarts"));
			if(row.wasNull()) cells[9]=DataType.getMissingCell();
			cells[10]=new StringCell(row.getString("exonEnds"));
			if(row.wasNull()) cells[10]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.affyExonProbeAmbiguous")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,score,strand,thickStart,thickEnd,reserved,blockCount from hg19.affyExonProbeAmbiguous where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[11];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".score",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".strand",StringCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".thickStart",IntCell.TYPE).createSpec();
		colSpecs[8]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".thickEnd",IntCell.TYPE).createSpec();
		colSpecs[9]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".reserved",IntCell.TYPE).createSpec();
		colSpecs[10]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".blockCount",IntCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "affyExonProbeAmbiguous";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[11];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("score"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new StringCell(row.getString("strand"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new IntCell(row.getInt("thickStart"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
			cells[8]=new IntCell(row.getInt("thickEnd"));
			if(row.wasNull()) cells[8]=DataType.getMissingCell();
			cells[9]=new IntCell(row.getInt("reserved"));
			if(row.wasNull()) cells[9]=DataType.getMissingCell();
			cells[10]=new IntCell(row.getInt("blockCount"));
			if(row.wasNull()) cells[10]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.affyExonProbeCore")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,score,strand,thickStart,thickEnd,reserved,blockCount from hg19.affyExonProbeCore where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[11];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".score",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".strand",StringCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".thickStart",IntCell.TYPE).createSpec();
		colSpecs[8]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".thickEnd",IntCell.TYPE).createSpec();
		colSpecs[9]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".reserved",IntCell.TYPE).createSpec();
		colSpecs[10]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".blockCount",IntCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "affyExonProbeCore";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[11];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("score"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new StringCell(row.getString("strand"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new IntCell(row.getInt("thickStart"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
			cells[8]=new IntCell(row.getInt("thickEnd"));
			if(row.wasNull()) cells[8]=DataType.getMissingCell();
			cells[9]=new IntCell(row.getInt("reserved"));
			if(row.wasNull()) cells[9]=DataType.getMissingCell();
			cells[10]=new IntCell(row.getInt("blockCount"));
			if(row.wasNull()) cells[10]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.affyExonProbeExtended")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,score,strand,thickStart,thickEnd,reserved,blockCount from hg19.affyExonProbeExtended where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[11];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".score",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".strand",StringCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".thickStart",IntCell.TYPE).createSpec();
		colSpecs[8]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".thickEnd",IntCell.TYPE).createSpec();
		colSpecs[9]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".reserved",IntCell.TYPE).createSpec();
		colSpecs[10]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".blockCount",IntCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "affyExonProbeExtended";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[11];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("score"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new StringCell(row.getString("strand"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new IntCell(row.getInt("thickStart"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
			cells[8]=new IntCell(row.getInt("thickEnd"));
			if(row.wasNull()) cells[8]=DataType.getMissingCell();
			cells[9]=new IntCell(row.getInt("reserved"));
			if(row.wasNull()) cells[9]=DataType.getMissingCell();
			cells[10]=new IntCell(row.getInt("blockCount"));
			if(row.wasNull()) cells[10]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.affyExonProbeFree")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,score,strand,thickStart,thickEnd,reserved,blockCount from hg19.affyExonProbeFree where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[11];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".score",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".strand",StringCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".thickStart",IntCell.TYPE).createSpec();
		colSpecs[8]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".thickEnd",IntCell.TYPE).createSpec();
		colSpecs[9]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".reserved",IntCell.TYPE).createSpec();
		colSpecs[10]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".blockCount",IntCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "affyExonProbeFree";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[11];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("score"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new StringCell(row.getString("strand"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new IntCell(row.getInt("thickStart"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
			cells[8]=new IntCell(row.getInt("thickEnd"));
			if(row.wasNull()) cells[8]=DataType.getMissingCell();
			cells[9]=new IntCell(row.getInt("reserved"));
			if(row.wasNull()) cells[9]=DataType.getMissingCell();
			cells[10]=new IntCell(row.getInt("blockCount"));
			if(row.wasNull()) cells[10]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.affyExonProbeFull")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,score,strand,thickStart,thickEnd,reserved,blockCount from hg19.affyExonProbeFull where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[11];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".score",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".strand",StringCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".thickStart",IntCell.TYPE).createSpec();
		colSpecs[8]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".thickEnd",IntCell.TYPE).createSpec();
		colSpecs[9]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".reserved",IntCell.TYPE).createSpec();
		colSpecs[10]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".blockCount",IntCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "affyExonProbeFull";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[11];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("score"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new StringCell(row.getString("strand"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new IntCell(row.getInt("thickStart"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
			cells[8]=new IntCell(row.getInt("thickEnd"));
			if(row.wasNull()) cells[8]=DataType.getMissingCell();
			cells[9]=new IntCell(row.getInt("reserved"));
			if(row.wasNull()) cells[9]=DataType.getMissingCell();
			cells[10]=new IntCell(row.getInt("blockCount"));
			if(row.wasNull()) cells[10]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.affyExonProbesetAmbiguous")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,score,strand,thickStart,thickEnd,reserved,blockCount,blockSizes,chromStarts from hg19.affyExonProbesetAmbiguous where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[13];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".score",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".strand",StringCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".thickStart",IntCell.TYPE).createSpec();
		colSpecs[8]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".thickEnd",IntCell.TYPE).createSpec();
		colSpecs[9]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".reserved",IntCell.TYPE).createSpec();
		colSpecs[10]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".blockCount",IntCell.TYPE).createSpec();
		colSpecs[11]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".blockSizes",StringCell.TYPE).createSpec();
		colSpecs[12]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStarts",StringCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "affyExonProbesetAmbiguous";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[13];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("score"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new StringCell(row.getString("strand"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new IntCell(row.getInt("thickStart"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
			cells[8]=new IntCell(row.getInt("thickEnd"));
			if(row.wasNull()) cells[8]=DataType.getMissingCell();
			cells[9]=new IntCell(row.getInt("reserved"));
			if(row.wasNull()) cells[9]=DataType.getMissingCell();
			cells[10]=new IntCell(row.getInt("blockCount"));
			if(row.wasNull()) cells[10]=DataType.getMissingCell();
			cells[11]=new StringCell(row.getString("blockSizes"));
			if(row.wasNull()) cells[11]=DataType.getMissingCell();
			cells[12]=new StringCell(row.getString("chromStarts"));
			if(row.wasNull()) cells[12]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.affyExonProbesetCore")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,score,strand,thickStart,thickEnd,reserved,blockCount,blockSizes,chromStarts from hg19.affyExonProbesetCore where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[13];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".score",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".strand",StringCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".thickStart",IntCell.TYPE).createSpec();
		colSpecs[8]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".thickEnd",IntCell.TYPE).createSpec();
		colSpecs[9]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".reserved",IntCell.TYPE).createSpec();
		colSpecs[10]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".blockCount",IntCell.TYPE).createSpec();
		colSpecs[11]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".blockSizes",StringCell.TYPE).createSpec();
		colSpecs[12]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStarts",StringCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "affyExonProbesetCore";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[13];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("score"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new StringCell(row.getString("strand"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new IntCell(row.getInt("thickStart"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
			cells[8]=new IntCell(row.getInt("thickEnd"));
			if(row.wasNull()) cells[8]=DataType.getMissingCell();
			cells[9]=new IntCell(row.getInt("reserved"));
			if(row.wasNull()) cells[9]=DataType.getMissingCell();
			cells[10]=new IntCell(row.getInt("blockCount"));
			if(row.wasNull()) cells[10]=DataType.getMissingCell();
			cells[11]=new StringCell(row.getString("blockSizes"));
			if(row.wasNull()) cells[11]=DataType.getMissingCell();
			cells[12]=new StringCell(row.getString("chromStarts"));
			if(row.wasNull()) cells[12]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.affyExonProbesetExtended")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,score,strand,thickStart,thickEnd,reserved,blockCount,blockSizes,chromStarts from hg19.affyExonProbesetExtended where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[13];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".score",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".strand",StringCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".thickStart",IntCell.TYPE).createSpec();
		colSpecs[8]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".thickEnd",IntCell.TYPE).createSpec();
		colSpecs[9]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".reserved",IntCell.TYPE).createSpec();
		colSpecs[10]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".blockCount",IntCell.TYPE).createSpec();
		colSpecs[11]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".blockSizes",StringCell.TYPE).createSpec();
		colSpecs[12]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStarts",StringCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "affyExonProbesetExtended";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[13];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("score"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new StringCell(row.getString("strand"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new IntCell(row.getInt("thickStart"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
			cells[8]=new IntCell(row.getInt("thickEnd"));
			if(row.wasNull()) cells[8]=DataType.getMissingCell();
			cells[9]=new IntCell(row.getInt("reserved"));
			if(row.wasNull()) cells[9]=DataType.getMissingCell();
			cells[10]=new IntCell(row.getInt("blockCount"));
			if(row.wasNull()) cells[10]=DataType.getMissingCell();
			cells[11]=new StringCell(row.getString("blockSizes"));
			if(row.wasNull()) cells[11]=DataType.getMissingCell();
			cells[12]=new StringCell(row.getString("chromStarts"));
			if(row.wasNull()) cells[12]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.affyExonProbesetFree")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,score,strand,thickStart,thickEnd,reserved,blockCount,blockSizes,chromStarts from hg19.affyExonProbesetFree where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[13];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".score",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".strand",StringCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".thickStart",IntCell.TYPE).createSpec();
		colSpecs[8]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".thickEnd",IntCell.TYPE).createSpec();
		colSpecs[9]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".reserved",IntCell.TYPE).createSpec();
		colSpecs[10]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".blockCount",IntCell.TYPE).createSpec();
		colSpecs[11]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".blockSizes",StringCell.TYPE).createSpec();
		colSpecs[12]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStarts",StringCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "affyExonProbesetFree";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[13];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("score"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new StringCell(row.getString("strand"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new IntCell(row.getInt("thickStart"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
			cells[8]=new IntCell(row.getInt("thickEnd"));
			if(row.wasNull()) cells[8]=DataType.getMissingCell();
			cells[9]=new IntCell(row.getInt("reserved"));
			if(row.wasNull()) cells[9]=DataType.getMissingCell();
			cells[10]=new IntCell(row.getInt("blockCount"));
			if(row.wasNull()) cells[10]=DataType.getMissingCell();
			cells[11]=new StringCell(row.getString("blockSizes"));
			if(row.wasNull()) cells[11]=DataType.getMissingCell();
			cells[12]=new StringCell(row.getString("chromStarts"));
			if(row.wasNull()) cells[12]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.affyExonProbesetFull")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,score,strand,thickStart,thickEnd,reserved,blockCount,blockSizes,chromStarts from hg19.affyExonProbesetFull where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[13];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".score",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".strand",StringCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".thickStart",IntCell.TYPE).createSpec();
		colSpecs[8]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".thickEnd",IntCell.TYPE).createSpec();
		colSpecs[9]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".reserved",IntCell.TYPE).createSpec();
		colSpecs[10]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".blockCount",IntCell.TYPE).createSpec();
		colSpecs[11]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".blockSizes",StringCell.TYPE).createSpec();
		colSpecs[12]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStarts",StringCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "affyExonProbesetFull";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[13];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("score"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new StringCell(row.getString("strand"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new IntCell(row.getInt("thickStart"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
			cells[8]=new IntCell(row.getInt("thickEnd"));
			if(row.wasNull()) cells[8]=DataType.getMissingCell();
			cells[9]=new IntCell(row.getInt("reserved"));
			if(row.wasNull()) cells[9]=DataType.getMissingCell();
			cells[10]=new IntCell(row.getInt("blockCount"));
			if(row.wasNull()) cells[10]=DataType.getMissingCell();
			cells[11]=new StringCell(row.getString("blockSizes"));
			if(row.wasNull()) cells[11]=DataType.getMissingCell();
			cells[12]=new StringCell(row.getString("chromStarts"));
			if(row.wasNull()) cells[12]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.agilentCgh1x1m")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name from hg19.agilentCgh1x1m where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[5];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "agilentCgh1x1m";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[5];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.agilentCgh1x244k")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name from hg19.agilentCgh1x244k where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[5];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "agilentCgh1x244k";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[5];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.agilentCgh2x105k")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name from hg19.agilentCgh2x105k where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[5];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "agilentCgh2x105k";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[5];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.agilentCgh2x400k")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name from hg19.agilentCgh2x400k where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[5];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "agilentCgh2x400k";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[5];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.agilentCgh4x180k")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name from hg19.agilentCgh4x180k where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[5];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "agilentCgh4x180k";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[5];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.agilentCgh4x44k")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name from hg19.agilentCgh4x44k where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[5];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "agilentCgh4x44k";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[5];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.agilentCgh8x60k")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name from hg19.agilentCgh8x60k where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[5];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "agilentCgh8x60k";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[5];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.agilentCghSnp2x400k")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,score,strand,thickStart,thickEnd,reserved from hg19.agilentCghSnp2x400k where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[10];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".score",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".strand",StringCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".thickStart",IntCell.TYPE).createSpec();
		colSpecs[8]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".thickEnd",IntCell.TYPE).createSpec();
		colSpecs[9]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".reserved",IntCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "agilentCghSnp2x400k";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[10];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("score"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new StringCell(row.getString("strand"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new IntCell(row.getInt("thickStart"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
			cells[8]=new IntCell(row.getInt("thickEnd"));
			if(row.wasNull()) cells[8]=DataType.getMissingCell();
			cells[9]=new IntCell(row.getInt("reserved"));
			if(row.wasNull()) cells[9]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.agilentCghSnp4x180k")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,score,strand,thickStart,thickEnd,reserved from hg19.agilentCghSnp4x180k where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[10];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".score",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".strand",StringCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".thickStart",IntCell.TYPE).createSpec();
		colSpecs[8]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".thickEnd",IntCell.TYPE).createSpec();
		colSpecs[9]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".reserved",IntCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "agilentCghSnp4x180k";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[10];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("score"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new StringCell(row.getString("strand"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new IntCell(row.getInt("thickStart"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
			cells[8]=new IntCell(row.getInt("thickEnd"));
			if(row.wasNull()) cells[8]=DataType.getMissingCell();
			cells[9]=new IntCell(row.getInt("reserved"));
			if(row.wasNull()) cells[9]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.agilentHrd1x1m")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name from hg19.agilentHrd1x1m where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[5];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "agilentHrd1x1m";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[5];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.altSeqHaplotypes")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,score,strand from hg19.altSeqHaplotypes where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[7];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".score",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".strand",StringCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "altSeqHaplotypes";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[7];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("score"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new StringCell(row.getString("strand"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.altSeqPatches")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,score,strand from hg19.altSeqPatches where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[7];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".score",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".strand",StringCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "altSeqPatches";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[7];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("score"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new StringCell(row.getString("strand"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.bacEndPairs")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,score,strand,pslTable,lfCount,lfStarts,lfSizes,lfNames from hg19.bacEndPairs where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[12];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".score",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".strand",StringCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".pslTable",StringCell.TYPE).createSpec();
		colSpecs[8]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".lfCount",IntCell.TYPE).createSpec();
		colSpecs[9]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".lfStarts",StringCell.TYPE).createSpec();
		colSpecs[10]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".lfSizes",StringCell.TYPE).createSpec();
		colSpecs[11]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".lfNames",StringCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "bacEndPairs";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[12];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("score"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new StringCell(row.getString("strand"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new StringCell(row.getString("pslTable"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
			cells[8]=new IntCell(row.getInt("lfCount"));
			if(row.wasNull()) cells[8]=DataType.getMissingCell();
			cells[9]=new StringCell(row.getString("lfStarts"));
			if(row.wasNull()) cells[9]=DataType.getMissingCell();
			cells[10]=new StringCell(row.getString("lfSizes"));
			if(row.wasNull()) cells[10]=DataType.getMissingCell();
			cells[11]=new StringCell(row.getString("lfNames"));
			if(row.wasNull()) cells[11]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.burgeRnaSeqGemMapperAlignAdipose")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,score,strand,thickStart,thickEnd,reserved,blockCount,blockSizes,chromStarts from hg19.burgeRnaSeqGemMapperAlignAdipose where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[13];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".score",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".strand",StringCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".thickStart",IntCell.TYPE).createSpec();
		colSpecs[8]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".thickEnd",IntCell.TYPE).createSpec();
		colSpecs[9]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".reserved",IntCell.TYPE).createSpec();
		colSpecs[10]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".blockCount",IntCell.TYPE).createSpec();
		colSpecs[11]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".blockSizes",StringCell.TYPE).createSpec();
		colSpecs[12]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStarts",StringCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "burgeRnaSeqGemMapperAlignAdipose";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[13];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("score"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new StringCell(row.getString("strand"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new IntCell(row.getInt("thickStart"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
			cells[8]=new IntCell(row.getInt("thickEnd"));
			if(row.wasNull()) cells[8]=DataType.getMissingCell();
			cells[9]=new IntCell(row.getInt("reserved"));
			if(row.wasNull()) cells[9]=DataType.getMissingCell();
			cells[10]=new IntCell(row.getInt("blockCount"));
			if(row.wasNull()) cells[10]=DataType.getMissingCell();
			cells[11]=new StringCell(row.getString("blockSizes"));
			if(row.wasNull()) cells[11]=DataType.getMissingCell();
			cells[12]=new StringCell(row.getString("chromStarts"));
			if(row.wasNull()) cells[12]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.burgeRnaSeqGemMapperAlignAdiposeAllRawSignal")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,dataValue from hg19.burgeRnaSeqGemMapperAlignAdiposeAllRawSignal where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[5];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".dataValue",DoubleCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "burgeRnaSeqGemMapperAlignAdiposeAllRawSignal";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[5];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new DoubleCell(row.getDouble("dataValue"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.burgeRnaSeqGemMapperAlignBT474")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,score,strand,thickStart,thickEnd,reserved,blockCount,blockSizes,chromStarts from hg19.burgeRnaSeqGemMapperAlignBT474 where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[13];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".score",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".strand",StringCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".thickStart",IntCell.TYPE).createSpec();
		colSpecs[8]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".thickEnd",IntCell.TYPE).createSpec();
		colSpecs[9]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".reserved",IntCell.TYPE).createSpec();
		colSpecs[10]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".blockCount",IntCell.TYPE).createSpec();
		colSpecs[11]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".blockSizes",StringCell.TYPE).createSpec();
		colSpecs[12]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStarts",StringCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "burgeRnaSeqGemMapperAlignBT474";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[13];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("score"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new StringCell(row.getString("strand"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new IntCell(row.getInt("thickStart"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
			cells[8]=new IntCell(row.getInt("thickEnd"));
			if(row.wasNull()) cells[8]=DataType.getMissingCell();
			cells[9]=new IntCell(row.getInt("reserved"));
			if(row.wasNull()) cells[9]=DataType.getMissingCell();
			cells[10]=new IntCell(row.getInt("blockCount"));
			if(row.wasNull()) cells[10]=DataType.getMissingCell();
			cells[11]=new StringCell(row.getString("blockSizes"));
			if(row.wasNull()) cells[11]=DataType.getMissingCell();
			cells[12]=new StringCell(row.getString("chromStarts"));
			if(row.wasNull()) cells[12]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.burgeRnaSeqGemMapperAlignBT474AllRawSignal")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,dataValue from hg19.burgeRnaSeqGemMapperAlignBT474AllRawSignal where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[5];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".dataValue",DoubleCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "burgeRnaSeqGemMapperAlignBT474AllRawSignal";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[5];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new DoubleCell(row.getDouble("dataValue"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.burgeRnaSeqGemMapperAlignBrain")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,score,strand,thickStart,thickEnd,reserved,blockCount,blockSizes,chromStarts from hg19.burgeRnaSeqGemMapperAlignBrain where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[13];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".score",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".strand",StringCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".thickStart",IntCell.TYPE).createSpec();
		colSpecs[8]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".thickEnd",IntCell.TYPE).createSpec();
		colSpecs[9]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".reserved",IntCell.TYPE).createSpec();
		colSpecs[10]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".blockCount",IntCell.TYPE).createSpec();
		colSpecs[11]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".blockSizes",StringCell.TYPE).createSpec();
		colSpecs[12]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStarts",StringCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "burgeRnaSeqGemMapperAlignBrain";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[13];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("score"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new StringCell(row.getString("strand"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new IntCell(row.getInt("thickStart"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
			cells[8]=new IntCell(row.getInt("thickEnd"));
			if(row.wasNull()) cells[8]=DataType.getMissingCell();
			cells[9]=new IntCell(row.getInt("reserved"));
			if(row.wasNull()) cells[9]=DataType.getMissingCell();
			cells[10]=new IntCell(row.getInt("blockCount"));
			if(row.wasNull()) cells[10]=DataType.getMissingCell();
			cells[11]=new StringCell(row.getString("blockSizes"));
			if(row.wasNull()) cells[11]=DataType.getMissingCell();
			cells[12]=new StringCell(row.getString("chromStarts"));
			if(row.wasNull()) cells[12]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.burgeRnaSeqGemMapperAlignBrainAllRawSignal")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,dataValue from hg19.burgeRnaSeqGemMapperAlignBrainAllRawSignal where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[5];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".dataValue",DoubleCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "burgeRnaSeqGemMapperAlignBrainAllRawSignal";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[5];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new DoubleCell(row.getDouble("dataValue"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.burgeRnaSeqGemMapperAlignBreast")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,score,strand,thickStart,thickEnd,reserved,blockCount,blockSizes,chromStarts from hg19.burgeRnaSeqGemMapperAlignBreast where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[13];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".score",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".strand",StringCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".thickStart",IntCell.TYPE).createSpec();
		colSpecs[8]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".thickEnd",IntCell.TYPE).createSpec();
		colSpecs[9]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".reserved",IntCell.TYPE).createSpec();
		colSpecs[10]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".blockCount",IntCell.TYPE).createSpec();
		colSpecs[11]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".blockSizes",StringCell.TYPE).createSpec();
		colSpecs[12]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStarts",StringCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "burgeRnaSeqGemMapperAlignBreast";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[13];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("score"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new StringCell(row.getString("strand"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new IntCell(row.getInt("thickStart"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
			cells[8]=new IntCell(row.getInt("thickEnd"));
			if(row.wasNull()) cells[8]=DataType.getMissingCell();
			cells[9]=new IntCell(row.getInt("reserved"));
			if(row.wasNull()) cells[9]=DataType.getMissingCell();
			cells[10]=new IntCell(row.getInt("blockCount"));
			if(row.wasNull()) cells[10]=DataType.getMissingCell();
			cells[11]=new StringCell(row.getString("blockSizes"));
			if(row.wasNull()) cells[11]=DataType.getMissingCell();
			cells[12]=new StringCell(row.getString("chromStarts"));
			if(row.wasNull()) cells[12]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.burgeRnaSeqGemMapperAlignBreastAllRawSignal")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,dataValue from hg19.burgeRnaSeqGemMapperAlignBreastAllRawSignal where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[5];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".dataValue",DoubleCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "burgeRnaSeqGemMapperAlignBreastAllRawSignal";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[5];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new DoubleCell(row.getDouble("dataValue"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.burgeRnaSeqGemMapperAlignColon")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,score,strand,thickStart,thickEnd,reserved,blockCount,blockSizes,chromStarts from hg19.burgeRnaSeqGemMapperAlignColon where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[13];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".score",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".strand",StringCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".thickStart",IntCell.TYPE).createSpec();
		colSpecs[8]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".thickEnd",IntCell.TYPE).createSpec();
		colSpecs[9]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".reserved",IntCell.TYPE).createSpec();
		colSpecs[10]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".blockCount",IntCell.TYPE).createSpec();
		colSpecs[11]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".blockSizes",StringCell.TYPE).createSpec();
		colSpecs[12]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStarts",StringCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "burgeRnaSeqGemMapperAlignColon";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[13];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("score"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new StringCell(row.getString("strand"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new IntCell(row.getInt("thickStart"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
			cells[8]=new IntCell(row.getInt("thickEnd"));
			if(row.wasNull()) cells[8]=DataType.getMissingCell();
			cells[9]=new IntCell(row.getInt("reserved"));
			if(row.wasNull()) cells[9]=DataType.getMissingCell();
			cells[10]=new IntCell(row.getInt("blockCount"));
			if(row.wasNull()) cells[10]=DataType.getMissingCell();
			cells[11]=new StringCell(row.getString("blockSizes"));
			if(row.wasNull()) cells[11]=DataType.getMissingCell();
			cells[12]=new StringCell(row.getString("chromStarts"));
			if(row.wasNull()) cells[12]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.burgeRnaSeqGemMapperAlignColonAllRawSignal")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,dataValue from hg19.burgeRnaSeqGemMapperAlignColonAllRawSignal where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[5];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".dataValue",DoubleCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "burgeRnaSeqGemMapperAlignColonAllRawSignal";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[5];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new DoubleCell(row.getDouble("dataValue"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.burgeRnaSeqGemMapperAlignHME")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,score,strand,thickStart,thickEnd,reserved,blockCount,blockSizes,chromStarts from hg19.burgeRnaSeqGemMapperAlignHME where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[13];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".score",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".strand",StringCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".thickStart",IntCell.TYPE).createSpec();
		colSpecs[8]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".thickEnd",IntCell.TYPE).createSpec();
		colSpecs[9]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".reserved",IntCell.TYPE).createSpec();
		colSpecs[10]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".blockCount",IntCell.TYPE).createSpec();
		colSpecs[11]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".blockSizes",StringCell.TYPE).createSpec();
		colSpecs[12]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStarts",StringCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "burgeRnaSeqGemMapperAlignHME";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[13];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("score"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new StringCell(row.getString("strand"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new IntCell(row.getInt("thickStart"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
			cells[8]=new IntCell(row.getInt("thickEnd"));
			if(row.wasNull()) cells[8]=DataType.getMissingCell();
			cells[9]=new IntCell(row.getInt("reserved"));
			if(row.wasNull()) cells[9]=DataType.getMissingCell();
			cells[10]=new IntCell(row.getInt("blockCount"));
			if(row.wasNull()) cells[10]=DataType.getMissingCell();
			cells[11]=new StringCell(row.getString("blockSizes"));
			if(row.wasNull()) cells[11]=DataType.getMissingCell();
			cells[12]=new StringCell(row.getString("chromStarts"));
			if(row.wasNull()) cells[12]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.burgeRnaSeqGemMapperAlignHMEAllRawSignal")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,dataValue from hg19.burgeRnaSeqGemMapperAlignHMEAllRawSignal where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[5];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".dataValue",DoubleCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "burgeRnaSeqGemMapperAlignHMEAllRawSignal";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[5];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new DoubleCell(row.getDouble("dataValue"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.burgeRnaSeqGemMapperAlignHeart")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,score,strand,thickStart,thickEnd,reserved,blockCount,blockSizes,chromStarts from hg19.burgeRnaSeqGemMapperAlignHeart where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[13];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".score",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".strand",StringCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".thickStart",IntCell.TYPE).createSpec();
		colSpecs[8]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".thickEnd",IntCell.TYPE).createSpec();
		colSpecs[9]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".reserved",IntCell.TYPE).createSpec();
		colSpecs[10]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".blockCount",IntCell.TYPE).createSpec();
		colSpecs[11]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".blockSizes",StringCell.TYPE).createSpec();
		colSpecs[12]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStarts",StringCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "burgeRnaSeqGemMapperAlignHeart";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[13];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("score"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new StringCell(row.getString("strand"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new IntCell(row.getInt("thickStart"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
			cells[8]=new IntCell(row.getInt("thickEnd"));
			if(row.wasNull()) cells[8]=DataType.getMissingCell();
			cells[9]=new IntCell(row.getInt("reserved"));
			if(row.wasNull()) cells[9]=DataType.getMissingCell();
			cells[10]=new IntCell(row.getInt("blockCount"));
			if(row.wasNull()) cells[10]=DataType.getMissingCell();
			cells[11]=new StringCell(row.getString("blockSizes"));
			if(row.wasNull()) cells[11]=DataType.getMissingCell();
			cells[12]=new StringCell(row.getString("chromStarts"));
			if(row.wasNull()) cells[12]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.burgeRnaSeqGemMapperAlignHeartAllRawSignal")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,dataValue from hg19.burgeRnaSeqGemMapperAlignHeartAllRawSignal where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[5];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".dataValue",DoubleCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "burgeRnaSeqGemMapperAlignHeartAllRawSignal";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[5];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new DoubleCell(row.getDouble("dataValue"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.burgeRnaSeqGemMapperAlignLiver")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,score,strand,thickStart,thickEnd,reserved,blockCount,blockSizes,chromStarts from hg19.burgeRnaSeqGemMapperAlignLiver where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[13];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".score",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".strand",StringCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".thickStart",IntCell.TYPE).createSpec();
		colSpecs[8]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".thickEnd",IntCell.TYPE).createSpec();
		colSpecs[9]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".reserved",IntCell.TYPE).createSpec();
		colSpecs[10]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".blockCount",IntCell.TYPE).createSpec();
		colSpecs[11]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".blockSizes",StringCell.TYPE).createSpec();
		colSpecs[12]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStarts",StringCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "burgeRnaSeqGemMapperAlignLiver";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[13];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("score"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new StringCell(row.getString("strand"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new IntCell(row.getInt("thickStart"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
			cells[8]=new IntCell(row.getInt("thickEnd"));
			if(row.wasNull()) cells[8]=DataType.getMissingCell();
			cells[9]=new IntCell(row.getInt("reserved"));
			if(row.wasNull()) cells[9]=DataType.getMissingCell();
			cells[10]=new IntCell(row.getInt("blockCount"));
			if(row.wasNull()) cells[10]=DataType.getMissingCell();
			cells[11]=new StringCell(row.getString("blockSizes"));
			if(row.wasNull()) cells[11]=DataType.getMissingCell();
			cells[12]=new StringCell(row.getString("chromStarts"));
			if(row.wasNull()) cells[12]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.burgeRnaSeqGemMapperAlignLiverAllRawSignal")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,dataValue from hg19.burgeRnaSeqGemMapperAlignLiverAllRawSignal where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[5];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".dataValue",DoubleCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "burgeRnaSeqGemMapperAlignLiverAllRawSignal";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[5];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new DoubleCell(row.getDouble("dataValue"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.burgeRnaSeqGemMapperAlignLymphNode")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,score,strand,thickStart,thickEnd,reserved,blockCount,blockSizes,chromStarts from hg19.burgeRnaSeqGemMapperAlignLymphNode where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[13];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".score",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".strand",StringCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".thickStart",IntCell.TYPE).createSpec();
		colSpecs[8]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".thickEnd",IntCell.TYPE).createSpec();
		colSpecs[9]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".reserved",IntCell.TYPE).createSpec();
		colSpecs[10]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".blockCount",IntCell.TYPE).createSpec();
		colSpecs[11]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".blockSizes",StringCell.TYPE).createSpec();
		colSpecs[12]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStarts",StringCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "burgeRnaSeqGemMapperAlignLymphNode";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[13];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("score"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new StringCell(row.getString("strand"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new IntCell(row.getInt("thickStart"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
			cells[8]=new IntCell(row.getInt("thickEnd"));
			if(row.wasNull()) cells[8]=DataType.getMissingCell();
			cells[9]=new IntCell(row.getInt("reserved"));
			if(row.wasNull()) cells[9]=DataType.getMissingCell();
			cells[10]=new IntCell(row.getInt("blockCount"));
			if(row.wasNull()) cells[10]=DataType.getMissingCell();
			cells[11]=new StringCell(row.getString("blockSizes"));
			if(row.wasNull()) cells[11]=DataType.getMissingCell();
			cells[12]=new StringCell(row.getString("chromStarts"));
			if(row.wasNull()) cells[12]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.burgeRnaSeqGemMapperAlignLymphNodeAllRawSignal")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,dataValue from hg19.burgeRnaSeqGemMapperAlignLymphNodeAllRawSignal where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[5];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".dataValue",DoubleCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "burgeRnaSeqGemMapperAlignLymphNodeAllRawSignal";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[5];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new DoubleCell(row.getDouble("dataValue"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.burgeRnaSeqGemMapperAlignMB435")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,score,strand,thickStart,thickEnd,reserved,blockCount,blockSizes,chromStarts from hg19.burgeRnaSeqGemMapperAlignMB435 where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[13];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".score",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".strand",StringCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".thickStart",IntCell.TYPE).createSpec();
		colSpecs[8]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".thickEnd",IntCell.TYPE).createSpec();
		colSpecs[9]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".reserved",IntCell.TYPE).createSpec();
		colSpecs[10]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".blockCount",IntCell.TYPE).createSpec();
		colSpecs[11]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".blockSizes",StringCell.TYPE).createSpec();
		colSpecs[12]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStarts",StringCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "burgeRnaSeqGemMapperAlignMB435";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[13];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("score"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new StringCell(row.getString("strand"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new IntCell(row.getInt("thickStart"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
			cells[8]=new IntCell(row.getInt("thickEnd"));
			if(row.wasNull()) cells[8]=DataType.getMissingCell();
			cells[9]=new IntCell(row.getInt("reserved"));
			if(row.wasNull()) cells[9]=DataType.getMissingCell();
			cells[10]=new IntCell(row.getInt("blockCount"));
			if(row.wasNull()) cells[10]=DataType.getMissingCell();
			cells[11]=new StringCell(row.getString("blockSizes"));
			if(row.wasNull()) cells[11]=DataType.getMissingCell();
			cells[12]=new StringCell(row.getString("chromStarts"));
			if(row.wasNull()) cells[12]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.burgeRnaSeqGemMapperAlignMB435AllRawSignal")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,dataValue from hg19.burgeRnaSeqGemMapperAlignMB435AllRawSignal where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[5];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".dataValue",DoubleCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "burgeRnaSeqGemMapperAlignMB435AllRawSignal";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[5];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new DoubleCell(row.getDouble("dataValue"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.burgeRnaSeqGemMapperAlignMCF7")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,score,strand,thickStart,thickEnd,reserved,blockCount,blockSizes,chromStarts from hg19.burgeRnaSeqGemMapperAlignMCF7 where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[13];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".score",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".strand",StringCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".thickStart",IntCell.TYPE).createSpec();
		colSpecs[8]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".thickEnd",IntCell.TYPE).createSpec();
		colSpecs[9]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".reserved",IntCell.TYPE).createSpec();
		colSpecs[10]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".blockCount",IntCell.TYPE).createSpec();
		colSpecs[11]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".blockSizes",StringCell.TYPE).createSpec();
		colSpecs[12]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStarts",StringCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "burgeRnaSeqGemMapperAlignMCF7";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[13];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("score"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new StringCell(row.getString("strand"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new IntCell(row.getInt("thickStart"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
			cells[8]=new IntCell(row.getInt("thickEnd"));
			if(row.wasNull()) cells[8]=DataType.getMissingCell();
			cells[9]=new IntCell(row.getInt("reserved"));
			if(row.wasNull()) cells[9]=DataType.getMissingCell();
			cells[10]=new IntCell(row.getInt("blockCount"));
			if(row.wasNull()) cells[10]=DataType.getMissingCell();
			cells[11]=new StringCell(row.getString("blockSizes"));
			if(row.wasNull()) cells[11]=DataType.getMissingCell();
			cells[12]=new StringCell(row.getString("chromStarts"));
			if(row.wasNull()) cells[12]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.burgeRnaSeqGemMapperAlignMCF7AllRawSignal")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,dataValue from hg19.burgeRnaSeqGemMapperAlignMCF7AllRawSignal where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[5];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".dataValue",DoubleCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "burgeRnaSeqGemMapperAlignMCF7AllRawSignal";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[5];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new DoubleCell(row.getDouble("dataValue"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.burgeRnaSeqGemMapperAlignSkelMuscle")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,score,strand,thickStart,thickEnd,reserved,blockCount,blockSizes,chromStarts from hg19.burgeRnaSeqGemMapperAlignSkelMuscle where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[13];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".score",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".strand",StringCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".thickStart",IntCell.TYPE).createSpec();
		colSpecs[8]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".thickEnd",IntCell.TYPE).createSpec();
		colSpecs[9]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".reserved",IntCell.TYPE).createSpec();
		colSpecs[10]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".blockCount",IntCell.TYPE).createSpec();
		colSpecs[11]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".blockSizes",StringCell.TYPE).createSpec();
		colSpecs[12]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStarts",StringCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "burgeRnaSeqGemMapperAlignSkelMuscle";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[13];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("score"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new StringCell(row.getString("strand"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new IntCell(row.getInt("thickStart"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
			cells[8]=new IntCell(row.getInt("thickEnd"));
			if(row.wasNull()) cells[8]=DataType.getMissingCell();
			cells[9]=new IntCell(row.getInt("reserved"));
			if(row.wasNull()) cells[9]=DataType.getMissingCell();
			cells[10]=new IntCell(row.getInt("blockCount"));
			if(row.wasNull()) cells[10]=DataType.getMissingCell();
			cells[11]=new StringCell(row.getString("blockSizes"));
			if(row.wasNull()) cells[11]=DataType.getMissingCell();
			cells[12]=new StringCell(row.getString("chromStarts"));
			if(row.wasNull()) cells[12]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.burgeRnaSeqGemMapperAlignSkelMuscleAllRawSignal")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,dataValue from hg19.burgeRnaSeqGemMapperAlignSkelMuscleAllRawSignal where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[5];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".dataValue",DoubleCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "burgeRnaSeqGemMapperAlignSkelMuscleAllRawSignal";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[5];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new DoubleCell(row.getDouble("dataValue"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.burgeRnaSeqGemMapperAlignT47D")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,score,strand,thickStart,thickEnd,reserved,blockCount,blockSizes,chromStarts from hg19.burgeRnaSeqGemMapperAlignT47D where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[13];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".score",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".strand",StringCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".thickStart",IntCell.TYPE).createSpec();
		colSpecs[8]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".thickEnd",IntCell.TYPE).createSpec();
		colSpecs[9]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".reserved",IntCell.TYPE).createSpec();
		colSpecs[10]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".blockCount",IntCell.TYPE).createSpec();
		colSpecs[11]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".blockSizes",StringCell.TYPE).createSpec();
		colSpecs[12]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStarts",StringCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "burgeRnaSeqGemMapperAlignT47D";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[13];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("score"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new StringCell(row.getString("strand"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new IntCell(row.getInt("thickStart"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
			cells[8]=new IntCell(row.getInt("thickEnd"));
			if(row.wasNull()) cells[8]=DataType.getMissingCell();
			cells[9]=new IntCell(row.getInt("reserved"));
			if(row.wasNull()) cells[9]=DataType.getMissingCell();
			cells[10]=new IntCell(row.getInt("blockCount"));
			if(row.wasNull()) cells[10]=DataType.getMissingCell();
			cells[11]=new StringCell(row.getString("blockSizes"));
			if(row.wasNull()) cells[11]=DataType.getMissingCell();
			cells[12]=new StringCell(row.getString("chromStarts"));
			if(row.wasNull()) cells[12]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.burgeRnaSeqGemMapperAlignT47DAllRawSignal")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,dataValue from hg19.burgeRnaSeqGemMapperAlignT47DAllRawSignal where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[5];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".dataValue",DoubleCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "burgeRnaSeqGemMapperAlignT47DAllRawSignal";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[5];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new DoubleCell(row.getDouble("dataValue"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.burgeRnaSeqGemMapperAlignTestes")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,score,strand,thickStart,thickEnd,reserved,blockCount,blockSizes,chromStarts from hg19.burgeRnaSeqGemMapperAlignTestes where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[13];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".score",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".strand",StringCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".thickStart",IntCell.TYPE).createSpec();
		colSpecs[8]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".thickEnd",IntCell.TYPE).createSpec();
		colSpecs[9]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".reserved",IntCell.TYPE).createSpec();
		colSpecs[10]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".blockCount",IntCell.TYPE).createSpec();
		colSpecs[11]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".blockSizes",StringCell.TYPE).createSpec();
		colSpecs[12]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStarts",StringCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "burgeRnaSeqGemMapperAlignTestes";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[13];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("score"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new StringCell(row.getString("strand"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new IntCell(row.getInt("thickStart"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
			cells[8]=new IntCell(row.getInt("thickEnd"));
			if(row.wasNull()) cells[8]=DataType.getMissingCell();
			cells[9]=new IntCell(row.getInt("reserved"));
			if(row.wasNull()) cells[9]=DataType.getMissingCell();
			cells[10]=new IntCell(row.getInt("blockCount"));
			if(row.wasNull()) cells[10]=DataType.getMissingCell();
			cells[11]=new StringCell(row.getString("blockSizes"));
			if(row.wasNull()) cells[11]=DataType.getMissingCell();
			cells[12]=new StringCell(row.getString("chromStarts"));
			if(row.wasNull()) cells[12]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.burgeRnaSeqGemMapperAlignTestesAllRawSignal")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,dataValue from hg19.burgeRnaSeqGemMapperAlignTestesAllRawSignal where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[5];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".dataValue",DoubleCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "burgeRnaSeqGemMapperAlignTestesAllRawSignal";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[5];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new DoubleCell(row.getDouble("dataValue"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.ccdsGene")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 2;}
public int getChromStartColumn() { return 4;}
public int getChromEndColumn() { return 5;}
public String getPreparedStatement() { return "select bin,name,chrom,strand,txStart,txEnd,cdsStart,cdsEnd,exonCount,exonStarts,exonEnds,score,name2,cdsStartStat,cdsEndStat,exonFrames from hg19.ccdsGene where chrom=? and not(txEnd<=? or txStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[16];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".strand",StringCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".txStart",IntCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".txEnd",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".cdsStart",IntCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".cdsEnd",IntCell.TYPE).createSpec();
		colSpecs[8]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".exonCount",IntCell.TYPE).createSpec();
		colSpecs[9]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".exonStarts",StringCell.TYPE).createSpec();
		colSpecs[10]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".exonEnds",StringCell.TYPE).createSpec();
		colSpecs[11]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".score",IntCell.TYPE).createSpec();
		colSpecs[12]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name2",StringCell.TYPE).createSpec();
		colSpecs[13]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".cdsStartStat",StringCell.TYPE).createSpec();
		colSpecs[14]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".cdsEndStat",StringCell.TYPE).createSpec();
		colSpecs[15]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".exonFrames",StringCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "ccdsGene";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[16];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new StringCell(row.getString("strand"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new IntCell(row.getInt("txStart"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("txEnd"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new IntCell(row.getInt("cdsStart"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new IntCell(row.getInt("cdsEnd"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
			cells[8]=new IntCell(row.getInt("exonCount"));
			if(row.wasNull()) cells[8]=DataType.getMissingCell();
			cells[9]=new StringCell(row.getString("exonStarts"));
			if(row.wasNull()) cells[9]=DataType.getMissingCell();
			cells[10]=new StringCell(row.getString("exonEnds"));
			if(row.wasNull()) cells[10]=DataType.getMissingCell();
			cells[11]=new IntCell(row.getInt("score"));
			if(row.wasNull()) cells[11]=DataType.getMissingCell();
			cells[12]=new StringCell(row.getString("name2"));
			if(row.wasNull()) cells[12]=DataType.getMissingCell();
			cells[13]=new StringCell(row.getString("cdsStartStat"));
			if(row.wasNull()) cells[13]=DataType.getMissingCell();
			cells[14]=new StringCell(row.getString("cdsEndStat"));
			if(row.wasNull()) cells[14]=DataType.getMissingCell();
			cells[15]=new StringCell(row.getString("exonFrames"));
			if(row.wasNull()) cells[15]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.ccdsKgMap")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return -1;}
public int getChromColumn() { return 2;}
public int getChromStartColumn() { return 3;}
public int getChromEndColumn() { return 4;}
public String getPreparedStatement() { return "select ccdsId,geneId,chrom,chromStart,chromEnd,cdsSimilarity from hg19.ccdsKgMap where chrom=? and not(chromEnd<=? or chromStart>?)";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[6];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".ccdsId",StringCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".geneId",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".cdsSimilarity",DoubleCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "ccdsKgMap";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[6];
			cells[0]=new StringCell(row.getString("ccdsId"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("geneId"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new DoubleCell(row.getDouble("cdsSimilarity"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.cgapSage")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,score,strand,thickStart,thickEnd,numLibs,libIds,freqs,tagTpms,numSnps,snps from hg19.cgapSage where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[15];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".score",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".strand",StringCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".thickStart",IntCell.TYPE).createSpec();
		colSpecs[8]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".thickEnd",IntCell.TYPE).createSpec();
		colSpecs[9]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".numLibs",IntCell.TYPE).createSpec();
		colSpecs[10]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".libIds",StringCell.TYPE).createSpec();
		colSpecs[11]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".freqs",StringCell.TYPE).createSpec();
		colSpecs[12]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".tagTpms",StringCell.TYPE).createSpec();
		colSpecs[13]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".numSnps",IntCell.TYPE).createSpec();
		colSpecs[14]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".snps",StringCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "cgapSage";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[15];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("score"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new StringCell(row.getString("strand"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new IntCell(row.getInt("thickStart"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
			cells[8]=new IntCell(row.getInt("thickEnd"));
			if(row.wasNull()) cells[8]=DataType.getMissingCell();
			cells[9]=new IntCell(row.getInt("numLibs"));
			if(row.wasNull()) cells[9]=DataType.getMissingCell();
			cells[10]=new StringCell(row.getString("libIds"));
			if(row.wasNull()) cells[10]=DataType.getMissingCell();
			cells[11]=new StringCell(row.getString("freqs"));
			if(row.wasNull()) cells[11]=DataType.getMissingCell();
			cells[12]=new StringCell(row.getString("tagTpms"));
			if(row.wasNull()) cells[12]=DataType.getMissingCell();
			cells[13]=new IntCell(row.getInt("numSnps"));
			if(row.wasNull()) cells[13]=DataType.getMissingCell();
			cells[14]=new StringCell(row.getString("snps"));
			if(row.wasNull()) cells[14]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.consIndelsHgMmCanFam")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,score from hg19.consIndelsHgMmCanFam where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[6];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".score",IntCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "consIndelsHgMmCanFam";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[6];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("score"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.cpgIslandExt")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,length,cpgNum,gcNum,perCpg,perGc,obsExp from hg19.cpgIslandExt where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[11];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".length",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".cpgNum",IntCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".gcNum",IntCell.TYPE).createSpec();
		colSpecs[8]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".perCpg",DoubleCell.TYPE).createSpec();
		colSpecs[9]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".perGc",DoubleCell.TYPE).createSpec();
		colSpecs[10]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".obsExp",DoubleCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "cpgIslandExt";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[11];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("length"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new IntCell(row.getInt("cpgNum"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new IntCell(row.getInt("gcNum"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
			cells[8]=new DoubleCell(row.getDouble("perCpg"));
			if(row.wasNull()) cells[8]=DataType.getMissingCell();
			cells[9]=new DoubleCell(row.getDouble("perGc"));
			if(row.wasNull()) cells[9]=DataType.getMissingCell();
			cells[10]=new DoubleCell(row.getDouble("obsExp"));
			if(row.wasNull()) cells[10]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.ctgPos")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return -1;}
public int getChromColumn() { return 2;}
public int getChromStartColumn() { return 3;}
public int getChromEndColumn() { return 4;}
public String getPreparedStatement() { return "select contig,size,chrom,chromStart,chromEnd from hg19.ctgPos where chrom=? and not(chromEnd<=? or chromStart>?)";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[5];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".contig",StringCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".size",IntCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "ctgPos";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[5];
			cells[0]=new StringCell(row.getString("contig"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new IntCell(row.getInt("size"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.ctgPos2")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return -1;}
public int getChromColumn() { return 2;}
public int getChromStartColumn() { return 3;}
public int getChromEndColumn() { return 4;}
public String getPreparedStatement() { return "select contig,size,chrom,chromStart,chromEnd,type from hg19.ctgPos2 where chrom=? and not(chromEnd<=? or chromStart>?)";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[6];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".contig",StringCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".size",IntCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".type",StringCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "ctgPos2";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[6];
			cells[0]=new StringCell(row.getString("contig"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new IntCell(row.getInt("size"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new StringCell(row.getString("type"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.cytoBand")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return -1;}
public int getChromColumn() { return 0;}
public int getChromStartColumn() { return 1;}
public int getChromEndColumn() { return 2;}
public String getPreparedStatement() { return "select chrom,chromStart,chromEnd,name,gieStain from hg19.cytoBand where chrom=? and not(chromEnd<=? or chromStart>?)";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[5];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".gieStain",StringCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "cytoBand";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[5];
			cells[0]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("gieStain"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.cytoBandIdeo")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return -1;}
public int getChromColumn() { return 0;}
public int getChromStartColumn() { return 1;}
public int getChromEndColumn() { return 2;}
public String getPreparedStatement() { return "select chrom,chromStart,chromEnd,name,gieStain from hg19.cytoBandIdeo where chrom=? and not(chromEnd<=? or chromStart>?)";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[5];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".gieStain",StringCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "cytoBandIdeo";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[5];
			cells[0]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("gieStain"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.darned")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,score,strand,thickStart,thickEnd,reserved from hg19.darned where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[10];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".score",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".strand",StringCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".thickStart",IntCell.TYPE).createSpec();
		colSpecs[8]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".thickEnd",IntCell.TYPE).createSpec();
		colSpecs[9]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".reserved",IntCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "darned";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[10];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("score"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new StringCell(row.getString("strand"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new IntCell(row.getInt("thickStart"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
			cells[8]=new IntCell(row.getInt("thickEnd"));
			if(row.wasNull()) cells[8]=DataType.getMissingCell();
			cells[9]=new IntCell(row.getInt("reserved"));
			if(row.wasNull()) cells[9]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.dgv")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,score,strand,thickStart,thickEnd,itemRgb,landmark,varType,reference,pubMedId,method,sample from hg19.dgv where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[16];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".score",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".strand",StringCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".thickStart",IntCell.TYPE).createSpec();
		colSpecs[8]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".thickEnd",IntCell.TYPE).createSpec();
		colSpecs[9]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".itemRgb",IntCell.TYPE).createSpec();
		colSpecs[10]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".landmark",StringCell.TYPE).createSpec();
		colSpecs[11]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".varType",StringCell.TYPE).createSpec();
		colSpecs[12]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".reference",StringCell.TYPE).createSpec();
		colSpecs[13]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".pubMedId",IntCell.TYPE).createSpec();
		colSpecs[14]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".method",StringCell.TYPE).createSpec();
		colSpecs[15]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".sample",StringCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "dgv";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[16];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("score"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new StringCell(row.getString("strand"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new IntCell(row.getInt("thickStart"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
			cells[8]=new IntCell(row.getInt("thickEnd"));
			if(row.wasNull()) cells[8]=DataType.getMissingCell();
			cells[9]=new IntCell(row.getInt("itemRgb"));
			if(row.wasNull()) cells[9]=DataType.getMissingCell();
			cells[10]=new StringCell(row.getString("landmark"));
			if(row.wasNull()) cells[10]=DataType.getMissingCell();
			cells[11]=new StringCell(row.getString("varType"));
			if(row.wasNull()) cells[11]=DataType.getMissingCell();
			cells[12]=new StringCell(row.getString("reference"));
			if(row.wasNull()) cells[12]=DataType.getMissingCell();
			cells[13]=new IntCell(row.getInt("pubMedId"));
			if(row.wasNull()) cells[13]=DataType.getMissingCell();
			cells[14]=new StringCell(row.getString("method"));
			if(row.wasNull()) cells[14]=DataType.getMissingCell();
			cells[15]=new StringCell(row.getString("sample"));
			if(row.wasNull()) cells[15]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.eioJcviNASNeg")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd from hg19.eioJcviNASNeg where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[4];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "eioJcviNASNeg";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[4];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.eioJcviNASPos")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd from hg19.eioJcviNASPos where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[4];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "eioJcviNASPos";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[4];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.ensGene")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 2;}
public int getChromStartColumn() { return 4;}
public int getChromEndColumn() { return 5;}
public String getPreparedStatement() { return "select bin,name,chrom,strand,txStart,txEnd,cdsStart,cdsEnd,exonCount,exonStarts,exonEnds,score,name2,cdsStartStat,cdsEndStat,exonFrames from hg19.ensGene where chrom=? and not(txEnd<=? or txStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[16];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".strand",StringCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".txStart",IntCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".txEnd",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".cdsStart",IntCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".cdsEnd",IntCell.TYPE).createSpec();
		colSpecs[8]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".exonCount",IntCell.TYPE).createSpec();
		colSpecs[9]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".exonStarts",StringCell.TYPE).createSpec();
		colSpecs[10]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".exonEnds",StringCell.TYPE).createSpec();
		colSpecs[11]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".score",IntCell.TYPE).createSpec();
		colSpecs[12]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name2",StringCell.TYPE).createSpec();
		colSpecs[13]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".cdsStartStat",StringCell.TYPE).createSpec();
		colSpecs[14]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".cdsEndStat",StringCell.TYPE).createSpec();
		colSpecs[15]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".exonFrames",StringCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "ensGene";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[16];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new StringCell(row.getString("strand"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new IntCell(row.getInt("txStart"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("txEnd"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new IntCell(row.getInt("cdsStart"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new IntCell(row.getInt("cdsEnd"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
			cells[8]=new IntCell(row.getInt("exonCount"));
			if(row.wasNull()) cells[8]=DataType.getMissingCell();
			cells[9]=new StringCell(row.getString("exonStarts"));
			if(row.wasNull()) cells[9]=DataType.getMissingCell();
			cells[10]=new StringCell(row.getString("exonEnds"));
			if(row.wasNull()) cells[10]=DataType.getMissingCell();
			cells[11]=new IntCell(row.getInt("score"));
			if(row.wasNull()) cells[11]=DataType.getMissingCell();
			cells[12]=new StringCell(row.getString("name2"));
			if(row.wasNull()) cells[12]=DataType.getMissingCell();
			cells[13]=new StringCell(row.getString("cdsStartStat"));
			if(row.wasNull()) cells[13]=DataType.getMissingCell();
			cells[14]=new StringCell(row.getString("cdsEndStat"));
			if(row.wasNull()) cells[14]=DataType.getMissingCell();
			cells[15]=new StringCell(row.getString("exonFrames"));
			if(row.wasNull()) cells[15]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.estOrientInfo")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,intronOrientation,sizePolyA,revSizePolyA,signalPos,revSignalPos from hg19.estOrientInfo where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[10];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".intronOrientation",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".sizePolyA",IntCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".revSizePolyA",IntCell.TYPE).createSpec();
		colSpecs[8]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".signalPos",IntCell.TYPE).createSpec();
		colSpecs[9]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".revSignalPos",IntCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "estOrientInfo";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[10];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("intronOrientation"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new IntCell(row.getInt("sizePolyA"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new IntCell(row.getInt("revSizePolyA"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
			cells[8]=new IntCell(row.getInt("signalPos"));
			if(row.wasNull()) cells[8]=DataType.getMissingCell();
			cells[9]=new IntCell(row.getInt("revSignalPos"));
			if(row.wasNull()) cells[9]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.evofold")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,score,strand,size,secStr,conf from hg19.evofold where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[10];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".score",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".strand",StringCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".size",IntCell.TYPE).createSpec();
		colSpecs[8]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".secStr",StringCell.TYPE).createSpec();
		colSpecs[9]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".conf",StringCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "evofold";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[10];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("score"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new StringCell(row.getString("strand"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new IntCell(row.getInt("size"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
			cells[8]=new StringCell(row.getString("secStr"));
			if(row.wasNull()) cells[8]=DataType.getMissingCell();
			cells[9]=new StringCell(row.getString("conf"));
			if(row.wasNull()) cells[9]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.exoniphy")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 2;}
public int getChromStartColumn() { return 4;}
public int getChromEndColumn() { return 5;}
public String getPreparedStatement() { return "select bin,name,chrom,strand,txStart,txEnd,cdsStart,cdsEnd,exonCount,exonStarts,exonEnds,score,name2,cdsStartStat,cdsEndStat,exonFrames from hg19.exoniphy where chrom=? and not(txEnd<=? or txStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[16];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".strand",StringCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".txStart",IntCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".txEnd",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".cdsStart",IntCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".cdsEnd",IntCell.TYPE).createSpec();
		colSpecs[8]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".exonCount",IntCell.TYPE).createSpec();
		colSpecs[9]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".exonStarts",StringCell.TYPE).createSpec();
		colSpecs[10]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".exonEnds",StringCell.TYPE).createSpec();
		colSpecs[11]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".score",IntCell.TYPE).createSpec();
		colSpecs[12]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name2",StringCell.TYPE).createSpec();
		colSpecs[13]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".cdsStartStat",StringCell.TYPE).createSpec();
		colSpecs[14]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".cdsEndStat",StringCell.TYPE).createSpec();
		colSpecs[15]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".exonFrames",StringCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "exoniphy";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[16];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new StringCell(row.getString("strand"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new IntCell(row.getInt("txStart"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("txEnd"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new IntCell(row.getInt("cdsStart"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new IntCell(row.getInt("cdsEnd"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
			cells[8]=new IntCell(row.getInt("exonCount"));
			if(row.wasNull()) cells[8]=DataType.getMissingCell();
			cells[9]=new StringCell(row.getString("exonStarts"));
			if(row.wasNull()) cells[9]=DataType.getMissingCell();
			cells[10]=new StringCell(row.getString("exonEnds"));
			if(row.wasNull()) cells[10]=DataType.getMissingCell();
			cells[11]=new IntCell(row.getInt("score"));
			if(row.wasNull()) cells[11]=DataType.getMissingCell();
			cells[12]=new StringCell(row.getString("name2"));
			if(row.wasNull()) cells[12]=DataType.getMissingCell();
			cells[13]=new StringCell(row.getString("cdsStartStat"));
			if(row.wasNull()) cells[13]=DataType.getMissingCell();
			cells[14]=new StringCell(row.getString("cdsEndStat"));
			if(row.wasNull()) cells[14]=DataType.getMissingCell();
			cells[15]=new StringCell(row.getString("exonFrames"));
			if(row.wasNull()) cells[15]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.fishClones")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return -1;}
public int getChromColumn() { return 0;}
public int getChromStartColumn() { return 1;}
public int getChromEndColumn() { return 2;}
public String getPreparedStatement() { return "select chrom,chromStart,chromEnd,name,score,placeCount,bandStarts,bandEnds,labs,placeType,accCount,accNames,stsCount,stsNames,beCount,beNames from hg19.fishClones where chrom=? and not(chromEnd<=? or chromStart>?)";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[16];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".score",IntCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".placeCount",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bandStarts",StringCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bandEnds",StringCell.TYPE).createSpec();
		colSpecs[8]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".labs",StringCell.TYPE).createSpec();
		colSpecs[9]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".placeType",StringCell.TYPE).createSpec();
		colSpecs[10]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".accCount",IntCell.TYPE).createSpec();
		colSpecs[11]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".accNames",StringCell.TYPE).createSpec();
		colSpecs[12]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".stsCount",IntCell.TYPE).createSpec();
		colSpecs[13]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".stsNames",StringCell.TYPE).createSpec();
		colSpecs[14]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".beCount",IntCell.TYPE).createSpec();
		colSpecs[15]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".beNames",StringCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "fishClones";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[16];
			cells[0]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new IntCell(row.getInt("score"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("placeCount"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new StringCell(row.getString("bandStarts"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new StringCell(row.getString("bandEnds"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
			cells[8]=new StringCell(row.getString("labs"));
			if(row.wasNull()) cells[8]=DataType.getMissingCell();
			cells[9]=new StringCell(row.getString("placeType"));
			if(row.wasNull()) cells[9]=DataType.getMissingCell();
			cells[10]=new IntCell(row.getInt("accCount"));
			if(row.wasNull()) cells[10]=DataType.getMissingCell();
			cells[11]=new StringCell(row.getString("accNames"));
			if(row.wasNull()) cells[11]=DataType.getMissingCell();
			cells[12]=new IntCell(row.getInt("stsCount"));
			if(row.wasNull()) cells[12]=DataType.getMissingCell();
			cells[13]=new StringCell(row.getString("stsNames"));
			if(row.wasNull()) cells[13]=DataType.getMissingCell();
			cells[14]=new IntCell(row.getInt("beCount"));
			if(row.wasNull()) cells[14]=DataType.getMissingCell();
			cells[15]=new StringCell(row.getString("beNames"));
			if(row.wasNull()) cells[15]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.fosEndPairs")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,score,strand,pslTable,lfCount,lfStarts,lfSizes,lfNames from hg19.fosEndPairs where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[12];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".score",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".strand",StringCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".pslTable",StringCell.TYPE).createSpec();
		colSpecs[8]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".lfCount",IntCell.TYPE).createSpec();
		colSpecs[9]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".lfStarts",StringCell.TYPE).createSpec();
		colSpecs[10]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".lfSizes",StringCell.TYPE).createSpec();
		colSpecs[11]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".lfNames",StringCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "fosEndPairs";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[12];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("score"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new StringCell(row.getString("strand"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new StringCell(row.getString("pslTable"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
			cells[8]=new IntCell(row.getInt("lfCount"));
			if(row.wasNull()) cells[8]=DataType.getMissingCell();
			cells[9]=new StringCell(row.getString("lfStarts"));
			if(row.wasNull()) cells[9]=DataType.getMissingCell();
			cells[10]=new StringCell(row.getString("lfSizes"));
			if(row.wasNull()) cells[10]=DataType.getMissingCell();
			cells[11]=new StringCell(row.getString("lfNames"));
			if(row.wasNull()) cells[11]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.gad")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return -1;}
public int getChromColumn() { return 0;}
public int getChromStartColumn() { return 1;}
public int getChromEndColumn() { return 2;}
public String getPreparedStatement() { return "select chrom,chromStart,chromEnd,name from hg19.gad where chrom=? and not(chromEnd<=? or chromStart>?)";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[4];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "gad";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[4];
			cells[0]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.gap")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,ix,n,size,type,bridge from hg19.gap where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[9];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".ix",IntCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".n",StringCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".size",IntCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".type",StringCell.TYPE).createSpec();
		colSpecs[8]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bridge",StringCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "gap";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[9];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new IntCell(row.getInt("ix"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new StringCell(row.getString("n"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new IntCell(row.getInt("size"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new StringCell(row.getString("type"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
			cells[8]=new StringCell(row.getString("bridge"));
			if(row.wasNull()) cells[8]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.gc5Base")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,span,count,offset,file,lowerLimit,dataRange,validCount,sumData,sumSquares from hg19.gc5Base where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[14];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".span",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".count",IntCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".offset",IntCell.TYPE).createSpec();
		colSpecs[8]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".file",StringCell.TYPE).createSpec();
		colSpecs[9]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".lowerLimit",DoubleCell.TYPE).createSpec();
		colSpecs[10]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".dataRange",DoubleCell.TYPE).createSpec();
		colSpecs[11]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".validCount",IntCell.TYPE).createSpec();
		colSpecs[12]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".sumData",DoubleCell.TYPE).createSpec();
		colSpecs[13]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".sumSquares",DoubleCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "gc5Base";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[14];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("span"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new IntCell(row.getInt("count"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new IntCell(row.getInt("offset"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
			cells[8]=new StringCell(row.getString("file"));
			if(row.wasNull()) cells[8]=DataType.getMissingCell();
			cells[9]=new DoubleCell(row.getDouble("lowerLimit"));
			if(row.wasNull()) cells[9]=DataType.getMissingCell();
			cells[10]=new DoubleCell(row.getDouble("dataRange"));
			if(row.wasNull()) cells[10]=DataType.getMissingCell();
			cells[11]=new IntCell(row.getInt("validCount"));
			if(row.wasNull()) cells[11]=DataType.getMissingCell();
			cells[12]=new DoubleCell(row.getDouble("sumData"));
			if(row.wasNull()) cells[12]=DataType.getMissingCell();
			cells[13]=new DoubleCell(row.getDouble("sumSquares"));
			if(row.wasNull()) cells[13]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.geneid")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 2;}
public int getChromStartColumn() { return 4;}
public int getChromEndColumn() { return 5;}
public String getPreparedStatement() { return "select bin,name,chrom,strand,txStart,txEnd,cdsStart,cdsEnd,exonCount,exonStarts,exonEnds,score,name2,cdsStartStat,cdsEndStat,exonFrames from hg19.geneid where chrom=? and not(txEnd<=? or txStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[16];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".strand",StringCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".txStart",IntCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".txEnd",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".cdsStart",IntCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".cdsEnd",IntCell.TYPE).createSpec();
		colSpecs[8]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".exonCount",IntCell.TYPE).createSpec();
		colSpecs[9]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".exonStarts",StringCell.TYPE).createSpec();
		colSpecs[10]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".exonEnds",StringCell.TYPE).createSpec();
		colSpecs[11]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".score",IntCell.TYPE).createSpec();
		colSpecs[12]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name2",StringCell.TYPE).createSpec();
		colSpecs[13]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".cdsStartStat",StringCell.TYPE).createSpec();
		colSpecs[14]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".cdsEndStat",StringCell.TYPE).createSpec();
		colSpecs[15]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".exonFrames",StringCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "geneid";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[16];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new StringCell(row.getString("strand"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new IntCell(row.getInt("txStart"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("txEnd"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new IntCell(row.getInt("cdsStart"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new IntCell(row.getInt("cdsEnd"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
			cells[8]=new IntCell(row.getInt("exonCount"));
			if(row.wasNull()) cells[8]=DataType.getMissingCell();
			cells[9]=new StringCell(row.getString("exonStarts"));
			if(row.wasNull()) cells[9]=DataType.getMissingCell();
			cells[10]=new StringCell(row.getString("exonEnds"));
			if(row.wasNull()) cells[10]=DataType.getMissingCell();
			cells[11]=new IntCell(row.getInt("score"));
			if(row.wasNull()) cells[11]=DataType.getMissingCell();
			cells[12]=new StringCell(row.getString("name2"));
			if(row.wasNull()) cells[12]=DataType.getMissingCell();
			cells[13]=new StringCell(row.getString("cdsStartStat"));
			if(row.wasNull()) cells[13]=DataType.getMissingCell();
			cells[14]=new StringCell(row.getString("cdsEndStat"));
			if(row.wasNull()) cells[14]=DataType.getMissingCell();
			cells[15]=new StringCell(row.getString("exonFrames"));
			if(row.wasNull()) cells[15]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.genomicSuperDups")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,score,strand,otherChrom,otherStart,otherEnd,otherSize,uid,posBasesHit,testResult,verdict,chits,ccov,alignfile,alignL,indelN,indelS,alignB,matchB,mismatchB,transitionsB,transversionsB,fracMatch,fracMatchIndel,jcK,k2K from hg19.genomicSuperDups where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[30];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".score",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".strand",StringCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".otherChrom",StringCell.TYPE).createSpec();
		colSpecs[8]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".otherStart",IntCell.TYPE).createSpec();
		colSpecs[9]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".otherEnd",IntCell.TYPE).createSpec();
		colSpecs[10]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".otherSize",IntCell.TYPE).createSpec();
		colSpecs[11]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".uid",IntCell.TYPE).createSpec();
		colSpecs[12]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".posBasesHit",IntCell.TYPE).createSpec();
		colSpecs[13]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".testResult",StringCell.TYPE).createSpec();
		colSpecs[14]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".verdict",StringCell.TYPE).createSpec();
		colSpecs[15]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chits",StringCell.TYPE).createSpec();
		colSpecs[16]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".ccov",StringCell.TYPE).createSpec();
		colSpecs[17]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".alignfile",StringCell.TYPE).createSpec();
		colSpecs[18]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".alignL",IntCell.TYPE).createSpec();
		colSpecs[19]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".indelN",IntCell.TYPE).createSpec();
		colSpecs[20]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".indelS",IntCell.TYPE).createSpec();
		colSpecs[21]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".alignB",IntCell.TYPE).createSpec();
		colSpecs[22]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".matchB",IntCell.TYPE).createSpec();
		colSpecs[23]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".mismatchB",IntCell.TYPE).createSpec();
		colSpecs[24]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".transitionsB",IntCell.TYPE).createSpec();
		colSpecs[25]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".transversionsB",IntCell.TYPE).createSpec();
		colSpecs[26]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".fracMatch",DoubleCell.TYPE).createSpec();
		colSpecs[27]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".fracMatchIndel",DoubleCell.TYPE).createSpec();
		colSpecs[28]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".jcK",DoubleCell.TYPE).createSpec();
		colSpecs[29]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".k2K",DoubleCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "genomicSuperDups";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[30];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("score"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new StringCell(row.getString("strand"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new StringCell(row.getString("otherChrom"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
			cells[8]=new IntCell(row.getInt("otherStart"));
			if(row.wasNull()) cells[8]=DataType.getMissingCell();
			cells[9]=new IntCell(row.getInt("otherEnd"));
			if(row.wasNull()) cells[9]=DataType.getMissingCell();
			cells[10]=new IntCell(row.getInt("otherSize"));
			if(row.wasNull()) cells[10]=DataType.getMissingCell();
			cells[11]=new IntCell(row.getInt("uid"));
			if(row.wasNull()) cells[11]=DataType.getMissingCell();
			cells[12]=new IntCell(row.getInt("posBasesHit"));
			if(row.wasNull()) cells[12]=DataType.getMissingCell();
			cells[13]=new StringCell(row.getString("testResult"));
			if(row.wasNull()) cells[13]=DataType.getMissingCell();
			cells[14]=new StringCell(row.getString("verdict"));
			if(row.wasNull()) cells[14]=DataType.getMissingCell();
			cells[15]=new StringCell(row.getString("chits"));
			if(row.wasNull()) cells[15]=DataType.getMissingCell();
			cells[16]=new StringCell(row.getString("ccov"));
			if(row.wasNull()) cells[16]=DataType.getMissingCell();
			cells[17]=new StringCell(row.getString("alignfile"));
			if(row.wasNull()) cells[17]=DataType.getMissingCell();
			cells[18]=new IntCell(row.getInt("alignL"));
			if(row.wasNull()) cells[18]=DataType.getMissingCell();
			cells[19]=new IntCell(row.getInt("indelN"));
			if(row.wasNull()) cells[19]=DataType.getMissingCell();
			cells[20]=new IntCell(row.getInt("indelS"));
			if(row.wasNull()) cells[20]=DataType.getMissingCell();
			cells[21]=new IntCell(row.getInt("alignB"));
			if(row.wasNull()) cells[21]=DataType.getMissingCell();
			cells[22]=new IntCell(row.getInt("matchB"));
			if(row.wasNull()) cells[22]=DataType.getMissingCell();
			cells[23]=new IntCell(row.getInt("mismatchB"));
			if(row.wasNull()) cells[23]=DataType.getMissingCell();
			cells[24]=new IntCell(row.getInt("transitionsB"));
			if(row.wasNull()) cells[24]=DataType.getMissingCell();
			cells[25]=new IntCell(row.getInt("transversionsB"));
			if(row.wasNull()) cells[25]=DataType.getMissingCell();
			cells[26]=new DoubleCell(row.getDouble("fracMatch"));
			if(row.wasNull()) cells[26]=DataType.getMissingCell();
			cells[27]=new DoubleCell(row.getDouble("fracMatchIndel"));
			if(row.wasNull()) cells[27]=DataType.getMissingCell();
			cells[28]=new DoubleCell(row.getDouble("jcK"));
			if(row.wasNull()) cells[28]=DataType.getMissingCell();
			cells[29]=new DoubleCell(row.getDouble("k2K"));
			if(row.wasNull()) cells[29]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.genscan")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 2;}
public int getChromStartColumn() { return 4;}
public int getChromEndColumn() { return 5;}
public String getPreparedStatement() { return "select bin,name,chrom,strand,txStart,txEnd,cdsStart,cdsEnd,exonCount,exonStarts,exonEnds from hg19.genscan where chrom=? and not(txEnd<=? or txStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[11];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".strand",StringCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".txStart",IntCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".txEnd",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".cdsStart",IntCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".cdsEnd",IntCell.TYPE).createSpec();
		colSpecs[8]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".exonCount",IntCell.TYPE).createSpec();
		colSpecs[9]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".exonStarts",StringCell.TYPE).createSpec();
		colSpecs[10]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".exonEnds",StringCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "genscan";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[11];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new StringCell(row.getString("strand"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new IntCell(row.getInt("txStart"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("txEnd"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new IntCell(row.getInt("cdsStart"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new IntCell(row.getInt("cdsEnd"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
			cells[8]=new IntCell(row.getInt("exonCount"));
			if(row.wasNull()) cells[8]=DataType.getMissingCell();
			cells[9]=new StringCell(row.getString("exonStarts"));
			if(row.wasNull()) cells[9]=DataType.getMissingCell();
			cells[10]=new StringCell(row.getString("exonEnds"));
			if(row.wasNull()) cells[10]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.gnfAtlas2")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,score,strand,thickStart,thickEnd,reserved,blockCount,blockSizes,chromStarts,expCount,expIds,expScores from hg19.gnfAtlas2 where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[16];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".score",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".strand",StringCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".thickStart",IntCell.TYPE).createSpec();
		colSpecs[8]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".thickEnd",IntCell.TYPE).createSpec();
		colSpecs[9]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".reserved",IntCell.TYPE).createSpec();
		colSpecs[10]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".blockCount",IntCell.TYPE).createSpec();
		colSpecs[11]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".blockSizes",StringCell.TYPE).createSpec();
		colSpecs[12]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStarts",StringCell.TYPE).createSpec();
		colSpecs[13]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".expCount",IntCell.TYPE).createSpec();
		colSpecs[14]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".expIds",StringCell.TYPE).createSpec();
		colSpecs[15]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".expScores",StringCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "gnfAtlas2";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[16];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("score"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new StringCell(row.getString("strand"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new IntCell(row.getInt("thickStart"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
			cells[8]=new IntCell(row.getInt("thickEnd"));
			if(row.wasNull()) cells[8]=DataType.getMissingCell();
			cells[9]=new IntCell(row.getInt("reserved"));
			if(row.wasNull()) cells[9]=DataType.getMissingCell();
			cells[10]=new IntCell(row.getInt("blockCount"));
			if(row.wasNull()) cells[10]=DataType.getMissingCell();
			cells[11]=new StringCell(row.getString("blockSizes"));
			if(row.wasNull()) cells[11]=DataType.getMissingCell();
			cells[12]=new StringCell(row.getString("chromStarts"));
			if(row.wasNull()) cells[12]=DataType.getMissingCell();
			cells[13]=new IntCell(row.getInt("expCount"));
			if(row.wasNull()) cells[13]=DataType.getMissingCell();
			cells[14]=new StringCell(row.getString("expIds"));
			if(row.wasNull()) cells[14]=DataType.getMissingCell();
			cells[15]=new StringCell(row.getString("expScores"));
			if(row.wasNull()) cells[15]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.gold")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,ix,type,frag,fragStart,fragEnd,strand from hg19.gold where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[10];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".ix",IntCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".type",StringCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".frag",StringCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".fragStart",IntCell.TYPE).createSpec();
		colSpecs[8]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".fragEnd",IntCell.TYPE).createSpec();
		colSpecs[9]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".strand",StringCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "gold";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[10];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new IntCell(row.getInt("ix"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new StringCell(row.getString("type"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new StringCell(row.getString("frag"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new IntCell(row.getInt("fragStart"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
			cells[8]=new IntCell(row.getInt("fragEnd"));
			if(row.wasNull()) cells[8]=DataType.getMissingCell();
			cells[9]=new StringCell(row.getString("strand"));
			if(row.wasNull()) cells[9]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.gwasCatalog")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,pubMedID,author,pubDate,journal,title,trait,initSample,replSample,region,genes,riskAllele,riskAlFreq,pValue,pValueDesc,orOrBeta,ci95,platform,cnv from hg19.gwasCatalog where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[23];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".pubMedID",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".author",StringCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".pubDate",StringCell.TYPE).createSpec();
		colSpecs[8]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".journal",StringCell.TYPE).createSpec();
		colSpecs[9]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".title",StringCell.TYPE).createSpec();
		colSpecs[10]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".trait",StringCell.TYPE).createSpec();
		colSpecs[11]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".initSample",StringCell.TYPE).createSpec();
		colSpecs[12]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".replSample",StringCell.TYPE).createSpec();
		colSpecs[13]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".region",StringCell.TYPE).createSpec();
		colSpecs[14]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".genes",StringCell.TYPE).createSpec();
		colSpecs[15]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".riskAllele",StringCell.TYPE).createSpec();
		colSpecs[16]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".riskAlFreq",StringCell.TYPE).createSpec();
		colSpecs[17]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".pValue",StringCell.TYPE).createSpec();
		colSpecs[18]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".pValueDesc",StringCell.TYPE).createSpec();
		colSpecs[19]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".orOrBeta",StringCell.TYPE).createSpec();
		colSpecs[20]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".ci95",StringCell.TYPE).createSpec();
		colSpecs[21]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".platform",StringCell.TYPE).createSpec();
		colSpecs[22]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".cnv",StringCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "gwasCatalog";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[23];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("pubMedID"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new StringCell(row.getString("author"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new StringCell(row.getString("pubDate"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
			cells[8]=new StringCell(row.getString("journal"));
			if(row.wasNull()) cells[8]=DataType.getMissingCell();
			cells[9]=new StringCell(row.getString("title"));
			if(row.wasNull()) cells[9]=DataType.getMissingCell();
			cells[10]=new StringCell(row.getString("trait"));
			if(row.wasNull()) cells[10]=DataType.getMissingCell();
			cells[11]=new StringCell(row.getString("initSample"));
			if(row.wasNull()) cells[11]=DataType.getMissingCell();
			cells[12]=new StringCell(row.getString("replSample"));
			if(row.wasNull()) cells[12]=DataType.getMissingCell();
			cells[13]=new StringCell(row.getString("region"));
			if(row.wasNull()) cells[13]=DataType.getMissingCell();
			cells[14]=new StringCell(row.getString("genes"));
			if(row.wasNull()) cells[14]=DataType.getMissingCell();
			cells[15]=new StringCell(row.getString("riskAllele"));
			if(row.wasNull()) cells[15]=DataType.getMissingCell();
			cells[16]=new StringCell(row.getString("riskAlFreq"));
			if(row.wasNull()) cells[16]=DataType.getMissingCell();
			cells[17]=new StringCell(row.getString("pValue"));
			if(row.wasNull()) cells[17]=DataType.getMissingCell();
			cells[18]=new StringCell(row.getString("pValueDesc"));
			if(row.wasNull()) cells[18]=DataType.getMissingCell();
			cells[19]=new StringCell(row.getString("orOrBeta"));
			if(row.wasNull()) cells[19]=DataType.getMissingCell();
			cells[20]=new StringCell(row.getString("ci95"));
			if(row.wasNull()) cells[20]=DataType.getMissingCell();
			cells[21]=new StringCell(row.getString("platform"));
			if(row.wasNull()) cells[21]=DataType.getMissingCell();
			cells[22]=new StringCell(row.getString("cnv"));
			if(row.wasNull()) cells[22]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.hapmapAllelesChimp")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,score,strand,refUCSC,observed,orthoChrom,orthoStart,orthoEnd,orthoStrand,orthoAllele from hg19.hapmapAllelesChimp where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[14];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".score",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".strand",StringCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".refUCSC",StringCell.TYPE).createSpec();
		colSpecs[8]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".observed",StringCell.TYPE).createSpec();
		colSpecs[9]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".orthoChrom",StringCell.TYPE).createSpec();
		colSpecs[10]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".orthoStart",IntCell.TYPE).createSpec();
		colSpecs[11]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".orthoEnd",IntCell.TYPE).createSpec();
		colSpecs[12]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".orthoStrand",StringCell.TYPE).createSpec();
		colSpecs[13]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".orthoAllele",StringCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "hapmapAllelesChimp";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[14];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("score"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new StringCell(row.getString("strand"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new StringCell(row.getString("refUCSC"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
			cells[8]=new StringCell(row.getString("observed"));
			if(row.wasNull()) cells[8]=DataType.getMissingCell();
			cells[9]=new StringCell(row.getString("orthoChrom"));
			if(row.wasNull()) cells[9]=DataType.getMissingCell();
			cells[10]=new IntCell(row.getInt("orthoStart"));
			if(row.wasNull()) cells[10]=DataType.getMissingCell();
			cells[11]=new IntCell(row.getInt("orthoEnd"));
			if(row.wasNull()) cells[11]=DataType.getMissingCell();
			cells[12]=new StringCell(row.getString("orthoStrand"));
			if(row.wasNull()) cells[12]=DataType.getMissingCell();
			cells[13]=new StringCell(row.getString("orthoAllele"));
			if(row.wasNull()) cells[13]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.hapmapAllelesMacaque")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,score,strand,refUCSC,observed,orthoChrom,orthoStart,orthoEnd,orthoStrand,orthoAllele from hg19.hapmapAllelesMacaque where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[14];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".score",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".strand",StringCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".refUCSC",StringCell.TYPE).createSpec();
		colSpecs[8]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".observed",StringCell.TYPE).createSpec();
		colSpecs[9]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".orthoChrom",StringCell.TYPE).createSpec();
		colSpecs[10]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".orthoStart",IntCell.TYPE).createSpec();
		colSpecs[11]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".orthoEnd",IntCell.TYPE).createSpec();
		colSpecs[12]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".orthoStrand",StringCell.TYPE).createSpec();
		colSpecs[13]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".orthoAllele",StringCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "hapmapAllelesMacaque";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[14];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("score"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new StringCell(row.getString("strand"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new StringCell(row.getString("refUCSC"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
			cells[8]=new StringCell(row.getString("observed"));
			if(row.wasNull()) cells[8]=DataType.getMissingCell();
			cells[9]=new StringCell(row.getString("orthoChrom"));
			if(row.wasNull()) cells[9]=DataType.getMissingCell();
			cells[10]=new IntCell(row.getInt("orthoStart"));
			if(row.wasNull()) cells[10]=DataType.getMissingCell();
			cells[11]=new IntCell(row.getInt("orthoEnd"));
			if(row.wasNull()) cells[11]=DataType.getMissingCell();
			cells[12]=new StringCell(row.getString("orthoStrand"));
			if(row.wasNull()) cells[12]=DataType.getMissingCell();
			cells[13]=new StringCell(row.getString("orthoAllele"));
			if(row.wasNull()) cells[13]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.hapmapAllelesSummary")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,score,strand,observed,allele1,allele2,popCount,isMixed,majorAlleleCEU,majorAlleleCountCEU,totalAlleleCountCEU,majorAlleleCHB,majorAlleleCountCHB,totalAlleleCountCHB,majorAlleleJPT,majorAlleleCountJPT,totalAlleleCountJPT,majorAlleleYRI,majorAlleleCountYRI,totalAlleleCountYRI,chimpAllele,chimpAlleleQuality,macaqueAllele,macaqueAlleleQuality from hg19.hapmapAllelesSummary where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[28];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".score",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".strand",StringCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".observed",StringCell.TYPE).createSpec();
		colSpecs[8]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".allele1",StringCell.TYPE).createSpec();
		colSpecs[9]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".allele2",StringCell.TYPE).createSpec();
		colSpecs[10]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".popCount",IntCell.TYPE).createSpec();
		colSpecs[11]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".isMixed",StringCell.TYPE).createSpec();
		colSpecs[12]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".majorAlleleCEU",StringCell.TYPE).createSpec();
		colSpecs[13]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".majorAlleleCountCEU",IntCell.TYPE).createSpec();
		colSpecs[14]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".totalAlleleCountCEU",IntCell.TYPE).createSpec();
		colSpecs[15]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".majorAlleleCHB",StringCell.TYPE).createSpec();
		colSpecs[16]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".majorAlleleCountCHB",IntCell.TYPE).createSpec();
		colSpecs[17]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".totalAlleleCountCHB",IntCell.TYPE).createSpec();
		colSpecs[18]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".majorAlleleJPT",StringCell.TYPE).createSpec();
		colSpecs[19]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".majorAlleleCountJPT",IntCell.TYPE).createSpec();
		colSpecs[20]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".totalAlleleCountJPT",IntCell.TYPE).createSpec();
		colSpecs[21]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".majorAlleleYRI",StringCell.TYPE).createSpec();
		colSpecs[22]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".majorAlleleCountYRI",IntCell.TYPE).createSpec();
		colSpecs[23]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".totalAlleleCountYRI",IntCell.TYPE).createSpec();
		colSpecs[24]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chimpAllele",StringCell.TYPE).createSpec();
		colSpecs[25]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chimpAlleleQuality",IntCell.TYPE).createSpec();
		colSpecs[26]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".macaqueAllele",StringCell.TYPE).createSpec();
		colSpecs[27]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".macaqueAlleleQuality",IntCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "hapmapAllelesSummary";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[28];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("score"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new StringCell(row.getString("strand"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new StringCell(row.getString("observed"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
			cells[8]=new StringCell(row.getString("allele1"));
			if(row.wasNull()) cells[8]=DataType.getMissingCell();
			cells[9]=new StringCell(row.getString("allele2"));
			if(row.wasNull()) cells[9]=DataType.getMissingCell();
			cells[10]=new IntCell(row.getInt("popCount"));
			if(row.wasNull()) cells[10]=DataType.getMissingCell();
			cells[11]=new StringCell(row.getString("isMixed"));
			if(row.wasNull()) cells[11]=DataType.getMissingCell();
			cells[12]=new StringCell(row.getString("majorAlleleCEU"));
			if(row.wasNull()) cells[12]=DataType.getMissingCell();
			cells[13]=new IntCell(row.getInt("majorAlleleCountCEU"));
			if(row.wasNull()) cells[13]=DataType.getMissingCell();
			cells[14]=new IntCell(row.getInt("totalAlleleCountCEU"));
			if(row.wasNull()) cells[14]=DataType.getMissingCell();
			cells[15]=new StringCell(row.getString("majorAlleleCHB"));
			if(row.wasNull()) cells[15]=DataType.getMissingCell();
			cells[16]=new IntCell(row.getInt("majorAlleleCountCHB"));
			if(row.wasNull()) cells[16]=DataType.getMissingCell();
			cells[17]=new IntCell(row.getInt("totalAlleleCountCHB"));
			if(row.wasNull()) cells[17]=DataType.getMissingCell();
			cells[18]=new StringCell(row.getString("majorAlleleJPT"));
			if(row.wasNull()) cells[18]=DataType.getMissingCell();
			cells[19]=new IntCell(row.getInt("majorAlleleCountJPT"));
			if(row.wasNull()) cells[19]=DataType.getMissingCell();
			cells[20]=new IntCell(row.getInt("totalAlleleCountJPT"));
			if(row.wasNull()) cells[20]=DataType.getMissingCell();
			cells[21]=new StringCell(row.getString("majorAlleleYRI"));
			if(row.wasNull()) cells[21]=DataType.getMissingCell();
			cells[22]=new IntCell(row.getInt("majorAlleleCountYRI"));
			if(row.wasNull()) cells[22]=DataType.getMissingCell();
			cells[23]=new IntCell(row.getInt("totalAlleleCountYRI"));
			if(row.wasNull()) cells[23]=DataType.getMissingCell();
			cells[24]=new StringCell(row.getString("chimpAllele"));
			if(row.wasNull()) cells[24]=DataType.getMissingCell();
			cells[25]=new IntCell(row.getInt("chimpAlleleQuality"));
			if(row.wasNull()) cells[25]=DataType.getMissingCell();
			cells[26]=new StringCell(row.getString("macaqueAllele"));
			if(row.wasNull()) cells[26]=DataType.getMissingCell();
			cells[27]=new IntCell(row.getInt("macaqueAlleleQuality"));
			if(row.wasNull()) cells[27]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.hapmapPhaseIIISummary")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,score,observed,overallMajorAllele,overallMinorAllele,popCount,phaseIIPopCount,isMixed,foundInPop,monomorphicInPop,minFreq,maxFreq,orthoCount,orthoAlleles,orthoQuals from hg19.hapmapPhaseIIISummary where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[19];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".score",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".observed",StringCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".overallMajorAllele",StringCell.TYPE).createSpec();
		colSpecs[8]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".overallMinorAllele",StringCell.TYPE).createSpec();
		colSpecs[9]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".popCount",IntCell.TYPE).createSpec();
		colSpecs[10]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".phaseIIPopCount",IntCell.TYPE).createSpec();
		colSpecs[11]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".isMixed",IntCell.TYPE).createSpec();
		colSpecs[12]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".foundInPop",StringCell.TYPE).createSpec();
		colSpecs[13]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".monomorphicInPop",StringCell.TYPE).createSpec();
		colSpecs[14]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".minFreq",DoubleCell.TYPE).createSpec();
		colSpecs[15]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".maxFreq",DoubleCell.TYPE).createSpec();
		colSpecs[16]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".orthoCount",IntCell.TYPE).createSpec();
		colSpecs[17]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".orthoAlleles",StringCell.TYPE).createSpec();
		colSpecs[18]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".orthoQuals",StringCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "hapmapPhaseIIISummary";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[19];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("score"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new StringCell(row.getString("observed"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new StringCell(row.getString("overallMajorAllele"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
			cells[8]=new StringCell(row.getString("overallMinorAllele"));
			if(row.wasNull()) cells[8]=DataType.getMissingCell();
			cells[9]=new IntCell(row.getInt("popCount"));
			if(row.wasNull()) cells[9]=DataType.getMissingCell();
			cells[10]=new IntCell(row.getInt("phaseIIPopCount"));
			if(row.wasNull()) cells[10]=DataType.getMissingCell();
			cells[11]=new IntCell(row.getInt("isMixed"));
			if(row.wasNull()) cells[11]=DataType.getMissingCell();
			cells[12]=new StringCell(row.getString("foundInPop"));
			if(row.wasNull()) cells[12]=DataType.getMissingCell();
			cells[13]=new StringCell(row.getString("monomorphicInPop"));
			if(row.wasNull()) cells[13]=DataType.getMissingCell();
			cells[14]=new DoubleCell(row.getDouble("minFreq"));
			if(row.wasNull()) cells[14]=DataType.getMissingCell();
			cells[15]=new DoubleCell(row.getDouble("maxFreq"));
			if(row.wasNull()) cells[15]=DataType.getMissingCell();
			cells[16]=new IntCell(row.getInt("orthoCount"));
			if(row.wasNull()) cells[16]=DataType.getMissingCell();
			cells[17]=new StringCell(row.getString("orthoAlleles"));
			if(row.wasNull()) cells[17]=DataType.getMissingCell();
			cells[18]=new StringCell(row.getString("orthoQuals"));
			if(row.wasNull()) cells[18]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.hapmapSnpsASW")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,score,strand,observed,allele1,homoCount1,allele2,homoCount2,heteroCount from hg19.hapmapSnpsASW where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[13];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".score",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".strand",StringCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".observed",StringCell.TYPE).createSpec();
		colSpecs[8]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".allele1",StringCell.TYPE).createSpec();
		colSpecs[9]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".homoCount1",IntCell.TYPE).createSpec();
		colSpecs[10]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".allele2",StringCell.TYPE).createSpec();
		colSpecs[11]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".homoCount2",IntCell.TYPE).createSpec();
		colSpecs[12]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".heteroCount",IntCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "hapmapSnpsASW";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[13];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("score"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new StringCell(row.getString("strand"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new StringCell(row.getString("observed"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
			cells[8]=new StringCell(row.getString("allele1"));
			if(row.wasNull()) cells[8]=DataType.getMissingCell();
			cells[9]=new IntCell(row.getInt("homoCount1"));
			if(row.wasNull()) cells[9]=DataType.getMissingCell();
			cells[10]=new StringCell(row.getString("allele2"));
			if(row.wasNull()) cells[10]=DataType.getMissingCell();
			cells[11]=new IntCell(row.getInt("homoCount2"));
			if(row.wasNull()) cells[11]=DataType.getMissingCell();
			cells[12]=new IntCell(row.getInt("heteroCount"));
			if(row.wasNull()) cells[12]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.hapmapSnpsCEU")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,score,strand,observed,allele1,homoCount1,allele2,homoCount2,heteroCount from hg19.hapmapSnpsCEU where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[13];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".score",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".strand",StringCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".observed",StringCell.TYPE).createSpec();
		colSpecs[8]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".allele1",StringCell.TYPE).createSpec();
		colSpecs[9]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".homoCount1",IntCell.TYPE).createSpec();
		colSpecs[10]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".allele2",StringCell.TYPE).createSpec();
		colSpecs[11]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".homoCount2",IntCell.TYPE).createSpec();
		colSpecs[12]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".heteroCount",IntCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "hapmapSnpsCEU";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[13];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("score"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new StringCell(row.getString("strand"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new StringCell(row.getString("observed"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
			cells[8]=new StringCell(row.getString("allele1"));
			if(row.wasNull()) cells[8]=DataType.getMissingCell();
			cells[9]=new IntCell(row.getInt("homoCount1"));
			if(row.wasNull()) cells[9]=DataType.getMissingCell();
			cells[10]=new StringCell(row.getString("allele2"));
			if(row.wasNull()) cells[10]=DataType.getMissingCell();
			cells[11]=new IntCell(row.getInt("homoCount2"));
			if(row.wasNull()) cells[11]=DataType.getMissingCell();
			cells[12]=new IntCell(row.getInt("heteroCount"));
			if(row.wasNull()) cells[12]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.hapmapSnpsCHB")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,score,strand,observed,allele1,homoCount1,allele2,homoCount2,heteroCount from hg19.hapmapSnpsCHB where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[13];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".score",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".strand",StringCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".observed",StringCell.TYPE).createSpec();
		colSpecs[8]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".allele1",StringCell.TYPE).createSpec();
		colSpecs[9]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".homoCount1",IntCell.TYPE).createSpec();
		colSpecs[10]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".allele2",StringCell.TYPE).createSpec();
		colSpecs[11]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".homoCount2",IntCell.TYPE).createSpec();
		colSpecs[12]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".heteroCount",IntCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "hapmapSnpsCHB";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[13];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("score"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new StringCell(row.getString("strand"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new StringCell(row.getString("observed"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
			cells[8]=new StringCell(row.getString("allele1"));
			if(row.wasNull()) cells[8]=DataType.getMissingCell();
			cells[9]=new IntCell(row.getInt("homoCount1"));
			if(row.wasNull()) cells[9]=DataType.getMissingCell();
			cells[10]=new StringCell(row.getString("allele2"));
			if(row.wasNull()) cells[10]=DataType.getMissingCell();
			cells[11]=new IntCell(row.getInt("homoCount2"));
			if(row.wasNull()) cells[11]=DataType.getMissingCell();
			cells[12]=new IntCell(row.getInt("heteroCount"));
			if(row.wasNull()) cells[12]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.hapmapSnpsCHD")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,score,strand,observed,allele1,homoCount1,allele2,homoCount2,heteroCount from hg19.hapmapSnpsCHD where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[13];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".score",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".strand",StringCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".observed",StringCell.TYPE).createSpec();
		colSpecs[8]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".allele1",StringCell.TYPE).createSpec();
		colSpecs[9]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".homoCount1",IntCell.TYPE).createSpec();
		colSpecs[10]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".allele2",StringCell.TYPE).createSpec();
		colSpecs[11]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".homoCount2",IntCell.TYPE).createSpec();
		colSpecs[12]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".heteroCount",IntCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "hapmapSnpsCHD";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[13];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("score"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new StringCell(row.getString("strand"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new StringCell(row.getString("observed"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
			cells[8]=new StringCell(row.getString("allele1"));
			if(row.wasNull()) cells[8]=DataType.getMissingCell();
			cells[9]=new IntCell(row.getInt("homoCount1"));
			if(row.wasNull()) cells[9]=DataType.getMissingCell();
			cells[10]=new StringCell(row.getString("allele2"));
			if(row.wasNull()) cells[10]=DataType.getMissingCell();
			cells[11]=new IntCell(row.getInt("homoCount2"));
			if(row.wasNull()) cells[11]=DataType.getMissingCell();
			cells[12]=new IntCell(row.getInt("heteroCount"));
			if(row.wasNull()) cells[12]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.hapmapSnpsGIH")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,score,strand,observed,allele1,homoCount1,allele2,homoCount2,heteroCount from hg19.hapmapSnpsGIH where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[13];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".score",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".strand",StringCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".observed",StringCell.TYPE).createSpec();
		colSpecs[8]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".allele1",StringCell.TYPE).createSpec();
		colSpecs[9]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".homoCount1",IntCell.TYPE).createSpec();
		colSpecs[10]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".allele2",StringCell.TYPE).createSpec();
		colSpecs[11]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".homoCount2",IntCell.TYPE).createSpec();
		colSpecs[12]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".heteroCount",IntCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "hapmapSnpsGIH";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[13];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("score"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new StringCell(row.getString("strand"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new StringCell(row.getString("observed"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
			cells[8]=new StringCell(row.getString("allele1"));
			if(row.wasNull()) cells[8]=DataType.getMissingCell();
			cells[9]=new IntCell(row.getInt("homoCount1"));
			if(row.wasNull()) cells[9]=DataType.getMissingCell();
			cells[10]=new StringCell(row.getString("allele2"));
			if(row.wasNull()) cells[10]=DataType.getMissingCell();
			cells[11]=new IntCell(row.getInt("homoCount2"));
			if(row.wasNull()) cells[11]=DataType.getMissingCell();
			cells[12]=new IntCell(row.getInt("heteroCount"));
			if(row.wasNull()) cells[12]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.hapmapSnpsJPT")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,score,strand,observed,allele1,homoCount1,allele2,homoCount2,heteroCount from hg19.hapmapSnpsJPT where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[13];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".score",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".strand",StringCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".observed",StringCell.TYPE).createSpec();
		colSpecs[8]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".allele1",StringCell.TYPE).createSpec();
		colSpecs[9]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".homoCount1",IntCell.TYPE).createSpec();
		colSpecs[10]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".allele2",StringCell.TYPE).createSpec();
		colSpecs[11]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".homoCount2",IntCell.TYPE).createSpec();
		colSpecs[12]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".heteroCount",IntCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "hapmapSnpsJPT";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[13];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("score"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new StringCell(row.getString("strand"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new StringCell(row.getString("observed"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
			cells[8]=new StringCell(row.getString("allele1"));
			if(row.wasNull()) cells[8]=DataType.getMissingCell();
			cells[9]=new IntCell(row.getInt("homoCount1"));
			if(row.wasNull()) cells[9]=DataType.getMissingCell();
			cells[10]=new StringCell(row.getString("allele2"));
			if(row.wasNull()) cells[10]=DataType.getMissingCell();
			cells[11]=new IntCell(row.getInt("homoCount2"));
			if(row.wasNull()) cells[11]=DataType.getMissingCell();
			cells[12]=new IntCell(row.getInt("heteroCount"));
			if(row.wasNull()) cells[12]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.hapmapSnpsLWK")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,score,strand,observed,allele1,homoCount1,allele2,homoCount2,heteroCount from hg19.hapmapSnpsLWK where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[13];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".score",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".strand",StringCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".observed",StringCell.TYPE).createSpec();
		colSpecs[8]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".allele1",StringCell.TYPE).createSpec();
		colSpecs[9]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".homoCount1",IntCell.TYPE).createSpec();
		colSpecs[10]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".allele2",StringCell.TYPE).createSpec();
		colSpecs[11]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".homoCount2",IntCell.TYPE).createSpec();
		colSpecs[12]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".heteroCount",IntCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "hapmapSnpsLWK";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[13];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("score"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new StringCell(row.getString("strand"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new StringCell(row.getString("observed"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
			cells[8]=new StringCell(row.getString("allele1"));
			if(row.wasNull()) cells[8]=DataType.getMissingCell();
			cells[9]=new IntCell(row.getInt("homoCount1"));
			if(row.wasNull()) cells[9]=DataType.getMissingCell();
			cells[10]=new StringCell(row.getString("allele2"));
			if(row.wasNull()) cells[10]=DataType.getMissingCell();
			cells[11]=new IntCell(row.getInt("homoCount2"));
			if(row.wasNull()) cells[11]=DataType.getMissingCell();
			cells[12]=new IntCell(row.getInt("heteroCount"));
			if(row.wasNull()) cells[12]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.hapmapSnpsMEX")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,score,strand,observed,allele1,homoCount1,allele2,homoCount2,heteroCount from hg19.hapmapSnpsMEX where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[13];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".score",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".strand",StringCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".observed",StringCell.TYPE).createSpec();
		colSpecs[8]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".allele1",StringCell.TYPE).createSpec();
		colSpecs[9]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".homoCount1",IntCell.TYPE).createSpec();
		colSpecs[10]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".allele2",StringCell.TYPE).createSpec();
		colSpecs[11]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".homoCount2",IntCell.TYPE).createSpec();
		colSpecs[12]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".heteroCount",IntCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "hapmapSnpsMEX";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[13];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("score"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new StringCell(row.getString("strand"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new StringCell(row.getString("observed"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
			cells[8]=new StringCell(row.getString("allele1"));
			if(row.wasNull()) cells[8]=DataType.getMissingCell();
			cells[9]=new IntCell(row.getInt("homoCount1"));
			if(row.wasNull()) cells[9]=DataType.getMissingCell();
			cells[10]=new StringCell(row.getString("allele2"));
			if(row.wasNull()) cells[10]=DataType.getMissingCell();
			cells[11]=new IntCell(row.getInt("homoCount2"));
			if(row.wasNull()) cells[11]=DataType.getMissingCell();
			cells[12]=new IntCell(row.getInt("heteroCount"));
			if(row.wasNull()) cells[12]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.hapmapSnpsMKK")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,score,strand,observed,allele1,homoCount1,allele2,homoCount2,heteroCount from hg19.hapmapSnpsMKK where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[13];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".score",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".strand",StringCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".observed",StringCell.TYPE).createSpec();
		colSpecs[8]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".allele1",StringCell.TYPE).createSpec();
		colSpecs[9]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".homoCount1",IntCell.TYPE).createSpec();
		colSpecs[10]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".allele2",StringCell.TYPE).createSpec();
		colSpecs[11]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".homoCount2",IntCell.TYPE).createSpec();
		colSpecs[12]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".heteroCount",IntCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "hapmapSnpsMKK";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[13];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("score"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new StringCell(row.getString("strand"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new StringCell(row.getString("observed"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
			cells[8]=new StringCell(row.getString("allele1"));
			if(row.wasNull()) cells[8]=DataType.getMissingCell();
			cells[9]=new IntCell(row.getInt("homoCount1"));
			if(row.wasNull()) cells[9]=DataType.getMissingCell();
			cells[10]=new StringCell(row.getString("allele2"));
			if(row.wasNull()) cells[10]=DataType.getMissingCell();
			cells[11]=new IntCell(row.getInt("homoCount2"));
			if(row.wasNull()) cells[11]=DataType.getMissingCell();
			cells[12]=new IntCell(row.getInt("heteroCount"));
			if(row.wasNull()) cells[12]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.hapmapSnpsTSI")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,score,strand,observed,allele1,homoCount1,allele2,homoCount2,heteroCount from hg19.hapmapSnpsTSI where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[13];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".score",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".strand",StringCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".observed",StringCell.TYPE).createSpec();
		colSpecs[8]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".allele1",StringCell.TYPE).createSpec();
		colSpecs[9]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".homoCount1",IntCell.TYPE).createSpec();
		colSpecs[10]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".allele2",StringCell.TYPE).createSpec();
		colSpecs[11]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".homoCount2",IntCell.TYPE).createSpec();
		colSpecs[12]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".heteroCount",IntCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "hapmapSnpsTSI";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[13];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("score"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new StringCell(row.getString("strand"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new StringCell(row.getString("observed"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
			cells[8]=new StringCell(row.getString("allele1"));
			if(row.wasNull()) cells[8]=DataType.getMissingCell();
			cells[9]=new IntCell(row.getInt("homoCount1"));
			if(row.wasNull()) cells[9]=DataType.getMissingCell();
			cells[10]=new StringCell(row.getString("allele2"));
			if(row.wasNull()) cells[10]=DataType.getMissingCell();
			cells[11]=new IntCell(row.getInt("homoCount2"));
			if(row.wasNull()) cells[11]=DataType.getMissingCell();
			cells[12]=new IntCell(row.getInt("heteroCount"));
			if(row.wasNull()) cells[12]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.hapmapSnpsYRI")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,score,strand,observed,allele1,homoCount1,allele2,homoCount2,heteroCount from hg19.hapmapSnpsYRI where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[13];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".score",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".strand",StringCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".observed",StringCell.TYPE).createSpec();
		colSpecs[8]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".allele1",StringCell.TYPE).createSpec();
		colSpecs[9]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".homoCount1",IntCell.TYPE).createSpec();
		colSpecs[10]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".allele2",StringCell.TYPE).createSpec();
		colSpecs[11]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".homoCount2",IntCell.TYPE).createSpec();
		colSpecs[12]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".heteroCount",IntCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "hapmapSnpsYRI";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[13];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("score"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new StringCell(row.getString("strand"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new StringCell(row.getString("observed"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
			cells[8]=new StringCell(row.getString("allele1"));
			if(row.wasNull()) cells[8]=DataType.getMissingCell();
			cells[9]=new IntCell(row.getInt("homoCount1"));
			if(row.wasNull()) cells[9]=DataType.getMissingCell();
			cells[10]=new StringCell(row.getString("allele2"));
			if(row.wasNull()) cells[10]=DataType.getMissingCell();
			cells[11]=new IntCell(row.getInt("homoCount2"));
			if(row.wasNull()) cells[11]=DataType.getMissingCell();
			cells[12]=new IntCell(row.getInt("heteroCount"));
			if(row.wasNull()) cells[12]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.hg19ContigDiff")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,score,strand,thickStart,thickEnd,reserved from hg19.hg19ContigDiff where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[10];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".score",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".strand",StringCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".thickStart",IntCell.TYPE).createSpec();
		colSpecs[8]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".thickEnd",IntCell.TYPE).createSpec();
		colSpecs[9]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".reserved",IntCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "hg19ContigDiff";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[10];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("score"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new StringCell(row.getString("strand"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new IntCell(row.getInt("thickStart"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
			cells[8]=new IntCell(row.getInt("thickEnd"));
			if(row.wasNull()) cells[8]=DataType.getMissingCell();
			cells[9]=new IntCell(row.getInt("reserved"));
			if(row.wasNull()) cells[9]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.hgIkmc")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,score,strand,thickStart,thickEnd,reserved,blockCount,blockSizes,chromStarts from hg19.hgIkmc where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[13];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".score",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".strand",StringCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".thickStart",IntCell.TYPE).createSpec();
		colSpecs[8]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".thickEnd",IntCell.TYPE).createSpec();
		colSpecs[9]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".reserved",IntCell.TYPE).createSpec();
		colSpecs[10]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".blockCount",IntCell.TYPE).createSpec();
		colSpecs[11]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".blockSizes",StringCell.TYPE).createSpec();
		colSpecs[12]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStarts",StringCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "hgIkmc";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[13];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("score"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new StringCell(row.getString("strand"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new IntCell(row.getInt("thickStart"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
			cells[8]=new IntCell(row.getInt("thickEnd"));
			if(row.wasNull()) cells[8]=DataType.getMissingCell();
			cells[9]=new IntCell(row.getInt("reserved"));
			if(row.wasNull()) cells[9]=DataType.getMissingCell();
			cells[10]=new IntCell(row.getInt("blockCount"));
			if(row.wasNull()) cells[10]=DataType.getMissingCell();
			cells[11]=new StringCell(row.getString("blockSizes"));
			if(row.wasNull()) cells[11]=DataType.getMissingCell();
			cells[12]=new StringCell(row.getString("chromStarts"));
			if(row.wasNull()) cells[12]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.hgdpGeo")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,ancestralAllele,derivedAllele,popFreqs from hg19.hgdpGeo where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[8];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".ancestralAllele",StringCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".derivedAllele",StringCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".popFreqs",StringCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "hgdpGeo";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[8];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new StringCell(row.getString("ancestralAllele"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new StringCell(row.getString("derivedAllele"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new StringCell(row.getString("popFreqs"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.hinv70Coding")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,score,strand,thickStart,thickEnd,reserved,blockCount,blockSizes,chromStarts from hg19.hinv70Coding where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[13];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".score",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".strand",StringCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".thickStart",IntCell.TYPE).createSpec();
		colSpecs[8]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".thickEnd",IntCell.TYPE).createSpec();
		colSpecs[9]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".reserved",IntCell.TYPE).createSpec();
		colSpecs[10]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".blockCount",IntCell.TYPE).createSpec();
		colSpecs[11]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".blockSizes",StringCell.TYPE).createSpec();
		colSpecs[12]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStarts",StringCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "hinv70Coding";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[13];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("score"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new StringCell(row.getString("strand"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new IntCell(row.getInt("thickStart"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
			cells[8]=new IntCell(row.getInt("thickEnd"));
			if(row.wasNull()) cells[8]=DataType.getMissingCell();
			cells[9]=new IntCell(row.getInt("reserved"));
			if(row.wasNull()) cells[9]=DataType.getMissingCell();
			cells[10]=new IntCell(row.getInt("blockCount"));
			if(row.wasNull()) cells[10]=DataType.getMissingCell();
			cells[11]=new StringCell(row.getString("blockSizes"));
			if(row.wasNull()) cells[11]=DataType.getMissingCell();
			cells[12]=new StringCell(row.getString("chromStarts"));
			if(row.wasNull()) cells[12]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.hinv70NonCoding")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,score,strand,thickStart,thickEnd,reserved,blockCount,blockSizes,chromStarts from hg19.hinv70NonCoding where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[13];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".score",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".strand",StringCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".thickStart",IntCell.TYPE).createSpec();
		colSpecs[8]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".thickEnd",IntCell.TYPE).createSpec();
		colSpecs[9]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".reserved",IntCell.TYPE).createSpec();
		colSpecs[10]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".blockCount",IntCell.TYPE).createSpec();
		colSpecs[11]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".blockSizes",StringCell.TYPE).createSpec();
		colSpecs[12]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStarts",StringCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "hinv70NonCoding";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[13];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("score"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new StringCell(row.getString("strand"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new IntCell(row.getInt("thickStart"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
			cells[8]=new IntCell(row.getInt("thickEnd"));
			if(row.wasNull()) cells[8]=DataType.getMissingCell();
			cells[9]=new IntCell(row.getInt("reserved"));
			if(row.wasNull()) cells[9]=DataType.getMissingCell();
			cells[10]=new IntCell(row.getInt("blockCount"));
			if(row.wasNull()) cells[10]=DataType.getMissingCell();
			cells[11]=new StringCell(row.getString("blockSizes"));
			if(row.wasNull()) cells[11]=DataType.getMissingCell();
			cells[12]=new StringCell(row.getString("chromStarts"));
			if(row.wasNull()) cells[12]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.hinv70PseudoGene")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,score,strand,thickStart,thickEnd,reserved,blockCount,blockSizes,chromStarts from hg19.hinv70PseudoGene where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[13];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".score",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".strand",StringCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".thickStart",IntCell.TYPE).createSpec();
		colSpecs[8]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".thickEnd",IntCell.TYPE).createSpec();
		colSpecs[9]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".reserved",IntCell.TYPE).createSpec();
		colSpecs[10]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".blockCount",IntCell.TYPE).createSpec();
		colSpecs[11]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".blockSizes",StringCell.TYPE).createSpec();
		colSpecs[12]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStarts",StringCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "hinv70PseudoGene";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[13];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("score"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new StringCell(row.getString("strand"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new IntCell(row.getInt("thickStart"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
			cells[8]=new IntCell(row.getInt("thickEnd"));
			if(row.wasNull()) cells[8]=DataType.getMissingCell();
			cells[9]=new IntCell(row.getInt("reserved"));
			if(row.wasNull()) cells[9]=DataType.getMissingCell();
			cells[10]=new IntCell(row.getInt("blockCount"));
			if(row.wasNull()) cells[10]=DataType.getMissingCell();
			cells[11]=new StringCell(row.getString("blockSizes"));
			if(row.wasNull()) cells[11]=DataType.getMissingCell();
			cells[12]=new StringCell(row.getString("chromStarts"));
			if(row.wasNull()) cells[12]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.illuminaProbes")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,score,strand,thickStart,thickEnd,reserved,blockCount,blockSizes,chromStarts from hg19.illuminaProbes where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[13];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".score",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".strand",StringCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".thickStart",IntCell.TYPE).createSpec();
		colSpecs[8]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".thickEnd",IntCell.TYPE).createSpec();
		colSpecs[9]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".reserved",IntCell.TYPE).createSpec();
		colSpecs[10]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".blockCount",IntCell.TYPE).createSpec();
		colSpecs[11]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".blockSizes",StringCell.TYPE).createSpec();
		colSpecs[12]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStarts",StringCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "illuminaProbes";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[13];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("score"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new StringCell(row.getString("strand"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new IntCell(row.getInt("thickStart"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
			cells[8]=new IntCell(row.getInt("thickEnd"));
			if(row.wasNull()) cells[8]=DataType.getMissingCell();
			cells[9]=new IntCell(row.getInt("reserved"));
			if(row.wasNull()) cells[9]=DataType.getMissingCell();
			cells[10]=new IntCell(row.getInt("blockCount"));
			if(row.wasNull()) cells[10]=DataType.getMissingCell();
			cells[11]=new StringCell(row.getString("blockSizes"));
			if(row.wasNull()) cells[11]=DataType.getMissingCell();
			cells[12]=new StringCell(row.getString("chromStarts"));
			if(row.wasNull()) cells[12]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.jaxQtlAsIs")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name from hg19.jaxQtlAsIs where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[5];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "jaxQtlAsIs";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[5];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.jaxQtlPadded")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name from hg19.jaxQtlPadded where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[5];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "jaxQtlPadded";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[5];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.knownAlt")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,score,strand from hg19.knownAlt where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[7];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".score",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".strand",StringCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "knownAlt";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[7];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("score"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new StringCell(row.getString("strand"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.knownCanonical")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return -1;}
public int getChromColumn() { return 0;}
public int getChromStartColumn() { return 1;}
public int getChromEndColumn() { return 2;}
public String getPreparedStatement() { return "select chrom,chromStart,chromEnd,clusterId,transcript,protein from hg19.knownCanonical where chrom=? and not(chromEnd<=? or chromStart>?)";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[6];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".clusterId",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".transcript",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".protein",StringCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "knownCanonical";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[6];
			cells[0]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("clusterId"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("transcript"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new StringCell(row.getString("protein"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.knownGene")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return -1;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 3;}
public int getChromEndColumn() { return 4;}
public String getPreparedStatement() { return "select name,chrom,strand,txStart,txEnd,cdsStart,cdsEnd,exonCount,exonStarts,exonEnds,proteinID,alignID from hg19.knownGene where chrom=? and not(txEnd<=? or txStart>?)";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[12];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".strand",StringCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".txStart",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".txEnd",IntCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".cdsStart",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".cdsEnd",IntCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".exonCount",IntCell.TYPE).createSpec();
		colSpecs[8]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".exonStarts",StringCell.TYPE).createSpec();
		colSpecs[9]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".exonEnds",StringCell.TYPE).createSpec();
		colSpecs[10]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".proteinID",StringCell.TYPE).createSpec();
		colSpecs[11]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".alignID",StringCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "knownGene";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[12];
			cells[0]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new StringCell(row.getString("strand"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("txStart"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new IntCell(row.getInt("txEnd"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("cdsStart"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new IntCell(row.getInt("cdsEnd"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new IntCell(row.getInt("exonCount"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
			cells[8]=new StringCell(row.getString("exonStarts"));
			if(row.wasNull()) cells[8]=DataType.getMissingCell();
			cells[9]=new StringCell(row.getString("exonEnds"));
			if(row.wasNull()) cells[9]=DataType.getMissingCell();
			cells[10]=new StringCell(row.getString("proteinID"));
			if(row.wasNull()) cells[10]=DataType.getMissingCell();
			cells[11]=new StringCell(row.getString("alignID"));
			if(row.wasNull()) cells[11]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.laminB1")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,span,count,offset,file,lowerLimit,dataRange,validCount,sumData,sumSquares from hg19.laminB1 where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[14];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".span",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".count",IntCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".offset",IntCell.TYPE).createSpec();
		colSpecs[8]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".file",StringCell.TYPE).createSpec();
		colSpecs[9]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".lowerLimit",DoubleCell.TYPE).createSpec();
		colSpecs[10]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".dataRange",DoubleCell.TYPE).createSpec();
		colSpecs[11]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".validCount",IntCell.TYPE).createSpec();
		colSpecs[12]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".sumData",DoubleCell.TYPE).createSpec();
		colSpecs[13]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".sumSquares",DoubleCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "laminB1";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[14];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("span"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new IntCell(row.getInt("count"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new IntCell(row.getInt("offset"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
			cells[8]=new StringCell(row.getString("file"));
			if(row.wasNull()) cells[8]=DataType.getMissingCell();
			cells[9]=new DoubleCell(row.getDouble("lowerLimit"));
			if(row.wasNull()) cells[9]=DataType.getMissingCell();
			cells[10]=new DoubleCell(row.getDouble("dataRange"));
			if(row.wasNull()) cells[10]=DataType.getMissingCell();
			cells[11]=new IntCell(row.getInt("validCount"));
			if(row.wasNull()) cells[11]=DataType.getMissingCell();
			cells[12]=new DoubleCell(row.getDouble("sumData"));
			if(row.wasNull()) cells[12]=DataType.getMissingCell();
			cells[13]=new DoubleCell(row.getDouble("sumSquares"));
			if(row.wasNull()) cells[13]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.laminB1Lads")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd from hg19.laminB1Lads where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[4];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "laminB1Lads";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[4];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.mgcGenes")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 2;}
public int getChromStartColumn() { return 4;}
public int getChromEndColumn() { return 5;}
public String getPreparedStatement() { return "select bin,name,chrom,strand,txStart,txEnd,cdsStart,cdsEnd,exonCount,exonStarts,exonEnds,score,name2,cdsStartStat,cdsEndStat,exonFrames from hg19.mgcGenes where chrom=? and not(txEnd<=? or txStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[16];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".strand",StringCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".txStart",IntCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".txEnd",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".cdsStart",IntCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".cdsEnd",IntCell.TYPE).createSpec();
		colSpecs[8]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".exonCount",IntCell.TYPE).createSpec();
		colSpecs[9]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".exonStarts",StringCell.TYPE).createSpec();
		colSpecs[10]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".exonEnds",StringCell.TYPE).createSpec();
		colSpecs[11]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".score",IntCell.TYPE).createSpec();
		colSpecs[12]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name2",StringCell.TYPE).createSpec();
		colSpecs[13]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".cdsStartStat",StringCell.TYPE).createSpec();
		colSpecs[14]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".cdsEndStat",StringCell.TYPE).createSpec();
		colSpecs[15]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".exonFrames",StringCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "mgcGenes";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[16];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new StringCell(row.getString("strand"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new IntCell(row.getInt("txStart"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("txEnd"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new IntCell(row.getInt("cdsStart"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new IntCell(row.getInt("cdsEnd"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
			cells[8]=new IntCell(row.getInt("exonCount"));
			if(row.wasNull()) cells[8]=DataType.getMissingCell();
			cells[9]=new StringCell(row.getString("exonStarts"));
			if(row.wasNull()) cells[9]=DataType.getMissingCell();
			cells[10]=new StringCell(row.getString("exonEnds"));
			if(row.wasNull()) cells[10]=DataType.getMissingCell();
			cells[11]=new IntCell(row.getInt("score"));
			if(row.wasNull()) cells[11]=DataType.getMissingCell();
			cells[12]=new StringCell(row.getString("name2"));
			if(row.wasNull()) cells[12]=DataType.getMissingCell();
			cells[13]=new StringCell(row.getString("cdsStartStat"));
			if(row.wasNull()) cells[13]=DataType.getMissingCell();
			cells[14]=new StringCell(row.getString("cdsEndStat"));
			if(row.wasNull()) cells[14]=DataType.getMissingCell();
			cells[15]=new StringCell(row.getString("exonFrames"));
			if(row.wasNull()) cells[15]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.microsat")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name from hg19.microsat where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[5];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "microsat";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[5];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.mrnaOrientInfo")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,intronOrientation,sizePolyA,revSizePolyA,signalPos,revSignalPos from hg19.mrnaOrientInfo where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[10];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".intronOrientation",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".sizePolyA",IntCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".revSizePolyA",IntCell.TYPE).createSpec();
		colSpecs[8]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".signalPos",IntCell.TYPE).createSpec();
		colSpecs[9]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".revSignalPos",IntCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "mrnaOrientInfo";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[10];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("intronOrientation"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new IntCell(row.getInt("sizePolyA"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new IntCell(row.getInt("revSizePolyA"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
			cells[8]=new IntCell(row.getInt("signalPos"));
			if(row.wasNull()) cells[8]=DataType.getMissingCell();
			cells[9]=new IntCell(row.getInt("revSignalPos"));
			if(row.wasNull()) cells[9]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.multiz46way")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,extFile,offset,score from hg19.multiz46way where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[7];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".extFile",IntCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".offset",StringCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".score",DoubleCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "multiz46way";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[7];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new IntCell(row.getInt("extFile"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new StringCell(row.getString("offset"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new DoubleCell(row.getDouble("score"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.multiz46wayFrames")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,src,frame,strand,name,prevFramePos,nextFramePos,isExonStart,isExonEnd from hg19.multiz46wayFrames where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[12];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".src",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".frame",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".strand",StringCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[8]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".prevFramePos",IntCell.TYPE).createSpec();
		colSpecs[9]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".nextFramePos",IntCell.TYPE).createSpec();
		colSpecs[10]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".isExonStart",IntCell.TYPE).createSpec();
		colSpecs[11]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".isExonEnd",IntCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "multiz46wayFrames";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[12];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("src"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("frame"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new StringCell(row.getString("strand"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
			cells[8]=new IntCell(row.getInt("prevFramePos"));
			if(row.wasNull()) cells[8]=DataType.getMissingCell();
			cells[9]=new IntCell(row.getInt("nextFramePos"));
			if(row.wasNull()) cells[9]=DataType.getMissingCell();
			cells[10]=new IntCell(row.getInt("isExonStart"));
			if(row.wasNull()) cells[10]=DataType.getMissingCell();
			cells[11]=new IntCell(row.getInt("isExonEnd"));
			if(row.wasNull()) cells[11]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.multiz46waySummary")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,src,score,leftStatus,rightStatus from hg19.multiz46waySummary where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[8];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".src",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".score",DoubleCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".leftStatus",StringCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".rightStatus",StringCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "multiz46waySummary";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[8];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("src"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new DoubleCell(row.getDouble("score"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new StringCell(row.getString("leftStatus"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new StringCell(row.getString("rightStatus"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.nestedRepeats")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,score,strand,thickStart,thickEnd,reserved,blockCount,blockSizes,chromStarts,blockStrands,id,repClass,repFamily from hg19.nestedRepeats where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[17];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".score",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".strand",StringCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".thickStart",IntCell.TYPE).createSpec();
		colSpecs[8]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".thickEnd",IntCell.TYPE).createSpec();
		colSpecs[9]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".reserved",IntCell.TYPE).createSpec();
		colSpecs[10]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".blockCount",IntCell.TYPE).createSpec();
		colSpecs[11]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".blockSizes",StringCell.TYPE).createSpec();
		colSpecs[12]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStarts",StringCell.TYPE).createSpec();
		colSpecs[13]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".blockStrands",StringCell.TYPE).createSpec();
		colSpecs[14]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".id",IntCell.TYPE).createSpec();
		colSpecs[15]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".repClass",StringCell.TYPE).createSpec();
		colSpecs[16]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".repFamily",StringCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "nestedRepeats";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[17];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("score"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new StringCell(row.getString("strand"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new IntCell(row.getInt("thickStart"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
			cells[8]=new IntCell(row.getInt("thickEnd"));
			if(row.wasNull()) cells[8]=DataType.getMissingCell();
			cells[9]=new IntCell(row.getInt("reserved"));
			if(row.wasNull()) cells[9]=DataType.getMissingCell();
			cells[10]=new IntCell(row.getInt("blockCount"));
			if(row.wasNull()) cells[10]=DataType.getMissingCell();
			cells[11]=new StringCell(row.getString("blockSizes"));
			if(row.wasNull()) cells[11]=DataType.getMissingCell();
			cells[12]=new StringCell(row.getString("chromStarts"));
			if(row.wasNull()) cells[12]=DataType.getMissingCell();
			cells[13]=new StringCell(row.getString("blockStrands"));
			if(row.wasNull()) cells[13]=DataType.getMissingCell();
			cells[14]=new IntCell(row.getInt("id"));
			if(row.wasNull()) cells[14]=DataType.getMissingCell();
			cells[15]=new StringCell(row.getString("repClass"));
			if(row.wasNull()) cells[15]=DataType.getMissingCell();
			cells[16]=new StringCell(row.getString("repFamily"));
			if(row.wasNull()) cells[16]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.nscanGene")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 2;}
public int getChromStartColumn() { return 4;}
public int getChromEndColumn() { return 5;}
public String getPreparedStatement() { return "select bin,name,chrom,strand,txStart,txEnd,cdsStart,cdsEnd,exonCount,exonStarts,exonEnds,score,name2,cdsStartStat,cdsEndStat,exonFrames from hg19.nscanGene where chrom=? and not(txEnd<=? or txStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[16];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".strand",StringCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".txStart",IntCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".txEnd",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".cdsStart",IntCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".cdsEnd",IntCell.TYPE).createSpec();
		colSpecs[8]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".exonCount",IntCell.TYPE).createSpec();
		colSpecs[9]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".exonStarts",StringCell.TYPE).createSpec();
		colSpecs[10]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".exonEnds",StringCell.TYPE).createSpec();
		colSpecs[11]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".score",IntCell.TYPE).createSpec();
		colSpecs[12]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name2",StringCell.TYPE).createSpec();
		colSpecs[13]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".cdsStartStat",StringCell.TYPE).createSpec();
		colSpecs[14]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".cdsEndStat",StringCell.TYPE).createSpec();
		colSpecs[15]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".exonFrames",StringCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "nscanGene";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[16];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new StringCell(row.getString("strand"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new IntCell(row.getInt("txStart"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("txEnd"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new IntCell(row.getInt("cdsStart"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new IntCell(row.getInt("cdsEnd"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
			cells[8]=new IntCell(row.getInt("exonCount"));
			if(row.wasNull()) cells[8]=DataType.getMissingCell();
			cells[9]=new StringCell(row.getString("exonStarts"));
			if(row.wasNull()) cells[9]=DataType.getMissingCell();
			cells[10]=new StringCell(row.getString("exonEnds"));
			if(row.wasNull()) cells[10]=DataType.getMissingCell();
			cells[11]=new IntCell(row.getInt("score"));
			if(row.wasNull()) cells[11]=DataType.getMissingCell();
			cells[12]=new StringCell(row.getString("name2"));
			if(row.wasNull()) cells[12]=DataType.getMissingCell();
			cells[13]=new StringCell(row.getString("cdsStartStat"));
			if(row.wasNull()) cells[13]=DataType.getMissingCell();
			cells[14]=new StringCell(row.getString("cdsEndStat"));
			if(row.wasNull()) cells[14]=DataType.getMissingCell();
			cells[15]=new StringCell(row.getString("exonFrames"));
			if(row.wasNull()) cells[15]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.ntHumChimpCodingDiff")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,score,strand,thickStart,thickEnd,reserved from hg19.ntHumChimpCodingDiff where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[10];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".score",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".strand",StringCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".thickStart",IntCell.TYPE).createSpec();
		colSpecs[8]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".thickEnd",IntCell.TYPE).createSpec();
		colSpecs[9]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".reserved",IntCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "ntHumChimpCodingDiff";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[10];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("score"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new StringCell(row.getString("strand"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new IntCell(row.getInt("thickStart"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
			cells[8]=new IntCell(row.getInt("thickEnd"));
			if(row.wasNull()) cells[8]=DataType.getMissingCell();
			cells[9]=new IntCell(row.getInt("reserved"));
			if(row.wasNull()) cells[9]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.ntOoaHaplo")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,score,strand,thickStart,thickEnd,reserved,st,ooaTagFreq,am,dm,an,dn from hg19.ntOoaHaplo where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[16];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".score",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".strand",StringCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".thickStart",IntCell.TYPE).createSpec();
		colSpecs[8]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".thickEnd",IntCell.TYPE).createSpec();
		colSpecs[9]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".reserved",IntCell.TYPE).createSpec();
		colSpecs[10]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".st",DoubleCell.TYPE).createSpec();
		colSpecs[11]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".ooaTagFreq",DoubleCell.TYPE).createSpec();
		colSpecs[12]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".am",IntCell.TYPE).createSpec();
		colSpecs[13]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".dm",IntCell.TYPE).createSpec();
		colSpecs[14]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".an",IntCell.TYPE).createSpec();
		colSpecs[15]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".dn",IntCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "ntOoaHaplo";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[16];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("score"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new StringCell(row.getString("strand"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new IntCell(row.getInt("thickStart"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
			cells[8]=new IntCell(row.getInt("thickEnd"));
			if(row.wasNull()) cells[8]=DataType.getMissingCell();
			cells[9]=new IntCell(row.getInt("reserved"));
			if(row.wasNull()) cells[9]=DataType.getMissingCell();
			cells[10]=new DoubleCell(row.getDouble("st"));
			if(row.wasNull()) cells[10]=DataType.getMissingCell();
			cells[11]=new DoubleCell(row.getDouble("ooaTagFreq"));
			if(row.wasNull()) cells[11]=DataType.getMissingCell();
			cells[12]=new IntCell(row.getInt("am"));
			if(row.wasNull()) cells[12]=DataType.getMissingCell();
			cells[13]=new IntCell(row.getInt("dm"));
			if(row.wasNull()) cells[13]=DataType.getMissingCell();
			cells[14]=new IntCell(row.getInt("an"));
			if(row.wasNull()) cells[14]=DataType.getMissingCell();
			cells[15]=new IntCell(row.getInt("dn"));
			if(row.wasNull()) cells[15]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.ntSssSnps")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,score,strand,thickStart,thickEnd,reserved from hg19.ntSssSnps where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[10];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".score",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".strand",StringCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".thickStart",IntCell.TYPE).createSpec();
		colSpecs[8]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".thickEnd",IntCell.TYPE).createSpec();
		colSpecs[9]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".reserved",IntCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "ntSssSnps";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[10];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("score"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new StringCell(row.getString("strand"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new IntCell(row.getInt("thickStart"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
			cells[8]=new IntCell(row.getInt("thickEnd"));
			if(row.wasNull()) cells[8]=DataType.getMissingCell();
			cells[9]=new IntCell(row.getInt("reserved"));
			if(row.wasNull()) cells[9]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.ntSssTop5p")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,score from hg19.ntSssTop5p where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[6];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".score",IntCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "ntSssTop5p";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[6];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("score"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.omimGene")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name from hg19.omimGene where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[5];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "omimGene";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[5];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.oreganno")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,id,strand,name from hg19.oreganno where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[7];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".id",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".strand",StringCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "oreganno";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[7];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("id"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new StringCell(row.getString("strand"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.orfeomeGenes")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 2;}
public int getChromStartColumn() { return 4;}
public int getChromEndColumn() { return 5;}
public String getPreparedStatement() { return "select bin,name,chrom,strand,txStart,txEnd,cdsStart,cdsEnd,exonCount,exonStarts,exonEnds,score,name2,cdsStartStat,cdsEndStat,exonFrames from hg19.orfeomeGenes where chrom=? and not(txEnd<=? or txStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[16];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".strand",StringCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".txStart",IntCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".txEnd",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".cdsStart",IntCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".cdsEnd",IntCell.TYPE).createSpec();
		colSpecs[8]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".exonCount",IntCell.TYPE).createSpec();
		colSpecs[9]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".exonStarts",StringCell.TYPE).createSpec();
		colSpecs[10]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".exonEnds",StringCell.TYPE).createSpec();
		colSpecs[11]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".score",IntCell.TYPE).createSpec();
		colSpecs[12]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name2",StringCell.TYPE).createSpec();
		colSpecs[13]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".cdsStartStat",StringCell.TYPE).createSpec();
		colSpecs[14]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".cdsEndStat",StringCell.TYPE).createSpec();
		colSpecs[15]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".exonFrames",StringCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "orfeomeGenes";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[16];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new StringCell(row.getString("strand"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new IntCell(row.getInt("txStart"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("txEnd"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new IntCell(row.getInt("cdsStart"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new IntCell(row.getInt("cdsEnd"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
			cells[8]=new IntCell(row.getInt("exonCount"));
			if(row.wasNull()) cells[8]=DataType.getMissingCell();
			cells[9]=new StringCell(row.getString("exonStarts"));
			if(row.wasNull()) cells[9]=DataType.getMissingCell();
			cells[10]=new StringCell(row.getString("exonEnds"));
			if(row.wasNull()) cells[10]=DataType.getMissingCell();
			cells[11]=new IntCell(row.getInt("score"));
			if(row.wasNull()) cells[11]=DataType.getMissingCell();
			cells[12]=new StringCell(row.getString("name2"));
			if(row.wasNull()) cells[12]=DataType.getMissingCell();
			cells[13]=new StringCell(row.getString("cdsStartStat"));
			if(row.wasNull()) cells[13]=DataType.getMissingCell();
			cells[14]=new StringCell(row.getString("cdsEndStat"));
			if(row.wasNull()) cells[14]=DataType.getMissingCell();
			cells[15]=new StringCell(row.getString("exonFrames"));
			if(row.wasNull()) cells[15]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.pgNA12878")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,alleleCount,alleleFreq,alleleScores from hg19.pgNA12878 where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[8];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".alleleCount",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".alleleFreq",StringCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".alleleScores",StringCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "pgNA12878";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[8];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("alleleCount"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new StringCell(row.getString("alleleFreq"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new StringCell(row.getString("alleleScores"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.pgNA12891")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,alleleCount,alleleFreq,alleleScores from hg19.pgNA12891 where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[8];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".alleleCount",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".alleleFreq",StringCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".alleleScores",StringCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "pgNA12891";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[8];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("alleleCount"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new StringCell(row.getString("alleleFreq"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new StringCell(row.getString("alleleScores"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.pgNA12892")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,alleleCount,alleleFreq,alleleScores from hg19.pgNA12892 where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[8];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".alleleCount",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".alleleFreq",StringCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".alleleScores",StringCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "pgNA12892";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[8];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("alleleCount"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new StringCell(row.getString("alleleFreq"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new StringCell(row.getString("alleleScores"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.pgNA19240")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,alleleCount,alleleFreq,alleleScores from hg19.pgNA19240 where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[8];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".alleleCount",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".alleleFreq",StringCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".alleleScores",StringCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "pgNA19240";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[8];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("alleleCount"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new StringCell(row.getString("alleleFreq"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new StringCell(row.getString("alleleScores"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.pgSjk")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,alleleCount,alleleFreq,alleleScores from hg19.pgSjk where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[8];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".alleleCount",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".alleleFreq",StringCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".alleleScores",StringCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "pgSjk";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[8];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("alleleCount"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new StringCell(row.getString("alleleFreq"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new StringCell(row.getString("alleleScores"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.pgVenter")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,alleleCount,alleleFreq,alleleScores from hg19.pgVenter where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[8];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".alleleCount",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".alleleFreq",StringCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".alleleScores",StringCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "pgVenter";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[8];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("alleleCount"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new StringCell(row.getString("alleleFreq"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new StringCell(row.getString("alleleScores"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.pgWatson")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,alleleCount,alleleFreq,alleleScores from hg19.pgWatson where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[8];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".alleleCount",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".alleleFreq",StringCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".alleleScores",StringCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "pgWatson";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[8];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("alleleCount"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new StringCell(row.getString("alleleFreq"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new StringCell(row.getString("alleleScores"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.pgYh1")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,alleleCount,alleleFreq,alleleScores from hg19.pgYh1 where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[8];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".alleleCount",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".alleleFreq",StringCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".alleleScores",StringCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "pgYh1";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[8];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("alleleCount"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new StringCell(row.getString("alleleFreq"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new StringCell(row.getString("alleleScores"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.pgYoruban3")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,alleleCount,alleleFreq,alleleScores from hg19.pgYoruban3 where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[8];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".alleleCount",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".alleleFreq",StringCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".alleleScores",StringCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "pgYoruban3";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[8];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("alleleCount"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new StringCell(row.getString("alleleFreq"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new StringCell(row.getString("alleleScores"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.phastCons46way")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,span,count,offset,file,lowerLimit,dataRange,validCount,sumData,sumSquares from hg19.phastCons46way where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[14];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".span",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".count",IntCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".offset",IntCell.TYPE).createSpec();
		colSpecs[8]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".file",StringCell.TYPE).createSpec();
		colSpecs[9]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".lowerLimit",DoubleCell.TYPE).createSpec();
		colSpecs[10]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".dataRange",DoubleCell.TYPE).createSpec();
		colSpecs[11]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".validCount",IntCell.TYPE).createSpec();
		colSpecs[12]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".sumData",DoubleCell.TYPE).createSpec();
		colSpecs[13]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".sumSquares",DoubleCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "phastCons46way";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[14];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("span"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new IntCell(row.getInt("count"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new IntCell(row.getInt("offset"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
			cells[8]=new StringCell(row.getString("file"));
			if(row.wasNull()) cells[8]=DataType.getMissingCell();
			cells[9]=new DoubleCell(row.getDouble("lowerLimit"));
			if(row.wasNull()) cells[9]=DataType.getMissingCell();
			cells[10]=new DoubleCell(row.getDouble("dataRange"));
			if(row.wasNull()) cells[10]=DataType.getMissingCell();
			cells[11]=new IntCell(row.getInt("validCount"));
			if(row.wasNull()) cells[11]=DataType.getMissingCell();
			cells[12]=new DoubleCell(row.getDouble("sumData"));
			if(row.wasNull()) cells[12]=DataType.getMissingCell();
			cells[13]=new DoubleCell(row.getDouble("sumSquares"));
			if(row.wasNull()) cells[13]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.phastCons46wayPlacental")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,span,count,offset,file,lowerLimit,dataRange,validCount,sumData,sumSquares from hg19.phastCons46wayPlacental where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[14];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".span",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".count",IntCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".offset",IntCell.TYPE).createSpec();
		colSpecs[8]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".file",StringCell.TYPE).createSpec();
		colSpecs[9]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".lowerLimit",DoubleCell.TYPE).createSpec();
		colSpecs[10]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".dataRange",DoubleCell.TYPE).createSpec();
		colSpecs[11]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".validCount",IntCell.TYPE).createSpec();
		colSpecs[12]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".sumData",DoubleCell.TYPE).createSpec();
		colSpecs[13]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".sumSquares",DoubleCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "phastCons46wayPlacental";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[14];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("span"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new IntCell(row.getInt("count"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new IntCell(row.getInt("offset"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
			cells[8]=new StringCell(row.getString("file"));
			if(row.wasNull()) cells[8]=DataType.getMissingCell();
			cells[9]=new DoubleCell(row.getDouble("lowerLimit"));
			if(row.wasNull()) cells[9]=DataType.getMissingCell();
			cells[10]=new DoubleCell(row.getDouble("dataRange"));
			if(row.wasNull()) cells[10]=DataType.getMissingCell();
			cells[11]=new IntCell(row.getInt("validCount"));
			if(row.wasNull()) cells[11]=DataType.getMissingCell();
			cells[12]=new DoubleCell(row.getDouble("sumData"));
			if(row.wasNull()) cells[12]=DataType.getMissingCell();
			cells[13]=new DoubleCell(row.getDouble("sumSquares"));
			if(row.wasNull()) cells[13]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.phastCons46wayPrimates")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,span,count,offset,file,lowerLimit,dataRange,validCount,sumData,sumSquares from hg19.phastCons46wayPrimates where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[14];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".span",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".count",IntCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".offset",IntCell.TYPE).createSpec();
		colSpecs[8]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".file",StringCell.TYPE).createSpec();
		colSpecs[9]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".lowerLimit",DoubleCell.TYPE).createSpec();
		colSpecs[10]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".dataRange",DoubleCell.TYPE).createSpec();
		colSpecs[11]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".validCount",IntCell.TYPE).createSpec();
		colSpecs[12]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".sumData",DoubleCell.TYPE).createSpec();
		colSpecs[13]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".sumSquares",DoubleCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "phastCons46wayPrimates";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[14];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("span"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new IntCell(row.getInt("count"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new IntCell(row.getInt("offset"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
			cells[8]=new StringCell(row.getString("file"));
			if(row.wasNull()) cells[8]=DataType.getMissingCell();
			cells[9]=new DoubleCell(row.getDouble("lowerLimit"));
			if(row.wasNull()) cells[9]=DataType.getMissingCell();
			cells[10]=new DoubleCell(row.getDouble("dataRange"));
			if(row.wasNull()) cells[10]=DataType.getMissingCell();
			cells[11]=new IntCell(row.getInt("validCount"));
			if(row.wasNull()) cells[11]=DataType.getMissingCell();
			cells[12]=new DoubleCell(row.getDouble("sumData"));
			if(row.wasNull()) cells[12]=DataType.getMissingCell();
			cells[13]=new DoubleCell(row.getDouble("sumSquares"));
			if(row.wasNull()) cells[13]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.phastConsElements46way")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,score from hg19.phastConsElements46way where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[6];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".score",IntCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "phastConsElements46way";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[6];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("score"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.phastConsElements46wayPlacental")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,score from hg19.phastConsElements46wayPlacental where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[6];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".score",IntCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "phastConsElements46wayPlacental";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[6];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("score"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.phastConsElements46wayPrimates")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,score from hg19.phastConsElements46wayPrimates where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[6];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".score",IntCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "phastConsElements46wayPrimates";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[6];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("score"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.phyloP46wayAll")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,span,count,offset,file,lowerLimit,dataRange,validCount,sumData,sumSquares from hg19.phyloP46wayAll where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[14];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".span",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".count",IntCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".offset",IntCell.TYPE).createSpec();
		colSpecs[8]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".file",StringCell.TYPE).createSpec();
		colSpecs[9]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".lowerLimit",DoubleCell.TYPE).createSpec();
		colSpecs[10]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".dataRange",DoubleCell.TYPE).createSpec();
		colSpecs[11]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".validCount",IntCell.TYPE).createSpec();
		colSpecs[12]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".sumData",DoubleCell.TYPE).createSpec();
		colSpecs[13]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".sumSquares",DoubleCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "phyloP46wayAll";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[14];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("span"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new IntCell(row.getInt("count"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new IntCell(row.getInt("offset"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
			cells[8]=new StringCell(row.getString("file"));
			if(row.wasNull()) cells[8]=DataType.getMissingCell();
			cells[9]=new DoubleCell(row.getDouble("lowerLimit"));
			if(row.wasNull()) cells[9]=DataType.getMissingCell();
			cells[10]=new DoubleCell(row.getDouble("dataRange"));
			if(row.wasNull()) cells[10]=DataType.getMissingCell();
			cells[11]=new IntCell(row.getInt("validCount"));
			if(row.wasNull()) cells[11]=DataType.getMissingCell();
			cells[12]=new DoubleCell(row.getDouble("sumData"));
			if(row.wasNull()) cells[12]=DataType.getMissingCell();
			cells[13]=new DoubleCell(row.getDouble("sumSquares"));
			if(row.wasNull()) cells[13]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.phyloP46wayPlacental")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,span,count,offset,file,lowerLimit,dataRange,validCount,sumData,sumSquares from hg19.phyloP46wayPlacental where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[14];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".span",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".count",IntCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".offset",IntCell.TYPE).createSpec();
		colSpecs[8]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".file",StringCell.TYPE).createSpec();
		colSpecs[9]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".lowerLimit",DoubleCell.TYPE).createSpec();
		colSpecs[10]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".dataRange",DoubleCell.TYPE).createSpec();
		colSpecs[11]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".validCount",IntCell.TYPE).createSpec();
		colSpecs[12]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".sumData",DoubleCell.TYPE).createSpec();
		colSpecs[13]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".sumSquares",DoubleCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "phyloP46wayPlacental";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[14];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("span"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new IntCell(row.getInt("count"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new IntCell(row.getInt("offset"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
			cells[8]=new StringCell(row.getString("file"));
			if(row.wasNull()) cells[8]=DataType.getMissingCell();
			cells[9]=new DoubleCell(row.getDouble("lowerLimit"));
			if(row.wasNull()) cells[9]=DataType.getMissingCell();
			cells[10]=new DoubleCell(row.getDouble("dataRange"));
			if(row.wasNull()) cells[10]=DataType.getMissingCell();
			cells[11]=new IntCell(row.getInt("validCount"));
			if(row.wasNull()) cells[11]=DataType.getMissingCell();
			cells[12]=new DoubleCell(row.getDouble("sumData"));
			if(row.wasNull()) cells[12]=DataType.getMissingCell();
			cells[13]=new DoubleCell(row.getDouble("sumSquares"));
			if(row.wasNull()) cells[13]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.phyloP46wayPrimates")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,span,count,offset,file,lowerLimit,dataRange,validCount,sumData,sumSquares from hg19.phyloP46wayPrimates where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[14];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".span",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".count",IntCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".offset",IntCell.TYPE).createSpec();
		colSpecs[8]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".file",StringCell.TYPE).createSpec();
		colSpecs[9]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".lowerLimit",DoubleCell.TYPE).createSpec();
		colSpecs[10]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".dataRange",DoubleCell.TYPE).createSpec();
		colSpecs[11]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".validCount",IntCell.TYPE).createSpec();
		colSpecs[12]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".sumData",DoubleCell.TYPE).createSpec();
		colSpecs[13]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".sumSquares",DoubleCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "phyloP46wayPrimates";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[14];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("span"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new IntCell(row.getInt("count"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new IntCell(row.getInt("offset"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
			cells[8]=new StringCell(row.getString("file"));
			if(row.wasNull()) cells[8]=DataType.getMissingCell();
			cells[9]=new DoubleCell(row.getDouble("lowerLimit"));
			if(row.wasNull()) cells[9]=DataType.getMissingCell();
			cells[10]=new DoubleCell(row.getDouble("dataRange"));
			if(row.wasNull()) cells[10]=DataType.getMissingCell();
			cells[11]=new IntCell(row.getInt("validCount"));
			if(row.wasNull()) cells[11]=DataType.getMissingCell();
			cells[12]=new DoubleCell(row.getDouble("sumData"));
			if(row.wasNull()) cells[12]=DataType.getMissingCell();
			cells[13]=new DoubleCell(row.getDouble("sumSquares"));
			if(row.wasNull()) cells[13]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.polyaDb")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,score,strand,thickStart,thickEnd from hg19.polyaDb where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[9];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".score",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".strand",StringCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".thickStart",IntCell.TYPE).createSpec();
		colSpecs[8]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".thickEnd",IntCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "polyaDb";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[9];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("score"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new StringCell(row.getString("strand"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new IntCell(row.getInt("thickStart"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
			cells[8]=new IntCell(row.getInt("thickEnd"));
			if(row.wasNull()) cells[8]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.polyaPredict")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,score,strand,thickStart,thickEnd from hg19.polyaPredict where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[9];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".score",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".strand",StringCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".thickStart",IntCell.TYPE).createSpec();
		colSpecs[8]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".thickEnd",IntCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "polyaPredict";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[9];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("score"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new StringCell(row.getString("strand"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new IntCell(row.getInt("thickStart"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
			cells[8]=new IntCell(row.getInt("thickEnd"));
			if(row.wasNull()) cells[8]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.recombRate")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return -1;}
public int getChromColumn() { return 0;}
public int getChromStartColumn() { return 1;}
public int getChromEndColumn() { return 2;}
public String getPreparedStatement() { return "select chrom,chromStart,chromEnd,name,decodeAvg,decodeFemale,decodeMale,marshfieldAvg,marshfieldFemale,marshfieldMale,genethonAvg,genethonFemale,genethonMale from hg19.recombRate where chrom=? and not(chromEnd<=? or chromStart>?)";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[13];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".decodeAvg",DoubleCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".decodeFemale",DoubleCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".decodeMale",DoubleCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".marshfieldAvg",DoubleCell.TYPE).createSpec();
		colSpecs[8]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".marshfieldFemale",DoubleCell.TYPE).createSpec();
		colSpecs[9]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".marshfieldMale",DoubleCell.TYPE).createSpec();
		colSpecs[10]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".genethonAvg",DoubleCell.TYPE).createSpec();
		colSpecs[11]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".genethonFemale",DoubleCell.TYPE).createSpec();
		colSpecs[12]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".genethonMale",DoubleCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "recombRate";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[13];
			cells[0]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new DoubleCell(row.getDouble("decodeAvg"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new DoubleCell(row.getDouble("decodeFemale"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new DoubleCell(row.getDouble("decodeMale"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new DoubleCell(row.getDouble("marshfieldAvg"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
			cells[8]=new DoubleCell(row.getDouble("marshfieldFemale"));
			if(row.wasNull()) cells[8]=DataType.getMissingCell();
			cells[9]=new DoubleCell(row.getDouble("marshfieldMale"));
			if(row.wasNull()) cells[9]=DataType.getMissingCell();
			cells[10]=new DoubleCell(row.getDouble("genethonAvg"));
			if(row.wasNull()) cells[10]=DataType.getMissingCell();
			cells[11]=new DoubleCell(row.getDouble("genethonFemale"));
			if(row.wasNull()) cells[11]=DataType.getMissingCell();
			cells[12]=new DoubleCell(row.getDouble("genethonMale"));
			if(row.wasNull()) cells[12]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.refFlat")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return -1;}
public int getChromColumn() { return 2;}
public int getChromStartColumn() { return 4;}
public int getChromEndColumn() { return 5;}
public String getPreparedStatement() { return "select geneName,name,chrom,strand,txStart,txEnd,cdsStart,cdsEnd,exonCount,exonStarts,exonEnds from hg19.refFlat where chrom=? and not(txEnd<=? or txStart>?)";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[11];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".geneName",StringCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".strand",StringCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".txStart",IntCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".txEnd",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".cdsStart",IntCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".cdsEnd",IntCell.TYPE).createSpec();
		colSpecs[8]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".exonCount",IntCell.TYPE).createSpec();
		colSpecs[9]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".exonStarts",StringCell.TYPE).createSpec();
		colSpecs[10]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".exonEnds",StringCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "refFlat";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[11];
			cells[0]=new StringCell(row.getString("geneName"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new StringCell(row.getString("strand"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new IntCell(row.getInt("txStart"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("txEnd"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new IntCell(row.getInt("cdsStart"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new IntCell(row.getInt("cdsEnd"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
			cells[8]=new IntCell(row.getInt("exonCount"));
			if(row.wasNull()) cells[8]=DataType.getMissingCell();
			cells[9]=new StringCell(row.getString("exonStarts"));
			if(row.wasNull()) cells[9]=DataType.getMissingCell();
			cells[10]=new StringCell(row.getString("exonEnds"));
			if(row.wasNull()) cells[10]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.refGene")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 2;}
public int getChromStartColumn() { return 4;}
public int getChromEndColumn() { return 5;}
public String getPreparedStatement() { return "select bin,name,chrom,strand,txStart,txEnd,cdsStart,cdsEnd,exonCount,exonStarts,exonEnds,score,name2,cdsStartStat,cdsEndStat,exonFrames from hg19.refGene where chrom=? and not(txEnd<=? or txStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[16];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".strand",StringCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".txStart",IntCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".txEnd",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".cdsStart",IntCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".cdsEnd",IntCell.TYPE).createSpec();
		colSpecs[8]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".exonCount",IntCell.TYPE).createSpec();
		colSpecs[9]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".exonStarts",StringCell.TYPE).createSpec();
		colSpecs[10]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".exonEnds",StringCell.TYPE).createSpec();
		colSpecs[11]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".score",IntCell.TYPE).createSpec();
		colSpecs[12]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name2",StringCell.TYPE).createSpec();
		colSpecs[13]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".cdsStartStat",StringCell.TYPE).createSpec();
		colSpecs[14]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".cdsEndStat",StringCell.TYPE).createSpec();
		colSpecs[15]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".exonFrames",StringCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "refGene";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[16];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new StringCell(row.getString("strand"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new IntCell(row.getInt("txStart"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("txEnd"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new IntCell(row.getInt("cdsStart"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new IntCell(row.getInt("cdsEnd"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
			cells[8]=new IntCell(row.getInt("exonCount"));
			if(row.wasNull()) cells[8]=DataType.getMissingCell();
			cells[9]=new StringCell(row.getString("exonStarts"));
			if(row.wasNull()) cells[9]=DataType.getMissingCell();
			cells[10]=new StringCell(row.getString("exonEnds"));
			if(row.wasNull()) cells[10]=DataType.getMissingCell();
			cells[11]=new IntCell(row.getInt("score"));
			if(row.wasNull()) cells[11]=DataType.getMissingCell();
			cells[12]=new StringCell(row.getString("name2"));
			if(row.wasNull()) cells[12]=DataType.getMissingCell();
			cells[13]=new StringCell(row.getString("cdsStartStat"));
			if(row.wasNull()) cells[13]=DataType.getMissingCell();
			cells[14]=new StringCell(row.getString("cdsEndStat"));
			if(row.wasNull()) cells[14]=DataType.getMissingCell();
			cells[15]=new StringCell(row.getString("exonFrames"));
			if(row.wasNull()) cells[15]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.rgdQtl")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name from hg19.rgdQtl where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[5];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "rgdQtl";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[5];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.rgdRatQtl")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name from hg19.rgdRatQtl where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[5];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "rgdRatQtl";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[5];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.rnaCluster")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,score,strand,thickStart,thickEnd,reserved,blockCount,blockSizes,chromStarts from hg19.rnaCluster where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[13];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".score",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".strand",StringCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".thickStart",IntCell.TYPE).createSpec();
		colSpecs[8]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".thickEnd",IntCell.TYPE).createSpec();
		colSpecs[9]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".reserved",IntCell.TYPE).createSpec();
		colSpecs[10]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".blockCount",IntCell.TYPE).createSpec();
		colSpecs[11]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".blockSizes",StringCell.TYPE).createSpec();
		colSpecs[12]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStarts",StringCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "rnaCluster";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[13];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("score"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new StringCell(row.getString("strand"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new IntCell(row.getInt("thickStart"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
			cells[8]=new IntCell(row.getInt("thickEnd"));
			if(row.wasNull()) cells[8]=DataType.getMissingCell();
			cells[9]=new IntCell(row.getInt("reserved"));
			if(row.wasNull()) cells[9]=DataType.getMissingCell();
			cells[10]=new IntCell(row.getInt("blockCount"));
			if(row.wasNull()) cells[10]=DataType.getMissingCell();
			cells[11]=new StringCell(row.getString("blockSizes"));
			if(row.wasNull()) cells[11]=DataType.getMissingCell();
			cells[12]=new StringCell(row.getString("chromStarts"));
			if(row.wasNull()) cells[12]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.sestanBrainAtlas")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,score,strand,thickStart,thickEnd,reserved,blockCount,blockSizes,chromStarts,expCount,expIds,expScores from hg19.sestanBrainAtlas where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[16];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".score",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".strand",StringCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".thickStart",IntCell.TYPE).createSpec();
		colSpecs[8]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".thickEnd",IntCell.TYPE).createSpec();
		colSpecs[9]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".reserved",IntCell.TYPE).createSpec();
		colSpecs[10]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".blockCount",IntCell.TYPE).createSpec();
		colSpecs[11]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".blockSizes",StringCell.TYPE).createSpec();
		colSpecs[12]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStarts",StringCell.TYPE).createSpec();
		colSpecs[13]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".expCount",IntCell.TYPE).createSpec();
		colSpecs[14]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".expIds",StringCell.TYPE).createSpec();
		colSpecs[15]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".expScores",StringCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "sestanBrainAtlas";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[16];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("score"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new StringCell(row.getString("strand"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new IntCell(row.getInt("thickStart"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
			cells[8]=new IntCell(row.getInt("thickEnd"));
			if(row.wasNull()) cells[8]=DataType.getMissingCell();
			cells[9]=new IntCell(row.getInt("reserved"));
			if(row.wasNull()) cells[9]=DataType.getMissingCell();
			cells[10]=new IntCell(row.getInt("blockCount"));
			if(row.wasNull()) cells[10]=DataType.getMissingCell();
			cells[11]=new StringCell(row.getString("blockSizes"));
			if(row.wasNull()) cells[11]=DataType.getMissingCell();
			cells[12]=new StringCell(row.getString("chromStarts"));
			if(row.wasNull()) cells[12]=DataType.getMissingCell();
			cells[13]=new IntCell(row.getInt("expCount"));
			if(row.wasNull()) cells[13]=DataType.getMissingCell();
			cells[14]=new StringCell(row.getString("expIds"));
			if(row.wasNull()) cells[14]=DataType.getMissingCell();
			cells[15]=new StringCell(row.getString("expScores"));
			if(row.wasNull()) cells[15]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.sgpGene")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 2;}
public int getChromStartColumn() { return 4;}
public int getChromEndColumn() { return 5;}
public String getPreparedStatement() { return "select bin,name,chrom,strand,txStart,txEnd,cdsStart,cdsEnd,exonCount,exonStarts,exonEnds,score,name2,cdsStartStat,cdsEndStat,exonFrames from hg19.sgpGene where chrom=? and not(txEnd<=? or txStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[16];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".strand",StringCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".txStart",IntCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".txEnd",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".cdsStart",IntCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".cdsEnd",IntCell.TYPE).createSpec();
		colSpecs[8]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".exonCount",IntCell.TYPE).createSpec();
		colSpecs[9]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".exonStarts",StringCell.TYPE).createSpec();
		colSpecs[10]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".exonEnds",StringCell.TYPE).createSpec();
		colSpecs[11]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".score",IntCell.TYPE).createSpec();
		colSpecs[12]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name2",StringCell.TYPE).createSpec();
		colSpecs[13]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".cdsStartStat",StringCell.TYPE).createSpec();
		colSpecs[14]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".cdsEndStat",StringCell.TYPE).createSpec();
		colSpecs[15]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".exonFrames",StringCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "sgpGene";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[16];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new StringCell(row.getString("strand"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new IntCell(row.getInt("txStart"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("txEnd"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new IntCell(row.getInt("cdsStart"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new IntCell(row.getInt("cdsEnd"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
			cells[8]=new IntCell(row.getInt("exonCount"));
			if(row.wasNull()) cells[8]=DataType.getMissingCell();
			cells[9]=new StringCell(row.getString("exonStarts"));
			if(row.wasNull()) cells[9]=DataType.getMissingCell();
			cells[10]=new StringCell(row.getString("exonEnds"));
			if(row.wasNull()) cells[10]=DataType.getMissingCell();
			cells[11]=new IntCell(row.getInt("score"));
			if(row.wasNull()) cells[11]=DataType.getMissingCell();
			cells[12]=new StringCell(row.getString("name2"));
			if(row.wasNull()) cells[12]=DataType.getMissingCell();
			cells[13]=new StringCell(row.getString("cdsStartStat"));
			if(row.wasNull()) cells[13]=DataType.getMissingCell();
			cells[14]=new StringCell(row.getString("cdsEndStat"));
			if(row.wasNull()) cells[14]=DataType.getMissingCell();
			cells[15]=new StringCell(row.getString("exonFrames"));
			if(row.wasNull()) cells[15]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.simpleRepeat")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,period,copyNum,consensusSize,perMatch,perIndel,score,A,C,G,T,entropy,sequence from hg19.simpleRepeat where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[17];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".period",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".copyNum",DoubleCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".consensusSize",IntCell.TYPE).createSpec();
		colSpecs[8]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".perMatch",IntCell.TYPE).createSpec();
		colSpecs[9]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".perIndel",IntCell.TYPE).createSpec();
		colSpecs[10]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".score",IntCell.TYPE).createSpec();
		colSpecs[11]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".A",IntCell.TYPE).createSpec();
		colSpecs[12]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".C",IntCell.TYPE).createSpec();
		colSpecs[13]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".G",IntCell.TYPE).createSpec();
		colSpecs[14]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".T",IntCell.TYPE).createSpec();
		colSpecs[15]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".entropy",DoubleCell.TYPE).createSpec();
		colSpecs[16]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".sequence",StringCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "simpleRepeat";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[17];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("period"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new DoubleCell(row.getDouble("copyNum"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new IntCell(row.getInt("consensusSize"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
			cells[8]=new IntCell(row.getInt("perMatch"));
			if(row.wasNull()) cells[8]=DataType.getMissingCell();
			cells[9]=new IntCell(row.getInt("perIndel"));
			if(row.wasNull()) cells[9]=DataType.getMissingCell();
			cells[10]=new IntCell(row.getInt("score"));
			if(row.wasNull()) cells[10]=DataType.getMissingCell();
			cells[11]=new IntCell(row.getInt("A"));
			if(row.wasNull()) cells[11]=DataType.getMissingCell();
			cells[12]=new IntCell(row.getInt("C"));
			if(row.wasNull()) cells[12]=DataType.getMissingCell();
			cells[13]=new IntCell(row.getInt("G"));
			if(row.wasNull()) cells[13]=DataType.getMissingCell();
			cells[14]=new IntCell(row.getInt("T"));
			if(row.wasNull()) cells[14]=DataType.getMissingCell();
			cells[15]=new DoubleCell(row.getDouble("entropy"));
			if(row.wasNull()) cells[15]=DataType.getMissingCell();
			cells[16]=new StringCell(row.getString("sequence"));
			if(row.wasNull()) cells[16]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.snp131")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,score,strand,refNCBI,refUCSC,observed,molType,class,valid,avHet,avHetSE,func,locType,weight from hg19.snp131 where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[18];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".score",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".strand",StringCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".refNCBI",StringCell.TYPE).createSpec();
		colSpecs[8]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".refUCSC",StringCell.TYPE).createSpec();
		colSpecs[9]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".observed",StringCell.TYPE).createSpec();
		colSpecs[10]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".molType",StringCell.TYPE).createSpec();
		colSpecs[11]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".class",StringCell.TYPE).createSpec();
		colSpecs[12]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".valid",StringCell.TYPE).createSpec();
		colSpecs[13]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".avHet",DoubleCell.TYPE).createSpec();
		colSpecs[14]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".avHetSE",DoubleCell.TYPE).createSpec();
		colSpecs[15]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".func",StringCell.TYPE).createSpec();
		colSpecs[16]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".locType",StringCell.TYPE).createSpec();
		colSpecs[17]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".weight",IntCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "snp131";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[18];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("score"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new StringCell(row.getString("strand"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new StringCell(row.getString("refNCBI"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
			cells[8]=new StringCell(row.getString("refUCSC"));
			if(row.wasNull()) cells[8]=DataType.getMissingCell();
			cells[9]=new StringCell(row.getString("observed"));
			if(row.wasNull()) cells[9]=DataType.getMissingCell();
			cells[10]=new StringCell(row.getString("molType"));
			if(row.wasNull()) cells[10]=DataType.getMissingCell();
			cells[11]=new StringCell(row.getString("class"));
			if(row.wasNull()) cells[11]=DataType.getMissingCell();
			cells[12]=new StringCell(row.getString("valid"));
			if(row.wasNull()) cells[12]=DataType.getMissingCell();
			cells[13]=new DoubleCell(row.getDouble("avHet"));
			if(row.wasNull()) cells[13]=DataType.getMissingCell();
			cells[14]=new DoubleCell(row.getDouble("avHetSE"));
			if(row.wasNull()) cells[14]=DataType.getMissingCell();
			cells[15]=new StringCell(row.getString("func"));
			if(row.wasNull()) cells[15]=DataType.getMissingCell();
			cells[16]=new StringCell(row.getString("locType"));
			if(row.wasNull()) cells[16]=DataType.getMissingCell();
			cells[17]=new IntCell(row.getInt("weight"));
			if(row.wasNull()) cells[17]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.snp131CodingDbSnp")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,transcript,frame,alleleCount,funcCodes,alleles,codons,peptides from hg19.snp131CodingDbSnp where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[12];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".transcript",StringCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".frame",StringCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".alleleCount",IntCell.TYPE).createSpec();
		colSpecs[8]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".funcCodes",StringCell.TYPE).createSpec();
		colSpecs[9]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".alleles",StringCell.TYPE).createSpec();
		colSpecs[10]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".codons",StringCell.TYPE).createSpec();
		colSpecs[11]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".peptides",StringCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "snp131CodingDbSnp";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[12];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new StringCell(row.getString("transcript"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new StringCell(row.getString("frame"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new IntCell(row.getInt("alleleCount"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
			cells[8]=new StringCell(row.getString("funcCodes"));
			if(row.wasNull()) cells[8]=DataType.getMissingCell();
			cells[9]=new StringCell(row.getString("alleles"));
			if(row.wasNull()) cells[9]=DataType.getMissingCell();
			cells[10]=new StringCell(row.getString("codons"));
			if(row.wasNull()) cells[10]=DataType.getMissingCell();
			cells[11]=new StringCell(row.getString("peptides"));
			if(row.wasNull()) cells[11]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.snp131Exceptions")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,exception from hg19.snp131Exceptions where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[6];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".exception",StringCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "snp131Exceptions";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[6];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new StringCell(row.getString("exception"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.snp131OrthoPt2Pa2Rm2")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,humanObserved,humanAllele,humanStrand,chimpChrom,chimpStart,chimpEnd,chimpAllele,chimpStrand,orangChrom,orangStart,orangEnd,orangAllele,orangStrand,macaqueChrom,macaqueStart,macaqueEnd,macaqueAllele,macaqueStrand from hg19.snp131OrthoPt2Pa2Rm2 where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[23];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".humanObserved",StringCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".humanAllele",StringCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".humanStrand",StringCell.TYPE).createSpec();
		colSpecs[8]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chimpChrom",StringCell.TYPE).createSpec();
		colSpecs[9]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chimpStart",IntCell.TYPE).createSpec();
		colSpecs[10]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chimpEnd",IntCell.TYPE).createSpec();
		colSpecs[11]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chimpAllele",StringCell.TYPE).createSpec();
		colSpecs[12]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chimpStrand",StringCell.TYPE).createSpec();
		colSpecs[13]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".orangChrom",StringCell.TYPE).createSpec();
		colSpecs[14]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".orangStart",IntCell.TYPE).createSpec();
		colSpecs[15]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".orangEnd",IntCell.TYPE).createSpec();
		colSpecs[16]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".orangAllele",StringCell.TYPE).createSpec();
		colSpecs[17]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".orangStrand",StringCell.TYPE).createSpec();
		colSpecs[18]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".macaqueChrom",StringCell.TYPE).createSpec();
		colSpecs[19]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".macaqueStart",IntCell.TYPE).createSpec();
		colSpecs[20]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".macaqueEnd",IntCell.TYPE).createSpec();
		colSpecs[21]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".macaqueAllele",StringCell.TYPE).createSpec();
		colSpecs[22]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".macaqueStrand",StringCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "snp131OrthoPt2Pa2Rm2";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[23];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new StringCell(row.getString("humanObserved"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new StringCell(row.getString("humanAllele"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new StringCell(row.getString("humanStrand"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
			cells[8]=new StringCell(row.getString("chimpChrom"));
			if(row.wasNull()) cells[8]=DataType.getMissingCell();
			cells[9]=new IntCell(row.getInt("chimpStart"));
			if(row.wasNull()) cells[9]=DataType.getMissingCell();
			cells[10]=new IntCell(row.getInt("chimpEnd"));
			if(row.wasNull()) cells[10]=DataType.getMissingCell();
			cells[11]=new StringCell(row.getString("chimpAllele"));
			if(row.wasNull()) cells[11]=DataType.getMissingCell();
			cells[12]=new StringCell(row.getString("chimpStrand"));
			if(row.wasNull()) cells[12]=DataType.getMissingCell();
			cells[13]=new StringCell(row.getString("orangChrom"));
			if(row.wasNull()) cells[13]=DataType.getMissingCell();
			cells[14]=new IntCell(row.getInt("orangStart"));
			if(row.wasNull()) cells[14]=DataType.getMissingCell();
			cells[15]=new IntCell(row.getInt("orangEnd"));
			if(row.wasNull()) cells[15]=DataType.getMissingCell();
			cells[16]=new StringCell(row.getString("orangAllele"));
			if(row.wasNull()) cells[16]=DataType.getMissingCell();
			cells[17]=new StringCell(row.getString("orangStrand"));
			if(row.wasNull()) cells[17]=DataType.getMissingCell();
			cells[18]=new StringCell(row.getString("macaqueChrom"));
			if(row.wasNull()) cells[18]=DataType.getMissingCell();
			cells[19]=new IntCell(row.getInt("macaqueStart"));
			if(row.wasNull()) cells[19]=DataType.getMissingCell();
			cells[20]=new IntCell(row.getInt("macaqueEnd"));
			if(row.wasNull()) cells[20]=DataType.getMissingCell();
			cells[21]=new StringCell(row.getString("macaqueAllele"));
			if(row.wasNull()) cells[21]=DataType.getMissingCell();
			cells[22]=new StringCell(row.getString("macaqueStrand"));
			if(row.wasNull()) cells[22]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.snp132")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,score,strand,refNCBI,refUCSC,observed,molType,class,valid,avHet,avHetSE,func,locType,weight,exceptions,submitterCount,submitters,alleleFreqCount,alleles,alleleNs,alleleFreqs,bitfields from hg19.snp132 where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[26];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".score",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".strand",StringCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".refNCBI",StringCell.TYPE).createSpec();
		colSpecs[8]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".refUCSC",StringCell.TYPE).createSpec();
		colSpecs[9]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".observed",StringCell.TYPE).createSpec();
		colSpecs[10]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".molType",StringCell.TYPE).createSpec();
		colSpecs[11]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".class",StringCell.TYPE).createSpec();
		colSpecs[12]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".valid",StringCell.TYPE).createSpec();
		colSpecs[13]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".avHet",DoubleCell.TYPE).createSpec();
		colSpecs[14]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".avHetSE",DoubleCell.TYPE).createSpec();
		colSpecs[15]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".func",StringCell.TYPE).createSpec();
		colSpecs[16]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".locType",StringCell.TYPE).createSpec();
		colSpecs[17]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".weight",IntCell.TYPE).createSpec();
		colSpecs[18]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".exceptions",StringCell.TYPE).createSpec();
		colSpecs[19]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".submitterCount",IntCell.TYPE).createSpec();
		colSpecs[20]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".submitters",StringCell.TYPE).createSpec();
		colSpecs[21]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".alleleFreqCount",IntCell.TYPE).createSpec();
		colSpecs[22]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".alleles",StringCell.TYPE).createSpec();
		colSpecs[23]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".alleleNs",StringCell.TYPE).createSpec();
		colSpecs[24]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".alleleFreqs",StringCell.TYPE).createSpec();
		colSpecs[25]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bitfields",StringCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "snp132";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[26];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("score"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new StringCell(row.getString("strand"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new StringCell(row.getString("refNCBI"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
			cells[8]=new StringCell(row.getString("refUCSC"));
			if(row.wasNull()) cells[8]=DataType.getMissingCell();
			cells[9]=new StringCell(row.getString("observed"));
			if(row.wasNull()) cells[9]=DataType.getMissingCell();
			cells[10]=new StringCell(row.getString("molType"));
			if(row.wasNull()) cells[10]=DataType.getMissingCell();
			cells[11]=new StringCell(row.getString("class"));
			if(row.wasNull()) cells[11]=DataType.getMissingCell();
			cells[12]=new StringCell(row.getString("valid"));
			if(row.wasNull()) cells[12]=DataType.getMissingCell();
			cells[13]=new DoubleCell(row.getDouble("avHet"));
			if(row.wasNull()) cells[13]=DataType.getMissingCell();
			cells[14]=new DoubleCell(row.getDouble("avHetSE"));
			if(row.wasNull()) cells[14]=DataType.getMissingCell();
			cells[15]=new StringCell(row.getString("func"));
			if(row.wasNull()) cells[15]=DataType.getMissingCell();
			cells[16]=new StringCell(row.getString("locType"));
			if(row.wasNull()) cells[16]=DataType.getMissingCell();
			cells[17]=new IntCell(row.getInt("weight"));
			if(row.wasNull()) cells[17]=DataType.getMissingCell();
			cells[18]=new StringCell(row.getString("exceptions"));
			if(row.wasNull()) cells[18]=DataType.getMissingCell();
			cells[19]=new IntCell(row.getInt("submitterCount"));
			if(row.wasNull()) cells[19]=DataType.getMissingCell();
			cells[20]=new StringCell(row.getString("submitters"));
			if(row.wasNull()) cells[20]=DataType.getMissingCell();
			cells[21]=new IntCell(row.getInt("alleleFreqCount"));
			if(row.wasNull()) cells[21]=DataType.getMissingCell();
			cells[22]=new StringCell(row.getString("alleles"));
			if(row.wasNull()) cells[22]=DataType.getMissingCell();
			cells[23]=new StringCell(row.getString("alleleNs"));
			if(row.wasNull()) cells[23]=DataType.getMissingCell();
			cells[24]=new StringCell(row.getString("alleleFreqs"));
			if(row.wasNull()) cells[24]=DataType.getMissingCell();
			cells[25]=new StringCell(row.getString("bitfields"));
			if(row.wasNull()) cells[25]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.snp132CodingDbSnp")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,transcript,frame,alleleCount,funcCodes,alleles,codons,peptides from hg19.snp132CodingDbSnp where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[12];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".transcript",StringCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".frame",StringCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".alleleCount",IntCell.TYPE).createSpec();
		colSpecs[8]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".funcCodes",StringCell.TYPE).createSpec();
		colSpecs[9]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".alleles",StringCell.TYPE).createSpec();
		colSpecs[10]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".codons",StringCell.TYPE).createSpec();
		colSpecs[11]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".peptides",StringCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "snp132CodingDbSnp";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[12];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new StringCell(row.getString("transcript"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new StringCell(row.getString("frame"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new IntCell(row.getInt("alleleCount"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
			cells[8]=new StringCell(row.getString("funcCodes"));
			if(row.wasNull()) cells[8]=DataType.getMissingCell();
			cells[9]=new StringCell(row.getString("alleles"));
			if(row.wasNull()) cells[9]=DataType.getMissingCell();
			cells[10]=new StringCell(row.getString("codons"));
			if(row.wasNull()) cells[10]=DataType.getMissingCell();
			cells[11]=new StringCell(row.getString("peptides"));
			if(row.wasNull()) cells[11]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.snp132Common")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,score,strand,refNCBI,refUCSC,observed,molType,class,valid,avHet,avHetSE,func,locType,weight,exceptions,submitterCount,submitters,alleleFreqCount,alleles,alleleNs,alleleFreqs,bitfields from hg19.snp132Common where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[26];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".score",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".strand",StringCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".refNCBI",StringCell.TYPE).createSpec();
		colSpecs[8]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".refUCSC",StringCell.TYPE).createSpec();
		colSpecs[9]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".observed",StringCell.TYPE).createSpec();
		colSpecs[10]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".molType",StringCell.TYPE).createSpec();
		colSpecs[11]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".class",StringCell.TYPE).createSpec();
		colSpecs[12]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".valid",StringCell.TYPE).createSpec();
		colSpecs[13]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".avHet",DoubleCell.TYPE).createSpec();
		colSpecs[14]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".avHetSE",DoubleCell.TYPE).createSpec();
		colSpecs[15]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".func",StringCell.TYPE).createSpec();
		colSpecs[16]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".locType",StringCell.TYPE).createSpec();
		colSpecs[17]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".weight",IntCell.TYPE).createSpec();
		colSpecs[18]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".exceptions",StringCell.TYPE).createSpec();
		colSpecs[19]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".submitterCount",IntCell.TYPE).createSpec();
		colSpecs[20]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".submitters",StringCell.TYPE).createSpec();
		colSpecs[21]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".alleleFreqCount",IntCell.TYPE).createSpec();
		colSpecs[22]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".alleles",StringCell.TYPE).createSpec();
		colSpecs[23]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".alleleNs",StringCell.TYPE).createSpec();
		colSpecs[24]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".alleleFreqs",StringCell.TYPE).createSpec();
		colSpecs[25]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bitfields",StringCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "snp132Common";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[26];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("score"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new StringCell(row.getString("strand"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new StringCell(row.getString("refNCBI"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
			cells[8]=new StringCell(row.getString("refUCSC"));
			if(row.wasNull()) cells[8]=DataType.getMissingCell();
			cells[9]=new StringCell(row.getString("observed"));
			if(row.wasNull()) cells[9]=DataType.getMissingCell();
			cells[10]=new StringCell(row.getString("molType"));
			if(row.wasNull()) cells[10]=DataType.getMissingCell();
			cells[11]=new StringCell(row.getString("class"));
			if(row.wasNull()) cells[11]=DataType.getMissingCell();
			cells[12]=new StringCell(row.getString("valid"));
			if(row.wasNull()) cells[12]=DataType.getMissingCell();
			cells[13]=new DoubleCell(row.getDouble("avHet"));
			if(row.wasNull()) cells[13]=DataType.getMissingCell();
			cells[14]=new DoubleCell(row.getDouble("avHetSE"));
			if(row.wasNull()) cells[14]=DataType.getMissingCell();
			cells[15]=new StringCell(row.getString("func"));
			if(row.wasNull()) cells[15]=DataType.getMissingCell();
			cells[16]=new StringCell(row.getString("locType"));
			if(row.wasNull()) cells[16]=DataType.getMissingCell();
			cells[17]=new IntCell(row.getInt("weight"));
			if(row.wasNull()) cells[17]=DataType.getMissingCell();
			cells[18]=new StringCell(row.getString("exceptions"));
			if(row.wasNull()) cells[18]=DataType.getMissingCell();
			cells[19]=new IntCell(row.getInt("submitterCount"));
			if(row.wasNull()) cells[19]=DataType.getMissingCell();
			cells[20]=new StringCell(row.getString("submitters"));
			if(row.wasNull()) cells[20]=DataType.getMissingCell();
			cells[21]=new IntCell(row.getInt("alleleFreqCount"));
			if(row.wasNull()) cells[21]=DataType.getMissingCell();
			cells[22]=new StringCell(row.getString("alleles"));
			if(row.wasNull()) cells[22]=DataType.getMissingCell();
			cells[23]=new StringCell(row.getString("alleleNs"));
			if(row.wasNull()) cells[23]=DataType.getMissingCell();
			cells[24]=new StringCell(row.getString("alleleFreqs"));
			if(row.wasNull()) cells[24]=DataType.getMissingCell();
			cells[25]=new StringCell(row.getString("bitfields"));
			if(row.wasNull()) cells[25]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.snp132Flagged")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,score,strand,refNCBI,refUCSC,observed,molType,class,valid,avHet,avHetSE,func,locType,weight,exceptions,submitterCount,submitters,alleleFreqCount,alleles,alleleNs,alleleFreqs,bitfields from hg19.snp132Flagged where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[26];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".score",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".strand",StringCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".refNCBI",StringCell.TYPE).createSpec();
		colSpecs[8]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".refUCSC",StringCell.TYPE).createSpec();
		colSpecs[9]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".observed",StringCell.TYPE).createSpec();
		colSpecs[10]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".molType",StringCell.TYPE).createSpec();
		colSpecs[11]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".class",StringCell.TYPE).createSpec();
		colSpecs[12]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".valid",StringCell.TYPE).createSpec();
		colSpecs[13]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".avHet",DoubleCell.TYPE).createSpec();
		colSpecs[14]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".avHetSE",DoubleCell.TYPE).createSpec();
		colSpecs[15]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".func",StringCell.TYPE).createSpec();
		colSpecs[16]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".locType",StringCell.TYPE).createSpec();
		colSpecs[17]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".weight",IntCell.TYPE).createSpec();
		colSpecs[18]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".exceptions",StringCell.TYPE).createSpec();
		colSpecs[19]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".submitterCount",IntCell.TYPE).createSpec();
		colSpecs[20]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".submitters",StringCell.TYPE).createSpec();
		colSpecs[21]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".alleleFreqCount",IntCell.TYPE).createSpec();
		colSpecs[22]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".alleles",StringCell.TYPE).createSpec();
		colSpecs[23]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".alleleNs",StringCell.TYPE).createSpec();
		colSpecs[24]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".alleleFreqs",StringCell.TYPE).createSpec();
		colSpecs[25]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bitfields",StringCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "snp132Flagged";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[26];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("score"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new StringCell(row.getString("strand"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new StringCell(row.getString("refNCBI"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
			cells[8]=new StringCell(row.getString("refUCSC"));
			if(row.wasNull()) cells[8]=DataType.getMissingCell();
			cells[9]=new StringCell(row.getString("observed"));
			if(row.wasNull()) cells[9]=DataType.getMissingCell();
			cells[10]=new StringCell(row.getString("molType"));
			if(row.wasNull()) cells[10]=DataType.getMissingCell();
			cells[11]=new StringCell(row.getString("class"));
			if(row.wasNull()) cells[11]=DataType.getMissingCell();
			cells[12]=new StringCell(row.getString("valid"));
			if(row.wasNull()) cells[12]=DataType.getMissingCell();
			cells[13]=new DoubleCell(row.getDouble("avHet"));
			if(row.wasNull()) cells[13]=DataType.getMissingCell();
			cells[14]=new DoubleCell(row.getDouble("avHetSE"));
			if(row.wasNull()) cells[14]=DataType.getMissingCell();
			cells[15]=new StringCell(row.getString("func"));
			if(row.wasNull()) cells[15]=DataType.getMissingCell();
			cells[16]=new StringCell(row.getString("locType"));
			if(row.wasNull()) cells[16]=DataType.getMissingCell();
			cells[17]=new IntCell(row.getInt("weight"));
			if(row.wasNull()) cells[17]=DataType.getMissingCell();
			cells[18]=new StringCell(row.getString("exceptions"));
			if(row.wasNull()) cells[18]=DataType.getMissingCell();
			cells[19]=new IntCell(row.getInt("submitterCount"));
			if(row.wasNull()) cells[19]=DataType.getMissingCell();
			cells[20]=new StringCell(row.getString("submitters"));
			if(row.wasNull()) cells[20]=DataType.getMissingCell();
			cells[21]=new IntCell(row.getInt("alleleFreqCount"));
			if(row.wasNull()) cells[21]=DataType.getMissingCell();
			cells[22]=new StringCell(row.getString("alleles"));
			if(row.wasNull()) cells[22]=DataType.getMissingCell();
			cells[23]=new StringCell(row.getString("alleleNs"));
			if(row.wasNull()) cells[23]=DataType.getMissingCell();
			cells[24]=new StringCell(row.getString("alleleFreqs"));
			if(row.wasNull()) cells[24]=DataType.getMissingCell();
			cells[25]=new StringCell(row.getString("bitfields"));
			if(row.wasNull()) cells[25]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.snp132Mult")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,score,strand,refNCBI,refUCSC,observed,molType,class,valid,avHet,avHetSE,func,locType,weight,exceptions,submitterCount,submitters,alleleFreqCount,alleles,alleleNs,alleleFreqs,bitfields from hg19.snp132Mult where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[26];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".score",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".strand",StringCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".refNCBI",StringCell.TYPE).createSpec();
		colSpecs[8]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".refUCSC",StringCell.TYPE).createSpec();
		colSpecs[9]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".observed",StringCell.TYPE).createSpec();
		colSpecs[10]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".molType",StringCell.TYPE).createSpec();
		colSpecs[11]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".class",StringCell.TYPE).createSpec();
		colSpecs[12]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".valid",StringCell.TYPE).createSpec();
		colSpecs[13]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".avHet",DoubleCell.TYPE).createSpec();
		colSpecs[14]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".avHetSE",DoubleCell.TYPE).createSpec();
		colSpecs[15]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".func",StringCell.TYPE).createSpec();
		colSpecs[16]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".locType",StringCell.TYPE).createSpec();
		colSpecs[17]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".weight",IntCell.TYPE).createSpec();
		colSpecs[18]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".exceptions",StringCell.TYPE).createSpec();
		colSpecs[19]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".submitterCount",IntCell.TYPE).createSpec();
		colSpecs[20]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".submitters",StringCell.TYPE).createSpec();
		colSpecs[21]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".alleleFreqCount",IntCell.TYPE).createSpec();
		colSpecs[22]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".alleles",StringCell.TYPE).createSpec();
		colSpecs[23]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".alleleNs",StringCell.TYPE).createSpec();
		colSpecs[24]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".alleleFreqs",StringCell.TYPE).createSpec();
		colSpecs[25]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bitfields",StringCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "snp132Mult";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[26];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("score"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new StringCell(row.getString("strand"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new StringCell(row.getString("refNCBI"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
			cells[8]=new StringCell(row.getString("refUCSC"));
			if(row.wasNull()) cells[8]=DataType.getMissingCell();
			cells[9]=new StringCell(row.getString("observed"));
			if(row.wasNull()) cells[9]=DataType.getMissingCell();
			cells[10]=new StringCell(row.getString("molType"));
			if(row.wasNull()) cells[10]=DataType.getMissingCell();
			cells[11]=new StringCell(row.getString("class"));
			if(row.wasNull()) cells[11]=DataType.getMissingCell();
			cells[12]=new StringCell(row.getString("valid"));
			if(row.wasNull()) cells[12]=DataType.getMissingCell();
			cells[13]=new DoubleCell(row.getDouble("avHet"));
			if(row.wasNull()) cells[13]=DataType.getMissingCell();
			cells[14]=new DoubleCell(row.getDouble("avHetSE"));
			if(row.wasNull()) cells[14]=DataType.getMissingCell();
			cells[15]=new StringCell(row.getString("func"));
			if(row.wasNull()) cells[15]=DataType.getMissingCell();
			cells[16]=new StringCell(row.getString("locType"));
			if(row.wasNull()) cells[16]=DataType.getMissingCell();
			cells[17]=new IntCell(row.getInt("weight"));
			if(row.wasNull()) cells[17]=DataType.getMissingCell();
			cells[18]=new StringCell(row.getString("exceptions"));
			if(row.wasNull()) cells[18]=DataType.getMissingCell();
			cells[19]=new IntCell(row.getInt("submitterCount"));
			if(row.wasNull()) cells[19]=DataType.getMissingCell();
			cells[20]=new StringCell(row.getString("submitters"));
			if(row.wasNull()) cells[20]=DataType.getMissingCell();
			cells[21]=new IntCell(row.getInt("alleleFreqCount"));
			if(row.wasNull()) cells[21]=DataType.getMissingCell();
			cells[22]=new StringCell(row.getString("alleles"));
			if(row.wasNull()) cells[22]=DataType.getMissingCell();
			cells[23]=new StringCell(row.getString("alleleNs"));
			if(row.wasNull()) cells[23]=DataType.getMissingCell();
			cells[24]=new StringCell(row.getString("alleleFreqs"));
			if(row.wasNull()) cells[24]=DataType.getMissingCell();
			cells[25]=new StringCell(row.getString("bitfields"));
			if(row.wasNull()) cells[25]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.snp132OrthoPt2Pa2Rm2")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,humanObserved,humanAllele,humanStrand,chimpChrom,chimpStart,chimpEnd,chimpAllele,chimpStrand,orangChrom,orangStart,orangEnd,orangAllele,orangStrand,macaqueChrom,macaqueStart,macaqueEnd,macaqueAllele,macaqueStrand from hg19.snp132OrthoPt2Pa2Rm2 where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[23];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".humanObserved",StringCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".humanAllele",StringCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".humanStrand",StringCell.TYPE).createSpec();
		colSpecs[8]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chimpChrom",StringCell.TYPE).createSpec();
		colSpecs[9]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chimpStart",IntCell.TYPE).createSpec();
		colSpecs[10]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chimpEnd",IntCell.TYPE).createSpec();
		colSpecs[11]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chimpAllele",StringCell.TYPE).createSpec();
		colSpecs[12]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chimpStrand",StringCell.TYPE).createSpec();
		colSpecs[13]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".orangChrom",StringCell.TYPE).createSpec();
		colSpecs[14]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".orangStart",IntCell.TYPE).createSpec();
		colSpecs[15]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".orangEnd",IntCell.TYPE).createSpec();
		colSpecs[16]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".orangAllele",StringCell.TYPE).createSpec();
		colSpecs[17]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".orangStrand",StringCell.TYPE).createSpec();
		colSpecs[18]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".macaqueChrom",StringCell.TYPE).createSpec();
		colSpecs[19]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".macaqueStart",IntCell.TYPE).createSpec();
		colSpecs[20]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".macaqueEnd",IntCell.TYPE).createSpec();
		colSpecs[21]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".macaqueAllele",StringCell.TYPE).createSpec();
		colSpecs[22]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".macaqueStrand",StringCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "snp132OrthoPt2Pa2Rm2";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[23];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new StringCell(row.getString("humanObserved"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new StringCell(row.getString("humanAllele"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new StringCell(row.getString("humanStrand"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
			cells[8]=new StringCell(row.getString("chimpChrom"));
			if(row.wasNull()) cells[8]=DataType.getMissingCell();
			cells[9]=new IntCell(row.getInt("chimpStart"));
			if(row.wasNull()) cells[9]=DataType.getMissingCell();
			cells[10]=new IntCell(row.getInt("chimpEnd"));
			if(row.wasNull()) cells[10]=DataType.getMissingCell();
			cells[11]=new StringCell(row.getString("chimpAllele"));
			if(row.wasNull()) cells[11]=DataType.getMissingCell();
			cells[12]=new StringCell(row.getString("chimpStrand"));
			if(row.wasNull()) cells[12]=DataType.getMissingCell();
			cells[13]=new StringCell(row.getString("orangChrom"));
			if(row.wasNull()) cells[13]=DataType.getMissingCell();
			cells[14]=new IntCell(row.getInt("orangStart"));
			if(row.wasNull()) cells[14]=DataType.getMissingCell();
			cells[15]=new IntCell(row.getInt("orangEnd"));
			if(row.wasNull()) cells[15]=DataType.getMissingCell();
			cells[16]=new StringCell(row.getString("orangAllele"));
			if(row.wasNull()) cells[16]=DataType.getMissingCell();
			cells[17]=new StringCell(row.getString("orangStrand"));
			if(row.wasNull()) cells[17]=DataType.getMissingCell();
			cells[18]=new StringCell(row.getString("macaqueChrom"));
			if(row.wasNull()) cells[18]=DataType.getMissingCell();
			cells[19]=new IntCell(row.getInt("macaqueStart"));
			if(row.wasNull()) cells[19]=DataType.getMissingCell();
			cells[20]=new IntCell(row.getInt("macaqueEnd"));
			if(row.wasNull()) cells[20]=DataType.getMissingCell();
			cells[21]=new StringCell(row.getString("macaqueAllele"));
			if(row.wasNull()) cells[21]=DataType.getMissingCell();
			cells[22]=new StringCell(row.getString("macaqueStrand"));
			if(row.wasNull()) cells[22]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.snpArrayAffy250Nsp")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,score,strand,observed,rsId from hg19.snpArrayAffy250Nsp where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[9];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".score",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".strand",StringCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".observed",StringCell.TYPE).createSpec();
		colSpecs[8]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".rsId",StringCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "snpArrayAffy250Nsp";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[9];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("score"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new StringCell(row.getString("strand"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new StringCell(row.getString("observed"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
			cells[8]=new StringCell(row.getString("rsId"));
			if(row.wasNull()) cells[8]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.snpArrayAffy250Sty")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,score,strand,observed,rsId from hg19.snpArrayAffy250Sty where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[9];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".score",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".strand",StringCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".observed",StringCell.TYPE).createSpec();
		colSpecs[8]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".rsId",StringCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "snpArrayAffy250Sty";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[9];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("score"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new StringCell(row.getString("strand"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new StringCell(row.getString("observed"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
			cells[8]=new StringCell(row.getString("rsId"));
			if(row.wasNull()) cells[8]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.snpArrayAffy5")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,score,strand,observed,rsId from hg19.snpArrayAffy5 where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[9];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".score",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".strand",StringCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".observed",StringCell.TYPE).createSpec();
		colSpecs[8]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".rsId",StringCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "snpArrayAffy5";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[9];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("score"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new StringCell(row.getString("strand"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new StringCell(row.getString("observed"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
			cells[8]=new StringCell(row.getString("rsId"));
			if(row.wasNull()) cells[8]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.snpArrayAffy6")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,score,strand,observed,rsId from hg19.snpArrayAffy6 where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[9];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".score",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".strand",StringCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".observed",StringCell.TYPE).createSpec();
		colSpecs[8]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".rsId",StringCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "snpArrayAffy6";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[9];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("score"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new StringCell(row.getString("strand"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new StringCell(row.getString("observed"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
			cells[8]=new StringCell(row.getString("rsId"));
			if(row.wasNull()) cells[8]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.snpArrayAffy6SV")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,score,strand from hg19.snpArrayAffy6SV where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[7];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".score",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".strand",StringCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "snpArrayAffy6SV";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[7];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("score"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new StringCell(row.getString("strand"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.snpArrayIllumina1M")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,score,strand,observed from hg19.snpArrayIllumina1M where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[8];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".score",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".strand",StringCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".observed",StringCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "snpArrayIllumina1M";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[8];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("score"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new StringCell(row.getString("strand"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new StringCell(row.getString("observed"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.snpArrayIllumina300")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,score,strand,observed from hg19.snpArrayIllumina300 where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[8];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".score",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".strand",StringCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".observed",StringCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "snpArrayIllumina300";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[8];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("score"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new StringCell(row.getString("strand"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new StringCell(row.getString("observed"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.snpArrayIllumina550")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,score,strand,observed from hg19.snpArrayIllumina550 where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[8];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".score",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".strand",StringCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".observed",StringCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "snpArrayIllumina550";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[8];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("score"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new StringCell(row.getString("strand"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new StringCell(row.getString("observed"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.snpArrayIllumina650")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,score,strand,observed from hg19.snpArrayIllumina650 where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[8];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".score",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".strand",StringCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".observed",StringCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "snpArrayIllumina650";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[8];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("score"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new StringCell(row.getString("strand"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new StringCell(row.getString("observed"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.snpArrayIlluminaHuman660W_Quad")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,score,strand,observed from hg19.snpArrayIlluminaHuman660W_Quad where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[8];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".score",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".strand",StringCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".observed",StringCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "snpArrayIlluminaHuman660W_Quad";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[8];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("score"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new StringCell(row.getString("strand"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new StringCell(row.getString("observed"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.snpArrayIlluminaHumanCytoSNP_12")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,score,strand,observed from hg19.snpArrayIlluminaHumanCytoSNP_12 where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[8];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".score",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".strand",StringCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".observed",StringCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "snpArrayIlluminaHumanCytoSNP_12";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[8];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("score"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new StringCell(row.getString("strand"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new StringCell(row.getString("observed"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.snpArrayIlluminaHumanOmni1_Quad")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,score,strand,observed from hg19.snpArrayIlluminaHumanOmni1_Quad where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[8];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".score",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".strand",StringCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".observed",StringCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "snpArrayIlluminaHumanOmni1_Quad";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[8];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("score"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new StringCell(row.getString("strand"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new StringCell(row.getString("observed"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.stsMap")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return -1;}
public int getChromColumn() { return 0;}
public int getChromStartColumn() { return 1;}
public int getChromEndColumn() { return 2;}
public String getPreparedStatement() { return "select chrom,chromStart,chromEnd,name,score,identNo,ctgAcc,otherAcc,genethonChrom,genethonPos,marshfieldChrom,marshfieldPos,gm99Gb4Chrom,gm99Gb4Pos,shgcTngChrom,shgcTngPos,shgcG3Chrom,shgcG3Pos,wiYacChrom,wiYacPos,wiRhChrom,wiRhPos,fishChrom,beginBand,endBand,lab,decodeChrom,decodePos from hg19.stsMap where chrom=? and not(chromEnd<=? or chromStart>?)";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[28];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".score",IntCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".identNo",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".ctgAcc",StringCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".otherAcc",StringCell.TYPE).createSpec();
		colSpecs[8]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".genethonChrom",StringCell.TYPE).createSpec();
		colSpecs[9]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".genethonPos",DoubleCell.TYPE).createSpec();
		colSpecs[10]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".marshfieldChrom",StringCell.TYPE).createSpec();
		colSpecs[11]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".marshfieldPos",DoubleCell.TYPE).createSpec();
		colSpecs[12]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".gm99Gb4Chrom",StringCell.TYPE).createSpec();
		colSpecs[13]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".gm99Gb4Pos",DoubleCell.TYPE).createSpec();
		colSpecs[14]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".shgcTngChrom",StringCell.TYPE).createSpec();
		colSpecs[15]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".shgcTngPos",DoubleCell.TYPE).createSpec();
		colSpecs[16]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".shgcG3Chrom",StringCell.TYPE).createSpec();
		colSpecs[17]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".shgcG3Pos",DoubleCell.TYPE).createSpec();
		colSpecs[18]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".wiYacChrom",StringCell.TYPE).createSpec();
		colSpecs[19]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".wiYacPos",DoubleCell.TYPE).createSpec();
		colSpecs[20]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".wiRhChrom",StringCell.TYPE).createSpec();
		colSpecs[21]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".wiRhPos",DoubleCell.TYPE).createSpec();
		colSpecs[22]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".fishChrom",StringCell.TYPE).createSpec();
		colSpecs[23]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".beginBand",StringCell.TYPE).createSpec();
		colSpecs[24]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".endBand",StringCell.TYPE).createSpec();
		colSpecs[25]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".lab",StringCell.TYPE).createSpec();
		colSpecs[26]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".decodeChrom",StringCell.TYPE).createSpec();
		colSpecs[27]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".decodePos",DoubleCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "stsMap";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[28];
			cells[0]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new IntCell(row.getInt("score"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("identNo"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new StringCell(row.getString("ctgAcc"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new StringCell(row.getString("otherAcc"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
			cells[8]=new StringCell(row.getString("genethonChrom"));
			if(row.wasNull()) cells[8]=DataType.getMissingCell();
			cells[9]=new DoubleCell(row.getDouble("genethonPos"));
			if(row.wasNull()) cells[9]=DataType.getMissingCell();
			cells[10]=new StringCell(row.getString("marshfieldChrom"));
			if(row.wasNull()) cells[10]=DataType.getMissingCell();
			cells[11]=new DoubleCell(row.getDouble("marshfieldPos"));
			if(row.wasNull()) cells[11]=DataType.getMissingCell();
			cells[12]=new StringCell(row.getString("gm99Gb4Chrom"));
			if(row.wasNull()) cells[12]=DataType.getMissingCell();
			cells[13]=new DoubleCell(row.getDouble("gm99Gb4Pos"));
			if(row.wasNull()) cells[13]=DataType.getMissingCell();
			cells[14]=new StringCell(row.getString("shgcTngChrom"));
			if(row.wasNull()) cells[14]=DataType.getMissingCell();
			cells[15]=new DoubleCell(row.getDouble("shgcTngPos"));
			if(row.wasNull()) cells[15]=DataType.getMissingCell();
			cells[16]=new StringCell(row.getString("shgcG3Chrom"));
			if(row.wasNull()) cells[16]=DataType.getMissingCell();
			cells[17]=new DoubleCell(row.getDouble("shgcG3Pos"));
			if(row.wasNull()) cells[17]=DataType.getMissingCell();
			cells[18]=new StringCell(row.getString("wiYacChrom"));
			if(row.wasNull()) cells[18]=DataType.getMissingCell();
			cells[19]=new DoubleCell(row.getDouble("wiYacPos"));
			if(row.wasNull()) cells[19]=DataType.getMissingCell();
			cells[20]=new StringCell(row.getString("wiRhChrom"));
			if(row.wasNull()) cells[20]=DataType.getMissingCell();
			cells[21]=new DoubleCell(row.getDouble("wiRhPos"));
			if(row.wasNull()) cells[21]=DataType.getMissingCell();
			cells[22]=new StringCell(row.getString("fishChrom"));
			if(row.wasNull()) cells[22]=DataType.getMissingCell();
			cells[23]=new StringCell(row.getString("beginBand"));
			if(row.wasNull()) cells[23]=DataType.getMissingCell();
			cells[24]=new StringCell(row.getString("endBand"));
			if(row.wasNull()) cells[24]=DataType.getMissingCell();
			cells[25]=new StringCell(row.getString("lab"));
			if(row.wasNull()) cells[25]=DataType.getMissingCell();
			cells[26]=new StringCell(row.getString("decodeChrom"));
			if(row.wasNull()) cells[26]=DataType.getMissingCell();
			cells[27]=new DoubleCell(row.getDouble("decodePos"));
			if(row.wasNull()) cells[27]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.switchDbTss")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,score,strand,confScore,gmName,gmChromStart,gmChromEnd,isPseudo from hg19.switchDbTss where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[12];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".score",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".strand",StringCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".confScore",DoubleCell.TYPE).createSpec();
		colSpecs[8]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".gmName",StringCell.TYPE).createSpec();
		colSpecs[9]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".gmChromStart",IntCell.TYPE).createSpec();
		colSpecs[10]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".gmChromEnd",IntCell.TYPE).createSpec();
		colSpecs[11]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".isPseudo",IntCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "switchDbTss";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[12];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("score"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new StringCell(row.getString("strand"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new DoubleCell(row.getDouble("confScore"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
			cells[8]=new StringCell(row.getString("gmName"));
			if(row.wasNull()) cells[8]=DataType.getMissingCell();
			cells[9]=new IntCell(row.getInt("gmChromStart"));
			if(row.wasNull()) cells[9]=DataType.getMissingCell();
			cells[10]=new IntCell(row.getInt("gmChromEnd"));
			if(row.wasNull()) cells[10]=DataType.getMissingCell();
			cells[11]=new IntCell(row.getInt("isPseudo"));
			if(row.wasNull()) cells[11]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.tRNAs")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,score,strand,aa,ac,intron,trnaScore,genomeUrl,trnaUrl from hg19.tRNAs where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[13];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".score",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".strand",StringCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".aa",StringCell.TYPE).createSpec();
		colSpecs[8]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".ac",StringCell.TYPE).createSpec();
		colSpecs[9]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".intron",StringCell.TYPE).createSpec();
		colSpecs[10]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".trnaScore",DoubleCell.TYPE).createSpec();
		colSpecs[11]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".genomeUrl",StringCell.TYPE).createSpec();
		colSpecs[12]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".trnaUrl",StringCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "tRNAs";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[13];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("score"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new StringCell(row.getString("strand"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new StringCell(row.getString("aa"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
			cells[8]=new StringCell(row.getString("ac"));
			if(row.wasNull()) cells[8]=DataType.getMissingCell();
			cells[9]=new StringCell(row.getString("intron"));
			if(row.wasNull()) cells[9]=DataType.getMissingCell();
			cells[10]=new DoubleCell(row.getDouble("trnaScore"));
			if(row.wasNull()) cells[10]=DataType.getMissingCell();
			cells[11]=new StringCell(row.getString("genomeUrl"));
			if(row.wasNull()) cells[11]=DataType.getMissingCell();
			cells[12]=new StringCell(row.getString("trnaUrl"));
			if(row.wasNull()) cells[12]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.targetScanS")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,score,strand from hg19.targetScanS where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[7];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".score",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".strand",StringCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "targetScanS";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[7];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("score"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new StringCell(row.getString("strand"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.tfbsConsSites")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,score,strand,zScore from hg19.tfbsConsSites where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[8];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".score",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".strand",StringCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".zScore",DoubleCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "tfbsConsSites";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[8];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("score"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new StringCell(row.getString("strand"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new DoubleCell(row.getDouble("zScore"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.uMassBrainHistonePeaksInfant")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,score,floatScore from hg19.uMassBrainHistonePeaksInfant where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[7];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".score",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".floatScore",DoubleCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "uMassBrainHistonePeaksInfant";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[7];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("score"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new DoubleCell(row.getDouble("floatScore"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.uMassBrainHistonePeaksNeuron")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,score,floatScore from hg19.uMassBrainHistonePeaksNeuron where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[7];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".score",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".floatScore",DoubleCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "uMassBrainHistonePeaksNeuron";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[7];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("score"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new DoubleCell(row.getDouble("floatScore"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.uMassBrainHistonePeaksSample")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,score,floatScore from hg19.uMassBrainHistonePeaksSample where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[7];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".score",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".floatScore",DoubleCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "uMassBrainHistonePeaksSample";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[7];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("score"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new DoubleCell(row.getDouble("floatScore"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.ucsfChipSeqH3K4me3BrainCoverage")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,dataValue from hg19.ucsfChipSeqH3K4me3BrainCoverage where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[5];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".dataValue",DoubleCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "ucsfChipSeqH3K4me3BrainCoverage";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[5];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new DoubleCell(row.getDouble("dataValue"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.ucsfMedipSeqBrainCoverage")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,dataValue from hg19.ucsfMedipSeqBrainCoverage where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[5];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".dataValue",DoubleCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "ucsfMedipSeqBrainCoverage";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[5];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new DoubleCell(row.getDouble("dataValue"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.ucsfMedipSeqBrainCpG")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,dataValue from hg19.ucsfMedipSeqBrainCpG where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[5];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".dataValue",DoubleCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "ucsfMedipSeqBrainCpG";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[5];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new DoubleCell(row.getDouble("dataValue"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.ucsfMedipSeqBrainReads")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,score,strand,thickStart,thickEnd,reserved from hg19.ucsfMedipSeqBrainReads where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[10];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".score",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".strand",StringCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".thickStart",IntCell.TYPE).createSpec();
		colSpecs[8]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".thickEnd",IntCell.TYPE).createSpec();
		colSpecs[9]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".reserved",IntCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "ucsfMedipSeqBrainReads";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[10];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("score"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new StringCell(row.getString("strand"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new IntCell(row.getInt("thickStart"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
			cells[8]=new IntCell(row.getInt("thickEnd"));
			if(row.wasNull()) cells[8]=DataType.getMissingCell();
			cells[9]=new IntCell(row.getInt("reserved"));
			if(row.wasNull()) cells[9]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.ucsfMreSeqBrainCpG")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,dataValue from hg19.ucsfMreSeqBrainCpG where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[5];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".dataValue",DoubleCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "ucsfMreSeqBrainCpG";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[5];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new DoubleCell(row.getDouble("dataValue"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.ucsfMreSeqBrainReads")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,score,strand,thickStart,thickEnd,reserved from hg19.ucsfMreSeqBrainReads where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[10];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".score",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".strand",StringCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".thickStart",IntCell.TYPE).createSpec();
		colSpecs[8]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".thickEnd",IntCell.TYPE).createSpec();
		colSpecs[9]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".reserved",IntCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "ucsfMreSeqBrainReads";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[10];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("score"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new StringCell(row.getString("strand"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new IntCell(row.getInt("thickStart"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
			cells[8]=new IntCell(row.getInt("thickEnd"));
			if(row.wasNull()) cells[8]=DataType.getMissingCell();
			cells[9]=new IntCell(row.getInt("reserved"));
			if(row.wasNull()) cells[9]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.ucsfRnaSeqBrainAllCoverage")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,dataValue from hg19.ucsfRnaSeqBrainAllCoverage where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[5];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".dataValue",DoubleCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "ucsfRnaSeqBrainAllCoverage";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[5];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new DoubleCell(row.getDouble("dataValue"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.ucsfRnaSeqBrainAllReads")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,score,strand,thickStart,thickEnd,reserved from hg19.ucsfRnaSeqBrainAllReads where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[10];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".score",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".strand",StringCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".thickStart",IntCell.TYPE).createSpec();
		colSpecs[8]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".thickEnd",IntCell.TYPE).createSpec();
		colSpecs[9]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".reserved",IntCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "ucsfRnaSeqBrainAllReads";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[10];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("score"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new StringCell(row.getString("strand"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new IntCell(row.getInt("thickStart"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
			cells[8]=new IntCell(row.getInt("thickEnd"));
			if(row.wasNull()) cells[8]=DataType.getMissingCell();
			cells[9]=new IntCell(row.getInt("reserved"));
			if(row.wasNull()) cells[9]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.ucsfRnaSeqBrainSmartCoverage")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,dataValue from hg19.ucsfRnaSeqBrainSmartCoverage where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[5];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".dataValue",DoubleCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "ucsfRnaSeqBrainSmartCoverage";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[5];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new DoubleCell(row.getDouble("dataValue"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.ucsfRnaSeqBrainSmartReads")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,score,strand,thickStart,thickEnd,reserved from hg19.ucsfRnaSeqBrainSmartReads where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[10];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".score",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".strand",StringCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".thickStart",IntCell.TYPE).createSpec();
		colSpecs[8]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".thickEnd",IntCell.TYPE).createSpec();
		colSpecs[9]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".reserved",IntCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "ucsfRnaSeqBrainSmartReads";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[10];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("score"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new StringCell(row.getString("strand"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new IntCell(row.getInt("thickStart"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
			cells[8]=new IntCell(row.getInt("thickEnd"));
			if(row.wasNull()) cells[8]=DataType.getMissingCell();
			cells[9]=new IntCell(row.getInt("reserved"));
			if(row.wasNull()) cells[9]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.vegaGene")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 2;}
public int getChromStartColumn() { return 4;}
public int getChromEndColumn() { return 5;}
public String getPreparedStatement() { return "select bin,name,chrom,strand,txStart,txEnd,cdsStart,cdsEnd,exonCount,exonStarts,exonEnds,score,name2,cdsStartStat,cdsEndStat,exonFrames from hg19.vegaGene where chrom=? and not(txEnd<=? or txStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[16];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".strand",StringCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".txStart",IntCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".txEnd",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".cdsStart",IntCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".cdsEnd",IntCell.TYPE).createSpec();
		colSpecs[8]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".exonCount",IntCell.TYPE).createSpec();
		colSpecs[9]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".exonStarts",StringCell.TYPE).createSpec();
		colSpecs[10]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".exonEnds",StringCell.TYPE).createSpec();
		colSpecs[11]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".score",IntCell.TYPE).createSpec();
		colSpecs[12]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name2",StringCell.TYPE).createSpec();
		colSpecs[13]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".cdsStartStat",StringCell.TYPE).createSpec();
		colSpecs[14]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".cdsEndStat",StringCell.TYPE).createSpec();
		colSpecs[15]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".exonFrames",StringCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "vegaGene";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[16];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new StringCell(row.getString("strand"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new IntCell(row.getInt("txStart"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("txEnd"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new IntCell(row.getInt("cdsStart"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new IntCell(row.getInt("cdsEnd"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
			cells[8]=new IntCell(row.getInt("exonCount"));
			if(row.wasNull()) cells[8]=DataType.getMissingCell();
			cells[9]=new StringCell(row.getString("exonStarts"));
			if(row.wasNull()) cells[9]=DataType.getMissingCell();
			cells[10]=new StringCell(row.getString("exonEnds"));
			if(row.wasNull()) cells[10]=DataType.getMissingCell();
			cells[11]=new IntCell(row.getInt("score"));
			if(row.wasNull()) cells[11]=DataType.getMissingCell();
			cells[12]=new StringCell(row.getString("name2"));
			if(row.wasNull()) cells[12]=DataType.getMissingCell();
			cells[13]=new StringCell(row.getString("cdsStartStat"));
			if(row.wasNull()) cells[13]=DataType.getMissingCell();
			cells[14]=new StringCell(row.getString("cdsEndStat"));
			if(row.wasNull()) cells[14]=DataType.getMissingCell();
			cells[15]=new StringCell(row.getString("exonFrames"));
			if(row.wasNull()) cells[15]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.vegaPseudoGene")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 2;}
public int getChromStartColumn() { return 4;}
public int getChromEndColumn() { return 5;}
public String getPreparedStatement() { return "select bin,name,chrom,strand,txStart,txEnd,cdsStart,cdsEnd,exonCount,exonStarts,exonEnds,score,name2,cdsStartStat,cdsEndStat,exonFrames from hg19.vegaPseudoGene where chrom=? and not(txEnd<=? or txStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[16];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".strand",StringCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".txStart",IntCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".txEnd",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".cdsStart",IntCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".cdsEnd",IntCell.TYPE).createSpec();
		colSpecs[8]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".exonCount",IntCell.TYPE).createSpec();
		colSpecs[9]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".exonStarts",StringCell.TYPE).createSpec();
		colSpecs[10]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".exonEnds",StringCell.TYPE).createSpec();
		colSpecs[11]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".score",IntCell.TYPE).createSpec();
		colSpecs[12]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name2",StringCell.TYPE).createSpec();
		colSpecs[13]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".cdsStartStat",StringCell.TYPE).createSpec();
		colSpecs[14]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".cdsEndStat",StringCell.TYPE).createSpec();
		colSpecs[15]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".exonFrames",StringCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "vegaPseudoGene";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[16];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new StringCell(row.getString("strand"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new IntCell(row.getInt("txStart"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("txEnd"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new IntCell(row.getInt("cdsStart"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new IntCell(row.getInt("cdsEnd"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
			cells[8]=new IntCell(row.getInt("exonCount"));
			if(row.wasNull()) cells[8]=DataType.getMissingCell();
			cells[9]=new StringCell(row.getString("exonStarts"));
			if(row.wasNull()) cells[9]=DataType.getMissingCell();
			cells[10]=new StringCell(row.getString("exonEnds"));
			if(row.wasNull()) cells[10]=DataType.getMissingCell();
			cells[11]=new IntCell(row.getInt("score"));
			if(row.wasNull()) cells[11]=DataType.getMissingCell();
			cells[12]=new StringCell(row.getString("name2"));
			if(row.wasNull()) cells[12]=DataType.getMissingCell();
			cells[13]=new StringCell(row.getString("cdsStartStat"));
			if(row.wasNull()) cells[13]=DataType.getMissingCell();
			cells[14]=new StringCell(row.getString("cdsEndStat"));
			if(row.wasNull()) cells[14]=DataType.getMissingCell();
			cells[15]=new StringCell(row.getString("exonFrames"));
			if(row.wasNull()) cells[15]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.vistaEnhancers")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,score from hg19.vistaEnhancers where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[6];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".score",IntCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "vistaEnhancers";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[6];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("score"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.wgRna")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
public String getPreparedStatement() { return "select bin,chrom,chromStart,chromEnd,name,score,strand,thickStart,thickEnd,type from hg19.wgRna where chrom=? and not(chromEnd<=? or chromStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[10];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromStart",IntCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chromEnd",IntCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".score",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".strand",StringCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".thickStart",IntCell.TYPE).createSpec();
		colSpecs[8]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".thickEnd",IntCell.TYPE).createSpec();
		colSpecs[9]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".type",StringCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "wgRna";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[10];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new IntCell(row.getInt("chromStart"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new IntCell(row.getInt("chromEnd"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("score"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new StringCell(row.getString("strand"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new IntCell(row.getInt("thickStart"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
			cells[8]=new IntCell(row.getInt("thickEnd"));
			if(row.wasNull()) cells[8]=DataType.getMissingCell();
			cells[9]=new StringCell(row.getString("type"));
			if(row.wasNull()) cells[9]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.xenoRefFlat")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return -1;}
public int getChromColumn() { return 2;}
public int getChromStartColumn() { return 4;}
public int getChromEndColumn() { return 5;}
public String getPreparedStatement() { return "select geneName,name,chrom,strand,txStart,txEnd,cdsStart,cdsEnd,exonCount,exonStarts,exonEnds from hg19.xenoRefFlat where chrom=? and not(txEnd<=? or txStart>?)";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[11];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".geneName",StringCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".strand",StringCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".txStart",IntCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".txEnd",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".cdsStart",IntCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".cdsEnd",IntCell.TYPE).createSpec();
		colSpecs[8]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".exonCount",IntCell.TYPE).createSpec();
		colSpecs[9]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".exonStarts",StringCell.TYPE).createSpec();
		colSpecs[10]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".exonEnds",StringCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "xenoRefFlat";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[11];
			cells[0]=new StringCell(row.getString("geneName"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new StringCell(row.getString("strand"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new IntCell(row.getInt("txStart"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("txEnd"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new IntCell(row.getInt("cdsStart"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new IntCell(row.getInt("cdsEnd"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
			cells[8]=new IntCell(row.getInt("exonCount"));
			if(row.wasNull()) cells[8]=DataType.getMissingCell();
			cells[9]=new StringCell(row.getString("exonStarts"));
			if(row.wasNull()) cells[9]=DataType.getMissingCell();
			cells[10]=new StringCell(row.getString("exonEnds"));
			if(row.wasNull()) cells[10]=DataType.getMissingCell();
		return cells;
		}
	}
;
if(id.equals("hg19.xenoRefGene")) return 
new UcscDatabaseMysqlHandler()
	{
public int getBinColumn() { return 0;}
public int getChromColumn() { return 2;}
public int getChromStartColumn() { return 4;}
public int getChromEndColumn() { return 5;}
public String getPreparedStatement() { return "select bin,name,chrom,strand,txStart,txEnd,cdsStart,cdsEnd,exonCount,exonStarts,exonEnds,score,name2,cdsStartStat,cdsEndStat,exonFrames from hg19.xenoRefGene where chrom=? and not(txEnd<=? or txStart>?) and bin=?";}
	public DataTableSpec getDataTableSpec()
		{
		DataColumnSpec  colSpecs[]=new DataColumnSpec[16];
		colSpecs[0]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".bin",IntCell.TYPE).createSpec();
		colSpecs[1]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name",StringCell.TYPE).createSpec();
		colSpecs[2]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".chrom",StringCell.TYPE).createSpec();
		colSpecs[3]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".strand",StringCell.TYPE).createSpec();
		colSpecs[4]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".txStart",IntCell.TYPE).createSpec();
		colSpecs[5]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".txEnd",IntCell.TYPE).createSpec();
		colSpecs[6]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".cdsStart",IntCell.TYPE).createSpec();
		colSpecs[7]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".cdsEnd",IntCell.TYPE).createSpec();
		colSpecs[8]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".exonCount",IntCell.TYPE).createSpec();
		colSpecs[9]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".exonStarts",StringCell.TYPE).createSpec();
		colSpecs[10]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".exonEnds",StringCell.TYPE).createSpec();
		colSpecs[11]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".score",IntCell.TYPE).createSpec();
		colSpecs[12]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".name2",StringCell.TYPE).createSpec();
		colSpecs[13]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".cdsStartStat",StringCell.TYPE).createSpec();
		colSpecs[14]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".cdsEndStat",StringCell.TYPE).createSpec();
		colSpecs[15]=new DataColumnSpecCreator(getDatabaseName()+"."+getTableName()+".exonFrames",StringCell.TYPE).createSpec();
		return new DataTableSpec(colSpecs);
		}
	public String getDatabaseName()
		{
		return "hg19";
		}
	public String getTableName()
		{
		return "xenoRefGene";
		}
	public DataCell[] parse(ResultSet row) throws SQLException
		{
DataCell cells[]=new DataCell[16];
			cells[0]=new IntCell(row.getInt("bin"));
			if(row.wasNull()) cells[0]=DataType.getMissingCell();
			cells[1]=new StringCell(row.getString("name"));
			if(row.wasNull()) cells[1]=DataType.getMissingCell();
			cells[2]=new StringCell(row.getString("chrom"));
			if(row.wasNull()) cells[2]=DataType.getMissingCell();
			cells[3]=new StringCell(row.getString("strand"));
			if(row.wasNull()) cells[3]=DataType.getMissingCell();
			cells[4]=new IntCell(row.getInt("txStart"));
			if(row.wasNull()) cells[4]=DataType.getMissingCell();
			cells[5]=new IntCell(row.getInt("txEnd"));
			if(row.wasNull()) cells[5]=DataType.getMissingCell();
			cells[6]=new IntCell(row.getInt("cdsStart"));
			if(row.wasNull()) cells[6]=DataType.getMissingCell();
			cells[7]=new IntCell(row.getInt("cdsEnd"));
			if(row.wasNull()) cells[7]=DataType.getMissingCell();
			cells[8]=new IntCell(row.getInt("exonCount"));
			if(row.wasNull()) cells[8]=DataType.getMissingCell();
			cells[9]=new StringCell(row.getString("exonStarts"));
			if(row.wasNull()) cells[9]=DataType.getMissingCell();
			cells[10]=new StringCell(row.getString("exonEnds"));
			if(row.wasNull()) cells[10]=DataType.getMissingCell();
			cells[11]=new IntCell(row.getInt("score"));
			if(row.wasNull()) cells[11]=DataType.getMissingCell();
			cells[12]=new StringCell(row.getString("name2"));
			if(row.wasNull()) cells[12]=DataType.getMissingCell();
			cells[13]=new StringCell(row.getString("cdsStartStat"));
			if(row.wasNull()) cells[13]=DataType.getMissingCell();
			cells[14]=new StringCell(row.getString("cdsEndStat"));
			if(row.wasNull()) cells[14]=DataType.getMissingCell();
			cells[15]=new StringCell(row.getString("exonFrames"));
			if(row.wasNull()) cells[15]=DataType.getMissingCell();
		return cells;
		}
	}
;
		return null;
		}
	}

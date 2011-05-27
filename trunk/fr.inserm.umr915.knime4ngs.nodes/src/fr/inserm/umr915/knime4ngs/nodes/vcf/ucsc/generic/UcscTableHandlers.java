package fr.inserm.umr915.knime4ngs.nodes.vcf.ucsc.generic;
import org.knime.core.data.*;
import org.knime.core.data.def.*;
public class UcscTableHandlers
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
	public UcscTableHandlers() {} 
	public static String[] getTableIds() { return TABLE_IDS;}
	public UcscDatabaseHandler getHandlerById(String id)
		{
if(id.equals("hg19.acembly")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/acembly.txt.gz";
		}
public int getChromColumn() { return 2;}
public int getChromStartColumn() { return 4;}
public int getChromEndColumn() { return 5;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[11];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new StringCell((tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new StringCell((tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new IntCell(Integer.parseInt(tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new IntCell(Integer.parseInt(tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new IntCell(Integer.parseInt(tokens[7]));
			}
		if(tokens.length<=8 || tokens[8].equals("NULL"))
			{
			cells[8]=DataType.getMissingCell();
			}
		else
			{
			cells[8]=new IntCell(Integer.parseInt(tokens[8]));
			}
		if(tokens.length<=9 || tokens[9].equals("NULL"))
			{
			cells[9]=DataType.getMissingCell();
			}
		else
			{
			cells[9]=new StringCell((tokens[9]));
			}
		if(tokens.length<=10 || tokens[10].equals("NULL"))
			{
			cells[10]=DataType.getMissingCell();
			}
		else
			{
			cells[10]=new StringCell((tokens[10]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.affyExonProbeAmbiguous")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/affyExonProbeAmbiguous.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[11];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new StringCell((tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new IntCell(Integer.parseInt(tokens[7]));
			}
		if(tokens.length<=8 || tokens[8].equals("NULL"))
			{
			cells[8]=DataType.getMissingCell();
			}
		else
			{
			cells[8]=new IntCell(Integer.parseInt(tokens[8]));
			}
		if(tokens.length<=9 || tokens[9].equals("NULL"))
			{
			cells[9]=DataType.getMissingCell();
			}
		else
			{
			cells[9]=new IntCell(Integer.parseInt(tokens[9]));
			}
		if(tokens.length<=10 || tokens[10].equals("NULL"))
			{
			cells[10]=DataType.getMissingCell();
			}
		else
			{
			cells[10]=new IntCell(Integer.parseInt(tokens[10]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.affyExonProbeCore")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/affyExonProbeCore.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[11];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new StringCell((tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new IntCell(Integer.parseInt(tokens[7]));
			}
		if(tokens.length<=8 || tokens[8].equals("NULL"))
			{
			cells[8]=DataType.getMissingCell();
			}
		else
			{
			cells[8]=new IntCell(Integer.parseInt(tokens[8]));
			}
		if(tokens.length<=9 || tokens[9].equals("NULL"))
			{
			cells[9]=DataType.getMissingCell();
			}
		else
			{
			cells[9]=new IntCell(Integer.parseInt(tokens[9]));
			}
		if(tokens.length<=10 || tokens[10].equals("NULL"))
			{
			cells[10]=DataType.getMissingCell();
			}
		else
			{
			cells[10]=new IntCell(Integer.parseInt(tokens[10]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.affyExonProbeExtended")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/affyExonProbeExtended.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[11];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new StringCell((tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new IntCell(Integer.parseInt(tokens[7]));
			}
		if(tokens.length<=8 || tokens[8].equals("NULL"))
			{
			cells[8]=DataType.getMissingCell();
			}
		else
			{
			cells[8]=new IntCell(Integer.parseInt(tokens[8]));
			}
		if(tokens.length<=9 || tokens[9].equals("NULL"))
			{
			cells[9]=DataType.getMissingCell();
			}
		else
			{
			cells[9]=new IntCell(Integer.parseInt(tokens[9]));
			}
		if(tokens.length<=10 || tokens[10].equals("NULL"))
			{
			cells[10]=DataType.getMissingCell();
			}
		else
			{
			cells[10]=new IntCell(Integer.parseInt(tokens[10]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.affyExonProbeFree")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/affyExonProbeFree.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[11];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new StringCell((tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new IntCell(Integer.parseInt(tokens[7]));
			}
		if(tokens.length<=8 || tokens[8].equals("NULL"))
			{
			cells[8]=DataType.getMissingCell();
			}
		else
			{
			cells[8]=new IntCell(Integer.parseInt(tokens[8]));
			}
		if(tokens.length<=9 || tokens[9].equals("NULL"))
			{
			cells[9]=DataType.getMissingCell();
			}
		else
			{
			cells[9]=new IntCell(Integer.parseInt(tokens[9]));
			}
		if(tokens.length<=10 || tokens[10].equals("NULL"))
			{
			cells[10]=DataType.getMissingCell();
			}
		else
			{
			cells[10]=new IntCell(Integer.parseInt(tokens[10]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.affyExonProbeFull")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/affyExonProbeFull.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[11];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new StringCell((tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new IntCell(Integer.parseInt(tokens[7]));
			}
		if(tokens.length<=8 || tokens[8].equals("NULL"))
			{
			cells[8]=DataType.getMissingCell();
			}
		else
			{
			cells[8]=new IntCell(Integer.parseInt(tokens[8]));
			}
		if(tokens.length<=9 || tokens[9].equals("NULL"))
			{
			cells[9]=DataType.getMissingCell();
			}
		else
			{
			cells[9]=new IntCell(Integer.parseInt(tokens[9]));
			}
		if(tokens.length<=10 || tokens[10].equals("NULL"))
			{
			cells[10]=DataType.getMissingCell();
			}
		else
			{
			cells[10]=new IntCell(Integer.parseInt(tokens[10]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.affyExonProbesetAmbiguous")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/affyExonProbesetAmbiguous.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[13];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new StringCell((tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new IntCell(Integer.parseInt(tokens[7]));
			}
		if(tokens.length<=8 || tokens[8].equals("NULL"))
			{
			cells[8]=DataType.getMissingCell();
			}
		else
			{
			cells[8]=new IntCell(Integer.parseInt(tokens[8]));
			}
		if(tokens.length<=9 || tokens[9].equals("NULL"))
			{
			cells[9]=DataType.getMissingCell();
			}
		else
			{
			cells[9]=new IntCell(Integer.parseInt(tokens[9]));
			}
		if(tokens.length<=10 || tokens[10].equals("NULL"))
			{
			cells[10]=DataType.getMissingCell();
			}
		else
			{
			cells[10]=new IntCell(Integer.parseInt(tokens[10]));
			}
		if(tokens.length<=11 || tokens[11].equals("NULL"))
			{
			cells[11]=DataType.getMissingCell();
			}
		else
			{
			cells[11]=new StringCell((tokens[11]));
			}
		if(tokens.length<=12 || tokens[12].equals("NULL"))
			{
			cells[12]=DataType.getMissingCell();
			}
		else
			{
			cells[12]=new StringCell((tokens[12]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.affyExonProbesetCore")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/affyExonProbesetCore.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[13];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new StringCell((tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new IntCell(Integer.parseInt(tokens[7]));
			}
		if(tokens.length<=8 || tokens[8].equals("NULL"))
			{
			cells[8]=DataType.getMissingCell();
			}
		else
			{
			cells[8]=new IntCell(Integer.parseInt(tokens[8]));
			}
		if(tokens.length<=9 || tokens[9].equals("NULL"))
			{
			cells[9]=DataType.getMissingCell();
			}
		else
			{
			cells[9]=new IntCell(Integer.parseInt(tokens[9]));
			}
		if(tokens.length<=10 || tokens[10].equals("NULL"))
			{
			cells[10]=DataType.getMissingCell();
			}
		else
			{
			cells[10]=new IntCell(Integer.parseInt(tokens[10]));
			}
		if(tokens.length<=11 || tokens[11].equals("NULL"))
			{
			cells[11]=DataType.getMissingCell();
			}
		else
			{
			cells[11]=new StringCell((tokens[11]));
			}
		if(tokens.length<=12 || tokens[12].equals("NULL"))
			{
			cells[12]=DataType.getMissingCell();
			}
		else
			{
			cells[12]=new StringCell((tokens[12]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.affyExonProbesetExtended")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/affyExonProbesetExtended.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[13];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new StringCell((tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new IntCell(Integer.parseInt(tokens[7]));
			}
		if(tokens.length<=8 || tokens[8].equals("NULL"))
			{
			cells[8]=DataType.getMissingCell();
			}
		else
			{
			cells[8]=new IntCell(Integer.parseInt(tokens[8]));
			}
		if(tokens.length<=9 || tokens[9].equals("NULL"))
			{
			cells[9]=DataType.getMissingCell();
			}
		else
			{
			cells[9]=new IntCell(Integer.parseInt(tokens[9]));
			}
		if(tokens.length<=10 || tokens[10].equals("NULL"))
			{
			cells[10]=DataType.getMissingCell();
			}
		else
			{
			cells[10]=new IntCell(Integer.parseInt(tokens[10]));
			}
		if(tokens.length<=11 || tokens[11].equals("NULL"))
			{
			cells[11]=DataType.getMissingCell();
			}
		else
			{
			cells[11]=new StringCell((tokens[11]));
			}
		if(tokens.length<=12 || tokens[12].equals("NULL"))
			{
			cells[12]=DataType.getMissingCell();
			}
		else
			{
			cells[12]=new StringCell((tokens[12]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.affyExonProbesetFree")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/affyExonProbesetFree.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[13];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new StringCell((tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new IntCell(Integer.parseInt(tokens[7]));
			}
		if(tokens.length<=8 || tokens[8].equals("NULL"))
			{
			cells[8]=DataType.getMissingCell();
			}
		else
			{
			cells[8]=new IntCell(Integer.parseInt(tokens[8]));
			}
		if(tokens.length<=9 || tokens[9].equals("NULL"))
			{
			cells[9]=DataType.getMissingCell();
			}
		else
			{
			cells[9]=new IntCell(Integer.parseInt(tokens[9]));
			}
		if(tokens.length<=10 || tokens[10].equals("NULL"))
			{
			cells[10]=DataType.getMissingCell();
			}
		else
			{
			cells[10]=new IntCell(Integer.parseInt(tokens[10]));
			}
		if(tokens.length<=11 || tokens[11].equals("NULL"))
			{
			cells[11]=DataType.getMissingCell();
			}
		else
			{
			cells[11]=new StringCell((tokens[11]));
			}
		if(tokens.length<=12 || tokens[12].equals("NULL"))
			{
			cells[12]=DataType.getMissingCell();
			}
		else
			{
			cells[12]=new StringCell((tokens[12]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.affyExonProbesetFull")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/affyExonProbesetFull.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[13];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new StringCell((tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new IntCell(Integer.parseInt(tokens[7]));
			}
		if(tokens.length<=8 || tokens[8].equals("NULL"))
			{
			cells[8]=DataType.getMissingCell();
			}
		else
			{
			cells[8]=new IntCell(Integer.parseInt(tokens[8]));
			}
		if(tokens.length<=9 || tokens[9].equals("NULL"))
			{
			cells[9]=DataType.getMissingCell();
			}
		else
			{
			cells[9]=new IntCell(Integer.parseInt(tokens[9]));
			}
		if(tokens.length<=10 || tokens[10].equals("NULL"))
			{
			cells[10]=DataType.getMissingCell();
			}
		else
			{
			cells[10]=new IntCell(Integer.parseInt(tokens[10]));
			}
		if(tokens.length<=11 || tokens[11].equals("NULL"))
			{
			cells[11]=DataType.getMissingCell();
			}
		else
			{
			cells[11]=new StringCell((tokens[11]));
			}
		if(tokens.length<=12 || tokens[12].equals("NULL"))
			{
			cells[12]=DataType.getMissingCell();
			}
		else
			{
			cells[12]=new StringCell((tokens[12]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.agilentCgh1x1m")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/agilentCgh1x1m.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[5];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.agilentCgh1x244k")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/agilentCgh1x244k.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[5];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.agilentCgh2x105k")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/agilentCgh2x105k.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[5];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.agilentCgh2x400k")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/agilentCgh2x400k.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[5];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.agilentCgh4x180k")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/agilentCgh4x180k.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[5];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.agilentCgh4x44k")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/agilentCgh4x44k.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[5];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.agilentCgh8x60k")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/agilentCgh8x60k.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[5];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.agilentCghSnp2x400k")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/agilentCghSnp2x400k.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[10];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new StringCell((tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new IntCell(Integer.parseInt(tokens[7]));
			}
		if(tokens.length<=8 || tokens[8].equals("NULL"))
			{
			cells[8]=DataType.getMissingCell();
			}
		else
			{
			cells[8]=new IntCell(Integer.parseInt(tokens[8]));
			}
		if(tokens.length<=9 || tokens[9].equals("NULL"))
			{
			cells[9]=DataType.getMissingCell();
			}
		else
			{
			cells[9]=new IntCell(Integer.parseInt(tokens[9]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.agilentCghSnp4x180k")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/agilentCghSnp4x180k.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[10];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new StringCell((tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new IntCell(Integer.parseInt(tokens[7]));
			}
		if(tokens.length<=8 || tokens[8].equals("NULL"))
			{
			cells[8]=DataType.getMissingCell();
			}
		else
			{
			cells[8]=new IntCell(Integer.parseInt(tokens[8]));
			}
		if(tokens.length<=9 || tokens[9].equals("NULL"))
			{
			cells[9]=DataType.getMissingCell();
			}
		else
			{
			cells[9]=new IntCell(Integer.parseInt(tokens[9]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.agilentHrd1x1m")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/agilentHrd1x1m.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[5];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.altSeqHaplotypes")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/altSeqHaplotypes.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[7];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new StringCell((tokens[6]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.altSeqPatches")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/altSeqPatches.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[7];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new StringCell((tokens[6]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.bacEndPairs")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/bacEndPairs.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[12];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new StringCell((tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new StringCell((tokens[7]));
			}
		if(tokens.length<=8 || tokens[8].equals("NULL"))
			{
			cells[8]=DataType.getMissingCell();
			}
		else
			{
			cells[8]=new IntCell(Integer.parseInt(tokens[8]));
			}
		if(tokens.length<=9 || tokens[9].equals("NULL"))
			{
			cells[9]=DataType.getMissingCell();
			}
		else
			{
			cells[9]=new StringCell((tokens[9]));
			}
		if(tokens.length<=10 || tokens[10].equals("NULL"))
			{
			cells[10]=DataType.getMissingCell();
			}
		else
			{
			cells[10]=new StringCell((tokens[10]));
			}
		if(tokens.length<=11 || tokens[11].equals("NULL"))
			{
			cells[11]=DataType.getMissingCell();
			}
		else
			{
			cells[11]=new StringCell((tokens[11]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.burgeRnaSeqGemMapperAlignAdipose")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/burgeRnaSeqGemMapperAlignAdipose.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[13];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new StringCell((tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new IntCell(Integer.parseInt(tokens[7]));
			}
		if(tokens.length<=8 || tokens[8].equals("NULL"))
			{
			cells[8]=DataType.getMissingCell();
			}
		else
			{
			cells[8]=new IntCell(Integer.parseInt(tokens[8]));
			}
		if(tokens.length<=9 || tokens[9].equals("NULL"))
			{
			cells[9]=DataType.getMissingCell();
			}
		else
			{
			cells[9]=new IntCell(Integer.parseInt(tokens[9]));
			}
		if(tokens.length<=10 || tokens[10].equals("NULL"))
			{
			cells[10]=DataType.getMissingCell();
			}
		else
			{
			cells[10]=new IntCell(Integer.parseInt(tokens[10]));
			}
		if(tokens.length<=11 || tokens[11].equals("NULL"))
			{
			cells[11]=DataType.getMissingCell();
			}
		else
			{
			cells[11]=new StringCell((tokens[11]));
			}
		if(tokens.length<=12 || tokens[12].equals("NULL"))
			{
			cells[12]=DataType.getMissingCell();
			}
		else
			{
			cells[12]=new StringCell((tokens[12]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.burgeRnaSeqGemMapperAlignAdiposeAllRawSignal")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/burgeRnaSeqGemMapperAlignAdiposeAllRawSignal.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[5];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new DoubleCell(Double.parseDouble(tokens[4]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.burgeRnaSeqGemMapperAlignBT474")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/burgeRnaSeqGemMapperAlignBT474.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[13];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new StringCell((tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new IntCell(Integer.parseInt(tokens[7]));
			}
		if(tokens.length<=8 || tokens[8].equals("NULL"))
			{
			cells[8]=DataType.getMissingCell();
			}
		else
			{
			cells[8]=new IntCell(Integer.parseInt(tokens[8]));
			}
		if(tokens.length<=9 || tokens[9].equals("NULL"))
			{
			cells[9]=DataType.getMissingCell();
			}
		else
			{
			cells[9]=new IntCell(Integer.parseInt(tokens[9]));
			}
		if(tokens.length<=10 || tokens[10].equals("NULL"))
			{
			cells[10]=DataType.getMissingCell();
			}
		else
			{
			cells[10]=new IntCell(Integer.parseInt(tokens[10]));
			}
		if(tokens.length<=11 || tokens[11].equals("NULL"))
			{
			cells[11]=DataType.getMissingCell();
			}
		else
			{
			cells[11]=new StringCell((tokens[11]));
			}
		if(tokens.length<=12 || tokens[12].equals("NULL"))
			{
			cells[12]=DataType.getMissingCell();
			}
		else
			{
			cells[12]=new StringCell((tokens[12]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.burgeRnaSeqGemMapperAlignBT474AllRawSignal")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/burgeRnaSeqGemMapperAlignBT474AllRawSignal.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[5];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new DoubleCell(Double.parseDouble(tokens[4]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.burgeRnaSeqGemMapperAlignBrain")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/burgeRnaSeqGemMapperAlignBrain.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[13];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new StringCell((tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new IntCell(Integer.parseInt(tokens[7]));
			}
		if(tokens.length<=8 || tokens[8].equals("NULL"))
			{
			cells[8]=DataType.getMissingCell();
			}
		else
			{
			cells[8]=new IntCell(Integer.parseInt(tokens[8]));
			}
		if(tokens.length<=9 || tokens[9].equals("NULL"))
			{
			cells[9]=DataType.getMissingCell();
			}
		else
			{
			cells[9]=new IntCell(Integer.parseInt(tokens[9]));
			}
		if(tokens.length<=10 || tokens[10].equals("NULL"))
			{
			cells[10]=DataType.getMissingCell();
			}
		else
			{
			cells[10]=new IntCell(Integer.parseInt(tokens[10]));
			}
		if(tokens.length<=11 || tokens[11].equals("NULL"))
			{
			cells[11]=DataType.getMissingCell();
			}
		else
			{
			cells[11]=new StringCell((tokens[11]));
			}
		if(tokens.length<=12 || tokens[12].equals("NULL"))
			{
			cells[12]=DataType.getMissingCell();
			}
		else
			{
			cells[12]=new StringCell((tokens[12]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.burgeRnaSeqGemMapperAlignBrainAllRawSignal")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/burgeRnaSeqGemMapperAlignBrainAllRawSignal.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[5];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new DoubleCell(Double.parseDouble(tokens[4]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.burgeRnaSeqGemMapperAlignBreast")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/burgeRnaSeqGemMapperAlignBreast.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[13];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new StringCell((tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new IntCell(Integer.parseInt(tokens[7]));
			}
		if(tokens.length<=8 || tokens[8].equals("NULL"))
			{
			cells[8]=DataType.getMissingCell();
			}
		else
			{
			cells[8]=new IntCell(Integer.parseInt(tokens[8]));
			}
		if(tokens.length<=9 || tokens[9].equals("NULL"))
			{
			cells[9]=DataType.getMissingCell();
			}
		else
			{
			cells[9]=new IntCell(Integer.parseInt(tokens[9]));
			}
		if(tokens.length<=10 || tokens[10].equals("NULL"))
			{
			cells[10]=DataType.getMissingCell();
			}
		else
			{
			cells[10]=new IntCell(Integer.parseInt(tokens[10]));
			}
		if(tokens.length<=11 || tokens[11].equals("NULL"))
			{
			cells[11]=DataType.getMissingCell();
			}
		else
			{
			cells[11]=new StringCell((tokens[11]));
			}
		if(tokens.length<=12 || tokens[12].equals("NULL"))
			{
			cells[12]=DataType.getMissingCell();
			}
		else
			{
			cells[12]=new StringCell((tokens[12]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.burgeRnaSeqGemMapperAlignBreastAllRawSignal")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/burgeRnaSeqGemMapperAlignBreastAllRawSignal.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[5];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new DoubleCell(Double.parseDouble(tokens[4]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.burgeRnaSeqGemMapperAlignColon")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/burgeRnaSeqGemMapperAlignColon.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[13];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new StringCell((tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new IntCell(Integer.parseInt(tokens[7]));
			}
		if(tokens.length<=8 || tokens[8].equals("NULL"))
			{
			cells[8]=DataType.getMissingCell();
			}
		else
			{
			cells[8]=new IntCell(Integer.parseInt(tokens[8]));
			}
		if(tokens.length<=9 || tokens[9].equals("NULL"))
			{
			cells[9]=DataType.getMissingCell();
			}
		else
			{
			cells[9]=new IntCell(Integer.parseInt(tokens[9]));
			}
		if(tokens.length<=10 || tokens[10].equals("NULL"))
			{
			cells[10]=DataType.getMissingCell();
			}
		else
			{
			cells[10]=new IntCell(Integer.parseInt(tokens[10]));
			}
		if(tokens.length<=11 || tokens[11].equals("NULL"))
			{
			cells[11]=DataType.getMissingCell();
			}
		else
			{
			cells[11]=new StringCell((tokens[11]));
			}
		if(tokens.length<=12 || tokens[12].equals("NULL"))
			{
			cells[12]=DataType.getMissingCell();
			}
		else
			{
			cells[12]=new StringCell((tokens[12]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.burgeRnaSeqGemMapperAlignColonAllRawSignal")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/burgeRnaSeqGemMapperAlignColonAllRawSignal.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[5];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new DoubleCell(Double.parseDouble(tokens[4]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.burgeRnaSeqGemMapperAlignHME")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/burgeRnaSeqGemMapperAlignHME.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[13];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new StringCell((tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new IntCell(Integer.parseInt(tokens[7]));
			}
		if(tokens.length<=8 || tokens[8].equals("NULL"))
			{
			cells[8]=DataType.getMissingCell();
			}
		else
			{
			cells[8]=new IntCell(Integer.parseInt(tokens[8]));
			}
		if(tokens.length<=9 || tokens[9].equals("NULL"))
			{
			cells[9]=DataType.getMissingCell();
			}
		else
			{
			cells[9]=new IntCell(Integer.parseInt(tokens[9]));
			}
		if(tokens.length<=10 || tokens[10].equals("NULL"))
			{
			cells[10]=DataType.getMissingCell();
			}
		else
			{
			cells[10]=new IntCell(Integer.parseInt(tokens[10]));
			}
		if(tokens.length<=11 || tokens[11].equals("NULL"))
			{
			cells[11]=DataType.getMissingCell();
			}
		else
			{
			cells[11]=new StringCell((tokens[11]));
			}
		if(tokens.length<=12 || tokens[12].equals("NULL"))
			{
			cells[12]=DataType.getMissingCell();
			}
		else
			{
			cells[12]=new StringCell((tokens[12]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.burgeRnaSeqGemMapperAlignHMEAllRawSignal")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/burgeRnaSeqGemMapperAlignHMEAllRawSignal.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[5];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new DoubleCell(Double.parseDouble(tokens[4]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.burgeRnaSeqGemMapperAlignHeart")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/burgeRnaSeqGemMapperAlignHeart.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[13];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new StringCell((tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new IntCell(Integer.parseInt(tokens[7]));
			}
		if(tokens.length<=8 || tokens[8].equals("NULL"))
			{
			cells[8]=DataType.getMissingCell();
			}
		else
			{
			cells[8]=new IntCell(Integer.parseInt(tokens[8]));
			}
		if(tokens.length<=9 || tokens[9].equals("NULL"))
			{
			cells[9]=DataType.getMissingCell();
			}
		else
			{
			cells[9]=new IntCell(Integer.parseInt(tokens[9]));
			}
		if(tokens.length<=10 || tokens[10].equals("NULL"))
			{
			cells[10]=DataType.getMissingCell();
			}
		else
			{
			cells[10]=new IntCell(Integer.parseInt(tokens[10]));
			}
		if(tokens.length<=11 || tokens[11].equals("NULL"))
			{
			cells[11]=DataType.getMissingCell();
			}
		else
			{
			cells[11]=new StringCell((tokens[11]));
			}
		if(tokens.length<=12 || tokens[12].equals("NULL"))
			{
			cells[12]=DataType.getMissingCell();
			}
		else
			{
			cells[12]=new StringCell((tokens[12]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.burgeRnaSeqGemMapperAlignHeartAllRawSignal")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/burgeRnaSeqGemMapperAlignHeartAllRawSignal.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[5];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new DoubleCell(Double.parseDouble(tokens[4]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.burgeRnaSeqGemMapperAlignLiver")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/burgeRnaSeqGemMapperAlignLiver.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[13];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new StringCell((tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new IntCell(Integer.parseInt(tokens[7]));
			}
		if(tokens.length<=8 || tokens[8].equals("NULL"))
			{
			cells[8]=DataType.getMissingCell();
			}
		else
			{
			cells[8]=new IntCell(Integer.parseInt(tokens[8]));
			}
		if(tokens.length<=9 || tokens[9].equals("NULL"))
			{
			cells[9]=DataType.getMissingCell();
			}
		else
			{
			cells[9]=new IntCell(Integer.parseInt(tokens[9]));
			}
		if(tokens.length<=10 || tokens[10].equals("NULL"))
			{
			cells[10]=DataType.getMissingCell();
			}
		else
			{
			cells[10]=new IntCell(Integer.parseInt(tokens[10]));
			}
		if(tokens.length<=11 || tokens[11].equals("NULL"))
			{
			cells[11]=DataType.getMissingCell();
			}
		else
			{
			cells[11]=new StringCell((tokens[11]));
			}
		if(tokens.length<=12 || tokens[12].equals("NULL"))
			{
			cells[12]=DataType.getMissingCell();
			}
		else
			{
			cells[12]=new StringCell((tokens[12]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.burgeRnaSeqGemMapperAlignLiverAllRawSignal")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/burgeRnaSeqGemMapperAlignLiverAllRawSignal.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[5];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new DoubleCell(Double.parseDouble(tokens[4]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.burgeRnaSeqGemMapperAlignLymphNode")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/burgeRnaSeqGemMapperAlignLymphNode.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[13];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new StringCell((tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new IntCell(Integer.parseInt(tokens[7]));
			}
		if(tokens.length<=8 || tokens[8].equals("NULL"))
			{
			cells[8]=DataType.getMissingCell();
			}
		else
			{
			cells[8]=new IntCell(Integer.parseInt(tokens[8]));
			}
		if(tokens.length<=9 || tokens[9].equals("NULL"))
			{
			cells[9]=DataType.getMissingCell();
			}
		else
			{
			cells[9]=new IntCell(Integer.parseInt(tokens[9]));
			}
		if(tokens.length<=10 || tokens[10].equals("NULL"))
			{
			cells[10]=DataType.getMissingCell();
			}
		else
			{
			cells[10]=new IntCell(Integer.parseInt(tokens[10]));
			}
		if(tokens.length<=11 || tokens[11].equals("NULL"))
			{
			cells[11]=DataType.getMissingCell();
			}
		else
			{
			cells[11]=new StringCell((tokens[11]));
			}
		if(tokens.length<=12 || tokens[12].equals("NULL"))
			{
			cells[12]=DataType.getMissingCell();
			}
		else
			{
			cells[12]=new StringCell((tokens[12]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.burgeRnaSeqGemMapperAlignLymphNodeAllRawSignal")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/burgeRnaSeqGemMapperAlignLymphNodeAllRawSignal.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[5];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new DoubleCell(Double.parseDouble(tokens[4]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.burgeRnaSeqGemMapperAlignMB435")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/burgeRnaSeqGemMapperAlignMB435.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[13];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new StringCell((tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new IntCell(Integer.parseInt(tokens[7]));
			}
		if(tokens.length<=8 || tokens[8].equals("NULL"))
			{
			cells[8]=DataType.getMissingCell();
			}
		else
			{
			cells[8]=new IntCell(Integer.parseInt(tokens[8]));
			}
		if(tokens.length<=9 || tokens[9].equals("NULL"))
			{
			cells[9]=DataType.getMissingCell();
			}
		else
			{
			cells[9]=new IntCell(Integer.parseInt(tokens[9]));
			}
		if(tokens.length<=10 || tokens[10].equals("NULL"))
			{
			cells[10]=DataType.getMissingCell();
			}
		else
			{
			cells[10]=new IntCell(Integer.parseInt(tokens[10]));
			}
		if(tokens.length<=11 || tokens[11].equals("NULL"))
			{
			cells[11]=DataType.getMissingCell();
			}
		else
			{
			cells[11]=new StringCell((tokens[11]));
			}
		if(tokens.length<=12 || tokens[12].equals("NULL"))
			{
			cells[12]=DataType.getMissingCell();
			}
		else
			{
			cells[12]=new StringCell((tokens[12]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.burgeRnaSeqGemMapperAlignMB435AllRawSignal")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/burgeRnaSeqGemMapperAlignMB435AllRawSignal.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[5];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new DoubleCell(Double.parseDouble(tokens[4]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.burgeRnaSeqGemMapperAlignMCF7")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/burgeRnaSeqGemMapperAlignMCF7.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[13];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new StringCell((tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new IntCell(Integer.parseInt(tokens[7]));
			}
		if(tokens.length<=8 || tokens[8].equals("NULL"))
			{
			cells[8]=DataType.getMissingCell();
			}
		else
			{
			cells[8]=new IntCell(Integer.parseInt(tokens[8]));
			}
		if(tokens.length<=9 || tokens[9].equals("NULL"))
			{
			cells[9]=DataType.getMissingCell();
			}
		else
			{
			cells[9]=new IntCell(Integer.parseInt(tokens[9]));
			}
		if(tokens.length<=10 || tokens[10].equals("NULL"))
			{
			cells[10]=DataType.getMissingCell();
			}
		else
			{
			cells[10]=new IntCell(Integer.parseInt(tokens[10]));
			}
		if(tokens.length<=11 || tokens[11].equals("NULL"))
			{
			cells[11]=DataType.getMissingCell();
			}
		else
			{
			cells[11]=new StringCell((tokens[11]));
			}
		if(tokens.length<=12 || tokens[12].equals("NULL"))
			{
			cells[12]=DataType.getMissingCell();
			}
		else
			{
			cells[12]=new StringCell((tokens[12]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.burgeRnaSeqGemMapperAlignMCF7AllRawSignal")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/burgeRnaSeqGemMapperAlignMCF7AllRawSignal.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[5];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new DoubleCell(Double.parseDouble(tokens[4]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.burgeRnaSeqGemMapperAlignSkelMuscle")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/burgeRnaSeqGemMapperAlignSkelMuscle.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[13];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new StringCell((tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new IntCell(Integer.parseInt(tokens[7]));
			}
		if(tokens.length<=8 || tokens[8].equals("NULL"))
			{
			cells[8]=DataType.getMissingCell();
			}
		else
			{
			cells[8]=new IntCell(Integer.parseInt(tokens[8]));
			}
		if(tokens.length<=9 || tokens[9].equals("NULL"))
			{
			cells[9]=DataType.getMissingCell();
			}
		else
			{
			cells[9]=new IntCell(Integer.parseInt(tokens[9]));
			}
		if(tokens.length<=10 || tokens[10].equals("NULL"))
			{
			cells[10]=DataType.getMissingCell();
			}
		else
			{
			cells[10]=new IntCell(Integer.parseInt(tokens[10]));
			}
		if(tokens.length<=11 || tokens[11].equals("NULL"))
			{
			cells[11]=DataType.getMissingCell();
			}
		else
			{
			cells[11]=new StringCell((tokens[11]));
			}
		if(tokens.length<=12 || tokens[12].equals("NULL"))
			{
			cells[12]=DataType.getMissingCell();
			}
		else
			{
			cells[12]=new StringCell((tokens[12]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.burgeRnaSeqGemMapperAlignSkelMuscleAllRawSignal")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/burgeRnaSeqGemMapperAlignSkelMuscleAllRawSignal.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[5];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new DoubleCell(Double.parseDouble(tokens[4]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.burgeRnaSeqGemMapperAlignT47D")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/burgeRnaSeqGemMapperAlignT47D.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[13];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new StringCell((tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new IntCell(Integer.parseInt(tokens[7]));
			}
		if(tokens.length<=8 || tokens[8].equals("NULL"))
			{
			cells[8]=DataType.getMissingCell();
			}
		else
			{
			cells[8]=new IntCell(Integer.parseInt(tokens[8]));
			}
		if(tokens.length<=9 || tokens[9].equals("NULL"))
			{
			cells[9]=DataType.getMissingCell();
			}
		else
			{
			cells[9]=new IntCell(Integer.parseInt(tokens[9]));
			}
		if(tokens.length<=10 || tokens[10].equals("NULL"))
			{
			cells[10]=DataType.getMissingCell();
			}
		else
			{
			cells[10]=new IntCell(Integer.parseInt(tokens[10]));
			}
		if(tokens.length<=11 || tokens[11].equals("NULL"))
			{
			cells[11]=DataType.getMissingCell();
			}
		else
			{
			cells[11]=new StringCell((tokens[11]));
			}
		if(tokens.length<=12 || tokens[12].equals("NULL"))
			{
			cells[12]=DataType.getMissingCell();
			}
		else
			{
			cells[12]=new StringCell((tokens[12]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.burgeRnaSeqGemMapperAlignT47DAllRawSignal")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/burgeRnaSeqGemMapperAlignT47DAllRawSignal.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[5];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new DoubleCell(Double.parseDouble(tokens[4]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.burgeRnaSeqGemMapperAlignTestes")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/burgeRnaSeqGemMapperAlignTestes.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[13];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new StringCell((tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new IntCell(Integer.parseInt(tokens[7]));
			}
		if(tokens.length<=8 || tokens[8].equals("NULL"))
			{
			cells[8]=DataType.getMissingCell();
			}
		else
			{
			cells[8]=new IntCell(Integer.parseInt(tokens[8]));
			}
		if(tokens.length<=9 || tokens[9].equals("NULL"))
			{
			cells[9]=DataType.getMissingCell();
			}
		else
			{
			cells[9]=new IntCell(Integer.parseInt(tokens[9]));
			}
		if(tokens.length<=10 || tokens[10].equals("NULL"))
			{
			cells[10]=DataType.getMissingCell();
			}
		else
			{
			cells[10]=new IntCell(Integer.parseInt(tokens[10]));
			}
		if(tokens.length<=11 || tokens[11].equals("NULL"))
			{
			cells[11]=DataType.getMissingCell();
			}
		else
			{
			cells[11]=new StringCell((tokens[11]));
			}
		if(tokens.length<=12 || tokens[12].equals("NULL"))
			{
			cells[12]=DataType.getMissingCell();
			}
		else
			{
			cells[12]=new StringCell((tokens[12]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.burgeRnaSeqGemMapperAlignTestesAllRawSignal")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/burgeRnaSeqGemMapperAlignTestesAllRawSignal.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[5];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new DoubleCell(Double.parseDouble(tokens[4]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.ccdsGene")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/ccdsGene.txt.gz";
		}
public int getChromColumn() { return 2;}
public int getChromStartColumn() { return 4;}
public int getChromEndColumn() { return 5;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[16];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new StringCell((tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new StringCell((tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new IntCell(Integer.parseInt(tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new IntCell(Integer.parseInt(tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new IntCell(Integer.parseInt(tokens[7]));
			}
		if(tokens.length<=8 || tokens[8].equals("NULL"))
			{
			cells[8]=DataType.getMissingCell();
			}
		else
			{
			cells[8]=new IntCell(Integer.parseInt(tokens[8]));
			}
		if(tokens.length<=9 || tokens[9].equals("NULL"))
			{
			cells[9]=DataType.getMissingCell();
			}
		else
			{
			cells[9]=new StringCell((tokens[9]));
			}
		if(tokens.length<=10 || tokens[10].equals("NULL"))
			{
			cells[10]=DataType.getMissingCell();
			}
		else
			{
			cells[10]=new StringCell((tokens[10]));
			}
		if(tokens.length<=11 || tokens[11].equals("NULL"))
			{
			cells[11]=DataType.getMissingCell();
			}
		else
			{
			cells[11]=new IntCell(Integer.parseInt(tokens[11]));
			}
		if(tokens.length<=12 || tokens[12].equals("NULL"))
			{
			cells[12]=DataType.getMissingCell();
			}
		else
			{
			cells[12]=new StringCell((tokens[12]));
			}
		if(tokens.length<=13 || tokens[13].equals("NULL"))
			{
			cells[13]=DataType.getMissingCell();
			}
		else
			{
			cells[13]=new StringCell((tokens[13]));
			}
		if(tokens.length<=14 || tokens[14].equals("NULL"))
			{
			cells[14]=DataType.getMissingCell();
			}
		else
			{
			cells[14]=new StringCell((tokens[14]));
			}
		if(tokens.length<=15 || tokens[15].equals("NULL"))
			{
			cells[15]=DataType.getMissingCell();
			}
		else
			{
			cells[15]=new StringCell((tokens[15]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.ccdsKgMap")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/ccdsKgMap.txt.gz";
		}
public int getChromColumn() { return 2;}
public int getChromStartColumn() { return 3;}
public int getChromEndColumn() { return 4;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[6];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new StringCell((tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new StringCell((tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new IntCell(Integer.parseInt(tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new DoubleCell(Double.parseDouble(tokens[5]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.cgapSage")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/cgapSage.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[15];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new StringCell((tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new IntCell(Integer.parseInt(tokens[7]));
			}
		if(tokens.length<=8 || tokens[8].equals("NULL"))
			{
			cells[8]=DataType.getMissingCell();
			}
		else
			{
			cells[8]=new IntCell(Integer.parseInt(tokens[8]));
			}
		if(tokens.length<=9 || tokens[9].equals("NULL"))
			{
			cells[9]=DataType.getMissingCell();
			}
		else
			{
			cells[9]=new IntCell(Integer.parseInt(tokens[9]));
			}
		if(tokens.length<=10 || tokens[10].equals("NULL"))
			{
			cells[10]=DataType.getMissingCell();
			}
		else
			{
			cells[10]=new StringCell((tokens[10]));
			}
		if(tokens.length<=11 || tokens[11].equals("NULL"))
			{
			cells[11]=DataType.getMissingCell();
			}
		else
			{
			cells[11]=new StringCell((tokens[11]));
			}
		if(tokens.length<=12 || tokens[12].equals("NULL"))
			{
			cells[12]=DataType.getMissingCell();
			}
		else
			{
			cells[12]=new StringCell((tokens[12]));
			}
		if(tokens.length<=13 || tokens[13].equals("NULL"))
			{
			cells[13]=DataType.getMissingCell();
			}
		else
			{
			cells[13]=new IntCell(Integer.parseInt(tokens[13]));
			}
		if(tokens.length<=14 || tokens[14].equals("NULL"))
			{
			cells[14]=DataType.getMissingCell();
			}
		else
			{
			cells[14]=new StringCell((tokens[14]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.consIndelsHgMmCanFam")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/consIndelsHgMmCanFam.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[6];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.cpgIslandExt")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/cpgIslandExt.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[11];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new IntCell(Integer.parseInt(tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new IntCell(Integer.parseInt(tokens[7]));
			}
		if(tokens.length<=8 || tokens[8].equals("NULL"))
			{
			cells[8]=DataType.getMissingCell();
			}
		else
			{
			cells[8]=new DoubleCell(Double.parseDouble(tokens[8]));
			}
		if(tokens.length<=9 || tokens[9].equals("NULL"))
			{
			cells[9]=DataType.getMissingCell();
			}
		else
			{
			cells[9]=new DoubleCell(Double.parseDouble(tokens[9]));
			}
		if(tokens.length<=10 || tokens[10].equals("NULL"))
			{
			cells[10]=DataType.getMissingCell();
			}
		else
			{
			cells[10]=new DoubleCell(Double.parseDouble(tokens[10]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.ctgPos")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/ctgPos.txt.gz";
		}
public int getChromColumn() { return 2;}
public int getChromStartColumn() { return 3;}
public int getChromEndColumn() { return 4;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[5];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new StringCell((tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new IntCell(Integer.parseInt(tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new StringCell((tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new IntCell(Integer.parseInt(tokens[4]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.ctgPos2")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/ctgPos2.txt.gz";
		}
public int getChromColumn() { return 2;}
public int getChromStartColumn() { return 3;}
public int getChromEndColumn() { return 4;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[6];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new StringCell((tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new IntCell(Integer.parseInt(tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new StringCell((tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new IntCell(Integer.parseInt(tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new StringCell((tokens[5]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.cytoBand")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/cytoBand.txt.gz";
		}
public int getChromColumn() { return 0;}
public int getChromStartColumn() { return 1;}
public int getChromEndColumn() { return 2;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[5];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new StringCell((tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new IntCell(Integer.parseInt(tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new StringCell((tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.cytoBandIdeo")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/cytoBandIdeo.txt.gz";
		}
public int getChromColumn() { return 0;}
public int getChromStartColumn() { return 1;}
public int getChromEndColumn() { return 2;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[5];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new StringCell((tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new IntCell(Integer.parseInt(tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new StringCell((tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.darned")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/darned.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[10];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new StringCell((tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new IntCell(Integer.parseInt(tokens[7]));
			}
		if(tokens.length<=8 || tokens[8].equals("NULL"))
			{
			cells[8]=DataType.getMissingCell();
			}
		else
			{
			cells[8]=new IntCell(Integer.parseInt(tokens[8]));
			}
		if(tokens.length<=9 || tokens[9].equals("NULL"))
			{
			cells[9]=DataType.getMissingCell();
			}
		else
			{
			cells[9]=new IntCell(Integer.parseInt(tokens[9]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.dgv")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/dgv.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[16];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new StringCell((tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new IntCell(Integer.parseInt(tokens[7]));
			}
		if(tokens.length<=8 || tokens[8].equals("NULL"))
			{
			cells[8]=DataType.getMissingCell();
			}
		else
			{
			cells[8]=new IntCell(Integer.parseInt(tokens[8]));
			}
		if(tokens.length<=9 || tokens[9].equals("NULL"))
			{
			cells[9]=DataType.getMissingCell();
			}
		else
			{
			cells[9]=new IntCell(Integer.parseInt(tokens[9]));
			}
		if(tokens.length<=10 || tokens[10].equals("NULL"))
			{
			cells[10]=DataType.getMissingCell();
			}
		else
			{
			cells[10]=new StringCell((tokens[10]));
			}
		if(tokens.length<=11 || tokens[11].equals("NULL"))
			{
			cells[11]=DataType.getMissingCell();
			}
		else
			{
			cells[11]=new StringCell((tokens[11]));
			}
		if(tokens.length<=12 || tokens[12].equals("NULL"))
			{
			cells[12]=DataType.getMissingCell();
			}
		else
			{
			cells[12]=new StringCell((tokens[12]));
			}
		if(tokens.length<=13 || tokens[13].equals("NULL"))
			{
			cells[13]=DataType.getMissingCell();
			}
		else
			{
			cells[13]=new IntCell(Integer.parseInt(tokens[13]));
			}
		if(tokens.length<=14 || tokens[14].equals("NULL"))
			{
			cells[14]=DataType.getMissingCell();
			}
		else
			{
			cells[14]=new StringCell((tokens[14]));
			}
		if(tokens.length<=15 || tokens[15].equals("NULL"))
			{
			cells[15]=DataType.getMissingCell();
			}
		else
			{
			cells[15]=new StringCell((tokens[15]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.eioJcviNASNeg")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/eioJcviNASNeg.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[4];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.eioJcviNASPos")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/eioJcviNASPos.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[4];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.ensGene")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/ensGene.txt.gz";
		}
public int getChromColumn() { return 2;}
public int getChromStartColumn() { return 4;}
public int getChromEndColumn() { return 5;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[16];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new StringCell((tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new StringCell((tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new IntCell(Integer.parseInt(tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new IntCell(Integer.parseInt(tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new IntCell(Integer.parseInt(tokens[7]));
			}
		if(tokens.length<=8 || tokens[8].equals("NULL"))
			{
			cells[8]=DataType.getMissingCell();
			}
		else
			{
			cells[8]=new IntCell(Integer.parseInt(tokens[8]));
			}
		if(tokens.length<=9 || tokens[9].equals("NULL"))
			{
			cells[9]=DataType.getMissingCell();
			}
		else
			{
			cells[9]=new StringCell((tokens[9]));
			}
		if(tokens.length<=10 || tokens[10].equals("NULL"))
			{
			cells[10]=DataType.getMissingCell();
			}
		else
			{
			cells[10]=new StringCell((tokens[10]));
			}
		if(tokens.length<=11 || tokens[11].equals("NULL"))
			{
			cells[11]=DataType.getMissingCell();
			}
		else
			{
			cells[11]=new IntCell(Integer.parseInt(tokens[11]));
			}
		if(tokens.length<=12 || tokens[12].equals("NULL"))
			{
			cells[12]=DataType.getMissingCell();
			}
		else
			{
			cells[12]=new StringCell((tokens[12]));
			}
		if(tokens.length<=13 || tokens[13].equals("NULL"))
			{
			cells[13]=DataType.getMissingCell();
			}
		else
			{
			cells[13]=new StringCell((tokens[13]));
			}
		if(tokens.length<=14 || tokens[14].equals("NULL"))
			{
			cells[14]=DataType.getMissingCell();
			}
		else
			{
			cells[14]=new StringCell((tokens[14]));
			}
		if(tokens.length<=15 || tokens[15].equals("NULL"))
			{
			cells[15]=DataType.getMissingCell();
			}
		else
			{
			cells[15]=new StringCell((tokens[15]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.estOrientInfo")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/estOrientInfo.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[10];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new IntCell(Integer.parseInt(tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new IntCell(Integer.parseInt(tokens[7]));
			}
		if(tokens.length<=8 || tokens[8].equals("NULL"))
			{
			cells[8]=DataType.getMissingCell();
			}
		else
			{
			cells[8]=new IntCell(Integer.parseInt(tokens[8]));
			}
		if(tokens.length<=9 || tokens[9].equals("NULL"))
			{
			cells[9]=DataType.getMissingCell();
			}
		else
			{
			cells[9]=new IntCell(Integer.parseInt(tokens[9]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.evofold")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/evofold.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[10];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new StringCell((tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new IntCell(Integer.parseInt(tokens[7]));
			}
		if(tokens.length<=8 || tokens[8].equals("NULL"))
			{
			cells[8]=DataType.getMissingCell();
			}
		else
			{
			cells[8]=new StringCell((tokens[8]));
			}
		if(tokens.length<=9 || tokens[9].equals("NULL"))
			{
			cells[9]=DataType.getMissingCell();
			}
		else
			{
			cells[9]=new StringCell((tokens[9]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.exoniphy")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/exoniphy.txt.gz";
		}
public int getChromColumn() { return 2;}
public int getChromStartColumn() { return 4;}
public int getChromEndColumn() { return 5;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[16];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new StringCell((tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new StringCell((tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new IntCell(Integer.parseInt(tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new IntCell(Integer.parseInt(tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new IntCell(Integer.parseInt(tokens[7]));
			}
		if(tokens.length<=8 || tokens[8].equals("NULL"))
			{
			cells[8]=DataType.getMissingCell();
			}
		else
			{
			cells[8]=new IntCell(Integer.parseInt(tokens[8]));
			}
		if(tokens.length<=9 || tokens[9].equals("NULL"))
			{
			cells[9]=DataType.getMissingCell();
			}
		else
			{
			cells[9]=new StringCell((tokens[9]));
			}
		if(tokens.length<=10 || tokens[10].equals("NULL"))
			{
			cells[10]=DataType.getMissingCell();
			}
		else
			{
			cells[10]=new StringCell((tokens[10]));
			}
		if(tokens.length<=11 || tokens[11].equals("NULL"))
			{
			cells[11]=DataType.getMissingCell();
			}
		else
			{
			cells[11]=new IntCell(Integer.parseInt(tokens[11]));
			}
		if(tokens.length<=12 || tokens[12].equals("NULL"))
			{
			cells[12]=DataType.getMissingCell();
			}
		else
			{
			cells[12]=new StringCell((tokens[12]));
			}
		if(tokens.length<=13 || tokens[13].equals("NULL"))
			{
			cells[13]=DataType.getMissingCell();
			}
		else
			{
			cells[13]=new StringCell((tokens[13]));
			}
		if(tokens.length<=14 || tokens[14].equals("NULL"))
			{
			cells[14]=DataType.getMissingCell();
			}
		else
			{
			cells[14]=new StringCell((tokens[14]));
			}
		if(tokens.length<=15 || tokens[15].equals("NULL"))
			{
			cells[15]=DataType.getMissingCell();
			}
		else
			{
			cells[15]=new StringCell((tokens[15]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.fishClones")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/fishClones.txt.gz";
		}
public int getChromColumn() { return 0;}
public int getChromStartColumn() { return 1;}
public int getChromEndColumn() { return 2;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[16];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new StringCell((tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new IntCell(Integer.parseInt(tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new StringCell((tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new IntCell(Integer.parseInt(tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new StringCell((tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new StringCell((tokens[7]));
			}
		if(tokens.length<=8 || tokens[8].equals("NULL"))
			{
			cells[8]=DataType.getMissingCell();
			}
		else
			{
			cells[8]=new StringCell((tokens[8]));
			}
		if(tokens.length<=9 || tokens[9].equals("NULL"))
			{
			cells[9]=DataType.getMissingCell();
			}
		else
			{
			cells[9]=new StringCell((tokens[9]));
			}
		if(tokens.length<=10 || tokens[10].equals("NULL"))
			{
			cells[10]=DataType.getMissingCell();
			}
		else
			{
			cells[10]=new IntCell(Integer.parseInt(tokens[10]));
			}
		if(tokens.length<=11 || tokens[11].equals("NULL"))
			{
			cells[11]=DataType.getMissingCell();
			}
		else
			{
			cells[11]=new StringCell((tokens[11]));
			}
		if(tokens.length<=12 || tokens[12].equals("NULL"))
			{
			cells[12]=DataType.getMissingCell();
			}
		else
			{
			cells[12]=new IntCell(Integer.parseInt(tokens[12]));
			}
		if(tokens.length<=13 || tokens[13].equals("NULL"))
			{
			cells[13]=DataType.getMissingCell();
			}
		else
			{
			cells[13]=new StringCell((tokens[13]));
			}
		if(tokens.length<=14 || tokens[14].equals("NULL"))
			{
			cells[14]=DataType.getMissingCell();
			}
		else
			{
			cells[14]=new IntCell(Integer.parseInt(tokens[14]));
			}
		if(tokens.length<=15 || tokens[15].equals("NULL"))
			{
			cells[15]=DataType.getMissingCell();
			}
		else
			{
			cells[15]=new StringCell((tokens[15]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.fosEndPairs")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/fosEndPairs.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[12];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new StringCell((tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new StringCell((tokens[7]));
			}
		if(tokens.length<=8 || tokens[8].equals("NULL"))
			{
			cells[8]=DataType.getMissingCell();
			}
		else
			{
			cells[8]=new IntCell(Integer.parseInt(tokens[8]));
			}
		if(tokens.length<=9 || tokens[9].equals("NULL"))
			{
			cells[9]=DataType.getMissingCell();
			}
		else
			{
			cells[9]=new StringCell((tokens[9]));
			}
		if(tokens.length<=10 || tokens[10].equals("NULL"))
			{
			cells[10]=DataType.getMissingCell();
			}
		else
			{
			cells[10]=new StringCell((tokens[10]));
			}
		if(tokens.length<=11 || tokens[11].equals("NULL"))
			{
			cells[11]=DataType.getMissingCell();
			}
		else
			{
			cells[11]=new StringCell((tokens[11]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.gad")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/gad.txt.gz";
		}
public int getChromColumn() { return 0;}
public int getChromStartColumn() { return 1;}
public int getChromEndColumn() { return 2;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[4];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new StringCell((tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new IntCell(Integer.parseInt(tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new StringCell((tokens[3]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.gap")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/gap.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[9];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new IntCell(Integer.parseInt(tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new StringCell((tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new IntCell(Integer.parseInt(tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new StringCell((tokens[7]));
			}
		if(tokens.length<=8 || tokens[8].equals("NULL"))
			{
			cells[8]=DataType.getMissingCell();
			}
		else
			{
			cells[8]=new StringCell((tokens[8]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.gc5Base")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/gc5Base.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[14];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new IntCell(Integer.parseInt(tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new IntCell(Integer.parseInt(tokens[7]));
			}
		if(tokens.length<=8 || tokens[8].equals("NULL"))
			{
			cells[8]=DataType.getMissingCell();
			}
		else
			{
			cells[8]=new StringCell((tokens[8]));
			}
		if(tokens.length<=9 || tokens[9].equals("NULL"))
			{
			cells[9]=DataType.getMissingCell();
			}
		else
			{
			cells[9]=new DoubleCell(Double.parseDouble(tokens[9]));
			}
		if(tokens.length<=10 || tokens[10].equals("NULL"))
			{
			cells[10]=DataType.getMissingCell();
			}
		else
			{
			cells[10]=new DoubleCell(Double.parseDouble(tokens[10]));
			}
		if(tokens.length<=11 || tokens[11].equals("NULL"))
			{
			cells[11]=DataType.getMissingCell();
			}
		else
			{
			cells[11]=new IntCell(Integer.parseInt(tokens[11]));
			}
		if(tokens.length<=12 || tokens[12].equals("NULL"))
			{
			cells[12]=DataType.getMissingCell();
			}
		else
			{
			cells[12]=new DoubleCell(Double.parseDouble(tokens[12]));
			}
		if(tokens.length<=13 || tokens[13].equals("NULL"))
			{
			cells[13]=DataType.getMissingCell();
			}
		else
			{
			cells[13]=new DoubleCell(Double.parseDouble(tokens[13]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.geneid")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/geneid.txt.gz";
		}
public int getChromColumn() { return 2;}
public int getChromStartColumn() { return 4;}
public int getChromEndColumn() { return 5;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[16];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new StringCell((tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new StringCell((tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new IntCell(Integer.parseInt(tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new IntCell(Integer.parseInt(tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new IntCell(Integer.parseInt(tokens[7]));
			}
		if(tokens.length<=8 || tokens[8].equals("NULL"))
			{
			cells[8]=DataType.getMissingCell();
			}
		else
			{
			cells[8]=new IntCell(Integer.parseInt(tokens[8]));
			}
		if(tokens.length<=9 || tokens[9].equals("NULL"))
			{
			cells[9]=DataType.getMissingCell();
			}
		else
			{
			cells[9]=new StringCell((tokens[9]));
			}
		if(tokens.length<=10 || tokens[10].equals("NULL"))
			{
			cells[10]=DataType.getMissingCell();
			}
		else
			{
			cells[10]=new StringCell((tokens[10]));
			}
		if(tokens.length<=11 || tokens[11].equals("NULL"))
			{
			cells[11]=DataType.getMissingCell();
			}
		else
			{
			cells[11]=new IntCell(Integer.parseInt(tokens[11]));
			}
		if(tokens.length<=12 || tokens[12].equals("NULL"))
			{
			cells[12]=DataType.getMissingCell();
			}
		else
			{
			cells[12]=new StringCell((tokens[12]));
			}
		if(tokens.length<=13 || tokens[13].equals("NULL"))
			{
			cells[13]=DataType.getMissingCell();
			}
		else
			{
			cells[13]=new StringCell((tokens[13]));
			}
		if(tokens.length<=14 || tokens[14].equals("NULL"))
			{
			cells[14]=DataType.getMissingCell();
			}
		else
			{
			cells[14]=new StringCell((tokens[14]));
			}
		if(tokens.length<=15 || tokens[15].equals("NULL"))
			{
			cells[15]=DataType.getMissingCell();
			}
		else
			{
			cells[15]=new StringCell((tokens[15]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.genomicSuperDups")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/genomicSuperDups.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[30];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new StringCell((tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new StringCell((tokens[7]));
			}
		if(tokens.length<=8 || tokens[8].equals("NULL"))
			{
			cells[8]=DataType.getMissingCell();
			}
		else
			{
			cells[8]=new IntCell(Integer.parseInt(tokens[8]));
			}
		if(tokens.length<=9 || tokens[9].equals("NULL"))
			{
			cells[9]=DataType.getMissingCell();
			}
		else
			{
			cells[9]=new IntCell(Integer.parseInt(tokens[9]));
			}
		if(tokens.length<=10 || tokens[10].equals("NULL"))
			{
			cells[10]=DataType.getMissingCell();
			}
		else
			{
			cells[10]=new IntCell(Integer.parseInt(tokens[10]));
			}
		if(tokens.length<=11 || tokens[11].equals("NULL"))
			{
			cells[11]=DataType.getMissingCell();
			}
		else
			{
			cells[11]=new IntCell(Integer.parseInt(tokens[11]));
			}
		if(tokens.length<=12 || tokens[12].equals("NULL"))
			{
			cells[12]=DataType.getMissingCell();
			}
		else
			{
			cells[12]=new IntCell(Integer.parseInt(tokens[12]));
			}
		if(tokens.length<=13 || tokens[13].equals("NULL"))
			{
			cells[13]=DataType.getMissingCell();
			}
		else
			{
			cells[13]=new StringCell((tokens[13]));
			}
		if(tokens.length<=14 || tokens[14].equals("NULL"))
			{
			cells[14]=DataType.getMissingCell();
			}
		else
			{
			cells[14]=new StringCell((tokens[14]));
			}
		if(tokens.length<=15 || tokens[15].equals("NULL"))
			{
			cells[15]=DataType.getMissingCell();
			}
		else
			{
			cells[15]=new StringCell((tokens[15]));
			}
		if(tokens.length<=16 || tokens[16].equals("NULL"))
			{
			cells[16]=DataType.getMissingCell();
			}
		else
			{
			cells[16]=new StringCell((tokens[16]));
			}
		if(tokens.length<=17 || tokens[17].equals("NULL"))
			{
			cells[17]=DataType.getMissingCell();
			}
		else
			{
			cells[17]=new StringCell((tokens[17]));
			}
		if(tokens.length<=18 || tokens[18].equals("NULL"))
			{
			cells[18]=DataType.getMissingCell();
			}
		else
			{
			cells[18]=new IntCell(Integer.parseInt(tokens[18]));
			}
		if(tokens.length<=19 || tokens[19].equals("NULL"))
			{
			cells[19]=DataType.getMissingCell();
			}
		else
			{
			cells[19]=new IntCell(Integer.parseInt(tokens[19]));
			}
		if(tokens.length<=20 || tokens[20].equals("NULL"))
			{
			cells[20]=DataType.getMissingCell();
			}
		else
			{
			cells[20]=new IntCell(Integer.parseInt(tokens[20]));
			}
		if(tokens.length<=21 || tokens[21].equals("NULL"))
			{
			cells[21]=DataType.getMissingCell();
			}
		else
			{
			cells[21]=new IntCell(Integer.parseInt(tokens[21]));
			}
		if(tokens.length<=22 || tokens[22].equals("NULL"))
			{
			cells[22]=DataType.getMissingCell();
			}
		else
			{
			cells[22]=new IntCell(Integer.parseInt(tokens[22]));
			}
		if(tokens.length<=23 || tokens[23].equals("NULL"))
			{
			cells[23]=DataType.getMissingCell();
			}
		else
			{
			cells[23]=new IntCell(Integer.parseInt(tokens[23]));
			}
		if(tokens.length<=24 || tokens[24].equals("NULL"))
			{
			cells[24]=DataType.getMissingCell();
			}
		else
			{
			cells[24]=new IntCell(Integer.parseInt(tokens[24]));
			}
		if(tokens.length<=25 || tokens[25].equals("NULL"))
			{
			cells[25]=DataType.getMissingCell();
			}
		else
			{
			cells[25]=new IntCell(Integer.parseInt(tokens[25]));
			}
		if(tokens.length<=26 || tokens[26].equals("NULL"))
			{
			cells[26]=DataType.getMissingCell();
			}
		else
			{
			cells[26]=new DoubleCell(Double.parseDouble(tokens[26]));
			}
		if(tokens.length<=27 || tokens[27].equals("NULL"))
			{
			cells[27]=DataType.getMissingCell();
			}
		else
			{
			cells[27]=new DoubleCell(Double.parseDouble(tokens[27]));
			}
		if(tokens.length<=28 || tokens[28].equals("NULL"))
			{
			cells[28]=DataType.getMissingCell();
			}
		else
			{
			cells[28]=new DoubleCell(Double.parseDouble(tokens[28]));
			}
		if(tokens.length<=29 || tokens[29].equals("NULL"))
			{
			cells[29]=DataType.getMissingCell();
			}
		else
			{
			cells[29]=new DoubleCell(Double.parseDouble(tokens[29]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.genscan")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/genscan.txt.gz";
		}
public int getChromColumn() { return 2;}
public int getChromStartColumn() { return 4;}
public int getChromEndColumn() { return 5;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[11];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new StringCell((tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new StringCell((tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new IntCell(Integer.parseInt(tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new IntCell(Integer.parseInt(tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new IntCell(Integer.parseInt(tokens[7]));
			}
		if(tokens.length<=8 || tokens[8].equals("NULL"))
			{
			cells[8]=DataType.getMissingCell();
			}
		else
			{
			cells[8]=new IntCell(Integer.parseInt(tokens[8]));
			}
		if(tokens.length<=9 || tokens[9].equals("NULL"))
			{
			cells[9]=DataType.getMissingCell();
			}
		else
			{
			cells[9]=new StringCell((tokens[9]));
			}
		if(tokens.length<=10 || tokens[10].equals("NULL"))
			{
			cells[10]=DataType.getMissingCell();
			}
		else
			{
			cells[10]=new StringCell((tokens[10]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.gnfAtlas2")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/gnfAtlas2.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[16];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new StringCell((tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new IntCell(Integer.parseInt(tokens[7]));
			}
		if(tokens.length<=8 || tokens[8].equals("NULL"))
			{
			cells[8]=DataType.getMissingCell();
			}
		else
			{
			cells[8]=new IntCell(Integer.parseInt(tokens[8]));
			}
		if(tokens.length<=9 || tokens[9].equals("NULL"))
			{
			cells[9]=DataType.getMissingCell();
			}
		else
			{
			cells[9]=new IntCell(Integer.parseInt(tokens[9]));
			}
		if(tokens.length<=10 || tokens[10].equals("NULL"))
			{
			cells[10]=DataType.getMissingCell();
			}
		else
			{
			cells[10]=new IntCell(Integer.parseInt(tokens[10]));
			}
		if(tokens.length<=11 || tokens[11].equals("NULL"))
			{
			cells[11]=DataType.getMissingCell();
			}
		else
			{
			cells[11]=new StringCell((tokens[11]));
			}
		if(tokens.length<=12 || tokens[12].equals("NULL"))
			{
			cells[12]=DataType.getMissingCell();
			}
		else
			{
			cells[12]=new StringCell((tokens[12]));
			}
		if(tokens.length<=13 || tokens[13].equals("NULL"))
			{
			cells[13]=DataType.getMissingCell();
			}
		else
			{
			cells[13]=new IntCell(Integer.parseInt(tokens[13]));
			}
		if(tokens.length<=14 || tokens[14].equals("NULL"))
			{
			cells[14]=DataType.getMissingCell();
			}
		else
			{
			cells[14]=new StringCell((tokens[14]));
			}
		if(tokens.length<=15 || tokens[15].equals("NULL"))
			{
			cells[15]=DataType.getMissingCell();
			}
		else
			{
			cells[15]=new StringCell((tokens[15]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.gold")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/gold.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[10];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new IntCell(Integer.parseInt(tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new StringCell((tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new StringCell((tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new IntCell(Integer.parseInt(tokens[7]));
			}
		if(tokens.length<=8 || tokens[8].equals("NULL"))
			{
			cells[8]=DataType.getMissingCell();
			}
		else
			{
			cells[8]=new IntCell(Integer.parseInt(tokens[8]));
			}
		if(tokens.length<=9 || tokens[9].equals("NULL"))
			{
			cells[9]=DataType.getMissingCell();
			}
		else
			{
			cells[9]=new StringCell((tokens[9]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.gwasCatalog")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/gwasCatalog.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[23];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new StringCell((tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new StringCell((tokens[7]));
			}
		if(tokens.length<=8 || tokens[8].equals("NULL"))
			{
			cells[8]=DataType.getMissingCell();
			}
		else
			{
			cells[8]=new StringCell((tokens[8]));
			}
		if(tokens.length<=9 || tokens[9].equals("NULL"))
			{
			cells[9]=DataType.getMissingCell();
			}
		else
			{
			cells[9]=new StringCell((tokens[9]));
			}
		if(tokens.length<=10 || tokens[10].equals("NULL"))
			{
			cells[10]=DataType.getMissingCell();
			}
		else
			{
			cells[10]=new StringCell((tokens[10]));
			}
		if(tokens.length<=11 || tokens[11].equals("NULL"))
			{
			cells[11]=DataType.getMissingCell();
			}
		else
			{
			cells[11]=new StringCell((tokens[11]));
			}
		if(tokens.length<=12 || tokens[12].equals("NULL"))
			{
			cells[12]=DataType.getMissingCell();
			}
		else
			{
			cells[12]=new StringCell((tokens[12]));
			}
		if(tokens.length<=13 || tokens[13].equals("NULL"))
			{
			cells[13]=DataType.getMissingCell();
			}
		else
			{
			cells[13]=new StringCell((tokens[13]));
			}
		if(tokens.length<=14 || tokens[14].equals("NULL"))
			{
			cells[14]=DataType.getMissingCell();
			}
		else
			{
			cells[14]=new StringCell((tokens[14]));
			}
		if(tokens.length<=15 || tokens[15].equals("NULL"))
			{
			cells[15]=DataType.getMissingCell();
			}
		else
			{
			cells[15]=new StringCell((tokens[15]));
			}
		if(tokens.length<=16 || tokens[16].equals("NULL"))
			{
			cells[16]=DataType.getMissingCell();
			}
		else
			{
			cells[16]=new StringCell((tokens[16]));
			}
		if(tokens.length<=17 || tokens[17].equals("NULL"))
			{
			cells[17]=DataType.getMissingCell();
			}
		else
			{
			cells[17]=new StringCell((tokens[17]));
			}
		if(tokens.length<=18 || tokens[18].equals("NULL"))
			{
			cells[18]=DataType.getMissingCell();
			}
		else
			{
			cells[18]=new StringCell((tokens[18]));
			}
		if(tokens.length<=19 || tokens[19].equals("NULL"))
			{
			cells[19]=DataType.getMissingCell();
			}
		else
			{
			cells[19]=new StringCell((tokens[19]));
			}
		if(tokens.length<=20 || tokens[20].equals("NULL"))
			{
			cells[20]=DataType.getMissingCell();
			}
		else
			{
			cells[20]=new StringCell((tokens[20]));
			}
		if(tokens.length<=21 || tokens[21].equals("NULL"))
			{
			cells[21]=DataType.getMissingCell();
			}
		else
			{
			cells[21]=new StringCell((tokens[21]));
			}
		if(tokens.length<=22 || tokens[22].equals("NULL"))
			{
			cells[22]=DataType.getMissingCell();
			}
		else
			{
			cells[22]=new StringCell((tokens[22]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.hapmapAllelesChimp")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/hapmapAllelesChimp.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[14];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new StringCell((tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new StringCell((tokens[7]));
			}
		if(tokens.length<=8 || tokens[8].equals("NULL"))
			{
			cells[8]=DataType.getMissingCell();
			}
		else
			{
			cells[8]=new StringCell((tokens[8]));
			}
		if(tokens.length<=9 || tokens[9].equals("NULL"))
			{
			cells[9]=DataType.getMissingCell();
			}
		else
			{
			cells[9]=new StringCell((tokens[9]));
			}
		if(tokens.length<=10 || tokens[10].equals("NULL"))
			{
			cells[10]=DataType.getMissingCell();
			}
		else
			{
			cells[10]=new IntCell(Integer.parseInt(tokens[10]));
			}
		if(tokens.length<=11 || tokens[11].equals("NULL"))
			{
			cells[11]=DataType.getMissingCell();
			}
		else
			{
			cells[11]=new IntCell(Integer.parseInt(tokens[11]));
			}
		if(tokens.length<=12 || tokens[12].equals("NULL"))
			{
			cells[12]=DataType.getMissingCell();
			}
		else
			{
			cells[12]=new StringCell((tokens[12]));
			}
		if(tokens.length<=13 || tokens[13].equals("NULL"))
			{
			cells[13]=DataType.getMissingCell();
			}
		else
			{
			cells[13]=new StringCell((tokens[13]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.hapmapAllelesMacaque")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/hapmapAllelesMacaque.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[14];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new StringCell((tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new StringCell((tokens[7]));
			}
		if(tokens.length<=8 || tokens[8].equals("NULL"))
			{
			cells[8]=DataType.getMissingCell();
			}
		else
			{
			cells[8]=new StringCell((tokens[8]));
			}
		if(tokens.length<=9 || tokens[9].equals("NULL"))
			{
			cells[9]=DataType.getMissingCell();
			}
		else
			{
			cells[9]=new StringCell((tokens[9]));
			}
		if(tokens.length<=10 || tokens[10].equals("NULL"))
			{
			cells[10]=DataType.getMissingCell();
			}
		else
			{
			cells[10]=new IntCell(Integer.parseInt(tokens[10]));
			}
		if(tokens.length<=11 || tokens[11].equals("NULL"))
			{
			cells[11]=DataType.getMissingCell();
			}
		else
			{
			cells[11]=new IntCell(Integer.parseInt(tokens[11]));
			}
		if(tokens.length<=12 || tokens[12].equals("NULL"))
			{
			cells[12]=DataType.getMissingCell();
			}
		else
			{
			cells[12]=new StringCell((tokens[12]));
			}
		if(tokens.length<=13 || tokens[13].equals("NULL"))
			{
			cells[13]=DataType.getMissingCell();
			}
		else
			{
			cells[13]=new StringCell((tokens[13]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.hapmapAllelesSummary")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/hapmapAllelesSummary.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[28];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new StringCell((tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new StringCell((tokens[7]));
			}
		if(tokens.length<=8 || tokens[8].equals("NULL"))
			{
			cells[8]=DataType.getMissingCell();
			}
		else
			{
			cells[8]=new StringCell((tokens[8]));
			}
		if(tokens.length<=9 || tokens[9].equals("NULL"))
			{
			cells[9]=DataType.getMissingCell();
			}
		else
			{
			cells[9]=new StringCell((tokens[9]));
			}
		if(tokens.length<=10 || tokens[10].equals("NULL"))
			{
			cells[10]=DataType.getMissingCell();
			}
		else
			{
			cells[10]=new IntCell(Integer.parseInt(tokens[10]));
			}
		if(tokens.length<=11 || tokens[11].equals("NULL"))
			{
			cells[11]=DataType.getMissingCell();
			}
		else
			{
			cells[11]=new StringCell((tokens[11]));
			}
		if(tokens.length<=12 || tokens[12].equals("NULL"))
			{
			cells[12]=DataType.getMissingCell();
			}
		else
			{
			cells[12]=new StringCell((tokens[12]));
			}
		if(tokens.length<=13 || tokens[13].equals("NULL"))
			{
			cells[13]=DataType.getMissingCell();
			}
		else
			{
			cells[13]=new IntCell(Integer.parseInt(tokens[13]));
			}
		if(tokens.length<=14 || tokens[14].equals("NULL"))
			{
			cells[14]=DataType.getMissingCell();
			}
		else
			{
			cells[14]=new IntCell(Integer.parseInt(tokens[14]));
			}
		if(tokens.length<=15 || tokens[15].equals("NULL"))
			{
			cells[15]=DataType.getMissingCell();
			}
		else
			{
			cells[15]=new StringCell((tokens[15]));
			}
		if(tokens.length<=16 || tokens[16].equals("NULL"))
			{
			cells[16]=DataType.getMissingCell();
			}
		else
			{
			cells[16]=new IntCell(Integer.parseInt(tokens[16]));
			}
		if(tokens.length<=17 || tokens[17].equals("NULL"))
			{
			cells[17]=DataType.getMissingCell();
			}
		else
			{
			cells[17]=new IntCell(Integer.parseInt(tokens[17]));
			}
		if(tokens.length<=18 || tokens[18].equals("NULL"))
			{
			cells[18]=DataType.getMissingCell();
			}
		else
			{
			cells[18]=new StringCell((tokens[18]));
			}
		if(tokens.length<=19 || tokens[19].equals("NULL"))
			{
			cells[19]=DataType.getMissingCell();
			}
		else
			{
			cells[19]=new IntCell(Integer.parseInt(tokens[19]));
			}
		if(tokens.length<=20 || tokens[20].equals("NULL"))
			{
			cells[20]=DataType.getMissingCell();
			}
		else
			{
			cells[20]=new IntCell(Integer.parseInt(tokens[20]));
			}
		if(tokens.length<=21 || tokens[21].equals("NULL"))
			{
			cells[21]=DataType.getMissingCell();
			}
		else
			{
			cells[21]=new StringCell((tokens[21]));
			}
		if(tokens.length<=22 || tokens[22].equals("NULL"))
			{
			cells[22]=DataType.getMissingCell();
			}
		else
			{
			cells[22]=new IntCell(Integer.parseInt(tokens[22]));
			}
		if(tokens.length<=23 || tokens[23].equals("NULL"))
			{
			cells[23]=DataType.getMissingCell();
			}
		else
			{
			cells[23]=new IntCell(Integer.parseInt(tokens[23]));
			}
		if(tokens.length<=24 || tokens[24].equals("NULL"))
			{
			cells[24]=DataType.getMissingCell();
			}
		else
			{
			cells[24]=new StringCell((tokens[24]));
			}
		if(tokens.length<=25 || tokens[25].equals("NULL"))
			{
			cells[25]=DataType.getMissingCell();
			}
		else
			{
			cells[25]=new IntCell(Integer.parseInt(tokens[25]));
			}
		if(tokens.length<=26 || tokens[26].equals("NULL"))
			{
			cells[26]=DataType.getMissingCell();
			}
		else
			{
			cells[26]=new StringCell((tokens[26]));
			}
		if(tokens.length<=27 || tokens[27].equals("NULL"))
			{
			cells[27]=DataType.getMissingCell();
			}
		else
			{
			cells[27]=new IntCell(Integer.parseInt(tokens[27]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.hapmapPhaseIIISummary")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/hapmapPhaseIIISummary.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[19];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new StringCell((tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new StringCell((tokens[7]));
			}
		if(tokens.length<=8 || tokens[8].equals("NULL"))
			{
			cells[8]=DataType.getMissingCell();
			}
		else
			{
			cells[8]=new StringCell((tokens[8]));
			}
		if(tokens.length<=9 || tokens[9].equals("NULL"))
			{
			cells[9]=DataType.getMissingCell();
			}
		else
			{
			cells[9]=new IntCell(Integer.parseInt(tokens[9]));
			}
		if(tokens.length<=10 || tokens[10].equals("NULL"))
			{
			cells[10]=DataType.getMissingCell();
			}
		else
			{
			cells[10]=new IntCell(Integer.parseInt(tokens[10]));
			}
		if(tokens.length<=11 || tokens[11].equals("NULL"))
			{
			cells[11]=DataType.getMissingCell();
			}
		else
			{
			cells[11]=new IntCell(Integer.parseInt(tokens[11]));
			}
		if(tokens.length<=12 || tokens[12].equals("NULL"))
			{
			cells[12]=DataType.getMissingCell();
			}
		else
			{
			cells[12]=new StringCell((tokens[12]));
			}
		if(tokens.length<=13 || tokens[13].equals("NULL"))
			{
			cells[13]=DataType.getMissingCell();
			}
		else
			{
			cells[13]=new StringCell((tokens[13]));
			}
		if(tokens.length<=14 || tokens[14].equals("NULL"))
			{
			cells[14]=DataType.getMissingCell();
			}
		else
			{
			cells[14]=new DoubleCell(Double.parseDouble(tokens[14]));
			}
		if(tokens.length<=15 || tokens[15].equals("NULL"))
			{
			cells[15]=DataType.getMissingCell();
			}
		else
			{
			cells[15]=new DoubleCell(Double.parseDouble(tokens[15]));
			}
		if(tokens.length<=16 || tokens[16].equals("NULL"))
			{
			cells[16]=DataType.getMissingCell();
			}
		else
			{
			cells[16]=new IntCell(Integer.parseInt(tokens[16]));
			}
		if(tokens.length<=17 || tokens[17].equals("NULL"))
			{
			cells[17]=DataType.getMissingCell();
			}
		else
			{
			cells[17]=new StringCell((tokens[17]));
			}
		if(tokens.length<=18 || tokens[18].equals("NULL"))
			{
			cells[18]=DataType.getMissingCell();
			}
		else
			{
			cells[18]=new StringCell((tokens[18]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.hapmapSnpsASW")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/hapmapSnpsASW.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[13];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new StringCell((tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new StringCell((tokens[7]));
			}
		if(tokens.length<=8 || tokens[8].equals("NULL"))
			{
			cells[8]=DataType.getMissingCell();
			}
		else
			{
			cells[8]=new StringCell((tokens[8]));
			}
		if(tokens.length<=9 || tokens[9].equals("NULL"))
			{
			cells[9]=DataType.getMissingCell();
			}
		else
			{
			cells[9]=new IntCell(Integer.parseInt(tokens[9]));
			}
		if(tokens.length<=10 || tokens[10].equals("NULL"))
			{
			cells[10]=DataType.getMissingCell();
			}
		else
			{
			cells[10]=new StringCell((tokens[10]));
			}
		if(tokens.length<=11 || tokens[11].equals("NULL"))
			{
			cells[11]=DataType.getMissingCell();
			}
		else
			{
			cells[11]=new IntCell(Integer.parseInt(tokens[11]));
			}
		if(tokens.length<=12 || tokens[12].equals("NULL"))
			{
			cells[12]=DataType.getMissingCell();
			}
		else
			{
			cells[12]=new IntCell(Integer.parseInt(tokens[12]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.hapmapSnpsCEU")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/hapmapSnpsCEU.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[13];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new StringCell((tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new StringCell((tokens[7]));
			}
		if(tokens.length<=8 || tokens[8].equals("NULL"))
			{
			cells[8]=DataType.getMissingCell();
			}
		else
			{
			cells[8]=new StringCell((tokens[8]));
			}
		if(tokens.length<=9 || tokens[9].equals("NULL"))
			{
			cells[9]=DataType.getMissingCell();
			}
		else
			{
			cells[9]=new IntCell(Integer.parseInt(tokens[9]));
			}
		if(tokens.length<=10 || tokens[10].equals("NULL"))
			{
			cells[10]=DataType.getMissingCell();
			}
		else
			{
			cells[10]=new StringCell((tokens[10]));
			}
		if(tokens.length<=11 || tokens[11].equals("NULL"))
			{
			cells[11]=DataType.getMissingCell();
			}
		else
			{
			cells[11]=new IntCell(Integer.parseInt(tokens[11]));
			}
		if(tokens.length<=12 || tokens[12].equals("NULL"))
			{
			cells[12]=DataType.getMissingCell();
			}
		else
			{
			cells[12]=new IntCell(Integer.parseInt(tokens[12]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.hapmapSnpsCHB")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/hapmapSnpsCHB.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[13];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new StringCell((tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new StringCell((tokens[7]));
			}
		if(tokens.length<=8 || tokens[8].equals("NULL"))
			{
			cells[8]=DataType.getMissingCell();
			}
		else
			{
			cells[8]=new StringCell((tokens[8]));
			}
		if(tokens.length<=9 || tokens[9].equals("NULL"))
			{
			cells[9]=DataType.getMissingCell();
			}
		else
			{
			cells[9]=new IntCell(Integer.parseInt(tokens[9]));
			}
		if(tokens.length<=10 || tokens[10].equals("NULL"))
			{
			cells[10]=DataType.getMissingCell();
			}
		else
			{
			cells[10]=new StringCell((tokens[10]));
			}
		if(tokens.length<=11 || tokens[11].equals("NULL"))
			{
			cells[11]=DataType.getMissingCell();
			}
		else
			{
			cells[11]=new IntCell(Integer.parseInt(tokens[11]));
			}
		if(tokens.length<=12 || tokens[12].equals("NULL"))
			{
			cells[12]=DataType.getMissingCell();
			}
		else
			{
			cells[12]=new IntCell(Integer.parseInt(tokens[12]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.hapmapSnpsCHD")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/hapmapSnpsCHD.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[13];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new StringCell((tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new StringCell((tokens[7]));
			}
		if(tokens.length<=8 || tokens[8].equals("NULL"))
			{
			cells[8]=DataType.getMissingCell();
			}
		else
			{
			cells[8]=new StringCell((tokens[8]));
			}
		if(tokens.length<=9 || tokens[9].equals("NULL"))
			{
			cells[9]=DataType.getMissingCell();
			}
		else
			{
			cells[9]=new IntCell(Integer.parseInt(tokens[9]));
			}
		if(tokens.length<=10 || tokens[10].equals("NULL"))
			{
			cells[10]=DataType.getMissingCell();
			}
		else
			{
			cells[10]=new StringCell((tokens[10]));
			}
		if(tokens.length<=11 || tokens[11].equals("NULL"))
			{
			cells[11]=DataType.getMissingCell();
			}
		else
			{
			cells[11]=new IntCell(Integer.parseInt(tokens[11]));
			}
		if(tokens.length<=12 || tokens[12].equals("NULL"))
			{
			cells[12]=DataType.getMissingCell();
			}
		else
			{
			cells[12]=new IntCell(Integer.parseInt(tokens[12]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.hapmapSnpsGIH")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/hapmapSnpsGIH.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[13];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new StringCell((tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new StringCell((tokens[7]));
			}
		if(tokens.length<=8 || tokens[8].equals("NULL"))
			{
			cells[8]=DataType.getMissingCell();
			}
		else
			{
			cells[8]=new StringCell((tokens[8]));
			}
		if(tokens.length<=9 || tokens[9].equals("NULL"))
			{
			cells[9]=DataType.getMissingCell();
			}
		else
			{
			cells[9]=new IntCell(Integer.parseInt(tokens[9]));
			}
		if(tokens.length<=10 || tokens[10].equals("NULL"))
			{
			cells[10]=DataType.getMissingCell();
			}
		else
			{
			cells[10]=new StringCell((tokens[10]));
			}
		if(tokens.length<=11 || tokens[11].equals("NULL"))
			{
			cells[11]=DataType.getMissingCell();
			}
		else
			{
			cells[11]=new IntCell(Integer.parseInt(tokens[11]));
			}
		if(tokens.length<=12 || tokens[12].equals("NULL"))
			{
			cells[12]=DataType.getMissingCell();
			}
		else
			{
			cells[12]=new IntCell(Integer.parseInt(tokens[12]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.hapmapSnpsJPT")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/hapmapSnpsJPT.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[13];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new StringCell((tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new StringCell((tokens[7]));
			}
		if(tokens.length<=8 || tokens[8].equals("NULL"))
			{
			cells[8]=DataType.getMissingCell();
			}
		else
			{
			cells[8]=new StringCell((tokens[8]));
			}
		if(tokens.length<=9 || tokens[9].equals("NULL"))
			{
			cells[9]=DataType.getMissingCell();
			}
		else
			{
			cells[9]=new IntCell(Integer.parseInt(tokens[9]));
			}
		if(tokens.length<=10 || tokens[10].equals("NULL"))
			{
			cells[10]=DataType.getMissingCell();
			}
		else
			{
			cells[10]=new StringCell((tokens[10]));
			}
		if(tokens.length<=11 || tokens[11].equals("NULL"))
			{
			cells[11]=DataType.getMissingCell();
			}
		else
			{
			cells[11]=new IntCell(Integer.parseInt(tokens[11]));
			}
		if(tokens.length<=12 || tokens[12].equals("NULL"))
			{
			cells[12]=DataType.getMissingCell();
			}
		else
			{
			cells[12]=new IntCell(Integer.parseInt(tokens[12]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.hapmapSnpsLWK")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/hapmapSnpsLWK.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[13];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new StringCell((tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new StringCell((tokens[7]));
			}
		if(tokens.length<=8 || tokens[8].equals("NULL"))
			{
			cells[8]=DataType.getMissingCell();
			}
		else
			{
			cells[8]=new StringCell((tokens[8]));
			}
		if(tokens.length<=9 || tokens[9].equals("NULL"))
			{
			cells[9]=DataType.getMissingCell();
			}
		else
			{
			cells[9]=new IntCell(Integer.parseInt(tokens[9]));
			}
		if(tokens.length<=10 || tokens[10].equals("NULL"))
			{
			cells[10]=DataType.getMissingCell();
			}
		else
			{
			cells[10]=new StringCell((tokens[10]));
			}
		if(tokens.length<=11 || tokens[11].equals("NULL"))
			{
			cells[11]=DataType.getMissingCell();
			}
		else
			{
			cells[11]=new IntCell(Integer.parseInt(tokens[11]));
			}
		if(tokens.length<=12 || tokens[12].equals("NULL"))
			{
			cells[12]=DataType.getMissingCell();
			}
		else
			{
			cells[12]=new IntCell(Integer.parseInt(tokens[12]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.hapmapSnpsMEX")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/hapmapSnpsMEX.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[13];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new StringCell((tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new StringCell((tokens[7]));
			}
		if(tokens.length<=8 || tokens[8].equals("NULL"))
			{
			cells[8]=DataType.getMissingCell();
			}
		else
			{
			cells[8]=new StringCell((tokens[8]));
			}
		if(tokens.length<=9 || tokens[9].equals("NULL"))
			{
			cells[9]=DataType.getMissingCell();
			}
		else
			{
			cells[9]=new IntCell(Integer.parseInt(tokens[9]));
			}
		if(tokens.length<=10 || tokens[10].equals("NULL"))
			{
			cells[10]=DataType.getMissingCell();
			}
		else
			{
			cells[10]=new StringCell((tokens[10]));
			}
		if(tokens.length<=11 || tokens[11].equals("NULL"))
			{
			cells[11]=DataType.getMissingCell();
			}
		else
			{
			cells[11]=new IntCell(Integer.parseInt(tokens[11]));
			}
		if(tokens.length<=12 || tokens[12].equals("NULL"))
			{
			cells[12]=DataType.getMissingCell();
			}
		else
			{
			cells[12]=new IntCell(Integer.parseInt(tokens[12]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.hapmapSnpsMKK")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/hapmapSnpsMKK.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[13];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new StringCell((tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new StringCell((tokens[7]));
			}
		if(tokens.length<=8 || tokens[8].equals("NULL"))
			{
			cells[8]=DataType.getMissingCell();
			}
		else
			{
			cells[8]=new StringCell((tokens[8]));
			}
		if(tokens.length<=9 || tokens[9].equals("NULL"))
			{
			cells[9]=DataType.getMissingCell();
			}
		else
			{
			cells[9]=new IntCell(Integer.parseInt(tokens[9]));
			}
		if(tokens.length<=10 || tokens[10].equals("NULL"))
			{
			cells[10]=DataType.getMissingCell();
			}
		else
			{
			cells[10]=new StringCell((tokens[10]));
			}
		if(tokens.length<=11 || tokens[11].equals("NULL"))
			{
			cells[11]=DataType.getMissingCell();
			}
		else
			{
			cells[11]=new IntCell(Integer.parseInt(tokens[11]));
			}
		if(tokens.length<=12 || tokens[12].equals("NULL"))
			{
			cells[12]=DataType.getMissingCell();
			}
		else
			{
			cells[12]=new IntCell(Integer.parseInt(tokens[12]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.hapmapSnpsTSI")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/hapmapSnpsTSI.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[13];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new StringCell((tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new StringCell((tokens[7]));
			}
		if(tokens.length<=8 || tokens[8].equals("NULL"))
			{
			cells[8]=DataType.getMissingCell();
			}
		else
			{
			cells[8]=new StringCell((tokens[8]));
			}
		if(tokens.length<=9 || tokens[9].equals("NULL"))
			{
			cells[9]=DataType.getMissingCell();
			}
		else
			{
			cells[9]=new IntCell(Integer.parseInt(tokens[9]));
			}
		if(tokens.length<=10 || tokens[10].equals("NULL"))
			{
			cells[10]=DataType.getMissingCell();
			}
		else
			{
			cells[10]=new StringCell((tokens[10]));
			}
		if(tokens.length<=11 || tokens[11].equals("NULL"))
			{
			cells[11]=DataType.getMissingCell();
			}
		else
			{
			cells[11]=new IntCell(Integer.parseInt(tokens[11]));
			}
		if(tokens.length<=12 || tokens[12].equals("NULL"))
			{
			cells[12]=DataType.getMissingCell();
			}
		else
			{
			cells[12]=new IntCell(Integer.parseInt(tokens[12]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.hapmapSnpsYRI")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/hapmapSnpsYRI.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[13];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new StringCell((tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new StringCell((tokens[7]));
			}
		if(tokens.length<=8 || tokens[8].equals("NULL"))
			{
			cells[8]=DataType.getMissingCell();
			}
		else
			{
			cells[8]=new StringCell((tokens[8]));
			}
		if(tokens.length<=9 || tokens[9].equals("NULL"))
			{
			cells[9]=DataType.getMissingCell();
			}
		else
			{
			cells[9]=new IntCell(Integer.parseInt(tokens[9]));
			}
		if(tokens.length<=10 || tokens[10].equals("NULL"))
			{
			cells[10]=DataType.getMissingCell();
			}
		else
			{
			cells[10]=new StringCell((tokens[10]));
			}
		if(tokens.length<=11 || tokens[11].equals("NULL"))
			{
			cells[11]=DataType.getMissingCell();
			}
		else
			{
			cells[11]=new IntCell(Integer.parseInt(tokens[11]));
			}
		if(tokens.length<=12 || tokens[12].equals("NULL"))
			{
			cells[12]=DataType.getMissingCell();
			}
		else
			{
			cells[12]=new IntCell(Integer.parseInt(tokens[12]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.hg19ContigDiff")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/hg19ContigDiff.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[10];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new StringCell((tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new IntCell(Integer.parseInt(tokens[7]));
			}
		if(tokens.length<=8 || tokens[8].equals("NULL"))
			{
			cells[8]=DataType.getMissingCell();
			}
		else
			{
			cells[8]=new IntCell(Integer.parseInt(tokens[8]));
			}
		if(tokens.length<=9 || tokens[9].equals("NULL"))
			{
			cells[9]=DataType.getMissingCell();
			}
		else
			{
			cells[9]=new IntCell(Integer.parseInt(tokens[9]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.hgIkmc")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/hgIkmc.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[13];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new StringCell((tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new IntCell(Integer.parseInt(tokens[7]));
			}
		if(tokens.length<=8 || tokens[8].equals("NULL"))
			{
			cells[8]=DataType.getMissingCell();
			}
		else
			{
			cells[8]=new IntCell(Integer.parseInt(tokens[8]));
			}
		if(tokens.length<=9 || tokens[9].equals("NULL"))
			{
			cells[9]=DataType.getMissingCell();
			}
		else
			{
			cells[9]=new IntCell(Integer.parseInt(tokens[9]));
			}
		if(tokens.length<=10 || tokens[10].equals("NULL"))
			{
			cells[10]=DataType.getMissingCell();
			}
		else
			{
			cells[10]=new IntCell(Integer.parseInt(tokens[10]));
			}
		if(tokens.length<=11 || tokens[11].equals("NULL"))
			{
			cells[11]=DataType.getMissingCell();
			}
		else
			{
			cells[11]=new StringCell((tokens[11]));
			}
		if(tokens.length<=12 || tokens[12].equals("NULL"))
			{
			cells[12]=DataType.getMissingCell();
			}
		else
			{
			cells[12]=new StringCell((tokens[12]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.hgdpGeo")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/hgdpGeo.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[8];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new StringCell((tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new StringCell((tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new StringCell((tokens[7]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.hinv70Coding")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/hinv70Coding.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[13];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new StringCell((tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new IntCell(Integer.parseInt(tokens[7]));
			}
		if(tokens.length<=8 || tokens[8].equals("NULL"))
			{
			cells[8]=DataType.getMissingCell();
			}
		else
			{
			cells[8]=new IntCell(Integer.parseInt(tokens[8]));
			}
		if(tokens.length<=9 || tokens[9].equals("NULL"))
			{
			cells[9]=DataType.getMissingCell();
			}
		else
			{
			cells[9]=new IntCell(Integer.parseInt(tokens[9]));
			}
		if(tokens.length<=10 || tokens[10].equals("NULL"))
			{
			cells[10]=DataType.getMissingCell();
			}
		else
			{
			cells[10]=new IntCell(Integer.parseInt(tokens[10]));
			}
		if(tokens.length<=11 || tokens[11].equals("NULL"))
			{
			cells[11]=DataType.getMissingCell();
			}
		else
			{
			cells[11]=new StringCell((tokens[11]));
			}
		if(tokens.length<=12 || tokens[12].equals("NULL"))
			{
			cells[12]=DataType.getMissingCell();
			}
		else
			{
			cells[12]=new StringCell((tokens[12]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.hinv70NonCoding")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/hinv70NonCoding.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[13];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new StringCell((tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new IntCell(Integer.parseInt(tokens[7]));
			}
		if(tokens.length<=8 || tokens[8].equals("NULL"))
			{
			cells[8]=DataType.getMissingCell();
			}
		else
			{
			cells[8]=new IntCell(Integer.parseInt(tokens[8]));
			}
		if(tokens.length<=9 || tokens[9].equals("NULL"))
			{
			cells[9]=DataType.getMissingCell();
			}
		else
			{
			cells[9]=new IntCell(Integer.parseInt(tokens[9]));
			}
		if(tokens.length<=10 || tokens[10].equals("NULL"))
			{
			cells[10]=DataType.getMissingCell();
			}
		else
			{
			cells[10]=new IntCell(Integer.parseInt(tokens[10]));
			}
		if(tokens.length<=11 || tokens[11].equals("NULL"))
			{
			cells[11]=DataType.getMissingCell();
			}
		else
			{
			cells[11]=new StringCell((tokens[11]));
			}
		if(tokens.length<=12 || tokens[12].equals("NULL"))
			{
			cells[12]=DataType.getMissingCell();
			}
		else
			{
			cells[12]=new StringCell((tokens[12]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.hinv70PseudoGene")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/hinv70PseudoGene.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[13];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new StringCell((tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new IntCell(Integer.parseInt(tokens[7]));
			}
		if(tokens.length<=8 || tokens[8].equals("NULL"))
			{
			cells[8]=DataType.getMissingCell();
			}
		else
			{
			cells[8]=new IntCell(Integer.parseInt(tokens[8]));
			}
		if(tokens.length<=9 || tokens[9].equals("NULL"))
			{
			cells[9]=DataType.getMissingCell();
			}
		else
			{
			cells[9]=new IntCell(Integer.parseInt(tokens[9]));
			}
		if(tokens.length<=10 || tokens[10].equals("NULL"))
			{
			cells[10]=DataType.getMissingCell();
			}
		else
			{
			cells[10]=new IntCell(Integer.parseInt(tokens[10]));
			}
		if(tokens.length<=11 || tokens[11].equals("NULL"))
			{
			cells[11]=DataType.getMissingCell();
			}
		else
			{
			cells[11]=new StringCell((tokens[11]));
			}
		if(tokens.length<=12 || tokens[12].equals("NULL"))
			{
			cells[12]=DataType.getMissingCell();
			}
		else
			{
			cells[12]=new StringCell((tokens[12]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.illuminaProbes")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/illuminaProbes.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[13];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new StringCell((tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new IntCell(Integer.parseInt(tokens[7]));
			}
		if(tokens.length<=8 || tokens[8].equals("NULL"))
			{
			cells[8]=DataType.getMissingCell();
			}
		else
			{
			cells[8]=new IntCell(Integer.parseInt(tokens[8]));
			}
		if(tokens.length<=9 || tokens[9].equals("NULL"))
			{
			cells[9]=DataType.getMissingCell();
			}
		else
			{
			cells[9]=new IntCell(Integer.parseInt(tokens[9]));
			}
		if(tokens.length<=10 || tokens[10].equals("NULL"))
			{
			cells[10]=DataType.getMissingCell();
			}
		else
			{
			cells[10]=new IntCell(Integer.parseInt(tokens[10]));
			}
		if(tokens.length<=11 || tokens[11].equals("NULL"))
			{
			cells[11]=DataType.getMissingCell();
			}
		else
			{
			cells[11]=new StringCell((tokens[11]));
			}
		if(tokens.length<=12 || tokens[12].equals("NULL"))
			{
			cells[12]=DataType.getMissingCell();
			}
		else
			{
			cells[12]=new StringCell((tokens[12]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.jaxQtlAsIs")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/jaxQtlAsIs.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[5];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.jaxQtlPadded")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/jaxQtlPadded.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[5];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.knownAlt")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/knownAlt.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[7];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new StringCell((tokens[6]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.knownCanonical")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/knownCanonical.txt.gz";
		}
public int getChromColumn() { return 0;}
public int getChromStartColumn() { return 1;}
public int getChromEndColumn() { return 2;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[6];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new StringCell((tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new IntCell(Integer.parseInt(tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new StringCell((tokens[5]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.knownGene")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/knownGene.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 3;}
public int getChromEndColumn() { return 4;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[12];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new StringCell((tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new StringCell((tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new IntCell(Integer.parseInt(tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new IntCell(Integer.parseInt(tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new IntCell(Integer.parseInt(tokens[7]));
			}
		if(tokens.length<=8 || tokens[8].equals("NULL"))
			{
			cells[8]=DataType.getMissingCell();
			}
		else
			{
			cells[8]=new StringCell((tokens[8]));
			}
		if(tokens.length<=9 || tokens[9].equals("NULL"))
			{
			cells[9]=DataType.getMissingCell();
			}
		else
			{
			cells[9]=new StringCell((tokens[9]));
			}
		if(tokens.length<=10 || tokens[10].equals("NULL"))
			{
			cells[10]=DataType.getMissingCell();
			}
		else
			{
			cells[10]=new StringCell((tokens[10]));
			}
		if(tokens.length<=11 || tokens[11].equals("NULL"))
			{
			cells[11]=DataType.getMissingCell();
			}
		else
			{
			cells[11]=new StringCell((tokens[11]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.laminB1")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/laminB1.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[14];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new IntCell(Integer.parseInt(tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new IntCell(Integer.parseInt(tokens[7]));
			}
		if(tokens.length<=8 || tokens[8].equals("NULL"))
			{
			cells[8]=DataType.getMissingCell();
			}
		else
			{
			cells[8]=new StringCell((tokens[8]));
			}
		if(tokens.length<=9 || tokens[9].equals("NULL"))
			{
			cells[9]=DataType.getMissingCell();
			}
		else
			{
			cells[9]=new DoubleCell(Double.parseDouble(tokens[9]));
			}
		if(tokens.length<=10 || tokens[10].equals("NULL"))
			{
			cells[10]=DataType.getMissingCell();
			}
		else
			{
			cells[10]=new DoubleCell(Double.parseDouble(tokens[10]));
			}
		if(tokens.length<=11 || tokens[11].equals("NULL"))
			{
			cells[11]=DataType.getMissingCell();
			}
		else
			{
			cells[11]=new IntCell(Integer.parseInt(tokens[11]));
			}
		if(tokens.length<=12 || tokens[12].equals("NULL"))
			{
			cells[12]=DataType.getMissingCell();
			}
		else
			{
			cells[12]=new DoubleCell(Double.parseDouble(tokens[12]));
			}
		if(tokens.length<=13 || tokens[13].equals("NULL"))
			{
			cells[13]=DataType.getMissingCell();
			}
		else
			{
			cells[13]=new DoubleCell(Double.parseDouble(tokens[13]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.laminB1Lads")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/laminB1Lads.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[4];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.mgcGenes")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/mgcGenes.txt.gz";
		}
public int getChromColumn() { return 2;}
public int getChromStartColumn() { return 4;}
public int getChromEndColumn() { return 5;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[16];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new StringCell((tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new StringCell((tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new IntCell(Integer.parseInt(tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new IntCell(Integer.parseInt(tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new IntCell(Integer.parseInt(tokens[7]));
			}
		if(tokens.length<=8 || tokens[8].equals("NULL"))
			{
			cells[8]=DataType.getMissingCell();
			}
		else
			{
			cells[8]=new IntCell(Integer.parseInt(tokens[8]));
			}
		if(tokens.length<=9 || tokens[9].equals("NULL"))
			{
			cells[9]=DataType.getMissingCell();
			}
		else
			{
			cells[9]=new StringCell((tokens[9]));
			}
		if(tokens.length<=10 || tokens[10].equals("NULL"))
			{
			cells[10]=DataType.getMissingCell();
			}
		else
			{
			cells[10]=new StringCell((tokens[10]));
			}
		if(tokens.length<=11 || tokens[11].equals("NULL"))
			{
			cells[11]=DataType.getMissingCell();
			}
		else
			{
			cells[11]=new IntCell(Integer.parseInt(tokens[11]));
			}
		if(tokens.length<=12 || tokens[12].equals("NULL"))
			{
			cells[12]=DataType.getMissingCell();
			}
		else
			{
			cells[12]=new StringCell((tokens[12]));
			}
		if(tokens.length<=13 || tokens[13].equals("NULL"))
			{
			cells[13]=DataType.getMissingCell();
			}
		else
			{
			cells[13]=new StringCell((tokens[13]));
			}
		if(tokens.length<=14 || tokens[14].equals("NULL"))
			{
			cells[14]=DataType.getMissingCell();
			}
		else
			{
			cells[14]=new StringCell((tokens[14]));
			}
		if(tokens.length<=15 || tokens[15].equals("NULL"))
			{
			cells[15]=DataType.getMissingCell();
			}
		else
			{
			cells[15]=new StringCell((tokens[15]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.microsat")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/microsat.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[5];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.mrnaOrientInfo")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/mrnaOrientInfo.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[10];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new IntCell(Integer.parseInt(tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new IntCell(Integer.parseInt(tokens[7]));
			}
		if(tokens.length<=8 || tokens[8].equals("NULL"))
			{
			cells[8]=DataType.getMissingCell();
			}
		else
			{
			cells[8]=new IntCell(Integer.parseInt(tokens[8]));
			}
		if(tokens.length<=9 || tokens[9].equals("NULL"))
			{
			cells[9]=DataType.getMissingCell();
			}
		else
			{
			cells[9]=new IntCell(Integer.parseInt(tokens[9]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.multiz46way")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/multiz46way.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[7];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new IntCell(Integer.parseInt(tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new StringCell((tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new DoubleCell(Double.parseDouble(tokens[6]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.multiz46wayFrames")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/multiz46wayFrames.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[12];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new StringCell((tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new StringCell((tokens[7]));
			}
		if(tokens.length<=8 || tokens[8].equals("NULL"))
			{
			cells[8]=DataType.getMissingCell();
			}
		else
			{
			cells[8]=new IntCell(Integer.parseInt(tokens[8]));
			}
		if(tokens.length<=9 || tokens[9].equals("NULL"))
			{
			cells[9]=DataType.getMissingCell();
			}
		else
			{
			cells[9]=new IntCell(Integer.parseInt(tokens[9]));
			}
		if(tokens.length<=10 || tokens[10].equals("NULL"))
			{
			cells[10]=DataType.getMissingCell();
			}
		else
			{
			cells[10]=new IntCell(Integer.parseInt(tokens[10]));
			}
		if(tokens.length<=11 || tokens[11].equals("NULL"))
			{
			cells[11]=DataType.getMissingCell();
			}
		else
			{
			cells[11]=new IntCell(Integer.parseInt(tokens[11]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.multiz46waySummary")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/multiz46waySummary.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[8];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new DoubleCell(Double.parseDouble(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new StringCell((tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new StringCell((tokens[7]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.nestedRepeats")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/nestedRepeats.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[17];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new StringCell((tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new IntCell(Integer.parseInt(tokens[7]));
			}
		if(tokens.length<=8 || tokens[8].equals("NULL"))
			{
			cells[8]=DataType.getMissingCell();
			}
		else
			{
			cells[8]=new IntCell(Integer.parseInt(tokens[8]));
			}
		if(tokens.length<=9 || tokens[9].equals("NULL"))
			{
			cells[9]=DataType.getMissingCell();
			}
		else
			{
			cells[9]=new IntCell(Integer.parseInt(tokens[9]));
			}
		if(tokens.length<=10 || tokens[10].equals("NULL"))
			{
			cells[10]=DataType.getMissingCell();
			}
		else
			{
			cells[10]=new IntCell(Integer.parseInt(tokens[10]));
			}
		if(tokens.length<=11 || tokens[11].equals("NULL"))
			{
			cells[11]=DataType.getMissingCell();
			}
		else
			{
			cells[11]=new StringCell((tokens[11]));
			}
		if(tokens.length<=12 || tokens[12].equals("NULL"))
			{
			cells[12]=DataType.getMissingCell();
			}
		else
			{
			cells[12]=new StringCell((tokens[12]));
			}
		if(tokens.length<=13 || tokens[13].equals("NULL"))
			{
			cells[13]=DataType.getMissingCell();
			}
		else
			{
			cells[13]=new StringCell((tokens[13]));
			}
		if(tokens.length<=14 || tokens[14].equals("NULL"))
			{
			cells[14]=DataType.getMissingCell();
			}
		else
			{
			cells[14]=new IntCell(Integer.parseInt(tokens[14]));
			}
		if(tokens.length<=15 || tokens[15].equals("NULL"))
			{
			cells[15]=DataType.getMissingCell();
			}
		else
			{
			cells[15]=new StringCell((tokens[15]));
			}
		if(tokens.length<=16 || tokens[16].equals("NULL"))
			{
			cells[16]=DataType.getMissingCell();
			}
		else
			{
			cells[16]=new StringCell((tokens[16]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.nscanGene")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/nscanGene.txt.gz";
		}
public int getChromColumn() { return 2;}
public int getChromStartColumn() { return 4;}
public int getChromEndColumn() { return 5;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[16];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new StringCell((tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new StringCell((tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new IntCell(Integer.parseInt(tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new IntCell(Integer.parseInt(tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new IntCell(Integer.parseInt(tokens[7]));
			}
		if(tokens.length<=8 || tokens[8].equals("NULL"))
			{
			cells[8]=DataType.getMissingCell();
			}
		else
			{
			cells[8]=new IntCell(Integer.parseInt(tokens[8]));
			}
		if(tokens.length<=9 || tokens[9].equals("NULL"))
			{
			cells[9]=DataType.getMissingCell();
			}
		else
			{
			cells[9]=new StringCell((tokens[9]));
			}
		if(tokens.length<=10 || tokens[10].equals("NULL"))
			{
			cells[10]=DataType.getMissingCell();
			}
		else
			{
			cells[10]=new StringCell((tokens[10]));
			}
		if(tokens.length<=11 || tokens[11].equals("NULL"))
			{
			cells[11]=DataType.getMissingCell();
			}
		else
			{
			cells[11]=new IntCell(Integer.parseInt(tokens[11]));
			}
		if(tokens.length<=12 || tokens[12].equals("NULL"))
			{
			cells[12]=DataType.getMissingCell();
			}
		else
			{
			cells[12]=new StringCell((tokens[12]));
			}
		if(tokens.length<=13 || tokens[13].equals("NULL"))
			{
			cells[13]=DataType.getMissingCell();
			}
		else
			{
			cells[13]=new StringCell((tokens[13]));
			}
		if(tokens.length<=14 || tokens[14].equals("NULL"))
			{
			cells[14]=DataType.getMissingCell();
			}
		else
			{
			cells[14]=new StringCell((tokens[14]));
			}
		if(tokens.length<=15 || tokens[15].equals("NULL"))
			{
			cells[15]=DataType.getMissingCell();
			}
		else
			{
			cells[15]=new StringCell((tokens[15]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.ntHumChimpCodingDiff")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/ntHumChimpCodingDiff.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[10];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new StringCell((tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new IntCell(Integer.parseInt(tokens[7]));
			}
		if(tokens.length<=8 || tokens[8].equals("NULL"))
			{
			cells[8]=DataType.getMissingCell();
			}
		else
			{
			cells[8]=new IntCell(Integer.parseInt(tokens[8]));
			}
		if(tokens.length<=9 || tokens[9].equals("NULL"))
			{
			cells[9]=DataType.getMissingCell();
			}
		else
			{
			cells[9]=new IntCell(Integer.parseInt(tokens[9]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.ntOoaHaplo")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/ntOoaHaplo.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[16];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new StringCell((tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new IntCell(Integer.parseInt(tokens[7]));
			}
		if(tokens.length<=8 || tokens[8].equals("NULL"))
			{
			cells[8]=DataType.getMissingCell();
			}
		else
			{
			cells[8]=new IntCell(Integer.parseInt(tokens[8]));
			}
		if(tokens.length<=9 || tokens[9].equals("NULL"))
			{
			cells[9]=DataType.getMissingCell();
			}
		else
			{
			cells[9]=new IntCell(Integer.parseInt(tokens[9]));
			}
		if(tokens.length<=10 || tokens[10].equals("NULL"))
			{
			cells[10]=DataType.getMissingCell();
			}
		else
			{
			cells[10]=new DoubleCell(Double.parseDouble(tokens[10]));
			}
		if(tokens.length<=11 || tokens[11].equals("NULL"))
			{
			cells[11]=DataType.getMissingCell();
			}
		else
			{
			cells[11]=new DoubleCell(Double.parseDouble(tokens[11]));
			}
		if(tokens.length<=12 || tokens[12].equals("NULL"))
			{
			cells[12]=DataType.getMissingCell();
			}
		else
			{
			cells[12]=new IntCell(Integer.parseInt(tokens[12]));
			}
		if(tokens.length<=13 || tokens[13].equals("NULL"))
			{
			cells[13]=DataType.getMissingCell();
			}
		else
			{
			cells[13]=new IntCell(Integer.parseInt(tokens[13]));
			}
		if(tokens.length<=14 || tokens[14].equals("NULL"))
			{
			cells[14]=DataType.getMissingCell();
			}
		else
			{
			cells[14]=new IntCell(Integer.parseInt(tokens[14]));
			}
		if(tokens.length<=15 || tokens[15].equals("NULL"))
			{
			cells[15]=DataType.getMissingCell();
			}
		else
			{
			cells[15]=new IntCell(Integer.parseInt(tokens[15]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.ntSssSnps")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/ntSssSnps.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[10];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new StringCell((tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new IntCell(Integer.parseInt(tokens[7]));
			}
		if(tokens.length<=8 || tokens[8].equals("NULL"))
			{
			cells[8]=DataType.getMissingCell();
			}
		else
			{
			cells[8]=new IntCell(Integer.parseInt(tokens[8]));
			}
		if(tokens.length<=9 || tokens[9].equals("NULL"))
			{
			cells[9]=DataType.getMissingCell();
			}
		else
			{
			cells[9]=new IntCell(Integer.parseInt(tokens[9]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.ntSssTop5p")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/ntSssTop5p.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[6];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.omimGene")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/omimGene.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[5];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.oreganno")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/oreganno.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[7];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new StringCell((tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new StringCell((tokens[6]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.orfeomeGenes")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/orfeomeGenes.txt.gz";
		}
public int getChromColumn() { return 2;}
public int getChromStartColumn() { return 4;}
public int getChromEndColumn() { return 5;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[16];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new StringCell((tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new StringCell((tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new IntCell(Integer.parseInt(tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new IntCell(Integer.parseInt(tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new IntCell(Integer.parseInt(tokens[7]));
			}
		if(tokens.length<=8 || tokens[8].equals("NULL"))
			{
			cells[8]=DataType.getMissingCell();
			}
		else
			{
			cells[8]=new IntCell(Integer.parseInt(tokens[8]));
			}
		if(tokens.length<=9 || tokens[9].equals("NULL"))
			{
			cells[9]=DataType.getMissingCell();
			}
		else
			{
			cells[9]=new StringCell((tokens[9]));
			}
		if(tokens.length<=10 || tokens[10].equals("NULL"))
			{
			cells[10]=DataType.getMissingCell();
			}
		else
			{
			cells[10]=new StringCell((tokens[10]));
			}
		if(tokens.length<=11 || tokens[11].equals("NULL"))
			{
			cells[11]=DataType.getMissingCell();
			}
		else
			{
			cells[11]=new IntCell(Integer.parseInt(tokens[11]));
			}
		if(tokens.length<=12 || tokens[12].equals("NULL"))
			{
			cells[12]=DataType.getMissingCell();
			}
		else
			{
			cells[12]=new StringCell((tokens[12]));
			}
		if(tokens.length<=13 || tokens[13].equals("NULL"))
			{
			cells[13]=DataType.getMissingCell();
			}
		else
			{
			cells[13]=new StringCell((tokens[13]));
			}
		if(tokens.length<=14 || tokens[14].equals("NULL"))
			{
			cells[14]=DataType.getMissingCell();
			}
		else
			{
			cells[14]=new StringCell((tokens[14]));
			}
		if(tokens.length<=15 || tokens[15].equals("NULL"))
			{
			cells[15]=DataType.getMissingCell();
			}
		else
			{
			cells[15]=new StringCell((tokens[15]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.pgNA12878")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/pgNA12878.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[8];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new StringCell((tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new StringCell((tokens[7]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.pgNA12891")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/pgNA12891.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[8];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new StringCell((tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new StringCell((tokens[7]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.pgNA12892")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/pgNA12892.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[8];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new StringCell((tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new StringCell((tokens[7]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.pgNA19240")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/pgNA19240.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[8];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new StringCell((tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new StringCell((tokens[7]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.pgSjk")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/pgSjk.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[8];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new StringCell((tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new StringCell((tokens[7]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.pgVenter")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/pgVenter.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[8];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new StringCell((tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new StringCell((tokens[7]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.pgWatson")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/pgWatson.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[8];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new StringCell((tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new StringCell((tokens[7]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.pgYh1")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/pgYh1.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[8];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new StringCell((tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new StringCell((tokens[7]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.pgYoruban3")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/pgYoruban3.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[8];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new StringCell((tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new StringCell((tokens[7]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.phastCons46way")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/phastCons46way.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[14];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new IntCell(Integer.parseInt(tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new IntCell(Integer.parseInt(tokens[7]));
			}
		if(tokens.length<=8 || tokens[8].equals("NULL"))
			{
			cells[8]=DataType.getMissingCell();
			}
		else
			{
			cells[8]=new StringCell((tokens[8]));
			}
		if(tokens.length<=9 || tokens[9].equals("NULL"))
			{
			cells[9]=DataType.getMissingCell();
			}
		else
			{
			cells[9]=new DoubleCell(Double.parseDouble(tokens[9]));
			}
		if(tokens.length<=10 || tokens[10].equals("NULL"))
			{
			cells[10]=DataType.getMissingCell();
			}
		else
			{
			cells[10]=new DoubleCell(Double.parseDouble(tokens[10]));
			}
		if(tokens.length<=11 || tokens[11].equals("NULL"))
			{
			cells[11]=DataType.getMissingCell();
			}
		else
			{
			cells[11]=new IntCell(Integer.parseInt(tokens[11]));
			}
		if(tokens.length<=12 || tokens[12].equals("NULL"))
			{
			cells[12]=DataType.getMissingCell();
			}
		else
			{
			cells[12]=new DoubleCell(Double.parseDouble(tokens[12]));
			}
		if(tokens.length<=13 || tokens[13].equals("NULL"))
			{
			cells[13]=DataType.getMissingCell();
			}
		else
			{
			cells[13]=new DoubleCell(Double.parseDouble(tokens[13]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.phastCons46wayPlacental")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/phastCons46wayPlacental.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[14];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new IntCell(Integer.parseInt(tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new IntCell(Integer.parseInt(tokens[7]));
			}
		if(tokens.length<=8 || tokens[8].equals("NULL"))
			{
			cells[8]=DataType.getMissingCell();
			}
		else
			{
			cells[8]=new StringCell((tokens[8]));
			}
		if(tokens.length<=9 || tokens[9].equals("NULL"))
			{
			cells[9]=DataType.getMissingCell();
			}
		else
			{
			cells[9]=new DoubleCell(Double.parseDouble(tokens[9]));
			}
		if(tokens.length<=10 || tokens[10].equals("NULL"))
			{
			cells[10]=DataType.getMissingCell();
			}
		else
			{
			cells[10]=new DoubleCell(Double.parseDouble(tokens[10]));
			}
		if(tokens.length<=11 || tokens[11].equals("NULL"))
			{
			cells[11]=DataType.getMissingCell();
			}
		else
			{
			cells[11]=new IntCell(Integer.parseInt(tokens[11]));
			}
		if(tokens.length<=12 || tokens[12].equals("NULL"))
			{
			cells[12]=DataType.getMissingCell();
			}
		else
			{
			cells[12]=new DoubleCell(Double.parseDouble(tokens[12]));
			}
		if(tokens.length<=13 || tokens[13].equals("NULL"))
			{
			cells[13]=DataType.getMissingCell();
			}
		else
			{
			cells[13]=new DoubleCell(Double.parseDouble(tokens[13]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.phastCons46wayPrimates")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/phastCons46wayPrimates.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[14];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new IntCell(Integer.parseInt(tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new IntCell(Integer.parseInt(tokens[7]));
			}
		if(tokens.length<=8 || tokens[8].equals("NULL"))
			{
			cells[8]=DataType.getMissingCell();
			}
		else
			{
			cells[8]=new StringCell((tokens[8]));
			}
		if(tokens.length<=9 || tokens[9].equals("NULL"))
			{
			cells[9]=DataType.getMissingCell();
			}
		else
			{
			cells[9]=new DoubleCell(Double.parseDouble(tokens[9]));
			}
		if(tokens.length<=10 || tokens[10].equals("NULL"))
			{
			cells[10]=DataType.getMissingCell();
			}
		else
			{
			cells[10]=new DoubleCell(Double.parseDouble(tokens[10]));
			}
		if(tokens.length<=11 || tokens[11].equals("NULL"))
			{
			cells[11]=DataType.getMissingCell();
			}
		else
			{
			cells[11]=new IntCell(Integer.parseInt(tokens[11]));
			}
		if(tokens.length<=12 || tokens[12].equals("NULL"))
			{
			cells[12]=DataType.getMissingCell();
			}
		else
			{
			cells[12]=new DoubleCell(Double.parseDouble(tokens[12]));
			}
		if(tokens.length<=13 || tokens[13].equals("NULL"))
			{
			cells[13]=DataType.getMissingCell();
			}
		else
			{
			cells[13]=new DoubleCell(Double.parseDouble(tokens[13]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.phastConsElements46way")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/phastConsElements46way.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[6];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.phastConsElements46wayPlacental")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/phastConsElements46wayPlacental.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[6];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.phastConsElements46wayPrimates")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/phastConsElements46wayPrimates.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[6];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.phyloP46wayAll")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/phyloP46wayAll.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[14];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new IntCell(Integer.parseInt(tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new IntCell(Integer.parseInt(tokens[7]));
			}
		if(tokens.length<=8 || tokens[8].equals("NULL"))
			{
			cells[8]=DataType.getMissingCell();
			}
		else
			{
			cells[8]=new StringCell((tokens[8]));
			}
		if(tokens.length<=9 || tokens[9].equals("NULL"))
			{
			cells[9]=DataType.getMissingCell();
			}
		else
			{
			cells[9]=new DoubleCell(Double.parseDouble(tokens[9]));
			}
		if(tokens.length<=10 || tokens[10].equals("NULL"))
			{
			cells[10]=DataType.getMissingCell();
			}
		else
			{
			cells[10]=new DoubleCell(Double.parseDouble(tokens[10]));
			}
		if(tokens.length<=11 || tokens[11].equals("NULL"))
			{
			cells[11]=DataType.getMissingCell();
			}
		else
			{
			cells[11]=new IntCell(Integer.parseInt(tokens[11]));
			}
		if(tokens.length<=12 || tokens[12].equals("NULL"))
			{
			cells[12]=DataType.getMissingCell();
			}
		else
			{
			cells[12]=new DoubleCell(Double.parseDouble(tokens[12]));
			}
		if(tokens.length<=13 || tokens[13].equals("NULL"))
			{
			cells[13]=DataType.getMissingCell();
			}
		else
			{
			cells[13]=new DoubleCell(Double.parseDouble(tokens[13]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.phyloP46wayPlacental")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/phyloP46wayPlacental.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[14];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new IntCell(Integer.parseInt(tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new IntCell(Integer.parseInt(tokens[7]));
			}
		if(tokens.length<=8 || tokens[8].equals("NULL"))
			{
			cells[8]=DataType.getMissingCell();
			}
		else
			{
			cells[8]=new StringCell((tokens[8]));
			}
		if(tokens.length<=9 || tokens[9].equals("NULL"))
			{
			cells[9]=DataType.getMissingCell();
			}
		else
			{
			cells[9]=new DoubleCell(Double.parseDouble(tokens[9]));
			}
		if(tokens.length<=10 || tokens[10].equals("NULL"))
			{
			cells[10]=DataType.getMissingCell();
			}
		else
			{
			cells[10]=new DoubleCell(Double.parseDouble(tokens[10]));
			}
		if(tokens.length<=11 || tokens[11].equals("NULL"))
			{
			cells[11]=DataType.getMissingCell();
			}
		else
			{
			cells[11]=new IntCell(Integer.parseInt(tokens[11]));
			}
		if(tokens.length<=12 || tokens[12].equals("NULL"))
			{
			cells[12]=DataType.getMissingCell();
			}
		else
			{
			cells[12]=new DoubleCell(Double.parseDouble(tokens[12]));
			}
		if(tokens.length<=13 || tokens[13].equals("NULL"))
			{
			cells[13]=DataType.getMissingCell();
			}
		else
			{
			cells[13]=new DoubleCell(Double.parseDouble(tokens[13]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.phyloP46wayPrimates")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/phyloP46wayPrimates.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[14];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new IntCell(Integer.parseInt(tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new IntCell(Integer.parseInt(tokens[7]));
			}
		if(tokens.length<=8 || tokens[8].equals("NULL"))
			{
			cells[8]=DataType.getMissingCell();
			}
		else
			{
			cells[8]=new StringCell((tokens[8]));
			}
		if(tokens.length<=9 || tokens[9].equals("NULL"))
			{
			cells[9]=DataType.getMissingCell();
			}
		else
			{
			cells[9]=new DoubleCell(Double.parseDouble(tokens[9]));
			}
		if(tokens.length<=10 || tokens[10].equals("NULL"))
			{
			cells[10]=DataType.getMissingCell();
			}
		else
			{
			cells[10]=new DoubleCell(Double.parseDouble(tokens[10]));
			}
		if(tokens.length<=11 || tokens[11].equals("NULL"))
			{
			cells[11]=DataType.getMissingCell();
			}
		else
			{
			cells[11]=new IntCell(Integer.parseInt(tokens[11]));
			}
		if(tokens.length<=12 || tokens[12].equals("NULL"))
			{
			cells[12]=DataType.getMissingCell();
			}
		else
			{
			cells[12]=new DoubleCell(Double.parseDouble(tokens[12]));
			}
		if(tokens.length<=13 || tokens[13].equals("NULL"))
			{
			cells[13]=DataType.getMissingCell();
			}
		else
			{
			cells[13]=new DoubleCell(Double.parseDouble(tokens[13]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.polyaDb")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/polyaDb.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[9];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new StringCell((tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new IntCell(Integer.parseInt(tokens[7]));
			}
		if(tokens.length<=8 || tokens[8].equals("NULL"))
			{
			cells[8]=DataType.getMissingCell();
			}
		else
			{
			cells[8]=new IntCell(Integer.parseInt(tokens[8]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.polyaPredict")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/polyaPredict.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[9];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new StringCell((tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new IntCell(Integer.parseInt(tokens[7]));
			}
		if(tokens.length<=8 || tokens[8].equals("NULL"))
			{
			cells[8]=DataType.getMissingCell();
			}
		else
			{
			cells[8]=new IntCell(Integer.parseInt(tokens[8]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.recombRate")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/recombRate.txt.gz";
		}
public int getChromColumn() { return 0;}
public int getChromStartColumn() { return 1;}
public int getChromEndColumn() { return 2;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[13];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new StringCell((tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new IntCell(Integer.parseInt(tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new StringCell((tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new DoubleCell(Double.parseDouble(tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new DoubleCell(Double.parseDouble(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new DoubleCell(Double.parseDouble(tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new DoubleCell(Double.parseDouble(tokens[7]));
			}
		if(tokens.length<=8 || tokens[8].equals("NULL"))
			{
			cells[8]=DataType.getMissingCell();
			}
		else
			{
			cells[8]=new DoubleCell(Double.parseDouble(tokens[8]));
			}
		if(tokens.length<=9 || tokens[9].equals("NULL"))
			{
			cells[9]=DataType.getMissingCell();
			}
		else
			{
			cells[9]=new DoubleCell(Double.parseDouble(tokens[9]));
			}
		if(tokens.length<=10 || tokens[10].equals("NULL"))
			{
			cells[10]=DataType.getMissingCell();
			}
		else
			{
			cells[10]=new DoubleCell(Double.parseDouble(tokens[10]));
			}
		if(tokens.length<=11 || tokens[11].equals("NULL"))
			{
			cells[11]=DataType.getMissingCell();
			}
		else
			{
			cells[11]=new DoubleCell(Double.parseDouble(tokens[11]));
			}
		if(tokens.length<=12 || tokens[12].equals("NULL"))
			{
			cells[12]=DataType.getMissingCell();
			}
		else
			{
			cells[12]=new DoubleCell(Double.parseDouble(tokens[12]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.refFlat")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/refFlat.txt.gz";
		}
public int getChromColumn() { return 2;}
public int getChromStartColumn() { return 4;}
public int getChromEndColumn() { return 5;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[11];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new StringCell((tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new StringCell((tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new StringCell((tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new IntCell(Integer.parseInt(tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new IntCell(Integer.parseInt(tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new IntCell(Integer.parseInt(tokens[7]));
			}
		if(tokens.length<=8 || tokens[8].equals("NULL"))
			{
			cells[8]=DataType.getMissingCell();
			}
		else
			{
			cells[8]=new IntCell(Integer.parseInt(tokens[8]));
			}
		if(tokens.length<=9 || tokens[9].equals("NULL"))
			{
			cells[9]=DataType.getMissingCell();
			}
		else
			{
			cells[9]=new StringCell((tokens[9]));
			}
		if(tokens.length<=10 || tokens[10].equals("NULL"))
			{
			cells[10]=DataType.getMissingCell();
			}
		else
			{
			cells[10]=new StringCell((tokens[10]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.refGene")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/refGene.txt.gz";
		}
public int getChromColumn() { return 2;}
public int getChromStartColumn() { return 4;}
public int getChromEndColumn() { return 5;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[16];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new StringCell((tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new StringCell((tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new IntCell(Integer.parseInt(tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new IntCell(Integer.parseInt(tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new IntCell(Integer.parseInt(tokens[7]));
			}
		if(tokens.length<=8 || tokens[8].equals("NULL"))
			{
			cells[8]=DataType.getMissingCell();
			}
		else
			{
			cells[8]=new IntCell(Integer.parseInt(tokens[8]));
			}
		if(tokens.length<=9 || tokens[9].equals("NULL"))
			{
			cells[9]=DataType.getMissingCell();
			}
		else
			{
			cells[9]=new StringCell((tokens[9]));
			}
		if(tokens.length<=10 || tokens[10].equals("NULL"))
			{
			cells[10]=DataType.getMissingCell();
			}
		else
			{
			cells[10]=new StringCell((tokens[10]));
			}
		if(tokens.length<=11 || tokens[11].equals("NULL"))
			{
			cells[11]=DataType.getMissingCell();
			}
		else
			{
			cells[11]=new IntCell(Integer.parseInt(tokens[11]));
			}
		if(tokens.length<=12 || tokens[12].equals("NULL"))
			{
			cells[12]=DataType.getMissingCell();
			}
		else
			{
			cells[12]=new StringCell((tokens[12]));
			}
		if(tokens.length<=13 || tokens[13].equals("NULL"))
			{
			cells[13]=DataType.getMissingCell();
			}
		else
			{
			cells[13]=new StringCell((tokens[13]));
			}
		if(tokens.length<=14 || tokens[14].equals("NULL"))
			{
			cells[14]=DataType.getMissingCell();
			}
		else
			{
			cells[14]=new StringCell((tokens[14]));
			}
		if(tokens.length<=15 || tokens[15].equals("NULL"))
			{
			cells[15]=DataType.getMissingCell();
			}
		else
			{
			cells[15]=new StringCell((tokens[15]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.rgdQtl")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/rgdQtl.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[5];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.rgdRatQtl")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/rgdRatQtl.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[5];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.rnaCluster")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/rnaCluster.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[13];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new StringCell((tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new IntCell(Integer.parseInt(tokens[7]));
			}
		if(tokens.length<=8 || tokens[8].equals("NULL"))
			{
			cells[8]=DataType.getMissingCell();
			}
		else
			{
			cells[8]=new IntCell(Integer.parseInt(tokens[8]));
			}
		if(tokens.length<=9 || tokens[9].equals("NULL"))
			{
			cells[9]=DataType.getMissingCell();
			}
		else
			{
			cells[9]=new IntCell(Integer.parseInt(tokens[9]));
			}
		if(tokens.length<=10 || tokens[10].equals("NULL"))
			{
			cells[10]=DataType.getMissingCell();
			}
		else
			{
			cells[10]=new IntCell(Integer.parseInt(tokens[10]));
			}
		if(tokens.length<=11 || tokens[11].equals("NULL"))
			{
			cells[11]=DataType.getMissingCell();
			}
		else
			{
			cells[11]=new StringCell((tokens[11]));
			}
		if(tokens.length<=12 || tokens[12].equals("NULL"))
			{
			cells[12]=DataType.getMissingCell();
			}
		else
			{
			cells[12]=new StringCell((tokens[12]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.sestanBrainAtlas")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/sestanBrainAtlas.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[16];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new StringCell((tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new IntCell(Integer.parseInt(tokens[7]));
			}
		if(tokens.length<=8 || tokens[8].equals("NULL"))
			{
			cells[8]=DataType.getMissingCell();
			}
		else
			{
			cells[8]=new IntCell(Integer.parseInt(tokens[8]));
			}
		if(tokens.length<=9 || tokens[9].equals("NULL"))
			{
			cells[9]=DataType.getMissingCell();
			}
		else
			{
			cells[9]=new IntCell(Integer.parseInt(tokens[9]));
			}
		if(tokens.length<=10 || tokens[10].equals("NULL"))
			{
			cells[10]=DataType.getMissingCell();
			}
		else
			{
			cells[10]=new IntCell(Integer.parseInt(tokens[10]));
			}
		if(tokens.length<=11 || tokens[11].equals("NULL"))
			{
			cells[11]=DataType.getMissingCell();
			}
		else
			{
			cells[11]=new StringCell((tokens[11]));
			}
		if(tokens.length<=12 || tokens[12].equals("NULL"))
			{
			cells[12]=DataType.getMissingCell();
			}
		else
			{
			cells[12]=new StringCell((tokens[12]));
			}
		if(tokens.length<=13 || tokens[13].equals("NULL"))
			{
			cells[13]=DataType.getMissingCell();
			}
		else
			{
			cells[13]=new IntCell(Integer.parseInt(tokens[13]));
			}
		if(tokens.length<=14 || tokens[14].equals("NULL"))
			{
			cells[14]=DataType.getMissingCell();
			}
		else
			{
			cells[14]=new StringCell((tokens[14]));
			}
		if(tokens.length<=15 || tokens[15].equals("NULL"))
			{
			cells[15]=DataType.getMissingCell();
			}
		else
			{
			cells[15]=new StringCell((tokens[15]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.sgpGene")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/sgpGene.txt.gz";
		}
public int getChromColumn() { return 2;}
public int getChromStartColumn() { return 4;}
public int getChromEndColumn() { return 5;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[16];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new StringCell((tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new StringCell((tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new IntCell(Integer.parseInt(tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new IntCell(Integer.parseInt(tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new IntCell(Integer.parseInt(tokens[7]));
			}
		if(tokens.length<=8 || tokens[8].equals("NULL"))
			{
			cells[8]=DataType.getMissingCell();
			}
		else
			{
			cells[8]=new IntCell(Integer.parseInt(tokens[8]));
			}
		if(tokens.length<=9 || tokens[9].equals("NULL"))
			{
			cells[9]=DataType.getMissingCell();
			}
		else
			{
			cells[9]=new StringCell((tokens[9]));
			}
		if(tokens.length<=10 || tokens[10].equals("NULL"))
			{
			cells[10]=DataType.getMissingCell();
			}
		else
			{
			cells[10]=new StringCell((tokens[10]));
			}
		if(tokens.length<=11 || tokens[11].equals("NULL"))
			{
			cells[11]=DataType.getMissingCell();
			}
		else
			{
			cells[11]=new IntCell(Integer.parseInt(tokens[11]));
			}
		if(tokens.length<=12 || tokens[12].equals("NULL"))
			{
			cells[12]=DataType.getMissingCell();
			}
		else
			{
			cells[12]=new StringCell((tokens[12]));
			}
		if(tokens.length<=13 || tokens[13].equals("NULL"))
			{
			cells[13]=DataType.getMissingCell();
			}
		else
			{
			cells[13]=new StringCell((tokens[13]));
			}
		if(tokens.length<=14 || tokens[14].equals("NULL"))
			{
			cells[14]=DataType.getMissingCell();
			}
		else
			{
			cells[14]=new StringCell((tokens[14]));
			}
		if(tokens.length<=15 || tokens[15].equals("NULL"))
			{
			cells[15]=DataType.getMissingCell();
			}
		else
			{
			cells[15]=new StringCell((tokens[15]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.simpleRepeat")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/simpleRepeat.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[17];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new DoubleCell(Double.parseDouble(tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new IntCell(Integer.parseInt(tokens[7]));
			}
		if(tokens.length<=8 || tokens[8].equals("NULL"))
			{
			cells[8]=DataType.getMissingCell();
			}
		else
			{
			cells[8]=new IntCell(Integer.parseInt(tokens[8]));
			}
		if(tokens.length<=9 || tokens[9].equals("NULL"))
			{
			cells[9]=DataType.getMissingCell();
			}
		else
			{
			cells[9]=new IntCell(Integer.parseInt(tokens[9]));
			}
		if(tokens.length<=10 || tokens[10].equals("NULL"))
			{
			cells[10]=DataType.getMissingCell();
			}
		else
			{
			cells[10]=new IntCell(Integer.parseInt(tokens[10]));
			}
		if(tokens.length<=11 || tokens[11].equals("NULL"))
			{
			cells[11]=DataType.getMissingCell();
			}
		else
			{
			cells[11]=new IntCell(Integer.parseInt(tokens[11]));
			}
		if(tokens.length<=12 || tokens[12].equals("NULL"))
			{
			cells[12]=DataType.getMissingCell();
			}
		else
			{
			cells[12]=new IntCell(Integer.parseInt(tokens[12]));
			}
		if(tokens.length<=13 || tokens[13].equals("NULL"))
			{
			cells[13]=DataType.getMissingCell();
			}
		else
			{
			cells[13]=new IntCell(Integer.parseInt(tokens[13]));
			}
		if(tokens.length<=14 || tokens[14].equals("NULL"))
			{
			cells[14]=DataType.getMissingCell();
			}
		else
			{
			cells[14]=new IntCell(Integer.parseInt(tokens[14]));
			}
		if(tokens.length<=15 || tokens[15].equals("NULL"))
			{
			cells[15]=DataType.getMissingCell();
			}
		else
			{
			cells[15]=new DoubleCell(Double.parseDouble(tokens[15]));
			}
		if(tokens.length<=16 || tokens[16].equals("NULL"))
			{
			cells[16]=DataType.getMissingCell();
			}
		else
			{
			cells[16]=new StringCell((tokens[16]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.snp131")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/snp131.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[18];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new StringCell((tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new StringCell((tokens[7]));
			}
		if(tokens.length<=8 || tokens[8].equals("NULL"))
			{
			cells[8]=DataType.getMissingCell();
			}
		else
			{
			cells[8]=new StringCell((tokens[8]));
			}
		if(tokens.length<=9 || tokens[9].equals("NULL"))
			{
			cells[9]=DataType.getMissingCell();
			}
		else
			{
			cells[9]=new StringCell((tokens[9]));
			}
		if(tokens.length<=10 || tokens[10].equals("NULL"))
			{
			cells[10]=DataType.getMissingCell();
			}
		else
			{
			cells[10]=new StringCell((tokens[10]));
			}
		if(tokens.length<=11 || tokens[11].equals("NULL"))
			{
			cells[11]=DataType.getMissingCell();
			}
		else
			{
			cells[11]=new StringCell((tokens[11]));
			}
		if(tokens.length<=12 || tokens[12].equals("NULL"))
			{
			cells[12]=DataType.getMissingCell();
			}
		else
			{
			cells[12]=new StringCell((tokens[12]));
			}
		if(tokens.length<=13 || tokens[13].equals("NULL"))
			{
			cells[13]=DataType.getMissingCell();
			}
		else
			{
			cells[13]=new DoubleCell(Double.parseDouble(tokens[13]));
			}
		if(tokens.length<=14 || tokens[14].equals("NULL"))
			{
			cells[14]=DataType.getMissingCell();
			}
		else
			{
			cells[14]=new DoubleCell(Double.parseDouble(tokens[14]));
			}
		if(tokens.length<=15 || tokens[15].equals("NULL"))
			{
			cells[15]=DataType.getMissingCell();
			}
		else
			{
			cells[15]=new StringCell((tokens[15]));
			}
		if(tokens.length<=16 || tokens[16].equals("NULL"))
			{
			cells[16]=DataType.getMissingCell();
			}
		else
			{
			cells[16]=new StringCell((tokens[16]));
			}
		if(tokens.length<=17 || tokens[17].equals("NULL"))
			{
			cells[17]=DataType.getMissingCell();
			}
		else
			{
			cells[17]=new IntCell(Integer.parseInt(tokens[17]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.snp131CodingDbSnp")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/snp131CodingDbSnp.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[12];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new StringCell((tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new StringCell((tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new IntCell(Integer.parseInt(tokens[7]));
			}
		if(tokens.length<=8 || tokens[8].equals("NULL"))
			{
			cells[8]=DataType.getMissingCell();
			}
		else
			{
			cells[8]=new StringCell((tokens[8]));
			}
		if(tokens.length<=9 || tokens[9].equals("NULL"))
			{
			cells[9]=DataType.getMissingCell();
			}
		else
			{
			cells[9]=new StringCell((tokens[9]));
			}
		if(tokens.length<=10 || tokens[10].equals("NULL"))
			{
			cells[10]=DataType.getMissingCell();
			}
		else
			{
			cells[10]=new StringCell((tokens[10]));
			}
		if(tokens.length<=11 || tokens[11].equals("NULL"))
			{
			cells[11]=DataType.getMissingCell();
			}
		else
			{
			cells[11]=new StringCell((tokens[11]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.snp131Exceptions")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/snp131Exceptions.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[6];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new StringCell((tokens[5]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.snp131OrthoPt2Pa2Rm2")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/snp131OrthoPt2Pa2Rm2.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[23];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new StringCell((tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new StringCell((tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new StringCell((tokens[7]));
			}
		if(tokens.length<=8 || tokens[8].equals("NULL"))
			{
			cells[8]=DataType.getMissingCell();
			}
		else
			{
			cells[8]=new StringCell((tokens[8]));
			}
		if(tokens.length<=9 || tokens[9].equals("NULL"))
			{
			cells[9]=DataType.getMissingCell();
			}
		else
			{
			cells[9]=new IntCell(Integer.parseInt(tokens[9]));
			}
		if(tokens.length<=10 || tokens[10].equals("NULL"))
			{
			cells[10]=DataType.getMissingCell();
			}
		else
			{
			cells[10]=new IntCell(Integer.parseInt(tokens[10]));
			}
		if(tokens.length<=11 || tokens[11].equals("NULL"))
			{
			cells[11]=DataType.getMissingCell();
			}
		else
			{
			cells[11]=new StringCell((tokens[11]));
			}
		if(tokens.length<=12 || tokens[12].equals("NULL"))
			{
			cells[12]=DataType.getMissingCell();
			}
		else
			{
			cells[12]=new StringCell((tokens[12]));
			}
		if(tokens.length<=13 || tokens[13].equals("NULL"))
			{
			cells[13]=DataType.getMissingCell();
			}
		else
			{
			cells[13]=new StringCell((tokens[13]));
			}
		if(tokens.length<=14 || tokens[14].equals("NULL"))
			{
			cells[14]=DataType.getMissingCell();
			}
		else
			{
			cells[14]=new IntCell(Integer.parseInt(tokens[14]));
			}
		if(tokens.length<=15 || tokens[15].equals("NULL"))
			{
			cells[15]=DataType.getMissingCell();
			}
		else
			{
			cells[15]=new IntCell(Integer.parseInt(tokens[15]));
			}
		if(tokens.length<=16 || tokens[16].equals("NULL"))
			{
			cells[16]=DataType.getMissingCell();
			}
		else
			{
			cells[16]=new StringCell((tokens[16]));
			}
		if(tokens.length<=17 || tokens[17].equals("NULL"))
			{
			cells[17]=DataType.getMissingCell();
			}
		else
			{
			cells[17]=new StringCell((tokens[17]));
			}
		if(tokens.length<=18 || tokens[18].equals("NULL"))
			{
			cells[18]=DataType.getMissingCell();
			}
		else
			{
			cells[18]=new StringCell((tokens[18]));
			}
		if(tokens.length<=19 || tokens[19].equals("NULL"))
			{
			cells[19]=DataType.getMissingCell();
			}
		else
			{
			cells[19]=new IntCell(Integer.parseInt(tokens[19]));
			}
		if(tokens.length<=20 || tokens[20].equals("NULL"))
			{
			cells[20]=DataType.getMissingCell();
			}
		else
			{
			cells[20]=new IntCell(Integer.parseInt(tokens[20]));
			}
		if(tokens.length<=21 || tokens[21].equals("NULL"))
			{
			cells[21]=DataType.getMissingCell();
			}
		else
			{
			cells[21]=new StringCell((tokens[21]));
			}
		if(tokens.length<=22 || tokens[22].equals("NULL"))
			{
			cells[22]=DataType.getMissingCell();
			}
		else
			{
			cells[22]=new StringCell((tokens[22]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.snp132")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/snp132.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[26];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new StringCell((tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new StringCell((tokens[7]));
			}
		if(tokens.length<=8 || tokens[8].equals("NULL"))
			{
			cells[8]=DataType.getMissingCell();
			}
		else
			{
			cells[8]=new StringCell((tokens[8]));
			}
		if(tokens.length<=9 || tokens[9].equals("NULL"))
			{
			cells[9]=DataType.getMissingCell();
			}
		else
			{
			cells[9]=new StringCell((tokens[9]));
			}
		if(tokens.length<=10 || tokens[10].equals("NULL"))
			{
			cells[10]=DataType.getMissingCell();
			}
		else
			{
			cells[10]=new StringCell((tokens[10]));
			}
		if(tokens.length<=11 || tokens[11].equals("NULL"))
			{
			cells[11]=DataType.getMissingCell();
			}
		else
			{
			cells[11]=new StringCell((tokens[11]));
			}
		if(tokens.length<=12 || tokens[12].equals("NULL"))
			{
			cells[12]=DataType.getMissingCell();
			}
		else
			{
			cells[12]=new StringCell((tokens[12]));
			}
		if(tokens.length<=13 || tokens[13].equals("NULL"))
			{
			cells[13]=DataType.getMissingCell();
			}
		else
			{
			cells[13]=new DoubleCell(Double.parseDouble(tokens[13]));
			}
		if(tokens.length<=14 || tokens[14].equals("NULL"))
			{
			cells[14]=DataType.getMissingCell();
			}
		else
			{
			cells[14]=new DoubleCell(Double.parseDouble(tokens[14]));
			}
		if(tokens.length<=15 || tokens[15].equals("NULL"))
			{
			cells[15]=DataType.getMissingCell();
			}
		else
			{
			cells[15]=new StringCell((tokens[15]));
			}
		if(tokens.length<=16 || tokens[16].equals("NULL"))
			{
			cells[16]=DataType.getMissingCell();
			}
		else
			{
			cells[16]=new StringCell((tokens[16]));
			}
		if(tokens.length<=17 || tokens[17].equals("NULL"))
			{
			cells[17]=DataType.getMissingCell();
			}
		else
			{
			cells[17]=new IntCell(Integer.parseInt(tokens[17]));
			}
		if(tokens.length<=18 || tokens[18].equals("NULL"))
			{
			cells[18]=DataType.getMissingCell();
			}
		else
			{
			cells[18]=new StringCell((tokens[18]));
			}
		if(tokens.length<=19 || tokens[19].equals("NULL"))
			{
			cells[19]=DataType.getMissingCell();
			}
		else
			{
			cells[19]=new IntCell(Integer.parseInt(tokens[19]));
			}
		if(tokens.length<=20 || tokens[20].equals("NULL"))
			{
			cells[20]=DataType.getMissingCell();
			}
		else
			{
			cells[20]=new StringCell((tokens[20]));
			}
		if(tokens.length<=21 || tokens[21].equals("NULL"))
			{
			cells[21]=DataType.getMissingCell();
			}
		else
			{
			cells[21]=new IntCell(Integer.parseInt(tokens[21]));
			}
		if(tokens.length<=22 || tokens[22].equals("NULL"))
			{
			cells[22]=DataType.getMissingCell();
			}
		else
			{
			cells[22]=new StringCell((tokens[22]));
			}
		if(tokens.length<=23 || tokens[23].equals("NULL"))
			{
			cells[23]=DataType.getMissingCell();
			}
		else
			{
			cells[23]=new StringCell((tokens[23]));
			}
		if(tokens.length<=24 || tokens[24].equals("NULL"))
			{
			cells[24]=DataType.getMissingCell();
			}
		else
			{
			cells[24]=new StringCell((tokens[24]));
			}
		if(tokens.length<=25 || tokens[25].equals("NULL"))
			{
			cells[25]=DataType.getMissingCell();
			}
		else
			{
			cells[25]=new StringCell((tokens[25]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.snp132CodingDbSnp")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/snp132CodingDbSnp.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[12];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new StringCell((tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new StringCell((tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new IntCell(Integer.parseInt(tokens[7]));
			}
		if(tokens.length<=8 || tokens[8].equals("NULL"))
			{
			cells[8]=DataType.getMissingCell();
			}
		else
			{
			cells[8]=new StringCell((tokens[8]));
			}
		if(tokens.length<=9 || tokens[9].equals("NULL"))
			{
			cells[9]=DataType.getMissingCell();
			}
		else
			{
			cells[9]=new StringCell((tokens[9]));
			}
		if(tokens.length<=10 || tokens[10].equals("NULL"))
			{
			cells[10]=DataType.getMissingCell();
			}
		else
			{
			cells[10]=new StringCell((tokens[10]));
			}
		if(tokens.length<=11 || tokens[11].equals("NULL"))
			{
			cells[11]=DataType.getMissingCell();
			}
		else
			{
			cells[11]=new StringCell((tokens[11]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.snp132Common")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/snp132Common.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[26];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new StringCell((tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new StringCell((tokens[7]));
			}
		if(tokens.length<=8 || tokens[8].equals("NULL"))
			{
			cells[8]=DataType.getMissingCell();
			}
		else
			{
			cells[8]=new StringCell((tokens[8]));
			}
		if(tokens.length<=9 || tokens[9].equals("NULL"))
			{
			cells[9]=DataType.getMissingCell();
			}
		else
			{
			cells[9]=new StringCell((tokens[9]));
			}
		if(tokens.length<=10 || tokens[10].equals("NULL"))
			{
			cells[10]=DataType.getMissingCell();
			}
		else
			{
			cells[10]=new StringCell((tokens[10]));
			}
		if(tokens.length<=11 || tokens[11].equals("NULL"))
			{
			cells[11]=DataType.getMissingCell();
			}
		else
			{
			cells[11]=new StringCell((tokens[11]));
			}
		if(tokens.length<=12 || tokens[12].equals("NULL"))
			{
			cells[12]=DataType.getMissingCell();
			}
		else
			{
			cells[12]=new StringCell((tokens[12]));
			}
		if(tokens.length<=13 || tokens[13].equals("NULL"))
			{
			cells[13]=DataType.getMissingCell();
			}
		else
			{
			cells[13]=new DoubleCell(Double.parseDouble(tokens[13]));
			}
		if(tokens.length<=14 || tokens[14].equals("NULL"))
			{
			cells[14]=DataType.getMissingCell();
			}
		else
			{
			cells[14]=new DoubleCell(Double.parseDouble(tokens[14]));
			}
		if(tokens.length<=15 || tokens[15].equals("NULL"))
			{
			cells[15]=DataType.getMissingCell();
			}
		else
			{
			cells[15]=new StringCell((tokens[15]));
			}
		if(tokens.length<=16 || tokens[16].equals("NULL"))
			{
			cells[16]=DataType.getMissingCell();
			}
		else
			{
			cells[16]=new StringCell((tokens[16]));
			}
		if(tokens.length<=17 || tokens[17].equals("NULL"))
			{
			cells[17]=DataType.getMissingCell();
			}
		else
			{
			cells[17]=new IntCell(Integer.parseInt(tokens[17]));
			}
		if(tokens.length<=18 || tokens[18].equals("NULL"))
			{
			cells[18]=DataType.getMissingCell();
			}
		else
			{
			cells[18]=new StringCell((tokens[18]));
			}
		if(tokens.length<=19 || tokens[19].equals("NULL"))
			{
			cells[19]=DataType.getMissingCell();
			}
		else
			{
			cells[19]=new IntCell(Integer.parseInt(tokens[19]));
			}
		if(tokens.length<=20 || tokens[20].equals("NULL"))
			{
			cells[20]=DataType.getMissingCell();
			}
		else
			{
			cells[20]=new StringCell((tokens[20]));
			}
		if(tokens.length<=21 || tokens[21].equals("NULL"))
			{
			cells[21]=DataType.getMissingCell();
			}
		else
			{
			cells[21]=new IntCell(Integer.parseInt(tokens[21]));
			}
		if(tokens.length<=22 || tokens[22].equals("NULL"))
			{
			cells[22]=DataType.getMissingCell();
			}
		else
			{
			cells[22]=new StringCell((tokens[22]));
			}
		if(tokens.length<=23 || tokens[23].equals("NULL"))
			{
			cells[23]=DataType.getMissingCell();
			}
		else
			{
			cells[23]=new StringCell((tokens[23]));
			}
		if(tokens.length<=24 || tokens[24].equals("NULL"))
			{
			cells[24]=DataType.getMissingCell();
			}
		else
			{
			cells[24]=new StringCell((tokens[24]));
			}
		if(tokens.length<=25 || tokens[25].equals("NULL"))
			{
			cells[25]=DataType.getMissingCell();
			}
		else
			{
			cells[25]=new StringCell((tokens[25]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.snp132Flagged")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/snp132Flagged.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[26];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new StringCell((tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new StringCell((tokens[7]));
			}
		if(tokens.length<=8 || tokens[8].equals("NULL"))
			{
			cells[8]=DataType.getMissingCell();
			}
		else
			{
			cells[8]=new StringCell((tokens[8]));
			}
		if(tokens.length<=9 || tokens[9].equals("NULL"))
			{
			cells[9]=DataType.getMissingCell();
			}
		else
			{
			cells[9]=new StringCell((tokens[9]));
			}
		if(tokens.length<=10 || tokens[10].equals("NULL"))
			{
			cells[10]=DataType.getMissingCell();
			}
		else
			{
			cells[10]=new StringCell((tokens[10]));
			}
		if(tokens.length<=11 || tokens[11].equals("NULL"))
			{
			cells[11]=DataType.getMissingCell();
			}
		else
			{
			cells[11]=new StringCell((tokens[11]));
			}
		if(tokens.length<=12 || tokens[12].equals("NULL"))
			{
			cells[12]=DataType.getMissingCell();
			}
		else
			{
			cells[12]=new StringCell((tokens[12]));
			}
		if(tokens.length<=13 || tokens[13].equals("NULL"))
			{
			cells[13]=DataType.getMissingCell();
			}
		else
			{
			cells[13]=new DoubleCell(Double.parseDouble(tokens[13]));
			}
		if(tokens.length<=14 || tokens[14].equals("NULL"))
			{
			cells[14]=DataType.getMissingCell();
			}
		else
			{
			cells[14]=new DoubleCell(Double.parseDouble(tokens[14]));
			}
		if(tokens.length<=15 || tokens[15].equals("NULL"))
			{
			cells[15]=DataType.getMissingCell();
			}
		else
			{
			cells[15]=new StringCell((tokens[15]));
			}
		if(tokens.length<=16 || tokens[16].equals("NULL"))
			{
			cells[16]=DataType.getMissingCell();
			}
		else
			{
			cells[16]=new StringCell((tokens[16]));
			}
		if(tokens.length<=17 || tokens[17].equals("NULL"))
			{
			cells[17]=DataType.getMissingCell();
			}
		else
			{
			cells[17]=new IntCell(Integer.parseInt(tokens[17]));
			}
		if(tokens.length<=18 || tokens[18].equals("NULL"))
			{
			cells[18]=DataType.getMissingCell();
			}
		else
			{
			cells[18]=new StringCell((tokens[18]));
			}
		if(tokens.length<=19 || tokens[19].equals("NULL"))
			{
			cells[19]=DataType.getMissingCell();
			}
		else
			{
			cells[19]=new IntCell(Integer.parseInt(tokens[19]));
			}
		if(tokens.length<=20 || tokens[20].equals("NULL"))
			{
			cells[20]=DataType.getMissingCell();
			}
		else
			{
			cells[20]=new StringCell((tokens[20]));
			}
		if(tokens.length<=21 || tokens[21].equals("NULL"))
			{
			cells[21]=DataType.getMissingCell();
			}
		else
			{
			cells[21]=new IntCell(Integer.parseInt(tokens[21]));
			}
		if(tokens.length<=22 || tokens[22].equals("NULL"))
			{
			cells[22]=DataType.getMissingCell();
			}
		else
			{
			cells[22]=new StringCell((tokens[22]));
			}
		if(tokens.length<=23 || tokens[23].equals("NULL"))
			{
			cells[23]=DataType.getMissingCell();
			}
		else
			{
			cells[23]=new StringCell((tokens[23]));
			}
		if(tokens.length<=24 || tokens[24].equals("NULL"))
			{
			cells[24]=DataType.getMissingCell();
			}
		else
			{
			cells[24]=new StringCell((tokens[24]));
			}
		if(tokens.length<=25 || tokens[25].equals("NULL"))
			{
			cells[25]=DataType.getMissingCell();
			}
		else
			{
			cells[25]=new StringCell((tokens[25]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.snp132Mult")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/snp132Mult.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[26];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new StringCell((tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new StringCell((tokens[7]));
			}
		if(tokens.length<=8 || tokens[8].equals("NULL"))
			{
			cells[8]=DataType.getMissingCell();
			}
		else
			{
			cells[8]=new StringCell((tokens[8]));
			}
		if(tokens.length<=9 || tokens[9].equals("NULL"))
			{
			cells[9]=DataType.getMissingCell();
			}
		else
			{
			cells[9]=new StringCell((tokens[9]));
			}
		if(tokens.length<=10 || tokens[10].equals("NULL"))
			{
			cells[10]=DataType.getMissingCell();
			}
		else
			{
			cells[10]=new StringCell((tokens[10]));
			}
		if(tokens.length<=11 || tokens[11].equals("NULL"))
			{
			cells[11]=DataType.getMissingCell();
			}
		else
			{
			cells[11]=new StringCell((tokens[11]));
			}
		if(tokens.length<=12 || tokens[12].equals("NULL"))
			{
			cells[12]=DataType.getMissingCell();
			}
		else
			{
			cells[12]=new StringCell((tokens[12]));
			}
		if(tokens.length<=13 || tokens[13].equals("NULL"))
			{
			cells[13]=DataType.getMissingCell();
			}
		else
			{
			cells[13]=new DoubleCell(Double.parseDouble(tokens[13]));
			}
		if(tokens.length<=14 || tokens[14].equals("NULL"))
			{
			cells[14]=DataType.getMissingCell();
			}
		else
			{
			cells[14]=new DoubleCell(Double.parseDouble(tokens[14]));
			}
		if(tokens.length<=15 || tokens[15].equals("NULL"))
			{
			cells[15]=DataType.getMissingCell();
			}
		else
			{
			cells[15]=new StringCell((tokens[15]));
			}
		if(tokens.length<=16 || tokens[16].equals("NULL"))
			{
			cells[16]=DataType.getMissingCell();
			}
		else
			{
			cells[16]=new StringCell((tokens[16]));
			}
		if(tokens.length<=17 || tokens[17].equals("NULL"))
			{
			cells[17]=DataType.getMissingCell();
			}
		else
			{
			cells[17]=new IntCell(Integer.parseInt(tokens[17]));
			}
		if(tokens.length<=18 || tokens[18].equals("NULL"))
			{
			cells[18]=DataType.getMissingCell();
			}
		else
			{
			cells[18]=new StringCell((tokens[18]));
			}
		if(tokens.length<=19 || tokens[19].equals("NULL"))
			{
			cells[19]=DataType.getMissingCell();
			}
		else
			{
			cells[19]=new IntCell(Integer.parseInt(tokens[19]));
			}
		if(tokens.length<=20 || tokens[20].equals("NULL"))
			{
			cells[20]=DataType.getMissingCell();
			}
		else
			{
			cells[20]=new StringCell((tokens[20]));
			}
		if(tokens.length<=21 || tokens[21].equals("NULL"))
			{
			cells[21]=DataType.getMissingCell();
			}
		else
			{
			cells[21]=new IntCell(Integer.parseInt(tokens[21]));
			}
		if(tokens.length<=22 || tokens[22].equals("NULL"))
			{
			cells[22]=DataType.getMissingCell();
			}
		else
			{
			cells[22]=new StringCell((tokens[22]));
			}
		if(tokens.length<=23 || tokens[23].equals("NULL"))
			{
			cells[23]=DataType.getMissingCell();
			}
		else
			{
			cells[23]=new StringCell((tokens[23]));
			}
		if(tokens.length<=24 || tokens[24].equals("NULL"))
			{
			cells[24]=DataType.getMissingCell();
			}
		else
			{
			cells[24]=new StringCell((tokens[24]));
			}
		if(tokens.length<=25 || tokens[25].equals("NULL"))
			{
			cells[25]=DataType.getMissingCell();
			}
		else
			{
			cells[25]=new StringCell((tokens[25]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.snp132OrthoPt2Pa2Rm2")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/snp132OrthoPt2Pa2Rm2.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[23];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new StringCell((tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new StringCell((tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new StringCell((tokens[7]));
			}
		if(tokens.length<=8 || tokens[8].equals("NULL"))
			{
			cells[8]=DataType.getMissingCell();
			}
		else
			{
			cells[8]=new StringCell((tokens[8]));
			}
		if(tokens.length<=9 || tokens[9].equals("NULL"))
			{
			cells[9]=DataType.getMissingCell();
			}
		else
			{
			cells[9]=new IntCell(Integer.parseInt(tokens[9]));
			}
		if(tokens.length<=10 || tokens[10].equals("NULL"))
			{
			cells[10]=DataType.getMissingCell();
			}
		else
			{
			cells[10]=new IntCell(Integer.parseInt(tokens[10]));
			}
		if(tokens.length<=11 || tokens[11].equals("NULL"))
			{
			cells[11]=DataType.getMissingCell();
			}
		else
			{
			cells[11]=new StringCell((tokens[11]));
			}
		if(tokens.length<=12 || tokens[12].equals("NULL"))
			{
			cells[12]=DataType.getMissingCell();
			}
		else
			{
			cells[12]=new StringCell((tokens[12]));
			}
		if(tokens.length<=13 || tokens[13].equals("NULL"))
			{
			cells[13]=DataType.getMissingCell();
			}
		else
			{
			cells[13]=new StringCell((tokens[13]));
			}
		if(tokens.length<=14 || tokens[14].equals("NULL"))
			{
			cells[14]=DataType.getMissingCell();
			}
		else
			{
			cells[14]=new IntCell(Integer.parseInt(tokens[14]));
			}
		if(tokens.length<=15 || tokens[15].equals("NULL"))
			{
			cells[15]=DataType.getMissingCell();
			}
		else
			{
			cells[15]=new IntCell(Integer.parseInt(tokens[15]));
			}
		if(tokens.length<=16 || tokens[16].equals("NULL"))
			{
			cells[16]=DataType.getMissingCell();
			}
		else
			{
			cells[16]=new StringCell((tokens[16]));
			}
		if(tokens.length<=17 || tokens[17].equals("NULL"))
			{
			cells[17]=DataType.getMissingCell();
			}
		else
			{
			cells[17]=new StringCell((tokens[17]));
			}
		if(tokens.length<=18 || tokens[18].equals("NULL"))
			{
			cells[18]=DataType.getMissingCell();
			}
		else
			{
			cells[18]=new StringCell((tokens[18]));
			}
		if(tokens.length<=19 || tokens[19].equals("NULL"))
			{
			cells[19]=DataType.getMissingCell();
			}
		else
			{
			cells[19]=new IntCell(Integer.parseInt(tokens[19]));
			}
		if(tokens.length<=20 || tokens[20].equals("NULL"))
			{
			cells[20]=DataType.getMissingCell();
			}
		else
			{
			cells[20]=new IntCell(Integer.parseInt(tokens[20]));
			}
		if(tokens.length<=21 || tokens[21].equals("NULL"))
			{
			cells[21]=DataType.getMissingCell();
			}
		else
			{
			cells[21]=new StringCell((tokens[21]));
			}
		if(tokens.length<=22 || tokens[22].equals("NULL"))
			{
			cells[22]=DataType.getMissingCell();
			}
		else
			{
			cells[22]=new StringCell((tokens[22]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.snpArrayAffy250Nsp")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/snpArrayAffy250Nsp.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[9];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new StringCell((tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new StringCell((tokens[7]));
			}
		if(tokens.length<=8 || tokens[8].equals("NULL"))
			{
			cells[8]=DataType.getMissingCell();
			}
		else
			{
			cells[8]=new StringCell((tokens[8]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.snpArrayAffy250Sty")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/snpArrayAffy250Sty.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[9];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new StringCell((tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new StringCell((tokens[7]));
			}
		if(tokens.length<=8 || tokens[8].equals("NULL"))
			{
			cells[8]=DataType.getMissingCell();
			}
		else
			{
			cells[8]=new StringCell((tokens[8]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.snpArrayAffy5")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/snpArrayAffy5.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[9];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new StringCell((tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new StringCell((tokens[7]));
			}
		if(tokens.length<=8 || tokens[8].equals("NULL"))
			{
			cells[8]=DataType.getMissingCell();
			}
		else
			{
			cells[8]=new StringCell((tokens[8]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.snpArrayAffy6")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/snpArrayAffy6.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[9];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new StringCell((tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new StringCell((tokens[7]));
			}
		if(tokens.length<=8 || tokens[8].equals("NULL"))
			{
			cells[8]=DataType.getMissingCell();
			}
		else
			{
			cells[8]=new StringCell((tokens[8]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.snpArrayAffy6SV")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/snpArrayAffy6SV.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[7];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new StringCell((tokens[6]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.snpArrayIllumina1M")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/snpArrayIllumina1M.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[8];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new StringCell((tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new StringCell((tokens[7]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.snpArrayIllumina300")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/snpArrayIllumina300.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[8];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new StringCell((tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new StringCell((tokens[7]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.snpArrayIllumina550")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/snpArrayIllumina550.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[8];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new StringCell((tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new StringCell((tokens[7]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.snpArrayIllumina650")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/snpArrayIllumina650.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[8];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new StringCell((tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new StringCell((tokens[7]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.snpArrayIlluminaHuman660W_Quad")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/snpArrayIlluminaHuman660W_Quad.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[8];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new StringCell((tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new StringCell((tokens[7]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.snpArrayIlluminaHumanCytoSNP_12")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/snpArrayIlluminaHumanCytoSNP_12.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[8];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new StringCell((tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new StringCell((tokens[7]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.snpArrayIlluminaHumanOmni1_Quad")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/snpArrayIlluminaHumanOmni1_Quad.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[8];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new StringCell((tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new StringCell((tokens[7]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.stsMap")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/stsMap.txt.gz";
		}
public int getChromColumn() { return 0;}
public int getChromStartColumn() { return 1;}
public int getChromEndColumn() { return 2;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[28];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new StringCell((tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new IntCell(Integer.parseInt(tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new StringCell((tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new IntCell(Integer.parseInt(tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new StringCell((tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new StringCell((tokens[7]));
			}
		if(tokens.length<=8 || tokens[8].equals("NULL"))
			{
			cells[8]=DataType.getMissingCell();
			}
		else
			{
			cells[8]=new StringCell((tokens[8]));
			}
		if(tokens.length<=9 || tokens[9].equals("NULL"))
			{
			cells[9]=DataType.getMissingCell();
			}
		else
			{
			cells[9]=new DoubleCell(Double.parseDouble(tokens[9]));
			}
		if(tokens.length<=10 || tokens[10].equals("NULL"))
			{
			cells[10]=DataType.getMissingCell();
			}
		else
			{
			cells[10]=new StringCell((tokens[10]));
			}
		if(tokens.length<=11 || tokens[11].equals("NULL"))
			{
			cells[11]=DataType.getMissingCell();
			}
		else
			{
			cells[11]=new DoubleCell(Double.parseDouble(tokens[11]));
			}
		if(tokens.length<=12 || tokens[12].equals("NULL"))
			{
			cells[12]=DataType.getMissingCell();
			}
		else
			{
			cells[12]=new StringCell((tokens[12]));
			}
		if(tokens.length<=13 || tokens[13].equals("NULL"))
			{
			cells[13]=DataType.getMissingCell();
			}
		else
			{
			cells[13]=new DoubleCell(Double.parseDouble(tokens[13]));
			}
		if(tokens.length<=14 || tokens[14].equals("NULL"))
			{
			cells[14]=DataType.getMissingCell();
			}
		else
			{
			cells[14]=new StringCell((tokens[14]));
			}
		if(tokens.length<=15 || tokens[15].equals("NULL"))
			{
			cells[15]=DataType.getMissingCell();
			}
		else
			{
			cells[15]=new DoubleCell(Double.parseDouble(tokens[15]));
			}
		if(tokens.length<=16 || tokens[16].equals("NULL"))
			{
			cells[16]=DataType.getMissingCell();
			}
		else
			{
			cells[16]=new StringCell((tokens[16]));
			}
		if(tokens.length<=17 || tokens[17].equals("NULL"))
			{
			cells[17]=DataType.getMissingCell();
			}
		else
			{
			cells[17]=new DoubleCell(Double.parseDouble(tokens[17]));
			}
		if(tokens.length<=18 || tokens[18].equals("NULL"))
			{
			cells[18]=DataType.getMissingCell();
			}
		else
			{
			cells[18]=new StringCell((tokens[18]));
			}
		if(tokens.length<=19 || tokens[19].equals("NULL"))
			{
			cells[19]=DataType.getMissingCell();
			}
		else
			{
			cells[19]=new DoubleCell(Double.parseDouble(tokens[19]));
			}
		if(tokens.length<=20 || tokens[20].equals("NULL"))
			{
			cells[20]=DataType.getMissingCell();
			}
		else
			{
			cells[20]=new StringCell((tokens[20]));
			}
		if(tokens.length<=21 || tokens[21].equals("NULL"))
			{
			cells[21]=DataType.getMissingCell();
			}
		else
			{
			cells[21]=new DoubleCell(Double.parseDouble(tokens[21]));
			}
		if(tokens.length<=22 || tokens[22].equals("NULL"))
			{
			cells[22]=DataType.getMissingCell();
			}
		else
			{
			cells[22]=new StringCell((tokens[22]));
			}
		if(tokens.length<=23 || tokens[23].equals("NULL"))
			{
			cells[23]=DataType.getMissingCell();
			}
		else
			{
			cells[23]=new StringCell((tokens[23]));
			}
		if(tokens.length<=24 || tokens[24].equals("NULL"))
			{
			cells[24]=DataType.getMissingCell();
			}
		else
			{
			cells[24]=new StringCell((tokens[24]));
			}
		if(tokens.length<=25 || tokens[25].equals("NULL"))
			{
			cells[25]=DataType.getMissingCell();
			}
		else
			{
			cells[25]=new StringCell((tokens[25]));
			}
		if(tokens.length<=26 || tokens[26].equals("NULL"))
			{
			cells[26]=DataType.getMissingCell();
			}
		else
			{
			cells[26]=new StringCell((tokens[26]));
			}
		if(tokens.length<=27 || tokens[27].equals("NULL"))
			{
			cells[27]=DataType.getMissingCell();
			}
		else
			{
			cells[27]=new DoubleCell(Double.parseDouble(tokens[27]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.switchDbTss")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/switchDbTss.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[12];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new StringCell((tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new DoubleCell(Double.parseDouble(tokens[7]));
			}
		if(tokens.length<=8 || tokens[8].equals("NULL"))
			{
			cells[8]=DataType.getMissingCell();
			}
		else
			{
			cells[8]=new StringCell((tokens[8]));
			}
		if(tokens.length<=9 || tokens[9].equals("NULL"))
			{
			cells[9]=DataType.getMissingCell();
			}
		else
			{
			cells[9]=new IntCell(Integer.parseInt(tokens[9]));
			}
		if(tokens.length<=10 || tokens[10].equals("NULL"))
			{
			cells[10]=DataType.getMissingCell();
			}
		else
			{
			cells[10]=new IntCell(Integer.parseInt(tokens[10]));
			}
		if(tokens.length<=11 || tokens[11].equals("NULL"))
			{
			cells[11]=DataType.getMissingCell();
			}
		else
			{
			cells[11]=new IntCell(Integer.parseInt(tokens[11]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.tRNAs")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/tRNAs.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[13];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new StringCell((tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new StringCell((tokens[7]));
			}
		if(tokens.length<=8 || tokens[8].equals("NULL"))
			{
			cells[8]=DataType.getMissingCell();
			}
		else
			{
			cells[8]=new StringCell((tokens[8]));
			}
		if(tokens.length<=9 || tokens[9].equals("NULL"))
			{
			cells[9]=DataType.getMissingCell();
			}
		else
			{
			cells[9]=new StringCell((tokens[9]));
			}
		if(tokens.length<=10 || tokens[10].equals("NULL"))
			{
			cells[10]=DataType.getMissingCell();
			}
		else
			{
			cells[10]=new DoubleCell(Double.parseDouble(tokens[10]));
			}
		if(tokens.length<=11 || tokens[11].equals("NULL"))
			{
			cells[11]=DataType.getMissingCell();
			}
		else
			{
			cells[11]=new StringCell((tokens[11]));
			}
		if(tokens.length<=12 || tokens[12].equals("NULL"))
			{
			cells[12]=DataType.getMissingCell();
			}
		else
			{
			cells[12]=new StringCell((tokens[12]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.targetScanS")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/targetScanS.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[7];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new StringCell((tokens[6]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.tfbsConsSites")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/tfbsConsSites.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[8];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new StringCell((tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new DoubleCell(Double.parseDouble(tokens[7]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.uMassBrainHistonePeaksInfant")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/uMassBrainHistonePeaksInfant.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[7];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new DoubleCell(Double.parseDouble(tokens[6]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.uMassBrainHistonePeaksNeuron")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/uMassBrainHistonePeaksNeuron.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[7];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new DoubleCell(Double.parseDouble(tokens[6]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.uMassBrainHistonePeaksSample")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/uMassBrainHistonePeaksSample.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[7];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new DoubleCell(Double.parseDouble(tokens[6]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.ucsfChipSeqH3K4me3BrainCoverage")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/ucsfChipSeqH3K4me3BrainCoverage.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[5];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new DoubleCell(Double.parseDouble(tokens[4]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.ucsfMedipSeqBrainCoverage")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/ucsfMedipSeqBrainCoverage.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[5];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new DoubleCell(Double.parseDouble(tokens[4]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.ucsfMedipSeqBrainCpG")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/ucsfMedipSeqBrainCpG.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[5];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new DoubleCell(Double.parseDouble(tokens[4]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.ucsfMedipSeqBrainReads")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/ucsfMedipSeqBrainReads.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[10];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new StringCell((tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new IntCell(Integer.parseInt(tokens[7]));
			}
		if(tokens.length<=8 || tokens[8].equals("NULL"))
			{
			cells[8]=DataType.getMissingCell();
			}
		else
			{
			cells[8]=new IntCell(Integer.parseInt(tokens[8]));
			}
		if(tokens.length<=9 || tokens[9].equals("NULL"))
			{
			cells[9]=DataType.getMissingCell();
			}
		else
			{
			cells[9]=new IntCell(Integer.parseInt(tokens[9]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.ucsfMreSeqBrainCpG")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/ucsfMreSeqBrainCpG.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[5];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new DoubleCell(Double.parseDouble(tokens[4]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.ucsfMreSeqBrainReads")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/ucsfMreSeqBrainReads.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[10];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new StringCell((tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new IntCell(Integer.parseInt(tokens[7]));
			}
		if(tokens.length<=8 || tokens[8].equals("NULL"))
			{
			cells[8]=DataType.getMissingCell();
			}
		else
			{
			cells[8]=new IntCell(Integer.parseInt(tokens[8]));
			}
		if(tokens.length<=9 || tokens[9].equals("NULL"))
			{
			cells[9]=DataType.getMissingCell();
			}
		else
			{
			cells[9]=new IntCell(Integer.parseInt(tokens[9]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.ucsfRnaSeqBrainAllCoverage")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/ucsfRnaSeqBrainAllCoverage.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[5];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new DoubleCell(Double.parseDouble(tokens[4]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.ucsfRnaSeqBrainAllReads")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/ucsfRnaSeqBrainAllReads.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[10];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new StringCell((tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new IntCell(Integer.parseInt(tokens[7]));
			}
		if(tokens.length<=8 || tokens[8].equals("NULL"))
			{
			cells[8]=DataType.getMissingCell();
			}
		else
			{
			cells[8]=new IntCell(Integer.parseInt(tokens[8]));
			}
		if(tokens.length<=9 || tokens[9].equals("NULL"))
			{
			cells[9]=DataType.getMissingCell();
			}
		else
			{
			cells[9]=new IntCell(Integer.parseInt(tokens[9]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.ucsfRnaSeqBrainSmartCoverage")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/ucsfRnaSeqBrainSmartCoverage.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[5];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new DoubleCell(Double.parseDouble(tokens[4]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.ucsfRnaSeqBrainSmartReads")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/ucsfRnaSeqBrainSmartReads.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[10];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new StringCell((tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new IntCell(Integer.parseInt(tokens[7]));
			}
		if(tokens.length<=8 || tokens[8].equals("NULL"))
			{
			cells[8]=DataType.getMissingCell();
			}
		else
			{
			cells[8]=new IntCell(Integer.parseInt(tokens[8]));
			}
		if(tokens.length<=9 || tokens[9].equals("NULL"))
			{
			cells[9]=DataType.getMissingCell();
			}
		else
			{
			cells[9]=new IntCell(Integer.parseInt(tokens[9]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.vegaGene")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/vegaGene.txt.gz";
		}
public int getChromColumn() { return 2;}
public int getChromStartColumn() { return 4;}
public int getChromEndColumn() { return 5;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[16];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new StringCell((tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new StringCell((tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new IntCell(Integer.parseInt(tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new IntCell(Integer.parseInt(tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new IntCell(Integer.parseInt(tokens[7]));
			}
		if(tokens.length<=8 || tokens[8].equals("NULL"))
			{
			cells[8]=DataType.getMissingCell();
			}
		else
			{
			cells[8]=new IntCell(Integer.parseInt(tokens[8]));
			}
		if(tokens.length<=9 || tokens[9].equals("NULL"))
			{
			cells[9]=DataType.getMissingCell();
			}
		else
			{
			cells[9]=new StringCell((tokens[9]));
			}
		if(tokens.length<=10 || tokens[10].equals("NULL"))
			{
			cells[10]=DataType.getMissingCell();
			}
		else
			{
			cells[10]=new StringCell((tokens[10]));
			}
		if(tokens.length<=11 || tokens[11].equals("NULL"))
			{
			cells[11]=DataType.getMissingCell();
			}
		else
			{
			cells[11]=new IntCell(Integer.parseInt(tokens[11]));
			}
		if(tokens.length<=12 || tokens[12].equals("NULL"))
			{
			cells[12]=DataType.getMissingCell();
			}
		else
			{
			cells[12]=new StringCell((tokens[12]));
			}
		if(tokens.length<=13 || tokens[13].equals("NULL"))
			{
			cells[13]=DataType.getMissingCell();
			}
		else
			{
			cells[13]=new StringCell((tokens[13]));
			}
		if(tokens.length<=14 || tokens[14].equals("NULL"))
			{
			cells[14]=DataType.getMissingCell();
			}
		else
			{
			cells[14]=new StringCell((tokens[14]));
			}
		if(tokens.length<=15 || tokens[15].equals("NULL"))
			{
			cells[15]=DataType.getMissingCell();
			}
		else
			{
			cells[15]=new StringCell((tokens[15]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.vegaPseudoGene")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/vegaPseudoGene.txt.gz";
		}
public int getChromColumn() { return 2;}
public int getChromStartColumn() { return 4;}
public int getChromEndColumn() { return 5;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[16];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new StringCell((tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new StringCell((tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new IntCell(Integer.parseInt(tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new IntCell(Integer.parseInt(tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new IntCell(Integer.parseInt(tokens[7]));
			}
		if(tokens.length<=8 || tokens[8].equals("NULL"))
			{
			cells[8]=DataType.getMissingCell();
			}
		else
			{
			cells[8]=new IntCell(Integer.parseInt(tokens[8]));
			}
		if(tokens.length<=9 || tokens[9].equals("NULL"))
			{
			cells[9]=DataType.getMissingCell();
			}
		else
			{
			cells[9]=new StringCell((tokens[9]));
			}
		if(tokens.length<=10 || tokens[10].equals("NULL"))
			{
			cells[10]=DataType.getMissingCell();
			}
		else
			{
			cells[10]=new StringCell((tokens[10]));
			}
		if(tokens.length<=11 || tokens[11].equals("NULL"))
			{
			cells[11]=DataType.getMissingCell();
			}
		else
			{
			cells[11]=new IntCell(Integer.parseInt(tokens[11]));
			}
		if(tokens.length<=12 || tokens[12].equals("NULL"))
			{
			cells[12]=DataType.getMissingCell();
			}
		else
			{
			cells[12]=new StringCell((tokens[12]));
			}
		if(tokens.length<=13 || tokens[13].equals("NULL"))
			{
			cells[13]=DataType.getMissingCell();
			}
		else
			{
			cells[13]=new StringCell((tokens[13]));
			}
		if(tokens.length<=14 || tokens[14].equals("NULL"))
			{
			cells[14]=DataType.getMissingCell();
			}
		else
			{
			cells[14]=new StringCell((tokens[14]));
			}
		if(tokens.length<=15 || tokens[15].equals("NULL"))
			{
			cells[15]=DataType.getMissingCell();
			}
		else
			{
			cells[15]=new StringCell((tokens[15]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.vistaEnhancers")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/vistaEnhancers.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[6];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.wgRna")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/wgRna.txt.gz";
		}
public int getChromColumn() { return 1;}
public int getChromStartColumn() { return 2;}
public int getChromEndColumn() { return 3;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[10];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new IntCell(Integer.parseInt(tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new IntCell(Integer.parseInt(tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new StringCell((tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new StringCell((tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new IntCell(Integer.parseInt(tokens[7]));
			}
		if(tokens.length<=8 || tokens[8].equals("NULL"))
			{
			cells[8]=DataType.getMissingCell();
			}
		else
			{
			cells[8]=new IntCell(Integer.parseInt(tokens[8]));
			}
		if(tokens.length<=9 || tokens[9].equals("NULL"))
			{
			cells[9]=DataType.getMissingCell();
			}
		else
			{
			cells[9]=new StringCell((tokens[9]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.xenoRefFlat")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/xenoRefFlat.txt.gz";
		}
public int getChromColumn() { return 2;}
public int getChromStartColumn() { return 4;}
public int getChromEndColumn() { return 5;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[11];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new StringCell((tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new StringCell((tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new StringCell((tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new IntCell(Integer.parseInt(tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new IntCell(Integer.parseInt(tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new IntCell(Integer.parseInt(tokens[7]));
			}
		if(tokens.length<=8 || tokens[8].equals("NULL"))
			{
			cells[8]=DataType.getMissingCell();
			}
		else
			{
			cells[8]=new IntCell(Integer.parseInt(tokens[8]));
			}
		if(tokens.length<=9 || tokens[9].equals("NULL"))
			{
			cells[9]=DataType.getMissingCell();
			}
		else
			{
			cells[9]=new StringCell((tokens[9]));
			}
		if(tokens.length<=10 || tokens[10].equals("NULL"))
			{
			cells[10]=DataType.getMissingCell();
			}
		else
			{
			cells[10]=new StringCell((tokens[10]));
			}
		return cells;
		}
	}
;
if(id.equals("hg19.xenoRefGene")) return 
new UcscDatabaseHandler()
	{
	public String getUrl()
		{
		return "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/xenoRefGene.txt.gz";
		}
public int getChromColumn() { return 2;}
public int getChromStartColumn() { return 4;}
public int getChromEndColumn() { return 5;}
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
	public DataCell[] parse(String tokens[])
		{
DataCell cells[]=new DataCell[16];
		if(tokens.length<=0 || tokens[0].equals("NULL"))
			{
			cells[0]=DataType.getMissingCell();
			}
		else
			{
			cells[0]=new IntCell(Integer.parseInt(tokens[0]));
			}
		if(tokens.length<=1 || tokens[1].equals("NULL"))
			{
			cells[1]=DataType.getMissingCell();
			}
		else
			{
			cells[1]=new StringCell((tokens[1]));
			}
		if(tokens.length<=2 || tokens[2].equals("NULL"))
			{
			cells[2]=DataType.getMissingCell();
			}
		else
			{
			cells[2]=new StringCell((tokens[2]));
			}
		if(tokens.length<=3 || tokens[3].equals("NULL"))
			{
			cells[3]=DataType.getMissingCell();
			}
		else
			{
			cells[3]=new StringCell((tokens[3]));
			}
		if(tokens.length<=4 || tokens[4].equals("NULL"))
			{
			cells[4]=DataType.getMissingCell();
			}
		else
			{
			cells[4]=new IntCell(Integer.parseInt(tokens[4]));
			}
		if(tokens.length<=5 || tokens[5].equals("NULL"))
			{
			cells[5]=DataType.getMissingCell();
			}
		else
			{
			cells[5]=new IntCell(Integer.parseInt(tokens[5]));
			}
		if(tokens.length<=6 || tokens[6].equals("NULL"))
			{
			cells[6]=DataType.getMissingCell();
			}
		else
			{
			cells[6]=new IntCell(Integer.parseInt(tokens[6]));
			}
		if(tokens.length<=7 || tokens[7].equals("NULL"))
			{
			cells[7]=DataType.getMissingCell();
			}
		else
			{
			cells[7]=new IntCell(Integer.parseInt(tokens[7]));
			}
		if(tokens.length<=8 || tokens[8].equals("NULL"))
			{
			cells[8]=DataType.getMissingCell();
			}
		else
			{
			cells[8]=new IntCell(Integer.parseInt(tokens[8]));
			}
		if(tokens.length<=9 || tokens[9].equals("NULL"))
			{
			cells[9]=DataType.getMissingCell();
			}
		else
			{
			cells[9]=new StringCell((tokens[9]));
			}
		if(tokens.length<=10 || tokens[10].equals("NULL"))
			{
			cells[10]=DataType.getMissingCell();
			}
		else
			{
			cells[10]=new StringCell((tokens[10]));
			}
		if(tokens.length<=11 || tokens[11].equals("NULL"))
			{
			cells[11]=DataType.getMissingCell();
			}
		else
			{
			cells[11]=new IntCell(Integer.parseInt(tokens[11]));
			}
		if(tokens.length<=12 || tokens[12].equals("NULL"))
			{
			cells[12]=DataType.getMissingCell();
			}
		else
			{
			cells[12]=new StringCell((tokens[12]));
			}
		if(tokens.length<=13 || tokens[13].equals("NULL"))
			{
			cells[13]=DataType.getMissingCell();
			}
		else
			{
			cells[13]=new StringCell((tokens[13]));
			}
		if(tokens.length<=14 || tokens[14].equals("NULL"))
			{
			cells[14]=DataType.getMissingCell();
			}
		else
			{
			cells[14]=new StringCell((tokens[14]));
			}
		if(tokens.length<=15 || tokens[15].equals("NULL"))
			{
			cells[15]=DataType.getMissingCell();
			}
		else
			{
			cells[15]=new StringCell((tokens[15]));
			}
		return cells;
		}
	}
;
		return null;
		}
	}

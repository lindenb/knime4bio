package fr.inserm.umr915.knime4ngs.nodes.ncbi;

public class EInfo {
	private final static String ALL_NCBI_DB[]={
		"pubmed",
		"protein",
		"nuccore",
		"nucleotide",
		"nucgss",
		"nucest",
		"structure",
		"genome",
		"genomeprj",
		"bioproject",
		"biosample",
		"biosystems",
		//"blastdbinfo",
		"books",
		"cancerchromosomes",
		"cdd",
		"gap",
		"dbvar",
		"epigenomics",
		"gene",
		"gensat",
		"gds",
		"geo",
		"geoprofiles",
		"homologene",
		"journals",
		"mesh",
		"ncbisearch",
		"nlmcatalog",
		"omia",
		"omim",
		"pmc",
		"popset",
		"probe",
		"proteinclusters",
		"pcassay",
		"pccompound",
		"pcsubstance",
		"pubmedhealth",
		"seqannot",
		"snp",
		"sra",
		"taxonomy",
		//"toolkit",
		//"toolkitall",
		"unigene",
		"unists",
		"gencoll"
		};
	
	public static String[] getDatabases()
		{
		return ALL_NCBI_DB;
		}
}

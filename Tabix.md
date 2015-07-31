Tabix is a tool indexing a TAB-delimited file compressed with bgzip (an extension of the standard gzip format) containing some annotated genome positions. After indexing, the tabix node is able to quickly retrieve data lines overlapping a genomic region. A node can use a file indexed with tabix to find the genomic features overlapping a given SNP.

This node joins a file indexed with TABIX (http://samtools.sourceforge.net/tabix.shtml[http://samtools.sourceforge.net/tabix.shtml]).

# Parameters #


  * **CHROM** : The CHROM column in the VCF
  * **POS** : The POS column in the VCF
  * **Tabix File** : The file indexed with tabix
  * **Prefix** : Column prefix for the tabix columns

# Input #


  * **In-Port 1** : VCF In


# Output #


  * **out** : VCF Out
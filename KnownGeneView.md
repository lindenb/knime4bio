This node displays the gene Context using UCSC known Genes.

# Parameters #


  * **jdbc.uri** : JDBC uri. For the ucsc data it is: jdbc:mysql://genome-mysql.cse.ucsc.edu/hg19
  * **jdbc.user** : JDBC user. For the ucsc data it is: "genome"
  * **jdbc.password** : JDBC password. For the ucsc data it is empty
  * **CHROM** : The VCF CHROM column
  * **POS** : The VCF POS column. The genomic position (1-based)

# Input #

  * **input1** : VCF
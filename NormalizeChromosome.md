This node does its best to normalize a human chromosome name. For example, '01' would be changed to 'chr1', '23' to 'chrX', etc..

# Parameters #


  * **CHROM** : The CHROM column
  * **append 'chr'** : If checked, it tries to convert the chromosome name to a chromosome name for the UCSC data (with a 'chr' prefix). If unckecked, it tries to use the nomenclature for Ensembl

# Input #


  * **In-Port 1** : Input


# Output #


  * **out** : Output
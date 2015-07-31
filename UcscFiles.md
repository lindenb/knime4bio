This node appends positional data from UCSC tables from the flat files
stored  under
http://hgdownload.cse.ucsc.edu/goldenPath/[http://hgdownload.cse.ucsc.edu/goldenPath/].

Requirement: file containing the data must still exists since this node was compiled .
This file should be tab delimited and sorted on chrom/chromStart.

# Parameters #


  * **CHROM** : Chromosome Column
  * **POS** : Position column (first base is 1)
  * **table** : The UCSC table to be parsed

# Input #


  * **In-Port 1** : VCF

# Output #

  * **Accepted** : VCF
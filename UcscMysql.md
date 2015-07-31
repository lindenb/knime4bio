This node appends positional data from UCSC tables from the MYSQL table
stored in a UCSC database .

Requirement: the table containing the data should exists.

# Parameters #


  * **jdbc.uri** : JDBC uri. For the ucsc data it is: jdbc:mysql://genome-mysql.cse.ucsc.edu/hg19
  * **jdbc.user** : JDBC user. For the ucsc data it is: "genome"
  * **jdbc.password** : JDBC password. For the ucsc data it is empty
  * **CHROM** : Chromosome Column
  * **POS** : Position column
  * **One based** : First base has index= +1
  * **table** : The UCSC table to be parsed

# Input #


  * **In-Port 1** : VCF


# Output #


  * **Accepted** : Output
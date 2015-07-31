Variant effect predictor  using the  **UCSC KnownGene**  table.
The mysql database  **MUST**  contain a copy of the  **UCSC KnownGene**  table. (eg: http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/knownGene.txt.gz[http://hgdownload.cse.ucsc.edu/goldenPath/hg19])
# Parameters #


  * **jdbc.uri** : JDBC uri. For the ucsc mysql sever it is:
```
jdbc:mysql://genome-mysql.cse.ucsc.edu/hg19
```

  * **jdbc.user** : JDBC user. For the ucsc mysql sever it is: "genome"
  * **jdbc.password** : JDBC password. For the ucsc data it is empty
  * **CHROM** : chromosome column
  * **POS** : Position column (first base is 1)
  * **REF** : REF column
  * **ALT** : ALT column
  * **genome reference** : Full path to reference genome indexed with samtools
  * **mRNA** : add mRNA sequence
  * **protein** : add protein sequence

# Input #


  * **In1** : VCF


# Output #


  * **Out** : Annotated VCF
This node gets the Intersection of the table with a  **Sorted (CHROM/START/END)**  BEDhttp://genome.ucsc.edu/FAQ/FAQformat.html#format1  **file** .

# Parameters #


  * **CHROM** : VCF CHROM
  * **POS** : VCF position
  * **File URI** : BED file URI. It can be gzipped or BGZipped . Data must be sorted on CHROM/START/END
  * **bed chrom** : Chromosome column index in the BED file (first index is 1)
  * **bed start** : Start column index in the BED file (first index is 1)
  * **bed end** : End column index in the BED file (first index is 1)
  * **delimiter** : Regular expression for the BED column delimiter
  * **prefix** : Output column prefix
  * **stop after 1st hit** : only use one hit per VCF row

# Input #

  * **In-Port 1** : VCF

  * **In-Port 2** : BED

# Output #


  * **Accepted** : Merged
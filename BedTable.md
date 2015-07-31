BED ∩ VCF: Computes the intersection between a BED table and a VCF table.
Both tables must be sorted on chromosome/position.
NULL values will be appended to the Unmatched VCF rows.

# Parameters #


  * **CHROM** : VCF input. Chromosome column
  * **POS** : VCF input. POS column (first base index=1)
  * **chrom** : BED input. Chromosome column
  * **chromStart** : BED input. chromStart column (first base index=0)
  * **chromEnd** : BED input. chromEnd column (first base index=0)

# Input #


  * **In-Port 1** : VCF

  * **In-Port 2** : BED


# Output #


  * **Accepted** : Accepted VCF1
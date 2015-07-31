BED ∩ BED

# Motivation #

Computes the intersection between two BED tables

Both tables must be sorted on chromosome/start


# Parameters #


  * **chrom1** : BED input 1. Chromosome column
  * **chrom1Start** : BED input 1. chromStart column (first base index=0)
  * **chrom1End** : BED input 1. chromEnd column (first base index=0)
  * **chrom2** : BED input 2. Chromosome column
  * **chrom2Start** : BED input 2. chromStart column (first base index=0)
  * **chrom2End** : BED input 2. chromEnd column (first base index=0)
  * **overlap type** : Type of overlap (both tables, only 1st table, no overlap in first table)
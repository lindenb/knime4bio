# Motivation #

This node saves a table as BED  filehttp://genome.ucsc.edu/FAQ/FAQformat.html#format1.

# Parameters #


  * **CHROM** : The chromosome column
  * **POS** : The position start column
  * **end** : The position end column (optional)
  * **first base is 1** : Index of first base is  **1** , not  **0**
  * **Name** : The column to be used as a name in the BED file (optional). You could use a column merger to create a composite name from several columns.
  * **filename** : Output filename

# Input #

  * **In-Port name** : VCF
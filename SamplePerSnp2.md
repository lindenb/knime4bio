This node gets the number of Samples having a given SNV.
It adds an integer column for the number of columns per snv.
This node only considers the positions (CHROM/POS), but not REF and ALT.
Rows must be sorted by chom/pos/sample
This node counts the number of times a variant (defined as a genomic position and an alternative allele) was found among all the sequenced samples.
Those mutations shared between several unrelated samples, are insightly either a common variation between the sample, or an error in the reference sequence.

# Parameters #

  * **CHROM** : The CHROM column
  * **POS** : The POS column
  * **Sample** : The Sample column

# Input #

  * **input** : input


# Output #

  * **Output** : Output
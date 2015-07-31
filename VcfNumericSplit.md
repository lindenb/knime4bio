Numeric VCF split

# Motivation #

discard data per SNPs on a numeric value

# Solution #



For a given observed numerical column and for all the samples of a given SNP, will accept or discard a
set of  chom/pos/ref/alt/sample if all the observed value
are contained in the specified range of data.


Rows  **must**  be sorted by chom/pos/ref/alt/sample.


# Parameters #


  * **CHROM** : The CHROM column
  * **POS** : The POS column
  * **REF** : The REF column
  * **ALT** : The ALT column
  * **use Ref/Alt** : Use Ref/Alt to group the data for the same SNP
  * **Observed** : The observed column
  * **min** : min observed value
  * **max** : max observed value
  * **invert** : invert result

# Input #


  * **Sorted VCF** : A sorted VCF-like table (chom/pos/ref/alt/sample)


# Output #


  * **VCF** : Filtered data
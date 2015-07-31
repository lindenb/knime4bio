you want to remove one or more chromosomes from a VCF file.
## Solution ##
Create a row filter:
  * select exclude rows by attribute value
  * select the column '#CHROM'
  * set 'regular expression'
  * set the pattern as, for example `chr(X|Y|M)` (the word 'chr' followed by ('X' or 'Y' or 'M') )
![https://knime4bio.googlecode.com/svn/wiki/ExludingChromosomes.png](https://knime4bio.googlecode.com/svn/wiki/ExludingChromosomes.png)
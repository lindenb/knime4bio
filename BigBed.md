Joins data with a BIG Bed http://genome.ucsc.edu/FAQ/FAQformat.html#format1.5/Wig http://genome.ucsc.edu/FAQ/FAQformat.html#format6.1 file.

This node uses the BigWig java API: http://code.google.com/p/bigwig/

# Parameters #


  * **CHROM** : The VCF CHROM column
  * **POS** : The VCF POS column (first base is '1')
  * **Wig URI** : URI of the WIG https://cgwb.nci.nih.gov/goldenPath/help/wiggle.html ; Eitheir fixed or variable intervals.
  * **Column-name** : New Column name

# Input #


  * **In-Port 1** : Input


# Output #


  * **out** : Output
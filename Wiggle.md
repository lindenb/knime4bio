Joins a sorted WIGhttp://genome.ucsc.edu/FAQ/FAQformat.html#format6 file.

# Parameters #

  * **CHROM** : The CHROM column
  * **POS** : The POS column (first base is '1')
  * **Wig URI** : URI of the WIGhttps://cgwb.nci.nih.gov/goldenPath/help/wiggle.html (possibly gzipped or bgzipped); Eitheir fixed or variable intervals.
  * **Column-name** : New Column name
  * **Use step** : Use start+'wig.step' instead of start+'wig.span' to get the end of a genomic region.

# Input #


  * **In-Port 1** : Input


# Output #


  * **out** : Output
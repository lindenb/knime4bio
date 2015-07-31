Reads one or more VCF File.


# Solution #

Reads one or more VCFhttp://www.1000genomes.org/node/101 File associated to a sample name.
It takes as an input a table containing a SAMPLE name and a full file path/URL to a VCF file (possibly gzipped ,or bgzipped).
It currently only supports one sample per VCF.

# Parameters #


  * **quality** : Ignore VCF data with a quality lower than this value
  * **SAMPLE** : the SAMPLE column
  * **VCF URI** : the  **absolute**  path-to-vcf column

# Input #


  * **Input** : A table containing at least two columns. A column contains a sample name and another column is the  **absolute path**  to a VCF file (which can be g-zipped if needed)


# Output #


  * **VCF** : VCF data and the sample name

  * **Headers** : VCF Headers
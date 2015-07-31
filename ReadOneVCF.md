Read ONE VCF

# Solution #

Reads  **one**  VCF http://www.1000genomes.org/node/101 File and associate it to an optional SAMPLE name.
It currently only supports one sample/call per VCF.

![https://knime4bio.googlecode.com/svn/wiki/ReadOneVCF01.png](https://knime4bio.googlecode.com/svn/wiki/ReadOneVCF01.png)

You can group several of those nodes using a - Cat - node.

## See also ##

  * **Read VCF files** ( ReadingVCF ).

# Parameters #

  * **quality** : Ignore VCF data with a quality lower than this value
  * **SAMPLE** : the SAMPLE name (if not defined, the header of the 10th column will be used)
  * **VCF file** : vcf file. Valid extensions are: vcf, vcf.gz, vcf.bgz

# Output #

  * **VCF/Sample** : VCF data and the sample name
  * **VCF Headers/Sample** : VCF Headers
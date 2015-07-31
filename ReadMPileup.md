# Motivation #

Reads one samtools MPileup VCF File.


# Solution #

Reads  **one**  VCFhttp://www.1000genomes.org/node/101 File containing one or more sample.


if a GT (genotype) field is found in the FORMAT column and GT=0/0 then the
column for this sample is set to NULL.


An extra column "samples.per.snp" is also added.




# Parameters #


  * **quality** : Ignore VCF data with a quality lower than this value
  * **VCF file** : vcf file. Valid extensions are: vcf, vcf.gz, vcf.bgz

# Output #


  * **VCF/Sample** : VCF data

  * **VCF Headers/Sample** : VCF Headers
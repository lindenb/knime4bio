This node groups the mutations by  **gene** . Displaying the number of
samples  affected, the number of distinct mutation and the number of
SNVs found in each sample. From this kind of table:

```

#CHROM  POS   REF   ALT   GENE   SAMPLE
chr1    1     A     T     Gene1  Sample1
chr1    1     A     T     Gene1  Sample2
chr1    2     G     C     Gene1  Sample2
chr2    3     A     T     Gene2  Sample2

```

It generates the following pivot table:

```
#Gene   CHROM   Sample1  Sample2
Gene1   chr1    1        2
Gene2   chr2    0        1
```


# Parameters #


  * **CHROM** : VCF Chrom Column
  * **POS** : VCF POS Column
  * **REF** : VCF REF Column
  * **ALT** : VCF ALT Column
  * **Sample** : Sample Name Column
  * **Gene** : Gene Identifier Column

# Input #


  * **In-Port name** : input


# Output #


  * **Accepted** : output
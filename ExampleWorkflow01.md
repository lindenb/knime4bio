VCF data look like this:
```
#CHROM	POS	ID	REF	ALT	QUAL	FILTER	INFO	FORMAT	Brs8
X	80238	rs374	A	G	48	0	AC=2;DB=1;ST=0:0,3:4;DP=7;NC=-3.73;UM=3;CQ=INTRONIC;MQ=60;AN=2;PA=11:0.930&21:0.860&31:0.950;MZ=0;GN=NOC2L;PS=1	GT:GQ:DP:FLT	1/1:48:7:0
X	81627	rs2757	G	A	78	0	AC=2;DB=1;ST=0:0,10:7;DP=17;NC=2.43;UM=3;CQ=SYNONYMOUS_CODING;MQ=57;AN=2;PA=11:0.720&21:0.000&31:0.640;MZ=0;GN=NOC2L;PS=1,2	GT:GQ:DP:FLT	1/1:78:17:0
```

  * [Install](Install.md) KNIME
  * create a tab delimited file containing the Sample name and the full path to the VCF (gzipped)

```
Sample    Path
Sample1   /full/pathSample1.vcf.gz
Sample2   /full/pathSample2.vcf.gz
Sample2   /full/pathSample2.vcf.gz
```

  * read this file with a node: **/IO/Read/FileReader**

  * read Row Id: yes

  * read column header yes

  * Let's ignore chromosomes X, Y and MT : Add a "Row Filter"

  * "exclude rows"

  * use regular expression

  * column "CHROM"

  * pattern :`(X|Y|M)`

  * ignore SNP with a known rs-id: Add a "Row Filter"

  * "exclude rows"

  * use regular expression

  * column "ID"

  * pattern:  rs[0-9]+

  * normalize the chromosome name (e.g: chr01 => chr1, 23 => chrX , etc.. )  with a node NGS/Chrom normalization. Useful if you need to join the data with the UCSC

  * Filter on Depth: Extract the field **DP** as an integer with a node `NGS/Extract INFO`. Then, filter with a "numeric row filter" on the new DP column.

  * Filter on Ensembl Variant Prediction tool; Extract the field **CQ** as an String with a node `NGS/Extract INFO`. Then, filter with a node "NGS/Ensembl CQ.

  * for the remaining steps, the nodes must be sorted on CHROM/POS/REF/ALT/SAMPLE using a "Sort" node.

  * remove the duplicate data (CHROM/POS/REF/ALT/SAMPLE) if any (e.g. Dindel). Use a node: "Unix/Uniq" on `CHROM/POS/REF/ALT/SAMPLE`

  * Filter on the number of samples per SNP. The node `NGS/ SNP per Sample` will create a new column **sample.per.snp** that will be filtered by a **Numeric row splitter**.

  * extract the gene-name with a  `NGS/Extract INFO` and the tag **GN** (result is a String).
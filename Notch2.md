The following workflow was used to analyses the data of http://www.ncbi.nlm.nih.gov/pubmed/21378989
```
Truncating mutations in the last exon of NOTCH2 cause a rare skeletal disorder with osteoporosis.
Isidor B, Lindenbaum P, Pichon O, Bézieau S, Dina C, Jacquemont S, Martin-Coignard D, Thauvin-Robinet C, Le Merrer M, Mandel JL, David A, Faivre L, Cormier-Daire V, Redon R, Le Caignec C.
Nat Genet. 2011 Mar 6;43(4):306-8.
```

![https://knime4bio.googlecode.com/svn/wiki/Notch2a.png](https://knime4bio.googlecode.com/svn/wiki/Notch2a.png)
(use right-click/View image, to see a larger picture)

Exomes of six patients affected by the Hajdu Cheney syndrome.

The short reads were mapped to the Human genome using bwa 0.5.9  and the variants were called with samtools mpileup 0.1.17.
Homozygous variants, known SNPs (from dbSNP), and poor-quality variants were discarded.
In 45 minutes, our Knime pipeline generated a list of 6 genes: CELSR1, COL4A2, MAGEF1, MYO15A, ZNF341 and  more importantly NOTCH2 the expected candidate gene.

![http://www.myexperiment.org/images/logo.png](http://www.myexperiment.org/images/logo.png)

This workflow is available on myexperiment.org at: http://www.myexperiment.org/workflows/2320.html

or from the download area: http://code.google.com/p/knime4bio/downloads/detail?name=NOTCH2.zip&can=2&q=#makechanges
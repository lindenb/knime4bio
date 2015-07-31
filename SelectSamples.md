This node select the Samples using a sql-like query

Rows  **must**  be sorted by chom/pos/ref/alt/sample.


**AND nodes** :
```
(A and B && C and D)
```


**OR nodes** :
```
(A OR B || C or D)
```


**NOT nodes** :
```
!(A)
```


**Samples nodes** :
```
Sample1
```


**Example** :
```
(Sample_1 and "Sample 3" and not("Sample 4" or "Sample2"))
```


# Parameters #


  * **CHROM** : The CHROM column
  * **POS** : The POS column
  * **REF** : The REF column
  * **ALT** : The ALT column
  * **Sample** : The Sample column
  * **Query** : The Query
  * **USe REF/ALT** : Should we use the REF and ALT column to answer if two mutations are the same ?

# Input #


  * **Sorted VCF** : A sorted VCF-like table (chom/pos/ref/alt/sample)


# Output #


  * **VCF** : The rows matching the query
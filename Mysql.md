This node applies a SQL Query to every row of the input table.

## Example ##

Example1:

```
select chrom,chromStart from hg19.snp132 where name= ?ID limit 1
```

Example2:

```
select name from hg19.snp132 where chrom= ?CHROM and chromStart <=( ?POS -1 ) and ( ?POS -1 ) < chromEnd limit 1 
```


See also Biostar: What are the public SQL servers for bioinformatics ?http://biostar.stackexchange.com/questions/474.


# Parameters #


  * **Query: the SQL query** :
> > The SQL query. Words starting with  **?columnName**  will be replace by the content of the table. Don't put a semi colon at the end of the query.
> > Example:
```
Select chrom as C,chromStart as S,chromEnd as E from hg19.snp132 where name= ?SNP
```
.

  * **jdbc.uri** : JDBC uri. For the ucsc data it is: jdbc:mysql://genome-mysql.cse.ucsc.edu/hg19
  * **jdbc.user** : JDBC user. For the ucsc data it is: "genome"
  * **jdbc.password** : JDBC password. For the ucsc data it is empty
  * **only one** : Stop searching after first match.

# Input #


  * **In-Port name** : Input


# Output #


  * **Out-Port name** : Output1
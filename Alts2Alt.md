ALTS to ALT: convert the comma separated alleles in ALT to some new rows

# Solution #



Convert the comma separated alleles in ALT to some new rows.


For example the following ALT cells:

```
ALT
A,T
G,C
```


will be converted to

```
ALT  ALT.src
A    A,T
T    A,T
G    G,C
C    G,C
```




# Parameters #

  * **ALT** : The ALT column

# Input #


  * **Table with ALTS** : A table containing a ALT column


# Output #


  * **Table with ALT+ALT.src** : The new table
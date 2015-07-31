This node extracts a key/value from a FORMAT and a Call column in a VCF
file. Example:

```
GT:PL:GQ	1/1:37,3,0:4
```


# Parameters #


  * **FORMAT** : the FORMAT column in the VCF
  * **CALL** : the CALL column in the VCF
  * **DataType** : The type of data to be extracted (int, double, boolean, etc... )
  * **Flag** : the name of the flag to be extracted (many separated by a comma or a whitespace)

# Input #


  * **In-Port 1** : VCF


# Output #


  * **out** : VCF1
# Motivation #

Extracts a value from a INFO column in a VCF file

# Solution #

Extracts a key/value from a INFO column in a VCF file. Example of info field:

```
AC=2;DB=1;ST=0:0,3:4;DP=7;NC=-3.73;UM=3;CQ=INTRONIC;MQ=60;AN=2;PA=11:0.930&21:0.860&31:0.950;MZ=0;GN=NOC2L;PS=1
```


# Parameters #


  * **INFO** : the INFO column in the VCF
  * **DataType** : The type of data to be extracted (int, double, boolean, etc... )
  * **Flag** : the name of the flag to be extracted. e.g: " **DP** "

# Input #


  * **In-Port 1** : VCF


# Output #


  * **out** : VCF1
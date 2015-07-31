# Motivation #
excluding SNP having a rs## in dbSNP

# Solution #

Create a new 'Row Filter' using a regular expression

```
rs[0-9]+
```
means the words 'rs' followed by one or more digits:


![https://knime4bio.googlecode.com/svn/wiki/FilterRs.png](https://knime4bio.googlecode.com/svn/wiki/FilterRs.png)
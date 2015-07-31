We want to know whether a mutation is contained within the functional region of a protain

# Solution #

Use a 'Uniprot' Node

## Source table ##



The columns: the chromosme, the position and the position of the mutation in the protein

![https://knime4bio.googlecode.com/svn/wiki/Uniprot01.png](https://knime4bio.googlecode.com/svn/wiki/Uniprot01.png)

we add a UCSC SQL node to find the knownGenes overlapping that mutation:

![https://knime4bio.googlecode.com/svn/wiki/Uniprot02.png](https://knime4bio.googlecode.com/svn/wiki/Uniprot02.png)

as the 'position in the peptide' is a string, it is converted to int.

![https://knime4bio.googlecode.com/svn/wiki/Uniprot03.png](https://knime4bio.googlecode.com/svn/wiki/Uniprot03.png)

We then use the Uniprot node

![https://knime4bio.googlecode.com/svn/wiki/Uniprot04.png](https://knime4bio.googlecode.com/svn/wiki/Uniprot04.png)

![https://knime4bio.googlecode.com/svn/wiki/Uniprot05.png](https://knime4bio.googlecode.com/svn/wiki/Uniprot05.png)
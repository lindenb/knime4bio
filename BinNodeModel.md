creates a UCSC Jim Kent's 'Bin' column

# Motivation #

This node adds a 'bin' column to the table. See http://plindenbaum.blogspot.com/2010/05/binning-genome.html

this 'bin' column was an index described by Jim Kent in "The Human Genome Browser at UCSC", doi: 10.1101/gr.229102 . Genome Res. 2002. 12:996-1006 and on the UCSC Wiki. Citing:"Binning scheme for optimizing database accesses for genomic annotations that cover a particular region of the genome( ...) Features are put in the smallest bin in which they fit. (...) Typically, almost all features are in the smaller bins, and in the most common usage scenarios only the contents of a few of these smaller bins need to be examined. (...) Since all of these bins are in sizes of powers of two, the calculation of the bin number is a simple matter of bit shifting of the chromStart and chromEnd coordinates".

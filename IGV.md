IGV
# Motivation #
Opens a window in IGV
# Solution #

"The Integrative Genomics Viewer (IGV)http://www.broadinstitute.org/software/igv/ is a high-performance visualization tool for interactive exploration of large, integrated datasets".
This node opens tells IGV to open a given genomic location.

See also http://www.broadinstitute.org/igv/PortCommands[http://www.broadinstitute.org/igv/PortCommands]

![https://knime4bio.googlecode.com/svn/wiki/IGV01.png](https://knime4bio.googlecode.com/svn/wiki/IGV01.png)

# Parameters #

  * **CHROM** : The VCF CHROM column
  * **POS** : The VCF POS column. The genomic position (1-based)
  * **IGV host** : The host for IGV (see http://www.broadinstitute.org/igv/PortCommands[http://www.broadinstitute.org/igv/PortCommands])
  * **IGV port** : The port for IGV (see http://www.broadinstitute.org/igv/PortCommands[http://www.broadinstitute.org/igv/PortCommands])

# Input #

  * **input1** : VCF
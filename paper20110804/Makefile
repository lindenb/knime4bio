LATEXDIFFDIR=/home/lindenb/package/latexdiff/compiled/bin/
all:knime.pdf
knime.pdf:knime.dvi
	dvipdf $<
knime.dvi:knime.tex knime.bib fig01.eps
	latex $<
	latex $<
	latex $<
	bibtex knime
	latex $<
	latex $<
	latex $<
		
pubmed.xml:
	curl "http://eutils.ncbi.nlm.nih.gov/entrez/eutils/efetch.fcgi?db=pubmed&retmode=xml&id=21177655,21873641,21221095,18269742,20080505,16845108,19689959,21531983,21067998,21447597,17098935,21520341,11337480,1208982,16500937,18957448,19505943,19920128,20354512,20639541,21378989,21653522,21208982" > $@
knime.bib:pubmed.xml 
	cat knime_part.bib > $@
	xsltproc --novalid pubmed2bibtex.xsl $< >> $@
fig01.eps:fig01.ps
	ps2eps -f $<
	eps2eps fig01.eps jeter.eps
	mv jeter.eps fig01.eps
fig01.ps:fig01.pnm
	pnmtops -noturn -imagewidth 3.5 $< > $@
fig01.pnm:fig01.png
	pngtopnm $< > $@
knime.rtf:knime.pdf
	latex2rtf -F -b knime.bbl -a knime.aux knime.tex
diff:knime-diff175.pdf
knime-diff175.pdf:knime-diff175.dvi
	dvipdf $<
knime-diff175.dvi:knime-diff175.tex  knime.bib fig01.eps
	-latex --interaction=batchmode $<	
knime-diff175.tex:knime.tex
	echo "check latexdiff is in the PATH"
	$(LATEXDIFFDIR)/latexdiff-svn -r 175 $<
clean:clean175
	rm -f knime.aux knime.bbl knime.bib knime.blg knime.dvi knime.log knime.pdf pubmed.xml knime.rtf
clean175:
	rm -f knime-diff175.*


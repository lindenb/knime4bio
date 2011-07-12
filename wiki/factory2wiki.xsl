<?xml version='1.0'  encoding="UTF-8" ?>
<xsl:stylesheet
        xmlns:xsl='http://www.w3.org/1999/XSL/Transform'
        version='1.0'
        >
<xsl:output method="text"/>

<xsl:template match="/">
<xsl:text>#summary </xsl:text>
<xsl:value-of select="knimeNode/shortDescription"/>
<xsl:text>
</xsl:text>
  <xsl:apply-templates select="knimeNode"/>
</xsl:template>

<xsl:template match="knimeNode">
  <xsl:apply-templates select="name|shortDescription|fullDescription|ports"/>
</xsl:template>


<xsl:template match="name">
<xsl:value-of select="."/><xsl:text>

</xsl:text>
</xsl:template>


<xsl:template match="shortDescription">

<xsl:text>

= Motivation =

</xsl:text>
<xsl:apply-templates/>
</xsl:template>


<xsl:template match="fullDescription">

<xsl:text>

= Solution =

</xsl:text>
<xsl:apply-templates select="intro"/>
<xsl:text>

= Parameters =

</xsl:text>
<xsl:apply-templates select="option"/>
</xsl:template>

<xsl:template match="option">
<xsl:text>
  * *</xsl:text>
<xsl:value-of select="@name"/>
<xsl:text>* : </xsl:text>
<xsl:apply-templates/>
</xsl:template>


<xsl:template match="ports">
<xsl:if test="inPort">
<xsl:text>

= Input =

</xsl:text>
<xsl:apply-templates select="inPort"/>
</xsl:if>
<xsl:if test="outPort">
<xsl:text>

= Output =

</xsl:text>
<xsl:apply-templates select="outPort"/>
</xsl:if>
</xsl:template>


<xsl:template match="inPort|outPort">
<xsl:text>
  * *</xsl:text>
<xsl:value-of select="@name"/>
<xsl:text>* : </xsl:text>
<xsl:apply-templates/>
<xsl:text>
</xsl:text>
</xsl:template>


<xsl:template match="h3">
<xsl:text>
==</xsl:text>
<xsl:apply-templates/>
<xsl:text>==
</xsl:text>
</xsl:template>

<xsl:template match="h4">
<xsl:text>
===</xsl:text>
<xsl:apply-templates/>
<xsl:text>===
</xsl:text>
</xsl:template>


<xsl:template match="b">
<xsl:text> *</xsl:text>
<xsl:value-of select="."/>
<xsl:text>* </xsl:text>
</xsl:template>

<xsl:template match="i">
<xsl:text> _</xsl:text>
<xsl:value-of select="."/>
<xsl:text>_ </xsl:text>
</xsl:template>

<xsl:template match="a">
<xsl:value-of select="."/>
<xsl:text>[</xsl:text>
<xsl:value-of select="@href"/>
<xsl:text>]</xsl:text>
</xsl:template>

<xsl:template match="pre">
<xsl:text>
{{{
</xsl:text>
<xsl:apply-templates/>
<xsl:text>
}}}
</xsl:text>
</xsl:template>


</xsl:stylesheet>

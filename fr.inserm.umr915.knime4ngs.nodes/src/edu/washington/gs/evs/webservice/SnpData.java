
package edu.washington.gs.evs.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for snpData complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="snpData">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="positionString" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="chrPosition" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="alleles" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="uaAlleleCounts" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="aaAlleleCounts" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="totalAlleleCounts" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="uaMAF" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="aaMAF" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="totalMAF" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="avgSampleReadDepth" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="geneList" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="snpFunction" type="{http://webservice.evs.gs.washington.edu/}snpFunction" minOccurs="0"/>
 *         &lt;element name="conservationScore" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="conservationScoreGERP" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="refAllele" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="altAlleles" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ancestralAllele" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="chromosome" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="hasAtLeastOneAccession" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rsIds" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="filters" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="clinicalLink" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dbsnpVersion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="uaGenotypeCounts" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="aaGenotypeCounts" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="totalGenotypeCounts" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="onExomeChip" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "snpData", propOrder = {
    "positionString",
    "chrPosition",
    "alleles",
    "uaAlleleCounts",
    "aaAlleleCounts",
    "totalAlleleCounts",
    "uaMAF",
    "aaMAF",
    "totalMAF",
    "avgSampleReadDepth",
    "geneList",
    "snpFunction",
    "conservationScore",
    "conservationScoreGERP",
    "refAllele",
    "altAlleles",
    "ancestralAllele",
    "chromosome",
    "hasAtLeastOneAccession",
    "rsIds",
    "filters",
    "clinicalLink",
    "dbsnpVersion",
    "uaGenotypeCounts",
    "aaGenotypeCounts",
    "totalGenotypeCounts",
    "onExomeChip"
})
public class SnpData {

    protected String positionString;
    protected int chrPosition;
    protected String alleles;
    protected String uaAlleleCounts;
    protected String aaAlleleCounts;
    protected String totalAlleleCounts;
    protected double uaMAF;
    protected double aaMAF;
    protected double totalMAF;
    protected int avgSampleReadDepth;
    protected String geneList;
    protected SnpFunction snpFunction;
    protected String conservationScore;
    protected String conservationScoreGERP;
    protected String refAllele;
    protected String altAlleles;
    protected String ancestralAllele;
    protected String chromosome;
    protected String hasAtLeastOneAccession;
    protected String rsIds;
    protected String filters;
    protected String clinicalLink;
    protected String dbsnpVersion;
    protected String uaGenotypeCounts;
    protected String aaGenotypeCounts;
    protected String totalGenotypeCounts;
    protected boolean onExomeChip;

    /**
     * Gets the value of the positionString property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPositionString() {
        return positionString;
    }

    /**
     * Sets the value of the positionString property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPositionString(String value) {
        this.positionString = value;
    }

    /**
     * Gets the value of the chrPosition property.
     * 
     */
    public int getChrPosition() {
        return chrPosition;
    }

    /**
     * Sets the value of the chrPosition property.
     * 
     */
    public void setChrPosition(int value) {
        this.chrPosition = value;
    }

    /**
     * Gets the value of the alleles property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAlleles() {
        return alleles;
    }

    /**
     * Sets the value of the alleles property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAlleles(String value) {
        this.alleles = value;
    }

    /**
     * Gets the value of the uaAlleleCounts property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUaAlleleCounts() {
        return uaAlleleCounts;
    }

    /**
     * Sets the value of the uaAlleleCounts property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUaAlleleCounts(String value) {
        this.uaAlleleCounts = value;
    }

    /**
     * Gets the value of the aaAlleleCounts property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAaAlleleCounts() {
        return aaAlleleCounts;
    }

    /**
     * Sets the value of the aaAlleleCounts property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAaAlleleCounts(String value) {
        this.aaAlleleCounts = value;
    }

    /**
     * Gets the value of the totalAlleleCounts property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotalAlleleCounts() {
        return totalAlleleCounts;
    }

    /**
     * Sets the value of the totalAlleleCounts property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotalAlleleCounts(String value) {
        this.totalAlleleCounts = value;
    }

    /**
     * Gets the value of the uaMAF property.
     * 
     */
    public double getUaMAF() {
        return uaMAF;
    }

    /**
     * Sets the value of the uaMAF property.
     * 
     */
    public void setUaMAF(double value) {
        this.uaMAF = value;
    }

    /**
     * Gets the value of the aaMAF property.
     * 
     */
    public double getAaMAF() {
        return aaMAF;
    }

    /**
     * Sets the value of the aaMAF property.
     * 
     */
    public void setAaMAF(double value) {
        this.aaMAF = value;
    }

    /**
     * Gets the value of the totalMAF property.
     * 
     */
    public double getTotalMAF() {
        return totalMAF;
    }

    /**
     * Sets the value of the totalMAF property.
     * 
     */
    public void setTotalMAF(double value) {
        this.totalMAF = value;
    }

    /**
     * Gets the value of the avgSampleReadDepth property.
     * 
     */
    public int getAvgSampleReadDepth() {
        return avgSampleReadDepth;
    }

    /**
     * Sets the value of the avgSampleReadDepth property.
     * 
     */
    public void setAvgSampleReadDepth(int value) {
        this.avgSampleReadDepth = value;
    }

    /**
     * Gets the value of the geneList property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGeneList() {
        return geneList;
    }

    /**
     * Sets the value of the geneList property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGeneList(String value) {
        this.geneList = value;
    }

    /**
     * Gets the value of the snpFunction property.
     * 
     * @return
     *     possible object is
     *     {@link SnpFunction }
     *     
     */
    public SnpFunction getSnpFunction() {
        return snpFunction;
    }

    /**
     * Sets the value of the snpFunction property.
     * 
     * @param value
     *     allowed object is
     *     {@link SnpFunction }
     *     
     */
    public void setSnpFunction(SnpFunction value) {
        this.snpFunction = value;
    }

    /**
     * Gets the value of the conservationScore property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConservationScore() {
        return conservationScore;
    }

    /**
     * Sets the value of the conservationScore property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConservationScore(String value) {
        this.conservationScore = value;
    }

    /**
     * Gets the value of the conservationScoreGERP property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConservationScoreGERP() {
        return conservationScoreGERP;
    }

    /**
     * Sets the value of the conservationScoreGERP property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConservationScoreGERP(String value) {
        this.conservationScoreGERP = value;
    }

    /**
     * Gets the value of the refAllele property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRefAllele() {
        return refAllele;
    }

    /**
     * Sets the value of the refAllele property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRefAllele(String value) {
        this.refAllele = value;
    }

    /**
     * Gets the value of the altAlleles property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAltAlleles() {
        return altAlleles;
    }

    /**
     * Sets the value of the altAlleles property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAltAlleles(String value) {
        this.altAlleles = value;
    }

    /**
     * Gets the value of the ancestralAllele property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAncestralAllele() {
        return ancestralAllele;
    }

    /**
     * Sets the value of the ancestralAllele property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAncestralAllele(String value) {
        this.ancestralAllele = value;
    }

    /**
     * Gets the value of the chromosome property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChromosome() {
        return chromosome;
    }

    /**
     * Sets the value of the chromosome property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChromosome(String value) {
        this.chromosome = value;
    }

    /**
     * Gets the value of the hasAtLeastOneAccession property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHasAtLeastOneAccession() {
        return hasAtLeastOneAccession;
    }

    /**
     * Sets the value of the hasAtLeastOneAccession property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHasAtLeastOneAccession(String value) {
        this.hasAtLeastOneAccession = value;
    }

    /**
     * Gets the value of the rsIds property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRsIds() {
        return rsIds;
    }

    /**
     * Sets the value of the rsIds property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRsIds(String value) {
        this.rsIds = value;
    }

    /**
     * Gets the value of the filters property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFilters() {
        return filters;
    }

    /**
     * Sets the value of the filters property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFilters(String value) {
        this.filters = value;
    }

    /**
     * Gets the value of the clinicalLink property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClinicalLink() {
        return clinicalLink;
    }

    /**
     * Sets the value of the clinicalLink property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClinicalLink(String value) {
        this.clinicalLink = value;
    }

    /**
     * Gets the value of the dbsnpVersion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDbsnpVersion() {
        return dbsnpVersion;
    }

    /**
     * Sets the value of the dbsnpVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDbsnpVersion(String value) {
        this.dbsnpVersion = value;
    }

    /**
     * Gets the value of the uaGenotypeCounts property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUaGenotypeCounts() {
        return uaGenotypeCounts;
    }

    /**
     * Sets the value of the uaGenotypeCounts property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUaGenotypeCounts(String value) {
        this.uaGenotypeCounts = value;
    }

    /**
     * Gets the value of the aaGenotypeCounts property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAaGenotypeCounts() {
        return aaGenotypeCounts;
    }

    /**
     * Sets the value of the aaGenotypeCounts property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAaGenotypeCounts(String value) {
        this.aaGenotypeCounts = value;
    }

    /**
     * Gets the value of the totalGenotypeCounts property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotalGenotypeCounts() {
        return totalGenotypeCounts;
    }

    /**
     * Sets the value of the totalGenotypeCounts property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotalGenotypeCounts(String value) {
        this.totalGenotypeCounts = value;
    }

    /**
     * Gets the value of the onExomeChip property.
     * 
     */
    public boolean isOnExomeChip() {
        return onExomeChip;
    }

    /**
     * Sets the value of the onExomeChip property.
     * 
     */
    public void setOnExomeChip(boolean value) {
        this.onExomeChip = value;
    }

}

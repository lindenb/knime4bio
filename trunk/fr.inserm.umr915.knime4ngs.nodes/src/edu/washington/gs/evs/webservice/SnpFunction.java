
package edu.washington.gs.evs.webservice;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for snpFunction complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="snpFunction">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="chromosome" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="position" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="conservationScore" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="conservationScoreGERP" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="snpFxnList" type="{http://webservice.evs.gs.washington.edu/}functionInfo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="refAllele" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ancestralAllele" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="firstRsId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="secondRsId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="filters" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="clinicalLink" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "snpFunction", propOrder = {
    "chromosome",
    "position",
    "conservationScore",
    "conservationScoreGERP",
    "snpFxnList",
    "refAllele",
    "ancestralAllele",
    "firstRsId",
    "secondRsId",
    "filters",
    "clinicalLink"
})
public class SnpFunction {

    protected String chromosome;
    protected int position;
    protected String conservationScore;
    protected String conservationScoreGERP;
    @XmlElement(nillable = true)
    protected List<FunctionInfo> snpFxnList;
    protected String refAllele;
    protected String ancestralAllele;
    protected int firstRsId;
    protected int secondRsId;
    protected String filters;
    protected String clinicalLink;

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
     * Gets the value of the position property.
     * 
     */
    public int getPosition() {
        return position;
    }

    /**
     * Sets the value of the position property.
     * 
     */
    public void setPosition(int value) {
        this.position = value;
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
     * Gets the value of the snpFxnList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the snpFxnList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSnpFxnList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FunctionInfo }
     * 
     * 
     */
    public List<FunctionInfo> getSnpFxnList() {
        if (snpFxnList == null) {
            snpFxnList = new ArrayList<FunctionInfo>();
        }
        return this.snpFxnList;
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
     * Gets the value of the firstRsId property.
     * 
     */
    public int getFirstRsId() {
        return firstRsId;
    }

    /**
     * Sets the value of the firstRsId property.
     * 
     */
    public void setFirstRsId(int value) {
        this.firstRsId = value;
    }

    /**
     * Gets the value of the secondRsId property.
     * 
     */
    public int getSecondRsId() {
        return secondRsId;
    }

    /**
     * Sets the value of the secondRsId property.
     * 
     */
    public void setSecondRsId(int value) {
        this.secondRsId = value;
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

}

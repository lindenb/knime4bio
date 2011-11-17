
package edu.washington.gs.evs.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for functionInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="functionInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="mrnaAccession" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fxnClassGVS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="aminoAcids" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="proteinPos" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cdnaPos" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="pphPrediction" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="granthamScore" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "functionInfo", propOrder = {
    "mrnaAccession",
    "fxnClassGVS",
    "aminoAcids",
    "proteinPos",
    "cdnaPos",
    "pphPrediction",
    "granthamScore"
})
public class FunctionInfo {

    protected String mrnaAccession;
    protected String fxnClassGVS;
    protected String aminoAcids;
    protected String proteinPos;
    protected int cdnaPos;
    protected String pphPrediction;
    protected String granthamScore;

    /**
     * Gets the value of the mrnaAccession property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMrnaAccession() {
        return mrnaAccession;
    }

    /**
     * Sets the value of the mrnaAccession property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMrnaAccession(String value) {
        this.mrnaAccession = value;
    }

    /**
     * Gets the value of the fxnClassGVS property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFxnClassGVS() {
        return fxnClassGVS;
    }

    /**
     * Sets the value of the fxnClassGVS property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFxnClassGVS(String value) {
        this.fxnClassGVS = value;
    }

    /**
     * Gets the value of the aminoAcids property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAminoAcids() {
        return aminoAcids;
    }

    /**
     * Sets the value of the aminoAcids property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAminoAcids(String value) {
        this.aminoAcids = value;
    }

    /**
     * Gets the value of the proteinPos property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProteinPos() {
        return proteinPos;
    }

    /**
     * Sets the value of the proteinPos property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProteinPos(String value) {
        this.proteinPos = value;
    }

    /**
     * Gets the value of the cdnaPos property.
     * 
     */
    public int getCdnaPos() {
        return cdnaPos;
    }

    /**
     * Sets the value of the cdnaPos property.
     * 
     */
    public void setCdnaPos(int value) {
        this.cdnaPos = value;
    }

    /**
     * Gets the value of the pphPrediction property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPphPrediction() {
        return pphPrediction;
    }

    /**
     * Sets the value of the pphPrediction property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPphPrediction(String value) {
        this.pphPrediction = value;
    }

    /**
     * Gets the value of the granthamScore property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGranthamScore() {
        return granthamScore;
    }

    /**
     * Sets the value of the granthamScore property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGranthamScore(String value) {
        this.granthamScore = value;
    }

}

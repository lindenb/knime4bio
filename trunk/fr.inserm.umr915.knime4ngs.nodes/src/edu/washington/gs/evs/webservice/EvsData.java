
package edu.washington.gs.evs.webservice;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for evsData complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="evsData">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="chromosome" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="start" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="stop" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="strand" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="snpList" type="{http://webservice.evs.gs.washington.edu/}snpData" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="setOfSiteCoverageInfo" type="{http://webservice.evs.gs.washington.edu/}siteCoverageInfo" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "evsData", propOrder = {
    "chromosome",
    "start",
    "stop",
    "strand",
    "snpList",
    "setOfSiteCoverageInfo"
})
public class EvsData {

    protected String chromosome;
    protected int start;
    protected int stop;
    protected String strand;
    @XmlElement(nillable = true)
    protected List<SnpData> snpList;
    @XmlElement(nillable = true)
    protected List<SiteCoverageInfo> setOfSiteCoverageInfo;

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
     * Gets the value of the start property.
     * 
     */
    public int getStart() {
        return start;
    }

    /**
     * Sets the value of the start property.
     * 
     */
    public void setStart(int value) {
        this.start = value;
    }

    /**
     * Gets the value of the stop property.
     * 
     */
    public int getStop() {
        return stop;
    }

    /**
     * Sets the value of the stop property.
     * 
     */
    public void setStop(int value) {
        this.stop = value;
    }

    /**
     * Gets the value of the strand property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStrand() {
        return strand;
    }

    /**
     * Sets the value of the strand property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStrand(String value) {
        this.strand = value;
    }

    /**
     * Gets the value of the snpList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the snpList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSnpList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SnpData }
     * 
     * 
     */
    public List<SnpData> getSnpList() {
        if (snpList == null) {
            snpList = new ArrayList<SnpData>();
        }
        return this.snpList;
    }

    /**
     * Gets the value of the setOfSiteCoverageInfo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the setOfSiteCoverageInfo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSetOfSiteCoverageInfo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SiteCoverageInfo }
     * 
     * 
     */
    public List<SiteCoverageInfo> getSetOfSiteCoverageInfo() {
        if (setOfSiteCoverageInfo == null) {
            setOfSiteCoverageInfo = new ArrayList<SiteCoverageInfo>();
        }
        return this.setOfSiteCoverageInfo;
    }

}

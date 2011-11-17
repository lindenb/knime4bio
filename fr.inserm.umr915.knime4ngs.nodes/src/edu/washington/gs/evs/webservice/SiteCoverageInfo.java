
package edu.washington.gs.evs.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for siteCoverageInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="siteCoverageInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="chromosome" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="position" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="totalSamplesCovered" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="avgSampleReadDepth" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="eaSamplesCovered" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="avgEaSampleReadDepth" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="aaSamplesCovered" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="avgAaSampleReadDepth" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "siteCoverageInfo", propOrder = {
    "chromosome",
    "position",
    "totalSamplesCovered",
    "avgSampleReadDepth",
    "eaSamplesCovered",
    "avgEaSampleReadDepth",
    "aaSamplesCovered",
    "avgAaSampleReadDepth"
})
public class SiteCoverageInfo {

    protected String chromosome;
    protected int position;
    protected int totalSamplesCovered;
    protected double avgSampleReadDepth;
    protected int eaSamplesCovered;
    protected double avgEaSampleReadDepth;
    protected int aaSamplesCovered;
    protected double avgAaSampleReadDepth;

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
     * Gets the value of the totalSamplesCovered property.
     * 
     */
    public int getTotalSamplesCovered() {
        return totalSamplesCovered;
    }

    /**
     * Sets the value of the totalSamplesCovered property.
     * 
     */
    public void setTotalSamplesCovered(int value) {
        this.totalSamplesCovered = value;
    }

    /**
     * Gets the value of the avgSampleReadDepth property.
     * 
     */
    public double getAvgSampleReadDepth() {
        return avgSampleReadDepth;
    }

    /**
     * Sets the value of the avgSampleReadDepth property.
     * 
     */
    public void setAvgSampleReadDepth(double value) {
        this.avgSampleReadDepth = value;
    }

    /**
     * Gets the value of the eaSamplesCovered property.
     * 
     */
    public int getEaSamplesCovered() {
        return eaSamplesCovered;
    }

    /**
     * Sets the value of the eaSamplesCovered property.
     * 
     */
    public void setEaSamplesCovered(int value) {
        this.eaSamplesCovered = value;
    }

    /**
     * Gets the value of the avgEaSampleReadDepth property.
     * 
     */
    public double getAvgEaSampleReadDepth() {
        return avgEaSampleReadDepth;
    }

    /**
     * Sets the value of the avgEaSampleReadDepth property.
     * 
     */
    public void setAvgEaSampleReadDepth(double value) {
        this.avgEaSampleReadDepth = value;
    }

    /**
     * Gets the value of the aaSamplesCovered property.
     * 
     */
    public int getAaSamplesCovered() {
        return aaSamplesCovered;
    }

    /**
     * Sets the value of the aaSamplesCovered property.
     * 
     */
    public void setAaSamplesCovered(int value) {
        this.aaSamplesCovered = value;
    }

    /**
     * Gets the value of the avgAaSampleReadDepth property.
     * 
     */
    public double getAvgAaSampleReadDepth() {
        return avgAaSampleReadDepth;
    }

    /**
     * Sets the value of the avgAaSampleReadDepth property.
     * 
     */
    public void setAvgAaSampleReadDepth(double value) {
        this.avgAaSampleReadDepth = value;
    }

}


package com.vignette.idm.render.manager;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CRendererInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CRendererInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="bstrType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="bstrDescription" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="bstrConfigURI" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CRendererInfo", propOrder = {
    "bstrType",
    "bstrDescription",
    "bstrConfigURI"
})
public class CRendererInfo {

    @XmlElement(required = true)
    protected String bstrType;
    @XmlElement(required = true)
    protected String bstrDescription;
    @XmlElement(required = true)
    protected String bstrConfigURI;

    /**
     * Gets the value of the bstrType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBstrType() {
        return bstrType;
    }

    /**
     * Sets the value of the bstrType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBstrType(String value) {
        this.bstrType = value;
    }

    /**
     * Gets the value of the bstrDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBstrDescription() {
        return bstrDescription;
    }

    /**
     * Sets the value of the bstrDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBstrDescription(String value) {
        this.bstrDescription = value;
    }

    /**
     * Gets the value of the bstrConfigURI property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBstrConfigURI() {
        return bstrConfigURI;
    }

    /**
     * Sets the value of the bstrConfigURI property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBstrConfigURI(String value) {
        this.bstrConfigURI = value;
    }

}

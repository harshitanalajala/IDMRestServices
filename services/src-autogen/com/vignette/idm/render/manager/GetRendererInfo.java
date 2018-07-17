
package com.vignette.idm.render.manager;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="bstrType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "bstrType"
})
@XmlRootElement(name = "getRendererInfo")
public class GetRendererInfo {

    @XmlElement(required = true)
    protected String bstrType;

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

}

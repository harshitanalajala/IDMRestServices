
package com.vignette.idm.server;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for UnknownVarNamePatternExceptionFaultDetail complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="UnknownVarNamePatternExceptionFaultDetail">
 *   &lt;complexContent>
 *     &lt;extension base="{http://idm.vignette.com/}GeneralExceptionFaultDetail">
 *       &lt;sequence>
 *         &lt;element name="varNamePattern" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UnknownVarNamePatternExceptionFaultDetail", propOrder = {
    "varNamePattern"
})
public class UnknownVarNamePatternExceptionFaultDetail
    extends GeneralExceptionFaultDetail
{

    @XmlElement(required = true)
    protected String varNamePattern;

    /**
     * Gets the value of the varNamePattern property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVarNamePattern() {
        return varNamePattern;
    }

    /**
     * Sets the value of the varNamePattern property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVarNamePattern(String value) {
        this.varNamePattern = value;
    }

}

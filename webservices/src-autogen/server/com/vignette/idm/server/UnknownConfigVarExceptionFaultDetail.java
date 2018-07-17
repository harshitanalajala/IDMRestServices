
package com.vignette.idm.server;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for UnknownConfigVarExceptionFaultDetail complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="UnknownConfigVarExceptionFaultDetail">
 *   &lt;complexContent>
 *     &lt;extension base="{http://idm.vignette.com/}GeneralExceptionFaultDetail">
 *       &lt;sequence>
 *         &lt;element name="configVarName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UnknownConfigVarExceptionFaultDetail", propOrder = {
    "configVarName"
})
public class UnknownConfigVarExceptionFaultDetail
    extends GeneralExceptionFaultDetail
{

    @XmlElement(required = true)
    protected String configVarName;

    /**
     * Gets the value of the configVarName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConfigVarName() {
        return configVarName;
    }

    /**
     * Sets the value of the configVarName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConfigVarName(String value) {
        this.configVarName = value;
    }

}

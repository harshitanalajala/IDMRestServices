
package com.vignette.idm.server;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for NotLibAppExceptionFaultDetail complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="NotLibAppExceptionFaultDetail">
 *   &lt;complexContent>
 *     &lt;extension base="{http://idm.vignette.com/}GeneralExceptionFaultDetail">
 *       &lt;sequence>
 *         &lt;element name="appName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NotLibAppExceptionFaultDetail", propOrder = {
    "appName"
})
public class NotLibAppExceptionFaultDetail
    extends GeneralExceptionFaultDetail
{

    @XmlElement(required = true)
    protected String appName;

    /**
     * Gets the value of the appName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAppName() {
        return appName;
    }

    /**
     * Sets the value of the appName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAppName(String value) {
        this.appName = value;
    }

}

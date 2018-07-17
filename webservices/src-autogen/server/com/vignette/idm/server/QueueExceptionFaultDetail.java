
package com.vignette.idm.server;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for QueueExceptionFaultDetail complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="QueueExceptionFaultDetail">
 *   &lt;complexContent>
 *     &lt;extension base="{http://idm.vignette.com/}GeneralExceptionFaultDetail">
 *       &lt;sequence>
 *         &lt;element name="hostName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="queueSysName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "QueueExceptionFaultDetail", propOrder = {
    "hostName",
    "queueSysName"
})
public class QueueExceptionFaultDetail
    extends GeneralExceptionFaultDetail
{

    @XmlElement(required = true)
    protected String hostName;
    @XmlElement(required = true)
    protected String queueSysName;

    /**
     * Gets the value of the hostName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHostName() {
        return hostName;
    }

    /**
     * Sets the value of the hostName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHostName(String value) {
        this.hostName = value;
    }

    /**
     * Gets the value of the queueSysName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQueueSysName() {
        return queueSysName;
    }

    /**
     * Sets the value of the queueSysName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQueueSysName(String value) {
        this.queueSysName = value;
    }

}

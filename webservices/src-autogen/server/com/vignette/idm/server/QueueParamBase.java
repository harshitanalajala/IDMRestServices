
package com.vignette.idm.server;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for QueueParamBase complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="QueueParamBase">
 *   &lt;complexContent>
 *     &lt;extension base="{http://idm.vignette.com/}LicensedParam">
 *       &lt;sequence>
 *         &lt;element name="queueServer" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="queueServiceName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "QueueParamBase", propOrder = {
    "queueServer",
    "queueServiceName"
})
public class QueueParamBase
    extends LicensedParam
{

    @XmlElement(required = true)
    protected String queueServer;
    @XmlElement(required = true)
    protected String queueServiceName;

    /**
     * Gets the value of the queueServer property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQueueServer() {
        return queueServer;
    }

    /**
     * Sets the value of the queueServer property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQueueServer(String value) {
        this.queueServer = value;
    }

    /**
     * Gets the value of the queueServiceName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQueueServiceName() {
        return queueServiceName;
    }

    /**
     * Sets the value of the queueServiceName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQueueServiceName(String value) {
        this.queueServiceName = value;
    }

}


package com.vignette.idm.server;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for QueueIDUnknownExceptionFaultDetail complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="QueueIDUnknownExceptionFaultDetail">
 *   &lt;complexContent>
 *     &lt;extension base="{http://idm.vignette.com/}QueueExceptionFaultDetail">
 *       &lt;sequence>
 *         &lt;element name="queueID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "QueueIDUnknownExceptionFaultDetail", propOrder = {
    "queueID"
})
public class QueueIDUnknownExceptionFaultDetail
    extends QueueExceptionFaultDetail
{

    protected long queueID;

    /**
     * Gets the value of the queueID property.
     * 
     */
    public long getQueueID() {
        return queueID;
    }

    /**
     * Sets the value of the queueID property.
     * 
     */
    public void setQueueID(long value) {
        this.queueID = value;
    }

}

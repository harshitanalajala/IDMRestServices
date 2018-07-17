
package com.vignette.idm.server;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for QueueEntryNotLockedExceptionFaultDetail complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="QueueEntryNotLockedExceptionFaultDetail">
 *   &lt;complexContent>
 *     &lt;extension base="{http://idm.vignette.com/}QueueExceptionFaultDetail">
 *       &lt;sequence>
 *         &lt;element name="entryID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "QueueEntryNotLockedExceptionFaultDetail", propOrder = {
    "entryID"
})
public class QueueEntryNotLockedExceptionFaultDetail
    extends QueueExceptionFaultDetail
{

    protected long entryID;

    /**
     * Gets the value of the entryID property.
     * 
     */
    public long getEntryID() {
        return entryID;
    }

    /**
     * Sets the value of the entryID property.
     * 
     */
    public void setEntryID(long value) {
        this.entryID = value;
    }

}

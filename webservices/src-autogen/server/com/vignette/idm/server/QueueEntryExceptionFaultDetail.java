
package com.vignette.idm.server;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for QueueEntryExceptionFaultDetail complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="QueueEntryExceptionFaultDetail">
 *   &lt;complexContent>
 *     &lt;extension base="{http://idm.vignette.com/}QueueIDUnknownExceptionFaultDetail">
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
@XmlType(name = "QueueEntryExceptionFaultDetail", propOrder = {
    "entryID"
})
public class QueueEntryExceptionFaultDetail
    extends QueueIDUnknownExceptionFaultDetail
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

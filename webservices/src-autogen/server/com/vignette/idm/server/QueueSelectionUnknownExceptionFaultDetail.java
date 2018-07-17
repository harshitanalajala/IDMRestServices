
package com.vignette.idm.server;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for QueueSelectionUnknownExceptionFaultDetail complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="QueueSelectionUnknownExceptionFaultDetail">
 *   &lt;complexContent>
 *     &lt;extension base="{http://idm.vignette.com/}QueueExceptionFaultDetail">
 *       &lt;sequence>
 *         &lt;element name="selectionID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "QueueSelectionUnknownExceptionFaultDetail", propOrder = {
    "selectionID"
})
public class QueueSelectionUnknownExceptionFaultDetail
    extends QueueExceptionFaultDetail
{

    @XmlElement(required = true)
    protected String selectionID;

    /**
     * Gets the value of the selectionID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSelectionID() {
        return selectionID;
    }

    /**
     * Sets the value of the selectionID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSelectionID(String value) {
        this.selectionID = value;
    }

}


package com.vignette.idm.server;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ResultRow complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ResultRow">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="fieldValues" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="dotNet_wa" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResultRow", propOrder = {
    "fieldValues",
    "dotNetWa"
})
public class ResultRow {

    @XmlElement(nillable = true)
    protected List<String> fieldValues;
    @XmlElement(name = "dotNet_wa", required = true, nillable = true)
    protected String dotNetWa;

    /**
     * Gets the value of the fieldValues property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the fieldValues property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFieldValues().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getFieldValues() {
        if (fieldValues == null) {
            fieldValues = new ArrayList<String>();
        }
        return this.fieldValues;
    }

    /**
     * Gets the value of the dotNetWa property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDotNetWa() {
        return dotNetWa;
    }

    /**
     * Sets the value of the dotNetWa property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDotNetWa(String value) {
        this.dotNetWa = value;
    }

}

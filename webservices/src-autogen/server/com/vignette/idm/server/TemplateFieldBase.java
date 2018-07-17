
package com.vignette.idm.server;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TemplateFieldBase complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TemplateFieldBase">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="colPos" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="rowPos" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="displayLength" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="appFieldName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TemplateFieldBase", propOrder = {
    "colPos",
    "rowPos",
    "displayLength",
    "appFieldName"
})
public class TemplateFieldBase {

    protected int colPos;
    protected int rowPos;
    protected int displayLength;
    @XmlElement(required = true)
    protected String appFieldName;

    /**
     * Gets the value of the colPos property.
     * 
     */
    public int getColPos() {
        return colPos;
    }

    /**
     * Sets the value of the colPos property.
     * 
     */
    public void setColPos(int value) {
        this.colPos = value;
    }

    /**
     * Gets the value of the rowPos property.
     * 
     */
    public int getRowPos() {
        return rowPos;
    }

    /**
     * Sets the value of the rowPos property.
     * 
     */
    public void setRowPos(int value) {
        this.rowPos = value;
    }

    /**
     * Gets the value of the displayLength property.
     * 
     */
    public int getDisplayLength() {
        return displayLength;
    }

    /**
     * Sets the value of the displayLength property.
     * 
     */
    public void setDisplayLength(int value) {
        this.displayLength = value;
    }

    /**
     * Gets the value of the appFieldName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAppFieldName() {
        return appFieldName;
    }

    /**
     * Sets the value of the appFieldName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAppFieldName(String value) {
        this.appFieldName = value;
    }

}

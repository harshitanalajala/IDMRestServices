
package com.vignette.idm.server;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for EntryFieldDef complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EntryFieldDef">
 *   &lt;complexContent>
 *     &lt;extension base="{http://idm.vignette.com/}TemplateFieldBase">
 *       &lt;sequence>
 *         &lt;element name="dataType" type="{http://idm.vignette.com/}AppDataType"/>
 *         &lt;element name="entryType" type="{http://idm.vignette.com/}EntryType"/>
 *         &lt;element name="valueList" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="defaultValue" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="validationFunction" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="conversionFunction" type="{http://idm.vignette.com/}ConversionFunction"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EntryFieldDef", propOrder = {
    "dataType",
    "entryType",
    "valueList",
    "defaultValue",
    "validationFunction",
    "conversionFunction"
})
public class EntryFieldDef
    extends TemplateFieldBase
{

    @XmlElement(required = true)
    protected AppDataType dataType;
    @XmlElement(required = true)
    protected EntryType entryType;
    protected List<String> valueList;
    @XmlElement(required = true)
    protected String defaultValue;
    @XmlElement(required = true)
    protected String validationFunction;
    @XmlElement(required = true)
    protected ConversionFunction conversionFunction;

    /**
     * Gets the value of the dataType property.
     * 
     * @return
     *     possible object is
     *     {@link AppDataType }
     *     
     */
    public AppDataType getDataType() {
        return dataType;
    }

    /**
     * Sets the value of the dataType property.
     * 
     * @param value
     *     allowed object is
     *     {@link AppDataType }
     *     
     */
    public void setDataType(AppDataType value) {
        this.dataType = value;
    }

    /**
     * Gets the value of the entryType property.
     * 
     * @return
     *     possible object is
     *     {@link EntryType }
     *     
     */
    public EntryType getEntryType() {
        return entryType;
    }

    /**
     * Sets the value of the entryType property.
     * 
     * @param value
     *     allowed object is
     *     {@link EntryType }
     *     
     */
    public void setEntryType(EntryType value) {
        this.entryType = value;
    }

    /**
     * Gets the value of the valueList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the valueList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getValueList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getValueList() {
        if (valueList == null) {
            valueList = new ArrayList<String>();
        }
        return this.valueList;
    }

    /**
     * Gets the value of the defaultValue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDefaultValue() {
        return defaultValue;
    }

    /**
     * Sets the value of the defaultValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDefaultValue(String value) {
        this.defaultValue = value;
    }

    /**
     * Gets the value of the validationFunction property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValidationFunction() {
        return validationFunction;
    }

    /**
     * Sets the value of the validationFunction property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValidationFunction(String value) {
        this.validationFunction = value;
    }

    /**
     * Gets the value of the conversionFunction property.
     * 
     * @return
     *     possible object is
     *     {@link ConversionFunction }
     *     
     */
    public ConversionFunction getConversionFunction() {
        return conversionFunction;
    }

    /**
     * Sets the value of the conversionFunction property.
     * 
     * @param value
     *     allowed object is
     *     {@link ConversionFunction }
     *     
     */
    public void setConversionFunction(ConversionFunction value) {
        this.conversionFunction = value;
    }

}

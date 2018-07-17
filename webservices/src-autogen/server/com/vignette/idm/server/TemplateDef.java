
package com.vignette.idm.server;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TemplateDef complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TemplateDef">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="displayFields" type="{http://idm.vignette.com/}DisplayFieldDef" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="entryFields" type="{http://idm.vignette.com/}EntryFieldDef" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TemplateDef", propOrder = {
    "displayFields",
    "entryFields"
})
public class TemplateDef {

    protected List<DisplayFieldDef> displayFields;
    protected List<EntryFieldDef> entryFields;

    /**
     * Gets the value of the displayFields property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the displayFields property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDisplayFields().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DisplayFieldDef }
     * 
     * 
     */
    public List<DisplayFieldDef> getDisplayFields() {
        if (displayFields == null) {
            displayFields = new ArrayList<DisplayFieldDef>();
        }
        return this.displayFields;
    }

    /**
     * Gets the value of the entryFields property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the entryFields property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEntryFields().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EntryFieldDef }
     * 
     * 
     */
    public List<EntryFieldDef> getEntryFields() {
        if (entryFields == null) {
            entryFields = new ArrayList<EntryFieldDef>();
        }
        return this.entryFields;
    }

}

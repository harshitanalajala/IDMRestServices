
package com.vignette.idm.server;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AppList complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AppList">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="appDefs" type="{http://idm.vignette.com/}AppDef" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="defaultApp" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AppList", propOrder = {
    "appDefs",
    "defaultApp"
})
public class AppList {

    protected List<AppDef> appDefs;
    @XmlElement(required = true)
    protected String defaultApp;

    /**
     * Gets the value of the appDefs property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the appDefs property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAppDefs().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AppDef }
     * 
     * 
     */
    public List<AppDef> getAppDefs() {
        if (appDefs == null) {
            appDefs = new ArrayList<AppDef>();
        }
        return this.appDefs;
    }

    /**
     * Gets the value of the defaultApp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDefaultApp() {
        return defaultApp;
    }

    /**
     * Sets the value of the defaultApp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDefaultApp(String value) {
        this.defaultApp = value;
    }

}

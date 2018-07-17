
package com.vignette.idm.server;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for QueueInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="QueueInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="queueID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="type" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="createTime" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="accessTime" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="modifyTime" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="deleteTime" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="numEntries" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="requiredVarDefs" type="{http://idm.vignette.com/}QueueVarDef" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "QueueInfo", propOrder = {
    "queueID",
    "name",
    "type",
    "createTime",
    "accessTime",
    "modifyTime",
    "deleteTime",
    "numEntries",
    "requiredVarDefs"
})
public class QueueInfo {

    protected long queueID;
    @XmlElement(required = true)
    protected String name;
    @XmlElement(required = true)
    protected String type;
    @XmlElement(required = true)
    protected String createTime;
    @XmlElement(required = true)
    protected String accessTime;
    @XmlElement(required = true)
    protected String modifyTime;
    @XmlElement(required = true)
    protected String deleteTime;
    protected long numEntries;
    @XmlElement(nillable = true)
    protected List<QueueVarDef> requiredVarDefs;

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

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

    /**
     * Gets the value of the createTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * Sets the value of the createTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreateTime(String value) {
        this.createTime = value;
    }

    /**
     * Gets the value of the accessTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccessTime() {
        return accessTime;
    }

    /**
     * Sets the value of the accessTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccessTime(String value) {
        this.accessTime = value;
    }

    /**
     * Gets the value of the modifyTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getModifyTime() {
        return modifyTime;
    }

    /**
     * Sets the value of the modifyTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setModifyTime(String value) {
        this.modifyTime = value;
    }

    /**
     * Gets the value of the deleteTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeleteTime() {
        return deleteTime;
    }

    /**
     * Sets the value of the deleteTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeleteTime(String value) {
        this.deleteTime = value;
    }

    /**
     * Gets the value of the numEntries property.
     * 
     */
    public long getNumEntries() {
        return numEntries;
    }

    /**
     * Sets the value of the numEntries property.
     * 
     */
    public void setNumEntries(long value) {
        this.numEntries = value;
    }

    /**
     * Gets the value of the requiredVarDefs property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the requiredVarDefs property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRequiredVarDefs().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link QueueVarDef }
     * 
     * 
     */
    public List<QueueVarDef> getRequiredVarDefs() {
        if (requiredVarDefs == null) {
            requiredVarDefs = new ArrayList<QueueVarDef>();
        }
        return this.requiredVarDefs;
    }

}

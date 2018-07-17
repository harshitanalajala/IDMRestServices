
package com.vignette.idm.server;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for QueueEntry complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="QueueEntry">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="QueueID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="entryID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="priority" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="createTime" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="modifyTime" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="entryTime" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="lockNextEnabled" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="lockUser" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="variables" type="{http://idm.vignette.com/}NameValue" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "QueueEntry", propOrder = {
    "queueID",
    "entryID",
    "priority",
    "createTime",
    "modifyTime",
    "entryTime",
    "lockNextEnabled",
    "lockUser",
    "variables"
})
public class QueueEntry {

    @XmlElement(name = "QueueID")
    protected long queueID;
    protected long entryID;
    protected long priority;
    @XmlElement(required = true)
    protected String createTime;
    @XmlElement(required = true)
    protected String modifyTime;
    @XmlElement(required = true)
    protected String entryTime;
    protected boolean lockNextEnabled;
    @XmlElement(required = true)
    protected String lockUser;
    @XmlElement(required = true)
    protected List<NameValue> variables;

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

    /**
     * Gets the value of the priority property.
     * 
     */
    public long getPriority() {
        return priority;
    }

    /**
     * Sets the value of the priority property.
     * 
     */
    public void setPriority(long value) {
        this.priority = value;
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
     * Gets the value of the entryTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEntryTime() {
        return entryTime;
    }

    /**
     * Sets the value of the entryTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEntryTime(String value) {
        this.entryTime = value;
    }

    /**
     * Gets the value of the lockNextEnabled property.
     * 
     */
    public boolean isLockNextEnabled() {
        return lockNextEnabled;
    }

    /**
     * Sets the value of the lockNextEnabled property.
     * 
     */
    public void setLockNextEnabled(boolean value) {
        this.lockNextEnabled = value;
    }

    /**
     * Gets the value of the lockUser property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLockUser() {
        return lockUser;
    }

    /**
     * Sets the value of the lockUser property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLockUser(String value) {
        this.lockUser = value;
    }

    /**
     * Gets the value of the variables property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the variables property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getVariables().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link NameValue }
     * 
     * 
     */
    public List<NameValue> getVariables() {
        if (variables == null) {
            variables = new ArrayList<NameValue>();
        }
        return this.variables;
    }

}

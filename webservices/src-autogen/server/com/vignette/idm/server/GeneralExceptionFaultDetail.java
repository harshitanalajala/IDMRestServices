
package com.vignette.idm.server;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GeneralExceptionFaultDetail complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GeneralExceptionFaultDetail">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="message" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="wsName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="wsMethodName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="callStack" type="{http://idm.vignette.com/}StackTrace"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GeneralExceptionFaultDetail", propOrder = {
    "message",
    "wsName",
    "wsMethodName",
    "callStack"
})
public class GeneralExceptionFaultDetail {

    @XmlElement(required = true)
    protected String message;
    @XmlElement(required = true)
    protected String wsName;
    @XmlElement(required = true)
    protected String wsMethodName;
    @XmlElement(required = true, nillable = true)
    protected StackTrace callStack;

    /**
     * Gets the value of the message property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the value of the message property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessage(String value) {
        this.message = value;
    }

    /**
     * Gets the value of the wsName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWsName() {
        return wsName;
    }

    /**
     * Sets the value of the wsName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWsName(String value) {
        this.wsName = value;
    }

    /**
     * Gets the value of the wsMethodName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWsMethodName() {
        return wsMethodName;
    }

    /**
     * Sets the value of the wsMethodName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWsMethodName(String value) {
        this.wsMethodName = value;
    }

    /**
     * Gets the value of the callStack property.
     * 
     * @return
     *     possible object is
     *     {@link StackTrace }
     *     
     */
    public StackTrace getCallStack() {
        return callStack;
    }

    /**
     * Sets the value of the callStack property.
     * 
     * @param value
     *     allowed object is
     *     {@link StackTrace }
     *     
     */
    public void setCallStack(StackTrace value) {
        this.callStack = value;
    }

}

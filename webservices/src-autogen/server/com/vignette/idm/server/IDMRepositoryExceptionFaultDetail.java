
package com.vignette.idm.server;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for IDMRepositoryExceptionFaultDetail complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="IDMRepositoryExceptionFaultDetail">
 *   &lt;complexContent>
 *     &lt;extension base="{http://idm.vignette.com/}GeneralExceptionFaultDetail">
 *       &lt;sequence>
 *         &lt;element name="errorNum" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="errorCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IDMRepositoryExceptionFaultDetail", propOrder = {
    "errorNum",
    "errorCode"
})
public class IDMRepositoryExceptionFaultDetail
    extends GeneralExceptionFaultDetail
{

    protected int errorNum;
    @XmlElement(required = true)
    protected String errorCode;

    /**
     * Gets the value of the errorNum property.
     * 
     */
    public int getErrorNum() {
        return errorNum;
    }

    /**
     * Sets the value of the errorNum property.
     * 
     */
    public void setErrorNum(int value) {
        this.errorNum = value;
    }

    /**
     * Gets the value of the errorCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * Sets the value of the errorCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrorCode(String value) {
        this.errorCode = value;
    }

}

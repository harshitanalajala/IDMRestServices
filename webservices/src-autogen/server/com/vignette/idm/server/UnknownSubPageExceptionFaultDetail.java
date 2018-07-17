
package com.vignette.idm.server;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for UnknownSubPageExceptionFaultDetail complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="UnknownSubPageExceptionFaultDetail">
 *   &lt;complexContent>
 *     &lt;extension base="{http://idm.vignette.com/}UnknownPageExceptionFaultDetail">
 *       &lt;sequence>
 *         &lt;element name="subpageNum" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UnknownSubPageExceptionFaultDetail", propOrder = {
    "subpageNum"
})
public class UnknownSubPageExceptionFaultDetail
    extends UnknownPageExceptionFaultDetail
{

    protected long subpageNum;

    /**
     * Gets the value of the subpageNum property.
     * 
     */
    public long getSubpageNum() {
        return subpageNum;
    }

    /**
     * Sets the value of the subpageNum property.
     * 
     */
    public void setSubpageNum(long value) {
        this.subpageNum = value;
    }

}

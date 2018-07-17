
package com.vignette.idm.render.agent;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="plBeginPage" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="plEndPage" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="pbstrImageType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ppParams">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="string" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="ppPagesData">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="base64Binary" type="{http://www.w3.org/2001/XMLSchema}base64Binary" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="pbstrErrMsg" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "_return",
    "plBeginPage",
    "plEndPage",
    "pbstrImageType",
    "ppParams",
    "ppPagesData",
    "pbstrErrMsg"
})
@XmlRootElement(name = "renderDocumentResponse")
public class RenderDocumentResponse {

    @XmlElement(name = "return")
    protected int _return;
    protected int plBeginPage;
    protected int plEndPage;
    @XmlElement(required = true)
    protected String pbstrImageType;
    @XmlElement(required = true, nillable = true)
    protected RenderDocumentResponse.PpParams ppParams;
    @XmlElement(required = true, nillable = true)
    protected RenderDocumentResponse.PpPagesData ppPagesData;
    @XmlElement(required = true)
    protected String pbstrErrMsg;

    /**
     * Gets the value of the return property.
     * 
     */
    public int getReturn() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     * 
     */
    public void setReturn(int value) {
        this._return = value;
    }

    /**
     * Gets the value of the plBeginPage property.
     * 
     */
    public int getPlBeginPage() {
        return plBeginPage;
    }

    /**
     * Sets the value of the plBeginPage property.
     * 
     */
    public void setPlBeginPage(int value) {
        this.plBeginPage = value;
    }

    /**
     * Gets the value of the plEndPage property.
     * 
     */
    public int getPlEndPage() {
        return plEndPage;
    }

    /**
     * Sets the value of the plEndPage property.
     * 
     */
    public void setPlEndPage(int value) {
        this.plEndPage = value;
    }

    /**
     * Gets the value of the pbstrImageType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPbstrImageType() {
        return pbstrImageType;
    }

    /**
     * Sets the value of the pbstrImageType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPbstrImageType(String value) {
        this.pbstrImageType = value;
    }

    /**
     * Gets the value of the ppParams property.
     * 
     * @return
     *     possible object is
     *     {@link RenderDocumentResponse.PpParams }
     *     
     */
    public RenderDocumentResponse.PpParams getPpParams() {
        return ppParams;
    }

    /**
     * Sets the value of the ppParams property.
     * 
     * @param value
     *     allowed object is
     *     {@link RenderDocumentResponse.PpParams }
     *     
     */
    public void setPpParams(RenderDocumentResponse.PpParams value) {
        this.ppParams = value;
    }

    /**
     * Gets the value of the ppPagesData property.
     * 
     * @return
     *     possible object is
     *     {@link RenderDocumentResponse.PpPagesData }
     *     
     */
    public RenderDocumentResponse.PpPagesData getPpPagesData() {
        return ppPagesData;
    }

    /**
     * Sets the value of the ppPagesData property.
     * 
     * @param value
     *     allowed object is
     *     {@link RenderDocumentResponse.PpPagesData }
     *     
     */
    public void setPpPagesData(RenderDocumentResponse.PpPagesData value) {
        this.ppPagesData = value;
    }

    /**
     * Gets the value of the pbstrErrMsg property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPbstrErrMsg() {
        return pbstrErrMsg;
    }

    /**
     * Sets the value of the pbstrErrMsg property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPbstrErrMsg(String value) {
        this.pbstrErrMsg = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="base64Binary" type="{http://www.w3.org/2001/XMLSchema}base64Binary" maxOccurs="unbounded" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "base64Binary"
    })
    public static class PpPagesData {

        protected List<byte[]> base64Binary;

        /**
         * Gets the value of the base64Binary property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the base64Binary property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getBase64Binary().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * byte[]
         * 
         */
        public List<byte[]> getBase64Binary() {
            if (base64Binary == null) {
                base64Binary = new ArrayList<byte[]>();
            }
            return this.base64Binary;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="string" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "string"
    })
    public static class PpParams {

        protected List<String> string;

        /**
         * Gets the value of the string property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the string property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getString().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getString() {
            if (string == null) {
                string = new ArrayList<String>();
            }
            return this.string;
        }

    }

}

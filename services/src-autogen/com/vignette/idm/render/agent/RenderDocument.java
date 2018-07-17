
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
 *         &lt;element name="bstrRenderer" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="inData" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
 *         &lt;element name="bstrFileName" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "bstrRenderer",
    "inData",
    "bstrFileName",
    "plBeginPage",
    "plEndPage",
    "pbstrImageType",
    "ppParams"
})
@XmlRootElement(name = "renderDocument")
public class RenderDocument {

    @XmlElement(required = true)
    protected String bstrRenderer;
    @XmlElement(required = true)
    protected byte[] inData;
    @XmlElement(required = true)
    protected String bstrFileName;
    protected int plBeginPage;
    protected int plEndPage;
    @XmlElement(required = true)
    protected String pbstrImageType;
    @XmlElement(required = true, nillable = true)
    protected RenderDocument.PpParams ppParams;

    /**
     * Gets the value of the bstrRenderer property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBstrRenderer() {
        return bstrRenderer;
    }

    /**
     * Sets the value of the bstrRenderer property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBstrRenderer(String value) {
        this.bstrRenderer = value;
    }

    /**
     * Gets the value of the inData property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getInData() {
        return inData;
    }

    /**
     * Sets the value of the inData property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setInData(byte[] value) {
        this.inData = (value);
    }

    /**
     * Gets the value of the bstrFileName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBstrFileName() {
        return bstrFileName;
    }

    /**
     * Sets the value of the bstrFileName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBstrFileName(String value) {
        this.bstrFileName = value;
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
     *     {@link RenderDocument.PpParams }
     *     
     */
    public RenderDocument.PpParams getPpParams() {
        return ppParams;
    }

    /**
     * Sets the value of the ppParams property.
     * 
     * @param value
     *     allowed object is
     *     {@link RenderDocument.PpParams }
     *     
     */
    public void setPpParams(RenderDocument.PpParams value) {
        this.ppParams = value;
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

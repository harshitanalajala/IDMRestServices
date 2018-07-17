
package com.vignette.idm.server;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for QueueDataType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="QueueDataType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="CHAR"/>
 *     &lt;enumeration value="INTEGER"/>
 *     &lt;enumeration value="SMALLINT"/>
 *     &lt;enumeration value="DATE"/>
 *     &lt;enumeration value="TIME"/>
 *     &lt;enumeration value="DATETIME"/>
 *     &lt;enumeration value="CUST00"/>
 *     &lt;enumeration value="CUST01"/>
 *     &lt;enumeration value="CUST02"/>
 *     &lt;enumeration value="CUST03"/>
 *     &lt;enumeration value="CUST04"/>
 *     &lt;enumeration value="CUST05"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "QueueDataType")
@XmlEnum
public enum QueueDataType {

    CHAR("CHAR"),
    INTEGER("INTEGER"),
    SMALLINT("SMALLINT"),
    DATE("DATE"),
    TIME("TIME"),
    DATETIME("DATETIME"),
    @XmlEnumValue("CUST00")
    CUST_00("CUST00"),
    @XmlEnumValue("CUST01")
    CUST_01("CUST01"),
    @XmlEnumValue("CUST02")
    CUST_02("CUST02"),
    @XmlEnumValue("CUST03")
    CUST_03("CUST03"),
    @XmlEnumValue("CUST04")
    CUST_04("CUST04"),
    @XmlEnumValue("CUST05")
    CUST_05("CUST05");
    private final String value;

    QueueDataType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static QueueDataType fromValue(String v) {
        for (QueueDataType c: QueueDataType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

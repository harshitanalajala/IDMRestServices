
package com.vignette.idm.server;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AppDataType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="AppDataType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="CHAR"/>
 *     &lt;enumeration value="INTEGER"/>
 *     &lt;enumeration value="SMALLINT"/>
 *     &lt;enumeration value="DATE"/>
 *     &lt;enumeration value="TIME"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "AppDataType")
@XmlEnum
public enum AppDataType {

    CHAR,
    INTEGER,
    SMALLINT,
    DATE,
    TIME;

    public String value() {
        return name();
    }

    public static AppDataType fromValue(String v) {
        return valueOf(v);
    }

}

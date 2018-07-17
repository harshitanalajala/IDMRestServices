
package com.vignette.idm.server;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ConversionFunction.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ConversionFunction">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="CONVERT_NO_CONVERSION"/>
 *     &lt;enumeration value="CONVERT_DEFAULT_DATE"/>
 *     &lt;enumeration value="CONVERT_AUTO_INCREMENT"/>
 *     &lt;enumeration value="CONVERT_USER_ID"/>
 *     &lt;enumeration value="CONVERT_FULL_USER_NAME"/>
 *     &lt;enumeration value="CONVERT_DOCTYPE_TO_SLEVEL"/>
 *     &lt;enumeration value="CONVERT_DEFAULT_AS_ENVIRONMENT_VAR"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ConversionFunction")
@XmlEnum
public enum ConversionFunction {

    CONVERT_NO_CONVERSION,
    CONVERT_DEFAULT_DATE,
    CONVERT_AUTO_INCREMENT,
    CONVERT_USER_ID,
    CONVERT_FULL_USER_NAME,
    CONVERT_DOCTYPE_TO_SLEVEL,
    CONVERT_DEFAULT_AS_ENVIRONMENT_VAR;

    public String value() {
        return name();
    }

    public static ConversionFunction fromValue(String v) {
        return valueOf(v);
    }

}


package com.vignette.idm.server;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for EntryType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="EntryType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="STD_EDIT_BOX"/>
 *     &lt;enumeration value="DROPDOWN_COMBO"/>
 *     &lt;enumeration value="CHECK_BOX"/>
 *     &lt;enumeration value="DATE_PICKER"/>
 *     &lt;enumeration value="DROPDOWN_LIST"/>
 *     &lt;enumeration value="DOCTYPE_TO_SLEVEL"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "EntryType")
@XmlEnum
public enum EntryType {

    STD_EDIT_BOX,
    DROPDOWN_COMBO,
    CHECK_BOX,
    DATE_PICKER,
    DROPDOWN_LIST,
    DOCTYPE_TO_SLEVEL;

    public String value() {
        return name();
    }

    public static EntryType fromValue(String v) {
        return valueOf(v);
    }

}

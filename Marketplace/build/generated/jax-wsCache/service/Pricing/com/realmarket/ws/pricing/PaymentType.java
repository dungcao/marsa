
package com.realmarket.ws.pricing;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for paymentType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="paymentType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="TIME_PLAN"/>
 *     &lt;enumeration value="DATA_SIZE"/>
 *     &lt;enumeration value="API_HANDLE"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "paymentType")
@XmlEnum
public enum PaymentType {

    TIME_PLAN,
    DATA_SIZE,
    API_HANDLE;

    public String value() {
        return name();
    }

    public static PaymentType fromValue(String v) {
        return valueOf(v);
    }

}

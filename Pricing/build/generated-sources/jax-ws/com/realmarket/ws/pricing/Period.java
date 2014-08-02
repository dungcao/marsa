
package com.realmarket.ws.pricing;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for period complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="period">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="extraPrice" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="repeat" type="{http://realmarket.com/ws/pricing}repeatBy" minOccurs="0"/>
 *         &lt;element name="fValue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tValue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "period", propOrder = {
    "extraPrice",
    "repeat",
    "fValue",
    "tValue"
})
public class Period {

    protected float extraPrice;
    protected RepeatBy repeat;
    protected String fValue;
    protected String tValue;

    /**
     * Gets the value of the extraPrice property.
     * 
     */
    public float getExtraPrice() {
        return extraPrice;
    }

    /**
     * Sets the value of the extraPrice property.
     * 
     */
    public void setExtraPrice(float value) {
        this.extraPrice = value;
    }

    /**
     * Gets the value of the repeat property.
     * 
     * @return
     *     possible object is
     *     {@link RepeatBy }
     *     
     */
    public RepeatBy getRepeat() {
        return repeat;
    }

    /**
     * Sets the value of the repeat property.
     * 
     * @param value
     *     allowed object is
     *     {@link RepeatBy }
     *     
     */
    public void setRepeat(RepeatBy value) {
        this.repeat = value;
    }

    /**
     * Gets the value of the fValue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFValue() {
        return fValue;
    }

    /**
     * Sets the value of the fValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFValue(String value) {
        this.fValue = value;
    }

    /**
     * Gets the value of the tValue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTValue() {
        return tValue;
    }

    /**
     * Sets the value of the tValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTValue(String value) {
        this.tValue = value;
    }

}

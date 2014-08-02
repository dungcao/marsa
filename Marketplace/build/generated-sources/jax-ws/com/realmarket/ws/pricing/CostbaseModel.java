
package com.realmarket.ws.pricing;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for costbaseModel complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="costbaseModel">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="basicPrice" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "costbaseModel", propOrder = {
    "basicPrice"
})
@XmlSeeAlso({
    ApiHande.class,
    DataSize.class,
    Subscription.class,
    DataUnit.class,
    TimePlan.class
})
public class CostbaseModel {

    protected float basicPrice;

    /**
     * Gets the value of the basicPrice property.
     * 
     */
    public float getBasicPrice() {
        return basicPrice;
    }

    /**
     * Sets the value of the basicPrice property.
     * 
     */
    public void setBasicPrice(float value) {
        this.basicPrice = value;
    }

}

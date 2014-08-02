
package com.realmarket.ws.pricing;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Create complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Create">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TimePlan" type="{http://realmarket.com/ws/pricing}timePlan" minOccurs="0"/>
 *         &lt;element name="DataSize" type="{http://realmarket.com/ws/pricing}dataSize" minOccurs="0"/>
 *         &lt;element name="APIHande" type="{http://realmarket.com/ws/pricing}apiHande" minOccurs="0"/>
 *         &lt;element name="Subscription" type="{http://realmarket.com/ws/pricing}subscription" minOccurs="0"/>
 *         &lt;element name="DataUnit" type="{http://realmarket.com/ws/pricing}dataUnit" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Create", propOrder = {
    "timePlan",
    "dataSize",
    "apiHande",
    "subscription",
    "dataUnit"
})
public class Create {

    @XmlElement(name = "TimePlan")
    protected TimePlan timePlan;
    @XmlElement(name = "DataSize")
    protected DataSize dataSize;
    @XmlElement(name = "APIHande")
    protected ApiHande apiHande;
    @XmlElement(name = "Subscription")
    protected Subscription subscription;
    @XmlElement(name = "DataUnit")
    protected DataUnit dataUnit;

    /**
     * Gets the value of the timePlan property.
     * 
     * @return
     *     possible object is
     *     {@link TimePlan }
     *     
     */
    public TimePlan getTimePlan() {
        return timePlan;
    }

    /**
     * Sets the value of the timePlan property.
     * 
     * @param value
     *     allowed object is
     *     {@link TimePlan }
     *     
     */
    public void setTimePlan(TimePlan value) {
        this.timePlan = value;
    }

    /**
     * Gets the value of the dataSize property.
     * 
     * @return
     *     possible object is
     *     {@link DataSize }
     *     
     */
    public DataSize getDataSize() {
        return dataSize;
    }

    /**
     * Sets the value of the dataSize property.
     * 
     * @param value
     *     allowed object is
     *     {@link DataSize }
     *     
     */
    public void setDataSize(DataSize value) {
        this.dataSize = value;
    }

    /**
     * Gets the value of the apiHande property.
     * 
     * @return
     *     possible object is
     *     {@link ApiHande }
     *     
     */
    public ApiHande getAPIHande() {
        return apiHande;
    }

    /**
     * Sets the value of the apiHande property.
     * 
     * @param value
     *     allowed object is
     *     {@link ApiHande }
     *     
     */
    public void setAPIHande(ApiHande value) {
        this.apiHande = value;
    }

    /**
     * Gets the value of the subscription property.
     * 
     * @return
     *     possible object is
     *     {@link Subscription }
     *     
     */
    public Subscription getSubscription() {
        return subscription;
    }

    /**
     * Sets the value of the subscription property.
     * 
     * @param value
     *     allowed object is
     *     {@link Subscription }
     *     
     */
    public void setSubscription(Subscription value) {
        this.subscription = value;
    }

    /**
     * Gets the value of the dataUnit property.
     * 
     * @return
     *     possible object is
     *     {@link DataUnit }
     *     
     */
    public DataUnit getDataUnit() {
        return dataUnit;
    }

    /**
     * Sets the value of the dataUnit property.
     * 
     * @param value
     *     allowed object is
     *     {@link DataUnit }
     *     
     */
    public void setDataUnit(DataUnit value) {
        this.dataUnit = value;
    }

}

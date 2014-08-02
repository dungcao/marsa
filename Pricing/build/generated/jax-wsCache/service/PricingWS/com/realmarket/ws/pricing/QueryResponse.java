
package com.realmarket.ws.pricing;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for queryResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="queryResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="apihandle" type="{http://realmarket.com/ws/pricing}apiHande" minOccurs="0"/>
 *         &lt;element name="datasize" type="{http://realmarket.com/ws/pricing}dataSize" minOccurs="0"/>
 *         &lt;element name="dataunit" type="{http://realmarket.com/ws/pricing}dataUnit" minOccurs="0"/>
 *         &lt;element name="subscription" type="{http://realmarket.com/ws/pricing}subscription" minOccurs="0"/>
 *         &lt;element name="timeplan" type="{http://realmarket.com/ws/pricing}timePlan" minOccurs="0"/>
 *         &lt;element name="updatedtime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="version" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "queryResponse", propOrder = {
    "apihandle",
    "datasize",
    "dataunit",
    "subscription",
    "timeplan",
    "updatedtime",
    "version"
})
public class QueryResponse {

    protected ApiHande apihandle;
    protected DataSize datasize;
    protected DataUnit dataunit;
    protected Subscription subscription;
    protected TimePlan timeplan;
    protected String updatedtime;
    protected String version;

    /**
     * Gets the value of the apihandle property.
     * 
     * @return
     *     possible object is
     *     {@link ApiHande }
     *     
     */
    public ApiHande getApihandle() {
        return apihandle;
    }

    /**
     * Sets the value of the apihandle property.
     * 
     * @param value
     *     allowed object is
     *     {@link ApiHande }
     *     
     */
    public void setApihandle(ApiHande value) {
        this.apihandle = value;
    }

    /**
     * Gets the value of the datasize property.
     * 
     * @return
     *     possible object is
     *     {@link DataSize }
     *     
     */
    public DataSize getDatasize() {
        return datasize;
    }

    /**
     * Sets the value of the datasize property.
     * 
     * @param value
     *     allowed object is
     *     {@link DataSize }
     *     
     */
    public void setDatasize(DataSize value) {
        this.datasize = value;
    }

    /**
     * Gets the value of the dataunit property.
     * 
     * @return
     *     possible object is
     *     {@link DataUnit }
     *     
     */
    public DataUnit getDataunit() {
        return dataunit;
    }

    /**
     * Sets the value of the dataunit property.
     * 
     * @param value
     *     allowed object is
     *     {@link DataUnit }
     *     
     */
    public void setDataunit(DataUnit value) {
        this.dataunit = value;
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
     * Gets the value of the timeplan property.
     * 
     * @return
     *     possible object is
     *     {@link TimePlan }
     *     
     */
    public TimePlan getTimeplan() {
        return timeplan;
    }

    /**
     * Sets the value of the timeplan property.
     * 
     * @param value
     *     allowed object is
     *     {@link TimePlan }
     *     
     */
    public void setTimeplan(TimePlan value) {
        this.timeplan = value;
    }

    /**
     * Gets the value of the updatedtime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUpdatedtime() {
        return updatedtime;
    }

    /**
     * Sets the value of the updatedtime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUpdatedtime(String value) {
        this.updatedtime = value;
    }

    /**
     * Gets the value of the version property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersion() {
        return version;
    }

    /**
     * Sets the value of the version property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersion(String value) {
        this.version = value;
    }

}

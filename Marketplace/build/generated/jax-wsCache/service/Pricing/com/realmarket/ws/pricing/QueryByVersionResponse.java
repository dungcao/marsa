
package com.realmarket.ws.pricing;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for QueryByVersionResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="QueryByVersionResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="resp" type="{http://realmarket.com/ws/pricing}queryResponse" minOccurs="0" form="qualified"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "QueryByVersionResponse", propOrder = {
    "resp"
})
public class QueryByVersionResponse {

    @XmlElement(namespace = "http://realmarket.com/ws/pricing")
    protected QueryResponse resp;

    /**
     * Gets the value of the resp property.
     * 
     * @return
     *     possible object is
     *     {@link QueryResponse }
     *     
     */
    public QueryResponse getResp() {
        return resp;
    }

    /**
     * Sets the value of the resp property.
     * 
     * @param value
     *     allowed object is
     *     {@link QueryResponse }
     *     
     */
    public void setResp(QueryResponse value) {
        this.resp = value;
    }

}

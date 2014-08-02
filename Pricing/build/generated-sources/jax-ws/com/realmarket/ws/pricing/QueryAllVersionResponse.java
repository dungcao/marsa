
package com.realmarket.ws.pricing;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for QueryAllVersionResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="QueryAllVersionResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="resp" type="{http://realmarket.com/ws/pricing}queryResponse" maxOccurs="unbounded" minOccurs="0" form="qualified"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "QueryAllVersionResponse", propOrder = {
    "resp"
})
public class QueryAllVersionResponse {

    @XmlElement(namespace = "http://realmarket.com/ws/pricing")
    protected List<QueryResponse> resp;

    /**
     * Gets the value of the resp property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the resp property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getResp().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link QueryResponse }
     * 
     * 
     */
    public List<QueryResponse> getResp() {
        if (resp == null) {
            resp = new ArrayList<QueryResponse>();
        }
        return this.resp;
    }

}

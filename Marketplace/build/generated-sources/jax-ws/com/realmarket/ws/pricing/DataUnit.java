
package com.realmarket.ws.pricing;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dataUnit complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dataUnit">
 *   &lt;complexContent>
 *     &lt;extension base="{http://realmarket.com/ws/pricing}costbaseModel">
 *       &lt;sequence>
 *         &lt;element name="streams" type="{http://realmarket.com/ws/pricing}stream" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dataUnit", propOrder = {
    "streams"
})
public class DataUnit
    extends CostbaseModel
{

    @XmlElement(required = true)
    protected List<Stream> streams;

    /**
     * Gets the value of the streams property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the streams property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getStreams().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Stream }
     * 
     * 
     */
    public List<Stream> getStreams() {
        if (streams == null) {
            streams = new ArrayList<Stream>();
        }
        return this.streams;
    }

}

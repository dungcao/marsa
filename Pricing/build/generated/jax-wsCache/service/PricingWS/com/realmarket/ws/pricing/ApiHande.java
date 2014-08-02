
package com.realmarket.ws.pricing;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for apiHande complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="apiHande">
 *   &lt;complexContent>
 *     &lt;extension base="{http://realmarket.com/ws/pricing}costbaseModel">
 *       &lt;sequence>
 *         &lt;element name="objNum" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "apiHande", propOrder = {
    "objNum"
})
public class ApiHande
    extends CostbaseModel
{

    protected int objNum;

    /**
     * Gets the value of the objNum property.
     * 
     */
    public int getObjNum() {
        return objNum;
    }

    /**
     * Sets the value of the objNum property.
     * 
     */
    public void setObjNum(int value) {
        this.objNum = value;
    }

}

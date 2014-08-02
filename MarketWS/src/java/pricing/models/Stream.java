/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pricing.models;

import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author ttu01
 */
public class Stream{
    private String streamId;
    private PaymentType pType;
    private int value;
    private String unit; //no use if payment type is API handle

    @XmlElement(required = true)
    public String getStreamId() {
        return streamId;
    }

    public void setStreamId(String streamId) {
        this.streamId = streamId;
    }

    @XmlElement(required = true)
    public PaymentType getpType() {
        return pType;
    }

    public void setpType(PaymentType pType) {
        this.pType = pType;
    }

    @XmlElement(required = true)
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @XmlElement(required = true)
    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
    
    
}

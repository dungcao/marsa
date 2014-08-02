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
public class TimePlan extends CostbaseModel{

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
    @XmlElement(required = true)
    public int getDuration() {
        return duration;
    }
    
    @XmlElement(required = true)
    public String getUnit() {
        return unit;
    }
    
//    @XmlElement(required = true)
//    @Override
//    public int getbasicPrice()
//    {
//        return  super.getbasicPrice();
//    }
//    
//    @Override
//    public void setBasicPrice(int price)
//    {
//        super.setBasicPrice(price);
//    }
    
    private int duration;
    private String unit;
}

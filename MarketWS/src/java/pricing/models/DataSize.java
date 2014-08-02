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
public class DataSize extends CostbaseModel{
    private int size;
    private String unit;

    @XmlElement(required = true)
    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @XmlElement(required = true)
    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
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
}

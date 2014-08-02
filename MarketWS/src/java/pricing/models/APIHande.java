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
public class APIHande extends CostbaseModel{
    private int objNum;

    @XmlElement(required = true)
    public int getObjNum() {
        return objNum;
    }

    public void setObjNum(int objNum) {
        this.objNum = objNum;
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

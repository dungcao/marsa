/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pricing.models;

/**
 *
 * @author ttu01
 */
public class Period {
    private float extraPrice;
    private String fValue;
    private String tValue;
    private RepeatBy repeat;

    public float getExtraPrice() {
        return extraPrice;
    }

    public void setExtraPrice(float extraPrice) {
        this.extraPrice = extraPrice;
    }

    public String getfValue() {
        return fValue;
    }

    public void setfValue(String fValue) {
        this.fValue = fValue;
    }

    public String gettValue() {
        return tValue;
    }

    public void settValue(String tValue) {
        this.tValue = tValue;
    }

    public RepeatBy getRepeat() {
        return repeat;
    }

    public void setRepeat(RepeatBy repeat) {
        this.repeat = repeat;
    }
    
    
}

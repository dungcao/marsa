/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package api.market.broker;

import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author ttu01
 */
public class MaxBudget {
    private int max_budget;

    /**
     * 
     * @return 
     */
    @XmlElement(required = true)
    public int getMax_budget() {
        return max_budget;
    }

    /**
     * 
     * @param max_budget 
     */
    public void setMax_budget(int max_budget) {
        this.max_budget = max_budget;
    }
    
}

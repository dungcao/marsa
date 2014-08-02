/**
 * 
 */
package api.market.broker;

/**
 * @author ttu01
 *
 */
public class Payment {
    private MaxBudget pay_as_you_go;
    private MaxBudget pay_on_data_unit;
    private Duration pay_on_plan;

    /**
     * @return the pay_on_plan
     */
    public Duration getPay_on_plan() {
            return pay_on_plan;
    }
    /**
     * @param pay_on_plan the pay_on_plan to set
     */
    public void setPay_on_plan(Duration pay_on_plan) {
            this.pay_on_plan = pay_on_plan;
    }

    public MaxBudget getPay_as_you_go() {
        return pay_as_you_go;
    }

    public void setPay_as_you_go(MaxBudget pay_as_you_go) {
        this.pay_as_you_go = pay_as_you_go;
    }

    public MaxBudget getPay_on_data_unit() {
        return pay_on_data_unit;
    }

    public void setPay_on_data_unit(MaxBudget pay_on_data_unit) {
        this.pay_on_data_unit = pay_on_data_unit;
    }
}

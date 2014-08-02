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
public class QueryResponse {
    private APIHande apihandle;
    private DataUnit dataunit;
    private DataSize datasize;
    private TimePlan timeplan;
    private Subscription subscription;
    private String updatedtime;
    private String version;
    
    public String getUpdatedtime() {
        return updatedtime;
    }

    public void setUpdatedtime(String updatedtime) {
        this.updatedtime = updatedtime;
    }
    
    public APIHande getApihandle() {
        return apihandle;
    }

    public void setApihandle(APIHande apihandle) {
        this.apihandle = apihandle;
    }

    public DataUnit getDataunit() {
        return dataunit;
    }

    public void setDataunit(DataUnit dataunit) {
        this.dataunit = dataunit;
    }

    public DataSize getDatasize() {
        return datasize;
    }

    public void setDatasize(DataSize datasize) {
        this.datasize = datasize;
    }

    public TimePlan getTimeplan() {
        return timeplan;
    }

    public void setTimeplan(TimePlan timeplan) {
        this.timeplan = timeplan;
    }

    public Subscription getSubscription() {
        return subscription;
    }

    public void setSubscription(Subscription sub) {
        this.subscription = sub;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package api.market.broker;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author ttu01
 */
public class SubscribeReq {
    private String user; 
    private String pass;
    private String service_uuid;
    private List<String> stream_uuids;
    private String payment_model;
    
    @XmlElement(required = true)
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @XmlElement(required = true)
    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @XmlElement(required = true)
    public String getService_uuid() {
        return service_uuid;
    }

    public void setService_uuid(String service_uuid) {
        this.service_uuid = service_uuid;
    }

    /**
     *
     * @return
     */
    public List<String> getStream_uuids() {
        return stream_uuids;
    }

    public void setStream_uuids(List<String> stream_uuids) {
        this.stream_uuids = stream_uuids;
    }

    /**
     *
     * @return
     */
    @XmlElement(required = true)
    public String getPayment_model() {
        return payment_model;
    }

    public void setPayment_model(String payment_model) {
        this.payment_model = payment_model;
    }
}

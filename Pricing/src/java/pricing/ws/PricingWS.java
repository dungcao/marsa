/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pricing.ws;

import com.realmarket.ws.pricing.PricingService;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;

/**
 *
 * @author ttu01
 */
@WebService(serviceName = "PricingService", portName = "PricingPort", endpointInterface = "com.realmarket.ws.pricing.Pricing", targetNamespace = "http://realmarket.com/ws/pricing", wsdlLocation = "WEB-INF/wsdl/PricingWS/109.231.124.57_8080/ws/Pricing.wsdl")
public class PricingWS {

    public java.lang.String update(java.lang.String serviceId, com.realmarket.ws.pricing.TimePlan timePlan, com.realmarket.ws.pricing.DataSize dataSize, com.realmarket.ws.pricing.ApiHande apiHande, com.realmarket.ws.pricing.Subscription subscription, com.realmarket.ws.pricing.DataUnit dataUnit) {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public com.realmarket.ws.pricing.QueryResponse queryByVersion(java.lang.String serviceIdVersion) {
        URL url = null;
        try {
            //TODO implement this method
            url = new URL("http://localhost:8080/ws/Pricing?wsdl");
        } catch (MalformedURLException ex) {
            Logger.getLogger(PricingWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        PricingService ps = new PricingService(url);
        return ps.getPricingPort().queryByVersion(serviceIdVersion);
    }

    public java.util.List<com.realmarket.ws.pricing.QueryResponse> queryAllVersion(java.lang.String serviceId) {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public java.lang.String create(com.realmarket.ws.pricing.TimePlan timePlan, com.realmarket.ws.pricing.DataSize dataSize, com.realmarket.ws.pricing.ApiHande apiHande, com.realmarket.ws.pricing.Subscription subscription, com.realmarket.ws.pricing.DataUnit dataUnit) {
        //TODO implement this method
        URL url = null;
        try {
            //TODO implement this method
            url = new URL("http://localhost:8080/ws/Pricing?wsdl");
        } catch (MalformedURLException ex) {
            Logger.getLogger(PricingWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        PricingService ps = new PricingService(url);
        return  ps.getPricingPort().create(timePlan, dataSize, apiHande, subscription, dataUnit);
    }
    
}

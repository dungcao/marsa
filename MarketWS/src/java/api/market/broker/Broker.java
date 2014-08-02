/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package api.market.broker;

import java.util.ArrayList;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;
/**
 *
 * @author ttu01
 */
@WebService(serviceName = "Broker", targetNamespace = "http://realmarket.com/ws/broker")
public class Broker {

    /**
     * Allow consumer to search the services on the marketplace
     * @param req
     * @return 
     */
    @WebMethod(operationName = "AdvancedSearch")
    @WebResult(name = "resp", targetNamespace = "http://realmarket.com/ws/broker")
    public SearchResp AdvancedSearch(@XmlElement(required = true) @WebParam(name = "request") AdvancedSearchReq req) {
        //TODO write your implementation code here:
        List<Stream> streamList = new ArrayList<>();
        Stream stream = new Stream();
        stream.setUuid("service-uuid00001/stream-uuid001");
        stream.setDescription("Bus 627");
        stream.setPubsub_url("www.dungcao.com:1883");
        stream.setDevice("Smartphone/Nokia Lumia N900");
        stream.setTimeseries("10:s");
        stream.setDatatype("http://example.org/schema/type");
        stream.setDataorigin("YES (HCM, VN)");
        
        streamList.add(stream);
        
        SearchResp resp = new SearchResp();
        resp.setServiceUUID("service/uuid100001");
        resp.setServiceName("Example");
        resp.setProvider("Tan Tao Univ");
        resp.setPricing_model("pay on subscription (1$/Mb)");
        resp.setQoS("http://xxxx.org/QoS/yyy");
        resp.setStreams(streamList);
        resp.setDescription("This method temporally doesn't support yet! This is default values.");
        return resp;
    }
    
    /**
     * return list of services where either their service name, service description,
     * provider, stream device name, or stream description contains 'keyword'.  
     * @param keyword
     * @return SearchResp (list of services)
     */
    @WebMethod(operationName = "SimpleSearch")
    @WebResult(name = "resp", targetNamespace = "http://realmarket.com/ws/broker")
    public SearchResp SimpleSearch(@XmlElement(required = true) @WebParam(name = "keyword") String keyword) {
        //TODO write your implementation code here:
        List<Stream> streamList = new ArrayList<>();
        Stream stream = new Stream();
        stream.setUuid("service-uuid00001/stream-uuid001");
        stream.setDescription("Bus 627");
        stream.setPubsub_url("www.dungcao.com:1883");
        stream.setDevice("Smartphone/Nokia Lumia N900");
        stream.setTimeseries("10:s");
        stream.setDatatype("http://example.org/schema/type");
        stream.setDataorigin("YES (HCM, VN)");
        
        streamList.add(stream);
        
        SearchResp resp = new SearchResp();
        resp.setServiceUUID("service/uuid100001");
        resp.setServiceName("Example");
        resp.setProvider("Tan Tao Univ");
        resp.setPricing_model("pay on subscription (1$/Mb)");
        resp.setQoS("http://xxxx.org/QoS/yyy");
        resp.setStreams(streamList);
        resp.setDescription("This method temporally doesn't support yet! This is default values.");
        return resp;
    }
    
    /**
     * Use to subscribe a service on the marketplace, this operation will generate a data contract.
     * @param req
     * @return true/false
     */
    @WebMethod(operationName = "Subscribe")
    @WebResult(name = "resp", targetNamespace = "http://realmarket.com/ws/broker")
    public boolean Subscribe(@XmlElement(required = true) @WebParam(name="subReq") SubscribeReq req)
    {
        //TODO
        return true;
    }
}

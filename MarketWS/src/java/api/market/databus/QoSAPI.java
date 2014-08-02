/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package api.market.databus;

import database.MySQL;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.xml.bind.annotation.XmlElement;
import java.sql.ResultSet;
/**
 *
 * @author ttu01
 */
@WebService(serviceName = "QoS", targetNamespace = "http://realmarket.com/ws/qos")
public class QoSAPI {
    //database variable
    private final MySQL mysql = new MySQL();
    
    /**
     * Post timestamp to marketplace 
     * @param msgId
     * @param streamuuid
     * @param user
     * @param role
     */
    @WebMethod(operationName = "timestamp")
    public void timestamp(@XmlElement(required = true) @WebParam(name = "msgId") long msgId,
            @XmlElement(required = true) @WebParam(name = "streamuuid") String streamuuid,
            @XmlElement(required = true) @WebParam(name = "user") String user,
            @XmlElement(required = true) @WebParam(name = "role") Role role) {
        //TODO write your implementation code here:
        if (role == Role.PUBLISHER) {
            String sql = "INSERT INTO log_pub_timestamp(stream_uuid,timestamp,messageid) VALUES(\'" + streamuuid +"\',"
                            + System.currentTimeMillis() +","+ msgId +")";
            try {
                mysql.execute(sql);
            } catch (SQLException ex) {
                Logger.getLogger(QoSAPI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            String sql = "INSERT INTO log_sub_timestamp(username,stream_uuid,timestamp,messageid) VALUES(\'" + user + "\',\'" + streamuuid +"\',"
                            + System.currentTimeMillis() +","+ msgId +")";
            try {
                mysql.execute(sql);
            } catch (SQLException ex) {
                Logger.getLogger(QoSAPI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    /**
     * Post a message which is randomly chosen by API to marketplace to validate with its schema (Not support yet)
     * @param msg
     * @param streamuuid
     */
    @WebMethod(operationName = "validSchema")
    public void validSchema(@XmlElement(required = true) @WebParam(name = "message") String msg,
            @XmlElement(required = true) @WebParam(name = "streamuuid") String streamuuid){
        //TODO
    }
    
    /**
     * Update data rate of a stream (Not support yet)
     * @param rate
     * @param streamuuid 
     */
    @WebMethod(operationName = "setDataRate")
    public void setDataRate(@XmlElement(required = true) @WebParam(name = "rate") int rate,
            @XmlElement(required = true) @WebParam(name = "streamuuid") String streamuuid){
        //TODO
    }
    
    /**
     * get max message_id from marketplace
     * @param streamuuid
     * @return
     */
    @WebMethod(operationName = "getMeesageId")
    public long getMeesageId(@XmlElement(required = true) @WebParam(name = "streamuuid") String streamuuid){
        long resp = 1;
        String sql = "SELECT max(messageid) as maxId FROM log_pub_timestamp WHERE stream_uuid=\'" + streamuuid + "\'";
        try {
            ResultSet rs = mysql.query(sql);
            if(rs.absolute(1)){
                resp = rs.getLong("maxId");
            }
        } catch (SQLException ex) {
            Logger.getLogger(QoSAPI.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resp;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package api.market.databus;

import api.market.broker.Duration;
import database.MySQL;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author ttu01
 */
@WebService(serviceName = "Payment", targetNamespace = "http://realmarket.com/ws/payment")
public class PaymentAPI {
    //database variable
    private final MySQL mysql = new MySQL();
    /**
     * Notify marketplace whenever API subscribes data from the data buses
     * @param user String
     * @param uuid  String
     */
    @WebMethod(operationName = "subscribe")
    public void subscribe(@XmlElement(required = true) @WebParam(name = "user") String user, 
            @XmlElement(required = true) @WebParam(name = "streamuuid") String uuid) {
        
        try {
            String sql = "INSERT INTO log_subscription(user, stream_uuid, start_time) VALUES(\'"+ user +"\',\'"+ uuid +"\', NOW())";
            mysql.execute(sql);
        } catch (SQLException ex) {
            Logger.getLogger(PaymentAPI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Notify marketplace whenever API unsubscribes data
     * @param user String   
     * @param uuid  String
     */
    @WebMethod(operationName = "unsubscribe")
    public void unsubscribe(@XmlElement(required = true) @WebParam(name = "user") String user, 
            @XmlElement(required = true) @WebParam(name = "streamuuid") String uuid){
        try {
            String sql = "UPDATE log_subscription AS A, (SELECT MAX(start_time) AS maxtime, user, stream_uuid " +
                                                        "FROM log_subscription WHERE user= \'" + user + "\' AND stream_uuid=\'" + uuid + "\') AS B " +
                         " SET end_time=NOW()" +
                         " WHERE A.user= B.user AND A.stream_uuid = B.stream_uuid AND A.start_time = B.maxtime";
                    
            mysql.execute(sql);
        } catch (SQLException ex) {
            Logger.getLogger(PaymentAPI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Notify the number of received messages
     * @param user
     * @param streamuuid
     * @param number 
     */
    @WebMethod(operationName = "setMessageCounter")
    public void setMessageCounter(@XmlElement(required = true) @WebParam(name = "user") String user,
            @XmlElement(required = true) @WebParam(name = "streamuuid") String streamuuid, 
            @XmlElement(required = true) @WebParam(name="msgNumber") int number)
    {
        //TODO
        String sql = "UPDATE log_received_data SET message_count = message_count + " + number +
                    " WHERE DATE(date_time)=DATE(NOW()) AND HOUR(date_time) = HOUR(NOW()) AND" +
                    " user=\'" + user +"\' AND stream_uuid=\'" + streamuuid + "\'";
        try {
            if(mysql.execute(sql)==0){
                sql = "INSERT INTO log_received_data(user, stream_uuid, date_time, message_count) VALUES(\'"+user+"\',\'"+streamuuid +"\', NOW(), "+ number + ")";
                mysql.execute(sql);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PaymentAPI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Notify the size of data
     * @param user
     * @param streamuuid
     * @param size 
     * @return  true/false, false if users have been reached their limitation
     */
    @WebMethod(operationName = "setDataSizeCounter")
    public boolean setDataSizeCounter(@XmlElement(required = true) @WebParam(name = "user") String user,
            @XmlElement(required = true) @WebParam(name = "streamuuid") String streamuuid, 
            @XmlElement(required = true) @WebParam(name="dataSize") long size)
    {
        //TODO
        String sql = "UPDATE log_received_data SET data_size = data_size + " + size +
                    " WHERE DATE(date_time)=DATE(NOW()) AND HOUR(date_time)=HOUR(NOW()) AND" +
                    " user=\'" + user +"\' AND stream_uuid=\'" + streamuuid + "\'";
        try {
            if(mysql.execute(sql)==0){
                sql = "INSERT INTO log_received_data(user, stream_uuid, date_time, data_size) VALUES(\'"+user+"\',\'"+streamuuid +"\', NOW(), "+ size + ")";
                mysql.execute(sql);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PaymentAPI.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    
    /**
     * Not support yet, wait contract model implementation
     * @param user
     * @param streamuuid
     * @return a duration of time
     */
    @WebMethod(operationName = "timeplan")
    public Duration timeplan(@XmlElement(required = true) @WebParam(name = "user") String user,
            @XmlElement(required = true) @WebParam(name = "streamuuid") String streamuuid)
    {
        Duration plan = new Duration();
        Calendar c = Calendar.getInstance();
        plan.setBegin(c.getTime());
        c.add(Calendar.DATE, 7);
        plan.setEnd(c.getTime());
        return  plan;
    }
}

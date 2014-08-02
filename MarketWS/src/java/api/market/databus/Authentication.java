/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package api.market.databus;

import database.MySQL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
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
@WebService(serviceName = "Authentication", targetNamespace = "http://realmarket.com/ws/authentication")
public class Authentication {
    //database variable
    private final MySQL mysql = new MySQL();
    /**
     * Login operation
     * @param user
     * @param pass
     * @param role
     * @return session-id, null if fail
     */
    @WebMethod(operationName = "login")
    public String login(@XmlElement(required = true) @WebParam(name = "user") String user, 
            @XmlElement(required = true) @WebParam(name = "pass") String pass, 
            @XmlElement(required = true) @WebParam(name ="role") Role role) {
        //TODO write your implementation code here:
        String sql = "SELECT user FROM tbl_users WHERE user = \'" + user + "\' AND pass=\'" + MD5(pass) + "\' and role=\'" + (role==Role.PUBLISHER?"P":"S") +"\'";
        String sessionid = null;
        try {
            ResultSet rs = mysql.query(sql);
            if(rs.absolute(1)){
                if(rs.getString("user").equals(user)){
                    sessionid = MD5(user + System.currentTimeMillis());
                    //appname: S -> Web Service; W -> Web Servlet
                    if(mysql.execute("UPDATE util_logged_users SET session_id=\'" + sessionid + "\', logged_time=NOW() WHERE user=\'" + user +"\'") == 0)
                        mysql.execute("INSERT INTO util_logged_users(user, session_id, logged_time, appname) VALUES(\'"+user+"\',\'"+sessionid+"\',NOW(),'S')");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(QoSAPI.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sessionid;
    }
    
    /**
     *
     * @param sessionid
     * @return
     */
    @WebMethod(operationName = "logout")
    public boolean logout(@XmlElement(required = true) @WebParam(name = "session_id") String sessionid){
        String sql = "DELETE FROM util_logged_users WHERE session_id=\'" + sessionid + "\' AND appname='S'";
        try {
            if (mysql.execute(sql) > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Authentication.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    /**
     *
     * @param sessionid
     * @return
     */
    @WebMethod(operationName = "verify")
    public boolean verify(@XmlElement(required = true) @WebParam(name = "session_id") String sessionid){
        String sql = "SELECT user FROM util_logged_users WHERE session_id=\'" + sessionid + "\' AND appname='S'";
        try {
            ResultSet rs = mysql.query(sql);
            if (rs.absolute(1)) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Authentication.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    /**
     * Return a list of valid stream for current session
     * @param session_id
     * @return stream_uuids
     */
    @WebMethod(operationName = "getValidStreams")
    public List<String> getStreamIds(@XmlElement(required = true) @WebParam(name = "session_id") String session_id)
    {
        return null;
    }
    
    private static String MD5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < array.length; ++i) {
              sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
           }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        }
        return null;
    }
}

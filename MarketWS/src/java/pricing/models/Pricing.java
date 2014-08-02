/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pricing.models;

import api.market.databus.QoSAPI;
import database.MySQL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author ttu01
 */
@WebService(serviceName = "PricingService", targetNamespace = "http://realmarket.com/ws/pricing")
public class Pricing {
    
    @WebMethod(operationName = "Create")
    @WebResult(name = "serviceId", targetNamespace = "http://realmarket.com/ws/pricing")
    public String Create(@WebParam(name = "TimePlan") TimePlan time,
            @WebParam(name = "DataSize") DataSize size,
            @WebParam(name = "APIHande") APIHande api,
            @WebParam(name = "Subscription") Subscription sub,
            @WebParam(name = "DataUnit") DataUnit unit){
        String sid = "sid" + System.currentTimeMillis();
        int vid = save(sid, time, size, api, sub, unit, false);
        return sid + "/v" + vid;
    }
    
    @WebMethod(operationName = "Update")
    @WebResult(name = "serviceId_version", targetNamespace = "http://realmarket.com/ws/pricing")
    public String Update(@XmlElement(required = true) @WebParam(name = "serviceId") String sid,
            @WebParam(name = "TimePlan") TimePlan time,
            @WebParam(name = "DataSize") DataSize size,
            @WebParam(name = "APIHande") APIHande api,
            @WebParam(name = "Subscription") Subscription sub,
            @WebParam(name = "DataUnit") DataUnit unit){
        int index = sid.indexOf("/");
        if (index > 0) {
            sid = sid.substring(0, index);
        }
        int vid = save(sid, time, size, api, sub, unit, true);
        return sid + "/v"+ vid;
    }
    
    @WebMethod(operationName = "QueryByVersion")
    @WebResult(name = "resp", targetNamespace = "http://realmarket.com/ws/pricing")
    public QueryResponse QueryByVersion(@XmlElement(required = true) @WebParam(name = "serviceId_version") String sid){
        String vid;
        int index = sid.indexOf("/v");
        if (index > 0) {
            vid = sid.substring(index+2);
            sid = sid.substring(0, index);
        }else 
            vid = "1";
        
        return getByVersion(sid, vid);
    }
    
    @WebMethod(operationName = "QueryAllVersion")
    @WebResult(name = "resp", targetNamespace = "http://realmarket.com/ws/pricing")
    public ArrayList<QueryResponse> QueryAllVersion(@XmlElement(required = true) @WebParam(name = "serviceId") String sid){
        MySQL mysql = new MySQL();
        int index = sid.indexOf("/v");
        if (index > 0) {
            sid = sid.substring(0, index);
        }
        int maxvid = 1;
        
        //get last version of serice
        //query from 5 tables and get the maximum number
        String sql = "SELECT MAX( vid ) AS maxid FROM ( " +
                                    "SELECT MAX( vid ) AS vid FROM  tbl_CostDataUnit WHERE sid = '" +  sid +"' UNION " + 
                                    "SELECT MAX( vid ) AS vid FROM  tbl_CostDataSize WHERE sid = '" +  sid +"' UNION " + 
                                    "SELECT MAX( vid ) AS vid FROM  tbl_CostAPIHandle WHERE sid = '" +  sid +"' UNION " +  
                                    "SELECT MAX( vid ) AS vid FROM  tbl_CostTimeplan WHERE sid = '" +  sid +"' UNION " +  
                                    "SELECT MAX( vid ) AS vid FROM  tbl_CostSubscription WHERE sid = '" +  sid +"' ) AS Uni"; 
            try {
                ResultSet rs = mysql.query(sql);
                if(rs.absolute(1)){
                    maxvid = rs.getInt("maxid");
                }
            } catch (SQLException ex) {
                Logger.getLogger(QoSAPI.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        ArrayList<QueryResponse> rs = new ArrayList<>();
        for (int i = 0; i < maxvid; i++) {
            rs.add(getByVersion(sid, i+1 + ""));
        }
        
        return rs;
    }
    
    private int save(String sid, TimePlan time, DataSize size, APIHande api, Subscription sub, DataUnit unit, boolean isUpdated)
    {
        MySQL mysql = new MySQL();
        String vid = "1";
        if (isUpdated) {
            String sql = "SELECT MAX( vid ) + 1 AS maxid FROM ( " +
                                    "SELECT MAX( vid ) AS vid FROM  tbl_CostDataUnit WHERE sid = '" +  sid +"' UNION " + 
                                    "SELECT MAX( vid ) AS vid FROM  tbl_CostDataSize WHERE sid = '" +  sid +"' UNION " + 
                                    "SELECT MAX( vid ) AS vid FROM  tbl_CostAPIHandle WHERE sid = '" +  sid +"' UNION " +  
                                    "SELECT MAX( vid ) AS vid FROM  tbl_CostTimeplan WHERE sid = '" +  sid +"' UNION " +  
                                    "SELECT MAX( vid ) AS vid FROM  tbl_CostSubscription WHERE sid = '" +  sid +"' ) AS Uni"; 
            try {
                ResultSet rs = mysql.query(sql);
                if(rs.absolute(1)){
                    vid = rs.getString("maxid");
                }
            } catch (SQLException ex) {
                Logger.getLogger(QoSAPI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if (time != null) {
            String sql = "INSERT INTO tbl_CostTimeplan(sid, vid, price, duration, unit) " +
                    "VALUES ('"+ sid +"',"+ vid + ","+ time.getBasicPrice()+","+ time.getDuration()+",'"+ time.getUnit()+"')";
            try {
                mysql.execute(sql);
            } catch (SQLException ex) {
                Logger.getLogger(Pricing.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (size != null) {
            String sql = "INSERT INTO tbl_CostDataSize(sid, vid, price, size, unit) " +
                    "VALUES ('"+ sid +"',"+ vid + ","+ size.getBasicPrice()+","+ size.getSize()+",'"+ size.getUnit()+"')";
            try {
                mysql.execute(sql);
            } catch (SQLException ex) {
                Logger.getLogger(Pricing.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (api != null) {
            String sql = "INSERT INTO tbl_CostAPIHandle(sid, vid, price, objNum) " +
                    "VALUES ('"+ sid +"',"+ vid + ","+ api.getBasicPrice()+","+ api.getObjNum()+")";
            try {
                mysql.execute(sql);
            } catch (SQLException ex) {
                Logger.getLogger(Pricing.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (unit != null && unit.getStreams() != null) {
            float price = unit.getBasicPrice();
            for (Stream s : unit.getStreams()) {
                if(s != null){
                    String sql = "INSERT INTO tbl_CostDataUnit(sid, vid, price, streamid, pplan, value, unit) " +
                        "VALUES ('"+ sid +"',"+ vid + ","+ price+",'"+ s.getStreamId() +"','"+ s.getpType()+"',"+ s.getValue()+",'"+s.getUnit() +"')";
                    try {
                        mysql.execute(sql);
                    } catch (SQLException ex) {
                        Logger.getLogger(Pricing.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            
        }

        if (sub != null) {
            String sql = "INSERT INTO tbl_CostSubscription(sid, vid, price, duration, unit) " +
                    "VALUES ('"+ sid +"',"+ vid + ","+ sub.getBasicPrice()+",'"+ sub.getDuration() +"','"+ sub.getUnit() +"')";
            try {
                mysql.execute(sql);
            } catch (SQLException ex) {
                Logger.getLogger(Pricing.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (sub.getImportants() != null) {
                for (Period p : sub.getImportants()) {
                    if(p != null){
                        sql = "INSERT INTO tbl_CostPeriod(sid, vid, exp, fvalue, tvalue, repeatBy) " + 
                            "VALUES ('"+ sid +"',"+ vid + ","+ p.getExtraPrice()+","+ getIntValue(p,true) +","+ getIntValue(p,false) +",'"+ p.getRepeat() +"')\n";
                        try {
                            mysql.execute(sql);
                        } catch (SQLException ex) {
                            Logger.getLogger(Pricing.class.getName()).log(Level.SEVERE, null, ex);
                        }    
                    }
                }
                
            }
        }
        return Integer.parseInt(vid);
    }
    
    private QueryResponse getByVersion(String sid, String vid){
        MySQL mysql = new MySQL();
        QueryResponse qr = new QueryResponse();
        String updateddate = null;
        String sql = "SELECT price, objNum, createdate FROM tbl_CostAPIHandle WHERE sid='"+sid+"' AND vid=" + vid;
        try {
            ResultSet rs = mysql.query(sql);
            if(rs.absolute(1)){
                APIHande api = new APIHande();
                api.setBasicPrice(rs.getFloat("price"));
                api.setObjNum(rs.getInt("objNum"));
                updateddate = rs.getString("createdate");
                qr.setApihandle(api);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QoSAPI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        sql = "SELECT price, size, unit, createdate FROM tbl_CostDataSize WHERE sid='"+sid+"' AND vid=" + vid;
        try {
            ResultSet rs = mysql.query(sql);
            if(rs.absolute(1)){
                DataSize size = new DataSize();
                size.setBasicPrice(rs.getFloat("price"));
                size.setSize(rs.getInt("size"));
                size.setUnit(rs.getString("unit"));
                updateddate = rs.getString("createdate");
                qr.setDatasize(size);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QoSAPI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        sql = "SELECT price, duration, unit, createdate FROM tbl_CostTimeplan WHERE sid='"+sid+"' AND vid=" + vid;
        try {
            ResultSet rs = mysql.query(sql);
            if(rs.absolute(1)){
                TimePlan time = new TimePlan();
                time.setBasicPrice(rs.getFloat("price"));
                time.setDuration(rs.getInt("duration"));
                time.setUnit(rs.getString("unit"));
                updateddate = rs.getString("createdate");
                qr.setTimeplan(time);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QoSAPI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        sql = "SELECT price, streamid, pplan, value, unit, createdate FROM tbl_CostDataUnit WHERE sid='"+sid+"' AND vid=" + vid;
        try {
            ResultSet rs = mysql.query(sql);
            if(rs.absolute(1)){
                DataUnit unit = new DataUnit();
                unit.setBasicPrice(rs.getFloat("price"));
                updateddate = rs.getString("createdate");
                ArrayList<Stream> streamList = new ArrayList<>();
                do{
                    Stream s = new Stream();
                    s.setStreamId(rs.getString("streamid"));
                    s.setUnit(rs.getString("unit"));
                    s.setValue(rs.getInt("value"));
                    s.setpType(getPaymentType(rs.getString("pplan")));
                    streamList.add(s);
                }while(rs.next());
                unit.setStreams(streamList);
                qr.setDataunit(unit);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QoSAPI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        sql = "SELECT price, duration, unit, createdate FROM tbl_CostSubscription WHERE sid='"+sid+"' AND vid=" + vid;
        try {
            ResultSet rs = mysql.query(sql);
            if(rs.absolute(1)){
                Subscription sub = new Subscription();
                sub.setBasicPrice(rs.getFloat("price"));
                sub.setDuration(rs.getInt("duration"));
                sub.setUnit(rs.getString("unit"));
                updateddate = rs.getString("createdate");
                
                ArrayList<Period> pList =  new ArrayList<>();
                sql = "SELECT exp, fvalue, tvalue, repeatBy FROM tbl_CostPeriod WHERE sid='"+sid+"' AND vid=" + vid;
                rs = mysql.query(sql);
                while (rs.next()) {                    
                    Period p = new Period();
                    p.setExtraPrice(rs.getFloat("exp"));
                    p.setfValue(getStringValue(rs.getString("repeatBy"), rs.getInt("fvalue")));
                    p.settValue(getStringValue(rs.getString("repeatBy"), rs.getInt("tvalue")));
                    p.setRepeat(getRepeatType(rs.getString("repeatBy")));
                    pList.add(p);
                }
                
                if (pList.size() > 0) {
                    sub.setImportants(pList);
                }
                qr.setSubscription(sub);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QoSAPI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        qr.setUpdatedtime(updateddate);
        qr.setVersion(vid);
        return qr;
    }
    
    private int getIntValue(Period p, boolean from){
        int value = 0;
        String  svalue;
        if (from) {
            svalue = p.getfValue().toUpperCase();
        }else
            svalue = p.gettValue().toUpperCase();
        
        if (p.getRepeat().equals(RepeatBy.DAY)) {
            int index = svalue.indexOf(":");
            if (index > 0) {
                value = Integer.parseInt(svalue.substring(0, index)) * 60 + Integer.parseInt(svalue.substring(index+1,index+3));
            }else
                value = Integer.parseInt(svalue) * 60;
        }else if(p.getRepeat().equals(RepeatBy.MONTH)){
            value = Integer.parseInt(svalue);
        }else if(p.getRepeat().equals(RepeatBy.WEEK)){
            if (svalue.startsWith("SUN")) {
                value =  1;
            }else if (svalue.startsWith("MON")) {
                value = 2;
            }else if (svalue.startsWith("TUE")) {
                value = 3;
            }else if (svalue.startsWith("WED")) {
                value = 4;
            }else if (svalue.startsWith("THU")) {
                value = 5;
            }else if (svalue.startsWith("FRI")) {
                value = 6;
            }else if (svalue.startsWith("SAT")) {
                value = 7;
            }
        }
        return value;
    }
    
    private String getStringValue(String repeat, int value){
        RepeatBy type = getRepeatType(repeat);
        String result = "";
        
        if (type == RepeatBy.MONTH) {
            result = value + "";
        }else if (type == RepeatBy.DAY) {
            result = value / 60 + ":" + String.format("%02d", value % 60 );
        } else if (type == RepeatBy.WEEK) {
            switch(value){
                case 1: 
                    result = "SUNDAY";
                    break;
                case 2:
                    result = "MONDAY";
                    break;
                case 3:
                    result = "TUESDAY";
                    break;
                case 4:
                    result = "WEDNESDAY";
                    break;
                case 5:
                    result = "THURSDAY";
                    break;
                case 6:
                    result = "FRIDAY";
                    break;
                case 7:
                    result = "SATURDAY";
                    break;
            }
        }
        
        return result;
    }
    
    private PaymentType getPaymentType(String s)
    {
        if (s.startsWith("DATA")) {
            return PaymentType.DATA_SIZE;
        }else if (s.startsWith("API")) {
            return  PaymentType.API_HANDLE;
        }else return PaymentType.TIME_PLAN;
    }
    
    private RepeatBy getRepeatType(String s)
    {
        if (s.startsWith("DAY")) {
            return RepeatBy.DAY;
        }else if (s.startsWith("WEEK")) {
            return RepeatBy.WEEK;
        }else return RepeatBy.MONTH;
    }
}

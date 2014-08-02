/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package market.servlet;

import database.MySQL;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.Constants;
import utils.HTMLUtils;

/**
 *
 * @author ttu01
 */
public class Service extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        MySQL mysql = new MySQL();
        
        Cookie[] cookie = request.getCookies();
        if (cookie != null) {
            String sessionid = "";
            for (Cookie cookie1 : cookie) {
                if (cookie1.getName().equals(Constants.SESSION_ID_COOKIE)) {
                    sessionid = cookie1.getValue();
                    break;
                }
            }
            String sql = "SELECT A.role FROM marketplace.tbl_users AS A INNER JOIN util_logged_users AS B" +
                    " ON A.user=B.user WHERE session_id=\'"+sessionid+"\'";
            
            String role = "P"; // provider
            if (request.getParameter("role") == null) {
                try {
                    try (ResultSet rs = mysql.query(sql)) {
                        if (rs.absolute(1)) {
                            role = rs.getString("role");
                        }
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if(role.equals("P"))
                processServiceWithoutLogging(request, response, sessionid, mysql);
            else
                //allow to subscribe if user has the consumer role
                processServiceLoggedIn(request, response, mysql);
        }else{
            processServiceWithoutLogging(request, response, null, mysql);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void processServiceLoggedIn(HttpServletRequest request, HttpServletResponse response, MySQL mysql) throws IOException{
        String uuid = request.getParameter("uuid");
        PrintWriter out = response.getWriter();
        HTMLUtils htmlUtils = new HTMLUtils();
        out.println(htmlUtils.getHtmlHeader());
        out.println(htmlUtils.getTitle("Realtime data marketplace", 100, 100));
        out.println("<script src=\"./scripts/script.js\" type=\"text/javascript\"></script>");
        out.println(htmlUtils.getTableHead("center", 0));
        out.println("<TR><TD>&nbsp;</TD></TR>");
        out.println("<TR><TD align = left><a href='./home'><h1>Realtime Data Marketplace</h1></a></TD></TR>");
        out.println("<TR><TD align = left><h3>Service Information</h3></TD></TR>");

        String sql = "SELECT Name, Provider, Description, CostURL, Databus, CategoryListIds, ContractListIds, QoSListIds, registered_date FROM tbl_Services WHERE UUID=\'" + uuid +"\'";
        String catlist = "";
        String conlist = "";
        String qoslist = "";
        try {
            out.println("<TR> <TD>");
            out.println(htmlUtils.getTableHead("left", 0));

            ResultSet rs = mysql.query(sql);
            if(rs.absolute(1))
            {
                out.println("<TR> <TD>ServiceUUID </TD> <TD>" + uuid + "</TD> </TR>");
                out.println("<TR> <TD>Service Name </TD> <TD>" + rs.getString("Name") + "</TD> </TR>");
                out.println("<TR> <TD>Provider</TD> <TD>" + rs.getString("Provider") + "</TD> </TR>");
                out.println("<TR> <TD>Description </TD> <TD>" + rs.getString("Description") + "</TD> </TR>");
                out.println("<TR> <TD>Databus for subscription </TD> <TD>" + rs.getString("Databus") + "</TD> </TR>");
                String curl = rs.getString("CostURL");
                out.println("<TR> <TD valign='top'>Cost for service</TD> <TD>" + (curl != null? "<a href=\'" + curl + "\'> " + curl +" </a>":"Not available") +"</TD> </TR>");
                out.println("<TR> <TD>Service is registered on </TD> <TD>" + rs.getString("registered_date").substring(0, 10) + "</TD> </TR>");
                catlist = rs.getString("CategoryListIds");
                conlist = rs.getString("ContractListIds");
                qoslist = rs.getString("QoSListIds");
            }
            rs.close();

            //query categories
            out.println("<TR> <TD>Categories </TD>");
            if (catlist != null) {
                catlist = catlist.replaceAll(",", "\',\'");
                //get categories
                sql = "SELECT Term, Scheme FROM tbl_Categories WHERE ID IN(\'"+ catlist.substring(1, catlist.length()-1) + "\')";
                rs = mysql.query(sql);

                catlist = "";
                while (rs.next()) {
                    catlist += "<a href=\'"+ rs.getString("Scheme") +"\'> " + rs.getString("Term") + " </a>;";
                }  
                rs.close();
            }else 
                catlist = "Not available";
            out.println("<TD>" +catlist+" </TD> </TR> ");

            //query QoS
            out.println("<TR> <TD>Quality of Service models</TD>");
            if(qoslist != null){
                qoslist = qoslist.replaceAll(",", "\',\'");
                //get qos
                sql = "SELECT Name, SourceURL FROM tbl_QoS WHERE Id IN(\'"+ qoslist.substring(1, qoslist.length()-1) + "\')";
                rs = mysql.query(sql);

                qoslist = "";
                while (rs.next()) {
                    qoslist += "<a href=\'"+ rs.getString("SourceURL") +"\'> " + rs.getString("Name") + " </a>;";
                }
                rs.close();
            }else
                qoslist = "Not available";
            out.println("<TD>" +qoslist+ " </TD> </TR> ");

            //get contract
            out.println("<TR> <TD>Contract models </TD>");
            if(conlist != null){
                conlist = conlist.replaceAll(",", "\',\'");

                sql = "SELECT Name, SourceURL FROM tbl_ContractModel WHERE Id IN(\'"+ conlist.substring(1, conlist.length()-1) + "\')";
                rs = mysql.query(sql);

                conlist = "";
                while (rs.next()) {
                    conlist += "<a href=\'"+ rs.getString("SourceURL") +"\'> " + rs.getString("Name") + " </a>;";
                }
                rs.close();
            }else
                conlist = "Not available";

            out.println("<TD>" +conlist+" </TD> </TR> ");

            out.println("<form method=\'post\' action=\'./subscribe?uuid="+ uuid+"\'>");
            //start stream row
            out.println("<TR> <TD valign='top'> Data streams</TD><TD></TD></TR>");
            out.println("<TR> <TD colspan=2>");
            out.println(htmlUtils.getTableHead("left", 0));
            
            out.println(htmlUtils.getTH("left", "<input id=\'check_all_box\' type=\'checkbox\' onclick=\'checkboxAll()\'> All"));
            out.println(htmlUtils.getTH("left", "UUID"));
            out.println(htmlUtils.getTH("left", "Description"));
            out.println(htmlUtils.getTH("left", "Device"));
            out.println(htmlUtils.getTH("left", "Data type"));
            out.println(htmlUtils.getTH("left", "Data bus"));
            out.println(htmlUtils.getTH("left", "Cost"));
            out.println(htmlUtils.getTH("left", "Time series"));
            out.println(htmlUtils.getTH("left", "Rate"));
            out.println(htmlUtils.getTH("left", "Latency"));
            out.println(htmlUtils.getTH("left", "Origin"));
            
            sql = "SELECT A.StreamUUID, A.Description, A.Databus, A.CostURL, A.TimeSeries, A.DataOrigin,A.DataRate,A.Latency, B.Type AS DataType,B.SourceURL AS DataTypeURL ,C.Type AS DeviceType, C.SourceUrl AS DeviceURL" +
                  " FROM tbl_Streams AS A LEFT JOIN tbl_DataType AS B ON A.DataTypeID=B.ID" +
                  " LEFT JOIN tbl_Devices AS C ON A.DeviceID=C.Id" +
                  " WHERE A.ServiceUUID=\'"+uuid+"\'";
            rs = mysql.query(sql);

            while (rs.next()) {
                out.println("<TR>");
                out.println("<TD><input type=\'checkbox\' name=\'ichecks\' value=\'" + rs.getString("StreamUUID") + "\'></TD>");
                out.println("<TD>" + rs.getString("StreamUUID") +"</TD>");
                out.println("<TD>" + rs.getString("Description") +"</TD>");
                out.println("<TD><a href="+rs.getString("DeviceURL")+">" + rs.getString("DeviceType") +"</a></TD>");
                out.println("<TD><a href="+rs.getString("DataTypeURL")+">" + rs.getString("DataType") +"</a></TD>");
                out.println("<TD>" + rs.getString("Databus") +"</TD>");
                String costUrl = rs.getString("CostURL");
                if(costUrl != null)
                    out.println("<TD><a href=\'" + costUrl +"\'>view</a></TD>");
                else
                    out.println("<TD>&nbsp</TD>");
                out.println("<TD align='center'>" + rs.getString("TimeSeries") +"</TD>");
                out.println("<TD align='center'>" + rs.getString("DataRate") +"</TD>");
                out.println("<TD align='center'>" + rs.getString("Latency") +"</TD>");
                out.println("<TD align='center'>" + rs.getString("DataOrigin") +"</TD>");
                out.println("</TR>");
            }
            out.println(htmlUtils.getClosedTable());
            out.println("<TR><TD><input type=\'submit\' value=\'Subscribe\'></TD></TR>");
            out.println("</form>");
            //end stream row
            out.println("</TD></TR>");

            // close table
            out.println(htmlUtils.getClosedTable());
            out.println("</TD> </TR>");

        } catch (SQLException ex) {
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
        }
        out.println(htmlUtils.getClosedTable());
        out.println(htmlUtils.getHtmlFooter());
        
    }
    
    private void processServiceWithoutLogging(HttpServletRequest request, HttpServletResponse response, String sessionid, MySQL mysql) throws IOException{
        String uuid = request.getParameter("uuid");
        PrintWriter out = response.getWriter();
        HTMLUtils htmlUtils = new HTMLUtils();
        out.println(htmlUtils.getHtmlHeader());
        out.println(htmlUtils.getTitle("Realtime data marketplace", 100, 100));
        out.println(htmlUtils.getTableHead("center", 0));
        out.println("<TR><TD>&nbsp;</TD></TR>");
	out.println("<TR><TD align = left><a href='./"+ (sessionid==null?"default":"home") +"'><h1>Realtime Data Marketplace</h1></a></TD></TR>");
        out.println("<TR><TD align = left><h3>Service Information</h3></TD></TR>");
        
        String sql = "SELECT Name, Provider, Description, CostURL, Databus, CategoryListIds, ContractListIds, QoSListIds, registered_date FROM tbl_Services WHERE UUID=\'" + uuid +"\'";
        String catlist = "";
        String conlist = "";
        String qoslist = "";
        try {
            out.println("<TR> <TD>");
            out.println(htmlUtils.getTableHead("left", 0));
            
            ResultSet rs = mysql.query(sql);
            if(rs.absolute(1))
            {
                out.println("<TR> <TD>ServiceUUID </TD> <TD>" + uuid + "</TD> </TR>");
                out.println("<TR> <TD>Service Name </TD> <TD>" + rs.getString("Name") + "</TD> </TR>");
                out.println("<TR> <TD>Provider</TD> <TD>" + rs.getString("Provider") + "</TD> </TR>");
                out.println("<TR> <TD>Description </TD> <TD>" + rs.getString("Description") + "</TD> </TR>");
                out.println("<TR> <TD>Databus for subscription </TD> <TD>" + rs.getString("Databus") + "</TD> </TR>");
                String curl = rs.getString("CostURL");
                out.println("<TR> <TD valign='top'>Cost for service</TD> <TD>" + (curl != null? "<a href=\'" + curl + "\'> " + curl +" </a>":"Not available") +"</TD> </TR>");
                out.println("<TR> <TD>Service is registered on </TD> <TD>" + rs.getString("registered_date").substring(0, 10) + "</TD> </TR>");
                catlist = rs.getString("CategoryListIds");
                conlist = rs.getString("ContractListIds");
                qoslist = rs.getString("QoSListIds");
            }
            rs.close();
            
            //query categories
            out.println("<TR> <TD>Categories </TD>");
            if (catlist != null) {
                catlist = catlist.replaceAll(",", "\',\'");
                //get categories
                sql = "SELECT Term, Scheme FROM tbl_Categories WHERE ID IN(\'"+ catlist.substring(1, catlist.length()-1) + "\')";
                rs = mysql.query(sql);

                catlist = "";
                while (rs.next()) {
                    catlist += "<a href=\'"+ rs.getString("Scheme") +"\'> " + rs.getString("Term") + " </a>;";
                }  
                rs.close();
            }else
                catlist = "Not available";
            out.println("<TD>" +catlist+" </TD> </TR> ");
            
            //query QoS
            out.println("<TR> <TD>Quality of Service models</TD>");
            if(qoslist != null){
                qoslist = qoslist.replaceAll(",", "\',\'");
                //get qos
                sql = "SELECT Name, SourceURL FROM tbl_QoS WHERE Id IN(\'"+ qoslist.substring(1, qoslist.length()-1) + "\')";
                rs = mysql.query(sql);

                qoslist = "";
                while (rs.next()) {
                    qoslist += "<a href=\'"+ rs.getString("SourceURL") +"\'> " + rs.getString("Name") + " </a>;";
                }
                rs.close();
            }else
                qoslist = "Not available";
            out.println("<TD>" +qoslist+ " </TD> </TR> ");
            
            //get contract
            out.println("<TR> <TD>Contract models </TD>");
            if(conlist != null){
                conlist = conlist.replaceAll(",", "\',\'");
                
                sql = "SELECT Name, SourceURL FROM tbl_ContractModel WHERE ID IN(\'"+ conlist.substring(1, conlist.length()-1) + "\')";
                rs = mysql.query(sql);

                conlist = "";
                while (rs.next()) {
                    conlist += "<a href=\'"+ rs.getString("SourceURL") +"\'> " + rs.getString("Name") + " </a>;";
                }
                rs.close();
            }else
                conlist = "Not available";
            
            out.println("<TD>" +conlist+" </TD> </TR> ");
            
            //start stream row
            out.println("<TR> <TD valign='top'> Data streams</TD><TD></TD></TR>");
            out.println("<TR> <TD colspan=2>");
            out.println(htmlUtils.getTableHead("left", 0));
            out.println(htmlUtils.getTH("left", "UUID"));
            out.println(htmlUtils.getTH("left", "Description"));
            out.println(htmlUtils.getTH("left", "Device"));
            out.println(htmlUtils.getTH("left", "Data type"));
            out.println(htmlUtils.getTH("left", "Data bus"));
            out.println(htmlUtils.getTH("left", "Cost"));
            out.println(htmlUtils.getTH("left", "Time series"));
            out.println(htmlUtils.getTH("left", "Rate"));
            out.println(htmlUtils.getTH("left", "Latency"));
            out.println(htmlUtils.getTH("left", "Origin"));
            sql = "SELECT A.StreamUUID, A.Description, A.Databus, A.CostURL, A.TimeSeries, A.DataOrigin,A.DataRate,A.Latency, B.Type AS DataType,B.SourceURL AS DataTypeURL ,C.Type AS DeviceType, C.SourceUrl AS DeviceURL" +
                  " FROM tbl_Streams AS A LEFT JOIN tbl_DataType AS B ON A.DataTypeID=B.ID" +
                  " LEFT JOIN tbl_Devices AS C ON A.DeviceID=C.Id" +
                  " WHERE A.ServiceUUID=\'"+uuid+"\'";
            rs = mysql.query(sql);
            
            while (rs.next()) {
                out.println("<TR>");
                out.println("<TD>" + rs.getString("StreamUUID") +"</TD>");
                out.println("<TD>" + rs.getString("Description") +"</TD>");
                out.println("<TD><a href="+rs.getString("DeviceURL")+">" + rs.getString("DeviceType") +"</a></TD>");
                out.println("<TD><a href="+rs.getString("DataTypeURL")+">" + rs.getString("DataType") +"</a></TD>");
                out.println("<TD>" + rs.getString("Databus") +"</TD>");
                String costUrl = rs.getString("CostURL");
                if(costUrl != null)
                    out.println("<TD><a href=\'" + costUrl +"\'>view</a></TD>");
                else
                    out.println("<TD>&nbsp</TD>");
                out.println("<TD align='center'>" + rs.getString("TimeSeries") +"</TD>");
                out.println("<TD align='center'>" + rs.getString("DataRate") +"</TD>");
                out.println("<TD align='center'>" + rs.getString("Latency") +"</TD>");
                out.println("<TD align='center'>" + rs.getString("DataOrigin") +"</TD>");
                out.println("</TR>");
            }
            out.println(htmlUtils.getClosedTable());
            
            //end stream row
            out.println("</TD></TR>");
            
            // close table
            out.println(htmlUtils.getClosedTable());
            out.println("</TD> </TR>");
            
        } catch (SQLException ex) {
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
        }
        out.println(htmlUtils.getClosedTable());
        out.println(htmlUtils.getHtmlFooter());
    }
}

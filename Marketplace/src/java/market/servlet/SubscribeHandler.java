/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package market.servlet;

import database.MySQL;
import java.io.IOException;
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
import utils.PaymentModelType;
/**
 *
 * @author ttu01
 */
public class SubscribeHandler extends HttpServlet {

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
        String serviceuuid = request.getParameter("serviceuuid");
        String[] costs =  request.getParameterValues("costsid");
        String[] streamuuids = request.getParameterValues("streamuuid");
        String[] payments = request.getParameterValues("paymenttype");
        String[] contracts = request.getParameterValues("contractmodel");
        String[] startdate = request.getParameterValues("startdate");
        String[] enddate = request.getParameterValues("enddate");
//        PrintWriter out = response.getWriter();
        MySQL mysql =new MySQL();
        String sessionid = null;
        Cookie[] cookie = request.getCookies();
        if(cookie != null){
            for (Cookie cookie1 : cookie) {
                if (cookie1.getName().equals(Constants.SESSION_ID_COOKIE)) {
                    sessionid = cookie1.getValue();
                    break;
                }
            }
        }
        
        if (sessionid != null) {
            String sql = "SELECT user FROM util_logged_users WHERE appname=\'W\' AND session_id=\'" + sessionid + "\'";
            try {
                ResultSet rs = mysql.query(sql);
                if (rs.absolute(1)) {
                    String username = rs.getString("user");
                    rs.close();
                    int j=0;
                    for (int i = 0; i < streamuuids.length; i++) {
                        sql = "INSERT INTO tbl_subscription(serviceUUID, streamUUID, paymentType, Costmodel, Contractmodel, subscriber, subscriptiontime"+ (payments[i].equals(PaymentModelType.PayOnPlan)?",startdate,enddate":"") +")" +
                        " VALUES(\'"+serviceuuid+"\', \'"+streamuuids[i]+"\', \'"+payments[i]+"\', \'"+ costs[i]+"\', \'"+contracts[i]+"\' , \'"+username+"\', NOW() "+ (payments[i].equals(PaymentModelType.PayOnPlan)?",\'" + startdate[j] + "\',\'"+enddate[j]+"\'":"")+")";
                        mysql.execute(sql);
                        if(payments[i].equals(PaymentModelType.PayOnPlan))
                            j++;
                    }
                    
                    
                    mysql.execute("UPDATE tbl_Services SET subscriber_number=subscriber_number+1 WHERE UUID=\'"+serviceuuid+"\'");
                    response.sendRedirect("./home");
                }else response.sendRedirect("./login?logged=false");
            } catch (SQLException ex) {
                Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            response.sendRedirect("./login?logged=false");
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

}

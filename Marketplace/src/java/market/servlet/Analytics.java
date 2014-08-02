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
public class Analytics extends HttpServlet {

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
        HTMLUtils htmlUtils = new HTMLUtils();
        PrintWriter out = response.getWriter();
        response.setContentType("text/html;charset=UTF-8");
        String sessionid = request.getParameter("sessionid");
        String user = request.getParameter("user");
        
        out.println(htmlUtils.getHtmlHeader());
        out.println(htmlUtils.getTitle("Realtime data marketplace", 100, 100));
        out.println("<TABLE align=center border=0>");
        out.println("<TR><TD>&nbsp;</TD></TR>");
	out.println("<TR><TD align = left><h1>Realtime Data Marketplace</h1></TD></TR>");
        out.println("<TR><TD align = left><h3>Quality of Service analytics!</h3></TD></TR>");
        Cookie[] cookie = request.getCookies();
        if(cookie != null){
            for (Cookie cookie1 : cookie) {
                if (cookie1.getName().equals(Constants.SESSION_ID_COOKIE)) {
                    if(!sessionid.equals(cookie1.getValue())){
                        out.println("<TR><TD>Username is not valid!</TD></TR>");
                        out.println(htmlUtils.getClosedTable());
                        return;
                    }
                }
            }
        }
        out.println("<form method='post'>");
        out.println("<TR><TD> Please choose a stream <select name='stream_uuid'>");
        String sql = "SELECT streamUUID FROM tbl_subscription WHERE subscriber=\'"+user+"\' AND active=1";
        MySQL mysql = new MySQL();
        try {
            ResultSet rs = mysql.query(sql);
            while(rs.next()){
                out.println("<option value=\'"+rs.getString("streamUUID")+"\'>" +rs.getString("streamUUID")+"</option>");
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Analytics.class.getName()).log(Level.SEVERE, null, ex);
        }
        out.println("</select></TD></TR>");
        
        out.println("<TR><TD> View in last <input type='number' name='duration' size='3' value='1'> <select name='time_unit'>");
        out.println("<option value='M'>Minutes</option>");
        out.println("<option value='H'>Hours</option>");
        out.println("<option value='D'>Days</option>");
        out.println("<option value='W'>Weeks</option>");
        out.println("</select></TD></TR>");
        out.println("<TR><TD>");
        out.println("<input type='submit' name='latency' value='Latency' onclick=\"form.action='latencyviewer?user="+user+"';\"/>");
        out.println("<input type='submit' name='datarate' value='Data Rate' onclick=\"form.action='datarateviewer';\"/></TD></TR>");
        out.println("</form>");
        out.println(htmlUtils.getClosedTable());
        out.println(htmlUtils.getHtmlFooter());
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

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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.Constants;
import utils.HTMLUtils;
//import utils.ScriptUtils;
import utils.Role;
/**
 *
 * @author ttu01
 */
@WebServlet(name = "Home", urlPatterns = {"/home"})
public class Home extends HttpServlet {
    private String username = "";
    private String role = "";
    private String sessionid = "";

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

        Cookie[] cookie = request.getCookies();
        if(cookie != null){
            for (Cookie cookie1 : cookie) {
                if (cookie1.getName().equals(Constants.SESSION_ID_COOKIE)) {
                    sessionid = cookie1.getValue();
                    break;
                }
            }
        }
//        else //in case fail to set cookie, get sessionid from query string 
//            sessionid = request.getParameter("sessionid");

        String sql = "SELECT A.user, A.role FROM tbl_users AS A INNER JOIN util_logged_users AS B ON A.user=B.user WHERE B.appname=\'W\' AND B.session_id=\'" + sessionid + "\'";

        try {
            MySQL mysql = new MySQL();
            try (ResultSet rs = mysql.query(sql)) {
                if (rs.absolute(1)) {
                    username = rs.getString("user");
                    role = rs.getString("role");
                    if (role.equals(Role.PUBLISHER)) {
                        showPublisherHome(request, response, mysql);
                    }else{
                        showSubscriberHome(request, response, mysql);
                    }
                }else response.sendRedirect("./login?logged=false");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
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

    private void showPublisherHome(HttpServletRequest request, HttpServletResponse response, MySQL mysql) throws IOException{
        HTMLUtils htmlUtils = new HTMLUtils();
        PrintWriter out = response.getWriter();
        response.setContentType("text/html;charset=UTF-8");
        
        out.println(htmlUtils.getHtmlHeader());
        out.println(htmlUtils.getTitle("Realtime data marketplace", 100, 100));
        out.println("<TABLE align=center border=0>");
        out.println("<TR><TD>&nbsp;</TD></TR>");
	out.println("<TR><TD align = left><h1>Realtime Data Marketplace</h1></TD></TR>");
        
        out.println("<TR><TD>");
	out.println("Welcome <b>"+ username +"</b> (logged in as a "+ (role.equals(Role.PUBLISHER)?"provider":"consumer") +")!&nbsp;&nbsp;&nbsp;&nbsp; <a href=\'./logout\'>Logout</a>");
	out.println("</TD></TR>");
                        
        out.println("<TR><TD>&nbsp;</TD></TR>");
        
        out.println("<TR><TD>List of your services <a href=\'./register\'>Register a new service</a> </TD></TR>");
        
        //service list
        out.println("<TR><TD>");
        out.print(htmlUtils.getTableHead("left", 0));
        out.print(htmlUtils.getTH("left", "Name"));
	out.print(htmlUtils.getTH("left", "Provider"));
	out.print(htmlUtils.getTH("left", "Description"));
        out.print(htmlUtils.getTH("left", "Databus"));
        out.print(htmlUtils.getTH("left", "Registered Date"));
        
        String sql = "SELECT UUID, Name, Provider, Description, Databus, registered_date FROM tbl_Services WHERE owner=\'" + username + "\'";
        
        try {
            try (ResultSet rs = mysql.query(sql)) {
                while(rs.next())
                {
                    String uuid = rs.getString("UUID");
                    String name = rs.getString("Name");
                    String provider = rs.getString("Provider");
                    String desc = rs.getString("Description");
                    String bus = rs.getString("Databus");
                    String regis = rs.getString("registered_date");
                    out.println("<TR><TD><a href='./service?uuid="+uuid+"'>"+name+"</a></TD> <TD>"+provider+"</TD> <TD>"+desc+"</TD> <TD>"+bus+"</TD> <TD align='center'> " + regis.substring(0, 10)+" </TD></TR>");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Default.class.getName()).log(Level.SEVERE, null, ex);
        }
        out.println(htmlUtils.getClosedTable());
        out.println("</TD></TR>");
        
        out.println("<TR><TD>&nbsp;</TD></TR>");
        
        //submit a new service
        out.println("<TR><TD>");
        out.println("<form action='./uploadservice?user="+username+"' method='post' enctype='multipart/form-data'>");
        out.println("<b>Submit your service</b>");
        out.println("<input type='file' name='samplefile'/><p>");
        out.println("<input type='submit' name='button' value='Submit'/><br>");	 		
        out.println("</form>");
        out.println("</TD></TR>");
        
        out.println(htmlUtils.getClosedTable());
        out.println(htmlUtils.getHtmlFooter());
    }
    
    private void showSubscriberHome(HttpServletRequest request, HttpServletResponse response, MySQL mysql) throws IOException{
        HTMLUtils htmlUtils = new HTMLUtils();
        PrintWriter out = response.getWriter();
        response.setContentType("text/html;charset=UTF-8");
        
        out.println(htmlUtils.getHtmlHeader());
        out.println(htmlUtils.getTitle("Realtime data marketplace", 100, 100));
        out.println("<TABLE align=center border=0>");
        out.println("<TR><TD>&nbsp;</TD></TR>");
	out.println("<TR><TD align = left><h1>Realtime Data Marketplace</h1></TD></TR>");
        out.println("<TR><TD>");
        out.println(htmlUtils.getTableHead("right", 0));
        out.println("<TR>");
        out.println("<TD><a href=\'./home'>Home</a></TD>");
        out.println("<TD><a href=\'./bill?sessionid="+ sessionid+"&user="+username+"\'>Billing</a></TD>");
        out.println("<TD><a href=\'./analytics?sessionid="+ sessionid+"&user="+username+"\'>QoS Analytics</a></TD>");
	out.println("<TD><a href=\'./logout?sessionid="+ sessionid+"\'>Logout</a></TD>");
        out.println("</TR>");
        out.println(htmlUtils.getClosedTable());
        
	out.println("</TD></TR>");
        
        out.println("");
        out.println("<TR><TD>");
	out.println("Welcome <b>"+ username +"</b> (logged in as a "+ (role.equals(Role.PUBLISHER)?"provider":"consumer") +")!");
	out.println("</TD></TR>");
                        
        out.println("<TR><TD>&nbsp;</TD></TR>");
        
        //submit a new service
        out.println("<form action='./search' method='post'>");
	out.println("<TR><TD align = left>");
        out.println("<input type='text' name='search' size='30'>");
        out.println("<input type='submit' name='btsearch' value='Search new services'>");
        out.println("</TD></TR></form>");
        
        out.println("<TR><TD>&nbsp;</TD></TR>");
        
        out.println("<TR><TD>List of subscribed services </TD></TR>");
        
        //service list
        out.println("<TR><TD>");
        out.print(htmlUtils.getTableHead("left", 0));
        out.print(htmlUtils.getTH("left", "Name"));
	out.print(htmlUtils.getTH("left", "Provider"));
	out.print(htmlUtils.getTH("left", "Description"));
        out.print(htmlUtils.getTH("left", "Databus"));
        out.print(htmlUtils.getTH("left", "Registered Date"));
        
        //QUERY HERE
        String sql = "SELECT UUID, Name, Provider, Description, Databus, registered_date FROM marketplace.tbl_Services AS A" +
                    " INNER JOIN marketplace.tbl_subscription AS B ON A.UUID=B.serviceUUID" +
                    " WHERE subscriber=\'" + username + "\' AND active=1 GROUP BY UUID, Name, Provider, Description, Databus, registered_date";
        
        try {
            try (ResultSet rs = mysql.query(sql)) {
                while(rs.next())
                {
                    String uuid = rs.getString("UUID");
                    String name = rs.getString("Name");
                    String provider = rs.getString("Provider");
                    String desc = rs.getString("Description");
                    String bus = rs.getString("Databus");
                    String regis = rs.getString("registered_date");
                    out.println("<TR><TD><a href='./service?uuid="+uuid+"&role=P'>"+name+"</a></TD> <TD>"+provider+"</TD> <TD>"+desc+"</TD> <TD>"+bus+"</TD> <TD align='center'> " + regis.substring(0, 10)+" </TD> <TD> <a href='./unsubscribe?uuid="+uuid+"'>Unsubscribe</a></TD></TR>");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Default.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        out.println(htmlUtils.getClosedTable());
        out.println("</TD></TR>");
        
        out.println(htmlUtils.getClosedTable());
        out.println(htmlUtils.getHtmlFooter());
    }
}

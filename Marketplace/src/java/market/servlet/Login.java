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
import utils.Utils;

/**
 *
 * @author ttu01
 */
public class Login extends HttpServlet {

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
		response.setContentType("text/html");
                out.println(htmlUtils.getHtmlHeader());
                out.println(htmlUtils.getTitle("Login", 100, 100));
                String logged = request.getParameter("logged");
                if (logged == null || logged.equals("false")) {
                    out.println(htmlUtils.getTableHead("center", 0));
                    out.println("<TR><TD><b>Login</b></TD></TR>");
                    out.println("<form action='./login?logged=true' method='post'>");
                    out.println("<TR><TD><input type=\"text\" name=\"username\"></TD></TR>");
                    out.println("<TR><TD>");
                    out.println("<b>Password</b></TD></TR> <TR><TD><input type=\"password\" name=\"password\">");
                    out.println("</TD></TR>");
                    out.println("<TR><TD>");
                    out.println("<input type='submit' name='button' value='Login'/><br>");
                    out.println("</TD></TR>");
                    out.println("</form>");
                    out.println(htmlUtils.getClosedTable());
                    out.println(htmlUtils.getHtmlFooter());
                }else if(logged.equals("true")){
                    String user = request.getParameter("username");
                    String pass = request.getParameter("password");
                    String sql = "SELECT user FROM tbl_users WHERE user = \'" + user + "\' AND pass=\'" + Utils.MD5(pass) + "\'";
                    try {
                        MySQL mysql = new MySQL();
                        ResultSet rs = mysql.query(sql);
                        if(rs.absolute(1)){
                            if(rs.getString("user").equals(user)){
                                String sessionid = Utils.MD5(user + System.currentTimeMillis());
                                //appname: S -> Web Service; W -> Web Servlet
                                if (mysql.execute("UPDATE util_logged_users SET session_id=\'" + sessionid + "\', logged_time=NOW() WHERE user=\'" + user +"\'") == 0) {
                                    mysql.execute("INSERT INTO util_logged_users(user, session_id, logged_time, appname) VALUES(\'"+user+"\',\'"+sessionid+"\',NOW(),'W')");
                                }
                                Cookie c = new Cookie(Constants.SESSION_ID_COOKIE, sessionid);
                                response.addCookie(c);
                                response.sendRedirect("./home");
                            }
                        }else{
                           out.println("Login fail! <a href=\'./login?logged=false\'> Try again</a>");
                        }
                        rs.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                    }
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

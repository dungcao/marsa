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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.HTMLUtils;
import utils.Role;
/**
 *
 * @author ttu01
 */
public class SignupHandler extends HttpServlet {

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
        String user = request.getParameter("username");
        String pass = request.getParameter("password");
        String conf_pass = request.getParameter("confirm_pass");
        String email = request.getParameter("email");
        String role = request.getParameter("role");
        String notify = "";
        out.println(htmlUtils.getHtmlHeader());
        out.println(htmlUtils.getTitle("Signup Hanlder", 100, 100));

        MySQL mysql = new MySQL();
        if (0 <= user.length()) {
            ResultSet rs;
            try {
                rs = mysql.query("SELECT user FROM tbl_users WHERE user=\'" + user +"\'");
                if (rs.first()) {
                    notify += "Username already existed, please choose another one!<br>";
                }
            } catch (SQLException ex) {
                Logger.getLogger(SignupHandler.class.getName()).log(Level.SEVERE, null, ex);
            }

        }else{
            notify += "Please enter an username or your username is too short!<br>";
        }
        if (pass.length() < 6 || conf_pass.length() < 6) {
            notify += "The password lenght must be greater than 6 characters!<br>";
        }else if (!pass.equals(conf_pass)) {
            notify += "Please confirm your password!<br>";
        }

        if (email.length() == 0) {
            notify += "Please enter email!<br>"; 
        }
        
        if (notify.length() > 0) {
            out.println("<form action='./signup' method='get'>");
            out.println(notify);
            out.println("<input type='submit' name='button' value='Back'/><br>");
            out.println("</form>");
        }else{
            try {
                if (role.equals("sub")) {
                    role = Role.SUBSCRIBER;
                }else role = Role.PUBLISHER;
                
                String sql = "INSERT INTO tbl_users(user, pass, email, role) VALUES(\'" + user + "\',\'"+ utils.Utils.MD5(pass) +"\',\'"+ email +"\',\'"+role +"\')";
//                out.println(sql);
                mysql.execute(sql);
                out.println("<h2>Successful!<br>");
                out.println("Go to <a href=\"./login\"> login</a><br><h2>");
            } catch (SQLException ex) {
                out.println(ex.toString());
            }
        }
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package market.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.HTMLUtils;

/**
 *
 * @author ttu01
 */
public class Signup extends HttpServlet {

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
//		ScriptUtils scriptUtils = new ScriptUtils();
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
                out.println(htmlUtils.getHtmlHeader());
                out.println(htmlUtils.getTitle("Signup", 100, 100));
                out.println("<TABLE align=center border=0>");
                out.println("<TR><TD align = left colspan=\"2\"><h2>Please fill your information!</h2></TD></TR>");
                out.println("<form action='./signuphandler' method='post'>");
                out.println("<TR><TD>");
                out.println("Username: </TD><TD><input type=\"text\" name=\"username\">*");
                out.println("</TD></TR>");
                out.println("<TR><TD>");
                out.println("Choose password: </TD><TD><input type=\"password\" name=\"password\">*");
                out.println("</TD></TR>");
                out.println("<TR><TD>");
                out.println("Confirm password: </TD><TD><input type=\"password\" name=\"confirm_pass\">*");
                out.println("</TD></TR>");
                out.println("<TR><TD>");
                out.println("Email: </TD><TD><input type=\"email\" name=\"email\">*");
                out.println("</TD></TR>");
                out.println("<TR><TD>");
                out.println("Role: </TD><TD>");
                out.println("<select name=\"role\">");
                out.println("<option value=\"sub\" selected=\"true\">Consumer</option>");
                out.println("<option value=\"pub\">Provider</option>");
                out.println("</select>");
                out.println("</TD></TR>");
                out.println("<TR><TD>");
                out.println("* Required values");
                out.println("</TD></TR>");
                out.println("<TR><TD></TD><TD>");
                out.println("<input type='submit' name='button' value='Submit'/><br>");
                out.println("</TD></TR>");
                out.println("</form>");
                out.println("</TABLE>");
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

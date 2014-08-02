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
import utils.ScriptUtils;

/**
 *
 * @author ttu01
 */
public class Default extends HttpServlet {

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
        ScriptUtils scriptUtils = new ScriptUtils();
        PrintWriter out = response.getWriter();
        response.setContentType("text/html;charset=UTF-8");
        
        String option = request.getParameter("service");
        MySQL mysql = new MySQL();
        
        out.println(htmlUtils.getHtmlHeader());
        out.println(htmlUtils.getTitle("Realtime data marketplace", 100, 100));
        out.println("<TABLE align=center border=0>");
        out.println("<TR><TD>&nbsp;</TD></TR>");
	out.println("<TR><TD align = left><h1>Realtime Data Marketplace</h1></TD></TR>");
        
        out.println("<TR><TD>");
	out.println(scriptUtils.getMenuDisplay());
	out.println("</TD></TR>");
                        
        out.println("<form action='./search' method='post'>");
	out.println("<TR><TD align = left>");
        out.println("<input type='text' name='search' size='50'>");
        out.println("<input type='submit' name='btsearch' value='Search your services'>");
        out.println("</TD></TR></form>");
        
        out.println("<TR><TD>&nbsp;</TD></TR>");
        
        out.println("<TR><TD> Top <a href='./default?service=new'>new</a> | <a href='./default?service=most_subscribed'>most subscribed </a> services </TD></TR>");
        
        //service list
        out.println("<TR><TD>");
        out.print(htmlUtils.getTableHead("left", 0));
        out.print(htmlUtils.getTH("left", "Name"));
	out.print(htmlUtils.getTH("left", "Provider"));
	out.print(htmlUtils.getTH("left", "Description"));
        out.print(htmlUtils.getTH("left", "Databus"));
        out.print(htmlUtils.getTH("left", "Registered Date"));
        
        String sql;
        //get first 10 newest service
        if (option == null || option.equals("new"))
            sql = "SELECT UUID, Name, Provider, Description, Databus, registered_date FROM marketplace.tbl_Services ORDER BY registered_date DESC LIMIT 0, 10";
        else
            sql = "SELECT UUID, Name, Provider, Description, Databus, registered_date FROM marketplace.tbl_Services ORDER BY subscriber_number DESC LIMIT 0, 10";
        
        try {
            ResultSet rs = mysql.query(sql);
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
        } catch (SQLException ex) {
            Logger.getLogger(Default.class.getName()).log(Level.SEVERE, null, ex);
        }
        out.println("</TABLE>");
        out.println("</TD></TR>");
        
        out.println("<TR><TD>&nbsp;</TD></TR>");
        
        out.println("<TR><TD align = left> <a href='./login'>Log In</a> to publish or subscribe a service. You don't have an account? <a href='./signup'>Sign Up</a> </TD></TR>");
        
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

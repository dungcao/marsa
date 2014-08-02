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
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.Constants;
import utils.HTMLUtils;
import utils.ScriptUtils;

/**
 *
 * @author ttu01
 */
public class Search extends HttpServlet {

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
        
        String keys = request.getParameter("search");
        MySQL mysql = new MySQL();
        
        String sessionid = null;
        Cookie[] cookie = request.getCookies();
        if (cookie != null) {
            for (Cookie cookie1 : cookie) {
                if (cookie1.getName().equals(Constants.SESSION_ID_COOKIE)) {
                    sessionid = cookie1.getValue();
                    break;
                }
            }
        }
        out.println(htmlUtils.getHtmlHeader());
        out.println(htmlUtils.getTitle("Realtime Data Marketplace", 100, 100));
        out.println(htmlUtils.getTableHead("center", 0));
        out.println("<TR><TD>&nbsp;</TD></TR>");
	out.println("<TR><TD align = left><a href='./"+(sessionid==null?"default":"home")+ "'><h1>Realtime Data Marketplace</h1></a></TD></TR>");
        
        out.println("<TR><TD>");
	out.println(scriptUtils.getMenuDisplay());
	out.println("</TD></TR>");
        
        out.println("<TR><TD>");
        out.println("Search results of <b>" + keys + "</b>");
        out.println("</TD></TR>");
        
        out.println("<TR><TD>");
        out.print(htmlUtils.getTableHead("left", 0));
        out.print(htmlUtils.getTH("left", "Name"));
	out.print(htmlUtils.getTH("left", "Provider"));
	out.print(htmlUtils.getTH("left", "Description"));
        out.print(htmlUtils.getTH("left", "Databus"));
        out.print(htmlUtils.getTH("left", "Registered Date"));
        
        try {
            StringTokenizer tokens = new StringTokenizer(keys, ";");
            String where = "";
            while(tokens.hasMoreTokens()){
                String tk = tokens.nextToken();
                if (where.length() > 0) {
                    where +=" OR ";
                }
                where += " (Name LIKE \'%" + tk + "%\' OR " + "Provider LIKE \'%" + tk + "%\' OR Description LIKE \'%" + tk +"%\')";
            }
            String sql = "SELECT UUID, Name, Provider, Description, Databus, registered_date FROM tbl_Services WHERE " + where;
                    
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
        
        out.println(htmlUtils.getClosedTable());
        out.println("</TD></TR>");
        
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

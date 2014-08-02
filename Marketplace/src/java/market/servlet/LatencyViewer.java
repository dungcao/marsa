/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package market.servlet;

import database.MySQL;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.HTMLUtils;

/**
 *
 * @author ttu01
 */
public class LatencyViewer extends HttpServlet {

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
        String stream_uuid = request.getParameter("stream_uuid");
        String duration = request.getParameter("duration");
        String time_unit = request.getParameter("time_unit");
        String user = request.getParameter("user");
        out.println(htmlUtils.getHtmlHeader());
        out.println("<TITLE> Realtime data marketplace </TITLE></HEAD><BODY>");
        out.println("<TABLE align=left border=0>");
        out.println("<TR><TD>&nbsp;</TD></TR>");
	out.println("<TR><TD align = left><a href='./home'><h1>Realtime Data Marketplace</h1></a></TD></TR>");
        String lasttime = "";
        String time = "";
        if(time_unit.equals("M")){
            lasttime = "1";
            time = " minute(s)";
        }else if(time_unit.equals("H")){
            //group by minute
            lasttime = "60";
            time = " hour(s)";
        }else if(time_unit.equals("D")){
            //group by 5 minutes
            lasttime = "300";
            time = " day(s)";
        }else if(time_unit.equals("D")){
            //group by 35 minutes
            lasttime = "2100";
            time = " week(s)";
        }
        
        out.println("<TR><TD>Latency analytics of stream <b>" + stream_uuid+"</b> in last "  + duration + time +"</TD></TR>");
        out.println("</TABLE>");
        
        if(time_unit.equals("H") || time_unit.equals("M"))
            duration = duration + " * 60";
        else if(time_unit.equals("D"))
            duration = duration + " * 60 * 24";
        else if(time_unit.equals("W"))
            duration = duration + " * 60 * 24 * 7";
        
        String sql = "SELECT ROUND(AVG(sub.timestamp - pub.timestamp),0) AS Latency, FROM_UNIXTIME(ROUND(sub.timestamp/"+lasttime+"000,0) * "+lasttime+") AS Timestamp " +
                    "FROM log_pub_timestamp AS pub INNER JOIN log_sub_timestamp AS sub ON " +
                    "pub.stream_uuid=sub.stream_uuid AND pub.messageid=sub.messageId " +
                    "WHERE sub.username=\'"+user+"\' AND sub.stream_uuid=\'"+stream_uuid+"\' AND (UNIX_TIMESTAMP() - sub.timestamp/1000) < "+duration+" * "+lasttime+" " +
                    "GROUP BY ROUND(sub.timestamp/"+lasttime+"000,0)";
        
        MySQL mysql = new MySQL();
        PrintWriter file = new PrintWriter(getServletContext().getRealPath("/") + "/data.tsv");
        
        file.println("time\tlatency");
        try {
            ResultSet rs = mysql.query(sql);
            while(rs.next()){
                file.println(rs.getString("Timestamp").substring(0, 19) + "\t" + rs.getString("Latency"));
            }
            file.close();
        } catch (SQLException ex) {
            Logger.getLogger(LatencyViewer.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scanner scan = new Scanner(new File(getServletContext().getRealPath("/") +"/scripts/latency.script"));
        while(scan.hasNextLine()){
            out.println(scan.nextLine());
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

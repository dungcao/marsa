/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package market.servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
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
public class Register extends HttpServlet {
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
        Cookie[] cookie = request.getCookies();
        if(cookie != null){
            for (Cookie cookie1 : cookie) {
                if (cookie1.getName().equals(Constants.SESSION_ID_COOKIE)) {
                    sessionid = cookie1.getValue();
                    break;
                }
            }
        }

        String sid = request.getParameter("sid");
        String hostname = request.getLocalName();
        int hostport = request.getLocalPort();
        if(hostport != 80)
            hostname += ":" + hostport;
        
        
        if(sid == null)
        {
            String serviceId = "suuid" + System.currentTimeMillis();
            response.setContentType("text/html;charset=UTF-8");
            HTMLUtils htmlUtils = new HTMLUtils();
            try (PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */
                out.println(htmlUtils.getHtmlHeader());
                out.println(htmlUtils.getTitle("Realtime data marketplace", 100, 100));
                out.println("<TABLE align=center border=0>");
                out.println("<TR><TD>&nbsp;</TD></TR>");
                out.println("<TR><TD align = left><h1>Realtime Data Marketplace</h1></TD></TR>");
                out.println("<TR><TD>&nbsp;</TD></TR>");
                out.println("<TR><TD>&nbsp;</TD></TR>");
                out.println("<TR><TD>We recieved successfully your request!</TD></TR>");
                out.println("<TR><TD>Click here to <a href=\'./register?sid="+serviceId+"\'>download</a> your registration form, fill it and submit again!</TD></TR>");
                out.println("<TR><TD>Click here to <a href=\'http://"+ hostname +"/pricing/create?sessionid="+sessionid+"\'>register</a> your cost models!</TD></TR>");
                out.println(htmlUtils.getClosedTable());
            }
        }else{
            String sample = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                            "<market:marketcatalog xmlns:market=\"http://realmarket.com/schemas/market\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://"+hostname+"/marketplace/schemas/market.xsd\">\n" +
                            "   <market:service>\n"+
                            "       <!-- please do not modify the service uuid -->\n"+
                            "       <market:serviceuuid>"+sid+"</market:serviceuuid>\n"+
                            "       <market:servicename>???</market:servicename>\n"+
                            "       <market:provider>???</market:provider>\n"+
                            "       <market:servicedescription>???</market:servicedescription>\n"+
                            "       <market:servicecost>Copy URL from cost service to here</market:servicecost>\n"+
                            "       <market:servicecontracts>\n"+
                            "           <!-- one or multiple contract models -->\n"+
                            "           <market:contractmodel name=\"???\" src=\"Copy URL from contract service to here\"/>\n"+
                            "       </market:servicecontracts>\n"+
                            "       <market:servicecategories>\n"+
                            "           <!-- one or multiple categories -->\n"+
                            "           <market:category name=\"???\" src=\"???\"/>\n"+
                            "       </market:servicecategories>\n"+
                            "       <market:qos name=\"???\" src=\"???\"/>\n"+
                            "       <market:servicedatabus>??? (example: tcp://iot.eclipse.org:1883)</market:servicedatabus>\n"+
                            "       <!-- one or multiple streams -->\n"+
                            "       <market:datastream>\n"+
                            "           <market:streamuuid>"+sid+"/sid001</market:streamuuid>\n"+
                            "           <market:device name=\"???\" src=\"???\"/>\n"+
                            "           <market:datadescription>???</market:datadescription>\n"+
                            "           <!-- optional -->\n"+
                            "           <market:datacost>Copy URL from cost service to here</market:datacost>\n"+
                            "           <!-- optional -->\n"+
                            "           <market:datacontract name=\"???\" src=\"Copy URL from contract service to here\"/>\n"+
                            "           <!-- optional -->\n"+
                            "           <market:datacategory name=\"???\" src=\"???\"/>\n"+
                            "           <market:datatype name=\"???\" src=\"???\"/>\n"+
                            "           <market:streamdatabus>???</market:streamdatabus>\n"+
                            "           <!-- the default unit of time series, uncertainly and latency are second -->\n"+
                            "           <market:timeseries>???</market:timeseries>\n"+
                            "           <market:uncertainly>??</market:uncertainly>\n"+
                            "           <market:dataorigin>??</market:dataorigin>\n"+
                            "           <!-- the default unit of data rate is byte/second -->\n"+
                            "           <market:datarate>???</market:datarate>\n"+
                            "           <market:latency>???</market:latency>\n"+
                            "       </market:datastream>\n"+
                            "       <market:datastream>\n"+
                            "           <market:streamuuid>"+sid+"/sid002</market:streamuuid>\n"+
                            "           <market:device name=\"???\" src=\"???\"/>\n"+
                            "           <market:datadescription>???</market:datadescription>\n"+
                            "           <!-- optional -->\n"+
                            "           <market:datacost>Copy URL from cost service to here</market:datacost>\n"+
                            "           <!-- optional -->\n"+
                            "           <market:datacontract name=\"???\" src=\"Copy URL from contract service to here\"/>\n"+
                            "           <!-- optional -->\n"+
                            "           <market:datacategory name=\"???\" src=\"???\"/>\n"+
                            "           <market:datatype name=\"???\" src=\"???\"/>\n"+
                            "           <market:streamdatabus>???</market:streamdatabus>\n"+
                            "           <!-- the default unit of time series, uncertainly and latency are second -->\n"+
                            "           <market:timeseries>???</market:timeseries>\n"+
                            "           <market:uncertainly>??</market:uncertainly>\n"+
                            "           <market:dataorigin>??</market:dataorigin>\n"+
                            "           <!-- the default unit of data rate is byte/second -->\n"+
                            "           <market:datarate>???</market:datarate>\n"+
                            "           <market:latency>???</market:latency>\n"+
                            "       </market:datastream>\n"+
                            "   </market:service>\n"+
                            "</market:marketcatalog>";

            response.setContentType("text/xml;charset=UTF-8");
            response.setContentLength(sample.length());

            // forces download
            response.setHeader("Content-Disposition", "attachment; filename=sample_"+sid+".xml");

            try ( // obtains response's output stream
                    OutputStream outStream = response.getOutputStream()) {
                    outStream.write(sample.getBytes());
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

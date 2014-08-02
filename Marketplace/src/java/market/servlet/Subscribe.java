/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package market.servlet;

import com.realmarket.ws.pricing.QueryResponse;
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
import utils.PaymentModelType;
import ws.PricingClient;
/**
 *
 * @author ttu01
 */
public class Subscribe extends HttpServlet {

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
        MySQL mysql = new MySQL();
        String []streams = request.getParameterValues("ichecks");
        String uuid = request.getParameter("uuid");
        PrintWriter out = response.getWriter();
        HTMLUtils htmlUtils = new HTMLUtils();
        out.println(htmlUtils.getHtmlHeader());
        out.println(htmlUtils.getTitle("Realtime data marketplace", 100, 100));
        
        //javascript
        out.println("<script src=\"./scripts/script.js\" type=\"text/javascript\"></script>");

        out.println(htmlUtils.getTableHead("center", 0));
        out.println("<TR><TD>&nbsp;</TD></TR>");
        out.println("<TR><TD align = left><a href='./home'><h1>Realtime Data Marketplace</h1></a></TD></TR>");
        out.println("<TR><TD>&nbsp;</TD></TR>");

        if (streams == null || streams.length == 0) {
            out.println("<TR><TD>No stream is selected!</TD></TR>");
            out.println(htmlUtils.getClosedTable());
            out.println(htmlUtils.getHtmlFooter());
        }else{
            out.println("<TR><TD>Select your payment strategy and contract model</TD></TR>");
            out.println("<TR><TD><TABLE>");
            out.println("<form method=\'post\' action=\'./subscribehandler\'>");
            out.println(htmlUtils.getTH("left", "Stream UUID"));
            out.println(htmlUtils.getTH("left", "Payment strategy"));
            out.println(htmlUtils.getTH("left", "Contract model"));
            out.println(htmlUtils.getTH("left", "Start date"));
            out.println(htmlUtils.getTH("left", "End date"));
//            out.println("<TR><TD>Stream UUID</TD><TD>Payment strategy</TD><TD>Contract model</TD><TR>");
            String streamids = "\'" +streams[0]+"\'";
            for (int i = 1; i < streams.length; i++) {
                streamids += ",\'" +streams[i] + "\'";
            }
            String sql = "SELECT B.StreamUUID, A.CostURL AS CostService, B.CostURL AS CostStream, B.ContractID, A.ContractListIds " +
                     "FROM tbl_Services AS A JOIN tbl_Streams AS B ON A.UUID = B.ServiceUUID " +
                     "WHERE A.UUID='"+uuid+"' AND B.StreamUUID IN (" + streamids + ")";

            try {
                try (ResultSet rs = mysql.query(sql)) {
                    PricingClient pricing = new PricingClient();
                    QueryResponse costservice = null;
                    String costuuid = "";
                    
                    while(rs.next()){
                        String streamuuid = rs.getString("StreamUUID");
                        String costurl_stream = rs.getString("CostStream");
                        String costurl_service = rs.getString("CostService");
                        String contractId = rs.getString("ContractID");
                        String contractListIds = rs.getString("ContractListIds");
                        
                        QueryResponse coststream = null;
                        if(costurl_stream != null){
                            int p = costurl_stream.indexOf("=");
                            if(p>0){
                                costuuid = costurl_stream.substring(p+1);
                                coststream = pricing.queryByVersion(costuuid);
                            }
                        }else if(costservice == null && costurl_service != null){
                            int p = costurl_service.indexOf("=");
                            if(p>0){
                                costuuid = costurl_service.substring(p+1);
                                costservice = pricing.queryByVersion(costuuid);
                            }
                        }
                        out.println("<TR>");
                        
                        out.println("<TD> "+ streamuuid + "</TD>");
                        out.println("<TD><select id=\'select_id_"+ streamuuid +"\' name=\'paymenttype\' onChange=\"pchange(\'"+streamuuid+"\')\">");
                        if(coststream != null){
                            if (coststream.getApihandle() != null)
                                out.println("<option value=\'" + PaymentModelType.PayOnAPI + "\' >API Handle</option>");
                            if (coststream.getDatasize() != null)
                                out.println("<option value=\'" + PaymentModelType.PayOnDataSize + "\' >Data size</option>");
                            if (coststream.getDataunit() != null)
                                out.println("<option value=\'" + PaymentModelType.PayOnUnit + "\' >Data unit</option>");
                            if (coststream.getTimeplan() != null)
                                out.println("<option value=\'" + PaymentModelType.PayOnPlan + "\' >Time plan</option>");
                            if (coststream.getSubscription() != null)
                                out.println("<option value=\'" + PaymentModelType.PayOnSubscription + "\' >Subscription time</option>");
                        }else if(costservice != null){
                            if (costservice.getApihandle() != null)
                                out.println("<option value=\'" + PaymentModelType.PayOnAPI + "\' >API Handle</option>");
                            if (costservice.getDatasize() != null)
                                out.println("<option value=\'" + PaymentModelType.PayOnDataSize + "\' >Data size</option>");
                            if (costservice.getDataunit() != null)
                                out.println("<option value=\'" + PaymentModelType.PayOnUnit + "\' >Data unit</option>");
                            if (costservice.getTimeplan() != null)
                                out.println("<option value=\'" + PaymentModelType.PayOnPlan + "\' >Time plan</option>");
                            if (costservice.getSubscription() != null)
                                out.println("<option value=\'" + PaymentModelType.PayOnSubscription + "\' >Subscription time</option>");
                        }
                        out.println("</select></TD>");
                        
                        out.println("<TD><select name=\'contractmodel\'>");
                        if(contractId != null){
                            sql = "SELECT Name, SourceURL FROM  tbl_ContractModel WHERE ID =  \'"+contractId+"\'";
                            try (ResultSet rs1 = mysql.query(sql)) {
                                while(rs1.next()){
                                    out.println("<option value=\'" + rs1.getString("SourceURL") + "\' >" + rs1.getString("Name") + "</option>");
                                }
                            }
                        }else if(contractListIds != null){
                            contractListIds = contractListIds.replaceAll(",", "\',\'");
                            sql = "SELECT Name, SourceURL FROM  tbl_ContractModel WHERE ID IN (\'" + contractListIds.substring(1, contractListIds.length()-1) +"\')";
                            try (ResultSet rs1 = mysql.query(sql)) {
                                while(rs1.next()){
                                    out.println("<option value=\'" + rs1.getString("SourceURL") + "\' >" + rs1.getString("Name") + "</option>");
                                }
                            }
                        }
                        
                        
                        out.println("</select></TD>");
                        out.println("<TD><input id=\'startdate_id_"+streamuuid+"\' type=\'date\' name=\'startdate\' disabled required></TD>");
                        out.println("<TD><input id=\'enddate_id_"+streamuuid+"\' type=\'date\' name=\'enddate\' disabled required></TD>");
                        out.println("</TR>");
                        out.println("<input type=\'hidden\' name=\'streamuuid\' value=\'"+streamuuid+"\'>");
                        out.println("<input type=\'hidden\' name=\'costsid\' value=\'"+costuuid+"\'>");
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(Subscribe.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            out.println("</TABLE></TD></TR>");
            out.println("<input type=\'hidden\' name=\'serviceuuid\' value=\'"+uuid+"\'>");
            out.println("<TR><TD><input type=\'submit\' value=\'Confirm\'></TD><TR>");
            out.println("</form>");

            out.println(htmlUtils.getClosedTable());
            out.println(htmlUtils.getHtmlFooter());
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

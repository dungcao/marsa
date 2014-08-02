/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servlet;

import com.realmarket.ws.pricing.ApiHande;
import com.realmarket.ws.pricing.DataSize;
import com.realmarket.ws.pricing.DataUnit;
import com.realmarket.ws.pricing.PaymentType;
import com.realmarket.ws.pricing.Period;
import com.realmarket.ws.pricing.QueryResponse;
import com.realmarket.ws.pricing.RepeatBy;
import com.realmarket.ws.pricing.Stream;
import com.realmarket.ws.pricing.Subscription;
import com.realmarket.ws.pricing.TimePlan;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pricing.ws.PricingWS;

/**
 *
 * @author ttu01
 */
public class View extends HttpServlet {

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
        String query = request.getQueryString();
        try (PrintWriter out = response.getWriter()) {
            int p = query.indexOf("=");
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Pricing stratergy view</title>");            
            out.println("</head>");
            out.println("<body>");
            
            if(p > 0){
                String sid = query.substring(p+1);
                PricingWS service = new PricingWS();
                QueryResponse resp = service.queryByVersion(sid);
                if (resp == null) {
                    out.println("Your service does not exist!");
                }else{
                    out.println("<table align=\'center\'>");
                    out.println("<tr><td colspan=\'2\'><h3>The detail of your strategy (default currency is US dollar)!<h3></td><tr>");
                    if(resp.getDatasize() != null)
                    {
                        DataSize size = resp.getDatasize();
                        out.println("<tr><td>+ Payment on data size: </td><td>"+size.getBasicPrice()+"$/"+size.getSize()+size.getUnit()+"</td></tr>");    
                    }
                    
                    if(resp.getTimeplan() != null)
                    {
                        TimePlan time = resp.getTimeplan();
                        out.println("<tr><td>+ Payment on time plan: </td><td>"+time.getBasicPrice()+"$/"+time.getDuration()+time.getUnit()+"</td></tr>");    
                    }
                    
                    if(resp.getApihandle() != null)
                    {
                        ApiHande api = resp.getApihandle();
                        out.println("<tr><td>+ Payment on API handle: </td><td>"+api.getBasicPrice()+"$/"+api.getObjNum()+" packages</td></tr>");    
                    }
                    
                    if(resp.getDataunit() != null)
                    {
                        DataUnit unit = resp.getDataunit();
                        out.println("<tr><td>+ Payment on data unit: </td><td>"+unit.getBasicPrice()+"$ for following stream:</td></tr>");
                        List<Stream> ss = unit.getStreams();
                        for (int i = 0; i < ss.size(); i++) {
                            Stream stream = ss.get(i);
                            out.println("<tr><td>&nbsp</td><td>-"+stream.getStreamId()+" " + stream.getValue() + (stream.getPType()==PaymentType.API_HANDLE? " packages": stream.getUnit()) + "</td></tr>");
                        }
                    }
                    
                    if(resp.getSubscription() != null)
                    {
                        Subscription sub = resp.getSubscription();
                        out.println("<tr><td>+ Payment on time of subscription: </td><td>"+sub.getBasicPrice()+"$/"+sub.getDuration()+sub.getUnit()+", in which the following time will be charged more!</td></tr>"); 
                        if(!sub.getImportants().isEmpty()){
//                            out.println("<tr><td colspan =\'2\'>&nbsp&nbsp </td></tr>");
                            
                            List<Period> imps = sub.getImportants();
                            for (int i = 0; i < imps.size(); i++) {
                                Period period = imps.get(i);
                                if(period.getRepeat()== RepeatBy.DAY)
                                    out.println("<tr><td colspan=\'2\'> &nbsp&nbsp&nbsp&nbsp- from "+period.getFValue()+" to " + period.getTValue() + " everyday, you will be charged more " + period.getExtraPrice() +"$/"+sub.getDuration()+sub.getUnit()+"</td></tr>");
                                else if(period.getRepeat() == RepeatBy.WEEK){
                                    if(period.getFValue().equalsIgnoreCase(period.getTValue()))
                                        out.println("<tr><td colspan=\'2\'> &nbsp&nbsp&nbsp&nbsp- every "+period.getFValue()+ ", you will be charged more " + period.getExtraPrice() +"$/"+sub.getDuration()+sub.getUnit()+"</td></tr>");
                                    else 
                                        out.println("<tr><td colspan=\'2\'> &nbsp&nbsp&nbsp&nbsp- from "+period.getFValue()+" to " + period.getTValue() + "every week, you will be charged more " + period.getExtraPrice() +"$/"+sub.getDuration()+sub.getUnit()+"</td></tr>");
                                }else if(period.getRepeat() == RepeatBy.MONTH){
                                    if(period.getFValue().equalsIgnoreCase(period.getTValue()))
                                        out.println("<tr><td colspan=\'2\'> &nbsp&nbsp&nbsp&nbsp- every "+period.getFValue()+ " of month, you will be charged more " + period.getExtraPrice() +"$/"+sub.getDuration()+sub.getUnit()+"</td></tr>");
                                    else 
                                        out.println("<tr><td colspan=\'2\'> &nbsp&nbsp&nbsp&nbsp- from "+period.getFValue()+" to " + period.getTValue() + "every month, you will be charged more " + period.getExtraPrice() +"$/"+sub.getDuration()+sub.getUnit()+"</td></tr>");
                                }
                                
                            }
                        }
                    }
                    out.println("</table>");
                }
            }else{
                out.println("Please provide your service identification!");
            }
            out.println("</body>");
            out.println("</html>");
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

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
import com.realmarket.ws.pricing.RepeatBy;
import com.realmarket.ws.pricing.Stream;
import com.realmarket.ws.pricing.Subscription;
import com.realmarket.ws.pricing.TimePlan;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
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
public class Create extends HttpServlet {

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
        String s = request.getMethod();
        String hostname = request.getLocalName();
        int hostport = request.getLocalPort();
        if(hostport != 80)
            hostname += ":" + hostport;
        
        if (s.toUpperCase().equals("POST")) {
            try (PrintWriter out = response.getWriter()) {
                String sizeChecked = request.getParameter("checkSize");
                String timeChecked = request.getParameter("checkTime");
                String apiChecked = request.getParameter("checkAPI");
                String subChecked = request.getParameter("checkSub");
                String unitChecked = request.getParameter("checkUnit");
                
                DataSize dsize = null;
                TimePlan tplan = null;
                ApiHande ahandle = null;
                Subscription sub = null;
                DataUnit dunit = null;
                
                if (sizeChecked != null) {
                    dsize = new DataSize();
                    float price = Float.parseFloat(request.getParameter("txtdatasize_price"));
                    dsize.setBasicPrice(price);
                    int value = Integer.parseInt(request.getParameter("txtdatasize_value"));
                    dsize.setSize(value);
                    dsize.setUnit(request.getParameter("txtdatasize_unit"));
                }
                
                if (timeChecked != null) {
                    tplan = new TimePlan();
                    float price = Float.parseFloat(request.getParameter("txttimeplan_price"));
                    tplan.setBasicPrice(price);
                    int value = Integer.parseInt(request.getParameter("txttimeplan_value"));
                    tplan.setDuration(value);
                    tplan.setUnit(request.getParameter("txttimeplan_unit"));
                }
                
                if (apiChecked != null) {
                    ahandle = new ApiHande();
                    float price = Float.parseFloat(request.getParameter("txtapihandle_price"));
                    ahandle.setBasicPrice(price);
                    int value = Integer.parseInt(request.getParameter("txtapihandle_value"));
                    ahandle.setObjNum(value);
                }
                
                if(unitChecked != null)
                {
                    dunit = new DataUnit();
                    float price = Float.parseFloat(request.getParameter("txtdataunit_price"));
                    dunit.setBasicPrice(price);
                    List<Stream> streams = dunit.getStreams();
                    String[] stream_ids = request.getParameterValues("stream_id");
                    String[] stream_payments = request.getParameterValues("stream_payment");
                    String[] stream_values = request.getParameterValues("stream_value");
                    String[] stream_units = request.getParameterValues("stream_unit");

                    for (int i = 0; i < stream_ids.length; i++) {
                        Stream stream = new Stream();
                        stream.setStreamId(stream_ids[i]);
                        stream.setPType(getPaymentType(stream_payments[i]));
                        stream.setValue(Integer.parseInt(stream_values[i]));
                        stream.setUnit(stream_units[i]);
                        streams.add(stream);
                    }
                }
                
                if(subChecked != null)
                {
                    sub = new Subscription();
                    float price = Float.parseFloat(request.getParameter("txtsubscription_price"));
                    sub.setBasicPrice(price);
                    int duration = Integer.parseInt(request.getParameter("txtsubscription_value"));
                    sub.setDuration(duration);
                    sub.setUnit(request.getParameter("txtsubscription_unit"));
                    List<Period> importants = sub.getImportants();
                    String[] eprices = request.getParameterValues("extraprice");
                    String[] froms = request.getParameterValues("from_value");
                    String[] tos = request.getParameterValues("to_value");
                    String[] repeats = request.getParameterValues("repeatby");
                    for (int i = 0; i < eprices.length; i++) {
                        Period p = new Period();
                        p.setExtraPrice(Float.parseFloat(eprices[i]));
                        p.setFValue(froms[i]);
                        p.setTValue(tos[i]);
                        p.setRepeat(getRepeatBY(repeats[i]));
                        importants.add(p);
                    }
                    
                }
                
                PricingWS service = new PricingWS();
                String suuid = service.create(tplan, dsize, ahandle, sub, dunit);
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Create pricing strategy</title>");
                out.println("Copy the following link and put into your subsciption sample<br>");
                out.println("http://" + hostname + "/pricing/view?sid="+suuid);
                out.println("<br><a href=\'./create\'>Back</a>");
                out.println("</body>");
                out.println("</html>");
            }
        }else{
            try (PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Create pricing strategy</title>");
                out.println("<script src=\"./scripts/script.js\" type=\"text/javascript\"></script>");
                out.println("</head>");
                out.println("<body>");
                //will add table into form later by javascript
                out.println("<form id=\'form_id\' action=\"#\" method=\"post\"/>");
                
                out.println("<table align=\'center\' id=\"parent_table_id\">");
                out.println("<tr><td colspan=\'2\'><h1>Create a new pricing stratergy.</h1></td></tr>");
                out.println("<tr><td colspan=\'2\'><h3>Select your support plan:</h3></td></tr>");
                out.println("<tr><td><input id = \'id_size_check\' type=\'checkbox\' name=\'checkSize\' value=\'checked\' onclick=\'checkDataSize()\' checked> Data size</td></tr>");
                out.println("<tr><td><input id = \'id_time_check\' type=\'checkbox\' name=\'checkTime\' value=\'checked\' onclick=\'checkTimePlan()\' checked> Time Plan</td></tr>");
                out.println("<tr><td><input id = \'id_api_check\' type=\'checkbox\' name=\'checkAPI\' value=\'checked\' onclick=\'checkAPIHandle()\' checked> API Handle</td></tr>");
                out.println("<tr><td><input id = \'id_sub_check\' type=\'checkbox\' name=\'checkSub\' value=\'checked\' onclick=\'checkSubscription()\' checked>Subscription</td></tr>");
                out.println("<tr><td><input id = \'id_unit_check\' type=\'checkbox\' name=\'checkUnit\' value=\'checked\' onclick=\'checkDataUnit()\' checked>Data Unit</td></tr>");
                
                out.println("<tr><td colspan=\'2\'><b>Payment on data size!</b></td></tr>");
                out.println("<tr><td>The price</td><td><input id=\'datasize_price_id\' type=\'number\' name=\'txtdatasize_price\' step=\'0.01\' size=\'10\' required ></td></tr>");
                out.println("<tr><td>Data size</td><td><input id=\'datasize_value_id\' type=\'number\' name=\'txtdatasize_value\' size=\'10\' required ></td></tr>");
                out.println("<tr><td>Data unit</td><td><select id=\'datasize_unit_id\' name=\'txtdatasize_unit\' required>");
                out.println("<option value =\'Kb\'>Kb</option>");
                out.println("<option value =\'Mb\'>Mb</option>");
                out.println("<option value =\'Gb\'>Gb</option>");
                out.println("</select</td></tr>");
                out.println("<tr><td colspan=\'2\'><hr></td></tr>");
                
                out.println("<tr><td colspan=\'2\'><b>Payment on time plan!</b></td></tr>");
                out.println("<tr><td>The price</td><td><input id=\'timeplan_price_id\' type=\'number\' name=\'txttimeplan_price\' step=\'0.01\' size=\'10\' required></td></tr>");
                out.println("<tr><td>Time duration</td><td><input id=\'timeplan_value_id\' type=\'number\' name=\'txttimeplan_value\' size=\'10\' required></td></tr>");
                out.println("<tr><td>Time unit</td><td><select id=\'timeplan_unit_id\' name=\'txttimeplan_unit\' required>");
                out.println("<option value =\'HOUR\'>Hour</option>");
                out.println("<option value =\'DAY\'>Day</option>");
                out.println("<option value =\'WEEK\'>Week</option>");
                out.println("<option value =\'MONTH\'>Month</option>");
                out.println("</select</td></tr>");
                out.println("<tr><td colspan=\'2\'><hr></td></tr>");
                
                out.println("<tr><td colspan=\'2\'><b>Payment on API handle!</b></td></tr>");
                out.println("<tr><td>The price</td><td><input id=\'apihandle_price_id\' type=\'number\' name=\'txtapihandle_price\' step=\'0.01\' size=\'10\' required></td></tr>");
                out.println("<tr><td>Object number</td><td><input id=\'apihandle_value_id\' type=\'number\' name=\'txtapihandle_value\' size=\'10\' required></td></tr>");
                out.println("<tr><td colspan=\'2\'><hr></td></tr>");
                
                out.println("<tr><td colspan=\'2\'><b>Payment on data unit!</b><button id=\'dataunit_button_id\' type=\'button\' onclick=\'addStream()\' >Add stream</button></td></tr>");
                out.println("<tr><td>The price of unit</td><td><input id=\'dataunit_price_id\' type=\'number\' name=\'txtdataunit_price\' step=\'0.01\' size=\'10\' required></td></tr>");
                
                out.println("<tr><td colspan=\'2\'><table id=\"dataunit_streamlist_id\">");
                out.println("<tr><td></td><td>Stream Id</td><td>Payment plan</td><td>Value</td><td>Unit</td></tr>");
                out.println("<tr><td>+ </td><td><input id=\'dataunit_stream_id_1\' type=\'text\' name=\'stream_id\' required></td>");
                out.println("<td><select id=\'dataunit_select_id_1\' name =\'stream_payment\' required>");
                out.println("<option value =\'DATASIZE\'>Data Size</option>");
                out.println("<option value =\'TIMEPLAN\'>Time Plan</option>");
                out.println("<option value =\'APIHANDLE\'>API Handle</option>");
                out.println("</select</td>");
                out.println("<td><input id=\'dataunit_value_id_1\' type=\'number\' name=\'stream_value\' size=\'6\' required></td>");
                out.println("<td><input id=\'dataunit_unit_id_1\' type=\'text\' name=\'stream_unit\' size=\'6\' required></td></tr>");
                out.println("</table></td></tr>");
                out.println("<tr><td colspan=\'2\'><hr></td></tr>");
                
                out.println("<tr><td colspan=\'2\'><b>Payment on subsciption time!</b><button id=\'sub_button_id\' type=\'button\' onclick=\'addPeriod()\' required>Add important moment</button></td></tr>");
                out.println("<tr><td>The price</td><td><input id=\'sub_price_id\' type=\'number\' name=\'txtsubscription_price\' step=\'0.01\' size=\'10\' required></td></tr>");
                out.println("<tr><td>Time duration</td><td><input id=\'sub_value_id\' type=\'number\' name=\'txtsubscription_value\' size=\'10\' required></td></tr>");
                out.println("<tr><td>Time unit</td><td><select id=\'sub_unit_id\' name=\'txtsubscription_unit\' required>");
                out.println("<option value =\'HOUR\'>Hour</option>");
                out.println("<option value =\'DAY\'>Day</option>");
                out.println("<option value =\'WEEK\'>Week</option>");
                out.println("<option value =\'MONTH\'>Month</option>");
                out.println("</select</td></tr>");
                out.println("<tr><td colspan=\'2\'><table id=\"periodlist_id\"></table></td></tr>");
                
                out.println("<tr><td colspan=\'2\'><hr></td></tr>");
                out.println("<tr><td><input type=\"submit\" value=\"Submit\" onclick=\"submitFrom()\"></input></td></tr>");
                
                out.println("</table>");
                out.println("</body>");
                out.println("</html>");
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
    
    private PaymentType getPaymentType(String s){
        switch (s) {
            case "DATASIZE":
                return PaymentType.DATA_SIZE;
            case "TIMEPLAN":
                return PaymentType.TIME_PLAN;
            default:
                return PaymentType.API_HANDLE;
        }
    }
    
    private RepeatBy getRepeatBY(String s)
    {
        switch(s){
            case "DAY":
                return RepeatBy.DAY;
            case "WEEK":
                return RepeatBy.WEEK;
            default: 
                return RepeatBy.MONTH;
        }
    }
}

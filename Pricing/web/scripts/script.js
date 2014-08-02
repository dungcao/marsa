/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var stream_counter = 1;
var important_counter = 1;

function checkDataSize()
{
    var checked = document.getElementById('id_size_check').checked;
    if(checked){
        document.getElementById("datasize_price_id").disabled = false;
        document.getElementById("datasize_value_id").disabled = false;
        document.getElementById("datasize_unit_id").disabled = false;
    }else{
        document.getElementById("datasize_price_id").disabled = true;
        document.getElementById("datasize_value_id").disabled = true;
        document.getElementById("datasize_unit_id").disabled = true;
    }
}
    function checkTimePlan()
{
    var checked = document.getElementById('id_time_check').checked;
    if(checked){
        document.getElementById("timeplan_price_id").disabled = false;
        document.getElementById("timeplan_value_id").disabled = false;
        document.getElementById("timeplan_unit_id").disabled = false;
    }else{
        document.getElementById("timeplan_price_id").disabled = true;
        document.getElementById("timeplan_value_id").disabled = true;
        document.getElementById("timeplan_unit_id").disabled = true;
    }
}

function checkAPIHandle()
{
    var checked = document.getElementById('id_api_check').checked;
    if(checked){
        document.getElementById("apihandle_price_id").disabled = false;
        document.getElementById("apihandle_value_id").disabled = false;
    }else{
        document.getElementById("apihandle_price_id").disabled = true;
        document.getElementById("apihandle_value_id").disabled = true;
    }
}

function checkDataUnit()
{
    var checked = document.getElementById('id_unit_check').checked;
    if(checked){
        document.getElementById("dataunit_price_id").disabled = false;
        document.getElementById("dataunit_button_id").disabled = false;
        for (i = 1; i<=stream_counter; i++){
            document.getElementById("dataunit_stream_id_" + i).disabled = false;
            document.getElementById("dataunit_select_id_" + i).disabled = false;
            document.getElementById("dataunit_value_id_" + i).disabled = false;
            document.getElementById("dataunit_unit_id_" + i).disabled = false;
        }
    }else{
        document.getElementById("dataunit_price_id").disabled = true;
        document.getElementById("dataunit_button_id").disabled = true;
        for (i = 1; i<=stream_counter; i++){
            document.getElementById("dataunit_stream_id_" + i).disabled = true;
            document.getElementById("dataunit_select_id_" + i).disabled = true;
            document.getElementById("dataunit_value_id_" + i).disabled = true;
            document.getElementById("dataunit_unit_id_" + i).disabled = true;
    }
    }
}

function checkSubscription()
{
    var checked = document.getElementById('id_sub_check').checked;
    if(checked){
        document.getElementById("sub_price_id").disabled = false;
        document.getElementById("sub_button_id").disabled = false;
        document.getElementById("sub_value_id").disabled = false;
        document.getElementById("sub_unit_id").disabled = false;
        for (i = 1; i<important_counter; i++){
            document.getElementById("extraprice_id_" + i).disabled = false;
            document.getElementById("from_value_id_" + i).disabled = false;
            document.getElementById("to_value_id_" + i).disabled = false;
            document.getElementById("repeatby_id_" + i).disabled = false;
        }
    }else{
        document.getElementById("sub_price_id").disabled = true;
        document.getElementById("sub_button_id").disabled = true;
        document.getElementById("sub_value_id").disabled = true;
        document.getElementById("sub_unit_id").disabled = true;
        for (i = 1; i<important_counter; i++){
            document.getElementById("extraprice_id_" + i).disabled = true;
            document.getElementById("from_value_id_" + i).disabled = true;
            document.getElementById("to_value_id_" + i).disabled = true;
            document.getElementById("repeatby_id_" + i).disabled = true;
        }
    }
}

function addStream(){
    stream_counter++;
    var parent = document.getElementById("dataunit_streamlist_id");
    var tr = document.createElement("tr");
    
    var t = document.createElement("td");
    t.appendChild(document.createTextNode("+ "));
    tr.appendChild(t);
    
    var id_td = document.createElement("td");
    var id_input = document.createElement("input");
    id_input.setAttribute("type", "text");
    id_input.setAttribute("name", "stream_id");
    id_input.setAttribute("id","dataunit_stream_id_" + stream_counter);
    id_input.setAttribute("required","");
    id_td.appendChild(id_input);

    tr.appendChild(id_td);
    
    var select_td = document.createElement("td");
    var select = document.createElement("select");
    select.setAttribute("name", "stream_payment");
    select.setAttribute("id","dataunit_select_id_"+ stream_counter);
    var op_time = document.createElement("option");
    op_time.value = "TIMEPLAN";
    op_time.text = "Time Plan";
    var op_size = document.createElement("option");
    op_size.value = "DATASIZE";
    op_size.text = "Data Size";
    var op_api = document.createElement("option");
    op_api.value = "APIHANDLE";
    op_api.text = "API Handle";
    select.appendChild(op_size);
    select.appendChild(op_time);
    select.appendChild(op_api);
    select_td.appendChild(select);

    tr.appendChild(select_td);
    
    var value_td = document.createElement("td");
    var value_input = document.createElement("input");
    value_input.setAttribute("type", "number");
    value_input.setAttribute("name", "stream_value");
    value_input.setAttribute("id","dataunit_value_id_"+ + stream_counter);
    value_input.setAttribute("size","6");
    value_td.appendChild(value_input);

    tr.appendChild(value_td);
    
    var unit_td = document.createElement("td");
    var unit_input = document.createElement("input");
    unit_input.setAttribute("type", "text");
    unit_input.setAttribute("name", "stream_unit");
    unit_input.setAttribute("id","dataunit_unit_id_" + stream_counter);
    unit_input.setAttribute("size","6");
    unit_td.appendChild(unit_input);

    tr.appendChild(unit_td);
    parent.appendChild(tr);

}

function addPeriod(){
    var parent = document.getElementById("periodlist_id");
    //add header if it's first time.
    if(important_counter <= 1){
        var tr = document.createElement("tr");
        var tep = document.createElement("td");
        tep.appendChild(document.createTextNode("Extra price"));
        tr.appendChild(tep);
        
        var tfrom = document.createElement("td");
        tfrom.appendChild(document.createTextNode("Value (from)"));
        tr.appendChild(tfrom);
        
        var tto = document.createElement("td");
        tto.appendChild(document.createTextNode("Value (to)"));
        tr.appendChild(tto);
        
        var tby = document.createElement("td");
        tby.appendChild(document.createTextNode("Repeat By"));
        tr.appendChild(tby);
        
        parent.appendChild(tr);
    }
    
    var tr = document.createElement("tr");
    
    var ep_td = document.createElement("td");
    var ep_input = document.createElement("input");
    ep_input.setAttribute("type", "number");
    ep_input.setAttribute("step", "0.01");
    ep_input.setAttribute("name", "extraprice");
    ep_input.setAttribute("id","extraprice_id_"+important_counter);
    
    ep_td.appendChild(ep_input);
    tr.appendChild(ep_td);
    
    var value_from_td = document.createElement("td");
    var value_from_input = document.createElement("input");
    value_from_input.setAttribute("type", "text");
    value_from_input.setAttribute("name", "from_value");
    value_from_input.setAttribute("id","from_value_id_"+important_counter);
    value_from_input.setAttribute("size","10");
    value_from_td.appendChild(value_from_input);
    tr.appendChild(value_from_td);
    
    var value_to_td = document.createElement("td");
    var value_to_input = document.createElement("input");
    value_to_input.setAttribute("type", "text");
    value_to_input.setAttribute("name", "to_value");
    value_to_input.setAttribute("id","to_value_id_"+important_counter);
    value_to_input.setAttribute("size","10");
    value_to_td.appendChild(value_to_input);
    tr.appendChild(value_to_td);
    
    var select_td = document.createElement("td");
    var select = document.createElement("select");
    select.setAttribute("name", "repeatby");
    select.setAttribute("id","repeatby_id_"+important_counter);
    var op_day = document.createElement("option");
    op_day.value = "DAY";
    op_day.text = "DAY";
    var op_week = document.createElement("option");
    op_week.value = "WEEK";
    op_week.text = "WEEK";
    var op_month = document.createElement("option");
    op_month.value = "MONTH";
    op_month.text = "MONTH";
    select.appendChild(op_day);
    select.appendChild(op_week);
    select.appendChild(op_month);
    select_td.appendChild(select);
    tr.appendChild(select_td);
    
    parent.appendChild(tr);
    important_counter++;
}

function submitFrom(){
    var parent = document.getElementById("parent_table_id");
    var frm = document.getElementById("form_id");
    frm.appendChild(parent);
    frm.submit();
}
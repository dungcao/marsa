/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function checkboxAll(){
    var checkall = document.getElementById("check_all_box").checked;
    if (checkall){
        var checklist = document.getElementsByName("ichecks");
        for (var i=0; i< checklist.length; i++)
        {
            checklist[i].checked = true;
        }
    }else{
        var checklist = document.getElementsByName("ichecks");
        for (var i=0; i< checklist.length; i++)
        {
            checklist[i].checked = false;
        }
    }
}

function pchange(suuid){
    var s = document.getElementById("select_id_" + suuid);
    var selectElement = s.options[s.selectedIndex].value;
    if(selectElement==="PLAN"){
        document.getElementById("startdate_id_"+suuid).disabled = false;
        document.getElementById("enddate_id_"+suuid).disabled = false;
    }else{
        document.getElementById("startdate_id_"+suuid).disabled = true;
        document.getElementById("enddate_id_"+suuid).disabled = true;
    }
}

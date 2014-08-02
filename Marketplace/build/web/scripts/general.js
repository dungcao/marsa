function goMenu(page){
  window.location.href = page;
}

function goSubmit(page){
  document.myform.action = page;
  document.myform.submit();
}

function goSubmitWithListBox(page){
  var listbox = document.myform.FromLB;
  for (i = 0; i < listbox.options.length; i++){
    listbox.options[i].selected = true;
  }
  document.myform.action = page;
  document.myform.submit();
}

function goSubmitWithListBoxDuration(page){
  var listbox = document.myform.FromLB;
  var lastIndex;
  var length;
  var duration;
  for (i = 0; i < listbox.options.length; i++){
    lastIndex = listbox.options[i].text.lastIndexOf("(");
    length = listbox.options[i].text.length;
    duration = listbox.options[i].text.substr(lastIndex + 1, length - lastIndex - 2);
    listbox.options[i].value = listbox.options[i].value + "_" + duration;
    listbox.options[i].selected = true;
  }
  document.myform.action = page;
  document.myform.submit();
}

function moveItems(tbFrom, tbTo) 
{
  var arrFrom = new Array(); 
  var arrTo = new Array(); 
  var arrLU = new Array();
  var i;

  for (i = 0; i < tbTo.options.length; i++) 
  {
    arrLU[tbTo.options[i].text] = tbTo.options[i].value;
    arrTo[i] = tbTo.options[i].text;
  }

  var fLength = 0;
  var tLength = arrTo.length;

  for (i = 0; i < tbFrom.options.length; i++) 
  {
    arrLU[tbFrom.options[i].text] = tbFrom.options[i].value;
    if (tbFrom.options[i].selected && tbFrom.options[i].value != "") 
    {
       arrTo[tLength] = tbFrom.options[i].text;
       tLength++;
    }
    else 
    {
       arrFrom[fLength] = tbFrom.options[i].text;
       fLength++;
    }
  }

  tbFrom.length = 0;
  tbTo.length = 0;
  var ii;

  for(ii = 0; ii < arrFrom.length; ii++) 
  {
    var no = new Option();
    no.value = arrLU[arrFrom[ii]];
    no.text = arrFrom[ii];
    tbFrom[ii] = no;
  }

  for(ii = 0; ii < arrTo.length; ii++) 
  {
    var no = new Option();
    no.value = arrLU[arrTo[ii]];
    no.text = arrTo[ii];
    tbTo[ii] = no;
  }
}

/*remove duration at the end*/
function moveItemsRemoveDuration(tbFrom, tbTo) 
{
  var arrFrom = new Array(); 
  var arrTo = new Array(); 
  var arrLU = new Array();
  var lastIndex;
  var i;

  for (i = 0; i < tbTo.options.length; i++) 
  {
    arrLU[tbTo.options[i].text] = tbTo.options[i].value;
    arrTo[i] = tbTo.options[i].text;
  }

  var fLength = 0;
  var tLength = arrTo.length;

  for (i = 0; i < tbFrom.options.length; i++) 
  {    
    if (tbFrom.options[i].selected && tbFrom.options[i].value != "") 
    {
       lastIndex = tbFrom.options[i].text.lastIndexOf("(");
       arrTo[tLength] = tbFrom.options[i].text.substr(0, lastIndex);
       arrLU[arrTo[tLength]] = tbFrom.options[i].value;
       tLength++;
    }
    else 
    {
       arrFrom[fLength] = tbFrom.options[i].text;
       arrLU[tbFrom.options[i].text] = tbFrom.options[i].value;
       fLength++;
    }
  }

  tbFrom.length = 0;
  tbTo.length = 0;
  var ii;

  for(ii = 0; ii < arrFrom.length; ii++) 
  {
    var no = new Option();
    no.value = arrLU[arrFrom[ii]];
    no.text = arrFrom[ii];
    tbFrom[ii] = no;
  }

  for(ii = 0; ii < arrTo.length; ii++) 
  {
    var no = new Option();
    no.value = arrLU[arrTo[ii]];
    no.text = arrTo[ii];
    tbTo[ii] = no;
  }
}

function moveItemsAddDuration(tbFrom, tbTo, duration) 
{
  if (duration.value == ""){
    alert("Please specify duration for the selected actor(s)");
    return;
  }

  var arrFrom = new Array(); 
  var arrTo = new Array(); 
  var arrLU = new Array();
  var i;

  for (i = 0; i < tbTo.options.length; i++) 
  {
    arrLU[tbTo.options[i].text] = tbTo.options[i].value;
    arrTo[i] = tbTo.options[i].text;
  }

  var fLength = 0;
  var tLength = arrTo.length;

  for (i = 0; i < tbFrom.options.length; i++) 
  {    
    if (tbFrom.options[i].selected && tbFrom.options[i].value != "") 
    {
       arrTo[tLength] = tbFrom.options[i].text + "(" + duration.value + ")";
       arrLU[arrTo[tLength]] = tbFrom.options[i].value;
       tLength++;
    }
    else 
    {       
       arrFrom[fLength] = tbFrom.options[i].text;
       arrLU[tbFrom.options[i].text] = tbFrom.options[i].value;
       fLength++;
    }
  }

  tbFrom.length = 0;
  tbTo.length = 0;
  var ii;

  for(ii = 0; ii < arrFrom.length; ii++) 
  {
    var no = new Option();
    no.value = arrLU[arrFrom[ii]];
    no.text = arrFrom[ii];
    tbFrom[ii] = no;
  }

  for(ii = 0; ii < arrTo.length; ii++) 
  {
    var no = new Option();
    no.value = arrLU[arrTo[ii]];
    no.text = arrTo[ii];
    tbTo[ii] = no;
  }
}
package utils;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Vector;

public class HTMLUtils {
	
	/*get HTML code of the header*/
	public String getHtmlHeader(){
		String output = "";
	  output += "<HTML><HEAD>";
	  return output;
	}
	
	/*get HTML code of a title*/
	public String getTitle(String title, int width, int height){
		String output="";
//		output += "<TITLE> " + title + " </TITLE></HEAD><BODY style=\"background-image:url(images/background.png);background-size: "+width+"% "+height+"%;\">";
                output += "<TITLE> " + title + " </TITLE></HEAD><BODY>";
		return output;
	}
	
	/*get HTML code of a footer*/
	public String getHtmlFooter() {
	  String output="";
	  
	  output += "</BODY></HTML>";
	  
	  return output;
	}

	/*get HTML code of a menu*/
	public String getHtmlMenu(){
		String output;
		output = "<table align=left><tr align=left>";
		
		output += "<td><img name=\"homebutton\" src=\"images/home.gif\" ";
		output += "onmouseover=\"javascript:document['homebutton'].src='images/home_on.gif'\" ";
		output += "onmouseout=\"javascript:document['homebutton'].src='images/home.gif'\" ";
		output += "onclick=\"javascript:window.location.href='welcome.jsp'\"></td>";
		
		output += "<td><img name=\"problemdescription\" src=\"images/problemdescription.gif\" ";
		output += "onmouseover=\"javascript:document['problemdescription'].src='images/problemdescription_on.gif'\" ";
		output += "onmouseout=\"javascript:document['problemdescription'].src='images/problemdescription.gif'\" ";
		output += "onclick=\"javascript:go('/problemview')\"></td>";
		
		output += "<td><img name=\"schedulesolution\" src=\"images/schedulesolution.gif\" ";
		output += "onmouseover=\"javascript:document['schedulesolution'].src='images/schedulesolution_on.gif'\" ";
		output += "onmouseout=\"javascript:document['schedulesolution'].src='images/schedulesolution.gif'\" ";
		output += "onclick=\"javascript:go('/cscheduler')\"></td>";
		
		output += "<td><img name=\"ganttchartview\" src=\"images/ganttchart.gif\" ";
		output += "onmouseover=\"javascript:document['ganttchartview'].src='images/ganttchart_on.gif'\" ";
		output += "onmouseout=\"javascript:document['ganttchartview'].src='images/ganttchart.gif'\" ";
		output += "onclick=\"javascript:go('/ganttchartview')\"></td>";
		
		output += "</tr></table>";
		return output;
	}

	/*get HTML code of a head*/
	public String getHead(int level, String heading) {
	  String output;
		output = "<H" + level + "> " + heading + "</H" + level + ">";
		return output;
	}

	/*get HTML code of a table head*/
	public String getTableHead(String align, int border) {
    String output = null;
    output = "<TABLE CELLPADDING=2 CELLSPACING=2 bordercolor=#C1D2EE align=" + align + " border=" + border + ">";
    return output;
  }

	/*get HTML code of a table tail*/
	public String getClosedTable(){
		String output;
		output = "</TABLE>";
		return output;
	}
	
	/*get HTML code of a TH*/
	public String getTH(String align, String value) {
	  String THCell = null;
	  THCell = "<TH bgcolor=#C1D2EE align=" + align + "> " + value + " </TH>";
	  return THCell;
	}
		
	/*get HTML code of a TH*/
	public String getTH(String align, String value, int width) {
	  String THCell = null;
	  THCell = "<TH bgcolor=#C1D2EE width=" + width + " align=" + align + ">" + value + "</TH>";
	  return THCell;
	}
	
	/*get HTML code of a TR*/
	public String getTR(String align) {
	  String TRCell = null;
	  TRCell = "<TR align=" + align + ">";
	  return TRCell;
	}

	/*get HTML code of a TR*/
	public String getTR() {
	  String TRCell = null;
	  TRCell = "<TR>";
	  return TRCell;
	}

	/*get HTML code to close a TR*/
	public String getClosedTR() {
	  String TRCell = null;
	  TRCell = "</TR>";
	  return TRCell;
	}

	/*get HTML code of a TD*/
	public String getTD(String align, String value) {
	  String TDCell = null;
	  TDCell = "<TD align=" + align + "> " + value + " </TD>";
	  return TDCell;
	}

		/*get HTML code of a TD*/
	public String getTD(int width) {
	  String TDCell = null;
	  TDCell = "<TD WIDTH=" + width + ">";
	  return TDCell;
	}

	/*get HTML code of a TD*/
	public String getTD() {
	  String TDCell = null;
	  TDCell = "<TD>";
	  return TDCell;
	}

	/*get HTML code to close a TD*/
	public String getClosedTD() {
	  String TDCell = null;
	  TDCell = "</TD>";
	  return TDCell;
	}
	
	/*get HTML code of table contents*/
	public String getTableContents(String align, @SuppressWarnings("rawtypes") Vector values, int elementCounter) throws IOException {
		StringWriter Cells = new StringWriter();
	  String contents = new String();
	  int vsize = values.size();

	  Cells.write("<TR>");
	  for (int i = 0; i < vsize; i++) {
	  	String value = values.elementAt(i).toString();
 
	  	if (i != 0) {
	      if (i >= elementCounter) {
	        if (i % elementCounter == 0) {
	          Cells.write("</TR>\n\n<TR>");
	        }
	      }
	    }
	    Cells.write("<TD align=" + align + "> " + value + " </TD> \n");
	  }

	  Cells.write("</TR></TABLE>");

	  contents = Cells.toString();
	  Cells.flush();
	  Cells.close();

	  return contents;
	}

	/*get HTML code of a BR*/
	public String getBR(int lines) {

	  StringWriter lineBR = new StringWriter();
	  String lineBRs = new String();

	  for (int i = 0; i <= lines; i++) {
	    lineBR.write("<BR>\n");
	  }
	  lineBRs = lineBR.toString();

	  return lineBRs;
	}

	/*get HTML code of LI*/
	public String getLI(String item) {
	  String li = new String("<LI>");
	  li += item;
	  return li;
	}
	
	/*get HTML code to open a form*/
	public String getFormHead(String actionTarget){
		String output;
		output = "<FORM action=" + actionTarget + " method=post enctype=multipart/form-data name=myform>";
		return output;
	}
	
	/*get HTML to close a form*/
	public String getClosedForm(){
		String output;
		output = "</FORM>";
		return output;
	}
	
	/*get HTML code of a hidden input*/
	public String getHiddenField(String fieldName, String fieldValue){
		String output;
		output = "<input type=hidden name=" + fieldName + " value=" + fieldValue + ">";
		return output;
	}
	
	/*get HTML code of a submit button*/
	public String getSubmitButton(String buttonCaption){
		String output;
		output = "<input type=submit name=button value=" + buttonCaption + ">";
		return output;
	}
	
	/*get HTML code of a check box*/
	public String getCheckBox(String id){
		String output;
		output = "<input type=checkbox name=" + id + ">";
		return output;
	}
}

package utils;

public class ScriptUtils {
	
	/*get HTML code to include gantt chart library*/
	public String getGeneralLibrary(){
		String output = "";
		
		output += "<script language=\"javascript\" src=\"./scripts/general.js\"></script>";
		
		return output;
	}
	
	public String getGanttChartLibrary(){
		String output = "";
		
		output += "<link rel=\"stylesheet\" type=\"text/css\" href=\"jsgantt.css\"/>";
		output += "<script language=\"javascript\" src=\"jsgantt.js\"></script>";
		
		return output;
	}
	
	/*get HTML code to include DynAPI library*/
	public String getDynAPIlibrary(){
		String output = "";
		
		/*include library*/
		output += "<script language='javaScript' src='./dynapi/dynapi.js'></script>";
		output += "<script language='javascript'>";
		output += "dynapi.library.setPath('./dynapi/');";
		output += "dynapi.library.include('dynapi.api');";
		output += "dynapi.library.include('HTMLMenu');";
		output += "dynapi.library.include('Image');";
		output += "</script>";
		
		/*write style to browser*/
		output += "<script language='javascript'>";
		output += "HTMLComponent.writeStyle({MNUItm: 'border: 2px solid #E0E0E0', MNUItmText: 'cursor: default; text-decoration: none; color: #000000; font: 14px Arial, Helvetica;'});";
		output += "var mnu = dynapi.document.addChild(new HTMLMenu(),'mnu');";
		output += "mnu.backCol = '#C1D2EE';"; //#EFEBD7
		output += "mnu.selBgCol = '#14AA14';"; //#C1D2EE
		output += "mnu.cssMenu = 'MNU';";
		output += "mnu.cssMenuText = 'MNUItmText';";
		output += "mnu.cssMenuItem = 'MNUItm';";
		output += "</script>";
			
		return output;
	}
	
	/*get HTML code to insert menu items*/
	public String getMenuScript(){
		String output = "";
		
		output += "<script language='javascript'>";
		output += "var mbar;";
		
		/*main menu*/
		output += "mbar = mnu.createMenuBar('main',80,25);";
		output += "mbar.addItem(null,'Home','home','javascript:goMenu(\"home\");');";		
		output += "mbar.addItem(null,'Services','services','javascript:goMenu(\"services\");');";
		output += "mbar.addItem(null,'Search','search','javascript:goMenu(\"search\");');";
		output += "mbar.addItem(null,'Log','log','javascript:goMenu(\"home\");');";
		
		/*log menu*/
		output += "mbar = mnu.createMenuBar('log',112);";
		output += "mbar.addItem(null,'Data Rate','datarate','javascript:goMenu(\"datarateview\");');";
		output += "mbar.addItem(null,'Data size','datasize','javascript:goMenu(\"datasizeview\");');";
		output += "mbar.addItem(null,'Latency','latency','javascript:goMenu(\"latencyview\");');";
		output += "mbar.addItem(null,'Object Count','objectcount','javascript:goMenu(\"objectcountview\");');";
		output += "mbar.addItem(null,'Subscribe Start','subscribestart','javascript:goMenu(\"subscribestartview\");');";
		output += "mbar.addItem(null,'Subscribe End','subscribeend','javascript:goMenu(\"subscribeendview\");');";
		output += "mbar.addItem(null,'Timestampt','timestampt','javascript:goMenu(\"timestamptview\");');";
		output += "mbar.addItem(null,'Valid Schema','validschema','javascript:goMenu(\"validschemaview\");');";
		
		output += "</script>";
		
		return output;
	}
	
	/*get HTML code to insert menu items displayed in mobile devices*/
	public String getMenuScriptMobile(){
		String output = "";
		
		output += "<script language='javascript'>";
		output += "var mbar;";
		
		/*main menu*/
		output += "mbar = mnu.createMenuBar('main',100,25);";
		output += "mbar.addItem(null,'Search Task','searchtask','javascript:goMenu(\"searchtask\");');";
		output += "mbar.addItem(null,'Search User','searchuser','javascript:goMenu(\"searchuser\");');";
	
		output += "</script>";
		
		return output;
	}
	
	/*get HTML code to display menu*/
	public String getMenuDisplay(){
		String output = "";
		
		output += "<script>dynapi.document.insertChild(mnu);</script>";
		
		return output;
	}
}

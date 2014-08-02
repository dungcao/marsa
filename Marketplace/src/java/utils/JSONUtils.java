package utils;

import java.util.ArrayList;

public class JSONUtils {
	
	/*convert a list of term-value pairs to JSON*/
	public static String termvalueToJSON(ArrayList<String> termvalueList){
		String strJSON = "";
		
		for (int i = 0; i < termvalueList.size(); i = i + 2)
			strJSON += "," + termvalueList.get(i) + ":" + termvalueList.get(i + 1); 
		
		return strJSON.substring(1);
	}
	
	/*convert JSON to a list of term-value pairs*/
	public static ArrayList<String> jsonToTermvalue(String strJSON){
		ArrayList<String> termvalueList = new ArrayList<String>();
		
		String[] termvaluePairs = strJSON.split(",");
		for (int i = 0; i < termvaluePairs.length; i++){
			String[] termValuePair = termvaluePairs[i].split(":");			
			termvalueList.add(termValuePair[0]);			
			
			if (termValuePair.length < 2) termvalueList.add("");
			else termvalueList.add(termValuePair[1]);
		}
		
		return termvalueList;
	}
}

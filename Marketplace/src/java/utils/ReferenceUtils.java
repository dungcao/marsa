package utils;

import java.util.ArrayList;

public class ReferenceUtils {

	public static boolean isTermIn(ArrayList<String> reference, String term){
		for (int i = 0; i < reference.size(); i++){
			if (reference.get(i).equals(term))
				return true;
		}
		
		return false;
	}
}

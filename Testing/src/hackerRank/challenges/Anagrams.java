package hackerRank.challenges;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Anagrams {
	
	static Map<Character, Integer> getCharOccurrences(String s){
		
		Map<Character, Integer> charsMap = new HashMap<>();
		
		s.toLowerCase()
		 .chars()
		 .forEach(c -> charsMap.compute(new Character((char) c), (Key, Value) -> (Value == null) ? 1 : Value+1 ));
		
//    	String lowerString = s.toLowerCase();
//    	for (int i=0; i< lowerString.length(); i++) {
//    		charsMap.compute(lowerString.charAt(i), (Key, Value) -> (Value == null) ? 1 : Value+1 );
//    	}
		
		return charsMap;
	}
	
    static boolean isAnagram(String a, String b) {
    	
    	if( a.length() != b.length()) 
    		return false;
    	
    	if( a.equalsIgnoreCase(b) ) 
    		return true;
    	
    	Map<Character, Integer> firstMap = getCharOccurrences(a);
    	Map<Character, Integer> secondMap = getCharOccurrences(b);
    	
    	return firstMap.equals(secondMap);
    }

  public static void main(String[] args) {
    
        Scanner scan = new Scanner(System.in);
        String a = scan.next();
        String b = scan.next();
        scan.close();
        boolean ret = isAnagram(a, b);
        System.out.println( (ret) ? "Anagrams" : "Not Anagrams" );
    }
}



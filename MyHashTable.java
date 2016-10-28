import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

import javax.xml.transform.Templates;

public class MyHashTable {

	Hashtable<Character, List<String>> wordsDict = new Hashtable<>();
	
	public void createHashTable() {
		
		try {			
			
			FileReader ip = new FileReader("C:/Users/Amruta Folane/Desktop/project4_dictionary.txt");
			BufferedReader bfrd = new BufferedReader(ip);
			
			String word = bfrd.readLine();
			char temp;
			
			while (word != null) {
				List<String> list1 = new ArrayList<>();
				temp = word.charAt(0);			//checking the first character for key
							
				while ( word != null && temp == word.charAt(0) ){
					
					list1.add(word);
					word = bfrd.readLine();
					
				}	
				wordsDict.put(temp, list1); 
			}	
			
		} catch (Exception e){
			System.out.println(e.getMessage());
		}
		
	}	
}

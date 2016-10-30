//NET ID: ASF160130

import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

import javax.naming.StringRefAddr;
import javax.sound.midi.VoiceStatus;
import javax.xml.transform.Templates;

public class MyHashTable {

	public void MyHashTable () {
		
		int tableSize;
		createHashTable();
	}
	
	Hashtable<Character, List<String>> wordsDict = new Hashtable<>();
	
	public void createHashTable() {
		
		
		
	}
	
	public void add() {
		
		try {			
			
			FileReader ip = new FileReader("C:/Users/Amruta Folane/Desktop/project4_dictionary.txt");
			BufferedReader bfrd = new BufferedReader(ip);
			
			String word = bfrd.readLine();
			char temp;
			
			while (word != null) {
				List<String> list1 = new ArrayList<>();
				temp = word.charAt(0);							//checking the first character for key
							
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
	
	public List<String> getList (String key) {
		
		List<String> listOfAlp = wordsDict.get(key);
		System.out.println(listOfAlp);
		return listOfAlp;
	}
	
	public void main(String []args) {
		
		List<String> listOfAlp = wordsDict.get('k');
		System.out.println(listOfAlp);
	}
}

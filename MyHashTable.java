//NET ID: ASF160130
//SEPARATE CHAIN IMPLEMENTATION

import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;

import javax.naming.StringRefAddr;
import javax.print.attribute.standard.PrinterMessageFromOperator;
import javax.sound.midi.VoiceStatus;
import javax.swing.plaf.synth.SynthScrollBarUI;
import javax.xml.transform.Templates;

class HashNode {

	Integer key;
	String value;

	// next node reference
	HashNode  next;

	//constructor
	public HashNode(String value) {

		this.value = value;

	}

}

public class MyHashTable  {

	// table is used to store array of chains
	private ArrayList<HashNode> table;
	private int tableSize;
	private int currSize;


	//constructor
	public MyHashTable ( int tS ) {

		table = new ArrayList<HashNode>(Collections.nCopies(tS, new HashNode("_")));	
		tableSize = tS;
		currSize = 0;

	}

	//returns the current size
	public int tSize() { 
		return currSize; 
	}


	//GET VALUE for a key
	public HashNode getValue (Integer key) {

		//get the head node for the key
		HashNode head = table.get(key);

		//check if the key is already present
		while ( head != null) {

			if (head.key.equals(key)){
				return	head;			
			}
			head = head.next;				
		}

		return null;		
	}

	//ADD a new (key,value) pair in the hashTable
	public void add( String value) {		
		
	if (!value.contains("'")){				//word puzzle does not contain words with "'", therefore ignore them
				
		Integer key = 0;
		
		//get key for the value
		if (value.length() == 1)
			key = 1 * (value.charAt(0) - 'a');
		else if (value.length() == 2)
			key = (value.charAt(0) - 'a') + 2 * (value.charAt(1) - 'a');
		else if (value.length() == 3)
		{
			key = 1 * (value.charAt(0) - 'a') + 2 * (value.charAt(1) - 'a') +
	                3 * (value.charAt(2) - 'a');
		}
		else 
			key = (value.charAt(0) - 'a') + 2 *(value.charAt(1) - 'a') +
		                3 * (value.charAt(2) - 'a') + 4 * (value.charAt(3) - 'a');

		HashNode head = table.get(key);

		HashNode newNode = new HashNode(value);
		newNode.value = value;
		newNode.next = null;
		
		if (head.value == null) {			
			
			table.set(key, newNode);
			
		}
		
		//check if the key is already present
		else  {

			table.set(key, newNode);
			newNode.next = head;
			
		}

		currSize++;

	}

	}

	public boolean isPresent (String value) {
		
		Integer key = 0;
		//System.out.println("value:  " + value);
		//get key for the value
		if (value.length() == 1)
			key = 1 * (value.charAt(0) - 'a');
		else if (value.length() == 2)
			key = (value.charAt(0) - 'a') + 2 * (value.charAt(1) - 'a');
		else if (value.length() == 3)
		{
			key = 1 * (value.charAt(0) - 'a') + 2 * (value.charAt(1) - 'a') +
	                3 * (value.charAt(2) - 'a');
		}
		else 
			key = (value.charAt(0) - 'a') + 2 *(value.charAt(1) - 'a') +
		                3 * (value.charAt(2) - 'a') + 4 * (value.charAt(3) - 'a');


		HashNode head = table.get(key);
		while ( head != null) {
			
			if (head.value.equals(value))
				return true;
			else
				head = head.next;	
		}
		
		return false;
		
	}

}

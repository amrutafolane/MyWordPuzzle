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
import javax.xml.transform.Templates;

class HashNode {

	Character key;
	String value;

	// next node reference
	HashNode  next;

	//constructor
	public HashNode(String value) {

		this.value = value;

	}

}

public class MyHashTable  {

	// bucketArray is used to store array of chains
	private ArrayList<HashNode> table;
	private int tableSize;
	private int currSize;


	//constructor
	public MyHashTable ( int tS ) {

		table = new ArrayList<HashNode>(Collections.nCopies(tS, new HashNode(null)));	
		tableSize = tS;
		currSize = 0;

	}

	//returns the current size
	public int tSize() { 
		return currSize; 
	}

	//GET INDEX for a particular key
	public int getTableIndex (Character key) {

		//convert alphabets into sequential numbers 0-25
		char c = (char)key; 
		int index = c - 'a';
		//System.out.println("index:" + index);
		return index;

	}


	//GET VALUE for a key
	public HashNode getValue (Character key) {

		//get the head node for the key
		int tableIndex = getTableIndex(key);
		HashNode head = table.get(tableIndex);

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

		//get key for the value
		Character key = value.charAt(0);

		//get the head node for the key
		int tableIndex = getTableIndex(key);
		HashNode head = table.get(tableIndex);

		HashNode newNode = new HashNode(value);
		newNode.value = value;
		
		if (head.value == null) {			
			
			table.set(tableIndex, newNode);
			
		}
		
		//check if the key is already present
		else  {

			table.set(tableIndex, newNode);
			newNode.next = head;
			
		}

		//insert the key, value in the respective List
		currSize++;

	}



	public boolean isPresent (String word) {
		
		int tableIndex = getTableIndex(word.charAt(0));
		HashNode head = table.get(tableIndex);
		
		while ( head != null) {
			
			if (head.value.equals(word))
				return true;
			else
				head = head.next;
			
		}
		
		return false;
		
	}
//		public static void main(String []args) {
//			MyHashTable ht = new MyHashTable(26);
//	        ht.add("amruta");
//	        ht.add("agenda");
//	        System.out.println(ht.tSize());
//	        System.out.println(ht.tSize());
//	        if(ht.isPresent("amruta")){System.out.println("xyz");}
//
//	}

}

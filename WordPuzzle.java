import java.awt.AlphaComposite;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import javax.security.sasl.SaslClient;

public class WordPuzzle {

	private static final char[][] temp = null;
	String alphabets = "abcefghijklmnopqrstuvwxyz";
	char[][] myGrid;
	char[][] transGrid;

	ArrayList<String> arrStr = new ArrayList<>();
	
	//constructor
	public WordPuzzle(int r, int c) {
 
		myGrid = new char[r][c];
		transGrid = new char[r][c];
		createGrid(r,c);
		
	}

	//create a grid of (r,c)
	public char[][] createGrid(int r, int c) {
		
		char alp;
		
		Random rand = new Random();		
		
		//populating the grid randomly
		for (int i=0; i<r; i++){						
			for (int j=0; j<c; j++){
				//generate a random alphabet
				int rNo = rand.nextInt(25) + 0;
				alp = alphabets.charAt(rNo);
				
				myGrid[i][j] = alp; 
			}
		}		
		return myGrid;
	}
	
	//print the word puzzle
	public void printPuzzle( int r, int c) {
	
		for (int i=0; i<r; i++){						
			for (int j=0; j<c; j++){				
				System.out.print(myGrid[i][j] + " ");				
			}
			System.out.println("");
		}
	}
	
//	//print the word puzzle transpose
//		public void printPuzzleT( int r, int c) {
//		
//			for (int i=0; i<r; i++){						
//				for (int j=0; j<c; j++){				
//					System.out.print(transGrid[i][j] + " ");				
//				}
//				System.out.println("");
//			}
//		}
		
	//reverse strings
	public String reverseStr (String str){
			
		StringBuilder sb = new StringBuilder();
		sb.append(str);
		sb.reverse();
		String revStr = sb.toString();
		return revStr;
	}
		
	//get words from rows/horizontal, fwd & bkwd
	public void getRowWords(int r, int c){
		
		for (int row=0; row<r; row++){
			char[] rows = myGrid[row];
			String entireRow = String.valueOf(rows);
			String revEntireRow = reverseStr(entireRow);
			//System.out.println(entireRow);
			for (int i=0; i<r; i++){
				for (int j=i+1; j<c+1; j++){
					 String str = entireRow.substring(i, j);
					 arrStr.add(str);
					 String revStr = revEntireRow.substring(i, j);
					 arrStr.add(revStr);
				}
			}
			
		}
		//System.out.println(arrStr.size());
	}
	
	
	//make transpose of the grid
	public void gridTranspose(int r, int c){
		
		for (int i=0; i<r; i++){
			for (int j=0; j<c; j++){
				 transGrid[i][j] = myGrid[j][i];				 
			}
		}
	}
	
	
	//get words from cols/vertical, fwd & bkwd
	public void getColWords(int r, int c){
		
		gridTranspose(r, c);
		for (int col=0; col<c; col++){
			
			char[] cols = transGrid[col];
			String entireCol = String.valueOf(cols);
			String revEntireCol = reverseStr(entireCol);
			
			for (int i=0; i<c; i++){
				for (int j=i+1; j<r+1; j++){
					 String str = entireCol.substring(i, j);
					 arrStr.add(str); 
					 String revStr = revEntireCol.substring(i, j);
					 arrStr.add(revStr);
				}
			}
		}
		//System.out.println(arrStr.size());
	}
	
	
	
	//main method
	public static void main(String []args){
		
		int r=0,c=0;
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Enter the number of rows and columns of the grid: ");
		System.out.println("r = ");
		r = scan.nextInt();
		System.out.println("c = ");
		c = scan.nextInt();
		
		//creating a word puzzle
		WordPuzzle puzzle = new WordPuzzle(r, c);
		puzzle.printPuzzle(r, c);			
		
		MyHashTable dict = new MyHashTable();
		dict.createHashTable();
		
		puzzle.getRowWords(r, c);
		puzzle.getColWords(r, c);

		System.out.println(puzzle.arrStr);
		
		
	}
}

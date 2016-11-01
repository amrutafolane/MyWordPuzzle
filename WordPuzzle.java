//NET ID: ASF160130


import java.awt.AlphaComposite;
import java.awt.FontFormatException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

import javax.security.sasl.SaslClient;

import org.omg.Messaging.SyncScopeHelper;
import org.w3c.dom.ls.LSInput;

public class WordPuzzle {

	private static final char[][] temp = null;
	String alphabets = "abcefghijklmnopqrstuvwxyz";
	char[][] myGrid;
	char[][] transGrid;

	ArrayList<String> arrStr = new ArrayList<>();
	List<String> processedList  = new ArrayList<>();

	//constructor
	public WordPuzzle(int r, int c) {

		myGrid = new char[r][c];
		transGrid = new char[c][r];
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
			for (int i=0; i<r; i++){
				for (int j=i+1; j<c+1; j++){
					String str = entireRow.substring(i, j);
					arrStr.add(str);
					if (str.length() > 1){
						String revStr = reverseStr(str);
						arrStr.add(revStr);
					}
				}
			}

		}
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

			for (int i=0; i<c; i++){
				for (int j=i+1; j<r+1; j++){
					String str = entireCol.substring(i, j);
					arrStr.add(str); 
					if (str.length() > 1){
						String revStr = reverseStr(str);
						arrStr.add(revStr);
					}
				}
			}
		}
	}


	//print diagonals bottom left to top right
	public void printDiagsBlTr (int r, int c) {

		for( int k = 0 ; k < 2*r-1; k++ ) {
			StringBuilder sb = new StringBuilder();
			for( int j = 0 ; j <= k ; j++ ) {
				int i = k - j;
				if( i < r && j < c ) {
					sb.append(myGrid[i][j]);				//append each word along the diag
				}
			}
			String entireDiagBltr = sb.toString();

			//adding diag and reverse adding
			if (sb.length() > 1){							//reversing only those with length > 1

				for (int i=0; i<sb.length(); i++){
					for (int j=i+1; j<sb.length(); j++){
						String str = entireDiagBltr.substring(i, j);
						arrStr.add(str); 
						if (str.length() > 1){
							String revStr = reverseStr(str);
							arrStr.add(revStr);
						}
					}
				}

			}												//clear sb for next diag
		}
	}


	//print diagonals top left to bottom right
	public void printDiagsTlBr (int r, int c) {

		for( int k = 0 ; k < 2*r-1; k++ ) {
			StringBuilder sb = new StringBuilder();
			for( int j = 0 ; j <= k ; j++ ) {
				int i = r - k + j - 1;
				if( i>=0 && i < c && j < r ) {
					sb.append(myGrid[i][j]);				//append each word along the diag
				}
			}
			String entireDiagTlBr = sb.toString();

			//adding diag and reverse adding
			if (sb.length() > 1){							//reversing only those with length > 1

				for (int i=0; i<sb.length(); i++){
					for (int j=i+1; j<sb.length()+1; j++){
						String str = entireDiagTlBr.substring(i, j);
						arrStr.add(str); 
						if (str.length() > 1){
							String revStr = reverseStr(str);
							arrStr.add(revStr);
						}
					}
				}

			}												//clear sb for next diag
		}
	}


	//pre-processing on the arrayList created
	//delete repetitions and sort the list
	public void preProcess(int r, int c) {	

		processedList = arrStr.stream().distinct().collect(Collectors.toList());
		Collections.sort(processedList);
	}

	
	//----------------------------------------------------------------------------------------------------
	//----------------------------------------- LINKEDLIST -----------------------------------------------
	//----------------------------------------------------------------------------------------------------

	public void compareLinkedList () {
		
		LinkedList<String> linkedDict = new LinkedList<>();
		try {
			
			FileReader ip = new FileReader("C:/Users/Amruta Folane/Desktop/project4_dictionary.txt");
			BufferedReader bfrd = new BufferedReader(ip);

			String word = bfrd.readLine();

			while (word != null) {
				linkedDict.add(word);
				word = bfrd.readLine();
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}


		//compare with linkedList
		List<String> linkedPresent = new ArrayList<>();

		for (int i=0; i<processedList.size(); i++) {
			String key = processedList.get(i);
			if (linkedDict.contains(key)) {
				linkedPresent.add(key);
			}			
		}

		System.out.println("The words present in the word puzzle are,linkedPresent: "+ linkedPresent);

	}

	//----------------------------------------------------------------------------------------------------
	//----------------------------------------- AVL TREE -----------------------------------------------
	//----------------------------------------------------------------------------------------------------

	public void compareTree () {

		AvlTree<String> treeDict = new AvlTree<String>( );

		try {
			
			FileReader ip = new FileReader("C:/Users/Amruta Folane/Desktop/project4_dictionary.txt");
			BufferedReader bfrd = new BufferedReader(ip);

			String word = bfrd.readLine();

			while (word != null) {
				treeDict.insert(word);
				word = bfrd.readLine();
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}


		//comparison with tree
		List<String> treePresent = new ArrayList<>();
		for (int i=0; i<processedList.size(); i++) {

			String key = processedList.get(i);
			if ( treeDict.contains(key)) {
				treePresent.add(key);
			}			
		}
		System.out.println("The words present in the word puzzle are,treePresent: "+ treePresent);

	}

	//compareHashTable
	public void compareHashTable () {

		MyHashTable hashedDict = new MyHashTable(260);

		try {			


			FileReader ip = new FileReader("C:/Users/Amruta Folane/Desktop/project4_dictionary.txt");
			BufferedReader bfrd = new BufferedReader(ip);

			String word = bfrd.readLine();

			while (word != null) {

				hashedDict.add(word);
				word = bfrd.readLine();

			}		

		} catch (Exception e){
			System.out.println(e.getMessage());
		}

		//comparison with hash table
		
				List<String> hashPresent = new ArrayList<>();
				for (int i=0; i<processedList.size(); i++) {

					String key = processedList.get(i);
					if ( hashedDict.isPresent(key)) {
						hashPresent.add(key);
					}			
				}
				System.out.println("The words present in the word puzzle are,hashPresent: "+ hashPresent);

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

		puzzle.getRowWords(r, c);				//all row words
		puzzle.getColWords(r, c);				//all col words
		puzzle.printDiagsBlTr(r, c);			//bottom left to top right
		puzzle.printDiagsTlBr(r, c);			//bottom left to top right

		puzzle.preProcess(r, c);	
		//hastTable object
		puzzle.compareHashTable();
		//linkedList comparison
		puzzle.compareLinkedList();
		//AVL tree comparison
		puzzle.compareTree();
	}
}

//AvlTree class
//
// CONSTRUCTION: with no initializer
//
// ******************PUBLIC OPERATIONS*********************
// void insert( x )       --> Insert x
// boolean contains( x )  --> Return true if x is present
// void printTree( )      --> Print tree in sorted order
// ******************ERRORS********************************
// Throws UnderflowException as appropriate

/**
 * Implements an AVL tree.
 * Note that all "matching" is based on the compareTo method.
 * @author Mark Allen Weiss
 */
class AvlTree<AnyType extends Comparable<? super AnyType>>
{
	/**
	 * Construct the tree.
	 */
	public AvlTree( )
	{
		root = null;
	}

	/**
	 * Insert into the tree; duplicates are ignored.
	 * @param x the item to insert.
	 */
	public void insert( AnyType x )
	{
		root = insert( x, root );
	}

	/**
	 * Remove from the tree. Nothing is done if x is not found.
	 * @param x the item to remove.
	 */
	public void remove( AnyType x )
	{
		System.out.println( "Sorry, remove unimplemented" );
	}


	/**
	 * Find an item in the tree.
	 * @param x the item to search for.
	 * @return true if x is found.
	 */
	public boolean contains( AnyType x )
	{
		return contains( x, root );
	}

	/**
	 * Test if the tree is logically empty.
	 * @return true if empty, false otherwise.
	 */
	public boolean isEmpty( )
	{
		return root == null;
	}

	/**
	 * Print the tree contents in sorted order.
	 */
	public void printTree( )
	{
		if( isEmpty( ) )
			System.out.println( "Empty tree" );
		else
			printTree( root );
	}

	/**
	 * Internal method to insert into a subtree.
	 * @param x the item to insert.
	 * @param t the node that roots the subtree.
	 * @return the new root of the subtree.
	 */
	private AvlNode<AnyType> insert( AnyType x, AvlNode<AnyType> t )
	{
		if( t == null )
			return new AvlNode<AnyType>( x, null, null );

		int compareResult = x.compareTo( t.element );

		if( compareResult < 0 )
		{
			t.left = insert( x, t.left );
			if( height( t.left ) - height( t.right ) == 2 )
				if( x.compareTo( t.left.element ) < 0 )
					t = rotateWithLeftChild( t );
				else
					t = doubleWithLeftChild( t );
		}
		else if( compareResult > 0 )
		{
			t.right = insert( x, t.right );
			if( height( t.right ) - height( t.left ) == 2 )
				if( x.compareTo( t.right.element ) > 0 )
					t = rotateWithRightChild( t );
				else
					t = doubleWithRightChild( t );
		}
		else
			;  // Duplicate; do nothing
		t.height = Math.max( height( t.left ), height( t.right ) ) + 1;
		return t;
	}


	/**
	 * Internal method to find an item in a subtree.
	 * @param x is item to search for.
	 * @param t the node that roots the tree.
	 * @return true if x is found in subtree.
	 */
	private boolean contains( AnyType x, AvlNode<AnyType> t )
	{
		while( t != null )
		{
			int compareResult = x.compareTo( t.element );

			if( compareResult < 0 )
				t = t.left;
			else if( compareResult > 0 )
				t = t.right;
			else
				return true;    // Match
		}

		return false;   // No match
	}

	/**
	 * Internal method to print a subtree in sorted order.
	 * @param t the node that roots the tree.
	 */
	private void printTree( AvlNode<AnyType> t )
	{
		if( t != null )
		{
			printTree( t.left );
			System.out.println( t.element );
			printTree( t.right );
		}
	}

	/**
	 * Return the height of node t, or -1, if null.
	 */
	private int height( AvlNode<AnyType> t )
	{
		return t == null ? -1 : t.height;
	}

	/**
	 * Rotate binary tree node with left child.
	 * For AVL trees, this is a single rotation for case 1.
	 * Update heights, then return new root.
	 */
	private AvlNode<AnyType> rotateWithLeftChild( AvlNode<AnyType> k2 )
	{
		AvlNode<AnyType> k1 = k2.left;
		k2.left = k1.right;
		k1.right = k2;
		k2.height = Math.max( height( k2.left ), height( k2.right ) ) + 1;
		k1.height = Math.max( height( k1.left ), k2.height ) + 1;
		return k1;
	}

	/**
	 * Rotate binary tree node with right child.
	 * For AVL trees, this is a single rotation for case 4.
	 * Update heights, then return new root.
	 */
	private AvlNode<AnyType> rotateWithRightChild( AvlNode<AnyType> k1 )
	{
		AvlNode<AnyType> k2 = k1.right;
		k1.right = k2.left;
		k2.left = k1;
		k1.height = Math.max( height( k1.left ), height( k1.right ) ) + 1;
		k2.height = Math.max( height( k2.right ), k1.height ) + 1;
		return k2;
	}

	/**
	 * Double rotate binary tree node: first left child
	 * with its right child; then node k3 with new left child.
	 * For AVL trees, this is a double rotation for case 2.
	 * Update heights, then return new root.
	 */
	private AvlNode<AnyType> doubleWithLeftChild( AvlNode<AnyType> k3 )
	{
		k3.left = rotateWithRightChild( k3.left );
		return rotateWithLeftChild( k3 );
	}

	/**
	 * Double rotate binary tree node: first right child
	 * with its left child; then node k1 with new right child.
	 * For AVL trees, this is a double rotation for case 3.
	 * Update heights, then return new root.
	 */
	private AvlNode<AnyType> doubleWithRightChild( AvlNode<AnyType> k1 )
	{
		k1.right = rotateWithLeftChild( k1.right );
		return rotateWithRightChild( k1 );
	}

	private class AvlNode<AnyType>
	{
		// Constructors
		AvlNode( AnyType theElement )
		{
			this( theElement, null, null );
		}

		AvlNode( AnyType theElement, AvlNode<AnyType> lt, AvlNode<AnyType> rt )
		{
			element  = theElement;
			left     = lt;
			right    = rt;
			height   = 0;
		}

		AnyType           element;      // The data in the node
		AvlNode<AnyType>  left;         // Left child
		AvlNode<AnyType>  right;        // Right child
		int               height;       // Height
	}

	/** The tree root. */
	private AvlNode<AnyType> root;

}
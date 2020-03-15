package hw4;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;
/*
 * This class is used by:
 * 1. FindSpacing.java
 * 2. FindSpacingDriver.java
 * 3. WordGame.java
 * 4. WordGameDriver.java
 */
public class WordGameHelperClass {
	
	/*
	 * Returns true if an only the string s
	 * is equal to one of the strings in dict.
	 * Assumes dict is in alphabetical order.
	 */
	public static boolean inDictionary(String [] dict, String s) {
		// TODO Implement using binary search
		// Use String's compareTo method to compare
		// two strings. Do not run compareTo more than
		// once on the same pair of strings during the search -- 
		// instead, run it once and save the result in a variable
		// if you need it again.
		// You may want to see https://docs.oracle.com/javase/8/docs/api/java/lang/String.html\  
	    int beg = 0;
        int end = dict.length;
        while (beg <= end) {
          int mid =(beg+end)/2;
          int compare = dict[mid].compareTo(s);
          if (compare == 0) {
            return true;
          }
          else if (compare < 0) {
            beg = mid+1;
          }
          else if (compare > 0) {
            end = mid-1;
           }
        }
		return false;
	}
	
	
	/*
	 *  Returns true if and only if
	 *  dict is in alphabetical order
	 */
	public static boolean checkAlphaOrder(String [] dict) {
		// TODO replace the "return true" below
		// with a check that dict is in alphabetical order
		// Use compareTo method of the String class
		// (you may want to see https://docs.oracle.com/javase/8/docs/api/java/lang/String.html)
		//		 
		// Empty dictionary and one-word dictionary
		// are considered okay
	    if (dict.length <= 1) {
	      return true;
	    }
	    for (int i = 1; i < dict.length; i++) {
	      if (dict[i-1].compareTo(dict[i]) >= 0) {
	        return false;
	      }
	    }
	    

		return true;
	}

	
	/*
	 * Reads in a dictionary file (one word per line) and checks if it's
	 * in alphabetical order. Returns null in case of failure.
	 */
	public static String[] readDictionary (String dictionaryFileName) {
		Scanner fileScanner;
		
		// Open the dictionary file
		try {
			fileScanner = new Scanner (new File (dictionaryFileName));
		}
		catch (IOException e) {
			System.err.println("Unable to open dictionary file. "+e.getMessage());
			System.err.println("Currently in directory "+ System.getProperty("user.dir"));
			return null;
		}

		// read the dictionary file
		LinkedList<String> prelimDict = new LinkedList<String>();
		while (fileScanner.hasNext()) {
			prelimDict.add(fileScanner.next().toLowerCase());
		}
		String [] dict = prelimDict.toArray(new String[0]);
		fileScanner.close();
		if(checkAlphaOrder(dict)) {
			return dict;
		}
		else {
			System.err.println("Error: dictionary not in alphabetical order.");
			return null;
		}
	}
	
	/*
	 *  Returns true if and only if board is a rectangular array:
	 *  that is, board[i].length is the same for every
	 *  i from 0 to board.length-1 
	 */
	public static boolean checkIfRectangle(char [][] board) {
		// TODO replace the "return true" below
		// with an appropriate check
		// Empty board and one-line board
		// should be considered rectangles
	    if (board.length <= 1) {
	      return true;
	    }
	    for (int i = 1; i < board.length; i++) {
	      if (board[i].length != board[i-1].length) {
	        return false;
	      }
	    }
		return true;
	}
	

	/*
	 * Converts a text file into a two-dimensional
	 * array of characters, where a[i][j] is the 
	 * character in row i from the top (starting at 0)
	 * and column j from the left (starting at 0).
	 * Newline characters are not included in the array.
	 * Checks that the array is rectangular.
	 * Returns null in case of failure.
	 */
	public static char[][] readBoard (String boardFileName) {
		Scanner fileScanner;
		// open the board file
		try {
			fileScanner = new Scanner (new File (boardFileName));
		}
		catch (IOException e) {
			System.out.println("Unable to open board file "+e.getMessage());
			System.out.println("Currently in directory "+ System.getProperty("user.dir"));
			return null;
		}

		// read the board file
		LinkedList<char[]> prelimBoard = new LinkedList<char[]>();
		while (fileScanner.hasNext()) {
			prelimBoard.add(fileScanner.next().toLowerCase().toCharArray());
		}
		char [][] board = prelimBoard.toArray(new char[0][]);
		fileScanner.close();
		
		if(checkIfRectangle(board)) {
			return board;
		}
		else {
			System.err.println("Error: board is not rectangular.");
			return null;
		}
	}

}
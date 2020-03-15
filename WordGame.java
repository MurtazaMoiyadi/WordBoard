package hw4;
import java.util.ArrayList;

public class WordGame {
	/*
	 * Returns all strings that appear
	 * as a consecutive horizontal or vertical sequence of letters
	 * (left-right, right-left, up-down, or down-up)
	 * in the array board and also appear in dict.
	 * Note that the same word may appear multiple times
	 * on the board, and will then be multiple times in 
	 * the returned array.
	 * 
	 * dict is assumed to be in alphabetical order
	 * board is assumed to be rectangular
	 */
	public static String[] search(String[] dict, char[][] board) {
		/*The following line contains <String>, this means
		 * that the ret is an ArrayList that contains String. It is an 
		 * ArrayList of only Strings. 
		 */
		ArrayList<String> ret = new ArrayList<String>();
		int height = board.length;
		
		// Set width to 0 for an empty board,
		// and to the width for first line otherwise,
		// because we assume the board is a rectangle
		int width = board.length==0 ? 0 : board[0].length;
		
		// TODO Generate substrings of the board
		// and check if they are in the dictionary
		// by calling inDictionary (which you implement).
		// If they are, use ret.add to add them
		// to the array that gets returned.
		// Character.toString, String+char, and String+=char
		// can all be useful here.
		// You may want to see https://docs.oracle.com/javase/8/docs/api/java/lang/String.html
        // and https://docs.oracle.com/javase/8/docs/api/java/lang/Character.html
		//
		
		// The outer most loop iterates over the height of the board
		// it's inner loop iterates over the width
		// within the inner loop, I have four loops that represent each
		// direction (left-right, right-left, up-down, down-up).
		// Within these four loops, I use Character.toString to turn
		// each individual character into a string and then add it an
		// existing string to concatenate each character in the row or column.
		// I then check if ret already contains the individual character.
		// If it doesn't, I then check if the full string is in the given
		// dictionary. If it is, I add the string to ret.
		for (int i = 0; i<height; i++) 
		{
		  for(int j = 0; j<width; j++) 
		  {
		    String leftToRight = "";
		    for (int k = j; k<width; k++) 
		    {
		      String c = Character.toString(board[i][k]);
		      leftToRight += c;
		      if (ret.contains(c)==false) 
		      {
		        if (WordGameHelperClass.inDictionary(dict, leftToRight)) 
                {
                  ret.add(leftToRight);
                }
		      } 
		    }
		    String rightToLeft = "";
		    for (int k = j; k>=0; k--) 
            {
		      String c2 = Character.toString(board[i][k]);
		      rightToLeft += c2;
		      if (ret.contains(c2)==false) 
		      {
                if (WordGameHelperClass.inDictionary(dict, rightToLeft)) 
                  {
                    ret.add(rightToLeft);
                  }
		      }
             }
		    String upToDown = "";
		    for (int k = i; k<height; k++) 
            {
		      String c3 = Character.toString(board[k][j]);
		      upToDown += c3;
		      if (ret.contains(c3)==false) 
              {
                if (WordGameHelperClass.inDictionary(dict, upToDown)) 
                  {
                    ret.add(upToDown);
                  }
              }
            }
		    String downToUp = "";
		    for (int k = i; k>=0; k--) 
            {
		      String c4 = Character.toString(board[k][j]);
		      downToUp += c4;
		      if (ret.contains(c4)==false) 
              {
                if (WordGameHelperClass.inDictionary(dict, downToUp)) 
                  {
                    ret.add(downToUp);
                  }
              }
		  }
		}
	}
		
		// TODO (10% of your grade): if your board
		// has height h and width w, how many strings
		// do you need to check using inDictionary
		// (assume that you do nothing to filter out
		// duplicates or, equivalently, that the board
		// is such that there are no duplicates)
		// ANSWER: height*width*4
		// EXPLANATION OF THE ANSWER: The outer-most loop runs height number of times,
		// while it's inner loop runs width number of times. In inner-most loops add up to 4,
		// meaning height*width*4 strings would be checked.

		// This line converts ArrayList<String> to String []
		return ret.toArray(new String[0]);
	}
}
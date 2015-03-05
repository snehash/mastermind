package assignment3;

import java.util.ArrayList;

/**
Class to store and manipulate a Code object.
Solves EE422C programming assignment #3
@author Sneha Shrotriya, Robert Gilmore
@version 2.01 2015-03-06
*/

public class Code
{
	private ArrayList<Peg> myCode;
	private String codeString;
	
	/**
	 * Constructs a code made up of Pegs.
	 *
	 * @param s the String representing the code
	 */
	public Code(String s)
	{
		codeString = s;
		myCode = new ArrayList<Peg>();
		for(int i = 0; i<s.length(); i++)
		{
			String sub = s.substring(i, i+1);
			Color pegColor = Color.RED;
			switch(sub){
				case "G": pegColor = Color.GREEN;
					  	  break;
				case "Y": pegColor = Color.YELLOW;
				          break;
				case "B": pegColor = Color.BLUE;
				  		  break;
				case "O": pegColor = Color.ORANGE;
				  		  break;
				case "P": pegColor = Color.PURPLE;
						  break;
				case "M": pegColor = Color.MAROON;
				default: break;
			}
			Peg p = new Peg(i, pegColor);
			myCode.add(p);
		}
	}
	
	/**
	 * Removes a Peg from the Code at the given position
	 *
	 * @param pos the position to remove the Peg from
	 */
	public void removePeg(int pos){
		myCode.remove(pos);
	}
	
	/**
	 * Returns the Peg at a given position in the Code
	 * 
	 * @param pos the position of the Peg
	 * @return Peg at the position given
	 */
	public Peg getPeg(int pos)
	{
		return myCode.get(pos);
	}
	
	/**
	 * Returns the length of the code
	 *
	 * @return int length of the code
	 */
	public int size()
	{
		return myCode.size();
	}
	
	/**
	 * Returns a String representation of the code
	 *
	 * @return String representation of the code
	 */
	public String toString()
	{
		return codeString;
	}
	
	
	/**
	 * Changes the Peg at a given position
	 * @param pos int Position in code to modify 
	 * @param p Peg to place in given position
	 */
	public void setPeg(int pos, Peg p)
	{
		myCode.set(pos, p);
	}
	
}

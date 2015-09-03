package mastermind;

import java.util.ArrayList;

/**
Class to store and manipulate a Code object.
Solves EE422C programming assignment #3
@author Sneha Shrotriya
@version 2.01 2015-03-06
*/

public class Code
{
	private ArrayList<Peg> myCode;
	private String codeString;
	
	/**
	 * Constructs a code made up of Pegs.
	 *
	 * @param s the String representing the code. Should be capital first letter of each Peg color in the Code
	 * 
	 */
	public Code(String s)
	{
		codeString = s;
		myCode = new ArrayList<Peg>();
		for(int i = 0; i<s.length(); i++)
		{
			String sub = s.substring(i, i+1);
			Color pegColor = new Color(sub);
			Peg p = new Peg(i, pegColor);
			myCode.add(p);
		}
	}
	
	
	/**
	 * Returns the Peg at a given position in the Code
	 * 
	 * @param pos the position of the Peg
	 * @return Peg at the position given
	 * Precondition: 0<= pos < Code size
	 * Postcondition: None
	 * Invariant: Code size
	 */
	public Peg getPeg(int pos)
	{
		return myCode.get(pos);
	}
	
	/**
	 * Returns the length of the code
	 *
	 * @return int length of the code
	 * Invariant: Code size
	 */
	public int size()
	{
		return myCode.size();
	}
	
	/**
	 * Use capital first letter of each Peg color to get String representation of Code
	 *
	 * @return String representation of the code
	 * Invariant: Code 
	 */
	public String toString()
	{
		return codeString;
	}
	
	
	/**
	 * Changes the Peg at a given position
	 * @param pos int Position in code to modify 
	 * @param p Peg to place in given position
	 * 
	 * Precondition: 0 <= pos < Code size
	 * Postcondition: index pos now contain Peg p instead of old Peg
	 */
	public void setPeg(int pos, Peg p)
	{
		myCode.set(pos, p);
	}
	
}

package assignment3;

/**
 * Class to hold information about color and position of a Peg
 * Solves EE422C programming assignment #3
 * @author Sneha Shrotriya, Robert Gilmore
 * @version 2.01 2015-03-06
 */

public class Peg 
{
	private int myPosition;
	private Color myColor;
	
	/**
	 * 
	 * @param position int position of Peg in a Code
	 * @param c Color of Peg
	 */
	public Peg(int position, Color c){
		myPosition = position;
		myColor = c;
	}
	
	/**
	 * Return position of Peg in a Code
	 * @return int position of Peg in a Code
	 */
	public int getPosition()
	{
		return myPosition;
	}
	
	/**
	 * Return Color of Peg
	 * @return Color of Peg
	 */
	public Color getColor()
	{
		return myColor;
	}
	
	/**
	 * Determines if two pegs are the same
	 * @param p Peg to check against
	 * @return boolean true if Pegs have same position and Color otherwise false
	 */
	public boolean equals(Peg p)
	{
		if(myPosition == p.getPosition() && myColor.equals(p.getColor()))
		{
			return true;
		}
		return false;
	}

}


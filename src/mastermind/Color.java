package mastermind;

/**
*Class to store Colors.
*Solves EE422C programming assignment #3
*@author Sneha Shrotriya
*@version 2.01 2015-03-06
*/
public class Color
{
	String symbol;
	ColorNum c;
	
	/**
	 * Creates a color from capital first letter of color
	 * @param symbol capital first letter of color name
	 */
	Color(String symbol)
	{
		this.symbol = symbol;
		switch(symbol)
		{
		case "R": c = ColorNum.RED;
				  break;
		case "G": c = ColorNum.GREEN;
			  	  break;
		case "Y": c = ColorNum.YELLOW;
		          break;
		case "B": c = ColorNum.BLUE;
		  		  break;
		case "O": c = ColorNum.ORANGE;
		  		  break;
		case "P": c = ColorNum.PURPLE;
				  break;
		case "M": c = ColorNum.MAROON;
		default: break;
		}
	}

	
	/**
	 * Checks if two colors are the same
	 * @param c Color to check against
	 * @return boolean true if the same, otherwise false
	 * Invariants: Both colors remain unchanged
	 */
	public boolean equals(Color co)
	{
		if(co.getColor() == c)
		{
			return true;
		}
		
		return false;
	}
	
	
	/**
	 * Gets capital first letter of Color name
	 * 
	 * @return String representation of Color
	 * Invariants: Color is unchanged
	 */
	public String toString()
	{
		return this.symbol;
	}
	
/**
 * Gets all the possible colors
 * @return an array with all possible colors
 */
	public static ColorNum[] values()
	{
		return ColorNum.values();
	}
	
	/**
	 * Gets color name 
	 * @return ColorNum value assignment to Color object
	 * Invariant: Color is unchanged 
	 */
	public ColorNum getColor()
	{
		return c;
	}
	
	/**
	 * Enum to keep track of all the colors
	 * @author Sneha Shrotriya, Robert Gilmore
	 *
	 */
	public enum ColorNum
	{
		BLUE,
		GREEN,
		ORANGE,
		PURPLE, 
		YELLOW,
		RED,
		MAROON;
	}
}

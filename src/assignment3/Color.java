package assignment3;

/**
*Enum to store Colors.
*Solves EE422C programming assignment #3
*@author Sneha Shrotriya, Robert Gilmore
*@version 2.01 2015-03-06
*/

public enum Color 
{
	BLUE ("B"),
	GREEN ("G"),
	ORANGE ("O"),
	PURPLE ("P"), 
	YELLOW ("Y"),
	RED ("R"),
	MAROON("M");
	
	String symbol;
	Color(String symbol)
	{
		this.symbol = symbol;
	}
	
	/**
	 * Turns Color into String representations
	 * 
	 * @return String of capital first letter of Color
	 */
	public String toString()
	{
		return this.symbol;
	}
	
	/**
	 * Checks if two colors are the same
	 * @param c Color to check against
	 * @return boolean true if the same, otherwise false
	 */
	public boolean equals(Color c)
	{
		if(c.symbol.equals(symbol))
		{
			return true;
		}
		
		return false;
	}
}

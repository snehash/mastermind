package assignment3;

/*
 * Enum to assign letter to colors
 */
public enum Color {
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
	
	public String toString()
	{
		return this.symbol;
	}
	
	public boolean equals(Color c)
	{
		if(c.symbol.equals(symbol))
		{
			return true;
		}
		
		return false;
	}
}

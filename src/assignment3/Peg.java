package assignment3;

/*
 * Class to hold information about color and position of a Peg
 */
public class Peg 
{
	private int myPosition;
	private Color myColor;
	
	public Peg(int position, Color c){
		myPosition = position;
		myColor = c;
	}
	
	public int getPosition()
	{
		return myPosition;
	}
	
	public Color getColor(){
		return myColor;
	}
	
	public boolean equals(Peg p)
	{
		if(myPosition == p.getPosition() && myColor.equals(p.getColor()))
		{
			return true;
		}
		return false;
	}

}


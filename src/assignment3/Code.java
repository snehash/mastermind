package assignment3;

import java.util.ArrayList;

/*
 * Class to hold all information about codes
 */
public class Code
{
	private ArrayList<Peg> myCode;
	private String codeString;
	
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
	
	public void removePeg(int i){
		myCode.remove(i);
	}
	
	public Peg getPeg(int i)
	{
		return myCode.get(i);
	}

	public int size()
	{
		return myCode.size();
	}
	
	public String toString()
	{
		return codeString;
	}
	
	public void setPeg(int i, Peg p)
	{
		myCode.set(i, p);
	}
}

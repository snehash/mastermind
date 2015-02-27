package assignment3;


/*
 * Class to hold black and white peg count when determining feedback
 */

public class FeedbackResult {
	private int myBlack;
	private int myWhite;
	
	public FeedbackResult()
	{
		myBlack = 0;
		myWhite = 0;
	}
	
	public void addBlack()
	{
		myBlack++;
	}
	
	public void addWhite()
	{
		myWhite++;
	}
	
	public int getBlack()
	{
		return myBlack;
	}
	
	public int getWhite()
	{
		return myWhite;
	}
	
	public String toString()
	{
		return ("White: " + myWhite + " Black: " + myBlack);
	}
}


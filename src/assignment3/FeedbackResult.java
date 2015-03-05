package assignment3;


/**
 * Class to hold black and white peg count when determining feedback
 * Solves EE422C programming assignment #3
 * @author Sneha Shrotriya, Robert Gilmore
 * @version 2.01 2015-03-06
 */

public class FeedbackResult {
	private int myBlack;
	private int myWhite;
	
	/**
	 * Constructs an empty FeedbackResult object with 0 white and black pegs.
	 */
	public FeedbackResult()
	{
		myBlack = 0;
		myWhite = 0;
	}
	
	/**
	 * Adds one black peg to the FeedbackResult
	 */
	public void addBlack()
	{
		myBlack++;
	}
	
	/**
	 * Adds one white peg to the FeedbackResult
	 */
	public void addWhite()
	{
		myWhite++;
	}
	
	/**
	 * Gets the number of black pegs in the FeedbackResult
	 * @return int number of black pegs
	 */
	public int getBlack()
	{
		return myBlack;
	}
	
	/**
	 * Gets the number of white pegs in the FeedbackResult
	 * @return int number of white pegs
	 */
	public int getWhite()
	{
		return myWhite;
	}
	
}


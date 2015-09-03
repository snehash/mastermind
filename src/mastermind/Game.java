package mastermind;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;

import mastermind.Color.ColorNum;


/**
 * Class to do all the game logic
 * Solves EE422C programming assignment #3
 * @author Sneha Shrotriya, Robert Gilmore
 * @version 2.01 2015-03-06
 */

public class Game {

	private Code secretCode;
	private boolean myDebug;
	private String myCodeString;
	private int myCodeSize;
	private ArrayList<Code> myGuesses;
	private ArrayList<FeedbackResult> myFeedback;
	

	/**
	 * Initializes game components and generates secret code.
	 * @param debug boolean to determine whether code should print to screen for testing purposes
	 * @param codeSize int to determine length of code
	 */
	public Game(boolean debug, int codeSize)
	{
		myCodeSize = codeSize;
		myDebug = debug;
		String codeString = "";
		Random rand = new Random();
		ColorNum[] myColors = Color.values();
		for (int i = 0; i<myCodeSize; i++)
		{
			int random = rand.nextInt(myColors.length);
			String peg = "";
			switch(myColors[random])
			 {
				 case RED:  peg = "R";
				 			break;
				 case YELLOW: peg = "Y";
				          	  break;
				 case GREEN: peg = "G";
	          	  			  break;
				 case BLUE: peg = "B";
	  			  			break;
				 case ORANGE: peg = "O";
				 			break;
				 case PURPLE: peg = "P";
				 			break;
				 case MAROON: peg = "M";
				 default: break;
			 }
			codeString += peg;
		}
		
		myCodeString = codeString;
		secretCode = new Code(codeString);
		myGuesses = new ArrayList<Code>();
		myFeedback = new ArrayList<FeedbackResult>();
		
		if (myDebug)
		{
			System.out.println(myCodeString);
		}
	}
	
	/**
	 * Attempts to generate a Code object from the user's guess
	 * 
	 * @param guess String with String representation of Code
	 * @return Code created from guess String
	 * @throws DuplicateGuessException if guess has been made previously
	 * @throws IllegalGuessException if guess is otherwise invalid
	 * Precondition: guess string contains myCodeSize number of capital letters of valid colors 
	 */
	public Code generateCode(String guess)
	{
		
		Pattern valid = Pattern.compile("^[BGOPYRM]{" + myCodeSize + "}$");
		guess.toUpperCase();
		Matcher valid_input = valid.matcher(guess);
		if(!valid_input.find())
		{
			throw new IllegalGuessException();
		}	
		for(int i = 0; i<myGuesses.size(); i++)
		{
			if(myGuesses.get(i).toString().equals(guess))
			{
				throw new DuplicateGuessException();
			}
		}
			
		Code guessCode = new Code(guess);
		return guessCode;
	}
	
	/**
	 * Generates black peg and white peg count encapsulated within FeedbackResult type based on user guess.
	 * 
	 * @param guess Code object of guess made
	 * @return FeedbackResult object with black and white peg count
	 * Precondition: Code guess is a valid guess 
	 */
	public FeedbackResult generateFeedback(Code guess)
	{

		myGuesses.add(guess);
		FeedbackResult feedback = new FeedbackResult();
		Code copy = new Code(myCodeString);
		Code guessCopy = new Code(guess.toString());
		
		//check for perfect matches; black pegs
		for(int i = 0; i<myCodeSize; i++)
		{
			if(secretCode.getPeg(i).equals(guess.getPeg(i)))
			{
				feedback.addBlack();
				copy.setPeg(i, null);
				guessCopy.setPeg(i, null);
			}
		}
		
		//check for correct colors; white pegs
		for(int i = 0; i<copy.size(); i++)
		{
			if(!(copy.getPeg(i) == null))
			{
				for(int j = 0; j<guessCopy.size(); j++)
				{
					if(!(guessCopy.getPeg(j) == null))
					{
						if(copy.getPeg(i).getColor().equals(guessCopy.getPeg(j).getColor()))
						{
							feedback.addWhite();
							copy.setPeg(i, null);
							guessCopy.setPeg(j, null);
							break;
					
						}
					}
				}
			} 
		}
		
		myFeedback.add(feedback);
		return feedback;
	}
	
	/**
	 * Gets string representation of the code
	 * @return String representing code
	 * Invariant: Code
	 */
	public String getCodeString()
	{
		return myCodeString;
	}
	

}

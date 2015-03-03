package assignment3;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;


public class Game {

	private Code secretCode;
	private boolean myDebug;
	private String myCodeString;
	private int myCodeSize;
	private ArrayList<Code> myGuesses;
	private ArrayList<FeedbackResult> myFeedback;
	/*
	 * Initializes new game board and generates secret code.
	 */
	public Game(boolean debug, int codeSize)
	{
		myCodeSize = codeSize;
		myDebug = debug;
		String codeString = "";
		Random rand = new Random();
		Color myColors[] = Color.values();
		for (int i = 0; i<myCodeSize; i++)
		{
			int random = rand.nextInt(myColors.length);
			codeString += myColors[random].toString();
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
	
	public Code getSecretCode()
	{
		return secretCode;
	}
	
	
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
			if(myGuesses.get(i).getCodeString().equals(guess))
			{
				throw new DuplicateGuessException();
			}
		}
			
		Code guessCode = new Code(guess);
		generateFeedback(guessCode);
		return guessCode;
	}
	
	/*
	 * Input: Code
	 * Output: FeedbackResult 
	 * Generates black peg and white peg count encapsulated within FeedbackResult type based on user guess.
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
	

}

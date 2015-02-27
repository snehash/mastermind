package assignment3;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Game {

	private static int codeSize = 5;
	private Code secretCode;
	private Board myBoard;
	private boolean myDebug;
	private String myCodeString;
	private ArrayList<Code> myGuesses;
	private ArrayList<FeedbackResult> myFeedback;
	/*
	 * Initializes new game board and generates secret code.
	 */
	public Game(boolean debug)
	{
		myDebug = debug;
		myBoard = new Board();
		String codeString = "";
		Random rand = new Random();
		Color myColors[] = Color.values();
		for (int i = 0; i<codeSize; i++)
		{
			int random = rand.nextInt(myColors.length);
			codeString += myColors[random].toString();
		}
		
		myCodeString = codeString;
		secretCode = new Code(codeString);
		myGuesses = new ArrayList<Code>();
		myFeedback = new ArrayList<FeedbackResult>();
		
	}
	
	public Code getSecretCode()
	{
		return secretCode;
	}
	
	/*
	 * Input:None
	 * Output: boolean; true if game is won by user
	 * Reads input and determines first if the input is valid and second whether to generate feedback or display history.
	 */
	public boolean turn()
	{
		boolean success = false;
		while(!success)
			{
				Pattern valid = Pattern.compile("^[BGOPYRM]{" + codeSize + "}$");
				String guessString = myBoard.getInput();
				try
				{
					success = true;
					guessString.toUpperCase();
			
					Matcher valid_input = valid.matcher(guessString);
					if(!valid_input.find())
					{
						if(guessString.equalsIgnoreCase("HISTORY"))
						{
							this.history(); 
							return false; 
							
						}
						else
						{
							throw new IllegalGuessException();
						}
					}
					
					boolean made = false;
					for(int i = 0; i<myGuesses.size(); i++)
					{
						if(myGuesses.get(i).getCodeString().equals(guessString))
						{
							myBoard.outputDuplicateGuess(myFeedback.get(i).toString());
							success = false;
							made = true;
							break;
						}
					}
					
					if(made == true)
					{
						continue;
					}
					myBoard.decrementTurns();
					Code guess = new Code(guessString);
					//check if guess if valid
					FeedbackResult feedback = generateFeedback(guess);
					myBoard.outputFeedback(feedback);
					if(feedback.getBlack() == codeSize)
					{
						return true;
					}
					
					return false;
				}
				
				catch(NullPointerException ex)
				{
					continue;
				}
			}
		
		return false;
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
		for(int i = 0; i<codeSize; i++)
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
	
	/*
	 * Input: None
	 * Output: boolean; True if user wins
	 * Determines general flow of game. Use to play game. 
	 */
	public boolean gameRunner()
	{
		boolean success = myBoard.displayGreeting();
		if (!success)
		{
			return false;
		}
		if (myDebug)
		{
			System.out.println("The secret code is " + myCodeString);
		}
		
		boolean win = false;
		while(myBoard.myTurns != 0 && !win)
		{
			try
			{
				win = turn();
			}
			catch(IllegalGuessException ex)
			{
				myBoard.outputDumbUser(codeSize);
			}
			
		}
		
		if(win)
		{
			myBoard.printWin();
		}
		else
		{
			myBoard.printLose();
		}
		
		return myBoard.playAgain();
		
		
	}

	/*
	 * Input: None
	 * Output: None
	 * Displays history of guesses and feedback
	 */
	public void history()
	{ 
	  StringBuilder history = new StringBuilder("");
	  history.append("Your Guess  |  Feedback\n"); 
	  for (int i=0; i< myGuesses.size(); i++)
	  {
		  history.append(myGuesses.get(i).toString() + " ----- "+ myFeedback.get(i).toString() + "\n");
	  }
	  	 JOptionPane.showMessageDialog(null, history);
	}

}

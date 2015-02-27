package assignment3;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
/*This class deals with all the i/o. */

public class Board {
	
	int myTurns;
	Scanner myScanner;
	
	public Board()
	{
		myTurns = 15;
		myScanner = new Scanner(System.in);
	}
	
	/*
	 * Input: None
	 * Output: True if successful
	 * Displays greeting and determined start of play
	 */
	public boolean displayGreeting()
	{
		System.out.println("Welcome to Mastermind.  Here are the rules.\n"
				+ "This is a text version of the classic board game Mastermind.\n"
				+ "The computer will think of a secret code. The code consists of 5 colored pegs.\n"
				+ "The pegs MUST be one of six colors: blue, green, orange, purple, red, maroon, or yellow.\n"
				+ "A color may appear more than once in the code. You try to guess what colored pegs are in the code and what order they are in.\n"
				+ "After you make a valid guess the result (feedback) will be displayed.\n"
				+ "The result consists of a black peg for each peg you have guessed exactly correct (color and position) in your guess.\n"
				+ "For each peg in the guess that is the correct color, but is out of position, you get a white peg.\n"
				+ "For each peg, which is fully incorrect, you get no feedback.\n"
				+ "Only the first letter of the color is displayed. B for Blue, R for Red, and so forth.\n"
				+ "When entering guesses you only need to enter the first character of each color as a capital letter.\n"
				+ "You have 15 guesses to figure out the secret code or you lose the game.  Are you ready to play? (Y/N): ");
		
		
		
		Pattern valid = Pattern.compile("[YNyn]{1}");
		String input = myScanner.next();
		Matcher valid_input = valid.matcher(input);
		while(!valid_input.find())
		{
			System.out.println("Invalid Option. Type 'Y' for yes and 'N' for no.");
			input = myScanner.next();
			valid_input = valid.matcher(input);
		}
		if(input.equalsIgnoreCase("n"))
		{
			return false;
		}
		System.out.println("Generating Secret Code...");
		return true;
	}

	
	public void decrementTurns()
	{
		myTurns--;
	}
	
	/*
	 * Input: None
	 * Output: String
	 * Reads user input from screen and returns as a String
	 */
	public String getInput()
	{
		System.out.println("You have " + myTurns + " guesses left. \nWhat is your next guess?\n"
				+ "Type in the characters for your guess and press enter.\n"
				+ "Enter Guess: ");
		String input = myScanner.next();
		return input;
	}
	
	/*
	 * Displays error message for incorrect output
	 */
	public void outputDumbUser()
	{
		System.out.println("INVALID GUESS. Valid colors are blue, red, green, yellow, orange, maroon, and purple.\nUse only uppercase first letter for colors.\n");
	}
	
	public void outputFeedback(FeedbackResult feedback)
	{
		System.out.println(feedback.toString());
		System.out.println("\n");
	}
	
	public void printWin()
	{
		System.out.println("You win!");
	}
	
	public void printLose()
	{
		System.out.println("You lose :(");
	}

	/*
	 * Input: None
	 * Output: boolean; true if play again
	 * Determines if user wants to play again or end program
	 */
	public boolean playAgain()
	{
		System.out.println("Do you want to play again (Y/N)?");
		Pattern valid = Pattern.compile("[YNyn]{1}");
		String input = myScanner.next();
		Matcher valid_input = valid.matcher(input);
		while(!valid_input.find())
		{
			System.out.println("Invalid Option. Type 'Y' for yes and 'N' for no.");
			input = myScanner.next();
			valid_input = valid.matcher(input);
		}
		if(input.equalsIgnoreCase("n"))
		{
			System.out.println("Goodbye.");
			return false;
		}
		return true;
	}
}

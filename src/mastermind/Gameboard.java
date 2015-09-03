package mastermind;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;

/**
 * Deals with GUI implementation
 * Solves EE422C programming assignment #3
 * @author Sneha Shrotriya
 * @version 2.01 2015-03-06
 */
public class Gameboard {	
	private static final int TURNS = 15;
	private static final int CODESIZE = 5;
	
	private boolean debug;
	private int myCurrentTurns;
	private JTextField inputBox;
	private JButton submitGuess;
	private Game myGame;
	private JPanel panel;
	private JFrame frame;
	private JLabel[][] guesses;
	private JLabel[][] feedbacks;
	
	/**
	 * Creates a new Gameboard and displays GUI components. Note all methods and fields are private so users can only construct a Gameboard object.
	 * This code will take care of the rest of the GUI.
	 * @param d boolean debug value tells Game to print code to console for debugging if true
	 */
	public Gameboard(boolean d) 
	{
		debug = d;
        setup();
    }
	
	
	/**
	 * Sets up data fields. Separate from constructor to facilitate play again option.
	 */
	private void setup()
	{   
		frame = new JFrame();
		panel = new JPanel();
        inputBox = new JTextField(CODESIZE);
        submitGuess = new JButton("Enter");
        myGame = new Game(debug, CODESIZE);
        guesses = new JLabel[TURNS][CODESIZE];
        feedbacks = new JLabel[TURNS][CODESIZE];
        myCurrentTurns = TURNS;


        
        initUI();
	}

	/**
	 * Initializes user interface components.
	 * Postcondition: Inital components have been drawn onto board
	 */
    private final void initUI() {
       
       frame.getContentPane().add(panel);
       panel.setLayout(null);
       panel.setBackground(new Color(204,255,255));
       
       //add instructions button
       JButton instructions = new JButton("Instructions");
       instructions.setBounds(250, 640, 110, 30);
       instructions.addActionListener(new java.awt.event.ActionListener() {
 	      @Override
 	      public void actionPerformed(java.awt.event.ActionEvent evt) {
 	    	 JOptionPane.showMessageDialog(panel,
 					"The computer will think of a secret code. The code consists of "+ CODESIZE + " colored pegs.\n"
 					+ "The pegs MUST be one of seven colors: blue, green, orange, purple, red, maroon, or yellow.\n"
 					+ "A color may appear more than once in the code. You try to guess what colored pegs are in the code and what order they are in.\n"
 					+ "After you make a valid guess the result (feedback) will be displayed.\n"
 					+ "The result consists of a black peg for each peg you have guessed exactly correct (color and position) in your guess.\n"
 					+ "For each peg in the guess that is the correct color, but is out of position, you get a white peg.\n"
 					+ "For each peg, which is fully incorrect, you get no feedback.\n"
 					+ "NOTE: the feedback is not displayed in any particular order in relation to the code.\n"
 					+ "Only the first letter of the color is displayed. B for Blue, R for Red, and so forth.\n"
 					+ "When entering guesses you only need to enter the first character of each color as a capital letter.\n"
 					+ "You have " + TURNS + " guesses to figure out the secret code or you lose the game.");
 	      }
 	   });

       panel.add(instructions);

       //add input mechanism 
       JLabel inputBoxLabel = new JLabel("Enter Guess:");
       inputBoxLabel.setFont(new Font("Serif", Font.PLAIN, 18));
       inputBoxLabel.setBounds(25, 620, 110, 20);
       inputBox.setBounds(25, 641, 90, 30);
       panel.add(inputBox);
       panel.add(inputBoxLabel);
       submitGuess.setBounds(140, 640, 75, 30);
       submitGuess.addActionListener(new java.awt.event.ActionListener() {
 	      @Override
 	      public void actionPerformed(java.awt.event.ActionEvent evt) {
 	    	 processGuess();
 	      }
       });
       panel.add(submitGuess);
       
       //draw board and feedback result items
       Icon emptyGuess = new ImageIcon("empty_guess2.png");
       Icon blankFeedback = new ImageIcon("blankfeedback.png");
       for(int i = 0; i<guesses.length; i++)
       {
    	   for(int j = 0; j<guesses[j].length; j++)
    	   {
    		   guesses[i][j] = new JLabel();
    		   guesses[i][j].setBounds(25+j*37, 65 + i*37,32,32);
    		   guesses[i][j].setIcon(emptyGuess);
    		   panel.add(guesses[i][j]);
    		   
    		   
    		   feedbacks[i][j] = new JLabel();
    		   feedbacks[i][j].setBounds(230+j*25, 70 + i*37,20,20);
    		   feedbacks[i][j].setIcon(blankFeedback);
    		   panel.add(feedbacks[i][j]);
    		   
    		   
    	   }
       }
       
       //add labels
       JLabel yourGuesses = new JLabel("Guesses");
       yourGuesses.setBounds(80,40,200,20);
       yourGuesses.setFont(new Font("Serif", Font.PLAIN, 20));
       panel.add(yourGuesses);
       
       JLabel yourFb = new JLabel("Feedback");
       yourFb.setBounds(255,40,200,20);
       yourFb.setFont(new Font("Serif", Font.PLAIN, 20));
       panel.add(yourFb);
       
       JLabel title = new JLabel("Mastermind");
       title.setBounds(130, 5, 200, 20);
       title.setFont(new Font("Serif", Font.PLAIN, 25));
       panel.add(title);
       
       //show GUI
       frame.setTitle("Mastermind");
       frame.setSize(410, 730);
       frame.setLocationRelativeTo(null);
       frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
       frame.setVisible(true);
    }
    
    /**
     * Processes a user guess and draws appropriate GUI components
     * Postcondition: Feedback and guess icons have been changed to reflect guess
     */
    private void processGuess()
    {
    	try
    	{
    		String input = inputBox.getText();
    		Code guess = myGame.generateCode(input);
    		
    		
    		for(int i = 0; i<guess.size(); i++)
    		{
    			 String filepath = null;
    			 mastermind.Color color = guess.getPeg(i).getColor();
    			 switch(color.getColor())
    			 {
	 				 case RED:  filepath = "red2.png";
	 				 			break;
	 				 case YELLOW: filepath = "yellow2.png";
	 				          	  break;
	 				 case GREEN: filepath = "green2.png";
		          	  			  break;
	 				 case BLUE: filepath = "blue2.png";
	 	  			  			break;
	 				 case ORANGE: filepath = "orange2.png";
	 				 			break;
	 				 case PURPLE: filepath = "purple2.png";
	 				 			break;
	 				 case MAROON: filepath = "maroon2.png";
	 				 default: break;
    			 }
 				
    			 guesses[TURNS-myCurrentTurns][i].setIcon(new ImageIcon(filepath));
    		}
    		
    		FeedbackResult feedback = myGame.generateFeedback(guess);
    		int total = 0;
    		for (int i = 0; i<feedback.getBlack(); i++)
    		{
    			feedbacks[TURNS-myCurrentTurns][total].setIcon(new ImageIcon("black2.png"));
    			total++;
    		}
    		for (int i = 0; i<feedback.getWhite(); i++)
    		{
    			feedbacks[TURNS-myCurrentTurns][total].setIcon(new ImageIcon("white2.png"));
    			total++;
    		}
    		myCurrentTurns--;
    		
    		if(feedback.getBlack() == CODESIZE)
    		{
    			playAgain("You Win!!!");
    		}
    		else if(myCurrentTurns == 0)
    		{
    			playAgain("You Lose :( \nThe secret code was " + myGame.getCodeString()+ ".\n");

    		}
    	}
    	catch (IllegalGuessException ex)
    	{
    		JOptionPane.showMessageDialog(panel,"INVALID GUESS. Valid colors are blue, red, green, yellow, orange, maroon, and purple.\nUse only uppercase first letter for colors.\nGuess must have " + CODESIZE + " pegs.");
    	}
    	catch (DuplicateGuessException ex)
    	{
    		JOptionPane.showMessageDialog(panel,"You have made this guess previously.");
    	}
    	
    }
    
    /**
     * Figures out if user wants to play game again.
     * @param message String for win or lose so that appropriate dialog text is shown
     */
    private void playAgain(String message)
    {
		String n_message =  message + "\nDo you want to play again?";
		int play_again = JOptionPane.showConfirmDialog(panel, n_message, "Game Over", JOptionPane.YES_NO_OPTION);
		if(play_again == JOptionPane.YES_OPTION)
		{
			frame.dispose();
			setup();
		}
		
		else
		{
			frame.dispose();
		}
		
    }
    
}


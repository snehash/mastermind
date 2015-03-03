package assignment3;

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



@SuppressWarnings("serial")
public class Gameboard extends JFrame{
	/**
	 * 
	 */
	private static final int TURNS = 15;
	private static final int CODESIZE = 5;
	private int myCurrentTurns = TURNS;
	private final boolean DEBUG = true;
	private JTextField inputBox;
	private JButton submitGuess;
	private Game myGame;
	private JPanel panel;
	private JLabel[][] guesses;
	private JLabel[][] feedbacks;

	
	
	public Gameboard() 
	{
        setup();
    }
	
	public void setup()
	{
        inputBox = new JTextField(CODESIZE);
        submitGuess = new JButton("Enter");
        myGame = new Game(DEBUG, CODESIZE);
        panel = new JPanel();
        guesses = new JLabel[TURNS][CODESIZE];
        feedbacks = new JLabel[TURNS][CODESIZE];

        
        initUI();
	}

    public final void initUI() {

       getContentPane().add(panel);
       panel.setLayout(null);
       panel.setBackground(new Color(255,255,255));
       JButton instructions = new JButton("Instructions");
       instructions.setBounds(50, 60, 110, 30);
       instructions.addActionListener(new java.awt.event.ActionListener() {
 	      @Override
 	      public void actionPerformed(java.awt.event.ActionEvent evt) {
 	    	 JOptionPane.showMessageDialog(panel, "Welcome to Mastermind.  Here are the rules.\n"
 					+ "The computer will think of a secret code. The code consists of 5 colored pegs.\n"
 					+ "The pegs MUST be one of six colors: blue, green, orange, purple, red, maroon, or yellow.\n"
 					+ "A color may appear more than once in the code. You try to guess what colored pegs are in the code and what order they are in.\n"
 					+ "After you make a valid guess the result (feedback) will be displayed.\n"
 					+ "The result consists of a black peg for each peg you have guessed exactly correct (color and position) in your guess.\n"
 					+ "For each peg in the guess that is the correct color, but is out of position, you get a gray peg.\n"
 					+ "For each peg, which is fully incorrect, you get no feedback.\n"
 					+ "Only the first letter of the color is displayed. B for Blue, R for Red, and so forth.\n"
 					+ "When entering guesses you only need to enter the first character of each color as a capital letter.\n"
 					+ "You have " + TURNS + " guesses to figure out the secret code or you lose the game.");
 	      }
 	   });

       panel.add(instructions);

       
       JLabel inputBoxLabel = new JLabel("Enter Guess");
       inputBoxLabel.setBounds(50, 270, 110, 20);
       inputBox.setBounds(50, 300, 110, 30);
       panel.add(inputBox);
       panel.add(inputBoxLabel);
       submitGuess.setBounds(170, 300, 70, 30);
       submitGuess.addActionListener(new java.awt.event.ActionListener() {
 	      @Override
 	      public void actionPerformed(java.awt.event.ActionEvent evt) {
 	    	 processGuess();
 	      }
       });
       panel.add(submitGuess);
       
       
       
       Icon emptyGuess = new ImageIcon("empty_guess.png");
       for(int i = 0; i<guesses.length; i++)
       {
    	   for(int j = 0; j<guesses[j].length; j++)
    	   {
    		   guesses[i][j] = new JLabel();
    		   guesses[i][j].setBounds(350+j*45, 20 + i*40,37,30);
    		   guesses[i][j].setIcon(emptyGuess);
    		   panel.add(guesses[i][j]);
    		   
    		   feedbacks[i][j] = new JLabel();
    		   feedbacks[i][j].setBounds(650+j*45, 20 + i*40,37,30);
    		   feedbacks[i][j].setIcon(emptyGuess);
    		   panel.add(feedbacks[i][j]);
    		   
    	   }
       }
       
       JLabel yourGuesses = new JLabel("Guesses");
       yourGuesses.setBounds(400,625,200,20);
       yourGuesses.setFont(new Font("Serif", Font.PLAIN, 25));
       panel.add(yourGuesses);
       
       JLabel yourFb = new JLabel("Feedback");
       yourFb.setBounds(700,625,200,20);
       yourFb.setFont(new Font("Serif", Font.PLAIN, 25));
       panel.add(yourFb);
       
       setTitle("Mastermind");
       setSize(1000, 700);
       setLocationRelativeTo(null);
       //setDefaultCloseOperation(EXIT_ON_CLOSE);
       setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    
    public void processGuess()
    {
    	try
    	{
    		String input = inputBox.getText();
    		Code guess = myGame.generateCode(input);
    		
    		
    		for(int i = 0; i<guess.size(); i++)
    		{
    			 String filepath = null;
    			 assignment3.Color color = guess.getPeg(i).getColor();
    			 switch(color)
    			 {
	 				 case RED:  filepath = "red.png";
	 				 			break;
	 				 case YELLOW: filepath = "yello.png";
	 				          	  break;
	 				 case GREEN: filepath = "green.png";
		          	  			  break;
	 				 case BLUE: filepath = "blue.png";
	 	  			  			break;
	 				 case ORANGE: filepath = "orange.png";
	 				 			break;
	 				 case PURPLE: filepath = "purple.png";
	 				 			break;
	 				 case MAROON: filepath = "maroon.png";
	 				 default: break;
    			 }
 				
    			 guesses[TURNS-myCurrentTurns][i].setIcon(new ImageIcon(filepath));
    		}
    		
    		FeedbackResult feedback = myGame.generateFeedback(guess);
    		int total = 0;
    		for (int i = 0; i<feedback.getBlack(); i++)
    		{
    			feedbacks[TURNS-myCurrentTurns][total].setIcon(new ImageIcon("black.png"));
    			total++;
    		}
    		for (int i = 0; i<feedback.getWhite(); i++)
    		{
    			feedbacks[TURNS-myCurrentTurns][total].setIcon(new ImageIcon("grey.png"));
    			total++;
    		}
    		myCurrentTurns--;
    		
    		if(feedback.getBlack() == CODESIZE)
    		{
    			JOptionPane.showMessageDialog(panel,"You Win!!!");
    			setup();
    		}
    		else if(myCurrentTurns == 0)
    		{
    			JOptionPane.showMessageDialog(panel,"You Lose :(");
    			setup();
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
    

    public static void main(String[] args) {
           Gameboard Mastermind = new Gameboard();
           Mastermind.setVisible(true);
    }
}


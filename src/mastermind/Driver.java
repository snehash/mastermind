package mastermind;

/**
Classes to play mastermind.
Solves EE422C programming assignment #3
@author Sneha Shrotriya
@version 2.01 2015-03-06
*/


public class Driver {

	//changing this determines whether code will be printed to console or not
	private static final boolean DEBUG = false;
	
	/**
	 * Main method to launch GUI 
	 * @param args
	 */
    @SuppressWarnings("unused")
	public static void main(String[] args) {
           Gameboard Mastermind = new Gameboard(DEBUG);
    }

}

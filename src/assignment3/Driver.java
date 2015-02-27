package assignment3;

/*
 * 
 */

public class Driver {

	public static void main(String[] args) 
	{
		boolean debug = false;
		boolean play= true; 
		while(play)
		{
			
			Game g = new Game(debug);
			play= g.gameRunner();
		
		}
	}

}

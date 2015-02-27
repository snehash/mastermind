package assignment3;

/*
 * 
 */

public class Driver {

	public static void main(String[] args) 
	{
		boolean play= true; 
		while(play)
		{
		
			Game g = new Game(false);
			play= g.gameRunner();
		
		}
	}

}

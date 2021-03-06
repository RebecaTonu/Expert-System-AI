import javax.swing.JButton;

import jess.JessException;


public class NoButton extends JButton implements Command{
	
	public NoButton()
	{
		super("Nu");
	}
	
	public void Execute() 
	{
		CreateFacts.rasp=0;
	}

}

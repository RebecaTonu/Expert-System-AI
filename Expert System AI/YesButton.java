import javax.swing.JButton;

import jess.JessException;


public class YesButton extends JButton implements Command{
	
	public YesButton()
	{
		super("Da");
	}
	public void Execute() 
	{
		CreateFacts.rasp=1;
	}

}

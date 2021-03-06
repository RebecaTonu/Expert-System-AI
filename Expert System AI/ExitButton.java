import javax.swing.JButton;


public class ExitButton extends JButton implements Command{
	
	public ExitButton()
	{
		super("Exit");
	}
	public void Execute() 
	{
		System.exit(0);
	}

}

import java.awt.Button;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import jess.JessException;
import java.awt.Font;


public class Interfata extends JFrame implements ActionListener
{
	public static JTextArea intrebare;
	public static YesButton da;
	public static NoButton nu;
	public ExitButton exit;
	public static CreateFacts c;
	
	public Interfata()
	{
		setFont(new Font("Dialog", Font.PLAIN, 20));
		getContentPane().setLayout(new GridLayout(2,4));
		
		intrebare=new JTextArea();
		intrebare.setLineWrap(true);
		intrebare.setEditable(false);
		intrebare.setFont(new Font("Monospaced", Font.PLAIN, 20 ));
		intrebare.setAlignmentX(CENTER_ALIGNMENT);
		intrebare.setAlignmentY(CENTER_ALIGNMENT);
		
		da=new YesButton();
		da.setBounds(67, 59, 145, 57);
		da.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		nu=new NoButton();
		nu.setBounds(222, 59, 145, 57);
		nu.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		exit=new ExitButton();
		exit.setBounds(377, 59, 145, 57);
		exit.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		
		JPanel frame=new JPanel();
		getContentPane().add(intrebare);
		frame.setLayout(null);
		frame.add(da);
		frame.add(nu);
		frame.add(exit);
		getContentPane().add(frame);
		
		da.addActionListener(this);
		nu.addActionListener(this);
		exit.addActionListener(this);
		intrebare.setText("Dormiti minim 6 sau 7 ore pe noapte?");
		setSize(new Dimension(600,400));
		setVisible(true);
		
		try 
		{
			c=new CreateFacts();
		} catch (JessException e) 
		{
			e.printStackTrace();
		}
		
	}
	public static void setComponente(String intrebareString, boolean isButtonVisible)
	{
		intrebare.setText(intrebareString);
		if (!isButtonVisible)
		{
			da.setVisible(false);
			nu.setVisible(false);
		}
	}
	public void actionPerformed(ActionEvent evt) {
		String button;
		button=evt.getActionCommand();
		if (button.equalsIgnoreCase("da"))
		{
			da.Execute();
			try 
			{
				c.createFact();
			} catch (JessException e) 
			{
				e.printStackTrace();
			}
		}
		else
			if (button.equalsIgnoreCase("nu"))
			{
				nu.Execute();
				try 
				{
					c.createFact();
				} catch (JessException e) 
				{
					e.printStackTrace();
				}
			}
			else
				if (button.equalsIgnoreCase("exit"))
					exit.Execute();
	}
}

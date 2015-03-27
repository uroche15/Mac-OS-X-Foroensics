package Programs;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class Plist extends JFrame{

	private static final long serialVersionUID = 1L;
	
	public static void main (String [] args)
	{
		new Plist().setVisible(true);
	}
	
	public Plist()
	{
		super("Plist Reader");
		setSize(600,450);
		setResizable(true);
		
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
	}

}

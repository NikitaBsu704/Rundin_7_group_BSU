package bsu.rfe.java.group7.lab7.Ryndin.var1;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Start 
{

	public static void main(String[] args) 
	{
		SwingUtilities.invokeLater(new Runnable() 
		{
			@Override
			public void run() 
			{
				InitFrame frame = new InitFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});

     }
}

package bsu.rfe.java.group7.lab7.Ryndin.var1;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Start 
{

	public static void main(String[] args) 
	{
		int PORT_A,PORT_B,PORT_C,PORT_user;
		PORT_A = 4566;
		PORT_user = 4567;
		PORT_B = 4568;
		PORT_C = 4569;
		SwingUtilities.invokeLater(new Runnable() 
		{
			@Override
			public void run() 
			{
				
				JoinFrame frame1 = new JoinFrame();
				frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame1.setVisible(true);
				MainFrame frame2 = new MainFrame();
				frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame2.setVisible(true);
				JoinServer Server1 = new JoinServer(PORT_A);
				ServerUser Server2 = new ServerUser(PORT_B,"127.0.0.1",PORT_A);
				ServerUser Server3 = new ServerUser(PORT_C,"127.0.0.1",PORT_A);
			}
		});

     }
}

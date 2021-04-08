package bsu.rfe.java.group7.lab7.Ryndin.var1;



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
@SuppressWarnings("serial")
////����� ��� ����������� �� ������
public class JoinFrame extends JFrame 
{
	private static final int Defult_SERVER_PORT = 4568;
	private static final int WIDTH = 500;
	private static final int HEIGHT = 400;
	
	
	Box MainBox = Box.createVerticalBox();
	private JTextField AdressField;
	private JTextField PortField;
	private JTextField ChatChannelName;
	private JTextField UserName;
	private JTextField UserSurname;
	private JTextField KeyWord;
	private boolean    Answered;
	private boolean    IsConnected;
	
	
	
	
	
	
	
	
	
	public JoinFrame() 
	{
		super("������ � �������");
		setSize(WIDTH, HEIGHT);
		Toolkit kit = Toolkit.getDefaultToolkit();
		setLocation((kit.getScreenSize().width - WIDTH)/2,
		(kit.getScreenSize().height - HEIGHT) / 2);
		
		AdressField = new JTextField("0", 10);
		AdressField.setMaximumSize(AdressField.getPreferredSize());
		PortField = new JTextField("0", 10);
		PortField.setMaximumSize(PortField.getPreferredSize());
		ChatChannelName = new JTextField("0", 10);
		ChatChannelName.setMaximumSize(AdressField.getPreferredSize());
		
		
		
		UserName = new JTextField("0", 10);
		UserName.setMaximumSize(UserName.getPreferredSize());
		UserSurname = new JTextField("0", 10);
		UserSurname.setMaximumSize(UserSurname.getPreferredSize());
		KeyWord = new JTextField("0", 10);
		KeyWord.setMaximumSize(KeyWord.getPreferredSize());
		
		UserName.setText("0");
		UserSurname.setText("0");
		KeyWord.setText("0");
		Answered = false;
		IsConnected = false;
		
		
		/*
		Icon icon = new ImageIcon("D:\\@images_for_eclipse\triangle_001.png");
		JButton Button_Request = new JButton(icon);
		Button_Request.setIcon        (new ImageIcon("D:\\@images_for_eclipse/triangle_001.png"));
		Button_Request.setRolloverIcon(new ImageIcon("D:\\@images_for_eclipse/triangle_002.png"));
		Button_Request.setPressedIcon (new ImageIcon("D:\\@images_for_eclipse/triangle_002.png"));
		Button_Request.setDisabledIcon(new ImageIcon("D:\\@images_for_eclipse/triangle_001.png"));
		
		Button_Request.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent ev) 
			{
				try 
				{
				     String destinationAddress = AdressField.getText();
					
				} 
				catch (NumberFormatException ex) 
				{
					JOptionPane.showMessageDialog(InitFrame.this,"������ � ������� ������ ������ �������", "��������� ������ �����",JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		*/
		JButton Button_send = new JButton("��������� ������");
		Button_send.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent ev) 
			{
				
				try 
				{
				     String destinationAddress = AdressField.getText();
				     int Port;//������ ���� ��������
				     try
				     {
				         // ������ ����� String ������������� � int
				    	 Port = Integer.parseInt(PortField.getText().trim());
				     }
				     catch (NumberFormatException nfe)
				     {
				       Port = Defult_SERVER_PORT;
				       System.out.println("NumberFormatException: " + nfe.getMessage());
				     }
				     
				     String Name = UserName.getText();
				     String Surname = UserSurname.getText();
				     String Key = KeyWord.getText();
				     String ChatName = ChatChannelName.getText();
				   
				     
				     if (destinationAddress.isEmpty()) 
						{
							JOptionPane.showMessageDialog(JoinFrame.this, "����� ������� �� ������", "������",JOptionPane.ERROR_MESSAGE);
							return;
						}
						if (Name.isEmpty()) 
						{
							JOptionPane.showMessageDialog(JoinFrame.this,"������� ���� ���", "������",JOptionPane.ERROR_MESSAGE);
							return;
						}
						if (Surname.isEmpty()) 
						{
							JOptionPane.showMessageDialog(JoinFrame.this,"������� ���� �������", "������",JOptionPane.ERROR_MESSAGE);
							return;
						}
						if (Key.isEmpty()) 
						{
							JOptionPane.showMessageDialog(JoinFrame.this,"������� ���� �������", "������",JOptionPane.ERROR_MESSAGE);
							return;
						}
						
						///��� ������, ������ � �������
						try 
						{
							final Socket socket = new Socket(destinationAddress, Port);
							// ��������� ����� ������ ������
							final DataOutputStream out = new DataOutputStream(socket.getOutputStream());
							out.writeUTF(Name);
							out.writeUTF(Surname);
							out.writeUTF(Key);
							out.writeUTF(ChatName);
							// ��������� �����
							socket.close();
						}
						catch (UnknownHostException e) 
						{
							e.printStackTrace();
							JOptionPane.showMessageDialog(JoinFrame.this,"�� ������� ��������� ���������: ����-������� �� ������","������", JOptionPane.ERROR_MESSAGE);
						} 
						catch (IOException e) 
						{
							e.printStackTrace();
							JOptionPane.showMessageDialog(JoinFrame.this,"�� ������� ��������� ���������", "������",JOptionPane.ERROR_MESSAGE);
						}
				     
				     
				} 
				catch (NumberFormatException ex) 
				{
					JOptionPane.showMessageDialog(JoinFrame.this,"������ �����������", "��������� ������ �����",JOptionPane.WARNING_MESSAGE);
				}
				
		    }
		});
		
		
		
		Box AcceptBox = Box.createHorizontalBox();
		AcceptBox.add(Box.createHorizontalGlue());
		AcceptBox.add(Button_send);
		AcceptBox.add(Box.createHorizontalGlue());
		AcceptBox.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		
	    
		///������ ��� ����������� ����� �����
	    Box AdressBox = Box.createHorizontalBox();
	    AdressBox.add(Box.createHorizontalGlue());
	    JLabel LabelForAdress1 = new JLabel("����� ������� �����:");
	    AdressBox.add(LabelForAdress1);
	    AdressBox.add(Box.createHorizontalStrut(10));
	    AdressBox.add(AdressField);
	    AdressBox.add(Box.createHorizontalGlue());
	    
	    
	    JLabel LabelForPort = new JLabel("����� ��� �����:");
	    AdressBox.add(Box.createHorizontalStrut(10));
	    AdressBox.add(LabelForPort);
	    AdressBox.add(Box.createHorizontalStrut(10));
	    AdressBox.add(PortField);
	    AdressBox.add(Box.createHorizontalGlue());
	    AdressBox.setBorder(BorderFactory.createLineBorder(Color.GREEN));
		
	    ///������ ��� ����������� ����� ���� ������������
	    Box ChatNameBox = Box.createHorizontalBox();
	    ChatNameBox.add(Box.createHorizontalGlue());
	    JLabel LabelForName = new JLabel("��� ������:");
	    ChatNameBox.add(LabelForName);
	    ChatNameBox.add(Box.createHorizontalStrut(10));
	    ChatNameBox.add(ChatChannelName);
	    ChatNameBox.add(Box.createHorizontalGlue());
	    
		
		
		Box AuthorizationBox = Box.createHorizontalBox();
		AuthorizationBox.add(Box.createHorizontalGlue());
		JLabel label_N = new JLabel("���");
		AuthorizationBox.add(label_N);
		AuthorizationBox.add(Box.createHorizontalStrut(10));
		AuthorizationBox.add(UserName);
		AuthorizationBox.add(Box.createHorizontalGlue());
		 
		 JLabel label_SN = new JLabel("�������");
		 AuthorizationBox.add(label_SN);
		 AuthorizationBox.add(Box.createHorizontalStrut(10));
		 AuthorizationBox.add(UserSurname);
		 AuthorizationBox.add(Box.createHorizontalGlue());
		 
		 JLabel label_Key = new JLabel("������");
		 AuthorizationBox.add(label_Key);
		 AuthorizationBox.add(Box.createHorizontalStrut(10));
		 AuthorizationBox.add(KeyWord);
		 AuthorizationBox.add(Box.createHorizontalGlue());
		 AuthorizationBox.setBorder(BorderFactory.createLineBorder(Color.RED));
		 

		
		MainBox.add(Box.createVerticalGlue());
		MainBox.add(AcceptBox);
		MainBox.add(Box.createVerticalStrut(10));
		MainBox.add(AdressBox);
		MainBox.add(ChatNameBox);
		MainBox.add(Box.createVerticalStrut(10));
		MainBox.add(AuthorizationBox);
		MainBox.add(Box.createVerticalGlue());
		MainBox.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		getContentPane().add(MainBox, BorderLayout.CENTER);
			
	}
	
	

}
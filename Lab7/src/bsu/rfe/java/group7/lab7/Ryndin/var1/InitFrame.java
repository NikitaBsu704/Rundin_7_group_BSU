package bsu.rfe.java.group7.lab7.Ryndin.var1;


//������������� ������, ������������ � ����������
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
//������� ����� ����������, �� �� ����� ������
public class InitFrame extends JFrame 
{
	
	private static final int WIDTH = 500;
	private static final int HEIGHT = 400;
	
	
	Box MainBox = Box.createVerticalBox();
	private JTextField AdressField;
	private JTextField UserName;
	private JTextField UserSurname;
	private JTextField KeyWord;
	private boolean    Answered;
	private boolean    IsConnected;
	
	
	
	
	
	
	
	
	
	public InitFrame() 
	{
		super("������ � �������");
		setSize(WIDTH, HEIGHT);
		Toolkit kit = Toolkit.getDefaultToolkit();
		setLocation((kit.getScreenSize().width - WIDTH)/2,
		(kit.getScreenSize().height - HEIGHT) / 2);
		
		AdressField = new JTextField("0", 10);
		AdressField.setMaximumSize(AdressField.getPreferredSize());
		UserName = new JTextField("0", 10);
		UserName.setMaximumSize(AdressField.getPreferredSize());
		UserSurname = new JTextField("0", 10);
		UserSurname.setMaximumSize(AdressField.getPreferredSize());
		KeyWord = new JTextField("0", 10);
		KeyWord.setMaximumSize(AdressField.getPreferredSize());
		
		UserName.setText("0");
		UserSurname.setText("0");
		KeyWord.setText("0");
		Answered = false;
		IsConnected = false;
		
		
		
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
		
		JButton Button_send = new JButton("��������� ������");
		Button_send.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent ev) 
			{
				
				try 
				{
				     String destinationAddress = AdressField.getText();
				     String Name = UserName.getText();
				     String Surname = UserSurname.getText();
				     String Key = KeyWord.getText();
				} 
				catch (NumberFormatException ex) 
				{
					JOptionPane.showMessageDialog(InitFrame.this,"������ � ������� ������ ������ �������", "��������� ������ �����",JOptionPane.WARNING_MESSAGE);
				}
				
		    }
		});
		
		
		
		Box AcceptBox = Box.createHorizontalBox();
		AcceptBox.add(Box.createHorizontalGlue());
		AcceptBox.add(Button_send);
		AcceptBox.add(Box.createHorizontalGlue());
		AcceptBox.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		
	
		
	    Box AdressBox = Box.createHorizontalBox();
	    AdressBox.add(Box.createHorizontalGlue());
	    JLabel LabelForAdress = new JLabel("����� �������");
	    AdressBox.add(LabelForAdress);
	    AdressBox.add(Box.createHorizontalStrut(10));
	    AdressBox.add(AdressField);
	    AdressBox.add(Box.createHorizontalGlue());
	    AdressBox.setBorder(BorderFactory.createLineBorder(Color.GREEN));
		
		
		
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
		MainBox.add(Box.createVerticalStrut(10));
		MainBox.add(AuthorizationBox);
		MainBox.add(Box.createVerticalGlue());
		MainBox.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		getContentPane().add(MainBox, BorderLayout.CENTER);
			
	}
	
	

}
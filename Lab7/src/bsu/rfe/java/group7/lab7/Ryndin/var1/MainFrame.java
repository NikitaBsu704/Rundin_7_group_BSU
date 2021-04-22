package bsu.rfe.java.group7.lab7.Ryndin.var1;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
@SuppressWarnings("serial")
public class MainFrame extends JFrame 
{
	private static final String FRAME_TITLE = "������ ���������� ���������";
	private static final int FRAME_MINIMUM_WIDTH = 500;
	private static final int FRAME_MINIMUM_HEIGHT = 500;
	private static final int FROM_FIELD_DEFAULT_COLUMNS = 10;
	private static final int TO_FIELD_DEFAULT_COLUMNS = 20;
	private static final int INCOMING_AREA_DEFAULT_ROWS = 10;
	private static final int OUTGOING_AREA_DEFAULT_ROWS = 5;
	private static final int SMALL_GAP = 5;
	private static final int MEDIUM_GAP = 10;
	private static final int LARGE_GAP = 15;
	private final InstantMessenger MessageClient;
	private JTextField textFieldIP;
	private JTextField textFieldPort;
	private JTextArea textAreaIncoming;
	private JTextArea textAreaOutgoing;
	JoinFrame InviteFrame; ///���� �������
	public MainFrame() 
	{
		
		
		super(FRAME_TITLE);
		setMinimumSize(new Dimension(FRAME_MINIMUM_WIDTH, FRAME_MINIMUM_HEIGHT));
		// ������������� ����
		final Toolkit kit = Toolkit.getDefaultToolkit();
		setLocation((kit.getScreenSize().width - getWidth()) / 2,(kit.getScreenSize().height - getHeight()) / 2);
		// ��������� ������� ��� ����������� ���������� ���������
		textAreaIncoming = new JTextArea(INCOMING_AREA_DEFAULT_ROWS, 0);
		textAreaIncoming.setEditable(false);
		// ���������, �������������� ��������� ��������� �������
		final JScrollPane scrollPaneIncoming = new JScrollPane(textAreaIncoming);
		// ������� �����
		final JLabel labelIP = new JLabel("�����");
		final JLabel labelPort = new JLabel("����");
		
		textFieldIP = new JTextField(FROM_FIELD_DEFAULT_COLUMNS);
		textFieldPort = new JTextField(TO_FIELD_DEFAULT_COLUMNS);
		// ��������� ������� ��� ����� ���������
		textAreaOutgoing = new JTextArea(OUTGOING_AREA_DEFAULT_ROWS, 0);
		// ���������, �������������� ��������� ��������� �������
		final JScrollPane scrollPaneOutgoing = new JScrollPane(textAreaOutgoing);
		// ������ ����� ���������
		final JPanel messagePanel = new JPanel();
		messagePanel.setBorder(BorderFactory.createTitledBorder("���������"));
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		MessageClient = new InstantMessenger(this);
		InstantMessenger.addMessageListener(new MessageListener()
		{
			public void messageReceived(Peer sender, String message) 
			{
				textAreaIncoming.append("IP: " + sender.GetIP() + " \n (" + sender.GetName1() + " | " + sender.GetName2() + " | " + sender.GetName3() + ") \n" + message + "\n");
			}
		});
		MessageClient.start();
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// ������ �������� ���������
		final JButton sendButton = new JButton("���������");
		sendButton.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				final String destinationAddress = textFieldIP.getText();
				final String MyAddress = "localhost";
				final int Port = Integer.parseInt(textFieldPort.getText().trim());
				final String message = textAreaOutgoing.getText();
				MessageClient.sendMessage(destinationAddress,Port,message);
				textAreaOutgoing.setText("");
			}
		});
		
		///����� ����
		
		final JButton ConnectButton = new JButton("���������� �����");
		ConnectButton.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				if(InviteFrame != null){InviteFrame.setVisible(false);}
				InviteFrame = new JoinFrame();
				InviteFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				InviteFrame.setVisible(true);
			}
		});
		
		// ���������� ��������� ������ "���������"
		final GroupLayout layout2 = new GroupLayout(messagePanel);
		messagePanel.setLayout(layout2);
		
		
		layout2.setHorizontalGroup(layout2.createSequentialGroup()
		.addContainerGap()
		.addGroup(layout2.createParallelGroup(Alignment.TRAILING)
		.addGroup(layout2.createSequentialGroup()
		.addComponent(labelIP)
		.addGap(SMALL_GAP)
		.addComponent(textFieldIP)
		.addGap(LARGE_GAP)
		.addComponent(labelPort)
		.addGap(SMALL_GAP)
		.addComponent(textFieldPort))
		.addComponent(scrollPaneOutgoing)
		.addComponent(sendButton).addComponent(ConnectButton))
		.addContainerGap());
		
		
		
		
		
		
		
		layout2.setVerticalGroup(layout2.createSequentialGroup().addContainerGap()
		.addGroup(layout2.createParallelGroup(Alignment.BASELINE)
		.addComponent(labelIP)
		.addComponent(textFieldIP)
		.addComponent(labelPort)
		.addComponent(textFieldPort))
		.addGap(MEDIUM_GAP)
		.addComponent(scrollPaneOutgoing)
		.addGap(MEDIUM_GAP)
		.addComponent(sendButton).addComponent(ConnectButton)
		.addContainerGap());
		// ���������� ��������� ������
		final GroupLayout layout1 = new GroupLayout(getContentPane());
		setLayout(layout1);
		
		
		layout1.setHorizontalGroup(layout1.createSequentialGroup()
		.addContainerGap()
		.addGroup(layout1.createParallelGroup()
		.addComponent(scrollPaneIncoming)
		.addComponent(messagePanel))
		.addContainerGap());
		
		
		layout1.setVerticalGroup(layout1.createSequentialGroup()
		.addContainerGap()
		.addComponent(scrollPaneIncoming)
		.addGap(MEDIUM_GAP)
		.addComponent(messagePanel)
		.addContainerGap());
	}
}

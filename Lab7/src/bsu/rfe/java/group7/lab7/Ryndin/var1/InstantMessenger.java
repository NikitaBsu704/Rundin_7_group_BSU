package bsu.rfe.java.group7.lab7.Ryndin.var1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JOptionPane;

public class InstantMessenger extends Thread 
{
	//final MainFrame UserFrame;///���� ����� � MainFrame (������ �� �����)
	
	//string message ����� �������� ��������� ���� - � ����� �� MainFrame �������� ������ �������� MainFrame � ������, ����� �����������
	private static final int User_Port = 4567;
	public static ArrayList<MessageListener> listeners = new ArrayList<MessageListener>();;
	  
	

	public InstantMessenger(MainFrame SomeFrame) 
	{
		//UserFrame = SomeFrame;
		// TODO Auto-generated constructor stub
	}
	///������� Run � �������� �� ��������� �������
	///���������� ��������� ����� listener ��� ����� ������ mainframe �������������� �������� MainFrame ����� instant messenger
	public void run() 
	{
		try 
		{
			final ServerSocket serverSocket = new ServerSocket(User_Port);
			while (!Thread.interrupted()) 
			{
				final Socket socket = serverSocket.accept();
				final DataInputStream in = new DataInputStream(socket.getInputStream());
				// ��������� ������ �����������
				final String address = ((InetSocketAddress) socket.getRemoteSocketAddress()).getAddress().getHostAddress();
				final String senderName = in.readUTF();
				final String senderSurName = in.readUTF();
				final String message = in.readUTF();
				// ��������� ����������
				socket.close();
				Peer sender = new Peer(address,senderName,senderSurName);
				notifyListeners(sender, message); 
				
				
				
				// ������� ��������� � ��������� �������
				
			}
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
			//JOptionPane.showMessageDialog(UserFrame,"������ � ������ �������", "������",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void sendMessage(final String destinationAddress,final int Port,String message) 
	{
		try 
		{
			// ����������� ��������� ��������
			
			// ����������, ��� ���� �� ������
			
			if (destinationAddress.isEmpty()) 
			{
				//JOptionPane.showMessageDialog(UserFrame,"������� ����� ����-����������", "������",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (message.isEmpty()) 
			{
				//JOptionPane.showMessageDialog(UserFrame,"������� ����� ���������", "������",JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			// ������� ����� ��� ����������
			//final Socket socket = new Socket(destinationAddress, Port,MyAddress,User_Port);
			final Socket socket = new Socket(destinationAddress, Port);
			// ��������� ����� ������ ������
			final DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			// ���������� � ����� ���
			//out.writeUTF(senderName);
			// ���������� � ����� ���������
			out.writeUTF(message);
			// ��������� �����
			socket.close();
			
			
			
		} 
		catch (UnknownHostException e) 
		{
			e.printStackTrace();
			//JOptionPane.showMessageDialog(UserFrame,"�� ������� ��������� ���������: ����-������� �� ������","������", JOptionPane.ERROR_MESSAGE);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
			//JOptionPane.showMessageDialog(UserFrame,"�� ������� ��������� ���������", "������",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public static void addMessageListener(MessageListener listener) 
	{
		synchronized (listeners)  
		{
		    listeners.add(listener);
		}
	}
	public static void removeMessageListener(MessageListener listener) 
	{
		synchronized (listeners) 
		{
		   listeners.remove(listener);
		}
	}
	
	private void notifyListeners(Peer sender, String message) 
	{
		synchronized (listeners) 
		{
			for (MessageListener listener : listeners) 
			{
			    listener.messageReceived(sender, message);
			}
		}
	}
	
}

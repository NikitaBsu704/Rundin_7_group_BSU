package bsu.rfe.java.group7.lab7.Ryndin.var1;


import java.awt.Color;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JOptionPane;


public class JoinServer implements Runnable 
{
	// ������������ ������, ������� ����� ����� ���
	
	private int InitServer_Port;//���� ������� �������
	private int User_Port = 4567;//���� ������������ ����� �� ���������
	private String NameCreator;
	private String SurnameCreator;
	private String KeyCreator;
	
	
	
	private int HowMany1;
	private String[] NameUsers;
	private String[] SurnameUsers;
	private String[] KeyUsers;
	
	
	///���� ����, �� ������� ��������������
	private int HowMany2;
	private String[] ChatNames;
	private String[] ChatIp;
	private int[] ChatPorts;
	
	
	
	

	// ����������� ������ 
	public JoinServer(int PORT_A)
	{
		InitServer_Port = PORT_A;
		//Another_Server_Port = 4489;
		//NameCreator = new String(Name);
		//SurnameCreator = new String(Surname);
		//KeyCreator = new String(Key);
		
		
		CreateDataBase();
		
		
		
		Thread thisThread = new Thread(this);
		// ��������� �����
		thisThread.start();
	}
	// ����� run() ����������� ������ ������. ����� �� ��������� ������, 
	// �� ����������� � �����
	public void run() 
	{
		try 
		{
			// ������ ����������� ����, �.�. ���� ��� �� �������, 
			// �� �� �������� �����������
			while(true) 
			{
				final ServerSocket AuthorisationSocket = new ServerSocket(InitServer_Port);
				try 
				{
					
					while (!Thread.interrupted()) 
					{
						Socket UserSocket = AuthorisationSocket.accept();
						try {
								///��������� ���������� ������
							    //Socket UserSocket = AuthorisationSocket.accept();
								final DataInputStream in = new DataInputStream(UserSocket.getInputStream());
								// ������ �����������
								String address = ((InetSocketAddress) UserSocket.getRemoteSocketAddress()).getAddress().getHostAddress();
								int Port = 1111;
								String SenderName = in.readUTF();
								String SenderSurname = in.readUTF();
								String SenderKey = in.readUTF();
								String ChatName = in.readUTF();
								///��������
								if (address.isEmpty()) {return;}
								if (SenderName.isEmpty()) {return;}
							    if (SenderSurname.isEmpty()) {return;}
								if (SenderKey.isEmpty()) {return;}
								if (address.isEmpty()) {return;}
								
								
								
							    ///��� ������, ������ ������� ��������
								boolean Equal = false;
								///Equal = SenderName.equals(NameCreator) && SenderSurname.equals(SurnameCreator) && SenderKey.equals(KeyCreator);
								/*
								////����� �����
								if(Equal)
								{
								}
								*/
								///����� �� ����� - ��������� ������
								int i = 0;
								Equal = SenderName.equals(NameUsers[i]) && SenderSurname.equals(SurnameUsers[i]) && SenderKey.equals(KeyUsers[i]);
								while(i < (HowMany1-1) && (! Equal))
								{
									i = i + 1;
									Equal = SenderName.equals(NameUsers[i]) && SenderSurname.equals(SurnameUsers[i]) && SenderKey.equals(KeyUsers[i]);
								}
								/// ������������ ����������� - ������������� ��� �� ���� ����
								if(Equal)
								{
									////�� �������� � ���������� ���� ���������� ��� - ����� �������� ��� �� ���������
									i = 0;
									Equal = ChatNames[i].equals(ChatName);
									while(!Equal && i < (HowMany2-1))
									{
										i = i + 1;
										Equal = ChatNames[i].equals(ChatName);
									}
									/// ��� ������ - ��� ������ �����
									if(Equal) 
									{
										try 
										{
										///��� ������ �������� ������ �� ���������� ������������ ���-�������
										final Socket SocketChatServer = new Socket(ChatIp[i], ChatPorts[i]);
										final DataOutputStream out = new DataOutputStream(SocketChatServer.getOutputStream());
										out.writeUTF(address);
										out.writeUTF(SenderName);
										out.writeUTF(SenderSurname);
										/*
										///���� ������
										final DataInputStream in = new DataInputStream(SocketChatServer.getInputStream());
										String TechnicalMessage = in.readUTF();
										
										///��� ������� ������?
										//������� 1:��� ������
										if (TechnicalMessage.equals("True"))
										{
											
										}
										*/
										SocketChatServer.close();
										}
										catch (IOException ex) 
										{
											// ���� ��� ��������, �� ���-�� ����� ������...
											// � ������ ������� (�����������)
										}
										
										
										
									}
									/// ������������� ������������ �� ��������� ��� �� ���������
									else 
									{
										
									}
									
									
								}
								/// �������� ���������� - ���������������
								else 
								{
									////just nothing ignore user....
								}
							
						     }
						     finally
						     {
							       UserSocket.close();
						     }			
					}
				} 
				finally
				{
					AuthorisationSocket.close();
				}	
				///����� ������
				Thread.sleep(100);
			}
		} 
		catch (InterruptedException | IOException ex) 
		{
			// ���� ��� ��������, �� ������ �� ������ 
			// � ������ ������� (�����������)
		}
		
	}
	void CreateDataBase()
	{
		
		HowMany1 = 10;
		NameUsers = new String[HowMany1];
		SurnameUsers = new String[HowMany1];
		KeyUsers = new String[HowMany1];
		
		for(int i = 0;i < HowMany1;i++)
		{
			NameUsers[i] = new String ("1");
			SurnameUsers[i] = new String ("2");
			KeyUsers[i] = new String ("3");
		}
		
		NameUsers[0] = new String ("Nikita");
		SurnameUsers[0] = new String ("Rundin");
		KeyUsers[0] = new String ("31428");
		
		NameUsers[1] = new String ("A");
		SurnameUsers[1] = new String ("B");
		KeyUsers[1] = new String ("1111");
		
		NameUsers[2] = new String ("Shtukatur");
		SurnameUsers[2] = new String ("Lutkowski");
		KeyUsers[2] = new String ("2222");
		
		
		
		
		HowMany2 = 2;
		ChatNames = new String[HowMany2];
		ChatIp = new String[HowMany2];
		ChatPorts = new int[HowMany2];
		
		///������ ���� ��������� ��� �� ������ ���������
		ChatNames[0] = new String("Default");
		ChatIp[0] = new String("127.0.0.1");
		ChatPorts[0] = 4568;
		///�������� �������
		ChatNames[1] = new String("Main");
		ChatIp[1] = new String("127.0.0.1");
		ChatPorts[1] = 4569;
		
	}
	
	
	
	// ����� ���������� ������ ����
	
}

















package bsu.rfe.java.group7.lab7.Ryndin.var1;

import java.awt.Color;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;
//InetSocketAddress

public class ServerUser implements Runnable 
{
	
	private String InitServer_IpAdress;//IP ����� �������-�����
	private int InitServer_Port;//���� �������-�����
	private int User_Server_Port;//���� ������� �������
	private int User_Port = 4567;//���� ������������ ����� �� ���������
	
	///�������������� ������������
	private int HowMany;
	private String[] IpAdress;
	private String[] NameUsers;
	private String[] SurnameUsers;
	

	// ����������� ������ BouncingBall
	public ServerUser(int Port_A,String IPServer,int Port_B) 
	{
		User_Server_Port = Port_A;
		InitServer_IpAdress = IPServer;
		InitServer_Port = Port_B;
		HowMany = 1;///�������� �������
		IpAdress = new String[1];
		NameUsers= new String[1];
		SurnameUsers= new String[1];
		IpAdress[0] = new String("127.0.0.1");
		NameUsers[0] = new String("ABC");
		SurnameUsers[0] = new String("CBA");
		
		
		
		
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
				final ServerSocket ServerSocket = new ServerSocket(User_Server_Port);
				try 
				{
					
					while (!Thread.interrupted()) 
					{
						
						
						Socket UserSocket = ServerSocket.accept();
						int i = 0;
						boolean Equal = false;
						try {
							    System.out.println("Server has got some new access...");
								///��������� ���������� ������ ����� IP ��������� � ����
								String address = ((InetSocketAddress) UserSocket.getRemoteSocketAddress()).getAddress().getHostAddress();
								//int Port = UserSocket.getLocalPort();
								System.out.println("IP");
								System.out.println(address);
								//System.out.println("Port");
								//System.out.println(Port);
								
								System.out.println("getRemoteSocketAddress()");
								System.out.println(UserSocket.getRemoteSocketAddress());
								System.out.println(".getRemoteSocketAddress()).getAddress()");
								System.out.println(((InetSocketAddress) UserSocket.getRemoteSocketAddress()).getAddress());
								
								
								
								/// � ������ � ������������ �������� � ���� �����
								Equal = InitServer_IpAdress.equals(address) ;//&& (InitServer_Port == Port);
								///� ��� ������ ������ ����� - ��������� ������������ ��������� - ������� ����������
								if(Equal)
								{
									 System.out.println("add new user");
									final DataInputStream in = new DataInputStream(UserSocket.getInputStream());
									String IP = in.readUTF();
									String Name = in.readUTF();
									String Surname = in.readUTF();
									AddUser(IP,Name,Surname);
									/*
									///������ �������� � ������ ����� - ����� ��������, ��� ��� ������
									final DataOutputStream out = new DataOutputStream(UserSocket.getOutputStream());
									out.writeUTF("True");
									*/
									
									
									
									///����������� ��������� ����������
									UserSocket.close();
									String ServerName = new String("ChatServer");
									String InviteMessage = new String ("You are invited for server \n Port: "+ String.valueOf(User_Server_Port));
									SendMessageUser(IP,ServerName,ServerName, InviteMessage);
									
								}
								///���� �������� ������������ ���� ���-�� ���
								else 
								{   
									///�������� ������������
									
										i = 0;
										Equal = IpAdress[i].equals(address);
										System.out.println("Try to define user...");
										System.out.println(address);
										while(!Equal && i < (HowMany-1) )
										{
											i = i+1;
											Equal = IpAdress[i].equals(address);
										}
										///��� ������ Ip ����� ������ ����� ������ - ������� ����������
										/// ���� ��� - ���������� ���������� � ��������� � ���������� � �������
										if (Equal) 
										{
											System.out.println("User defined");
											final DataInputStream in = new DataInputStream(UserSocket.getInputStream());
											String SomeMessage = in.readUTF();
											///������� ��������� - ������� �����
											
											UserSocket.close();
											///����� ����� ������ �������� ��������� ���� � ���
											for(i = 0;i < HowMany;i++)
											{
												SendMessageUser(IpAdress[i],NameUsers[i],SurnameUsers[i], SomeMessage);
											}
										}
										///� ��� �������� ������������, �������� ��� � ���� ������
										else 
										{
											///just ignore user
											System.out.println("Permission denied...");
										}
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
					ServerSocket.close();
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
	public void AddUser(String Ip,String Name,String Surname)
	{
		
		System.out.println("Function for add user is launched");
		String[] IpAdress_old;
		String[] NameUsers_old;
		String[] SurnameUsers_old;
		///���������� ������
		IpAdress_old = IpAdress;
		NameUsers_old =  NameUsers;
		SurnameUsers_old = SurnameUsers;
		
		///���������� ��������
		IpAdress = new String[HowMany+1];
		NameUsers = new String[HowMany+1];
		SurnameUsers = new String[HowMany+1];
		///�����������
		for(int i = 0;i < HowMany;i++)
		{
			IpAdress[i] = new String (IpAdress_old[i]);
			NameUsers[i] = new String (NameUsers_old[i]);
			SurnameUsers[i] = new String (SurnameUsers_old[i]);
		}
		///���������� ������
		IpAdress[HowMany] = new String(Ip);
		NameUsers[HowMany] = new String(Name);
		SurnameUsers[HowMany] = new String(Surname);
		///�� �������� �������� �������
		HowMany = HowMany+1;
		
		System.out.println("New User data");
		System.out.println(IpAdress[HowMany-1]);
		System.out.println(NameUsers[HowMany-1]);
		System.out.println(SurnameUsers[HowMany-1]);
		System.out.println(HowMany);
		
	}
	public void SendMessageUser(String Ip,String Name,String Surname,String message)
	{
		try 
		{
	     final Socket socket = new Socket(Ip, User_Port);
		 // ��������� ����� ������ ������
		 final DataOutputStream out = new DataOutputStream(socket.getOutputStream());
		 // ���������� � ������
		 out.writeUTF(Name);
		 out.writeUTF(Surname);
		 out.writeUTF(message);
		 // ��������� �����
		 socket.close();
		}
		catch (UnknownHostException e) 
		{
			e.printStackTrace();
			
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
			
		}
		
	}
	
}













package bsu.rfe.java.group7.lab7.Ryndin.var1;

public class Peer 
{
	private final String IPAdress;
	private final String Name;
	private final String Surname;
	
	public Peer(String IP,String NamePeer,String SurnamePeer) 
	{
		IPAdress = new String(IP);
		Name = new String(NamePeer);
		Surname = new String(SurnamePeer);
		
	}
	
	public String GetIP()
	{
		return new String(IPAdress);
	}
	
	public String GetName1()
	{
		return new String(Name);
	}
	
	public String GetName2()
	{
		return new String(Surname);
	}
	
	public String GetName3()
	{
		return new String("???");
	}

}

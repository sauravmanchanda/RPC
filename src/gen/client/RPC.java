
import java.util.*;
import java.net.*;
import java.util.ArrayList;
import java.io.*;

public abstract class RPC extends Throwable
{
	public static Vector<String> getSongsList() throws Throwable
	{
		ArrayList<String> ipList = new ArrayList<String>();
		ipList.add("10.102.42.169");

		String serverName = ServerSelector.select(ipList,"first");
		int port = 6066;		//What to do if multiple processes on same machine?

		class retTypeClass implements java.io.Serializable
		{
			public Vector<String> retVal;
		}
		retTypeClass ret = new retTypeClass();
		try
		{
			Socket s = new Socket(serverName,port);
			OutputStream os = s.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(os);
			InputStream is = s.getInputStream();
			ObjectInputStream ois = new ObjectInputStream(is);

			String funName = "getSongsList";
			oos.writeObject(funName);

			Vector<String> retVal=(Vector<String>)ois.readObject();
			ret.retVal=retVal;
			Throwable thro=(Throwable)ois.readObject();

			oos.close();
			os.close();
			ois.close();
			is.close();
			s.close();
			if(thro!=null)
				throw thro;
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return ret.retVal;
	}
	public static Vector<String> getSongsList(String ipAddr) throws Throwable
	{
		ArrayList<String> ipList = new ArrayList<String>();
		ipList.add(ipAddr);

		String serverName = ServerSelector.select(ipList,"first");
		int port = 6066;		//What to do if multiple processes on same machine?

		class retTypeClass implements java.io.Serializable
		{
			public Vector<String> retVal;
		}
		retTypeClass ret = new retTypeClass();
		try
		{
			Socket s = new Socket(serverName,port);
			OutputStream os = s.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(os);
			InputStream is = s.getInputStream();
			ObjectInputStream ois = new ObjectInputStream(is);

			String funName = "getSongsList";
			oos.writeObject(funName);

			Vector<String> retVal=(Vector<String>)ois.readObject();
			ret.retVal=retVal;
			Throwable thro=(Throwable)ois.readObject();

			oos.close();
			os.close();
			ois.close();
			is.close();
			s.close();
			if(thro!=null)
				throw thro;
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return ret.retVal;
	}



	public static Vector<String> getIPList() throws Throwable
	{
		ArrayList<String> ipList = new ArrayList<String>();
		ipList.add("10.102.42.169");

		String serverName = ServerSelector.select(ipList,"first");
		int port = 6066;		//What to do if multiple processes on same machine?

		class retTypeClass implements java.io.Serializable
		{
			public Vector<String> retVal;
		}
		retTypeClass ret = new retTypeClass();
		try
		{
			Socket s = new Socket(serverName,port);
			OutputStream os = s.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(os);
			InputStream is = s.getInputStream();
			ObjectInputStream ois = new ObjectInputStream(is);

			String funName = "getIPList";
			oos.writeObject(funName);

			Vector<String> retVal=(Vector<String>)ois.readObject();
			ret.retVal=retVal;
			Throwable thro=(Throwable)ois.readObject();

			oos.close();
			os.close();
			ois.close();
			is.close();
			s.close();
			if(thro!=null)
				throw thro;
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return ret.retVal;
	}
	public static Vector<String> getIPList(String ipAddr) throws Throwable
	{
		ArrayList<String> ipList = new ArrayList<String>();
		ipList.add(ipAddr);

		String serverName = ServerSelector.select(ipList,"first");
		int port = 6066;		//What to do if multiple processes on same machine?

		class retTypeClass implements java.io.Serializable
		{
			public Vector<String> retVal;
		}
		retTypeClass ret = new retTypeClass();
		try
		{
			Socket s = new Socket(serverName,port);
			OutputStream os = s.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(os);
			InputStream is = s.getInputStream();
			ObjectInputStream ois = new ObjectInputStream(is);

			String funName = "getIPList";
			oos.writeObject(funName);

			Vector<String> retVal=(Vector<String>)ois.readObject();
			ret.retVal=retVal;
			Throwable thro=(Throwable)ois.readObject();

			oos.close();
			os.close();
			ois.close();
			is.close();
			s.close();
			if(thro!=null)
				throw thro;
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return ret.retVal;
	}



	public static int Register(Vector<String> songNames, String ip) throws Throwable
	{
		ArrayList<String> ipList = new ArrayList<String>();
		ipList.add("10.102.42.169");

		String serverName = ServerSelector.select(ipList,"first");
		int port = 6066;		//What to do if multiple processes on same machine?

		class retTypeClass implements java.io.Serializable
		{
			public int retVal;
		}
		retTypeClass ret = new retTypeClass();
		try
		{
			Socket s = new Socket(serverName,port);
			OutputStream os = s.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(os);
			InputStream is = s.getInputStream();
			ObjectInputStream ois = new ObjectInputStream(is);

			String funName = "Register";
			oos.writeObject(funName);
			oos.writeObject(songNames);
			oos.writeObject(ip);

			int retVal=(int)ois.readObject();
			ret.retVal=retVal;
			songNames=(Vector<String>)ois.readObject();
			ip=(String)ois.readObject();
			Throwable thro=(Throwable)ois.readObject();

			oos.close();
			os.close();
			ois.close();
			is.close();
			s.close();
			if(thro!=null)
				throw thro;
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return ret.retVal;
	}
	public static int Register(Vector<String> songNames, String ip,String ipAddr) throws Throwable
	{
		ArrayList<String> ipList = new ArrayList<String>();
		ipList.add(ipAddr);

		String serverName = ServerSelector.select(ipList,"first");
		int port = 6066;		//What to do if multiple processes on same machine?

		class retTypeClass implements java.io.Serializable
		{
			public int retVal;
		}
		retTypeClass ret = new retTypeClass();
		try
		{
			Socket s = new Socket(serverName,port);
			OutputStream os = s.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(os);
			InputStream is = s.getInputStream();
			ObjectInputStream ois = new ObjectInputStream(is);

			String funName = "Register";
			oos.writeObject(funName);
			oos.writeObject(songNames);
			oos.writeObject(ip);

			int retVal=(int)ois.readObject();
			ret.retVal=retVal;
			songNames=(Vector<String>)ois.readObject();
			ip=(String)ois.readObject();
			Throwable thro=(Throwable)ois.readObject();

			oos.close();
			os.close();
			ois.close();
			is.close();
			s.close();
			if(thro!=null)
				throw thro;
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return ret.retVal;
	}



	public static Vector<Byte> getSong(String SongName) throws Throwable
	{
		ArrayList<String> ipList = new ArrayList<String>();
		ipList.add("10.102.42.169");

		String serverName = ServerSelector.select(ipList,"first");
		int port = 6066;		//What to do if multiple processes on same machine?

		class retTypeClass implements java.io.Serializable
		{
			public Vector<Byte> retVal;
		}
		retTypeClass ret = new retTypeClass();
		try
		{
			Socket s = new Socket(serverName,port);
			OutputStream os = s.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(os);
			InputStream is = s.getInputStream();
			ObjectInputStream ois = new ObjectInputStream(is);

			String funName = "getSong";
			oos.writeObject(funName);
			oos.writeObject(SongName);

			Vector<Byte> retVal=(Vector<Byte>)ois.readObject();
			ret.retVal=retVal;
			SongName=(String)ois.readObject();
			Throwable thro=(Throwable)ois.readObject();

			oos.close();
			os.close();
			ois.close();
			is.close();
			s.close();
			if(thro!=null)
				throw thro;
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return ret.retVal;
	}
	public static Vector<Byte> getSong(String SongName,String ipAddr) throws Throwable
	{
		ArrayList<String> ipList = new ArrayList<String>();
		ipList.add(ipAddr);

		String serverName = ServerSelector.select(ipList,"first");
		int port = 6066;		//What to do if multiple processes on same machine?

		class retTypeClass implements java.io.Serializable
		{
			public Vector<Byte> retVal;
		}
		retTypeClass ret = new retTypeClass();
		try
		{
			Socket s = new Socket(serverName,port);
			OutputStream os = s.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(os);
			InputStream is = s.getInputStream();
			ObjectInputStream ois = new ObjectInputStream(is);

			String funName = "getSong";
			oos.writeObject(funName);
			oos.writeObject(SongName);

			Vector<Byte> retVal=(Vector<Byte>)ois.readObject();
			ret.retVal=retVal;
			SongName=(String)ois.readObject();
			Throwable thro=(Throwable)ois.readObject();

			oos.close();
			os.close();
			ois.close();
			is.close();
			s.close();
			if(thro!=null)
				throw thro;
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return ret.retVal;
	}



	public static Vector<Byte> getSonginParts(String SongName, Integer offset, Integer chunksize) throws Throwable
	{
		ArrayList<String> ipList = new ArrayList<String>();
		ipList.add("10.102.42.169");

		String serverName = ServerSelector.select(ipList,"first");
		int port = 6066;		//What to do if multiple processes on same machine?

		class retTypeClass implements java.io.Serializable
		{
			public Vector<Byte> retVal;
		}
		retTypeClass ret = new retTypeClass();
		try
		{
			Socket s = new Socket(serverName,port);
			OutputStream os = s.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(os);
			InputStream is = s.getInputStream();
			ObjectInputStream ois = new ObjectInputStream(is);

			String funName = "getSonginParts";
			oos.writeObject(funName);
			oos.writeObject(SongName);
			oos.writeObject(offset);
			oos.writeObject(chunksize);

			Vector<Byte> retVal=(Vector<Byte>)ois.readObject();
			ret.retVal=retVal;
			SongName=(String)ois.readObject();
			offset=(Integer)ois.readObject();
			chunksize=(Integer)ois.readObject();
			Throwable thro=(Throwable)ois.readObject();

			oos.close();
			os.close();
			ois.close();
			is.close();
			s.close();
			if(thro!=null)
				throw thro;
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return ret.retVal;
	}
	public static Vector<Byte> getSonginParts(String SongName, Integer offset, Integer chunksize,String ipAddr) throws Throwable
	{
		ArrayList<String> ipList = new ArrayList<String>();
		ipList.add(ipAddr);

		String serverName = ServerSelector.select(ipList,"first");
		int port = 6066;		//What to do if multiple processes on same machine?

		class retTypeClass implements java.io.Serializable
		{
			public Vector<Byte> retVal;
		}
		retTypeClass ret = new retTypeClass();
		try
		{
			Socket s = new Socket(serverName,port);
			OutputStream os = s.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(os);
			InputStream is = s.getInputStream();
			ObjectInputStream ois = new ObjectInputStream(is);

			String funName = "getSonginParts";
			oos.writeObject(funName);
			oos.writeObject(SongName);
			oos.writeObject(offset);
			oos.writeObject(chunksize);

			Vector<Byte> retVal=(Vector<Byte>)ois.readObject();
			ret.retVal=retVal;
			SongName=(String)ois.readObject();
			offset=(Integer)ois.readObject();
			chunksize=(Integer)ois.readObject();
			Throwable thro=(Throwable)ois.readObject();

			oos.close();
			os.close();
			ois.close();
			is.close();
			s.close();
			if(thro!=null)
				throw thro;
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return ret.retVal;
	}
}
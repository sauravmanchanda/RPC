import UDC.*;
import java.net.*;
import java.util.ArrayList;
import java.io.*;

public abstract class RPC extends Throwable
{
	public static float refresh() throws Throwable
	{
		class RPCClass implements java.io.Serializable
		{
			public float retVal;
			public Throwable thro;
			public void writeToStream(OutputStream os)
			{
				try
				{
					ObjectOutputStream oos = new ObjectOutputStream(os);
					String funName = "refresh";
					oos.writeObject(funName);
					oos.writeObject(this);
					oos.close();
				}
				catch(Exception e)
				{
					System.out.println(e);
				}
			}
			public RPCClass readFromStream(InputStream is)
			{
				RPCClass returned_obj=this;
				try
				{
					ObjectInputStream ois = new ObjectInputStream(is);
					returned_obj = (RPCClass)ois.readObject();
					ois.close();
				}
				catch(Exception e)
				{
					System.out.println(e);
				}
				finally
				{
					return returned_obj;
				}
			}
		}
		RPCClass RPCObj = new RPCClass();
		RPCObj.thro=null;

		ArrayList<String> ipList = new ArrayList<String>();
		ipList.add("localhost");

		String serverName = ServerSelector.select(ipList,"first");
		int port = 6066;		//What to do if multiple processes on same machine?
		try
		{
			Socket s = new Socket(serverName,port);
			OutputStream os = s.getOutputStream();
			InputStream is = s.getInputStream();
			RPCObj.writeToStream(os);
			os.close();
			RPCObj=RPCObj.readFromStream(is);
			is.close();
			s.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		if(RPCObj.thro!=null)
			throw RPCObj.thro;
		return RPCObj.retVal;
	}



	public static int negate(float a, float b) throws Throwable
	{
		class RPCClass implements java.io.Serializable
		{
			public int retVal;
			public float a;
			public float b;
			public Throwable thro;
			public void writeToStream(OutputStream os)
			{
				try
				{
					ObjectOutputStream oos = new ObjectOutputStream(os);
					String funName = "negate";
					oos.writeObject(funName);
					oos.writeObject(this);
					oos.close();
				}
				catch(Exception e)
				{
					System.out.println(e);
				}
			}
			public RPCClass readFromStream(InputStream is)
			{
				RPCClass returned_obj=this;
				try
				{
					ObjectInputStream ois = new ObjectInputStream(is);
					returned_obj = (RPCClass)ois.readObject();
					ois.close();
				}
				catch(Exception e)
				{
					System.out.println(e);
				}
				finally
				{
					return returned_obj;
				}
			}
		}
		RPCClass RPCObj = new RPCClass();
		RPCObj.a=a;
		RPCObj.b=b;
		RPCObj.thro=null;

		ArrayList<String> ipList = new ArrayList<String>();
		ipList.add("localhost");
		ipList.add("localhost");

		String serverName = ServerSelector.select(ipList,"first");
		int port = 6066;		//What to do if multiple processes on same machine?
		try
		{
			Socket s = new Socket(serverName,port);
			OutputStream os = s.getOutputStream();
			InputStream is = s.getInputStream();
			RPCObj.writeToStream(os);
			os.close();
			RPCObj=RPCObj.readFromStream(is);
			is.close();
			s.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		a = RPCObj.a;
		b = RPCObj.b;
		if(RPCObj.thro!=null)
			throw RPCObj.thro;
		return RPCObj.retVal;
	}



	public static int add(int a, int b, int c) throws Throwable
	{
		class RPCClass implements java.io.Serializable
		{
			public int retVal;
			public int a;
			public int b;
			public int c;
			public Throwable thro;
			public void writeToStream(OutputStream os)
			{
				try
				{
					ObjectOutputStream oos = new ObjectOutputStream(os);
					String funName = "add";
					oos.writeObject(funName);
					oos.writeObject(this);
					oos.close();
				}
				catch(Exception e)
				{
					System.out.println(e);
				}
			}
			public RPCClass readFromStream(InputStream is)
			{
				RPCClass returned_obj=this;
				try
				{
					ObjectInputStream ois = new ObjectInputStream(is);
					returned_obj = (RPCClass)ois.readObject();
					ois.close();
				}
				catch(Exception e)
				{
					System.out.println(e);
				}
				finally
				{
					return returned_obj;
				}
			}
		}
		RPCClass RPCObj = new RPCClass();
		RPCObj.a=a;
		RPCObj.b=b;
		RPCObj.c=c;
		RPCObj.thro=null;

		ArrayList<String> ipList = new ArrayList<String>();
		ipList.add("localhost");

		String serverName = ServerSelector.select(ipList,"first");
		int port = 6066;		//What to do if multiple processes on same machine?
		try
		{
			Socket s = new Socket(serverName,port);
			OutputStream os = s.getOutputStream();
			InputStream is = s.getInputStream();
			RPCObj.writeToStream(os);
			os.close();
			RPCObj=RPCObj.readFromStream(is);
			is.close();
			s.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		a = RPCObj.a;
		b = RPCObj.b;
		c = RPCObj.c;
		if(RPCObj.thro!=null)
			throw RPCObj.thro;
		return RPCObj.retVal;
	}



	public static int multiply(int a, int b, int c) throws Throwable
	{
		class RPCClass implements java.io.Serializable
		{
			public int retVal;
			public int a;
			public int b;
			public int c;
			public Throwable thro;
			public void writeToStream(OutputStream os)
			{
				try
				{
					ObjectOutputStream oos = new ObjectOutputStream(os);
					String funName = "multiply";
					oos.writeObject(funName);
					oos.writeObject(this);
					oos.close();
				}
				catch(Exception e)
				{
					System.out.println(e);
				}
			}
			public RPCClass readFromStream(InputStream is)
			{
				RPCClass returned_obj=this;
				try
				{
					ObjectInputStream ois = new ObjectInputStream(is);
					returned_obj = (RPCClass)ois.readObject();
					ois.close();
				}
				catch(Exception e)
				{
					System.out.println(e);
				}
				finally
				{
					return returned_obj;
				}
			}
		}
		RPCClass RPCObj = new RPCClass();
		RPCObj.a=a;
		RPCObj.b=b;
		RPCObj.c=c;
		RPCObj.thro=null;

		ArrayList<String> ipList = new ArrayList<String>();
		ipList.add("localhost");
		ipList.add("localhost");

		String serverName = ServerSelector.select(ipList,"first");
		int port = 6066;		//What to do if multiple processes on same machine?
		try
		{
			Socket s = new Socket(serverName,port);
			OutputStream os = s.getOutputStream();
			InputStream is = s.getInputStream();
			RPCObj.writeToStream(os);
			os.close();
			RPCObj=RPCObj.readFromStream(is);
			is.close();
			s.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		a = RPCObj.a;
		b = RPCObj.b;
		c = RPCObj.c;
		if(RPCObj.thro!=null)
			throw RPCObj.thro;
		return RPCObj.retVal;
	}
}
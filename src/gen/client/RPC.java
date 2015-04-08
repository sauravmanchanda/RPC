import java.net.*;
import java.util.ArrayList;
import java.io.*;

public abstract class RPC extends Throwable
{
	public static float refresh(vector<int> a) throws Throwable
	{
		ArrayList<String> ipList = new ArrayList<String>();
		ipList.add("10.102.62.121");

		String serverName = ServerSelector.select(ipList,"first");
		int port = 6066;		//What to do if multiple processes on same machine?

		class retTypeClass implements java.io.Serializable
		{
			public float retVal;
		}
		retTypeClass ret = new retTypeClass();
		try
		{
			Socket s = new Socket(serverName,port);
			OutputStream os = s.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(os);
			InputStream is = s.getInputStream();
			ObjectInputStream ois = new ObjectInputStream(is);

			String funName = "refresh";
			oos.writeObject(funName);
			oos.writeObject(a);

			float retVal=(float)ois.readObject();
			ret.retVal=retVal;
			a=(vector<int>)ois.readObject();
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



	public static int negate(float a, float b) throws Throwable
	{
		ArrayList<String> ipList = new ArrayList<String>();
		ipList.add("localhost");
		ipList.add("localhost");

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

			String funName = "negate";
			oos.writeObject(funName);
			oos.writeObject(a);
			oos.writeObject(b);

			int retVal=(int)ois.readObject();
			ret.retVal=retVal;
			a=(float)ois.readObject();
			b=(float)ois.readObject();
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



	public static int add(int a, int b) throws Throwable
	{
		ArrayList<String> ipList = new ArrayList<String>();
		ipList.add("localhost");

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

			String funName = "add";
			oos.writeObject(funName);
			oos.writeObject(a);
			oos.writeObject(b);

			int retVal=(int)ois.readObject();
			ret.retVal=retVal;
			a=(int)ois.readObject();
			b=(int)ois.readObject();
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



	public static int multiply(int a, int b) throws Throwable
	{
		ArrayList<String> ipList = new ArrayList<String>();
		ipList.add("localhost");
		ipList.add("localhost");

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

			String funName = "multiply";
			oos.writeObject(funName);
			oos.writeObject(a);
			oos.writeObject(b);

			int retVal=(int)ois.readObject();
			ret.retVal=retVal;
			a=(int)ois.readObject();
			b=(int)ois.readObject();
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
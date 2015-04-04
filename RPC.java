import UDC.*;
import java.net.*;
import java.io.*;

public abstract class RPC extends Throwable
{
	public static float refresh()
	{
		class RPCClass implements java.io.Serializable
		{
			public float retVal;
			public String funName;
			public Throwable thro;
			public void write_to_stream(OutputStream os)
			{
				try
				{
					ObjectOutputStream oos = new ObjectOutputStream(os);
					oos.writeObject(this);
					oos.close();
				}
				catch(Exception e)
				{
					System.out.println(e);
				}
			}
		}
		RPCClass RPCObj = new RPCClass();
		RPCObj.retVal=null;
		RPCObj.funName="refresh";
		RPCObj.thro=null;
		Thread receiver = new Thread("Receiver")
		{
			public void run()
			{
				try
				{
					ServerSocket ss = new ServerSocket(2002);
					Socket receiver = ss.accept();
					InputStream is = receiver.getInputStream();
					ObjectInputStream ois = new ObjectInputStream(is);
					RPCClass returned_obj = (RPCClass)ois.readObject();
					ois.close();
					is.close();
					receiver.close();
					ss.close();
					RPCObj.thro = returned_obj.thro;
					RPCObj.retVal = returned_obj.retVal;
				}
				catch(Exception e)
				{
					System.out.println(e);
				}
			}
		};
		receiver.start();
		try
		{
			Socket s = new Socket("localhost",2002);
			OutputStream os = s.getOutputStream();
			RPCObj.write_to_stream(os);
			os.close();
			s.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		while(receiver.getState()!=Thread.State.TERMINATED){}
		return RPCObj.retVal;
	}



	public static int negate(float a, float b)
	{
		class RPCClass implements java.io.Serializable
		{
			public int retVal;
			public String funName;
			public float a;
			public float b;
			public Throwable thro;
			public void write_to_stream(OutputStream os)
			{
				try
				{
					ObjectOutputStream oos = new ObjectOutputStream(os);
					oos.writeObject(this);
					oos.close();
				}
				catch(Exception e)
				{
					System.out.println(e);
				}
			}
		}
		RPCClass RPCObj = new RPCClass();
		RPCObj.retVal=null;
		RPCObj.funName="negate";
		RPCObj.a=a;
		RPCObj.b=b;
		RPCObj.thro=null;
		Thread receiver = new Thread("Receiver")
		{
			public void run()
			{
				try
				{
					ServerSocket ss = new ServerSocket(2002);
					Socket receiver = ss.accept();
					InputStream is = receiver.getInputStream();
					ObjectInputStream ois = new ObjectInputStream(is);
					RPCClass returned_obj = (RPCClass)ois.readObject();
					ois.close();
					is.close();
					receiver.close();
					ss.close();
					RPCObj.a = returned_obj.a;
					RPCObj.b = returned_obj.b;
					RPCObj.thro = returned_obj.thro;
					RPCObj.retVal = returned_obj.retVal;
				}
				catch(Exception e)
				{
					System.out.println(e);
				}
			}
		};
		receiver.start();
		try
		{
			Socket s = new Socket("localhost",2002);
			OutputStream os = s.getOutputStream();
			RPCObj.write_to_stream(os);
			os.close();
			s.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		while(receiver.getState()!=Thread.State.TERMINATED){}
		a = RPCObj.a;
		b = RPCObj.b;
		return RPCObj.retVal;
	}



	public static int add(int a, int b, int c)
	{
		class RPCClass implements java.io.Serializable
		{
			public int retVal;
			public String funName;
			public int a;
			public int b;
			public int c;
			public Throwable thro;
			public void write_to_stream(OutputStream os)
			{
				try
				{
					ObjectOutputStream oos = new ObjectOutputStream(os);
					oos.writeObject(this);
					oos.close();
				}
				catch(Exception e)
				{
					System.out.println(e);
				}
			}
		}
		RPCClass RPCObj = new RPCClass();
		RPCObj.retVal=null;
		RPCObj.funName="add";
		RPCObj.a=a;
		RPCObj.b=b;
		RPCObj.c=c;
		RPCObj.thro=null;
		Thread receiver = new Thread("Receiver")
		{
			public void run()
			{
				try
				{
					ServerSocket ss = new ServerSocket(2002);
					Socket receiver = ss.accept();
					InputStream is = receiver.getInputStream();
					ObjectInputStream ois = new ObjectInputStream(is);
					RPCClass returned_obj = (RPCClass)ois.readObject();
					ois.close();
					is.close();
					receiver.close();
					ss.close();
					RPCObj.a = returned_obj.a;
					RPCObj.b = returned_obj.b;
					RPCObj.c = returned_obj.c;
					RPCObj.thro = returned_obj.thro;
					RPCObj.retVal = returned_obj.retVal;
				}
				catch(Exception e)
				{
					System.out.println(e);
				}
			}
		};
		receiver.start();
		try
		{
			Socket s = new Socket("localhost",2002);
			OutputStream os = s.getOutputStream();
			RPCObj.write_to_stream(os);
			os.close();
			s.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		while(receiver.getState()!=Thread.State.TERMINATED){}
		a = RPCObj.a;
		b = RPCObj.b;
		c = RPCObj.c;
		return RPCObj.retVal;
	}



	public static int multiply(int a, int b, int c)
	{
		class RPCClass implements java.io.Serializable
		{
			public int retVal;
			public String funName;
			public int a;
			public int b;
			public int c;
			public Throwable thro;
			public void write_to_stream(OutputStream os)
			{
				try
				{
					ObjectOutputStream oos = new ObjectOutputStream(os);
					oos.writeObject(this);
					oos.close();
				}
				catch(Exception e)
				{
					System.out.println(e);
				}
			}
		}
		RPCClass RPCObj = new RPCClass();
		RPCObj.retVal=null;
		RPCObj.funName="multiply";
		RPCObj.a=a;
		RPCObj.b=b;
		RPCObj.c=c;
		RPCObj.thro=null;
		Thread receiver = new Thread("Receiver")
		{
			public void run()
			{
				try
				{
					ServerSocket ss = new ServerSocket(2002);
					Socket receiver = ss.accept();
					InputStream is = receiver.getInputStream();
					ObjectInputStream ois = new ObjectInputStream(is);
					RPCClass returned_obj = (RPCClass)ois.readObject();
					ois.close();
					is.close();
					receiver.close();
					ss.close();
					RPCObj.a = returned_obj.a;
					RPCObj.b = returned_obj.b;
					RPCObj.c = returned_obj.c;
					RPCObj.thro = returned_obj.thro;
					RPCObj.retVal = returned_obj.retVal;
				}
				catch(Exception e)
				{
					System.out.println(e);
				}
			}
		};
		receiver.start();
		try
		{
			Socket s = new Socket("localhost",2002);
			OutputStream os = s.getOutputStream();
			RPCObj.write_to_stream(os);
			os.close();
			s.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		while(receiver.getState()!=Thread.State.TERMINATED){}
		a = RPCObj.a;
		b = RPCObj.b;
		c = RPCObj.c;
		return RPCObj.retVal;
	}
}
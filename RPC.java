import UDC.*;
import java.net.*;
import java.io.*;

public abstract class RPC extends Throwable
{
	public static udc0 fun(udc1 p0,udc2 p1)
	{
		class RPCClass implements java.io.Serializable
		{
			public udc0 retVal;
			public String funName;
			public udc1 p0;
			public udc2 p1;
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
		RPCObj.funName="fun";
		RPCObj.p0=p0;
		RPCObj.p1=p1;
		RPCObj.thro=null;
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
		return RPCObj.retVal;
	}
}
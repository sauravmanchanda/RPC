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
					RPCObj.p0 = returned_obj.p0;
					RPCObj.p1 = returned_obj.p1;
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
		p0 = RPCObj.p0;
		p1 = RPCObj.p1;
		return RPCObj.retVal;
	}
}
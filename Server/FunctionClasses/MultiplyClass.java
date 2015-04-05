package FunctionClasses;
import UDC.*;
public abstract class multiplyClass
{
	public static int multiply(int a, int b, int c) throws Throwable
	{



	}
	public static void multiplyCaller(InputStream is, OutputStream os)
	{
		class RPCClass implements java.io.Serializable
		{
			public int retVal;
			public String funName;
			public int a;
			public int b;
			public int c;
			public Throwable thro;
			public void writeToStream(OutputStream os)
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
		try
		{
			RPCObj=RPCObj.readFromStream(is);
			try
			{
				RPCObj.retVal = multiply(RPCObj.a, RPCObj.b, RPCObj.c);
			}
			catch(Throwable th)
			{
				RPCObj.thro=th;
			}
			RPCObj.writeToStream(os);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}
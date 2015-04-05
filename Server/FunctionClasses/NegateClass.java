package FunctionClasses;
import UDC.*;
public abstract class negateClass
{
	public static int negate(float a, float b) throws Throwable
	{



	}
	public static void negateCaller(InputStream is, OutputStream os)
	{
		class RPCClass implements java.io.Serializable
		{
			public int retVal;
			public String funName;
			public float a;
			public float b;
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
				RPCObj.retVal = negate(RPCObj.a, RPCObj.b);
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
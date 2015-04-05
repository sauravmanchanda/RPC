package FunctionClasses;
import UDC.*;
public abstract class refreshClass
{
	public static float refresh() throws Throwable
	{



	}
	public static void refreshCaller(InputStream is, OutputStream os)
	{
		class RPCClass implements java.io.Serializable
		{
			public float retVal;
			public String funName;
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
				RPCObj.retVal = refresh();
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
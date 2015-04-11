
import java.util.*;
import java.io.*;
public abstract class GetIPListClass
{
	public static Vector<String> getIPList() throws Throwable
	{
		try{
			return local.getIPList();
		}		catch(Throwable th){
			throw th;
		}	}

	public static void getIPListCaller(ObjectInputStream ois, ObjectOutputStream oos)
	{
		try
		{
			class retTypeClass implements java.io.Serializable
			{
				public Vector<String> retVal;
			}
			retTypeClass ret = new retTypeClass();

			Vector<String> retVal=ret.retVal;
			Throwable thro=null;
			try
			{
				retVal = getIPList();
			}
			catch(Throwable th)
			{
				thro=th;
			}
			oos.writeObject(retVal);
			oos.writeObject(thro);

		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}
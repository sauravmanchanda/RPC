
import java.util.*;
import java.io.*;
public abstract class RegisterClass
{
	public static int Register(Vector<String> songNames, String ip) throws Throwable
	{
		try{
			return local.Register(songNames, ip);
		}		catch(Throwable th){
			throw th;
		}	}

	public static void RegisterCaller(ObjectInputStream ois, ObjectOutputStream oos)
	{
		try
		{
			class retTypeClass implements java.io.Serializable
			{
				public int retVal;
			}
			retTypeClass ret = new retTypeClass();
			Vector<String> songNames=(Vector<String>)ois.readObject();
			String ip=(String)ois.readObject();

			int retVal=ret.retVal;
			Throwable thro=null;
			try
			{
				retVal = Register(songNames, ip);
			}
			catch(Throwable th)
			{
				thro=th;
			}
			oos.writeObject(retVal);
			oos.writeObject(songNames);
			oos.writeObject(ip);
			oos.writeObject(thro);

		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}

import java.util.*;
import java.io.*;
public abstract class GetSongsListClass
{
	public static Vector<String> getSongsList() throws Throwable
	{
		try{
			return local.getSongsList();
		}		catch(Throwable th){
			throw th;
		}	}

	public static void getSongsListCaller(ObjectInputStream ois, ObjectOutputStream oos)
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
				retVal = getSongsList();
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
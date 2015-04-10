
import java.util.*;
import java.io.*;
public abstract class GetSongClass
{
	public static Vector<Byte> getSong(String SongName) throws Throwable
	{
		try{
			return local.getSong(SongName);
		}		catch(Throwable th){
			throw th;
		}	}

	public static void getSongCaller(ObjectInputStream ois, ObjectOutputStream oos)
	{
		try
		{
			class retTypeClass implements java.io.Serializable
			{
				public Vector<Byte> retVal;
			}
			retTypeClass ret = new retTypeClass();
			String SongName=(String)ois.readObject();

			Vector<Byte> retVal=ret.retVal;
			Throwable thro=null;
			try
			{
				retVal = getSong(SongName);
			}
			catch(Throwable th)
			{
				thro=th;
			}
			oos.writeObject(retVal);
			oos.writeObject(SongName);
			oos.writeObject(thro);

		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}
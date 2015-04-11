
import java.util.*;
import java.io.*;
public abstract class GetSonginPartsClass
{
	public static Vector<Byte> getSonginParts(String SongName, Integer offset, Integer chunksize) throws Throwable
	{
		try{
			return local.getSonginParts(SongName, offset, chunksize);
		}		catch(Throwable th){
			throw th;
		}	}

	public static void getSonginPartsCaller(ObjectInputStream ois, ObjectOutputStream oos)
	{
		try
		{
			class retTypeClass implements java.io.Serializable
			{
				public Vector<Byte> retVal;
			}
			retTypeClass ret = new retTypeClass();
			String SongName=(String)ois.readObject();
			Integer offset=(Integer)ois.readObject();
			Integer chunksize=(Integer)ois.readObject();

			Vector<Byte> retVal=ret.retVal;
			Throwable thro=null;
			try
			{
				retVal = getSonginParts(SongName, offset, chunksize);
			}
			catch(Throwable th)
			{
				thro=th;
			}
			oos.writeObject(retVal);
			oos.writeObject(SongName);
			oos.writeObject(offset);
			oos.writeObject(chunksize);
			oos.writeObject(thro);

		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}

import java.util.*;
import java.io.*;
public abstract class RefreshClass
{
	public static float refresh(Vector<Integer> a) throws Throwable
	{
		try{
			return local.refresh(a);
		}		catch(Throwable th){
			throw th;
		}	}

	public static void refreshCaller(ObjectInputStream ois, ObjectOutputStream oos)
	{
		try
		{
			class retTypeClass implements java.io.Serializable
			{
				public float retVal;
			}
			retTypeClass ret = new retTypeClass();
			Vector<Integer> a=(Vector<Integer>)ois.readObject();

			float retVal=ret.retVal;
			Throwable thro=null;
			try
			{
				retVal = refresh(a);
			}
			catch(Throwable th)
			{
				thro=th;
			}
			oos.writeObject(retVal);
			oos.writeObject(a);
			oos.writeObject(thro);

		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}
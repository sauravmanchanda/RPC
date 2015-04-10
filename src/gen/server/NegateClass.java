
import java.util.*;
import java.io.*;
public abstract class NegateClass
{
	public static int negate(float a, float b) throws Throwable
	{
		try{
			return local.negate(a, b);
		}		catch(Throwable th){
			throw th;
		}	}

	public static void negateCaller(ObjectInputStream ois, ObjectOutputStream oos)
	{
		try
		{
			class retTypeClass implements java.io.Serializable
			{
				public int retVal;
			}
			retTypeClass ret = new retTypeClass();
			float a=(float)ois.readObject();
			float b=(float)ois.readObject();

			int retVal=ret.retVal;
			Throwable thro=null;
			try
			{
				retVal = negate(a, b);
			}
			catch(Throwable th)
			{
				thro=th;
			}
			oos.writeObject(retVal);
			oos.writeObject(a);
			oos.writeObject(b);
			oos.writeObject(thro);

		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}
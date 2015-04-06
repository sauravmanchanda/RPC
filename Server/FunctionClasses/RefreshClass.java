package FunctionClasses;
import UDC.*;
import java.io.*;
public abstract class RefreshClass
{
	public static float refresh() throws Throwable
	{



	}
	public static void refreshCaller(ObjectInputStream ois, ObjectOutputStream oos)
	{
		try
		{
			class retTypeClass implements java.io.Serializable
			{
				public float retVal;
			}
			retTypeClass ret = new retTypeClass();

			float retVal=ret.retVal;
			Throwable thro=null;
			try
			{
				retVal = refresh();
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
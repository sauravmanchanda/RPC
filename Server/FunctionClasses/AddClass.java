package FunctionClasses;
import UDC.*;
import java.io.*;
public abstract class AddClass
{
	public static int add(int a, int b, int c) throws Throwable
	{

		return a+b+c;

	}
	public static void addCaller(ObjectInputStream ois, ObjectOutputStream oos)
	{
		try
		{
			class retTypeClass implements java.io.Serializable
			{
				public int retVal;
			}
			retTypeClass ret = new retTypeClass();
			int a=(int)ois.readObject();
			int b=(int)ois.readObject();
			int c=(int)ois.readObject();

			int retVal=ret.retVal;
			Throwable thro=null;
			try
			{
				retVal = add(a, b, c);
			}
			catch(Throwable th)
			{
				thro=th;
			}
			oos.writeObject(retVal);
			oos.writeObject(a);
			oos.writeObject(b);
			oos.writeObject(c);
			oos.writeObject(thro);

		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}
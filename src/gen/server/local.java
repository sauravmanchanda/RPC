import java.util.*;
public abstract class local
{
	public static float refresh(Vector <Integer> a){
		Enumeration vEnum = a.elements(); 
		while(vEnum.hasMoreElements())
			System.out.print(vEnum.nextElement() + " "); 
		return 1;
	}

	public static int multiply(int a, int b) throws Throwable
	{
		return a * b ;
	}

	public static int add(int a, int b) throws Throwable
	{
		return a + b ;
	}
	
	public static int negate(float a, float b) throws Throwable
	{
		return 0;
	}
	
}
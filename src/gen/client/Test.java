import java.util.*;

public class Test
{
	public static void main(String[] args)
	{
		try
		{
			int a=3;
			int b=33;
			int c=123;
			int d=RPC.add(a,b);
			System.out.println("a = "+a);
			System.out.println("b = "+b);
			System.out.println("c = "+c);
			System.out.println("d = "+d);


			Vector v = new Vector(3, 2);
			System.out.println("Initial size: " + v.size());
			System.out.println("Initial capacity: " + v.capacity());
			
			for (int i = 1; i < 1000; i ++){
				v.addElement(new Integer(i)); 
			}
			
			System.out.println("Final size: " + v.size());
			System.out.println("calling refresh");
			RPC.refresh(v);
		}
		catch(Throwable th)
		{
			System.out.println(th);
		}
	}

}
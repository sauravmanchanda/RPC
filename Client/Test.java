public class Test
{
	public static void main(String[] args)
	{
		try
		{
			int a=3;
			int b=33;
			int c=123;
			int d=RPC.add(a,b,c);
			System.out.println("a = "+a);
			System.out.println("b = "+b);
			System.out.println("c = "+c);
			System.out.println("d = "+d);
		}
		catch(Throwable th)
		{
			System.out.println(th);
		}
	}

}
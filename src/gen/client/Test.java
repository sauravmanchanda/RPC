import java.util.*;
import java.io.*;

public class Test
{
	public static void main(String[] args)
	{
		try
		{
			Vector<String> v = new Vector();
			for (Integer i = 0; i < 10; i ++){
				v.addElement( "song " + i.toString()); 
				System.out.println("added " + v.get(i));
			}
		

			RPC.Register(v, "10.102.42.169");
			System.out.println("finished registering");

			Vector <String > x,y;
			x = new Vector(); y = new Vector();
			x.add ("a"); y.add("b");
			System.out.println("getting songs ");
			x = RPC.getSongsList();
			y = RPC.getIPList();
			System.out.println("getting songs call completed " + x.size () + " " + y.size());

			for(int i = 0; i < x.size(); i++){
				System.out.println("received " + x.get(i) + " at " + y.get(i));
			}
			Vector<Byte> b = RPC.getSong("a", "10.102.42.169");
			FileOutputStream fileStream=new FileOutputStream(new File("media.mp3"));
			BufferedOutputStream bos = new BufferedOutputStream(fileStream);
			for(int i = 0; i < b.size(); i++){
				bos.write(b.get(i));
			}

		}
		catch(Throwable th)
		{
			System.out.println("some error");
			System.out.println(th);
		}
	}

}
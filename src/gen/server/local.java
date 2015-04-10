import java.util.*;
import java.io.*;

public abstract class local
{
	public static Vector<String> sNames = new Vector();
	public static Vector<String> ipNames= new Vector();
	
	public static Vector<String> getSongsList(){
		return sNames;
	}

	public static Vector<String> getIPList(){
		return ipNames;
	}

	public static int Register(Vector <String> songNames, String ipAddr) throws Throwable
	{
		System.out.println("Called Register " + songNames.size());	
	
		for(Integer i = 0; i < songNames.size(); i++){
			sNames.add(songNames.get(i));
			ipNames.add (ipAddr);	
			System.out.println("Registering : " + songNames.get(i) + "  at " + ipAddr);	
		}
		System.out.println("Finished registering " + ipAddr);
		return 0;
	}

	public static Vector<Byte> getSong(String songName){


	/// getBytes from anyWhere
	// I'm getting byte array from File
		System.out.println("get song called for " + songName);
		try {
			File file=null;
			FileInputStream fileStream=new FileInputStream(file=new File("media.mp3"));

	//		FileInputStream fileStream=new FileInputStream(file=new File("songs/" + songName));

		    // Instantiate array
			System.out.println("file size : " + (int)file.length() );
		    byte []arr= new byte[(int)file.length()];

		    /// read All bytes of File stream
		    fileStream.read(arr,0,arr.length);
		    System.out.println("reading over, printing file");
		    Vector<Byte> v = new Vector();

		    for (int i =0 ; i < arr.length; i++){
//		    	System.out.println( "set at " + i + " " + arr[i]);
		        v.add(arr[i]);
		    }
		    return v;
	    }
	    catch(Exception e)
		{
			System.out.println(e);
		}
		return null;
	}
}
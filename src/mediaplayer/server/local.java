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
			FileInputStream fileStream=new FileInputStream(file=new File("songs/" + songName));

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

	public static Vector<Byte> getSonginParts(String songName, Integer offset, Integer chunksize){


	/// getBytes from anyWhere
	// I'm getting byte array from File
		System.out.println("get song in part for song called for " + songName + " from " + offset.toString() + " size " + chunksize);
		try {
			File file=null;
			FileInputStream fileStream=new FileInputStream(file=new File("songs/" + songName));

	//		FileInputStream fileStream=new FileInputStream(file=new File("songs/" + songName));

		    // Instantiate array
			System.out.println("file size : " + (int)file.length() );
		    byte []arr= new byte[chunksize];
		    fileStream.skip(offset);
		    /// read All bytes of File stream
		    int retval = fileStream.read(arr,0,arr.length);
		    System.out.println("reading over, sending chunk");
		    Vector<Byte> v = new Vector();
		    if (retval == -1 ){
		    	System.out.println("EOF reached, returning");
		    	return v;
		    }
		    for (int i =0 ; i < retval; i++){
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

	public static int Deregister(String ip){
		Vector<String> s, ips;
		s = new Vector(sNames); ips = new Vector(ipNames);
		sNames.clear(); ipNames.clear();
		for (int i = 0 ; i < ipNames.size(); i++){
			if (ipNames.get(i).equals(ip)){

			}else{
				sNames.add(s.get(i));
				ipNames.add(ips.get(i));
			}
		}

	}
}
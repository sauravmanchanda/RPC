package tutorial;

import java.util.ArrayList;
public abstract class ServerSelector
{
	public static String select(ArrayList<String> ipList, String type)
	{
		if (type.equals("first"))
			return ipList.get(0);
		return ipList.get(0);
	}

}
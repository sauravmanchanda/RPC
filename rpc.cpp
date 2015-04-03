#include <iostream>
#include <fstream>
#include <vector>
#include <string>

using namespace std;

void writeFunction(ofstream &fout)
{
	/* code */
	string retType("udc0");
	string funName("fun");
	vector<string> parVect;
	vector<string> ipList;
	parVect.push_back("udc1");
	parVect.push_back("udc2");
	ipList.push_back("127.0.0.1");
	







	//Work starts here
	fout<<"\tpublic static "<<retType<<" "<<funName<<"(";
	int i;
	for (i=0;i<parVect.size()-1;i++)
	{
		fout<<parVect[i]<<" p"<<i<<",";
	}
	if(parVect.size())
		fout<<parVect[i]<<" p"<<i;	
	fout<<")\n\t{\n";



	fout<<"\t\tclass RPCClass implements java.io.Serializable\n\t\t{\n";
	fout<<"\t\t\tpublic "<<retType<<" retVal;\n";
	fout<<"\t\t\tpublic String funName;\n";
	for (i=0;i<parVect.size();i++)
	{
		fout<<"\t\t\tpublic "<<parVect[i]<<" p"<<i<<";\n";
	}
	fout<<"\t\t\tpublic Throwable thro;\n";
	fout<<"\t\t\tpublic void write_to_stream(OutputStream os)\n";	
	fout<<"\t\t\t{\n";		
	fout<<"\t\t\t\ttry\n";	
	fout<<"\t\t\t\t{\n";
	fout<<"\t\t\t\t\tObjectOutputStream oos = new ObjectOutputStream(os);\n";
	fout<<"\t\t\t\t\toos.writeObject(this);\n";
	fout<<"\t\t\t\t\toos.close();\n";
	fout<<"\t\t\t\t}\n";		
	fout<<"\t\t\t\tcatch(Exception e)\n";	
	fout<<"\t\t\t\t{\n";
	fout<<"\t\t\t\t\tSystem.out.println(e);\n";
	fout<<"\t\t\t\t}\n";	
	fout<<"\t\t\t}\n";	
	fout<<"\t\t}\n";


	fout<<"\t\tRPCClass RPCObj = new RPCClass();\n";
	fout<<"\t\tRPCObj.retVal=null;\n";
	fout<<"\t\tRPCObj.funName=\""<<funName<<"\";\n";
	for (i=0;i<parVect.size();i++)
	{
		fout<<"\t\tRPCObj.p"<<i<<"=p"<<i<<";\n";
	}
	fout<<"\t\tRPCObj.thro=null;\n";
	//Work to be done

	fout<<"\t\tThread receiver = new Thread(\"Receiver\")\n";	
	fout<<"\t\t{\n";
	fout<<"\t\t\tpublic void run()\n";	
	fout<<"\t\t\t{\n";	
	fout<<"\t\t\t\ttry\n";	
	fout<<"\t\t\t\t{\n";
	fout<<"\t\t\t\t\tServerSocket ss = new ServerSocket(2002);\n";
	fout<<"\t\t\t\t\tSocket receiver = ss.accept();\n";
	fout<<"\t\t\t\t\tInputStream is = receiver.getInputStream();\n";
	fout<<"\t\t\t\t\tObjectInputStream ois = new ObjectInputStream(is);\n";
	fout<<"\t\t\t\t\tRPCClass returned_obj = (RPCClass)ois.readObject();\n";
	fout<<"\t\t\t\t\tois.close();\n";
	fout<<"\t\t\t\t\tis.close();\n";
	fout<<"\t\t\t\t\treceiver.close();\n";
	fout<<"\t\t\t\t\tss.close();\n";
	fout<<"\t\t\t\t\tRPCObj.p0 = returned_obj.p0;\n";
	fout<<"\t\t\t\t\tRPCObj.p1 = returned_obj.p1;\n";
	fout<<"\t\t\t\t\tRPCObj.thro = returned_obj.thro;\n";
	fout<<"\t\t\t\t\tRPCObj.retVal = returned_obj.retVal;\n";
	fout<<"\t\t\t\t}\n";
	fout<<"\t\t\t\tcatch(Exception e)\n";	
	fout<<"\t\t\t\t{\n";
	fout<<"\t\t\t\t\tSystem.out.println(e);\n";
	fout<<"\t\t\t\t}\n";	
	fout<<"\t\t\t}\n";	
	fout<<"\t\t};\n";	
	fout<<"\t\treceiver.start();\n";
	fout<<"\t\ttry\n";	
	fout<<"\t\t{\n";
	fout<<"\t\t\tSocket s = new Socket(\"localhost\",2002);\n";
	fout<<"\t\t\tOutputStream os = s.getOutputStream();\n";
	fout<<"\t\t\tRPCObj.write_to_stream(os);\n";
	fout<<"\t\t\tos.close();\n";
	fout<<"\t\t\ts.close();\n";
	fout<<"\t\t}\n";		
	fout<<"\t\tcatch(Exception e)\n";	
	fout<<"\t\t{\n";
	fout<<"\t\t\tSystem.out.println(e);\n";
	fout<<"\t\t}\n";	




	fout<<"\t\twhile(receiver.getState()!=Thread.State.TERMINATED){}\n";
	for (i=0;i<parVect.size();i++)
	{
		fout<<"\t\tp"<<i<<" = RPCObj.p"<<i<<";\n";
	}
	fout<<"\t\treturn RPCObj.retVal;\n";
	fout<<"\t}\n";




	//work ends here














	
}
void writeFile()
{
	ofstream fout("RPC.java",ofstream::out);
	fout<<"import UDC.*;\n";
	fout<<"import java.net.*;\n";
	fout<<"import java.io.*;\n\n";
	fout<<"public abstract class RPC extends Throwable\n{\n";

	writeFunction(fout);
	
	fout<<"}";
	fout.close();
}
int main(int argc, char const *argv[])
{
	writeFile();
	return 0;
}
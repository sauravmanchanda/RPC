#include <iostream>
#include <fstream>
#include <vector>
#include <string>
#include <map>
#include "decl.h"

using namespace std;
using namespace MyNamespace;

extern void writeFunction(ofstream &fout, string retType, string funName, vector<Argument>& parVect, vector<string>& ipList)
{
  /* code */

//Work starts here
  fout<<"\tpublic static "<<retType<<" "<<funName<<"(";
  int i;
  for (i=0;i<parVect.size();i++)
  {
    fout<< parVect[i].datatype <<" "<<parVect[i].name;
      if (i < parVect.size() - 1) 
                fout << ", " ;
        }
  fout<<")\n\t{\n";



fout<<"\t\tclass RPCClass implements java.io.Serializable\n\t\t{\n";
  fout<<"\t\t\tpublic "<<retType<<" retVal;\n";
  fout<<"\t\t\tpublic String funName;\n";
  for (i=0;i<parVect.size();i++)
  {
    fout<<"\t\t\tpublic "<< parVect[i].datatype <<" "<<parVect[i].name<<";\n";
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
  // fout<<"\t\tRPCObj.retVal=null;\n";
  fout<<"\t\tRPCObj.funName=\""<<funName<<"\";\n";
  for (i=0;i<parVect.size();i++)
  {
    fout<<"\t\tRPCObj."<<parVect[i].name<<"="<<parVect[i].name<<";\n";
  }
  fout<<"\t\tRPCObj.thro=null;\n";
  
  fout<<"\t\tArrayList<String> ipList = new ArrayList<String>();\n";
  for (i=0;i<ipList.size();i++)
  {
    fout<<"\t\tipList.add(\""<<ipList[i]<<"\");\n";
  }
  fout<<"\n";


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

for (i=0;i<parVect.size();i++)
  {
    fout<<"\t\t\t\t\tRPCObj."<<parVect[i].name<<" = returned_obj."<<parVect[i].name<<";\n";
  }
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
    fout<<"\t\t"<<parVect[i].name<<" = RPCObj."<<parVect[i].name<<";\n";
  }
  fout<<"\t\treturn RPCObj.retVal;\n";
  fout<<"\t}\n";

//work ends here
}


void writeFile(ofstream &fout, map <int, string> &allReturnTypes, map <int, string> &funcName, map <int, vector <Argument> > &allArguments, map <int, vector <string> > &allLocations, int &funcID)
{
	fout<<"import UDC.*;\n";
	fout<<"import java.net.*;\n";
  fout<<"import java.util.ArrayList;\n";
	fout<<"import java.io.*;\n\n";
	fout<<"public abstract class RPC extends Throwable\n{\n";

  for(int i = 1; i < funcID; i++){
            if (i > 1) {fout << "\n\n\n" ;}
            writeFunction(fout, allReturnTypes[i], funcName[i], allArguments[i], allLocations[i]);
          }

	
	fout<<"}";
}
/*
int main(int argc, char const *argv[])
{
	writeFile();
	return 0;
}
*/

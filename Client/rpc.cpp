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
  fout<<") throws Throwable\n\t{\n";


  /*
  fout<<"\t\tclass RPCClass implements java.io.Serializable\n\t\t{\n";
  fout<<"\t\t\tpublic "<<retType<<" retVal;\n";
  for (i=0;i<parVect.size();i++)
  {
    fout<<"\t\t\tpublic "<< parVect[i].datatype <<" "<<parVect[i].name<<";\n";
  }
  fout<<"\t\t\tpublic Throwable thro;\n";
  fout<<"\t\t\tpublic void writeToStream(OutputStream os)\n";	
  fout<<"\t\t\t{\n";		
  fout<<"\t\t\t\ttry\n";	
  fout<<"\t\t\t\t{\n";
  fout<<"\t\t\t\t\tObjectOutputStream oos = new ObjectOutputStream(os);\n";
  fout<<"\t\t\t\t\tString funName = \""<<funName<<"\";\n";
  fout<<"\t\t\t\t\toos.writeObject(funName);\n";
  fout<<"\t\t\t\t\toos.writeObject(this);\n";
  fout<<"\t\t\t\t\toos.close();\n";
  fout<<"\t\t\t\t}\n";		
  fout<<"\t\t\t\tcatch(Exception e)\n";	
  fout<<"\t\t\t\t{\n";
  fout<<"\t\t\t\t\tSystem.out.println(e);\n";
  fout<<"\t\t\t\t}\n";	
  fout<<"\t\t\t}\n";

  fout<<"\t\t\tpublic RPCClass readFromStream(InputStream is)\n"; 
  fout<<"\t\t\t{\n";    
  fout<<"\t\t\t\tRPCClass returned_obj=this;\n";
  fout<<"\t\t\t\ttry\n";  
  fout<<"\t\t\t\t{\n";
  fout<<"\t\t\t\t\tObjectInputStream ois = new ObjectInputStream(is);\n";
  fout<<"\t\t\t\t\treturned_obj = (RPCClass)ois.readObject();\n";
  fout<<"\t\t\t\t\tois.close();\n";
  fout<<"\t\t\t\t}\n";    
  fout<<"\t\t\t\tcatch(Exception e)\n"; 
  fout<<"\t\t\t\t{\n";
  fout<<"\t\t\t\t\tSystem.out.println(e);\n";
  fout<<"\t\t\t\t}\n";
  fout<<"\t\t\t\tfinally\n"; 
  fout<<"\t\t\t\t{\n";
  fout<<"\t\t\t\t\treturn returned_obj;\n";
  fout<<"\t\t\t\t}\n";
  fout<<"\t\t\t}\n";
  fout<<"\t\t}\n";
  fout<<"\t\tRPCClass RPCObj = new RPCClass();\n";
  */
  // fout<<"\t\tRPCObj.retVal=null;\n";

  fout<<"\t\tArrayList<String> ipList = new ArrayList<String>();\n";
  for (i=0;i<ipList.size();i++)
  {
    fout<<"\t\tipList.add(\""<<ipList[i]<<"\");\n";
  }
  fout<<"\n";



  fout<<"\t\tString serverName = ServerSelector.select(ipList,\"first\");\n";
  fout<<"\t\tint port = 6066;\t\t//What to do if multiple processes on same machine?\n"; 




  fout<<"\n";

  
  fout<<"\t\tclass retTypeClass implements java.io.Serializable\n\t\t{\n";
  fout<<"\t\t\tpublic "<<retType<<" retVal;\n";
  fout<<"\t\t}\n";
  fout<<"\t\tretTypeClass ret = new retTypeClass();\n";

  
  fout<<"\t\ttry\n";	
  fout<<"\t\t{\n";
  fout<<"\t\t\tSocket s = new Socket(serverName,port);\n";
  fout<<"\t\t\tOutputStream os = s.getOutputStream();\n";
  fout<<"\t\t\tObjectOutputStream oos = new ObjectOutputStream(os);\n";
  
  fout<<"\t\t\tInputStream is = s.getInputStream();\n";
  fout<<"\t\t\tObjectInputStream ois = new ObjectInputStream(is);\n";
  fout<<"\n";
  fout<<"\t\t\tString funName = \""<<funName<<"\";\n";
  fout<<"\t\t\toos.writeObject(funName);\n";
  for (i=0;i<parVect.size();i++)
  {
    fout<<"\t\t\toos.writeObject("<<parVect[i].name<<");\n";
  }
  fout<<"\n";
  
  

  fout<<"\t\t\t"<<retType<<" retVal=("<<retType<<")ois.readObject();\n";
  fout<<"\t\t\tret.retVal=retVal;\n";
  for (i=0;i<parVect.size();i++)
  {
    fout<<"\t\t\t"<<parVect[i].name<<"=("<<parVect[i].datatype<<")ois.readObject();\n";
  }
  fout<<"\t\t\tThrowable thro=(Throwable)ois.readObject();\n";
  fout<<"\n";
  

  fout<<"\t\t\toos.close();\n";
  fout<<"\t\t\tos.close();\n";
  fout<<"\t\t\tois.close();\n";  
  fout<<"\t\t\tis.close();\n";
  fout<<"\t\t\ts.close();\n";

  fout<<"\t\t\tif(thro!=null)\n\t\t\t\tthrow thro;\n";
  

  fout<<"\t\t}\n";		
  fout<<"\t\tcatch(Exception e)\n";	
  fout<<"\t\t{\n";
  fout<<"\t\t\tSystem.out.println(e);\n";
  fout<<"\t\t}\n";	

  fout<<"\t\treturn ret.retVal;\n";
  
  fout<<"\t}\n";
  


//work ends here
}


void writeFile(map <int, string> &allReturnTypes, map <int, string> &funcName, map <int, vector <Argument> > &allArguments, map <int, vector <string> > &allLocations, int &funcID)
{
  ofstream fout("RPC.java",ofstream::out);
	// fout<<"package RPC.Client;\n";
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
  fout.close();
}
/*
int main(int argc, char const *argv[])
{
	writeFile();
	return 0;
}
*/

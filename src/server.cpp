#include <iostream>
#include <fstream>
#include <vector>
#include <string>
#include <map>
#include <cstdlib>
#include <cctype>
#include "decl.h"
extern string ip() ;

using namespace std;
using namespace MyNamespace;
void writeFunction1(ofstream &fout, string retType, string funName, vector<Argument>& parVect, vector<string>& ipList)
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
    fout << "\t\ttry{\n" ;
    fout << "\t\t\treturn local." ;
    fout << funName << "(" ;

    for (i=0;i<parVect.size();i++)
    {
      fout <<parVect[i].name;
      if (i < parVect.size() - 1) 
        fout << ", " ;
    }

    fout << ");\n";
	fout << "\t\t}";

	fout << "\t\tcatch(Throwable th){\n" ;
    
    fout << "\t\t\tthrow th;\n";
	fout << "\t\t}";

	fout << "\t}\n\n";
	
//work ends here
}

void writeFunction2(ofstream &fout, string retType, string funName, vector<Argument>& parVect, vector<string>& ipList)
{
  /* code */

//Work starts here
  int i; 
  fout<<"\tpublic static void "<<funName<<"Caller(ObjectInputStream ois, ObjectOutputStream oos)\n\t{\n";


  


  fout<<"\t\ttry\n";  
  fout<<"\t\t{\n";
  if (retType.compare("void")!=0)
  {
    fout<<"\t\t\tclass retTypeClass implements java.io.Serializable\n\t\t\t{\n";
    fout<<"\t\t\t\tpublic "<<retType<<" retVal;\n";
    fout<<"\t\t\t}\n";
    fout<<"\t\t\tretTypeClass ret = new retTypeClass();\n";
  }


  for (i=0;i<parVect.size();i++)
  {
    fout<<"\t\t\t"<<parVect[i].datatype<<" "<<parVect[i].name<<"=("<<parVect[i].datatype<<")ois.readObject();\n";
  }
  fout<<"\n";

  


  if (retType.compare("void")!=0)
    fout<<"\t\t\t"<<retType<<" retVal=ret.retVal;\n";
  fout<<"\t\t\tThrowable thro=null;\n";
  fout<<"\t\t\ttry\n";  
  fout<<"\t\t\t{\n";

  if (retType.compare("void")!=0)
    fout<<"\t\t\t\tretVal = ";
  fout<<funName<<"(";
  for (i=0;i<parVect.size();i++)
  {
    fout<<parVect[i].name;
    if (i < parVect.size() - 1) 
      fout << ", " ;
  }
  fout<<");\n";
  fout<<"\t\t\t}\n";    
  fout<<"\t\t\tcatch(Throwable th)\n"; 
  fout<<"\t\t\t{\n";
  fout<<"\t\t\t\tthro=th;\n";
  fout<<"\t\t\t}\n";  


  if (retType.compare("void")!=0)
    fout<<"\t\t\toos.writeObject(retVal);\n";
  for (i=0;i<parVect.size();i++)
  {
    fout<<"\t\t\toos.writeObject("<<parVect[i].name<<");\n";
  }
  fout<<"\t\t\toos.writeObject(thro);\n";
  fout<<"\n";


  fout<<"\t\t}\n";    
  fout<<"\t\tcatch(Exception e)\n"; 
  fout<<"\t\t{\n";
  fout<<"\t\t\tSystem.out.println(e);\n";
  fout<<"\t\t}\n";  


  fout<<"\t}\n";

//work ends here
}


extern void writeServer(map <int, string> &allReturnTypes, map <int, string> &funcName, map <int, vector <Argument> > &allArguments, map <int, vector <string> > &allLocations, int funcID,string &definitions)
{
  //cout<<"Going to overwrite FunctionClasses/ (All content will be reset and any code you wrote in this directory will be lost.)\nPlease save the code somewhere else if you want to use it later(Press any key to continue)\n";
  //cin.get();

  //system("rm -r FunctionClasses;mkdir FunctionClasses");
  
  for(int i = 1; i < funcID; i++)
  {
    bool flag=false;
    for(int j=0;j<allLocations[i].size();j++)
      if(allLocations[i][j].compare(ip())==0 || allLocations[i][j].compare("localhost")==0)
        flag=true;
    if(!flag)
      continue;

    string className=funcName[i]+"Class";
    className[0]=toupper(className[0]);

    ofstream fout(("gen/server/"+className+".java").c_str(),ofstream::out);
    
    fout<<definitions;
    fout<<"import java.io.*;\n";

    fout<<"public abstract class "+className+"\n{\n";


    writeFunction1(fout, allReturnTypes[i], funcName[i], allArguments[i], allLocations[i]);
    writeFunction2(fout, allReturnTypes[i], funcName[i], allArguments[i], allLocations[i]);


    fout<<"}";
    fout.close();
  }
}
/*
int main(int argc, char const *argv[])
{
	writeFile();
	return 0;
}
*/

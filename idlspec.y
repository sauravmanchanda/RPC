%{
#include <cstdio>
#include <iostream>
#include <string>
#include <cstring>
#include <vector>
#include <fstream>
#include <set>
#include <map>
#include "decl.h"
using namespace std;
using namespace MyNamespace;
int funcID = 1;
// stuff from flex that bison needs to know about:
extern "C" int yylex();
extern "C" int yyparse();
extern "C" FILE *yyin;
extern "C" int linenum;

map <int, vector <Argument> > allArguments;
map <int, vector <string> > allLocations;
map <int, string> funcName;
string definitions; 
void yyerror(const char *s);
%}

%union {
    int ival;
    float fval;
    char *sval;
}

%token IO_IN IO_OUT IO_INOUT INT FLOAT DEF_BEGIN
%token <sval> IDENTIFIER
%token <sval> DEF_END 
%type <sval> type_specifier
%type <ival> io_type

%%
idl:
    definition_section body_section { cout << "done with a IDL file!" << endl; }
    | body_section{ cout << "done with a IDL file!" << endl; }
    ;

definition_section : 
    DEF_BEGIN DEF_END { cout << "Found definitions : "<< endl; definitions = $2; cout << definitions << endl; }

body_section:
    body_lines
    ;
body_lines:
    body_lines body_line {}
    | body_line       {}
    ;

body_line:
    IDENTIFIER '('  ')' '[' locations ']' {funcName[funcID] = $1; funcID ++;}
    | IDENTIFIER '(' arguments ')' '[' locations ']'  {funcName[funcID] = $1; funcID ++;}
    ;

locations:
    location
    | location ',' locations

location :
    IDENTIFIER {allLocations[funcID].push_back($1);}

arguments:
    argument  {}
    | argument ',' arguments  {}
;
argument:
    io_type type_specifier IDENTIFIER  {
                Argument a;
                a. iotype = $1;
                a. datatype = $2;
                a. name = $3;
                //cout << "detected argument " << funcID << " " << a.iotype << " " << a.datatype << " " << a.name << endl;
                
                allArguments[funcID].push_back(a);
            }
;
io_type : 
    IO_IN           {$$ = TYPE_IN;}
    | IO_OUT         {$$ = TYPE_OUT;}
;

type_specifier :
    IDENTIFIER             {$$ = $1; }
;
%%


string lookUpConstantName(int c){
    switch (c) {
        case TYPE_INT : return "int";
        case TYPE_FLOAT : return "float";
        case TYPE_IN : return "in";
        case TYPE_OUT : return "out";

    }
}

void writeFunction(ofstream &fout, string retType, string funName, vector<Argument>& parVect, vector<string>& ipList)
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
	fout<<"\t\tRPCObj.retVal=null;\n";
	fout<<"\t\tRPCObj.funName=\""<<funName<<"\";\n";
	for (i=0;i<parVect.size();i++)
	{
		fout<<"\t\tRPCObj."<<parVect[i].name<<"="<<parVect[i].name<<";\n";
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
void writeFile()
{
	ofstream fout("RPC.java",ofstream::out);
	fout<<"import UDC.*;\n";
	fout<<"import java.net.*;\n";
	fout<<"import java.io.*;\n\n";
	fout<<"public abstract class RPC extends Throwable\n{\n";

        for(int i = 1; i < funcID; i++){
	        string retType("int");
	        if (i > 1) {fout << "\n\n\n" ;}
                writeFunction(fout, retType, funcName[i], allArguments[i], allLocations[i]);
        }

	
	fout<<"}";
	fout.close();
}


int main() {
    // open a file handle to a particular file:
    FILE *myfile = fopen("input.idl", "r");
    // make sure it is valid:
    if (!myfile) {
        cout << "I can't open input.idl!" << endl;
        return -1;
    }
    // set flex to read from it instead of defaulting to STDIN:
    yyin = myfile;

    yyparse();
    writeFile();

}

void yyerror(const char *s) {
    cout << "EEK, parse error!  Message: " << s << " on line " << linenum << endl;
    // might as well halt now:
    exit(-1);
}

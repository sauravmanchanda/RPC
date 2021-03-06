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

extern void writeServer(map <int, string> &allReturnTypes, map <int, string> &funcName, map <int, vector <Argument> > &allArguments, map <int, vector <string> > &allLocations, int funcID,string &definitions);

extern void writeClient(map <int, string> &allReturnTypes, map <int, string> &funcName, map <int, vector <Argument> > &allArguments, map <int, vector <string> > &allLocations, int funcID,string &definitions);


map <int, vector <Argument> > allArguments;
map <int, vector <string> > allLocations;
map <int, string> allReturnTypes;
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

%%
idl:
   definition_section body_section { cout << "done with a IDL file!" << endl; }
    | body_section{ cout << "Done with a IDL file!" << endl; }
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
         type_specifier IDENTIFIER '('  ')' '[' ']' {allReturnTypes[funcID] = $1 ;funcName[funcID] = $2; funcID ++;}
    | type_specifier IDENTIFIER '(' arguments ')' '['  ']'  {allReturnTypes[funcID] = $1; funcName[funcID] = $2; funcID ++;}
    | type_specifier IDENTIFIER '('  ')' '[' locations ']' {allReturnTypes[funcID] = $1 ;funcName[funcID] = $2; funcID ++;}
    | type_specifier IDENTIFIER '(' arguments ')' '[' locations ']'  {allReturnTypes[funcID] = $1; funcName[funcID] = $2; funcID ++;}
    ;

locations:
    | location
    | location ',' locations

location :
         IDENTIFIER {allLocations[funcID].push_back($1);}

arguments:
         argument  {}
    | argument ',' arguments  {}
;
argument:
        type_specifier IDENTIFIER  {
                Argument a;
                a. datatype = $1;
                a. name = $2;
                //cout << "detected argument " << funcID << " " << a.iotype << " " << a.datatype << " " << a.name << endl;

allArguments[funcID].push_back(a);
            }
;


type_specifier :
               IDENTIFIER             {$$ = $1; }
               | IDENTIFIER '<' IDENTIFIER '>' {
                        $$ = (char *)malloc (strlen ($1) + strlen ($3) + 5); 
                        $$[0] = '\0';
                        strcat($$, $1);
                        strcat($$, "<");
                        strcat($$, $3);
                        strcat($$, ">");
                        }
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

/*
void writeFile()
{
  ofstream fout("RPC.java",ofstream::out);
  fout<<"import UDC.*;\n";
  fout<<"import java.net.*;\n";
  fout<<"import java.io.*;\n\n";
  fout<<"public abstract class RPC extends Throwable\n{\n";

for(int i = 1; i < funcID; i++){
          if (i > 1) {fout << "\n\n\n" ;}
                writeFunction(fout, allReturnTypes[i], funcName[i], allArguments[i], allLocations[i]);
        }


fout<<"}";
  fout.close();
}
*/


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

    writeClient(allReturnTypes, funcName, allArguments, allLocations,funcID,definitions);
    writeServer(allReturnTypes, funcName, allArguments, allLocations,funcID,definitions);
}

void yyerror(const char *s) {
    cout << "EEK, parse error!  Message: " << s << " on line " << linenum << endl;
    // might as well halt now:
    exit(-1);
}

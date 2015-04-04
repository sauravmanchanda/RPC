%{
#include <cstdio>
#include <iostream>
#include <string>
#include <cstring>
#include <sstream>
#include "decl.h"
using namespace std;
using namespace MyNamespace;

#define YY_DECL extern "C" int yylex()
extern int linenum = 0;
#include "idlspec.tab.hpp"  // to get the token types that we return
std::stringstream defs;

%}
%option noyywrap
%x DEF_STATE
%%
<INITIAL>[\n]            {linenum++;}
<DEF_STATE>[\n]            {linenum++; defs << "\n";}
[ \t]           ;
in              {return IO_IN;}
out             {return IO_OUT;}
inout           {return IO_INOUT;}
"#def"          {/*cout << "in def state\n";*/ BEGIN(DEF_STATE)  ; return DEF_BEGIN; }
<DEF_STATE>"#enddef"       {/*cout << " back to initial\n";*/ BEGIN(INITIAL); yylval.sval = strdup(defs.str().c_str());  return DEF_END; }
"("         { return('('); }
")"         { return(')'); }
","         { return(','); }
"["         { return('['); }
"]"         { return(']'); }

[a-zA-Z0-9]+      {
    // cout << "Found a string: " << yytext << endl;
    // we have to copy because we can't rely on yytext not changing underneath us:
    yylval.sval = strdup(yytext);
    return IDENTIFIER;
}

<DEF_STATE>. {
   defs.put(*yytext);
}
%%

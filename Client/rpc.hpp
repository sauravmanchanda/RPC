#ifndef _RPC_H
#define _RPC_H


#include "decl.h"


using namespace MyNamespace;

void writeFile(map <int, string> &allReturnTypes, map <int, string> &funcName, map <int, vector <Argument> > &allArguments, map <int, vector <string> > &allLocations, int &funcID);
#endif
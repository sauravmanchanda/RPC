#ifndef _DECL_H
#define _DECL_H
#include <string>
#include <list>
#define TYPE_INT 0
#define TYPE_FLOAT 1
#define TYPE_DOUBLE 2
#define TYPE_SHORT 3
#define TYPE_LONG 4
#define TYPE_CHAR 5
#define TYPE_VOID 6
#define TYPE_IN 100
#define TYPE_OUT 101

using namespace std;
namespace MyNamespace{



struct Argument{
	int iotype;
        string datatype;
	string name;
};


}
#endif

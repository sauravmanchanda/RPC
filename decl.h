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

struct Identifier{
	public:
		int type;
		std::string *name;
		std::list <int> NextList;
		std::list <int> TrueList;
		std::list <int> FalseList;
		int StartLoc;

		Identifier(char *name,int type){
			this -> name = new string(name);
			this -> type = type;
		}
		Identifier(string name,int type){
			this -> name = new string(name);
			this -> type = type;
		}
		Identifier(string *name,int type){
			this -> name = name;
			this -> type = type;
		}
		~Identifier(){
			delete(name);
		}
	};

struct Statement{
	int StartLoc;
	std::list<int> TrueList;
	std::list<int> FalseList;
	std::list<int> NextList;
};

struct Insert{
	int StartLoc;
	int Address;
};

struct Constant{
	int type;
	union {
		int integer;
		float real;		
	}value;
};

struct Argument{
	int iotype, datatype;
	string name;
};


}
#endif

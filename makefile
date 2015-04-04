run : compile
	idlparser
compile : 
	bison -d idlspec.y -o idlspec.tab.cpp
	flex idlspec.l
	g++ idlspec.tab.cpp lex.yy.c -o idlparser
rpc : 
	g++ rpc.cpp -o rpc
	rpc


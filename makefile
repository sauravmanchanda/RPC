run : compile
	idlparser
compile : 
	bison -d idlspec.y -o idlspec.tab.cpp
	flex idlspec.l
	g++ idlspec.tab.cpp lex.yy.c rpc.cpp -o idlparser
rpc : 
	g++ rpc.cpp -o rpc
	rpc
clean :
	rm idlparser.exe rpc.exe idlHeader.hpp idlspec.tab.cpp idlspec.tab.hpp lex.yy.c rpc.class rpc.java

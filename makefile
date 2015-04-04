run : compile
	./idlparser
compile : 
	bison -d -o idlspec.tab.cpp idlspec.y
	flex -o lex.yy.cpp idlspec.l
	g++ idlspec.tab.cpp lex.yy.cpp rpc.cpp -o idlparser
rpc : 
	g++ rpc.cpp -o rpc
	rpc
clean :
	rm idlparser.exe rpc.exe idlHeader.hpp idlspec.tab.cpp idlspec.tab.hpp lex.yy.c rpc.class rpc.java

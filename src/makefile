run : createClientAndServerCode

createClientAndServerCode : compileIDL


compileIDL : compileParser
	idlparser.exe 

compileParser : 
	bison -d -o idlspec.tab.cpp idlspec.y
	flex idlspec.l 
	g++ idlspec.tab.cpp lex.yy.c server.cpp ip_windows.cpp client.cpp -o idlparser
	
#Need to update clean
clean :
	rm idlparser.exe rpc.exe idlHeader.hpp idlspec.tab.cpp idlspec.tab.hpp lex.yy.c rpc.class rpc.java

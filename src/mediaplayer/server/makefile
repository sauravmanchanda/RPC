ifdef SystemRoot
	MF = \*.java
else
	MF = *.java
endif

run: compile
	java -cp . RPCServer

compile:
	javac $(MF)

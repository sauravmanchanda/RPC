ifdef SystemRoot
	MF = \*.java
else
	MF = *.java
endif

run: compile
	java -cp . Test

compile:
	javac $(MF)

Parser.class: Parser.java ICode.class SymbolTable.class Symbol.class
	javac Parser.java

Parser.java: project7.by
	byaccj -J project7.by

ICode.class: ICode.java Symbol.class
	javac ICode.java

SymbolTable.class: SymbolTable.java Symbol.class
	javac SymbolTable.java

Symbol.class: Symbol.java
	javac Symbol.java

clean:
	/bin/rm -f Parser.java ParserVal.java *.class *~

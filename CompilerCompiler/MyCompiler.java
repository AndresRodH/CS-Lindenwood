import java.io.*;

public class MyCompiler
{

    public static void main(String args[]) throws IOException
    {
	MyLexer lexer = new MyLexer(args[0]);
	
	Token t = lexer.nextToken();
	
	while(t!=null)
	    {
		System.out.println(t);
		t = lexer.nextToken();
	    }
    }
}
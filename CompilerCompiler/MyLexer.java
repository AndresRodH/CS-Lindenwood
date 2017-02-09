import java.io.*;

/**
 *MyLexer - provides a nextToken method that returns a token 
 *          based on an underlying DFA 
 * @author S. Blythe
 * @version 8/2016
 */

public class MyLexer
{
    private DFA lexer;         // the underlying DFA
    private BufferedReader br; // scanner from which the characters are read

    private int lineNo;        // keeps track of line number
                               //   might be usefull in debugging later
                               //   to know what line number had a bad token

    // internal method to build up the DFA used for lexical analysis.
    private void buildDFA()
    {
	// digit transitions
	for(int c = (int) '0'; c <= (int) '9'; c++)
	    {
		//integers
		lexer.setTransition(0,1,(char) c);
		lexer.setTransition(1,1,(char) c);

		//id's
		lexer.setTransition(2,2,(char) c);

	    }

	// alphabet transitions
	for(int c= (int) 'a'; c<= (int) 'z'; c++)
	    {
		//id's
		lexer.setTransition(0,2,(char) c);
		lexer.setTransition(2,2,(char) c);
	    }  

	for(int c= (int) 'A'; c<= (int) 'Z'; c++)
	    {
		//id's
		lexer.setTransition(0,2,(char) c);
		lexer.setTransition(2,2,(char) c);
	    }  


	for(int c=0; c<300; c++) // 300 is well beyond typable character limit
	    {
		//strings
		lexer.setTransition(20,20, (char)c);
	    }

	lexer.setTransition(0,3, '&'); // AND

	lexer.setTransition(0,4, '<'); // RELOP (LT)
	lexer.setTransition(4,5, '-'); // ASSIGNOP

	lexer.setTransition(0,6, ','); // COMMA

	lexer.setTransition(0,7, '{'); // CURLL

	lexer.setTransition(0,8, '}'); // CURLR

	lexer.setTransition(0,9, '*'); // MULOP
	lexer.setTransition(0,9, '/'); // MULOP

	lexer.setTransition(0,10, '=');
	lexer.setTransition(10,11,'=');// RELOP (EQ)
	lexer.setTransition(4,11, '=');// RELOP (LTE)
	lexer.setTransition(0,12, '>');// RELOP (GT) 
	lexer.setTransition(12,11,'=');// RELOP (GTE)
	lexer.setTransition(0,13, '!');// NOT
	lexer.setTransition(13,11,'=');// RELOP (NEQ)
      
	lexer.setTransition(0,14, '-');// ADDOP
	lexer.setTransition(0,14, '+');// ADDOP

	lexer.setTransition(0,15, '|');// OR

	lexer.setTransition(0,16, '(');// PARENL
	lexer.setTransition(0,17, ')');// PARENR

	lexer.setTransition(0,18, ';');// SEMICOLON

	lexer.setTransition(0,20, '"');
	lexer.setTransition(20,19,'"'); // STRING

	// mark final states
	lexer.markFinal(1); // integer
	lexer.markFinal(2); // id (might be  keyword)
    }



    // method that skips whitespace within input stream ...
    private boolean skipWhiteSpace() throws IOException
    {
	// no input left? then there's nothing to skip.
	if (!br.hasNext())
	    return false;

	boolean found = false; // we have not found whitespace yet ...

	char ch=br.readChar(); // prime search with first character

	// as long as nex character is whitespace ...
	while(Character.isWhitespace(ch))
	    {
		// update line number as appropriate ...
		if (ch=='\n') 
		    lineNo++;

		found=true; // we've found some whitespace if we got here. 

		// out of input? go back to whomever called us! 
		if (!br.hasNext())
		    return true;

		// get next char
		ch=br.readChar();
	    }

	// we read one non whitespace char, and it needs to be "put back"
	br.ungetChar(ch);

	return found;
    }

    // method that skips a comment within input stream ...
    private boolean skipComments() throws IOException
    {
	// no input left? then there's nothing to skip.
	if (!br.hasNext())
	    return false;

	boolean found=false;    // we have not (yet) found a comment ...

	char ch=br.readChar(); // prime the search with the next character
	
	// if the char indicates a comment
	if(ch=='#')
	    {
		while(ch!='\n') ch=br.readChar(); //read through to end of line
		lineNo++; // one more line seen ...
		return true; //yep, we read another comment
	    }
	else
	    {
		br.ungetChar(ch); // restore character for other consumption
		return false;     // dod not see a comment
	    }
    }


    /**
     * MyLexer constructur based on a file (name)
     *  @param fname the name of the file (including path) to open
     */
    public MyLexer(String fname)
    {
	lineNo=0; // we're on the first line 


	try
	    {
		// build buffered reader .
		br = new BufferedReader(fname);

		// create and build internal DFA
		lexer = new DFA(25, 300);
		buildDFA();
	    }
	catch(Exception e)
	    {
		System.err.println("Unable to properly construct Lexer");
		System.err.println(e);
	    }
    }

    /**
     * nextToken - the core of this class. call this to get the next token
     *             file specified in the constructor.
     */
    public Token nextToken() throws IOException
    {
	// skip white space and/or comments fro as long as they are found
	while( skipWhiteSpace()||skipComments())
	    ;

	// no more input in file? Then there's no token!
	if (!br.hasNext())
	    {
		return null;
	    }

	// start in DFA state 0;
	int currState=0;
	int prevState=currState;

	// actual token text is empty, we'll add characers as we go ...
	String token="";

	// next character in input file. for sfety's sake init to single space
	char ch=' ';

	// as long we're still in a valid DFA state ...
	while (currState!=-1)
	    {
		// get next character and move on to next state based on DFA
		ch = br.readChar();
		prevState=currState;
		currState = lexer.nextState(prevState, ch);

		// as long as character was valid transition, add it to result
		if (currState!=-1)
		    token+=ch;
	    }

	// we did get one extra character, so put it back ...
	br.ungetChar(ch);

	// build Token out of state number and token string (and line number). 
	Token t =new Token(prevState, token, lineNo);

	return t;  // return the token we built. 
    }

}
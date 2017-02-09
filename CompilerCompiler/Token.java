/**
 * Token - basic data type that CSC49001 project 1 was to identify.
 * @author S. Blythe
 * @version 8/2016
 */
public class Token
{
    // Token "types" - these do NOT correspond directly to what bison
    //    generates, so use with care. I gave serious consideration
    //    to making each of these private ...
    public final static int ERROR = 0;

    public final static int NUMBER =1;
    public final static int ID =2;
    public final static int AND =3;
    public final static int RELOP =4;
    public final static int ASSIGNOP =5; 
    public final static int COMMA =6;
    public final static int CURLL =7;
    public final static int CURLR =8;
    public final static int MULOP =9;

    //public final static int RELOP =11;
    //public final static int RELOP =12;
    public final static int NOT =13;
    public final static int ADDOP =14;
    public final static int OR =15;
    public final static int PARENL =16;
    public final static int PARENR =17;
    public final static int SEMICOLON =18;
    public final static int STRING =19;
    //public final static int STRING =20;

    public final static int ELSE = 21;
    public final static int FUNCTION = 22;
    public final static int IF = 23;
    public final static int RETURN = 24;
    public final static int VAR = 25;
    public final static int WHILE = 26;


    // strings corresponding to each of the (numbered) constants above
    private final static String tokenTypeAsString[] =
    {"ERROR","NUMBER","ID","AND","RELOP","ASSIGNOP",
     "COMMA","CURLL","CURLR","MULOP","ERROR",
     "RELOP","RELOP","NOT","ADDOP","OR",
     "PARENL","PARENR","SEMICOLON","STRING","ERROR",
     "ELSE","FUNCTION","IF","RETURN","VAR",
     "WHILE"};

    // constant ints  corresponding to each of the (numbered) constants above
    private final static int tokenTypeAsInt[] =
    {ERROR,NUMBER,ID,AND,RELOP,ASSIGNOP,
     COMMA,CURLL,CURLR,MULOP,ERROR,
     RELOP,RELOP,NOT,ADDOP,OR,
     PARENL,PARENR,SEMICOLON,STRING,ERROR,
     ELSE,FUNCTION,IF,RETURN,VAR,
     WHILE};


    private int type;       // each Token has a type (one the constants above)
    private String value;   // actual STring in the Token
    private int lineNumber; // line number this token was found at

    /**
     * default constructor. Gives useless values for instance variables 
     */
    public Token()
    {
	type=-1;
	value="";
	lineNumber=-1;
    }

    /**
     * useful constructor for token:
     *  @param type specifies type of token
     *  @param value specifies string value of actual token
     *  @param lineNum specifies the line number the token was found at
     */
    public Token(int type, String value, int lineNum)
    {
	// initialize Token in stance variables based on parameters
	this.type=tokenTypeAsInt[type];
	this.value=value;
	this.lineNumber=lineNum;

	// catch - an ID might actually be a keyword. Check for such.
	if (this.type==ID)
	    {
		for(int tt=ELSE; tt<=WHILE; tt++)
		    {
			if (value.equalsIgnoreCase(tokenTypeAsString[tt]) &&
                            value.equals(value.toLowerCase())
			    )
			    {
				this.type=tt;
				break;
			    }
		    }
	    }
    }

    /**
     * equals - utility method for checking token equality
     * @param other the token to compare ourselves to
     */
    public boolean equals(Token other)
    {
	// if we're the same tyoe of token and have same string value ....
	return (type==other.type && value.equals(other.value));
    }

    
    /**
     * equals - utility method for checking token equality based only on type
     * @param other the token to compare ourselves to
     */
    public boolean equalType(Token other)
    {
	return type==other.type;
    }

    /**
     * equals - utility method for checking token equality based only on type
     * @param tokenType the type to compare ourselves to
     */
    public boolean equalType(int tokenType)
    {
	return type==tokenType;
    }

    /**
     * equalValue -utility method for checking token equality based only on
     *             value
     * @param other the token to compare ourselves to
     */
    public boolean equalValue(Token other)
    {
	return value.equals(other.value);
    }

    /**
     * equalValue -utility method for checking token equality based only on
     *             value
     * @param tokenValue the token value to compare ourselves to
     */
    public boolean equalValue(String tokenValue)
    {
	return value.equals(tokenValue);
    }

    // accessor methods for instance variables

    /** 
     * getType gives token type
     * @return the token type
     */
    public int getType(){return type;}


    /** 
     * getVale gives token value
     * @return the token value
     */
    public String getValue(){return value;}

    /** 
     * getLine gives line number
     * @return the line number for the Token
     */    
    public int getLine() {return lineNumber;}

    /** 
     * toString allows JJava to print a Token
     * @return String representation of Token 
     */
    public String toString() {return tokenTypeAsString[type]+"\t\t"+value;}
}
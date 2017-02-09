//### This file created by BYACC 1.8(/Java extension  1.15)
//### Java capabilities added 7 Jan 97, Bob Jamison
//### Updated : 27 Nov 97  -- Bob Jamison, Joe Nieten
//###           01 Jan 98  -- Bob Jamison -- fixed generic semantic constructor
//###           01 Jun 99  -- Bob Jamison -- added Runnable support
//###           06 Aug 00  -- Bob Jamison -- made state variables class-global
//###           03 Jan 01  -- Bob Jamison -- improved flags, tracing
//###           16 May 01  -- Bob Jamison -- added custom stack sizing
//###           04 Mar 02  -- Yuval Oren  -- improved java performance, added options
//###           14 Mar 02  -- Tomas Hurka -- -d support, static initializer workaround
//### Please send bug reports to tom@hukatronic.cz
//### static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";






//#line 2 "project7.by"
import java.lang.Math;
import java.io.*;
import java.util.StringTokenizer;
import java.util.List;
import java.util.LinkedList;
//#line 23 "Parser.java"




public class Parser
{

boolean yydebug;        //do I want debug output?
int yynerrs;            //number of errors so far
int yyerrflag;          //was there an error?
int yychar;             //the current working character

//########## MESSAGES ##########
//###############################################################
// method: debug
//###############################################################
void debug(String msg)
{
  if (yydebug)
    System.out.println(msg);
}

//########## STATE STACK ##########
final static int YYSTACKSIZE = 500;  //maximum stack size
int statestk[] = new int[YYSTACKSIZE]; //state stack
int stateptr;
int stateptrmax;                     //highest index of stackptr
int statemax;                        //state when highest index reached
//###############################################################
// methods: state stack push,pop,drop,peek
//###############################################################
final void state_push(int state)
{
  try {
		stateptr++;
		statestk[stateptr]=state;
	 }
	 catch (ArrayIndexOutOfBoundsException e) {
     int oldsize = statestk.length;
     int newsize = oldsize * 2;
     int[] newstack = new int[newsize];
     System.arraycopy(statestk,0,newstack,0,oldsize);
     statestk = newstack;
     statestk[stateptr]=state;
  }
}
final int state_pop()
{
  return statestk[stateptr--];
}
final void state_drop(int cnt)
{
  stateptr -= cnt; 
}
final int state_peek(int relative)
{
  return statestk[stateptr-relative];
}
//###############################################################
// method: init_stacks : allocate and prepare stacks
//###############################################################
final boolean init_stacks()
{
  stateptr = -1;
  val_init();
  return true;
}
//###############################################################
// method: dump_stacks : show n levels of the stacks
//###############################################################
void dump_stacks(int count)
{
int i;
  System.out.println("=index==state====value=     s:"+stateptr+"  v:"+valptr);
  for (i=0;i<count;i++)
    System.out.println(" "+i+"    "+statestk[i]+"      "+valstk[i]);
  System.out.println("======================");
}


//########## SEMANTIC VALUES ##########
//public class ParserVal is defined in ParserVal.java


String   yytext;//user variable to return contextual strings
ParserVal yyval; //used to return semantic vals from action routines
ParserVal yylval;//the 'lval' (result) I got from yylex()
ParserVal valstk[];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
void val_init()
{
  valstk=new ParserVal[YYSTACKSIZE];
  yyval=new ParserVal();
  yylval=new ParserVal();
  valptr=-1;
}
void val_push(ParserVal val)
{
  if (valptr>=YYSTACKSIZE)
    return;
  valstk[++valptr]=val;
}
ParserVal val_pop()
{
  if (valptr<0)
    return new ParserVal();
  return valstk[valptr--];
}
void val_drop(int cnt)
{
int ptr;
  ptr=valptr-cnt;
  if (ptr<0)
    return;
  valptr = ptr;
}
ParserVal val_peek(int relative)
{
int ptr;
  ptr=valptr-relative;
  if (ptr<0)
    return new ParserVal();
  return valstk[ptr];
}
final ParserVal dup_yyval(ParserVal val)
{
  ParserVal dup = new ParserVal();
  dup.ival = val.ival;
  dup.dval = val.dval;
  dup.sval = val.sval;
  dup.obj = val.obj;
  return dup;
}
//#### end semantic value section ####
public final static short ADDOP=257;
public final static short AND=258;
public final static short ASSIGNOP=259;
public final static short COMMA=260;
public final static short CURLL=261;
public final static short CURLR=262;
public final static short ELSE=263;
public final static short FUNCTION=264;
public final static short ID=265;
public final static short IF=266;
public final static short MULOP=267;
public final static short NOT=268;
public final static short NUMBER=269;
public final static short OR=270;
public final static short PARENL=271;
public final static short PARENR=272;
public final static short RELOP=273;
public final static short SEMICOLON=274;
public final static short STRING=275;
public final static short VAR=276;
public final static short WHILE=277;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    1,    2,    2,    3,    5,    5,    7,    4,
    9,    4,    6,    8,    8,   10,   10,   10,   11,   11,
   11,   11,   11,   12,   16,   16,   17,   17,   18,   18,
   18,   18,   18,   19,   19,   20,   20,   21,   21,   22,
   22,   13,   13,   23,   23,   23,   24,   25,   14,   26,
   28,   15,   27,   27,
};
final static short yylen[] = {                            2,
    1,    2,    1,    1,    1,    3,    3,    1,    0,    6,
    0,    7,    3,    3,    1,    2,    2,    0,    2,    2,
    1,    1,    1,    3,    1,    3,    1,    3,    1,    1,
    3,    2,    1,    1,    3,    1,    3,    1,    2,    3,
    3,    3,    4,    3,    1,    1,    0,    0,    7,    0,
    0,    8,    2,    0,
};
final static short yydefred[] = {                         0,
    0,    0,    0,    1,    0,    4,    5,    0,    0,    0,
    2,    0,    0,    6,    0,    9,    0,    7,    0,    0,
   11,   14,    0,   10,    0,    0,    0,   47,    0,   23,
    0,    0,    0,    0,   21,   22,   12,    0,    0,    0,
    0,   16,   13,   17,   19,   20,    0,    0,   30,    0,
   33,    0,    0,   27,   42,   46,    0,    0,    0,    0,
    0,    0,    0,   36,   38,    0,   32,    0,    0,    0,
    0,   43,   39,    0,    0,    0,    0,    0,    0,    0,
   31,    0,   28,   44,   41,    0,    0,    0,   37,    0,
   51,    0,    0,   49,    0,   52,   53,
};
final static short yydgoto[] = {                          3,
    4,    5,   29,    7,   10,   30,   20,   17,   25,   31,
   32,   33,   51,   35,   36,   61,   53,   54,   62,   63,
   64,   65,   58,   41,   90,   78,   96,   93,
};
final static short yysindex[] = {                      -226,
 -183, -181,    0,    0, -226,    0,    0, -172, -156, -160,
    0, -209, -181,    0, -151,    0, -159,    0, -150, -145,
    0,    0, -188,    0, -145, -161, -154,    0, -188,    0,
 -144, -188, -155, -153,    0,    0,    0, -216, -253, -178,
 -149,    0,    0,    0,    0,    0, -216, -148,    0, -216,
    0, -137, -143,    0,    0,    0, -224, -147, -163, -163,
 -249, -142, -132,    0,    0, -178,    0, -225, -216, -216,
 -240,    0,    0, -252, -141, -216, -178, -140, -178, -142,
    0, -143,    0,    0,    0, -137, -132, -185,    0, -139,
    0, -185, -136,    0, -185,    0,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,  129,    0,    0,    0, -138,    0,
    0,    0,    0,    0, -135,    0,    0,    0,    0,    0,
    0,    0, -128,    0,    0,    0,    0,    0, -128,    0,
    0, -128,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0, -230,    0,    0,
    0, -134, -212,    0,    0,    0, -133,    0,    0,    0,
    0, -131, -213,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0, -130,
    0, -206,    0,    0,    0, -175, -165,    0,    0,    0,
    0,    0, -191,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
  125,    0,   10,    0,  122,   76,    0,  119,    0,   71,
  -81,    0,  -23,    0,    0,  -37,   74,  -44,   78,   68,
   67,   52,   77,    0,    0,    0,    0,    0,
};
final static int YYTABLESIZE=148;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         34,
   52,   57,   67,   47,   69,   34,   91,   69,   34,    6,
   94,   48,   68,   97,    6,   49,   47,   50,   55,   81,
   76,   56,   74,   76,   48,   83,   29,   29,   49,   29,
   50,   69,   69,   57,   56,   71,   29,    1,   86,   29,
   47,   29,   29,   29,   25,   25,   81,   25,   48,    2,
   26,   26,   49,   26,   50,   15,   34,   25,   34,   25,
   25,   25,   16,   26,   34,   26,   26,   26,   34,   54,
   54,   34,   23,   54,   54,   23,   26,   27,   47,   26,
   27,    8,   40,    9,   54,   54,   48,    2,   28,   59,
   49,   28,   60,   47,   40,   24,   40,   38,   12,   42,
   37,   48,   44,   13,   35,   49,   35,   60,   19,   39,
   73,   75,   21,   14,   15,   23,   40,   43,   45,   69,
   46,   66,   39,   70,   72,   79,   95,   77,    3,   11,
   85,   88,   92,   18,   18,    8,   15,   22,   45,   24,
   50,   48,   82,   80,   87,   89,    0,   84,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         23,
   38,   39,   47,  257,  257,   29,   88,  257,   32,    0,
   92,  265,   50,   95,    5,  269,  257,  271,  272,  272,
  273,  275,   60,  273,  265,   70,  257,  258,  269,  260,
  271,  257,  257,   71,  275,  260,  267,  264,   76,  270,
  257,  272,  273,  274,  257,  258,  272,  260,  265,  276,
  257,  258,  269,  260,  271,  265,  270,  270,  272,  272,
  273,  274,  272,  270,   88,  272,  273,  274,   92,  261,
  262,   95,  261,  265,  266,  261,  265,  266,  257,  265,
  266,  265,  258,  265,  276,  277,  265,  276,  277,  268,
  269,  277,  271,  257,  270,   20,  272,  259,  271,   29,
   25,  265,   32,  260,  270,  269,  272,  271,  260,  271,
   59,   60,  272,  274,  265,  261,  271,  262,  274,  257,
  274,  271,  271,  267,  272,  258,  263,  270,    0,    5,
  272,  272,  272,  262,   13,  274,  272,   19,  272,  274,
  272,  272,   69,   66,   77,   79,   -1,   71,
};
}
final static short YYFINAL=3;
final static short YYMAXTOKEN=277;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,"ADDOP","AND","ASSIGNOP","COMMA","CURLL","CURLR","ELSE",
"FUNCTION","ID","IF","MULOP","NOT","NUMBER","OR","PARENL","PARENR","RELOP",
"SEMICOLON","STRING","VAR","WHILE",
};
final static String yyrule[] = {
"$accept : start",
"start : pgm",
"pgm : pgmpart pgm",
"pgm : pgmpart",
"pgmpart : vardecl",
"pgmpart : function",
"vardecl : VAR varlist SEMICOLON",
"varlist : ID COMMA varlist",
"varlist : ID",
"$$1 :",
"function : FUNCTION ID PARENL PARENR $$1 body",
"$$2 :",
"function : FUNCTION ID PARENL fplist PARENR $$2 body",
"body : CURLL bodylist CURLR",
"fplist : ID COMMA fplist",
"fplist : ID",
"bodylist : vardecl bodylist",
"bodylist : stmt bodylist",
"bodylist :",
"stmt : assign SEMICOLON",
"stmt : fcall SEMICOLON",
"stmt : while",
"stmt : if",
"stmt : body",
"assign : ID ASSIGNOP expr",
"expr : factor",
"expr : expr ADDOP factor",
"factor : term",
"factor : factor MULOP term",
"term : ID",
"term : NUMBER",
"term : PARENL expr PARENR",
"term : ADDOP term",
"term : fcall",
"bexpr : bfactor",
"bexpr : bexpr OR bfactor",
"bfactor : bneg",
"bfactor : bfactor AND bneg",
"bneg : bterm",
"bneg : NOT bterm",
"bterm : expr RELOP expr",
"bterm : PARENL bterm PARENR",
"fcall : ID PARENL PARENR",
"fcall : ID PARENL aplist PARENR",
"aplist : expr COMMA aplist",
"aplist : expr",
"aplist : STRING",
"$$3 :",
"$$4 :",
"while : WHILE $$3 PARENL bexpr $$4 PARENR stmt",
"$$5 :",
"$$6 :",
"if : IF PARENL bexpr $$5 PARENR stmt $$6 elsepart",
"elsepart : ELSE stmt",
"elsepart :",
};

//#line 390 "project7.by"

    public LinkedList<String> whileLabels = new LinkedList<String>();
    public LinkedList<String> ifLabels = new LinkedList<String>();

    public static SymbolTable globals = new SymbolTable("__GLOBALS__");
    public SymbolTable currTable;

    public static LinkedList<SymbolTable> allTables = new LinkedList<SymbolTable>();

    public int stmtCount;
    public int funcCount;

    public static String left;
    public static int paramSize;
    public static String flip;

    private MyLexer yylexer;

private Token t;

public void yyerror(String s)
{
    System.out.println("error found near line:"+t.getLine());
    System.out.println(" token:"+t.getValue());
    System.out.println(s);
}



public int yylex()
{
    try
	{
	    t = yylexer.nextToken();
	}
    catch (Exception e)
	{
	    System.err.println("yylex unable to aquire token");
	    return -1;
	}

    if (t==null)
	return -1;

    String tVal = t.getValue();
    switch(t.getType())
	{
	case Token.NUMBER:
	    yylval = new ParserVal(Integer.parseInt(tVal));
	    return NUMBER;
	case Token.ADDOP:
	    yylval = new ParserVal(tVal);
	    return ADDOP;
	case Token.MULOP:
	    yylval = new ParserVal(tVal);
	    return MULOP;
	case Token.RELOP:
	    yylval = new ParserVal(tVal);
	    return RELOP;
	case Token.ID:
	    yylval = new ParserVal(tVal);
	    return ID;
	case Token.PARENL:
	    return PARENL;
	case Token.PARENR:
	    return PARENR;
	case Token.COMMA:
	    return COMMA;
	case Token.ASSIGNOP:
	    return ASSIGNOP;
	case Token.SEMICOLON:
	    return SEMICOLON;
	case Token.IF:
	    return IF;
	case Token.WHILE:
	    return WHILE;
	case Token.ELSE:
	    return ELSE;
	case Token.CURLL:
	    return CURLL;
	case Token.CURLR:
	    return CURLR;
	case Token.VAR:
	    return VAR;
	case Token.FUNCTION:
	    return FUNCTION;
	case Token.OR:
	    return OR;
	case Token.AND:
	    return AND;
	case Token.NOT:
	    return NOT;
	case Token.STRING:
	    yylval = new ParserVal(tVal);
	    return STRING;
	default:
	    return -1;
	}
}

public void setup(String fname)
{
    yylexer = new MyLexer(fname);
    stmtCount=0;
    funcCount=0;
}


public static void main(String args[])
{
 Parser par = new Parser(false);
 par.setup(args[0]);
 par.yyparse();

 String currST = "";

 for(ICode c : ICode.stmtList)
 {
	 System.out.print("#"); c.print();

     // get the opcode
     String opCode = c.getOpCode();

     if (opCode.equals("PROCSTART"))  // opcode is PROCSTART
     {
        // get label for this symbol table and print it
        currST = c.getLabel();
        c.clearOPC();
        c.print();

        // set pointers
        new ICode("pushq", "%rbp").print();
        new ICode("movq", "%rsp, %rbp").print();
        // find the table in which we are working
        for (SymbolTable st : allTables)
        {
            if (st.getName().equals(currST))  // table found
            {
                int size = st.getSize()*4;
                // allocate space for local variables
                //System.out.println(st);

                LinkedList<Symbol> syms = st.getSymbols();

                int count = 1;

                for (Symbol s : syms)
                {
                    if (s.getType().equals("param"))
                    {
                        if (count == 1)
                        {
                            String paramOff = Integer.toString(s.getOffset()*(-1))+"(%rbp)";
                            new ICode("movl", "%eax", paramOff).print();
                            count++;
                        }
                        else if (count == 2)
                        {
                            String paramOff = Integer.toString(s.getOffset()*(-1))+"(%rbp)";
                            new ICode("movl", "%ebx", paramOff).print();
                            count++;
                        }
                        else if (count == 3)
                        {
                            String paramOff = Integer.toString(s.getOffset()*(-1))+"(%rbp)";
                            new ICode("movl", "%ecx", paramOff).print();
                            count++;
                        }
                    }
                }
                new ICode("subq", "$"+size, "%rsp").print();
            }
        }
     }
     else if (opCode.equals("SUB"))  // opcode is SUB
     {
        // get all of the parameters
        List<String> opList = c.getOperands();

        String p1 = opList.get(0);
        String p2 = opList.get(1);
        String result = opList.get(2);
        
        // find the table in which we are working
        for (SymbolTable st : allTables)
        {
            if (st.getName().equals(currST))  // table found
            {
                // intialize offset
                int offset = 0;

                try
                {
                    // get offset of the operand
                    offset = st.find(p1).getOffset()*(-1);
                    p1 = Integer.toString(offset)+"(%rbp)";
                }
                catch (Exception e)
                {
                    // value is a NUMBER
                    p1 = "$"+p1;
                }

                // assign operand
                new ICode("movl", p1, "%eax").print();

                try
                {
                    // get offset of the operand
                    offset = st.find(p2).getOffset()*(-1);
                    p2 = Integer.toString(offset)+"(%rbp)";
                }
                catch (Exception e)
                {
                    // value is a NUMBER
                    p2 = "$"+p2;
                }

                // add operands
                new ICode("movl", p2, "%ebx").print();
                
                // add to result
                offset = st.find(result).getOffset()*(-1);
                result = Integer.toString(offset)+"(%rbp)";
                new ICode("subl", "%ebx", "%eax").print();
                new ICode("movl", "%eax", result).print();
            }
        }
     }
     else if (opCode.equals("ADD"))  // opcode is ADD
     {
        // get the operands involved in the addition
        List<String> opList = c.getOperands();

        String p1 = opList.get(0);
        String p2 = opList.get(1);
        String result = opList.get(2);

        // find the table in which we are working
        for (SymbolTable st : allTables)
        {
            if (st.getName().equals(currST))  // table found
            {
                // intialize offset
                int offset = 0;

                try
                {
                    // get offset of the operand
                    offset = st.find(p1).getOffset()*(-1);
                    p1 = Integer.toString(offset)+"(%rbp)";
                }
                catch (Exception e)
                {
                    // value is a NUMBER
                    p1 = "$"+p1;
                }

                // assign operand
                new ICode("movl", p1, "%eax").print();

                try
                {
                    // get offset of the operand
                    offset = st.find(p2).getOffset()*(-1);
                    p2 = Integer.toString(offset)+"(%rbp)";
                }
                catch (Exception e)
                {
                    // value is a NUMBER
                    p2 = "$"+p2;
                }

                // add operands
                new ICode("addl", p2, "%eax").print();
                
                // add to result
                offset = st.find(result).getOffset()*(-1);
                result = Integer.toString(offset)+"(%rbp)";
                new ICode("movl", "%eax", result).print();
            }
        }

     }
     else if (opCode.equals("PARAM"))  // opcode is PARAM
     {
        // get all (if any) of the parameters
        List<String> opList = c.getOperands();
        
        String param = opList.get(0);
        String reg = "";
        // find the table in which we are working
        for (SymbolTable st : allTables)
        {
            if (st.getName().equals(currST))  // table found
            {
                // intialize offset
                int offset = 0;

                try
                {
                    // get offset of the "value"
                    offset = st.find(param).getOffset()*(-1);
                    param = Integer.toString(offset)+"(%rbp)";
                }
                catch (Exception e)
                {
                    // value is a NUMBER
                    param = "$"+param;
                }

                if (paramSize == 0)
                    reg = "%eax";
                else if (paramSize == 1)
                    reg = "%ebx";
                else if (paramSize == 2)
                    reg = "%ecx";

                paramSize++;
                new ICode("movl", param, reg).print();
            }
        }
     }
     else if (opCode.equals("CALL"))  // opcode is CALL
     {
        // get name of the function
        List<String> opList = c.getOperands();

        new ICode("call", opList.get(0)).print();

        paramSize = 0;
     }
     else if (opCode.equals("STRET"))  // opcode is STRET
     {
        // get whatever is in STRET
        String thing = c.getOperands().get(0);

        // find the table in which we are working
        for (SymbolTable st : allTables)
        {
            if (st.getName().equals(currST))  // table found
            {
                // get offset
                int offset = st.find(thing).getOffset()*(-1);
                // print instruction
                new ICode("movl", "%eax", Integer.toString(offset)+"(%rbp)").print();
            }
        }
     }
     else if (opCode.equals("MOV"))  // opcode is MOV
     {
        // get all of the parameters
        List<String> opList = c.getOperands();

        String value = opList.get(0);
        String variable = opList.get(1);

        // find the table in which we are working
        for (SymbolTable st : allTables)
        {
            if (st.getName().equals(currST))  // table found
            {
                // intialize offset
                int offset;

                try
                {
                    // get offset of the "value"
                    offset = st.find(value).getOffset()*(-1);
                    // print instruction
                    new ICode("movl", Integer.toString(offset)+"(%rbp)", "%eax").print();
                }
                catch (Exception e)
                {
                    // value is a NUMBER
                    new ICode("movl", "$" + value, "%eax").print();
                }

                // get offset of the "variable"
                offset = st.find(variable).getOffset()*(-1);
                // print instruction
                new ICode("movl", "%eax", Integer.toString(offset)+"(%rbp)").print();
            }
        }

     }
     else if (opCode.equals("LT") || opCode.equals("LTE") || opCode.equals("EQL"))  // opcode is LT
     {
        // get all of the parameters
        List<String> opList = c.getOperands();
        
        if (opCode.equals("LT"))
            flip = "jl";
        else if (opCode.equals("LTE"))
            flip = "jle";
        else if (opCode.equals("EQL"))
            flip = "je";

        String left = opList.get(0);
        String right = opList.get(1);
        String result = opList.get(2);

        // find the table in which we are working
        for (SymbolTable st : allTables)
        {
            if (st.getName().equals(currST))  // table found
            {
                // intialize dummy offset
                int offset = 0;

                try
                {
                    // get offset of the leftmost part
                    offset = st.find(left).getOffset()*(-1);
                    left = Integer.toString(offset)+"(%rbp)";
                }
                catch (Exception e)
                {
                    // left part of the expresion is a NUMBER
                    left = "$"+left;
                }

                // print code for left
                new ICode("movl", left, "%eax").print();

                try
                {
                    // get offset of the rightmost part
                    offset = st.find(right).getOffset()*(-1);
                    right = Integer.toString(offset)+"(%rbp)";
                }
                catch (Exception e)
                {
                    // right part of the expresion is a NUMBER
                    right = "$"+right;
                }

                // print code for right
                new ICode("movl", right, "%ebx").print();

                try
                {
                    // get offset of the result part
                    offset = st.find(result).getOffset()*(-1);
                    result = Integer.toString(offset)+"(%rbp)";
                }
                catch (Exception e)
                {
                    // left part of the expresion is a NUMBER
                    result = "$"+result;
                }

                new ICode("movl", "$1", "%edx").print();
                new ICode("cmp", "%ebx", "%eax").print();
                String lbl = ICode.genLabel();
                new ICode(flip, lbl).print();
                new ICode("movl", "$0", "%edx").print();

                // print subroutine label
                ICode ltrue = new ICode("NOP");
                ltrue.addLabel(lbl);
                ltrue.clearOPC();
                ltrue.print();

                new ICode("movl", "%edx", result).print();
            }
        }
     }
     else if (opCode.equals("CMP"))  // opcode is CMP
     {
        // get all of the parameters
        List<String> opList = c.getOperands();

        String left = opList.get(0);
        String right = opList.get(1);

        // find the table in which we are working
        for (SymbolTable st : allTables)
        {
            if (st.getName().equals(currST))  // table found
            {
                // intialize dummy offset
                int offset = 0;

                try
                {
                    // get offset of the leftmost part
                    offset = st.find(left).getOffset()*(-1);
                    left = Integer.toString(offset)+"(%rbp)";
                }
                catch (Exception e)
                {
                    // left part of the expresion is a NUMBER
                    left = "$"+left;
                }

                try
                {
                    // get offset of the rightmost part
                    offset = st.find(right).getOffset()*(-1);
                    right = Integer.toString(offset)+"(%rbp)";
                }
                catch (Exception e)
                {
                    // right part of the expresion is a NUMBER
                    right = "$"+right;
                }
            }
        }

        new ICode("cmpl", right, left).print();
     }
     else if (opCode.equals("BE"))  // opcode is BE
     {
        // gets the jump
        String jump = c.getOperands().get(0);

        new ICode("je", jump).print();
     }
     else if (opCode.equals("BA"))  // opcode is BA
     {
        // gets the jump
        String jump = c.getOperands().get(0);

        new ICode("jmp", jump).print();
     }
     else if (opCode.equals("NEG"))  // opcode is BA
     {
        // gets the parameter to be negated
        String op1 = c.getOperands().get(0);
        String op2 = c.getOperands().get(1);

        // find the table in which we are working
        for (SymbolTable st : allTables)
        {
            if (st.getName().equals(currST))  // table found
            {
                // intialize dummy offset
                int offset = 0;

                try
                {
                    // get offset of the leftmost part
                    offset = st.find(op1).getOffset()*(-1);
                    op1 = Integer.toString(offset)+"(%rbp)";
                }
                catch (Exception e)
                {
                    // op1 is a NUMBER
                    op1 = "$"+op1;
                }

                // print code for neg
                new ICode("movl", op1, "%eax").print();
                new ICode("neg", "%eax").print();

                try
                {
                    // get offset of the leftmost part
                    offset = st.find(op2).getOffset()*(-1);
                    op2 = Integer.toString(offset)+"(%rbp)";
                }
                catch (Exception e)
                {
                    // op2
                    op2 = "$"+op2;
                }
                
                new ICode("movl", "%eax", op2).print();
            }
        }
     }
     else if (opCode.equals("MUL"))  // opcode is MUL
     {
        // get all of the parameters
        List<String> opList = c.getOperands();

        String p1 = opList.get(0);
        String p2 = opList.get(1);
        String result = opList.get(2);
        
        // find the table in which we are working
        for (SymbolTable st : allTables)
        {
            if (st.getName().equals(currST))  // table found
            {
                // intialize offset
                int offset = 0;

                try
                {
                    // get offset of the operand
                    offset = st.find(p1).getOffset()*(-1);
                    p1 = Integer.toString(offset)+"(%rbp)";
                }
                catch (Exception e)
                {
                    // value is a NUMBER
                    p1 = "$"+p1;
                }

                // assign operand
                new ICode("movl", p1, "%eax").print();

                try
                {
                    // get offset of the operand
                    offset = st.find(p2).getOffset()*(-1);
                    p2 = Integer.toString(offset)+"(%rbp)";
                }
                catch (Exception e)
                {
                    // value is a NUMBER
                    p2 = "$"+p2;
                }

                // add operands
                new ICode("movl", p2, "%ebx").print();
                
                // add to result
                offset = st.find(result).getOffset()*(-1);
                result = Integer.toString(offset)+"(%rbp)";
                new ICode("imul", "%ebx").print();
                new ICode("movl", "%eax", result).print();
            }
        }
     }
     else if (opCode.equals("DIV"))  // opcode is DIV
     {

        // get all of the parameters
        List<String> opList = c.getOperands();

        String p1 = opList.get(0);
        String p2 = opList.get(1);
        String result = opList.get(2);
        
        // find the table in which we are working
        for (SymbolTable st : allTables)
        {
            if (st.getName().equals(currST))  // table found
            {
                // intialize offset
                int offset = 0;

                try
                {
                    // get offset of the operand
                    offset = st.find(p1).getOffset()*(-1);
                    p1 = Integer.toString(offset)+"(%rbp)";
                }
                catch (Exception e)
                {
                    // value is a NUMBER
                    p1 = "$"+p1;
                }

                // assign operand
                new ICode("movl", p1, "%eax").print();

                try
                {
                    // get offset of the operand
                    offset = st.find(p2).getOffset()*(-1);
                    p2 = Integer.toString(offset)+"(%rbp)";
                }
                catch (Exception e)
                {
                    // value is a NUMBER
                    p2 = "$"+p2;
                }

                // add operands
                new ICode("movl", p2, "%ebx").print();
                
                // clear registers (?)
                new ICode("cltd").print();
                // add to result
                offset = st.find(result).getOffset()*(-1);
                result = Integer.toString(offset)+"(%rbp)";
                new ICode("idivl", "%ebx").print();
                new ICode("movl", "%eax", result).print();
            }
        }
     }
     else if (opCode.equals("NOP"))  // opcode is NOP
     {
        // print subroutine label
        c.clearOPC();
        c.print();
     }
     else if (opCode.equals("RET"))  // opcode is RET
     {
        new ICode("leave").print();
        new ICode("ret").print();
     }
 }
}
//#line 999 "Parser.java"
//###############################################################
// method: yylexdebug : check lexer state
//###############################################################
void yylexdebug(int state,int ch)
{
String s=null;
  if (ch < 0) ch=0;
  if (ch <= YYMAXTOKEN) //check index bounds
     s = yyname[ch];    //now get it
  if (s==null)
    s = "illegal-symbol";
  debug("state "+state+", reading "+ch+" ("+s+")");
}





//The following are now global, to aid in error reporting
int yyn;       //next next thing to do
int yym;       //
int yystate;   //current parsing state from state table
String yys;    //current token string


//###############################################################
// method: yyparse : parse input and execute indicated items
//###############################################################
int yyparse()
{
boolean doaction;
  init_stacks();
  yynerrs = 0;
  yyerrflag = 0;
  yychar = -1;          //impossible char forces a read
  yystate=0;            //initial state
  state_push(yystate);  //save it
  val_push(yylval);     //save empty value
  while (true) //until parsing is done, either correctly, or w/error
    {
    doaction=true;
    if (yydebug) debug("loop"); 
    //#### NEXT ACTION (from reduction table)
    for (yyn=yydefred[yystate];yyn==0;yyn=yydefred[yystate])
      {
      if (yydebug) debug("yyn:"+yyn+"  state:"+yystate+"  yychar:"+yychar);
      if (yychar < 0)      //we want a char?
        {
        yychar = yylex();  //get next token
        if (yydebug) debug(" next yychar:"+yychar);
        //#### ERROR CHECK ####
        if (yychar < 0)    //it it didn't work/error
          {
          yychar = 0;      //change it to default string (no -1!)
          if (yydebug)
            yylexdebug(yystate,yychar);
          }
        }//yychar<0
      yyn = yysindex[yystate];  //get amount to shift by (shift index)
      if ((yyn != 0) && (yyn += yychar) >= 0 &&
          yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
        {
        if (yydebug)
          debug("state "+yystate+", shifting to state "+yytable[yyn]);
        //#### NEXT STATE ####
        yystate = yytable[yyn];//we are in a new state
        state_push(yystate);   //save it
        val_push(yylval);      //push our lval as the input for next rule
        yychar = -1;           //since we have 'eaten' a token, say we need another
        if (yyerrflag > 0)     //have we recovered an error?
           --yyerrflag;        //give ourselves credit
        doaction=false;        //but don't process yet
        break;   //quit the yyn=0 loop
        }

    yyn = yyrindex[yystate];  //reduce
    if ((yyn !=0 ) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
      {   //we reduced!
      if (yydebug) debug("reduce");
      yyn = yytable[yyn];
      doaction=true; //get ready to execute
      break;         //drop down to actions
      }
    else //ERROR RECOVERY
      {
      if (yyerrflag==0)
        {
        yyerror("syntax error");
        yynerrs++;
        }
      if (yyerrflag < 3) //low error count?
        {
        yyerrflag = 3;
        while (true)   //do until break
          {
          if (stateptr<0)   //check for under & overflow here
            {
            yyerror("stack underflow. aborting...");  //note lower case 's'
            return 1;
            }
          yyn = yysindex[state_peek(0)];
          if ((yyn != 0) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
            if (yydebug)
              debug("state "+state_peek(0)+", error recovery shifting to state "+yytable[yyn]+" ");
            yystate = yytable[yyn];
            state_push(yystate);
            val_push(yylval);
            doaction=false;
            break;
            }
          else
            {
            if (yydebug)
              debug("error recovery discarding state "+state_peek(0)+" ");
            if (stateptr<0)   //check for under & overflow here
              {
              yyerror("Stack underflow. aborting...");  //capital 'S'
              return 1;
              }
            state_pop();
            val_pop();
            }
          }
        }
      else            //discard this token
        {
        if (yychar == 0)
          return 1; //yyabort
        if (yydebug)
          {
          yys = null;
          if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
          if (yys == null) yys = "illegal-symbol";
          debug("state "+yystate+", error recovery discards token "+yychar+" ("+yys+")");
          }
        yychar = -1;  //read another
        }
      }//end error recovery
    }//yyn=0 loop
    if (!doaction)   //any reason not to proceed?
      continue;      //skip action
    yym = yylen[yyn];          //get count of terminals on rhs
    if (yydebug)
      debug("state "+yystate+", reducing "+yym+" by rule "+yyn+" ("+yyrule[yyn]+")");
    if (yym>0)                 //if count of rhs not 'nil'
      yyval = val_peek(yym-1); //get current semantic value
    yyval = dup_yyval(yyval); //duplicate yyval if ParserVal is used as semantic value
    switch(yyn)
      {
//########## USER-SUPPLIED ACTIONS ##########
case 1:
//#line 39 "project7.by"
{
    allTables.push(globals);
}
break;
case 4:
//#line 49 "project7.by"
{
    LinkedList<String> vars = (LinkedList<String>) val_peek(0).obj;
    for(String v:vars)
	{
	    globals.add(new Symbol(v, "int"));
	}
}
break;
case 5:
//#line 56 "project7.by"
{funcCount++;}
break;
case 6:
//#line 61 "project7.by"
{
    yyval.obj = val_peek(1).obj;
    /* code to add each var to the symbol table */
}
break;
case 7:
//#line 68 "project7.by"
{
    LinkedList<String> vl = (LinkedList<String>) val_peek(0).obj;
    vl.add(val_peek(2).sval);
    yyval.obj=vl;
}
break;
case 8:
//#line 74 "project7.by"
{
    LinkedList<String> vl = new LinkedList<String>();
    vl.add(val_peek(0).sval);
    yyval.obj = vl;
}
break;
case 9:
//#line 83 "project7.by"
{
    ICode funcStart = new ICode("PROCSTART");
    funcStart.addLabel(val_peek(2).sval);
    funcStart.emit();

    globals.add(new Symbol(val_peek(2).sval, "function"));
    currTable = new SymbolTable(val_peek(2).sval);
    currTable.add(new Symbol(val_peek(2).sval, "retval"));
}
break;
case 10:
//#line 93 "project7.by"
{
    new ICode("RET").emit();
    allTables.addLast(currTable);
}
break;
case 11:
//#line 98 "project7.by"
{
    ICode funcStart = new ICode("PROCSTART");
    funcStart.addLabel(val_peek(3).sval);
    funcStart.emit();

    globals.add(new Symbol(val_peek(3).sval, "function"));
    currTable = new SymbolTable(val_peek(3).sval);
    LinkedList<String> fpl = (LinkedList<String>) val_peek(1).obj; 
    for(String p:fpl)
	currTable.add(new Symbol(p,"param"));
    currTable.add(new Symbol(val_peek(3).sval, "retval"));
}
break;
case 12:
//#line 111 "project7.by"
{
    new ICode("RET").emit();
    allTables.addLast(currTable);

}
break;
case 14:
//#line 123 "project7.by"
{
    LinkedList<String> fpl = (LinkedList<String>) val_peek(0).obj;
    fpl.addFirst(val_peek(2).sval);
    yyval.obj=fpl;
}
break;
case 15:
//#line 129 "project7.by"
{
    LinkedList<String> ls = new LinkedList<String>();
    yyval.obj = ls;
    ls.add(val_peek(0).sval);
}
break;
case 16:
//#line 138 "project7.by"
{
    LinkedList<String> vl = (LinkedList<String>) val_peek(1).obj;
    for(String v:vl)
	{
	    currTable.add(new Symbol(v, "int"));
	}

}
break;
case 19:
//#line 151 "project7.by"
{stmtCount++;}
break;
case 20:
//#line 152 "project7.by"
{stmtCount++;}
break;
case 21:
//#line 153 "project7.by"
{stmtCount++;}
break;
case 22:
//#line 154 "project7.by"
{stmtCount++;}
break;
case 24:
//#line 159 "project7.by"
{
    new ICode("MOV", val_peek(0).sval, val_peek(2).sval).emit();   
}
break;
case 25:
//#line 164 "project7.by"
{yyval = val_peek(0);}
break;
case 26:
//#line 166 "project7.by"
{
    String res = ICode.genTemp();
    currTable.add(res, "int");
    String op;
    if (val_peek(1).sval.equals("+"))
	op = "ADD";
    else
	op = "SUB";

    new ICode(op, val_peek(2).sval, val_peek(0).sval, res).emit();
    yyval.sval = res;
}
break;
case 27:
//#line 181 "project7.by"
{ yyval = val_peek(0); }
break;
case 28:
//#line 183 "project7.by"
{
    String res = ICode.genTemp();
    currTable.add(res,"int");
    String op;
    if (val_peek(1).sval.equals("*"))
	op = "MUL";
    else
	op = "DIV";

    new ICode(op, val_peek(2).sval, val_peek(0).sval, res).emit();
    yyval.sval = res;
}
break;
case 29:
//#line 197 "project7.by"
{ yyval.sval = val_peek(0).sval; }
break;
case 30:
//#line 198 "project7.by"
{ yyval.sval = ""+val_peek(0).ival; }
break;
case 31:
//#line 199 "project7.by"
{ yyval.sval = val_peek(1).sval; }
break;
case 32:
//#line 200 "project7.by"
{ if (val_peek(1).sval.equals("+"))
		      yyval.sval = val_peek(0).sval; 
	            else /* "-"*/
		    {
			String res = ICode.genTemp();
			currTable.add(res, "int");
			new ICode("NEG", val_peek(0).sval, res ).emit();
			yyval.sval = res;			
		    }
		  }
break;
case 33:
//#line 210 "project7.by"
{ yyval =val_peek(0); }
break;
case 34:
//#line 213 "project7.by"
{yyval.sval=val_peek(0).sval;}
break;
case 35:
//#line 215 "project7.by"
{
    String res = ICode.genTemp();
    currTable.add(res,"int");
    new ICode("OR", val_peek(2).sval, val_peek(0).sval, res ).emit();
    yyval.sval = res;
}
break;
case 36:
//#line 223 "project7.by"
{yyval=val_peek(0);}
break;
case 37:
//#line 225 "project7.by"
{
    String res = ICode.genTemp();
    currTable.add(res,"int");
    new ICode("AND", val_peek(2).sval, val_peek(0).sval, res ).emit();
    yyval.sval = res;
}
break;
case 38:
//#line 233 "project7.by"
{yyval=val_peek(0);}
break;
case 39:
//#line 235 "project7.by"
{
    String res = ICode.genTemp();
    currTable.add(res,"int");
    new ICode("NOT", val_peek(0).sval, res ).emit();
    yyval.sval=res;
}
break;
case 40:
//#line 244 "project7.by"
{
    String op;
    if (val_peek(1).sval.equals("=="))
	op = "EQL";
    else if (val_peek(1).sval.equals("<"))
	op = "LT";
    else if (val_peek(1).sval.equals("<="))
	op = "LTE";
    else if (val_peek(1).sval.equals(">"))
	op = "GT";
    else if (val_peek(1).sval.equals(">="))
	op = "GTE";
    else /*if ($2.sval.equals("!="))*/
	op = "NEQ";

    String res = ICode.genTemp();
    currTable.add(res,"int");
    new ICode(op, val_peek(2).sval, val_peek(0).sval, res ).emit();
    yyval.sval=res;
    /*    System.out.println("done bterm, res="+res+"; $$.sval="+$$.sval);*/
    
}
break;
case 41:
//#line 266 "project7.by"
{yyval.sval=val_peek(1).sval;}
break;
case 42:
//#line 269 "project7.by"
{
                        String callres = ICode.genTemp();    
			currTable.add(callres,"int");
			new ICode("CALL", val_peek(2).sval ).emit();
			new ICode("STRET",  callres ).emit();
			yyval.sval = callres; 
                       }
break;
case 43:
//#line 276 "project7.by"
{
                        String callres = ICode.genTemp();
			currTable.add(callres,"int");
			LinkedList<String> apl = (LinkedList<String>)val_peek(1).obj;
			for(String p:apl)
			    new ICode("PARAM", p ).emit();

			new ICode("CALL", val_peek(3).sval ).emit();
			new ICode("STRET", callres ).emit();
			yyval.sval = callres;
                       }
break;
case 44:
//#line 290 "project7.by"
{
    LinkedList<String> apl = (LinkedList<String>) val_peek(0).obj;
    apl.addFirst(val_peek(2).sval);
    yyval.obj=apl;
}
break;
case 45:
//#line 296 "project7.by"
{
    LinkedList<String> ls = new LinkedList<String>();
    yyval.obj = ls;
    ls.add(val_peek(0).sval);
}
break;
case 46:
//#line 302 "project7.by"
{
    LinkedList<String> ls = new LinkedList<String>();
    yyval.obj = ls;
    ls.add(val_peek(0).sval);
}
break;
case 47:
//#line 311 "project7.by"
{
    String topLabel = ICode.genLabel();
    whileLabels.push(topLabel);


    ICode topCode = new ICode("NOP" );
    topCode.addLabel(topLabel);
    topCode.emit();
}
break;
case 48:
//#line 321 "project7.by"
{

    String outLabel = ICode.genLabel();
    whileLabels.push(outLabel);

    /*new ICode("CMPwh", $3.sval, "0" ).emit();*/
    /*System.out.println("$4.sval=:"+$4.sval+":");*/
    new ICode("CMP", val_peek(0).sval , "0" ).emit();
    new ICode("BE", outLabel ).emit();
}
break;
case 49:
//#line 332 "project7.by"
{
    String outLabel=whileLabels.pop();
    String topLabel=whileLabels.pop();
    new ICode("BA", topLabel).emit();
    ICode bottomCode = new ICode("NOP" );
    bottomCode.addLabel(outLabel);
    bottomCode.emit();   
}
break;
case 50:
//#line 343 "project7.by"
{
    String midLabel = ICode.genLabel();
    new ICode("CMP", val_peek(0).sval, "0").emit();
    new ICode("BE", midLabel).emit();
    ifLabels.push(midLabel);
}
break;
case 51:
//#line 350 "project7.by"
{
    String  midLabel = ifLabels.pop();

    String pastElse = ICode.genLabel();
    new ICode("BA", pastElse).emit();
    ifLabels.push(pastElse);


    ICode bottomCode = new ICode("NOP");
    bottomCode.addLabel(midLabel);
    bottomCode.emit();
}
break;
case 53:
//#line 365 "project7.by"
{
    String pastElse = ifLabels.pop();
    ICode endElseCode = new ICode("NOP");
    endElseCode.addLabel(pastElse);
    endElseCode.emit();
}
break;
case 54:
//#line 372 "project7.by"
{
    String noElse = ifLabels.pop();
    ICode noElseCode = new ICode("NOP");
    noElseCode.addLabel(noElse);
    noElseCode.emit();
}
break;
//#line 1543 "Parser.java"
//########## END OF USER-SUPPLIED ACTIONS ##########
    }//switch
    //#### Now let's reduce... ####
    if (yydebug) debug("reduce");
    state_drop(yym);             //we just reduced yylen states
    yystate = state_peek(0);     //get new state
    val_drop(yym);               //corresponding value drop
    yym = yylhs[yyn];            //select next TERMINAL(on lhs)
    if (yystate == 0 && yym == 0)//done? 'rest' state and at first TERMINAL
      {
      if (yydebug) debug("After reduction, shifting from state 0 to state "+YYFINAL+"");
      yystate = YYFINAL;         //explicitly say we're done
      state_push(YYFINAL);       //and save it
      val_push(yyval);           //also save the semantic value of parsing
      if (yychar < 0)            //we want another character?
        {
        yychar = yylex();        //get next character
        if (yychar<0) yychar=0;  //clean, if necessary
        if (yydebug)
          yylexdebug(yystate,yychar);
        }
      if (yychar == 0)          //Good exit (if lex returns 0 ;-)
         break;                 //quit the loop--all DONE
      }//if yystate
    else                        //else not done yet
      {                         //get next state and push, for next yydefred[]
      yyn = yygindex[yym];      //find out where to go
      if ((yyn != 0) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn]; //get new state
      else
        yystate = yydgoto[yym]; //else go to new defred
      if (yydebug) debug("after reduction, shifting from state "+state_peek(0)+" to state "+yystate+"");
      state_push(yystate);     //going again, so push state & val...
      val_push(yyval);         //for next action
      }
    }//main loop
  return 0;//yyaccept!!
}
//## end of method parse() ######################################



//## run() --- for Thread #######################################
/**
 * A default run method, used for operating this parser
 * object in the background.  It is intended for extending Thread
 * or implementing Runnable.  Turn off with -Jnorun .
 */
public void run()
{
  yyparse();
}
//## end of method run() ########################################



//## Constructors ###############################################
/**
 * Default constructor.  Turn off with -Jnoconstruct .

 */
public Parser()
{
  //nothing to do
}


/**
 * Create a parser, setting the debug to true or false.
 * @param debugMe true for debugging, false for no debug.
 */
public Parser(boolean debugMe)
{
  yydebug=debugMe;
}
//###############################################################



}
//################### END OF CLASS ##############################

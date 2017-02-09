import java.util.List;
import java.util.LinkedList;


/**
 *  ICode - class to generate a single intermediate code statement, and
 *          some utility methods to support intermediate code
 *
 *    @author Stephen Blythe
 *    @version 11/2016
 */
public class ICode
{
    private static int labelCount = 0;  // counter for generating unique labels
    private static int tempCount = 0;   // counter for generating unique ids

    // UPDATE: a public "shared" list of icode statements
    public static LinkedList<ICode> stmtList = new LinkedList<ICode>();

    /**
     *  genTemp -- generates a new temporary variable name
     *    @return the new temporary variable name
     */
    public static String genTemp()
    {
	return "__T"+tempCount++;
    }

    /**
     *  genLabel -- generates a new label name
     *    @return the new label name
     */
    public static String genLabel()
    {
	return "__L"+labelCount++;
    }


    private String label;  // a statement may have a label
    private String opcode; // a statement will have an opcode
    private int nops;      // how many operands (up to 3) in the statement?
    private String op1;    // first possible oeprand
    private String op2;    // second possible operand
    private String op3;    // third possible operand

    /**
     * Constructor for 0 operand statement
     *   @param op - the opcode for the statement
     */
    public ICode(String op)
    {
	nops = 0;
	genCode(op, null, null, null);
    }

    /**
     * Constructor for 1 operand statement
     *   @param op - the opcode for the statement
     *   @param op1 - the operand
     */
    public ICode(String op, String op1)
    {
	nops = 0;
	genCode(op, op1, null, null);
    }

    /**
     * Constructor for 2 operand statement
     *   @param op - the opcode for the statement
     *   @param op1 - the first operand
     *   @param op2 - the second operand
     */
    public ICode(String op, String op1, String op2)
    {
	nops = 0;
	genCode(op, op1, op2, null);
    }


   /**
     * Constructor for 2 operand statement
     *   @param op - the opcode for the statement
     *   @param op1 - the first operand
     *   @param op2 - the second operand
     *   @param op3 - the third operand
     */
    public ICode(String op, String op1, String op2, String op3)
    {
	nops = 0;
	genCode(op, op1, op2, op3);
    }

    // utility method for filling in an operand.
    private void genCode(String op, String op1, String op2, String op3)
    {
	// take parameters and place the into associated instance variables
	opcode = op;
	this.op1 = op1;
	this.op2 = op2;
	this.op3 = op3;
	label = null;  // by default, no stateent has a label.
    }

    /**
     * addLabel - adds a label to an existing statement
     *  @param lbl the label name to add.
     */
    public void addLabel(String lbl)
    {
	label = lbl;
    }

    /**
     *  getLabel - returns the tlabel for a statement
     *    @return the label name
     */
    public String getLabel() { return label;}

    /**
     *  getOpCode - returns the opcode of the statement
     *     @return the opcode of the statement
     */
    public String getOpCode() { return opcode;}  // fixed error here

    /**
     * getOperands - returns a list of the operands in the statement
     *    @return the list of operands. 
     */
    public List<String> getOperands()
    {
	List<String> ans = new LinkedList<String>();
	
	// check each operand; if it exists (i.e. is not null), add to result
	if (op1!=null)
	    ans.add(op1);

	if (op2!=null)
	    ans.add(op2);

	if (op3!=null)
	    ans.add(op3);

	return ans;
    }

    /**
     * emit - produce the statement. 
     *  UPDATE: adds to linked list of ICode
     */
    public void emit()
    {
	stmtList.addLast(this);
    }

    /**
     * print  -- prints current Statement to stdout
     */
    public void print()
    {
	// if there's a label, print it. in any case, print the opcode. 
	if (label!=null)
	    System.out.printf("%-20s%-5s", label+":", opcode);
	else
	    System.out.printf("%20s%-5s", "", opcode);


	// print out the operands. 
	if (op1!=null)
	    {
		System.out.print("\t" + op1);
		if (op2!=null)
		    {
			System.out.print(", " + op2);
			if (op3!=null)
			    {
				System.out.print(", " + op3);
			    }
		    }
	    }
	System.out.println();
	
	
    }

    public void clearOPC() { opcode = ""; }

    public void setOPC(String s) { opcode = s; }
}
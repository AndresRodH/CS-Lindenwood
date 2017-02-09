import java.util.List;
import java.util.LinkedList;

/**
 * A SymbolTable is effectively a list of Symbols
 *   @author Stephen Blythe
 *   @version 10/2016
 */
public class SymbolTable
{
    private int _offset;   // current offset of end of table;
    private String _name;  // nsme of tsble
    private LinkedList<Symbol> _symbols; // list of Smbols in table 

    /**
     * constructor to build a new (empty) symbol table with given name
     *  @param name - the name of this symbol table
     */
    public SymbolTable(String name)
    {
	_name = name;
	_symbols = new LinkedList<Symbol>(); // symbol table should start empty
	_offset=0;   // since empty, end offset is 0
    }


    /**
     * getName -accessor for SymbolTable's name
     * @return the name of teh Symbol Table
     */
    public String getName() {return _name;}
    
    /**
     * getSymbols - accessor for list of Symbols
     * @return list of symbols in table
     */
    public LinkedList<Symbol> getSymbols() {return _symbols;}

    /**
     * add - put a new Symbol in this table
     *  @param s = the new Symbol
     */
    public void add(Symbol s)
    {
	// provided symbol is not already in the table ...
	if (find(s.getName())==null)
	    {
		_offset+=4;          // tble's offset is now 1 word bigger
		s.setOffset(_offset); // set the symbol's offset
		_symbols.add(s);      // add symbol to table
	    }
    }

    /**
     * getSize - returns the size (on stack) needed to hold this table
     *  @return the size of this table
     **/
    public int getSize()
    {
	return _offset;
    }
    
    /**
     * add - create and add a new SYmbool
     *  @param n the name of the new Symbol
     *  @param t the tye of the new Symbol
     */
    public void add(String n, String t)
    {
	this.add(new Symbol(n,t));
    }

    /**
     * find - return a symbol matching the name requested, if found in table
     *   @param the name of the symbol to look for
     *   @return the matching Symbol (or null if no such Symbol)
     */
    public Symbol find(String vname)
    {
	// go through ech Symbol in the table
	for (Symbol s: _symbols)
	    {
		// if it matches the search parameter (name), return it
		if (s.getName().equals(vname))
		    return s;
	    }

	// if we get here, Symbol was not found, so return null 
	return null;
    }
    
    /**
     * toString - give String equivalent of entire table
     *   @return STring contaning the table
     */
    public String toString()
    {
	// add a header, including table name
	String ans = "#============ " + _name + " =============\n";
	// add each symbol to string
	for(Symbol s:_symbols)
	    {
		ans+="#"+s+"\n";
	    }
	ans +="#===================================================\n";
	return ans;
    }
}

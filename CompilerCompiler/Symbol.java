/**
 * Symbol describes a single symbol (ex. variable, function, etc...) 
 *    @author Stephen Blythe
 *    @version 10/2016
 */
public class Symbol 
{
    private static int _nextOffset = 0; // keeps track of next offset to use
    private String _name;   // name of symbol
    private String _type;   // tyoe of symbol
    private int _offset;    // offset of symbol

    /**
     * constructor to build a symbol given a name and a type
     *   @param name - the new symbol's name
     *   @param type - the new symbol's type (ex. integer, fuction, ...)
     */
    public Symbol(String name, String type)
    {
	buildSymbol(name, type, 0);
	_nextOffset +=4; // next symbol will have offset 4 after this one. 
    }

    // internal method to actually store the details about a new symbol
    private void buildSymbol(String name, String type, int offset)
    {
	// give instance vars good values. 
	_name = name;
	_type = type;
	_offset = offset;
    }

    /**
     * accessor for Symbol name
     *   @return the symbol name
     */
    public String getName() {return _name;}

    /**
     * accessor for Symbol type
     *   @return the symbol type
     */
    public String getType() {return _type;}

    /**
     * accessor for Symbol offset
     *   @return the offset of this 
     */
    public int getOffset() {return _offset;}

    /**
     * gives new value to offset
     *   @param offset - the new offset value
     */
    public void setOffset(int offset) {_offset=offset;}

    /**
     * returns a String version of this symbol
     *   @retunr String representation of Symbol  
     */
    public String toString()
    {
	String ans = String.format("%40s %10s %10d", _name, _type, _offset);
	return ans;
    }
}
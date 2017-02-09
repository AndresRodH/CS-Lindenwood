import java.io.*;

/**
 *
 * Buffered Reader - allows character by character input and
 *                   return of character(s) to input stream
 * @author S. Blythe
 * @version 8/2016
 *
 */



public class BufferedReader
{
    private InputStream currStream;  // stream from which input is taken
    private String buffer;           // buffer of "putback" characters

    /**
     *   default constructor that uses standard input as input stream
     */
    public BufferedReader()
    {
	currStream = System.in;
	buffer=new String();
    }

    /**
     *   construct that uses given InputStream. 
     *
     *   @input is the input stream to utilize
     */
    public BufferedReader(InputStream is)
    {
	currStream = is;
	buffer = new String();
    }


    /**
     *  constructor that uses a given file as the input stream
     *
     *  @input f the file to use
     *  @throws FileNotFoundException
     * 
     */
    public BufferedReader(File f) throws FileNotFoundException
    {
	currStream = new FileInputStream(f);
	buffer = new String();
    }


    /**
     *  constructor that builds a File out of given filename and uses 
     *  that file as the input stream. 
     *
     *  @input fname - the name of the file to use. The file must be in
     *                 the current working directory
     *  @throws FileNotFoundException
     * 
     */
    public BufferedReader(String fname) throws FileNotFoundException
    {
	currStream = new FileInputStream(fname);
	buffer = new String();
    }

    /**
     *  method that reads a single character. The character will come
     *  from the pushBack buffer if it is nonempty and from the actual
     *  stream if the buffer is empty. Can give excetions in
     *  situations where both buffer and input stream are empty. 
     *
     *  @return the next character in the BufferReader stream
     *  @throws IOException
     */
    public char readChar() throws IOException 
    { 
	// if the buffer has no data, use the actual InputStream
	//   note that this can cause an exception if there is no more 
	//   data in the input stream.
	if (buffer.length()==0)
	    return (char) currStream.read(); 
	else // buffer is not empty ...
	{
	    // get character from buffer and remove that character from buffer
	    char result = buffer.charAt(0);
	    buffer = buffer.substring(1); 
	    return result;
       } 
    }

    /**
     * method that returns true if there is still data to be read, 
     *    false otherwise
     *
     *   @return whether or not there is still data to be read. 
     *   @throws IOException
     */
    public boolean hasNext() throws IOException
    {
	// if there is data in ether the stream or the buffer return true.
	return (currStream.available()>0 || buffer.length()>0 );
    }

    /**
     *  method to place a character back in the BufferedInput stream.
     *
     *  @param c - the character to "put back"
     */
    public void ungetChar(char c)
    {
	// push character back into input stream.
	buffer = c + buffer;
    }

}

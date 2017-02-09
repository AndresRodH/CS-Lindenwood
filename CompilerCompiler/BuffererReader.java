

public class BufferedReader
{
    private InputStream currStream;
    private String buffer;

    public BufferedReader()
    {
	currStream = System.in;
	buffer=new String();
    }
}
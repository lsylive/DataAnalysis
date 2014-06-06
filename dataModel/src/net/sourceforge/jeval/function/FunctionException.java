


//    FunctionException.java

package net.sourceforge.jeval.function;


public class FunctionException extends Exception
{

	private static final long serialVersionUID = 0x4228ad50e1b11454L;

	public FunctionException(String message)
	{
		super(message);
	}

	public FunctionException(Exception exception)
	{
		super(exception);
	}

	public FunctionException(String message, Exception exception)
	{
		super(message, exception);
	}
}

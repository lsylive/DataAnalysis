


//    EvaluationException.java

package net.sourceforge.jeval;


public class EvaluationException extends Exception
{

	private static final long serialVersionUID = 0xd63925c137f4fb6bL;

	public EvaluationException(String message)
	{
		super(message);
	}

	public EvaluationException(Exception exception)
	{
		super(exception);
	}

	public EvaluationException(String message, Exception exception)
	{
		super(message, exception);
	}
}

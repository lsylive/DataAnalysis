


//    FunctionResult.java

package net.sourceforge.jeval.function;


// Referenced classes of package net.sourceforge.jeval.function:
//			FunctionException

public class FunctionResult
{

	private String result;
	private int type;

	public FunctionResult(String result, int type)
		throws FunctionException
	{
		if (type < 0 || type > 2)
		{
			throw new FunctionException("Invalid function result type.");
		} else
		{
			this.result = result;
			this.type = type;
			return;
		}
	}

	public String getResult()
	{
		return result;
	}

	public int getType()
	{
		return type;
	}
}

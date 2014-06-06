


//    Function.java

package net.sourceforge.jeval.function;

import net.sourceforge.jeval.Evaluator;

// Referenced classes of package net.sourceforge.jeval.function:
//			FunctionException, FunctionResult

public interface Function
{

	public abstract String getName();

	public abstract String getDescription();

	public abstract FunctionResult execute(Evaluator evaluator, String s)
		throws FunctionException;
}

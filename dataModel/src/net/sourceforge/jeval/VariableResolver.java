


//    VariableResolver.java

package net.sourceforge.jeval;

import net.sourceforge.jeval.function.FunctionException;

public interface VariableResolver
{

	public abstract String resolveVariable(String s)
		throws FunctionException;
}

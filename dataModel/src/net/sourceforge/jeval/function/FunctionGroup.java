


//    FunctionGroup.java

package net.sourceforge.jeval.function;

import java.util.List;
import net.sourceforge.jeval.Evaluator;

public interface FunctionGroup
{

	public abstract String getName();

	public abstract List getFunctions();

	public abstract void load(Evaluator evaluator);

	public abstract void unload(Evaluator evaluator);
}

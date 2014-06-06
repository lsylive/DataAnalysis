


//    MathFunctions.java

package net.sourceforge.jeval.function.math;

import java.util.*;
import net.sourceforge.jeval.Evaluator;
import net.sourceforge.jeval.function.Function;
import net.sourceforge.jeval.function.FunctionGroup;

// Referenced classes of package net.sourceforge.jeval.function.math:
//			Abs, Acos, Asin, Atan, 
//			Atan2, Ceil, Cos, Exp, 
//			Floor, IEEEremainder, Log, Max, 
//			Min, Pow, Random, Rint, 
//			Round, Sin, Sqrt, Tan, 
//			ToDegrees, ToRadians

public class MathFunctions
	implements FunctionGroup
{

	private List<Function> functions;

	public MathFunctions()
	{
		functions = new ArrayList();
		functions.add(new Abs());
		functions.add(new Acos());
		functions.add(new Asin());
		functions.add(new Atan());
		functions.add(new Atan2());
		functions.add(new Ceil());
		functions.add(new Cos());
		functions.add(new Exp());
		functions.add(new Floor());
		functions.add(new IEEEremainder());
		functions.add(new Log());
		functions.add(new Max());
		functions.add(new Min());
		functions.add(new Pow());
		functions.add(new Random());
		functions.add(new Rint());
		functions.add(new Round());
		functions.add(new Sin());
		functions.add(new Sqrt());
		functions.add(new Tan());
		functions.add(new ToDegrees());
		functions.add(new ToRadians());
	}

	public String getName()
	{
		return "numberFunctions";
	}

	public List<Function> getFunctions()
	{
		return functions;
	}

	public void load(Evaluator evaluator)
	{
		for (Iterator functionIterator = functions.iterator(); functionIterator.hasNext(); evaluator.putFunction((Function)functionIterator.next()));
	}

	public void unload(Evaluator evaluator)
	{
		for (Iterator functionIterator = functions.iterator(); functionIterator.hasNext(); evaluator.removeFunction(((Function)functionIterator.next()).getName()));
	}
}

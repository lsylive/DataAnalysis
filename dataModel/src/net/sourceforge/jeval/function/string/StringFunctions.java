


//    StringFunctions.java

package net.sourceforge.jeval.function.string;

import java.util.*;
import net.sourceforge.jeval.Evaluator;
import net.sourceforge.jeval.function.Function;
import net.sourceforge.jeval.function.FunctionGroup;

// Referenced classes of package net.sourceforge.jeval.function.string:
//			ChangeTo, CharAt, CompareTo, CompareToIgnoreCase, 
//			Concat, EndsWith, Equals, EqualsIgnoreCase, 
//			Eval, IndexOf, LastIndexOf, Length, 
//			Replace, StartsWith, Substring, ToLowerCase, 
//			ToUpperCase, Trim

public class StringFunctions
	implements FunctionGroup
{

	private List<Function> functions;

	public StringFunctions()
	{
		functions = new ArrayList();
		functions.add(new ChangeTo());
		functions.add(new CharAt());
		functions.add(new CompareTo());
		functions.add(new CompareToIgnoreCase());
		functions.add(new Concat());
		functions.add(new EndsWith());
		functions.add(new Equals());
		functions.add(new EqualsIgnoreCase());
		functions.add(new Eval());
		functions.add(new IndexOf());
		functions.add(new LastIndexOf());
		functions.add(new Length());
		functions.add(new Replace());
		functions.add(new StartsWith());
		functions.add(new Substring());
		functions.add(new ToLowerCase());
		functions.add(new ToUpperCase());
		functions.add(new Trim());
	}

	public String getName()
	{
		return "stringFunctions";
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

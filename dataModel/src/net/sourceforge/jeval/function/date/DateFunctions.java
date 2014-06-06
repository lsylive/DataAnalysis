


//    DateFunctions.java

package net.sourceforge.jeval.function.date;

import java.util.*;
import net.sourceforge.jeval.Evaluator;
import net.sourceforge.jeval.function.Function;
import net.sourceforge.jeval.function.FunctionGroup;

// Referenced classes of package net.sourceforge.jeval.function.date:
//			Year, Month, Day, Hour, 
//			Minute, DayOfWeek, DayDifference, MinuteDifference, 
//			AddDay, AddMinute, NewDay

public class DateFunctions
	implements FunctionGroup
{

	private List<Function> functions;

	public DateFunctions()
	{
		functions = new ArrayList();
		functions.add(new Year());
		functions.add(new Month());
		functions.add(new Day());
		functions.add(new Hour());
		functions.add(new Minute());
		functions.add(new DayOfWeek());
		functions.add(new DayDifference());
		functions.add(new MinuteDifference());
		functions.add(new AddDay());
		functions.add(new AddMinute());
		functions.add(new NewDay());
	}

	public String getName()
	{
		return "dateFunctions";
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

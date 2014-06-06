


//    test.java

package net.sourceforge.jeval;

import java.io.PrintStream;

// Referenced classes of package net.sourceforge.jeval:
//			Evaluator

public class test
{

	public test()
	{
	}

	public static void main(String args[])
	{
		Evaluator Evaluator = new Evaluator();
		String exp = "'#'+substring('12345678',2,5)+'#'";
		try
		{
			String res = Evaluator.evaluate(exp, false, true);
			System.out.print(res);
		}
		catch (Exception e)
		{
			System.out.print(e.getMessage());
		}
	}
}




//    DivisionOperator.java

package net.sourceforge.jeval.operator;


// Referenced classes of package net.sourceforge.jeval.operator:
//			AbstractOperator

public class DivisionOperator extends AbstractOperator
{

	public DivisionOperator()
	{
		super("/", 6);
	}

	public double evaluate(double leftOperand, double rightOperand)
	{
		Double rtnValue = new Double(leftOperand / rightOperand);
		return rtnValue.doubleValue();
	}
}

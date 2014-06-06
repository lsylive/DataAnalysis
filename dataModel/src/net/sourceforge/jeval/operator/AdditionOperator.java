


//    AdditionOperator.java

package net.sourceforge.jeval.operator;


// Referenced classes of package net.sourceforge.jeval.operator:
//			AbstractOperator

public class AdditionOperator extends AbstractOperator
{

	public AdditionOperator()
	{
		super("+", 5, true);
	}

	public double evaluate(double leftOperand, double rightOperand)
	{
		Double rtnValue = new Double(leftOperand + rightOperand);
		return rtnValue.doubleValue();
	}

	public String evaluate(String leftOperand, String rightOperand)
	{
		String rtnValue = new String((new StringBuilder(String.valueOf(leftOperand.substring(0, leftOperand.length() - 1)))).append(rightOperand.substring(1, rightOperand.length())).toString());
		return rtnValue;
	}

	public double evaluate(double operand)
	{
		return operand;
	}
}

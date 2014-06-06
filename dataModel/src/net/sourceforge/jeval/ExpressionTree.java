


//    ExpressionTree.java

package net.sourceforge.jeval;

import net.sourceforge.jeval.function.Function;
import net.sourceforge.jeval.function.FunctionException;
import net.sourceforge.jeval.function.FunctionResult;
import net.sourceforge.jeval.operator.Operator;

// Referenced classes of package net.sourceforge.jeval:
//			EvaluationException, ExpressionOperand, Evaluator, ParsedFunction

public class ExpressionTree
{

	private Object leftOperand;
	private Object rightOperand;
	private Operator operator;
	private Operator unaryOperator;
	private Evaluator evaluator;

	public ExpressionTree(Evaluator evaluator, Object leftOperand, Object rightOperand, Operator operator, Operator unaryOperator)
	{
		this.leftOperand = null;
		this.rightOperand = null;
		this.operator = null;
		this.unaryOperator = null;
		this.evaluator = null;
		this.evaluator = evaluator;
		this.leftOperand = leftOperand;
		this.rightOperand = rightOperand;
		this.operator = operator;
		this.unaryOperator = unaryOperator;
	}

	public Object getLeftOperand()
	{
		return leftOperand;
	}

	public Object getRightOperand()
	{
		return rightOperand;
	}

	public Operator getOperator()
	{
		return operator;
	}

	public Operator getUnaryOperator()
	{
		return unaryOperator;
	}

	public String evaluate(boolean wrapStringFunctionResults)
		throws EvaluationException
	{
		String rtnResult = null;
		String leftResultString = null;
		Double leftResultDouble = null;
		if (leftOperand instanceof ExpressionTree)
		{
			leftResultString = ((ExpressionTree)leftOperand).evaluate(wrapStringFunctionResults);
			try
			{
				leftResultDouble = new Double(leftResultString);
				leftResultString = null;
			}
			catch (NumberFormatException exception)
			{
				leftResultDouble = null;
			}
		} else
		if (leftOperand instanceof ExpressionOperand)
		{
			ExpressionOperand leftExpressionOperand = (ExpressionOperand)leftOperand;
			leftResultString = leftExpressionOperand.getValue();
			leftResultString = evaluator.replaceVariables(leftResultString);
			if (!evaluator.isExpressionString(leftResultString))
			{
				try
				{
					leftResultDouble = new Double(leftResultString);
					leftResultString = null;
				}
				catch (NumberFormatException nfe)
				{
					throw new EvaluationException("Expression is invalid.", nfe);
				}
				if (leftExpressionOperand.getUnaryOperator() != null)
					leftResultDouble = new Double(leftExpressionOperand.getUnaryOperator().evaluate(leftResultDouble.doubleValue()));
			} else
			if (leftExpressionOperand.getUnaryOperator() != null)
				throw new EvaluationException("Invalid operand for unary operator.");
		} else
		if (leftOperand instanceof ParsedFunction)
		{
			ParsedFunction parsedFunction = (ParsedFunction)leftOperand;
			Function function = parsedFunction.getFunction();
			String arguments = parsedFunction.getArguments();
			arguments = evaluator.replaceVariables(arguments);
			if (evaluator.getProcessNestedFunctions())
				arguments = evaluator.processNestedFunctions(arguments);
			try
			{
				FunctionResult functionResult = function.execute(evaluator, arguments);
				leftResultString = functionResult.getResult();
				if (functionResult.getType() == 0)
				{
					Double resultDouble = new Double(leftResultString);
					if (parsedFunction.getUnaryOperator() != null)
						resultDouble = new Double(parsedFunction.getUnaryOperator().evaluate(resultDouble.doubleValue()));
					leftResultString = resultDouble.toString();
				} else
				if (functionResult.getType() == 1)
				{
					if (wrapStringFunctionResults)
						leftResultString = (new StringBuilder(String.valueOf(evaluator.getQuoteCharacter()))).append(leftResultString).append(evaluator.getQuoteCharacter()).toString();
					if (parsedFunction.getUnaryOperator() != null)
						throw new EvaluationException("Invalid operand for unary operator.");
				}
			}
			catch (FunctionException fe)
			{
				throw new EvaluationException(fe.getMessage(), fe);
			}
			if (!evaluator.isExpressionString(leftResultString))
				try
				{
					leftResultDouble = new Double(leftResultString);
					leftResultString = null;
				}
				catch (NumberFormatException nfe)
				{
					throw new EvaluationException("Expression is invalid.", nfe);
				}
		} else
		if (leftOperand != null)
			throw new EvaluationException("Expression is invalid.");
		String rightResultString = null;
		Double rightResultDouble = null;
		if (rightOperand instanceof ExpressionTree)
		{
			rightResultString = ((ExpressionTree)rightOperand).evaluate(wrapStringFunctionResults);
			try
			{
				rightResultDouble = new Double(rightResultString);
				rightResultString = null;
			}
			catch (NumberFormatException exception)
			{
				rightResultDouble = null;
			}
		} else
		if (rightOperand instanceof ExpressionOperand)
		{
			ExpressionOperand rightExpressionOperand = (ExpressionOperand)rightOperand;
			rightResultString = ((ExpressionOperand)rightOperand).getValue();
			rightResultString = evaluator.replaceVariables(rightResultString);
			if (!evaluator.isExpressionString(rightResultString))
			{
				try
				{
					rightResultDouble = new Double(rightResultString);
					rightResultString = null;
				}
				catch (NumberFormatException nfe)
				{
					throw new EvaluationException("Expression is invalid.", nfe);
				}
				if (rightExpressionOperand.getUnaryOperator() != null)
					rightResultDouble = new Double(rightExpressionOperand.getUnaryOperator().evaluate(rightResultDouble.doubleValue()));
			} else
			if (rightExpressionOperand.getUnaryOperator() != null)
				throw new EvaluationException("Invalid operand for unary operator.");
		} else
		if (rightOperand instanceof ParsedFunction)
		{
			ParsedFunction parsedFunction = (ParsedFunction)rightOperand;
			Function function = parsedFunction.getFunction();
			String arguments = parsedFunction.getArguments();
			arguments = evaluator.replaceVariables(arguments);
			if (evaluator.getProcessNestedFunctions())
				arguments = evaluator.processNestedFunctions(arguments);
			try
			{
				FunctionResult functionResult = function.execute(evaluator, arguments);
				rightResultString = functionResult.getResult();
				if (functionResult.getType() == 0)
				{
					Double resultDouble = new Double(rightResultString);
					if (parsedFunction.getUnaryOperator() != null)
						resultDouble = new Double(parsedFunction.getUnaryOperator().evaluate(resultDouble.doubleValue()));
					rightResultString = resultDouble.toString();
				} else
				{
					if (wrapStringFunctionResults)
						rightResultString = (new StringBuilder(String.valueOf(evaluator.getQuoteCharacter()))).append(rightResultString).append(evaluator.getQuoteCharacter()).toString();
					if (parsedFunction.getUnaryOperator() != null)
						throw new EvaluationException("Invalid operand for unary operator.");
				}
			}
			catch (FunctionException fe)
			{
				throw new EvaluationException(fe.getMessage(), fe);
			}
			if (!evaluator.isExpressionString(rightResultString))
				try
				{
					rightResultDouble = new Double(rightResultString);
					rightResultString = null;
				}
				catch (NumberFormatException nfe)
				{
					throw new EvaluationException("Expression is invalid.", nfe);
				}
		} else
		if (rightOperand != null)
			throw new EvaluationException("Expression is invalid.");
		if (leftResultDouble != null && rightResultDouble != null)
		{
			double doubleResult = operator.evaluate(leftResultDouble.doubleValue(), rightResultDouble.doubleValue());
			if (getUnaryOperator() != null)
				doubleResult = getUnaryOperator().evaluate(doubleResult);
			rtnResult = (new Double(doubleResult)).toString();
		} else
		if (leftResultString != null && rightResultString != null)
			rtnResult = operator.evaluate(leftResultString, rightResultString);
		else
		if (leftResultDouble != null && rightResultDouble == null)
		{
			double doubleResult = -1D;
			if (unaryOperator != null)
				doubleResult = unaryOperator.evaluate(leftResultDouble.doubleValue());
			else
				throw new EvaluationException("Expression is invalid.");
			rtnResult = (new Double(doubleResult)).toString();
		} else
		{
			throw new EvaluationException("Expression is invalid.");
		}
		return rtnResult;
	}
}

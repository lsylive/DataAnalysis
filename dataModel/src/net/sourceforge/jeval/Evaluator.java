


//    Evaluator.java

package net.sourceforge.jeval;

import java.util.*;
import net.sourceforge.jeval.function.Function;
import net.sourceforge.jeval.function.FunctionException;
import net.sourceforge.jeval.function.FunctionGroup;
import net.sourceforge.jeval.function.FunctionResult;
import net.sourceforge.jeval.function.date.DateFunctions;
import net.sourceforge.jeval.function.math.MathFunctions;
import net.sourceforge.jeval.function.string.StringFunctions;
import net.sourceforge.jeval.operator.AdditionOperator;
import net.sourceforge.jeval.operator.BooleanAndOperator;
import net.sourceforge.jeval.operator.BooleanNotOperator;
import net.sourceforge.jeval.operator.BooleanOrOperator;
import net.sourceforge.jeval.operator.ClosedParenthesesOperator;
import net.sourceforge.jeval.operator.DivisionOperator;
import net.sourceforge.jeval.operator.EqualOperator;
import net.sourceforge.jeval.operator.GreaterThanOperator;
import net.sourceforge.jeval.operator.GreaterThanOrEqualOperator;
import net.sourceforge.jeval.operator.LessThanOperator;
import net.sourceforge.jeval.operator.LessThanOrEqualOperator;
import net.sourceforge.jeval.operator.ModulusOperator;
import net.sourceforge.jeval.operator.MultiplicationOperator;
import net.sourceforge.jeval.operator.NotEqualOperator;
import net.sourceforge.jeval.operator.OpenParenthesesOperator;
import net.sourceforge.jeval.operator.Operator;
import net.sourceforge.jeval.operator.SubtractionOperator;

// Referenced classes of package net.sourceforge.jeval:
//			EvaluationException, VariableResolver, EvaluationHelper, NextOperator, 
//			ExpressionOperand, ExpressionOperator, ExpressionTree, ParsedFunction, 
//			EvaluationConstants, ArgumentTokenizer

public class Evaluator
{

	private List operators;
	private Map functions;
	private Map variables;
	private char quoteCharacter;
	private Operator openParenthesesOperator;
	private Operator closedParenthesesOperator;
	private boolean loadMathVariables;
	private boolean loadMathFunctions;
	private boolean loadStringFunctions;
	private boolean loadDateFunctions;
	private boolean processNestedFunctions;
	private String previousExpression;
	private Stack previousOperatorStack;
	private Stack previousOperandStack;
	private Stack operatorStack;
	private Stack operandStack;
	private VariableResolver variableResolver;

	public Evaluator()
	{
		this('\'', true, true, true, true, true);
	}

	public Evaluator(char quoteCharacter, boolean loadMathVariables, boolean loadMathFunctions, boolean loadStringFunctions, boolean processNestedFunctions, boolean loadDateFunctions)
	{
		operators = new ArrayList();
		functions = new HashMap();
		variables = new HashMap();
		this.quoteCharacter = '\'';
		openParenthesesOperator = new OpenParenthesesOperator();
		closedParenthesesOperator = new ClosedParenthesesOperator();
		previousExpression = null;
		previousOperatorStack = null;
		previousOperandStack = null;
		operatorStack = null;
		operandStack = null;
		variableResolver = null;
		installOperators();
		this.loadMathVariables = loadMathVariables;
		loadSystemVariables();
		this.loadMathFunctions = loadMathFunctions;
		this.loadStringFunctions = loadStringFunctions;
		this.loadDateFunctions = loadDateFunctions;
		loadSystemFunctions();
		setQuoteCharacter(quoteCharacter);
		this.processNestedFunctions = processNestedFunctions;
	}

	public char getQuoteCharacter()
	{
		return quoteCharacter;
	}

	public void setQuoteCharacter(char quoteCharacter)
	{
		if (quoteCharacter == '\'' || quoteCharacter == '"')
			this.quoteCharacter = quoteCharacter;
		else
			throw new IllegalArgumentException("Invalid quote character.");
	}

	public void putFunction(Function function)
	{
		isValidName(function.getName());
		Function existingFunction = (Function)functions.get(function.getName());
		if (existingFunction == null)
			functions.put(function.getName(), function);
		else
			throw new IllegalArgumentException("A function with the same name already exists.");
	}

	public Function getFunction(String functionName)
	{
		return (Function)functions.get(functionName);
	}

	public void removeFunction(String functionName)
	{
		if (functions.containsKey(functionName))
			functions.remove(functionName);
		else
			throw new IllegalArgumentException("The function does not exist.");
	}

	public void clearFunctions()
	{
		functions.clear();
		loadSystemFunctions();
	}

	public Map getFunctions()
	{
		return functions;
	}

	public void setFunctions(Map functions)
	{
		this.functions = functions;
	}

	public void putVariable(String variableName, String variableValue)
	{
		isValidName(variableName);
		variables.put(variableName, variableValue);
	}

	public String getVariableValue(String variableName)
		throws EvaluationException
	{
		String variableValue = null;
		if (variableResolver != null)
			try
			{
				variableValue = variableResolver.resolveVariable(variableName);
			}
			catch (FunctionException fe)
			{
				throw new EvaluationException(fe.getMessage(), fe);
			}
		if (variableValue == null)
			variableValue = (String)variables.get(variableName);
		if (variableValue == null)
			throw new EvaluationException((new StringBuilder("Can not resolve variable with name equal to \"")).append(variableName).append("\".").toString());
		else
			return variableValue;
	}

	public void removeVaraible(String variableName)
	{
		if (variables.containsKey(variableName))
			variables.remove(variableName);
		else
			throw new IllegalArgumentException("The variable does not exist.");
	}

	public void clearVariables()
	{
		variables.clear();
		loadSystemVariables();
	}

	public Map getVariables()
	{
		return variables;
	}

	public void setVariables(Map variables)
	{
		this.variables = variables;
	}

	public VariableResolver getVariableResolver()
	{
		return variableResolver;
	}

	public void setVariableResolver(VariableResolver variableResolver)
	{
		this.variableResolver = variableResolver;
	}

	public String evaluate(String expression)
		throws EvaluationException
	{
		return evaluate(expression, true, true);
	}

	public String evaluate()
		throws EvaluationException
	{
		String expression = previousExpression;
		if (expression == null || expression.length() == 0)
			throw new EvaluationException("No expression has been specified.");
		else
			return evaluate(expression, true, true);
	}

	public String evaluate(String expression, boolean keepQuotes, boolean wrapStringFunctionResults)
		throws EvaluationException
	{
		parse(expression);
		String result = getResult(operatorStack, operandStack, wrapStringFunctionResults);
		if (isExpressionString(result) && !keepQuotes)
			result = result.substring(1, result.length() - 1);
		return result;
	}

	public String evaluate(boolean keepQuotes, boolean wrapStringFunctionResults)
		throws EvaluationException
	{
		String expression = previousExpression;
		if (expression == null || expression.length() == 0)
			throw new EvaluationException("No expression has been specified.");
		else
			return evaluate(expression, keepQuotes, wrapStringFunctionResults);
	}

	public boolean getBooleanResult(String expression)
		throws EvaluationException
	{
		String result = evaluate(expression);
		try {
			Double doubleResult = new Double(result);
			return doubleResult.doubleValue() == 1.0D;	
		} catch (NumberFormatException e) {
			// TODO: handle exception
		}
		return false;
	}

	public double getNumberResult(String expression)
		throws EvaluationException
	{
		String result = evaluate(expression);
		Double doubleResult = null;
		try
		{
			doubleResult = new Double(result);
		}
		catch (NumberFormatException nfe)
		{
			throw new EvaluationException("Expression does not produce a number.", nfe);
		}
		return doubleResult.doubleValue();
	}

	public void parse(String expression)
		throws EvaluationException
	{
		boolean parse = true;
		if (!expression.equals(previousExpression))
		{
			previousExpression = expression;
		} else
		{
			parse = false;
			operatorStack = (Stack)previousOperatorStack.clone();
			operandStack = (Stack)previousOperandStack.clone();
		}
		try
		{
			if (parse)
			{
				operandStack = new Stack();
				operatorStack = new Stack();
				boolean haveOperand = false;
				boolean haveOperator = false;
				Operator unaryOperator = null;
				int numChars = expression.length();
				for (int charCtr = 0; charCtr < numChars;)
				{
					Operator operator = null;
					int operatorIndex = -1;
					if (EvaluationHelper.isSpace(expression.charAt(charCtr)))
					{
						charCtr++;
					} else
					{
						NextOperator nextOperator = getNextOperator(expression, charCtr, null);
						if (nextOperator != null)
						{
							operator = nextOperator.getOperator();
							operatorIndex = nextOperator.getIndex();
						}
						if (operatorIndex > charCtr || operatorIndex == -1)
						{
							charCtr = processOperand(expression, charCtr, operatorIndex, operandStack, unaryOperator);
							haveOperand = true;
							haveOperator = false;
							unaryOperator = null;
						}
						if (operatorIndex == charCtr)
						{
							if (nextOperator.getOperator().isUnary() && (haveOperator || charCtr == 0))
							{
								charCtr = processUnaryOperator(operatorIndex, nextOperator.getOperator());
								if (unaryOperator == null)
									unaryOperator = nextOperator.getOperator();
								else
									throw new EvaluationException((new StringBuilder("Consecutive unary operators are not allowed (index=")).append(charCtr).append(").").toString());
							} else
							{
								charCtr = processOperator(expression, operatorIndex, operator, operatorStack, operandStack, haveOperand, unaryOperator);
								unaryOperator = null;
							}
							if (!(nextOperator.getOperator() instanceof ClosedParenthesesOperator))
							{
								haveOperand = false;
								haveOperator = true;
							}
						}
					}
				}

				previousOperatorStack = (Stack)operatorStack.clone();
				previousOperandStack = (Stack)operandStack.clone();
			}
		}
		catch (Exception e)
		{
			previousExpression = "";
			throw new EvaluationException(e.getMessage(), e);
		}
	}

	private void installOperators()
	{
		operators.add(openParenthesesOperator);
		operators.add(closedParenthesesOperator);
		operators.add(new AdditionOperator());
		operators.add(new SubtractionOperator());
		operators.add(new MultiplicationOperator());
		operators.add(new DivisionOperator());
		operators.add(new EqualOperator());
		operators.add(new NotEqualOperator());
		operators.add(new LessThanOrEqualOperator());
		operators.add(new LessThanOperator());
		operators.add(new GreaterThanOrEqualOperator());
		operators.add(new GreaterThanOperator());
		operators.add(new BooleanAndOperator());
		operators.add(new BooleanOrOperator());
		operators.add(new BooleanNotOperator());
		operators.add(new ModulusOperator());
	}

	private int processOperand(String expression, int charCtr, int operatorIndex, Stack operandStack, Operator unaryOperator)
		throws EvaluationException
	{
		String operandString = null;
		int rtnCtr = -1;
		if (operatorIndex == -1)
		{
			operandString = expression.substring(charCtr).trim();
			rtnCtr = expression.length();
		} else
		{
			operandString = expression.substring(charCtr, operatorIndex).trim();
			rtnCtr = operatorIndex;
		}
		if (operandString.length() == 0)
		{
			throw new EvaluationException("Expression is invalid.");
		} else
		{
			ExpressionOperand operand = new ExpressionOperand(operandString, unaryOperator);
			operandStack.push(operand);
			return rtnCtr;
		}
	}

	private int processOperator(String expression, int originalOperatorIndex, Operator originalOperator, Stack operatorStack, Stack operandStack, boolean haveOperand, Operator unaryOperator)
		throws EvaluationException
	{
		int operatorIndex = originalOperatorIndex;
		Operator operator = originalOperator;
		if (haveOperand && (operator instanceof OpenParenthesesOperator))
		{
			NextOperator nextOperator = processFunction(expression, operatorIndex, operandStack);
			operator = nextOperator.getOperator();
			operatorIndex = nextOperator.getIndex() + operator.getLength();
			nextOperator = getNextOperator(expression, operatorIndex, null);
			if (nextOperator != null)
			{
				operator = nextOperator.getOperator();
				operatorIndex = nextOperator.getIndex();
			} else
			{
				return operatorIndex;
			}
		}
		if (operator instanceof OpenParenthesesOperator)
		{
			ExpressionOperator expressionOperator = new ExpressionOperator(operator, unaryOperator);
			operatorStack.push(expressionOperator);
		} else
		if (operator instanceof ClosedParenthesesOperator)
		{
			ExpressionOperator stackOperator = null;
			if (operatorStack.size() > 0)
				stackOperator = (ExpressionOperator)operatorStack.peek();
			while (stackOperator != null && !(stackOperator.getOperator() instanceof OpenParenthesesOperator)) 
			{
				processTree(operandStack, operatorStack);
				if (operatorStack.size() > 0)
					stackOperator = (ExpressionOperator)operatorStack.peek();
				else
					stackOperator = null;
			}
			if (operatorStack.isEmpty())
				throw new EvaluationException("Expression is invalid.");
			ExpressionOperator expressionOperator = (ExpressionOperator)operatorStack.pop();
			if (!(expressionOperator.getOperator() instanceof OpenParenthesesOperator))
				throw new EvaluationException("Expression is invalid.");
			if (expressionOperator.getUnaryOperator() != null)
			{
				Object operand = operandStack.pop();
				ExpressionTree tree = new ExpressionTree(this, operand, null, null, expressionOperator.getUnaryOperator());
				operandStack.push(tree);
			}
		} else
		{
			if (operatorStack.size() > 0)
			{
				for (ExpressionOperator stackOperator = (ExpressionOperator)operatorStack.peek(); stackOperator != null && stackOperator.getOperator().getPrecedence() >= operator.getPrecedence();)
				{
					processTree(operandStack, operatorStack);
					if (operatorStack.size() > 0)
						stackOperator = (ExpressionOperator)operatorStack.peek();
					else
						stackOperator = null;
				}

			}
			ExpressionOperator expressionOperator = new ExpressionOperator(operator, unaryOperator);
			operatorStack.push(expressionOperator);
		}
		int rtnCtr = operatorIndex + operator.getLength();
		return rtnCtr;
	}

	private int processUnaryOperator(int operatorIndex, Operator operator)
	{
		int rtnCtr = operatorIndex + operator.getSymbol().length();
		return rtnCtr;
	}

	private NextOperator processFunction(String expression, int operatorIndex, Stack operandStack)
		throws EvaluationException
	{
		int parenthesisCount = 1;
		NextOperator nextOperator = null;
		int nextOperatorIndex;
		for (nextOperatorIndex = operatorIndex; parenthesisCount > 0; nextOperatorIndex = nextOperator.getIndex())
		{
			nextOperator = getNextOperator(expression, nextOperatorIndex + 1, null);
			if (nextOperator == null)
				throw new EvaluationException("Function is not closed.");
			if (nextOperator.getOperator() instanceof OpenParenthesesOperator)
				parenthesisCount++;
			else
			if (nextOperator.getOperator() instanceof ClosedParenthesesOperator)
				parenthesisCount--;
		}

		String arguments = expression.substring(operatorIndex + 1, nextOperatorIndex);
		ExpressionOperand operand = (ExpressionOperand)operandStack.pop();
		Operator unaryOperator = operand.getUnaryOperator();
		String functionName = operand.getValue();
		try
		{
			isValidName(functionName);
		}
		catch (IllegalArgumentException iae)
		{
			throw new EvaluationException((new StringBuilder("Invalid function name of \"")).append(functionName).append("\".").toString(), iae);
		}
		Function function = (Function)functions.get(functionName);
		if (function == null)
		{
			throw new EvaluationException((new StringBuilder("A function is not defined (index=")).append(operatorIndex).append(").").toString());
		} else
		{
			ParsedFunction parsedFunction = new ParsedFunction(function, arguments, unaryOperator);
			operandStack.push(parsedFunction);
			return nextOperator;
		}
	}

	private void processTree(Stack operandStack, Stack operatorStack)
	{
		Object rightOperand = null;
		Object leftOperand = null;
		Operator operator = null;
		if (operandStack.size() > 0)
			rightOperand = operandStack.pop();
		if (operandStack.size() > 0)
			leftOperand = operandStack.pop();
		operator = ((ExpressionOperator)operatorStack.pop()).getOperator();
		ExpressionTree tree = new ExpressionTree(this, leftOperand, rightOperand, operator, null);
		operandStack.push(tree);
	}

	private String getResult(Stack operatorStack, Stack operandStack, boolean wrapStringFunctionResults)
		throws EvaluationException
	{
		String resultString = null;
		for (; operatorStack.size() > 0; processTree(operandStack, operatorStack));
		if (operandStack.size() != 1)
			throw new EvaluationException("Expression is invalid.");
		Object finalOperand = operandStack.pop();
		if (finalOperand instanceof ExpressionTree)
			resultString = ((ExpressionTree)finalOperand).evaluate(wrapStringFunctionResults);
		else
		if (finalOperand instanceof ExpressionOperand)
		{
			ExpressionOperand resultExpressionOperand = (ExpressionOperand)finalOperand;
			resultString = ((ExpressionOperand)finalOperand).getValue();
			resultString = replaceVariables(resultString);
			if (!isExpressionString(resultString))
			{
				Double resultDouble = null;
				try
				{
					resultDouble = new Double(resultString);
				}
				catch (Exception e)
				{
					throw new EvaluationException("Expression is invalid.", e);
				}
				if (resultExpressionOperand.getUnaryOperator() != null)
					resultDouble = new Double(resultExpressionOperand.getUnaryOperator().evaluate(resultDouble.doubleValue()));
				resultString = resultDouble.toString();
			} else
			if (resultExpressionOperand.getUnaryOperator() != null)
				throw new EvaluationException("Invalid operand for unary operator.");
		} else
		if (finalOperand instanceof ParsedFunction)
		{
			ParsedFunction parsedFunction = (ParsedFunction)finalOperand;
			Function function = parsedFunction.getFunction();
			String arguments = parsedFunction.getArguments();
			if (processNestedFunctions)
				arguments = processNestedFunctions(arguments);
			arguments = replaceVariables(arguments);
			try
			{
				FunctionResult functionResult = function.execute(this, arguments);
				resultString = functionResult.getResult();
				if (functionResult.getType() == 0)
				{
					Double resultDouble = new Double(resultString);
					if (parsedFunction.getUnaryOperator() != null)
						resultDouble = new Double(parsedFunction.getUnaryOperator().evaluate(resultDouble.doubleValue()));
					resultString = resultDouble.toString();
				} else
				if (functionResult.getType() == 1)
				{
					if (wrapStringFunctionResults)
						resultString = (new StringBuilder(String.valueOf(quoteCharacter))).append(resultString).append(quoteCharacter).toString();
					if (parsedFunction.getUnaryOperator() != null)
						throw new EvaluationException("Invalid operand for unary operator.");
				}
			}
			catch (FunctionException fe)
			{
				throw new EvaluationException(fe.getMessage(), fe);
			}
		} else
		{
			throw new EvaluationException("Expression is invalid.");
		}
		return resultString;
	}

	private NextOperator getNextOperator(String expression, int start, Operator match)
	{
		int numChars = expression.length();
		int numQuoteCharacters = 0;
		for (int charCtr = start; charCtr < numChars; charCtr++)
		{
			if (expression.charAt(charCtr) == quoteCharacter)
				numQuoteCharacters++;
			if (numQuoteCharacters % 2 != 1)
			{
				int numOperators = operators.size();
				for (int operatorCtr = 0; operatorCtr < numOperators; operatorCtr++)
				{
					Operator operator = (Operator)operators.get(operatorCtr);
					if (match == null || match.equals(operator))
						if (operator.getLength() == 2)
						{
							int endCtr = -1;
							if (charCtr + 2 <= expression.length())
								endCtr = charCtr + 2;
							else
								endCtr = expression.length();
							if (expression.substring(charCtr, endCtr).equals(operator.getSymbol()))
							{
								NextOperator nextOperator = new NextOperator(operator, charCtr);
								return nextOperator;
							}
						} else
						if (expression.charAt(charCtr) == operator.getSymbol().charAt(0))
						{
							NextOperator nextOperator = new NextOperator(operator, charCtr);
							return nextOperator;
						}
				}

			}
		}

		return null;
	}

	protected boolean isExpressionString(String expressionString)
		throws EvaluationException
	{
		if (expressionString.length() > 1 && expressionString.charAt(0) == quoteCharacter && expressionString.charAt(expressionString.length() - 1) == quoteCharacter)
			return true;
		if (expressionString.indexOf(quoteCharacter) >= 0)
			throw new EvaluationException("Invalid use of quotes.");
		else
			return false;
	}

	public void isValidName(String name)
		throws IllegalArgumentException
	{
		if (name.length() == 0)
			throw new IllegalArgumentException("Variable is empty.");
		char firstChar = name.charAt(0);
		if (firstChar >= '0' && firstChar <= '9')
			throw new IllegalArgumentException("A variable or function name can not start with a number.");
		if (name.indexOf('\'') > -1)
			throw new IllegalArgumentException("A variable or function name can not contain a quote character.");
		if (name.indexOf('"') > -1)
			throw new IllegalArgumentException("A variable or function name can not contain a quote character.");
		if (name.indexOf('{') > -1)
			throw new IllegalArgumentException("A variable or function name can not contain an open brace character.");
		if (name.indexOf('}') > -1)
			throw new IllegalArgumentException("A variable or function name can not contain a closed brace character.");
		if (name.indexOf('#') > -1)
			throw new IllegalArgumentException("A variable or function name can not contain a pound sign character.");
		for (Iterator operatorIterator = operators.iterator(); operatorIterator.hasNext();)
		{
			Operator operator = (Operator)operatorIterator.next();
			if (name.indexOf(operator.getSymbol()) > -1)
				throw new IllegalArgumentException("A variable or function name can not contain an operator symbol.");
		}

		if (name.indexOf("!") > -1)
			throw new IllegalArgumentException("A variable or function name can not contain a special character.");
		if (name.indexOf("~") > -1)
			throw new IllegalArgumentException("A variable or function name can not contain a special character.");
		if (name.indexOf("^") > -1)
			throw new IllegalArgumentException("A variable or function name can not contain a special character.");
		if (name.indexOf(",") > -1)
			throw new IllegalArgumentException("A variable or function name can not contain a special character.");
		else
			return;
	}

	private void loadSystemFunctions()
	{
		if (loadMathFunctions)
		{
			FunctionGroup mathFunctions = new MathFunctions();
			mathFunctions.load(this);
		}
		if (loadStringFunctions)
		{
			FunctionGroup stringFunctions = new StringFunctions();
			stringFunctions.load(this);
		}
		if (loadDateFunctions)
		{
			FunctionGroup dateFunctions = new DateFunctions();
			dateFunctions.load(this);
		}
	}

	private void loadSystemVariables()
	{
		if (loadMathVariables)
		{
			putVariable("E", (new Double(2.7182818284590451D)).toString());
			putVariable("PI", (new Double(3.1415926535897931D)).toString());
		}
	}

	public String replaceVariables(String expression)
		throws EvaluationException
	{
		int openIndex = expression.indexOf(EvaluationConstants.OPEN_VARIABLE);
		if (openIndex < 0)
			return expression;
		String replacedExpression = expression;
		for (; openIndex >= 0; openIndex = replacedExpression.indexOf(EvaluationConstants.OPEN_VARIABLE))
		{
			int closedIndex = -1;
			if (openIndex < 0)
				continue;
			closedIndex = replacedExpression.indexOf(EvaluationConstants.CLOSED_VARIABLE, openIndex + 1);
			if (closedIndex <= openIndex)
				break;
			String variableName = replacedExpression.substring(openIndex + EvaluationConstants.OPEN_VARIABLE.length(), closedIndex);
			try
			{
				isValidName(variableName);
			}
			catch (IllegalArgumentException iae)
			{
				throw new EvaluationException((new StringBuilder("Invalid variable name of \"")).append(variableName).append("\".").toString(), iae);
			}
			String variableValue = getVariableValue(variableName);
			String variableString = (new StringBuilder(String.valueOf(EvaluationConstants.OPEN_VARIABLE))).append(variableName).append(EvaluationConstants.CLOSED_VARIABLE).toString();
			replacedExpression = EvaluationHelper.replaceAll(replacedExpression, variableString, variableValue);
		}

		int openBraceIndex = replacedExpression.indexOf(EvaluationConstants.OPEN_VARIABLE);
		if (openBraceIndex > -1)
			throw new EvaluationException((new StringBuilder("A variable has not been closed (index=")).append(openBraceIndex).append(").").toString());
		else
			return replacedExpression;
	}

	protected String processNestedFunctions(String arguments)
		throws EvaluationException
	{
		StringBuffer evaluatedArguments = new StringBuffer();
		if (arguments.length() > 0)
		{
			Evaluator argumentsEvaluator = new Evaluator(quoteCharacter, loadMathVariables, loadMathFunctions, loadStringFunctions, processNestedFunctions, loadDateFunctions);
			argumentsEvaluator.setFunctions(getFunctions());
			argumentsEvaluator.setVariables(getVariables());
			argumentsEvaluator.setVariableResolver(getVariableResolver());
			ArgumentTokenizer tokenizer = new ArgumentTokenizer(arguments, ',');
			List evalautedArgumentList = new ArrayList();
			String argument;
			for (; tokenizer.hasMoreTokens(); evalautedArgumentList.add(argument))
			{
				argument = tokenizer.nextToken().trim();
				try
				{
					argument = argumentsEvaluator.evaluate(argument);
				}
				catch (Exception e)
				{
					throw new EvaluationException(e.getMessage(), e);
				}
			}

			String evaluatedArgument;
			for (Iterator evaluatedArgumentIterator = evalautedArgumentList.iterator(); evaluatedArgumentIterator.hasNext(); evaluatedArguments.append(evaluatedArgument))
			{
				if (evaluatedArguments.length() > 0)
					evaluatedArguments.append(',');
				evaluatedArgument = (String)evaluatedArgumentIterator.next();
			}

		}
		return evaluatedArguments.toString();
	}

	public boolean isLoadMathVariables()
	{
		return loadMathVariables;
	}

	public boolean getLoadMathFunctions()
	{
		return loadMathFunctions;
	}

	public boolean getLoadStringFunctions()
	{
		return loadStringFunctions;
	}

	public boolean getProcessNestedFunctions()
	{
		return processNestedFunctions;
	}
}

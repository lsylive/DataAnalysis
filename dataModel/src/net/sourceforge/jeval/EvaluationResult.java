


//    EvaluationResult.java

package net.sourceforge.jeval;


public class EvaluationResult
{

	private String result;
	private char quoteCharacter;

	public EvaluationResult(String result, char quoteCharacter)
	{
		this.result = result;
		this.quoteCharacter = quoteCharacter;
	}

	public char getQuoteCharacter()
	{
		return quoteCharacter;
	}

	public void setQuoteCharacter(char quoteCharacter)
	{
		this.quoteCharacter = quoteCharacter;
	}

	public String getResult()
	{
		return result;
	}

	public void setResult(String result)
	{
		this.result = result;
	}

	public boolean isBooleanTrue()
	{
		return result != null && "1.0".equals(result);
	}

	public boolean isBooleanFalse()
	{
		return result != null && "0.0".equals(result);
	}

	public boolean isString()
	{
		return result != null && result.length() >= 2 && result.charAt(0) == quoteCharacter && result.charAt(result.length() - 1) == quoteCharacter;
	}

	public Double getDouble()
		throws NumberFormatException
	{
		return new Double(result);
	}

	public String getUnwrappedString()
	{
		if (result != null && result.length() >= 2 && result.charAt(0) == quoteCharacter && result.charAt(result.length() - 1) == quoteCharacter)
			return result.substring(1, result.length() - 1);
		else
			return null;
	}
}

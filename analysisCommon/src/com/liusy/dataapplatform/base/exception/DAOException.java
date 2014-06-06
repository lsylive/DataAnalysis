


//   DAOException.java

package com.liusy.dataapplatform.base.exception;


public class DAOException extends RuntimeException
{

	public DAOException()
	{
	}

	public DAOException(String s)
	{
		super(s);
	}

	public DAOException(Exception e)
	{
		super(e.getMessage());
	}
}

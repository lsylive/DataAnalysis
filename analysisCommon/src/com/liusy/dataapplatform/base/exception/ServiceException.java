


//   ServiceException.java

package com.liusy.dataapplatform.base.exception;


public class ServiceException extends RuntimeException
{

	public ServiceException()
	{
	}

	public ServiceException(String s)
	{
		super(s);
	}

	public ServiceException(Exception e)
	{
		super(e.getMessage());
	}
}

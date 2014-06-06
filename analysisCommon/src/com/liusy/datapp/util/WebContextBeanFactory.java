


//   WebContextBeanFactory.java

package com.liusy.datapp.util;

import java.io.Serializable;
import javax.servlet.ServletContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class WebContextBeanFactory
	implements Serializable
{

	private static WebApplicationContext context;

	public WebContextBeanFactory()
	{
	}

	public static void setWebContext(WebApplicationContext ctx)
	{
		context = ctx;
	}

	public static void setWebContextByServletContext(ServletContext sctx)
	{
		context = WebApplicationContextUtils.getRequiredWebApplicationContext(sctx);
	}

	public static Object getBean(String beanName)
	{
		if (context == null)
			throw new IllegalStateException("ApplicationContext Not Initalized!");
		if (beanName != null && beanName.trim().length() == 0)
			throw new IllegalArgumentException("Illegal BeanName");
		else
			return context.getBean(beanName);
	}
}

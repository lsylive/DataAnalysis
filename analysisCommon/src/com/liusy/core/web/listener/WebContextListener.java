


//   WebContextListener.java

package com.liusy.core.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.ContextLoaderListener;

public class WebContextListener extends ContextLoaderListener
	implements ServletContextListener, HttpSessionListener
{

	private static final Log log = LogFactory.getLog(WebContextListener.class);

	public WebContextListener()
	{
	}

	public void contextInitialized(ServletContextEvent event)
	{
		if (log.isDebugEnabled())
			log.debug("initializing context...");
		super.contextInitialized(event);
	}

	public void sessionCreated(HttpSessionEvent httpsessionevent)
	{
	}

	public void sessionDestroyed(HttpSessionEvent httpsessionevent)
	{
	}

}

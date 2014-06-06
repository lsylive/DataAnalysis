


//   WebContextListener.java

package com.liusy.dataapplatform.listener;

import com.liusy.datapp.util.WebContextBeanFactory;
import javax.servlet.*;
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
		//setupContext(event.getServletContext());
	}

	public void sessionCreated(HttpSessionEvent httpsessionevent)
	{
	}

	public void sessionDestroyed(HttpSessionEvent httpsessionevent)
	{
	}

	private void setupContext(ServletContext context)
	{
		WebContextBeanFactory.setWebContextByServletContext(context);
	}

}

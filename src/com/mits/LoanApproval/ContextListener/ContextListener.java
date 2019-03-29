package com.mits.LoanApproval.ContextListener;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.PropertyConfigurator;

public class ContextListener implements ServletContextListener 
{
	@Override
	public void contextDestroyed(ServletContextEvent arg0) 
	{
		
	}

	@Override
	public void contextInitialized(ServletContextEvent event) 
	{
		ServletContext context = event.getServletContext();
		String log4jConfigFile = context.getInitParameter("log4j-config-location");
		String fullPath = context.getRealPath("") + File.separator + log4jConfigFile;
		
		PropertyConfigurator.configure(fullPath);
	}
}




//   ThunisoftActionServlet.java

package com.liusy.core.web.servlet;

import java.io.File;
import javax.servlet.*;
import org.apache.struts.action.ActionServlet;

public class ThunisoftActionServlet extends ActionServlet
{

	protected String configPath;

	public ThunisoftActionServlet()
	{
	}

	protected void initOther()
		throws ServletException
	{
		super.initOther();
		String value = getServletConfig().getInitParameter("configLocation");
		if (value != null)
		{
			configPath = value;
			if (config == null)
				config = findFileNames(configPath);
			else
			if ("".equals(config))
				config = findFileNames(configPath);
			else
				config = (new StringBuilder(String.valueOf(config))).append(",").append(findFileNames(configPath)).toString();
		}
	}

	private String findFileNames(String path)
	{
		String fileNames = "";
		String filePaths[] = path.split(",");
		if (filePaths == null)
			return "";
		for (int i = 0; i < filePaths.length; i++)
		{
			String fs = findPathFiles(filePaths[i]);
			if (fileNames.equals(""))
				fileNames = fs;
			else
				fileNames = (new StringBuilder(String.valueOf(fileNames))).append(",").append(fs).toString();
		}

		return fileNames;
	}

	private String findPathFiles(String path)
	{
		String fileNames = "";
		String realPath = getServletContext().getRealPath(path);
		if (!realPath.endsWith("/") && !realPath.endsWith("\\"))
			realPath = (new StringBuilder(String.valueOf(realPath))).append("/").toString();
		File file = new File(realPath);
		String fnames[] = file.list();
		if (fnames == null)
			return "";
		for (int i = 0; i < fnames.length; i++)
			if (fnames[i].endsWith(".xml"))
			{
				fileNames = (new StringBuilder(String.valueOf(fileNames))).append(path).toString();
				if (!path.endsWith("/"))
					fileNames = (new StringBuilder(String.valueOf(fileNames))).append("/").append(fnames[i]).append(",").toString();
				else
					fileNames = (new StringBuilder(String.valueOf(fileNames))).append(fnames[i]).append(",").toString();
			}

		if (fileNames.endsWith(","))
			fileNames = fileNames.substring(0, fileNames.length() - 1);
		return fileNames;
	}
}

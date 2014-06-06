


//   ClassLoaderUtil.java

package com.liusy.core.util;

import java.io.FileInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ClassLoaderUtil
{

	private static Log log = LogFactory.getLog(ClassLoaderUtil.class);

	public ClassLoaderUtil()
	{
	}

	public static String getRelativeClassFilePath(String relativePath)
	{
		if (!relativePath.contains("../"))
		{
			return ClassLoaderUtil.getResource(relativePath).getPath();
		} else
		{
			String absolutePath = getAbsolutePathOfClassLoaderClassPath();
			String wildcardString = relativePath.substring(0, relativePath.lastIndexOf("../") + 3);
			String filename = relativePath.substring(relativePath.lastIndexOf("../") + 3);
			int num = CountSubFolder(wildcardString, "../");
			String retpath = (new StringBuilder(String.valueOf(getAbsoultePathByRelative(absolutePath, "/", num)))).append(filename).toString();
			return retpath;
		}
	}

	public static String getAbsolutePathOfClassLoaderClassPath()
	{
		return ClassLoaderUtil.getResource("/").getPath();
	}

	private static int CountSubFolder(String source, String separate)
	{
		int countnum = 0;
		int sepalength = separate.length();
		for (; source.contains(separate); source = source.substring(sepalength))
			countnum++;

		return countnum;
	}

	private static String getAbsoultePathByRelative(String source, String separate, int num)
	{
		if (source.endsWith("/"))
			source = source.substring(0, source.length() - 1);
		for (int i = 0; i < num; i++)
			if (source.contains(separate))
				source = source.substring(0, source.lastIndexOf(separate));
			else
				source = "";

		return (new StringBuilder(String.valueOf(source))).append(separate).toString();
	}

	public static Properties loadProperties(String relativePath)
		throws Exception
	{
		String path = getRelativeClassFilePath(relativePath);
		Properties prop = new Properties();
		prop.load(new FileInputStream(path));
		return prop;
	}

	public static String getExtendResource(String relativePath)
		throws MalformedURLException
	{
		if (!relativePath.contains("../"))
			return getResource(relativePath).getPath();
		String classPathAbsolutePath = getAbsolutePathOfClassLoaderClassPath();
		if (relativePath.substring(0, 1).equals("/"))
			relativePath = relativePath.substring(1);
		String wildcardString = relativePath.substring(0, relativePath.lastIndexOf("../") + 3);
		relativePath = relativePath.substring(relativePath.lastIndexOf("../") + 3);
		int containSum = CountSubFolder(wildcardString, "../");
		classPathAbsolutePath = getAbsoultePathByRelative(classPathAbsolutePath, "/", containSum);
		String resourceAbsolutePath = (new StringBuilder(String.valueOf(classPathAbsolutePath))).append(relativePath).toString();
		return resourceAbsolutePath;
	}

	public static URL getResource(String resource)
	{
		return getClassLoader().getResource(resource);
	}

	public static ClassLoader getClassLoader()
	{
		return ClassLoaderUtil.getClassLoader();
	}

}

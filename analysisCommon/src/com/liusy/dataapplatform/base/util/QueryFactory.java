


//   QueryFactory.java

package com.liusy.dataapplatform.base.util;

import java.io.*;
import java.net.URL;
import java.util.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.InitializingBean;

public class QueryFactory
	implements InitializingBean
{

	private String xmlConfigPath;
	private static Log log = LogFactory.getLog(QueryFactory.class);
	private static Map queryMap;

	private QueryFactory()
	{
		xmlConfigPath = "";
		try
		{
			queryMap = new HashMap();
			String xmlpath = xmlConfigPath;
			if (xmlpath == null || "".equals(xmlpath))
			{
				xmlpath = getClass().getClassLoader().getResource("/").getPath();
				int pos = xmlpath.indexOf("classes");
				xmlpath = (new StringBuilder(String.valueOf(xmlpath.substring(0, pos)))).append("config/queryConfig").toString();
			}
			File file = new File(xmlpath);
			if (!file.isDirectory())
				throw new Exception((new StringBuilder("XML PATH:")).append(xmlpath).append(" is not a Directory.").toString());
			File files[] = file.listFiles();
			for (int i = 0; i < files.length; i++)
			{
				File subfile = files[i];
				if (subfile.getName().endsWith("xml"))
					parseXML(subfile);
			}

		}
		catch (Exception e)
		{
			log.error(e);
			System.out.println(e.getMessage());
		}
	}

	public void parseXML(String xmlfile)
		throws Exception
	{
		if (xmlfile == null || xmlfile.trim().length() == 0)
		{
			throw new IllegalArgumentException("No xml files!");
		} else
		{
			Document document = (new SAXReader()).read(new File(xmlfile));
			putQueryMap(document);
			return;
		}
	}

	public void parseXML(File file)
		throws Exception
	{
		if (file == null || !file.isFile())
		{
			throw new IllegalArgumentException("xml file missing!");
		} else
		{
			Document document = (new SAXReader()).read(file);
			putQueryMap(document);
			return;
		}
	}

	public void parseXML(InputStream is)
		throws Exception
	{
		if (is == null)
		{
			throw new IllegalArgumentException("parseXML(InputStream is null)!");
		} else
		{
			Document document = (new SAXReader()).read(is);
			putQueryMap(document);
			return;
		}
	}

	private void putQueryMap(Document document)
		throws Exception
	{
		Element root = document.getRootElement();
		String id;
		String sql;
		for (Iterator iter = root.elementIterator("SQLSCRIPT"); iter.hasNext(); queryMap.put(id, sql))
		{
			Element element = (Element)iter.next();
			id = element.attributeValue("ID");
			if (queryMap.containsKey(id))
				throw new Exception((new StringBuilder()).append("�ظ���selectId:").append(id).toString());
			sql = element.elementText("QUERY");
			if (sql.indexOf("&lt;") > -1)
				sql = sql.replaceAll("&lt;", "<");
			else
			if (sql.indexOf("&gt;") > -1)
				sql = sql.replaceAll("&gt;", ">");
		}

	}

	public String getQuery(String selectId)
	{
		if (selectId != null && selectId.trim().length() > 0 && queryMap.containsKey(selectId))
			return (String)queryMap.get(selectId);
		else
			return null;
	}

	public static void main(String args[])
		throws Exception
	{
	}

	public void afterPropertiesSet()
		throws Exception
	{
	}

}

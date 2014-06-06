


//   CsvGenerator.java

package com.liusy.dataapplatform.base.util;

import com.liusy.datapp.util.poolobj.ColumnPoolObj;
import java.io.*;
import java.util.*;
import org.supercsv.io.*;
import org.supercsv.prefs.CsvPreference;

public class CsvGenerator
{

	public CsvGenerator()
	{
	}

	public static int ReadFile(InputStream inputStream, Map columnMap, List columnResultList)
	{
		ICsvListReader reader = new CsvListReader(new InputStreamReader(inputStream), CsvPreference.STANDARD_PREFERENCE);
		int pos = 0;
		try
		{
			String header[] = reader.getCSVHeader(true);
			if (header == null || header.length == 0)
				throw new Exception("没有描述头，无法正常导入");
			List colList = new ArrayList();
			List ecolList = new ArrayList();
			for (int k = 0; k < header.length; k++)
			{
				ColumnPoolObj column = (ColumnPoolObj)columnMap.get(header[k].toUpperCase());
				if (column == null)
					column = (ColumnPoolObj)columnMap.get(header[k].toLowerCase());
				if (column != null)
				{
					colList.add(Integer.valueOf(k));
					ecolList.add(column);
				}
			}

			for (List resultlist = new ArrayList(); (resultlist = reader.read()) != null;)
			{
				pos++;
				Map resultMap = new HashMap();
				for (int j = 0; j < colList.size(); j++)
				{
					ColumnPoolObj col = (ColumnPoolObj)ecolList.get(j);
					resultMap.put(col.getName(), (String)resultlist.get(((Integer)colList.get(j)).intValue()));
				}

				columnResultList.add(resultMap);
			}

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return pos;
	}

	public static void WriteFile(PrintWriter pwriter, String header[], List resultList)
	{
		try
		{
			ICsvListWriter writer = new CsvListWriter(pwriter, CsvPreference.STANDARD_PREFERENCE);
			writer.writeHeader(header);
			String strArr[];
			for (Iterator iterator = resultList.iterator(); iterator.hasNext(); writer.write(strArr))
				strArr = (String[])iterator.next();

			writer.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}

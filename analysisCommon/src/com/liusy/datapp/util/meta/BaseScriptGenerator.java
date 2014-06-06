


//   BaseScriptGenerator.java

package com.liusy.datapp.util.meta;

import java.io.PrintStream;
import java.util.*;

// Referenced classes of package com.liusy.datapp.util.meta:
//			SqlScriptGenerator

public abstract class BaseScriptGenerator
	implements SqlScriptGenerator
{

	protected String indentity;

	public BaseScriptGenerator()
	{
		indentity = "";
	}

	public String generateAlterTableScript(List columnList)
	{
		return null;
	}

	public String generateCreateTableScript(List columnList, String dbtableName)
	{
		Iterator iter = columnList.iterator();
		StringBuffer createBuffer = new StringBuffer();
		String createtableSql = (new StringBuilder("create table ")).append(dbtableName).append(" (").toString();
		HashMap columnMap;
		for (; iter.hasNext(); createBuffer.append((new StringBuilder(String.valueOf(FilterDataTypeByMetaType(columnMap)))).append(",").toString()))
			columnMap = (HashMap)iter.next();

		createtableSql = (new StringBuilder(String.valueOf(createtableSql))).append(createBuffer.substring(0, createBuffer.length() - 1)).append(")").toString();
		System.out.println((new StringBuilder("Create Table sql=")).append(createtableSql).toString());
		return createtableSql;
	}

	public String generateDeleteTableScript(String tableName)
	{
		return (new StringBuilder("delete from ")).append(tableName).toString();
	}

	public String getColumnCreateSql(HashMap columnMap)
	{
		String columnName = (String)columnMap.get("columnName");
		return (new StringBuilder(String.valueOf(columnName))).append(" ").append(FilterDataTypeByMetaType(columnMap)).toString();
	}

	public String getPrimaryKeySql(List primaryList, String tableName)
	{
		Iterator iter = primaryList.iterator();
		StringBuffer buffer = new StringBuffer();
		String constraintName = "";
		HashMap pMap;
		for (; iter.hasNext(); buffer.append((new StringBuilder(String.valueOf((String)pMap.get("columnName")))).append(",").toString()))
		{
			pMap = (HashMap)iter.next();
			constraintName = (String)pMap.get("constriantName");
		}

		String constraintCol = buffer.substring(buffer.length() - 1);
		String createSql = (new StringBuilder("alter table ")).append(tableName).append(" add constraint ").append(constraintName).append(" primary key (").append(constraintCol).append(");").toString();
		return createSql;
	}

	public String generateForeignKeyScript(List foriegnList, String tableName)
	{
		return null;
	}

	public abstract String FilterDataTypeByMetaType(Map map);

	public abstract String generateDropTableScript(String s);

	public abstract String generateDropTableColumnScript(String s, Map map);

	public abstract String isDbExistSql(String s);
}

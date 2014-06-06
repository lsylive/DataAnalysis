


//   OracleScriptGenerator.java

package com.liusy.datapp.util.meta;

import com.liusy.core.util.Const;
import java.util.*;

// Referenced classes of package com.liusy.datapp.util.meta:
//			BaseScriptGenerator, SqlScriptGenerator

public class OracleScriptGenerator extends BaseScriptGenerator
	implements SqlScriptGenerator
{

	public OracleScriptGenerator()
	{
	}

	public String generateAlterTableScript(List columnList)
	{
		for (Iterator iter = columnList.iterator(); iter.hasNext(););
		return null;
	}

	public String generateCreateTableScript(List columnList, String dbtableName)
	{
		return super.generateCreateTableScript(columnList, dbtableName);
	}

	public String getColumnCreateSql(HashMap columnMap)
	{
		return super.getColumnCreateSql(columnMap);
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
		Iterator iter = foriegnList.iterator();
		StringBuffer buffer = new StringBuffer();
		String constraintName = "";
		String createSql;
		for (; iter.hasNext(); buffer.append(createSql))
		{
			HashMap pMap = (HashMap)iter.next();
			constraintName = (String)pMap.get("constriantName");
			String columnName = (String)pMap.get("columnName");
			String rtableName = (String)pMap.get("rtableName");
			String rcolumnName = (String)pMap.get("rcolumnName");
			createSql = (new StringBuilder("alter table ")).append(tableName).append(" add constraint ").append(constraintName).append(" foreign key (").append(columnName).append(") references ").append(rtableName).append("(").append(rcolumnName).append(");").toString();
		}

		return buffer.toString();
	}

	public String FilterDataTypeByMetaType(Map columnMap)
	{
		String columnName = (String)columnMap.get("columnName");
		String metaType = (String)columnMap.get("columnType");
		int len = 0;
		if (columnMap.get("columnLength") != null && !"".equals(columnMap.get("columnLength")))
			len = Integer.parseInt((String)columnMap.get("columnLength"));
		String length = (String)columnMap.get("columnLength");
		String isnull = (String)columnMap.get("isNullable");
		String precise = (String)columnMap.get("precise");
		String dataType = (new StringBuilder(String.valueOf(columnName))).append(" ").toString();
		if (metaType.equals(Const.META_TYPE_STRING))
		{
			if (len > 2)
				dataType = (new StringBuilder(String.valueOf(dataType))).append("VARCHAR2(").append(length).append(")").toString();
			else
			if (len == 1)
				dataType = (new StringBuilder(String.valueOf(dataType))).append("CHAR(1)").toString();
			else
				dataType = (new StringBuilder(String.valueOf(dataType))).append("CHAR(200)").toString();
		} else
		if (metaType.equals(Const.META_TYPE_NUMERIC))
		{
			if (precise != null && !"".equals(precise))
				dataType = (new StringBuilder(String.valueOf(dataType))).append("NUMERIC(").append(length).append(",").append(precise).append(")").toString();
			else
				dataType = (new StringBuilder(String.valueOf(dataType))).append("INTEGER").toString();
		} else
		if (metaType.equals(Const.META_TYPE_DATE))
			dataType = (new StringBuilder(String.valueOf(dataType))).append("DATE").toString();
		else
			dataType = (new StringBuilder(String.valueOf(dataType))).append("VARCHAR2(2000)").toString();
		if (isnull.equalsIgnoreCase("N"))
			dataType = (new StringBuilder(String.valueOf(dataType))).append(" not null").toString();
		return dataType;
	}

	public String generateDropTableColumnScript(String dbtableName, Map columnMap)
	{
		String dropsql = "";
		if (columnMap != null)
			dropsql = (new StringBuilder("alert table ")).append(dbtableName).append(" drop ").append((String)columnMap.get("columnName")).toString();
		return dropsql;
	}

	public String generateDropTableScript(String dbtableName)
	{
		String dropsql = (new StringBuilder("drop table ")).append(dbtableName).append(" purge").toString();
		return dropsql;
	}

	public String isDbExistSql(String tableName)
	{
		String sql = (new StringBuilder("select count(*) from user_objects  where object_type='TABLE' and OBJECT_NAME='")).append(tableName.toUpperCase()).append("'").toString();
		return sql;
	}

	public String gererateGetTableFieldNamesSql(String tableName)
	{
		String sql = (new StringBuilder("select COLUMN_NAME from user_col_comments where upper(TABLE_NAME) ='")).append(tableName.toUpperCase()).append("'").toString();
		return sql;
	}
}

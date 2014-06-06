


//   SqlScriptGenerator.java

package com.liusy.datapp.util.meta;

import java.util.*;

public interface SqlScriptGenerator
{

	public abstract String generateCreateTableScript(List list, String s);

	public abstract String generateAlterTableScript(List list);

	public abstract String generateDropTableScript(String s);

	public abstract String generateDropTableColumnScript(String s, Map map);

	public abstract String getColumnCreateSql(HashMap hashmap);

	public abstract String generateForeignKeyScript(List list, String s);

	public abstract String getPrimaryKeySql(List list, String s);

	public abstract String generateDeleteTableScript(String s);

	public abstract String isDbExistSql(String s);

	public abstract String gererateGetTableFieldNamesSql(String s);
}

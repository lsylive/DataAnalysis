


//   BaseSqlGen.java

package com.liusy.dataapplatform.base.util;

import java.util.List;

// Referenced classes of package com.liusy.dataapplatform.base.util:
//			SqlParam, PagerObject, PageQuery, QueryParam

public interface BaseSqlGen
{

	public abstract String generateSql(SqlParam sqlparam);

	public abstract String generateCountSql(String s);

	public abstract String generatePageSql(String s, SqlParam sqlparam);

	public abstract String generateComplexPageSql(SqlParam sqlparam, List list, PagerObject pagerobject);

	public abstract String generateComplexCountSql(SqlParam sqlparam, List list);

	public abstract String generateSqlBySelectId(String s, PageQuery pagequery);

	public abstract String generatePageSql(String s, PageQuery pagequery);

	public abstract String getQueryStringPart(List list, String s);

	public abstract String getQueryStringPart(List list);

	public abstract String getQueryString(List list, String s);

	public abstract String getQueryStringByDiffOper(List list);

	public abstract String toSQLWithType(QueryParam queryparam);

	public abstract String generatePageSqlById(String s, PageQuery pagequery, int i);

	public abstract String generateMinIdSql(String s);
}

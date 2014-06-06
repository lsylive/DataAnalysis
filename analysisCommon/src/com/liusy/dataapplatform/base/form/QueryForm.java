


//   QueryForm.java

package com.liusy.dataapplatform.base.form;

import com.liusy.dataapplatform.base.util.PageQuery;
import java.util.HashMap;
import java.util.Map;

// Referenced classes of package com.liusy.dataapplatform.base.form:
//			BaseForm

public class QueryForm extends BaseForm
{

	private static final long serialVersionUID = 1L;
	private Map<String, String> record;
	private PageQuery query;
	private String itemIds[];

	public QueryForm()
	{
	}

	public String[] getItemIds()
	{
		return itemIds;
	}

	public void setItemIds(String itemIds[])
	{
		this.itemIds = itemIds;
	}

	public PageQuery getQuery()
	{
		if (query == null)
			query = new PageQuery();
		return query;
	}

	public void setQuery(PageQuery query)
	{
		this.query = query;
	}

	public Map<String,String> getRecord()
	{
		if (record == null)
			record = new HashMap();
		return record;
	}

	public void setRecord(Map record)
	{
		this.record = record;
	}
}

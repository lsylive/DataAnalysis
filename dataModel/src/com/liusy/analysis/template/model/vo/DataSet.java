
package com.liusy.analysis.template.model.vo;

import java.util.List;
import java.util.Map;

public class DataSet
{

	private int id;
	private List<Map<String, Object>> resultSet;

	public DataSet()
	{
	}

	public DataSet(int id, List<Map<String, Object>> resultSet)
	{
		this.id = id;
		this.resultSet = resultSet;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}


	  public List<Map<String, Object>> getResultSet() {
	    return this.resultSet;
	  }

	  public void setResultSet(List<Map<String, Object>> resultSet) {
	    this.resultSet = resultSet;
	  }
}

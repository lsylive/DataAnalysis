


//   SubjectConfigPoolObj.java

package com.liusy.datapp.util.poolobj;

import java.io.Serializable;
import java.util.List;

public class SubjectConfigPoolObj
	implements Serializable
{

	private static final long serialVersionUID = 1L;
	private String subName;
	private String subCode;
	private String subDesc;
	private String cityId;
	private String cityName;
	private String scId;
	private String subType;
	private List tableMapList;

	public SubjectConfigPoolObj()
	{
	}

	public String getSubCode()
	{
		return subCode;
	}

	public void setSubCode(String subCode)
	{
		this.subCode = subCode;
	}

	public String getSubDesc()
	{
		return subDesc;
	}

	public void setSubDesc(String subDesc)
	{
		this.subDesc = subDesc;
	}

	public String getSubName()
	{
		return subName;
	}

	public void setSubName(String subName)
	{
		this.subName = subName;
	}

	public List getTableMapList()
	{
		return tableMapList;
	}

	public void setTableMapList(List tableMapList)
	{
		this.tableMapList = tableMapList;
	}

	public String getCityId()
	{
		return cityId;
	}

	public void setCityId(String cityId)
	{
		this.cityId = cityId;
	}

	public String getCityName()
	{
		return cityName;
	}

	public void setCityName(String cityName)
	{
		this.cityName = cityName;
	}

	public String getScId()
	{
		return scId;
	}

	public void setScId(String scId)
	{
		this.scId = scId;
	}

	public String getSubType()
	{
		return subType;
	}

	public void setSubType(String subType)
	{
		this.subType = subType;
	}
}

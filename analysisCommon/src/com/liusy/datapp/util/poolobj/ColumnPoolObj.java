


//   ColumnPoolObj.java

package com.liusy.datapp.util.poolobj;

import java.io.Serializable;

public class ColumnPoolObj
	implements Serializable
{

	private String id;
	private String securityLevel;
	private String dataType;
	private String name;
	private String cnName;
	private String indicatorId;
	private String codesetId;
	private String isPrimary;
	private String isNull;
	private String dataPercise;
	private String dataLength;

	public ColumnPoolObj()
	{
	}

	public String getCnName()
	{
		return cnName;
	}

	public void setCnName(String cnName)
	{
		this.cnName = cnName;
	}

	public String getCodesetId()
	{
		return codesetId;
	}

	public void setCodesetId(String codesetId)
	{
		this.codesetId = codesetId;
	}

	public String getDataType()
	{
		return dataType;
	}

	public void setDataType(String dataType)
	{
		this.dataType = dataType;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getIndicatorId()
	{
		return indicatorId;
	}

	public void setIndicatorId(String indicatorId)
	{
		this.indicatorId = indicatorId;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getSecurityLevel()
	{
		return securityLevel;
	}

	public void setSecurityLevel(String securityLevel)
	{
		this.securityLevel = securityLevel;
	}

	public String getIsPrimary()
	{
		return isPrimary;
	}

	public void setIsPrimary(String isPrimary)
	{
		this.isPrimary = isPrimary;
	}

	public String getDataPercise()
	{
		return dataPercise;
	}

	public void setDataPercise(String dataPercise)
	{
		this.dataPercise = dataPercise;
	}

	public String getIsNull()
	{
		return isNull;
	}

	public void setIsNull(String isNull)
	{
		this.isNull = isNull;
	}

	public String getDataLength()
	{
		return dataLength;
	}

	public void setDataLength(String dataLength)
	{
		this.dataLength = dataLength;
	}

	public String toString()
	{
		StringBuffer buffer = new StringBuffer();
		buffer.append("[ColumnPoolObj:");
		buffer.append(" id: ");
		buffer.append(id);
		buffer.append(" securityLevel: ");
		buffer.append(securityLevel);
		buffer.append(" dataType: ");
		buffer.append(dataType);
		buffer.append(" name: ");
		buffer.append(name);
		buffer.append(" cnName: ");
		buffer.append(cnName);
		buffer.append(" indicatorId: ");
		buffer.append(indicatorId);
		buffer.append(" codesetId: ");
		buffer.append(codesetId);
		buffer.append(" isPrimary: ");
		buffer.append(isPrimary);
		buffer.append(" isNull: ");
		buffer.append(isNull);
		buffer.append(" dataPercise: ");
		buffer.append(dataPercise);
		buffer.append(" dataLength: ");
		buffer.append(dataLength);
		buffer.append("]");
		return buffer.toString();
	}
}

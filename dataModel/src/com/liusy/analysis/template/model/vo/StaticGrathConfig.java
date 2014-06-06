
package com.liusy.analysis.template.model.vo;

import com.liusy.analysis.template.model.Consts;
import java.io.Serializable;
import java.util.Date;

public class StaticGrathConfig
	implements Cloneable, Serializable
{

	private static final long serialVersionUID = 1L;
	private int recordCount;
	private int templateType;
	private String grathTitle;
	private String axisTitle;
	private String columnName;
	private String dataType;
	private int axisStart;
	private int axisEnd;
	private Date axisStartDate;
	private Date axisEndDate;
	private int stepLength;

	public StaticGrathConfig()
	{
		recordCount = 0;
		templateType = 0;
		grathTitle = "";
		axisTitle = "";
		columnName = "";
		dataType = Consts.DATATYPE_STRING;
		axisStart = 0;
		axisEnd = 0;
		axisStartDate = new Date();
		axisEndDate = new Date();
		stepLength = 0;
	}

	public Object clone()
		throws CloneNotSupportedException
	{
		return super.clone();
	}

	public boolean equals(Object obj)
	{
		if (obj == null)
			return false;
		if (this == obj)
			return true;
		if (obj.getClass() != getClass())
			return false;
		StaticGrathConfig other = (StaticGrathConfig)obj;
		return recordCount == other.recordCount && templateType == other.templateType && getGrathTitle().equals(other.getGrathTitle()) && getAxisTitle().equals(other.getAxisTitle()) && getAxisEnd() == other.getAxisEnd() && getAxisStart() == other.getAxisStart() && getColumnName().equals(other.getColumnName()) && getStepLength() == other.getStepLength();
	}

	public int getTemplateType()
	{
		return templateType;
	}

	public void setTemplateType(int templateType)
	{
		this.templateType = templateType;
	}

	public int getRecordCount()
	{
		return recordCount;
	}

	public void setRecordCount(int recordCount)
	{
		this.recordCount = recordCount;
	}

	public String getGrathTitle()
	{
		return grathTitle;
	}

	public void setGrathTitle(String grathTitle)
	{
		this.grathTitle = grathTitle;
	}

	public String getAxisTitle()
	{
		return axisTitle;
	}

	public void setAxisTitle(String axisTitle)
	{
		this.axisTitle = axisTitle;
	}

	public String getColumnName()
	{
		return columnName;
	}

	public void setColumnName(String columnName)
	{
		this.columnName = columnName;
	}

	public int getAxisStart()
	{
		return axisStart;
	}

	public void setAxisStart(int axisStart)
	{
		this.axisStart = axisStart;
	}

	public int getAxisEnd()
	{
		return axisEnd;
	}

	public void setAxisEnd(int axisEnd)
	{
		this.axisEnd = axisEnd;
	}

	public Date getAxisStartDate()
	{
		return axisStartDate;
	}

	public void setAxisStartDate(Date axisStartDate)
	{
		this.axisStartDate = axisStartDate;
	}

	public Date getAxisEndDate()
	{
		return axisEndDate;
	}

	public void setAxisEndDate(Date axisEndDate)
	{
		this.axisEndDate = axisEndDate;
	}

	public int getStepLength()
	{
		return stepLength;
	}

	public void setStepLength(int stepLength)
	{
		this.stepLength = stepLength;
	}

	public String getDataType()
	{
		return dataType;
	}

	public void setDataType(String dataType)
	{
		this.dataType = dataType;
	}
}

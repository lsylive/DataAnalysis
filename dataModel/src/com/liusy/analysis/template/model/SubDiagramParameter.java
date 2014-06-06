
package com.liusy.analysis.template.model;

import java.io.Serializable;


public class SubDiagramParameter extends DiagramParameter
	implements Cloneable, Serializable
{

	private static final long serialVersionUID = 1L;
	protected String fatherParameter;

	public SubDiagramParameter()
	{
		fatherParameter = "";
	}

	public SubDiagramParameter(DiagramParameter dp)
	{
		fatherParameter = "";
		cnName = dp.getCnName();
		codeSet = dp.getCodeSet();
		dataType = dp.getDataType();
		description = dp.getDescription();
		editFlg = dp.getEditFlg();
		id = dp.getId();
		length = dp.getLength();
		name = dp.getName();
		polyLineFlag = dp.getPolyLineFlag();
		precision = dp.getPrecision();
	}

	public Object clone()
	{
		return super.clone();
	}

	public String getFatherParameter()
	{
		return fatherParameter;
	}

	public void setFatherParameter(String fatherParameter)
	{
		this.fatherParameter = fatherParameter;
	}
}

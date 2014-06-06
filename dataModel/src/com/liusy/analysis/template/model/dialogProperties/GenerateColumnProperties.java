
package com.liusy.analysis.template.model.dialogProperties;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.liusy.analysis.template.model.vo.GenerateDataField;

public class GenerateColumnProperties
	implements Cloneable, Serializable
{

	private static final long serialVersionUID = 1L;
	private List<GenerateDataField> fields = new ArrayList<GenerateDataField>();

	public Object clone()
		throws CloneNotSupportedException
	{
		return super.clone();
	}

	  public List<GenerateDataField> getFields() {
		    return this.fields;
		  }

	public void setFields(List<GenerateDataField> fields)
	{
		this.fields = fields;
	}

	public boolean equals(Object obj)
	{
		if (obj == null)
			return false;
		if (this == obj)
			return true;
		if (obj.getClass() != getClass())
			return false;
		GenerateColumnProperties other = (GenerateColumnProperties)obj;
		return getFields().equals(other.getFields());
	}
}

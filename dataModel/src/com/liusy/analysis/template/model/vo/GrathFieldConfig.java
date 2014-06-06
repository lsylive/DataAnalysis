
package com.liusy.analysis.template.model.vo;

import com.liusy.analysis.template.model.base.Metadata;
import java.io.Serializable;

// Referenced classes of package com.liusy.analysis.template.model.vo:
//			FieldConfig

public class GrathFieldConfig extends FieldConfig
	implements Cloneable, Serializable
{

	private static final long serialVersionUID = 1L;
	protected String axis;

	public GrathFieldConfig()
	{
		axis = "";
	}

	public GrathFieldConfig(Metadata md)
	{
		axis = "";
		cnName = md.getCnName();
		name = md.getName();
	}

	public GrathFieldConfig clone()
	{
		GrathFieldConfig fc = new GrathFieldConfig();
		fc.setAlign(align);
		fc.setCnName(cnName);
		fc.setDataFormat(dataFormat);
		fc.setName(name);
		fc.setVisible(visible);
		fc.setWidth(width);
		fc.setCodeSet(codeSet);
		fc.setDataType(dataType);
		fc.setDetailLink(detailLink);
		fc.setAxis(axis);
		return fc;
	}

	public String toXML()
	{
		return super.toXML();
	}

	public String getAxis()
	{
		return axis;
	}

	public void setAxis(String axis)
	{
		this.axis = axis;
	}


}

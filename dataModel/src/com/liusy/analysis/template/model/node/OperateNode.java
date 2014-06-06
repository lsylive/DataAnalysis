
package com.liusy.analysis.template.model.node;

import java.util.List;
import java.util.Map;

import com.liusy.analysis.template.model.base.Metadata;
import com.liusy.analysis.template.model.dialogProperties.OperateNodeBean;
import com.liusy.analysis.template.model.vo.DataSet;
import com.liusy.analysis.template.model.vo.FieldConfig;


public class OperateNode extends  Node<Object>
implements INode
{

	private static final long serialVersionUID = 0xc1dc339bf4815489L;
	public static final String EXTR_PROPERTIES = "EXTRPROPERTIES";
	protected String name;
	protected OperateNodeBean extrProperties;

	public OperateNode()
	{
		name = "操作";
		extrProperties = new OperateNodeBean();
	}

	public OperateNodeBean getExtrProperties()
	{
		return extrProperties;
	}

	public void setExtrProperties(OperateNodeBean extrProperties)
	{
		if (this.extrProperties.equals(extrProperties))
		{
			return;
		} else
		{
			this.extrProperties = extrProperties;
			return;
		}
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		if (this.name.equals(name))
		{
			return;
		} else
		{
			this.name = name;
			System.out.println("call node rename");
			firePropertyChange("NAME", null, name);
			return;
		}
	}
	  public List<Metadata> getMeta() {
		    return null;
		  }

		  public List<Map<String, Object>> run(List<DataSet> inputDataSets)
		  {
		    return null;
		  }

		  public List<FieldConfig> getFieldConfigs()
		  {
		    return null;
		  }

	protected Object clone()
		throws CloneNotSupportedException
	{
		return super.clone();
	}

	public String getExtraConfigs()
	{
		return null;
	}
}

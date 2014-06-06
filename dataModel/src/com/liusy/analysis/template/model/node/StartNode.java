
package com.liusy.analysis.template.model.node;

import java.util.List;
import java.util.Map;

import com.liusy.analysis.template.model.base.Metadata;
import com.liusy.analysis.template.model.vo.DataSet;
import com.liusy.analysis.template.model.vo.FieldConfig;


public class StartNode extends  Node<String>
implements INode
{
	private static final long serialVersionUID = 0x1032904786e7cad5L;
	protected String name;
	protected String nullProperty;

	public StartNode()
	{
		name = "开始";
	}

	  public String getName()
	  {
	    return this.name;
	  }

	  public void setName(String name) {
	    if (this.name.equals(name)) return;
	    this.name = name;
	    this.nullProperty = "";

	    firePropertyChange("NAME", null, name);
	  }

	  public List<Metadata> getMeta() {
	    return null;
	  }

	  public List<Map<String, Object>> run(List<DataSet> inputDataSets) {
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

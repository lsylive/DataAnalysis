
package com.liusy.analysis.template.model.node;

import java.util.List;
import java.util.Map;

import com.liusy.analysis.template.model.base.Metadata;
import com.liusy.analysis.template.model.vo.DataSet;
import com.liusy.analysis.template.model.vo.FieldConfig;

// Referenced classes of package com.liusy.analysis.template.model.node:
//			Node, INode

public class EndNode extends Node<String>
implements INode
{

	private static final long serialVersionUID = 1L;
	protected String name;
	protected String nullProperty;

	public EndNode()
	{
		this.name = "结束";
	}
	  public String getName()
	  {
	    return this.name;
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

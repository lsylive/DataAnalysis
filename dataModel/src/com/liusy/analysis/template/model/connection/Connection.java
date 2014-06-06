
package com.liusy.analysis.template.model.connection;

import com.liusy.analysis.template.model.base.Element;
import com.liusy.analysis.template.model.node.INode;

public class Connection extends Element
{

	private static final long serialVersionUID = 1L;
	public static final String PROP_NAME = "NAME";
	public static final String PROP_BENDPOINT = "PROPBENDPOINT";
	private String name;
	private INode source;
	private INode target;

	public Object clone()
		throws CloneNotSupportedException
	{
		return super.clone();
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
			firePropertyChange("NAME", null, name);
			return;
		}
	}

	public void setSource(INode source)
	{
		this.source = source;
	}

	public void setTarget(INode target)
	{
		this.target = target;
	}

	public INode getTarget()
	{
		return target;
	}

	public INode getSource()
	{
		return source;
	}

	public Connection(INode source, INode target)
	{
		name = "";
		this.source = source;
		this.target = target;
		source.addOutput(this);
		target.addInput(this);
	}
}

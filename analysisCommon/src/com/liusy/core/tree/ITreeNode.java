


//   ITreeNode.java

package com.liusy.core.tree;

import java.util.List;

public interface ITreeNode
{

	public abstract String getNodeId();

	public abstract String getNodeName();

	public abstract String getUpNodeId();

	public abstract ITreeNode getUpNode();

	public abstract List getChildNodes();
}

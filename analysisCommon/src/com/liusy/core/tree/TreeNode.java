


//   TreeNode.java

package com.liusy.core.tree;

import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.liusy.core.tree:
//			ITreeNode

public class TreeNode
	implements ITreeNode
{

	public String nodeId;
	public String nodeName;
	public String upNodeId;
	public ITreeNode upNode;
	public Object attributes;
	public String treeCode;
	public String treeLv;
	public List childNodes;

	public TreeNode()
	{
		childNodes = new ArrayList();
	}

	public List getChildNodes()
	{
		return childNodes;
	}

	public void setChildNodes(List childNodes)
	{
		this.childNodes = childNodes;
	}

	public String getNodeId()
	{
		return nodeId;
	}

	public void setNodeId(String nodeId)
	{
		this.nodeId = nodeId;
	}

	public String getUpNodeId()
	{
		return upNodeId;
	}

	public void setUpNodeId(String upNodeId)
	{
		this.upNodeId = upNodeId;
	}

	public ITreeNode getUpNode()
	{
		return upNode;
	}

	public void setUpNode(ITreeNode upNode)
	{
		this.upNode = upNode;
	}

	public String getTreeCode()
	{
		return treeCode;
	}

	public void setTreeCode(String treeCode)
	{
		this.treeCode = treeCode;
	}

	public String getTreeLv()
	{
		return treeLv;
	}

	public void setTreeLv(String treeLv)
	{
		this.treeLv = treeLv;
	}

	public boolean isChild(TreeNode node)
	{
		return node.getNodeId().equals(upNodeId);
	}

	public String getNodeName()
	{
		return nodeName;
	}

	public void setNodeName(String nodeName)
	{
		this.nodeName = nodeName;
	}
}

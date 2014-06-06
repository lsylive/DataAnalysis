/**
 *@author MengQingChang
 *Copyright 2007-10-28,MenqQingChang all rights reserved.
 */
package com.liusy.analysismodel.template;

import java.util.List;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
public class TreeViewerContentProvider implements ITreeContentProvider {
	public TreeViewerContentProvider() {
	}
    // 返回给定父元素的子元素
	public Object[] getChildren(Object parentElement) {
		// TODO 自动生成方法存根
		ITreeElement treeElement = (ITreeElement) parentElement;
		List<ITreeElement> list = treeElement.getChildren();
		if (list == null || list.isEmpty())
			return new Object[0];
		else
			return list.toArray();
	}

	public Object getParent(Object element) {
		return null;
	}

	// 返回给定元素(节点）是否有子元素
	public boolean hasChildren(Object element) {
		// TODO 自动生成方法存根
		ITreeElement treeElement = (ITreeElement) element;
		List<ITreeElement> list = treeElement.getChildren();
		// 判断子节点是否为空，即是否有子节点。
		if (list == null || list.isEmpty())
			return false;
		else
			return true;

	}

	// 当输入为设定的元素时，返回查看器中显示的元素。
	public Object[] getElements(Object inputElement) {
		// TODO 自动生成方法存根
		if (inputElement instanceof List) {
			List list = (List) inputElement;
			return list.toArray();
		} else {
			// 生成一个空数组
			return new Object[0];
		}
	}

	public void dispose() {
	}

	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}

}

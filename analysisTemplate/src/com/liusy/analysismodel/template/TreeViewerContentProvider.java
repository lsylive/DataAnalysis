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
    // ���ظ�����Ԫ�ص���Ԫ��
	public Object[] getChildren(Object parentElement) {
		// TODO �Զ����ɷ������
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

	// ���ظ���Ԫ��(�ڵ㣩�Ƿ�����Ԫ��
	public boolean hasChildren(Object element) {
		// TODO �Զ����ɷ������
		ITreeElement treeElement = (ITreeElement) element;
		List<ITreeElement> list = treeElement.getChildren();
		// �ж��ӽڵ��Ƿ�Ϊ�գ����Ƿ����ӽڵ㡣
		if (list == null || list.isEmpty())
			return false;
		else
			return true;

	}

	// ������Ϊ�趨��Ԫ��ʱ�����ز鿴������ʾ��Ԫ�ء�
	public Object[] getElements(Object inputElement) {
		// TODO �Զ����ɷ������
		if (inputElement instanceof List) {
			List list = (List) inputElement;
			return list.toArray();
		} else {
			// ����һ��������
			return new Object[0];
		}
	}

	public void dispose() {
	}

	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}

}

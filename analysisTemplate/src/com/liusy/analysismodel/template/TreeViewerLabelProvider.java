/**
 *@author MengQingChang
 *Copyright 2007-10-28,MenqQingChang all rights reserved.
 */
package com.liusy.analysismodel.template;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;


public class TreeViewerLabelProvider implements ILabelProvider {

	public TreeViewerLabelProvider() {
		// TODO �Զ����ɹ��캯�����
	}

	// ���������ͼ��
	public Image getImage(Object element) {
		// ϵͳ�Դ�����ͼ��
		String image1 = ISharedImages.IMG_OBJ_FOLDER;
		String image2 = ISharedImages.IMG_OBJ_FILE;

		// ��ȡÿ�����Ľڵ�����
		boolean fileFlag = ((NavigatorEntityElement) element).getFileInput().isFile();
		// Ϊһ����Σ������ڵ�����ͼ��
		if (!fileFlag){
			return PlatformUI.getWorkbench().getSharedImages().getImage(image1);
		} else {
			return PlatformUI.getWorkbench().getSharedImages().getImage(image2);
		}
	}

	// ���ظ����ڵ���ı�
	public String getText(Object element) {
		// TODO �Զ����ɷ������
		ITreeElement treeElement = (ITreeElement) element;
		return treeElement.getName();

	}

	// ------------------------���·�����ʵ��-----------------------------

	public void addListener(ILabelProviderListener listener) {
		// TODO �Զ����ɷ������
	}

	public void dispose() {
		// TODO �Զ����ɷ������
	}

	public boolean isLabelProperty(Object element, String property) {
		// TODO �Զ����ɷ������
		return false;
	}

	public void removeListener(ILabelProviderListener listener) {
		// TODO �Զ����ɷ������
	}

}

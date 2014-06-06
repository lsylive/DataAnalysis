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
		// TODO 自动生成构造函数存根
	}

	// 设置树结点图标
	public Image getImage(Object element) {
		// 系统自带定义图标
		String image1 = ISharedImages.IMG_OBJ_FOLDER;
		String image2 = ISharedImages.IMG_OBJ_FILE;

		// 获取每个树的节点名称
		boolean fileFlag = ((NavigatorEntityElement) element).getFileInput().isFile();
		// 为一级层次（根）节点设置图标
		if (!fileFlag){
			return PlatformUI.getWorkbench().getSharedImages().getImage(image1);
		} else {
			return PlatformUI.getWorkbench().getSharedImages().getImage(image2);
		}
	}

	// 返回给定节点的文本
	public String getText(Object element) {
		// TODO 自动生成方法存根
		ITreeElement treeElement = (ITreeElement) element;
		return treeElement.getName();

	}

	// ------------------------以下方法空实现-----------------------------

	public void addListener(ILabelProviderListener listener) {
		// TODO 自动生成方法存根
	}

	public void dispose() {
		// TODO 自动生成方法存根
	}

	public boolean isLabelProperty(Object element, String property) {
		// TODO 自动生成方法存根
		return false;
	}

	public void removeListener(ILabelProviderListener listener) {
		// TODO 自动生成方法存根
	}

}

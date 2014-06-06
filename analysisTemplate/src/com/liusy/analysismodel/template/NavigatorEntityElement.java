/**
 *@author MengQingChang
 *Copyright  2007-12-6,MenqQingChang all rights reserved.
 */
package com.liusy.analysismodel.template;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class NavigatorEntityElement implements ITreeElement {

	// 根节点名称
	private String name;
	private int templateId;
	private File fileInput;
	private NavigatorEntityElement parent;
	// 根节点包含的集合，封装在List中。
	private List<ITreeElement> list1 = new ArrayList<ITreeElement>();

	// 构造方法
	public NavigatorEntityElement() {
	}

	public NavigatorEntityElement(String name) {
		this.name = name;
	}

	// 实现接口ITreeElement方法
	public void setChildren(List<ITreeElement> children) {
		this.list1 = children;
	}

	// 实现接口ITreeElement方法
	public List<ITreeElement> getChildren() {
		return list1;
	}

	// 实现接口ITreeElement方法
	public void addChild(ITreeElement treeElement) {
		list1.add(treeElement);
	}
	public void delete(ITreeElement treeElement) {
		list1.remove(treeElement);
	}
	public ITreeElement getChildByName(String name) {
		for (ITreeElement item:list1) {
			if (name.equals(item.getName())) {
				return item;
			}
		}
		return null;
		
	}
	// 实现接口ITreeElement方法，判断是否有子节点
	public boolean hasChildren() {
		if (list1.size() > 0)
			return true;
		else
			return false;
	}

	// 实现接口ITreeElement方法
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public int getTemplateId() {
		return templateId;
	}

	public void setTemplateId(int templateId) {
		this.templateId = templateId;
	}

	public File getFileInput() {
		return fileInput;
	}

	public void setFileInput(File fileInput) {
		this.fileInput = fileInput;
	}

	public NavigatorEntityElement getParent() {
		return parent;
	}

	public void setParent(NavigatorEntityElement parent) {
		this.parent = parent;
	}
}

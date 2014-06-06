/**
 *@author MengQingChang
 *Copyright  2007-12-6,MenqQingChang all rights reserved.
 */
package com.liusy.analysismodel.template;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class NavigatorEntityElement implements ITreeElement {

	// ���ڵ�����
	private String name;
	private int templateId;
	private File fileInput;
	private NavigatorEntityElement parent;
	// ���ڵ�����ļ��ϣ���װ��List�С�
	private List<ITreeElement> list1 = new ArrayList<ITreeElement>();

	// ���췽��
	public NavigatorEntityElement() {
	}

	public NavigatorEntityElement(String name) {
		this.name = name;
	}

	// ʵ�ֽӿ�ITreeElement����
	public void setChildren(List<ITreeElement> children) {
		this.list1 = children;
	}

	// ʵ�ֽӿ�ITreeElement����
	public List<ITreeElement> getChildren() {
		return list1;
	}

	// ʵ�ֽӿ�ITreeElement����
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
	// ʵ�ֽӿ�ITreeElement�������ж��Ƿ����ӽڵ�
	public boolean hasChildren() {
		if (list1.size() > 0)
			return true;
		else
			return false;
	}

	// ʵ�ֽӿ�ITreeElement����
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

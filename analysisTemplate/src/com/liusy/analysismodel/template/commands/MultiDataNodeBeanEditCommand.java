package com.liusy.analysismodel.template.commands;

import com.liusy.analysis.template.model.dialogProperties.MultiDataNodeProperties;
import com.liusy.analysis.template.model.node.MultiDataNode;
import com.liusy.analysis.template.model.util.StringUtil;

public class MultiDataNodeBeanEditCommand extends NodePropertyEditCommand {
	private MultiDataNode				node;

	private MultiDataNodeProperties	newNodeBean;
	private MultiDataNodeProperties	oldNodeBean;

	public void setBean(MultiDataNodeProperties bean) {
		this.newNodeBean = bean;
	}

	public void setNode(MultiDataNode node) {
		this.node = node;
	}

	public void execute() {
		System.out.println("call  command setBean");
		oldNodeBean = (MultiDataNodeProperties) this.node.getProperties();
		this.node.setProperties(newNodeBean);
	}

	public void redo() {
		node.setProperties(newNodeBean);
	}

	public void undo() {
		node.setProperties(oldNodeBean);
	}

	public String getLabel() {
		return StringUtil.dataNodePropertyModifyCmd;
	}

	public boolean canExecute() {
		oldNodeBean = (MultiDataNodeProperties) this.node.getProperties();
		if (newNodeBean.equals(oldNodeBean)) {
			return false;
		}
		else {
			return true;
		}
	}
}

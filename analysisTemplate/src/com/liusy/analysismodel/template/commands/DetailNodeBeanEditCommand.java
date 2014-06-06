package com.liusy.analysismodel.template.commands;

import com.liusy.analysis.template.model.dialogProperties.DetailQueryInterfaceProperties;
import com.liusy.analysis.template.model.node.DetailQueryInterfaceNode;
import com.liusy.analysis.template.model.util.StringUtil;

public class DetailNodeBeanEditCommand extends NodePropertyEditCommand {
	private DetailQueryInterfaceNode node;
	
	private DetailQueryInterfaceProperties newNodeBean;
	private DetailQueryInterfaceProperties oldNodeBean;
    public void setBean(DetailQueryInterfaceProperties bean) {
        this.newNodeBean = bean;
    }

    public void setNode(DetailQueryInterfaceNode node) {
        this.node = node;
    }
    public void execute() {
    	System.out.println("call  command setBean");
    	oldNodeBean = (DetailQueryInterfaceProperties) this.node.getProperties();
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
    	oldNodeBean = (DetailQueryInterfaceProperties) this.node.getProperties();
    	if (newNodeBean.equals(oldNodeBean)) {
			return false;
		} else {
			return true;
		}
    }
}

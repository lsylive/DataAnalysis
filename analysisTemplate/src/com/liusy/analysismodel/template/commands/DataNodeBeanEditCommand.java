package com.liusy.analysismodel.template.commands;

import com.liusy.analysis.template.model.dialogProperties.DataNodeProperties;
import com.liusy.analysis.template.model.node.DataNode;
import com.liusy.analysis.template.model.util.StringUtil;

public class DataNodeBeanEditCommand extends NodePropertyEditCommand {
	private DataNode node;
	
	private DataNodeProperties newNodeBean;
	private DataNodeProperties oldNodeBean;
    public void setBean(DataNodeProperties bean) {
        this.newNodeBean = bean;
    }

    public void setNode(DataNode node) {
        this.node = node;
    }
    public void execute() {
    	System.out.println("call  command setBean");
    	oldNodeBean = (DataNodeProperties) this.node.getProperties();
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
    	oldNodeBean = (DataNodeProperties) this.node.getProperties();
    	if (newNodeBean.equals(oldNodeBean)) {
			return false;
		} else {
			return true;
		}
    }
}

package com.liusy.analysismodel.template.commands;

import org.eclipse.gef.commands.Command;

import com.liusy.analysis.template.model.dialogProperties.OperateNodeBean;
import com.liusy.analysis.template.model.node.OperateNode;

public class OperateNodeBeanEditCommand extends Command {
	private OperateNode node;
	
	private OperateNodeBean newNodeBean;
	private OperateNodeBean oldNodeBean;
    public void setBean(OperateNodeBean bean) {
        this.newNodeBean = bean;
    }

    public void setNode(OperateNode node) {
        this.node = node;
    }
    public void execute() {
    	oldNodeBean = this.node.getExtrProperties();
        this.node.setExtrProperties(newNodeBean);
    }
    public void redo() {
        node.setExtrProperties(newNodeBean);
    }

    public void undo() {
        node.setExtrProperties(oldNodeBean);
    }
    public String getLabel() {
        return "OperateNodeBean modify";
    }
    public boolean canExecute() {
    	oldNodeBean = this.node.getExtrProperties();
    	if (newNodeBean.equals(oldNodeBean)) {
			return false;
		} else {
			return true;
		}
    }
}

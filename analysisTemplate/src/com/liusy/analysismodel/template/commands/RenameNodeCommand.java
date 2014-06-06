/*
 * Created on 2005-1-25
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.liusy.analysismodel.template.commands;

import org.eclipse.gef.commands.Command;

import com.liusy.analysis.template.model.node.INode;
import com.liusy.analysis.template.model.util.StringUtil;
/**
 * <p>Title: Eclipse Plugin Development</p>
 * <p>Description: Free download</p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: Free</p>
 * @author gan.shu.man
 * @version 1.0
 */

public class RenameNodeCommand extends Command {

    private INode node;

    private String newName;

    private String oldName;

    public void setName(String name) {
        this.newName = name;
    }

    public void setNode(INode node) {
        this.node = node;
    }

    public void execute() {
    	System.out.println("call  command rename");
        oldName = this.node.getName();
        this.node.setName(newName);
    }

    public void redo() {
        node.setName(newName);
    }

    public void undo() {
        node.setName(oldName);
    }

    public String getLabel() {
        return StringUtil.reNameNodeCmd;
    }
}
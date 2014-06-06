/*
 * Created on 2005-1-27
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.liusy.analysismodel.template.commands;

import org.eclipse.gef.commands.Command;

import com.liusy.analysis.template.model.connection.Connection;
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

public class DeleteConnectionCommand extends Command {

    INode source;

    INode target;

    Connection connection;

    //Setters
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void setSource(INode source) {
        this.source = source;
    }

    public void setTarget(INode target) {
        this.target = target;
    }

    public void execute() {
        source.removeOutput(connection);
        target.removeInput(connection);
        connection.setSource(null);
        connection.setTarget(null);
    }

    public String getLabel() {
        return StringUtil.deleteConnectionCmd;
    }

    public void redo() {
        execute();
    }

    public void undo() {
        connection.setSource(source);
        connection.setTarget(target);
        source.addOutput(connection);
        target.addInput(connection);
    }
}
/*
 * Created on 2005-1-25
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.liusy.analysismodel.template.commands;

import java.util.List;

import org.eclipse.gef.commands.Command;

import com.liusy.analysis.template.model.connection.Connection;
import com.liusy.analysis.template.model.node.INode;
import com.liusy.analysis.template.model.util.StringUtil;


public class CreateConnectionCommand extends Command {

    protected Connection connection;

    protected INode source;

    protected INode target;

    public void setSource(INode source) {
        this.source = source;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void setTarget(INode target) {
        this.target = target;
    }

    //------------------------------------------------------------------------
    // Overridden from Command

    public void execute() {
        connection = new Connection(source, target);
    }

    public String getLabel() {
        return StringUtil.createConnectionCmd;
    }

    public void redo() {
        this.source.addOutput(this.connection);
        this.target.addInput(this.connection);
    }

    public void undo() {
        this.source.removeOutput(this.connection);
        this.target.removeInput(this.connection);
    }

    public boolean canExecute() {
        if (source.equals(target))
            return false;
//        if (source.getClass() == target.getClass()) {
//        	return false;
//        }
        // Check for existence of connection already
        List<Connection> connections = this.source.getOutgoingConnections();
        for (int i = 0; i < connections.size(); i++) {
            if (((Connection) connections.get(i)).getTarget().equals(target))
                return false;
        }
        
        return true;
    }
}
/*
 * Created on 2005-1-25
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.liusy.analysismodel.template.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.ReconnectRequest;

//import com.liusy.analysismodel.template.commands.CreateOperate2ConnectionCommand;
import com.liusy.analysismodel.template.commands.ReconnectSourceCommand;
import com.liusy.analysis.template.model.connection.Connection;
import com.liusy.analysis.template.model.node.OperateNode;

/**
 * <p>Title: Eclipse Plugin Development</p>
 * <p>Description: Free download</p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: Free</p>
 * @author gan.shu.man
 * @version 1.0
 */

public class OperateNodeGraphicalNodeEditPolicy extends GraphicalNodeEditPolicy {

    protected Command getConnectionCompleteCommand(CreateConnectionRequest request) {
//        CreateOperate2ConnectionCommand command = (CreateOperate2ConnectionCommand) request.getStartCommand();
//        command.setTarget((OperateNode) getHost().getModel());
//        return command;
        return null;
    }

    protected Command getConnectionCreateCommand(CreateConnectionRequest request) {
//        CreateOperate2ConnectionCommand command = new CreateOperate2ConnectionCommand();
//        command.setSource((OperateNode) getHost().getModel());
//        request.setStartCommand(command);
//        return command;
        return null;
    }

    protected Command getReconnectSourceCommand(ReconnectRequest request) {
//		ReconnectSourceCommand cmd = new ReconnectSourceCommand();
//		cmd.setConnection((Connection)request.getConnectionEditPart().getModel());
////		cmd.setSource((OperateNode)getHost().getModel());
//		return cmd;
		return null;
    }

    protected Command getReconnectTargetCommand(ReconnectRequest request) {
        return null;
    }
}
/*
 * Created on 2005-1-27
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.liusy.analysismodel.template.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import com.liusy.analysismodel.template.commands.DeleteConnectionCommand;
import com.liusy.analysis.template.model.connection.Connection;

/**
 * <p>Title: Eclipse Plugin Development</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * <p>Company: Thunisoft</p>
 * @author xing
 * @version 1.0
 */

public class ConnectionEditPolicy extends ComponentEditPolicy{

    protected Command createDeleteCommand(GroupRequest deleteRequest) {
        Connection conn=(Connection)getHost().getModel();
        DeleteConnectionCommand cmd=new DeleteConnectionCommand();
        cmd.setConnection(conn);
        cmd.setSource(conn.getSource());
        cmd.setTarget(conn.getTarget());
        return cmd;
    }
}

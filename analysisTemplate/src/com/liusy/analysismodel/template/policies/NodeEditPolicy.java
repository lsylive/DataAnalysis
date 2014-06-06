/*
 * Created on 2005-1-25
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.liusy.analysismodel.template.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import com.liusy.analysismodel.template.commands.DeleteNodeCommand;
import com.liusy.analysis.template.model.Diagram;
import com.liusy.analysis.template.model.node.INode;

/**
 * <p>Title: Eclipse Plugin Development</p>
 * <p>Description: Free download</p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: Free</p>
 * @author gan.shu.man
 * @version 1.0
 */

public class NodeEditPolicy extends ComponentEditPolicy{

    protected Command createDeleteCommand(GroupRequest deleteRequest) {
        DeleteNodeCommand deleteCommand=new DeleteNodeCommand();
        deleteCommand.setDiagram((Diagram)getHost().getParent().getModel());
        deleteCommand.setNode((INode)getHost().getModel());
        return deleteCommand;
    }
}

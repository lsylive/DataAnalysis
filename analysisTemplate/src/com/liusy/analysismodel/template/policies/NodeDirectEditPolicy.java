/*
 * Created on 2005-1-25
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.liusy.analysismodel.template.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.DirectEditPolicy;
import org.eclipse.gef.requests.DirectEditRequest;

import com.liusy.analysismodel.template.commands.RenameNodeCommand;
import com.liusy.analysismodel.template.figures.BaseFigure;
import com.liusy.analysis.template.model.node.INode;


/**
 * <p>Title: Eclipse Plugin Development</p>
 * <p>Description: Free download</p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: Free</p>
 * @author gan.shu.man
 * @version 1.0
 */

public class NodeDirectEditPolicy extends DirectEditPolicy{

    protected Command getDirectEditCommand(DirectEditRequest request) {
    	System.out.println("call RenameNodeCommand");
        RenameNodeCommand cmd = new RenameNodeCommand();
        cmd.setNode((INode) getHost().getModel());
        cmd.setName((String) request.getCellEditor().getValue());
        return cmd;
    }
    protected void showCurrentEditValue(DirectEditRequest request) {
        String value = (String) request.getCellEditor().getValue();
        ((BaseFigure) getHostFigure()).setName(value);
    }
}

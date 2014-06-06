/*
 * Created on 2005-1-24
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.liusy.analysismodel.template.policies;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gef.requests.CreateRequest;

import com.liusy.analysismodel.template.commands.CreateNodeCommand;
import com.liusy.analysismodel.template.commands.DeleteNodeCommand;
import com.liusy.analysismodel.template.commands.MoveNodeCommand;
import com.liusy.analysis.template.model.Diagram;
import com.liusy.analysis.template.model.node.INode;
import com.liusy.analysis.template.model.node.Node;
import com.liusy.analysismodel.template.parts.NodePart;

/**
 * <p>Title: Eclipse Plugin Development</p>
 * <p>Description: Free download</p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: Free</p>
 * @author gan.shu.man
 * @version 1.0
 */

public class DiagramLayoutEditPolicy extends XYLayoutEditPolicy {

    protected Command createAddCommand(EditPart child, Object constraint) {
        return null;
    }

    //创建模型位置改变的命令
    protected Command createChangeConstraintCommand(EditPart child, Object constraint) {
        if (!(constraint instanceof Rectangle))
            return null;
        if (child instanceof NodePart) {
        	MoveNodeCommand cmd = new MoveNodeCommand();
            cmd.setNode((INode) child.getModel());
            //设置模型新的位置信息
            cmd.setLocation(((Rectangle) constraint).getLocation());
            return cmd;
        } else {
        	return null;
        }
        

    }

    //获得创建模型的命令
    protected Command getCreateCommand(CreateRequest request) {
    	//判断请求创建的是否为Node
        if (request.getNewObject() instanceof Node) {
        	//新建CreateNodeCommand
            CreateNodeCommand cmd = new CreateNodeCommand();
            //设置父模型
            cmd.setDiagram((Diagram) getHost().getModel());
            //设置当前模型
            cmd.setNode((INode) request.getNewObject());
            Rectangle constraint = (Rectangle) getConstraintFor(request);
            //设置模型的位置信息
            cmd.setLocation(constraint.getLocation());
            //返回Command对象
            return cmd;
        }
        return null;
    }

    protected Command getDeleteDependantCommand(Request request) {
    	DeleteNodeCommand cmd = new DeleteNodeCommand();
        return cmd;
    }
}
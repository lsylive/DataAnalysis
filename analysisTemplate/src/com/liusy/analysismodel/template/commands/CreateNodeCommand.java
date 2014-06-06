/*
 * Created on 2005-1-25
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.liusy.analysismodel.template.commands;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;

import com.liusy.analysis.template.model.Diagram;
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

public class CreateNodeCommand extends Command {
    protected Diagram diagram;

    protected INode node;

    protected Point location;

    // setters

    public void setDiagram(Diagram diagram) {
        this.diagram = diagram;
    }

    public void setNode(INode node) {
        this.node = node;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public void execute() {
        if (this.location != null) {
            this.node.setLocation(this.location);
        }
        this.diagram.addNode(this.node);
    }

    public String getLabel() {
        return StringUtil.createNodeCmd;
    }

    public void redo() {
        this.execute();
    }

    public void undo() {
        diagram.removeNode(node);
    }
}
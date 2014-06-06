/*
 * Created on 2005-1-25
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.liusy.analysismodel.template.parts;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.gef.editparts.AbstractTreeEditPart;

import com.liusy.analysis.template.model.node.INode;

/**
 * <p>Title: Eclipse Plugin Development</p>
 * <p>Description: Free download</p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: Free</p>
 * @author gan.shu.man
 * @version 1.0
 */

public class NodeTreeEditPart extends AbstractTreeEditPart implements PropertyChangeListener {

    public NodeTreeEditPart(Object model) {
        super(model);
    }

    public void propertyChange(PropertyChangeEvent evt) {
        refreshVisuals();
    }

    public void activate() {
        super.activate();
        ((INode) getModel()).addPropertyChangeListener(this);
    }

    public void deactivate() {
        super.deactivate();
        ((INode) getModel()).removePropertyChangeListener(this);
    }

    protected void refreshVisuals() {
        setWidgetText(((INode) getModel()).getName());
    }

}
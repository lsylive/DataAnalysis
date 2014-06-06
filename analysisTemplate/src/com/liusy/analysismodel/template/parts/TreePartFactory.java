/*
 * Created on 2005-1-25
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.liusy.analysismodel.template.parts;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;

import com.liusy.analysis.template.model.Diagram;
import com.liusy.analysis.template.model.node.Node;


/**
 * <p>Title: Eclipse Plugin Development</p>
 * <p>Description: Free download</p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: Free</p>
 * @author gan.shu.man
 * @version 1.0
 */

public class TreePartFactory implements EditPartFactory{

    public EditPart createEditPart(EditPart context, Object model) {
        if (model instanceof Diagram) {
            return new DiagramTreeEditPart(model);
         }
         else if (model instanceof Node) {
            return new NodeTreeEditPart(model);
         }
         else {
            return null;
         }
    }
}

/*
 * Created on 2005-1-25
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.liusy.analysismodel.template.dnd;

import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.dnd.TemplateTransferDropTargetListener;
import org.eclipse.gef.requests.CreationFactory;

import com.liusy.analysismodel.template.tools.ElementFactory;

/**
 * <p>Title: Eclipse Plugin Development</p>
 * <p>Description: Free download</p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: Free</p>
 * @author gan.shu.man
 * @version 1.0
 */

public class DiagramTemplateTransferDropTargetListener extends TemplateTransferDropTargetListener {

    /**
     * @param viewer
     */
    public DiagramTemplateTransferDropTargetListener(EditPartViewer viewer) {
        super(viewer);
    }

    protected CreationFactory getFactory(Object template) {
        return new ElementFactory(template);
    }
}
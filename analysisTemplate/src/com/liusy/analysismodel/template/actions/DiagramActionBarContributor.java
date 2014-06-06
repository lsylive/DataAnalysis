/*
 * Created on 2005-1-25
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.liusy.analysismodel.template.actions;

import org.apache.log4j.Logger;
import org.eclipse.gef.ui.actions.ActionBarContributor;
import org.eclipse.jface.action.IToolBarManager;
import com.liusy.analysis.template.model.util.StringUtil;


public class DiagramActionBarContributor extends ActionBarContributor {
	private static Logger    log   = Logger.getLogger(DiagramActionBarContributor.class);
    protected void buildActions() {
        addRetargetAction(new UndoRetargetAction());
        addRetargetAction(new RedoRetargetAction());
        addRetargetAction(new DeleteRetargetAction());
    }
    protected void declareGlobalActionKeys() {

    }
    public void contributeToToolBar(IToolBarManager toolBarManager) {
//    	super.contributeToToolBar(toolBarManager);
        toolBarManager.add(getAction(StringUtil.undoId));
        toolBarManager.add(getAction(StringUtil.redoId));
        toolBarManager.add(getAction(StringUtil.deleteId));
    }
}

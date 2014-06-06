/*
 * Created on 2005-1-24
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.liusy.analysismodel.template.parts;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

import com.liusy.analysismodel.template.commands.NodePropertyEditCommand;
import com.liusy.analysismodel.template.figures.UnionOperateNodeFigure;
import com.liusy.analysis.template.model.node.UnionOperationNode;
import com.liusy.analysismodel.template.ui.UnionOperationNodeDialog;

public class UnionOperationNodePart extends NodePart {

	public void performRequest(Request req) {
		if (req.getType().equals(RequestConstants.REQ_OPEN)) {
			Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor().getSite().getShell();
			NodePropertyEditCommand cmd = new NodePropertyEditCommand();
			UnionOperationNodeDialog dlg = new UnionOperationNodeDialog(shell, (UnionOperationNode)getModel(), cmd);
			int result = dlg.open();
			if (result == 1) {
				this.getViewer().getEditDomain().getCommandStack().execute(cmd);
			}
		}
      
      directNodeLabelEdit(req);
	}

	// 创建模型对应的视图
	protected IFigure createFigure() {
		return new UnionOperateNodeFigure();
	}
}
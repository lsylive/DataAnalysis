package com.liusy.analysismodel.template.parts;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

import com.liusy.analysismodel.template.commands.NodePropertyEditCommand;
import com.liusy.analysismodel.template.figures.PathSearchNodeFigure;
import com.liusy.analysis.template.model.node.PathSearchNode;
import com.liusy.analysismodel.template.ui.PathSearchNodeDialog;

public class PathSearchNodePart extends NodePart {
   //��������
   public void performRequest(Request req) {
      if (req.getType().equals(RequestConstants.REQ_OPEN)) {
         Shell sel = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor().getSite().getShell();
			NodePropertyEditCommand cmd = new NodePropertyEditCommand();
			PathSearchNodeDialog dlg = new PathSearchNodeDialog(sel, (PathSearchNode)getModel(), cmd);
			int result = dlg.open();
			if (result == 1) {
				this.getViewer().getEditDomain().getCommandStack().execute(cmd);
			}
      }
      
      directNodeLabelEdit(req);
   }

   //����ģ�Ͷ�Ӧ����ͼ
   protected IFigure createFigure() {
      return new PathSearchNodeFigure();
   }
}

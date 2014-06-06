package com.liusy.analysismodel.template.parts;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

import com.liusy.analysismodel.template.commands.NodePropertyEditCommand;
import com.liusy.analysismodel.template.figures.QueryInterfaceNodeFigure;
import com.liusy.analysis.template.model.node.QueryInterfaceNode;
import com.liusy.analysismodel.template.ui.QueryInterfaceNodeDialog;

public class QueryInterfaceNodePart extends NodePart {
   //��������
   public void performRequest(Request req) {
      if (req.getType().equals(RequestConstants.REQ_OPEN)) {
         Shell sel = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor().getSite().getShell();
         NodePropertyEditCommand cmd = new NodePropertyEditCommand();
         QueryInterfaceNodeDialog dlg = new QueryInterfaceNodeDialog(sel, (QueryInterfaceNode)getModel(), cmd);
			int result = dlg.open();
			if (result == 1) {
				this.getViewer().getEditDomain().getCommandStack().execute(cmd);
			}
      }
      
      directNodeLabelEdit(req);
   }

   //����ģ�Ͷ�Ӧ����ͼ
   protected IFigure createFigure() {
      return new QueryInterfaceNodeFigure();
   }

}

package com.liusy.analysismodel.template.parts;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

import com.liusy.analysismodel.template.commands.NodePropertyEditCommand;
import com.liusy.analysismodel.template.figures.SortNodeFigure;
import com.liusy.analysis.template.model.node.SortNode;
import com.liusy.analysismodel.template.ui.SortNodeDialog;

public class SortNodePart extends NodePart {
   //处理请求
   public void performRequest(Request req) {
      if (req.getType().equals(RequestConstants.REQ_OPEN)) {
         Shell sel = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor().getSite().getShell();
			NodePropertyEditCommand cmd = new NodePropertyEditCommand();
			SortNodeDialog dlg = new SortNodeDialog(sel, (SortNode)getModel(), cmd);
			int result = dlg.open();
			if (result == 1) {
				this.getViewer().getEditDomain().getCommandStack().execute(cmd);
			}
      
      }
      
      directNodeLabelEdit(req);
   }

   //创建模型对应的视图
   protected IFigure createFigure() {
      return new SortNodeFigure();
   }
}

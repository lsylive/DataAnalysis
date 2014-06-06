package com.liusy.analysismodel.template.parts;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

import com.liusy.analysismodel.template.commands.NodePropertyEditCommand;
import com.liusy.analysismodel.template.figures.FilterNodeFigure;
import com.liusy.analysis.template.model.node.FilterNode;
import com.liusy.analysismodel.template.ui.FilterNodeDialog;

public class FilterNodePart extends NodePart {
   //处理请求
   public void performRequest(Request req) {
      if (req.getType().equals(RequestConstants.REQ_OPEN)) {
         Shell sel = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor().getSite().getShell();
         NodePropertyEditCommand cmd = new NodePropertyEditCommand();
         FilterNodeDialog dlg = new FilterNodeDialog(sel, (FilterNode)getModel(), cmd);
			int result = dlg.open();
			if (result == 1) {
				this.getViewer().getEditDomain().getCommandStack().execute(cmd);
			}
      }
      
      directNodeLabelEdit(req);
   }

   //创建模型对应的视图
   protected IFigure createFigure() {
      return new FilterNodeFigure();
   }
}

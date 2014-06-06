package com.liusy.analysismodel.template.parts;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

import com.liusy.analysismodel.template.commands.NodePropertyEditCommand;
import com.liusy.analysismodel.template.figures.ConditionQueryNodeFigure;
import com.liusy.analysis.template.model.node.ConditionQueryNode;
import com.liusy.analysismodel.template.ui.ConditionQueryNodeDialog;

public class ConditionQueryNodePart extends NodePart {
   //��������
   public void performRequest(Request req) {
      if (req.getType().equals(RequestConstants.REQ_OPEN)) {
         Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor().getSite().getShell();
         NodePropertyEditCommand cmd = new NodePropertyEditCommand();
         ConditionQueryNodeDialog dlg = new ConditionQueryNodeDialog(shell, (ConditionQueryNode) getModel(), cmd);
         int result = dlg.open();
         if (result == 1) {
            this.getViewer().getEditDomain().getCommandStack().execute(cmd);
         }
      }
      
      directNodeLabelEdit(req);
   }

   //����ģ�Ͷ�Ӧ����ͼ
   protected IFigure createFigure() {
      return new ConditionQueryNodeFigure();
   }

}

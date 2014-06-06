package com.liusy.analysismodel.template.parts;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

import com.liusy.analysismodel.template.commands.NodePropertyEditCommand;
//import com.liusy.analysismodel.template.figures.DataNodeFigure;
import com.liusy.analysismodel.template.figures.SumNodeFigure;
//import com.liusy.analysis.template.model.node.ConditionQueryNode;
import com.liusy.analysis.template.model.node.SumNode;
import com.liusy.analysismodel.template.policies.NodeGraphicalNodeEditPolicy;
//import com.liusy.analysismodel.template.ui.ConditionQueryNodeDialog;
import com.liusy.analysismodel.template.ui.SumNodeDialog;

public class SumNodePart extends NodePart {
   //处理请求
   public void performRequest(Request req) {
      if (req.getType().equals(RequestConstants.REQ_OPEN)) {
         Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor().getSite().getShell();
         NodePropertyEditCommand cmd = new NodePropertyEditCommand();
         SumNodeDialog dlg = new SumNodeDialog(shell, (SumNode) getModel(), cmd);
         int result = dlg.open();
         if (result == 1) {
            this.getViewer().getEditDomain().getCommandStack().execute(cmd);
         }

      }
      if (req.getType().equals(RequestConstants.REQ_DIRECT_EDIT)) {
         if (manager == null) {
         	SumNodeFigure figure = (SumNodeFigure) getFigure();
            Label lab = figure.getLabel();
            manager = new RealNodeDirectEditManager(this, TextCellEditor.class, new NodeCellEditorLocator(lab));
         }
         manager.show();
      }
   }

   //设置编辑策略
   protected void createEditPolicies() {
      super.createEditPolicies();
      installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, new NodeGraphicalNodeEditPolicy());
   }

   //创建模型对应的视图
   protected IFigure createFigure() {
      return new SumNodeFigure();
   }

}

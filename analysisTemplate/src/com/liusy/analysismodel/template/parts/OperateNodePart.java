/*
 * Created on 2005-1-24 TODO To change the template for this generated file go
 * to Window - Preferences - Java - Code Style - Code Templates
 */
package com.liusy.analysismodel.template.parts;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

import com.liusy.analysismodel.template.commands.OperateNodeBeanEditCommand;
import com.liusy.analysismodel.template.figures.OperateNodeFigure;
import com.liusy.analysis.template.model.node.OperateNode;
import com.liusy.analysismodel.template.ui.OperateNodeDialog;

public class OperateNodePart extends NodePart {

   //处理请求
   public void performRequest(Request req) {
      System.out.println("call performRequest");
      if (req.getType().equals(RequestConstants.REQ_OPEN)) {
         Shell sel = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor().getSite().getShell();
         OperateNodeBeanEditCommand cmd = new OperateNodeBeanEditCommand();
         OperateNodeDialog dlg = new OperateNodeDialog(sel, (OperateNode) getModel(), cmd);
         int result = dlg.open();
         if (result == 1) {
            this.getViewer().getEditDomain().getCommandStack().execute(cmd);
         }
      }
      directNodeLabelEdit(req);
   }

   //创建模型对应的视图
   protected IFigure createFigure() {
      return new OperateNodeFigure();
   }
}
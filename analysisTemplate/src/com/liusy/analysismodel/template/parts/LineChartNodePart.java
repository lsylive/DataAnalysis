/*
 * Created on 2005-1-24 TODO To change the template for this generated file go
 * to Window - Preferences - Java - Code Style - Code Templates
 */
package com.liusy.analysismodel.template.parts;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

import com.liusy.analysismodel.template.commands.LineChartNodeBeanEditCommand;
import com.liusy.analysismodel.template.figures.LineChartInterfaceNodeFigure;
import com.liusy.analysis.template.model.node.LineChartInterfaceNode;
import com.liusy.analysismodel.template.ui.LineChartInterfaceNodeDialog;

public class LineChartNodePart extends NodePart {

   //处理请求
   public void performRequest(Request req) {
      if (req.getType().equals(RequestConstants.REQ_OPEN)) {
         Shell sel = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor().getSite().getShell();
         LineChartNodeBeanEditCommand cmd = new LineChartNodeBeanEditCommand();
         LineChartInterfaceNodeDialog dlg = new LineChartInterfaceNodeDialog(sel, (LineChartInterfaceNode) getModel(), cmd);
         int result = dlg.open();
         if (result == 1) {
            this.getViewer().getEditDomain().getCommandStack().execute(cmd);
         }
      }
      
      directNodeLabelEdit(req);
   }

   //创建模型对应的视图
   protected IFigure createFigure() {
      return new LineChartInterfaceNodeFigure();
   }
}
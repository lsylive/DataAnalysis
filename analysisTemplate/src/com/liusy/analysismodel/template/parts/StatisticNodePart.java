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

import com.liusy.analysismodel.template.commands.StatisticNodeBeanEditCommand;
import com.liusy.analysismodel.template.figures.StatisticInterfaceNodeFigure;
import com.liusy.analysis.template.model.node.StatisticInterfaceNode;
import com.liusy.analysismodel.template.ui.StatisticInterfaceNodeDialog;

public class StatisticNodePart extends NodePart {

   //��������
   public void performRequest(Request req) {
      if (req.getType().equals(RequestConstants.REQ_OPEN)) {
         Shell sel = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor().getSite().getShell();
         StatisticNodeBeanEditCommand cmd = new StatisticNodeBeanEditCommand();
         StatisticInterfaceNodeDialog dlg = new StatisticInterfaceNodeDialog(sel, (StatisticInterfaceNode) getModel(), cmd);
         int result = dlg.open();
         if (result == 1) {
            this.getViewer().getEditDomain().getCommandStack().execute(cmd);
         }
      }
      
      directNodeLabelEdit(req);
   }

   //����ģ�Ͷ�Ӧ����ͼ
   protected IFigure createFigure() {
      return new StatisticInterfaceNodeFigure();
   }
}
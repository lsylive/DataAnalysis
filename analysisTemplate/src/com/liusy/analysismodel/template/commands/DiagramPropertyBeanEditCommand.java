package com.liusy.analysismodel.template.commands;

import org.eclipse.gef.commands.Command;

import com.liusy.analysis.template.model.Diagram;
import com.liusy.analysis.template.model.util.StringUtil;
public class DiagramPropertyBeanEditCommand extends Command {
   private Diagram diagram;

   private Diagram newNodeBean;
   private Diagram oldNodeBean;

   public void setBean(Diagram bean) {
      this.newNodeBean = bean;
   }

   public void execute() {
      oldNodeBean = this.diagram;
      this.diagram = newNodeBean;
   }

   public void redo() {
      diagram = newNodeBean;
   }

   public void undo() {
      diagram = oldNodeBean;
   }

   public String getLabel() {
      return StringUtil.diagramPropertyModifyCmd;
   }

   public boolean canExecute() {
      oldNodeBean = this.diagram;
      if (newNodeBean.equals(oldNodeBean)) {
         return false;
      }
      else {
         return true;
      }
   }

   public void setDiagram(Diagram diagram) {
      this.diagram = diagram;
   }
}

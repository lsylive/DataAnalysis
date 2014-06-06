package com.liusy.analysismodel.template.commands;

import com.liusy.analysis.template.model.dialogProperties.LineChartInterfaceProperties;
import com.liusy.analysis.template.model.dialogProperties.StatisticInterfaceProperties;
import com.liusy.analysis.template.model.node.LineChartInterfaceNode;
import com.liusy.analysis.template.model.node.StatisticInterfaceNode;

public class LineChartNodeBeanEditCommand extends NodePropertyEditCommand {
   private LineChartInterfaceNode       node;

   private LineChartInterfaceProperties newNodeBean;
   private LineChartInterfaceProperties oldNodeBean;

   public void setBean(LineChartInterfaceProperties bean) {
      this.newNodeBean = bean;
   }

   public void setNode(LineChartInterfaceNode node) {
      this.node = node;
   }

   public void execute() {
      oldNodeBean = (LineChartInterfaceProperties) this.node.getProperties();
      this.node.setProperties(newNodeBean);
   }

   public void redo() {
      node.setProperties(newNodeBean);
   }

   public void undo() {
      node.setProperties(oldNodeBean);
   }

   public boolean canExecute() {
      oldNodeBean = (LineChartInterfaceProperties) this.node.getProperties();
      if (newNodeBean.equals(oldNodeBean)) {
         return false;
      }
      else {
         return true;
      }
   }
}

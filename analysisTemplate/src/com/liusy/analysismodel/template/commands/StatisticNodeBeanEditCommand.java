package com.liusy.analysismodel.template.commands;

import com.liusy.analysis.template.model.dialogProperties.StatisticInterfaceProperties;
import com.liusy.analysis.template.model.node.StatisticInterfaceNode;

public class StatisticNodeBeanEditCommand extends NodePropertyEditCommand {
   private StatisticInterfaceNode       node;

   private StatisticInterfaceProperties newNodeBean;
   private StatisticInterfaceProperties oldNodeBean;

   public void setBean(StatisticInterfaceProperties bean) {
      this.newNodeBean = bean;
   }

   public void setNode(StatisticInterfaceNode node) {
      this.node = node;
   }

   public void execute() {
      oldNodeBean = (StatisticInterfaceProperties) this.node.getProperties();
      this.node.setProperties(newNodeBean);
   }

   public void redo() {
      node.setProperties(newNodeBean);
   }

   public void undo() {
      node.setProperties(oldNodeBean);
   }

   public boolean canExecute() {
      oldNodeBean = (StatisticInterfaceProperties) this.node.getProperties();
      if (newNodeBean.equals(oldNodeBean)) {
         return false;
      }
      else {
         return true;
      }
   }
}

package com.liusy.analysismodel.template.commands;

import com.liusy.analysis.template.model.dialogProperties.JudgeNodeProperties;
import com.liusy.analysis.template.model.node.JudgeNode;

public class JudgeNodeBeanEditCommand extends NodePropertyEditCommand {
   private JudgeNode       node;

   private JudgeNodeProperties newNodeBean;
   private JudgeNodeProperties oldNodeBean;

   public void setBean(JudgeNodeProperties bean) {
      this.newNodeBean = bean;
   }

   public void setNode(JudgeNode node) {
      this.node = node;
   }

   public void execute() {
      oldNodeBean = (JudgeNodeProperties) this.node.getProperties();
      this.node.setProperties(newNodeBean);
   }

   public void redo() {
      node.setProperties(newNodeBean);
   }

   public void undo() {
      node.setProperties(oldNodeBean);
   }

   public boolean canExecute() {
      oldNodeBean = (JudgeNodeProperties) this.node.getProperties();
      if (newNodeBean.equals(oldNodeBean)) {
         return false;
      }
      else {
         return true;
      }
   }
}

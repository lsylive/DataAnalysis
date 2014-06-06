package com.liusy.analysismodel.template.commands;

import com.liusy.analysis.template.model.dialogProperties.GenerateColumnProperties;
import com.liusy.analysis.template.model.node.GenerateColumnNode;

public class GenerateColumnNodeBeanEditCommand extends NodePropertyEditCommand {
   private GenerateColumnNode       node;
   private GenerateColumnNode       tempNode;
   private GenerateColumnProperties newNodeBean;
   private GenerateColumnProperties oldNodeBean;
   private String oldName;
   private String oldDes;

	public void setNode(GenerateColumnNode node) {
      this.node = node;
   }
	public void setTempNode(GenerateColumnNode node) {
      this.tempNode = node;
   }
   public void execute() {
      oldNodeBean = (GenerateColumnProperties) node.getProperties();
      oldDes = node.getDescription();
      oldName = node.getName();
      this.node.setProperties(tempNode.getProperties());
      this.node.setName(tempNode.getName());
      this.node.setDescription(tempNode.getDescription());
   }

   public void redo() {
      this.node.setProperties(tempNode.getProperties());
      this.node.setName(tempNode.getName());
      this.node.setDescription(tempNode.getDescription());
   }

   public void undo() {
      node.setProperties(oldNodeBean);
      node.setName(oldName);
      node.setDescription(oldDes);
   }

   public boolean canExecute() {
      oldNodeBean = (GenerateColumnProperties) this.node.getProperties();
      newNodeBean = (GenerateColumnProperties) this.tempNode.getProperties();
      if (newNodeBean.equals(oldNodeBean)&&
      		node.getName().equals(tempNode.getName())
      		&&node.getDescription().equals(tempNode.getDescription())) {
         return false;
      }
      else {
         return true;
      }
   }
}

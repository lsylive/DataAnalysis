/*
 * Created on 2005-1-27 TODO To change the template for this generated file go
 * to Window - Preferences - Java - Code Style - Code Templates
 */
package com.liusy.analysismodel.template.commands;

import org.eclipse.gef.commands.Command;

import com.liusy.analysis.template.model.connection.Connection;
import com.liusy.analysis.template.model.node.INode;
import com.liusy.analysis.template.model.util.StringUtil;

public class ReconnectSourceCommand extends Command {
   private Connection connection;

   private INode      newSource;

   private INode      oldSource;

   private INode      target;

   //setters
   public void setConnection(Connection connection) {
      this.connection = connection;
      this.target = this.connection.getTarget();
      this.oldSource = this.connection.getSource();
   }

   public void setSource(INode source) {
      this.newSource = source;
   }

   public void execute() {
      oldSource.removeOutput(connection);
      newSource.addOutput(connection);
      connection.setSource(newSource);
   }

   public String getLabel() {
      return StringUtil.reConnectionCmd;
   }

   public void redo() {
      execute();
   }

   public void undo() {
      newSource.removeOutput(connection);
      oldSource.addOutput(connection);
      connection.setSource(oldSource);
   }
}
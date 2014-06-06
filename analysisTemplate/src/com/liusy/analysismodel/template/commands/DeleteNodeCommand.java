/*
 * Created on 2005-1-25 TODO To change the template for this generated file go
 * to Window - Preferences - Java - Code Style - Code Templates
 */
package com.liusy.analysismodel.template.commands;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.commands.Command;

import com.liusy.analysis.template.model.Diagram;
import com.liusy.analysis.template.model.connection.Connection;
import com.liusy.analysis.template.model.node.INode;
import com.liusy.analysis.template.model.util.StringUtil;

public class DeleteNodeCommand extends Command {
   private Diagram diagram;

   private INode   node;

   private int     index;
   
   private List<Connection> inputs = new ArrayList<Connection>();
   private List<Connection> outputs = new ArrayList<Connection>();
   List<Connection> tempInputs = new ArrayList<Connection>();
   List<Connection> tempOutputs = new ArrayList<Connection>();
   public void setDiagram(Diagram diagram) {
      this.diagram = diagram;
   }

   public void setNode(INode node) {
      this.node = node;
   }

   //------------------------------------------------------------------------
   // Overridden from Command

   public void execute() {
      index = diagram.getNodes().indexOf(node);
      inputs = node.getIncomingConnections();
      outputs = node.getOutgoingConnections();
      
      if (!inputs.isEmpty()) {
      	int inputSize = inputs.size();
      	for (int i = 0 ;i< inputSize;i++) {
      		Connection conn = inputs.get(i);
				try {
					Connection tempCon = (Connection)conn.clone();
					tempInputs.add(tempCon);
				}
				catch (CloneNotSupportedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

      	}
      }
      if (!inputs.isEmpty()) {
   		while (inputs.size()>0) {
   			Connection connnection = inputs.get(0);
         	INode source = connnection.getSource();
      		source.removeOutput(connnection);
      		node.removeInput(connnection);
      		connnection.setSource(null);
      		connnection.setTarget(null);
   		}
      }
      if (!outputs.isEmpty()) {
      	int outputSize = outputs.size();
      	for (int j = 0 ;j<outputSize;j++) {
      		Connection conn = outputs.get(j);
      		try {
					Connection tempOutCon = (Connection)conn.clone();
					tempOutputs.add(tempOutCon);
				}
				catch (CloneNotSupportedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
      	}
      }
      if (!outputs.isEmpty()) {
      	while (outputs.size()>0) {
      		Connection connnection = outputs.get(0);
      		INode target = connnection.getTarget();
				target.removeInput(connnection);
	   		node.removeOutput(connnection);
	   		connnection.setSource(null);
	   		connnection.setTarget(null);
      	}
      }
      diagram.removeNode(node);
   }
   public void undo() {
   	 if (!tempInputs.isEmpty()) {
   		 for (Connection conn: tempInputs) {
   			 
   			 INode source = conn.getSource();
   			 source.addOutput(conn);
   			 node.addInput(conn);
   			 
   			 conn.setTarget(node);
   		 }
   	 }
   	 if (!tempOutputs.isEmpty()) {
   		 for (Connection conn: tempOutputs) {
   			 INode target = conn.getTarget();
   			 target.addInput(conn);
   			 node.addOutput(conn);
   			 conn.setSource(node);
   		 }
   	 }
      diagram.recoverNode(node);
   }
   public String getLabel() {
      return StringUtil.deleteNodeCmd;
   }
   public void redo() {
      execute();
   }
}
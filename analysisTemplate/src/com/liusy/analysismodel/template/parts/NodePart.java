/*
 * Created on 2005-1-24 TODO To change the template for this generated file go
 * to Window - Preferences - Java - Code Style - Code Templates
 */
package com.liusy.analysismodel.template.parts;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.tools.DirectEditManager;
import org.eclipse.jface.viewers.TextCellEditor;

import com.liusy.analysismodel.template.figures.BaseFigure;
import com.liusy.analysis.template.model.connection.Connection;
import com.liusy.analysis.template.model.node.INode;
import com.liusy.analysis.template.model.node.Node;
import com.liusy.analysismodel.template.policies.NodeDirectEditPolicy;
import com.liusy.analysismodel.template.policies.NodeEditPolicy;
import com.liusy.analysismodel.template.policies.NodeGraphicalNodeEditPolicy;

public abstract class NodePart extends AbstractGraphicalEditPart implements PropertyChangeListener, NodeEditPart {

   protected DirectEditManager manager;

   protected void directNodeLabelEdit(Request req) {
   	if (req.getType().equals(RequestConstants.REQ_DIRECT_EDIT)) {
         if (manager == null) {
            BaseFigure figure = (BaseFigure) getFigure();
            Label lab = figure.getLabel();
            manager = new RealNodeDirectEditManager(this, TextCellEditor.class, new NodeCellEditorLocator(lab));
         }
         manager.show();
   	}

   }
   
   public DragTracker getDragTracker(Request request) {
      return super.getDragTracker(request);
   }

   //监听模型属性改变
   public void propertyChange(PropertyChangeEvent evt) {
      if (evt.getPropertyName().equals(Node.PROP_LOCATION)) {
         //更新视图
         refreshVisuals();
      }
      else if (evt.getPropertyName().equals(Node.PROP_NAME)) {
         refreshVisuals();
      }
      else if (evt.getPropertyName().equals(Node.PROP_INPUTS))
      //更新连接
      refreshTargetConnections();
      else if (evt.getPropertyName().equals(Node.PROP_OUTPUTS)) refreshSourceConnections();
   }

   //设置编辑策略
   protected void createEditPolicies() {
      installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE, new NodeDirectEditPolicy());
      installEditPolicy(EditPolicy.COMPONENT_ROLE, new NodeEditPolicy());
      installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, new NodeGraphicalNodeEditPolicy());

   }

   //注册自己成为模型的监听器
   public void activate() {
      if (isActive()) { return; }
      super.activate();
      ((INode) getModel()).addPropertyChangeListener(this);
   }

   //把自己从模型的监听器中删除
   public void deactivate() {
      if (!isActive()) { return; }
      super.deactivate();
      ((INode) getModel()).removePropertyChangeListener(this);
   }

   //更新视图的显示
   protected void refreshVisuals() {
      INode node = (INode) getModel();
      ((BaseFigure) this.getFigure()).setName(((INode) this.getModel()).getName());
      Point loc = node.getLocation();

      //      int imageWidth = ((ImageFigure) ((BaseFigure) getFigure()).getImgFigure()).getImage().getBounds().width;
      //      int imageHeight = ((ImageFigure) ((BaseFigure) getFigure()).getImgFigure()).getImage().getBounds().height;
      //
      //      int textWidth = ((BaseFigure) getFigure()).getLabel().getTextBounds().width;
      //      int textHeight = ((BaseFigure) getFigure()).getLabel().getTextBounds().height;

      //Dimension size = new Dimension(72, 72);
      Dimension size = ((BaseFigure) getFigure()).getPreferredSize();
      Rectangle rectangle = new Rectangle(loc, size);

      ((GraphicalEditPart) getParent()).setLayoutConstraint(this, getFigure(), rectangle);
   }

   //------------------------------------------------------------------------
   // Abstract methods from NodeEditPart

   //得到源连接的连接点
   public ConnectionAnchor getSourceConnectionAnchor(ConnectionEditPart connection) {
      return new ChopboxAnchor(getFigure());
   }

   public ConnectionAnchor getSourceConnectionAnchor(Request request) {
      return new ChopboxAnchor(getFigure());
   }

   //得到目标连接的连接点
   public ConnectionAnchor getTargetConnectionAnchor(ConnectionEditPart connection) {
      return new ChopboxAnchor(getFigure());
   }

   public ConnectionAnchor getTargetConnectionAnchor(Request request) {
      return new ChopboxAnchor(getFigure());
   }

   //得到以模型作为源的连接列表
   protected List<Connection> getModelSourceConnections() {
      return ((INode) this.getModel()).getOutgoingConnections();
   }

   //得到以模型作为目标的连接列表 
   protected List<Connection> getModelTargetConnections() {
      return ((INode) this.getModel()).getIncomingConnections();
   }
}
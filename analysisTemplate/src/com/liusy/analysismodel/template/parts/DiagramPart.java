/*
 * Created on 2005-1-24
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.liusy.analysismodel.template.parts;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.FreeformLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

import com.liusy.analysis.template.model.Diagram;
import com.liusy.analysis.template.model.DiagramParameter;
import com.liusy.analysis.template.model.dialogProperties.DiagramProperties;
import com.liusy.analysis.template.model.node.INode;
import com.liusy.analysismodel.template.policies.DiagramLayoutEditPolicy;


public class DiagramPart extends AbstractGraphicalEditPart implements PropertyChangeListener {

    protected List<INode> getModelChildren() {
    	List<INode> nodes = ((Diagram) this.getModel()).getNodes();
//    	List operateNodes = ((Diagram) this.getModel()).getOperateNodes();
    	List<INode> newList = new ArrayList<INode>();
    	newList.addAll(nodes);
//    	newList.addAll(operateNodes);
        return newList;
    }

    public void activate() {
        super.activate();
        ((Diagram) getModel()).addPropertyChangeListener(this);
    }

    public void deactivate() {
        super.deactivate();
        ((Diagram) getModel()).removePropertyChangeListener(this);
    }

    public void propertyChange(PropertyChangeEvent evt) {
        String prop = evt.getPropertyName();
        if (Diagram.PROP_NODE.equals(prop))
            refreshChildren();
        if (Diagram.PROP_NAME.equals(prop)){
        	
        }
        if (Diagram.PROP_EXTENDPROPERTY.equals(prop)){
        	setShareProperty(((Diagram) getModel()).getDiagramPropertiesBean());
        }
    }
    public void setShareProperty(DiagramProperties diagramPropertiesBean) {
// 	   List<DiagramPrameterBean> propertyList = diagramPropertiesBean.getDiagramPrameters();
 	   List<DiagramParameter> pramList = ((Diagram) getModel()).getParameterList();
 	  ((Diagram) getModel()).setParameterList(pramList);
 	   // 	  pramList = propertyList;
// 	  pramList.clear();
// 	 pramList.addAll(propertyList)
// 	   for (DiagramPrameterBean bean: propertyList) {
// 		  if (!pramList.contains(bean.getPramName())) {
// 			 pramList.add(bean.getPramName());
// 		  }
// 	   }
    }
    protected IFigure createFigure() {
        Figure figure = new FreeformLayer();
        figure.setLayoutManager(new FreeformLayout());
        return figure;
    }

    protected void createEditPolicies() {
        installEditPolicy(EditPolicy.LAYOUT_ROLE, new DiagramLayoutEditPolicy());
    }

}
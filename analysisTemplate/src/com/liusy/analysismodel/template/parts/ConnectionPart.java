/*
 * Created on 2005-1-25
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.liusy.analysismodel.template.parts;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import org.eclipse.draw2d.Bendpoint;
import org.eclipse.draw2d.BendpointConnectionRouter;
import org.eclipse.draw2d.BendpointLocator;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.MidpointLocator;
import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.RelativeLocator;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.editparts.AbstractConnectionEditPart;
import org.eclipse.gef.editpolicies.ConnectionEndpointEditPolicy;
import org.eclipse.gef.tools.DirectEditManager;
import org.eclipse.jface.viewers.TextCellEditor;

import com.liusy.analysis.template.model.connection.Connection;
import com.liusy.analysismodel.template.policies.ConnectionDirectEditPolicy;
import com.liusy.analysismodel.template.policies.ConnectionEditPolicy;

public class ConnectionPart extends AbstractConnectionEditPart implements PropertyChangeListener {
	protected DirectEditManager manager;
	public void performRequest(Request req) {
		if (req.getType().equals(RequestConstants.REQ_DIRECT_EDIT)) {
        	if (manager == null) {
        		PolylineConnection figure = (PolylineConnection) getFigure();
        		Label lab= (Label)figure.getChildren().get(1);
        		manager = new ConnectionDirectEditManager(this, TextCellEditor.class, new ConnectionCellEditorLocator(lab));
        	}
        	manager.show();
        }
	}
    protected IFigure createFigure() {
//    	Label lab = new Label("connection");
//    	ConnectionFigure figure = new ConnectionFigure(lab);
        PolylineConnection conn = new PolylineConnection();
        conn.setSourceAnchor(getSourceConnectionAnchor());
        conn.setTargetAnchor(getTargetConnectionAnchor());
        conn.setTargetDecoration(new PolygonDecoration());
        conn.setConnectionRouter(new BendpointConnectionRouter());
        conn.setForegroundColor(ColorConstants.blue);


    	Rectangle rect = new Rectangle();
    	Point point = new Point();
    	point.x = (getSourceConnectionAnchor().getReferencePoint().x+
    	getTargetConnectionAnchor().getReferencePoint().x)/2;
    	point.y = (getSourceConnectionAnchor().getReferencePoint().y+
    	    	getTargetConnectionAnchor().getReferencePoint().y)/2;

    	Label label = new Label(((Connection)getModel()).getName());

    	label.setOpaque(true);
    	conn.add(label, new MidpointLocator(conn, 0));
//    	conn.add(label, point);

//    	label.setOpaque(true);
//    	conn.add(label, new RelativeLocator(conn, PositionConstants.CENTER));
//    	conn.add(label, point);

    	 
        return conn;
    }

    protected void createEditPolicies() {
    	installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE, new ConnectionDirectEditPolicy());
    	installEditPolicy(EditPolicy.COMPONENT_ROLE, new ConnectionEditPolicy());
        installEditPolicy(EditPolicy.CONNECTION_ENDPOINTS_ROLE, new ConnectionEndpointEditPolicy());

    }

	protected void refreshVisuals() {
		Connection conn = (Connection) getModel();
//		List<Bendpoint> modelConstraint = conn.getBendpoints();
		
//		List figureConstraint = new ArrayList();
//		for (int i = 0; i < modelConstraint.size(); i++) {
//			ConnectionBendpoint cbp = (ConnectionBendpoint) modelConstraint
//					.get(i);
//			RelativeBendpoint rbp = new RelativeBendpoint(getConnectionFigure());
//			rbp.setRelativeDimensions(cbp.getFirstRelativeDimension(), cbp
//					.getSecondRelativeDimension());
//			rbp.setWeight((i + 1) / ((float) modelConstraint.size() + 1));
//			figureConstraint.add(rbp);
//		}
//		getConnectionFigure().setRoutingConstraint(modelConstraint);
		Rectangle rec = getConnectionFigure().getBounds();
		Label lab =((Label)getConnectionFigure().getChildren().get(1));
		//--
//		PolylineConnection polyConn = (PolylineConnection)getConnectionFigure();
//		RelativeLocator locator = new RelativeLocator(polyConn, PositionConstants.CENTER);
//		polyConn.setConstraint(lab, locator);
		//--
		//**
		PolylineConnection polyConn = (PolylineConnection)getConnectionFigure();
		RelativeLocator locator = new RelativeLocator(getSourceConnectionAnchor().getOwner(), PositionConstants.CENTER);
		MidpointLocator locator2 = new MidpointLocator(polyConn,0);
//		if ((conn.getBendpoints().size())%2==1) {
////			BendpointLocator cbp = (BendpointLocator) modelConstraint
////			.get((conn.getBendpoints().size()+1)/2);
//			BendpointLocator locator3 = new BendpointLocator(polyConn,(conn.getBendpoints().size()+1)/2);
//			polyConn.setConstraint(lab, locator3);
//		}
		
//		RelativeLocator loc = locator2;
		
		//**
		//--
//		PolylineConnection polyConn = (PolylineConnection)getConnectionFigure();
		
//		Rectangle rect = new Rectangle();
//		rect = lab.getBounds();
//		rect.x = (getSourceConnectionAnchor().getLocation(getSourceConnectionAnchor().getReferencePoint()).x+
//    			getTargetConnectionAnchor().getLocation(getTargetConnectionAnchor().getReferencePoint()).x)/2;
//		rect.y = (getSourceConnectionAnchor().getLocation(getSourceConnectionAnchor().getReferencePoint()).y+
//    			getTargetConnectionAnchor().getLocation(getTargetConnectionAnchor().getReferencePoint()).y)/2;

		//		Point point = new Point();
//    	point.x = (getSourceConnectionAnchor().getLocation(getSourceConnectionAnchor().getReferencePoint()).x+
//    			getTargetConnectionAnchor().getLocation(getTargetConnectionAnchor().getReferencePoint()).x)/2;
//    	point.y = (getSourceConnectionAnchor().getLocation(getSourceConnectionAnchor().getReferencePoint()).y+
//    			getTargetConnectionAnchor().getLocation(getTargetConnectionAnchor().getReferencePoint()).y)/2;
//    	polyConn.setConstraint(lab, rect);
	}

    public void setSelected(int value) {
        super.setSelected(value);
        if (value != EditPart.SELECTED_NONE)
            ((PolylineConnection) getFigure()).setLineWidth(2);
        else
            ((PolylineConnection) getFigure()).setLineWidth(1);
    }
    public void activate() {
        super.activate();
        ((Connection)getModel()).addPropertyChangeListener(this);
    }

    public void deactivate() {
        super.deactivate();
        ((Connection)getModel()).removePropertyChangeListener(this);
    }
	public void propertyChange(PropertyChangeEvent event) {
		// TODO Auto-generated method stub
		String property = event.getPropertyName();
//	    if(Connection.PROP_BENDPOINT.equals(property)){
//	        refreshBendpoints();
//	    }
	    if (Connection.PROP_NAME.equals(property)) {
	    	((PolylineConnection)getFigure()).repaint();
	    }

	}
//	public void refreshBendpoints() {
//		Connection conn = (Connection) getModel();
//		PolylineConnection polyConn = (PolylineConnection)getConnectionFigure();
//		if ((conn.getBendpoints().size())%2==1) {
//			Label lab =((Label)getConnectionFigure().getChildren().get(1));
//			BendpointLocator locator4 = new BendpointLocator(polyConn,(conn.getBendpoints().size()+1)/2);
//			polyConn.setConstraint(lab, locator4);
//		} else {
//			polyConn.setRoutingConstraint(conn.getBendpoints());
//		}
//	}
	/**
     * Updates the bendpoints, based on the model.
     */
//    protected void refreshBendpoints() {
//        if (getConnectionFigure().getConnectionRouter() instanceof ManhattanConnectionRouter)
//            return;
//        List modelConstraint = getConnectionNode().getBendpoints();
//        List figureConstraint = new ArrayList();
//        for (int i = 0; i < modelConstraint.size(); i++) {
//            FastBendpoint cbp = (FastBendpoint) modelConstraint
//                    .get(i);
//            RelativeBendpoint rbp = new RelativeBendpoint(getConnectionFigure());
//            rbp.setRelativeDimensions(cbp.getFirstRelativeDimension(), cbp
//                    .getSecondRelativeDimension());
//            rbp.setWeight((i + 1) / ((float) modelConstraint.size() + 1));
//            figureConstraint.add(rbp);
//        }
//        getConnectionFigure().setRoutingConstraint(figureConstraint);
//    }
//    private void refreshBendpointEditPolicy() {
//        if (getConnectionFigure().getConnectionRouter() instanceof ManhattanConnectionRouter)
//            installEditPolicy(EditPolicy.CONNECTION_BENDPOINTS_ROLE, null);
//        else
//            installEditPolicy(EditPolicy.CONNECTION_BENDPOINTS_ROLE,
//                    new ConnectionNodeBendpointEditPolicy());
//    }

    /**
     * Refreshes the visual aspects of this, based upon the model
     * (ConnectionNode). It changes the wire color depending on the state of
     * ConnectionNode.
     */
//    protected void refreshVisuals() {
//        refreshBendpoints();
//        if (getConnectionNode().getValue())
//            getConnectionNodeFigure().setForegroundColor(alive);
//        else
//            getConnectionNodeFigure().setForegroundColor(dead);
//    }

}
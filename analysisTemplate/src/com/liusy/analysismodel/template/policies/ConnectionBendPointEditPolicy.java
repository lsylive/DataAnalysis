//package com.liusy.analysismodel.template.policies;
//
//import org.eclipse.draw2d.AbsoluteBendpoint;
//import org.eclipse.draw2d.Bendpoint;
//import org.eclipse.draw2d.geometry.Point;
//import org.eclipse.gef.commands.Command;
//import org.eclipse.gef.editpolicies.BendpointEditPolicy;
//import org.eclipse.gef.requests.BendpointRequest;
//import com.liusy.analysismodel.template.commands.CreateBendpointCommand;
//import com.liusy.analysismodel.template.commands.DeleteBendpointCommand;
//import com.liusy.analysismodel.template.commands.MoveBendpointCommand;
//import com.liusy.analysis.template.model.connection.Connection;
//
//public class ConnectionBendPointEditPolicy extends BendpointEditPolicy {
//
//	@Override
//	protected Command getCreateBendpointCommand(BendpointRequest request) {
//		// TODO Auto-generated method stub
//		int index = request.getIndex();
//		Connection conn = (Connection)request.getSource().getModel();
//		AbsoluteBendpoint p = new AbsoluteBendpoint(request.getLocation());
//		CreateBendpointCommand cmd = new CreateBendpointCommand(index,conn,p);
//		return cmd;
//	}
//
//	@Override
//	protected Command getDeleteBendpointCommand(BendpointRequest request) {
//		// TODO Auto-generated method stub
//		int index = request.getIndex();
//		Connection conn = (Connection)request.getSource().getModel();
//		Bendpoint p = (Bendpoint)conn.getBendpoints().get(index);
//		DeleteBendpointCommand cmd = new DeleteBendpointCommand(index,conn,p);
//		return cmd;
//
//	}
//
//	@Override
//	protected Command getMoveBendpointCommand(BendpointRequest request) {
//		// TODO Auto-generated method stub
//		int index = request.getIndex();
//		Connection conn = (Connection)request.getSource().getModel();
//		AbsoluteBendpoint p = new AbsoluteBendpoint(request.getLocation());
//		MoveBendpointCommand cmd = new MoveBendpointCommand(index,conn,p);
//		return cmd;
//	}
//
//}

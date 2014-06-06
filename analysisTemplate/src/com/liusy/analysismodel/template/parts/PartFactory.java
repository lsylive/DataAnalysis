/*
 * Created on 2005-1-24 TODO To change the template for this generated file go
 * to Window - Preferences - Java - Code Style - Code Templates
 */
package com.liusy.analysismodel.template.parts;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;

import com.liusy.analysis.template.model.Diagram;
import com.liusy.analysis.template.model.connection.Connection;
import com.liusy.analysis.template.model.node.ConditionQueryNode;
import com.liusy.analysis.template.model.node.DataNode;
import com.liusy.analysis.template.model.node.DetailQueryInterfaceNode;
import com.liusy.analysis.template.model.node.EndNode;
import com.liusy.analysis.template.model.node.FilterNode;
import com.liusy.analysis.template.model.node.GenerateColumnNode;
import com.liusy.analysis.template.model.node.IntersectionNode;
import com.liusy.analysis.template.model.node.JudgeNode;
import com.liusy.analysis.template.model.node.LineChartInterfaceNode;
import com.liusy.analysis.template.model.node.MultiDataNode;
import com.liusy.analysis.template.model.node.NetChartInterfaceNode;
import com.liusy.analysis.template.model.node.OperateNode;
import com.liusy.analysis.template.model.node.ParametersNode;
import com.liusy.analysis.template.model.node.PathSearchNode;
import com.liusy.analysis.template.model.node.QueryInterfaceNode;
import com.liusy.analysis.template.model.node.SortNode;
import com.liusy.analysis.template.model.node.StarChartInterfaceNode;
import com.liusy.analysis.template.model.node.StartNode;
import com.liusy.analysis.template.model.node.StatisticInterfaceNode;
import com.liusy.analysis.template.model.node.SubDiagramNode;
import com.liusy.analysis.template.model.node.SumNode;
import com.liusy.analysis.template.model.node.SyncContrastNode;
import com.liusy.analysis.template.model.node.TimeLineChartInterfaceNode;
import com.liusy.analysis.template.model.node.UnionNode;
import com.liusy.analysis.template.model.node.UnionOperationNode;

public class PartFactory implements EditPartFactory {

	public EditPart createEditPart(EditPart context, Object model) {
		EditPart part = null;
		// 根据模型创建相应的EditPart
		if (model instanceof Diagram) part = new DiagramPart();
		else if (model instanceof Connection) {
			part = new ConnectionPart();
		}
		else if (model instanceof StartNode) {
			part = new StartNodePart();
		}
		else if (model instanceof DataNode) {
			part = new DataNodePart();
		}
		else if (model instanceof MultiDataNode) {
			part = new MultiTableDataNodePart();
		}
		else if (model instanceof ParametersNode) {
			part = new ParametersNodePart();
		}
		else if (model instanceof SubDiagramNode) {
			part = new SubDiagramNodePart();
		}
		else if (model instanceof OperateNode) {
			part = new OperateNodePart();
		}
		else if (model instanceof IntersectionNode) {
			part = new IntersectionNodePart();
		}
		else if (model instanceof UnionNode) {
			part = new UnionNodePart();
		}
		else if (model instanceof SyncContrastNode) {
			part = new SyncContrastNodePart();
		}
		else if (model instanceof UnionOperationNode) {
			part = new UnionOperationNodePart();
		}
		else if (model instanceof QueryInterfaceNode) {
			part = new QueryInterfaceNodePart();
		}
		else if (model instanceof DetailQueryInterfaceNode) {
			part = new DetailQueryInterfaceNodePart();
		}
		else if (model instanceof StarChartInterfaceNode) {
			part = new StarChartInterfaceNodePart();
		}
		else if (model instanceof TimeLineChartInterfaceNode) {
			part = new TimeLineChartInterfaceNodePart();
		}
		else if (model instanceof NetChartInterfaceNode) {
			part = new NetChartInterfaceNodePart();
		}
		else if (model instanceof GenerateColumnNode) {
			part = new GenerateColumnNodePart();
		}
		else if (model instanceof ConditionQueryNode) {
			part = new ConditionQueryNodePart();
		}
		else if (model instanceof PathSearchNode) {
			part = new PathSearchNodePart();
		}
		else if (model instanceof FilterNode) {
			part = new FilterNodePart();
		}
		else if (model instanceof SortNode) {
			part = new SortNodePart();
		}
		else if (model instanceof LineChartInterfaceNode) {
			part = new LineChartNodePart();
		}
		else if (model instanceof JudgeNode) {
			part = new JudgeNodePart();
		}
		else if (model instanceof StatisticInterfaceNode) {
			part = new StatisticNodePart();
		}
		else if (model instanceof EndNode) {
			part = new EndNodePart();
		}
		else if (model instanceof SumNode) {
			part = new SumNodePart();
		}

		if (part != null) part.setModel(model);
		return part;
	}
}
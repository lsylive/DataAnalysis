/*
 * Created on Jul 22, 2004
 */
package com.liusy.analysismodel.template.actions;

import java.util.Map;

import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.swt.widgets.Shell;

import com.liusy.analysismodel.template.commands.DataNodeBeanEditCommand;
import com.liusy.analysismodel.template.commands.DetailNodeBeanEditCommand;
import com.liusy.analysismodel.template.commands.GenerateColumnNodeBeanEditCommand;
import com.liusy.analysismodel.template.commands.LineChartNodeBeanEditCommand;
import com.liusy.analysismodel.template.commands.MultiDataNodeBeanEditCommand;
import com.liusy.analysismodel.template.commands.NodePropertyEditCommand;
import com.liusy.analysismodel.template.commands.StatisticNodeBeanEditCommand;
import com.liusy.analysis.template.model.Diagram;
import com.liusy.analysis.template.model.node.ConditionQueryNode;
import com.liusy.analysis.template.model.node.DataNode;
import com.liusy.analysis.template.model.node.DetailQueryInterfaceNode;
import com.liusy.analysis.template.model.node.FilterNode;
import com.liusy.analysis.template.model.node.GenerateColumnNode;
import com.liusy.analysis.template.model.node.IntersectionNode;
import com.liusy.analysis.template.model.node.LineChartInterfaceNode;
import com.liusy.analysis.template.model.node.MultiDataNode;
import com.liusy.analysis.template.model.node.NetChartInterfaceNode;
import com.liusy.analysis.template.model.node.ParametersNode;
import com.liusy.analysis.template.model.node.PathSearchNode;
import com.liusy.analysis.template.model.node.QueryInterfaceNode;
import com.liusy.analysis.template.model.node.SortNode;
import com.liusy.analysis.template.model.node.StarChartInterfaceNode;
import com.liusy.analysis.template.model.node.StatisticInterfaceNode;
import com.liusy.analysis.template.model.node.SubDiagramNode;
import com.liusy.analysis.template.model.node.SumNode;
import com.liusy.analysis.template.model.node.SyncContrastNode;
import com.liusy.analysis.template.model.node.TimeLineChartInterfaceNode;
import com.liusy.analysis.template.model.node.UnionNode;
import com.liusy.analysis.template.model.node.UnionOperationNode;
import com.liusy.analysis.template.model.util.StringUtil;
import com.liusy.analysismodel.template.parts.ConditionQueryNodePart;
import com.liusy.analysismodel.template.parts.ConnectionPart;
import com.liusy.analysismodel.template.parts.DataNodePart;
import com.liusy.analysismodel.template.parts.DetailQueryInterfaceNodePart;
import com.liusy.analysismodel.template.parts.DiagramPart;
import com.liusy.analysismodel.template.parts.EndNodePart;
import com.liusy.analysismodel.template.parts.FilterNodePart;
import com.liusy.analysismodel.template.parts.GenerateColumnNodePart;
import com.liusy.analysismodel.template.parts.IntersectionNodePart;
import com.liusy.analysismodel.template.parts.LineChartNodePart;
import com.liusy.analysismodel.template.parts.MultiTableDataNodePart;
import com.liusy.analysismodel.template.parts.NetChartInterfaceNodePart;
import com.liusy.analysismodel.template.parts.ParametersNodePart;
import com.liusy.analysismodel.template.parts.PathSearchNodePart;
import com.liusy.analysismodel.template.parts.QueryInterfaceNodePart;
import com.liusy.analysismodel.template.parts.SortNodePart;
import com.liusy.analysismodel.template.parts.StarChartInterfaceNodePart;
import com.liusy.analysismodel.template.parts.StatisticNodePart;
import com.liusy.analysismodel.template.parts.SubDiagramNodePart;
import com.liusy.analysismodel.template.parts.SumNodePart;
import com.liusy.analysismodel.template.parts.SyncContrastNodePart;
import com.liusy.analysismodel.template.parts.TimeLineChartInterfaceNodePart;
import com.liusy.analysismodel.template.parts.UnionNodePart;
import com.liusy.analysismodel.template.parts.UnionOperationNodePart;
import com.liusy.analysismodel.template.ui.ConditionQueryNodeDialog;
import com.liusy.analysismodel.template.ui.DetailQueryInterfaceNodeDialog;
import com.liusy.analysismodel.template.ui.DiagramPropertyDialog;
import com.liusy.analysismodel.template.ui.FilterNodeDialog;
import com.liusy.analysismodel.template.ui.GenerateColumnNodeDialog;
import com.liusy.analysismodel.template.ui.IntersectionNodeDialog;
import com.liusy.analysismodel.template.ui.LineChartInterfaceNodeDialog;
import com.liusy.analysismodel.template.ui.MultiTableQueryNodeDialog;
import com.liusy.analysismodel.template.ui.NetChartInterfaceNodeDialog;
import com.liusy.analysismodel.template.ui.ParametersNodeDialog;
import com.liusy.analysismodel.template.ui.PathSearchNodeDialog;
import com.liusy.analysismodel.template.ui.QueryInterfaceNodeDialog;
import com.liusy.analysismodel.template.ui.SortNodeDialog;
import com.liusy.analysismodel.template.ui.StarChartInterfaceNodeDialog;
import com.liusy.analysismodel.template.ui.StatisticInterfaceNodeDialog;
import com.liusy.analysismodel.template.ui.SubDiagramNodeDialog;
import com.liusy.analysismodel.template.ui.SumNodeDialog;
import com.liusy.analysismodel.template.ui.SyncContrastNodeDialog;
import com.liusy.analysismodel.template.ui.TableQueryNodeDialog;
import com.liusy.analysismodel.template.ui.TimeLineChartInterfaceNodeDialog;
import com.liusy.analysismodel.template.ui.UnionNodeDialog;
import com.liusy.analysismodel.template.ui.UnionOperationNodeDialog;

public class SchemaContextMenuProvider extends ContextMenuProvider {

	private ActionRegistry	actionRegistry;
	private EditPartViewer	view;

	// private IMenuManager locMenu;

	/**
	 * Creates a new FlowContextMenuProvider assoicated with the given viewer and
	 * action registry.
	 * 
	 * @param viewer
	 *           the viewer
	 * @param registry
	 *           the action registry
	 */
	public SchemaContextMenuProvider(EditPartViewer viewer, ActionRegistry registry) {
		super(viewer);
		setActionRegistry(registry);
		this.view = viewer;
	}

	/**
	 * @see ContextMenuProvider#buildContextMenu(org.eclipse.jface.action.IMenuManager)
	 */
	public void buildContextMenu(IMenuManager menu) {
		final EditPartViewer view = super.getViewer();
		final EditPart editPart = view.getFocusEditPart();
		Map map = view.getEditPartRegistry();
		GEFActionConstants.addStandardActionGroups(menu);
		// locMenu = menu;
		IAction action;

		if (editPart.getClass() == DiagramPart.class) {
			action = new Action("属性(&P)") {
				public void run() {
					Diagram diagram = (Diagram) editPart.getModel();
					Shell shell = view.getControl().getShell();
					NodePropertyEditCommand cmd = new NodePropertyEditCommand();
					DiagramPropertyDialog dlg = new DiagramPropertyDialog(shell, diagram, cmd);
					int result = dlg.open();
					if (result == 1) {
						view.getEditDomain().getCommandStack().execute(cmd);
					}
				}
			};
			menu.appendToGroup(GEFActionConstants.GROUP_EDIT, action);
			UndoAction myAction = (UndoAction) getActionRegistry().getAction(StringUtil.undoId);
			myAction.setMenuProvider(this);
			RedoAction redoAction = (RedoAction) getActionRegistry().getAction(StringUtil.redoId);
			menu.appendToGroup(GEFActionConstants.GROUP_UNDO, myAction);
			menu.appendToGroup(GEFActionConstants.GROUP_UNDO, redoAction);
			// action = getActionRegistry().getAction(REDO);
			// menu.appendToGroup(GEFActionConstants.GROUP_UNDO, action);
		}
		else if (editPart.getClass() == ConnectionPart.class) {
		}
		else if (editPart.getClass() == DataNodePart.class) {
			action = new Action("属性(&P)") {
				public void run() {
					DataNodePart dataNodePart = (DataNodePart) editPart;
					DataNode model = (DataNode) dataNodePart.getModel();
					Shell shell = view.getControl().getShell();
					DataNodeBeanEditCommand cmd = new DataNodeBeanEditCommand();
					TableQueryNodeDialog dlg = new TableQueryNodeDialog(shell, model, cmd);
					int result = dlg.open();
					if (result == 1) {
						view.getEditDomain().getCommandStack().execute(cmd);
					}
				}
			};
			menu.appendToGroup(GEFActionConstants.GROUP_EDIT, action);
		}
		else if (editPart.getClass() == MultiTableDataNodePart.class) {
			action = new Action("属性(&P)") {
				public void run() {
					MultiTableDataNodePart dataNodePart = (MultiTableDataNodePart) editPart;
					MultiDataNode model = (MultiDataNode) dataNodePart.getModel();
					Shell shell = view.getControl().getShell();
					MultiDataNodeBeanEditCommand cmd = new MultiDataNodeBeanEditCommand();
					MultiTableQueryNodeDialog dlg = new MultiTableQueryNodeDialog(shell, model, cmd);
					int result = dlg.open();
					if (result == 1) {
						view.getEditDomain().getCommandStack().execute(cmd);
					}
				}
			};
			menu.appendToGroup(GEFActionConstants.GROUP_EDIT, action);
		}
		else if (editPart.getClass() == ConditionQueryNodePart.class) {
			action = new Action("属性(&P)") {
				public void run() {
					ConditionQueryNodePart dataNodePart = (ConditionQueryNodePart) editPart;
					ConditionQueryNode model = (ConditionQueryNode) dataNodePart.getModel();
					Shell shell = view.getControl().getShell();
					NodePropertyEditCommand cmd = new NodePropertyEditCommand();
					ConditionQueryNodeDialog dlg = new ConditionQueryNodeDialog(shell, model, cmd);
					int result = dlg.open();
					if (result == 1) {
						view.getEditDomain().getCommandStack().execute(cmd);
					}
				}
			};
			menu.appendToGroup(GEFActionConstants.GROUP_EDIT, action);
		}
		else if (editPart.getClass() == ParametersNodePart.class) {
			action = new Action("属性(&P)") {
				public void run() {
					ParametersNodePart ParametersNodePart = (ParametersNodePart) editPart;
					ParametersNode model = (ParametersNode) ParametersNodePart.getModel();
					Shell shell = view.getControl().getShell();
					NodePropertyEditCommand cmd = new NodePropertyEditCommand();
					ParametersNodeDialog dlg = new ParametersNodeDialog(shell, model, cmd);
					int result = dlg.open();
					if (result == 1) {
						view.getEditDomain().getCommandStack().execute(cmd);
					}
				}
			};
			menu.appendToGroup(GEFActionConstants.GROUP_EDIT, action);
		}
		else if (editPart.getClass() == SubDiagramNodePart.class) {
			action = new Action("属性(&P)") {
				public void run() {
					SubDiagramNodePart SubDiagramNodePart = (SubDiagramNodePart) editPart;
					SubDiagramNode model = (SubDiagramNode) SubDiagramNodePart.getModel();
					Shell shell = view.getControl().getShell();
					NodePropertyEditCommand cmd = new NodePropertyEditCommand();
					SubDiagramNodeDialog dlg = new SubDiagramNodeDialog(shell, model, cmd);
					int result = dlg.open();
					if (result == 1) {
						view.getEditDomain().getCommandStack().execute(cmd);
					}
				}
			};
			menu.appendToGroup(GEFActionConstants.GROUP_EDIT, action);
		}
		else if (editPart.getClass() == PathSearchNodePart.class) {
			action = new Action("属性(&P)") {
				public void run() {
					PathSearchNodePart part = (PathSearchNodePart) editPart;
					PathSearchNode model = (PathSearchNode) part.getModel();
					NodePropertyEditCommand cmd = new NodePropertyEditCommand();
					Shell shell = view.getControl().getShell();
					PathSearchNodeDialog dlg = new PathSearchNodeDialog(shell, model, cmd);
					int result = dlg.open();
					if (result == 1) {
						view.getEditDomain().getCommandStack().execute(cmd);
					}
				}
			};
			menu.appendToGroup(GEFActionConstants.GROUP_EDIT, action);
		}
		else if (editPart.getClass() == QueryInterfaceNodePart.class) {
			action = new Action("属性(&P)") {
				public void run() {
					QueryInterfaceNodePart part = (QueryInterfaceNodePart) editPart;
					QueryInterfaceNode model = (QueryInterfaceNode) part.getModel();
					NodePropertyEditCommand cmd = new NodePropertyEditCommand();
					Shell shell = view.getControl().getShell();
					QueryInterfaceNodeDialog dlg = new QueryInterfaceNodeDialog(shell, model, cmd);
					int result = dlg.open();
					if (result == 1) {
						view.getEditDomain().getCommandStack().execute(cmd);
					}
				}
			};
			menu.appendToGroup(GEFActionConstants.GROUP_EDIT, action);
		}
		else if (editPart.getClass() == DetailQueryInterfaceNodePart.class) {
			action = new Action("属性(&P)") {
				public void run() {
					DetailQueryInterfaceNodePart part = (DetailQueryInterfaceNodePart) editPart;
					DetailQueryInterfaceNode model = (DetailQueryInterfaceNode) part.getModel();
					DetailNodeBeanEditCommand cmd = new DetailNodeBeanEditCommand();
					Shell shell = view.getControl().getShell();
					DetailQueryInterfaceNodeDialog dlg = new DetailQueryInterfaceNodeDialog(shell, model, cmd);
					int result = dlg.open();
					if (result == 1) {
						view.getEditDomain().getCommandStack().execute(cmd);
					}
				}
			};
			menu.appendToGroup(GEFActionConstants.GROUP_EDIT, action);
		}
		else if (editPart.getClass() == StarChartInterfaceNodePart.class) {
			action = new Action("属性(&P)") {
				public void run() {
					StarChartInterfaceNodePart part = (StarChartInterfaceNodePart) editPart;
					StarChartInterfaceNode model = (StarChartInterfaceNode) part.getModel();
					NodePropertyEditCommand cmd = new NodePropertyEditCommand();
					Shell shell = view.getControl().getShell();
					StarChartInterfaceNodeDialog dlg = new StarChartInterfaceNodeDialog(shell, model, cmd);
					int result = dlg.open();
					if (result == 1) {
						view.getEditDomain().getCommandStack().execute(cmd);
					}
				}
			};
			menu.appendToGroup(GEFActionConstants.GROUP_EDIT, action);
		}
		else if (editPart.getClass() == TimeLineChartInterfaceNodePart.class) {
			action = new Action("属性(&P)") {
				public void run() {
					TimeLineChartInterfaceNodePart part = (TimeLineChartInterfaceNodePart) editPart;
					TimeLineChartInterfaceNode model = (TimeLineChartInterfaceNode) part.getModel();
					NodePropertyEditCommand cmd = new NodePropertyEditCommand();
					Shell shell = view.getControl().getShell();
					TimeLineChartInterfaceNodeDialog dlg = new TimeLineChartInterfaceNodeDialog(shell, model, cmd);
					int result = dlg.open();
					if (result == 1) {
						view.getEditDomain().getCommandStack().execute(cmd);
					}
				}
			};
			menu.appendToGroup(GEFActionConstants.GROUP_EDIT, action);
		}
		else if (editPart.getClass() == IntersectionNodePart.class) {
			action = new Action("属性(&P)") {
				public void run() {
					IntersectionNodePart IntersectionNodePart = (IntersectionNodePart) editPart;
					IntersectionNode model = (IntersectionNode) IntersectionNodePart.getModel();
					NodePropertyEditCommand cmd = new NodePropertyEditCommand();
					Shell shell = view.getControl().getShell();
					IntersectionNodeDialog dlg = new IntersectionNodeDialog(shell, model, cmd);
					int result = dlg.open();
					if (result == 1) {
						view.getEditDomain().getCommandStack().execute(cmd);
					}
				}
			};
			menu.appendToGroup(GEFActionConstants.GROUP_EDIT, action);
		}
		else if (editPart.getClass() == UnionNodePart.class) {
			action = new Action("属性(&P)") {
				public void run() {
					UnionNodePart UnionNodePart = (UnionNodePart) editPart;
					UnionNode model = (UnionNode) UnionNodePart.getModel();
					NodePropertyEditCommand cmd = new NodePropertyEditCommand();
					Shell shell = view.getControl().getShell();
					UnionNodeDialog dlg = new UnionNodeDialog(shell, model, cmd);
					int result = dlg.open();
					if (result == 1) {
						view.getEditDomain().getCommandStack().execute(cmd);
					}
				}
			};
			menu.appendToGroup(GEFActionConstants.GROUP_EDIT, action);
		}
		else if (editPart.getClass() == UnionOperationNodePart.class) {
			action = new Action("属性(&P)") {
				public void run() {
					UnionOperationNodePart UnionOperationNodePart = (UnionOperationNodePart) editPart;
					UnionOperationNode model = (UnionOperationNode) UnionOperationNodePart.getModel();
					Shell shell = view.getControl().getShell();
					NodePropertyEditCommand cmd = new NodePropertyEditCommand();
					UnionOperationNodeDialog dlg = new UnionOperationNodeDialog(shell, model, cmd);
					int result = dlg.open();
					if (result == 1) {
						view.getEditDomain().getCommandStack().execute(cmd);
					}
				}
			};
			menu.appendToGroup(GEFActionConstants.GROUP_EDIT, action);
		}
		else if (editPart.getClass() == SyncContrastNodePart.class) {
			action = new Action("属性(&P)") {
				public void run() {
					SyncContrastNodePart SyncContrastNodePart = (SyncContrastNodePart) editPart;
					SyncContrastNode model = (SyncContrastNode) SyncContrastNodePart.getModel();
					Shell shell = view.getControl().getShell();
					NodePropertyEditCommand cmd = new NodePropertyEditCommand();
					SyncContrastNodeDialog dlg = new SyncContrastNodeDialog(shell, model, cmd);
					int result = dlg.open();
					if (result == 1) {
						view.getEditDomain().getCommandStack().execute(cmd);
					}
				}
			};
			menu.appendToGroup(GEFActionConstants.GROUP_EDIT, action);
		}
		else if (editPart.getClass() == NetChartInterfaceNodePart.class) {
			action = new Action("属性(&P)") {
				public void run() {
					NetChartInterfaceNodePart part = (NetChartInterfaceNodePart) editPart;
					NetChartInterfaceNode model = (NetChartInterfaceNode) part.getModel();
					NodePropertyEditCommand cmd = new NodePropertyEditCommand();
					Shell shell = view.getControl().getShell();
					NetChartInterfaceNodeDialog dlg = new NetChartInterfaceNodeDialog(shell, model, cmd);
					int result = dlg.open();
					if (result == 1) {
						view.getEditDomain().getCommandStack().execute(cmd);
					}
				}
			};
			menu.appendToGroup(GEFActionConstants.GROUP_EDIT, action);
		}
		else if (editPart.getClass() == GenerateColumnNodePart.class) {
			action = new Action("属性(&P)") {
				public void run() {
					GenerateColumnNodePart part = (GenerateColumnNodePart) editPart;
					GenerateColumnNode model = (GenerateColumnNode) part.getModel();
					GenerateColumnNodeBeanEditCommand cmd = new GenerateColumnNodeBeanEditCommand();
					Shell shell = view.getControl().getShell();
					GenerateColumnNodeDialog dlg = new GenerateColumnNodeDialog(shell, model, cmd);
					int result = dlg.open();
					if (result == 1) {
						view.getEditDomain().getCommandStack().execute(cmd);
					}
				}
			};
			menu.appendToGroup(GEFActionConstants.GROUP_EDIT, action);
		}
		else if (editPart.getClass() == FilterNodePart.class) {
			action = new Action("属性(&P)") {
				public void run() {
					FilterNodePart part = (FilterNodePart) editPart;
					FilterNode model = (FilterNode) part.getModel();
					NodePropertyEditCommand cmd = new NodePropertyEditCommand();
					Shell shell = view.getControl().getShell();
					FilterNodeDialog dlg = new FilterNodeDialog(shell, model, cmd);
					int result = dlg.open();
					if (result == 1) {
						view.getEditDomain().getCommandStack().execute(cmd);
					}
				}
			};
			menu.appendToGroup(GEFActionConstants.GROUP_EDIT, action);
		}
		else if (editPart.getClass() == SortNodePart.class) {
			action = new Action("属性(&P)") {
				public void run() {
					SortNodePart part = (SortNodePart) editPart;
					SortNode model = (SortNode) part.getModel();
					NodePropertyEditCommand cmd = new NodePropertyEditCommand();
					Shell shell = view.getControl().getShell();
					SortNodeDialog dlg = new SortNodeDialog(shell, model, cmd);
					int result = dlg.open();
					if (result == 1) {
						view.getEditDomain().getCommandStack().execute(cmd);
					}
				}
			};
			menu.appendToGroup(GEFActionConstants.GROUP_EDIT, action);
		}
		else if (editPart.getClass() == StatisticNodePart.class) {
			action = new Action("属性(&P)") {
				public void run() {
					StatisticNodePart part = (StatisticNodePart) editPart;
					StatisticInterfaceNode model = (StatisticInterfaceNode) part.getModel();
					StatisticNodeBeanEditCommand cmd = new StatisticNodeBeanEditCommand();
					Shell shell = view.getControl().getShell();
					StatisticInterfaceNodeDialog dlg = new StatisticInterfaceNodeDialog(shell, model, cmd);
					int result = dlg.open();
					if (result == 1) {
						view.getEditDomain().getCommandStack().execute(cmd);
					}
				}
			};
			menu.appendToGroup(GEFActionConstants.GROUP_EDIT, action);
		}
		else if (editPart.getClass() == LineChartNodePart.class) {
			action = new Action("属性(&P)") {
				public void run() {
					LineChartNodePart part = (LineChartNodePart) editPart;
					LineChartInterfaceNode model = (LineChartInterfaceNode) part.getModel();
					LineChartNodeBeanEditCommand cmd = new LineChartNodeBeanEditCommand();
					Shell shell = view.getControl().getShell();
					LineChartInterfaceNodeDialog dlg = new LineChartInterfaceNodeDialog(shell, model, cmd);
					int result = dlg.open();
					if (result == 1) {
						view.getEditDomain().getCommandStack().execute(cmd);
					}
				}
			};
			menu.appendToGroup(GEFActionConstants.GROUP_EDIT, action);
		}
		else if (editPart.getClass() == SumNodePart.class) {
			action = new Action("属性(&P)") {
				public void run() {
					SumNodePart sumNodePart = (SumNodePart) editPart;
					SumNode model = (SumNode) sumNodePart.getModel();
					Shell shell = view.getControl().getShell();
					NodePropertyEditCommand cmd = new NodePropertyEditCommand();
					SumNodeDialog dlg = new SumNodeDialog(shell, model, cmd);
					int result = dlg.open();
				}
			};
			menu.appendToGroup(GEFActionConstants.GROUP_EDIT, action);
		}
		else if (editPart.getClass() == EndNodePart.class) {
		}

		if (editPart.getClass() != DiagramPart.class) {
			action = getActionRegistry().getAction(StringUtil.deleteId);
			menu.appendToGroup(GEFActionConstants.GROUP_UNDO, action);
		}
	}

	public static String	DELETE	= "delete";
	public static String	UNDO		= "undo";
	public static String	REDO		= "redo";

	private ActionRegistry getActionRegistry() {
		return actionRegistry;
	}

	/**
	 * Sets the action registry
	 * 
	 * @param registry
	 *           the action registry
	 */
	public void setActionRegistry(ActionRegistry registry) {
		actionRegistry = registry;
	}

}
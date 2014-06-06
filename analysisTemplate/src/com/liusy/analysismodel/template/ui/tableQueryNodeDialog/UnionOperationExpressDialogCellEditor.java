package com.liusy.analysismodel.template.ui.tableQueryNodeDialog;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.DialogCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

import com.liusy.analysis.template.model.Diagram;
import com.liusy.analysis.template.model.node.INode;
import com.liusy.analysis.template.model.vo.UnionOperationField;
import com.liusy.analysismodel.template.ui.dialog.ExpressionBuilderDialog;
import com.liusy.analysismodel.template.ui.dialog.GenerateExpressionDialog;

public class UnionOperationExpressDialogCellEditor extends DialogCellEditor {

	private List<INode>	nodeList	= new ArrayList<INode>();
	private Table			table;
	private Diagram		diagram;

	public UnionOperationExpressDialogCellEditor(Composite parent, List<INode> nodeList, Diagram diagram) {
		super(parent);
		this.nodeList = nodeList;
		this.table = (Table) parent;
		this.diagram = diagram;
	}

	protected Object openDialogBox(Control cellEditorWindow) {

		TableItem[] selectItems = table.getSelection();
		UnionOperationField Field = null;
		String exp = "";
		if (selectItems != null && selectItems.length > 0) {
			Field = (UnionOperationField) selectItems[0].getData();
			exp = Field.getExpression();
		}

		ExpressionBuilderDialog dialog = new ExpressionBuilderDialog(cellEditorWindow.getShell(), nodeList, diagram, exp);
//      GenerateExpressionDialog dialog = new GenerateExpressionDialog(cellEditorWindow.getShell(), exp);
		String res = dialog.open();

		return res;

	}

	protected Button createButton(Composite parent) {

		Button result = new Button(parent, SWT.PUSH);

		result.setText("...");

		return result;

	}

}

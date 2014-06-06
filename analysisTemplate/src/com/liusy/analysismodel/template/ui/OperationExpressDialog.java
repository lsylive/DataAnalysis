package com.liusy.analysismodel.template.ui;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;

import com.swtdesigner.ResourceManager;
import com.liusy.analysismodel.Activator;
import com.liusy.analysis.template.model.Consts;
import com.liusy.analysis.template.model.base.Metadata;
import com.liusy.analysis.template.model.node.INode;
import com.liusy.analysis.template.model.vo.UnionOperationField;
import com.liusy.analysismodel.template.ui.contentProvider.ViewContentProvider;

public class OperationExpressDialog extends Dialog {

	protected String					result;
	protected Shell					shell;
	private List<INode>				nodeList;
	private Text						expressTxt;
	private Table						nodeFieldtbl;
	private Combo						OperationCombo;
	private Combo						nodeNameCombo;
	private TableViewer				nodeFieldtv;
	public String[]					DATAFIELDS	= new String[] { "cnName", "name", "name", "dataType" };
	public List<Metadata>			metaList		= new ArrayList<Metadata>();
	private UnionOperationField	Field;

	public OperationExpressDialog(Shell parent, int style) {
		super(parent, style);
	}

	public OperationExpressDialog(Shell parent, List<INode> nodeList, UnionOperationField Field) {

		this(parent, SWT.NONE);
		this.nodeList = nodeList;
		this.Field = Field;
	}

	public String open() {
		createContents();
		shell.setLocation(getParentLocation());
		shell.setLayout(new FormLayout());
		shell.open();
		shell.layout();
		Display display = getParent().getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shell = new Shell(getParent(), SWT.DIALOG_TRIM);
		shell.setSize(483, 285);
		shell.setText("运算公式");

		Composite composite_1 = new Composite(shell, SWT.NONE);
		composite_1.setLayout(new FormLayout());
		FormData fd_composite_1 = new FormData();
		fd_composite_1.top = new FormAttachment(0, 0);
		fd_composite_1.left = new FormAttachment(0, 10);
		fd_composite_1.right = new FormAttachment(100, -10);
		composite_1.setLayoutData(fd_composite_1);

		Label label = new Label(composite_1, SWT.NONE);
		// fd_nodeNameCombo.left = new FormAttachment(label, 20);
		FormData fd_label = new FormData();
		fd_label.left = new FormAttachment(0, 0);
		fd_label.top = new FormAttachment(0, 7);
		label.setLayoutData(fd_label);
		label.setText("结点名称：");

		nodeNameCombo = new Combo(composite_1, SWT.NONE);
		nodeNameCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				metaList.clear();
				String nodeName = nodeNameCombo.getText().trim();
				for (INode node : nodeList) {
					if (node.getName().equals(nodeName)) {
						metaList = node.getMeta();
						nodeFieldtv.setInput(metaList);
						nodeFieldtv.refresh();
						break;
					}
				}

			}
		});
		nodeNameCombo.removeAll();
		for (int i = 0; i < this.nodeList.size(); i++) {
			INode node = nodeList.get(i);
			String nodeName = node.getName();
			nodeNameCombo.add(nodeName);
		}
		FormData fd_nodeNameCombo = new FormData();
		fd_nodeNameCombo.top = new FormAttachment(0, 7);
		fd_nodeNameCombo.left = new FormAttachment(label, 0);
		fd_nodeNameCombo.right = new FormAttachment(50, -10);
		nodeNameCombo.setLayoutData(fd_nodeNameCombo);

		Label label_1 = new Label(composite_1, SWT.NONE);
		// fd_nodeNameCombo.right = new FormAttachment(label_1, -22);
		label_1.setAlignment(SWT.CENTER);
		FormData fd_label_1 = new FormData();
		fd_label_1.left = new FormAttachment(50, 10);
		fd_label_1.top = new FormAttachment(0, 7);
		fd_label_1.width = 60;
		label_1.setLayoutData(fd_label_1);
		label_1.setText("运算法则：");

		OperationCombo = new Combo(composite_1, SWT.NONE);
		OperationCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				expressTxt.append(OperationCombo.getText());
			}
		});
		//OperationCombo.setItems(new String[] {"+", "-", "*", "/"});
		OperationCombo.add("");
		OperationCombo.add("+");
		OperationCombo.add("-");
		OperationCombo.add("*");
		OperationCombo.add("/");
		FormData fd_OperationCombo = new FormData();
		fd_OperationCombo.right = new FormAttachment(100, 0);
		fd_OperationCombo.left = new FormAttachment(label_1, 0);
		fd_OperationCombo.top = new FormAttachment(0, 7);
		OperationCombo.setLayoutData(fd_OperationCombo);

		Composite composite = new Composite(shell, SWT.NONE);
		composite.setLayout(new FormLayout());
		FormData fd_composite = new FormData();
		fd_composite.left = new FormAttachment(0, 10);
		fd_composite.top = new FormAttachment(composite_1, 10);
		fd_composite.right = new FormAttachment(100, -10);
		fd_composite.bottom = new FormAttachment(100, -10);
		composite.setLayoutData(fd_composite);

		Composite composite_2 = new Composite(composite, SWT.NONE);
		composite_2.setLayout(new FormLayout());
		FormData fd_composite_2 = new FormData();
		fd_composite_2.top = new FormAttachment(0, 0);
		fd_composite_2.left = new FormAttachment(0, 0);
		fd_composite_2.bottom = new FormAttachment(0, 140);
		fd_composite_2.right = new FormAttachment(100, 0);
		composite_2.setLayoutData(fd_composite_2);

		nodeFieldtv = new TableViewer(composite_2, SWT.BORDER | SWT.FULL_SELECTION);
		nodeFieldtv.setContentProvider(new ViewContentProvider());
		nodeFieldtv.setLabelProvider(new OperationExpressLabelProvider());
		addListener(nodeFieldtv);
		// nodeFieldtv.setColumnProperties(DATAFIELDS);
		nodeFieldtbl = nodeFieldtv.getTable();
		nodeFieldtbl.setLinesVisible(true);
		nodeFieldtbl.setHeaderVisible(true);
		FormData fd_nodeFieldtbl = new FormData();
		fd_nodeFieldtbl.right = new FormAttachment(100, 0);
		fd_nodeFieldtbl.top = new FormAttachment(0, 0);
		fd_nodeFieldtbl.bottom = new FormAttachment(100, 0);
		fd_nodeFieldtbl.left = new FormAttachment(0, 0);
		nodeFieldtbl.setLayoutData(fd_nodeFieldtbl);

		TableColumn tableColumn = new TableColumn(nodeFieldtbl, SWT.NONE);
		tableColumn.setWidth(100);
		tableColumn.setText("中文名");

		TableColumn tableColumn_1 = new TableColumn(nodeFieldtbl, SWT.NONE);
		tableColumn_1.setWidth(109);
		tableColumn_1.setText("字段名");

		TableColumn tableColumn_2 = new TableColumn(nodeFieldtbl, SWT.NONE);
		tableColumn_2.setWidth(115);
		tableColumn_2.setText("别名");

		TableColumn tableColumn_3 = new TableColumn(nodeFieldtbl, SWT.NONE);
		tableColumn_3.setWidth(119);
		tableColumn_3.setText("数据类型");

		Label label_2 = new Label(composite, SWT.NONE);
		label_2.setAlignment(SWT.RIGHT);
		FormData fd_label_2 = new FormData();
		fd_label_2.left = new FormAttachment(0, 0);
		fd_label_2.top = new FormAttachment(composite_2, 10);
		label_2.setLayoutData(fd_label_2);
		label_2.setText("表达式：");

		expressTxt = new Text(composite, SWT.BORDER);
		expressTxt.setText(Field.getExpression());
		FormData fd_expressTxt = new FormData();
		fd_expressTxt.right = new FormAttachment(100, 0);
		fd_expressTxt.left = new FormAttachment(label_2, 0);
		fd_expressTxt.top = new FormAttachment(composite_2, 10);
		expressTxt.setLayoutData(fd_expressTxt);

		Button BtnOk = new Button(composite, SWT.NONE);
		BtnOk.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/tick.png"));
		BtnOk.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				result = expressTxt.getText().trim();
				close();
			}
		});
		FormData fd_BtnOk = new FormData();
		fd_BtnOk.width = 80;
		fd_BtnOk.top = new FormAttachment(expressTxt, 20);
		fd_BtnOk.right = new FormAttachment(50, -20);
		// fd_BtnOk.bottom = new FormAttachment(100, -10);
		BtnOk.setLayoutData(fd_BtnOk);
		BtnOk.setText("确定(&O)");

		Button BtnCancel = new Button(composite, SWT.NONE);
		BtnCancel.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/cross.png"));
		BtnCancel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				expressTxt.setText("");
				close();
			}
		});
		FormData fd_BtnCancel = new FormData();
		fd_BtnCancel.width = 80;
		fd_BtnCancel.left = new FormAttachment(50, 20);
		fd_BtnCancel.top = new FormAttachment(expressTxt, 20);
		BtnCancel.setLayoutData(fd_BtnCancel);
		BtnCancel.setText("取消(&C)");
	}

	public Point getParentLocation() {
		Shell shell = getParent();
		int x = shell.getLocation().x;
		int y = shell.getLocation().y;
		Rectangle rectParent = shell.getBounds();
		Point size = this.shell.getSize();
		int xx = x + rectParent.width / 2 - size.x / 2;
		int yy = y + rectParent.height / 2 - size.y / 2;
		return new Point(xx, yy);
	}

	public void close() {
		shell.dispose();
	}

	private void addListener(TableViewer tv) {
		tv.addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(DoubleClickEvent event) {
				// 得到表格的选择对象，里面封装了表格中被选择的记录信息
				IStructuredSelection selection = (IStructuredSelection) event.getSelection();
				// 得到所选择的第一条实体对象（表格可以有多选），并进行类型转换
				Metadata o = (Metadata) selection.getFirstElement();
				expressTxt.append("[" + nodeNameCombo.getText() + "." + o.getName() + "]");
			}
		});
	}

	public class OperationExpressLabelProvider extends LabelProvider implements ITableLabelProvider {
		public String getColumnText(Object obj, int columnIndex) {
			Metadata dsi = (Metadata) obj;
			switch (columnIndex) {
			case 0:
				return dsi.getCnName() == null ? "" : dsi.getCnName();
			case 1:
				return dsi.getName() == null ? "" : dsi.getName();
			case 2:
				return dsi.getName() == null ? "" : dsi.getName();
			case 3:
				return dsi.getDataType() == null ? "" : getLabel(Consts.DATATYPE_LABEL, Consts.DATATYPE, dsi.getDataType());
			}
			return null;
		}

		private String getLabel(String[] labels, String[] values, String name) {
			for (int i = 0; i < values.length; i++) {
				if (values[i].equalsIgnoreCase(name)) return labels[i];
			}
			return "";
		}

		public String getText(Object element) {
			return element == null ? "" : element.toString();
		}

		public Image getColumnImage(Object obj, int index) {
			return null;
		}
	}
}

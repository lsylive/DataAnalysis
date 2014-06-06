package com.liusy.analysismodel.template.ui;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import com.swtdesigner.ResourceManager;
import com.liusy.analysismodel.Activator;
import com.liusy.analysismodel.template.commands.NodePropertyEditCommand;
import com.liusy.analysis.template.model.Consts;
import com.liusy.analysis.template.model.Diagram;
import com.liusy.analysis.template.model.base.Metadata;
import com.liusy.analysis.template.model.dialogProperties.SumNodeProperties;
import com.liusy.analysis.template.model.node.INode;
import com.liusy.analysis.template.model.node.SumNode;
import com.liusy.analysis.template.model.vo.SumConfig;
import com.liusy.analysismodel.template.ui.contentProvider.BaseCellModifer;
import com.liusy.analysismodel.template.ui.contentProvider.ViewContentProvider;
import com.liusy.analysismodel.template.ui.dialog.HelpDialog;
import com.liusy.analysismodel.util.MessageUtil;

public class SumNodeDialog extends Dialog {

	private TableViewer					tvInputMeta;
	private Table							tblInputMeta;
	private StyledText					txtDes;
	private Text							txtName;
	private int								result	= 0;
	protected SumNodeProperties		nodeProperties;
	private SumNode						node;
	private Diagram						diagram;
	private List<SumConfig>				inputFieldList;
	private NodePropertyEditCommand	cmd;

	public SumNodeDialog(Shell parent, int style) {
		super(parent, style);
	}

	public SumNodeDialog(Shell parent, SumNode node, NodePropertyEditCommand command) {
		this(parent, SWT.NONE);
		this.node = node;
		this.diagram = node.getDiagram();
		this.cmd = command;
	}

	public int open() {
		try {
			nodeProperties = (SumNodeProperties) node.getProperties().clone();
		}
		catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}

		createContents();
		init();

		shell.setLocation(getParentLocation());
		shell.open();
		shell.layout();
		Display display = getParent().getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) display.sleep();
		}
		return result;
	}

	protected void createContents() {
		shell = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
		shell.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "src/com/thunisoft/dataplatform/template/image/png16/eq_add.png"));
		shell.setLayout(new FormLayout());
		shell.setSize(800, 500);
		shell.setText("统计节点属性");

		final Group basicGroup = new Group(shell, SWT.NONE);
		basicGroup.setLayout(new FormLayout());
		final FormData fd_basicGroup = new FormData();
		fd_basicGroup.height = 80;
		fd_basicGroup.top = new FormAttachment(0, 1);
		fd_basicGroup.right = new FormAttachment(100, -5);
		fd_basicGroup.left = new FormAttachment(0, 5);
		basicGroup.setLayoutData(fd_basicGroup);

		final Label label = new Label(basicGroup, SWT.RIGHT);
		final FormData fd_label = new FormData();
		fd_label.top = new FormAttachment(0, 0);
		fd_label.right = new FormAttachment(15, 0);
		fd_label.left = new FormAttachment(0, 0);
		label.setLayoutData(fd_label);
		label.setText("节点名称：");

		txtName = new Text(basicGroup, SWT.BORDER);
		final FormData fd_txtName = new FormData();
		fd_txtName.top = new FormAttachment(0, 0);
		fd_txtName.right = new FormAttachment(40, 0);
		fd_txtName.left = new FormAttachment(label, 0);
		txtName.setLayoutData(fd_txtName);
		txtName.setEditable(true);

		final Label lblDescription = new Label(basicGroup, SWT.RIGHT);
		final FormData fd_lblDescription = new FormData();
		fd_lblDescription.top = new FormAttachment(label, 10);
		fd_lblDescription.bottom = new FormAttachment(100, -2);
		fd_lblDescription.left = new FormAttachment(0, 0);
		fd_lblDescription.right = new FormAttachment(15, 0);
		lblDescription.setLayoutData(fd_lblDescription);
		lblDescription.setText("说明：");

		final Group group_4 = new Group(basicGroup, SWT.NONE);
		final FormData fd_group_4 = new FormData();
		fd_group_4.top = new FormAttachment(0, 27);
		fd_group_4.width = 150;
		fd_group_4.right = new FormAttachment(100, 0);
		fd_group_4.bottom = new FormAttachment(100, 0);
		group_4.setLayoutData(fd_group_4);
		group_4.setLayout(new FormLayout());

		txtDes = new StyledText(basicGroup, SWT.BORDER);
		final FormData fd_txtDes = new FormData();
		fd_txtDes.top = new FormAttachment(txtName, 5);
		fd_txtDes.left = new FormAttachment(lblDescription, 0);
		fd_txtDes.right = new FormAttachment(group_4, -2);
		fd_txtDes.bottom = new FormAttachment(100, 0);
		txtDes.setLayoutData(fd_txtDes);

		final Button btnOk = new Button(shell, SWT.NONE);
		btnOk.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/tick.png"));
		final FormData fd_btnOk = new FormData();
		fd_btnOk.height = 28;
		fd_btnOk.width = 80;
		fd_btnOk.bottom = new FormAttachment(100, -5);
		fd_btnOk.right = new FormAttachment(50, -60);
		btnOk.setLayoutData(fd_btnOk);
		btnOk.setText("确定(&O)");
		btnOk.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				save();
				result = 1;
				close();
			}
		});

		final Button btnCancel = new Button(shell, SWT.NONE);
		btnCancel.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/cross.png"));
		final FormData fd_btnCancel = new FormData();
		fd_btnCancel.height = 28;
		fd_btnCancel.width = 80;
		fd_btnCancel.bottom = new FormAttachment(100, -5);
		fd_btnCancel.left = new FormAttachment(50, -40);
		btnCancel.setLayoutData(fd_btnCancel);
		btnCancel.setText("取消(&C)");
		btnCancel.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				close();
			}
		});

		final Button btnHelp = new Button(shell, SWT.NONE);
		btnHelp.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/question-frame.png"));
		final FormData fd_btnHelp = new FormData();
		fd_btnHelp.height = 28;
		fd_btnHelp.width = 80;
		fd_btnHelp.bottom = new FormAttachment(100, -5);
		fd_btnHelp.left = new FormAttachment(50, 60);
		btnHelp.setLayoutData(fd_btnHelp);
		btnHelp.setText("帮助(&H)");
		btnHelp.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				HelpDialog dlg = new HelpDialog(shell, node.getClass().getSimpleName());
				dlg.open();
			}
		});

		final TabFolder tabFolder = new TabFolder(shell, SWT.NONE);
		final FormData fd_tabFolder = new FormData();
		fd_tabFolder.bottom = new FormAttachment(100, -40);
		fd_tabFolder.top = new FormAttachment(basicGroup, 3, SWT.BOTTOM);
		fd_tabFolder.right = new FormAttachment(100, -5);
		fd_tabFolder.left = new FormAttachment(0, 5);
		tabFolder.setLayoutData(fd_tabFolder);

		final TabItem tabInput = new TabItem(tabFolder, SWT.NONE);
		tabInput.setText("统计设置");

		final Composite composite_2 = new Composite(tabFolder, SWT.NONE);
		composite_2.setLayout(new FormLayout());
		tabInput.setControl(composite_2);

		final Group group_3 = new Group(composite_2, SWT.NONE);
		group_3.setLayout(new FormLayout());
		final FormData fd_group_3 = new FormData();
		fd_group_3.top = new FormAttachment(0, -6);
		fd_group_3.left = new FormAttachment(0, 0);
		fd_group_3.bottom = new FormAttachment(100, 0);
		fd_group_3.right = new FormAttachment(100, 0);
		group_3.setLayoutData(fd_group_3);

		tvInputMeta = new TableViewer(group_3, SWT.MULTI | SWT.FULL_SELECTION | SWT.BORDER);
		tvInputMeta.setContentProvider(new ViewContentProvider());
		tvInputMeta.setLabelProvider(new InputMetaLabelProvider());
		tvInputMeta.setColumnProperties(INPUTMETAS);

		tblInputMeta = tvInputMeta.getTable();
		CellEditor[] cellEditor0 = new CellEditor[5];
		cellEditor0[0] = new TextCellEditor(tblInputMeta);
		cellEditor0[1] = new TextCellEditor(tblInputMeta);
		cellEditor0[2] = new ComboBoxCellEditor(tblInputMeta, Consts.DATATYPE_LABEL, SWT.READ_ONLY);
		cellEditor0[3] = new ComboBoxCellEditor(tblInputMeta, Consts.YESNO_LABEL, SWT.READ_ONLY);
		cellEditor0[4] = new ComboBoxCellEditor(tblInputMeta, Consts.YESNO_LABEL, SWT.READ_ONLY);
		tvInputMeta.setCellEditors(cellEditor0);
		tvInputMeta.setCellModifier(new ExpressionCellEditor(tvInputMeta));

		final FormData fd_tblInputMeta = new FormData();
		fd_tblInputMeta.bottom = new FormAttachment(100, -21);
		fd_tblInputMeta.right = new FormAttachment(100, 0);
		fd_tblInputMeta.top = new FormAttachment(0, -6);
		fd_tblInputMeta.left = new FormAttachment(0, 0);
		tblInputMeta.setLayoutData(fd_tblInputMeta);
		tblInputMeta.setLinesVisible(true);
		tblInputMeta.setHeaderVisible(true);

		final TableColumn colCnName1 = new TableColumn(tblInputMeta, SWT.NONE);
		colCnName1.setWidth(150);
		colCnName1.setText("中文名");

		final TableColumn colFieldName1 = new TableColumn(tblInputMeta, SWT.NONE);
		colFieldName1.setWidth(150);
		colFieldName1.setText("字段名");

		final TableColumn colDataType1 = new TableColumn(tblInputMeta, SWT.NONE);
		colDataType1.setWidth(120);
		colDataType1.setText("数据类型");

		final TableColumn colSumCondition = new TableColumn(tblInputMeta, SWT.NONE);
		colSumCondition.setWidth(100);
		colSumCondition.setText("是否统计条件");

		final TableColumn colSumResult = new TableColumn(tblInputMeta, SWT.NONE);
		colSumResult.setWidth(120);
		colSumResult.setText("是否统计输出字段");

		final Button btnInputLoad = new Button(group_3, SWT.NONE);
		btnInputLoad.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				int res = MessageUtil.comfirm(shell, "导入", "是否从上一环节导入字段信息?\r\n注意：已有字段信息将被覆盖!");
				if (res == SWT.YES) {
					List<com.liusy.analysis.template.model.connection.Connection> list = node.getIncomingConnections();
					if (list == null || list.size() == 0) {
						MessageUtil.alert(shell, "提示", "找不到节点。");
						return;
					}
					else if (list.size() > 1) {
						MessageUtil.alert(shell, "提示", "上一环节的节点不能多于一个。");
						return;
					}
					INode node = list.get(0).getSource();
					List<Metadata> listMeta = node.getMeta();
					inputFieldList.clear();
					if (listMeta.size() == 0 || listMeta == null) {
						MessageUtil.alert(shell, "提示", "节点元数据匹配错误。");
					}
					else {
						for (Metadata md : listMeta) {
							SumConfig sc = new SumConfig();
							sc.setCnName(md.getCnName());
							sc.setName(md.getName());
							sc.setDataType(md.getDataType());
							sc.setSumCondition(Consts.YES);
							sc.setSumResult(Consts.YES);

							inputFieldList.add(sc);
						}
					}
					tvInputMeta.refresh();
				}
			}
		});
		btnInputLoad.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/table-import.png"));
		final FormData fd_btnInputLoad = new FormData();
		fd_btnInputLoad.bottom = new FormAttachment(100, 0);
		fd_btnInputLoad.left = new FormAttachment(0, 0);
		fd_btnInputLoad.height = 20;
		fd_btnInputLoad.width = 20;
		btnInputLoad.setLayoutData(fd_btnInputLoad);
	}

	private void init() {

		txtName.setText(node.getName());
		txtDes.setText(node.getDescription());
		// ***txtTableName.setText(nodeProperties.getTableName());
		// ***txtOtherCondition.setText(nodeProperties.getAdditionSql());

		inputFieldList = new ArrayList<SumConfig>();
		if (nodeProperties.getInputFieldList() != null && nodeProperties.getInputFieldList().size() > 0) {
			// nodeProperties.setFields(nodeProperties.getInputFieldList());
			for (SumConfig sc : nodeProperties.getInputFieldList()) {
				inputFieldList.add(sc);
			}
		}
		tvInputMeta.setInput(inputFieldList);
	}

	private void save() {
		node.setName(txtName.getText().trim());
		node.setDescription(txtDes.getText());
		// ***nodeProperties.setTableName(txtTableName.getText().trim());
		// ***nodeProperties.setAdditionSql(txtOtherCondition.getText());

		nodeProperties.setInputFieldList(inputFieldList);
		node.setProperties(nodeProperties);
	}

	protected Shell	shell;

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

	class InputMetaLabelProvider extends LabelProvider implements ITableLabelProvider {
		public String getColumnText(Object obj, int columnIndex) {
			SumConfig dsi = (SumConfig) obj;
			switch (columnIndex) {
			case 0:
				return dsi.getCnName() == null ? "" : dsi.getCnName();
			case 1:
				return dsi.getName() == null ? "" : dsi.getName();
			case 2:
				return dsi.getDataType() == null ? "" : getLabel(Consts.DATATYPE_LABEL, Consts.DATATYPE, dsi.getDataType());
			case 3:
				return dsi.getSumCondition() == null ? "" : getLabel(Consts.YESNO_LABEL, Consts.YESNO, dsi.getSumCondition());
			case 4:
				return dsi.getSumResult() == null ? "" : getLabel(Consts.YESNO_LABEL, Consts.YESNO, dsi.getSumResult());
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

	public static String[]	INPUTMETAS	= new String[] { "cnName", "name", "dataType", "sumCondition", "sumResult" };

	class ExpressionCellEditor extends BaseCellModifer implements ICellModifier {

		public ExpressionCellEditor(TableViewer tv) {
			this.tv = tv;
		}

		public Object getValue(Object element, String property) {
			SumConfig o = (SumConfig) element;
			if (property.equals("cnName")) return o.getCnName();
			else if (property.equals("name")) return o.getName();
			else if (property.equals("dataType")) return o.getDataType();
			else if (property.equals("sumCondition")) return getIndex(Consts.YESNO, o.getSumCondition());
			else if (property.equals("sumResult")) return getIndex(Consts.YESNO, o.getSumResult());
			else throw new RuntimeException("错误的列别名:" + property);
		}

		public void modify(Object element, String property, Object value) {
			TableItem item = (TableItem) element;
			SumConfig o = (SumConfig) item.getData();

			if (property.equals("cnName")) o.setCnName((String) value);
			else if (property.equals("name")) o.setName((String) value);
			else if (property.equals("dataType")) o.setDataType((String) value);
			else if (property.equals("sumCondition")) o.setSumCondition(Consts.YESNO[(Integer) value]);
			else if (property.equals("sumResult")) o.setSumResult(Consts.YESNO[(Integer) value]);
			else throw new RuntimeException("错误的列别名:" + property);
			tv.update(o, null);
		}
	}

}

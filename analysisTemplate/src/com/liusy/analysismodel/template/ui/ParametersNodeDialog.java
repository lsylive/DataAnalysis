package com.liusy.analysismodel.template.ui;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
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
import com.liusy.analysis.template.model.DiagramParameter;
import com.liusy.analysis.template.model.dialogProperties.ParametersNodeProperties;
import com.liusy.analysis.template.model.node.ParametersNode;
import com.liusy.analysismodel.template.ui.contentProvider.ViewContentProvider;

public class ParametersNodeDialog extends Dialog {
	private TableViewer						tvInputPara;
	private Table								tblInputPara;
	private StyledText						txtDes;
	private Text								txtName;
	private int									result				= 0;
	protected ParametersNodeProperties	nodeProperties;
	private ParametersNode					node;
	private Diagram							diagram;
	private NodePropertyEditCommand		cmd;
	private List<DiagramParameter>		initialparamList	= new ArrayList<DiagramParameter>();
	private List<DiagramParameter>		ConfigparamList	= new ArrayList<DiagramParameter>();
	String[]										multiParamCnName;
	String[]										multiParamName;

	// private FiltersCellModifier filtersCellModifier;

	public ParametersNodeDialog(Shell parent, int style) {
		super(parent, style);
	}

	public ParametersNodeDialog(Shell parent, ParametersNode node, NodePropertyEditCommand command) {

		this(parent, SWT.NONE);
		this.node = node;
		this.diagram = node.getDiagram();
		this.cmd = command;
	}

	public int open() {
		initialparamList = diagram.getParameterList();
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
		nodeProperties = node.getProperties();
		shell = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
		shell.setLayout(new FormLayout());
		shell.setSize(671, 418);
		shell.setText("参数表");
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

		txtDes = new StyledText(basicGroup, SWT.BORDER);
		final FormData fd_txtDes = new FormData();
		fd_txtDes.right = new FormAttachment(80, 0);
		fd_txtDes.left = new FormAttachment(lblDescription);
		fd_txtDes.top = new FormAttachment(txtName, 5);
		fd_txtDes.bottom = new FormAttachment(100, 0);
		txtDes.setLayoutData(fd_txtDes);

		final Button btnOk = new Button(shell, SWT.NONE);
		btnOk.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/tick.png"));
		final FormData fd_btnOk = new FormData();
		fd_btnOk.height = 28;
		fd_btnOk.width = 80;
		fd_btnOk.bottom = new FormAttachment(100, -5);
		fd_btnOk.right = new FormAttachment(50, -20);
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
		fd_btnCancel.left = new FormAttachment(50, +20);
		btnCancel.setLayoutData(fd_btnCancel);
		btnCancel.setText("取消(&C)");
		btnCancel.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				close();
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
		tabInput.setText("参数");

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

		tvInputPara = new TableViewer(group_3, SWT.FULL_SELECTION | SWT.BORDER);
		tvInputPara.setContentProvider(new ViewContentProvider());
		tvInputPara.setLabelProvider(new InputParamLabelProvider());
		tvInputPara.setColumnProperties(INPUTPARAMS);
		tblInputPara = tvInputPara.getTable();
		final FormData fd_tblInputPara = new FormData();
		fd_tblInputPara.bottom = new FormAttachment(100, -21);
		fd_tblInputPara.right = new FormAttachment(100, 0);
		fd_tblInputPara.top = new FormAttachment(0, -6);
		fd_tblInputPara.left = new FormAttachment(0, 0);
		tblInputPara.setLayoutData(fd_tblInputPara);
		tblInputPara.setLinesVisible(true);
		tblInputPara.setHeaderVisible(true);
		CellEditor[] cellEditor2 = new CellEditor[1];
		String[] paramCnNames = new String[] { "" };
		paramCnNames = getParamNames(diagram);
		this.multiParamCnName = paramCnNames;
		cellEditor2[0] = new ComboBoxCellEditor(tblInputPara, paramCnNames, SWT.READ_ONLY);
		tvInputPara.setCellEditors(cellEditor2);
		tvInputPara.setCellModifier(new InputsCellModifier(tvInputPara, paramCnNames));

		final TableColumn colCnName1 = new TableColumn(tblInputPara, SWT.NONE);
		colCnName1.setWidth(150);
		colCnName1.setText("中文名");

		final TableColumn colFieldName1 = new TableColumn(tblInputPara, SWT.NONE);
		colFieldName1.setWidth(150);
		colFieldName1.setText("字段名");

		final TableColumn colDataType1 = new TableColumn(tblInputPara, SWT.NONE);
		colDataType1.setWidth(150);
		colDataType1.setText("数据类型");

		final Button btnFieldAdd = new Button(group_3, SWT.NONE);
		btnFieldAdd.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				// DiagramParameter a=diagram.getParameterList().get(0);
				for (DiagramParameter dp : diagram.getParameterList()) {
					if (dp.getPolyLineFlag().equals("1")) {
						ConfigparamList.add((DiagramParameter) dp.clone());
						break;
					}
				}
				tvInputPara.refresh();
			}
		});
		btnFieldAdd.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/plus.png"));
		final FormData fd_btnFieldAdd = new FormData();
		fd_btnFieldAdd.bottom = new FormAttachment(100, 0);
		fd_btnFieldAdd.left = new FormAttachment(0, 0);
		fd_btnFieldAdd.height = 20;
		fd_btnFieldAdd.width = 20;
		btnFieldAdd.setLayoutData(fd_btnFieldAdd);

		final Button btnFieldDel = new Button(group_3, SWT.NONE);
		btnFieldDel.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				TableItem[] selectItems = tblInputPara.getSelection();
				if (selectItems != null && selectItems.length > 0) {
					for (TableItem ti : selectItems) {
						DiagramParameter o = (DiagramParameter) ti.getData();
						ConfigparamList.remove(o);
					}
					tvInputPara.refresh();
				}
			}
		});
		btnFieldDel.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/minus.png"));
		final FormData fd_btnFieldDel = new FormData();
		fd_btnFieldDel.bottom = new FormAttachment(100, 0);
		fd_btnFieldDel.left = new FormAttachment(btnFieldAdd, 1);
		fd_btnFieldDel.height = 20;
		fd_btnFieldDel.width = 20;
		btnFieldDel.setLayoutData(fd_btnFieldDel);
	}

	private void init() {

		txtName.setText(node.getName());
		txtDes.setText(node.getDescription());
		ConfigparamList = new ArrayList<DiagramParameter>();
		if (nodeProperties.getParamList() != null && nodeProperties.getParamList().size() > 0) {
			for (DiagramParameter dp : nodeProperties.getParamList()) {
				ConfigparamList.add((DiagramParameter) dp.clone());
			}
		}
		tvInputPara.setInput(ConfigparamList);

	}

	private void save() {
		node.setName(txtName.getText().trim());
		node.setDescription(txtDes.getText());
		nodeProperties.setParamList(ConfigparamList);
		nodeProperties.setMultiParamName(multiParamName);
		nodeProperties.setMultiParamCnName(multiParamCnName);
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

	public String[] getParamNames(Diagram diagram) {
		List<DiagramParameter> paramList = diagram.getParameterList();
		ArrayList<String> paramCnNames = new ArrayList<String>();
		ArrayList<String> multiParamName = new ArrayList<String>();
		for (DiagramParameter dp : paramList) {
			if (dp.getPolyLineFlag().equals("1")) {
				paramCnNames.add(dp.getCnName());
				multiParamName.add(dp.getName());

			}
		}
		this.multiParamName = (String[]) multiParamName.toArray(new String[0]);
		String[] params = (String[]) paramCnNames.toArray(new String[0]);
		return params;
	}

	public void close() {
		shell.dispose();
	}

	class InputParamLabelProvider extends LabelProvider implements ITableLabelProvider {
		public String getColumnText(Object obj, int columnIndex) {
			DiagramParameter dsi = (DiagramParameter) obj;
			switch (columnIndex) {
			case 0:
				return dsi.getCnName() == null ? "" : dsi.getCnName();
			case 1:
				return dsi.getName() == null ? "" : dsi.getName();
			case 2:
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

	public static String[]	INPUTPARAMS	= new String[] { "cnName", "name", "dataType" };

	class InputsCellModifier implements ICellModifier {
		private TableViewer	tv;
		private String[]		paramName;

		public InputsCellModifier(TableViewer tv, String[] paramList) {
			this.tv = tv;
			this.paramName = paramList;
		}

		public boolean canModify(Object element, String property) {
			return true;
		}

		public Object getValue(Object element, String property) {
			DiagramParameter dp = (DiagramParameter) element;
			if (property.equals("cnName")) return getIndex(paramName, dp.getCnName());
			throw new RuntimeException("错误的列别名:" + property);
		}

		private int getIndex(String[] labels, String name) {
			for (int i = 0; i < labels.length; i++) {
				if (labels[i].equalsIgnoreCase(name)) return i;
			}
			return 0;
		}

		public void modify(Object element, String property, Object value) {
			TableItem item = (TableItem) element;
			DiagramParameter o = (DiagramParameter) item.getData();
			if (property.equals("cnName")) {
				Integer itmp = (Integer) value;
				String cnName = (paramName[itmp]);
				for (DiagramParameter dp : initialparamList) {
					if (dp.getCnName().equals(cnName)) {

						o.setCnName(dp.getCnName());
						o.setName(dp.getName());
						o.setDataType(dp.getDataType());
						o.setCodeSet(dp.getCodeSet());
						o.setDescription(dp.getDescription());
						o.setEditFlg(dp.getEditFlg());
						o.setId(dp.getId());
						o.setLength(dp.getLength());
						o.setPolyLineFlag(dp.getPolyLineFlag());
						o.setPrecision(dp.getPrecision());

						break;
					}

				}

			}

			else {
				throw new RuntimeException("错误的列别名:" + property);
			}
			tv.update(o, null);
			tv.refresh();
		}
	}
}

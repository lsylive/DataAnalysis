package com.liusy.analysismodel.template.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.grouplayout.Activator;
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
import com.liusy.analysismodel.template.commands.NodePropertyEditCommand;
import com.liusy.analysis.template.model.Consts;
import com.liusy.analysis.template.model.Diagram;
import com.liusy.analysis.template.model.base.Metadata;
import com.liusy.analysis.template.model.connection.Connection;
import com.liusy.analysis.template.model.dialogProperties.SyncContrastNodeProperties;
import com.liusy.analysis.template.model.node.INode;
import com.liusy.analysis.template.model.node.SyncContrastNode;
import com.liusy.analysis.template.model.vo.SyncContrastMetaConfig;
import com.liusy.analysismodel.template.ui.contentProvider.BaseCellModifer;
import com.liusy.analysismodel.template.ui.contentProvider.BaseLabelProvider;
import com.liusy.analysismodel.template.ui.contentProvider.ViewContentProvider;
import com.liusy.analysismodel.template.ui.dialog.HelpDialog;
import com.liusy.analysismodel.util.MessageUtil;

public class SyncContrastNodeDialog extends Dialog {
	private TableViewer							tvCommonMeta;
	private Table									tblCommonMeta;
	private StyledText							txtDes;
	private Text									txtName;
	private Text									txtContrastP;
	private int										result	= 0;
	protected SyncContrastNodeProperties	nodeProperties;
	private SyncContrastNode					node;
	private Diagram								diagram;
	private List<SyncContrastMetaConfig>	synContrastMetaList;
	// private List<DataNodeProperties> synContrastDataBaseTableList;
	private NodePropertyEditCommand			cmd;

	public SyncContrastNodeDialog(Shell parent, int style) {
		super(parent, style);
	}

	public SyncContrastNodeDialog(Shell parent, SyncContrastNode node, NodePropertyEditCommand command) {
		this(parent, SWT.NONE);
		this.node = node;
		this.diagram = node.getDiagram();
		this.cmd = command;
	}

	public int open() {

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
		nodeProperties = (SyncContrastNodeProperties) node.getProperties();
		shell = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
		shell.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "bin/com/thunisoft/dataplatform/template/image/png16/eq_add.png"));
		shell.setLayout(new FormLayout());
		shell.setSize(557, 394);
		shell.setText("同步对比属性配置");

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
		
		final Label ContrastPlabel = new Label(basicGroup, SWT.RIGHT);
		final FormData cp_label = new FormData();
		cp_label.top = new FormAttachment(0, 0);
		cp_label.right = new FormAttachment(50, 20);
		cp_label.left = new FormAttachment(txtName, 10);
		ContrastPlabel.setLayoutData(cp_label);
		ContrastPlabel.setText("对比参数：");

		txtContrastP = new Text(basicGroup, SWT.BORDER);
		final FormData fd_txtContrastP = new FormData();
		fd_txtContrastP.top = new FormAttachment(0, 0);
		fd_txtContrastP.right = new FormAttachment(100, -1);
		fd_txtContrastP.left = new FormAttachment(ContrastPlabel, 0);
		txtContrastP.setLayoutData(fd_txtContrastP);
		txtContrastP.setEditable(true);

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
		fd_txtDes.right = new FormAttachment(100, -1);
		fd_txtDes.left = new FormAttachment(lblDescription,-1);
		fd_txtDes.top = new FormAttachment(txtName, 5);
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
		tabInput.setText("共有字段列表");

		final Composite composite_2 = new Composite(tabFolder, SWT.NONE);
		composite_2.setLayout(new FormLayout());
		tabInput.setControl(composite_2);

		final Group group_2 = new Group(composite_2, SWT.NONE);
		group_2.setLayout(new FormLayout());
		final FormData fd_group_2 = new FormData();
		fd_group_2.top = new FormAttachment(0, -6);
		fd_group_2.left = new FormAttachment(0, 0);
		fd_group_2.bottom = new FormAttachment(100, 0);
		fd_group_2.right = new FormAttachment(100, 0);
		group_2.setLayoutData(fd_group_2);

		tvCommonMeta = new TableViewer(group_2, SWT.MULTI | SWT.FULL_SELECTION | SWT.BORDER);
		tvCommonMeta.setContentProvider(new ViewContentProvider());
		tvCommonMeta.setLabelProvider(new InputCommonMetaLabelProvider());
		tvCommonMeta.setColumnProperties(INPUTMETAS);

		tblCommonMeta = tvCommonMeta.getTable();
		CellEditor[] cellEditor = new CellEditor[4];
		cellEditor[0] = null;
		cellEditor[1] = null;
		cellEditor[2] = null;
		cellEditor[3] = new ComboBoxCellEditor(tblCommonMeta, Consts.YESNO_LABEL, SWT.READ_ONLY);
		tvCommonMeta.setCellEditors(cellEditor);
		tvCommonMeta.setCellModifier(new ContrastMetaCellEditor(tvCommonMeta));

		final FormData fd_tblCommonMeta = new FormData();
		fd_tblCommonMeta.bottom = new FormAttachment(100, -21);
		fd_tblCommonMeta.right = new FormAttachment(100, 0);
		fd_tblCommonMeta.top = new FormAttachment(0, -6);
		fd_tblCommonMeta.left = new FormAttachment(0, 0);
		tblCommonMeta.setLayoutData(fd_tblCommonMeta);
		tblCommonMeta.setLinesVisible(true);
		tblCommonMeta.setHeaderVisible(true);

		final TableColumn colCnName = new TableColumn(tblCommonMeta, SWT.NONE);
		colCnName.setWidth(150);
		colCnName.setText("中文名");

		final TableColumn colFieldName = new TableColumn(tblCommonMeta, SWT.NONE);
		colFieldName.setWidth(150);
		colFieldName.setText("字段名");

		final TableColumn colDataType = new TableColumn(tblCommonMeta, SWT.NONE);
		colDataType.setWidth(120);
		colDataType.setText("数据类型");

		final TableColumn colSyncContrastCondition = new TableColumn(tblCommonMeta, SWT.NONE);
		colSyncContrastCondition.setWidth(100);
		colSyncContrastCondition.setText("是否加入对比");

		final Button btnInputLoad = new Button(group_2, SWT.NONE);
		btnInputLoad.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/table-import.png"));
		btnInputLoad.setImage(ResourceManager.getPluginImage("DataAdminPlatform", "icons/table-import.png"));
		btnInputLoad.setSelection(true);
		final FormData fd_btnInputLoad = new FormData();
		fd_btnInputLoad.bottom = new FormAttachment(100, 0);
		fd_btnInputLoad.left = new FormAttachment(0, 0);
		fd_btnInputLoad.height = 20;
		fd_btnInputLoad.width = 20;
		btnInputLoad.setLayoutData(fd_btnInputLoad);
		btnInputLoad.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				int res = MessageUtil.comfirm(shell, "导入", "是否从上一环节导入字段信息?\r\n注意：已有字段信息将被覆盖!");
				if (res == SWT.YES) {
					List<Connection> list = node.getIncomingConnections();
					if (list == null || list.size() == 0) {
						MessageUtil.alert(shell, "提示", "找不到节点。");
						return;
					}
					// 得到所有上层判断结点的MetaList,保存在Properties中
					Map<Integer, List<Metadata>> metaMap = new HashMap<Integer, List<Metadata>>();
					for (Connection con : list) {
						INode node = con.getSource();
						int nodeId = node.getId();// 判断结点的ID
						List<Metadata> listMeta = node.getMeta();
						metaMap.put(nodeId, listMeta);
					}
					nodeProperties.setMetaMap(metaMap);
					// 得到共有字段并封装成synContrastMetaList
					synContrastMetaList = nodeProperties.FecthSynContrastMetaList();
					//nodeProperties.setSynContrastMetaList(synContrastMetaList);
					tvCommonMeta.setInput(synContrastMetaList);
					tvCommonMeta.refresh();
				}
			}
		});
	}

	private void init() {

		txtName.setText(node.getName());
		txtDes.setText(node.getDescription());
		if(nodeProperties.getContrastField()!=null){
			txtContrastP.setText(nodeProperties.getContrastField());
		}
		synContrastMetaList = new ArrayList<SyncContrastMetaConfig>();
		if (nodeProperties.getSynContrastMetaList() != null && nodeProperties.getSynContrastMetaList().size() > 0) {
			// nodeProperties.setFields(nodeProperties.getInputFieldList());
			for (SyncContrastMetaConfig sc : nodeProperties.getSynContrastMetaList()) {
				synContrastMetaList.add(sc.clone());
			}
		}
		tvCommonMeta.setInput(synContrastMetaList);
	}

	private void save() {
		node.setName(txtName.getText().trim());
		node.setDescription(txtDes.getText());
		nodeProperties.setSynContrastMetaList(synContrastMetaList);
		nodeProperties.setContrastField(txtContrastP.getText().toString().trim());
		nodeProperties.setDiagram(diagram);
		// nodeProperties.setSynContrastDataBaseTableList(synContrastDataBaseTableList);
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

	class InputCommonMetaLabelProvider extends BaseLabelProvider implements ITableLabelProvider {
		public String getColumnText(Object obj, int columnIndex) {
			SyncContrastMetaConfig dsi = (SyncContrastMetaConfig) obj;
			switch (columnIndex) {
			case 0:
				return dsi.getCnName() == null ? "" : dsi.getCnName();
			case 1:
				return dsi.getName() == null ? "" : dsi.getName();
			case 2:
				return dsi.getDataType() == null ? "" : getLabel(Consts.DATATYPE_LABEL, Consts.DATATYPE, dsi.getDataType());
			case 3:
				return dsi.getIsContrastMeta() == null ? "" : getLabel(Consts.YESNO_LABEL, Consts.YESNO, dsi.getIsContrastMeta());
			}
			return null;
		}
	}

	public static String[]	INPUTMETAS	= new String[] { "cnName", "name", "dataType", "isContrastMeta" };

	class ContrastMetaCellEditor extends BaseCellModifer implements ICellModifier {

		public ContrastMetaCellEditor(TableViewer tv) {
			this.tv = tv;
		}

		public Object getValue(Object element, String property) {
			SyncContrastMetaConfig o = (SyncContrastMetaConfig) element;
			if (property.equals("cnName")) return o.getCnName();
			else if (property.equals("name")) return o.getName();
			else if (property.equals("dataType")) return o.getDataType();
			else if (property.equals("isContrastMeta")) return getIndex(Consts.YESNO, o.getIsContrastMeta());
			else throw new RuntimeException("错误的列别名:" + property);
		}

		public void modify(Object element, String property, Object value) {
			TableItem item = (TableItem) element;
			SyncContrastMetaConfig o = (SyncContrastMetaConfig) item.getData();

			if (property.equals("isContrastMeta")) o.setIsContrastMeta(Consts.YESNO[(Integer) value]);
			else throw new RuntimeException("错误的列别名:" + property);
			tv.update(o, null);
		}
	}

}

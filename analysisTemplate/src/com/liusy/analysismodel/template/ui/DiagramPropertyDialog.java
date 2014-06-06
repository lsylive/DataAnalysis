package com.liusy.analysismodel.template.ui;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.Plugin;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
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
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbenchPage;

import com.swtdesigner.ResourceManager;
import com.liusy.analysismodel.Activator;
import com.liusy.analysismodel.template.NavigatorEntityElement;
import com.liusy.analysismodel.template.TemplateView;
import com.liusy.analysismodel.template.commands.NodePropertyEditCommand;
import com.liusy.analysis.template.model.Consts;
import com.liusy.analysis.template.model.Diagram;
import com.liusy.analysis.template.model.DiagramParameter;
import com.liusy.analysis.template.model.vo.CodeSet;
import com.liusy.analysismodel.template.ui.contentProvider.BaseCellModifer;
import com.liusy.analysismodel.template.ui.contentProvider.BaseLabelProvider;
import com.liusy.analysismodel.template.ui.contentProvider.ViewContentProvider;
import com.liusy.analysismodel.template.ui.dialog.CodeSetDialog;
import com.liusy.analysismodel.template.ui.dialog.CreateCodeSetDialog;
import com.liusy.analysismodel.util.DbConnectionManager;
import com.liusy.analysismodel.util.MessageUtil;

public class DiagramPropertyDialog extends Dialog {

	private Table									tblCodesets;
	private Combo									cboType;
	private Button									btnVisual;
	private Text									txtDescription;
	private Text									txtName;
	private Table									tblParameters;
	protected int									result;
	protected Shell								shell;
	private TableViewer							tvParameters;
	private TableViewer							tvCodesets;
	private List<DiagramParameter>			paramList;
	private List<CodeSet>						codeSetList;
	private Diagram								diagram;
	private String[]								codeSets;
	private String[]								codeSetLabels;
	private DiagramParameterLabelProvider	diagParamLabelProvider;
	private DiagramParameterCellModifier	diagParamCellModifier;
	public String[]								DATAFIELDS	= new String[] { "name", "cnName", "dataType", "length", "precision", "polyLineFlag", "codeSet", "mutliSelect" };
	private NodePropertyEditCommand			cmd;

	/**
	 * Create the dialog
	 * 
	 * @param parent
	 * @param style
	 */
	public DiagramPropertyDialog(Shell parent, int style) {
		super(parent, style);
	}

	/**
	 * Create the dialog
	 * 
	 * @param parent
	 */
	public DiagramPropertyDialog(Shell parent) {
		this(parent, SWT.NONE);
	}

	/**
	 * Create the dialog
	 * 
	 * @param parent
	 */
	public DiagramPropertyDialog(Shell parent, Diagram diagram, NodePropertyEditCommand cmd) {
		this(parent, SWT.NONE);
		this.cmd = cmd;
		this.diagram = diagram;

	}

	/**
	 * Open the dialog
	 * 
	 * @return the result
	 */
	public int open() {

		if (this.diagram.getCodeSetList() == null) {
			codeSetList = new ArrayList<CodeSet>();
			codeSetLabels = new String[] { "" };
			codeSets = new String[] { "" };
		}
		else {
			int size = this.diagram.getCodeSetList().size();
			codeSets = new String[size + 1];
			codeSetLabels = new String[size + 1];
			codeSets[0] = "";
			codeSetLabels[0] = "";
			codeSetList = new ArrayList<CodeSet>(size);
			for (int i = 0; i < size; i++) {
				CodeSet desBean = (CodeSet) this.diagram.getCodeSetList().get(i);
				codeSets[i + 1] = desBean.getName();
				codeSetLabels[i + 1] = desBean.getCnName();

				CodeSet tempBean = (CodeSet) desBean.clone();
				codeSetList.add(tempBean);
			}
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

	/**
	 * Create contents of the dialog
	 */
	protected void createContents() {
		shell = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
		shell.setLayout(new FormLayout());
		shell.setSize(881, 543);
		shell.setText("模板属性");

		final Group baseGroup = new Group(shell, SWT.NONE);
		baseGroup.setLayout(new FormLayout());
		final FormData fd_baseGroup = new FormData();
		fd_baseGroup.height = 80;
		fd_baseGroup.top = new FormAttachment(0, 1);
		fd_baseGroup.right = new FormAttachment(100, -5);
		fd_baseGroup.left = new FormAttachment(0, 5);
		baseGroup.setLayoutData(fd_baseGroup);

		final Label label_5 = new Label(baseGroup, SWT.RIGHT);
		final FormData fd_label_5 = new FormData();
		fd_label_5.top = new FormAttachment(0, 0);
		fd_label_5.right = new FormAttachment(15, 0);
		fd_label_5.left = new FormAttachment(0, 0);
		label_5.setLayoutData(fd_label_5);
		label_5.setText("模板名称：");

		txtName = new Text(baseGroup, SWT.BORDER);
		final FormData fd_txtName = new FormData();
		fd_txtName.top = new FormAttachment(0, 0);
		fd_txtName.right = new FormAttachment(55, 0);
		fd_txtName.left = new FormAttachment(label_5, 5);
		txtName.setLayoutData(fd_txtName);

		final Label label_6 = new Label(baseGroup, SWT.RIGHT);
		final FormData fd_label_6 = new FormData();
		fd_label_6.top = new FormAttachment(0, 0);
		fd_label_6.right = new FormAttachment(65, 0);
		fd_label_6.left = new FormAttachment(txtName, 0);
		label_6.setLayoutData(fd_label_6);
		label_6.setText("类型：");

		cboType = new Combo(baseGroup, SWT.NONE);
		final FormData fd_cboType = new FormData();
		fd_cboType.top = new FormAttachment(0, 0);
		fd_cboType.left = new FormAttachment(label_6, 0);
		fd_cboType.right = new FormAttachment(90, 0);
		cboType.setLayoutData(fd_cboType);

		final Label label_7 = new Label(baseGroup, SWT.RIGHT);
		final FormData fd_label_7 = new FormData();
		fd_label_7.top = new FormAttachment(txtName, 5);
		fd_label_7.right = new FormAttachment(15, 0);
		fd_label_7.left = new FormAttachment(0, 0);
		label_7.setLayoutData(fd_label_7);
		label_7.setText("描述：");

		txtDescription = new Text(baseGroup, SWT.BORDER);
		final FormData fd_txtDes = new FormData();
		fd_txtDes.top = new FormAttachment(txtName, 5);
		fd_txtDes.bottom = new FormAttachment(100, -5);
		fd_txtDes.right = new FormAttachment(100, 0);
		fd_txtDes.left = new FormAttachment(label_7, 5);
		txtDescription.setLayoutData(fd_txtDes);

		final TabFolder tabFolder = new TabFolder(shell, SWT.NONE);
		tabFolder.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				TabItem ti = (TabItem) e.item;
				if (ti.getText().startsWith("参数")) {
					if (diagParamLabelProvider != null) {
						int size = codeSetList.size();
						codeSets = new String[size + 1];
						codeSets[0] = "";
						codeSetLabels = new String[size + 1];
						codeSetLabels[0] = "";
						for (int i = 0; i < size; i++) {
							codeSets[i + 1] = codeSetList.get(i).getName();
							codeSetLabels[i + 1] = codeSetList.get(i).getCnName();
						}
						tvParameters.getCellEditors()[6] = new ComboBoxCellEditor(tvParameters.getTable(), codeSetLabels, SWT.READ_ONLY);
						((DiagramParameterLabelProvider) tvParameters.getLabelProvider()).setCodeSetLabels(codeSetLabels);
						((DiagramParameterLabelProvider) tvParameters.getLabelProvider()).setCodeSets(codeSets);
						((DiagramParameterCellModifier) tvParameters.getCellModifier()).setCodeSets(codeSets);
						tvParameters.refresh();
					}
				}
			}
		});
		final FormData fd_tabFolder = new FormData();
		fd_tabFolder.right = new FormAttachment(100, -5);
		fd_tabFolder.top = new FormAttachment(baseGroup, 5);
		fd_tabFolder.left = new FormAttachment(0, 5);
		fd_tabFolder.bottom = new FormAttachment(100, -40);
		tabFolder.setLayoutData(fd_tabFolder);

		final TabItem tabParameters = new TabItem(tabFolder, SWT.NONE);
		tabParameters.setText("参数");

		final Composite composite = new Composite(tabFolder, SWT.NONE);
		composite.setLayout(new FormLayout());
		tabParameters.setControl(composite);

		final TabItem tabCodesets = new TabItem(tabFolder, SWT.NONE);
		tabCodesets.setText("代码集");

		final Composite composite_1 = new Composite(tabFolder, SWT.NONE);
		composite_1.setLayout(new FormLayout());
		tabCodesets.setControl(composite_1);

		tvCodesets = new TableViewer(composite_1, SWT.FULL_SELECTION | SWT.MULTI | SWT.BORDER);
		tblCodesets = tvCodesets.getTable();
		final FormData fd_table_1 = new FormData();
		fd_table_1.bottom = new FormAttachment(100, -21);
		fd_table_1.top = new FormAttachment(0, 1);
		fd_table_1.right = new FormAttachment(100, -1);
		fd_table_1.left = new FormAttachment(0, 1);
		tblCodesets.setLayoutData(fd_table_1);
		tblCodesets.setLinesVisible(true);
		tblCodesets.setHeaderVisible(true);
		tvCodesets.setContentProvider(new ViewContentProvider());
		tvCodesets.setLabelProvider(new CodesetLabelProvider());

		final TableColumn newColumnTableColumn_6 = new TableColumn(tblCodesets, SWT.NONE);
		newColumnTableColumn_6.setWidth(150);
		newColumnTableColumn_6.setText("代码名称");

		final TableColumn newColumnTableColumn_8 = new TableColumn(tblCodesets, SWT.NONE);
		newColumnTableColumn_8.setWidth(150);
		newColumnTableColumn_8.setText("代码编号");

		final TableColumn newColumnTableColumn_7 = new TableColumn(tblCodesets, SWT.NONE);
		newColumnTableColumn_7.setWidth(100);
		newColumnTableColumn_7.setText("代码分类");

		final TableColumn newColumnTableColumn_9 = new TableColumn(tblCodesets, SWT.NONE);
		newColumnTableColumn_9.setWidth(180);
		newColumnTableColumn_9.setText("说明");

		final Button btnCodeSetAdd = new Button(composite_1, SWT.NONE);
		btnCodeSetAdd.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/plus.png"));
		final FormData fd_btnCodeSetAdd = new FormData();
		fd_btnCodeSetAdd.bottom = new FormAttachment(100, -1);
		fd_btnCodeSetAdd.top = new FormAttachment(100, -21);
		fd_btnCodeSetAdd.width = 20;
		fd_btnCodeSetAdd.left = new FormAttachment(0, 1);
		btnCodeSetAdd.setLayoutData(fd_btnCodeSetAdd);
		btnCodeSetAdd.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				CodeSetDialog dataSoruceDlg = new CodeSetDialog(shell);
				List<CodeSet> css = dataSoruceDlg.open();
				if (css != null) {
					List<CodeSet> tmp = new ArrayList<CodeSet>();
					for (CodeSet cs : css) {
						for (CodeSet c : codeSetList) {
							if (c.getName().equals(cs.getName())) tmp.add(c);
						}
					}
					if (tmp.size() > 0) codeSetList.removeAll(tmp);
					codeSetList.addAll(css);
					tvCodesets.refresh();
				}
			}
		});

		final Button btnCodeSetCreate = new Button(composite_1, SWT.NONE);
		btnCodeSetCreate.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/plus3.png"));
		final FormData fd_btnCodeSetCreate = new FormData();
		fd_btnCodeSetCreate.bottom = new FormAttachment(100, -1);
		fd_btnCodeSetCreate.top = new FormAttachment(100, -21);
		fd_btnCodeSetCreate.width = 20;
		fd_btnCodeSetCreate.left = new FormAttachment(btnCodeSetAdd, 1);
		btnCodeSetCreate.setLayoutData(fd_btnCodeSetCreate);
		btnCodeSetCreate.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				CodeSet cs = new CodeSet();
				CreateCodeSetDialog codeSetDlg = new CreateCodeSetDialog(shell, cs);
				CodeSet res = codeSetDlg.open();
				if (res != null) {
					codeSetList.add(cs);
					tvCodesets.refresh();
				}
			}
		});

		final Button btnCodeSetModify = new Button(composite_1, SWT.NONE);
		btnCodeSetModify.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/pencil.png"));
		final FormData fd_btnCodeSetModify = new FormData();
		fd_btnCodeSetModify.bottom = new FormAttachment(100, -1);
		fd_btnCodeSetModify.top = new FormAttachment(100, -21);
		fd_btnCodeSetModify.width = 20;
		fd_btnCodeSetModify.left = new FormAttachment(btnCodeSetCreate, 1);
		btnCodeSetModify.setLayoutData(fd_btnCodeSetModify);
		btnCodeSetModify.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				TableItem[] selectItems = tblCodesets.getSelection();
				if (selectItems != null && selectItems.length == 1) {
					TableItem ti = selectItems[0];
					CodeSet o = (CodeSet) ti.getData();
					CodeSet cs = (CodeSet) o.clone();

					CreateCodeSetDialog codeSetDlg = new CreateCodeSetDialog(shell, cs);
					CodeSet res = codeSetDlg.open();
					if (res != null) {
						codeSetList.remove(o);
						codeSetList.add(cs);
						tvCodesets.refresh();

					}

				}
				else MessageUtil.alert(shell, "提示", "请选择一个代码集。");
			}
		});

		final Button btnCodesetDel = new Button(composite_1, SWT.NONE);
		btnCodesetDel.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/minus.png"));
		final FormData fd_btnCodesetDel = new FormData();
		fd_btnCodesetDel.bottom = new FormAttachment(100, -1);
		fd_btnCodesetDel.top = new FormAttachment(100, -21);
		fd_btnCodesetDel.width = 20;
		fd_btnCodesetDel.left = new FormAttachment(btnCodeSetModify, 1);
		btnCodesetDel.setLayoutData(fd_btnCodesetDel);
		btnCodesetDel.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				TableItem[] selectItems = tblCodesets.getSelection();
				if (selectItems != null && selectItems.length > 0) {
					for (TableItem ti : selectItems) {
						CodeSet o = (CodeSet) ti.getData();
						codeSetList.remove(o);
					}
					tvCodesets.refresh();
				}
			}
		});

		Button btnParaDel;

		tvParameters = new TableViewer(composite, SWT.FULL_SELECTION | SWT.BORDER);

		tblParameters = tvParameters.getTable();
		final FormData fd_table = new FormData();
		fd_table.bottom = new FormAttachment(100, -21);
		fd_table.top = new FormAttachment(0, 0);
		fd_table.right = new FormAttachment(100, -1);
		fd_table.left = new FormAttachment(0, 1);
		tblParameters.setLayoutData(fd_table);
		tblParameters.setLinesVisible(true);
		tblParameters.setHeaderVisible(true);

		final TableColumn newColumnTableColumn = new TableColumn(tblParameters, SWT.NONE);
		newColumnTableColumn.setWidth(100);
		newColumnTableColumn.setText("参数名称");

		final TableColumn newColumnTableColumn_4 = new TableColumn(tblParameters, SWT.NONE);
		newColumnTableColumn_4.setWidth(120);
		newColumnTableColumn_4.setText("中文名称");

		final TableColumn newColumnTableColumn_1 = new TableColumn(tblParameters, SWT.NONE);
		newColumnTableColumn_1.setWidth(80);
		newColumnTableColumn_1.setText("数据类型");

		final TableColumn newColumnTableColumn_2 = new TableColumn(tblParameters, SWT.NONE);
		newColumnTableColumn_2.setWidth(50);
		newColumnTableColumn_2.setText("长度");

		final TableColumn newColumnTableColumn_5 = new TableColumn(tblParameters, SWT.NONE);
		newColumnTableColumn_5.setWidth(50);
		newColumnTableColumn_5.setText("精度");

		final TableColumn newColumnTableColumn_3 = new TableColumn(tblParameters, SWT.NONE);
		newColumnTableColumn_3.setWidth(80);
		newColumnTableColumn_3.setText("支持多行");

		final TableColumn newColumnTableColumn_10 = new TableColumn(tblParameters, SWT.NONE);
		newColumnTableColumn_10.setWidth(120);
		newColumnTableColumn_10.setText("代码集");

		final TableColumn newColumnTableColumn_11 = new TableColumn(tblParameters, SWT.NONE);
		newColumnTableColumn_11.setWidth(80);
		newColumnTableColumn_11.setText("支持多选");

		tvParameters.setContentProvider(new ViewContentProvider());
		diagParamLabelProvider = new DiagramParameterLabelProvider(codeSets, codeSetLabels);
		tvParameters.setLabelProvider(diagParamLabelProvider);

		tvParameters.setColumnProperties(DATAFIELDS);
		CellEditor[] paramCellEditor = new CellEditor[8];
		paramCellEditor[0] = new TextCellEditor(tvParameters.getTable());
		paramCellEditor[1] = new TextCellEditor(tvParameters.getTable());
		paramCellEditor[2] = new ComboBoxCellEditor(tvParameters.getTable(), Consts.DATATYPE_LABEL, SWT.READ_ONLY);
		paramCellEditor[3] = new TextCellEditor(tvParameters.getTable());
		paramCellEditor[4] = new TextCellEditor(tvParameters.getTable());
		paramCellEditor[5] = new ComboBoxCellEditor(tvParameters.getTable(), Consts.YESNO_LABEL, SWT.READ_ONLY);
		paramCellEditor[6] = new ComboBoxCellEditor(tvParameters.getTable(), codeSetLabels, SWT.READ_ONLY);
		paramCellEditor[7] = new ComboBoxCellEditor(tvParameters.getTable(), Consts.YESNO_LABEL, SWT.READ_ONLY);
		tvParameters.setCellEditors(paramCellEditor);
		diagParamCellModifier = new DiagramParameterCellModifier(tvParameters, codeSets);
		tvParameters.setCellModifier(diagParamCellModifier);
		tvParameters.setInput(paramList);

		final Button btnParaAdd = new Button(composite, SWT.NONE);
		btnParaAdd.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				DiagramParameter newBean = new DiagramParameter();
				paramList.add(newBean);
				tvParameters.refresh();
			}
		});
		btnParaAdd.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/plus.png"));
		final FormData fd_btnParaAdd = new FormData();
		fd_btnParaAdd.bottom = new FormAttachment(100, -1);
		fd_btnParaAdd.top = new FormAttachment(100, -21);
		fd_btnParaAdd.width = 20;
		fd_btnParaAdd.left = new FormAttachment(0, 1);
		fd_btnParaAdd.width = 20;
		btnParaAdd.setLayoutData(fd_btnParaAdd);
		btnParaDel = new Button(composite, SWT.NONE);
		btnParaDel.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				TableItem[] selectItems = tblParameters.getSelection();
				if (selectItems != null && selectItems.length > 0) {
					for (TableItem ti : selectItems) {
						DiagramParameter o = (DiagramParameter) ti.getData();
						paramList.remove(o);
					}
					tvParameters.refresh();
				}
			}
		});
		btnParaDel.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/minus.png"));
		final FormData fd_btnParaDel = new FormData();
		fd_btnParaDel.bottom = new FormAttachment(100, -1);
		fd_btnParaDel.top = new FormAttachment(100, -21);
		fd_btnParaDel.width = 20;
		fd_btnParaDel.left = new FormAttachment(btnParaAdd, 1);
		fd_btnParaDel.width = 20;
		btnParaDel.setLayoutData(fd_btnParaDel);

		final Button btnOk = new Button(shell, SWT.NONE);
		btnOk.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				ok();
			}
		});

		btnOk.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/tick.png"));
		final FormData fd_btnOk = new FormData();
		fd_btnOk.height = 25;
		fd_btnOk.width = 80;
		fd_btnOk.bottom = new FormAttachment(100, -5);
		fd_btnOk.right = new FormAttachment(50, -10);
		btnOk.setLayoutData(fd_btnOk);
		btnOk.setText("确定(&O)");

		final Button btnCancel = new Button(shell, SWT.NONE);
		btnCancel.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				close();
			}
		});

		btnCancel.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/cross.png"));
		final FormData fd_btnCancel = new FormData();
		fd_btnCancel.height = 25;
		fd_btnCancel.width = 80;
		fd_btnCancel.bottom = new FormAttachment(100, -5);
		fd_btnCancel.left = new FormAttachment(50, 10);
		btnCancel.setLayoutData(fd_btnCancel);
		btnCancel.setText("取消(&C)");

		cboType.setItems(Consts.TEMP_LABEL);
		cboType.setVisibleItemCount(10);

		btnVisual = new Button(baseGroup, SWT.CHECK);
		final FormData fd_btnVisual = new FormData();
		fd_btnVisual.height = 20;
		fd_btnVisual.top = new FormAttachment(cboType, 0, SWT.TOP);
		fd_btnVisual.left = new FormAttachment(90, 25);
		fd_btnVisual.right = new FormAttachment(100, -1);
		btnVisual.setLayoutData(fd_btnVisual);
		btnVisual.setText("隐藏");

	}

	private void ok() {
		String newDiagramName = txtName.getText().trim();
		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		List nameList = new ArrayList<String>();
		int oldDiagramType = 0;
		con = DbConnectionManager.getConnection();
		if (con != null) {
			try {
				pstmt = con.prepareStatement("select type from t_analysis_diagram where id=?");
				pstmt.setInt(1, diagram.getId());
				rs = pstmt.executeQuery();
				while (rs.next()) {
					oldDiagramType = rs.getInt(1);
				}
				rs.close();
				pstmt.close();
				pstmt1 = con.prepareStatement("select name from t_analysis_diagram where type=? and id!=?");
				pstmt1.setInt(1, oldDiagramType);
				pstmt1.setInt(2, diagram.getId());
				rs1 = pstmt1.executeQuery();
				while (rs1.next()) {
					nameList.add(rs1.getString(1));
				}
				rs1.close();
				pstmt1.close();
			}
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (nameList.contains(newDiagramName)) {
				MessageBox messageBox= new MessageBox(shell,SWT.OK);
				messageBox.setText("模板名称");
				messageBox.setMessage("模板名字重复！请重新设置模板名字。");
				int result = messageBox.open();
				txtName.setFocus();
				return;
			}
		}

		diagram.setName(txtName.getText().trim());
		diagram.setDescription(txtDescription.getText().trim());
		diagram.setType(cboType.getSelectionIndex());
		diagram.setParameterList(paramList);
		diagram.setCodeSetList(codeSetList);
		diagram.setVisiable(btnVisual.getSelection() == true ? Consts.NO : Consts.YES);
		
		close();
	}

	private void close() {
		shell.dispose();
	}

	public void init() {

		txtName.setText(diagram.getName() == null ? "" : diagram.getName());
		btnVisual.setSelection(Consts.NO.equals(diagram.getVisiable()) ? true : false);
		txtDescription.setText(diagram.getDescription() == null ? "" : diagram.getDescription());
		cboType.select(diagram.getType());

		if (this.diagram.getParameterList() == null) {
			paramList = new ArrayList<DiagramParameter>();
		}
		else {
			int size = this.diagram.getParameterList().size();
			paramList = new ArrayList<DiagramParameter>(size);
			for (int i = 0; i < size; i++) {
				DiagramParameter desBean = (DiagramParameter) this.diagram.getParameterList().get(i);
				DiagramParameter tempBean = (DiagramParameter) desBean.clone();
				paramList.add(tempBean);
			}
		}
		tvParameters.setInput(paramList);
		tvCodesets.setInput(codeSetList);

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

	class CodesetLabelProvider extends BaseLabelProvider implements ITableLabelProvider {
		public String getColumnText(Object obj, int columnIndex) {
			CodeSet codeset = (CodeSet) obj;
			switch (columnIndex) {
			case 0:
				return codeset.getName() == null ? "" : codeset.getName();
			case 1:
				return codeset.getCnName() == null ? "" : codeset.getCnName();
			case 2:
				return codeset.getCodesetType() == null ? "" : codeset.getCodesetType();
			case 3:
				return codeset.getDescription() == null ? "" : codeset.getDescription();
			}
			return null;
		}

		public String getText(Object element) {
			return element == null ? "" : element.toString();
		}

		public Image getColumnImage(Object obj, int index) {
			return null;
		}

		//      public static String[] DATAFIELDS = new String[] { "name", "cnName", "codesetType", "description" };
	}

	class DiagramParameterLabelProvider extends BaseLabelProvider implements ITableLabelProvider {

		private String[]	codeSets;
		private String[]	codeSetLabels;

		public DiagramParameterLabelProvider(String[] codeSets, String[] codeSetLabels) {
			this.codeSets = codeSets;
			this.codeSetLabels = codeSetLabels;
		}

		public String getColumnText(Object obj, int columnIndex) {
			DiagramParameter dp = (DiagramParameter) obj;
			switch (columnIndex) {
			case 0:
				return dp.getName() == null ? "" : dp.getName();
			case 1:
				return dp.getCnName() == null ? "" : dp.getCnName();
			case 2:
				return dp.getDataType() == null ? "" : getLabel(Consts.DATATYPE_LABEL, Consts.DATATYPE, dp.getDataType());
			case 3:
				return dp.getLength() == null ? "" : dp.getLength().toString();
			case 4:
				return dp.getPrecision() == null ? "" : dp.getPrecision().toString();
			case 5:
				return dp.getPolyLineFlag() == null ? "" : getLabel(Consts.YESNO_LABEL, Consts.YESNO, dp.getPolyLineFlag());
			case 6:
				return dp.getCodeSet() == null ? "" : getLabel(codeSetLabels, codeSets, dp.getCodeSet());
			case 7:
				return dp.getMutliSelect() == null ? "" : getLabel(Consts.YESNO_LABEL, Consts.YESNO, dp.getMutliSelect());
			}
			return null;
		}

		public String getText(Object element) {
			return element == null ? "" : element.toString();
		}

		public Image getColumnImage(Object obj, int index) {
			return null;
		}

		//      public static String[] DATAFIELDS = new String[] { "name", "cnName", "dataType", "length", "precision", "polyLineFlag", "codeSet" };

		public void setCodeSets(String[] codeSets) {
			this.codeSets = codeSets;
		}

		public void setCodeSetLabels(String[] codeSetLabels) {
			this.codeSetLabels = codeSetLabels;
		}
	}

	class DiagramParameterCellModifier extends BaseCellModifer implements ICellModifier {
		private TableViewer	tv;
		private String[]		codeSets;

		public DiagramParameterCellModifier(TableViewer tv, String[] codeSets) {
			this.tv = tv;
			this.codeSets = codeSets;
		}

		public boolean canModify(Object element, String property) {
			return true;
		}

		public Object getValue(Object element, String property) {
			DiagramParameter o = (DiagramParameter) element;
			if (property.equals(DiagramParameter.META_NAME)) return o.getName();
			else if (property.equals(DiagramParameter.META_CNNAME)) return o.getCnName();
			else if (property.equals(DiagramParameter.META_DATATYPE)) return getIndex(Consts.DATATYPE, o.getDataType());
			else if (property.equals(DiagramParameter.META_LENGTH)) return o.getLength().toString();
			else if (property.equals(DiagramParameter.META_PRECISION)) return o.getPrecision().toString();
			else if (property.equals(DiagramParameter.POLYLINEFLAG)) return getIndex(Consts.YESNO, o.getPolyLineFlag());
			else if (property.equals(DiagramParameter.MUTLISELECT)) return getIndex(Consts.YESNO, o.getMutliSelect());
			else if (property.equals(DiagramParameter.CODESET)) return getIndex(codeSets, o.getCodeSet());
			throw new RuntimeException("错误的列别名:" + property);
		}

		public void modify(Object element, String property, Object value) {
			TableItem item = (TableItem) element;
			DiagramParameter o = (DiagramParameter) item.getData();

			if (property.equals(DiagramParameter.META_NAME)) o.setName((String) value);
			else if (property.equals(DiagramParameter.META_CNNAME)) o.setCnName((String) value);
			else if (property.equals(DiagramParameter.META_DATATYPE)) {
				Integer itmp = (Integer) value;
				o.setDataType(Consts.DATATYPE[itmp]);
			}
			else if (property.equals(DiagramParameter.META_LENGTH)) {
				try {
					o.setLength(Integer.parseInt((String) value));
				}
				catch (Exception e) {
					o.setLength(0);
				}
			}
			else if (property.equals(DiagramParameter.META_PRECISION)) {
				try {
					o.setPrecision((Integer.parseInt((String) value)));
				}
				catch (Exception e) {
					o.setPrecision(0);
				}
			}
			else if (property.equals(DiagramParameter.POLYLINEFLAG)) o.setPolyLineFlag(Consts.YESNO[(Integer) value]);
			else if (property.equals(DiagramParameter.MUTLISELECT)) o.setMutliSelect(Consts.YESNO[(Integer) value]);
			else if (property.equals(DiagramParameter.CODESET)) o.setCodeSet(codeSets[(Integer) value]);
			else throw new RuntimeException("错误的列别名:" + property);
			tv.update(o, null);
		}

		public void setCodeSets(String[] codeSets) {
			this.codeSets = codeSets;
		}
	}
}
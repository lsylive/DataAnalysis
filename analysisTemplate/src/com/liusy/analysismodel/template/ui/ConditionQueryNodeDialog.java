package com.liusy.analysismodel.template.ui;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.DialogCellEditor;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
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
import com.swtdesigner.SWTResourceManager;
import com.liusy.analysismodel.Activator;
import com.liusy.analysismodel.template.commands.NodePropertyEditCommand;
import com.liusy.analysis.template.model.Consts;
import com.liusy.analysis.template.model.Diagram;
import com.liusy.analysis.template.model.DiagramParameter;
import com.liusy.analysis.template.model.base.Metadata;
import com.liusy.analysis.template.model.dialogProperties.ConditionQueryNodeProperties;
import com.liusy.analysis.template.model.dialogProperties.DataNodeProperties;
import com.liusy.analysis.template.model.node.ConditionQueryNode;
import com.liusy.analysis.template.model.node.INode;
import com.liusy.analysis.template.model.util.DiagramParameterUtil;
import com.liusy.analysis.template.model.util.StringUtil;
import com.liusy.analysis.template.model.vo.DataField;
import com.liusy.analysis.template.model.vo.DataTable;
import com.liusy.analysis.template.model.vo.Filter;
import com.liusy.analysis.template.model.vo.InputField;
import com.liusy.analysismodel.template.ui.contentProvider.ViewContentProvider;
import com.liusy.analysismodel.template.ui.dialog.DataTableDialog;
import com.liusy.analysismodel.template.ui.dialog.GenerateExpressionDialog;
import com.liusy.analysismodel.template.ui.dialog.HelpDialog;
import com.liusy.analysismodel.template.ui.tableQueryNodeDialog.DataFieldsCellModifier;
import com.liusy.analysismodel.template.ui.tableQueryNodeDialog.DataFieldsLabelProvider;
import com.liusy.analysismodel.template.ui.tableQueryNodeDialog.FiltersCellModifier;
import com.liusy.analysismodel.template.ui.tableQueryNodeDialog.FiltersLabelProvider;
import com.liusy.analysismodel.util.DbManager;
import com.liusy.analysismodel.util.MessageUtil;

public class ConditionQueryNodeDialog extends Dialog {

   private StyledText                     txtSQL;
   private StyledText                     txtOtherCondition;
   private TableViewer                    tvFilter;
   private Table                          tblFilter;
   private TableViewer                    tvDataField;
   private Table                          tblDataField;
   private TableViewer                    tvInputMeta;
   private Table                          tblInputMeta;
   private StyledText                     txtDes;
   private Text                           txtTableName;
   private Text                           txtName;
   private int                            result = 0;
   protected ConditionQueryNodeProperties nodeProperties;
   private ConditionQueryNode             node;
   private Diagram                        diagram;
   private List<DataField>                dataFieldList;
   private List<InputField>               inputFieldList;
   private List<Filter>                   filterList;
   private NodePropertyEditCommand        cmd;
   private Button                         btnUseNewDataSet;
   private Button                         btnUseOriginalDataSet;

   //   private FiltersCellModifier  filtersCellModifier;

   public ConditionQueryNodeDialog(Shell parent, int style) {
      super(parent, style);
   }

   public ConditionQueryNodeDialog(Shell parent, ConditionQueryNode node, NodePropertyEditCommand command) {

      this(parent, SWT.NONE);
      this.node = node;
      this.diagram = node.getDiagram();
      this.cmd = command;
   }
   public int open() {
      try {
         nodeProperties = (ConditionQueryNodeProperties) node.getProperties().clone();
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
      shell.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "src/com/thunisoft/dataplatform/template/image/png16/GEN.png"));
      shell.setLayout(new FormLayout());
      shell.setSize(820, 531);
      shell.setText("条件表查询属性");

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

      final Label label_1 = new Label(basicGroup, SWT.RIGHT);
      final FormData fd_label_1 = new FormData();
      fd_label_1.top = new FormAttachment(0, 0);
      fd_label_1.right = new FormAttachment(55, 0);
      fd_label_1.left = new FormAttachment(txtName, 0);
      label_1.setLayoutData(fd_label_1);
      label_1.setText("数据表名：");

      final Button btnSelectTable = new Button(basicGroup, SWT.NONE);
      btnSelectTable.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/document-horizontal-text.png"));
      final FormData fd_btnSelectTable = new FormData();
      fd_btnSelectTable.top = new FormAttachment(0, 0);
      fd_btnSelectTable.right = new FormAttachment(100, -1);
      fd_btnSelectTable.width = 23;
      fd_btnSelectTable.height = 23;
      btnSelectTable.setLayoutData(fd_btnSelectTable);
      btnSelectTable.addSelectionListener(new SelectionAdapter() {
         public void widgetSelected(final SelectionEvent e) {
            DataTableDialog dataSoruceDlg = new DataTableDialog(shell);
            DataTable dataTable = dataSoruceDlg.open();
            if (dataTable != null) {
               txtTableName.setText(dataTable.getCnName());
               nodeProperties.setTableName(dataTable.getCnName());
               nodeProperties.setName(dataTable.getName());
               int res = MessageUtil.comfirm(shell, "导入", "是否导入字段信息?\r\n注意：已有字段信息将被覆盖!");
               if (res == SWT.YES) {
                  loadFieldsInfo(dataTable);
                  dataFieldList.clear();
                  for (DataField df : dataTable.getFields()) {
                     dataFieldList.add(df);
                  }
                  tvDataField.refresh();
               }
            }
         }
      });

      txtTableName = new Text(basicGroup, SWT.BORDER);
      txtTableName.setEditable(false);
      final FormData fd_txtTableName = new FormData();
      fd_txtTableName.top = new FormAttachment(0, 0);
      fd_txtTableName.right = new FormAttachment(btnSelectTable, -1);
      fd_txtTableName.left = new FormAttachment(label_1, 0);
      txtTableName.setLayoutData(fd_txtTableName);

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
      fd_group_4.top = new FormAttachment(txtTableName, -3);
      fd_group_4.width = 150;
      fd_group_4.right = new FormAttachment(100, 0);
      fd_group_4.bottom = new FormAttachment(100, 0);
      group_4.setLayoutData(fd_group_4);
      group_4.setLayout(new FormLayout());

      btnUseNewDataSet = new Button(group_4, SWT.RADIO);
      btnUseNewDataSet.setToolTipText("按照新生成的数据结构输出数据，\r\n放弃原有的数据结构。");
      btnUseNewDataSet.setSelection(true);
      final FormData fd_btnUseNewDataSet = new FormData();
      fd_btnUseNewDataSet.left = new FormAttachment(0, 5);
      fd_btnUseNewDataSet.top = new FormAttachment(0, -3);
      btnUseNewDataSet.setLayoutData(fd_btnUseNewDataSet);
      btnUseNewDataSet.setText("生成新的数据集");

      btnUseOriginalDataSet = new Button(group_4, SWT.RADIO);
      btnUseOriginalDataSet.setToolTipText("将新查询出的数据添加到原有的数据集中，\r\n要求新生成的数据结构和输入的数据结构相同。");
      final FormData fd_btnUseOriginalDataSet = new FormData();
      fd_btnUseOriginalDataSet.left = new FormAttachment(0, 5);
      fd_btnUseOriginalDataSet.top = new FormAttachment(btnUseNewDataSet, 5);
      btnUseOriginalDataSet.setLayoutData(fd_btnUseOriginalDataSet);
      btnUseOriginalDataSet.setText("添加到原有的数据集");

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
      btnHelp.addSelectionListener(new SelectionAdapter() {
         public void widgetSelected(final SelectionEvent e) {
         }
      });
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
      tabFolder.addSelectionListener(new SelectionAdapter() {
         public void widgetSelected(final SelectionEvent e) {
            TabItem ti = (TabItem) e.item;
            if (ti.getText().startsWith("SQL")) {
               DataNodeProperties d = new DataNodeProperties();
               d.setAdditionSql(txtOtherCondition.getText());
               //               d.setTableName(nodeProperties.getTableName());
               d.setName(nodeProperties.getName());
               d.setFields(dataFieldList);
               d.setFilters(filterList);
               txtSQL.setText(d.getSQL(null));
            }
            else if (ti.getText().startsWith("过滤")) {
               String[] fNames = null;
               if (dataFieldList != null && dataFieldList.size() > 0) {
                  fNames = new String[dataFieldList.size()];
                  for (int i = 0; i < dataFieldList.size(); i++) {
                     fNames[i] = dataFieldList.get(i).getAliasName();
                  }
               }
               else fNames = new String[] { "" };
               //               filtersCellModifier.setAliasNames(fNames);
               tvFilter.setCellModifier(new FiltersCellModifier(tvFilter, fNames));
               tvFilter.getCellEditors()[0] = new ComboBoxCellEditor(tblFilter, fNames, SWT.READ_ONLY);
            }
         }
      });
      final FormData fd_tabFolder = new FormData();
      fd_tabFolder.bottom = new FormAttachment(100, -40);
      fd_tabFolder.top = new FormAttachment(basicGroup, 3, SWT.BOTTOM);
      fd_tabFolder.right = new FormAttachment(100, -5);
      fd_tabFolder.left = new FormAttachment(0, 5);
      tabFolder.setLayoutData(fd_tabFolder);

      final TabItem tabFields = new TabItem(tabFolder, SWT.NONE);
      tabFields.setText("查询字段");

      final Composite composite1 = new Composite(tabFolder, SWT.NONE);
      composite1.setLayout(new FormLayout());
      tabFields.setControl(composite1);

      final Group group_1 = new Group(composite1, SWT.NO_RADIO_GROUP);
      group_1.setLayout(new FormLayout());
      final FormData fd_group_1 = new FormData();
      fd_group_1.left = new FormAttachment(0, 0);
      fd_group_1.right = new FormAttachment(100, 0);
      fd_group_1.top = new FormAttachment(0, -6);
      fd_group_1.bottom = new FormAttachment(100, 0);
      group_1.setLayoutData(fd_group_1);
      //      tabFields.setControl(group_1);

      tvDataField = new TableViewer(group_1, SWT.MULTI | SWT.FULL_SELECTION | SWT.BORDER);
      tvDataField.setContentProvider(new ViewContentProvider());
      tvDataField.setLabelProvider(new DataFieldsLabelProvider());
      tvDataField.setColumnProperties(DataFieldsLabelProvider.DATAFIELDS);
      tblDataField = tvDataField.getTable();

      CellEditor[] cellEditor = new CellEditor[9];
      cellEditor[0] = new TextCellEditor(tblDataField);
      cellEditor[1] = new TextCellEditor(tblDataField);
      cellEditor[2] = new TextCellEditor(tblDataField);
      cellEditor[3] = new ComboBoxCellEditor(tblDataField, Consts.DATATYPE_LABEL, SWT.READ_ONLY);
      cellEditor[4] = new TextCellEditor(tblDataField);
      cellEditor[5] = new ComboBoxCellEditor(tblDataField, Consts.SORTDIRECT_LABEL, SWT.READ_ONLY);
      cellEditor[6] = new ComboBoxCellEditor(tblDataField, Consts.AGGREGATE_LABEL, SWT.READ_ONLY);
      cellEditor[7] = new TextCellEditor(tblDataField);
      cellEditor[8] = new ComboBoxCellEditor(tblDataField, Consts.YESNO_LABEL, SWT.READ_ONLY);
      Text text1 = (Text) cellEditor[4].getControl();
      text1.addVerifyListener(new VerifyListener() {
         public void verifyText(VerifyEvent e) {
            String str = e.text;
            if (str != null && str.length() > 0) e.doit = StringUtil.isInteger(str);
         }
      });
      Text text2 = (Text) cellEditor[7].getControl();
      text2.addVerifyListener(new VerifyListener() {
         public void verifyText(VerifyEvent e) {
            String str = e.text;
            if (str != null && str.length() > 0) e.doit = StringUtil.isInteger(str);
         }
      });

      tvDataField.setCellEditors(cellEditor);
      tvDataField.setCellModifier(new DataFieldsCellModifier(tvDataField));

      final FormData fd_table_1 = new FormData();
      fd_table_1.bottom = new FormAttachment(100, -22);
      fd_table_1.top = new FormAttachment(0, -6);
      fd_table_1.right = new FormAttachment(100, 0);
      fd_table_1.left = new FormAttachment(0, 0);
      tblDataField.setLayoutData(fd_table_1);
      tblDataField.setLinesVisible(true);
      tblDataField.setHeaderVisible(true);

      final TableColumn colCnName = new TableColumn(tblDataField, SWT.NONE);
      colCnName.setWidth(100);
      colCnName.setText("中文名");

      final TableColumn colFieldName = new TableColumn(tblDataField, SWT.NONE);
      colFieldName.setWidth(100);
      colFieldName.setText("字段名");

      final TableColumn colAliasName = new TableColumn(tblDataField, SWT.NONE);
      colAliasName.setWidth(100);
      colAliasName.setText("别名");

      final TableColumn colDataType = new TableColumn(tblDataField, SWT.NONE);
      colDataType.setWidth(80);
      colDataType.setText("数据类型");

      final TableColumn colSortNo = new TableColumn(tblDataField, SWT.NONE);
      colSortNo.setWidth(60);
      colSortNo.setText("排序顺序");

      final TableColumn colSortDirect = new TableColumn(tblDataField, SWT.NONE);
      colSortDirect.setWidth(80);
      colSortDirect.setText("排序方向");

      final TableColumn colAggregate = new TableColumn(tblDataField, SWT.NONE);
      colAggregate.setWidth(100);
      colAggregate.setText("聚集函数");

      final TableColumn colAggregateNo = new TableColumn(tblDataField, SWT.NONE);
      colAggregateNo.setWidth(60);
      colAggregateNo.setText("分组顺序");

      final TableColumn colOutput = new TableColumn(tblDataField, SWT.NONE);
      colOutput.setWidth(80);
      colOutput.setText("是否输出");

      final TabItem tabInput = new TabItem(tabFolder, SWT.NONE);
      tabInput.setText("输入数据");

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

      tvInputMeta = new TableViewer(group_3, SWT.FULL_SELECTION | SWT.BORDER);
      tvInputMeta.setContentProvider(new ViewContentProvider());
      tvInputMeta.setLabelProvider(new InputMetaLabelProvider());
      tvInputMeta.setColumnProperties(INPUTMETAS);
      tblInputMeta = tvInputMeta.getTable();
      final FormData fd_tblInputMeta = new FormData();
      fd_tblInputMeta.bottom = new FormAttachment(100, -21);
      fd_tblInputMeta.right = new FormAttachment(100, 0);
      fd_tblInputMeta.top = new FormAttachment(0, -6);
      fd_tblInputMeta.left = new FormAttachment(0, 0);
      tblInputMeta.setLayoutData(fd_tblInputMeta);
      tblInputMeta.setLinesVisible(true);
      tblInputMeta.setHeaderVisible(true);
      CellEditor[] cellEditor2 = new CellEditor[6];
      cellEditor2[0] = new TextCellEditor(tblInputMeta, SWT.READ_ONLY);
      cellEditor2[1] = new TextCellEditor(tblInputMeta, SWT.READ_ONLY);
      cellEditor2[2] = new TextCellEditor(tblInputMeta, SWT.READ_ONLY);
      cellEditor2[3] = new ComboBoxCellEditor(tblInputMeta, Consts.YESNO_LABEL, SWT.READ_ONLY);
      cellEditor2[4] = new ComboBoxCellEditor(tblInputMeta, OPERATOR_LABEL, SWT.READ_ONLY);
      cellEditor2[5] = new ExpressionCellEditor1(tblInputMeta);
      tvInputMeta.setCellEditors(cellEditor2);
      tvInputMeta.setCellModifier(new InputsCellModifier(tvInputMeta));

      final TableColumn colCnName1 = new TableColumn(tblInputMeta, SWT.NONE);
      colCnName1.setWidth(150);
      colCnName1.setText("中文名");

      final TableColumn colFieldName1 = new TableColumn(tblInputMeta, SWT.NONE);
      colFieldName1.setWidth(120);
      colFieldName1.setText("字段名");

      final TableColumn colDataType1 = new TableColumn(tblInputMeta, SWT.NONE);
      colDataType1.setWidth(120);
      colDataType1.setText("数据类型");

      final TableColumn colOutput1 = new TableColumn(tblInputMeta, SWT.NONE);
      colOutput1.setWidth(80);
      colOutput1.setText("是否输出");

      final TableColumn colOper1 = new TableColumn(tblInputMeta, SWT.NONE);
      colOper1.setWidth(100);
      colOper1.setText("操作符");

      final TableColumn colExpress1 = new TableColumn(tblInputMeta, SWT.NONE);
      colExpress1.setWidth(250);
      colExpress1.setText("表达式");

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
                     inputFieldList.add(new InputField(md));
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

      final TabItem tabFilter = new TabItem(tabFolder, SWT.NONE);
      tabFilter.setText("过滤条件");

      final Composite composite = new Composite(tabFolder, SWT.NONE);
      composite.setLayout(new FormLayout());
      tabFilter.setControl(composite);

      final Group group_2 = new Group(composite, SWT.NO_RADIO_GROUP);
      final FormData fd_group_2 = new FormData();
      fd_group_2.left = new FormAttachment(0, 0);
      fd_group_2.right = new FormAttachment(100, 0);
      fd_group_2.top = new FormAttachment(0, -6);
      fd_group_2.bottom = new FormAttachment(100, -80);
      group_2.setLayoutData(fd_group_2);
      group_2.setLayout(new FormLayout());

      tvFilter = new TableViewer(group_2, SWT.FULL_SELECTION | SWT.BORDER);
      tvFilter.setLabelProvider(new FiltersLabelProvider());
      tvFilter.setContentProvider(new ViewContentProvider());
      tvFilter.setColumnProperties(FiltersLabelProvider.DATAFIELDS);
      tblFilter = tvFilter.getTable();
      CellEditor[] cellEditor1 = new CellEditor[3];
      cellEditor1[0] = new TextCellEditor(tblFilter);
      String[] aliasNames = new String[] { "" };
      cellEditor1[0] = new ComboBoxCellEditor(tblFilter, aliasNames, SWT.READ_ONLY);
      cellEditor1[1] = new ComboBoxCellEditor(tblFilter, Consts.OPERATOR_LABEL, SWT.READ_ONLY);
      cellEditor1[2] = new ExpressionCellEditor(tblFilter);

      tvFilter.setCellEditors(cellEditor1);
      tvFilter.setCellModifier(new FiltersCellModifier(tvFilter, aliasNames));

      final FormData fd_table_2 = new FormData();
      fd_table_2.bottom = new FormAttachment(100, -21);
      fd_table_2.top = new FormAttachment(0, -5);
      fd_table_2.right = new FormAttachment(100, -1);
      fd_table_2.left = new FormAttachment(0, 1);
      tblFilter.setLayoutData(fd_table_2);
      tblFilter.setLinesVisible(true);
      tblFilter.setHeaderVisible(true);

      final TableColumn colFilterFieldName = new TableColumn(tblFilter, SWT.NONE);
      colFilterFieldName.setWidth(120);
      colFilterFieldName.setText("字段名称");

      final TableColumn colOper = new TableColumn(tblFilter, SWT.NONE);
      colOper.setWidth(100);
      colOper.setText("操作符");

      final TableColumn colFilterData = new TableColumn(tblFilter, SWT.NONE);
      colFilterData.setWidth(500);
      colFilterData.setText("表达式");

      final Button btnFilterAdd = new Button(group_2, SWT.NONE);
      btnFilterAdd.addSelectionListener(new SelectionAdapter() {
         public void widgetSelected(final SelectionEvent e) {
            Filter df = new Filter();
            df.setField("");
            df.setOperator("=");
            df.setExpression("");
            filterList.add(df);
            tvFilter.refresh();
         }
      });
      btnFilterAdd.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/plus.png"));
      final FormData fd_btnFilterAdd = new FormData();
      fd_btnFilterAdd.bottom = new FormAttachment(100, -1);
      fd_btnFilterAdd.left = new FormAttachment(0, 1);
      fd_btnFilterAdd.height = 20;
      fd_btnFilterAdd.width = 20;
      btnFilterAdd.setLayoutData(fd_btnFilterAdd);

      final Button btnFilterDel = new Button(group_2, SWT.NONE);
      btnFilterDel.addSelectionListener(new SelectionAdapter() {
         public void widgetSelected(final SelectionEvent e) {
            TableItem[] selectItems = tblFilter.getSelection();
            if (selectItems != null && selectItems.length > 0) {
               for (TableItem ti : selectItems) {
                  Filter o = (Filter) ti.getData();
                  filterList.remove(o);
               }
               tvFilter.refresh();
            }
         }
      });
      btnFilterDel.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/minus.png"));
      final FormData fd_btnFilterDel = new FormData();
      fd_btnFilterDel.bottom = new FormAttachment(100, -1);
      fd_btnFilterDel.left = new FormAttachment(btnFilterAdd, 1, SWT.DEFAULT);
      fd_btnFilterDel.height = 20;
      fd_btnFilterDel.width = 20;
      btnFilterDel.setLayoutData(fd_btnFilterDel);

      final Button btnFilterUp = new Button(group_2, SWT.NONE);
      btnFilterUp.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/arrow-090.png"));
      final FormData fd_btnFilterUp = new FormData();
      fd_btnFilterUp.bottom = new FormAttachment(100, -1);
      fd_btnFilterUp.left = new FormAttachment(btnFilterDel, 1, SWT.DEFAULT);
      fd_btnFilterUp.height = 20;
      fd_btnFilterUp.width = 20;
      btnFilterUp.setLayoutData(fd_btnFilterUp);
      btnFilterUp.setVisible(false);

      final Button btnFilterDown = new Button(group_2, SWT.NONE);
      btnFilterDown.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/arrow-270.png"));
      final FormData fd_btnFilterDown = new FormData();
      fd_btnFilterDown.bottom = new FormAttachment(100, -1);
      fd_btnFilterDown.left = new FormAttachment(btnFilterUp, 1, SWT.DEFAULT);
      fd_btnFilterDown.height = 20;
      fd_btnFilterDown.width = 20;
      btnFilterDown.setLayoutData(fd_btnFilterDown);
      btnFilterDown.setVisible(false);

      final Label label_2 = new Label(composite, SWT.NONE);
      final FormData fd_label_2 = new FormData();
      fd_label_2.bottom = new FormAttachment(100, -60);
      fd_label_2.top = new FormAttachment(group_2, 1);
      fd_label_2.width = 70;
      fd_label_2.left = new FormAttachment(0, 0);
      label_2.setLayoutData(fd_label_2);
      label_2.setText("其他条件：");

      txtOtherCondition = new StyledText(composite, SWT.BORDER);
      final FormData fd_txtOtherCondition = new FormData();
      fd_txtOtherCondition.bottom = new FormAttachment(100, -1);
      fd_txtOtherCondition.top = new FormAttachment(label_2, 1);
      fd_txtOtherCondition.right = new FormAttachment(100, -1);
      fd_txtOtherCondition.left = new FormAttachment(0, 1);
      txtOtherCondition.setLayoutData(fd_txtOtherCondition);

      final Button btnFieldAdd = new Button(group_1, SWT.NONE);
      btnFieldAdd.addSelectionListener(new SelectionAdapter() {
         public void widgetSelected(final SelectionEvent e) {
            DataField df = new DataField();
            df.setOutput(Consts.YES);
            df.setSortDirect("");
            df.setSortNo("");
            df.setAggregate("");
            dataFieldList.add(df);
            tvDataField.refresh();
         }
      });
      btnFieldAdd.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/plus.png"));
      final FormData fd_btnFieldAdd = new FormData();
      fd_btnFieldAdd.bottom = new FormAttachment(100, 0);
      fd_btnFieldAdd.left = new FormAttachment(0, 0);
      fd_btnFieldAdd.height = 20;
      fd_btnFieldAdd.width = 20;
      btnFieldAdd.setLayoutData(fd_btnFieldAdd);

      final Button btnFieldDel = new Button(group_1, SWT.NONE);
      btnFieldDel.addSelectionListener(new SelectionAdapter() {
         public void widgetSelected(final SelectionEvent e) {
            TableItem[] selectItems = tblDataField.getSelection();
            if (selectItems != null && selectItems.length > 0) {
               for (TableItem ti : selectItems) {
                  DataField o = (DataField) ti.getData();
                  dataFieldList.remove(o);
               }
               tvDataField.refresh();
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

      final TabItem tabSQL = new TabItem(tabFolder, SWT.NONE);
      tabSQL.setText("SQL语句");

      final Composite composite_1 = new Composite(tabFolder, SWT.NONE);
      composite_1.setLayout(new FormLayout());
      tabSQL.setControl(composite_1);

      final Group group = new Group(composite_1, SWT.NONE);
      group.setLayout(new FormLayout());
      final FormData fd_group = new FormData();
      fd_group.top = new FormAttachment(0, -6);
      fd_group.left = new FormAttachment(0, 0);
      fd_group.bottom = new FormAttachment(100, 0);
      fd_group.right = new FormAttachment(100, 0);
      group.setLayoutData(fd_group);

      txtSQL = new StyledText(group, SWT.BORDER);
      final FormData fd_txtSQL = new FormData();
      fd_txtSQL.bottom = new FormAttachment(100, 0);
      fd_txtSQL.top = new FormAttachment(0, -6);
      fd_txtSQL.right = new FormAttachment(100, 0);
      fd_txtSQL.left = new FormAttachment(0, 0);
      txtSQL.setLayoutData(fd_txtSQL);
      txtSQL.setFont(SWTResourceManager.getFont("Fixedsys", 10, SWT.NONE));
      txtSQL.setEditable(false);
   }

   private void loadFieldsInfo(DataTable dt) {
      List<DataField> list = DbManager.loadFieldsInfo(dt.getId());
      dt.setFields(list);
   }

   private void init() {

      txtName.setText(node.getName());
      txtDes.setText(node.getDescription());
      txtTableName.setText(nodeProperties.getTableName());
      txtOtherCondition.setText(nodeProperties.getAdditionSql());

      if (nodeProperties.getDatasetType() == ConditionQueryNodeProperties.DATASET_NEW) {
         btnUseNewDataSet.setSelection(true);
         btnUseOriginalDataSet.setSelection(false);
      }
      else {
         btnUseNewDataSet.setSelection(false);
         btnUseOriginalDataSet.setSelection(true);
      }

      dataFieldList = new ArrayList<DataField>();
      String[] fNames = null;
      if (nodeProperties.getFields() != null && nodeProperties.getFields().size() > 0) {
         fNames = new String[nodeProperties.getFields().size()];
         for (int i = 0; i < nodeProperties.getFields().size(); i++) {
            dataFieldList.add(nodeProperties.getFields().get(i));
            fNames[i] = nodeProperties.getFields().get(i).getAliasName();
         }
      }
      else fNames = new String[] { "" };
      tvDataField.setInput(dataFieldList);

      inputFieldList = new ArrayList<InputField>();
      if (nodeProperties.getInputFieldList() != null && nodeProperties.getInputFieldList().size() > 0) {
         for (InputField mt : nodeProperties.getInputFieldList()) {
            inputFieldList.add((InputField) mt.clone());
         }
      }
      tvInputMeta.setInput(inputFieldList);

      filterList = new ArrayList<Filter>();
      for (Filter df : nodeProperties.getFilters()) {
         filterList.add(df.clone());
      }
      tvFilter.setCellModifier(new FiltersCellModifier(tvFilter, fNames));
      tvFilter.getCellEditors()[0] = new ComboBoxCellEditor(tblFilter, fNames, SWT.READ_ONLY);
      tvFilter.setInput(filterList);
   }

   private void save() {
      node.setName(txtName.getText().trim());
      node.setDescription(txtDes.getText());
      nodeProperties.setTableName(txtTableName.getText().trim());
      nodeProperties.setAdditionSql(txtOtherCondition.getText());
      if (btnUseNewDataSet.getSelection()) nodeProperties.setDatasetType(ConditionQueryNodeProperties.DATASET_NEW);
      else nodeProperties.setDatasetType(ConditionQueryNodeProperties.DATASET_APPEND);

      nodeProperties.setFields(dataFieldList);
      nodeProperties.setFilters(filterList);
      nodeProperties.setInputFieldList(inputFieldList);
  
      //DiagramParameterUtil.FilterConvertDiagramParameter(diagram, filterList, dataFieldList);
      
      node.setProperties(nodeProperties);
   }

   protected Shell shell;

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
         InputField dsi = (InputField) obj;
         switch (columnIndex) {
         case 0:
            return dsi.getCnName() == null ? "" : dsi.getCnName();
         case 1:
            return dsi.getName() == null ? "" : dsi.getName();
         case 2:
            return dsi.getDataType() == null ? "" : getLabel(Consts.DATATYPE_LABEL, Consts.DATATYPE, dsi.getDataType());
         case 3:
            return dsi.getOutput() == null ? Consts.YES : getLabel(Consts.YESNO_LABEL, Consts.YESNO, dsi.getOutput());
         case 4:
            return dsi.getOperator() == null ? "" : getLabel(OPERATOR_LABEL, OPERATOR, dsi.getOperator());
         case 5:
            return dsi.getExpress() == null ? "" : dsi.getExpress();
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

   public static String[] INPUTMETAS = new String[] { "cnName", "name", "dataType", "output", "operator", "express" };

   class ExpressionCellEditor extends DialogCellEditor {

      private Table table;

      public ExpressionCellEditor(Composite parent) {
         super(parent);
         this.table = (Table) parent;
      }

      protected Object openDialogBox(Control cellEditorWindow) {
         TableItem[] selectItems = table.getSelection();
         String exp = "";
         if (selectItems != null && selectItems.length > 0) exp = ((Filter) selectItems[0].getData()).getExpression();

         GenerateExpressionDialog gcd = new GenerateExpressionDialog(cellEditorWindow.getShell(), exp);
         String res = gcd.open();
         return res;
      }
   }

   class ExpressionCellEditor1 extends DialogCellEditor {

      private Table table;

      public ExpressionCellEditor1(Composite parent) {
         super(parent);
         this.table = (Table) parent;
      }

      protected Object openDialogBox(Control cellEditorWindow) {
         TableItem[] selectItems = table.getSelection();
         String exp = "";
         if (selectItems != null && selectItems.length > 0) exp = ((InputField) selectItems[0].getData()).getExpress();

         GenerateExpressionDialog gcd = new GenerateExpressionDialog(cellEditorWindow.getShell(), exp);
         String res = gcd.open();
         return res;
      }
   }

   class InputsCellModifier implements ICellModifier {
      private TableViewer tv;

      public InputsCellModifier(TableViewer tv) {
         this.tv = tv;
      }

      public boolean canModify(Object element, String property) {
         return true;
      }

      public Object getValue(Object element, String property) {
         InputField o = (InputField) element;
         if (property.equals("name")) return o.getName();
         else if (property.equals("cnName")) return o.getCnName();
         else if (property.equals("express")) return o.getExpress();
         else if (property.equals("dataType")) return getIndex(Consts.DATATYPE, o.getDataType());
         else if (property.equals("output")) return getIndex(Consts.YESNO, o.getOutput());
         else if (property.equals("operator")) return getIndex(OPERATOR, o.getOperator());
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
         InputField o = (InputField) item.getData();

         if (property.equals("name")) {
            o.setName((String) value);
         }
         else if (property.equals("cnName")) {
            o.setCnName((String) value);
         }
         else if (property.equals("express")) {
            o.setExpress((String) value);
         }
         else if (property.equals("dataType")) {
            Integer itmp = (Integer) value;
            o.setDataType(Consts.DATATYPE[itmp]);
         }
         else if (property.equals("operator")) {
            Integer itmp = (Integer) value;
            o.setOperator(OPERATOR[itmp]);
         }
         else if (property.equals("output")) {
            Integer itmp = (Integer) value;
            o.setOutput(Consts.YESNO[itmp]);
         }
         else {
            throw new RuntimeException("错误的列别名:" + property);
         }
         tv.update(o, null);
      }
   }

   public static String[] OPERATOR       = { "", "=", "!=", ">", "<", ">=", "<=" };
   public static String[] OPERATOR_LABEL = { "", "等于", "不等于", "大于", "小于", "大于等于", "小于等于" };
}

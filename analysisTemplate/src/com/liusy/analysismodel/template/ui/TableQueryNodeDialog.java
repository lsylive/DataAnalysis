package com.liusy.analysismodel.template.ui;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
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
import com.swtdesigner.SWTResourceManager;
import com.liusy.analysismodel.Activator;
import com.liusy.analysismodel.template.commands.DataNodeBeanEditCommand;
import com.liusy.analysis.template.model.Consts;
import com.liusy.analysis.template.model.Diagram;
import com.liusy.analysis.template.model.dialogProperties.DataNodeProperties;
import com.liusy.analysis.template.model.node.DataNode;
import com.liusy.analysis.template.model.util.StringUtil;
import com.liusy.analysis.template.model.vo.DataField;
import com.liusy.analysis.template.model.vo.DataTable;
import com.liusy.analysis.template.model.vo.Filter;
import com.liusy.analysismodel.template.ui.contentProvider.ViewContentProvider;
import com.liusy.analysismodel.template.ui.dialog.DataTableDialog;
import com.liusy.analysismodel.template.ui.tableQueryNodeDialog.DataFieldsCellModifier;
import com.liusy.analysismodel.template.ui.tableQueryNodeDialog.DataFieldsLabelProvider;
import com.liusy.analysismodel.template.ui.tableQueryNodeDialog.FiltersCellModifier;
import com.liusy.analysismodel.template.ui.tableQueryNodeDialog.FiltersLabelProvider;
import com.liusy.analysismodel.util.DbConnectionManager;
import com.liusy.analysismodel.util.MessageUtil;

public class TableQueryNodeDialog extends Dialog {

   private StyledText              txtSQL;
   private StyledText              txtOtherCondition;
   private TableViewer             tvFilter;
   private Table                   tblFilter;
   private TableViewer             tvDataField;
   private Table                   tblDataField;
   private StyledText              txtDes;
   private Text                    txtTableName;
   private Text                    txtName;
   private int                     result = 0;
   protected DataNodeProperties    nodeProperties;
   private DataNode                node;
   private Diagram                 diagram;
   private List<DataField>         dataFieldList;
   private List<Filter>            filterList;
   private DataNodeBeanEditCommand cmd;
   private DataTable               dataTable;

   //   private FiltersCellModifier  filtersCellModifier;

   /**
    * Create the dialog
    * 
    * @param parent
    * @param style
    */
   public TableQueryNodeDialog(Shell parent, int style) {
      super(parent, style);
   }

   /**
    * Create the dialog
    * 
    * @param parent
    */
   public TableQueryNodeDialog(Shell parent, DataNode node, DataNodeBeanEditCommand command) {

      this(parent, SWT.NONE);
      this.node = node;
      this.diagram = node.getDiagram();
      this.cmd = command;
   }

   /**
    * Open the dialog
    * 
    * @return the result
    */
   public int open() {
      createContents();
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
      try {
         nodeProperties = (DataNodeProperties) node.getProperties().clone();
      }
      catch (CloneNotSupportedException e) {
         e.printStackTrace();
      }
      cmd.setBean(nodeProperties);
      cmd.setNode(node);
      shell = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
      shell.setLayout(new FormLayout());
      shell.setSize(800, 500);
      shell.setText("表查询属性");

      final Composite composite_3 = new Composite(shell, SWT.NONE);
      final FormData fd_composite_3 = new FormData();
      fd_composite_3.top = new FormAttachment(0, 1);
      fd_composite_3.left = new FormAttachment(0, 5);
      fd_composite_3.height = 100;
      fd_composite_3.right = new FormAttachment(100, -5);
      composite_3.setLayoutData(fd_composite_3);
      composite_3.setLayout(new FormLayout());

      final Group basicGroup = new Group(composite_3, SWT.NONE);
      basicGroup.setLayout(new FormLayout());
      final FormData fd_basicGroup = new FormData();
      fd_basicGroup.bottom = new FormAttachment(100, -1);
      fd_basicGroup.top = new FormAttachment(0, -6);
      fd_basicGroup.right = new FormAttachment(100, 0);
      fd_basicGroup.left = new FormAttachment(0, 0);
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

      txtTableName = new Text(basicGroup, SWT.BORDER);
      txtTableName.setEditable(false);
      final FormData fd_txtTableName = new FormData();
      fd_txtTableName.top = new FormAttachment(0, 0);
      fd_txtTableName.right = new FormAttachment(90, 0);
      fd_txtTableName.left = new FormAttachment(label_1, 0);
      txtTableName.setLayoutData(fd_txtTableName);

      final Button btnSelectTable = new Button(basicGroup, SWT.NONE);
      final FormData fd_btnSelectTable = new FormData();
      fd_btnSelectTable.top = new FormAttachment(0, 0);
      fd_btnSelectTable.right = new FormAttachment(100, -1);
      fd_btnSelectTable.left = new FormAttachment(txtTableName, 0);
      fd_btnSelectTable.height = 23;
      btnSelectTable.setLayoutData(fd_btnSelectTable);
      btnSelectTable.addSelectionListener(new SelectionAdapter() {
         public void widgetSelected(final SelectionEvent e) {
            DataTableDialog dataSoruceDlg = new DataTableDialog(shell);
            DataTable dt = dataSoruceDlg.open();
            if (dt != null) {
               txtTableName.setText(dt.getCnName());
               nodeProperties.setTableName(dt.getCnName());
               nodeProperties.setName(dt.getName());
               loadFieldsInfo(dt);
               nodeProperties.setDataTable(dt);
               int res = MessageUtil.comfirm(shell, "导入", "是否导入字段信息?\r\n注意：已有字段信息将被覆盖!");
               if (res == SWT.YES) {
                  dataFieldList.clear();
                  for (DataField df : dt.getFields()) {
                     dataFieldList.add(df);
                  }
                  tvDataField.refresh();
               }
            }
         }
      });
      btnSelectTable.setText("选择表");

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
      fd_txtDes.top = new FormAttachment(txtName, 5);
      fd_txtDes.left = new FormAttachment(15, 0);
      fd_txtDes.right = new FormAttachment(100, -2);
      fd_txtDes.bottom = new FormAttachment(100, -2);
      txtDes.setLayoutData(fd_txtDes);

      final Button btnOk = new Button(shell, SWT.NONE);
      btnOk.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/tick.png"));
      final FormData fd_btnOk = new FormData();
      fd_btnOk.height = 28;
      fd_btnOk.width = 80;
      fd_btnOk.bottom = new FormAttachment(100, -5);
      fd_btnOk.right = new FormAttachment(50, -10);
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
      fd_btnCancel.left = new FormAttachment(50, 10);
      btnCancel.setLayoutData(fd_btnCancel);
      btnCancel.setText("取消(&C)");
      btnCancel.addListener(SWT.Selection, new Listener() {
         public void handleEvent(Event e) {
            close();
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
      fd_tabFolder.top = new FormAttachment(composite_3, 3, SWT.BOTTOM);
      fd_tabFolder.right = new FormAttachment(100, -5);
      fd_tabFolder.left = new FormAttachment(0, 5);
      tabFolder.setLayoutData(fd_tabFolder);

      final TabItem tabFields = new TabItem(tabFolder, SWT.NONE);
      tabFields.setText("查询字段");

      final Composite composite_1 = new Composite(tabFolder, SWT.NONE);
      composite_1.setLayout(new FormLayout());
      tabFields.setControl(composite_1);

      final Group group_1 = new Group(composite_1, SWT.NONE);
      final FormData fd_group_1 = new FormData();
      fd_group_1.left = new FormAttachment(0, 0);
      fd_group_1.bottom = new FormAttachment(100, 0);
      fd_group_1.right = new FormAttachment(100, 0);
      fd_group_1.top = new FormAttachment(0, -6);
      group_1.setLayoutData(fd_group_1);
      group_1.setLayout(new FormLayout());
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
      cellEditor1[2] = new TextCellEditor(tblFilter);
      tvFilter.setCellEditors(cellEditor1);
      //      filtersCellModifier = new FiltersCellModifier(tvFilter, aliasNames);
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
      colOper.setWidth(120);
      colOper.setText("操作符");

      final TableColumn colFilterData = new TableColumn(tblFilter, SWT.NONE);
      colFilterData.setWidth(500);
      colFilterData.setText("操作数据");

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
      fd_btnFieldDel.left = new FormAttachment(0, 21);
      fd_btnFieldDel.height = 20;
      fd_btnFieldDel.width = 20;
      btnFieldDel.setLayoutData(fd_btnFieldDel);

      final TabItem tabSQL = new TabItem(tabFolder, SWT.NONE);
      tabSQL.setText("SQL语句");

      final Composite composite_2 = new Composite(tabFolder, SWT.NONE);
      composite_2.setLayout(new FormLayout());
      tabSQL.setControl(composite_2);

      final Group group = new Group(composite_2, SWT.NONE);
      final FormData fd_group = new FormData();
      fd_group.top = new FormAttachment(0, -6);
      fd_group.right = new FormAttachment(100, 0);
      fd_group.left = new FormAttachment(0, 0);
      fd_group.bottom = new FormAttachment(100, 0);
      group.setLayoutData(fd_group);
      group.setLayout(new FormLayout());

      txtSQL = new StyledText(group, SWT.BORDER|SWT.WRAP|SWT.MULTI|SWT.V_SCROLL|SWT.H_SCROLL);
      final FormData fd_txtSQL = new FormData();
      fd_txtSQL.bottom = new FormAttachment(100, 0);
      fd_txtSQL.top = new FormAttachment(0, -6);
      fd_txtSQL.right = new FormAttachment(100, 0);
      fd_txtSQL.left = new FormAttachment(0, 0);
      txtSQL.setLayoutData(fd_txtSQL);
      txtSQL.setWordWrap(true);
      txtSQL.setFont(SWTResourceManager.getFont("Fixedsys", 10, SWT.NONE));
      txtSQL.setEditable(false);

      //
      init();
   }

   private void loadFieldsInfo(DataTable dt) {
      List<DataField> list = new ArrayList<DataField>();
      String sql = "select id,name,cn_name as cname,data_type as dataType,is_primary as isPk from t_resource_column where table_id=" + dt.getId();
      Integer itmp;
      String stmp;
      Connection conn = null;
      Statement stat = null;
      ResultSet rs = null;

      try {
         conn = DbConnectionManager.getConnection();
         if (conn != null) {
            stat = conn.createStatement();
            rs = stat.executeQuery(sql);

            if (rs == null) return;
            while (rs.next()) {
               DataField df = new DataField();

               itmp = rs.getInt("id");
               if (rs.wasNull()) df.setId("");
               else df.setId(itmp.toString());

               stmp = rs.getString("cname");
               if (rs.wasNull()) df.setCnName("");
               else df.setCnName(stmp);

               stmp = rs.getString("dataType");
               if (rs.wasNull()) df.setDataType("");
               else df.setDataType(stmp);

               stmp = rs.getString("name");
               if (rs.wasNull()) {
                  df.setName("");
                  df.setAliasName("");
               }
               else {
                  df.setName(stmp);
                  df.setAliasName(stmp);
                  String stmp1 = rs.getString("isPk");
                  if (!rs.wasNull() && stmp1.equals("1")) dt.setPkName(stmp);
               }
               df.setOutput(Consts.YES);
               df.setSortDirect("");
               df.setSortNo("");
               df.setAggregate("");
               list.add(df);
            }
            conn.commit();
         }
      }
      catch (Exception e) {
         DbConnectionManager.rollBack(conn);
         System.out.println("数据表访问出错：" + e.getMessage());
      }
      finally {
         DbConnectionManager.closeResultSet(rs);
         DbConnectionManager.closeStatement(stat);
      }
      dt.setFields(list);
   }

   private void init() {

      txtName.setText(node.getName());
      txtDes.setText(node.getDescription());
      txtTableName.setText(nodeProperties.getTableName());
      txtOtherCondition.setText(nodeProperties.getAdditionSql());

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

      filterList = new ArrayList<Filter>();
      for (Filter df : nodeProperties.getFilters()) {
         filterList.add(df.clone());
      }
      //      filtersCellModifier.setAliasNames(fNames);
      tvFilter.setCellModifier(new FiltersCellModifier(tvFilter, fNames));
      tvFilter.getCellEditors()[0] = new ComboBoxCellEditor(tblFilter, fNames, SWT.READ_ONLY);
      tvFilter.setInput(filterList);
   }

   private void save() {
      node.setName(txtName.getText().trim());
      node.setDescription(txtDes.getText());
      nodeProperties.setTableName(txtTableName.getText().trim());
      nodeProperties.setAdditionSql(txtOtherCondition.getText());

      nodeProperties.setFields(dataFieldList);
      nodeProperties.setFilters(filterList);
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
}

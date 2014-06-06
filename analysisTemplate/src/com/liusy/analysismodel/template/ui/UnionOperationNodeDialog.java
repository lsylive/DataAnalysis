package com.liusy.analysismodel.template.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
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
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
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
import com.liusy.analysis.template.model.connection.Connection;
import com.liusy.analysis.template.model.dialogProperties.UnionOperationProperties;
import com.liusy.analysis.template.model.node.INode;
import com.liusy.analysis.template.model.node.UnionOperationNode;
import com.liusy.analysis.template.model.vo.UnionOperationConfig;
import com.liusy.analysis.template.model.vo.UnionOperationField;
import com.liusy.analysismodel.template.ui.contentProvider.ViewContentProvider;
import com.liusy.analysismodel.template.ui.tableQueryNodeDialog.OperatorLabelProvider;
import com.liusy.analysismodel.template.ui.tableQueryNodeDialog.UnionOperationExpressDialogCellEditor;
import com.liusy.analysismodel.template.ui.tableQueryNodeDialog.UnionOperation_ExpressionCellModifier;
import com.liusy.analysismodel.template.ui.tableQueryNodeDialog.UnionOperation_RelateMetaCellModifier;
import com.liusy.analysismodel.util.MessageUtil;

public class UnionOperationNodeDialog extends Dialog {

   protected int                      result;
   protected Shell                    shell;
   private Diagram                    diagram;
   private UnionOperationNode         node;
   private Text                       txtName;
   private StyledText                 txtDes;
   private Table                      tblShowConfig;
   protected UnionOperationProperties nodeProperties;
   private TableViewer                tableViewer;
   private TableViewer                tableViewer_1;
   private List<INode>                nodeList  = new ArrayList<INode>();
   private List<UnionOperationConfig> relateConfigList;
   private List<UnionOperationField>  expressionList;
   private String[]                   nodeId;
   private Map<String, String[]>      fieldList = new HashMap<String, String[]>();
   private Table                      tblOperator;
   private Combo                      structCombo;
   private String                     outputNodeStruct;
   private NodePropertyEditCommand    cmd;

   /**
    * Create the dialog.
    * 
    * @param parent
    * @param style
    */
   public UnionOperationNodeDialog(Shell parent, int style) {
      super(parent, style);
      setText("合集运算结点配置窗口");
   }

   public UnionOperationNodeDialog(Shell parent, UnionOperationNode node, NodePropertyEditCommand cmd) {

      this(parent, SWT.NONE);
      this.node = node;
      this.cmd = cmd;
      this.diagram = node.getDiagram();
   }

   /**
    * Open the dialog.
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
      shell.setSize(670, 422);
      shell.setText(getText());
      shell.setLayout(new FormLayout());

      Group group = new Group(shell, SWT.NONE);
      FormData fd_group = new FormData();
      fd_group.top = new FormAttachment(0, 1);
      fd_group.left = new FormAttachment(0, 5);
      fd_group.right = new FormAttachment(100, -5);
      fd_group.height = 80;
      group.setLayoutData(fd_group);
      group.setLayout(new FormLayout());

      Label label = new Label(group, SWT.RIGHT);
      label.setText("节点名称：");
      FormData fd_label = new FormData();
      fd_label.right = new FormAttachment(15, 0);
      fd_label.top = new FormAttachment(0, 0);
      fd_label.left = new FormAttachment(0, 0);
      label.setLayoutData(fd_label);

      txtName = new Text(group, SWT.BORDER);
      txtName.setEditable(true);
      FormData fd_txtName = new FormData();
      fd_txtName.left = new FormAttachment(label, 0);
      fd_txtName.top = new FormAttachment(0, 0);
      fd_txtName.right = new FormAttachment(50, -10);
      txtName.setLayoutData(fd_txtName);

      Label label_1 = new Label(group, SWT.RIGHT);

      label_1.setText("说明：");
      FormData fd_label_1 = new FormData();
      fd_label_1.top = new FormAttachment(label, 7);
      fd_label_1.bottom = new FormAttachment(100, -2);
      fd_label_1.right = new FormAttachment(15);
      fd_label_1.left = new FormAttachment(0);
      label_1.setLayoutData(fd_label_1);

      txtDes = new StyledText(group, SWT.BORDER);
      FormData fd_txtDes = new FormData();
      fd_txtDes.bottom = new FormAttachment(100, -2);
      fd_txtDes.top = new FormAttachment(txtName, 5);
      fd_txtDes.right = new FormAttachment(100, -2);
      fd_txtDes.left = new FormAttachment(15);
      txtDes.setLayoutData(fd_txtDes);

      TabFolder tabFolder = new TabFolder(shell, SWT.NONE);
      tabFolder.addSelectionListener(new SelectionAdapter() {
         public void widgetSelected(SelectionEvent e) {
            TabItem ti = (TabItem) e.item;
            if (ti.getText().startsWith("运算列")) {
               tableViewer_1.getCellEditors()[3] = new UnionOperationExpressDialogCellEditor(tblOperator, nodeList,diagram);
            }
         }
      });
      FormData fd_tabFolder = new FormData();
      fd_tabFolder.left = new FormAttachment(0, 5);
      fd_tabFolder.bottom = new FormAttachment(100, -40);
      fd_tabFolder.right = new FormAttachment(100, -5);
      fd_tabFolder.top = new FormAttachment(group, 3);
      tabFolder.setLayoutData(fd_tabFolder);
      Label label_2 = new Label(group, SWT.RIGHT);
      label_2.setText("节点输出结构：");
      FormData fd_label_2 = new FormData();
      fd_label_2.top = new FormAttachment(0, 0);
      fd_label_2.left = new FormAttachment(50, 10);
      label_2.setLayoutData(fd_label_2);

      structCombo = new Combo(group, SWT.NONE);
      structCombo.addFocusListener(new FocusAdapter() {
         public void focusGained(FocusEvent e) {
            structCombo.removeAll();
            if (nodeList == null) {
               List<Connection> list = node.getIncomingConnections();
               if (list == null || list.size() == 0) {
                  MessageUtil.alert(shell, "提示", "找不到节点。");
                  return;
               }
               INode node = null;
               for (int i = 0; i < list.size(); i++) {
                  node = list.get(i).getSource();
                  structCombo.add(node.getName());
               }
            }
            for (int i = 0; i < nodeList.size(); i++) {
               INode structNode = nodeList.get(i);
               String nodeName = structNode.getName();
               structCombo.add(nodeName);
            }
         }
      });
      FormData fd_combo = new FormData();
      fd_combo.left = new FormAttachment(label_2, 0);
      fd_combo.right = new FormAttachment(100, 0);
      fd_combo.top = new FormAttachment(0, 0);
      structCombo.setLayoutData(fd_combo);

      TabItem tabItem = new TabItem(tabFolder, SWT.NONE);
      tabItem.setText("关联字段");

      Composite composite = new Composite(tabFolder, SWT.NONE);
      tabItem.setControl(composite);
      composite.setLayout(new FormLayout());

      /*
       * 读取上一结点的数目，动态生成每一结点列。 TableColumn tableColumn_1 = new
       * TableColumn(tblShowConfig, SWT.NONE); tableColumn_1.setWidth(150);
       * tableColumn_1.setText("");
       */

      final Group group_1 = new Group(composite, SWT.NONE);
      final FormData fd_group_1 = new FormData();
      fd_group_1.bottom = new FormAttachment(100, 0);
      fd_group_1.top = new FormAttachment(0, -6);
      fd_group_1.left = new FormAttachment(0, 0);
      fd_group_1.right = new FormAttachment(100, 0);
      group_1.setLayoutData(fd_group_1);
      group_1.setLayout(new FormLayout());

      Button btnLoad = new Button(group_1, SWT.NONE);
      final FormData fd_btnLoad = new FormData();
      fd_btnLoad.bottom = new FormAttachment(100, 0);
      fd_btnLoad.height=20;
      fd_btnLoad.width=20;
      fd_btnLoad.left = new FormAttachment(0, 0);
      btnLoad.setLayoutData(fd_btnLoad);
      btnLoad.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/table-import.png"));
      btnLoad.addSelectionListener(new SelectionAdapter() {
         @Override
         public void widgetSelected(final SelectionEvent e) {
            nodeList.clear();
            fieldList.clear();
            relateConfigList.clear();
            expressionList.clear();
            nodeId=null;
            int res = MessageUtil.comfirm(shell, "导入", "是否从上一环节导入字段信息?\r\n注意：已有字段信息将被覆盖!");
            if (res == SWT.YES) {
               List<Connection> list = node.getIncomingConnections();
               if (list == null || list.size() == 0) {
                  MessageUtil.alert(shell, "提示", "找不到节点。");
                  return;
               }
               INode node = null;
               CellEditor[] cellEditor = new CellEditor[list.size()];
               TableColumn[] tableColumns;
               nodeId = new String[list.size()];
               tableColumns = tblShowConfig.getColumns();
               if (tableColumns.length > 0) {
                  for (int i = 0; i < tableColumns.length; i++) {
                     tableColumns[i].dispose();
                  }
               }
               else tableColumns = new TableColumn[list.size()];
               for (int i = 0; i < list.size(); i++) {
                  node = list.get(i).getSource();
                  nodeId[i] = String.valueOf(node.getId());
                  nodeList.add(node);
                  tableColumns[i] = new TableColumn(tblShowConfig, SWT.NONE);
                  tableColumns[i].setWidth(150);
                  tableColumns[i].setText(node.getName());
                  List<Metadata> MetaList = node.getMeta();
                  String[] MetaName = new String[MetaList.size()];
                  for (int j = 0; j < MetaList.size(); j++) {
                     MetaName[j] = MetaList.get(j).getCnName();
                  }
                  cellEditor[i] = new ComboBoxCellEditor(tblShowConfig, MetaName, SWT.READ_ONLY);
                  fieldList.put(nodeId[i], MetaName);

               }
               tableViewer.setContentProvider(new ViewContentProvider());
               tableViewer.setLabelProvider(new UnionOperationLabelProvider());
               tableViewer.setColumnProperties(nodeId);
               tableViewer.setCellEditors(cellEditor);
               tableViewer.setCellModifier(new UnionOperation_RelateMetaCellModifier(tableViewer, nodeId, fieldList));
               relateConfigList.clear();
               tableViewer.refresh();
            }
         }

      });
      btnLoad.setToolTipText("从上一环节导入数据结构");

      final Button btnAddRelate = new Button(group_1, SWT.NONE);
      final FormData fd_btnAddRelate = new FormData();
      fd_btnAddRelate.bottom = new FormAttachment(100, 0);
      fd_btnAddRelate.height=20;
      fd_btnAddRelate.width=20;
      fd_btnAddRelate.left = new FormAttachment(btnLoad, 0);
      btnAddRelate.setLayoutData(fd_btnAddRelate);
      btnAddRelate.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/plus.png"));
      btnAddRelate.addSelectionListener(new SelectionAdapter() {
         public void widgetSelected(SelectionEvent e) {
            UnionOperationConfig UnionConfig = new UnionOperationConfig();
            Map<String, String> relateMetaList = new HashMap<String, String>();
            for (Map.Entry<String, String[]> entry : fieldList.entrySet()) {
               String key = entry.getKey();
               String[] value = entry.getValue();
               relateMetaList.put(key, value[0]);
            }
            UnionConfig.setRelateMetaList(relateMetaList);
            relateConfigList.add(UnionConfig);
            tableViewer.refresh();
         }
      });


      final Button btnDelRelate = new Button(group_1, SWT.NONE);
      final FormData fd_btnDelRelate = new FormData();
      fd_btnDelRelate.bottom = new FormAttachment(100, 0);
      fd_btnDelRelate.height=20;
      fd_btnDelRelate.width=20;
      fd_btnDelRelate.left = new FormAttachment(btnAddRelate, 0);
      btnDelRelate.setLayoutData(fd_btnDelRelate);
      btnDelRelate.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/minus.png"));
      btnDelRelate.addSelectionListener(new SelectionAdapter() {
         @Override
         public void widgetSelected(SelectionEvent e) {
            TableItem[] selectItems = tblShowConfig.getSelection();
            if (selectItems != null && selectItems.length > 0) {
               for (TableItem ti : selectItems) {
                  UnionOperationConfig o = (UnionOperationConfig) ti.getData();
                  relateConfigList.remove(o);
               }
               tableViewer.refresh();
            }
         }
      });
      
      tableViewer = new TableViewer(group_1, SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI);
      tblShowConfig = tableViewer.getTable();
      final FormData fd_tblShowConfig = new FormData();
      fd_tblShowConfig.bottom = new FormAttachment(100, -21);
      fd_tblShowConfig.top = new FormAttachment(0, -6);
      fd_tblShowConfig.right = new FormAttachment(100, 0);
      fd_tblShowConfig.left = new FormAttachment(0, 0);
      tblShowConfig.setLayoutData(fd_tblShowConfig);
      tblShowConfig.setLinesVisible(true);
      tblShowConfig.setHeaderVisible(true);
      tblShowConfig.setLinesVisible(true);
      tblShowConfig.setHeaderVisible(true);

      TabItem tabItem_1 = new TabItem(tabFolder, SWT.NONE);
      tabItem_1.setText("运算列");

      Composite composite_1 = new Composite(tabFolder, SWT.NONE);
      tabItem_1.setControl(composite_1);
      composite_1.setLayout(new FormLayout());
      CellEditor[] cellEditor1 = new CellEditor[4];

      final Group group_2 = new Group(composite_1, SWT.NONE);
      final FormData fd_group_2 = new FormData();
      fd_group_2.left = new FormAttachment(0, 0);
      fd_group_2.bottom = new FormAttachment(100, 0);
      fd_group_2.right = new FormAttachment(100, 0);
      fd_group_2.top = new FormAttachment(0, -6);
      group_2.setLayoutData(fd_group_2);
      group_2.setLayout(new FormLayout());
      final Button btnAddOperator = new Button(group_2, SWT.NONE);
      final FormData fd_btnAddOperator = new FormData();
      fd_btnAddOperator.bottom = new FormAttachment(100, 0);
      fd_btnAddOperator.height=20;
      fd_btnAddOperator.width=20;
      fd_btnAddOperator.left = new FormAttachment(0, 0);
      btnAddOperator.setLayoutData(fd_btnAddOperator);
      btnAddOperator.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/plus.png"));

      btnAddOperator.addSelectionListener(new SelectionAdapter() {
         public void widgetSelected(SelectionEvent e) {
            UnionOperationField ExpressionConfig = new UnionOperationField();
            expressionList.add(ExpressionConfig);
            tableViewer_1.refresh();
         }
      });

      final Button btnDelOperator = new Button(group_2, SWT.NONE);
      final FormData fd_btnDelOperator = new FormData();
      fd_btnDelOperator.bottom = new FormAttachment(100, 0);
      fd_btnDelOperator.height=20;
      fd_btnDelOperator.width=20;
      fd_btnDelOperator.left = new FormAttachment(btnAddOperator, 0);
      btnDelOperator.setLayoutData(fd_btnDelOperator);
      btnDelOperator.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/minus.png"));
      btnDelOperator.addSelectionListener(new SelectionAdapter() {
         @Override
         public void widgetSelected(SelectionEvent e) {
            TableItem[] selectItems = tblOperator.getSelection();
            if (selectItems != null && selectItems.length > 0) {
               for (TableItem ti : selectItems) {
                  UnionOperationField o = (UnionOperationField) ti.getData();
                  expressionList.remove(o);
               }
               tableViewer_1.refresh();
            }
         }
      });
      
      tableViewer_1 = new TableViewer(group_2, SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI);
      tableViewer_1.setLabelProvider(new OperatorLabelProvider());
      tableViewer_1.setContentProvider(new ViewContentProvider());
      tableViewer_1.setColumnProperties(OperatorLabelProvider.DATAFIELDS);
      tblOperator = tableViewer_1.getTable();
      final FormData fd_tblOperator = new FormData();
      fd_tblOperator.bottom = new FormAttachment(100, -21);
      fd_tblOperator.top = new FormAttachment(0, -6);
      fd_tblOperator.right = new FormAttachment(100, 0);
      fd_tblOperator.left = new FormAttachment(0, 0);
      tblOperator.setLayoutData(fd_tblOperator);

      cellEditor1[0] = new TextCellEditor(tblOperator);
      cellEditor1[1] = new TextCellEditor(tblOperator);
      cellEditor1[2] = new ComboBoxCellEditor(tblOperator, Consts.DATATYPE_LABEL, SWT.READ_ONLY);
      cellEditor1[3] = new UnionOperationExpressDialogCellEditor(tblOperator, nodeList,diagram);
      tableViewer_1.setCellEditors(cellEditor1);
      tableViewer_1.setCellModifier(new UnionOperation_ExpressionCellModifier(tableViewer_1));
      tblOperator.setLinesVisible(true);
      tblOperator.setHeaderVisible(true);
      tblOperator.setLinesVisible(true);
      tblOperator.setHeaderVisible(true);

      final TableColumn colCnName = new TableColumn(tblOperator, SWT.NONE);
      colCnName.setWidth(100);
      colCnName.setText("中文名");

      final TableColumn colFieldName = new TableColumn(tblOperator, SWT.NONE);
      colFieldName.setWidth(100);
      colFieldName.setText("字段名");

      final TableColumn colDataType = new TableColumn(tblOperator, SWT.NONE);
      colDataType.setWidth(72);
      colDataType.setText("数据类型");

      final TableColumn Expression = new TableColumn(tblOperator, SWT.NONE);
      Expression.setWidth(280);
      Expression.setText("表达式");

      /*
       * final TableColumn colVisible = new TableColumn(tblOperator, SWT.NONE);
       * colVisible.setWidth(72); colVisible.setText("输出");
       */

      /*
       * final TableColumn Expression = new TableColumn(tblOperator, SWT.NONE);
       * Expression.setWidth(500); Expression.setText("运算公式"); final TableColumn
       * resultColumnDef = new TableColumn(tblOperator, SWT.NONE);
       * resultColumnDef.setWidth(100); resultColumnDef.setText("结果列定义");
       */



      Button btnOk = new Button(shell, SWT.NONE);
      btnOk.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/tick.png"));
      btnOk.addSelectionListener(new SelectionAdapter() {
         @Override
         public void widgetSelected(SelectionEvent e) {
            save();
            close();
         }
      });
      FormData fd_btnOk = new FormData();
      fd_btnOk.width = 80;
      fd_btnOk.height = 28;
      fd_btnOk.bottom = new FormAttachment(100, -5);
      fd_btnOk.right = new FormAttachment(50, -20);
      btnOk.setLayoutData(fd_btnOk);
      btnOk.setText("确定(&O)");

      Button btnCancel = new Button(shell, SWT.NONE);
      btnCancel.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/cross.png"));
      btnCancel.addSelectionListener(new SelectionAdapter() {
         public void widgetSelected(SelectionEvent e) {
            close();
         }
      });
      FormData fd_btnCancel = new FormData();
      fd_btnCancel.width = 80;
      fd_btnCancel.height = 28;
      fd_btnCancel.bottom = new FormAttachment(100, -5);
      fd_btnCancel.left = new FormAttachment(50, 20);
      btnCancel.setLayoutData(fd_btnCancel);
      btnCancel.setText("取消(&C)");

      init();

   }

   private void save() {
      node.setName(txtName.getText().trim());
      node.setDescription(txtDes.getText().trim());
      nodeProperties.setNodeId(nodeId);
      nodeProperties.setUnionOperationConfigList(relateConfigList);
      nodeProperties.setFieldList(fieldList);
      nodeProperties.setNodeList(nodeList);
      nodeProperties.setUnionOperationFilterList(expressionList);
      nodeProperties.setOutNodeStruct(structCombo.getText());
   }

   private void init() {

      nodeProperties = (UnionOperationProperties) node.getProperties();
      txtName.setText(node.getName());
      txtDes.setText(node.getDescription());
      relateConfigList = new ArrayList<UnionOperationConfig>();
      outputNodeStruct = nodeProperties.getOutNodeStruct();
      if (outputNodeStruct == null) outputNodeStruct = "";
      structCombo.setText(outputNodeStruct);

      if (nodeProperties.getUnionOperationConfigList() != null && nodeProperties.getUnionOperationConfigList().size() > 0) {
         fieldList = nodeProperties.getFieldList();
         this.nodeList = nodeProperties.getNodeList();
         this.nodeId = nodeProperties.getNodeId();
         for (int i = 0; i < nodeProperties.getUnionOperationConfigList().size(); i++) {
            relateConfigList.add((UnionOperationConfig) nodeProperties.getUnionOperationConfigList().get(i));
         }
         INode node = null;
         CellEditor[] cellEditor = new CellEditor[nodeId.length];
         TableColumn[] tableColumns;
         tableColumns = tblShowConfig.getColumns();
         if (tableColumns.length > 0) {
            for (int i = 0; i < tableColumns.length; i++) {
               tableColumns[i].dispose();
            }
         }
         else tableColumns = new TableColumn[nodeId.length];
         for (int i = 0; i < nodeId.length; i++) {
            node = (INode) nodeProperties.getNodeList().get(i);
            tableColumns[i] = new TableColumn(tblShowConfig, SWT.NONE);
            tableColumns[i].setWidth(150);
            tableColumns[i].setText(node.getName());
            List<Metadata> MetaList = node.getMeta();
            String[] MetaName = new String[MetaList.size()];
            for (int j = 0; j < MetaList.size(); j++) {
               MetaName[j] = MetaList.get(j).getCnName();
            }
            cellEditor[i] = new ComboBoxCellEditor(tblShowConfig, MetaName, SWT.READ_ONLY);

         }
         tableViewer.setCellEditors(cellEditor);
      }
      else {
         nodeId = new String[] { "" };
         fieldList.put("", new String[1]);
      }

      expressionList = new ArrayList<UnionOperationField>();
      for (UnionOperationField df : nodeProperties.getUnionOperationFilterList()) {
         expressionList.add(df.clone());
      }
      tableViewer_1.setCellModifier(new UnionOperation_ExpressionCellModifier(tableViewer_1));
      tableViewer_1.setInput(expressionList);

      tableViewer.setContentProvider(new ViewContentProvider());
      tableViewer.setLabelProvider(new UnionOperationLabelProvider());
      tableViewer.setColumnProperties(nodeId);
      tableViewer.setCellModifier(new UnionOperation_RelateMetaCellModifier(tableViewer, nodeId, fieldList));
      tableViewer.setInput(relateConfigList);
      tableViewer.refresh();
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

   class UnionOperationLabelProvider extends LabelProvider implements ITableLabelProvider {
      public String getColumnText(Object obj, int columnIndex) {
         UnionOperationConfig dsi = (UnionOperationConfig) obj;
         return dsi.getFieldNameByNodeId(nodeId[columnIndex]) == null ? "" : dsi.getFieldNameByNodeId(nodeId[columnIndex]);
      }

      public String getText(Object element) {
         return element == null ? "" : element.toString();
      }

      public Image getColumnImage(Object obj, int index) {
         return null;
      }

   }
}

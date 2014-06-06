package com.liusy.analysismodel.template.ui;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.DialogCellEditor;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
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
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import com.swtdesigner.ResourceManager;
import com.liusy.analysismodel.Activator;
import com.liusy.analysismodel.template.commands.GenerateColumnNodeBeanEditCommand;
import com.liusy.analysis.template.model.Consts;
import com.liusy.analysis.template.model.Diagram;
import com.liusy.analysis.template.model.base.Metadata;
import com.liusy.analysis.template.model.connection.Connection;
import com.liusy.analysis.template.model.dialogProperties.GenerateColumnProperties;
import com.liusy.analysis.template.model.node.GenerateColumnNode;
import com.liusy.analysis.template.model.node.INode;
import com.liusy.analysis.template.model.vo.GenerateDataField;
import com.liusy.analysismodel.template.ui.contentProvider.BaseCellModifer;
import com.liusy.analysismodel.template.ui.contentProvider.BaseLabelProvider;
import com.liusy.analysismodel.template.ui.contentProvider.ViewContentProvider;
import com.liusy.analysismodel.template.ui.dialog.GenerateColumnDialog;
import com.liusy.analysismodel.util.MessageUtil;

public class GenerateColumnNodeDialog extends Dialog {

   private TableViewer              tvShowConfig;
   private Table                    tblShowConfig;
   private StyledText               txtDes;
   private Text                     txtName;
   private int                      result = 0;
   private GenerateColumnNodeBeanEditCommand  cmd;
   private GenerateColumnProperties nodeProperties;
   private GenerateColumnNode       node;
   private GenerateColumnNode       mianNode;
   private Diagram                  diagram;
   private List<GenerateDataField>  configList;

   /**
    * Create the dialog
    * 
    * @param parent
    * @param style
    */
   public GenerateColumnNodeDialog(Shell parent, int style) {
      super(parent, style);
   }

   /**
    * Create the dialog
    * 
    * @param parent
    */
   public GenerateColumnNodeDialog(Shell parent, GenerateColumnNode node, GenerateColumnNodeBeanEditCommand command) {

      this(parent, SWT.NONE);
      this.mianNode = node;
      this.cmd = command;
      this.diagram = node.getDiagram();
      try {
			this.node = (GenerateColumnNode)node.clone();
			nodeProperties = (GenerateColumnProperties)node.getProperties().clone();
		}
		catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

   }

   /**
    * Open the dialog
    * 
    * @return the result
    */
   public int open() {
      cmd.setNode(mianNode);
      cmd.setTempNode(node);
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

   //   /**
   //    * Create contents of the dialog
   //    */
   protected void createContents() {

      shell = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
      shell.setLayout(new FormLayout());
      shell.setSize(750, 470);
      shell.setText("生成列属性");

      final Group basicGroup = new Group(shell, SWT.NONE);
      basicGroup.setLayout(new FormLayout());
      final FormData fd_basicGroup = new FormData();
      fd_basicGroup.height = 70;
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

      CellEditor[] cellEditor = new CellEditor[5];

      final Group group = new Group(shell, SWT.NONE);
      final FormData fd_group = new FormData();
      fd_group.bottom = new FormAttachment(100, -42);
      fd_group.right = new FormAttachment(100, -5);
      fd_group.top = new FormAttachment(basicGroup, 0);
      fd_group.left = new FormAttachment(0, 5);
      group.setLayoutData(fd_group);
      group.setText("数据列");
      group.setLayout(new FormLayout());

      tvShowConfig = new TableViewer(group, SWT.MULTI | SWT.FULL_SELECTION | SWT.BORDER);
      tvShowConfig.setContentProvider(new ViewContentProvider());
      tvShowConfig.setLabelProvider(new ShowConfigLabelProvider());
      tvShowConfig.setColumnProperties(DATAFIELDS);
      tblShowConfig = tvShowConfig.getTable();
      final FormData fd_tblShowConfig = new FormData();
      fd_tblShowConfig.bottom = new FormAttachment(100, -21);
      fd_tblShowConfig.top = new FormAttachment(0, 2);
      fd_tblShowConfig.right = new FormAttachment(100, -1);
      fd_tblShowConfig.left = new FormAttachment(0, 1);
      tblShowConfig.setLayoutData(fd_tblShowConfig);
      cellEditor[0] = new TextCellEditor(tblShowConfig);
      cellEditor[1] = new TextCellEditor(tblShowConfig);
      cellEditor[2] = new ComboBoxCellEditor(tblShowConfig, Consts.DATATYPE_LABEL, SWT.READ_ONLY);
      cellEditor[3] = new ExpressionCellEditor(tblShowConfig);
      cellEditor[4] = new ComboBoxCellEditor(tblShowConfig, Consts.YESNO_LABEL, SWT.READ_ONLY);

      tvShowConfig.setCellEditors(cellEditor);
      tvShowConfig.setCellModifier(new ShowConfigCellModifier(tvShowConfig));
      tblShowConfig.setLinesVisible(true);
      tblShowConfig.setHeaderVisible(true);

      final TableColumn colCnName = new TableColumn(tblShowConfig, SWT.NONE);
      colCnName.setWidth(150);
      colCnName.setText("中文名");

      final TableColumn colFieldName = new TableColumn(tblShowConfig, SWT.NONE);
      colFieldName.setWidth(120);
      colFieldName.setText("字段名");

      final TableColumn colDataType = new TableColumn(tblShowConfig, SWT.NONE);
      colDataType.setWidth(72);
      colDataType.setText("数据类型");

      final TableColumn colExpression = new TableColumn(tblShowConfig, SWT.NONE);
      colExpression.setWidth(280);
      colExpression.setText("表达式");

      final TableColumn colVisible = new TableColumn(tblShowConfig, SWT.NONE);
      colVisible.setWidth(72);
      colVisible.setText("输出");

      final Button btnLoad = new Button(group, SWT.NONE);
      final FormData fd_btnLoad = new FormData();
      fd_btnLoad.height = 20;
      fd_btnLoad.top = new FormAttachment(tblShowConfig, 1);
      fd_btnLoad.width = 20;
      fd_btnLoad.left = new FormAttachment(0, 0);
      btnLoad.setLayoutData(fd_btnLoad);
      btnLoad.addSelectionListener(new SelectionAdapter() {
         public void widgetSelected(final SelectionEvent e) {
            int res = MessageUtil.comfirm(shell, "导入", "是否从上一环节导入字段信息?\r\n注意：已有字段信息将被覆盖!");
            if (res == SWT.YES) {
               List<Connection> list = node.getIncomingConnections();
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
               configList.clear();
               for (Metadata md : listMeta) {
                  configList.add(new GenerateDataField(md));
               }
               tvShowConfig.refresh();
            }
         }
      });
      btnLoad.setToolTipText("从上一环节导入数据结构");
      btnLoad.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/table-import.png"));

      final Button btnAdd = new Button(group, SWT.NONE);
      final FormData fd_btnAdd = new FormData();
      fd_btnAdd.height = 20;
      fd_btnAdd.width = 20;
      fd_btnAdd.top = new FormAttachment(tblShowConfig, 1);
      fd_btnAdd.left = new FormAttachment(0, 21);
      btnAdd.setLayoutData(fd_btnAdd);
      btnAdd.addSelectionListener(new SelectionAdapter() {
         public void widgetSelected(final SelectionEvent e) {
            GenerateDataField o = new GenerateDataField();
            o.setGenerate(true);
            configList.add(o);
            tvShowConfig.refresh();
         }
      });
      btnAdd.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/plus.png"));

      final Button btnDel = new Button(group, SWT.NONE);
      final FormData fd_btnDel = new FormData();
      fd_btnDel.height = 20;
      fd_btnDel.width = 20;
      fd_btnDel.top = new FormAttachment(tblShowConfig, 1);
      fd_btnDel.left = new FormAttachment(0, 42);
      btnDel.setLayoutData(fd_btnDel);
      btnDel.addSelectionListener(new SelectionAdapter() {
         public void widgetSelected(final SelectionEvent e) {
            TableItem[] selectItems = tblShowConfig.getSelection();
            if (selectItems != null && selectItems.length > 0) {
               for (TableItem ti : selectItems) {
                  GenerateDataField o = (GenerateDataField) ti.getData();
                  configList.remove(o);
               }
               tvShowConfig.refresh();
            }
         }
      });
      btnDel.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/minus.png"));
   }

   private void init() {
      txtName.setText(node.getName());
      txtDes.setText(node.getDescription());

      configList = new ArrayList<GenerateDataField>();

      for (int i = 0; i < nodeProperties.getFields().size(); i++) {
         configList.add(nodeProperties.getFields().get(i).clone());
      }

      tvShowConfig.setInput(configList);
   }

   private void save() {
      node.setName(txtName.getText().trim());
      node.setDescription(txtDes.getText().trim());
      nodeProperties.setFields(configList);
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

   class ShowConfigLabelProvider extends BaseLabelProvider implements ITableLabelProvider {
      public String getColumnText(Object obj, int columnIndex) {
         GenerateDataField dsi = (GenerateDataField) obj;
         switch (columnIndex) {
         case 0:
            return dsi.getCnName() == null ? "" : dsi.getCnName();
         case 1:
            return dsi.getName() == null ? "" : dsi.getName();
         case 2:
            return dsi.getDataType() == null ? "" : getLabel(Consts.DATATYPE_LABEL, Consts.DATATYPE, dsi.getDataType());
         case 3:
            return dsi.getExpression() == null ? "" : dsi.getExpression();
         case 4:
            return dsi.getVisible() == null ? Consts.NO : getLabel(Consts.YESNO_LABEL, Consts.YESNO, dsi.getVisible());
         }
         return null;
      }

   }

   public static String[] DATAFIELDS = new String[] { "cnName", "name", "dataType", "expression", "visible" };

   class ShowConfigCellModifier extends BaseCellModifer implements ICellModifier {

      public ShowConfigCellModifier(TableViewer tv) {
         this.tv = tv;
      }

      public Object getValue(Object element, String property) {
         GenerateDataField o = (GenerateDataField) element;
         if (property.equals("name")) return o.getName();
         else if (property.equals("cnName")) return o.getCnName();
         else if (property.equals("dataType")) return getIndex(Consts.DATATYPE, o.getDataType());
         else if (property.equals("visible")) return getIndex(Consts.YESNO, o.getVisible());
         else if (property.equals("expression")) return o.getExpression();
         else throw new RuntimeException("错误的列别名:" + property);
      }

      public void modify(Object element, String property, Object value) {
         TableItem item = (TableItem) element;
         GenerateDataField o = (GenerateDataField) item.getData();
         if (property.equals("name")) o.setName((String) value);
         else if (property.equals("cnName")) o.setCnName((String) value);
         else if (property.equals("expression")) o.setExpression((String) value);
         else if (property.equals("dataType")) o.setDataType(Consts.DATATYPE[(Integer) value]);
         else if (property.equals("visible")) o.setVisible(Consts.YESNO[(Integer) value]);
         else throw new RuntimeException("错误的列别名:" + property);
         tv.update(o, null);
      }
   }

   class ExpressionCellEditor extends DialogCellEditor {

      private Table table;

      public ExpressionCellEditor(Composite parent) {
         super(parent);
         this.table = (Table) parent;
      }

      protected Object openDialogBox(Control cellEditorWindow) {
         TableItem[] selectItems = table.getSelection();
         GenerateDataField o = null;
         if (selectItems != null && selectItems.length > 0) o = (GenerateDataField) selectItems[0].getData();
         if (o == null || o.isGenerate() == false) {
            MessageUtil.alert(cellEditorWindow.getShell(), "提示", "只有新增的计算列才能设置表达式。");
            return "";
         }

         GenerateColumnDialog gcd = new GenerateColumnDialog(cellEditorWindow.getShell(), o);
         GenerateDataField res = gcd.open();
         return res.getExpression();
      }
   }
}


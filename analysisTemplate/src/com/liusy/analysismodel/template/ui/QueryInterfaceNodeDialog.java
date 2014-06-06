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
import com.liusy.analysismodel.template.commands.NodePropertyEditCommand;
import com.liusy.analysis.template.model.Consts;
import com.liusy.analysis.template.model.Diagram;
import com.liusy.analysis.template.model.base.Metadata;
import com.liusy.analysis.template.model.connection.Connection;
import com.liusy.analysis.template.model.dialogProperties.QueryInterfaceProperties;
import com.liusy.analysis.template.model.node.INode;
import com.liusy.analysis.template.model.node.QueryInterfaceNode;
import com.liusy.analysis.template.model.vo.CodeSet;
import com.liusy.analysis.template.model.vo.DetailLinkField;
import com.liusy.analysis.template.model.vo.FieldConfig;
import com.liusy.analysismodel.template.ui.contentProvider.BaseCellModifer;
import com.liusy.analysismodel.template.ui.contentProvider.BaseLabelProvider;
import com.liusy.analysismodel.template.ui.contentProvider.NumberVerifier;
import com.liusy.analysismodel.template.ui.contentProvider.ViewContentProvider;
import com.liusy.analysismodel.template.ui.dialog.DetailLinkDialog;
import com.liusy.analysismodel.template.ui.editor.DetailLinkCellEditor;
import com.liusy.analysismodel.util.MessageUtil;

public class QueryInterfaceNodeDialog extends Dialog {

   private TableViewer              tvShowConfig;
   private Table                    tblShowConfig;
   private StyledText               txtDes;
   private Text                     txtName;
   private int                      result = 0;
   private QueryInterfaceProperties nodeProperties;
   private QueryInterfaceNode       node;
   private Diagram                  diagram;
   private List<FieldConfig>        configList;
   private NodePropertyEditCommand  cmd;

   /**
    * Create the dialog
    * 
    * @param parent
    * @param style
    */
   public QueryInterfaceNodeDialog(Shell parent, int style) {
      super(parent, style);
   }

   /**
    * Create the dialog
    * 
    * @param parent
    */
   public QueryInterfaceNodeDialog(Shell parent, QueryInterfaceNode node, NodePropertyEditCommand command) {

      this(parent, SWT.NONE);
      this.node = node;
      this.cmd = command;
      this.diagram = node.getDiagram();
   }

   /**
    * Open the dialog
    * 
    * @return the result
    */
   public int open() {
      nodeProperties = node.getProperties();

      List<CodeSet> cs = diagram.getCodeSetList();
      if (cs == null) cs = new ArrayList<CodeSet>();
      codeSetNames = new String[cs.size() + 1];
      codeSetValues = new String[cs.size() + 1];
      codeSetNames[0] = "";
      codeSetValues[0] = "";
      for (int i = 0; i < cs.size(); i++) {
         codeSetNames[i + 1] = cs.get(i).getCnName();
         codeSetValues[i + 1] = cs.get(i).getName();
      }

      configList = new ArrayList<FieldConfig>();
      for (int i = 0; i < nodeProperties.getFieldConfigs().size(); i++) {
         configList.add(nodeProperties.getFieldConfigs().get(i).clone());
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

   //   /**
   //    * Create contents of the dialog
   //    */
   protected void createContents() {

      shell = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL | SWT.RESIZE);
      shell.setLayout(new FormLayout());
      shell.setSize(806, 470);
      shell.setText("查询配置属性");

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

      final Group group = new Group(shell, SWT.NONE);
      group.setText("列表配置");
      final FormData fd_group = new FormData();
      fd_group.top = new FormAttachment(basicGroup, 5);
      fd_group.left = new FormAttachment(0, 5);
      fd_group.right = new FormAttachment(100, -5);
      fd_group.bottom = new FormAttachment(100, -40);

      group.setLayoutData(fd_group);
      group.setLayout(new FormLayout());

      tvShowConfig = new TableViewer(group, SWT.MULTI | SWT.FULL_SELECTION | SWT.BORDER);
      tvShowConfig.setContentProvider(new ViewContentProvider());
      tvShowConfig.setLabelProvider(new ShowConfigLabelProvider());
      tvShowConfig.setColumnProperties(DATAFIELDS);
      tblShowConfig = tvShowConfig.getTable();

      CellEditor[] cellEditor = new CellEditor[8];
      cellEditor[0] = new TextCellEditor(tblShowConfig);
      cellEditor[1] = new TextCellEditor(tblShowConfig);
      cellEditor[2] = new TextCellEditor(tblShowConfig);
      cellEditor[3] = new ComboBoxCellEditor(tblShowConfig, Consts.ALIGN_LABEL, SWT.READ_ONLY);
      cellEditor[4] = new TextCellEditor(tblShowConfig);
      cellEditor[5] = new ComboBoxCellEditor(tblShowConfig, Consts.YESNO_LABEL, SWT.READ_ONLY);
      cellEditor[6] = new ComboBoxCellEditor(tblShowConfig, codeSetNames, SWT.READ_ONLY);
      cellEditor[7] = new DetailLinkCellEditor(tblShowConfig, configList, diagram.getNodes());
      Text text1 = (Text) cellEditor[4].getControl();
      text1.addVerifyListener(new NumberVerifier());

      tvShowConfig.setCellEditors(cellEditor);
      tvShowConfig.setCellModifier(new ShowConfigCellModifier(tvShowConfig));

      final FormData fd_table_1 = new FormData();
      fd_table_1.bottom = new FormAttachment(100, -21);
      fd_table_1.top = new FormAttachment(0, 1);
      fd_table_1.right = new FormAttachment(100, -1);
      fd_table_1.left = new FormAttachment(0, 0);
      tblShowConfig.setLayoutData(fd_table_1);
      tblShowConfig.setLinesVisible(true);
      tblShowConfig.setHeaderVisible(true);

      final TableColumn colCnName = new TableColumn(tblShowConfig, SWT.NONE);
      colCnName.setWidth(120);
      colCnName.setText("中文名");

      final TableColumn colFieldName = new TableColumn(tblShowConfig, SWT.NONE);
      colFieldName.setWidth(120);
      colFieldName.setText("列名");

      final TableColumn colDataType = new TableColumn(tblShowConfig, SWT.NONE);
      colDataType.setWidth(60);
      colDataType.setText("数据格式");

      final TableColumn colAlign = new TableColumn(tblShowConfig, SWT.NONE);
      colAlign.setWidth(70);
      colAlign.setText("对齐方式");

      final TableColumn colWidth = new TableColumn(tblShowConfig, SWT.NONE);
      colWidth.setWidth(40);
      colWidth.setText("宽度");

      final TableColumn colVisible = new TableColumn(tblShowConfig, SWT.NONE);
      colVisible.setWidth(70);
      colVisible.setText("是否显示");

      final TableColumn colCodeSet = new TableColumn(tblShowConfig, SWT.NONE);
      colCodeSet.setWidth(100);
      colCodeSet.setText("代码集");

      final TableColumn colLink = new TableColumn(tblShowConfig, SWT.NONE);
      colLink.setWidth(120);
      colLink.setText("链接");

      final Button btnLoad = new Button(group, SWT.NONE);
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
               if (listMeta.size() == 0 || listMeta == null) {
                  MessageUtil.alert(shell, "提示", "节点元数据匹配错误。");
               }
               else {
                  for (Metadata md : listMeta) {
                     configList.add(new FieldConfig(md));
                  }
               }
               tvShowConfig.refresh();
            }
         }
      });
      btnLoad.setToolTipText("从上一环节导入数据结构");
      btnLoad.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/table-import.png"));
      final FormData fd_btnLoad = new FormData();
      fd_btnLoad.top = new FormAttachment(tblShowConfig, 1);
      fd_btnLoad.width = 20;
      fd_btnLoad.height = 20;
      fd_btnLoad.left = new FormAttachment(0, 1);
      btnLoad.setLayoutData(fd_btnLoad);

      final Button btnDel = new Button(group, SWT.NONE);
      btnDel.addSelectionListener(new SelectionAdapter() {
         public void widgetSelected(final SelectionEvent e) {
            TableItem[] selectItems = tblShowConfig.getSelection();
            if (selectItems != null && selectItems.length > 0) {
               for (TableItem ti : selectItems) {
                  FieldConfig o = (FieldConfig) ti.getData();
                  configList.remove(o);
               }
               tvShowConfig.refresh();
            }
         }
      });
      btnDel.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/minus.png"));
      final FormData fd_btnDel = new FormData();
      fd_btnDel.top = new FormAttachment(tblShowConfig, 1);
      fd_btnDel.width = 20;
      fd_btnDel.height = 20;
      fd_btnDel.left = new FormAttachment(btnLoad, 1);
      btnDel.setLayoutData(fd_btnDel);

      final Button btnUp = new Button(group, SWT.NONE);
      btnUp.addSelectionListener(new SelectionAdapter() {
         public void widgetSelected(final SelectionEvent e) {
            if (tblShowConfig.getSelectionCount() == 1) {
               int idx = tblShowConfig.getSelectionIndex();
               if (idx > 0) {
                  FieldConfig o = (FieldConfig) tblShowConfig.getSelection()[0].getData();
                  configList.remove(o);
                  configList.add(idx - 1, o);
                  tvShowConfig.refresh();
               }
            }
         }
      });
      btnUp.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/arrow-090.png"));
      FormData fd_btnUp;
      fd_btnUp = new FormData();
      fd_btnUp.top = new FormAttachment(tblShowConfig, 1);
      fd_btnUp.width = 20;
      fd_btnUp.height = 20;
      fd_btnUp.left = new FormAttachment(btnDel, 1);
      btnUp.setLayoutData(fd_btnUp);

      final Button btnDown = new Button(group, SWT.NONE);
      btnDown.addSelectionListener(new SelectionAdapter() {
         public void widgetSelected(final SelectionEvent e) {
            if (tblShowConfig.getSelectionCount() == 1) {
               int idx = tblShowConfig.getSelectionIndex();
               if (idx < tblShowConfig.getItemCount()) {
                  FieldConfig o = (FieldConfig) tblShowConfig.getSelection()[0].getData();
                  configList.remove(o);
                  configList.add(idx + 1, o);
                  tvShowConfig.refresh();
               }
            }
         }
      });
      btnDown.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/arrow-270.png"));
      final FormData fd_btnDown = new FormData();
      fd_btnDown.top = new FormAttachment(tblShowConfig, 1);
      fd_btnDown.width = 20;
      fd_btnDown.height = 20;
      fd_btnDown.left = new FormAttachment(btnUp, 1);
      btnDown.setLayoutData(fd_btnDown);

   }

   private void init() {
      txtName.setText(node.getName());
      txtDes.setText(node.getDescription());

      tvShowConfig.setInput(configList);

   }

   private void save() {
      node.setName(txtName.getText().trim());
      node.setDescription(txtDes.getText().trim());
      nodeProperties.setFieldConfigs(configList);
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

   private String[] codeSetNames;
   private String[] codeSetValues;

   class ShowConfigLabelProvider extends BaseLabelProvider implements ITableLabelProvider {
      public ShowConfigLabelProvider() {
      }

      public String getColumnText(Object obj, int columnIndex) {

         FieldConfig dsi = (FieldConfig) obj;
         switch (columnIndex) {
            case 0:
               return dsi.getCnName() == null ? "" : dsi.getCnName();
            case 1:
               return dsi.getName() == null ? "" : dsi.getName();
            case 2:
               return dsi.getDataFormat() == null ? "" : dsi.getDataFormat();
            case 3:
               return dsi.getAlign() == null ? "" : getLabel(Consts.ALIGN_LABEL, Consts.ALIGN, dsi.getAlign());
            case 4:
               return dsi.getWidth() == null ? "" : dsi.getWidth();
            case 5:
               return dsi.getVisible() == null ? Consts.NO : getLabel(Consts.YESNO_LABEL, Consts.YESNO, dsi.getVisible());
            case 6:
               return dsi.getCodeSet() == null ? "" : getLabel(codeSetNames, codeSetValues, dsi.getCodeSet());
            case 7:
               if (null!=dsi.getDetailLink().getLinkType()&&dsi.getDetailLink().getLinkType().equals(Consts.DETAIL_LINKTYPE_NODE)) {
                  if (dsi.getDetailLink().getNodeName() == null) return "";
                  else return dsi.getDetailLink().getNodeName();
               }
               else {
                  if (dsi.getDetailLink().getNodeName() == null) return "";
                  else return dsi.getDetailLink().getNodeName();
               }
         }
         return null;
      }

   }

   public static String[] DATAFIELDS = new String[] { "cnName", "name", "dataFormat", "align", "width", "visible", "codeset", "link" };

   class ShowConfigCellModifier extends BaseCellModifer implements ICellModifier {

      public ShowConfigCellModifier(TableViewer tv) {
         this.tv = tv;
      }

      public Object getValue(Object element, String property) {
         FieldConfig o = (FieldConfig) element;
         if (property.equals("name")) return o.getName();
         else if (property.equals("cnName")) return o.getCnName();
         else if (property.equals("dataFormat")) return o.getDataFormat();
         else if (property.equals("align")) return getIndex(Consts.ALIGN, o.getAlign());
         else if (property.equals("visible")) return getIndex(Consts.YESNO, o.getVisible());
         else if (property.equals("width")) return o.getWidth();
         else if (property.equals("codeset")) return getIndex(codeSetValues, o.getCodeSet());
         else if (property.equals("link")) {
            if (o.getDetailLink() == null) {
               return "";
            }
            else {
               if (Consts.DETAIL_LINKTYPE_NODE.equals(o.getDetailLink().getLinkType())) return o.getDetailLink().getNodeName();
               else return o.getDetailLink().getDiagramName();
            }
         }
         else throw new RuntimeException("错误的列别名:" + property);
      }

      public void modify(Object element, String property, Object value) {
         TableItem item = (TableItem) element;
         FieldConfig o = (FieldConfig) item.getData();
         if (property.equals("name")) o.setName((String) value);
         else if (property.equals("cnName")) o.setCnName((String) value);
         else if (property.equals("dataFormat")) o.setDataFormat((String) value);
         else if (property.equals("width")) o.setWidth((String) value);
         else if (property.equals("codeset")) o.setCodeSet(codeSetValues[(Integer) value]);
         else if (property.equals("link")) {
            if (value instanceof DetailLinkField) o.setDetailLink((DetailLinkField) value);
         }
         else if (property.equals("align")) o.setAlign(Consts.ALIGN[(Integer) value]);
         else if (property.equals("visible")) o.setVisible(Consts.YESNO[(Integer) value]);
         else throw new RuntimeException("错误的列别名:" + property);
         tv.update(o, null);
      }
   }
}

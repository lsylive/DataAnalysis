package com.liusy.analysismodel.template.ui;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
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
import com.liusy.analysis.template.model.dialogProperties.PathSearchProperties;
import com.liusy.analysis.template.model.node.INode;
import com.liusy.analysis.template.model.node.PathSearchNode;
import com.liusy.analysis.template.model.vo.GenerateDataField;
import com.liusy.analysis.template.model.vo.SearchField;
import com.liusy.analysismodel.template.ui.QueryInterfaceNodeDialog.ShowConfigCellModifier;
import com.liusy.analysismodel.template.ui.contentProvider.BaseCellModifer;
import com.liusy.analysismodel.template.ui.contentProvider.BaseLabelProvider;
import com.liusy.analysismodel.template.ui.contentProvider.NumberVerifier;
import com.liusy.analysismodel.template.ui.contentProvider.ViewContentProvider;
import com.liusy.analysismodel.template.ui.dialog.FilterExpressionDialog;
import com.liusy.analysismodel.util.MessageUtil;

public class PathSearchNodeDialog extends Dialog {

   private Text                    txtDeep;
   private TableViewer             tvShowConfig;
   private Table                   tblShowConfig;
   private StyledText              txtDes;
   private Text                    txtName;
   private Text                    txtExpressStart;
   private Text                    txtExpressEnd;
   private int                     result = 0;
   private PathSearchProperties    nodeProperties;
   private PathSearchNode          node;
   private Diagram                 diagram;
   private List<SearchField>       configList;
   private NodePropertyEditCommand cmd;

   /**
    * Create the dialog
    * 
    * @param parent
    * @param style
    */
   public PathSearchNodeDialog(Shell parent, int style) {
      super(parent, style);
   }

   /**
    * Create the dialog
    * 
    * @param parent
    */
   public PathSearchNodeDialog(Shell parent, PathSearchNode node, NodePropertyEditCommand command) {

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
      shell.setSize(750, 470);
      shell.setText("路径搜索算法属性");

      final Group basicGroup = new Group(shell, SWT.NONE);
      basicGroup.setLayout(new FormLayout());
      final FormData fd_basicGroup = new FormData();
      fd_basicGroup.height = 110;
      fd_basicGroup.top = new FormAttachment(0, 1);
      fd_basicGroup.right = new FormAttachment(100, -5);
      fd_basicGroup.left = new FormAttachment(0, 5);
      basicGroup.setLayoutData(fd_basicGroup);

      final Label label = new Label(basicGroup, SWT.RIGHT);
      final FormData fd_label = new FormData();
      fd_label.top = new FormAttachment(0, 5);
      fd_label.right = new FormAttachment(15, 0);
      fd_label.left = new FormAttachment(0, 0);
      label.setLayoutData(fd_label);
      label.setText("节点名称：");

      txtName = new Text(basicGroup, SWT.BORDER);
      final FormData fd_txtName = new FormData();
      fd_txtName.top = new FormAttachment(0, 0);
      fd_txtName.right = new FormAttachment(50, 0);
      fd_txtName.left = new FormAttachment(label, 0);
      txtName.setLayoutData(fd_txtName);
      txtName.setEditable(true);

      final Label label_1 = new Label(basicGroup, SWT.RIGHT);
      final FormData fd_label_1 = new FormData();
      fd_label_1.top = new FormAttachment(0, 5);
      fd_label_1.right = new FormAttachment(65, -1);
      fd_label_1.left = new FormAttachment(50, 0);
      label_1.setLayoutData(fd_label_1);
      label_1.setText("搜索深度：");

      txtDeep = new Text(basicGroup, SWT.BORDER);
      final FormData fd_txtDeep = new FormData();
      fd_txtDeep.right = new FormAttachment(100, -1);
      fd_txtDeep.left = new FormAttachment(label_1, 0);
      fd_txtDeep.top = new FormAttachment(0, 0);
      txtDeep.setLayoutData(fd_txtDeep);

      final Label lblExpressStart = new Label(basicGroup, SWT.RIGHT);
      final FormData fd_lblExpressStart = new FormData();
      fd_lblExpressStart.top = new FormAttachment(label, 10);
      fd_lblExpressStart.right = new FormAttachment(15, 0);
      fd_lblExpressStart.left = new FormAttachment(0, 0);
      lblExpressStart.setLayoutData(fd_lblExpressStart);
      lblExpressStart.setText("起始值：");

      final Button btnExpressStart = new Button(basicGroup, SWT.NONE);
      btnExpressStart.addSelectionListener(new SelectionAdapter() {
         public void widgetSelected(final SelectionEvent e) {
            FilterExpressionDialog gcd = new FilterExpressionDialog(shell, txtExpressStart.getText());
            String res = gcd.open();
            txtExpressStart.setText(res);
         }
      });
      btnExpressStart.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/document-horizontal-text.png"));
      final FormData fd_btnExpressStart = new FormData();
      fd_btnExpressStart.top = new FormAttachment(txtName, 5);
      fd_btnExpressStart.right = new FormAttachment(50, -2);
      fd_btnExpressStart.width = 23;
      fd_btnExpressStart.height = 23;
      btnExpressStart.setLayoutData(fd_btnExpressStart);

      txtExpressStart = new Text(basicGroup, SWT.BORDER);
      txtExpressStart.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
      final FormData fd_txtExpressStart = new FormData();
      fd_txtExpressStart.top = new FormAttachment(txtName, 5);
      fd_txtExpressStart.right = new FormAttachment(btnExpressStart, -1);
      fd_txtExpressStart.left = new FormAttachment(lblExpressStart, 0);
      txtExpressStart.setLayoutData(fd_txtExpressStart);
      txtExpressStart.setEditable(false);

      final Label lblExpressEnd = new Label(basicGroup, SWT.RIGHT);
      final FormData fd_lblExpressEnd = new FormData();
      fd_lblExpressEnd.top = new FormAttachment(label, 10);
      fd_lblExpressEnd.right = new FormAttachment(65, 0);
      fd_lblExpressEnd.left = new FormAttachment(50, 0);
      lblExpressEnd.setLayoutData(fd_lblExpressEnd);
      lblExpressEnd.setText("结束值：");

      final Button btnExpressEnd = new Button(basicGroup, SWT.NONE);
      btnExpressEnd.addSelectionListener(new SelectionAdapter() {
         public void widgetSelected(final SelectionEvent e) {
            FilterExpressionDialog gcd = new FilterExpressionDialog(shell, txtExpressEnd.getText());
            String res = gcd.open();
            txtExpressEnd.setText(res);
         }
      });
      btnExpressEnd.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/document-horizontal-text.png"));
      final FormData fd_btnExpressEnd = new FormData();
      fd_btnExpressEnd.top = new FormAttachment(txtName, 5);
      fd_btnExpressEnd.right = new FormAttachment(100, -2);
      fd_btnExpressEnd.width = 23;
      fd_btnExpressEnd.height = 23;
      btnExpressEnd.setLayoutData(fd_btnExpressEnd);

      txtExpressEnd = new Text(basicGroup, SWT.BORDER);
      txtExpressEnd.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
      final FormData fd_txtExpressEnd = new FormData();
      fd_txtExpressEnd.top = new FormAttachment(txtName, 5);
      fd_txtExpressEnd.right = new FormAttachment(btnExpressEnd, -1);
      fd_txtExpressEnd.left = new FormAttachment(lblExpressEnd, 0);
      txtExpressEnd.setLayoutData(fd_txtExpressEnd);
      txtExpressEnd.setEditable(false);

      final Label lblDescription = new Label(basicGroup, SWT.RIGHT);
      final FormData fd_lblDescription = new FormData();
      fd_lblDescription.top = new FormAttachment(lblExpressStart, 10);
      fd_lblDescription.bottom = new FormAttachment(100, -2);
      fd_lblDescription.left = new FormAttachment(0, 0);
      fd_lblDescription.right = new FormAttachment(15, 0);
      lblDescription.setLayoutData(fd_lblDescription);
      lblDescription.setText("说明：");

      txtDes = new StyledText(basicGroup, SWT.BORDER);
      final FormData fd_txtDes = new FormData();
      fd_txtDes.top = new FormAttachment(txtExpressStart, 5);
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
      tblShowConfig.setLinesVisible(true);
      tblShowConfig.setHeaderVisible(true);

      CellEditor[] cellEditor = new CellEditor[4];
      cellEditor[0] = new TextCellEditor(tblShowConfig, SWT.READ_ONLY);
      cellEditor[1] = new TextCellEditor(tblShowConfig, SWT.READ_ONLY);
      cellEditor[2] = new TextCellEditor(tblShowConfig, SWT.READ_ONLY);
      cellEditor[3] = new ComboBoxCellEditor(tblShowConfig, Consts.SEARCH_LABEL, SWT.READ_ONLY);

      tvShowConfig.setCellEditors(cellEditor);
      tvShowConfig.setCellModifier(new ShowConfigCellModifier(tvShowConfig));

      final TableColumn colCnName = new TableColumn(tblShowConfig, SWT.NONE);
      colCnName.setWidth(180);
      colCnName.setText("中文名");

      final TableColumn colFieldName = new TableColumn(tblShowConfig, SWT.NONE);
      colFieldName.setWidth(180);
      colFieldName.setText("字段名");

      final TableColumn colDataType = new TableColumn(tblShowConfig, SWT.NONE);
      colDataType.setWidth(100);
      colDataType.setText("数据类型");

      final TableColumn colSearch = new TableColumn(tblShowConfig, SWT.NONE);
      colSearch.setWidth(120);
      colSearch.setText("搜索字段");

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
                  configList.add(new SearchField(md));
               }
               tvShowConfig.refresh();
            }
         }
      });
      btnLoad.setToolTipText("从上一环节导入数据结构");
      btnLoad.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/table-import.png"));
   }

   private void init() {
      txtName.setText(node.getName());
      txtDes.setText(node.getDescription());
      txtExpressStart.setText(nodeProperties.getStartValue() == null ? "" : nodeProperties.getStartValue());
      txtExpressEnd.setText(nodeProperties.getEndValue() == null ? "" : nodeProperties.getEndValue());
      txtDeep.setText(String.valueOf(nodeProperties.getSearchDepth()));
      configList = new ArrayList<SearchField>();

      for (int i = 0; i < nodeProperties.getFields().size(); i++) {
         SearchField mt = (SearchField) nodeProperties.getFields().get(i).clone();
         configList.add(mt);
      }

      tvShowConfig.setInput(configList);
   }

   private void save() {
      node.setName(txtName.getText().trim());
      node.setDescription(txtDes.getText().trim());
      nodeProperties.setStartValue(txtExpressStart.getText().trim());
      nodeProperties.setEndValue(txtExpressEnd.getText().trim());
      nodeProperties.setFields(configList);
      nodeProperties.setSearchDepth(Integer.parseInt(txtDeep.getText().trim()));
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
         SearchField dsi = (SearchField) obj;
         switch (columnIndex) {
         case 0:
            return dsi.getCnName() == null ? "" : dsi.getCnName();
         case 1:
            return dsi.getName() == null ? "" : dsi.getName();
         case 2:
            return dsi.getDataType() == null ? "" : getLabel(Consts.DATATYPE_LABEL, Consts.DATATYPE, dsi.getDataType());
         case 3:
            return dsi.getSearchSequence() == null ? "" : getLabel(Consts.SEARCH_LABEL, Consts.SEARCH, dsi.getSearchSequence());
         }
         return null;
      }

   }

   public static String[] DATAFIELDS = new String[] { "cnName", "name", "dataType", "search" };

   class ShowConfigCellModifier extends BaseCellModifer implements ICellModifier {

      public ShowConfigCellModifier(TableViewer tv) {
         this.tv = tv;
      }

      public Object getValue(Object element, String property) {
         SearchField o = (SearchField) element;
         if (property.equals("name")) return o.getName();
         else if (property.equals("cnName")) return o.getCnName();
         else if (property.equals("search")) return getIndex(Consts.SEARCH, o.getSearchSequence());
         else if (property.equals("dataType")) return getIndex(Consts.DATATYPE, o.getDataType());
         else throw new RuntimeException("错误的列别名:" + property);
      }

      public void modify(Object element, String property, Object value) {
         TableItem item = (TableItem) element;
         SearchField o = (SearchField) item.getData();
         if (property.equals("name")) o.setName((String) value);
         else if (property.equals("cnName")) o.setCnName((String) value);
         else if (property.equals("search")) o.setSearchSequence(Consts.SEARCH[(Integer) value]);
         else if (property.equals("dataType")) o.setDataType(Consts.DATATYPE[(Integer) value]);
         else throw new RuntimeException("错误的列别名:" + property);
         tv.update(o, null);
      }
   }
}

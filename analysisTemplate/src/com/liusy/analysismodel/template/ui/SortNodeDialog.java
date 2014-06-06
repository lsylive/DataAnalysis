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
import com.liusy.analysis.template.model.dialogProperties.SortProperties;
import com.liusy.analysis.template.model.node.INode;
import com.liusy.analysis.template.model.node.SortNode;
import com.liusy.analysis.template.model.vo.DataField;
import com.liusy.analysismodel.template.ui.contentProvider.BaseCellModifer;
import com.liusy.analysismodel.template.ui.contentProvider.BaseLabelProvider;
import com.liusy.analysismodel.template.ui.contentProvider.ViewContentProvider;
import com.liusy.analysismodel.util.MessageUtil;

public class SortNodeDialog extends Dialog {

   private TableViewer             tvShowConfig;
   private Table                   tblShowConfig;
   private StyledText              txtDes;
   private Text                    txtName;
   private int                     result = 0;
   private SortProperties          nodeProperties;
   private SortNode                node;
   private Diagram                 diagram;
   private List<DataField>         configList;
   private NodePropertyEditCommand cmd;

   /**
    * Create the dialog
    * 
    * @param parent
    * @param style
    */
   public SortNodeDialog(Shell parent, int style) {
      super(parent, style);
   }

   /**
    * Create the dialog
    * 
    * @param parent
    */
   public SortNodeDialog(Shell parent, SortNode node, NodePropertyEditCommand command) {

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

      shell = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
      shell.setLayout(new FormLayout());
      shell.setSize(750, 470);
      shell.setText("����ڵ�����");

      final Group basicGroup = new Group(shell, SWT.NONE);
      basicGroup.setLayout(new FormLayout());
      final FormData fd_basicGroup = new FormData();
      fd_basicGroup.height = 90;
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
      label.setText("�ڵ����ƣ�");

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
      lblDescription.setText("˵����");

      txtDes = new StyledText(basicGroup, SWT.BORDER);
      final FormData fd_txtDes = new FormData();
      fd_txtDes.top = new FormAttachment(txtName, 5);
      fd_txtDes.left = new FormAttachment(lblDescription, 0);
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
      btnOk.setText("ȷ��(&O)");
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
      btnCancel.setText("ȡ��(&C)");
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
      group.setText("������");
      group.setLayout(new FormLayout());

      tvShowConfig = new TableViewer(group, SWT.MULTI | SWT.FULL_SELECTION | SWT.BORDER);
      tvShowConfig.setContentProvider(new ViewContentProvider());
      tvShowConfig.setLabelProvider(new ShowConfigLabelProvider());
      tvShowConfig.setColumnProperties(DATAFIELDS);
      tblShowConfig = tvShowConfig.getTable();
      CellEditor[] cellEditor2 = new CellEditor[4];
      cellEditor2[0] = new TextCellEditor(tblShowConfig, SWT.READ_ONLY);
      cellEditor2[1] = new TextCellEditor(tblShowConfig, SWT.READ_ONLY);
      cellEditor2[2] = new TextCellEditor(tblShowConfig, SWT.READ_ONLY);
      cellEditor2[3] = new ComboBoxCellEditor(tblShowConfig, Consts.SORTDIRECT_LABEL, SWT.READ_ONLY);
      tvShowConfig.setCellEditors(cellEditor2);
      tvShowConfig.setCellModifier(new ShowConfigCellModifier(tvShowConfig));

      final FormData fd_tblShowConfig = new FormData();
      fd_tblShowConfig.bottom = new FormAttachment(100, -21);
      fd_tblShowConfig.top = new FormAttachment(0, 2);
      fd_tblShowConfig.right = new FormAttachment(100, -1);
      fd_tblShowConfig.left = new FormAttachment(0, 1);
      tblShowConfig.setLayoutData(fd_tblShowConfig);
      tblShowConfig.setLinesVisible(true);
      tblShowConfig.setHeaderVisible(true);

      final TableColumn colCnName = new TableColumn(tblShowConfig, SWT.NONE);
      colCnName.setWidth(150);
      colCnName.setText("������");

      final TableColumn colFieldName = new TableColumn(tblShowConfig, SWT.NONE);
      colFieldName.setWidth(150);
      colFieldName.setText("�ֶ���");

      final TableColumn colDataType = new TableColumn(tblShowConfig, SWT.NONE);
      colDataType.setWidth(100);
      colDataType.setText("��������");

      final TableColumn colSoft = new TableColumn(tblShowConfig, SWT.NONE);
      colSoft.setWidth(100);
      colSoft.setText("����");

      final Button btnLoad = new Button(group, SWT.NONE);
      final FormData fd_btnLoad = new FormData();
      fd_btnLoad.height = 20;
      fd_btnLoad.top = new FormAttachment(tblShowConfig, 1);
      fd_btnLoad.width = 20;
      fd_btnLoad.left = new FormAttachment(0, 0);
      btnLoad.setLayoutData(fd_btnLoad);
      btnLoad.addSelectionListener(new SelectionAdapter() {
         public void widgetSelected(final SelectionEvent e) {
            int res = MessageUtil.comfirm(shell, "����", "�Ƿ����һ���ڵ����ֶ���Ϣ?\r\nע�⣺�����ֶ���Ϣ��������!");
            if (res == SWT.YES) {
               List<Connection> list = node.getIncomingConnections();
               if (list == null || list.size() == 0) {
                  MessageUtil.alert(shell, "��ʾ", "�Ҳ����ڵ㡣");
                  return;
               }
               else if (list.size() > 1) {
                  MessageUtil.alert(shell, "��ʾ", "��һ���ڵĽڵ㲻�ܶ���һ����");
                  return;
               }
               INode node = list.get(0).getSource();
               List<Metadata> listMeta = node.getMeta();
               configList.clear();
               for (Metadata md : listMeta) {
                  configList.add(new DataField(md));
               }
               tvShowConfig.refresh();
            }
         }
      });
      btnLoad.setToolTipText("����һ���ڵ������ݽṹ");
      btnLoad.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/table-import.png"));
   }

   private void init() {
      txtName.setText(node.getName());
      txtDes.setText(node.getDescription());

      configList = new ArrayList<DataField>();

      for (int i = 0; i < nodeProperties.getFields().size(); i++) {
         DataField mt = (DataField) nodeProperties.getFields().get(i).clone();
         configList.add(mt);
      }

      tvShowConfig.setInput(configList);
   }

   private void save() {
      node.setName(txtName.getText().trim());
      node.setDescription(txtDes.getText().trim());
      nodeProperties.setFields(configList);
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
         DataField dsi = (DataField) obj;
         switch (columnIndex) {
         case 0:
            return dsi.getCnName() == null ? "" : dsi.getCnName();
         case 1:
            return dsi.getName() == null ? "" : dsi.getName();
         case 2:
            return dsi.getDataType() == null ? "" : getLabel(Consts.DATATYPE_LABEL, Consts.DATATYPE, dsi.getDataType());
         case 3:
            return dsi.getSortDirect() == null ? "" : getLabel(Consts.SORTDIRECT_LABEL, Consts.SORTDIRECT, dsi.getSortDirect());
         }
         return null;
      }

   }

   public static String[] DATAFIELDS = new String[] { "cnName", "name", "dataType", "sort" };

   class ShowConfigCellModifier extends BaseCellModifer implements ICellModifier {
      protected Table tb;

      public ShowConfigCellModifier(TableViewer tv) {
         this.tv = tv;
         this.tb = tv.getTable();
      }

      public Object getValue(Object element, String property) {
         DataField o = (DataField) element;
         if (property.equals("name")) return o.getName();
         else if (property.equals("cnName")) return o.getCnName();
         else if (property.equals("sort")) return getIndex(Consts.SORTDIRECT, o.getSortDirect());
         else if (property.equals("dataType")) return getIndex(Consts.DATATYPE, o.getDataType());
         else throw new RuntimeException("������б���:" + property);
      }

      public void modify(Object element, String property, Object value) {
         TableItem item = (TableItem) element;
         DataField o = (DataField) item.getData();
         if (property.equals("name")) o.setName((String) value);
         else if (property.equals("cnName")) o.setCnName((String) value);
         else if (property.equals("sort")) {
            TableItem[] items = tb.getItems();
            for (TableItem ti : items) {
               DataField tidata = (DataField) ti.getData();
               tidata.setSortDirect("");
            }
            o.setSortDirect(Consts.SORTDIRECT[(Integer) value]);
//            tv.refresh();
         }
         else if (property.equals("dataType")) o.setDataType(Consts.DATATYPE[(Integer) value]);
         else throw new RuntimeException("������б���:" + property);
         tv.update(o, null);
      }
   }
}

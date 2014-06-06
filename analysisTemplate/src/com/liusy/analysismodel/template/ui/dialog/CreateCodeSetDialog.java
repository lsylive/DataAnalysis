package com.liusy.analysismodel.template.ui.dialog;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
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
import com.liusy.analysis.template.model.vo.Code;
import com.liusy.analysis.template.model.vo.CodeSet;
import com.liusy.analysismodel.template.ui.contentProvider.BaseCellModifer;
import com.liusy.analysismodel.template.ui.contentProvider.BaseLabelProvider;
import com.liusy.analysismodel.template.ui.contentProvider.ViewContentProvider;
import com.liusy.analysismodel.util.MessageUtil;

public class CreateCodeSetDialog extends Dialog {

   private Text        txtCnName;
   private Text        txtName;
   private Table       tblCodeSet;
   protected CodeSet   codeSet;
   protected Shell     shell;
   private TableViewer tvCodeSet;
   private List<Code>  codes  = new ArrayList<Code>();
   private int         result = 0;

   public CreateCodeSetDialog(Shell parent, int style) {
      super(parent, style);
   }

   public CreateCodeSetDialog(Shell parent, CodeSet codeSet) {
      this(parent, SWT.NONE);
      this.codeSet = codeSet;
      for (Code c : codeSet.getCodes()) {
         codes.add((Code) c.clone());
      }
   }

   public CodeSet open() {
      createContents();
      init();
      shell.setLocation(getParentLocation());
      shell.open();
      shell.layout();
      Display display = getParent().getDisplay();
      while (!shell.isDisposed()) {
         if (!display.readAndDispatch()) display.sleep();
      }
      return codeSet;
   }

   protected void createContents() {
      shell = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
      shell.setLayout(new FormLayout());
      shell.setSize(424, 375);
      shell.setText("生成代码集");

      final Label label_5 = new Label(shell, SWT.RIGHT);
      final FormData fd_label_5 = new FormData();
      fd_label_5.top = new FormAttachment(0, 5);
      fd_label_5.right = new FormAttachment(20, 0);
      fd_label_5.left = new FormAttachment(0, 0);
      label_5.setLayoutData(fd_label_5);
      label_5.setText("中文名称：");

      txtCnName = new Text(shell, SWT.BORDER);
      final FormData fd_txtCnName = new FormData();
      fd_txtCnName.top = new FormAttachment(label_5, 0, SWT.TOP);
      fd_txtCnName.right = new FormAttachment(100, -1);
      fd_txtCnName.left = new FormAttachment(20, 0);
      txtCnName.setLayoutData(fd_txtCnName);

      final Label label_6 = new Label(shell, SWT.RIGHT);
      final FormData fd_label_6 = new FormData();
      fd_label_6.top = new FormAttachment(label_5, 8);
      fd_label_6.right = new FormAttachment(20, 0);
      fd_label_6.left = new FormAttachment(0, 0);
      label_6.setLayoutData(fd_label_6);
      label_6.setText("英文名称：");

      txtName = new Text(shell, SWT.BORDER);
      final FormData fd_txtName = new FormData();
      fd_txtName.top = new FormAttachment(txtCnName, 3);
      fd_txtName.right = new FormAttachment(100, -1);
      fd_txtName.left = new FormAttachment(20, 0);
      txtName.setLayoutData(fd_txtName);

      final Group group_1 = new Group(shell, SWT.NONE);
      group_1.setText(" 代码值 ");
      group_1.setLayout(new FormLayout());
      final FormData fd_group_1 = new FormData();
      fd_group_1.bottom = new FormAttachment(100, -35);
      fd_group_1.top = new FormAttachment(txtName, 0);
      fd_group_1.right = new FormAttachment(100, -1);
      fd_group_1.left = new FormAttachment(0, 1);
      group_1.setLayoutData(fd_group_1);

      tvCodeSet = new TableViewer(group_1, SWT.MULTI | SWT.FULL_SELECTION | SWT.BORDER);
      tvCodeSet.setContentProvider(new ViewContentProvider());
      tvCodeSet.setLabelProvider(new CodesetLabelProvider());
      tvCodeSet.setColumnProperties(DATAFIELDS);
      tblCodeSet = tvCodeSet.getTable();
      CellEditor[] cellEditor = new CellEditor[2];
      cellEditor[0] = new TextCellEditor(tblCodeSet);
      cellEditor[1] = new TextCellEditor(tblCodeSet);
      tvCodeSet.setCellEditors(cellEditor);
      tvCodeSet.setCellModifier(new CodesetCellModifier(tvCodeSet));

      final FormData fd_table = new FormData();
      fd_table.bottom = new FormAttachment(100, -21);
      fd_table.top = new FormAttachment(0, 2);
      fd_table.right = new FormAttachment(100, -1);
      fd_table.left = new FormAttachment(0, 1);
      tblCodeSet.setLayoutData(fd_table);
      tblCodeSet.setLinesVisible(true);
      tblCodeSet.setHeaderVisible(true);

      final TableColumn newColumnTableColumn = new TableColumn(tblCodeSet, SWT.NONE);
      newColumnTableColumn.setWidth(200);
      newColumnTableColumn.setText("代码名称");

      final TableColumn newColumnTableColumn_1 = new TableColumn(tblCodeSet, SWT.NONE);
      newColumnTableColumn_1.setWidth(200);
      newColumnTableColumn_1.setText("代码值");

      final Button btnCodeAdd = new Button(group_1, SWT.NONE);
      btnCodeAdd.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/plus.png"));
      final FormData fd_btnCodeAdd = new FormData();
      fd_btnCodeAdd.bottom = new FormAttachment(100, -1);
      fd_btnCodeAdd.height = 20;
      fd_btnCodeAdd.width = 20;
      fd_btnCodeAdd.left = new FormAttachment(0, 1);
      btnCodeAdd.setLayoutData(fd_btnCodeAdd);
      btnCodeAdd.addSelectionListener(new SelectionAdapter() {
         public void widgetSelected(final SelectionEvent e) {
            Code o = new Code();
            codes.add(o);
            tvCodeSet.refresh();
         }
      });

      final Button btnCodeDel = new Button(group_1, SWT.NONE);
      btnCodeDel.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/minus.png"));
      FormData fd_btnCodeDel;
      fd_btnCodeDel = new FormData();
      fd_btnCodeDel.bottom = new FormAttachment(100, -1);
      fd_btnCodeDel.height = 20;
      fd_btnCodeDel.width = 20;
      fd_btnCodeDel.left = new FormAttachment(btnCodeAdd, 0);
      btnCodeDel.setLayoutData(fd_btnCodeDel);
      btnCodeDel.addSelectionListener(new SelectionAdapter() {
         public void widgetSelected(final SelectionEvent e) {
            TableItem[] selectItems = tblCodeSet.getSelection();
            if (selectItems != null && selectItems.length > 0) {
               for (TableItem ti : selectItems) {
                  Code o = (Code) ti.getData();
                  codes.remove(o);
               }
               tvCodeSet.refresh();
            }
         }
      });

      final Button btnOk = new Button(shell, SWT.NONE);
      btnOk.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/tick.png"));
      final FormData fd_btnOk = new FormData();
      fd_btnOk.height = 25;
      fd_btnOk.width = 80;
      fd_btnOk.bottom = new FormAttachment(100, -5);
      fd_btnOk.right = new FormAttachment(50, -10);
      btnOk.setLayoutData(fd_btnOk);
      btnOk.setText("确定(&O)");
      btnOk.addListener(SWT.Selection, new Listener() {
         public void handleEvent(Event e) {
            ok();
         }
      });

      final Button btnCancel = new Button(shell, SWT.NONE);
      btnCancel.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/cross.png"));
      final FormData fd_btnCancel = new FormData();
      fd_btnCancel.height = 25;
      fd_btnCancel.width = 80;
      fd_btnCancel.bottom = new FormAttachment(100, -5);
      fd_btnCancel.left = new FormAttachment(50, 10);
      btnCancel.setLayoutData(fd_btnCancel);
      btnCancel.setText("取消(&C)");
      btnCancel.addListener(SWT.Selection, new Listener() {
         public void handleEvent(Event e) {
            codeSet = null;
            shell.dispose();
         }
      });
   }

   private void init() {
      txtCnName.setText(codeSet.getCnName() == null ? "" : codeSet.getCnName());
      txtName.setText(codeSet.getName() == null ? "" : codeSet.getName());
      tvCodeSet.setInput(codes);
   }

   private void ok() {
      if (txtCnName.getText() == null || txtName.getText() == null || txtCnName.getText().equals("") || txtName.getText().equals("")) {
         MessageUtil.alert(shell, "提示", "请输入代码集的中文名和英文名。");
         return;
      }
      codeSet.setCnName(txtCnName.getText() == null ? "" : txtCnName.getText());
      codeSet.setName(txtName.getText() == null ? "" : txtName.getText());
      for (Code c : codes) {
         c.setCodeSet(codeSet);
      }
      codeSet.setCodes(codes);
      shell.dispose();
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
         Code code = (Code) obj;
         switch (columnIndex) {
         case 0:
            return code.getKey() == null ? "" : code.getKey();
         case 1:
            return code.getValue() == null ? "" : code.getValue();
         }
         return null;
      }

      public String getText(Object element) {
         return element == null ? "" : element.toString();
      }

      public Image getColumnImage(Object obj, int index) {
         return null;
      }
   }

   public static String[] DATAFIELDS = new String[] { "key", "value" };

   class CodesetCellModifier extends BaseCellModifer implements ICellModifier {

      public CodesetCellModifier(TableViewer tv) {
         this.tv = tv;
      }

      public Object getValue(Object element, String property) {
         Code o = (Code) element;
         if (property.equals("key")) return o.getKey();
         else if (property.equals("value")) return o.getValue();
         else throw new RuntimeException("错误的列别名:" + property);
      }

      public void modify(Object element, String property, Object value) {
         TableItem item = (TableItem) element;
         Code o = (Code) item.getData();
         if (property.equals("value")) o.setValue((String) value);
         else if (property.equals("key")) o.setKey((String) value);
         else throw new RuntimeException("错误的列别名:" + property);
         tv.update(o, null);
      }
   }
}

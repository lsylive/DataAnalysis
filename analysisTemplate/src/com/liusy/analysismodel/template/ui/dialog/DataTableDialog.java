package com.liusy.analysismodel.template.ui.dialog;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
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
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import com.swtdesigner.ResourceManager;
import com.liusy.analysismodel.Activator;
import com.liusy.analysis.template.model.vo.DataTable;
import com.liusy.analysismodel.template.ui.contentProvider.ViewContentProvider;
import com.liusy.analysismodel.template.ui.labelProvider.DataTableLabelProvider;
import com.liusy.analysismodel.util.DbConnectionManager;

public class DataTableDialog extends Dialog {

   private Table           table;
   protected DataTable     result;
   protected Shell         shell;
   private TableViewer     tableViewer;
   private List<DataTable> beanList;

   /**
    * Create the dialog
    * 
    * @param parent
    * @param style2
    */
   public DataTableDialog(Shell parent, int style) {
      super(parent, style);
   }

   /**
    * Create the dialog
    * 
    * @param parent
    */
   public DataTableDialog(Shell parent) {
      this(parent, SWT.NONE);
   }

   /**
    * Open the dialog
    * 
    * @return the result
    */
   public DataTable open() {
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
      shell = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
      shell.setLayout(new FormLayout());
      shell.setSize(419, 375);
      shell.setText("选择表");

      final Group group_1 = new Group(shell, SWT.NONE);
      group_1.setLayout(new FormLayout());
      final FormData fd_group_1 = new FormData();
      fd_group_1.bottom = new FormAttachment(100, -35);
      fd_group_1.top = new FormAttachment(0, 0);
      fd_group_1.right = new FormAttachment(100, -1);
      fd_group_1.left = new FormAttachment(0, 1);
      group_1.setLayoutData(fd_group_1);

      tableViewer = new TableViewer(group_1, SWT.FULL_SELECTION | SWT.BORDER);
      tableViewer.setContentProvider(new ViewContentProvider());
      tableViewer.setLabelProvider(new DataTableLabelProvider());
      table = tableViewer.getTable();
      final FormData fd_table = new FormData();
      fd_table.bottom = new FormAttachment(100, -1);
      fd_table.top = new FormAttachment(0, -5);
      fd_table.right = new FormAttachment(100, -1);
      fd_table.left = new FormAttachment(0, 1);
      table.setLayoutData(fd_table);
      table.setLinesVisible(true);
      table.setHeaderVisible(true);

      final TableColumn newColumnTableColumn = new TableColumn(table, SWT.NONE);
      newColumnTableColumn.setWidth(200);
      newColumnTableColumn.setText("中文名");

      final TableColumn newColumnTableColumn_1 = new TableColumn(table, SWT.NONE);
      newColumnTableColumn_1.setWidth(100);
      newColumnTableColumn_1.setText("表名");

      final TableColumn newColumnTableColumn_2 = new TableColumn(table, SWT.NONE);
      newColumnTableColumn_2.setWidth(80);
      newColumnTableColumn_2.setText("分类");

      final Button btnOk = new Button(shell, SWT.NONE);
      btnOk.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/tick.png"));
      final FormData fd_btnOk = new FormData();
      fd_btnOk.height = 25;
      fd_btnOk.width = 80;
      fd_btnOk.bottom = new FormAttachment(100, -5);
      fd_btnOk.right = new FormAttachment(50, -10);
      btnOk.setLayoutData(fd_btnOk);
      btnOk.setText("选择(&O)");
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
            shell.dispose();
         }
      });
      tableViewer.setInput(getTableNames());
   }

   private void ok() {
      if (table.getSelectionCount() == 0) {
         MessageBox dialog = new MessageBox(shell);
         dialog.setText("提示");
         dialog.setMessage("请选择一张数据表。");
         dialog.open();
         return;
      }
      else {
         DataTable dt = (DataTable) table.getSelection()[0].getData();
         result = dt;
         shell.dispose();
      }
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

   private List<DataTable> getTableNames() {
      List<DataTable> list = new ArrayList<DataTable>();
      Connection conn = null;
      Statement stat = null;
      ResultSet rs = null;
      String sql = "select t.ID as id,t.NAME as name,t.CN_NAME as cname,c.name as catalogName";
      sql += " from t_resource_table t,t_standard_category c";
      sql += " where t.CATEGORY_ID=c.id order by c.name,t.CN_NAME";
      Integer itmp;
      String stmp;

      try {
         conn = DbConnectionManager.getConnection();
         if (conn != null) {
            stat = conn.createStatement();
            rs = stat.executeQuery(sql);
            while (rs.next()) {
               DataTable tableBean = new DataTable();

               itmp = rs.getInt("id");
               if (rs.wasNull()) tableBean.setId("");
               else tableBean.setId(itmp.toString());

               stmp = rs.getString("name");
               if (rs.wasNull()) tableBean.setName("");
               else tableBean.setName(stmp);

               stmp = rs.getString("cname");
               if (rs.wasNull()) tableBean.setCnName("");
               else tableBean.setCnName(stmp);

               stmp = rs.getString("catalogName");
               if (rs.wasNull()) tableBean.setCatalogName("");
               else tableBean.setCatalogName(stmp);

               list.add(tableBean);
            }
         }
      }
      catch (Exception e) {
         System.out.println("数据表访问出错：" + e.getMessage());
      }
      return list;
   }

}

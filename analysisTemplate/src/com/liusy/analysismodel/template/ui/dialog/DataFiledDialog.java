package com.liusy.analysismodel.template.ui.dialog;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;

import com.liusy.analysis.template.model.vo.DataField;
import com.liusy.analysismodel.template.ui.contentProvider.ViewContentProvider;
import com.liusy.analysismodel.template.ui.labelProvider.DataFieldLabelProvider;
import com.liusy.analysismodel.util.DbConnectionManager;
import com.liusy.analysismodel.util.XmlEditUtil;

public class DataFiledDialog extends Dialog {

   private Table table;
   private Text tableHd;
   private Text txtDataSet;
   private Text txtDataSource;
   protected Text result;
   protected Shell  shell;
   protected Text tableTxt;
   protected Text dataSource;
   private TableViewer tableViewer;
   private Vector<DataField> beanList;
   private int type;

   /**
    * Create the dialog
    * @param parent
    * @param style
    */
   public DataFiledDialog(Shell parent, int style,Text text) {
      super(parent, style);
      this.result = text;
   }

   /**
    * Create the dialog
    * @param parent
    */
   public DataFiledDialog(Shell parent,Text text,Text tableTxt,Text dataBase,Text tableId) {
      this(parent, SWT.NONE,text);
      this.dataSource = dataBase;
      this.tableTxt = tableTxt;
      tableHd = tableId;
   }

   /**
    * Open the dialog
    * @return the result
    */
   public Object open() {
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
      shell.setSize(500, 375);
      shell.setText("字段信息");

      final Group group = new Group(shell, SWT.NONE);
      group.setText("数据源基本信息");
      group.setBounds(10, 0, 474, 72);

      final Label label = new Label(group, SWT.NONE);
      label.setText("数据源：");
      label.setBounds(10, 28, 55, 12);

      txtDataSource = new Text(group, SWT.BORDER);
      txtDataSource.setBounds(71, 25, 80, 20);
      txtDataSource.setText(dataSource.getText().trim());

      final Label label_1 = new Label(group, SWT.NONE);
      label_1.setText("数据集：");
      label_1.setBounds(238, 28, 48, 12);

      txtDataSet = new Text(group, SWT.BORDER);
      txtDataSet.setBounds(303, 25, 80, 20);
      txtDataSet.setText(this.tableTxt.getText().trim());

      final Group group_1 = new Group(shell, SWT.NONE);
      group_1.setText("字段列表");
      group_1.setBounds(10, 86, 474, 176);

      tableViewer = new TableViewer(group_1, SWT.FULL_SELECTION | SWT.BORDER);
      table = tableViewer.getTable();
      table.setLinesVisible(true);
      table.setHeaderVisible(true);
      table.setBounds(10, 22, 454, 144);

      final TableColumn newColumnTableColumn = new TableColumn(table, SWT.NONE);
      newColumnTableColumn.setWidth(100);
      newColumnTableColumn.setText("字段名称");

      final TableColumn newColumnTableColumn_1 = new TableColumn(table, SWT.NONE);
      newColumnTableColumn_1.setWidth(100);
      newColumnTableColumn_1.setText("数据类型");

      final TableColumn newColumnTableColumn_2 = new TableColumn(table, SWT.NONE);
      newColumnTableColumn_2.setWidth(100);
      newColumnTableColumn_2.setText("长度");

      final Group group_2 = new Group(shell, SWT.NONE);
      group_2.setText("操作");
      group_2.setBounds(10, 271, 474, 62);

//      final Button button = new Button(group_2, SWT.NONE);
//      button.addSelectionListener(new SelectionAdapter() {
//         public void widgetSelected(final SelectionEvent e) {
//            result.setText("ID");
////            result.setData("colID", "2");
//            shell.close();
//         }
//      });
//      button.setText("选择");
//      button.setBounds(21, 225, 48, 22);

      final Button button_1 = new Button(group_2, SWT.NONE);
      button_1.addSelectionListener(new SelectionAdapter() {
         public void widgetSelected(final SelectionEvent e) {
            IStructuredSelection obj = (IStructuredSelection)tableViewer.getSelection();
            DataField bean = (DataField) obj.getFirstElement();
            if (bean != null) {
               result.setText(bean.getName());
               result.setData("colID", bean.getId());
               shell.close();
            }
         }
      });
      button_1.setText("选择");
      button_1.setBounds(315, 30, 48, 22);

      final Button button_2 = new Button(group_2, SWT.NONE);
      button_2.addSelectionListener(new SelectionAdapter() {
         public void widgetSelected(final SelectionEvent e) {
            shell.close();
         }
      });
      button_2.setText("关闭");
      button_2.setBounds(416, 30, 48, 22);
      //
      addContent();
   }
   public void addContent() {
      beanList = new Vector<DataField>();
      String sql = "select t1.id,t1.name,t1.cn_name,t1.data_length,t1.codeset_id from t_resource_column t1"+
    	  " where t1.table_id="+tableHd.getText().trim();
      
      try {
         DataSourceBean bean = XmlEditUtil.getDataSourceByName(dataSource.getText().trim());
         Connection conn=null;
         Statement statement = null;
         ResultSet rs = null;
         conn =  DbConnectionManager.getConnection();
         if (conn != null) {
            try {
               statement = conn.createStatement();
               rs = statement.executeQuery(sql);
               while (rs.next()) {
            	   DataField fieldBean = new DataField();
//            	   fieldBean.setId(rs.getInt("id"));
                   fieldBean.setName(rs.getString("name"));
                   fieldBean.setCnName(rs.getString("cn_name"));
                   fieldBean.setLength(rs.getInt("data_length"));
                   beanList.add(fieldBean);
               }
            } catch(Exception e2) {
               e2.printStackTrace();
            } finally {
               rs.close();
               statement.close();
               conn.close();
            }
         }
         
      } catch(Exception e) {
         e.printStackTrace();
      }
      
      tableViewer.setContentProvider(new ViewContentProvider());
      tableViewer.setLabelProvider(new DataFieldLabelProvider());
      tableViewer.setInput(beanList);
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
}
